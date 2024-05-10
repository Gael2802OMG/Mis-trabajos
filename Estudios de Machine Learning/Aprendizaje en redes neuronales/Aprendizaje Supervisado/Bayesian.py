"""
P(B/A) = P(B) / P(A) * P(A/B) <= Teorema de Bayes
P(A/B) = P(A) / P(B) * P(B/A)


Ejemplo: 
    P(A) = 0.3
    P(B) = 0.4          Hallar P(B/A)
    P(A/B) = 0.45

    P(B/A) = P(B) / P(A) * P(A/B) = 0.4/0.3 * 0.45 = [0.6]

Tasa de Probabilidades a posterior:
    P(Comprar/Datos)            P(Datos/Comprar) * P(Comprar)
    ----------------    =     ---------------------------------  
    P(Alquilar/Datos)           P(Datos/Alquilar) * P(Alquilar)



"""
#Algoritmo de Bayes => La mejor desición

import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
from matplotlib import colors
import seaborn as sb

plt.rcParams['figure.figsize'] = (16, 9)
plt.style.use('ggplot')

from sklearn.model_selection import train_test_split
from sklearn.metrics import classification_report
from sklearn.metrics import confusion_matrix
from sklearn.naive_bayes import GaussianNB
from sklearn.feature_selection import SelectKBest

dataframe = pd.read_csv(r"comprar_alquilar.csv")
pd.set_option('display.max_columns', None)
print(dataframe.head())
print('###########################################')
print(dataframe.describe())
print('###########################################')
print(dataframe.groupby('comprar').size())
print('###########################################')
#dataframe.drop(['comprar'],axis=1).hist()
#plt.show()
###########################################################################
##############-------- Preparar los datos de entrada --------##############

dataframe['gastos'] = (dataframe['gastos_comunes']+dataframe['gastos_otros']+dataframe['pago_coche'])
dataframe['financiar'] = (dataframe['vivienda']-dataframe['ahorros'])
headGastos=dataframe.drop(['gastos_comunes','gastos_otros','pago_coche'],axis=1)
print(headGastos.head(10))
print('###########################################')
print(headGastos.describe())
print('###########################################')

############################################################################
##############--------- Selección de caracteristícas ---------##############

x=dataframe.drop(['comprar'],axis=1)
y=dataframe['comprar']

best = SelectKBest(k=5)
xNew = best.fit_transform(x,y)
print(xNew.shape)
selected = best.get_support(indices=True)
print(x.columns[selected])
print('###########################################')
#############################################################################
#Correlación
used_features = x.columns[selected]

colormap = plt.cm.viridis
plt.figure(figsize=(12,12))
plt.title('Pearson Correlation of Features', y=1.05,size=15)
sb.heatmap(dataframe[used_features].astype(float).corr(),linewidths=0.1,vmax=1.0,square=True,cmap=colormap,linecolor='white',annot=True)
#Parte importante:
#dataframe: dataBase
#used_features: Mejores columnas para utilizar en el data set, según el algoritmo
#la función dataframe[used_features].astype(float).corr() crea una correlación
#de el dataset con las columnas que el algoritmo dijo que serían las más obtimas
#para realizar el entrenamiento, selecionamiendo el tipo con .astype(float)
#y la función .corr()
#plt.show()
#############################################################################
#Creación del modelo
xTrain,xTest = train_test_split(dataframe,test_size=0.2,random_state=6)
yTrain = xTrain["comprar"]
yTest = xTest["comprar"]

gnb = GaussianNB()
gnb.fit(xTrain[used_features].values,yTrain)

yPred = gnb.predict(xTest[used_features])

print('Precisión en el set de Entrenamiento: {:.2f}'
     .format(gnb.score(xTrain[used_features], yTrain)))
print('Precisión en el set de Test: {:.2f}'
     .format(gnb.score(xTest[used_features], yTest)))

#                   ['ingresos', 'ahorros', 'hijos', 'trabajo', 'financiar']
print(gnb.predict([[2000,        5000,     0,       5,         200000],
                   [6000,        34000,    2,       5,         320000] ]))
#Resultado esperado 0-Alquilar, 1-Comprar casa


