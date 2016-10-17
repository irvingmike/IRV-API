CREATE DATABASE  IF NOT EXISTS `irv_project` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `irv_project`;
-- MySQL dump 10.13  Distrib 5.7.9, for osx10.9 (x86_64)
--
-- Host: localhost    Database: irv_project
-- ------------------------------------------------------
-- Server version	5.7.15

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
-- Table structure for table `Voters`
--

DROP TABLE IF EXISTS `Voters`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Voters` (
  `voterid` int(11) NOT NULL AUTO_INCREMENT,
  `firstname` varchar(45) DEFAULT NULL,
  `lastname` varchar(100) DEFAULT NULL,
  `email` varchar(254) NOT NULL,
  `securedby` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`voterid`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `index_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Voters`
--

LOCK TABLES `Voters` WRITE;
/*!40000 ALTER TABLE `Voters` DISABLE KEYS */;
INSERT INTO `Voters` VALUES (1,'One','TestVoter','fake1@fake.com',''),(2,'Two','TestVoter','fake2@fake.com','testpass'),(3,'Three','TestVoter','fake3@fake.com',NULL),(4,'Four','TestVoter','fake4@fake.com',NULL),(5,'Five','TestVoter','fake5@fake.com',NULL),(6,'Six','TestVoter','fake6@fake.com',NULL),(7,'Seven','TestVoter','fake7@fake.com',NULL),(8,'Eight','TestVoter','fake8@fake.com',NULL),(9,'Nine','TestVoter','fake9@fake.com',NULL),(10,'Ten','TestVoter','fake10@fake.com',NULL),(11,'Aaron','Anderson','irvingmichael@gmail.com','voterpass'),(12,'Admin','Istration','admin@fake.com','adminpass');
/*!40000 ALTER TABLE `Voters` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-10-17 18:03:58
