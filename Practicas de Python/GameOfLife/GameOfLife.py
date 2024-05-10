from Agent import Agent
from DisplayGOL import DisplayGOL

# Función para la creación de colores
def colores(num):
    if num == 1:
        return (255,255,255)
    elif num == 2:
        return (0,0,0)

# Función principal
def main():
    agent = Agent()
    size = 90
    dim_cell = 60
    dis = DisplayGOL(size,dim_cell,agent)
    dis.window(colores(1))
    dis.text('Generacion',colores(2),35,size*11.3,30)
    dis.text('Random [r]',colores(2),35,size*11.3,300)
    dis.text('Iniciar [q]',colores(2),35,size*11.3,350)
    dis.text('Pausar [w]',colores(2),35,size*11.3,400)
    dis.text('Limpiar [t]',colores(2),35,size*11.3,450)
    dis.text('Salir [e]',colores(2),35,size*11.3,500)
    
    #dis.text(str(0),colores(2),35,820,size*11)
    dis.mapa(colores(1),colores(2))
    #print(dis)
    print(agent)
    dis.start(colores(1),colores(2),30)

main()
