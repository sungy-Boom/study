/*
Navicat MySQL Data Transfer

Source Server         : pad
Source Server Version : 50546
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2018-01-29 19:20:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for name_test
-- ----------------------------
DROP TABLE IF EXISTS `name_test`;
CREATE TABLE `name_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `big_num` bigint(20) DEFAULT NULL,
  `num` bigint(20) DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sub` int(11) DEFAULT NULL,
  `add_time` datetime NOT NULL,
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of name_test
-- ----------------------------
INSERT INTO `name_test` VALUES ('34', '123412312412', '123', '', '21', '2018-01-22 10:54:12', '2018-01-29 17:24:18');
INSERT INTO `name_test` VALUES ('35', '123123123', '123', 'ertwww', '2123', '2018-01-22 10:54:12', '2018-01-29 17:24:23');
INSERT INTO `name_test` VALUES ('36', '123123123', '123123', '2324', '34', '2018-01-12 17:47:41', '2018-01-29 17:24:25');
INSERT INTO `name_test` VALUES ('37', '1234567890123456789', '3', '这是一个测试', null, '2018-01-18 16:54:11', '2018-01-29 17:24:27');
