#include <stdio.h>
#include <conio.h>

struct alumno{
	char nombre[10];
	int edad,nota;
	};
	
	main (){
		struct alumno estudiante;
		FILE *parchivo;
		char mas;
		parchivo=fopen("C:\\Users\\Merari Jazel\\Desktop\\carpeta\\class.dat","ab");
		if (parchivo != NULL){
			do{
				printf("Nombre Alumno: ");
				scanf("%s",&estudiante.nombre);
				printf("Edad: ");
				scanf("%d",&estudiante.edad);
				printf("Nota: ");
				scanf("%d",&estudiante.nota);
				fwrite (&estudiante,sizeof(struct alumno),1,parchivo);
				printf("Mas Alumnos: "); mas=getch();
			}while (mas=='s' || mas=='S');
			fclose(parchivo);
		}
		else 
		printf("Error, no se ha podido crear el archivo");
	}
