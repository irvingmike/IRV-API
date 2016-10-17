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
-- Table structure for table `Polls`
--

DROP TABLE IF EXISTS `Polls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Polls` (
  `pollid` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(45) NOT NULL,
  `description` text,
  `pollcode` char(8) NOT NULL,
  `available` tinyint(1) NOT NULL DEFAULT '0',
  `votingopen` timestamp NULL DEFAULT NULL,
  `votingclosed` timestamp NULL DEFAULT NULL,
  `creator` int(11) DEFAULT NULL,
  PRIMARY KEY (`pollid`),
  KEY `fk_voterid_polls_idx` (`creator`),
  CONSTRAINT `fk_voterid_polls` FOREIGN KEY (`creator`) REFERENCES `Voters` (`voterid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Polls`
--

LOCK TABLES `Polls` WRITE;
/*!40000 ALTER TABLE `Polls` DISABLE KEYS */;
INSERT INTO `Polls` VALUES (1,'Test Poll','This is a test poll.','abcdefgh',1,'2016-10-03 22:50:58',NULL,1);
/*!40000 ALTER TABLE `Polls` ENABLE KEYS */;
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
