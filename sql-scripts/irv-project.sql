-- MySQL dump 10.13  Distrib 5.7.15, for osx10.11 (x86_64)
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
-- Table structure for table `AccessRoles`
--

DROP TABLE IF EXISTS `AccessRoles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AccessRoles` (
  `email` varchar(254) NOT NULL,
  `accessrole` varchar(45) NOT NULL DEFAULT 'voterStd',
  `accessroleid` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`accessroleid`),
  KEY `fk_accessroles_voteremail_idx` (`email`),
  CONSTRAINT `fk_accessroles_voteremail` FOREIGN KEY (`email`) REFERENCES `Voters` (`email`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AccessRoles`
--

LOCK TABLES `AccessRoles` WRITE;
/*!40000 ALTER TABLE `AccessRoles` DISABLE KEYS */;
INSERT INTO `AccessRoles` VALUES ('admin@fake.com','adminStd',1),('fake10@fake.com','voterStd',2),('fake1@fake.com','voterStd',3),('fake2@fake.com','voterStd',4),('fake3@fake.com','voterStd',5),('fake4@fake.com','voterStd',6),('fake5@fake.com','voterStd',7),('fake6@fake.com','voterStd',8),('fake7@fake.com','voterStd',9),('fake8@fake.com','voterStd',10),('fake9@fake.com','voterStd',11),('irvingmichael@gmail.com','voterStd',12),('fake2@fake.com','voterStd',13),('fake2@fake.com','voterStd',14),('fake2@fake.com','voterStd',15),('fake2@fake.com','voterStd',16);
/*!40000 ALTER TABLE `AccessRoles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `AuthTokens`
--

DROP TABLE IF EXISTS `AuthTokens`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `AuthTokens` (
  `Token` char(64) NOT NULL,
  `TokenId` int(11) NOT NULL AUTO_INCREMENT,
  `Created` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`TokenId`),
  UNIQUE KEY `AuthTokens_TokenId_uindex` (`TokenId`)
) ENGINE=InnoDB AUTO_INCREMENT=220 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `AuthTokens`
--

LOCK TABLES `AuthTokens` WRITE;
/*!40000 ALTER TABLE `AuthTokens` DISABLE KEYS */;
INSERT INTO `AuthTokens` VALUES ('0ZDFlSDcX95QkUq9hHv9EKwtXcRm83rwNpgqMAMQMxchkfRBro0s2XLVEkRKOUMN',196,'2016-11-03 17:44:03'),('djorkvLN07muSxq9Ns9Fupp0vSL0wsQYZKudNvF61O3HGXpgJ3WJJPHnYapcUMoz',197,'2016-11-03 17:44:03'),('GH7AsWtP78whKToB6I4gSgkp4F4JUeAqYm6YfqDsBP4e9DfU6FyQUjXMovPY0scJ',198,'2016-11-03 17:44:03'),('euyoxqqlEu4O2CSe6tg4aQb8SWPDMyeP1f1uO7Q1Kdfb26sHrrZnT2PQcDKBVRKr',199,'2016-11-03 17:47:55'),('pfOydLHKVIJFy6mCQ1iZkrdO3ZHKWp6IV9nf39TfF8YlxEJJ7W4CieBbqVP6MMLm',200,'2016-11-03 17:53:23'),('qk9RMLPaEHNyxUTdUrLxMGB5Httdl6ca9R8QNesRcZNBg9Rs6mO8l3qHYtsTuReh',201,'2016-11-03 17:53:23'),('IXcoI9Upl0eFPM3QCnqvNdSvQYTWxYBuX9N95zhhDvsqHyqSkPAnpKiZMgkv7TMk',202,'2016-11-03 17:53:23'),('TquiRmfGWMymemCc05V1EKPuNvZhXtULjhMxbtG4SUvpLSlKlnDoxiZwtQIgCDjY',203,'2016-11-03 17:53:23'),('9lnNXvTsi0TJIMTdTDh6CfcLyqN1VpdjYzcaqHjjMvnaRinNq2lujgcRbFD7Tx9O',204,'2016-11-03 17:54:16'),('Wbr1oijybe0ASBASWI4SYjJOi1EqAFmnNf7yTBbesLVmxepT4ftNVljeIRqfztfo',205,'2016-11-03 17:54:16'),('DrDU0HPJbLLkoLtzYwdpqL2y8xs9Q0G31tFxIQrndWkCE6i1PaUIgpRsX9kW4oJU',206,'2016-11-03 17:54:16'),('bKLw7HzydYAcVRJNdRuVtVkd97vHd451o72sVxG1eflHPXRptnO2tMIPYv8oNv5V',207,'2016-11-03 18:16:51'),('5e4D5xrznDkFNEmjMTg8NAkXGPJZH5gEHHhC6Nzi9lzIfdGw5TvjC8lxBCwG3nnc',208,'2016-11-03 18:07:11'),('ZWN5dFUb1v7OLSLSd1xBZVpQVTozWC65C6wZx22G1GzVRPaHWhnJZ9dqhGekfnHH',209,'2016-11-03 18:07:11'),('GyMdSZkkqd6WjTptMuH9RDguJeQGqElXSJWNiKby1yNiVAx0xw7Khfw7AYc58OGH',210,'2016-11-03 18:07:11'),('oIbz3GYZMJDBCApsa9C7Hv3HDowuoaa8QDk1DTlQ3z9zx2eyO5qWPfTr9gBWhZ1B',211,'2016-11-03 18:07:11'),('qHrcPzVrRVSpHk8OHpsaJMBt5VkQ7Pa45dw2PZswO4A7BrKQU1QlahvdvKcDqJ2h',212,'2016-11-03 18:12:23'),('0GedgbekY50hOf31mfo6qOyF2xKYsESpYempFzoxono9FfWInYeZCYPNTugJ6tKD',213,'2016-11-03 18:12:23'),('wTAqcEAJcRztRR7vegfQp22utlo6Cnb6up3Y1avmqSFZ6CO0xRIwOAu2Pu6uzn5b',214,'2016-11-03 18:12:23'),('7XNSl9yNHQuq7Q7fOIeSqdJZjyxAd8PkMKDp0zs87ScEonIbROuPxzbjukTujdaV',215,'2016-11-03 18:12:23'),('opokb9pZgNqKQMzNHisX0XA9k3RXFYD5qFoyDGK6KekPpxTggCQZmXO8xmsDCOES',216,'2016-11-03 18:16:23'),('kxuZP8ZpUb04TtmfXYMRwphDN7zvYOS5OECh8ocxk4GEhhsf3FAt0pbWlLWQplze',217,'2016-11-03 18:16:23'),('swlY1IluQxfJzYz6uKiIADk9wyiJXwVZtaY1b7AiaJaqLOI4notFqrDjl7DQ67UJ',218,'2016-11-03 18:16:23'),('LfwZF0fhCvG9SWrlIyQqMvvXr0XD9IuS7LIMgF8kNUEbqIbDJgHKJn5pKSqgc2m2',219,'2016-11-03 18:16:23');
/*!40000 ALTER TABLE `AuthTokens` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Choices`
--

DROP TABLE IF EXISTS `Choices`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Choices` (
  `choiceid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` text,
  `pollid` int(11) DEFAULT NULL,
  PRIMARY KEY (`choiceid`),
  KEY `fk_pollid_choices_idx` (`pollid`),
  CONSTRAINT `fk_pollid_choices` FOREIGN KEY (`pollid`) REFERENCES `Polls` (`pollid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=111 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Choices`
--

LOCK TABLES `Choices` WRITE;
/*!40000 ALTER TABLE `Choices` DISABLE KEYS */;
INSERT INTO `Choices` VALUES (1,'Choice A','This is choice A',1),(2,'Choice B','This is choice B',1),(3,'Choice C','This is choice C',1),(4,'Choice D','This is choice D',1),(80,'test api create poll choice 1 name','test api create poll choice 1 description',4),(81,'test api create poll choice 2 name','test api create poll choice 2 description',4),(82,'test api create poll choice 3 name','test api create poll choice 3 description',4),(83,'test api create poll choice 4 name','test api create poll choice 4 description',4);
/*!40000 ALTER TABLE `Choices` ENABLE KEYS */;
UNLOCK TABLES;

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
  `winner` int(11) DEFAULT '-1',
  `status` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pollid`),
  KEY `fk_voterid_polls_idx` (`creator`),
  CONSTRAINT `fk_voterid_polls` FOREIGN KEY (`creator`) REFERENCES `Voters` (`voterid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Polls`
--

LOCK TABLES `Polls` WRITE;
/*!40000 ALTER TABLE `Polls` DISABLE KEYS */;
INSERT INTO `Polls` VALUES (1,'Test Poll','This is a test poll.','abcdefgh',1,'2016-10-03 22:50:58',NULL,1,-1,1),(2,'test api create poll name','api create poll test description','BVGts8Hf',0,NULL,NULL,11,0,0),(3,'test api create poll name','api create poll test description','DRuuGLPW',0,NULL,NULL,11,0,0),(4,'test api create poll name','api create poll test description','ns8tfJB8',0,NULL,NULL,11,0,0);
/*!40000 ALTER TABLE `Polls` ENABLE KEYS */;
UNLOCK TABLES;

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
  `securedby` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`voterid`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  KEY `index_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Voters`
--

LOCK TABLES `Voters` WRITE;
/*!40000 ALTER TABLE `Voters` DISABLE KEYS */;
INSERT INTO `Voters` VALUES (1,'One','TestVoter','fake1@fake.com','1cf311c819be2d73ab4a3c8cb5327418c4f9e30cb17adb4c9330272fcc8e984c'),(2,'Two','TestVoter','fake2@fake.com','testpass'),(3,'Three','TestVoter','fake3@fake.com','voterpass'),(4,'Four','TestVoter','fake4@fake.com','voterpass'),(5,'Five','TestVoter','fake5@fake.com','voterpass'),(6,'Six','TestVoter','fake6@fake.com','voterpass'),(7,'Seven','TestVoter','fake7@fake.com','voterpass'),(8,'Eight','TestVoter','fake8@fake.com','voterpass'),(9,'Nine','TestVoter','fake9@fake.com','voterpass'),(10,'Ten','TestVoter','fake10@fake.com','voterpass'),(11,'Aaron','Anderson','irvingmichael@gmail.com','voterpass'),(12,'Admin','Istration','admin@fake.com','adminpass'),(13,'test','voter','fake11@fake.com',NULL),(18,'test','voter','fake12@fake.com',NULL),(23,'TestFirst','TestLast','testemail@fake.com',NULL);
/*!40000 ALTER TABLE `Voters` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `VotersPolls`
--

DROP TABLE IF EXISTS `VotersPolls`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `VotersPolls` (
  `voterid` int(11) NOT NULL,
  `pollid` int(11) NOT NULL,
  `notify` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`pollid`,`voterid`,`notify`),
  KEY `fk_voterid_voters_idx` (`voterid`),
  KEY `fk_pollid_polls_idx` (`pollid`),
  CONSTRAINT `fk_pollid_polls` FOREIGN KEY (`pollid`) REFERENCES `Polls` (`pollid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_voterid_voters` FOREIGN KEY (`voterid`) REFERENCES `Voters` (`voterid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `VotersPolls`
--

LOCK TABLES `VotersPolls` WRITE;
/*!40000 ALTER TABLE `VotersPolls` DISABLE KEYS */;
INSERT INTO `VotersPolls` VALUES (1,1,0),(2,1,0),(3,1,0),(4,1,0),(5,1,0),(6,1,0),(7,1,0),(8,1,0),(9,1,0),(10,1,0);
/*!40000 ALTER TABLE `VotersPolls` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `Votes`
--

DROP TABLE IF EXISTS `Votes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `Votes` (
  `voteid` int(11) NOT NULL AUTO_INCREMENT,
  `rank` int(11) NOT NULL,
  `votecast` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `choiceid` int(11) DEFAULT NULL,
  `pollid` int(11) DEFAULT NULL,
  `voterid` int(11) DEFAULT NULL,
  PRIMARY KEY (`voteid`),
  KEY `fk_choice-id_choices_idx` (`choiceid`),
  KEY `fk_poll-id_choices_idx` (`pollid`),
  KEY `fk_voter-id_choices_idx` (`voterid`),
  CONSTRAINT `fk_choiceid_votes` FOREIGN KEY (`choiceid`) REFERENCES `Choices` (`choiceid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_pollid_votes` FOREIGN KEY (`pollid`) REFERENCES `Polls` (`pollid`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_voterid_votes` FOREIGN KEY (`voterid`) REFERENCES `Voters` (`voterid`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `Votes`
--

LOCK TABLES `Votes` WRITE;
/*!40000 ALTER TABLE `Votes` DISABLE KEYS */;
INSERT INTO `Votes` VALUES (1,1,'2016-10-03 22:58:21',1,1,1),(2,2,'2016-10-03 22:58:21',2,1,1),(3,3,'2016-10-03 22:58:21',3,1,1),(4,4,'2016-10-03 22:58:21',4,1,1),(5,1,'2016-10-03 22:58:21',2,1,2),(6,2,'2016-10-03 22:58:21',1,1,2),(7,3,'2016-10-03 22:58:21',3,1,2),(8,4,'2016-10-03 22:58:21',4,1,2),(9,1,'2016-10-03 22:58:21',2,1,3),(10,2,'2016-10-03 22:58:21',1,1,3),(11,3,'2016-10-03 22:58:21',3,1,3),(12,4,'2016-10-03 22:58:21',4,1,3),(13,1,'2016-10-03 22:58:21',2,1,4),(14,2,'2016-10-03 22:58:21',1,1,4),(15,3,'2016-10-03 22:58:21',3,1,4),(16,4,'2016-10-03 22:58:21',4,1,4),(17,1,'2016-10-03 22:58:21',3,1,5),(18,2,'2016-10-03 22:58:21',4,1,5),(19,3,'2016-10-03 22:58:21',1,1,5),(20,4,'2016-10-03 22:58:21',2,1,5),(21,1,'2016-10-03 22:58:21',2,1,6),(22,2,'2016-10-03 22:58:21',3,1,6),(23,3,'2016-10-03 22:58:21',4,1,6),(24,4,'2016-10-03 22:58:21',1,1,6),(25,1,'2016-10-03 22:58:21',1,1,7),(26,2,'2016-10-03 22:58:21',2,1,7),(27,3,'2016-10-03 22:58:21',3,1,7),(28,4,'2016-10-03 22:58:21',4,1,7),(29,1,'2016-10-03 22:58:21',4,1,8),(30,2,'2016-10-03 22:58:21',1,1,8),(31,3,'2016-10-03 22:58:21',3,1,8),(32,4,'2016-10-03 22:58:21',2,1,8),(33,1,'2016-10-03 22:58:21',2,1,9),(34,2,'2016-10-03 22:58:21',3,1,9),(35,3,'2016-10-03 22:58:21',1,1,9),(36,4,'2016-10-03 22:58:21',4,1,9),(37,1,'2016-10-03 22:58:21',1,1,10),(38,2,'2016-10-03 22:58:21',2,1,10),(39,3,'2016-10-03 22:58:21',3,1,10),(40,4,'2016-10-03 22:58:21',4,1,10);
/*!40000 ALTER TABLE `Votes` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2016-11-03 14:04:49
