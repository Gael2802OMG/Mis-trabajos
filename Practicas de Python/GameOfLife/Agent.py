import numpy as np
import random as rd

# Clase Logica
class Agent():

    # Método constructor
    def __init__(self):
        self.life_x = None
        self.life_y = None
        self.map = []
    
    # Método para crear el mapa de estados
    def create_map(self,size):
        self.map = np.zeros((size,size))

    # Método para crear el mapa de estados de forma aleatoria
    def random(self,size):
        rand_map = [[rd.randint(0,1) for _ in range(size)] for _ in range(size)]
        self.map = np.array(rand_map)

    # Método que realiza una busqueda en el mapa de estados y toma desiciones el Agente
    def search(self):
        long = len(self.map)
        self.life_y,self.life_x = np.where(self.map == 1)
        print(self.life_y)
        print(self.life_x)

        for y in range(long):
            for x in range(long):
                count = 0
                for z in range(len(self.life_x)):
                    if self.life_x[z] >= x-1 and self.life_x[z] <= x+1:
                        if self.life_y[z] >= y-1 and self.life_y[z] <= y+1:
                            count += 1

                if self.map[y][x] == 1:
                    count -= 1 
                #print(count)
                if count == 3 and self.map[y][x] != 1:
                    self.map[y][x] = 1

                elif (count < 2 or count > 3) and self.map[y][x] == 1:
                    self.map[y][x] = 0  

                elif (count >= 2 and count <=3) and self.map[y][x] == 1:
                    self.map[y][x] == 1

    # Método para iniciar el comportamiento del Agente
    def start(self):
        self.search()
    
    # Método para mostrar el mapa de estados
    def __str__(self):
        return str(self.map)
    




