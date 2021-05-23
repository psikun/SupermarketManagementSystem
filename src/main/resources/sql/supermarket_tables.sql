-- MySQL dump 10.13  Distrib 8.0.22, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: supermarket
-- ------------------------------------------------------
-- Server version	8.0.22

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
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `brand`
(
    `brand_id`    int         NOT NULL AUTO_INCREMENT,
    `brand_name`  varchar(30) NOT NULL,
    `add_time`    timestamp   NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `update_time` timestamp   NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted`     tinyint     NOT NULL DEFAULT '0',
    PRIMARY KEY (`brand_id`),
    UNIQUE KEY `brand_id` (`brand_id`),
    UNIQUE KEY `brand_name` (`brand_name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 6
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand`
    DISABLE KEYS */;
INSERT INTO `brand`
VALUES (1, '小米', '2021-05-18 08:08:40', '2021-05-18 08:08:40', 0),
       (2, '华为', '2021-05-18 08:08:44', '2021-05-18 08:08:44', 0),
       (5, 'oppo', '2021-05-21 03:38:08', '2021-05-21 03:38:08', 0);
/*!40000 ALTER TABLE `brand`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `category`
--

DROP TABLE IF EXISTS `category`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `category`
(
    `category_id`   int         NOT NULL AUTO_INCREMENT,
    `category_name` varchar(30) NOT NULL,
    `add_time`      timestamp   NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `update_time`   timestamp   NULL     DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
    `deleted`       tinyint     NOT NULL DEFAULT '0',
    PRIMARY KEY (`category_id`),
    UNIQUE KEY `category_id` (`category_id`),
    UNIQUE KEY `category_name` (`category_name`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 5
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category`
    DISABLE KEYS */;
INSERT INTO `category`
VALUES (1, '手机', '2021-05-18 08:08:33', '2021-05-18 08:08:33', 0);
/*!40000 ALTER TABLE `category`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods` (
                         `id`          int           NOT NULL AUTO_INCREMENT,
                         `name`        varchar(128)  NOT NULL,
                         `price`       decimal(16,2) NOT NULL,
                         `c_id`        int           NOT NULL,
                         `b_id`        int           NOT NULL,
                         `sales`       int           NOT NULL DEFAULT '0',
                         `inventory`   int           NOT NULL DEFAULT '0',
                         `is_on_sale`  smallint      NOT NULL DEFAULT '1',
                         `add_time`    timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP,
                         `update_time` timestamp     NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
                         `remarks`     varchar(200)           DEFAULT NULL,
                         `deleted`     smallint      NOT NULL DEFAULT '0',
                         PRIMARY KEY (`id`),
                         UNIQUE KEY `id` (`id`),
                         UNIQUE KEY `name` (`name`),
                         KEY `c_id` (`c_id`),
                         KEY `b_id` (`b_id`),
                         CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `category` (`category_id`),
                         CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`b_id`) REFERENCES `brand` (`brand_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods`
    DISABLE KEYS */;
INSERT INTO `goods`
VALUES (1, '小米11', 3999.00, 1, 1, 0, 30, 1, '2021-05-18 08:08:55', '2021-05-23 01:44:49', '我试试能不能插入', 0),
       (2, '华为mate50', 6999.00, 1, 2, 0, 60, 1, '2021-05-20 08:19:07', NULL, NULL, 0),
       (9, '红米k40pro', 6999.00, 1, 1, 20, 100, 1, '2021-05-23 01:33:57', NULL, '', 0);
/*!40000 ALTER TABLE `goods`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user`
(
    `id`       int         NOT NULL AUTO_INCREMENT,
    `username` varchar(30) NOT NULL,
    `password` varchar(30) NOT NULL,
    `is_admin` tinyint DEFAULT '0',
    `deleted`  tinyint DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`),
    UNIQUE KEY `username` (`username`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 10
  DEFAULT CHARSET = utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user`
    DISABLE KEYS */;
INSERT INTO `user`
VALUES (1, 'admin', 'admin', 1, 0),
       (5, 'test', 'test', 0, 0),
       (6, 'root', 'root', 0, 0),
       (9, 'mysql', 'mysql', 0, 0);
/*!40000 ALTER TABLE `user`
    ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-23 15:13:32
