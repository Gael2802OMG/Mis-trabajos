import gym
import numpy as np
import tensorflow as tf
from collections import deque
import random

# Definimos la red neuronal Q
class QNetwork(tf.keras.Model):
    def __init__(self, input_size, output_size):
        super(QNetwork, self).__init__()
        self.fc1 = tf.keras.layers.Dense(input_size, activation='relu')
        self.fc2 = tf.keras.layers.Dense(output_size, activation='softmax')

    def call(self, x):
        x = self.fc1(x)
        x = self.fc2(x)
        return x

# Función para crear el modelo
def create_model(input_size, output_size):
    model = QNetwork(input_size, output_size)
    model.build((None, input_size))
    return model

# Función de exploración epsilon-greedy
def epsilon_greedy(q_values, epsilon):
    if np.random.random() < epsilon:
        return np.random.randint(len(q_values))
    else:
        return np.argmax(q_values)

# Definimos los hiperparámetros
gamma = 0.99
epsilon = 1.0
epsilon_min = 0.01
epsilon_decay = 0.995
batch_size = 32
memory = deque(maxlen=10000)
env = gym.make('CartPole-v1')
input_size = env.observation_space.shape[0]
output_size = env.action_space.n

model = create_model(input_size, output_size)
optimizer = tf.keras.optimizers.Adam(learning_rate=0.001)

# Función para entrenar el modelo
def train_model():
    if len(memory) < batch_size:
        return
    
    batch = random.choices(memory, k=batch_size)

    states = np.array([experience[0] for experience in batch])
    actions = np.array([experience[1] for experience in batch])
    rewards = np.array([experience[2] for experience in batch])
    next_states = np.array([experience[3] for experience in batch])
    dones = np.array([experience[4] for experience in batch])
    
    



    with tf.GradientTape() as tape:
        q_values = model(states)
        next_q_values = model(next_states)
        
        # Calcular el índice para actualizar los valores en q_values
        indices = tf.range(batch_size)
        indices = tf.reshape(indices, [-1, 1])

        # Calcular los valores de actualización
        update_values = rewards[:, None]
        update_values += (1 - dones[:, None]) * gamma * tf.reduce_max(next_q_values, axis=1, keepdims=True)
        # Expandir update_values a lo largo de la dimensión 1
        # Eliminar la dimensión adicional de updates
        update_values = tf.squeeze(update_values, axis=2)
        indices = tf.squeeze(indices, axis=1)
        # Actualizar q_values con los valores calculados
    
 

        print("Shapes before update:", q_values.shape, indices.shape, update_values.shape)
        # Actualizar q_values con los valores calculados
        q_values_updated = tf.tensor_scatter_nd_update(q_values, indices, update_values)

        # Calcular la pérdida
        loss = tf.reduce_mean(tf.square(q_values - tf.stop_gradient(q_values_updated)))
    
    # Calcular gradientes y aplicar la actualización
    grads = tape.gradient(loss, model.trainable_variables)
    optimizer.apply_gradients(zip(grads, model.trainable_variables))

# Entrenamiento del modelo
for episode in range(1000):
    state, _ = env.reset()
    done = False
    total_reward = 0
    
    while not done:
        q_values = model.predict(np.expand_dims(state, axis=0))
        action = epsilon_greedy(q_values[0], epsilon)
        next_state, reward, _, done, _ = env.step(action)
        
        memory.append((state, action, reward, next_state, done))
        train_model()
        
        state = next_state
        total_reward += reward
        
        epsilon = max(epsilon_min, epsilon * epsilon_decay)

    print(f"Episode: {episode}, Total Reward: {total_reward}")
