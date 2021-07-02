-- MySQL dump 10.13  Distrib 8.0.25, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: supermarket
-- ------------------------------------------------------
-- Server version	8.0.25

/*!40101 SET @OLD_CHARACTER_SET_CLIENT = @@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS = @@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION = @@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE = @@TIME_ZONE */;
/*!40103 SET TIME_ZONE = '+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS = @@UNIQUE_CHECKS, UNIQUE_CHECKS = 0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS = @@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS = 0 */;
/*!40101 SET @OLD_SQL_MODE = @@SQL_MODE, SQL_MODE = 'NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES = @@SQL_NOTES, SQL_NOTES = 0 */;

--
-- Table structure for table `brand`
--

DROP TABLE IF EXISTS `brand`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
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
  AUTO_INCREMENT = 27
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `brand`
--

LOCK TABLES `brand` WRITE;
/*!40000 ALTER TABLE `brand`
    DISABLE KEYS */;
INSERT INTO `brand`
VALUES (6, '小米', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (7, '华为', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (8, 'OPPO', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (9, '苹果', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (10, '耐克', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (11, '宏碁', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (12, '戴尔', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (13, '联想', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (14, '惠普', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (15, '三只松鼠', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (16, '卫龙', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (17, '米多奇', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (18, '旺仔', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (19, '森马', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (20, '特步', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (21, '鸿星尔克', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (22, '阿迪达斯', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (23, '百事', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (24, '青岛啤酒', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (25, '今麦郎', '2021-05-30 02:22:47', '2021-05-30 02:22:47', 0),
       (26, '舒肤佳', '2021-05-30 02:23:35', '2021-05-30 02:23:35', 0);
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
  AUTO_INCREMENT = 21
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `category`
--

LOCK TABLES `category` WRITE;
/*!40000 ALTER TABLE `category`
    DISABLE KEYS */;
INSERT INTO `category`
VALUES (14, '手机', '2021-05-30 02:15:51', '2021-05-30 02:15:51', 0),
       (15, '电脑', '2021-05-30 02:15:51', '2021-05-30 02:15:51', 0),
       (16, '零食', '2021-05-30 02:15:51', '2021-05-30 02:15:51', 0),
       (17, '服装', '2021-05-30 02:15:51', '2021-05-30 02:15:51', 0),
       (18, '洗浴', '2021-05-30 02:15:51', '2021-05-30 02:15:51', 0),
       (19, '酒水饮料', '2021-05-30 02:15:51', '2021-05-30 02:15:51', 0),
       (20, '鞋靴', '2021-05-30 02:17:59', '2021-05-30 02:17:59', 0);
/*!40000 ALTER TABLE `category`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `goods`
--

DROP TABLE IF EXISTS `goods`;
/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `goods`
(
    `id`          int            NOT NULL AUTO_INCREMENT,
    `name`        varchar(128)   NOT NULL,
    `price`       decimal(16, 2) NOT NULL,
    `c_id`        int            NOT NULL,
    `b_id`        int            NOT NULL,
    `sales`       int            NOT NULL DEFAULT '0',
    `inventory`   int            NOT NULL DEFAULT '0',
    `is_on_sale`  smallint       NOT NULL DEFAULT '1',
    `add_time`    timestamp      NOT NULL DEFAULT CURRENT_TIMESTAMP,
    `update_time` timestamp      NULL     DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
    `remarks`     varchar(200)            DEFAULT NULL,
    `deleted`     smallint       NOT NULL DEFAULT '0',
    PRIMARY KEY (`id`),
    UNIQUE KEY `id` (`id`),
    UNIQUE KEY `name` (`name`),
    KEY `c_id` (`c_id`),
    KEY `b_id` (`b_id`),
    CONSTRAINT `goods_ibfk_1` FOREIGN KEY (`c_id`) REFERENCES `category` (`category_id`),
    CONSTRAINT `goods_ibfk_2` FOREIGN KEY (`b_id`) REFERENCES `brand` (`brand_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 16
  DEFAULT CHARSET = utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `goods`
--

LOCK TABLES `goods` WRITE;
/*!40000 ALTER TABLE `goods`
    DISABLE KEYS */;
INSERT INTO `goods`
VALUES (10, '小米11pro', 4999.00, 14, 6, 0, 100, 1, '2021-05-30 02:25:33', '2021-05-30 02:28:12', NULL, 0),
       (11, '华为mate40pro', 6499.00, 14, 7, 0, 200, 1, '2021-05-30 02:26:48', '2021-05-30 02:28:13', NULL, 0),
       (12, '联想小新14pro', 4999.00, 15, 13, 0, 20, 1, '2021-05-30 02:28:11', '2021-05-30 02:28:19', NULL, 0),
       (13, '戴尔G7', 5999.00, 15, 12, 0, 10, 1, '2021-05-30 02:28:53', NULL, NULL, 0),
       (14, '坚果大礼包', 49.00, 16, 15, 0, 300, 1, '2021-05-30 02:29:49', NULL, NULL, 0),
       (15, '空军一号', 499.00, 20, 10, 0, 20, 1, '2021-05-30 02:30:22', NULL, NULL, 0);
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
  DEFAULT CHARSET = utf8mb3;
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
/*!40103 SET TIME_ZONE = @OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE = @OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS = @OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS = @OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT = @OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS = @OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION = @OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES = @OLD_SQL_NOTES */;

-- Dump completed on 2021-07-01 14:24:08
