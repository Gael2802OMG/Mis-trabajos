"""
k-Nearest Neighbors

Es un algoritmo de Machine Learning que se usa para
clasificar valores discretos o predecir valores continuos

"k" significa cantidad de puntos vecinos
que tenemos en cuenta en las cercanías para clasificar
grupos que ya se debe conocer previamente al ser un
algoritmo supervisado

Supervisado: Se tiene un conjunto de datos etiquetados
de entrenamiento con la clase o resultado esperado

Basado en Instancia: El algoritmo memoriza las intancias
de entrenamiento que son usadas como base de conocimiento para la fase de
predicción. Por lo que, nuestro algoritmo no aprende explícitamente

Este algoritmo se usa para: sistemas de recomendación, búsqueda semántica
y detección de anomalías

Ventajas: facil de aprender e implementar
Desventajas: Al requerir toda la base de datos en cada uno de sus puntos,
al usar una unidad muy grande de datos para entrenarse, hace que requiera 
mucha memoria y recuros de procesamiento. Por lo que KNN no funciona con
datasets de información masiva, unicamente funciona con apartados pequeños

Funcionamiento:
+ Calcula la distancia entre el item a clasificar y el resto de items del
dataset
+ Selecciona los k (puntos vecinos) más cercanos
+ Realiza una "votación de mayoría" entre los k puntos: según que clase/etiqueta
dominen, se decidirá su clasificación final

"""

#Importamos Librerías
import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.colors import ListedColormap
import matplotlib.patches as mpatches
import seaborn as sb

plt.rcParams['figure.figsize'] = (16,9)
plt.style.use('ggplot')

from sklearn.model_selection import train_test_split
from sklearn.preprocessing import MinMaxScaler
from sklearn.neighbors import KNeighborsClassifier
from sklearn.metrics import classification_report, confusion_matrix

dataframe = pd.read_csv(r'./reviews_sentiment.csv',sep=';')
pd.set_option('display.max_columns', None) #Linea necesaria para ver todas las columnas
print(dataframe.head()) #Nos muestra los primeros 5 valores
print('##########################################################')
print(dataframe.describe()) #Nos muestra un resumen detallado de los datos
#dataframe.hist()
#plt.show()
print('##########################################################')
print(dataframe.groupby('Star Rating').size())
#sb.countplot(x='Star Rating',data=dataframe, palette='Set2') #<= Grafica de barras de colores // ta bonito :3
#sb.countplot(x='wordcount',data=dataframe, palette='Set2') #<= Grafica de barras de colores // ta bonito :3
#plt.show()

# Preparamos las entradas

x = dataframe[['wordcount','sentimentValue']].values
y = dataframe['Star Rating'].values
xTrain,xTest,yTrain,yTest = train_test_split(x,y,random_state=0)
scaler = MinMaxScaler()
xTrain=scaler.fit_transform(xTrain)
xTest=scaler.transform(xTest)


# Creamos y Entrenamos el modelo
n_neighbors = 7
knn = KNeighborsClassifier(n_neighbors)
knn.fit(xTrain,yTrain)
print('Accuracy of K-NN classifier on training set: {:.2f}'.format(knn.score(xTrain,yTrain)))
print('Accuracy of K-NN classifier on test set: {:.2f}'.format(knn.score(xTest,yTest)))
print('##########################################################')

# Precision del Modelo
pred = knn.predict(xTest)
print(confusion_matrix(yTest,pred))
"""
TP,TN,FP,FN
TP: Modelo predice correctamente una muestra en la clase especifica
TN: Modelo Predice correctamente una muestra que no pertenece a esa clase
FP: Modelo predice incorrectamente una muestra en la clase especifica
FN: Modelo Predice incorrectamente una muestra que no pertenece a esa clase

en este caso:
[[ 9  0  1  0  0]
 [ 0  1  0  0  0]
 [ 0  1 17  0  1]
 [ 0  0  2  8  0]
 [ 0  0  4  0 21]]
 
 los numeros en diagonal: 9,1,17,8,21. Son los valores predecidos correctamente (TP)
 los numeros fuera de la diagonal, 0,1,2,4 son los valores predecidos incorrectamente (FN)
 Pero al tener varios espacios en 0 signficia que el modelo en su mayoria predice bien
 la relacion de los datos con cada clase
"""
print('##########################################################')
print(classification_report(yTest,pred))
"""
Presición: Muestra el porcentaje de aciertos que tuvo en cada clase
recall: Verifica cuantas de esas predicciones pertenecen realmente a la clase 1
f1-score: Taza de equilibrio entre la presición y el recall
suport: Numero de muestras reales de cada clase
Accuracy: Promedio total del porcentaje de predicciones del modelo
macro avg: Promedia las metricas
weighted avg: Pondera el soporte de cada clase
"""
print('##########################################################')
h = .02  #Tamaño de la malla

#Crear mapas de colores
cmap_light = ListedColormap(['#FFAAAA', '#ffcc99', '#ffffb3','#b3ffff','#c2f0c2']) #Colores Fondo
cmap_bold = ListedColormap(['#FF0000', '#ff9933','#FFFF00','#00ffff','#00FF00']) #Colores de puntos

# Creamos una instancia de Neighbors Classifier y ajustamos los datos.
clf = KNeighborsClassifier(n_neighbors, weights='distance')
# weights por defecto tiene 'uniform', haciendo que las muestras vecinas tengan el mismo peso
# sin embargo al tener 'distance' los pesos de las muestras vecinas tienen valores proporcionales a ala inversa
# de su distancia, por lo que mientras más cerca esten las muestres más ingluencia tendrán en la predicción
clf.fit(x, y)

# Traza el límite de decisión. Para eso le asignaremos un color a cada
# punto en la malla [x_min, x_max]x[y_min, y_max].
x_min, x_max = x[:, 0].min() - 1, x[:, 0].max() + 1
y_min, y_max = x[:, 1].min() - 1, x[:, 1].max() + 1
xx, yy = np.meshgrid(np.arange(x_min, x_max, h),
                         np.arange(y_min, y_max, h))
z = clf.predict(np.c_[xx.ravel(), yy.ravel()])

#Pon el resultado en un diagrama de color.
z = z.reshape(xx.shape)
plt.figure()
plt.pcolormesh(xx, yy, z, cmap=cmap_light)

#Trazar también los puntos de entrenamiento.
plt.scatter(x[:, 0], x[:, 1], c=y, cmap=cmap_bold,
                edgecolor='k', s=20)
plt.xlim(xx.min(), xx.max())
plt.ylim(yy.min(), yy.max())
    
patch0 = mpatches.Patch(color='#FF0000', label='1')
patch1 = mpatches.Patch(color='#ff9933', label='2')
patch2 = mpatches.Patch(color='#FFFF00', label='3')
patch3 = mpatches.Patch(color='#00ffff', label='4')
patch4 = mpatches.Patch(color='#00FF00', label='5')
plt.legend(handles=[patch0, patch1, patch2, patch3,patch4])

    
plt.title("5-Class classification (k ="+str(n_neighbors)+")")

plt.show()