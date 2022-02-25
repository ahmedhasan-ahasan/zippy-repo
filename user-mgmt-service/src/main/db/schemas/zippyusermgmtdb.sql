CREATE DATABASE  IF NOT EXISTS `zippyusermgmtdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `zippyusermgmtdb`;
-- MySQL dump 10.13  Distrib 8.0.27, for Linux (x86_64)
--
-- Host: 34.82.19.106    Database: zippyusermgmtdb
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
  `system_user_id` bigint NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_dt` datetime NOT NULL,
  `last_modified_by` varchar(50) NOT NULL,
  `last_modified_dt` datetime NOT NULL,
  PRIMARY KEY (`address_id`),
    KEY `address
_idx` (`address_id`),
  CONSTRAINT `address` FOREIGN KEY (`system_user_id`) REFERENCES `system_user` (`system_user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `system_user`
--

DROP TABLE IF EXISTS `system_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `system_user` (
  `system_user_id` bigint NOT NULL AUTO_INCREMENT,
  `email_address` varchar(80) NOT NULL,
  `mobile_nbr` varchar(13) NOT NULL,
  `first_nm` varchar(50) DEFAULT NULL,
  `last_nm` varchar(50) DEFAULT NULL,
  `password` varchar(60) NOT NULL,
  `email_verification_otp_code` varchar(12) NOT NULL,
  `mobile_nbr_otp_code` varchar(6) NOT NULL,
  `email_verification_otp_code_generated_dt` datetime NOT NULL,
  `mobile_nbr_otp_code_generated_dt` datetime NOT NULL,
  `mobile_nbr_otp_code_verified_status` int,
  `email_verification_otp_code_verified_status` int,
  `user_role_id` bigint NOT NULL,
  `status` varchar(1) NOT NULL,
  `registered_dt` datetime NOT NULL,
  `activated_dt` datetime,
  `created_by` varchar(50) NOT NULL,
  `created_dt` datetime NOT NULL,
  `last_modified_by` varchar(50) NOT NULL,
  `last_modified_dt` datetime NOT NULL,
  PRIMARY KEY (`system_user_id`),
  UNIQUE KEY `email_address_UNIQUE` (`email_address`),
  UNIQUE KEY `mobile_nbr_UNIQUE` (`mobile_nbr`),
  KEY `user_role_idx` (`user_role_id`),
  CONSTRAINT `user_role` FOREIGN KEY (`user_role_id`) REFERENCES `user_role` (`user_role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_role_id` bigint NOT NULL AUTO_INCREMENT,
  `role_nm` varchar(50) NOT NULL,
  `description` varchar(255) NOT NULL,
  `status` varchar(1) NOT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_dt` datetime NOT NULL,
  `last_modified_by` varchar(50) NOT NULL,
  `last_modified_dt` datetime NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `role_nm_UNIQUE` (`role_nm`)
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

-- Dump completed on 2022-01-11 16:51:34
