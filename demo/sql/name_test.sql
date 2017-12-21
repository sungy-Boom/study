SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `name_test`;
CREATE TABLE `name_test` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 DEFAULT NULL,
  `sub` varchar(255) CHARACTER SET utf8 DEFAULT '',
  `add_time` DATETIME not null,
  `update_time` timestamp not null default CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;