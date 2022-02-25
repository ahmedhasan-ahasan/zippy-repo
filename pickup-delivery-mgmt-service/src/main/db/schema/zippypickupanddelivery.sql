CREATE DATABASE  IF NOT EXISTS `zippypickupanddeliverymgmtdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `zippypickupanddeliverymgmtdb`;
-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: 34.82.19.106    Database: zippypickupanddeliverymgmtdb
-- ------------------------------------------------------
-- Server version	8.0.27-0ubuntu0.21.10.1

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
-- Table structure for table `address`
--

DROP TABLE IF EXISTS `address`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `address` (
  `address_id` bigint NOT NULL AUTO_INCREMENT,
  `fullname` varchar(50) NOT NULL,
  `address_line1` varchar(100) NOT NULL,
  `address_line2` varchar(100) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `state` varchar(50) NOT NULL,
  `zip` int NOT NULL,
  `country` varchar(50) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_dt` datetime NOT NULL,
  `last_modified_by` varchar(50) NOT NULL,
  `last_modified_dt` datetime NOT NULL,
  PRIMARY KEY (`address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `package_types`
--

DROP TABLE IF EXISTS `package_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `package_types` (
  `package_type_id` bigint NOT NULL AUTO_INCREMENT,
  `package_type_nm` varchar(50) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `max_weight_allowed` double NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_dt` datetime NOT NULL,
  `last_modified_by` varchar(50) NOT NULL,
  `last_modified_dt` datetime NOT NULL,
  PRIMARY KEY (`package_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `pickup_delivery_request`
--

DROP TABLE IF EXISTS `pickup_delivery_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `pickup_delivery_request` (
  `pickup_delivery_request_id` bigint NOT NULL AUTO_INCREMENT,
  `customer_id` int NOT NULL,
  `receiver_nm` varchar(50) NOT NULL,
  `receiver_mobile_nbr` varchar(13) NOT NULL,
  `pickup_otp_code` varchar(10) NOT NULL,
  `delivery_otp_code` varchar(10) NOT NULL,	  
  `pickup_delivery_request_dt` datetime NOT NULL,
  `expected_delivery_dt` datetime NOT NULL,
  `pickup_address_id` bigint NOT NULL,
  `delivery_address_id` bigint NOT NULL,
  `parcel_weight` decimal(10,0) NOT NULL,
  `package_type_id` bigint NOT NULL,
  `customer_notes` varchar(255) DEFAULT NULL,
  `amount` double NOT NULL,
  `delivery_tracking_no` varchar(50) NOT NULL,
  `status` varchar(1) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_dt` datetime NOT NULL,
  `last_modified_by` varchar(50) NOT NULL,
  `last_modified_dt` datetime NOT NULL,
  PRIMARY KEY (`pickup_delivery_request_id`),
  KEY `address
_idx` (`pickup_address_id`,`delivery_address_id`),
  KEY `address
_idx1` (`delivery_address_id`),
  KEY `package_types_idx` (`package_type_id`),
  CONSTRAINT `address` FOREIGN KEY (`delivery_address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `address
` FOREIGN KEY (`pickup_address_id`) REFERENCES `address` (`address_id`),
  CONSTRAINT `package_types` FOREIGN KEY (`package_type_id`) REFERENCES `package_types` (`package_type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-11 16:54:48
