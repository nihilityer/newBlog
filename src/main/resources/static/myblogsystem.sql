/*
 Navicat Premium Data Transfer

 Source Server         : locahost
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : localhost:3306
 Source Schema         : myblogsystem

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 19/09/2021 22:51:58
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for blog
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog`  (
  `blog_id` int(0) NOT NULL AUTO_INCREMENT,
  `blog_title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creater` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `creater_id` int(0) NOT NULL,
  `text` varchar(8000) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_time` datetime(0) NOT NULL,
  PRIMARY KEY (`blog_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 13 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES (1, '第一篇测试博客', 'Nihilityer', 1000000001, '这个系统让我这个新手做是真的折磨啊，网上新的知识已经不适合新手练手了，只能找老项目看着做，但是看着那些老项目，总感觉少了一点东西，只好一切从头开始，使用自己觉得学了有用的框架，不断试错，翻文档，百度，谷歌，翻源码。中途还换了几个框架，总算半个月敲出来了第一个可以正常使用的demo。之后的日子就是在这个项目中不断使用新技术，并且学习底层让项目变得更加优秀。\r\n	以下是测试：\r\n```java\r\n	public void sayHello() {\r\n		System.out.println(\"Hello World!\");\r\n	}\r\n```\r\n- 第一\r\n- 第二\r\n1. 第一\r\n1. 第二\r\n\r\n------------\r\n\r\n代码区域功能有待完善，之后会加上图片上传。', '2021-09-19 22:33:22');

-- ----------------------------
-- Table structure for timeline
-- ----------------------------
DROP TABLE IF EXISTS `timeline`;
CREATE TABLE `timeline`  (
  `index_id` int(0) NOT NULL AUTO_INCREMENT,
  `time` datetime(0) NOT NULL,
  `time_text` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`index_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of timeline
-- ----------------------------
INSERT INTO `timeline` VALUES (1, '2021-08-30 00:00:00', '制作完使用jsp+纯servlet的在线考试系统，准备做个人博客系统。');
INSERT INTO `timeline` VALUES (2, '2021-09-04 00:00:00', '确定了制作内容，开始设计前端页面<em>乘风万里，不问归期</em>');
INSERT INTO `timeline` VALUES (3, '2021-09-06 00:00:00', '页面设计断断续续，到这时刚好设计到时间线页。');
INSERT INTO `timeline` VALUES (4, '2021-09-07 00:00:00', '完成了所有能想到的页面。');
INSERT INTO `timeline` VALUES (5, '2021-09-08 00:00:00', '完成了页面的整合与数据库的设计');
INSERT INTO `timeline` VALUES (6, '2021-09-19 22:34:42', '基本功能已实现，准备在自己的小主机上跑一跑');

-- ----------------------------
-- Table structure for tstack
-- ----------------------------
DROP TABLE IF EXISTS `tstack`;
CREATE TABLE `tstack`  (
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `grade` int(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tstack
-- ----------------------------
INSERT INTO `tstack` VALUES ('Java', 16);
INSERT INTO `tstack` VALUES ('Spring', 6);
INSERT INTO `tstack` VALUES ('Springboot', 6);
INSERT INTO `tstack` VALUES ('Mybatis', 6);
INSERT INTO `tstack` VALUES ('SpringMVC', 4);
INSERT INTO `tstack` VALUES ('Layui', 4);
INSERT INTO `tstack` VALUES ('SpringSecurity', 2);

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
DROP TABLE IF EXISTS `userinfo`;
CREATE TABLE `userinfo`  (
  `user_id` int(0) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `user_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `user_sex` int(0) NULL DEFAULT 1,
  `user_role` char(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1000000002 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of userinfo
-- ----------------------------
INSERT INTO `userinfo` VALUES (1000000001, 'Nihilityer', 'nihilityer8411', 1, 'root');

-- ----------------------------
-- Table structure for webinfo
-- ----------------------------
DROP TABLE IF EXISTS `webinfo`;
CREATE TABLE `webinfo`  (
  `set_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `set_number` int(0) NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of webinfo
-- ----------------------------
INSERT INTO `webinfo` VALUES ('viewNumber', 0);
INSERT INTO `webinfo` VALUES ('blogNumber', 1);

SET FOREIGN_KEY_CHECKS = 1;
