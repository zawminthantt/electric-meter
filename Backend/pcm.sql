/*
 Navicat Premium Data Transfer

 Source Server         : CA_Backend_Server
 Source Server Type    : MySQL
 Source Server Version : 50545
 Source Host           : ap-cdbr-azure-southeast-b.cloudapp.net
 Source Database       : pcm

 Target Server Type    : MySQL
 Target Server Version : 50545
 File Encoding         : utf-8

 Date: 06/17/2016 23:55:56 PM
*/

SET NAMES utf8;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
--  Table structure for `location`
-- ----------------------------
DROP TABLE IF EXISTS `location`;
CREATE TABLE `location` (
  `location_id` int(11) NOT NULL AUTO_INCREMENT,
  `zip_code` varchar(255) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`location_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `meter`
-- ----------------------------
DROP TABLE IF EXISTS `meter`;
CREATE TABLE `meter` (
  `meter_id` int(11) NOT NULL,
  `location_id` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  KEY `meter_id` (`meter_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `meter_ibfk_1` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `meter_data`
-- ----------------------------
DROP TABLE IF EXISTS `meter_data`;
CREATE TABLE `meter_data` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `meter_id` int(11) DEFAULT NULL,
  `location_id` int(11) DEFAULT NULL,
  `mpower` varchar(255) DEFAULT NULL,
  `mdatetime` varchar(255) DEFAULT NULL,
  `current` varchar(255) DEFAULT NULL,
  `frequency` varchar(255) DEFAULT NULL,
  `voltage` varchar(255) DEFAULT NULL,
  `breaker_state` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `meter_id` (`meter_id`),
  KEY `location_id` (`location_id`),
  CONSTRAINT `meter_data_ibfk_1` FOREIGN KEY (`meter_id`) REFERENCES `meter` (`meter_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `meter_data_ibfk_2` FOREIGN KEY (`location_id`) REFERENCES `location` (`location_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=1731 DEFAULT CHARSET=utf8;

