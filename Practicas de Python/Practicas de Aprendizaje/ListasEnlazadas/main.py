from lista_simple_enlazada import listaSimpleEnlazada

lista = listaSimpleEnlazada()


lista.agregar_ultimo(12)
lista.agregar_ultimo(2)
lista.agregar_ultimo(25)
lista.agregar_ultimo(19)
lista.agregar_ultimo(30)


lista.recorrido()
print('-----------------------------')
lista.eliminar_ultimo()
lista.recorrido()
print('-----------------------------')
lista.eliminar_primero()
lista.recorrido()