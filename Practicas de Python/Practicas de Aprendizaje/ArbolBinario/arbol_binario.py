from nodo import Nodo

class ArbolBinarioDeBusqueda():
    def __init__(self):
        self.root = None
    
    def empty(self):
        return self.root == None
    
    def add(self,value):
        if self.empty:
            self.root = Nodo(value=value, is_root=True)
        else:
            nodo = self.tomando_Espacio(value)
            if value <= nodo.value:
                nodo.left = Nodo(value=value,parent=nodo, is_left=True)
            else:
                nodo.right = Nodo(value=value,parent=nodo, is_right=True)

    def tomando_Espacio(self,value):
        aux = self.root
        while aux:
            temp = aux
            if value <= aux.value:
                aux = aux.left
            else:
                aux = aux.right  

    def Recorrido_en_orden(self,nodo):
        pass

    def Recorrido_post_orden(self,nodo):
        pass

    def Recorrido_pre_orden(self,nodo):
        pass