/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.1.48-log : Database - qdm169076217_db
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`qdm169076217_db` /*!40100 DEFAULT CHARACTER SET gbk */;

USE `qdm169076217_db`;

/*Table structure for table `girl` */

DROP TABLE IF EXISTS `girl`;

CREATE TABLE `girl` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `age` int(11) DEFAULT NULL,
  `size_cup` varchar(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=gbk;

/*Data for the table `girl` */

insert  into `girl`(`id`,`age`,`size_cup`) values (16,12,NULL),(11,20,'A'),(10,20,'A'),(9,20,'A'),(13,20,'A'),(14,20,'A'),(15,200,'F'),(17,13,NULL),(18,15,'B'),(19,15,'B'),(20,151,'F'),(21,151,'F'),(22,22,'F');

/*Table structure for table `guser` */

DROP TABLE IF EXISTS `guser`;

CREATE TABLE `guser` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(128) NOT NULL,
  `password` varchar(128) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=gbk;

/*Data for the table `guser` */

/*Table structure for table `medicine` */

DROP TABLE IF EXISTS `medicine`;

CREATE TABLE `medicine` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `total` double NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=gbk;

/*Data for the table `medicine` */

insert  into `medicine`(`id`,`name`,`total`) values (1,'云南白药',123),(2,'金钱豹',500),(3,'枸杞',580),(4,'山药',190);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
