-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: projectdisprog
-- ------------------------------------------------------
-- Server version	5.5.5-10.4.24-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `administrators`
--

DROP TABLE IF EXISTS `administrators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `administrators` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `administrators`
--

LOCK TABLES `administrators` WRITE;
/*!40000 ALTER TABLE `administrators` DISABLE KEYS */;
INSERT INTO `administrators` VALUES (1,'jeremy123','jeremy123','jeremy'),(2,'alvin123','alvin123','alvin'),(3,'ikhsan12','ikhsan12','M. ikhsan'),(4,'fernando1234','fernando1234','fernandoss');
/*!40000 ALTER TABLE `administrators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `customers`
--

DROP TABLE IF EXISTS `customers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customers` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customers`
--

LOCK TABLES `customers` WRITE;
/*!40000 ALTER TABLE `customers` DISABLE KEYS */;
INSERT INTO `customers` VALUES (1,'alvin','alvin','alvin','sidodadi','alvin@gmail.com'),(2,'fernando','fernando','fernando','baru 12','fernando@gmai.com');
/*!40000 ALTER TABLE `customers` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `price` int(11) NOT NULL,
  `detail` varchar(245) NOT NULL,
  `restorants_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_menus_restorants1_idx` (`restorants_id`),
  CONSTRAINT `fk_menus_restorants1` FOREIGN KEY (`restorants_id`) REFERENCES `restaurants` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
INSERT INTO `menus` VALUES (2,'Bebek',12000,'Bisa ayam atau dada',1),(4,'Sate Ayam',20000,'Isi 10 tusuk',2),(7,'Ayam ',10000,'RECOMENDED',1),(8,'ngayam crispy',18000,'enak',4);
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `preorders`
--

DROP TABLE IF EXISTS `preorders`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `preorders` (
  `reservasis_id` int(11) NOT NULL,
  `menus_id` int(11) NOT NULL,
  `amount` int(11) NOT NULL,
  PRIMARY KEY (`reservasis_id`,`menus_id`),
  KEY `fk_reservasis_has_menus_menus1_idx` (`menus_id`),
  KEY `fk_reservasis_has_menus_reservasis1_idx` (`reservasis_id`),
  CONSTRAINT `fk_reservasis_has_menus_menus1` FOREIGN KEY (`menus_id`) REFERENCES `menus` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservasis_has_menus_reservasis1` FOREIGN KEY (`reservasis_id`) REFERENCES `reservasis` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `preorders`
--

LOCK TABLES `preorders` WRITE;
/*!40000 ALTER TABLE `preorders` DISABLE KEYS */;
INSERT INTO `preorders` VALUES (23,8,2),(24,8,5);
/*!40000 ALTER TABLE `preorders` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reservasis`
--

DROP TABLE IF EXISTS `reservasis`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reservasis` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `booking_date` date NOT NULL,
  `number_of_peoples` int(11) NOT NULL,
  `number_of_tables` int(11) NOT NULL,
  `restorants_id` int(11) NOT NULL,
  `customers_id` int(11) NOT NULL,
  `status` varchar(45) NOT NULL,
  `total_price` float NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservasis_restorants1_idx` (`restorants_id`),
  KEY `fk_reservasis_customers1_idx` (`customers_id`),
  CONSTRAINT `fk_reservasis_customers1` FOREIGN KEY (`customers_id`) REFERENCES `customers` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservasis_restorants1` FOREIGN KEY (`restorants_id`) REFERENCES `restaurants` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservasis`
--

LOCK TABLES `reservasis` WRITE;
/*!40000 ALTER TABLE `reservasis` DISABLE KEYS */;
INSERT INTO `reservasis` VALUES (1,'2022-06-24',16,4,1,1,'DONE',734400),(2,'2022-06-07',20,5,1,1,'DONE',918000),(3,'2022-06-30',20,5,1,1,'DONE',918000),(4,'2022-06-24',8,2,1,1,'DONE',367200),(5,'2022-06-23',2,1,1,1,'pending',142800),(6,'2022-06-24',2,1,1,2,'pending',21000),(7,'2022-06-23',2,1,1,2,'pending',21000),(8,'2022-06-17',2,1,1,1,'pending',142800),(9,'2022-06-23',2,1,1,1,'pending',142800),(10,'2022-06-16',2,1,1,1,'pending',142800),(11,'2022-06-23',2,1,1,1,'pending',142800),(15,'2022-06-25',4,1,1,2,'pending',27000),(16,'2022-06-27',10,3,1,2,'pending',75000),(20,'2022-06-25',5,2,4,1,'pending',300000),(22,'2022-06-30',5,2,4,2,'DONE',300000),(23,'2023-06-30',5,2,4,1,'DONE',336000),(24,'2022-06-25',8,2,4,1,'pending',450000);
/*!40000 ALTER TABLE `reservasis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restaurants`
--

DROP TABLE IF EXISTS `restaurants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restaurants` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `owner` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  `number_of_tables` int(11) NOT NULL,
  `preorder` tinyint(4) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `address` varchar(45) NOT NULL,
  `phone_number` varchar(45) NOT NULL,
  `price_reservation` float NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restaurants`
--

LOCK TABLES `restaurants` WRITE;
/*!40000 ALTER TABLE `restaurants` DISABLE KEYS */;
INSERT INTO `restaurants` VALUES (1,'Yantos','Yanto Restos',10,1,'yanto','yanto','jln yanto','122121123',102000),(2,'Bagas','Bagas Resto',2,0,'bagas','bagas','jalan bagas 111','132131231',15000),(4,'san','Mas Bro',88,1,'san','san','surabaya','0812',100000);
/*!40000 ALTER TABLE `restaurants` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-24 20:04:05
