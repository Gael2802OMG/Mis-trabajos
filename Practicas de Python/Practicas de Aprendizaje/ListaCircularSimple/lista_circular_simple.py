from nodo import Nodo

class ListaCircularSimple():
    def __init__(self):
        self.primero = None
        self.ultimo = None
    
    def vacia(self):
        return self.primero == None

    def agregarInicio(self,dato):
        if self.vacia():
            self.primero = self.ultimo = Nodo(dato)
            self.ultimo.siguiente = self.primero
        else:
            aux = Nodo(dato)
            aux.siguiente = self.primero
            self.primero = aux
            self.ultimo.siguiente = self.primero
    
    def agregarFinal(self,dato):
        if self.vacia():
            self.primero = self.ultimo = Nodo(dato)
            self.ultimo.siguiente = self.primero
        else:
            aux = self.ultimo
            self.ultimo = aux.siguiente = Nodo(dato)
            self.ultimo.siguiente = self.primero
    
    def moverPrimero_Ultimo(self):
        if self.vacia():
            print('La lista está vacia')
        else:
            aux = self.primero.siguiente
            self.primero = aux

    def removerInicio(self):
        if self.vacia():
            print('La lista está vacia')
        elif self.primero == self.ultimo:
            self.primero = self.ultimo = None
        else:
            self.primero = self.primero.siguiente
            self.ultimo.siguiente = self.primero

    def removerFinal(self):
        if self.vacia():
            print('La lista está vacia')
        elif self.primero == self.ultimo:
            self.primero = self.ultimo = None
        else:
            aux = self.primero
            while aux.siguiente != self.ultimo:
                aux = aux.siguiente
            self.ultimo = aux
            self.ultimo.siguiente = self.primero
            
    def recorrer(self):
        if self.vacia():
            print('La lista está vacia')
        else:
            aux = self.primero
            while aux:
                print(aux.dato)
                aux = aux.siguiente
                if aux == self.primero:
                    break
    