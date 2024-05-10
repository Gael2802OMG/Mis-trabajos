"""
Los Algoritmos de Regresión son utilizados para crear un modelo entre la
relación de distintas variables/caracteristicas, en las que se utiliza
una medida de error, que son minimizadas para realizar predicciones
lo más acertadas posibles

Nota: Algoritmos de Regresión, Logístico o Lineal. Nos ayudan a clasificar 
o predecir valores. Se intentará compensar la mejor respuesta a partir del 
menor error.

-------------------------------------------------------------------------
Regresión Lineal
Un algoritmo de aprendizaje supervisado, utilizado en Machine Learning y
estadística, el proposito es indicar una tendencia de un conjunto de datos
continuos

Nota: hago enfasis en estados continuos ya que esta algoritmo solo funciona
con ese tipo de datos.

Datos Continuos: cualquier dato que puede tomar un valor dentro de un rango
determinado y con un conjunto infinito de posibles valores dentro del intervalo

Ejemplos: altura, peso, temperatura, tiempo, etc.
------------------------------------------------------------------------

Formula de una recta: Y = mx + b

El todo algoritmo de regresión debera minimizar el coste de una función de
error cuadratico, el metodo más comun para realizar esto es utilizar
una versión vectorial y la llamada ecuación normal.

Error cuadratico: se refiere a la suma residual de cuadrados, dividida por el número de
grados de libertad

Ecuación normal: la aproximación de mínimos cuadrados de funciones lineales 
a los datos. Es un conjunto de formulaciones para resolver problemas 
estadísticos involucrados en la regresión lineal
"""

###################### --:: Ejercicio Practico ::-- ######################

#Se cargará un archivo .csv que contiene una lista de articulos usados por
#Techcrunch o KDnuggets
#Columnas:
#Title, url, Word count, of Links, of comments, Images video, Elapsed days, Shares

#import necesarios
import numpy as np #Numpy es una librería especial para matrices
import pandas as pd #Pandas librería especializada en obtener información de tablas
import seaborn as sb #Seaborn es una función entre Pandas y Matplotlib, en cuanto a visualización de estadisticas se refiere
import matplotlib.pyplot as plt #Matplotlib Creación de graficos en 2D
from mpl_toolkits.mplot3d import Axes3D #Axes3D es una extención de Matplotlib, para habilitar graficos en 3D
from matplotlib import cm #CM es otra extención de Matplotlib, en este caso para crear un mapeo de colores
plt.rcParams['figure.figsize'] = (16,9)
plt.style.use('ggplot')
from sklearn import linear_model 
from sklearn.metrics import mean_squared_error, r2_score

#cargamos los datos de entrada
data = pd.read_csv("./articulos_ml.csv") 
print('------------------------ Dimensiones ------------------------')
print(data.shape)
print('-------------------------------------------------------------')

#Son 161 registros con 8 columnas. Veamos los primeros registros
print('--------------------- Datos Cabecera --------------------')
print(data.head())
print('---------------------------------------------------------')

#Estadisticas de los datos
print('--------------------- Estadistica Datos --------------------')
print(data.describe())
print('------------------------------------------------------------')

#histograma = data.drop(['Title','url','Elapsed days'],axis = 1).hist() <= Esta linea crea un histograma de la tabla
#plt.show() <= muestra el histograma

#Vamos a RECORTAR los datos en la zona donde se concentran más los puntos
#esto es en el eje X: entre 0 y 3,500
#y en el eje Y: entre 0 y 80,000

filtered_data = data[(data['Word count'] <= 3500) & (data['# Shares'] <= 80000)] 
# ^= esta linea crea un filtro auxiliar que se encarga de obtener unicamente todos los articulos que tengan <= 3500 numeros de palabras y <= 80,000 numero de veces compartido
colores = ['orange','blue']
# ^= aquí se crea un array con 2 colores, que serán los que imprimiremos en pantalla
tamanos = [30,60]
# ^= Aquí las dimensiones de los puntitos

f1 = filtered_data['Word count'].values
# Obtiene todos los valores obtenidos del numero de palabras del filtro
f2 = filtered_data['# Shares'].values
# Obtiene todos los valores obtenidos de las veces compartidas del filtro


# Vamos a pintar en colores los puntos por debajo y por encima de la media
# cantidad de palabras
asignar = []
for index, row in filtered_data.iterrows():
    # Se crea un siclo con las filas que obtienen ambos datos, el numero de palabra y el numero de compartidas
    if(row['Word count']>1808):
        asignar.append(colores[0])
    else:
        asignar.append(colores[1])
    # se agregan los colores dependiendo de la condición row['Word count']>1808

plt.scatter(f1,f2,c=asignar,s=tamanos[0])
# plt.scatter es una función de Matplotlib para cerar un grafico disperso en 2D (Por eso los puntitos)
# Sintaxis: plt.scatter(x,y,s="tamaño de puntos", c="colores de los puntos",s="tamaño de puntos",marker="diseño de 'puntos', cmap="tipo de marcador", norm="normalizar datos", vmin="minimos", vmax="maximos", aplha="transparencia del 0 al 1", linewidths="grosor de bordes", edgecolors="Color de bordes")
plt.xlabel('Word Count', fontsize=20,color='black')
plt.ylabel('# Shares', fontsize=20,color='black')
plt.title('Relación de numero de palabras con veces compartidas', fontsize=24,color='black',fontstyle='italic')

#########################################################################
#Creasión de regresión lineal con python y sklearn
dataX = filtered_data[['Word count']]
x_train = np.array(dataX)
y_train = filtered_data['# Shares'].values

#Cramos el objeto de Regresión Lineal
regr = linear_model.LinearRegression()

#Entrenamos nuestro melo
regr.fit(x_train, y_train)

#Hacemos las predicciones que en definitiva una linea (en este caso, al ser 2D)
y_pred = regr.predict(x_train)



#Veamos los coeficientes obtenidos, En nuestro caso, serán la Tangente
print('Coeficiente: \n', regr.coef_)
print('------------------------------------------------------------')
#Este es el valor donde ciorta el eje Y (en X=0)
print('Inpendiente term: \n', regr.intercept_)
print('------------------------------------------------------------')
#Error Cuadrado Medio
print('Mean Squared Error: %.2f' % mean_squared_error(y_train,y_pred))
print('------------------------------------------------------------')
#Puntaje de varianza el mejor puntaje es 1.0
print('Variance score: %.2f' % r2_score(y_train,y_pred))
print('------------------------------------------------------------')
plt.plot(x_train,y_pred,color='red',linewidth=2)
plt.show()

#Vamos a comprobar:
#Quiero predecir cuántos "Shares" voy a obtener por un articulo con 2 mil palabras
#Según nuestro modelo, hacemos:
y_Dosmil = regr.predict([[2000]])
print(int(y_Dosmil))
#########################################################################

#Regresión Lineal Múltiple/Polinomial en Python
#(o "Rregresión con Múltiples Variables")

#Ecuación de multiples variables:
# Y = b + m1x1 + m2x2 + ... + m(n)x(n)

#Agregamos una nueva dimensión al modelo

suma = (filtered_data['# of Links']+filtered_data['# of comments'].fillna(0)+filtered_data['# Images video'])
#Suma los conjuntos de datos de links, comentarios e imagenes de video
dataX2 = pd.DataFrame()
#Creamos otro dataframe
dataX2['Word count'] = filtered_data['Word count']
#le agregamos los datos de Word count
dataX2['suma'] = suma
#Y los datos sumados de suma
xyTrain = np.array(dataX2)
#Creamos datos de entramiento
# consiguiendo ahora 2 datos de entrada 
zTrain = filtered_data['# Shares'].values
#Obtenemos nuestros datos de salida

#Creamos un nuevo modelo de regresión Lineal
regr2 = linear_model.LinearRegression()
#Lo entrenamos con una tupla en datos de entrada y los datos de salida
regr2.fit(xyTrain,zTrain)
zPred = regr2.predict(xyTrain)
#Coeficientes
print('Coefficients: \n',regr2.coef_)
print('------------------------------------------------------------')
#Error Cuadratico Medio
print('Error Cuadratico Medio: %0.2f '% mean_squared_error(zTrain,zPred))
print('------------------------------------------------------------')
#Puntaje de varianza 
print('Varianza: %0.2f'% r2_score(zTrain,zPred))
print('------------------------------------------------------------')

#####################################################
###########::--Visualizar el modelo 3D--::###########
fig = plt.figure()
ax = Axes3D(fig)

# Creamos una malla, sobre la cual graficaremos el plano
xx, yy = np.meshgrid(np.linspace(0, 3500, num=10), np.linspace(0, 60, num=10))

# calculamos los valores del plano para los puntos x e y
nuevoX = (regr2.coef_[0] * xx)
nuevoY = (regr2.coef_[1] * yy) 

# calculamos los correspondientes valores para z. Debemos sumar el punto de intercepción
z = (nuevoX + nuevoY + regr2.intercept_)

# Graficamos el plano
ax.plot_surface(xx, yy, z, alpha=0.2, cmap='hot')

# Graficamos en azul los puntos en 3D
ax.scatter(xyTrain[:, 0], xyTrain[:, 1], zTrain, c='blue',s=30)

# Graficamos en rojo, los puntos que 
ax.scatter(xyTrain[:, 0], xyTrain[:, 1], zPred, c='red',s=40)

# con esto situamos la "camara" con la que visualizamos
ax.view_init(elev=30., azim=65)
        
ax.set_xlabel('Cantidad de Palabras')
ax.set_ylabel('Cantidad de Enlaces,Comentarios e Imagenes')
ax.set_zlabel('Compartido en Redes')
ax.set_title('Regresión Lineal con Múltiples Variables')
#plt.show()


