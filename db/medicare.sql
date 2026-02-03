-- MySQL dump 10.13  Distrib 9.5.0, for macos26.1 (arm64)
--
-- Host: localhost    Database: medicare
-- ------------------------------------------------------
-- Server version	9.5.0

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `appointment_details`
--

DROP TABLE IF EXISTS `appointment_details`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appointment_details` (
  `appointmentId` bigint NOT NULL AUTO_INCREMENT,
  `appointment_date` datetime(6) DEFAULT NULL,
  `appointment_location` varchar(255) DEFAULT NULL,
  `patientId` bigint DEFAULT NULL,
  PRIMARY KEY (`appointmentId`),
  KEY `fk_appointment_patient` (`patientId`),
  CONSTRAINT `fk_appointment_patient` FOREIGN KEY (`patientId`) REFERENCES `patients` (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appointment_details`
--

LOCK TABLES `appointment_details` WRITE;
/*!40000 ALTER TABLE `appointment_details` DISABLE KEYS */;
INSERT INTO `appointment_details` VALUES (11,'2024-01-05 00:00:00.000000','chicago',6),(12,'2024-08-05 00:00:00.000000','NC',6),(13,'2024-01-01 00:00:00.000000','Chicago',2),(14,'2024-01-01 00:00:00.000000','Chicago',5),(19,NULL,NULL,24),(20,'2024-03-05 00:00:00.000000','New York',25),(22,'2024-03-05 00:00:00.000000','New York',26),(23,'2024-03-05 00:00:00.000000','New York',1),(24,'2024-03-05 00:00:00.000000','New York',1),(25,'2024-01-01 00:00:00.000000','Chicago',6),(26,'2024-01-01 00:00:00.000000','Chicago',6),(27,'2024-01-01 00:00:00.000000','Chicago',6),(28,'2025-12-12 00:00:00.000000','NC',3),(29,'2025-12-12 00:00:00.000000','NC',3),(30,'2026-01-25 00:00:00.000000','New York,NC',3),(31,'2026-01-25 00:00:00.000000','New York,NC',3),(32,'2026-01-25 00:00:00.000000','New York,NC',3),(33,'2024-03-05 00:00:00.000000','NC',1),(34,'2024-08-05 00:00:00.000000','NC',1),(35,'2024-08-05 00:00:00.000000','NC',1),(36,'2024-03-05 00:00:00.000000','NC',1),(37,'2024-08-05 00:00:00.000000','NC',1),(38,'2024-08-05 00:00:00.000000','NC',1),(39,'2024-03-05 00:00:00.000000','NC',1),(40,'2024-03-05 00:00:00.000000','NC',1),(41,'2024-08-05 00:00:00.000000','NC',1),(42,'2024-08-05 00:00:00.000000','NC',1);
/*!40000 ALTER TABLE `appointment_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctor_appointments`
--

DROP TABLE IF EXISTS `doctor_appointments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctor_appointments` (
  `doctorId` bigint NOT NULL,
  `appointmentId` bigint NOT NULL,
  PRIMARY KEY (`doctorId`,`appointmentId`),
  KEY `fk_da_appointment` (`appointmentId`),
  CONSTRAINT `fk_da_appointment` FOREIGN KEY (`appointmentId`) REFERENCES `appointment_details` (`appointmentId`),
  CONSTRAINT `fk_da_doctor` FOREIGN KEY (`doctorId`) REFERENCES `doctors` (`doctorId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctor_appointments`
--

LOCK TABLES `doctor_appointments` WRITE;
/*!40000 ALTER TABLE `doctor_appointments` DISABLE KEYS */;
INSERT INTO `doctor_appointments` VALUES (1,11),(6,11),(6,12),(11,19),(12,20),(13,22),(1,23),(1,24),(2,25),(2,26),(2,27),(3,28),(3,29),(3,30),(3,31),(3,32),(5,33),(3,34),(3,35),(5,36),(3,37),(3,38),(5,39),(5,40),(3,41),(3,42);
/*!40000 ALTER TABLE `doctor_appointments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `doctors`
--

DROP TABLE IF EXISTS `doctors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `doctors` (
  `doctorId` bigint NOT NULL AUTO_INCREMENT,
  `doctorName` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `location` varchar(255) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `fee` int DEFAULT NULL,
  PRIMARY KEY (`doctorId`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `doctors`
--

LOCK TABLES `doctors` WRITE;
/*!40000 ALTER TABLE `doctors` DISABLE KEYS */;
INSERT INTO `doctors` VALUES (1,'dr_smith','Cardiologist','New York','2024-03-05',300),(2,'dr_jones','Dermatologist','Chicago','2024-01-01',500),(3,'arsha','gynacologist','NC','2024-08-05',400),(4,'praji','Dermatologist','Chicago','2024-01-05',400),(5,'arsha','gynacologist','NC','2024-03-05',300),(6,'praji','Dermatologist','Chicago','2024-01-05',400),(7,'arsha','Dermatologist','NC','2024-03-05',300),(8,'praji','Dermatologist','Chicago','2024-08-05',400),(9,'Dr. John Smith','Cardiologist','New York','2024-01-01',500),(11,'Dr. Smith','Cardiologist','New York','2024-03-05',300),(12,'Dr. Smith','Cardiologist','New York','2024-03-05',300),(13,'Dr. Smith','Cardiologist','New York','2024-03-05',300),(14,'Dr. Smith','Cardiologist','New York','2024-01-01',200);
/*!40000 ALTER TABLE `doctors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `patients`
--

DROP TABLE IF EXISTS `patients`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `patients` (
  `patient_id` bigint NOT NULL AUTO_INCREMENT,
  `patientUsername` varchar(255) DEFAULT NULL,
  `patientPassword` varchar(255) DEFAULT NULL,
  `created_by` varchar(100) DEFAULT NULL,
  `created_at` date DEFAULT NULL,
  `issued_by` varchar(100) DEFAULT NULL,
  `issued_at` date DEFAULT NULL,
  PRIMARY KEY (`patient_id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patients`
--

LOCK TABLES `patients` WRITE;
/*!40000 ALTER TABLE `patients` DISABLE KEYS */;
INSERT INTO `patients` VALUES (1,'john_doe','jdpass','admin','2025-12-12','system','2025-12-12'),(2,'jane_doe','janepass','admin','2025-12-12','system','2025-12-12'),(3,'vaiga','vpass','admin','2025-12-12','system','2025-12-12'),(4,'dhaksha','dpass','admin','2025-12-12','system','2025-12-12'),(5,'vaiga','vpass','admin','2025-12-12','system','2025-12-12'),(6,'dhaksha','dpass','admin','2025-12-12','system','2025-12-12'),(7,'vaiga','vpass','admin','2025-11-12','system','2025-12-12'),(8,'dhaksha','dpass','admin','2025-09-12','system','2025-12-12'),(9,NULL,'dhak123','admin','2025-12-12','system','2025-12-12'),(10,NULL,'dhak123','admin','2025-12-12','system','2025-12-12'),(11,NULL,'dhak123','admin','2025-12-12','system','2025-12-12'),(12,NULL,'dhak123','admin','2025-12-12','system','2025-12-12'),(13,NULL,'dhak123','admin','2025-12-11','system','2025-12-11'),(14,NULL,'dhak123','admin','2025-12-11','system','2025-12-11'),(15,NULL,'dhak123','admin','2025-12-11','system','2025-12-11'),(16,NULL,'dhak123','admin','2025-12-11','system','2025-12-11'),(17,NULL,'dhak123','admin','2025-12-11','system','2025-12-11'),(18,NULL,'dhak123','admin','2025-12-11','system','2025-12-11'),(19,'dhak','dhak123','admin','2025-12-11','system','2025-12-11'),(20,'dhak',NULL,'admin','2025-12-11','system','2025-12-11'),(21,'dhak',NULL,'admin','2025-12-11','system','2025-12-11'),(22,'dhak',NULL,'admin','2025-12-11','system','2025-12-11'),(23,'dhak','dhak123','admin','2025-12-11','system','2025-12-11'),(24,'john_doe',NULL,NULL,NULL,NULL,NULL),(25,'john_doe',NULL,NULL,NULL,NULL,NULL),(26,'john_doe',NULL,NULL,NULL,NULL,NULL);
/*!40000 ALTER TABLE `patients` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2026-01-30 15:58:55
