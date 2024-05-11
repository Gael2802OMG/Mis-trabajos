#include <stdio.h>
#include <stdlib.h>
#include <windows.h>
#include <conio.h>
//--------------------------------------
#define Rojo 		"\x1b[31m"
#define Verde 		"\x1b[32m"
#define Reset 		"\x1b[0m"
//--------------------------------------

FILE *CArch;
FILE *TArch;

struct Clientes{
	int Noclt,Ed,Cdt;
	int CdP,NoEx,NoInt;
	float SQ,GM;
	char Noms[25],Appart[10],Apmart[10];
	char Pais[10],Etd[10],Cd[10],Drc[20],Cll[10],Prd;
	char NomT[15],Ptlb[15],Sx,Stcvl[10];
}clts[5];

struct Tarjetas{
	int NoTrj,NoARG[16],CVV[3];
	char NomCL[45],Ttrj,FF[5];
	float Din;
}trjs[5];

//--------------------Funciones para registrar/acomodar-------------------
void gotoxy(int X, int Y){
	HANDLE hcon;
	hcon=GetStdHandle(STD_OUTPUT_HANDLE);
	COORD dwPos;
	dwPos.X=X;
	dwPos.Y=Y;
	SetConsoleCursorPosition(hcon,dwPos);
}

void valitext(int lon,char *pnom){
	int c=0, x=0;
    do{
		c=getch();
		if(c=='\n'){
			
		}
        if(c>=65 && c<=90 || c>=97 && c<=122 || c==32 || c==164 || c==165){
			printf("%c",c);
            *(pnom+x)=c;
            x++;
        }
        if(c==8 && x>0){
			x--;
		    printf("\b \b");
	        *(pnom+x)=' ';
        }
    }while(c!=13 && x<lon);
    *(pnom+x)=' ';
}

char valichar(int lon,char pnom){
	int c=0, x=0;
    do{
		c=getch();
		if(c=='\n')
			pnom=c;
        if(c>=65 && c<=90 || c>=97 && c<=122 || c==32 || c==164 || c==165){
			printf("%c",c);
            pnom=c;
            x++;
        }
        if(c==8 && x>0){
			x--;
		    printf("\b \b");
	        pnom=' ';
        }
    }while(c!=13 && x<lon);
    return pnom;
}

int nument(int lon, int num){
	int n=0;
	char car,cadena[lon]={' '};
	do
	{
		car=getch();
		if(car>=48 && car<=57)
		{
			printf("%c",car);
			cadena[n]=car;
			n++;
		}
		if(car==8 && n>0){
			printf("\b \b");//Borrar Espacion en print pero no en la cadena
			n--;
			cadena[n]=' ';//Borrar el espcacio en la cadena
		}
	}while(car!=13 && n<lon);
	return num=atoi(cadena);
}

float numdec(int lon, float num){
		int n=0;
	char car,cadena[lon]={' '};
	do
	{
		car=getch();
		if(car>=48 && car<=57)
		{
			printf("%c",car);
			cadena[n]=car;
			n++;
		}
		if(car==8 && n>0){
			printf("\b \b");//Borrar Espacion en print pero no en la cadena
			n--;
			cadena[n]=' ';//Borrar el espcacio en la cadena
		}
	}while(car!=13 && n<lon);
	return num=atof(cadena);
}

void valinum(int lon,char *pnom){
	int c=0, x=0;
    do{
		c=getch();
        if(c>=48 && c<=57){
			printf("%c",c);
            *(pnom+x)=c;
            x++;
        }
        if(c==8 && x>0){
			x--;
		    printf("\b \b");
	        *(pnom+x)=' ';
        }
    }while(c!=13 && x<lon);
    *(pnom+x)='\n';
}

void valifec(char *pfecha){
	int dia=0, mes=0, anio=0, x=0, c=0, d=0, m=0, a=0, fec=0, bi=0;
	char diac[2], mesc[2], anioc[4];
	do{
		x=d=m=a=0;
		strcpy(diac,"  ");
		strcpy(mesc,"  ");
		strcpy(anioc,"    ");
		do{
			c=getch();
	    	if(c>=48 && c<=57){
				printf("%c",c);
				if (x<5){
					mesc[m]=c;
					m++;
				}
				else{
					anioc[a]=c;
					a++;
				}
				*(pfecha+x)=c;
    	    	x++;
	    	}
	    	if(x==2){
				printf("/");
    	    	*(pfecha+x)='/';
        		x++;        
        	} 
			if(c==8 && x>0){
				x--;
	    		if(x==2){
					printf("\b\b  \b\b");
	    			*(pfecha+x)=' ';
	    			x--;
	    			*(pfecha+x)=' ';
	    			d--;
	    		}
	    		else if(x<2){
					printf("\b \b");
					*(pfecha+x)=' ';
					d--;
				}
	    		else if(x==5){
					printf("\b\b  \b\b");
	    			*(pfecha+x)=' ';
	    			x--;
					*(pfecha+x)=' ';
					m--;
	    		}
	    		else if(x<5){
					printf("\b \b");
					*(pfecha+x)=' ';
					m--;
				}
	    		else if(x>5){
					printf("\b \b");
	    			*(pfecha+x)=' ';
	    			a--;
	    		}
    		}            
		}while(x<5);
		mes=atoi(mesc);
		anio=atoi(anioc);
		bi=0;
		if(anio%4==0 && (anio%100!=0 || anio%400==0))
			bi++;
		if((mes==1 || mes==3 || mes==5 || mes==7 || mes==8 || mes==10 || mes==12) && anio>=1900 && anio<=2050)
			fec=1;
		else if((mes==4 || mes==6 || mes==9 || mes==11) && anio>=1900 && anio<=2050)
			fec=1;
		else if(mes==2 && anio>=1900 && anio<=2050)
			fec=1;
		else if(mes==2 && anio>=1900 && anio<=2050 && bi==1)
			fec=1;
		
	}while(fec==1);
}

//-----------------------Clientes---------------------
int Credito(float Sueldo, int x){
	if(Sueldo >= 4000){
		return Sueldo*16000/5000;
	}
	else{
		system("cls");
		gotoxy(40,8);
		printf(Rojo "____________________________________________"Reset);
		gotoxy(40,11);
		printf(Rojo "____________________________________________"Reset);	
		gotoxy(40,10);
		printf(Verde"No tiene buena nomina para solicitar credito"Reset);
		getch();
		
		clts[x].Noclt=NULL;
		clts[x].Noclt=NULL;
		clts[x].Ed=NULL;
		clts[x].NoEx=NULL;
		clts[x].NoInt=NULL;
		clts[x].CdP=NULL;
		clts[x].Prd=NULL;
		clts[x].Sx=NULL;
		clts[x].SQ=NULL;
		clts[x].GM=NULL;
		clts[x].Cdt=NULL;
			
		for(int y=0;y<20;y++){
			clts[x].Noms[y]=NULL;
			clts[x].Appart[y]=NULL;
			clts[x].Apmart[y]=NULL;
			clts[x].Pais[y]=NULL;
			clts[x].Etd[y]=NULL;
			clts[x].Cd[y]=NULL;
			clts[x].Drc[y]=NULL;
			clts[x].Cll[y]=NULL;
			clts[x].Stcvl[y]=NULL;
			clts[x].NomT[y]=NULL;
			clts[x].Ptlb[y]=NULL;
		}
		system("cls");
		gotoxy(40,8);
		printf(Rojo "___________________________"Reset);
		gotoxy(40,11);
		printf(Rojo "___________________________"Reset);	
		gotoxy(40,10);
		printf(Verde"El cliente a sido eleminado"Reset);
		getch();
	}
}

void RClt(void){
	int x=0,z=0;
	
	system("cls");
	for(x=0;x<5;x++)
		if(clts[x].Noclt==NULL)
			break;
	if(x==5){
		gotoxy(25,9);
		printf(Rojo "___________________________________"Reset);
		gotoxy(45,10);
		printf(Verde "No se puede registrar otro cliente" Reset);
		gotoxy(25,11);
		printf(Rojo "___________________________________"Reset);
		getch();
	}
	else{
		gotoxy(15,8);
		printf(Rojo "____________________________________________________________________________________"Reset);
		gotoxy(15,20);
		printf(Rojo "____________________________________________________________________________________"Reset);
		gotoxy(15,10);
		printf(Verde "Numero de Cliente: " Reset);
		gotoxy(15,11);
		printf(Verde "Nombre/s: " Reset);
		gotoxy(44,11);
		printf(Verde "Apellido Paterno: " Reset);
		gotoxy(73,11);
		printf(Verde "Apellido Materno: " Reset);
		gotoxy(15,12);
		printf(Verde "Edad: " Reset);
		gotoxy(15,13);
		printf(Verde"Pais: "Reset);
		gotoxy(48,13);
		printf(Verde"Estado: "Reset);
		gotoxy(73,13);
		printf(Verde"Ciudad: "Reset);
		gotoxy(15,14);
		printf(Verde"Direccion: "Reset);
		gotoxy(48,14);
		printf(Verde"Calle: "Reset);
		gotoxy(15,15);
		printf(Verde"Numero Exterior: "Reset);
		gotoxy(48,15);
		printf(Verde"Numero Interior: "Reset);
		gotoxy(73,15);
		printf(Verde"Codigo postal: "Reset);
		gotoxy(15,16);
		printf(Verde"Tipo de propiedad: A)Propia B)Rentada C)Prestada D)Familiar: "Reset);
		gotoxy(15,17);
		printf(Verde"Sexo H/M: "Reset);
		gotoxy(60,17);
		printf(Verde"Estado civil: "Reset);
		gotoxy(15,18);
		printf(Verde"Nombre del Trabajo: "Reset);
		gotoxy(60,18);
		printf(Verde"Puesto laboral: "Reset);
		gotoxy(15,19);
		printf(Verde"Sueldo Quincenal: "Reset);
		gotoxy(60,19);
		printf(Verde"Gasto Mensual: "Reset);
		gotoxy(34,10);
		clts[x].Noclt=nument(1,clts[x].Noclt);//Numero.C
		for(int y=0;y<5;y++){
			if(clts[x].Noclt==clts[y].Noclt && x!=y){
				system("cls");
				gotoxy(35,10);
				printf(Rojo "__________________________________"Reset);
				gotoxy(35,12);
				printf(Rojo "__________________________________"Reset);
				gotoxy(35,11);
				printf(Verde"Este numero ya esta registrado"Reset);
				clts[x].Noclt=NULL;
				x--;
				getch();
				z=1;
			}
		}
		if(z!=1){
			gotoxy(25,11);
			valitext(25,clts[x].Noms);//Nombres
			gotoxy(62,11);
			valitext(10,clts[x].Appart);//Ap.paterno
			gotoxy(91,11);
			valitext(10,clts[x].Apmart);//Ap.Mat
			gotoxy(21,12);
			clts[x].Ed=nument(3,clts[x].Ed);//Ed
			gotoxy(21,13);
			valitext(10,clts[x].Pais);//Pais
			gotoxy(56,13);
			valitext(10,clts[x].Etd);//Est
			gotoxy(81,13);
			valitext(10,clts[x].Cd);//Cd
			gotoxy(26,14);
			valitext(20,clts[x].Drc);//Drc
			gotoxy(55,14);
			valitext(10,clts[x].Cll);//Calle
			gotoxy(32,15);
			clts[x].NoEx=nument(8,clts[x].NoEx);//No.Ext
			gotoxy(65,15);
			clts[x].NoInt=nument(8,clts[x].NoInt);//No.Int
			gotoxy(88,15);
			clts[x].CdP=nument(8,clts[x].CdP);//Cod.postal
			
			do{
				gotoxy(76,16);
				clts[x].Prd=valichar(1,clts[x].Prd);//Propiedad
			}while(toupper(clts[x].Prd)!='A' && toupper(clts[x].Prd)!='B' && toupper(clts[x].Prd)!='C' && toupper(clts[x].Prd)!='D');
			
			do{
				gotoxy(25,17);
				clts[x].Sx=valichar(1,clts[x].Sx);//Sx
			}while(toupper(clts[x].Sx)!='H' && toupper(clts[x].Sx)!='M');
			
			gotoxy(74,17);
			valitext(10,clts[x].Stcvl);//Estado Cvl
			gotoxy(35,18);
			valitext(10,clts[x].NomT);//Nom.Trabajo
			gotoxy(76,18);
			valitext(10,clts[x].Ptlb);//Puesto Lab
			gotoxy(33,19);
			clts[x].SQ=numdec(10,clts[x].SQ);//Sueldo
			gotoxy(75,19);
			clts[x].GM=numdec(10,clts[x].GM);//Gasto
			clts[x].Cdt=(float)Credito(clts[x].SQ,x);
			getch();
		}	
	}
}

void CsClt(void){
	int R;
	do{
		system("cls");
		gotoxy(40,8);
		printf(Rojo "_________________________________"Reset);
		gotoxy(40,14);
		printf(Rojo "_________________________________"Reset);
		gotoxy(40,10);
		printf(Verde"1.Consulta General"Reset);
		gotoxy(40,11);
		printf(Verde"2.Consulta Especifica"Reset);
		gotoxy(40,12);
		printf(Verde"3.Salir"Reset);
		gotoxy(40,13);
		printf(Verde"Elija una opcion: "Reset);
		R=nument(1,R);
		switch(R){
			case 1:
				for(int x=0;x<5;x++){
					if(clts[x].Noclt!=NULL){
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(73,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						getch();
					}
				}
				break;
				
			case 2:
				int N=0,x=0;
				system("cls");
				gotoxy(40,8);
				printf(Rojo"__________________________________________________"Reset);
				gotoxy(40,11);
				printf(Rojo"__________________________________________________"Reset);
				gotoxy(40,10);
				printf(Verde"Elija el numero de cliente que quiere consultar: "Reset);
				N=nument(1,N);
				system("cls");
				for(x=0;x<5;x++){
					if(clts[x].Noclt==N){
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,19);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(73,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						getch();
						break;
					}
				}
				if(x==5){
					system("cls");
					gotoxy(40,8);
					printf(Rojo "______________________________________"Reset);
					gotoxy(40,11);
					printf(Rojo "______________________________________"Reset);
					gotoxy(40,10);
					printf(Verde"No esta registrado el cliente que pide"Reset);
					getch();
				}
				break;
			
		}
	}while(R!=3);
	
	
}

void BrClt(void){
	int N=0,x=0;
	system("cls");
	gotoxy(40,8);
	printf(Rojo "______________________________________________"Reset);
	gotoxy(40,11);
	printf(Rojo "______________________________________________"Reset);
	gotoxy(40,10);
	printf(Verde"Elija el numero de cliente que quiere Borrar: "Reset);
	N=nument(1,N);

	for(x=0;x<5;x++){
		if(clts[x].Noclt==N && N!=clts[x+1].Noclt){
			system("cls");
			for(int w=0;w<5;w++){
				if(clts[x].Noclt==trjs[w].NoTrj){
					trjs[w].NoTrj=NULL;  
					trjs[w].Din=NULL;
					trjs[w].Ttrj=NULL;
					
					for(int y=0;y<16;y++){
						trjs[w].NoARG[y]=NULL;
					}
					for(int y=0;y<3;y++){
						trjs[w].CVV[y]=NULL;
					}
					for(int y=0;y<45;y++){
						trjs[w].NomCL[y]=NULL;
					}
					for(int y=0;y<10;y++){
						trjs[w].FF[y]=NULL;
					}
					gotoxy(40,8);
					printf(Rojo "___________________________"Reset);
					gotoxy(40,11);
					printf(Rojo "___________________________"Reset);	
					gotoxy(40,10);
					printf(Verde"La tarjeta a sido eleminada"Reset);
					getch();
					break;
				}
			}
			system("cls");
			printf("x: %i",x);getch();
			clts[x].Noclt=NULL;
			clts[x].Ed=NULL;
			clts[x].NoEx=NULL;
			clts[x].NoInt=NULL;
			clts[x].CdP=NULL;
			clts[x].Prd=NULL;
			clts[x].Sx=NULL;
			clts[x].SQ=NULL;
			clts[x].GM=NULL;
			clts[x].Cdt=NULL;
			
			for(int y=0;y<20;y++){
				clts[x].Noms[y]=NULL;
				clts[x].Appart[y]=NULL;
				clts[x].Apmart[y]=NULL;
				clts[x].Pais[y]=NULL;
				clts[x].Etd[y]=NULL;
				clts[x].Cd[y]=NULL;
				clts[x].Drc[y]=NULL;
				clts[x].Cll[y]=NULL;
				clts[x].Stcvl[y]=NULL;
				clts[x].NomT[y]=NULL;
				clts[x].Ptlb[y]=NULL;
			}
			gotoxy(40,8);
			printf(Rojo "___________________________"Reset);
			gotoxy(40,11);
			printf(Rojo "___________________________"Reset);	
			gotoxy(40,10);
			printf(Verde"El cliente a sido eleminado"Reset);	
			getch();
			break;
		}	
	}
	if(x==5){
		system("cls");
		gotoxy(40,8);
		printf(Rojo "______________________________________"Reset);
		gotoxy(40,11);
		printf(Rojo "______________________________________"Reset);
		gotoxy(40,10);
		printf(Verde"No esta registrado el cliente que pide"Reset);
		getch();
	}
}

void EdClt(void){
	int R,N=0,x=0;
	system("cls");
	gotoxy(40,8);
	printf(Rojo "______________________________________________"Reset);
	gotoxy(40,11);
	printf(Rojo "______________________________________________"Reset);
	gotoxy(40,10);
	printf(Verde"Elija el numero del cliente que desea editar: "Reset);
	N=nument(1,N);
	for(x=0;x<5;x++){
		if(clts[x].Noclt==N){
			do{
				system("cls");
				gotoxy(40,3);
				printf(Rojo "__________________________________"Reset);
				gotoxy(40,6);
				printf(Rojo "__________________________________"Reset);
				gotoxy(40,5);
				printf(Verde"Elija la opcion que desea editar: "Reset);
				
				gotoxy(15,8);
				printf(Rojo "____________________________________________________________________________________"Reset);
				gotoxy(15,21);
				printf(Rojo "____________________________________________________________________________________"Reset);
				gotoxy(15,10);
				printf(Verde "1.Numero de Cliente: "Reset "%i",clts[x].Noclt);
				gotoxy(15,11);
				printf(Verde "2.Nombre/s: " Reset "%s",clts[x].Noms);
				gotoxy(44,11);
				printf(Verde "3.Apellido Paterno: " Reset"%s",clts[x].Appart);
				gotoxy(73,11);
				printf(Verde "4.Apellido Materno: " Reset "%s",clts[x].Apmart);
				gotoxy(15,12);
				printf(Verde "5.Edad: " Reset "%i",clts[x].Ed);
				gotoxy(15,13);
				printf(Verde"6.Pais: "Reset "%s",clts[x].Pais);
				gotoxy(48,13);
				printf(Verde"7.Estado: "Reset "%s",clts[x].Etd);
				gotoxy(73,13);
				printf(Verde"8.Ciudad: "Reset "%s",clts[x].Cd);
				gotoxy(15,14);
				printf(Verde"9.Direccion: "Reset "%s",clts[x].Drc);
				gotoxy(48,14);
				printf(Verde"10.Calle: "Reset "%s",clts[x].Cll);
				gotoxy(15,15);
				printf(Verde"11.Numero Exterior: "Reset "%i",clts[x].NoEx);
				gotoxy(48,15);
				printf(Verde"12.Numero Interior: "Reset "%i",clts[x].NoInt);
				gotoxy(73,15);
				printf(Verde"13.Codigo postal: "Reset "%i",clts[x].CdP);
				gotoxy(15,16);
				printf(Verde"14.Tipo de propiedad: "Reset "%c",clts[x].Prd);
				gotoxy(48,16);
				printf(Verde"15.Sexo H/M: "Reset "%c",clts[x].Sx);
				gotoxy(73,16);
				printf(Verde"16.Estado civil: "Reset "%s",clts[x].Stcvl);
				gotoxy(15,17);
				printf(Verde"17.Nombre del Trabajo: "Reset "%s",clts[x].NomT);
				gotoxy(48,17);
				printf(Verde"18.Puesto laboral: "Reset "%s",clts[x].Ptlb);
				gotoxy(15,18);
				printf(Verde"19.Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
				gotoxy(48,18);
				printf(Verde"20.Gasto Mensual: "Reset "%0.2f",clts[x].GM);
				gotoxy(75,18);
				printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
				gotoxy(45,19);
				printf(Verde"21. Salir"Reset);
				gotoxy(45,23);
				printf(Verde"Elija una opcion: "Reset);
				R=nument(2,R);
				switch(R){
					case 1:
						int Aux;
						Aux=clts[x].Noclt;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: " Reset);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(34,10);
						clts[x].Noclt=nument(1,clts[x].Noclt);
						for (int y=0;y<5;y++){
							if(Aux==trjs[y].NoTrj)
								trjs[y].NoTrj=clts[x].Noclt;
						}
						for(int y=0;y<5;y++){
							if(clts[x].Noclt==clts[y].Noclt && x!=y){
								system("cls");
								gotoxy(35,10);
								printf(Rojo "__________________________________"Reset);
								gotoxy(35,12);
								printf(Rojo "__________________________________"Reset);
								gotoxy(35,11);
								printf(Verde"Este numero ya esta registrado"Reset);
								trjs[x].NoTrj=clts[x].Noclt=Aux;
								getch();
							}
						}
						
						break;
					case 2:
						for(int y=0;y<20;y++){
							clts[x].Noms[y]=NULL;
						}
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(25,11);
						valitext(20,clts[x].Noms);//Nombres
						break;
						
					case 3:
						for(int y=0;y<10;y++){
							clts[x].Appart[y]=NULL;
						}
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(62,11);
						valitext(10,clts[x].Appart);//Ap.paterno
						break;
						
					case 4:
						for(int y=0;y<10;y++){
							clts[x].Apmart[y]=NULL;
						}
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(91,11);
						valitext(10,clts[x].Apmart);//Ap.Mat
						break;
						
					case 5:
						clts[x].Ed=NULL;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(21,12);
						clts[x].Ed=nument(3,clts[x].Ed);//Ed
						break;
						
					case 6:
						for(int y=0;y<10;y++){
							clts[x].Pais[y]=NULL;
						}	
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(21,13);
						valitext(10,clts[x].Pais);//Pais
						break;
					case 7:
						for(int y=0;y<20;y++){
							clts[x].Etd[y]=NULL;
						}
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(56,13);
						valitext(10,clts[x].Etd);//Est
						break;
					case 8:
						for(int y=0;y<10;y++){
							clts[x].Cd[y]=NULL;
						}
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(81,13);
						valitext(10,clts[x].Cd);//Cd
						break;
						
					case 9:
						for(int y=0;y<20;y++){
							clts[x].Drc[y]=NULL;
						}
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(26,14);
						valitext(20,clts[x].Drc);//Drc
						break;
						
					case 10:
						for(int y=0;y<10;y++){
							clts[x].Cll[y]=NULL;
						}
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(55,14);
						valitext(10,clts[x].Cll);//Calle
						break;
					case 11:
						clts[x].NoEx=NULL;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(32,15);
						clts[x].NoEx=nument(8,clts[x].NoEx);//No.Ext
						break;
					case 12:
						clts[x].NoInt=NULL;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(65,15);
						clts[x].NoInt=nument(8,clts[x].NoInt);//No.Int
						break;
					case 13:
						clts[x].CdP=NULL;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(88,15);
						clts[x].CdP=nument(8,clts[x].CdP);//Cod.postal
						break;
					case 14:
						clts[x].Prd=NULL;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,21);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: A)Propia B)Rentada C)Prestada D)Familiar: "Reset);
						gotoxy(15,17);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(60,17);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,18);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(60,18);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,19);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(60,19);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(60,20);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(76,16);
						clts[x].Prd=valichar(1,clts[x].Prd);//Propiedad
						break;
					case 15:
						clts[x].Sx=NULL;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(58,16);
						clts[x].Sx=valichar(1,clts[x].Sx);//Sx
						break;
					case 16:
						for(int y=0;y<20;y++){
							clts[x].Stcvl[y]=NULL;
						}
					
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(87,16);
						valitext(10,clts[x].Stcvl);//Estado Cvl
						break;
					case 17:
						for(int y=0;y<20;y++){
							clts[x].NomT[y]=NULL;
						}
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(35,17);
						valitext(10,clts[x].NomT);//Nom.Trabajo
						break;
					case 18:
						for(int y=0;y<20;y++){
							clts[x].Ptlb[y]=NULL;
						}
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(64,17);
						valitext(10,clts[x].Ptlb);//Puesto Lab
						break;
					case 19:
						clts[x].SQ=NULL;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset "%0.2f",clts[x].GM);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(33,18);
						clts[x].SQ=numdec(10,clts[x].SQ);//Sueldo
						clts[x].Cdt=(float)Credito(clts[x].SQ,x);
						break;
					case 20:
						clts[x].GM=NULL;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,20);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde "Numero de Cliente: "Reset "%i",clts[x].Noclt);
						gotoxy(15,11);
						printf(Verde "Nombre/s: " Reset "%s",clts[x].Noms);
						gotoxy(44,11);
						printf(Verde "Apellido Paterno: " Reset"%s",clts[x].Appart);
						gotoxy(73,11);
						printf(Verde "Apellido Materno: " Reset "%s",clts[x].Apmart);
						gotoxy(15,12);
						printf(Verde "Edad: " Reset "%i",clts[x].Ed);
						gotoxy(15,13);
						printf(Verde"Pais: "Reset "%s",clts[x].Pais);
						gotoxy(48,13);
						printf(Verde"Estado: "Reset "%s",clts[x].Etd);
						gotoxy(73,13);
						printf(Verde"Ciudad: "Reset "%s",clts[x].Cd);
						gotoxy(15,14);
						printf(Verde"Direccion: "Reset "%s",clts[x].Drc);
						gotoxy(48,14);
						printf(Verde"Calle: "Reset "%s",clts[x].Cll);
						gotoxy(15,15);
						printf(Verde"Numero Exterior: "Reset "%i",clts[x].NoEx);
						gotoxy(48,15);
						printf(Verde"Numero Interior: "Reset "%i",clts[x].NoInt);
						gotoxy(73,15);
						printf(Verde"Codigo postal: "Reset "%i",clts[x].CdP);
						gotoxy(15,16);
						printf(Verde"Tipo de propiedad: "Reset "%c",clts[x].Prd);
						gotoxy(48,16);
						printf(Verde"Sexo H/M: "Reset "%c",clts[x].Sx);
						gotoxy(73,16);
						printf(Verde"Estado civil: "Reset "%s",clts[x].Stcvl);
						gotoxy(15,17);
						printf(Verde"Nombre del Trabajo: "Reset "%s",clts[x].NomT);
						gotoxy(48,17);
						printf(Verde"Puesto laboral: "Reset "%s",clts[x].Ptlb);
						gotoxy(15,18);
						printf(Verde"Sueldo Quincenal: "Reset "%0.2f",clts[x].SQ);
						gotoxy(48,18);
						printf(Verde"Gasto Mensual: "Reset);
						gotoxy(75,18);
						printf(Verde "Credito: "Reset "%i",clts[x].Cdt);
						gotoxy(63,18);
						clts[x].GM=numdec(10,clts[x].GM);//Gasto
						break;
						
				}
			}while(R!=21);
		}
		if(x==5){
		system("cls");
		printf("No esta registrado el cliente que pide");
		getch();
		}
	}
}
	
void MClt(void){
	int R;
	do{
		system("cls");
		gotoxy(40,8);
		printf(Rojo"_______________________________"Reset);
		gotoxy(40,10);
		printf(Verde"1.Registrar"Reset);
		gotoxy(40,11);
		printf(Verde"2.Consultar"Reset);
		gotoxy(40,12);
		printf(Verde"3.Borrar"Reset);
		gotoxy(40,13);
		printf(Verde"4.Editar"Reset);
		gotoxy(40,14);
		printf(Verde"5.Salir"Reset);
		gotoxy(40,15);
		printf(Verde"Elija una opcion: "Reset);
		gotoxy(40,16);
		printf(Rojo"_______________________________"Reset);
		gotoxy(58,15);
		R=nument(1,R);
		switch(R){
			case 1:
				RClt();
				break;
			case 2:
				CsClt();
				break;
			case 3:
				BrClt();
				break;
			case 4:
				EdClt();
			
		}
	}while(R!=5);
}

//-------------------Tarjetas-----------------
void RTrj(void){
	int x=0,z=0,v=0,r=0;
	system("cls");
	for(x=0;x<5;x++){
		if(trjs[x].NoTrj==NULL){
			r=x;
			break;
		}
		
	}
		
	
			
	if(x==5){
		gotoxy(25,9);
		printf(Rojo "__________________________________"Reset);
		gotoxy(45,10);
		printf(Verde"No se puede registrar otra Tarjeta"Reset);
		gotoxy(25,11);
		printf(Rojo "__________________________________"Reset);
		getch();
	}
	else{
		system("cls");
		gotoxy(15,8);
		printf(Rojo "____________________________________________________________________________________"Reset);
		gotoxy(15,15);
		printf(Rojo "____________________________________________________________________________________"Reset);
		gotoxy(15,10);
		printf(Verde"Numero de tarjeta: "Reset);
		gotoxy(15,11);
		printf(Verde"Numero de cuenta: "Reset);
		gotoxy(54,11);
		printf(Verde"CVV: "Reset);
		gotoxy(15,12);
		printf(Verde"Nombre en la tarjeta: "Reset);
		gotoxy(15,13);
		printf(Verde"Tipo de tarjeta A)Visa B)MasterCard C)AmericanExpress: "Reset);
		gotoxy(15,14);
		printf(Verde"Fecha de Vencimiento: "Reset);
		gotoxy(48,14);
		printf(Verde"Sueldo en la tarjeta: "Reset);
		gotoxy(34,10);
		trjs[x].NoTrj=nument(1,trjs[x].NoTrj);
		for(int y=0;y<5;y++){
			if(trjs[x].NoTrj==trjs[y].NoTrj && x!=y){
				system("cls");
				trjs[x].NoTrj=NULL;
				x--;
				v=1;
				z=1;
				break;
			}
		}
		for(int y=0;y<5;y++){
			if(trjs[x].NoTrj!=clts[y].Noclt && x==y){
				z=1;
			}
		}
		if(z==0){
			v=0;
			for(x=r;x<5;x++){
				for(int w=0;w<5;w++){	
					if(trjs[x].NoTrj==clts[w].Noclt){
						trjs[x].NoTrj=NULL;
						x=w;
						trjs[x].NoTrj=clts[w].Noclt;
						
						gotoxy(33,11);
						for(int y=0;y<=15;y++){			//Numero de Cuenta
							trjs[x].NoARG[y]=rand()%10;
							printf("%i",trjs[x].NoARG[y]);
							if(y==3||y==7||y==11)
								printf(" ");
						}
						
						gotoxy(59,11);
						for(int y=0;y<=2;y++){			//CVV
							trjs[x].CVV[y]=rand()%10;
							printf("%i",trjs[x].CVV[y]);
							
						}
						
						gotoxy(37,12);
						for(int y=0;y<45;y++){			
							if(clts[x].Noms[y]!=NULL){
								if(clts[x].Noms[y]=='\n'){
									clts[x].Noms[y]==NULL;
									trjs[x].NomCL[y]={' '};
									printf("%c",trjs[x].NomCL[y]);
								}
								else{
									trjs[x].NomCL[y]=clts[x].Noms[y];
									printf("%c",trjs[x].NomCL[y]);
								}
								if(clts[x].Appart!=NULL && clts[x].Noms[y]==NULL){
									trjs[x].NomCL[y]=clts[x].Appart[y];
									printf("%c",trjs[x].NomCL[y]);
									if(clts[x].Apmart[y]!=NULL && clts[x].Apmart[y]==NULL){
										trjs[x].NomCL[y]=clts[x].Appart[y];
										printf("%c",trjs[x].NomCL[y]);
									}
								}
							}
						}
						do{
							gotoxy(70,13);
							trjs[x].Ttrj=valichar(1,trjs[x].Ttrj);
						}while(toupper(trjs[x].Ttrj) != 'A' && toupper(trjs[x].Ttrj)!='B' && toupper(trjs[x].Ttrj)!='C');
						
						gotoxy(37,14);
						valifec(trjs[x].FF);
						gotoxy(70,14);
						trjs[x].Din=numdec(7,trjs[x].Din);
						getch();
						v=1;
						break;
					}
							
				}
				if(v==1)
					break;
			}	
		}	
		else if(z==1 && v==1){
			system("cls");
			gotoxy(35,9);
			printf(Rojo "______________________________"Reset);
			gotoxy(35,12);
			printf(Rojo "______________________________"Reset);
			gotoxy(35,11);
			printf(Verde"Este numero ya esta registrado"Reset);
			getch();
		}
		else if(z==1 && v==0){
			system("cls");
			gotoxy(35,9);
			printf(Rojo "______________________________________________"Reset);
			gotoxy(35,12);
			printf(Rojo "______________________________________________"Reset);
			gotoxy(35,11);
			printf(Verde"Este numero no esta vinculado a ningun cliente"Reset);
			trjs[x].NoTrj=NULL;
			x--;
			getch();
		}
	}
	
		
}

void CsTrj(void){
	int R;
	do{
		int x,w;
		system("cls");	
		gotoxy(40,8);
		printf(Rojo "_________________________________"Reset);
		gotoxy(40,14);                  
		printf(Rojo "_________________________________"Reset);
		gotoxy(40,10);
		printf(Verde"1.Consulta General"Reset);
		gotoxy(40,11);
		printf(Verde"2.Consulta Especifica"Reset);
		gotoxy(40,12);
		printf(Verde"3.Salir"Reset);
		gotoxy(40,13);
		printf(Verde"Elija una opcion: "Reset);
		R=nument(1,R);
		switch(R){
			case 1:
				for(x=0;x<5;x++){
					if(trjs[x].NoTrj!=NULL){
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,15);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde"Numero de tarjeta: "Reset "%i",trjs[x].NoTrj);
						gotoxy(15,11);
						printf(Verde"Numero de cuenta: "Reset);
						gotoxy(33,11);
						for(int y=0;y<=15;y++){
							printf("%i",trjs[x].NoARG[y]);
							if(y==3||y==7||y==11)
								printf(" ");
						}
							
						gotoxy(54,11);
						printf(Verde"CVV: "Reset);
						gotoxy(59,11);
						for(int y=0;y<=2;y++)
							printf("%i",trjs[x].CVV[y]);
							
						gotoxy(15,12);
						printf(Verde"Nombre en la tarjeta: "Reset);
						gotoxy(37,12);
						for(int y=0;y<45;y++){
							printf("%c",trjs[x].NomCL[y]);
						}
						
						gotoxy(15,13);
						printf(Verde"Tipo de tarjeta A)Visa B)MasterCard C)AmericanExpress: "Reset "%c",trjs[x].Ttrj);	
						gotoxy(15,14);
						printf(Verde"Fecha de Vencimiento: "Reset "%s",trjs[x].FF);
						gotoxy(48,14);
						printf(Verde"Sueldo en la tarjeta: "Reset "%0.2f",trjs[x].Din);
						w=1;
						getch();
					}
				}
				if(x==5 && w!=1){
					system("cls");
					gotoxy(40,8);
					printf(Rojo "__________________________________"Reset);
					gotoxy(40,11);
					printf(Rojo "__________________________________"Reset);
					gotoxy(40,10);
					printf(Verde"No esta registrado ninguna tarjeta"Reset);
					getch();
				}
				break;
			case 2:
				int N,w=0;
				x=0;
				system("cls");
				gotoxy(40,8);
				printf(Rojo"__________________________________________________"Reset);
				gotoxy(40,11);
				printf(Rojo"__________________________________________________"Reset);
				gotoxy(40,10);
				printf(Verde"Elija el numero de tarjeta que quiere consultar: "Reset);
				N=nument(1,N);
				for(x=0;x<5;x++){
					system("cls");
					if(trjs[x].NoTrj==N){
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,15);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde"Numero de tarjeta: "Reset "%i",trjs[x].NoTrj);
						
						gotoxy(15,11);
						printf(Verde"Numero de cuenta: "Reset);
						gotoxy(33,11);
						for(int y=0;y<=15;y++){
							printf("%i",trjs[x].NoARG[y]);
							if(y==3||y==7||y==11)
								printf(" ");
						}
							
						gotoxy(54,11);
						printf(Verde"CVV: "Reset);
						gotoxy(59,11);
						for(int y=0;y<=2;y++)
							printf("%i",trjs[x].CVV[y]);
							
						gotoxy(15,12);
						printf(Verde"Nombre en la tarjeta: "Reset);
						gotoxy(37,12);
						for(int y=0;y<45;y++){
							printf("%c",trjs[x].NomCL[y]);
						}
						
						gotoxy(15,13);
						printf(Verde"Tipo de tarjeta A)Visa B)MasterCard C)AmericanExpress: "Reset "%c",trjs[x].Ttrj);	
						gotoxy(15,14);
						printf(Verde"Fecha de Vencimiento: "Reset "%s",trjs[x].FF);
						gotoxy(48,14);
						printf(Verde"Sueldo en la tarjeta: "Reset "%0.2f",trjs[x].Din);
						getch();
						w=1;
						break;
						
					}
				}
				if(x==5 && w!=1){
						system("cls");
						gotoxy(40,8);
						printf(Rojo "______________________________________"Reset);
						gotoxy(40,11);
						printf(Rojo "______________________________________"Reset);
						gotoxy(40,10);
						printf(Verde"No esta registrado la tarjeta que pide"Reset);
						getch();
				}
				
				break;
		}
		
	}while(R!=3);
}

void BrTrj(void){
	int N=0,x=0,w=0;
	system("cls");
	gotoxy(40,8);
	printf(Rojo "______________________________________________"Reset);
	gotoxy(40,11);
	printf(Rojo "______________________________________________"Reset);
	gotoxy(40,10);
	printf(Verde"Elija el numero de tarjeta que quiere Borrar: "Reset);
	N=nument(1,N);
	system("cls");
	for(x=0;x<5;x++){
		if(trjs[x].NoTrj==N){
			trjs[x].NoTrj=NULL;  
			trjs[x].Din=NULL;
			trjs[x].Ttrj=NULL;
			
			for(int y=0;y<16;y++){
				trjs[x].NoARG[y]=NULL;
			}
			for(int y=0;y<3;y++){
				trjs[x].CVV[y]=NULL;
			}
			for(int y=0;y<45;y++){
				trjs[x].NomCL[y]=NULL;
			}
			for(int y=0;y<10;y++){
				trjs[x].FF[y]=NULL;
			}
			gotoxy(40,8);
			printf(Rojo "___________________________"Reset);
			gotoxy(40,11);
			printf(Rojo "___________________________"Reset);	
			gotoxy(40,10);
			printf(Verde"La tarjeta a sido eleminada"Reset);
			getch();
			break;
		}
	}
	if(x==5){
		system("cls");
		gotoxy(40,8);
		printf(Rojo "______________________________________"Reset);
		gotoxy(40,11);
		printf(Rojo "______________________________________"Reset);
		gotoxy(40,10);
		printf(Verde"No esta registrada la tarjeta que pide"Reset);
		getch();
	}
}

void EdTrj(void){
	int R,N=0,x=0;
	system("cls");
	gotoxy(35,3);
	printf(Rojo "______________________________________________"Reset);
	gotoxy(35,6);
	printf(Rojo "______________________________________________"Reset);
	gotoxy(35,5);
	printf(Verde"Elija el numero del cliente que desea editar: "Reset);
	N=nument(1,N);
	for(x=0;x<5;x++){
		if(trjs[x].NoTrj==N){
			do{
				char RArg;
				system("cls");
				gotoxy(40,3);
				printf(Rojo "__________________________________"Reset);
				gotoxy(40,6);
				printf(Rojo "__________________________________"Reset);
				gotoxy(40,5);
				printf(Verde"Elija la opcion que desea editar: "Reset);
				
				gotoxy(15,8);
				printf(Rojo "____________________________________________________________________________________"Reset);
				gotoxy(15,17);
				printf(Rojo "____________________________________________________________________________________"Reset);
				gotoxy(15,10);
				printf(Verde"1.Numero de tarjeta: "Reset "%i",trjs[x].NoTrj);
							
				gotoxy(15,11);
				printf(Verde"2.Numero de cuenta: "Reset);
				gotoxy(35,11);
				for(int y=0;y<=15;y++){
					printf("%i",trjs[x].NoARG[y]);
					if(y==3||y==7||y==11){
						printf(" ");
					}
				}
						
				gotoxy(56,11);
				printf(Verde"3.CVV: "Reset);
				gotoxy(63,11);
				for(int y=0;y<=2;y++)
					printf("%i",trjs[x].CVV[y]);
								
				gotoxy(15,12);
				printf(Verde"Nombre en la tarjeta: "Reset);
				gotoxy(37,12);
				for(int y=0;y<45;y++){
					printf("%c",trjs[x].NomCL[y]);
				}
							
				gotoxy(15,13);
				printf(Verde"4.Tipo de tarjeta A)Visa B)MasterCard C)AmericanExpress: "Reset "%c",trjs[x].Ttrj);	
				gotoxy(15,14);
				printf(Verde"5.Fecha de Vencimiento: "Reset "%s",trjs[x].FF);
				gotoxy(48,14);
				printf(Verde"6.Dinero en la tarjeta: "Reset "%0.2f",trjs[x].Din);
				gotoxy(48,16);
				printf(Verde"7.Salir"Reset);
				gotoxy(40,19);
				printf(Verde"Elije una opcion: "Reset);
				R=nument(1,R);
				switch(R){
					case 1:
						int Aux;
						system("cls");
						Aux=trjs[x].NoTrj;
						trjs[x].NoTrj=NULL;
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,15);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde"Numero de tarjeta: "Reset);
						
						gotoxy(15,11);
						printf(Verde"Numero de cuenta: "Reset);
						gotoxy(33,11);
						for(int y=0;y<=15;y++){
							printf("%i",trjs[x].NoARG[y]);
							if(y==3||y==7||y==11)
								printf(" ");
						}
							
						gotoxy(54,11);
						printf(Verde"CVV: "Reset);
						gotoxy(59,11);
						for(int y=0;y<=2;y++)
							printf("%i",trjs[x].CVV[y]);
																
						gotoxy(15,12);
						printf(Verde"Nombre en la tarjeta: "Reset);
						gotoxy(37,12);
						for(int y=0;y<45;y++){
							printf("%c",trjs[x].NomCL[y]);
						}
							
						gotoxy(15,13);
						printf(Verde"Tipo de tarjeta A)Visa B)MasterCard C)AmericanExpress: "Reset "%c",trjs[x].Ttrj);	
						gotoxy(15,14);
						printf(Verde"Fecha de Vencimiento: "Reset "%s",trjs[x].FF);
						gotoxy(48,14);
						printf(Verde"Sueldo en la tarjeta: "Reset "%0.2f",trjs[x].Din);
						gotoxy(34,10);
						trjs[x].NoTrj=nument(1,trjs[x].NoTrj);
						for(int y=0;y<5;y++){
						if(trjs[x].NoTrj==trjs[y].NoTrj && x!=y){
							system("cls");
							gotoxy(35,10);
							printf(Rojo "__________________________________"Reset);
							gotoxy(35,12);
							printf(Rojo "__________________________________"Reset);
							gotoxy(35,11);
							printf(Verde"Este numero ya esta registrado"Reset);
							trjs[x].NoTrj=Aux;
							getch();
						}
						clts[x].Noclt=trjs[x].NoTrj;
					}
					break;
					case 2:
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,15);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde"Numero de tarjeta: "Reset "%i",trjs[x].NoTrj);
							
						gotoxy(15,11);
						printf(Verde"Numero de cuenta: "Reset);
						
								
						gotoxy(54,11);
						printf(Verde"CVV: "Reset);
						gotoxy(59,11);
						for(int y=0;y<=2;y++)
							printf("%i",trjs[x].CVV[y]);
								
						gotoxy(15,12);
						printf(Verde"Nombre en la tarjeta: "Reset);
						gotoxy(37,12);
						for(int y=0;y<45;y++){
							printf("%c",trjs[x].NomCL[y]);
						}
							
						gotoxy(15,13);
						printf(Verde"Tipo de tarjeta A)Visa B)MasterCard C)AmericanExpress: "Reset "%c",trjs[x].Ttrj);	
						gotoxy(15,14);
						printf(Verde"Fecha de Vencimiento: "Reset "%s",trjs[x].FF);
						gotoxy(48,14);
						printf(Verde"Sueldo en la tarjeta: "Reset "%0.2f",trjs[x].Din);
						gotoxy(40,19);
						printf(Verde"Crear numero de cuenta aleatorio? "Reset"[S]Si/[N]No: ");
						RArg=valichar(1,RArg);
						if(toupper(RArg)=='S'){
							gotoxy(33,11);
							for(int y=0;y<=15;y++){
								trjs[x].NoARG[y]=rand()%10;
								printf("%i",trjs[x].NoARG[y]);
								if(y==3||y==7||y==11)
									printf(" ");
							}
						}
							
					break;
					case 3:
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,15);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde"Numero de tarjeta: "Reset "%i",trjs[x].NoTrj);
							
						gotoxy(15,11);
						printf(Verde"Numero de cuenta: "Reset);
						gotoxy(33,11);
						for(int y=0;y<=15;y++){
							printf("%i",trjs[x].NoARG[y]);
							if(y==3||y==7||y==11)
								printf(" ");
						}
								
						gotoxy(54,11);
						printf(Verde"CVV: "Reset);
						gotoxy(59,11);
						for(int y=0;y<=2;y++)
							printf("%i",trjs[x].CVV[y]);
								
						gotoxy(15,12);
						printf(Verde"Nombre en la tarjeta: "Reset);
						gotoxy(37,12);
						for(int y=0;y<45;y++){
							printf("%c",trjs[x].NomCL[y]);
						}
							
						gotoxy(15,13);
						printf(Verde"Tipo de tarjeta A)Visa B)MasterCard C)AmericanExpress: "Reset "%c",trjs[x].Ttrj);	
						gotoxy(15,14);
						printf(Verde"Fecha de Vencimiento: "Reset "%s",trjs[x].FF);
						gotoxy(48,14);
						printf(Verde"Sueldo en la tarjeta: "Reset "%0.2f",trjs[x].Din);
						gotoxy(40,19);
						printf(Verde"Crear CVV aleatorio? "Reset"[S]Si/[N]No: ");
						RArg=valichar(1,RArg);
						gotoxy(59,11);
						for(int y=0;y<=2;y++){
							trjs[x].CVV[y]=rand()%10;
							printf("%i",trjs[x].CVV[y]);
						}
						
					break;
					case 4:
						trjs[x].Ttrj=NULL;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,15);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde"Numero de tarjeta: "Reset "%i",trjs[x].NoTrj);
							
						gotoxy(15,11);
						printf(Verde"Numero de cuenta: "Reset);
						gotoxy(33,11);
						for(int y=0;y<=15;y++){
							printf("%i",trjs[x].NoARG[y]);
							if(y==3||y==7||y==11)
								printf(" ");
						}
								
						gotoxy(54,11);
						printf(Verde"CVV: "Reset);
						gotoxy(59,11);
						for(int y=0;y<=2;y++)
							printf("%i",trjs[x].CVV[y]);
							
						gotoxy(15,12);
						printf(Verde"Nombre en la tarjeta: "Reset);
						gotoxy(37,12);
						for(int y=0;y<45;y++){
							printf("%c",trjs[x].NomCL[y]);
						}
							
						gotoxy(15,13);
						printf(Verde"Tipo de tarjeta A)Visa B)MasterCard C)AmericanExpress: "Reset);	
						gotoxy(15,14);
						printf(Verde"Fecha de Vencimiento: "Reset "%s",trjs[x].FF);
						gotoxy(48,14);
						printf(Verde"Sueldo en la tarjeta: "Reset "%0.2f",trjs[x].Din);
						do{
							gotoxy(70,13);
							trjs[x].Ttrj=valichar(1,trjs[x].Ttrj);
						}while(toupper(trjs[x].Ttrj) != 'A' && toupper(trjs[x].Ttrj)!='B' && toupper(trjs[x].Ttrj)!='C');
						
					break;
					case 5:
						for(int y=0;y<5;y++)
							trjs[x].FF[y]=NULL;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,15);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde"Numero de tarjeta: "Reset "%i",trjs[x].NoTrj);
							
						gotoxy(15,11);
						printf(Verde"Numero de cuenta: "Reset);
						gotoxy(33,11);
						for(int y=0;y<=15;y++){
							printf("%i",trjs[x].NoARG[y]);
							if(y==3||y==7||y==11)
								printf(" ");
						}
							
						gotoxy(54,11);
						printf(Verde"CVV: "Reset);
						gotoxy(59,11);
						for(int y=0;y<=2;y++)
							printf("%i",trjs[x].CVV[y]);
							
						gotoxy(15,12);
						printf(Verde"Nombre en la tarjeta: "Reset);
						gotoxy(37,12);
						for(int y=0;y<45;y++){
							printf("%c",trjs[x].NomCL[y]);
						}
							
						gotoxy(15,13);
						printf(Verde"Tipo de tarjeta A)Visa B)MasterCard C)AmericanExpress: "Reset "%c",trjs[x].Ttrj);	
						gotoxy(15,14);
						printf(Verde"Fecha de Vencimiento: "Reset);
						gotoxy(48,14);
						printf(Verde"Sueldo en la tarjeta: "Reset "%0.2f",trjs[x].Din);
						gotoxy(37,14);
						valifec(trjs[x].FF);
					break;
					case 6:
						trjs[x].Din=NULL;
						system("cls");
						gotoxy(15,8);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,15);
						printf(Rojo "____________________________________________________________________________________"Reset);
						gotoxy(15,10);
						printf(Verde"Numero de tarjeta: "Reset "%i",trjs[x].NoTrj);
							
						gotoxy(15,11);
						printf(Verde"Numero de cuenta: "Reset);
						gotoxy(33,11);
						for(int y=0;y<=15;y++){
							printf("%i",trjs[x].NoARG[y]);
							if(y==3||y==7||y==11)
								printf(" ");
						}
								
						gotoxy(54,11);
						printf(Verde"CVV: "Reset);
						gotoxy(59,11);
						for(int y=0;y<=2;y++)
							printf("%i",trjs[x].CVV[y]);
							
						gotoxy(15,12);
						printf(Verde"Nombre en la tarjeta: "Reset);
						gotoxy(37,12);
						for(int y=0;y<45;y++){
							printf("%c",trjs[x].NomCL[y]);
						}
							
						gotoxy(15,13);
						printf(Verde"Tipo de tarjeta A)Visa B)MasterCard C)AmericanExpress: "Reset "%c",trjs[x].Ttrj);	
						gotoxy(15,14);
						printf(Verde"Fecha de Vencimiento: "Reset "%s",trjs[x].FF);
						gotoxy(48,14);
						printf(Verde"Sueldo en la tarjeta: "Reset);
						gotoxy(70,14);
						trjs[x].Din=nument(7,trjs[x].Din);
					break;
				}
				
			}while(R!=7);	
		}
	}
}

void MTrj(void){
	int R;
	do{
		system("cls");
		gotoxy(40,8);
		printf(Rojo"_______________________________"Reset);
		gotoxy(40,10);
		printf(Verde"1.Crear"Reset);
		gotoxy(40,11);
		printf(Verde"2.Consultar"Reset);
		gotoxy(40,12);
		printf(Verde"3.Borrar"Reset);
		gotoxy(40,13);
		printf(Verde"4.Editar"Reset);
		gotoxy(40,14);
		printf(Verde"5.Salir"Reset);
		gotoxy(40,15);
		printf(Verde"Elija una opcion: "Reset);
		gotoxy(40,16);
		printf(Rojo"_______________________________"Reset);
		gotoxy(58,15);
		R=nument(1,R);
		switch(R){
			case 1:
				RTrj();
				break;
			case 2:
				CsTrj();
				break;
			case 3:
				BrTrj();
				break;
			case 4:
				EdTrj();
			
		}
	}while(R!=5);
}

//-----------------Guardado-----------
void Menu(void){
	int R;
	do{
		system("cls");
		gotoxy(40,8);
		printf(Rojo"_______________________________"Reset);
		gotoxy(40,10);
		printf(Verde"1.Menu Clientes"Reset);
		gotoxy(40,11);
		printf(Verde"2.Menu Tarjetas"Reset); 
		gotoxy(40,12);
		printf(Verde"3.Salir");
		gotoxy(40,13);
		printf(Verde"Elija una opcion: "Reset);
		gotoxy(40,14);
		printf(Rojo"_______________________________"Reset);
		gotoxy(58,13);
		R=nument(1,R);
		
		switch(R){
			case 1:
				MClt();
				break;
			case 2:
				MTrj();
				break;
			
		}
	}while(R!=3);
	
	
}

void LrClts(void){
    if((CArch=fopen("Clientes.dat","a+b"))!=NULL)
        fread(&clts,sizeof(Clientes),5,CArch);
    else{
        printf("El archivo no se pudo crear/abrir\n");
        getch();
    }
    fclose(CArch);
}

void LrTrjs(void){
	if((TArch=fopen("Tarjetas.dat","a+b"))!=NULL)
        fread(&trjs,sizeof(Tarjetas),5,TArch);
    else{
        printf("El archivo no se pudo crear/abrir\n");
        getch();
    }
    fclose(TArch);
}

void GrClts(void){
    if((CArch=fopen("Clientes.dat","w+b"))!=NULL)
        fwrite(&clts,sizeof(Clientes),5,CArch);
    else{
        printf("No se encontro el archivo");
        getch();
    }
    fclose(CArch);
}

void GrTrjs(void){
	if((TArch=fopen("Tarjetas.dat","w+b"))!=NULL)
        fwrite(&trjs,sizeof(Tarjetas),5,TArch);
    else{
        printf("No se encontro el archivo");
        getch();
    }
    fclose(TArch);
}

main(){
	LrClts();
	LrTrjs();
	Menu();
	GrClts();
	GrTrjs();
}



