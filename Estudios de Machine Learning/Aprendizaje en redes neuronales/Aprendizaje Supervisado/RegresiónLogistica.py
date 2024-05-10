"""
Underfitting: Una de las causas probables por las que nuestro modelo puede fallar
a la hora de obtener los resultados esperados, traduciendose como subajuste
por ejemplo: si le damos a entrenar imagenes para reconocimiento de perros
con una unica raza de perros, entonces el modelo será propenso a fallar
dado que no tiene suficientes muestras, no puede generalizar el conocimiento

Overfitting: Otra causa probable por la que nuestro modelo pueda fallar en
los resultados dados, es por el sobreajuste, siendo este cuando recibe mucha
infomración que no pueda valorar otros posibles resultados correctos.
por ejemplo: Si entrenamos al modelo con 10 razas de perros, estamos sobreajustando
sus desiciones, haciendo que cuando mostremos una raza que cumpla con
caracteristicas muy estrictas, no pueda reconocer al perro en cuestión.
"""

#Regresión Logistica
import pandas as pd
import numpy as np
from sklearn import linear_model
from sklearn import model_selection
from sklearn.metrics import classification_report
from sklearn.metrics import confusion_matrix
from sklearn.metrics import accuracy_score
import matplotlib.pyplot as plt
import seaborn as sb


dataframe = pd.read_csv(r"usuarios_win_mac_lin.csv")

pd.set_option('display.max_columns', None)
print(dataframe.head())
print('###########################################')
print(dataframe.describe())
print('###########################################')
print(dataframe.groupby('clase').size())
print('###########################################')
#dataframe.drop(['clase'],axis=1).hist()
#sb.pairplot(dataframe.dropna(),hue='clase',height=4,vars=['duracion','paginas','acciones','valor'],kind='reg')
#plt.show()

x = np.array(dataframe.drop(['clase'],axis=1))

y = np.array(dataframe['clase'])

f1 = dataframe['duracion'].values
f2 = dataframe['paginas'].values
f3 = dataframe['acciones'].values
f4 = dataframe['valor'].values 
f5 = dataframe['clase'].values
print(dataframe.shape)
print('###########################################')
print(x.shape)
print('###########################################')
model = linear_model.LogisticRegression(max_iter=4000)
model.fit(x,y)
predic = model.predict(x)
print(predic[0:5])
print('###########################################')
print(round(model.score(x,y),2))
print('###########################################')





# Validación de nuestro modelo
# Una buena practica es subdividir el conjunto de datos
# de entrada en un set de entrenamiento y otro para validar
# el modelo. Para evitar falle por sobregeneralizar el
# conocimiento

validation_size = 0.2
seed = 7
xTrain,xVali,yTrain,yVali = model_selection.train_test_split(x,y,test_size=validation_size,random_state=seed)

name='Regresión Logistica'
kfold = model_selection.KFold(n_splits=10,random_state=seed, shuffle=True)
cv_results = model_selection.cross_val_score(model,xTrain,yTrain,cv=kfold,scoring='accuracy')
msg = name+": "+str(round(cv_results.mean(),2))+" ("+str(round(cv_results.std(),2))+")"
print(msg)
print('###########################################')
predic = model.predict(xVali)
print('Porcentaje de seguridad '+str(round(accuracy_score(yVali,predic),2)))
print('###########################################')
#Reporte de Resultados del Modelo
print('---------------- Matris de Predicciones ----------------')
print(confusion_matrix(yVali,predic))
print('###########################################')
print('---------------- Reporte de Clasificaciones ----------------')
print(classification_report(yVali,predic))
print('###########################################')
print('---------------- Nuevas Predicciones ----------------')
X_new = pd.DataFrame({'duracion': [21], 'paginas': [2], 'acciones': [4], 'valor': [12]})
predicnew=model.predict(X_new)
print(predicnew)

