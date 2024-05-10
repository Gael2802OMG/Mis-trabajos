"""
Arboles de decisión: Representaciones gráficas de posibles soluciones a una
decisión basadas en ciertas condiciones, se utilizan en tareas de clasificación
o regresión.

La forma en la que un arbol logra medir las predicciones en base a 2 algoritmos
uno es el indice gini y la ganancia de información

Indice Gini:
Utilizando valores continuos obtiene el grado de impureza, por lo que define
que tan mezclados o desordenados están los nodos al dividirlos.

Ganacia de información:
Utilizando atributos categóricos (ejemplo: hombre/mujer). Este criterio intenta
información que aporta cada atributo basado en la teoría de la información.
Para medir la aleatoriedad de incertidumbre de un valor aleatorio de una variable
"X" se define la Entropía.

Entropía:
Forma en la que se considera la cantidad de información promedio que contiene
algunos simbolos usados. Estos Simbolos pueden representar más o menos información
dependiendo de el objetivo al que se quiera llegar.
por ejemplo:
las palabras: que, el, a -> no aportan nada de información sobre el estudio
sin embargo las palabras: corren, niño, perro -> Aportan todo el contexto de
lo que se quiere estudiar
"""

#Importando Librearías
import numpy as np
import pandas as pd
import seaborn as sb
import matplotlib.pyplot as plt
plt.rcParams['figure.figsize'] = (16,9)
plt.style.use('ggplot')
from sklearn import tree
from sklearn.metrics import accuracy_score
from sklearn.model_selection import KFold
from sklearn.model_selection import cross_val_score
from IPython.display import Image as PImage
from PIL import Image, ImageDraw, ImageFont

dataset = pd.read_csv("./artists_billboard_fix3.csv")
pd.set_option('display.max_columns', None)
print(dataset.shape)
print('--------------------------------------------------------------------------------')
print(dataset.head())
print('--------------------------------------------------------------------------------\n\n')
print(dataset.describe())
print('--------------------------------------------------------------------------------')
print(dataset.groupby('top').size())
print('--------------------------------------------------------------------------------')
top = dataset.groupby('top').size()
"""
############ ::-- Graficas --:: ############
#Muestra Grafica de Top (Grafica de 1 dimensión)
plt.figure(figsize=(8,6))
plt.bar(top.index,top.values, color=['salmon','skyblue'])
plt.xticks((0,1))
plt.show()

#Muestra Grafica de Generos (Grafica de Tuplas)
plt.figure(figsize=(8,6))
sb.countplot(x='artist_type',data=dataset, palette='Set2')
plt.show()

#Grafica de Moods 
plt.figure(figsize=(8,6))
sb.countplot(x='mood',data=dataset, palette='Set2')
plt.show()

#Grafica de Tempo
plt.figure(figsize=(8,6))
sb.countplot(x='tempo',data=dataset,hue='top', palette='Set2')
plt.show()

#Grafica de Generos
plt.figure(figsize=(8,6))
sb.countplot(x='genre',data=dataset, palette='Set2')
plt.show()

#Grafica de Años de nacimiento
plt.figure(figsize=(8,6))
sb.countplot(x='anioNacimiento',data=dataset, palette='Set2')
plt.xticks((0,40))
plt.show()

#Grafica mostrando la relacion entre chart_date y durationSeg
f1 = dataset['chart_date'].values
f2 = dataset['durationSeg'].values

colores = ['orange','skyblue']
tamaños = [60,40]

asignar=[]
asignar2=[]
for index, row in dataset.iterrows():
    asignar.append(colores[row['top']])
    asignar2.append(tamaños[row['top']])

plt.scatter(f1,f2,c=asignar,s=asignar2)
plt.axis([20030101,20160101,0,600])
plt.show()
"""

def edad_fix(año):
    if año == 0:
        return 0
    return año

dataset['anioNacimiento']=dataset.apply(lambda x: edad_fix(x['anioNacimiento']),axis=1)

def calcula_edad(año,cuando):
    cad=str(cuando)
    momento = cad[:4]
    if año==0.0:
        return None
    return int(momento)-año

dataset['edad_en_billboard']=dataset.apply(lambda x: edad_fix(x['anioNacimiento'],x['chart_date']),axis=1)
    








