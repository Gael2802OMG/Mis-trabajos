from lista_doblemente_enlazada import listaDoblementeEnlazada

lista = listaDoblementeEnlazada()

lista.agregar_final('Niza')
lista.agregar_inicio('23')
lista.agregar_final('Luna')
lista.recorrer_inicio()
print('---------------------')
lista.recorrer_fin()
print('---------------------')
print(lista.size)
lista.eliminar_fin()
lista.eliminar_inicio()
print('---------------------')
lista.recorrer_inicio()
print('---------------------')

print(lista.size)