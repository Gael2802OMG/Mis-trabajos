#include <stdio.h>
#include <ctype.h>
#include <windows.h>
#include <conio.h>

void altas(void);
void muestra(void);

FILE *fich;

struct ficha{
	int codigo;
	char nombre[25];
	char direccion[40];
	int edad;
} cliente;

main(){
	char op;
	do{
		if((fich=fopen("C:\\Users\\Merari Jazel\\Desktop\\Programa\\archivos\\gestion.dat","a+b"))==NULL){
			printf("Error al crear fichero");
			break;
		}
		system("cls"); 
		printf("Altas\n");
		printf("Consulta\n");
		printf("Salir\n\n");
		printf("Elegir opcion\n");
		scanf("%c",&op);
		
		switch(toupper(op)){
			case 'A':
				altas();
				break;
			case 'C':
				muestra();
				break;
		}
		fclose(fich);
	}while(toupper(op)!='S');
}

void altas(void){
	system("cls");
	printf("Codigo: ");
	scanf("%i",&cliente.codigo);
	printf("Nombre: ");
	scanf("%i",&cliente.nombre);
	printf("Direccion: ");
	scanf("%i",&cliente.direccion);
	printf("Edad: ");
	scanf("%i",&cliente.edad);
	fwrite(&cliente,sizeof(cliente),1,fich);
}

void muestra(void){
	int cod;
	system("cls");
	rewind(fich);
	printf("Codigo a mostrar:");
	scanf("%i",&cod);
	while(!feof(fich)){
		fread(&cliente,sizeof(cliente),1,fich);
		if(cod==cliente.codigo){
			printf("Codigo: %i\n",cliente.codigo);
			printf("Nombre: %s\n",cliente.nombre);
			printf("Direccion: %s\n",cliente.direccion);
			printf("Edad: %i\n",cliente.edad);
			getch();
			break;
		}
	}
}
