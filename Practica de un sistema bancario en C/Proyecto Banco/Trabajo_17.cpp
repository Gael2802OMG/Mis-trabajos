#include <stdio.h>
#include <conio.h>

struct alumno{
	char nombre[10];
	int edad; int nota;
	};
	
int main (){
		struct alumno estudiante;
		FILE *parchivo;
		int numregistros; 
		parchivo=fopen("c:\\Users\\Merari Jazel\\Desktop\\carpeta\\class.dat","rb");
		if (parchivo != NULL){
			numregistros=fread(&estudiante,sizeof(struct alumno),1,parchivo);
			while (numregistros==1){
				printf("Nombre: %s \n",estudiante.nombre);
				printf("Edad: %d \n",estudiante.edad);
				printf("Edad: %d \n",estudiante.nota);
				numregistros=fread(&estudiante,sizeof(struct alumno),1,parchivo);
			}
			fclose(parchivo);
	    }
		else 
		printf("Error, no se ha podido crear el archivo");
	}
