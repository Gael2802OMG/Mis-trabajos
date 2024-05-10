import pygame as py
import numpy as np
import sys

# Clase Visual
class DisplayGOL:

    # Método constructor 
    def __init__(self,size,dim_cell,agent):
        self.size = size*10
        self.dim_cell = dim_cell
        self.dimension = self.size/dim_cell
        self.screen = None
        self.font = None
        self.cor = []
        self.agent = agent
        self.gen = 0
        self.vali = False
        self.vali2 = True

    # Método que reinicia los valores logicos y visuales dentro del juego
    def clean(self,color1,color2):
        self.gen = 0
        self.vali = False
        self.vali2 = True
        py.draw.rect(self.screen,color1,(90*10.7,45,120,40))
        self.text(str(self.gen),color2,35,90*11.3,70)
        self.agent.create_map(self.dim_cell)
        self.mapa(color1,color2)

    # Método para crear textos    
    def text(self,txt,color,size_font,width,heith):
        py.font.init()
        self.font = py.font.Font(None,size_font)
        texto = self.font.render(txt,True,color)
        espacio_txt = texto.get_rect()
        espacio_txt.center = (width,heith)
        self.screen.blit(texto,espacio_txt)

        py.display.flip()

    # Método para crear la ventana principal del juego
    def window(self,color):
        self.agent.create_map(self.dim_cell)
        self.screen = py.display.set_mode([self.size+(self.dim_cell*4) , self.size])
        self.screen.fill(color)
        py.display.flip()

    # Método para dibujar la cuadricula/matriz/mapa dentro de la ventana
    def mapa(self,color1,color2):
        for y in range(0,self.size,int(self.size/self.dim_cell)):
            #print('\n')
            self.cor.append(y)
            for x in range(0,self.size,int(self.size/self.dim_cell)):
                celda = int(self.agent.map[int(x/self.dimension),int(y/self.dimension)])

                if celda == 1:
                    py.draw.rect(self.screen,color2,(y,x,(self.size/self.dim_cell),(self.size/self.dim_cell)))
                  
                if celda == 0:
                    py.draw.rect(self.screen,color1,(y,x,(self.size/self.dim_cell),(self.size/self.dim_cell)))
                    py.draw.rect(self.screen,color2,(y,x,(self.size/self.dim_cell),(self.size/self.dim_cell)),width=1)
                 
                #print('('+str(x)+','+str(y)+')',end=' ')
        py.display.flip()
    
    # Método para ejecutar los eventos del mouse
    def mouse_button(self,color1, color2,event):
        if event.type == py.MOUSEBUTTONDOWN:
                if event.button == 1:
                    y,x = event.pos
                    #print(str(y)+','+str(x))
                    if x > (self.cor[-1]+self.dimension) or y > (self.cor[-1]+self.dimension):
                        print('equide')
                    else:
                        x1,y1 = int(y/self.dimension),int(x/self.dimension)
                        #print(x1,y1)
                        if self.agent.map[y1,x1] == 1:
                            self.agent.map[y1,x1] = 0

                        else:
                            self.agent.map[y1,x1] = 1
                        
                        self.mapa(color1, color2)

                        py.display.flip()
    
    # Método para ejecutar los eventos del teclado
    def key_button(self,event,color1,color2):
        if event.type == py.KEYDOWN:
            if event.key == py.K_r:  
                self.agent.random(self.dim_cell)
                self.mapa(color1,color2)

            if event.key == py.K_q:
                self.vali = True
                if self.vali2 == False:
                    self.gen = 0

            if event.key == py.K_t:
                self.clean(color1,color2)

            if event.key == py.K_e:
                py.quit()
                sys.exit()
            
        if event.type == py.KEYDOWN:
            if event.key == py.K_w:
                self.vali = False 

    # Método para iniciar el bucle del juego                  
    def start(self,color1,color2,fps):
        py.init()
        clock = py.time.Clock()
        #Game:
        while True:
            for event in py.event.get():
                if event.type == py.QUIT:
                    py.quit()
                    sys.exit()

                self.mouse_button(color1,color2,event)
                self.key_button(event,color1,color2)

            if self.vali:
                self.agent.start()
                self.mapa(color1,color2)
                vali_map_x, vali_map_y = np.where(self.agent.map == 1)

                print(str(len(vali_map_x))+','+str(len(vali_map_y)))

                if len(vali_map_x) == 0 and len(vali_map_y) == 0:
                    self.vali = self.vali2 = False

                py.draw.rect(self.screen,color1,(90*10.7,45,120,40))
                self.text(str(self.gen),color2,35,90*11.3,70)
                self.gen += 1
           
            py.display.flip()
            clock.tick(fps)

    # Método que muestra las caracteristicas más importantes de la clase
    def __str__(self):
        string = 'Size: ' + str(self.size) +'\ndim_cell: '+ str(self.dim_cell) +'\ndimension: ' + str(self.dimension) +'\ncordenadas: ' +str(self.cor)
        return string
            

