from nodo import Nodo

class listaSimpleEnlazada():
    def __init__(self):
        self.primero = None
        self.ultimo = None

    def vacia(self):
        return self.primero == None
    
    def agregar_ultimo(self,dato):
        if self.vacia() == True:
            self.primero = self.ultimo = Nodo(dato)
        else:
            aux = self.ultimo 
            self.ultimo = aux.siguiente = Nodo(dato)
    
    def agregar_primero(self,dato):
        if self.vacia() == True:
            self.primero = self.ultimo = Nodo(dato)
        else:
            aux = self.primero
            self.primero = Nodo(dato)
            self.primero.siguiente = aux

    def recorrido(self):
        aux = self.primero
        while aux != None:
            print(aux.dato)
            aux = aux.siguiente
    
    def eliminar_ultimo(self):
        aux = self.primero
        while aux.siguiente != self.ultimo:
            aux = aux.siguiente
        aux.siguiente = self.ultimo.siguiente
        self.ultimo = aux

    def eliminar_primero(self):
        self.primero = self.primero.siguiente

