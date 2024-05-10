import gym
import numpy as np

# Crear el entorno CartPole-v1
env = gym.make('CartPole-v1',render_mode="human")

# Cargar la tabla Q guardada
loaded_q_table = np.load('q_table.npy')

# Función para discretizar los estados
def discretize_state(state):
    bins = [np.linspace(-4.8, 4.8, num=10), np.linspace(-3, 3, num=10), 
            np.linspace(-0.418, 0.418, num=10), np.linspace(-3, 3, num=10)]
    discretized_state = [np.digitize(s, bins[i]) for i, s in enumerate(state)]
    return tuple(discretized_state)

# Función para seleccionar una acción utilizando la tabla Q cargada
def select_action(state):
    return np.argmax(loaded_q_table[state])

def take_reward(state):
    angulo = int(state[2] * 100)
    umbral = 30  # Umbral de inclinación
    max_recompensa = 1.0  # Recompensa máxima cuando el ángulo es 0
    min_recompensa = 0.0  # Recompensa mínima cuando el ángulo supera el umbral

    # Calcular la recompensa en función del ángulo
    if angulo == 0:
        return max_recompensa
    
    elif abs(angulo) > umbral:
        return min_recompensa
    
    else:
        # Calcular la recompensa como una función lineal del ángulo
        return max_recompensa - (max_recompensa / umbral) * abs(angulo)



# Probar el agente utilizando la tabla Q cargada
num_episodes = 100
total_rewards = []

for episode in range(num_episodes):
    state, _ = env.reset()
    state = discretize_state(state)
    done = False
    total_reward = 0
    
    while not done:
        action = select_action(state)
        next_state, _, _, done, _ = env.step(action)
        reward = take_reward(next_state)
        next_state = discretize_state(next_state)
        state = next_state
        total_reward += reward
    
    # Registrar la recompensa total del episodio
    total_rewards.append(total_reward)
    print(f'Episodio {episode + 1}: Recompensa total = {total_reward}')

# Calcular y mostrar la recompensa promedio
avg_reward = np.mean(total_rewards)
print(f'Recompensa promedio en {num_episodes} episodios: {avg_reward}')

# Cerrar el entorno
env.close()
