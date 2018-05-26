/*
 Source Server         : fastsearch
 Source Server Type    : MySQL
 Source Server Version : 50634
 Source Host           : 127.0.0.1
 Source Database       : fast_search

 Target Server Type    : MySQL
 Target Server Version : 50634
 File Encoding         : utf-8

 Date: 3/17/2018 23:38:56 PM
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP DATABASE IF EXISTS `fast_search`;
CREATE DATABASE `fast_search`;
USE `fast_search`;

-- ----------------------------
--  Table structure for `car`
-- ----------------------------

#用户基本信息表
DROP TABLE IF EXISTS `sys_users`;
CREATE TABLE `sys_users`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '用户的主键',
    `NAME` VARCHAR(32) DEFAULT NULL COMMENT '用户名称',
    `EMAIL` VARCHAR(32) DEFAULT NULL COMMENT '用户邮箱',
    `PASSWORD` VARCHAR(64) DEFAULT NULL COMMENT '用户密码',
    `PHONE_NUMBER` VARCHAR(15) NOT NULL COMMENT '用户的手机号',
    `STATUS` VARCHAR(32) NOT NULL DEFAULT 'NORMAL' COMMENT '用户的状态:NORMAL-正常,BAN-封禁',
    `AVATAR` VARCHAR(255) DEFAULT NULL COMMENT '用户头像',
    `CREATE_TIME` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '用户创建时间',
    `LAST_LOGIN_TIME` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '最近登录时间',
    `LAST_UPDATE_TIME` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上次更新记录时间',
    PRIMARY KEY (`ID`),
	UNIQUE KEY `INDEX_ON_PHONE` (`PHONE_NUMBER`) USING BTREE COMMENT '用户手机号',
	UNIQUE KEY `INDEX_ON_USERNAME` (`NAME`) USING BTREE COMMENT '用户名索引',
	UNIQUE KEY `INDEX_ON_EMAIL` (`EMAIL`) USING BTREE COMMENT '电子邮箱索引'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户基本信息表';

BEGIN;
INSERT INTO `sys_users`
(`ID`, `NAME`, `EMAIL`, `PASSWORD`, `PHONE_NUMBER`, `STATUS`, `AVATAR`, `CREATE_TIME`, `LAST_LOGIN_TIME`, `LAST_UPDATE_TIME`)
VALUES 
('1', 'super', 'super@qq.com', '$2a$10$CO59hGPWBZR7Wu8ECDV7Eu9/4GQAYst7emdJbegL9gUmRAtvLZ7j.', '1536956231', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('2', 'business', 'business@qq.com', '$2a$10$WhILKBPtFF1WTPxNAQ3ASuRgcx6FGRKZ2Q57My5Zg.5qn03SLgNtq', '1536956232', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('3', 'customer', 'customer@qq.com', '$2a$10$CSDdmWTkEzeXBltOILvUQe9ctl35P5waPcP497dVYooCDLyJve12e', '1536956233', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('4', 'user', 'user@qq.com', '$2a$10$LxqJjh7OuWVjJUEnio2qu.Iepvtg1CCZDyqHee.cjZx9wCiPjS/ei', '1536956234', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('5', 'tom', 'tom@qq.com', '$2a$10$HmMgLf3gXLe3GTMSj.UKnud6S.z50KmeVdm5FJS3NBlsdp29rvWFe', '1536956235', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('6', 'berg', 'berg@qq.com', '$2a$10$7WzTtYe9vkriFWdpivi1kOWg3Pi4N6glz.VHJ8sEDs7clcXESKmXy', '1536956236', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('7', 'turing', 'turing@qq.com', '$2a$10$Upk/PluUhHywtxrP4uR7GOza6887lGQolVujSgT.VGX0ysur9sxIe', '1536956237', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('8', 'tony', 'tony@qq.com', '$2a$10$09vinnsycbq1fnJ.j7GZm.d2O/D.jerro/y57HziBG.gNgTktS35q', '1536956238', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('9', 'baby', 'baby@qq.com', '$2a$10$S.JpxwTZwuJ1jJvxHrIaQuI5fLALncwGutFbPJ6K7WPl8R6/SI/xK', '1536956239', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02');
COMMIT;


#角色表
DROP TABLE IF EXISTS `sys_roles`;
CREATE TABLE `sys_roles`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色的主键',
	`CODE` VARCHAR(32) NOT NULL COMMENT '角色编码',
    `NAME` VARCHAR(32) NOT NULL COMMENT '角色名',
    `DESCRIPTION` VARCHAR(255) DEFAULT NULL COMMENT '描述',
    PRIMARY KEY (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='用户角色表';

BEGIN;
INSERT INTO `sys_roles`
(`ID`, `CODE`, `NAME`, `DESCRIPTION`)
VALUES
('1', 'SUPER_ADMIN', '超级管理员', '超级管理员'),
('2', 'BUSINESS_ADMIN', '业务专员', '业务专员'),
('3', 'CUSTOMER_ADMIN', '客户专员', '客户专员'),
('4', 'USER', '用户', '用户');
COMMIT;

#角色分派表
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '角色分派的主键',
    `USER_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '用户id,用户表(sys_users)的主键',
    `ROLE_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '角色的id,角色表(sys_roles)的主键',
    PRIMARY KEY (`ID`),
    UNIQUE KEY `USER_ID_AND_ROLE_ID` (`USER_ID`,`ROLE_ID`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='角色分派表';

BEGIN;
INSERT INTO `sys_user_role`
(`ID`, `USER_ID`, `ROLE_ID`)
VALUES
('1', '1', '1'),
('2', '2', '2'),
('3', '3', '3'),
('4', '4', '4'),
('5', '5', '4'),
('6', '6', '4'),
('7', '7', '4'),
('8', '8', '4'),
('9', '9', '4');
COMMIT;


#代码维护头表
DROP TABLE IF EXISTS `sys_code_b`;
CREATE TABLE `sys_code_b`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '代码维护头的主键',
    `CODE` VARCHAR(32) NOT NULL COMMENT '代码维护的编码',
    `DESCRIPTION` VARCHAR(255) DEFAULT NULL COMMENT '代码维护的描述',
    `ENABLED_FLAG` VARCHAR(1) DEFAULT 'N' COMMENT '禁用标识:Y-禁用,N-启用',
    `CREATE_TIME` DATETIME NOT NULL COMMENT '数据创建时间',
	`LAST_UPDATE_TIME` DATETIME NOT NULL COMMENT '最近更新时间',
    PRIMARY KEY (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='代码维护头表';

BEGIN;
INSERT INTO `fast_search`.`sys_code_b`
(`ID`, `CODE`, `DESCRIPTION`, `ENABLED_FLAG`, `CREATE_TIME`, `LAST_UPDATE_TIME`)
VALUES
('1', 'TEST_CODE_1', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('2', 'TEST_CODE_2', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('3', 'TEST_CODE_3', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('4', 'TEST_CODE_4', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('5', 'TEST_CODE_5', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('6', 'TEST_CODE_6', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('7', 'TEST_CODE_7', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('8', 'TEST_CODE_8', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('9', 'TEST_CODE_9', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02');
COMMIT;

#代码维护头多语言表
DROP TABLE IF EXISTS `sys_code_tl`;
CREATE TABLE `sys_code_tl`(
	`ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '代码维护头多语言的主键',
    `LANG` VARCHAR(20) NOT NULL COMMENT '语言类型',
    `DESCRIPTION` VARCHAR(255) COMMENT '描述',
    `CREATE_TIME` DATETIME NOT NULL COMMENT '数据创建时间',
	`LAST_UPDATE_TIME` DATETIME NOT NULL COMMENT '最近更新时间',
    PRIMARY KEY (`ID`, `LANG`)
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4 COMMENT='代码维护头的多语言表';

#代码维护值表
DROP TABLE IF EXISTS `sys_code_value_b`;
CREATE TABLE `sys_code_value_b`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '代码维护值的主键',
    `CODE_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '代码维护头表的id,代码维护头表(sys_code_b)的主键',
    `VALUE` VARCHAR(150) NOT NULL COMMENT '代码的值',
    `MEANING` VARCHAR(150) DEFAULT NULL COMMENT '代码的含义',
    `DESCRIPTION` VARCHAR(255) DEFAULT NULL COMMENT '描述',
    `TAG` VARCHAR(255) DEFAULT NULL COMMENT '标识',
    `ENABLED_FLAG` VARCHAR(1) NOT NULL DEFAULT 'N' COMMENT '禁用标识:Y-禁用,N-启用',
    `CREATE_TIME` DATETIME NOT NULL COMMENT '数据创建时间',
	`LAST_UPDATE_TIME` DATETIME NOT NULL COMMENT '最近更新时间',
    PRIMARY KEY (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4 COMMENT='代码维护值表';


#代码维护值多语言表
DROP TABLE IF EXISTS `sys_code_value_tl`;
CREATE TABLE `sys_code_value_tl`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '代码维护值的主键',
    `LANG` VARCHAR(20) NOT NULL COMMENT '语言类型',
    `DESCRIPTION` VARCHAR(255) COMMENT '描述',
    `CREATE_TIME` DATETIME NOT NULL COMMENT '数据创建时间',
	`LAST_UPDATE_TIME` DATETIME NOT NULL COMMENT '最近更新时间',
    PRIMARY KEY (`ID`, `LANG`)
)ENGINE=InnoDB AUTO_INCREMENT=1 CHARSET=utf8mb4 COMMENT='代码维护值多语言表';


#汽车实体表
DROP TABLE IF EXISTS `fs_cars`;
CREATE TABLE `fs_cars`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '汽车实体的主键',
    `TITLE` VARCHAR(32) NOT NULL,
    `BRAND_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '车辆品牌的id,用户表(fs_car_brands)的主键',
    `SERIES_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '车辆系列的id,用户表(fs_car_series)的主键',
    `DEPLOYEE_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '发布者的id,用户表(sys_users)的主键',
    `PRICE` DECIMAL NOT NULL COMMENT '汽车的价格',
    `SEATS` INT(2) NOT NULL COMMENT '汽车的座位数',
    `DISPLACEMENT` DECIMAL NOT NULL COMMENT '汽车的排量',
    `MILEAGE` DECIMAL NOT NULL COMMENT '汽车的里程',	
    `AGE` INT(3) NOT NULL COMMENT '车龄',
    `GEAR_BOX` VARCHAR(150) NOT NULL COMMENT '汽车变速箱类型,代码维护code:FS.CAR_GEAR_BOXS',
    `COLOR` VARCHAR(150) NOT NULL COMMENT '汽车的颜色,代码维护code:FS.CAR_COLORS',
    `DRIVE_TYPE` VARCHAR(150) NOT NULL COMMENT '汽车的驱动类型,代码维护code:FS_CAR_DRIVE_TYPES',
    `EMISSION_STANDARD` VARCHAR(150) NOT NULL COMMENT '汽车的排放标准,代码维护code:FS_CAR_DMISSION_STANDARDS',
    `STYLE` VARCHAR(150) NOT NULL COMMENT '车型,代码维护code:FS_CAR_STYLES',
    `FUEL_TYPE` VARCHAR(150) NOT NULL COMMENT '燃油类型,代码维护code:FS_CAR_FUEL_TYPES',
    `WATCH_TIMES` BIGINT(20) unsigned DEFAULT '0' COMMENT '被看次数',
    `CITY_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '城市Id',
	`REGION_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '地区Id',
    `ADDRESS` VARCHAR(255) NOT NULL COMMENT '详细地址 ',
	`COVER` VARCHAR(255) DEFAULT NULL COMMENT '封面',
    `STATUS` VARCHAR(32) NOT NULL DEFAULT '' COMMENT '汽车的状态:',
    `DESCRIPTION` VARCHAR(512) DEFAULT NULL COMMENT '描述',
    `CREATE_TIME` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '汽车创建时间',
    `LAST_UPDATE_TIME` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '上次更新记录时间',
    PRIMARY KEY (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='汽车实体表';

BEGIN;
INSERT INTO `fast_search`.`fs_cars`
(`ID`, `TITLE`, `BRAND_ID`, `SERIES_ID`, `DEPLOYEE_ID`, `PRICE`, `SEATS`, `DISPLACEMENT`, `MILEAGE`, `AGE`, `GEAR_BOX`, `COLOR`, `DRIVE_TYPE`, `EMISSION_STANDARD`, `STYLE`, `FUEL_TYPE`, `WATCH_TIMES`, `CITY_ID`, `REGION_ID`, `ADDRESS`, `STATUS`, `COVER`, `DESCRIPTION`, `CREATE_TIME`, `LAST_UPDATE_TIME`)
VALUES
('1', '奥迪SQ5', '1', '1', '1', '126328', '4', '3.6', '25000', '12', 'HAND', 'RED', 'TWO_DRIVER', 'LEVEL_TWO', 'HATCHBACK', 'GASOLINE', '6134', '1', '6', '中国北京市东城区', 'UNAUDITED', 'http://p8vm8po1c.bkt.clouddn.com/SQ5.jpeg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('2', '奥迪S7', '1', '2', '1', '126328', '4', '5.5', '13000', '3', 'AUTO', 'BLUE', 'FOUR_DRIVER', 'LEVEL_FOUR', 'THREE', 'DIESEL_OIL', '534', '1', '7', '中国北京市西城区', 'UNAUDITED', 'http://p8vm8po1c.bkt.clouddn.com/S7.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('3', '奥迪S6', '1', '3', '1', '126328', '4', '8.6', '6000', '7', 'AUTO', 'WHITE', 'TWO_DRIVER', 'LEVEL_FIVE', 'SPORT', 'GASOLINE', '874', '1', '8', '中国北京市海淀区', 'UNAUDITED', 'http://p8vm8po1c.bkt.clouddn.com/S6.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('4', '奥迪S8', '1', '4', '1', '126328', '4', '4.6', '60000', '8', 'AUTO', 'GREE', 'TWO_DRIVER', 'LEVEL_TWO', 'SUV', 'DIESEL_OIL', '1984', '1', '9', '中国北京市昌平区', 'UNAUDITED', 'http://p8vm8po1c.bkt.clouddn.com/S8.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('5', '奥迪S5 COUPE', '1', '5', '1', '126328', '4', '3.6', '58695', '14', 'AUTO', 'BLACK', 'TWO_DRIVER', 'LEVEL_THREE', 'MPV', 'GASOLINE', '34', '1', '10', '中国北京市朝阳区', 'UNAUDITED', 'http://p8vm8po1c.bkt.clouddn.com/S5COUPE.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('6', '奥迪S5 四门轿跑车', '1', '6', '1', '126328', '4', '4.9', '15222', '1', 'HAND', 'BLACK', 'FOUR_DRIVER', 'LEVEL_FIVE', 'MICROBUS', 'DIESEL_OIL', '4', '2', '11', '中国上海市普陀区', 'UNAUDITED', 'http://p8vm8po1c.bkt.clouddn.com/S5四门轿跑车.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('7', '奥迪S5 敞篷', '1', '7', '1', '126328', '4', '5.5', '6897', '5', 'HAND', 'RED', 'TWO_DRIVER', 'LEVEL_THREE', 'PICKUP', 'GASOLINE', '454', '3', '12', '中国石家庄市桥东区', 'UNAUDITED', 'http://p8vm8po1c.bkt.clouddn.com/S5敞篷.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('8', '奥迪S4', '1', '8', '1', '126328', '4', '5.9', '9999', '6', 'AUTO', 'GRAY', 'TWO_DRIVER', 'LEVEL_TWO', 'SUV', 'GASOLINE', '14', '3', '13', '中国石家庄市桥西区', 'UNAUDITED', 'http://p8vm8po1c.bkt.clouddn.com/S4.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('9', '奥迪S3 四门轿车', '1', '9', '1', '126328', '4', '6.8', '84562', '7', 'AUTO', 'WHITE', 'TWO_DRIVER', 'LEVEL_THREE', 'SUV', 'DIESEL_OIL', '24', '3', '14', '中国石家庄市新华区', 'UNAUDITED', 'http://p8vm8po1c.bkt.clouddn.com/S3四门轿车.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02');
COMMIT;

#汽车品牌表
DROP TABLE IF EXISTS `fs_car_brands`;
CREATE TABLE `fs_car_brands`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '汽车品牌的id',
	`CODE` VARCHAR(32) NOT NULL COMMENT '汽车品牌的代码',
    `NAME` VARCHAR(255) NOT NULL COMMENT '汽车品牌的名称',
    PRIMARY KEY (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='汽车品牌表';

BEGIN;
INSERT INTO `fast_search`.`fs_car_brands`
(`ID`, `CODE`, `NAME`)
VALUES
('1', 'ad', '奥迪'),
('2', 'bm', '宝马'),
('3', 'bc', '奔驰'),
('4', 'dz', '大众');
COMMIT;


#汽车系类表
DROP TABLE IF EXISTS `fs_car_series`;
CREATE TABLE `fs_car_series`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '汽车系列的主键',
    `BRAND_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '汽车品牌id,汽车品牌表(fs_car_brands)的主键',
    `CODE` VARCHAR(32) NOT NULL COMMENT '汽车系列的代码',
    `NAME` VARCHAR(255) NOT NULL COMMENT '汽车系列的名称',
    PRIMARY KEY (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COMMENT='汽车系列表';

BEGIN;
INSERT INTO `fast_search`.`fs_car_series`
(`ID`, `BRAND_ID`, `CODE`, `NAME`)
VALUES
('1', '1', 'SQ5', 'SQ5'),
('2', '1', 'S7', 'S7'),
('3', '1', 'S6', 'S6'),
('4', '1', 'S8', 'S8'),
('5', '1', 'S5_COUPE', 'S5 COUPE'),
('6', '1', 'S5_SMJPC', 'S5 四门轿跑车'),
('7', '1', 'S5_CP', 'S5 敞篷'),
('8', '1', 'S4', 'S4'),
('9', '1', 'S3_SMJC', 'S3 四门轿车');
COMMIT;

#汽车图片表
DROP TABLE IF EXISTS `fs_car_pictures`;
CREATE TABLE `fs_car_pictures`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '汽车图片的主键',
    `CAR_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '汽车id,汽车表(fs_cars)的主键',
    `CDN_PREFIX` VARCHAR(255) NOT NULL COMMENT '图片路径',
    `WIDTH` INT(11) DEFAULT NULL COMMENT '图片的宽',
    `HEIGHT` INT(11) DEFAULT NULL COMMENT '图片的高',
    `LOCATION` VARCHAR(32) DEFAULT NULL COMMENT '图片属于车辆的什么部位',
    `PATH` VARCHAR(255) NOT NULL COMMENT '文件名',
    PRIMARY KEY (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='汽车图片表';

BEGIN;
INSERT INTO `fast_search`.`fs_car_pictures`
(`ID`, `CAR_ID`, `CDN_PREFIX`, `WIDTH`, `HEIGHT`, `LOCATION`, `PATH`)
VALUES
('1', '1', 'http://p8vm8po1c.bkt.clouddn.com/', '660', '440', '', 'SQ5.jpeg'),
('2', '2', 'http://p8vm8po1c.bkt.clouddn.com/', '220', '165', '', 'S7.jpg'),
('3', '3', 'http://p8vm8po1c.bkt.clouddn.com/', '200', '165', '', 'S6.jpg'),
('4', '4', 'http://p8vm8po1c.bkt.clouddn.com/', '220', '147', '', 'S8.jpg'),
('5', '5', 'http://p8vm8po1c.bkt.clouddn.com/', '220', '155', '', 'S5COUPE.jpg'),
('6', '6', 'http://p8vm8po1c.bkt.clouddn.com/', '220', '147', '', 'S5四门轿跑车.jpg'),
('7', '7', 'http://p8vm8po1c.bkt.clouddn.com/', '220', '142', '', 'S5敞篷.jpg'),
('8', '8', 'http://p8vm8po1c.bkt.clouddn.com/', '220', '141', '', 'S4.jpg'),
('9', '9', 'http://p8vm8po1c.bkt.clouddn.com/', '220', '156', '', 'S3四门轿车.jpg'),
('10', '1', 'http://p8vm8po1c.bkt.clouddn.com/', '300', '200', '', 'SQ5-1.jpeg');
COMMIT;


#汽车标签表
DROP TABLE IF EXISTS `fs_car_tags`;
CREATE TABLE `fs_car_tags`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '汽车标签的主键',
    `NAME` VARCHAR(32) NOT NULL COMMENT '汽车标签',
    PRIMARY KEY (`ID`)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='汽车标签表';

BEGIN;
INSERT INTO `fast_search`.`fs_car_tags`
(`ID`, `NAME`)
VALUES
('1', '速度快'),
('2', '造型帅');
COMMIT;


#汽车与汽车标签关联表
DROP TABLE IF EXISTS `fs_car_tag_ass`;
CREATE TABLE `fs_car_tag_ass`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '汽车标签与汽车关联的主键',
    `CAR_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '汽车id,汽车表(fs_cars)的主键',
    `CAR_TAG_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '汽车标签id,汽车标签表(fs_car_tags)的主键',
    PRIMARY KEY (`ID`),
    UNIQUE KEY `CAR_ID_AND_CAR_TAG_ID` (`CAR_ID`, `CAR_TAG_ID`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='汽车与汽车标签关联表';

BEGIN;
INSERT INTO `fast_search`.`fs_car_tag_ass`
(`ID`, `CAR_ID`, `CAR_TAG_ID`)
VALUES
('1', '1', '1'),
('2', '1', '2'),
('3', '2', '1'),
('4', '2', '2'),
('5', '3', '1'),
('6', '4', '2'),
('7', '5', '1'),
('8', '6', '2'),
('9', '7', '1'),
('10', '8', '2'),
('11', '9', '1');
COMMIT;

#车辆预约表
DROP TABLE IF EXISTS `fs_car_subscribes`;
CREATE TABLE `fs_car_subscribes`(
	`ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '汽车预约表的主键',
    `CAR_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '汽车id,汽车表(fs_cars)的主键',
    `USER_ID` BIGINT(20) UNSIGNED NOT NULL COMMENT '用户id,用户表(sys_users)的主键',
    `STATUS` VARCHAR(32) NOT NULL DEFAULT 'FOLLOWED' COMMENT '预约状态:FOLLOWED-已关注,APPOINTED-已约定,FINISHED-已完成',
    `ORDER_TIME` DATETIME DEFAULT NULL COMMENT '预约看车的时间',
    `PHONE_NUMBER` VARCHAR(15) DEFAULT NULL COMMENT '联系电话',
    `DESCRIPTION` VARCHAR(512) DEFAULT NULL COMMENT '用户的描述',
    `CREATE_TIME` DATETIME NOT NULL COMMENT '数据创建时间',
	`LAST_UPDATE_TIME` DATETIME NOT NULL COMMENT '记录更新时间',
    PRIMARY KEY (`ID`),
    UNIQUE KEY `CAR_ID_AND_USER_ID` (`CAR_ID`, `USER_ID`) USING BTREE
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT='车辆预约表';


#城市表
DROP TABLE IF EXISTS `fs_support_address`;
CREATE TABLE `fs_support_address` (
  `ID` BIGINT(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '城市的主键',
  `BELONG_TO` BIGINT(20) UNSIGNED NOT NULL DEFAULT '0' COMMENT '上一级行政ID',
  `EN_NAME` VARCHAR(32) NOT NULL COMMENT '行政单位英文名缩写',
  `CN_NAME` VARCHAR(32) NOT NULL COMMENT '行政单位中文名',
  `LEVEL` VARCHAR(16) NOT NULL COMMENT '行政级别 市-CITY 地区-REGION',
  `BAIDU_MAP_LNG` DECIMAL(30, 20) NOT NULL COMMENT '百度地图经度',
  `BAIDU_MAP_LAT` DECIMAL(30, 20) NOT NULL COMMENT '百度地图纬度',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `UNIQUE_EN_NAME_AND_BELONG_TO` (`EN_NAME`,`LEVEL`,`BELONG_TO`) USING BTREE COMMENT '每个城市的英文名都是独一无二的'
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COMMENT '城市表';

BEGIN;
INSERT INTO `fs_support_address`
(`ID`, `BELONG_TO`, `EN_NAME`, `CN_NAME`, `LEVEL`, `BAIDU_MAP_LNG`, `BAIDU_MAP_LAT`)
VALUES
('1', '1', 'bj', '北京', 'city', '116.395645', '39.929986'),
('2', '2', 'sh', '上海', 'city', '121.487899', '31.249162'),
('3', '3', 'sjz', '石家庄', 'city', '114.522082', '38.048958'),
('4', '4', 'ts', '唐山', 'city', '118.183451', '39.650531'),
('5', '5', 'hd', '邯郸', 'city', '114.482694', '36.609308'),
('6', '1', 'dcq', '东城区', 'region', '116.42188470126446', '39.93857401298612'),
('7', '1', 'xcq', '西城区', 'region', '116.37319010401802', '39.93428014370851'),
('8', '1', 'hdq', '海淀区', 'region', '116.23967780102151', '40.03316204507791'),
('9', '1', 'cpq', '昌平区', 'region', '116.21645635689414', '40.2217235498323'),
('10', '1', 'cyq', '朝阳区', 'region', '116.52169489108084', '39.95895316640668'),
('11', '2', 'ptq', '普陀区', 'region', '121.39844294374956', '31.263742929075534'),
('12', '3', 'caq', '长安区', 'region', '114.59262155387033', '38.07687479578663'),
('13', '3', 'qdq', '桥东区', 'region', '114.51078430496142', '38.06338975380927'),
('14', '3', 'qxq', '桥西区', 'region', '114.43813995531943', '38.033364550068136'),
('15', '3', 'xhq', '新华区', 'region', '114.4535014286928', '38.117218640478164');
COMMIT;




#用于记住我功能的表
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;






