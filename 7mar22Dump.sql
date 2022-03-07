-- MySQL dump 10.13  Distrib 8.0.28, for Win64 (x86_64)
--
-- Host: localhost    Database: attendance
-- ------------------------------------------------------
-- Server version	8.0.28

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
-- Table structure for table `adminusers`
--

DROP TABLE IF EXISTS `adminusers`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `adminusers` (
  `username` varchar(64) NOT NULL,
  `password` varchar(64) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `adminusers`
--

INSERT INTO `adminusers` VALUES ('admin','adminpass');

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `staff_id` varchar(64) NOT NULL,
  `name` varchar(64) NOT NULL,
  `dept` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`staff_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

INSERT INTO `staff` VALUES ('123A','tom','HR'),('234B','tim','IT');

--
-- Table structure for table `timelog`
--

DROP TABLE IF EXISTS `timelog`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `timelog` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `staff_id` varchar(64) DEFAULT NULL,
  `Date` date DEFAULT NULL,
  `clock_in` varchar(64) DEFAULT 'Nil Entry',
  `clock_out` varchar(64) DEFAULT 'Nil Entry',
  `clock_in_loc` varchar(64) DEFAULT 'Nil Entry',
  `clock_out_loc` varchar(64) DEFAULT 'Nil Entry',
  PRIMARY KEY (`ID`),
  KEY `staff_id` (`staff_id`),
  CONSTRAINT `timelog_ibfk_1` FOREIGN KEY (`staff_id`) REFERENCES `staff` (`staff_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `timelog`
--

INSERT INTO `timelog` VALUES (1,'123A','2022-03-07','13:51:28','14:19:47','1.3167195 1.3167195','1.3167195 1.3167195'),(2,'123a','2022-03-07','13:51:44','14:19:47','1.3167195 1.3167195','1.3167195 1.3167195'),(3,'234B','2022-03-07','13:52:39','14:20:04','1.3167195 1.3167195','1.3167195 1.3167195'),(4,'111A','2022-03-07','14:01:37','14:02:00','1.3167195 1.3167195','1.3167195 1.3167195'),(5,'123a','2022-03-07','14:18:36','14:19:47','1.3167195 1.3167195','1.3167195 1.3167195'),(6,'123A','2022-01-15','09:00:00','17:00:00','1.111','1.111'),(7,'123A','2022-02-15','09:00:00','17:00:00','1.111','1.111'),(8,'123A','2021-12-15','09:00:00','17:00:00','1.111','1.111'),(9,'123A','2021-11-15','09:00:00','17:00:00','1.111','1.111');
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-03-07 15:10:56
