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
-- Table structure for table `admistrators`
--

DROP TABLE IF EXISTS `admistrators`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admistrators` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nama` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admistrators`
--

LOCK TABLES `admistrators` WRITE;
/*!40000 ALTER TABLE `admistrators` DISABLE KEYS */;
/*!40000 ALTER TABLE `admistrators` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `menus`
--

DROP TABLE IF EXISTS `menus`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `menus` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nama` varchar(45) NOT NULL,
  `harga` int(11) NOT NULL,
  `restorants_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_menus_restorants1_idx` (`restorants_id`),
  CONSTRAINT `fk_menus_restorants1` FOREIGN KEY (`restorants_id`) REFERENCES `restorants` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `menus`
--

LOCK TABLES `menus` WRITE;
/*!40000 ALTER TABLE `menus` DISABLE KEYS */;
/*!40000 ALTER TABLE `menus` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `pelanggans`
--

DROP TABLE IF EXISTS `pelanggans`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `pelanggans` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `alamat` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `pelanggans`
--

LOCK TABLES `pelanggans` WRITE;
/*!40000 ALTER TABLE `pelanggans` DISABLE KEYS */;
/*!40000 ALTER TABLE `pelanggans` ENABLE KEYS */;
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
  `jumlah` int(11) NOT NULL,
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
  `tanggal_booking` date NOT NULL,
  `jumlah_orang` int(11) NOT NULL,
  `jumlah_meja` int(11) NOT NULL,
  `pelanggans_id` int(11) NOT NULL,
  `restorants_id` int(11) NOT NULL,
  `total_harga` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_reservasis_pelanggans1_idx` (`pelanggans_id`),
  KEY `fk_reservasis_restorants1_idx` (`restorants_id`),
  CONSTRAINT `fk_reservasis_pelanggans1` FOREIGN KEY (`pelanggans_id`) REFERENCES `pelanggans` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_reservasis_restorants1` FOREIGN KEY (`restorants_id`) REFERENCES `restorants` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reservasis`
--

LOCK TABLES `reservasis` WRITE;
/*!40000 ALTER TABLE `reservasis` DISABLE KEYS */;
/*!40000 ALTER TABLE `reservasis` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `restorants`
--

DROP TABLE IF EXISTS `restorants`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `restorants` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pemilik` varchar(45) NOT NULL,
  `nama` varchar(45) NOT NULL,
  `jumlah_meja` int(11) NOT NULL,
  `preorder` tinyint(4) NOT NULL,
  `username` varchar(45) NOT NULL,
  `password` varchar(45) NOT NULL,
  `alamat` varchar(45) NOT NULL,
  `no_telepon` varchar(45) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `restorants`
--

LOCK TABLES `restorants` WRITE;
/*!40000 ALTER TABLE `restorants` DISABLE KEYS */;
/*!40000 ALTER TABLE `restorants` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-06-08 23:38:43
