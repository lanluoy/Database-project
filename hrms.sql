/*
 Navicat Premium Data Transfer

 Source Server         : 本机
 Source Server Type    : MySQL
 Source Server Version : 50724
 Source Host           : localhost:3306
 Source Schema         : database

 Target Server Type    : MySQL
 Target Server Version : 50724
 File Encoding         : 65001

 Date: 25/06/2022 16:05:01
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dept
-- ----------------------------
DROP TABLE IF EXISTS `dept`;
CREATE TABLE `dept`  (
  `depart_id` int(11) NOT NULL,
  `d_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `manager` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `employee_number` int(10) NOT NULL,
  `depart_desc` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`depart_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of dept
-- ----------------------------
INSERT INTO `dept` VALUES (201001, '项目部门', '杨思涵', 2, '完成项目设计，需求分析', '025-2592910');
INSERT INTO `dept` VALUES (201002, '研发部门', '张国辉', 2, '完成软件开发', '025-2592920');
INSERT INTO `dept` VALUES (201003, '技术部门', '方海燕', 2, '完成技术维护', '025-2592930');
INSERT INTO `dept` VALUES (201004, '摸鱼部门', '张笑', 0, '诶，我不上班，就划水，就是玩', '025-2592920');

-- ----------------------------
-- Table structure for edu
-- ----------------------------
DROP TABLE IF EXISTS `edu`;
CREATE TABLE `edu`  (
  `employee_id` int(11) NOT NULL,
  `e_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` date NULL DEFAULT NULL,
  `end_time` date NULL DEFAULT NULL,
  `culture` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `school` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `spec` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `edu_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`edu_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of edu
-- ----------------------------
INSERT INTO `edu` VALUES (220528001, '杨思涵', '2017-09-01', '2021-06-30', '研究生', '南京理工大学', '计算机', 1);
INSERT INTO `edu` VALUES (220528002, '张国辉', '2017-09-01', '2021-06-30', '研究生', '华南理工大学', '物联网', 2);
INSERT INTO `edu` VALUES (220528002, '张国辉', '2013-09-01', '2017-06-30', '本科生', '江苏大学', '物联网', 3);
INSERT INTO `edu` VALUES (220528003, '方海燕', '2017-09-01', '2021-06-30', '研究生', '中山大学', '计算机', 4);
INSERT INTO `edu` VALUES (220528003, '方海燕', '2013-09-01', '2017-06-30', '本科生', '天津理工大学', '计算机', 5);

-- ----------------------------
-- Table structure for emp
-- ----------------------------
DROP TABLE IF EXISTS `emp`;
CREATE TABLE `emp`  (
  `employee_id` int(11) NOT NULL,
  `e_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(2) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `date` date NOT NULL,
  `polity` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `culture` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `spec` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `work_type` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `duty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `depart` int(11) NOT NULL,
  `id_card` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `address` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `telephone` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `account` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE,
  INDEX `fk_emp_dept`(`depart`) USING BTREE,
  CONSTRAINT `fk_emp_dept` FOREIGN KEY (`depart`) REFERENCES `dept` (`depart_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp
-- ----------------------------
INSERT INTO `emp` VALUES (220528001, '杨思涵', '男', '1995-04-28', '共产党员', '研究生', '计算机', '技术开发人员', '项目部长', 201001, '320102199504284639', '江苏省南京市雨花台区能仁里46栋408室', '13758754561', '813641589@qq.com', '2784309367800378942');
INSERT INTO `emp` VALUES (220528002, '张国辉', '男', '1995-02-18', '共产党员', '研究生', '物联网', '软件工程师', '研发部长', 201002, '410721200011023518', '河南省新乡市卫滨区', '13849375277', '1662342836@qq.com', '8763918067361893027');
INSERT INTO `emp` VALUES (220528003, '方海燕', '女', '1994-10-08', '共产党员', '研究生', '计算机', '系统架构师', '技术部长', 201003, '341003200107080818', '安徽省黄山市黄山区三口镇三夫街', '13345699727', '1442832218@qq.com', '1728008939276172893');
INSERT INTO `emp` VALUES (220528004, '杨思', '男', '1995-04-28', '共产党员', '研究生', '计算机', '技术开发人员', '项目部长', 201002, '320102199504284639', '江苏省南京市雨花台区能仁里46栋408室', '13758754561', '813641589@qq.com', '2784309367800378942');
INSERT INTO `emp` VALUES (220528005, '杨涵', '男', '1995-04-28', '共产党员', '研究生', '计算机', '软件工程师', '项目部长', 201001, '320102199504284639', '江苏省南京市雨花台区能仁里46栋408室', '13758754561', '813641589@qq.com', '2784309367800378942');
INSERT INTO `emp` VALUES (220528006, '张潇', '女', '1995-04-28', '普通群众', '研究生', '计算机', '软件工程师', '普通员工', 201003, '320102199504284639', '安徽省宣城市薰化路302号', '13758754561', '813641589@qq.com', '2784309367800378942');

-- ----------------------------
-- Table structure for family
-- ----------------------------
DROP TABLE IF EXISTS `family`;
CREATE TABLE `family`  (
  `employee_id` int(11) NOT NULL,
  `e_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `marriage` varchar(4) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `family_number` int(10) NOT NULL,
  `child_number` int(10) NOT NULL,
  `dep_number` int(10) NOT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of family
-- ----------------------------
INSERT INTO `family` VALUES (220528001, '杨思涵', '已婚', 3, 1, 2);
INSERT INTO `family` VALUES (220528002, '张国辉', '未婚', 1, 0, 2);
INSERT INTO `family` VALUES (220528003, '方海燕', '已婚', 2, 0, 2);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, 'admin', '123456');

-- ----------------------------
-- Table structure for wage
-- ----------------------------
DROP TABLE IF EXISTS `wage`;
CREATE TABLE `wage`  (
  `employee_id` int(11) NOT NULL,
  `e_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `base_pay` int(10) NOT NULL,
  `prize` int(10) NOT NULL,
  `prize_record` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `deprive_pay` int(10) NOT NULL,
  `deprive_record` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`employee_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of wage
-- ----------------------------
INSERT INTO `wage` VALUES (220528001, '杨思涵', 10000, 0, '无', 100, '迟到');
INSERT INTO `wage` VALUES (220528002, '张国辉', 9000, 2000, '年终奖金，项目完成', 0, '无');
INSERT INTO `wage` VALUES (220528003, '方海燕', 8000, 3000, '年终奖金', 100, '无故旷工');
INSERT INTO `wage` VALUES (220528004, '杨思', 1000, 2000, '年终奖金，项目完成', 0, '无');
INSERT INTO `wage` VALUES (220528005, '杨涵', 8000, 1000, '完成全额目标', 0, '无');
INSERT INTO `wage` VALUES (220528006, '张潇', 1000, 0, '无', 0, '无');

-- ----------------------------
-- Table structure for work_hist
-- ----------------------------
DROP TABLE IF EXISTS `work_hist`;
CREATE TABLE `work_hist`  (
  `w_hist_id` int(11) NOT NULL AUTO_INCREMENT,
  `employee_id` int(11) NOT NULL,
  `e_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `duty` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `start_time` date NULL DEFAULT NULL,
  `end_time` date NULL DEFAULT NULL,
  `depart` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `pay` int(10) NOT NULL,
  PRIMARY KEY (`w_hist_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of work_hist
-- ----------------------------
INSERT INTO `work_hist` VALUES (1, 220528003, '方海燕', '技术组组长', '2019-04-19', '2022-08-09', '技术部门', 4000);
INSERT INTO `work_hist` VALUES (2, 220528123, '孙笑川', '技术部长', '2016-03-26', '2019-02-17', '技术部门', 8000);

-- ----------------------------
-- View structure for emp_view
-- ----------------------------
DROP VIEW IF EXISTS `emp_view`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `emp_view` AS select `emp`.`employee_id` AS `employee_id`,`emp`.`e_name` AS `e_name`,`dept`.`d_name` AS `d_name`,`emp`.`work_type` AS `work_type`,`emp`.`duty` AS `duty` from (`emp` join `dept`) where `emp`.`employee_id` in (select `emp`.`employee_id` from `emp` where (`emp`.`depart` = `dept`.`depart_id`));

-- ----------------------------
-- Procedure structure for duty_number
-- ----------------------------
DROP PROCEDURE IF EXISTS `duty_number`;
delimiter ;;
CREATE PROCEDURE `duty_number`(depart_id int)
begin
	select work_type ,count(work_type), avg(base_pay)  from emp, wage where depart = depart_id and emp.employee_id = wage.employee_id group by work_type;
end
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table emp
-- ----------------------------
DROP TRIGGER IF EXISTS `insert_trigger`;
delimiter ;;
CREATE TRIGGER `insert_trigger` AFTER INSERT ON `emp` FOR EACH ROW update dept set employee_number = (select count(*) from emp where depart = depart_id)
;
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table emp
-- ----------------------------
DROP TRIGGER IF EXISTS `update_trigger`;
delimiter ;;
CREATE TRIGGER `update_trigger` AFTER UPDATE ON `emp` FOR EACH ROW update dept set employee_number = (select count(*) from emp where depart = depart_id)
;
;;
delimiter ;

-- ----------------------------
-- Triggers structure for table emp
-- ----------------------------
DROP TRIGGER IF EXISTS `delete_trigger`;
delimiter ;;
CREATE TRIGGER `delete_trigger` AFTER DELETE ON `emp` FOR EACH ROW update dept set employee_number = (select count(*) from emp where depart = depart_id)
;
;;
delimiter ;

SET FOREIGN_KEY_CHECKS = 1;
