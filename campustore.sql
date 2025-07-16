-- MySQL dump 10.13  Distrib 8.0.42, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: campustore
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.32-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `carritodecompras`
--

DROP TABLE IF EXISTS `carritodecompras`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `carritodecompras` (
  `idcarrito` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) DEFAULT NULL,
  `fechacreacion` date DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idcarrito`),
  KEY `idusuario` (`idusuario`),
  CONSTRAINT `carritodecompras_ibfk_1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `carritodecompras`
--

LOCK TABLES `carritodecompras` WRITE;
/*!40000 ALTER TABLE `carritodecompras` DISABLE KEYS */;
INSERT INTO `carritodecompras` VALUES (1,1,NULL,'1'),(3,1,'2025-07-16','1');
/*!40000 ALTER TABLE `carritodecompras` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categoria`
--

DROP TABLE IF EXISTS `categoria`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categoria` (
  `idcategoria` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `descripcion` varchar(300) DEFAULT NULL,
  PRIMARY KEY (`idcategoria`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categoria`
--

LOCK TABLES `categoria` WRITE;
/*!40000 ALTER TABLE `categoria` DISABLE KEYS */;
INSERT INTO `categoria` VALUES (1,'aldo','23213'),(2,'calzados','Ventas de Medias'),(3,'Tecnologia','Uso de tecnologia'),(4,'Hojas','Material complementario'),(5,'Apuntes','Material Didactico'),(6,'Escritorio','Material'),(7,'ONU','Ayuda Humanitaria'),(9,'aa','Ayuda Humanitaria');
/*!40000 ALTER TABLE `categoria` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `detalle_carrito`
--

DROP TABLE IF EXISTS `detalle_carrito`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `detalle_carrito` (
  `idcarrito` int(11) NOT NULL,
  `idproducto` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT NULL,
  `precio_unitario` decimal(10,2) DEFAULT NULL,
  `subtotal` decimal(10,2) DEFAULT NULL,
  `fecha_agregado` date DEFAULT NULL,
  `estado_item` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idcarrito`,`idproducto`),
  KEY `detalle_carrito_ibfk_2` (`idproducto`),
  CONSTRAINT `detalle_carrito_ibfk_1` FOREIGN KEY (`idcarrito`) REFERENCES `carritodecompras` (`idcarrito`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `detalle_carrito_ibfk_2` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `detalle_carrito`
--

LOCK TABLES `detalle_carrito` WRITE;
/*!40000 ALTER TABLE `detalle_carrito` DISABLE KEYS */;
/*!40000 ALTER TABLE `detalle_carrito` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mensajes`
--

DROP TABLE IF EXISTS `mensajes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mensajes` (
  `id_mensaje` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) DEFAULT NULL,
  `asunto` varchar(100) DEFAULT NULL,
  `mensaje` varchar(250) DEFAULT NULL,
  `fecha_envio` date DEFAULT NULL,
  `estado_item` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id_mensaje`),
  KEY `idusuario` (`idusuario`),
  CONSTRAINT `mensajes_ibfk_1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mensajes`
--

LOCK TABLES `mensajes` WRITE;
/*!40000 ALTER TABLE `mensajes` DISABLE KEYS */;
/*!40000 ALTER TABLE `mensajes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pedido`
--

DROP TABLE IF EXISTS `pedido`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pedido` (
  `idpedido` int(11) NOT NULL AUTO_INCREMENT,
  `idcarrito` int(11) DEFAULT NULL,
  `fechapedido` date DEFAULT NULL,
  `montototal` decimal(10,2) DEFAULT NULL,
  `estado` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`idpedido`),
  KEY `idcarrito` (`idcarrito`),
  CONSTRAINT `pedido_ibfk_1` FOREIGN KEY (`idcarrito`) REFERENCES `carritodecompras` (`idcarrito`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pedido`
--

LOCK TABLES `pedido` WRITE;
/*!40000 ALTER TABLE `pedido` DISABLE KEYS */;
INSERT INTO `pedido` VALUES (1,1,NULL,12.23,'activo');
/*!40000 ALTER TABLE `pedido` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `producto`
--

DROP TABLE IF EXISTS `producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `producto` (
  `idproducto` int(11) NOT NULL AUTO_INCREMENT,
  `idcategoria` int(11) DEFAULT NULL,
  `nombre` varchar(100) DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `precio` decimal(10,2) DEFAULT NULL,
  `imagen` varchar(350) DEFAULT NULL,
  PRIMARY KEY (`idproducto`),
  KEY `idcategoria` (`idcategoria`),
  CONSTRAINT `producto_ibfk_1` FOREIGN KEY (`idcategoria`) REFERENCES `categoria` (`idcategoria`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `producto`
--

LOCK TABLES `producto` WRITE;
/*!40000 ALTER TABLE `producto` DISABLE KEYS */;
INSERT INTO `producto` VALUES (1,1,'aldo',12,23.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(2,1,'danny',23,23.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(3,1,'calculadora',123,22.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(4,1,'Example',23,3.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(5,1,'Example',56,63.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(6,2,'Teclado',45,15.50,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(7,3,'Mouse',87,12.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(8,1,'Monitor',20,150.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(9,4,'Impresora',10,200.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(10,2,'Webcam',30,50.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(11,1,'Auriculares',60,25.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(12,2,'Micrófono',18,45.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(13,3,'Laptop',7,800.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(14,4,'Tablet',12,300.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png'),(15,2,'Smartphone',50,500.00,'https://upload.wikimedia.org/wikipedia/commons/4/47/PNG_transparency_demonstration_1.png');
/*!40000 ALTER TABLE `producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `resenas_producto`
--

DROP TABLE IF EXISTS `resenas_producto`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `resenas_producto` (
  `id_resena` int(11) NOT NULL AUTO_INCREMENT,
  `idusuario` int(11) DEFAULT NULL,
  `idproducto` int(11) DEFAULT NULL,
  `calificacion` int(11) DEFAULT NULL,
  `comentario` varchar(250) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  PRIMARY KEY (`id_resena`),
  KEY `idusuario` (`idusuario`),
  KEY `idproducto` (`idproducto`),
  CONSTRAINT `resenas_producto_ibfk_1` FOREIGN KEY (`idusuario`) REFERENCES `usuario` (`idusuario`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `resenas_producto_ibfk_2` FOREIGN KEY (`idproducto`) REFERENCES `producto` (`idproducto`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `resenas_producto`
--

LOCK TABLES `resenas_producto` WRITE;
/*!40000 ALTER TABLE `resenas_producto` DISABLE KEYS */;
/*!40000 ALTER TABLE `resenas_producto` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usuario` (
  `idusuario` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) DEFAULT NULL,
  `correo` varchar(100) DEFAULT NULL,
  `contrasena` varchar(100) DEFAULT NULL,
  `tipousuario` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idusuario`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usuario`
--

LOCK TABLES `usuario` WRITE;
/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` VALUES (1,'a','aldo@asd','wqe','ADMIN'),(2,'a','aldo@asd','wqe','USER');
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-07-16  8:29:43
