#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <conio.h>

FILE *Arch;

struct Clientes{
	int No_Cliente,edad; 
	char Nom[20],Ap_Pat[10],Ap_Mat[10];
	char pais[10],Estado[10],Ciudad[10],Direccion[20],Propiedad[2];
	int Cod_Postal,No_Ex,No_int;
	int Su_M,Gas_M;
	char Nom_Trb[15],Pus_lab[10],Sexo[2],Est_cvl[10];
}clts;

struct Tarjetas{
	int Num_ARG[10],CVV[3];
	char Nom_Cl[25],Tipo[10],FF[5];
	float Din;
}trjs;

void RClientes(void){
	Arch=fopen("c:\\Users\\gaels\\Desktop\\Banco\\Clientes.dat","ab");
	if(Arch==NULL){
		printf("No se encontro el archivo");
		getch();
	}
	else{
		system("cls");
		printf("Numero de Cliente: ");
		scanf("%i",&clts.No_Cliente);
		printf("Edad: ");
		scanf("%i",&clts.edad);
		printf("Nombre/s: ");
		scanf("%s",&clts.Nom);
		printf("Apellido Paterno: ");
		scanf("%s",&clts.Ap_Pat);
		printf("Apellido Materno: ");
		scanf("%s",&clts.Ap_Mat);
		printf("País de Origen: ");
		scanf("%s",&clts.pais);
		printf("Estado: ");
		scanf("%s",&clts.Estado);
		printf("Ciudad: ");
		scanf("%s",&clts.Ciudad);
		printf("Direccion: ");
		scanf("%s",&clts.Direccion);
		printf("Numero Exterior: ");
		scanf("%s",&clts.No_Ex);
		printf("Numero Interior: ");
		scanf("%s",&clts.No_int);
		printf("Codigo Postal: ");
		scanf("%i",&clts.Cod_Postal);
		printf("Sueldo Mensual: ");
		scanf("%i",&clts.Su_M);
		printf("Gasto Mensual: ");
		scanf("%i",&clts.Gas_M);
		printf("Tipo de propiedad a)Propia b)Rentada c)Prestada d)Familiar ");
		scanf("%s",&clts.Propiedad);
		printf("Nombre del trabajo: ");
		scanf("%s",&clts.Nom_Trb);
		printf("puesto laboral: ");
		scanf("%s",&clts.Pus_lab);
		printf("Sexo H/M: ");
		scanf("%s",&clts.Sexo);
		printf("Estado civil: ");
		scanf("%s",&clts.Est_cvl);
		fwrite(&clts,sizeof(clts),1,Arch);
		getch();
		fclose(Arch);
	}
}

void CTarjetas(void){
	Arch=fopen("c:\\Users\\gaels\\Desktop\\Banco\\Tarjetas.dat","a+b");
	if(Arch==NULL){
		printf("No se encontro el archivo");
		getch();
	}
	else{
		printf("Numero de Tarjeta: ");
		for(int x=0;x<=9;x++){
			printf("%i",trjs.Num_ARG[x]=rand()%11);
		}
		printf("CVV: ");
		for(int x=0;x<=2;x++){
			printf("%i",trjs.CVV[x]=rand()%11);
		}
		printf("Fecha de vencimiento: ");
		scanf("%s",&trjs.FF);
		printf("Nombre del Cliente: ");
		scanf("%s",&trjs.Nom_Cl);
		printf("Fecha de vencimiento: ");
		scanf("%s",&trjs.FF);
		printf("Banco: ");
		scanf("%s",trjs.Tipo);
		printf("Dinero: ");
		scanf("%i",&trjs.Din);
		
		
		fwrite(&clts,sizeof(clts),1,Arch);
		getch();
		fclose(Arch);
	}
	
}

void MTarjetas(void){
	int R;
	do{
		system("cls");
		printf("Elije una opcion: \n1. Crear \n2. Consulta \n3. Editar \n4. Borrar \n5. Regresar al Menu\n");
		scanf("%i",&R);
		switch(R){
			case 1:
				CTarjetas();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
		}
	}while(R!=5);
}

void MClientes(void){
	int R;
	do{
		system("cls");
		printf("Elije una opcion: \n1. Registro \n2. Consulta \n3. Editar \n4. Borrar \n5. Regresar al Menu\n");
		scanf("%i",&R);
		switch(R){
			case 1:
				RClientes();
				break;
			case 2:
				break;
			case 3:
				break;
			case 4:
				break;
		}
	}while(R!=5);
}

void Menu(void){
	int R;
	do{
		system("cls");
		printf("Elije una opcion: \n1. Cliente \n2. Tarjetas \n3. Estado de Cuenta \n4. Salir\n");
		scanf("%i",&R);
		switch(R){
			case 1:
				MClientes();
				break;
			case 2:
				MTarjetas();
				break;
			case 3:
				break;
		}
	}while(R!=4);
}

void LArch(void){
	if((Arch=fopen("c:\\Users\\gaels\\Desktop\\Banco\\Clientes.dat","a+b"))!=NULL){
		fwrite(&clts,sizeof(struct Clientes),1,Arch);
	}
	else{
		printf("\nEl Archivo no se pudo crear/abrir\n");
		getch();
	}
	fclose(Arch);
}

void GArch(void){
	if((Arch=fopen("c:\\Users\\gaels\\Desktop\\Banco\\Clientes.dat","a+b"))!=NULL){
		fread(&clts,sizeof(struct Clientes),1,Arch);
		fclose(Arch);
	}
	else{
		printf("\nEl Archivo no se encontro\n");
	}
	
} 

main(){
	LArch();
	Menu();
	GArch();
}