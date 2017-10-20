CREATE DATABASE  IF NOT EXISTS `mybdd` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mybdd`;
-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mybdd
-- ------------------------------------------------------
-- Server version	5.7.18-log

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nom` varchar(20) NOT NULL,
  `prenom` varchar(20) DEFAULT NULL,
  `adresse` varchar(200) NOT NULL,
  `telephone` varchar(10) NOT NULL,
  `email` varchar(60) DEFAULT NULL,
  `image` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
INSERT INTO `client` VALUES (3,'Abdoul','beatz','198 route de ll','01020304','aaaa@gmail.com','Lighthouse.jpg'),(4,'aaaaa','aaaaaaaaaaa','123aaaaaaaaaaaaaa','00000000','lezybeatz@gmail.com',''),(5,'eeeeeee','eeeeeeee','eeeeeeeeeeeee','123456','aaa@gmai.com',''),(6,'yoooo','aaaaaaaaa','kelzlzle4444444','01123334','aa@ff.fr',''),(7,'adddddddddd','aaaaaaaa','dddddddddddddddd','8987979','dd@ff.fr','');
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `commande` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_client` int(11) DEFAULT NULL,
  `date` datetime NOT NULL,
  `montant` decimal(11,0) NOT NULL,
  `mode_paiement` varchar(20) NOT NULL,
  `statut_paiement` varchar(20) DEFAULT NULL,
  `mode_livraison` varchar(20) NOT NULL,
  `statut_livraison` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_id_client` (`id_client`),
  CONSTRAINT `fk_id_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande`
--

LOCK TABLES `commande` WRITE;
/*!40000 ALTER TABLE `commande` DISABLE KEYS */;
/*!40000 ALTER TABLE `commande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilisateur`
--

DROP TABLE IF EXISTS `utilisateur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `utilisateur` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(45) DEFAULT NULL,
  `mot_de_passe` char(200) NOT NULL,
  `nom` varchar(45) NOT NULL,
  `date_inscription` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nom_UNIQUE` (`nom`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilisateur`
--

LOCK TABLES `utilisateur` WRITE;
/*!40000 ALTER TABLE `utilisateur` DISABLE KEYS */;
INSERT INTO `utilisateur` VALUES (1,'yo@gmail.com','damn','Dool',NULL),(2,'yoyo@gmail.com','damn','Doolsy',NULL),(18,'raymond@anpe.fr','efb5d722dc8283932a43e0ec71e93caf','Raymond','2017-06-21 16:25:10'),(21,'charles@lamer.fr','3ca1313820d5ba507202c91a65910e22','Charles\'','2017-06-21 16:26:14'),(22,'charlie@lamer.fr','7ebdfcfbd743077faff9b7b9a7bc3a7c','Charlie','2017-06-21 16:34:05'),(24,'charliedelta@lamer.fr','7ebdfcfbd743077faff9b7b9a7bc3a7c','Charliedelta','2017-06-21 16:34:45'),(25,'test@test.fr','x2KxFxCkRPdMCOxZa7MI6DFMTiUERf2Cj6Ec3+ze1MqAAabyl7SV/w==','aaaa','2017-06-22 15:36:52'),(26,'test@test.frrrr','JvYfLgiwtuh7aHDxi5xD+sDeDSNSryf7q/FPNBLwuBSFOaMqhwWlWw==','aaaaaaaaaaaa','2017-06-29 11:21:47'),(27,'aaaa@bbb.frrr','kqVf+sr1P7TgUXaRxtvtkXUazkRd/c8yUBRytwbP0DpT/Hl/k/bkxA==','aaaaaaaaaaaaa',NULL),(28,'test@test.frrr','blFjbe/f9eaRhVq1UXSPo8dhBdz93ed6YB+c9COkN7gxT7M8ewZzlw==','aaaabbb',NULL),(29,'doolsy@gmail.com','$2a$10$uIO53AslAN5uVFRq7VMntO2ih3N.9A.h92EGRyHzG4LCZH/NDKrGy','Doulsy','1995-03-15 01:00:00'),(32,'dsky@gmail.com','$2a$10$J1BN/xSeCWPoybt6rNNL8.uEx7ZYTA.VL35HQ6AS13vS9DLYtGYTq','Doulsysky','2017-10-20 15:59:49'),(33,'dskonty@gmail.com','$2a$10$0r49QrCWGP9z/UyzNq/40e3Qk7aBZkmQrRY9VKLnvP9XiaOu64PDG','sysky','2017-10-20 16:03:43'),(34,'dskontyy@gmail.com','$2a$10$Z960JPwEW2slo4ih.y0vWufhs5FVpH4LnOufYERHVMb5rugeXhvAO','syskyy','2017-10-20 16:07:48'),(35,'dskontyy@gmail.comr','$2a$10$aXlR.SRbtohLxPyI1W1aO.twAooVIz/I.zlBNkzG2oldE1/1bKBfq','syskyyr','2017-10-20 16:14:29');
/*!40000 ALTER TABLE `utilisateur` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-10-20 17:22:11
