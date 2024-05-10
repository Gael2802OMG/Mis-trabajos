/*
SQLyog Ultimate v11.11 (64 bit)
MySQL - 5.5.5-10.4.28-MariaDB-log : Database - biblioteca
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`biblioteca` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci */;

USE `biblioteca`;

/*Table structure for table `autor` */

DROP TABLE IF EXISTS `autor`;

CREATE TABLE `autor` (
  `id_aut` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `fech_nac` date DEFAULT NULL,
  `Nac` varchar(100) DEFAULT NULL,
  `descrip` varchar(1200) DEFAULT NULL,
  PRIMARY KEY (`id_aut`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `autor` */

insert  into `autor`(`id_aut`,`nombre`,`fech_nac`,`Nac`,`descrip`) values (1,'Gabriel García Márquez','1927-03-06','Mexicano','Ganador del Premio Nobel de Literatura'),(2,'J.K. Rowling','1965-07-31','Reino Unido','Autora de la serie de Harry Potter'),(3,'Haruki Murakami','1949-01-12','Japón','Autor de \"Tokio blues\" y \"1Q84\"'),(4,'Chimamanda Ngozi Adichie','1977-09-15','Nigeria','Escritora feminista y autora de \"Americanah\"'),(5,'George Orwell','1903-06-25','Reino Unido','Autor de \"1984\" y \"Rebelión en la granja\"'),(6,'Isabel Allende','1942-08-02','Chile','Autora de \"La casa de los espíritus\" y otros');

/*Table structure for table `devoluciones` */

DROP TABLE IF EXISTS `devoluciones`;

CREATE TABLE `devoluciones` (
  `id_dev` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(100) DEFAULT NULL,
  `id_pres` int(11) DEFAULT NULL,
  `fechadev` date DEFAULT NULL,
  `costodia` float(10,2) DEFAULT NULL,
  `subtotal` float(10,2) DEFAULT NULL,
  `diasderetra` int(11) DEFAULT NULL,
  `iva` float(10,2) DEFAULT NULL,
  PRIMARY KEY (`id_dev`),
  KEY `devoluciones_ibfk_1` (`id_pres`),
  CONSTRAINT `devoluciones_ibfk_1` FOREIGN KEY (`id_pres`) REFERENCES `prestamos` (`id_prest`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `devoluciones` */

insert  into `devoluciones`(`id_dev`,`codigo`,`id_pres`,`fechadev`,`costodia`,`subtotal`,`diasderetra`,`iva`) values (1,'dev-01',1,'2023-08-15',2.50,25.00,10,4.50),(2,'dev-02',2,'2023-07-30',1.75,17.50,15,3.15),(3,'dev-03',3,'2023-06-15',3.00,30.00,8,5.40),(4,'dev-04',4,'2023-05-25',2.00,20.00,12,3.60),(5,'dev-05',5,'2023-04-20',2.25,22.50,10,4.05),(6,'dev-06',6,'2023-03-30',1.50,15.00,9,2.70),(9,'dev-07',7,'2023-08-21',2.50,25.00,10,4.00),(10,'dev-08',8,'2023-08-20',1.50,15.00,10,2.40),(11,'dev-09',9,'2023-08-31',0.00,0.00,0,0.00);

/*Table structure for table `estudiante` */

DROP TABLE IF EXISTS `estudiante`;

CREATE TABLE `estudiante` (
  `id_est` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `carrera` varchar(100) DEFAULT NULL,
  `tel_est` varchar(100) DEFAULT NULL,
  `adeudos` float DEFAULT NULL,
  `Estatus` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_est`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `estudiante` */

insert  into `estudiante`(`id_est`,`matricula`,`nombre`,`correo`,`carrera`,`tel_est`,`adeudos`,`Estatus`) values (1,'2021030266','Juan Pérez','juan@example.com','Ingeniería','555-123-4567',0,1),(2,'2021030388','María González','maria@example.com','Medicina','555-987-6543',25,1),(3,'2021030895','Carlos Rodríguez','carlos@example.com','Derecho','555-555-5555',10.5,0),(4,'2021030225','Laura Martínez','laura@example.com','Psicología','555-111-2222',5.25,0),(5,'2021030697','Sofía López','sofia@example.com','Arquitectura','555-333-4444',0,1),(6,'2021031045','Luis Hernández','luis@example.com','Economía','555-666-7777',3.75,0),(9,'2021039876','Andrés Torres','andres@example.com','Historia','555-888-9999',20.5,1),(10,'2021032345','Elena Ramírez','elena@example.com','Química','555-444-5555',0,1),(11,'2021038765','Diego Herrera','diego@example.com','Sociología','555-777-8888',6.75,0),(12,'2021035432','María Castro','maria.c@example.com','Lengua y Literatura','555-666-5555',0,0),(13,'2021037654','Jorge García','jorge@example.com','Física','555-222-1111',2.5,1),(14,'2021032345','Mónica Díaz','monica@example.com','Educación','555-555-4444',1.75,0),(15,'2021038765','Roberto Rivas','roberto@example.com','Matemáticas','555-888-7777',0,0),(16,'2021036543','Laura Vargas','laura.v@example.com','Psicología','555-333-2222',7.25,1),(17,'2021039876','José Torres','jose@example.com','Ingeniería','555-666-8888',0,0),(18,'2021032345','Fernanda Martínez','fernanda@example.com','Medicina','555-444-3333',4.5,1),(19,'2021038765','Alejandro López','alejandro@example.com','Arquitectura','555-777-5555',0,0),(20,'2021035432','Ana Pérez','ana.p@example.com','Derecho','555-555-6666',9.75,1),(21,'2021037654','Carmen Soto','carmen@example.com','Economía','555-222-3333',0,0);

/*Table structure for table `libro` */

DROP TABLE IF EXISTS `libro`;

CREATE TABLE `libro` (
  `id_lib` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(100) DEFAULT NULL,
  `categ` varchar(100) DEFAULT NULL,
  `id_autor` int(11) DEFAULT NULL,
  `editorial` varchar(100) DEFAULT NULL,
  `fech_p` date DEFAULT NULL,
  `num_pag` int(11) DEFAULT NULL,
  `num_ejem` int(11) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `preciolibro` float DEFAULT NULL,
  PRIMARY KEY (`id_lib`),
  KEY `autor` (`id_autor`),
  CONSTRAINT `fk_libro_autor` FOREIGN KEY (`id_autor`) REFERENCES `autor` (`id_aut`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `libro` */

insert  into `libro`(`id_lib`,`codigo`,`categ`,`id_autor`,`editorial`,`fech_p`,`num_pag`,`num_ejem`,`nombre`,`preciolibro`) values (1,'c-001','Ficción',1,'Futuro(Argentina)','1985-03-10',350,10,'Cien años de soledad',25),(2,'c-002','Fantasía',2,'Mágica(México)','1997-06-26',320,8,'Harry Potter y la piedra filosofal',18.99),(3,'c-003','Realismo mágico',1,'Magico(Ecuador)','1981-12-05',280,6,'Crónica de una muerte anunciada',15.5),(4,'c-004','Distopía',5,'Distopía(Argentina)','1949-06-08',300,5,'1984',12.5),(5,'c-005','Romance',6,'Romance (Mexico)','1982-05-08',400,12,'La casa de los espíritus',22.95),(6,'c-006','Suspense',3,'Suspense (España)','2001-09-05',420,7,'Tokio blues',20.75),(8,'c-007','Misterio',4,'Misterio (Mexico)','2005-10-15',280,5,'El Código Da Vinci',14.99),(9,'c-008','Ciencia Ficción',6,'cion(Argentina)','2010-04-20',400,8,'Los juegos del hambre',19.75),(10,'c-009','Novela Histórica',3,'NovHistorica (España)','1999-08-05',320,7,'En el tiempo de las mariposas',16.5),(11,'c-010','Terror',5,'Terror (E.U)','1987-07-12',250,6,'It',11.25),(12,'c-011','Romance',2,'Romance (Mexico)','2015-12-30',380,9,'Orgullo y prejuicio',13.95),(13,'c-012','Fantasía',1,'Mágica(México)','2003-06-18',360,10,'El señor de los anillos',22.5),(14,'c-013','Ficción',5,'Futuro(Argentina)','2018-02-28',300,6,'La chica del tren',17.25),(15,'c-014','Suspense',3,'Suspense (España)','2008-09-10',420,8,'El nombre del viento',21.99),(16,'c-015','Distopía',4,'Distopía(Argentina)','2012-11-05',290,5,'Divergente',14),(17,'c-016','Aventura',2,'Aventura (España)','1990-03-08',240,7,'La isla del tesoro',10.5),(18,'c-017','Realismo mágico',1,'Magico(Ecuador)','1982-07-25',310,6,'Cien años de soledad',23.5),(19,'c-018','Fantasía',6,'Mágica(México)','2016-06-02',340,7,'El alquimista',18.25),(20,'c-019','Terror',5,'Terror (E.U)','2007-10-18',270,5,'Drácula',12.75);

/*Table structure for table `personal` */

DROP TABLE IF EXISTS `personal`;

CREATE TABLE `personal` (
  `id_per` int(11) NOT NULL AUTO_INCREMENT,
  `matricula` varchar(100) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `contraseña` varchar(100) DEFAULT NULL,
  `cargo` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `tel_est` varchar(100) DEFAULT NULL,
  `direccion` varchar(100) DEFAULT NULL,
  `Estatus` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id_per`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `personal` */

insert  into `personal`(`id_per`,`matricula`,`nombre`,`contraseña`,`cargo`,`correo`,`tel_est`,`direccion`,`Estatus`) values (1,'2019030225','Ana Martínez','Ana123','Bibliotecaria','ana@example.com','555-111-2222','Calle Principal 123',0),(2,'2019030520','Luis Ramírez','Luis123','Asistente','luis@example.com','555-333-4444','Avenida Secundaria 456',0),(3,'2019030558','María Sánchez','Maria123','Bibliotecaria','maria@example.com','555-555-5555','Calle Secundaria 789',0),(4,'2019030558','Juan García','Juan123','Asistente','juan@example.com','555-777-8888','Avenida Principal 789',0),(5,'2019030985','Elena Fernández','Elena123','Bibliotecaria','elena@example.com','555-999-0000','Calle Central 456',0),(6,'2019030565','Roberto Martínez','Roberto123','Asistente','roberto@example.com','555-222-3333','Avenida Central 789',1),(11,'2020031234','Jorge Martínez','Jorge123','Bibliotecario','jorge@example.com','555-123-4567','Calle Principal 123',1),(12,'2020035678','María Rodríguez','Maria123','Asistente','maria@example.com','555-987-6543','Avenida Secundaria 456',0),(13,'2020037890','Luis Sánchez','Luis123','Bibliotecario','luis@example.com','555-555-5555','Calle Secundaria 789',1),(14,'2020034321','Ana López','Ana123','Asistente','ana@example.com','555-111-2222','Avenida Principal 789',0),(15,'2020038765','Diego Martínez','Diego123','Bibliotecario','diego@example.com','555-777-8888','Calle Central 456',1),(16,'2020033456','Carla González','Carla123','Asistente','carla@example.com','555-666-7777','Avenida Central 789',0),(17,'2020036543','Andrés Fernández','Andres123','Bibliotecario','andres@example.com','555-222-3333','Calle Principal 123',1),(18,'2020039876','Elena Torres','Elena123','Asistente','elena@example.com','555-888-9999','Avenida Secundaria 456',0),(19,'2020032345','Sara Ramírez','Sara123','Bibliotecario','sara@example.com','555-444-5555','Calle Secundaria 789',1),(20,'2020038765','Luis García','Luis123','Asistente','luis@example.com','555-777-8888','Avenida Principal 789',0),(21,'2020035432','Laura Martínez','Laura123','Bibliotecario','laura@example.com','555-666-5555','Calle Central 456',1),(22,'2020037654','Juan González','Juan123','Asistente','juan@example.com','555-222-1111','Avenida Central 789',0);

/*Table structure for table `prestamos` */

DROP TABLE IF EXISTS `prestamos`;

CREATE TABLE `prestamos` (
  `id_prest` int(11) NOT NULL AUTO_INCREMENT,
  `codigo` varchar(100) DEFAULT NULL,
  `fechini` date DEFAULT NULL,
  `fechfin` date DEFAULT NULL,
  `id_lib` int(11) DEFAULT NULL,
  `id_est` int(11) NOT NULL,
  `id_per` int(11) NOT NULL,
  PRIMARY KEY (`id_prest`),
  KEY `id_est` (`id_est`),
  KEY `id_per` (`id_per`),
  KEY `prestamos_ibfk_1` (`id_lib`),
  CONSTRAINT `prestamos_ibfk_1` FOREIGN KEY (`id_lib`) REFERENCES `libro` (`id_lib`),
  CONSTRAINT `prestamos_ibfk_2` FOREIGN KEY (`id_est`) REFERENCES `estudiante` (`id_est`),
  CONSTRAINT `prestamos_ibfk_3` FOREIGN KEY (`id_per`) REFERENCES `personal` (`id_per`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `prestamos` */

insert  into `prestamos`(`id_prest`,`codigo`,`fechini`,`fechfin`,`id_lib`,`id_est`,`id_per`) values (1,'pres-01','2023-08-01','2023-08-15',1,1,1),(2,'pres-02','2023-07-15','2023-07-30',2,2,2),(3,'pres-03','2023-06-01','2023-06-15',3,3,3),(4,'pres-04','2023-05-10','2023-05-25',4,4,4),(5,'pres-05','2023-04-05','2023-04-20',5,5,5),(6,'pres-06','2023-03-15','2023-03-30',6,6,6),(7,'pres-07','2023-08-17','2023-08-20',8,9,11),(8,'pres-08','2023-08-10','2023-08-20',9,11,12),(9,'pres-09','2023-08-23','2023-08-31',13,13,13);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
