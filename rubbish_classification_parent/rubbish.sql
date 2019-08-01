/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.20-log : Database - rubbish
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`rubbish` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `rubbish`;

/*Table structure for table `feedback` */

DROP TABLE IF EXISTS `feedback`;

CREATE TABLE `feedback` (
  `rubbish_name` varchar(100) DEFAULT NULL COMMENT '垃圾名字',
  `rubbish_class` int(5) NOT NULL DEFAULT '0' COMMENT '垃圾类别',
  KEY `rubbish_name` (`rubbish_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Table structure for table `rubbish` */

DROP TABLE IF EXISTS `rubbish`;

CREATE TABLE `rubbish` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `rubbish_name` varchar(100) DEFAULT NULL COMMENT '垃圾名字',
  `rubbish_class` int(11) DEFAULT NULL COMMENT '垃圾类别id',
  PRIMARY KEY (`id`),
  KEY `rubbish_class` (`rubbish_class`),
  KEY `rubbish_name` (`rubbish_name`),
  CONSTRAINT `rubbish_ibfk_1` FOREIGN KEY (`rubbish_class`) REFERENCES `rubbish_class` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

/*Table structure for table `rubbish_class` */

DROP TABLE IF EXISTS `rubbish_class`;

CREATE TABLE `rubbish_class` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `rubbish_class` varchar(100) DEFAULT NULL COMMENT '垃圾类型',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
