import tensorflow as tf
from keras.utils import to_categorical
import sklearn as sk
from sklearn.model_selection import train_test_split
from sklearn.utils import check_X_y
import matplotlib.pyplot as plt
import numpy as np
import pandas as pd
import pygame as py
import gym
from typing import TypeVar
import random
import os

class Agent:
    """
    Define una clase Agent básica.
    lleva la cuenta de la recompensa acumulada
    """
    def __init__(self,env, model=None):
        self.total_reward = 0.0
        self.reward = 0.0
        self.total_steps = 0
        self.current_observation = None
        self.env = env
        self.model = model
       
    def action_train(self):
        return self.env.action_space.sample()
    
    def action(self):
        if self.current_observation is None:
            action = self.env.action_space.sample()
        
        else:
            self.current_observation = np.expand_dims(self.current_observation, axis=0)
            prob_action = self.model.predict(self.current_observation)
            action = np.argmin(prob_action)
            
        return action
   
    def take_reward(self):
        angulo = int(self.current_observation[2] * 100)
        umbral = 50  # Umbral de inclinación
        max_recompensa = 1.0  # Recompensa máxima cuando el ángulo es 0
        min_recompensa = 0.0  # Recompensa mínima cuando el ángulo supera el umbral

        # Calcular la recompensa en función del ángulo
        if angulo == 0:
            return max_recompensa
        
        elif angulo > umbral:
            return min_recompensa
        
        else:
            # Calcular la recompensa como una función lineal del ángulo
            return max_recompensa - (max_recompensa / umbral) * abs(angulo)

    def take_action(self):
        action = self.action()
        #print(action)
        observation, _, is_done, _, _ = self.env.step(action)
        self.current_observation = observation

        self.reward = self.take_reward()
        #print('reward: {:.2f}'.format(self.reward))
        self.total_reward += self.reward
        
        self.total_steps += 1
        return observation, action, is_done

    def get_reward(self):
        return self.reward

    def get_total_reward(self):
        return self.total_reward    
    
    def get_current_observation(self):
        return self.current_observation
    
    def get_total_steps(self):
        return self.total_steps
    
class NeuronalNetwork():
    def __init__(self, num_inputs, num_outputs, optimizer='adam',loss=tf.keras.losses.CategoricalCrossentropy(), metrics=['accuracy']):
        self.dense1 = tf.keras.layers.Dense(num_inputs, activation = 'relu', input_shape=(num_inputs,))
        #self.dense2 = tf.keras.layers.Dense(64,activation='relu')
        self.dense3 = tf.keras.layers.Dense(num_outputs, activation='sigmoid')
        self.optimizer = optimizer
        self.loss = loss
        self.metrics = metrics

    def compile_model(self):
        model = tf.keras.Sequential([
            self.dense1,
            #self.dense2,
            self.dense3
        ])

        model.compile(optimizer=self.optimizer,
                      loss = self.loss,
                      metrics = self.metrics)
        
        return model
    
    def fit_model(self, x_train, y_train, x_vali, y_vali, epochs_f = 100, batch_size_f=25):
        model = self.compile_model()
        history = model.fit(x_train, y_train, epochs=epochs_f, batch_size = batch_size_f, validation_data=(x_vali,y_vali))
        return history, model
        
def observation(agent):
    observation_redim = agent.get_current_observation() *100
    return observation_redim

def get_data():
    env = gym.make('CartPole-v1')

    env.reset()
    agent = Agent(env)
    num_episodes = 1000

    observation_list = []
    action_list = []

    # Obteniendo datos de entrenamiento:
    for _ in range(num_episodes):
        is_done = False
        while not is_done:
            observation, action, is_done = agent.take_action()
            observation_list.append(observation)
            action_list.append(action)
            """
            if(agent.get_reward() < 0.1):
                is_done = True
                env.reset()
            """
        env.reset()    

    #data = pd.DataFrame({'Observation': observation_list, 'Action': action_list})

    """
    # Normalizando datos de observación
    mean = np.mean(observation_list, axis=0)
    std = np.std(observation_list, axis=0)
    observation_list = (observation_list - mean) / std

    # Normalizando datos de acción:
    action_list = (action_list - np.min(action_list)) / (np.max(action_list) - np.min(action_list))

    """  
    
    x_train, x_val, y_train, y_val = train_test_split(observation_list,action_list, test_size=0.2, random_state=42)
    
    #return x_train, x_val, y_train, y_val
    return x_train, x_val, y_train, y_val, observation_list, action_list

def learning_rates(history):
     # Visualizar la pérdida
    plt.plot(history.history['loss'], label='Training Loss')
    plt.plot(history.history['val_loss'], label='Validation Loss')
    plt.xlabel('Epoch')
    plt.ylabel('Loss')
    plt.title('Training and Validation Loss')
    plt.legend()
    plt.show()

    # Visualizar la precisión
    plt.plot(history.history['accuracy'], label='Training Accuracy')
    plt.plot(history.history['val_accuracy'], label='Validation Accuracy')
    plt.xlabel('Epoch')
    plt.ylabel('Accuracy')
    plt.title('Training and Validation Accuracy')
    plt.legend()
    plt.show()

def history_max_min(history):
    # Obtener la pérdida y la precisión del entrenamiento
    train_loss = history.history['loss']
    train_accuracy = history.history['accuracy']

    # Obtener la pérdida y la precisión de la validación
    val_loss = history.history['val_loss']
    val_accuracy = history.history['val_accuracy']

    # Calcular el máximo y el mínimo de la pérdida y la precisión
    max_train_loss = np.max(train_loss)
    min_train_loss = np.min(train_loss)
    max_train_accuracy = np.max(train_accuracy)
    min_train_accuracy = np.min(train_accuracy)

    max_val_loss = np.max(val_loss)
    min_val_loss = np.min(val_loss)
    max_val_accuracy = np.max(val_accuracy)
    min_val_accuracy = np.min(val_accuracy)

    # Imprimir los resultados
    print("Train Loss - Máximo:", max_train_loss, "Mínimo:", min_train_loss)
    print("Train Accuracy - Máximo:", max_train_accuracy, "Mínimo:", min_train_accuracy)
    print("Validation Loss - Máximo:", max_val_loss, "Mínimo:", min_val_loss)
    print("Validation Accuracy - Máximo:", max_val_accuracy, "Mínimo:", min_val_accuracy)

def grafica():
    _,_,_,_,observation_list,action_list = get_data()
    observation_array = np.array(observation_list)
    time_steps = list(range(len(observation_list)))

    # Crear el gráfico de líneas
    plt.figure(figsize=(10, 6))
    plt.plot(time_steps, observation_array[:, 0], label='Observation 1')
    #plt.plot(time_steps, observation_array[:, 1], label='Observation 2')
    plt.plot(time_steps, observation_array[:, 2], label='Observation 3')
    #plt.plot(time_steps, observation_array[:, 3], label='Observation 4')
    plt.scatter(time_steps, observation_array[:, 0], c=action_list, cmap='viridis', label='Actions')  # Colorea los puntos según la acción tomada
    plt.xlabel('Time Step')
    plt.ylabel('Observation Value')
    plt.title('Observations and Actions over Time')
    plt.legend()
    plt.colorbar(label='Action (0 or 1)')

    # Mostrar el gráfico
    plt.show()

def main():
    os.system('cls')
    """
    x_train, x_val, y_train, y_val, obs,act = get_data()
    x_train = np.array(x_train)
    x_val = np.array(x_val)
    y_train = np.array(y_train)
    y_val = np.array(y_val)

   
    y_train = to_categorical(y_train, num_classes=2)
    y_val = to_categorical(y_val, num_classes=2)
  
    num_inputs = 4
    num_outputs = 2
    print(y_train.shape)
    print(y_train)
    input()

    nn = NeuronalNetwork(num_inputs,num_outputs)
    history, model = nn.fit_model(x_train,y_train,x_val,y_val)
    history_max_min(history)
    learning_rates(history)

    opc = int(input('Desea guardar el modelo? [1] Si / [2] No'))
    if opc == 1:
        print('guardando pesos del modelo...')
        model.save_weights('pesos_cartpole.h5')
    
    else:
        print('Este modelo no ha sido guardado')
    """
   
    # HORA DE PROBAR EL MODELO #
    os.system('cls')
    print('Hora de cargar los pesos del modelo!!!...\n\n')
    os.system('pause')
    os.system('cls')
    num_inputs = 4
    num_outputs = 2

    nn = NeuronalNetwork(num_inputs,num_outputs)
    modelo = nn.compile_model()
    modelo.load_weights('pesos_cartpole.h5')
    os.system('cls')
    print('Pesos cargados exitosamente...\n\n')
    os.system('pause')
    os.system('cls')
    print('Probando el agente con las acciones tomadas por la red neuronal')
    
    
    # Obteniendo el ambiente de CartPole-V1
    env = gym.make('CartPole-v1',render_mode="human")
    
    # Inicianlizando el ambiente
    _ = env.reset()
    # Creando el agente y ejecutando su acción
    agent = Agent(env, modelo)
    #is_done = agent.take_action()
    num_episodes = 0
    while num_episodes <= 100:
        agent.take_action()

        if agent.get_reward() <= 0.1:
            num_episodes = 0
            env.reset()

        env.render()
        num_episodes += 1

    env.close()

    print("Recompensa total recibida: %.4f" % agent.get_total_reward())
    print("Posición final: ", agent.get_current_observation())
    print("número de pasos en el episodio", agent.get_total_steps())
    
    #grafica()
   
    
   
main()