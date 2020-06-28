/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : 0520erp

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 28/06/2020 15:10:56
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for bus_autiding
-- ----------------------------
DROP TABLE IF EXISTS `bus_autiding`;
CREATE TABLE `bus_autiding`  (
  `id` int(11) NOT NULL,
  `auditingname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `yewustatus` int(11) DEFAULT NULL,
  `caiwustatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '审核' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_autiding
-- ----------------------------
INSERT INTO `bus_autiding` VALUES (1, '未审核', 0, 0);
INSERT INTO `bus_autiding` VALUES (2, '业务过审财务待审', 1, 0);
INSERT INTO `bus_autiding` VALUES (3, '业务待审财务过审', 0, 1);
INSERT INTO `bus_autiding` VALUES (4, '已通过', 1, 1);
INSERT INTO `bus_autiding` VALUES (5, '业务驳回财务待审', -1, 0);
INSERT INTO `bus_autiding` VALUES (6, '业务驳回财务过审', -1, 1);
INSERT INTO `bus_autiding` VALUES (7, '业务待审财务驳回', 0, -1);
INSERT INTO `bus_autiding` VALUES (8, '业务过审财务驳回', 1, -1);
INSERT INTO `bus_autiding` VALUES (9, '业务驳回财务驳回', -1, -1);
INSERT INTO `bus_autiding` VALUES (10, '业务已完成', NULL, NULL);
INSERT INTO `bus_autiding` VALUES (11, '业务已退款', NULL, NULL);

-- ----------------------------
-- Table structure for bus_autiding_copy1
-- ----------------------------
DROP TABLE IF EXISTS `bus_autiding_copy1`;
CREATE TABLE `bus_autiding_copy1`  (
  `id` int(11) NOT NULL,
  `auditingname` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `yewustatus` int(11) DEFAULT NULL,
  `caiwustatus` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '审核' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_autiding_copy1
-- ----------------------------
INSERT INTO `bus_autiding_copy1` VALUES (1, '未审核', 0, 0);
INSERT INTO `bus_autiding_copy1` VALUES (2, '业务过审财务待审', 1, 0);
INSERT INTO `bus_autiding_copy1` VALUES (3, '业务待审财务过审', 0, 1);
INSERT INTO `bus_autiding_copy1` VALUES (4, '已通过', 1, 1);
INSERT INTO `bus_autiding_copy1` VALUES (5, '业务驳回财务待审', -1, 0);
INSERT INTO `bus_autiding_copy1` VALUES (6, '业务驳回财务过审', -1, 1);
INSERT INTO `bus_autiding_copy1` VALUES (7, '业务待审财务驳回', 0, -1);
INSERT INTO `bus_autiding_copy1` VALUES (8, '业务过审财务驳回', 1, -1);
INSERT INTO `bus_autiding_copy1` VALUES (9, '业务驳回财务驳回', -1, -1);
INSERT INTO `bus_autiding_copy1` VALUES (10, '业务已完成', NULL, NULL);
INSERT INTO `bus_autiding_copy1` VALUES (11, '业务已退款', NULL, NULL);

-- ----------------------------
-- Table structure for bus_company
-- ----------------------------
DROP TABLE IF EXISTS `bus_company`;
CREATE TABLE `bus_company`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `way` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `ctime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '公司' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_company
-- ----------------------------
INSERT INTO `bus_company` VALUES (1, '123456', 'D:\\upload\\company\\f6690aad5dc945abb58c779f6fe2b951', '2020-05-12 14:00:53');

-- ----------------------------
-- Table structure for bus_customer
-- ----------------------------
DROP TABLE IF EXISTS `bus_customer`;
CREATE TABLE `bus_customer`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `zip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `connectionperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_customer
-- ----------------------------
INSERT INTO `bus_customer` VALUES (1, '123456', '1234', '1234', '1234', '1324', '1234', '', '', '1234', '', 1);

-- ----------------------------
-- Table structure for bus_customer_company
-- ----------------------------
DROP TABLE IF EXISTS `bus_customer_company`;
CREATE TABLE `bus_customer_company`  (
  `customerid` int(11) NOT NULL,
  `companyid` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin NOT NULL,
  PRIMARY KEY (`customerid`, `companyid`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_debt
-- ----------------------------
DROP TABLE IF EXISTS `bus_debt`;
CREATE TABLE `bus_debt`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) DEFAULT NULL,
  `type` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `paytype` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `dailitime` datetime(0) DEFAULT NULL,
  `starttime` datetime(0) DEFAULT NULL,
  `endtime` datetime(0) DEFAULT NULL,
  `cuizhangtime` datetime(0) DEFAULT NULL,
  `discount` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '折扣',
  `state` tinyint(4) DEFAULT NULL,
  `ispay` tinyint(4) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = '债务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_fil
-- ----------------------------
DROP TABLE IF EXISTS `bus_fil`;
CREATE TABLE `bus_fil`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `way` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `imgway` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `folid` int(11) DEFAULT NULL,
  `ctime` datetime(0) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `deltime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_fil
-- ----------------------------
INSERT INTO `bus_fil` VALUES (1, '123.jpg', 'D:\\upload\\company\\f6690aad5dc945abb58c779f6fe2b951\\746ee7462bc74ba7ab2b323d12a3df10\\7bf7fec80f4d4d6f87694a011fc69a75', 'company\\f6690aad5dc945abb58c779f6fe2b951\\746ee7462bc74ba7ab2b323d12a3df10\\7bf7fec80f4d4d6f87694a011fc69a75', 1, '2020-05-12 14:33:27', 1, NULL);

-- ----------------------------
-- Table structure for bus_folder
-- ----------------------------
DROP TABLE IF EXISTS `bus_folder`;
CREATE TABLE `bus_folder`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `way` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `fwayid` int(11) DEFAULT NULL,
  `ctime` datetime(0) DEFAULT NULL,
  `companyid` int(11) DEFAULT NULL,
  `state` tinyint(4) DEFAULT NULL,
  `deltime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_folder
-- ----------------------------
INSERT INTO `bus_folder` VALUES (1, '证照', 'D:\\upload\\company\\f6690aad5dc945abb58c779f6fe2b951\\746ee7462bc74ba7ab2b323d12a3df10', 0, '2020-05-12 14:00:53', 1, 1, NULL);
INSERT INTO `bus_folder` VALUES (2, '文档', 'D:\\upload\\company\\f6690aad5dc945abb58c779f6fe2b951\\cf27362555af4c738305a9e3636ebc9b', 0, '2020-05-12 14:00:53', 1, 1, NULL);
INSERT INTO `bus_folder` VALUES (3, '财报', 'D:\\upload\\company\\f6690aad5dc945abb58c779f6fe2b951\\875445bcf70147f88621982b0b6b9afe', 0, '2020-05-12 14:00:53', 1, 1, NULL);

-- ----------------------------
-- Table structure for bus_goods
-- ----------------------------
DROP TABLE IF EXISTS `bus_goods`;
CREATE TABLE `bus_goods`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `goodsname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `produceplace` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `size` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goodspackage` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `productcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `promitcode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `price` double DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `dangernum` int(11) DEFAULT NULL,
  `goodsimg` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `providerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_sq0btr2v2lq8gt8b4gb8tlk0i`(`providerid`) USING BTREE,
  CONSTRAINT `bus_goods_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `bus_provider` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_goods
-- ----------------------------
INSERT INTO `bus_goods` VALUES (1, '娃哈哈', '武汉', '120ML', '瓶', 'PH12345', 'PZ1234', '小孩子都爱的', 2, 1100, 10, '2018-12-25/userface1.jpg', 1, 3);
INSERT INTO `bus_goods` VALUES (2, '旺旺雪饼[小包]', '仙桃', '包', '袋', 'PH12312312', 'PZ12312', '好吃不上火', 4, 1000, 10, '2018-12-25/userface2.jpg', 1, 1);
INSERT INTO `bus_goods` VALUES (3, '旺旺大礼包', '仙桃', '盒', '盒', '11', '11', '111', 28, 1000, 100, '2018-12-25/userface3.jpg', 1, 1);
INSERT INTO `bus_goods` VALUES (5, '娃哈哈', '武汉', '300ML', '瓶', '1234', '12321', '22221111', 3, 1000, 100, '2018-12-25/userface5.jpg', 1, 3);
INSERT INTO `bus_goods` VALUES (13, '123', '2134', '120ML', '1234', '21', '1234', '2134', 1001, 1503, 4123, '2020-04-26/AA56047BB35D43FABDBA63CE7753EBD0.jpg', 1, 3);
INSERT INTO `bus_goods` VALUES (14, '娃哈哈123', '武汉', '150ML', '瓶', '1234', 'PZ1234', '小孩子都爱的', 100, 900, 100, '2020-05-06/A8D93359FEFF48B2BCD13C471E8897F2.jpg', 1, 3);

-- ----------------------------
-- Table structure for bus_inport
-- ----------------------------
DROP TABLE IF EXISTS `bus_inport`;
CREATE TABLE `bus_inport`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `inporttime` datetime(0) DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `inportprice` double DEFAULT NULL,
  `providerid` int(11) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_1o4bujsyd2kl4iqw48fie224v`(`providerid`) USING BTREE,
  INDEX `FK_ho29poeu4o2dxu4rg1ammeaql`(`goodsid`) USING BTREE,
  CONSTRAINT `bus_inport_ibfk_1` FOREIGN KEY (`providerid`) REFERENCES `bus_provider` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `bus_inport_ibfk_2` FOREIGN KEY (`goodsid`) REFERENCES `bus_goods` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 15 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_inport
-- ----------------------------
INSERT INTO `bus_inport` VALUES (1, '微信', '2018-05-07 00:00:00', '张三', 100, '备注', 3.5, 1, 1);
INSERT INTO `bus_inport` VALUES (4, '银联', '2018-05-07 00:00:00', '张三', 1000, '无', 2, 3, 1);
INSERT INTO `bus_inport` VALUES (5, '银联', '2018-05-07 00:00:00', '张三', 100, '无', 1, 3, 1);
INSERT INTO `bus_inport` VALUES (6, '银联', '2018-05-07 00:00:00', '张三', 100, '1231', 2.5, 1, 2);
INSERT INTO `bus_inport` VALUES (8, '支付宝', '2018-05-07 00:00:00', '张三', 150, '1234', 1, 3, 1);
INSERT INTO `bus_inport` VALUES (10, '支付宝', '2018-08-07 17:17:57', '超级管理员', 100, 'sadfasfdsa', 1.5, 3, 1);
INSERT INTO `bus_inport` VALUES (11, '支付宝', '2018-09-17 16:12:25', '超级管理员', 21, '21', 21, 1, 3);
INSERT INTO `bus_inport` VALUES (12, '微信', '2018-12-25 16:48:24', '超级管理员', 100, '123213213', 12321321, 3, 1);
INSERT INTO `bus_inport` VALUES (14, '支付宝', '2020-04-27 17:03:58', '超级管理员', 1000, '1234', 5, 3, 13);

-- ----------------------------
-- Table structure for bus_outport
-- ----------------------------
DROP TABLE IF EXISTS `bus_outport`;
CREATE TABLE `bus_outport`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `providerid` int(11) DEFAULT NULL,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `outputtime` datetime(0) DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `outportprice` double(10, 2) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_outport
-- ----------------------------
INSERT INTO `bus_outport` VALUES (1, 3, '微信', '2019-08-16 08:19:50', '超级管理员', 12321321.00, 1, '', 1);
INSERT INTO `bus_outport` VALUES (2, 3, '微信', '2019-08-16 08:26:54', '超级管理员', 12321321.00, 11, '11', 1);
INSERT INTO `bus_outport` VALUES (3, 3, '支付宝', '2020-04-27 17:00:20', '超级管理员', 5.00, 50, NULL, 1);
INSERT INTO `bus_outport` VALUES (4, 3, '支付宝', '2020-04-27 17:04:20', '超级管理员', 5.00, 500, NULL, 13);
INSERT INTO `bus_outport` VALUES (5, 3, '支付宝', '2020-04-27 17:08:27', '超级管理员', 5.00, -1, NULL, 13);
INSERT INTO `bus_outport` VALUES (6, 3, '支付宝', '2020-04-27 17:08:56', '超级管理员', 5.00, -1, NULL, 13);
INSERT INTO `bus_outport` VALUES (7, 3, '支付宝', '2020-04-27 17:11:16', '超级管理员', 5.00, -1, NULL, 13);
INSERT INTO `bus_outport` VALUES (13, 3, '支付宝', '2020-04-28 08:52:28', '超级管理员', 5.00, 50, NULL, 14);
INSERT INTO `bus_outport` VALUES (14, 3, '支付宝', '2020-04-28 08:54:08', '超级管理员', 5.00, 50, NULL, 14);
INSERT INTO `bus_outport` VALUES (15, 3, '支付宝', '2020-04-28 09:01:41', '超级管理员', 5.00, 50, '123546', 14);

-- ----------------------------
-- Table structure for bus_proofread
-- ----------------------------
DROP TABLE IF EXISTS `bus_proofread`;
CREATE TABLE `bus_proofread`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `proofreadname` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务名称',
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `createtime` datetime(0) DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `paytype` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `customerid` int(11) DEFAULT NULL,
  `customerimg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `operateimg` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `auditingid` int(11) DEFAULT NULL COMMENT '审核',
  `remark` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `auditcontent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `ywaudittime` datetime(0) DEFAULT NULL,
  `cwaudittime` datetime(0) DEFAULT NULL,
  `completetime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin COMMENT = 'my所用的表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_proofread
-- ----------------------------
INSERT INTO `bus_proofread` VALUES (1, '123123', '1-2-3-4-5', 1234, '2020-06-21 18:16:48', '超级管理员', '支付宝', 1, 'img/default.jpg', 'img/default.jpg', 1, '12341234', 1, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for bus_provider
-- ----------------------------
DROP TABLE IF EXISTS `bus_provider`;
CREATE TABLE `bus_provider`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `providername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `zip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `telephone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `connectionperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `bank` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `fax` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of bus_provider
-- ----------------------------
INSERT INTO `bus_provider` VALUES (1, '旺旺食品', '434000', '仙桃', '0728-4124312', '小明', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', 1);
INSERT INTO `bus_provider` VALUES (2, '达利园食品', '430000', '汉川', '0728-4124312', '大明', '13413441141', '中国农业银行', '654124345131', '12312321@sina.com', '5123123', 1);
INSERT INTO `bus_provider` VALUES (3, '娃哈哈集团', '513131', '杭州', '21312', '小晨', '12312', '建设银行', '512314141541', '123131', '312312', 1);

-- ----------------------------
-- Table structure for bus_sales
-- ----------------------------
DROP TABLE IF EXISTS `bus_sales`;
CREATE TABLE `bus_sales`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) DEFAULT NULL,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `salestime` datetime(0) DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `saleprice` decimal(10, 2) DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for bus_salesback
-- ----------------------------
DROP TABLE IF EXISTS `bus_salesback`;
CREATE TABLE `bus_salesback`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `customerid` int(11) DEFAULT NULL,
  `paytype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `salesbacktime` datetime(0) DEFAULT NULL,
  `salebackprice` double(10, 2) DEFAULT NULL,
  `operateperson` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `goodsid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  `ordernum` int(11) DEFAULT NULL COMMENT '排序码【为了调事显示顺序】',
  `createtime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES (1, 0, '总经办', 1, '大BOSS', '深圳', 1, 1, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (2, 1, '销售部', 0, '程序员屌丝', '武汉', 1, 2, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (3, 1, '运营部', 0, '无', '武汉', 1, 3, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (4, 1, '生产部', 0, '无', '武汉', 1, 4, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (5, 2, '销售一部', 0, '销售一部', '武汉', 1, 5, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (7, 3, '运营一部', 0, '运营一部', '武汉', 1, 7, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (8, 2, '销售三部', 0, '销售三部', '11', 1, 8, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (9, 2, '销售四部', 0, '销售四部', '222', 1, 9, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (10, 2, '销售五部', 0, '销售五部', '33', 1, 10, '2019-04-10 14:06:32');
INSERT INTO `sys_dept` VALUES (18, 4, '生产一部', 0, '生产食品', '武汉', 1, 11, '2019-04-13 09:49:38');
INSERT INTO `sys_dept` VALUES (19, 1, '客户部', 1, '所有客户都在这个部门里', '温州', 1, 12, '2020-05-08 10:04:10');

-- ----------------------------
-- Table structure for sys_loginfo
-- ----------------------------
DROP TABLE IF EXISTS `sys_loginfo`;
CREATE TABLE `sys_loginfo`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `loginip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `logintime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 609 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_loginfo
-- ----------------------------
INSERT INTO `sys_loginfo` VALUES (2, '超级管理员-system', '127.0.0.1', '2018-12-21 16:54:52');
INSERT INTO `sys_loginfo` VALUES (3, '超级管理员-system', '127.0.0.1', '2018-12-21 16:55:15');
INSERT INTO `sys_loginfo` VALUES (4, '超级管理员-system', '127.0.0.1', '2018-12-21 17:29:22');
INSERT INTO `sys_loginfo` VALUES (5, '超级管理员-system', '127.0.0.1', '2018-12-22 09:05:22');
INSERT INTO `sys_loginfo` VALUES (6, '超级管理员-system', '127.0.0.1', '2018-12-22 09:20:43');
INSERT INTO `sys_loginfo` VALUES (7, '超级管理员-system', '127.0.0.1', '2018-12-22 09:25:40');
INSERT INTO `sys_loginfo` VALUES (8, '超级管理员-system', '127.0.0.1', '2018-12-22 09:27:11');
INSERT INTO `sys_loginfo` VALUES (9, '超级管理员-system', '127.0.0.1', '2018-12-22 09:29:58');
INSERT INTO `sys_loginfo` VALUES (10, '超级管理员-system', '127.0.0.1', '2018-12-22 09:36:49');
INSERT INTO `sys_loginfo` VALUES (11, '超级管理员-system', '127.0.0.1', '2018-12-22 09:46:56');
INSERT INTO `sys_loginfo` VALUES (12, '超级管理员-system', '127.0.0.1', '2018-12-22 09:48:29');
INSERT INTO `sys_loginfo` VALUES (13, '超级管理员-system', '127.0.0.1', '2018-12-22 09:49:13');
INSERT INTO `sys_loginfo` VALUES (14, '超级管理员-system', '127.0.0.1', '2018-12-22 09:49:57');
INSERT INTO `sys_loginfo` VALUES (15, '超级管理员-system', '127.0.0.1', '2018-12-22 09:57:46');
INSERT INTO `sys_loginfo` VALUES (16, '超级管理员-system', '127.0.0.1', '2018-12-22 10:21:53');
INSERT INTO `sys_loginfo` VALUES (17, '超级管理员-system', '127.0.0.1', '2018-12-22 10:38:25');
INSERT INTO `sys_loginfo` VALUES (18, '超级管理员-system', '127.0.0.1', '2018-12-22 10:49:21');
INSERT INTO `sys_loginfo` VALUES (19, '超级管理员-system', '127.0.0.1', '2018-12-22 14:01:42');
INSERT INTO `sys_loginfo` VALUES (20, '超级管理员-system', '127.0.0.1', '2018-12-22 14:19:48');
INSERT INTO `sys_loginfo` VALUES (21, '超级管理员-system', '127.0.0.1', '2018-12-22 14:45:29');
INSERT INTO `sys_loginfo` VALUES (22, '超级管理员-system', '127.0.0.1', '2018-12-22 15:58:05');
INSERT INTO `sys_loginfo` VALUES (23, '超级管理员-system', '127.0.0.1', '2018-12-22 16:40:53');
INSERT INTO `sys_loginfo` VALUES (24, '超级管理员-system', '127.0.0.1', '2018-12-22 17:12:01');
INSERT INTO `sys_loginfo` VALUES (25, '超级管理员-system', '127.0.0.1', '2018-12-24 09:19:15');
INSERT INTO `sys_loginfo` VALUES (26, '超级管理员-system', '127.0.0.1', '2018-12-24 09:37:28');
INSERT INTO `sys_loginfo` VALUES (27, '超级管理员-system', '127.0.0.1', '2018-12-24 09:46:51');
INSERT INTO `sys_loginfo` VALUES (28, '超级管理员-system', '127.0.0.1', '2018-12-24 09:50:40');
INSERT INTO `sys_loginfo` VALUES (29, '超级管理员-system', '127.0.0.1', '2018-12-24 09:52:52');
INSERT INTO `sys_loginfo` VALUES (30, '超级管理员-system', '127.0.0.1', '2018-12-24 10:00:26');
INSERT INTO `sys_loginfo` VALUES (31, '超级管理员-system', '127.0.0.1', '2018-12-24 10:10:58');
INSERT INTO `sys_loginfo` VALUES (32, '超级管理员-system', '127.0.0.1', '2018-12-24 10:21:28');
INSERT INTO `sys_loginfo` VALUES (33, '超级管理员-system', '127.0.0.1', '2018-12-24 11:22:27');
INSERT INTO `sys_loginfo` VALUES (34, '超级管理员-system', '127.0.0.1', '2018-12-24 11:35:28');
INSERT INTO `sys_loginfo` VALUES (35, '超级管理员-system', '127.0.0.1', '2018-12-24 14:05:28');
INSERT INTO `sys_loginfo` VALUES (36, '超级管理员-system', '127.0.0.1', '2018-12-24 15:16:29');
INSERT INTO `sys_loginfo` VALUES (37, '李四-ls', '127.0.0.1', '2018-12-24 15:16:50');
INSERT INTO `sys_loginfo` VALUES (38, '王五-ww', '127.0.0.1', '2018-12-24 15:17:36');
INSERT INTO `sys_loginfo` VALUES (39, '赵六-zl', '127.0.0.1', '2018-12-24 15:17:47');
INSERT INTO `sys_loginfo` VALUES (40, '孙七-sq', '127.0.0.1', '2018-12-24 15:17:58');
INSERT INTO `sys_loginfo` VALUES (41, '刘八-lb', '127.0.0.1', '2018-12-24 15:18:09');
INSERT INTO `sys_loginfo` VALUES (42, '超级管理员-system', '127.0.0.1', '2018-12-24 15:34:59');
INSERT INTO `sys_loginfo` VALUES (43, '超级管理员-system', '127.0.0.1', '2018-12-24 15:35:09');
INSERT INTO `sys_loginfo` VALUES (44, '刘八-lb', '127.0.0.1', '2018-12-24 15:36:09');
INSERT INTO `sys_loginfo` VALUES (45, '刘八-lb', '127.0.0.1', '2018-12-24 15:42:58');
INSERT INTO `sys_loginfo` VALUES (46, '刘八-lb', '127.0.0.1', '2018-12-24 15:43:04');
INSERT INTO `sys_loginfo` VALUES (47, '超级管理员-system', '127.0.0.1', '2018-12-24 15:46:01');
INSERT INTO `sys_loginfo` VALUES (48, '超级管理员-system', '127.0.0.1', '2018-12-24 17:03:54');
INSERT INTO `sys_loginfo` VALUES (49, '超级管理员-system', '127.0.0.1', '2018-12-24 17:26:32');
INSERT INTO `sys_loginfo` VALUES (50, '超级管理员-system', '127.0.0.1', '2018-12-25 09:07:42');
INSERT INTO `sys_loginfo` VALUES (51, '超级管理员-system', '127.0.0.1', '2018-12-25 09:16:27');
INSERT INTO `sys_loginfo` VALUES (52, '超级管理员-system', '127.0.0.1', '2018-12-25 09:59:03');
INSERT INTO `sys_loginfo` VALUES (53, '超级管理员-system', '127.0.0.1', '2018-12-25 10:05:13');
INSERT INTO `sys_loginfo` VALUES (54, '超级管理员-system', '127.0.0.1', '2018-12-25 10:05:47');
INSERT INTO `sys_loginfo` VALUES (55, '超级管理员-system', '127.0.0.1', '2018-12-25 10:11:17');
INSERT INTO `sys_loginfo` VALUES (56, '超级管理员-system', '127.0.0.1', '2018-12-25 10:14:06');
INSERT INTO `sys_loginfo` VALUES (57, '超级管理员-system', '127.0.0.1', '2018-12-25 10:36:16');
INSERT INTO `sys_loginfo` VALUES (58, '超级管理员-system', '127.0.0.1', '2018-12-25 14:17:21');
INSERT INTO `sys_loginfo` VALUES (59, '超级管理员-system', '127.0.0.1', '2018-12-25 14:20:00');
INSERT INTO `sys_loginfo` VALUES (60, '超级管理员-system', '127.0.0.1', '2018-12-25 14:34:22');
INSERT INTO `sys_loginfo` VALUES (61, '超级管理员-system', '127.0.0.1', '2018-12-25 14:34:27');
INSERT INTO `sys_loginfo` VALUES (62, '超级管理员-system', '127.0.0.1', '2018-12-25 14:38:37');
INSERT INTO `sys_loginfo` VALUES (63, '超级管理员-system', '127.0.0.1', '2018-12-25 14:50:37');
INSERT INTO `sys_loginfo` VALUES (64, '超级管理员-system', '127.0.0.1', '2018-12-25 16:01:35');
INSERT INTO `sys_loginfo` VALUES (65, '超级管理员-system', '127.0.0.1', '2018-12-25 16:25:28');
INSERT INTO `sys_loginfo` VALUES (66, '超级管理员-system', '127.0.0.1', '2018-12-25 16:27:37');
INSERT INTO `sys_loginfo` VALUES (67, '超级管理员-system', '127.0.0.1', '2018-12-25 16:28:28');
INSERT INTO `sys_loginfo` VALUES (68, '超级管理员-system', '127.0.0.1', '2018-12-25 16:44:02');
INSERT INTO `sys_loginfo` VALUES (69, '超级管理员-system', '127.0.0.1', '2018-12-25 16:47:55');
INSERT INTO `sys_loginfo` VALUES (70, '超级管理员-system', '127.0.0.1', '2018-12-28 15:59:34');
INSERT INTO `sys_loginfo` VALUES (71, '超级管理员-system', '127.0.0.1', '2018-12-28 17:27:16');
INSERT INTO `sys_loginfo` VALUES (72, '超级管理员-system', '127.0.0.1', '2018-12-28 17:29:40');
INSERT INTO `sys_loginfo` VALUES (73, '超级管理员-system', '127.0.0.1', '2018-12-28 17:51:10');
INSERT INTO `sys_loginfo` VALUES (74, '超级管理员-system', '127.0.0.1', '2019-04-15 17:05:02');
INSERT INTO `sys_loginfo` VALUES (75, '超级管理员-system', '127.0.0.1', '2019-04-15 17:05:12');
INSERT INTO `sys_loginfo` VALUES (76, '超级管理员-system', '127.0.0.1', '2019-04-15 17:10:22');
INSERT INTO `sys_loginfo` VALUES (77, '刘八-lb', '127.0.0.1', '2019-04-15 17:11:45');
INSERT INTO `sys_loginfo` VALUES (78, '刘八-lb', '127.0.0.1', '2019-04-15 17:28:50');
INSERT INTO `sys_loginfo` VALUES (79, '超级管理员-system', '127.0.0.1', '2019-04-15 17:29:13');
INSERT INTO `sys_loginfo` VALUES (80, '刘八-lb', '127.0.0.1', '2019-04-15 17:30:59');
INSERT INTO `sys_loginfo` VALUES (81, '刘八-lb', '127.0.0.1', '2019-04-15 17:32:42');
INSERT INTO `sys_loginfo` VALUES (82, '刘八-lb', '127.0.0.1', '2019-04-15 17:33:48');
INSERT INTO `sys_loginfo` VALUES (83, '刘八-lb', '127.0.0.1', '2019-04-15 17:34:17');
INSERT INTO `sys_loginfo` VALUES (84, '超级管理员-system', '127.0.0.1', '2019-04-15 17:36:40');
INSERT INTO `sys_loginfo` VALUES (85, '超级管理员-system', '127.0.0.1', '2019-04-15 17:47:21');
INSERT INTO `sys_loginfo` VALUES (86, '超级管理员-system', '127.0.0.1', '2019-04-15 17:54:10');
INSERT INTO `sys_loginfo` VALUES (87, '超级管理员-system', '127.0.0.1', '2019-04-15 17:55:52');
INSERT INTO `sys_loginfo` VALUES (88, '超级管理员-system', '127.0.0.1', '2019-04-16 09:26:01');
INSERT INTO `sys_loginfo` VALUES (89, '超级管理员-system', '127.0.0.1', '2019-04-16 09:26:25');
INSERT INTO `sys_loginfo` VALUES (90, '超级管理员-system', '127.0.0.1', '2019-04-16 09:46:54');
INSERT INTO `sys_loginfo` VALUES (91, '超级管理员-system', '127.0.0.1', '2019-04-16 10:07:48');
INSERT INTO `sys_loginfo` VALUES (92, '超级管理员-system', '127.0.0.1', '2019-04-16 10:10:30');
INSERT INTO `sys_loginfo` VALUES (93, '超级管理员-system', '127.0.0.1', '2019-04-16 10:14:29');
INSERT INTO `sys_loginfo` VALUES (94, '超级管理员-system', '127.0.0.1', '2019-04-16 10:15:04');
INSERT INTO `sys_loginfo` VALUES (95, '超级管理员-system', '127.0.0.1', '2019-04-16 10:15:58');
INSERT INTO `sys_loginfo` VALUES (96, '超级管理员-system', '127.0.0.1', '2019-04-16 10:28:14');
INSERT INTO `sys_loginfo` VALUES (97, '超级管理员-system', '127.0.0.1', '2019-04-16 10:32:42');
INSERT INTO `sys_loginfo` VALUES (98, '超级管理员-system', '127.0.0.1', '2019-04-16 10:33:03');
INSERT INTO `sys_loginfo` VALUES (99, '超级管理员-system', '127.0.0.1', '2019-04-16 11:02:01');
INSERT INTO `sys_loginfo` VALUES (100, '超级管理员-system', '127.0.0.1', '2019-04-16 11:03:09');
INSERT INTO `sys_loginfo` VALUES (101, '超级管理员-system', '127.0.0.1', '2019-04-16 11:13:42');
INSERT INTO `sys_loginfo` VALUES (102, '超级管理员-system', '127.0.0.1', '2019-04-16 11:24:55');
INSERT INTO `sys_loginfo` VALUES (104, '超级管理员-system', '127.0.0.1', '2019-08-14 01:11:34');
INSERT INTO `sys_loginfo` VALUES (105, '超级管理员-system', '127.0.0.1', '2019-08-14 01:24:03');
INSERT INTO `sys_loginfo` VALUES (106, '李四-ls', '127.0.0.1', '2019-08-14 01:25:47');
INSERT INTO `sys_loginfo` VALUES (107, '李四-ls', '127.0.0.1', '2019-08-14 01:26:41');
INSERT INTO `sys_loginfo` VALUES (108, '孙七-sq', '127.0.0.1', '2019-08-14 01:28:22');
INSERT INTO `sys_loginfo` VALUES (110, '超级管理员-system', '127.0.0.1', '2019-08-14 01:46:18');
INSERT INTO `sys_loginfo` VALUES (111, '超级管理员-system', '127.0.0.1', '2019-08-14 02:18:44');
INSERT INTO `sys_loginfo` VALUES (112, '超级管理员-system', '127.0.0.1', '2019-08-14 02:32:06');
INSERT INTO `sys_loginfo` VALUES (113, '李四-ls', '127.0.0.1', '2019-08-14 03:00:19');
INSERT INTO `sys_loginfo` VALUES (114, '李四-ls', '127.0.0.1', '2019-08-14 03:00:56');
INSERT INTO `sys_loginfo` VALUES (115, '李四-ls', '127.0.0.1', '2019-08-14 03:01:31');
INSERT INTO `sys_loginfo` VALUES (116, '李四-ls', '127.0.0.1', '2019-08-14 03:01:39');
INSERT INTO `sys_loginfo` VALUES (117, '李四-ls', '127.0.0.1', '2019-08-14 03:02:25');
INSERT INTO `sys_loginfo` VALUES (118, '李四-ls', '127.0.0.1', '2019-08-14 03:04:57');
INSERT INTO `sys_loginfo` VALUES (119, '李四-ls', '127.0.0.1', '2019-08-14 03:07:19');
INSERT INTO `sys_loginfo` VALUES (120, '李四-ls', '127.0.0.1', '2019-08-14 03:07:54');
INSERT INTO `sys_loginfo` VALUES (121, '超级管理员-system', '127.0.0.1', '2019-08-14 03:13:06');
INSERT INTO `sys_loginfo` VALUES (122, '李四-ls', '127.0.0.1', '2019-08-14 03:14:46');
INSERT INTO `sys_loginfo` VALUES (123, '超级管理员-system', '127.0.0.1', '2019-08-14 06:03:47');
INSERT INTO `sys_loginfo` VALUES (124, '超级管理员-system', '127.0.0.1', '2019-08-14 07:20:12');
INSERT INTO `sys_loginfo` VALUES (125, '超级管理员-system', '127.0.0.1', '2019-08-14 07:23:05');
INSERT INTO `sys_loginfo` VALUES (126, '超级管理员-system', '127.0.0.1', '2019-08-14 07:25:30');
INSERT INTO `sys_loginfo` VALUES (127, '超级管理员-system', '127.0.0.1', '2019-08-14 07:26:34');
INSERT INTO `sys_loginfo` VALUES (128, '超级管理员-system', '127.0.0.1', '2019-08-14 07:27:22');
INSERT INTO `sys_loginfo` VALUES (129, '超级管理员-system', '127.0.0.1', '2019-08-14 07:27:51');
INSERT INTO `sys_loginfo` VALUES (130, '超级管理员-system', '127.0.0.1', '2019-08-14 08:22:05');
INSERT INTO `sys_loginfo` VALUES (131, '超级管理员-system', '127.0.0.1', '2019-08-14 08:43:53');
INSERT INTO `sys_loginfo` VALUES (132, '超级管理员-system', '127.0.0.1', '2019-08-14 08:45:55');
INSERT INTO `sys_loginfo` VALUES (133, '超级管理员-system', '127.0.0.1', '2019-08-14 08:47:13');
INSERT INTO `sys_loginfo` VALUES (134, '超级管理员-system', '127.0.0.1', '2019-08-14 09:35:20');
INSERT INTO `sys_loginfo` VALUES (135, '超级管理员-system', '127.0.0.1', '2019-08-14 09:41:02');
INSERT INTO `sys_loginfo` VALUES (136, '超级管理员-system', '127.0.0.1', '2019-08-14 09:44:04');
INSERT INTO `sys_loginfo` VALUES (137, '超级管理员-system', '127.0.0.1', '2019-08-14 09:50:27');
INSERT INTO `sys_loginfo` VALUES (138, '超级管理员-system', '127.0.0.1', '2019-08-14 09:56:57');
INSERT INTO `sys_loginfo` VALUES (139, '超级管理员-system', '127.0.0.1', '2019-08-14 09:59:02');
INSERT INTO `sys_loginfo` VALUES (140, '超级管理员-system', '127.0.0.1', '2019-08-16 01:05:37');
INSERT INTO `sys_loginfo` VALUES (141, '超级管理员-system', '127.0.0.1', '2019-08-16 02:01:44');
INSERT INTO `sys_loginfo` VALUES (142, '超级管理员-system', '127.0.0.1', '2019-08-16 02:05:57');
INSERT INTO `sys_loginfo` VALUES (143, '超级管理员-system', '127.0.0.1', '2019-08-16 02:07:04');
INSERT INTO `sys_loginfo` VALUES (144, '超级管理员-system', '127.0.0.1', '2019-08-16 02:20:02');
INSERT INTO `sys_loginfo` VALUES (145, '超级管理员-system', '127.0.0.1', '2019-08-16 02:20:20');
INSERT INTO `sys_loginfo` VALUES (146, '超级管理员-system', '127.0.0.1', '2019-08-16 02:21:42');
INSERT INTO `sys_loginfo` VALUES (147, '超级管理员-system', '127.0.0.1', '2019-08-16 03:37:37');
INSERT INTO `sys_loginfo` VALUES (148, '超级管理员-system', '127.0.0.1', '2019-08-16 03:52:40');
INSERT INTO `sys_loginfo` VALUES (149, '超级管理员-system', '127.0.0.1', '2019-08-16 03:59:37');
INSERT INTO `sys_loginfo` VALUES (150, '超级管理员-system', '127.0.0.1', '2019-08-16 06:11:45');
INSERT INTO `sys_loginfo` VALUES (151, '超级管理员-system', '127.0.0.1', '2019-08-16 06:23:27');
INSERT INTO `sys_loginfo` VALUES (152, '超级管理员-system', '127.0.0.1', '2019-08-16 06:50:31');
INSERT INTO `sys_loginfo` VALUES (153, '超级管理员-system', '127.0.0.1', '2019-08-16 06:54:49');
INSERT INTO `sys_loginfo` VALUES (154, '超级管理员-system', '127.0.0.1', '2019-08-16 07:00:48');
INSERT INTO `sys_loginfo` VALUES (155, '超级管理员-system', '127.0.0.1', '2019-08-16 07:01:18');
INSERT INTO `sys_loginfo` VALUES (156, '超级管理员-system', '127.0.0.1', '2019-08-16 07:03:35');
INSERT INTO `sys_loginfo` VALUES (157, '超级管理员-system', '127.0.0.1', '2019-08-16 07:09:55');
INSERT INTO `sys_loginfo` VALUES (158, '超级管理员-system', '127.0.0.1', '2019-08-16 07:46:09');
INSERT INTO `sys_loginfo` VALUES (159, '超级管理员-system', '127.0.0.1', '2019-08-16 08:17:59');
INSERT INTO `sys_loginfo` VALUES (160, '超级管理员-system', '127.0.0.1', '2019-08-16 08:22:29');
INSERT INTO `sys_loginfo` VALUES (161, '超级管理员-system', '127.0.0.1', '2019-08-16 08:23:32');
INSERT INTO `sys_loginfo` VALUES (163, '超级管理员-system', '127.0.0.1', '2019-08-16 08:31:19');
INSERT INTO `sys_loginfo` VALUES (170, '超级管理员-system', '127.0.0.1', '2020-04-19 16:33:06');
INSERT INTO `sys_loginfo` VALUES (171, '超级管理员-system', '127.0.0.1', '2020-04-19 19:59:51');
INSERT INTO `sys_loginfo` VALUES (172, '超级管理员-system', '127.0.0.1', '2020-04-19 20:25:48');
INSERT INTO `sys_loginfo` VALUES (173, '超级管理员-system', '127.0.0.1', '2020-04-19 21:06:46');
INSERT INTO `sys_loginfo` VALUES (174, '超级管理员-system', '127.0.0.1', '2020-04-19 21:43:00');
INSERT INTO `sys_loginfo` VALUES (175, '超级管理员-system', '127.0.0.1', '2020-04-20 08:35:35');
INSERT INTO `sys_loginfo` VALUES (176, '超级管理员-system', '127.0.0.1', '2020-04-20 08:47:29');
INSERT INTO `sys_loginfo` VALUES (177, '超级管理员-system', '127.0.0.1', '2020-04-20 08:50:12');
INSERT INTO `sys_loginfo` VALUES (178, '超级管理员-system', '127.0.0.1', '2020-04-20 09:27:40');
INSERT INTO `sys_loginfo` VALUES (179, '超级管理员-system', '127.0.0.1', '2020-04-20 09:31:20');
INSERT INTO `sys_loginfo` VALUES (180, '超级管理员-system', '127.0.0.1', '2020-04-20 09:37:00');
INSERT INTO `sys_loginfo` VALUES (181, '超级管理员-system', '127.0.0.1', '2020-04-20 09:37:17');
INSERT INTO `sys_loginfo` VALUES (182, '超级管理员-system', '127.0.0.1', '2020-04-20 10:05:45');
INSERT INTO `sys_loginfo` VALUES (183, '超级管理员-system', '127.0.0.1', '2020-04-20 10:10:42');
INSERT INTO `sys_loginfo` VALUES (184, '超级管理员-system', '127.0.0.1', '2020-04-20 10:14:41');
INSERT INTO `sys_loginfo` VALUES (185, '超级管理员-system', '127.0.0.1', '2020-04-20 10:32:54');
INSERT INTO `sys_loginfo` VALUES (186, '超级管理员-system', '127.0.0.1', '2020-04-20 10:38:02');
INSERT INTO `sys_loginfo` VALUES (187, '超级管理员-system', '127.0.0.1', '2020-04-20 10:53:34');
INSERT INTO `sys_loginfo` VALUES (188, '超级管理员-system', '127.0.0.1', '2020-04-20 10:57:17');
INSERT INTO `sys_loginfo` VALUES (189, '超级管理员-system', '127.0.0.1', '2020-04-20 11:02:51');
INSERT INTO `sys_loginfo` VALUES (190, '超级管理员-system', '127.0.0.1', '2020-04-20 11:09:31');
INSERT INTO `sys_loginfo` VALUES (191, '超级管理员-system', '127.0.0.1', '2020-04-20 13:55:45');
INSERT INTO `sys_loginfo` VALUES (192, '超级管理员-system', '127.0.0.1', '2020-04-20 13:58:44');
INSERT INTO `sys_loginfo` VALUES (193, '超级管理员-system', '127.0.0.1', '2020-04-20 14:24:16');
INSERT INTO `sys_loginfo` VALUES (194, '超级管理员-system', '127.0.0.1', '2020-04-20 14:26:37');
INSERT INTO `sys_loginfo` VALUES (195, '超级管理员-system', '127.0.0.1', '2020-04-20 14:41:42');
INSERT INTO `sys_loginfo` VALUES (196, '超级管理员-system', '127.0.0.1', '2020-04-20 15:05:50');
INSERT INTO `sys_loginfo` VALUES (197, '超级管理员-system', '127.0.0.1', '2020-04-20 15:16:43');
INSERT INTO `sys_loginfo` VALUES (198, '超级管理员-system', '127.0.0.1', '2020-04-20 17:11:49');
INSERT INTO `sys_loginfo` VALUES (199, '超级管理员-system', '127.0.0.1', '2020-04-20 17:12:34');
INSERT INTO `sys_loginfo` VALUES (200, '超级管理员-system', '127.0.0.1', '2020-04-21 09:10:51');
INSERT INTO `sys_loginfo` VALUES (201, '超级管理员-system', '127.0.0.1', '2020-04-21 10:17:26');
INSERT INTO `sys_loginfo` VALUES (202, '超级管理员-system', '127.0.0.1', '2020-04-21 10:20:31');
INSERT INTO `sys_loginfo` VALUES (203, '超级管理员-system', '127.0.0.1', '2020-04-21 10:58:27');
INSERT INTO `sys_loginfo` VALUES (204, '超级管理员-system', '127.0.0.1', '2020-04-21 13:56:15');
INSERT INTO `sys_loginfo` VALUES (205, '超级管理员-system', '127.0.0.1', '2020-04-21 15:42:18');
INSERT INTO `sys_loginfo` VALUES (206, '超级管理员-system', '127.0.0.1', '2020-04-21 15:48:02');
INSERT INTO `sys_loginfo` VALUES (207, '超级管理员-system', '127.0.0.1', '2020-04-21 16:34:05');
INSERT INTO `sys_loginfo` VALUES (208, '超级管理员-system', '127.0.0.1', '2020-04-21 16:37:42');
INSERT INTO `sys_loginfo` VALUES (209, '超级管理员-system', '127.0.0.1', '2020-04-21 17:28:28');
INSERT INTO `sys_loginfo` VALUES (210, '超级管理员-system', '127.0.0.1', '2020-04-21 17:30:31');
INSERT INTO `sys_loginfo` VALUES (211, '超级管理员-system', '127.0.0.1', '2020-04-21 17:34:23');
INSERT INTO `sys_loginfo` VALUES (212, '超级管理员-system', '127.0.0.1', '2020-04-21 17:42:05');
INSERT INTO `sys_loginfo` VALUES (213, '超级管理员-system', '127.0.0.1', '2020-04-21 17:55:05');
INSERT INTO `sys_loginfo` VALUES (214, '超级管理员-system', '127.0.0.1', '2020-04-21 17:57:17');
INSERT INTO `sys_loginfo` VALUES (215, '超级管理员-system', '127.0.0.1', '2020-04-21 18:00:53');
INSERT INTO `sys_loginfo` VALUES (216, '超级管理员-system', '127.0.0.1', '2020-04-21 18:27:04');
INSERT INTO `sys_loginfo` VALUES (217, '超级管理员-system', '127.0.0.1', '2020-04-21 18:34:14');
INSERT INTO `sys_loginfo` VALUES (218, '超级管理员-system', '127.0.0.1', '2020-04-21 18:36:48');
INSERT INTO `sys_loginfo` VALUES (219, '超级管理员-system', '127.0.0.1', '2020-04-21 23:09:18');
INSERT INTO `sys_loginfo` VALUES (220, '超级管理员-system', '127.0.0.1', '2020-04-21 23:11:36');
INSERT INTO `sys_loginfo` VALUES (221, '超级管理员-system', '127.0.0.1', '2020-04-21 23:14:50');
INSERT INTO `sys_loginfo` VALUES (222, '超级管理员-system', '127.0.0.1', '2020-04-21 23:47:11');
INSERT INTO `sys_loginfo` VALUES (223, '超级管理员-system', '127.0.0.1', '2020-04-21 23:48:54');
INSERT INTO `sys_loginfo` VALUES (224, '超级管理员-system', '127.0.0.1', '2020-04-22 08:50:50');
INSERT INTO `sys_loginfo` VALUES (225, '超级管理员-system', '127.0.0.1', '2020-04-22 09:47:14');
INSERT INTO `sys_loginfo` VALUES (226, '超级管理员-system', '127.0.0.1', '2020-04-22 09:51:31');
INSERT INTO `sys_loginfo` VALUES (227, '超级管理员-system', '127.0.0.1', '2020-04-22 09:56:56');
INSERT INTO `sys_loginfo` VALUES (228, '超级管理员-system', '127.0.0.1', '2020-04-22 10:01:52');
INSERT INTO `sys_loginfo` VALUES (229, '超级管理员-system', '127.0.0.1', '2020-04-22 10:05:48');
INSERT INTO `sys_loginfo` VALUES (230, '超级管理员-system', '127.0.0.1', '2020-04-22 10:11:49');
INSERT INTO `sys_loginfo` VALUES (231, '超级管理员-system', '127.0.0.1', '2020-04-22 10:17:25');
INSERT INTO `sys_loginfo` VALUES (232, '超级管理员-system', '127.0.0.1', '2020-04-22 11:02:49');
INSERT INTO `sys_loginfo` VALUES (233, '超级管理员-system', '127.0.0.1', '2020-04-22 11:11:02');
INSERT INTO `sys_loginfo` VALUES (234, '超级管理员-system', '127.0.0.1', '2020-04-22 11:16:11');
INSERT INTO `sys_loginfo` VALUES (235, '超级管理员-system', '127.0.0.1', '2020-04-22 11:17:47');
INSERT INTO `sys_loginfo` VALUES (236, '超级管理员-system', '127.0.0.1', '2020-04-22 11:45:31');
INSERT INTO `sys_loginfo` VALUES (237, '超级管理员-system', '127.0.0.1', '2020-04-22 11:48:32');
INSERT INTO `sys_loginfo` VALUES (238, '超级管理员-system', '127.0.0.1', '2020-04-22 15:02:16');
INSERT INTO `sys_loginfo` VALUES (239, '超级管理员-system', '127.0.0.1', '2020-04-22 15:10:33');
INSERT INTO `sys_loginfo` VALUES (240, '超级管理员-system', '127.0.0.1', '2020-04-22 15:17:41');
INSERT INTO `sys_loginfo` VALUES (241, '超级管理员-system', '127.0.0.1', '2020-04-22 15:23:16');
INSERT INTO `sys_loginfo` VALUES (242, '超级管理员-system', '127.0.0.1', '2020-04-22 15:42:57');
INSERT INTO `sys_loginfo` VALUES (243, '超级管理员-system', '127.0.0.1', '2020-04-22 15:50:19');
INSERT INTO `sys_loginfo` VALUES (244, '超级管理员-system', '127.0.0.1', '2020-04-22 15:57:02');
INSERT INTO `sys_loginfo` VALUES (245, '超级管理员-system', '127.0.0.1', '2020-04-22 17:24:45');
INSERT INTO `sys_loginfo` VALUES (246, '超级管理员-system', '127.0.0.1', '2020-04-22 18:48:19');
INSERT INTO `sys_loginfo` VALUES (247, '超级管理员-system', '127.0.0.1', '2020-04-22 18:51:06');
INSERT INTO `sys_loginfo` VALUES (248, '超级管理员-system', '127.0.0.1', '2020-04-22 20:19:02');
INSERT INTO `sys_loginfo` VALUES (249, '超级管理员-system', '127.0.0.1', '2020-04-22 22:54:28');
INSERT INTO `sys_loginfo` VALUES (250, '超级管理员-system', '127.0.0.1', '2020-04-22 22:57:41');
INSERT INTO `sys_loginfo` VALUES (251, '超级管理员-system', '127.0.0.1', '2020-04-22 23:04:19');
INSERT INTO `sys_loginfo` VALUES (252, '超级管理员-system', '127.0.0.1', '2020-04-22 23:17:15');
INSERT INTO `sys_loginfo` VALUES (253, '超级管理员-system', '127.0.0.1', '2020-04-22 23:23:50');
INSERT INTO `sys_loginfo` VALUES (254, '超级管理员-system', '127.0.0.1', '2020-04-23 08:46:47');
INSERT INTO `sys_loginfo` VALUES (255, '超级管理员-system', '127.0.0.1', '2020-04-23 08:57:54');
INSERT INTO `sys_loginfo` VALUES (256, '超级管理员-system', '127.0.0.1', '2020-04-23 09:06:59');
INSERT INTO `sys_loginfo` VALUES (257, '超级管理员-system', '127.0.0.1', '2020-04-23 09:25:01');
INSERT INTO `sys_loginfo` VALUES (258, '超级管理员-system', '127.0.0.1', '2020-04-23 09:45:29');
INSERT INTO `sys_loginfo` VALUES (259, '超级管理员-system', '127.0.0.1', '2020-04-23 10:04:35');
INSERT INTO `sys_loginfo` VALUES (260, '超级管理员-system', '127.0.0.1', '2020-04-23 10:08:49');
INSERT INTO `sys_loginfo` VALUES (261, '超级管理员-system', '127.0.0.1', '2020-04-23 10:12:29');
INSERT INTO `sys_loginfo` VALUES (262, '超级管理员-system', '127.0.0.1', '2020-04-23 10:13:49');
INSERT INTO `sys_loginfo` VALUES (263, '超级管理员-system', '127.0.0.1', '2020-04-23 11:55:21');
INSERT INTO `sys_loginfo` VALUES (264, '超级管理员-system', '127.0.0.1', '2020-04-23 11:59:01');
INSERT INTO `sys_loginfo` VALUES (265, '超级管理员-system', '127.0.0.1', '2020-04-23 12:01:15');
INSERT INTO `sys_loginfo` VALUES (266, '超级管理员-system', '127.0.0.1', '2020-04-23 13:43:48');
INSERT INTO `sys_loginfo` VALUES (267, '超级管理员-system', '127.0.0.1', '2020-04-23 13:49:26');
INSERT INTO `sys_loginfo` VALUES (268, '超级管理员-system', '127.0.0.1', '2020-04-23 14:15:40');
INSERT INTO `sys_loginfo` VALUES (269, '超级管理员-system', '127.0.0.1', '2020-04-23 14:21:41');
INSERT INTO `sys_loginfo` VALUES (270, '超级管理员-system', '127.0.0.1', '2020-04-23 14:26:09');
INSERT INTO `sys_loginfo` VALUES (271, '超级管理员-system', '127.0.0.1', '2020-04-23 14:37:42');
INSERT INTO `sys_loginfo` VALUES (272, '超级管理员-system', '127.0.0.1', '2020-04-23 15:45:09');
INSERT INTO `sys_loginfo` VALUES (273, '超级管理员-system', '127.0.0.1', '2020-04-23 16:58:08');
INSERT INTO `sys_loginfo` VALUES (274, '李四-ls', '127.0.0.1', '2020-04-23 16:58:53');
INSERT INTO `sys_loginfo` VALUES (275, '王五-ww', '127.0.0.1', '2020-04-23 16:59:06');
INSERT INTO `sys_loginfo` VALUES (276, '赵六-zl', '127.0.0.1', '2020-04-23 16:59:19');
INSERT INTO `sys_loginfo` VALUES (277, '超级管理员-system', '127.0.0.1', '2020-04-23 17:04:19');
INSERT INTO `sys_loginfo` VALUES (278, '超级管理员-system', '127.0.0.1', '2020-04-24 08:58:44');
INSERT INTO `sys_loginfo` VALUES (279, '刘八-lb', '127.0.0.1', '2020-04-24 08:59:57');
INSERT INTO `sys_loginfo` VALUES (280, '超级管理员-system', '127.0.0.1', '2020-04-24 09:02:14');
INSERT INTO `sys_loginfo` VALUES (281, '刘八-lb', '127.0.0.1', '2020-04-24 09:03:12');
INSERT INTO `sys_loginfo` VALUES (282, '刘八-lb', '127.0.0.1', '2020-04-24 09:11:39');
INSERT INTO `sys_loginfo` VALUES (283, '超级管理员-system', '127.0.0.1', '2020-04-24 10:05:59');
INSERT INTO `sys_loginfo` VALUES (284, '超级管理员-system', '127.0.0.1', '2020-04-24 10:07:49');
INSERT INTO `sys_loginfo` VALUES (285, '超级管理员-system', '127.0.0.1', '2020-04-24 10:19:30');
INSERT INTO `sys_loginfo` VALUES (286, '超级管理员-system', '127.0.0.1', '2020-04-24 10:23:38');
INSERT INTO `sys_loginfo` VALUES (287, '超级管理员-system', '127.0.0.1', '2020-04-24 10:43:04');
INSERT INTO `sys_loginfo` VALUES (288, '超级管理员-system', '127.0.0.1', '2020-04-24 14:01:37');
INSERT INTO `sys_loginfo` VALUES (289, '超级管理员-system', '127.0.0.1', '2020-04-24 14:04:14');
INSERT INTO `sys_loginfo` VALUES (290, '超级管理员-system', '127.0.0.1', '2020-04-25 08:56:44');
INSERT INTO `sys_loginfo` VALUES (291, '超级管理员-system', '127.0.0.1', '2020-04-25 09:11:29');
INSERT INTO `sys_loginfo` VALUES (292, '超级管理员-system', '127.0.0.1', '2020-04-25 09:28:11');
INSERT INTO `sys_loginfo` VALUES (293, '超级管理员-system', '127.0.0.1', '2020-04-25 09:31:01');
INSERT INTO `sys_loginfo` VALUES (294, '超级管理员-system', '127.0.0.1', '2020-04-25 09:33:56');
INSERT INTO `sys_loginfo` VALUES (295, '超级管理员-system', '127.0.0.1', '2020-04-25 09:51:46');
INSERT INTO `sys_loginfo` VALUES (296, '超级管理员-system', '127.0.0.1', '2020-04-25 09:56:33');
INSERT INTO `sys_loginfo` VALUES (297, '超级管理员-system', '127.0.0.1', '2020-04-25 10:45:59');
INSERT INTO `sys_loginfo` VALUES (298, '超级管理员-system', '127.0.0.1', '2020-04-25 10:58:47');
INSERT INTO `sys_loginfo` VALUES (299, '超级管理员-system', '127.0.0.1', '2020-04-25 11:35:55');
INSERT INTO `sys_loginfo` VALUES (300, '超级管理员-system', '127.0.0.1', '2020-04-25 13:46:40');
INSERT INTO `sys_loginfo` VALUES (301, '超级管理员-system', '127.0.0.1', '2020-04-25 14:51:22');
INSERT INTO `sys_loginfo` VALUES (302, '超级管理员-system', '127.0.0.1', '2020-04-25 14:54:18');
INSERT INTO `sys_loginfo` VALUES (303, '超级管理员-system', '127.0.0.1', '2020-04-25 14:56:58');
INSERT INTO `sys_loginfo` VALUES (304, '超级管理员-system', '127.0.0.1', '2020-04-25 15:02:37');
INSERT INTO `sys_loginfo` VALUES (305, '超级管理员-system', '127.0.0.1', '2020-04-25 15:05:04');
INSERT INTO `sys_loginfo` VALUES (306, '超级管理员-system', '127.0.0.1', '2020-04-25 15:11:45');
INSERT INTO `sys_loginfo` VALUES (307, '超级管理员-system', '127.0.0.1', '2020-04-25 15:14:07');
INSERT INTO `sys_loginfo` VALUES (308, '超级管理员-system', '127.0.0.1', '2020-04-25 15:15:25');
INSERT INTO `sys_loginfo` VALUES (309, '超级管理员-system', '127.0.0.1', '2020-04-25 15:17:15');
INSERT INTO `sys_loginfo` VALUES (310, '超级管理员-system', '127.0.0.1', '2020-04-25 15:52:35');
INSERT INTO `sys_loginfo` VALUES (311, '超级管理员-system', '127.0.0.1', '2020-04-25 16:01:49');
INSERT INTO `sys_loginfo` VALUES (312, '超级管理员-system', '127.0.0.1', '2020-04-25 17:44:38');
INSERT INTO `sys_loginfo` VALUES (313, '超级管理员-system', '127.0.0.1', '2020-04-26 12:44:06');
INSERT INTO `sys_loginfo` VALUES (314, '超级管理员-system', '127.0.0.1', '2020-04-26 13:21:26');
INSERT INTO `sys_loginfo` VALUES (315, '超级管理员-system', '127.0.0.1', '2020-04-26 14:22:16');
INSERT INTO `sys_loginfo` VALUES (316, '超级管理员-system', '127.0.0.1', '2020-04-26 14:28:55');
INSERT INTO `sys_loginfo` VALUES (317, '超级管理员-system', '127.0.0.1', '2020-04-26 14:29:58');
INSERT INTO `sys_loginfo` VALUES (318, '超级管理员-system', '127.0.0.1', '2020-04-26 14:40:11');
INSERT INTO `sys_loginfo` VALUES (319, '超级管理员-system', '127.0.0.1', '2020-04-26 14:41:34');
INSERT INTO `sys_loginfo` VALUES (320, '超级管理员-system', '127.0.0.1', '2020-04-26 15:15:50');
INSERT INTO `sys_loginfo` VALUES (321, '超级管理员-system', '127.0.0.1', '2020-04-26 15:33:06');
INSERT INTO `sys_loginfo` VALUES (322, '超级管理员-system', '127.0.0.1', '2020-04-27 08:52:26');
INSERT INTO `sys_loginfo` VALUES (323, '超级管理员-system', '127.0.0.1', '2020-04-27 09:16:04');
INSERT INTO `sys_loginfo` VALUES (324, '超级管理员-system', '127.0.0.1', '2020-04-27 09:18:11');
INSERT INTO `sys_loginfo` VALUES (325, '超级管理员-system', '127.0.0.1', '2020-04-27 09:29:28');
INSERT INTO `sys_loginfo` VALUES (326, '超级管理员-system', '127.0.0.1', '2020-04-27 09:36:02');
INSERT INTO `sys_loginfo` VALUES (327, '超级管理员-system', '127.0.0.1', '2020-04-27 09:40:49');
INSERT INTO `sys_loginfo` VALUES (328, '超级管理员-system', '127.0.0.1', '2020-04-27 09:53:03');
INSERT INTO `sys_loginfo` VALUES (329, '超级管理员-system', '127.0.0.1', '2020-04-27 10:59:38');
INSERT INTO `sys_loginfo` VALUES (330, '超级管理员-system', '127.0.0.1', '2020-04-27 12:08:04');
INSERT INTO `sys_loginfo` VALUES (331, '超级管理员-system', '127.0.0.1', '2020-04-27 13:48:24');
INSERT INTO `sys_loginfo` VALUES (332, '超级管理员-system', '127.0.0.1', '2020-04-27 13:55:13');
INSERT INTO `sys_loginfo` VALUES (333, '超级管理员-system', '127.0.0.1', '2020-04-27 14:56:06');
INSERT INTO `sys_loginfo` VALUES (334, '超级管理员-system', '127.0.0.1', '2020-04-27 15:02:13');
INSERT INTO `sys_loginfo` VALUES (335, '超级管理员-system', '127.0.0.1', '2020-04-27 16:39:31');
INSERT INTO `sys_loginfo` VALUES (336, '超级管理员-system', '127.0.0.1', '2020-04-27 16:58:21');
INSERT INTO `sys_loginfo` VALUES (337, '超级管理员-system', '127.0.0.1', '2020-04-27 16:59:51');
INSERT INTO `sys_loginfo` VALUES (338, '超级管理员-system', '127.0.0.1', '2020-04-27 17:11:02');
INSERT INTO `sys_loginfo` VALUES (339, '超级管理员-system', '127.0.0.1', '2020-04-27 17:38:01');
INSERT INTO `sys_loginfo` VALUES (340, '超级管理员-system', '127.0.0.1', '2020-04-28 08:23:39');
INSERT INTO `sys_loginfo` VALUES (341, '超级管理员-system', '127.0.0.1', '2020-04-28 08:26:30');
INSERT INTO `sys_loginfo` VALUES (342, '超级管理员-system', '127.0.0.1', '2020-04-28 08:41:39');
INSERT INTO `sys_loginfo` VALUES (343, '超级管理员-system', '127.0.0.1', '2020-04-28 08:43:31');
INSERT INTO `sys_loginfo` VALUES (344, '超级管理员-system', '127.0.0.1', '2020-04-28 08:46:53');
INSERT INTO `sys_loginfo` VALUES (345, '超级管理员-system', '127.0.0.1', '2020-04-28 08:50:17');
INSERT INTO `sys_loginfo` VALUES (346, '超级管理员-system', '127.0.0.1', '2020-04-28 08:52:21');
INSERT INTO `sys_loginfo` VALUES (347, '超级管理员-system', '127.0.0.1', '2020-04-28 08:58:06');
INSERT INTO `sys_loginfo` VALUES (348, '超级管理员-system', '127.0.0.1', '2020-04-28 11:09:05');
INSERT INTO `sys_loginfo` VALUES (349, '超级管理员-system', '127.0.0.1', '2020-04-28 11:13:23');
INSERT INTO `sys_loginfo` VALUES (350, '超级管理员-system', '127.0.0.1', '2020-04-28 13:40:06');
INSERT INTO `sys_loginfo` VALUES (351, '超级管理员-system', '127.0.0.1', '2020-04-28 14:32:02');
INSERT INTO `sys_loginfo` VALUES (352, '超级管理员-system', '127.0.0.1', '2020-04-28 14:32:23');
INSERT INTO `sys_loginfo` VALUES (353, '超级管理员-system', '127.0.0.1', '2020-04-28 14:44:25');
INSERT INTO `sys_loginfo` VALUES (354, '超级管理员-system', '127.0.0.1', '2020-04-28 15:19:48');
INSERT INTO `sys_loginfo` VALUES (355, '超级管理员-system', '127.0.0.1', '2020-04-28 17:19:19');
INSERT INTO `sys_loginfo` VALUES (356, '超级管理员-system', '127.0.0.1', '2020-04-28 17:21:53');
INSERT INTO `sys_loginfo` VALUES (357, '超级管理员-system', '127.0.0.1', '2020-04-28 17:24:16');
INSERT INTO `sys_loginfo` VALUES (358, '超级管理员-system', '127.0.0.1', '2020-04-28 17:33:17');
INSERT INTO `sys_loginfo` VALUES (359, '超级管理员-system', '127.0.0.1', '2020-04-29 08:25:23');
INSERT INTO `sys_loginfo` VALUES (360, '超级管理员-system', '127.0.0.1', '2020-04-29 08:30:25');
INSERT INTO `sys_loginfo` VALUES (361, '超级管理员-system', '127.0.0.1', '2020-04-29 08:34:23');
INSERT INTO `sys_loginfo` VALUES (362, '超级管理员-system', '127.0.0.1', '2020-04-29 08:40:42');
INSERT INTO `sys_loginfo` VALUES (363, '超级管理员-system', '127.0.0.1', '2020-04-29 08:44:20');
INSERT INTO `sys_loginfo` VALUES (364, '超级管理员-system', '127.0.0.1', '2020-04-29 08:46:34');
INSERT INTO `sys_loginfo` VALUES (365, '超级管理员-system', '127.0.0.1', '2020-04-29 08:57:50');
INSERT INTO `sys_loginfo` VALUES (366, '超级管理员-system', '127.0.0.1', '2020-04-29 08:59:24');
INSERT INTO `sys_loginfo` VALUES (367, '超级管理员-system', '127.0.0.1', '2020-04-29 11:17:03');
INSERT INTO `sys_loginfo` VALUES (368, '超级管理员-system', '127.0.0.1', '2020-04-29 13:31:52');
INSERT INTO `sys_loginfo` VALUES (369, '超级管理员-system', '127.0.0.1', '2020-04-29 13:51:21');
INSERT INTO `sys_loginfo` VALUES (370, '超级管理员-system', '127.0.0.1', '2020-04-29 15:29:34');
INSERT INTO `sys_loginfo` VALUES (371, '超级管理员-system', '127.0.0.1', '2020-04-29 17:35:25');
INSERT INTO `sys_loginfo` VALUES (372, '超级管理员-system', '127.0.0.1', '2020-04-30 07:11:06');
INSERT INTO `sys_loginfo` VALUES (373, '超级管理员-system', '127.0.0.1', '2020-04-30 07:12:23');
INSERT INTO `sys_loginfo` VALUES (374, '超级管理员-system', '127.0.0.1', '2020-04-30 07:20:00');
INSERT INTO `sys_loginfo` VALUES (375, '超级管理员-system', '127.0.0.1', '2020-04-30 07:22:33');
INSERT INTO `sys_loginfo` VALUES (376, '超级管理员-system', '127.0.0.1', '2020-04-30 07:30:56');
INSERT INTO `sys_loginfo` VALUES (377, '超级管理员-system', '127.0.0.1', '2020-04-30 07:39:40');
INSERT INTO `sys_loginfo` VALUES (378, '超级管理员-system', '127.0.0.1', '2020-04-30 07:44:07');
INSERT INTO `sys_loginfo` VALUES (379, '超级管理员-system', '127.0.0.1', '2020-04-30 07:50:26');
INSERT INTO `sys_loginfo` VALUES (380, '超级管理员-system', '127.0.0.1', '2020-04-30 07:53:31');
INSERT INTO `sys_loginfo` VALUES (381, '超级管理员-system', '127.0.0.1', '2020-04-30 08:46:27');
INSERT INTO `sys_loginfo` VALUES (382, '超级管理员-system', '127.0.0.1', '2020-04-30 09:27:48');
INSERT INTO `sys_loginfo` VALUES (383, '超级管理员-system', '127.0.0.1', '2020-04-30 09:30:35');
INSERT INTO `sys_loginfo` VALUES (384, '超级管理员-system', '127.0.0.1', '2020-04-30 10:04:34');
INSERT INTO `sys_loginfo` VALUES (385, '超级管理员-system', '127.0.0.1', '2020-04-30 10:30:41');
INSERT INTO `sys_loginfo` VALUES (386, '超级管理员-system', '127.0.0.1', '2020-04-30 13:54:15');
INSERT INTO `sys_loginfo` VALUES (387, '超级管理员-system', '127.0.0.1', '2020-04-30 13:56:36');
INSERT INTO `sys_loginfo` VALUES (388, '超级管理员-system', '127.0.0.1', '2020-04-30 14:05:59');
INSERT INTO `sys_loginfo` VALUES (389, '超级管理员-system', '127.0.0.1', '2020-04-30 15:58:21');
INSERT INTO `sys_loginfo` VALUES (390, '超级管理员-system', '127.0.0.1', '2020-04-30 16:01:14');
INSERT INTO `sys_loginfo` VALUES (391, '超级管理员-system', '127.0.0.1', '2020-04-30 16:22:45');
INSERT INTO `sys_loginfo` VALUES (392, '超级管理员-system', '127.0.0.1', '2020-05-01 08:00:29');
INSERT INTO `sys_loginfo` VALUES (393, '超级管理员-system', '127.0.0.1', '2020-05-01 08:45:16');
INSERT INTO `sys_loginfo` VALUES (394, '超级管理员-system', '127.0.0.1', '2020-05-01 10:34:07');
INSERT INTO `sys_loginfo` VALUES (395, '超级管理员-system', '127.0.0.1', '2020-05-01 12:33:23');
INSERT INTO `sys_loginfo` VALUES (396, '超级管理员-system', '127.0.0.1', '2020-05-01 13:52:34');
INSERT INTO `sys_loginfo` VALUES (397, '超级管理员-system', '127.0.0.1', '2020-05-06 08:41:58');
INSERT INTO `sys_loginfo` VALUES (398, '超级管理员-system', '127.0.0.1', '2020-05-06 10:00:16');
INSERT INTO `sys_loginfo` VALUES (399, '超级管理员-system', '127.0.0.1', '2020-05-06 11:05:39');
INSERT INTO `sys_loginfo` VALUES (400, '超级管理员-system', '127.0.0.1', '2020-05-06 12:02:26');
INSERT INTO `sys_loginfo` VALUES (401, '超级管理员-system', '127.0.0.1', '2020-05-06 13:41:22');
INSERT INTO `sys_loginfo` VALUES (402, '超级管理员-system', '127.0.0.1', '2020-05-06 14:28:03');
INSERT INTO `sys_loginfo` VALUES (403, '超级管理员-system', '127.0.0.1', '2020-05-06 14:41:56');
INSERT INTO `sys_loginfo` VALUES (404, '超级管理员-system', '127.0.0.1', '2020-05-06 15:06:09');
INSERT INTO `sys_loginfo` VALUES (405, '超级管理员-system', '127.0.0.1', '2020-05-06 16:38:33');
INSERT INTO `sys_loginfo` VALUES (406, '超级管理员-system', '127.0.0.1', '2020-05-06 20:04:03');
INSERT INTO `sys_loginfo` VALUES (407, '超级管理员-system', '127.0.0.1', '2020-05-06 20:30:46');
INSERT INTO `sys_loginfo` VALUES (408, '超级管理员-system', '127.0.0.1', '2020-05-06 20:35:29');
INSERT INTO `sys_loginfo` VALUES (409, '超级管理员-system', '127.0.0.1', '2020-05-06 20:43:05');
INSERT INTO `sys_loginfo` VALUES (410, '超级管理员-system', '127.0.0.1', '2020-05-06 21:19:24');
INSERT INTO `sys_loginfo` VALUES (411, '超级管理员-system', '127.0.0.1', '2020-05-06 21:24:28');
INSERT INTO `sys_loginfo` VALUES (412, '超级管理员-system', '127.0.0.1', '2020-05-06 21:46:25');
INSERT INTO `sys_loginfo` VALUES (413, '超级管理员-system', '127.0.0.1', '2020-05-06 21:48:23');
INSERT INTO `sys_loginfo` VALUES (414, '超级管理员-system', '127.0.0.1', '2020-05-06 21:59:06');
INSERT INTO `sys_loginfo` VALUES (415, '超级管理员-system', '127.0.0.1', '2020-05-06 22:03:20');
INSERT INTO `sys_loginfo` VALUES (416, '超级管理员-system', '127.0.0.1', '2020-05-06 22:04:33');
INSERT INTO `sys_loginfo` VALUES (417, '超级管理员-system', '127.0.0.1', '2020-05-06 22:09:38');
INSERT INTO `sys_loginfo` VALUES (418, '超级管理员-system', '127.0.0.1', '2020-05-06 22:32:49');
INSERT INTO `sys_loginfo` VALUES (419, '超级管理员-system', '127.0.0.1', '2020-05-06 22:43:44');
INSERT INTO `sys_loginfo` VALUES (420, '超级管理员-system', '127.0.0.1', '2020-05-07 07:11:52');
INSERT INTO `sys_loginfo` VALUES (421, '超级管理员-system', '127.0.0.1', '2020-05-07 07:15:53');
INSERT INTO `sys_loginfo` VALUES (422, '超级管理员-system', '127.0.0.1', '2020-05-07 07:19:16');
INSERT INTO `sys_loginfo` VALUES (423, '超级管理员-system', '127.0.0.1', '2020-05-07 08:02:52');
INSERT INTO `sys_loginfo` VALUES (424, '超级管理员-system', '127.0.0.1', '2020-05-07 08:37:19');
INSERT INTO `sys_loginfo` VALUES (425, '超级管理员-system', '127.0.0.1', '2020-05-07 08:41:30');
INSERT INTO `sys_loginfo` VALUES (426, '超级管理员-system', '127.0.0.1', '2020-05-07 08:51:03');
INSERT INTO `sys_loginfo` VALUES (427, '超级管理员-system', '127.0.0.1', '2020-05-07 09:12:14');
INSERT INTO `sys_loginfo` VALUES (428, '超级管理员-system', '127.0.0.1', '2020-05-07 09:23:23');
INSERT INTO `sys_loginfo` VALUES (429, '超级管理员-system', '127.0.0.1', '2020-05-07 09:48:21');
INSERT INTO `sys_loginfo` VALUES (430, '超级管理员-system', '127.0.0.1', '2020-05-07 09:53:14');
INSERT INTO `sys_loginfo` VALUES (431, '超级管理员-system', '127.0.0.1', '2020-05-07 10:11:01');
INSERT INTO `sys_loginfo` VALUES (432, '超级管理员-system', '127.0.0.1', '2020-05-07 10:20:04');
INSERT INTO `sys_loginfo` VALUES (433, '超级管理员-system', '127.0.0.1', '2020-05-07 10:22:10');
INSERT INTO `sys_loginfo` VALUES (434, '超级管理员-system', '127.0.0.1', '2020-05-07 10:39:04');
INSERT INTO `sys_loginfo` VALUES (435, '超级管理员-system', '127.0.0.1', '2020-05-07 10:52:49');
INSERT INTO `sys_loginfo` VALUES (436, '超级管理员-system', '127.0.0.1', '2020-05-07 11:41:57');
INSERT INTO `sys_loginfo` VALUES (437, '超级管理员-system', '127.0.0.1', '2020-05-07 11:43:55');
INSERT INTO `sys_loginfo` VALUES (438, '超级管理员-system', '127.0.0.1', '2020-05-07 13:39:48');
INSERT INTO `sys_loginfo` VALUES (439, '超级管理员-system', '127.0.0.1', '2020-05-07 13:53:53');
INSERT INTO `sys_loginfo` VALUES (440, '超级管理员-system', '127.0.0.1', '2020-05-07 14:16:55');
INSERT INTO `sys_loginfo` VALUES (441, '超级管理员-system', '127.0.0.1', '2020-05-07 14:37:55');
INSERT INTO `sys_loginfo` VALUES (442, '超级管理员-system', '127.0.0.1', '2020-05-07 14:42:41');
INSERT INTO `sys_loginfo` VALUES (443, '超级管理员-system', '127.0.0.1', '2020-05-07 14:44:23');
INSERT INTO `sys_loginfo` VALUES (444, '超级管理员-system', '127.0.0.1', '2020-05-07 15:01:41');
INSERT INTO `sys_loginfo` VALUES (445, '超级管理员-system', '127.0.0.1', '2020-05-07 15:11:22');
INSERT INTO `sys_loginfo` VALUES (446, '超级管理员-system', '127.0.0.1', '2020-05-07 15:14:06');
INSERT INTO `sys_loginfo` VALUES (447, '超级管理员-system', '127.0.0.1', '2020-05-07 15:31:54');
INSERT INTO `sys_loginfo` VALUES (448, '超级管理员-system', '127.0.0.1', '2020-05-07 15:34:49');
INSERT INTO `sys_loginfo` VALUES (449, '超级管理员-system', '127.0.0.1', '2020-05-07 15:40:12');
INSERT INTO `sys_loginfo` VALUES (450, '超级管理员-system', '127.0.0.1', '2020-05-07 15:44:27');
INSERT INTO `sys_loginfo` VALUES (451, '超级管理员-system', '127.0.0.1', '2020-05-07 15:45:52');
INSERT INTO `sys_loginfo` VALUES (452, '超级管理员-system', '127.0.0.1', '2020-05-07 15:57:36');
INSERT INTO `sys_loginfo` VALUES (453, '超级管理员-system', '127.0.0.1', '2020-05-08 08:37:15');
INSERT INTO `sys_loginfo` VALUES (454, '超级管理员-system', '127.0.0.1', '2020-05-08 08:42:58');
INSERT INTO `sys_loginfo` VALUES (455, '超级管理员-system', '127.0.0.1', '2020-05-08 08:45:19');
INSERT INTO `sys_loginfo` VALUES (456, '超级管理员-system', '127.0.0.1', '2020-05-08 08:49:44');
INSERT INTO `sys_loginfo` VALUES (457, '超级管理员-system', '127.0.0.1', '2020-05-08 08:55:54');
INSERT INTO `sys_loginfo` VALUES (458, '超级管理员-system', '127.0.0.1', '2020-05-08 08:57:44');
INSERT INTO `sys_loginfo` VALUES (459, '超级管理员-system', '127.0.0.1', '2020-05-08 09:03:13');
INSERT INTO `sys_loginfo` VALUES (460, '超级管理员-system', '127.0.0.1', '2020-05-08 09:10:28');
INSERT INTO `sys_loginfo` VALUES (461, '超级管理员-system', '127.0.0.1', '2020-05-08 09:19:35');
INSERT INTO `sys_loginfo` VALUES (462, '超级管理员-system', '127.0.0.1', '2020-05-08 09:23:12');
INSERT INTO `sys_loginfo` VALUES (463, '超级管理员-system', '127.0.0.1', '2020-05-08 09:24:55');
INSERT INTO `sys_loginfo` VALUES (464, '超级管理员-system', '127.0.0.1', '2020-05-08 09:36:11');
INSERT INTO `sys_loginfo` VALUES (465, '小张超市-xiaozhangchaoshi', '127.0.0.1', '2020-05-08 10:07:45');
INSERT INTO `sys_loginfo` VALUES (466, '超级管理员-system', '127.0.0.1', '2020-05-08 10:08:08');
INSERT INTO `sys_loginfo` VALUES (467, '小张超市-xiaozhangchaoshi', '127.0.0.1', '2020-05-08 10:09:20');
INSERT INTO `sys_loginfo` VALUES (468, '超级管理员-system', '127.0.0.1', '2020-05-08 10:09:31');
INSERT INTO `sys_loginfo` VALUES (469, '超级管理员-system', '127.0.0.1', '2020-05-08 10:10:09');
INSERT INTO `sys_loginfo` VALUES (470, '小张超市-xiaozhangchaoshi', '127.0.0.1', '2020-05-08 10:10:55');
INSERT INTO `sys_loginfo` VALUES (471, '超级管理员-system', '127.0.0.1', '2020-05-08 10:15:50');
INSERT INTO `sys_loginfo` VALUES (472, '超级管理员-system', '127.0.0.1', '2020-05-08 10:49:37');
INSERT INTO `sys_loginfo` VALUES (473, '超级管理员-system', '127.0.0.1', '2020-05-08 10:55:13');
INSERT INTO `sys_loginfo` VALUES (474, '超级管理员-system', '127.0.0.1', '2020-05-08 10:58:53');
INSERT INTO `sys_loginfo` VALUES (475, '超级管理员-system', '127.0.0.1', '2020-05-08 16:41:09');
INSERT INTO `sys_loginfo` VALUES (476, '超级管理员-system', '127.0.0.1', '2020-05-08 16:49:47');
INSERT INTO `sys_loginfo` VALUES (477, '超级管理员-system', '127.0.0.1', '2020-05-08 17:38:46');
INSERT INTO `sys_loginfo` VALUES (478, '超级管理员-system', '127.0.0.1', '2020-05-08 17:52:09');
INSERT INTO `sys_loginfo` VALUES (479, '超级管理员-system', '127.0.0.1', '2020-05-09 08:20:29');
INSERT INTO `sys_loginfo` VALUES (480, '小彬彬-xiaobinbin', '127.0.0.1', '2020-05-09 09:11:14');
INSERT INTO `sys_loginfo` VALUES (481, '小彬彬-xiaobinbin', '127.0.0.1', '2020-05-09 09:14:02');
INSERT INTO `sys_loginfo` VALUES (482, '超级管理员-system', '127.0.0.1', '2020-05-09 09:14:57');
INSERT INTO `sys_loginfo` VALUES (483, '小彬彬-xiaobinbin', '127.0.0.1', '2020-05-09 09:15:07');
INSERT INTO `sys_loginfo` VALUES (484, '超级管理员-system', '127.0.0.1', '2020-05-09 09:23:06');
INSERT INTO `sys_loginfo` VALUES (485, '超级管理员-system', '127.0.0.1', '2020-05-09 09:28:08');
INSERT INTO `sys_loginfo` VALUES (486, '超级管理员-system', '127.0.0.1', '2020-05-09 09:28:51');
INSERT INTO `sys_loginfo` VALUES (487, '超级管理员-system', '127.0.0.1', '2020-05-09 09:48:42');
INSERT INTO `sys_loginfo` VALUES (488, '超级管理员-system', '127.0.0.1', '2020-05-09 10:17:02');
INSERT INTO `sys_loginfo` VALUES (489, '超级管理员-system', '127.0.0.1', '2020-05-09 10:18:21');
INSERT INTO `sys_loginfo` VALUES (490, '超级管理员-system', '127.0.0.1', '2020-05-09 16:19:50');
INSERT INTO `sys_loginfo` VALUES (491, '超级管理员-system', '127.0.0.1', '2020-05-09 16:30:19');
INSERT INTO `sys_loginfo` VALUES (492, '超级管理员-system', '127.0.0.1', '2020-05-09 16:35:40');
INSERT INTO `sys_loginfo` VALUES (493, '超级管理员-system', '127.0.0.1', '2020-05-09 16:42:54');
INSERT INTO `sys_loginfo` VALUES (494, '超级管理员-system', '127.0.0.1', '2020-05-09 16:47:07');
INSERT INTO `sys_loginfo` VALUES (495, '超级管理员-system', '127.0.0.1', '2020-05-09 16:53:30');
INSERT INTO `sys_loginfo` VALUES (496, '超级管理员-system', '127.0.0.1', '2020-05-09 16:56:12');
INSERT INTO `sys_loginfo` VALUES (497, '超级管理员-system', '127.0.0.1', '2020-05-09 17:02:51');
INSERT INTO `sys_loginfo` VALUES (498, '超级管理员-system', '127.0.0.1', '2020-05-09 17:10:38');
INSERT INTO `sys_loginfo` VALUES (499, '超级管理员-system', '127.0.0.1', '2020-05-09 17:13:58');
INSERT INTO `sys_loginfo` VALUES (500, '超级管理员-system', '127.0.0.1', '2020-05-09 17:17:25');
INSERT INTO `sys_loginfo` VALUES (501, '超级管理员-system', '127.0.0.1', '2020-05-09 17:44:50');
INSERT INTO `sys_loginfo` VALUES (502, '超级管理员-system', '127.0.0.1', '2020-05-09 18:13:40');
INSERT INTO `sys_loginfo` VALUES (503, '超级管理员-system', '127.0.0.1', '2020-05-09 18:23:54');
INSERT INTO `sys_loginfo` VALUES (504, '超级管理员-system', '127.0.0.1', '2020-05-09 18:52:14');
INSERT INTO `sys_loginfo` VALUES (505, '超级管理员-system', '127.0.0.1', '2020-05-09 19:11:07');
INSERT INTO `sys_loginfo` VALUES (506, '超级管理员-system', '127.0.0.1', '2020-05-09 19:19:33');
INSERT INTO `sys_loginfo` VALUES (507, '超级管理员-system', '127.0.0.1', '2020-05-09 19:19:54');
INSERT INTO `sys_loginfo` VALUES (508, '超级管理员-system', '127.0.0.1', '2020-05-09 19:20:03');
INSERT INTO `sys_loginfo` VALUES (509, '超级管理员-system', '127.0.0.1', '2020-05-09 19:21:59');
INSERT INTO `sys_loginfo` VALUES (510, '超级管理员-system', '127.0.0.1', '2020-05-09 20:36:24');
INSERT INTO `sys_loginfo` VALUES (511, '超级管理员-system', '127.0.0.1', '2020-05-09 20:38:30');
INSERT INTO `sys_loginfo` VALUES (512, '超级管理员-system', '127.0.0.1', '2020-05-09 20:40:06');
INSERT INTO `sys_loginfo` VALUES (513, '超级管理员-system', '0:0:0:0:0:0:0:1', '2020-05-09 20:52:29');
INSERT INTO `sys_loginfo` VALUES (514, '超级管理员-system', '127.0.0.1', '2020-05-09 21:27:51');
INSERT INTO `sys_loginfo` VALUES (515, '超级管理员-system', '127.0.0.1', '2020-05-09 21:34:19');
INSERT INTO `sys_loginfo` VALUES (516, '超级管理员-system', '127.0.0.1', '2020-05-09 21:45:17');
INSERT INTO `sys_loginfo` VALUES (517, '超级管理员-system', '127.0.0.1', '2020-05-09 21:54:59');
INSERT INTO `sys_loginfo` VALUES (518, '超级管理员-system', '127.0.0.1', '2020-05-09 21:56:17');
INSERT INTO `sys_loginfo` VALUES (519, '超级管理员-system', '127.0.0.1', '2020-05-10 09:51:54');
INSERT INTO `sys_loginfo` VALUES (520, '超级管理员-system', '127.0.0.1', '2020-05-10 10:09:12');
INSERT INTO `sys_loginfo` VALUES (521, '超级管理员-system', '127.0.0.1', '2020-05-10 10:12:42');
INSERT INTO `sys_loginfo` VALUES (522, '超级管理员-system', '127.0.0.1', '2020-05-10 10:20:27');
INSERT INTO `sys_loginfo` VALUES (523, '超级管理员-system', '127.0.0.1', '2020-05-10 10:21:56');
INSERT INTO `sys_loginfo` VALUES (524, '超级管理员-system', '127.0.0.1', '2020-05-10 10:26:26');
INSERT INTO `sys_loginfo` VALUES (525, '超级管理员-system', '127.0.0.1', '2020-05-10 10:27:48');
INSERT INTO `sys_loginfo` VALUES (526, '超级管理员-system', '127.0.0.1', '2020-05-10 10:29:52');
INSERT INTO `sys_loginfo` VALUES (527, '超级管理员-system', '127.0.0.1', '2020-05-10 10:47:18');
INSERT INTO `sys_loginfo` VALUES (528, '超级管理员-system', '127.0.0.1', '2020-05-10 10:50:52');
INSERT INTO `sys_loginfo` VALUES (529, '超级管理员-system', '127.0.0.1', '2020-05-10 10:55:19');
INSERT INTO `sys_loginfo` VALUES (530, '超级管理员-system', '127.0.0.1', '2020-05-10 14:50:34');
INSERT INTO `sys_loginfo` VALUES (531, '超级管理员-system', '127.0.0.1', '2020-05-10 16:02:34');
INSERT INTO `sys_loginfo` VALUES (532, '超级管理员-system', '127.0.0.1', '2020-05-10 16:52:04');
INSERT INTO `sys_loginfo` VALUES (533, '超级管理员-system', '127.0.0.1', '2020-05-10 19:41:58');
INSERT INTO `sys_loginfo` VALUES (534, '超级管理员-system', '127.0.0.1', '2020-05-10 23:05:29');
INSERT INTO `sys_loginfo` VALUES (535, '超级管理员-system', '127.0.0.1', '2020-05-10 23:16:31');
INSERT INTO `sys_loginfo` VALUES (536, '销售-xiaoshou', '127.0.0.1', '2020-05-10 23:18:02');
INSERT INTO `sys_loginfo` VALUES (537, '超级管理员-system', '127.0.0.1', '2020-05-10 23:18:25');
INSERT INTO `sys_loginfo` VALUES (538, '销售-xiaoshou', '127.0.0.1', '2020-05-10 23:19:01');
INSERT INTO `sys_loginfo` VALUES (539, '经理-jingli', '127.0.0.1', '2020-05-10 23:20:08');
INSERT INTO `sys_loginfo` VALUES (540, '财务-caiwu', '127.0.0.1', '2020-05-10 23:20:20');
INSERT INTO `sys_loginfo` VALUES (541, '超级管理员-system', '127.0.0.1', '2020-05-10 23:25:22');
INSERT INTO `sys_loginfo` VALUES (542, '销售-xiaoshou', '127.0.0.1', '2020-05-10 23:29:15');
INSERT INTO `sys_loginfo` VALUES (543, '经理-jingli', '127.0.0.1', '2020-05-10 23:29:53');
INSERT INTO `sys_loginfo` VALUES (544, '经理-jingli', '127.0.0.1', '2020-05-10 23:30:56');
INSERT INTO `sys_loginfo` VALUES (545, '经理-jingli', '127.0.0.1', '2020-05-10 23:31:21');
INSERT INTO `sys_loginfo` VALUES (546, '经理-jingli', '127.0.0.1', '2020-05-10 23:33:09');
INSERT INTO `sys_loginfo` VALUES (547, '经理-jingli', '127.0.0.1', '2020-05-10 23:33:33');
INSERT INTO `sys_loginfo` VALUES (548, '经理-jingli', '127.0.0.1', '2020-05-10 23:35:23');
INSERT INTO `sys_loginfo` VALUES (549, '超级管理员-system', '127.0.0.1', '2020-05-10 23:36:10');
INSERT INTO `sys_loginfo` VALUES (550, '财务-caiwu', '127.0.0.1', '2020-05-10 23:36:16');
INSERT INTO `sys_loginfo` VALUES (551, '超级管理员-system', '127.0.0.1', '2020-05-10 23:36:39');
INSERT INTO `sys_loginfo` VALUES (552, '财务-caiwu', '127.0.0.1', '2020-05-10 23:37:09');
INSERT INTO `sys_loginfo` VALUES (553, '经理-jingli', '127.0.0.1', '2020-05-11 08:39:22');
INSERT INTO `sys_loginfo` VALUES (554, '经理-jingli', '127.0.0.1', '2020-05-11 09:07:33');
INSERT INTO `sys_loginfo` VALUES (555, '超级管理员-system', '127.0.0.1', '2020-05-11 09:36:54');
INSERT INTO `sys_loginfo` VALUES (556, '超级管理员-system', '127.0.0.1', '2020-05-11 13:45:31');
INSERT INTO `sys_loginfo` VALUES (557, '超级管理员-system', '127.0.0.1', '2020-05-11 13:58:09');
INSERT INTO `sys_loginfo` VALUES (558, '超级管理员-system', '127.0.0.1', '2020-05-11 14:38:21');
INSERT INTO `sys_loginfo` VALUES (559, '超级管理员-system', '127.0.0.1', '2020-05-11 14:45:12');
INSERT INTO `sys_loginfo` VALUES (560, '李四-ls', '127.0.0.1', '2020-05-11 14:52:51');
INSERT INTO `sys_loginfo` VALUES (561, '李四-ls', '127.0.0.1', '2020-05-11 14:54:59');
INSERT INTO `sys_loginfo` VALUES (562, '超级管理员-system', '127.0.0.1', '2020-05-11 14:57:42');
INSERT INTO `sys_loginfo` VALUES (563, '李四-ls', '127.0.0.1', '2020-05-11 14:58:35');
INSERT INTO `sys_loginfo` VALUES (564, '超级管理员-system', '127.0.0.1', '2020-05-11 15:00:32');
INSERT INTO `sys_loginfo` VALUES (565, '超级管理员-system', '127.0.0.1', '2020-05-11 15:10:01');
INSERT INTO `sys_loginfo` VALUES (566, '超级管理员-system', '127.0.0.1', '2020-05-11 15:12:19');
INSERT INTO `sys_loginfo` VALUES (567, '超级管理员-system', '127.0.0.1', '2020-05-11 15:16:45');
INSERT INTO `sys_loginfo` VALUES (568, '经理-jingli', '127.0.0.1', '2020-05-11 15:53:33');
INSERT INTO `sys_loginfo` VALUES (569, '超级管理员-system', '127.0.0.1', '2020-05-11 15:57:08');
INSERT INTO `sys_loginfo` VALUES (570, '超级管理员-system', '127.0.0.1', '2020-05-11 16:28:14');
INSERT INTO `sys_loginfo` VALUES (571, '超级管理员-system', '127.0.0.1', '2020-05-11 17:22:32');
INSERT INTO `sys_loginfo` VALUES (572, '超级管理员-system', '127.0.0.1', '2020-05-11 17:22:54');
INSERT INTO `sys_loginfo` VALUES (573, '超级管理员-system', '127.0.0.1', '2020-05-12 14:00:45');
INSERT INTO `sys_loginfo` VALUES (574, '超级管理员-system', '127.0.0.1', '2020-05-12 14:12:17');
INSERT INTO `sys_loginfo` VALUES (575, '超级管理员-system', '127.0.0.1', '2020-05-12 14:15:27');
INSERT INTO `sys_loginfo` VALUES (576, '超级管理员-system', '127.0.0.1', '2020-05-12 14:21:51');
INSERT INTO `sys_loginfo` VALUES (577, '超级管理员-system', '127.0.0.1', '2020-05-12 14:32:18');
INSERT INTO `sys_loginfo` VALUES (578, '超级管理员-system', '127.0.0.1', '2020-05-12 14:32:30');
INSERT INTO `sys_loginfo` VALUES (579, '超级管理员-system', '127.0.0.1', '2020-05-21 17:19:58');
INSERT INTO `sys_loginfo` VALUES (580, '超级管理员-system', '127.0.0.1', '2020-05-21 17:24:09');
INSERT INTO `sys_loginfo` VALUES (581, '超级管理员-system', '127.0.0.1', '2020-05-21 17:35:20');
INSERT INTO `sys_loginfo` VALUES (582, '超级管理员-system', '127.0.0.1', '2020-05-21 17:37:09');
INSERT INTO `sys_loginfo` VALUES (583, '超级管理员-system', '127.0.0.1', '2020-05-21 17:37:31');
INSERT INTO `sys_loginfo` VALUES (584, '超级管理员-system', '127.0.0.1', '2020-05-21 17:43:05');
INSERT INTO `sys_loginfo` VALUES (585, '超级管理员-system', '127.0.0.1', '2020-05-21 17:44:15');
INSERT INTO `sys_loginfo` VALUES (586, '超级管理员-system', '127.0.0.1', '2020-05-21 17:47:07');
INSERT INTO `sys_loginfo` VALUES (587, '超级管理员-system', '127.0.0.1', '2020-05-21 17:49:03');
INSERT INTO `sys_loginfo` VALUES (588, '超级管理员-system', '127.0.0.1', '2020-05-21 17:49:47');
INSERT INTO `sys_loginfo` VALUES (589, '超级管理员-system', '127.0.0.1', '2020-05-21 17:54:26');
INSERT INTO `sys_loginfo` VALUES (590, '超级管理员-system', '127.0.0.1', '2020-05-21 18:17:06');
INSERT INTO `sys_loginfo` VALUES (591, '超级管理员-system', '127.0.0.1', '2020-05-21 18:17:31');
INSERT INTO `sys_loginfo` VALUES (592, '超级管理员-system', '127.0.0.1', '2020-05-21 18:24:38');
INSERT INTO `sys_loginfo` VALUES (593, '超级管理员-system', '127.0.0.1', '2020-05-21 18:24:59');
INSERT INTO `sys_loginfo` VALUES (594, '超级管理员-system', '127.0.0.1', '2020-05-21 18:28:57');
INSERT INTO `sys_loginfo` VALUES (595, '超级管理员-system', '127.0.0.1', '2020-05-21 18:29:07');
INSERT INTO `sys_loginfo` VALUES (596, '超级管理员-system', '127.0.0.1', '2020-05-21 18:30:57');
INSERT INTO `sys_loginfo` VALUES (597, '超级管理员-system', '127.0.0.1', '2020-05-21 18:31:44');
INSERT INTO `sys_loginfo` VALUES (598, '超级管理员-system', '127.0.0.1', '2020-05-21 18:32:36');
INSERT INTO `sys_loginfo` VALUES (599, '超级管理员-system', '127.0.0.1', '2020-05-21 18:35:31');
INSERT INTO `sys_loginfo` VALUES (600, '超级管理员-system', '127.0.0.1', '2020-05-21 18:35:59');
INSERT INTO `sys_loginfo` VALUES (601, '超级管理员-system', '127.0.0.1', '2020-05-21 18:36:40');
INSERT INTO `sys_loginfo` VALUES (602, '超级管理员-system', '127.0.0.1', '2020-05-21 18:37:23');
INSERT INTO `sys_loginfo` VALUES (603, '超级管理员-system', '127.0.0.1', '2020-05-21 18:37:47');
INSERT INTO `sys_loginfo` VALUES (604, '超级管理员-system', '127.0.0.1', '2020-05-21 18:39:16');
INSERT INTO `sys_loginfo` VALUES (605, '超级管理员-system', '127.0.0.1', '2020-05-21 18:39:34');
INSERT INTO `sys_loginfo` VALUES (606, '超级管理员-system', '127.0.0.1', '2020-05-21 18:40:01');
INSERT INTO `sys_loginfo` VALUES (607, '超级管理员-system', '127.0.0.1', '2020-06-06 14:59:26');
INSERT INTO `sys_loginfo` VALUES (608, '超级管理员-system', '127.0.0.1', '2020-06-21 18:14:59');

-- ----------------------------
-- Table structure for sys_notice
-- ----------------------------
DROP TABLE IF EXISTS `sys_notice`;
CREATE TABLE `sys_notice`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci,
  `createtime` datetime(0) DEFAULT NULL,
  `opername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 17 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_notice
-- ----------------------------
INSERT INTO `sys_notice` VALUES (1, '关于系统V1.3更新公告', '2', '2018-08-07 00:00:00', '管理员');
INSERT INTO `sys_notice` VALUES (10, '关于系统V1.2更新公告', '12312312<img src=\"/resources/layui/images/face/42.gif\" alt=\"[抓狂]\">', '2018-08-07 00:00:00', '超级管理员');
INSERT INTO `sys_notice` VALUES (11, '关于系统V1.1更新公告', '21321321321<img src=\"/resources/layui/images/face/18.gif\" alt=\"[右哼哼]\">', '2018-08-07 00:00:00', '超级管理员');
INSERT INTO `sys_notice` VALUES (12, '123123', '', '2020-04-19 21:06:53', '超级管理员');
INSERT INTO `sys_notice` VALUES (16, '今晚上11111', 'netty项目课 大家快来上课', '2020-04-20 17:12:57', '超级管理员');

-- ----------------------------
-- Table structure for sys_operatelog
-- ----------------------------
DROP TABLE IF EXISTS `sys_operatelog`;
CREATE TABLE `sys_operatelog`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `username` varchar(45) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `param` text CHARACTER SET utf8mb4 COLLATE utf8mb4_bin,
  `result` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `requestpath` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `requestaddr` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `description` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_bin DEFAULT NULL,
  `ctime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_bin ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_operatelog
-- ----------------------------
INSERT INTO `sys_operatelog` VALUES (16, 1, NULL, '{\"id\":[\"\"],\"customername\":[\"1234\"],\"telephone\":[\"2134\"],\"zip\":[\"1234\"],\"address\":[\"2134\"],\"connectionperson\":[\"2134\"],\"phone\":[\"2134\"],\"email\":[\"2134\"],\"bank\":[\"1234\"],\"account\":[\"1234\"],\"fax\":[\"1234\"],\"available\":[\"1\"]}', '{\"result\":{\"code\":200,\"msg\":\"添加成功\"}}', '/customer/addCustomer', '127.0.0.1', '添加客户', '2020-05-10 10:51:15');
INSERT INTO `sys_operatelog` VALUES (17, 1, '超级管理员', '{\"id\":[\"9\"]}', '{\"result\":{\"code\":200,\"msg\":\"删除成功\"}}', '/customer/deleteCustomer', '127.0.0.1', '删除客户', '2020-05-10 10:55:30');
INSERT INTO `sys_operatelog` VALUES (18, 1, '超级管理员', '{\"id\":[\"8\"]}', '{\"result\":{\"code\":200,\"msg\":\"删除成功\"}}', '/customer/deleteCustomer', '127.0.0.1', '删除客户', '2020-05-10 10:55:32');
INSERT INTO `sys_operatelog` VALUES (19, 1, '超级管理员', '{\"id\":[\"11\"]}', '{\"result\":{\"code\":200,\"msg\":\"删除成功\"}}', '/user/deleteUser', '127.0.0.1', '删除用户', '2020-05-10 16:04:10');
INSERT INTO `sys_operatelog` VALUES (20, 1, '超级管理员', '{\"id\":[\"7\"]}', '{\"result\":{\"code\":200,\"msg\":\"删除成功\"}}', '/customer/deleteCustomer', '127.0.0.1', '删除客户', '2020-05-10 16:04:54');
INSERT INTO `sys_operatelog` VALUES (21, 1, '超级管理员', '{\"deptid\":[\"4\"],\"deptTree_select_nodeId\":[\"4\"],\"deptTree_select_input\":[\"生产部\"],\"ordernum\":[\"7\"],\"id\":[\"10\"],\"leaderTree_select_nodeId\":[\"4\"],\"leaderTree_select_input\":[\"生产部\"],\"mgr\":[\"4\"],\"name\":[\"小彬彬\"],\"loginname\":[\"xiaobinbin\"],\"address\":[\"1231234\"],\"remark\":[\"12341234\"],\"sex\":[\"1\"],\"available\":[\"1\"],\"iscustomer\":[\"1\"],\"customerid\":[\"3\"]}', '{\"result\":{\"code\":200,\"msg\":\"修改成功\"}}', '/user/updateUser', '127.0.0.1', '修改用户', '2020-05-10 16:33:51');
INSERT INTO `sys_operatelog` VALUES (22, 1, '超级管理员', '{\"providerid\":[\"3\"],\"goodsid\":[\"1\"],\"id\":[\"8\"],\"number\":[\"150\"],\"inportprice\":[\"1\"],\"paytype\":[\"支付宝\"],\"remark\":[\"1234\"]}', '{\"result\":{\"code\":200,\"msg\":\"修改成功\"}}', '/inport/updateInport', '127.0.0.1', '修改进货单', '2020-05-10 16:52:11');
INSERT INTO `sys_operatelog` VALUES (23, 1, '超级管理员', '{\"id\":[\"\"],\"name\":[\"财务审核\"],\"remark\":[\"审核财务账单\"],\"available\":[\"1\"]}', '{\"result\":{\"code\":200,\"msg\":\"添加成功\"}}', '/role/addRole', '127.0.0.1', '添加角色', '2020-05-10 20:00:23');
INSERT INTO `sys_operatelog` VALUES (24, 1, '超级管理员', '{\"deptid\":[\"5\"],\"deptTree_select_nodeId\":[\"5\"],\"deptTree_select_input\":[\"销售一部\"],\"ordernum\":[\"10\"],\"id\":[\"14\"],\"leaderTree_select_nodeId\":[\"5\"],\"leaderTree_select_input\":[\"销售一部\"],\"mgr\":[\"0\"],\"name\":[\"经理\"],\"loginname\":[\"jingli\"],\"address\":[\"2134\"],\"remark\":[\"测试用的经理1号\"],\"sex\":[\"1\"],\"available\":[\"1\"],\"iscustomer\":[\"1\"],\"customerid\":[\"\"]}', '{\"result\":{\"code\":200,\"msg\":\"修改成功\"}}', '/user/updateUser', '127.0.0.1', '修改用户', '2020-05-10 23:16:08');
INSERT INTO `sys_operatelog` VALUES (25, 1, '超级管理员', '{\"deptid\":[\"5\"],\"deptTree_select_nodeId\":[\"5\"],\"deptTree_select_input\":[\"销售一部\"],\"ordernum\":[\"8\"],\"id\":[\"12\"],\"leaderTree_select_nodeId\":[\"5\"],\"leaderTree_select_input\":[\"销售一部\"],\"mgr\":[\"14\"],\"name\":[\"销售\"],\"loginname\":[\"xiaoshou\"],\"address\":[\"武汉\"],\"remark\":[\"测试用的业务员\"],\"sex\":[\"1\"],\"available\":[\"1\"],\"iscustomer\":[\"1\"],\"customerid\":[\"\"]}', '{\"result\":{\"code\":200,\"msg\":\"修改成功\"}}', '/user/updateUser', '127.0.0.1', '修改用户', '2020-05-10 23:16:18');
INSERT INTO `sys_operatelog` VALUES (26, 1, '超级管理员', '{\"uid\":[\"12\"],\"ids\":[\"11\"]}', '{\"result\":{\"code\":200,\"msg\":\"分配成功\"}}', '/user/saveUserRole', '127.0.0.1', '保存用户和角色之间的关系', '2020-05-10 23:16:57');
INSERT INTO `sys_operatelog` VALUES (27, 1, '超级管理员', '{\"uid\":[\"13\"],\"ids\":[\"13\"]}', '{\"result\":{\"code\":200,\"msg\":\"分配成功\"}}', '/user/saveUserRole', '127.0.0.1', '保存用户和角色之间的关系', '2020-05-10 23:17:04');
INSERT INTO `sys_operatelog` VALUES (28, 1, '超级管理员', '{\"uid\":[\"14\"],\"ids\":[\"12\"]}', '{\"result\":{\"code\":200,\"msg\":\"分配成功\"}}', '/user/saveUserRole', '127.0.0.1', '保存用户和角色之间的关系', '2020-05-10 23:17:08');
INSERT INTO `sys_operatelog` VALUES (29, 12, '销售', '{\"customerid\":[\"1\"],\"id\":[\"\"],\"proofreadname\":[\"123123\"],\"contents[0]\":[\"1\"],\"contents[1]\":[\"2\"],\"contents[2]\":[\"3\"],\"contents[3]\":[\"4\"],\"contents[4]\":[\"5\"],\"paytype\":[\"支付宝\"],\"price\":[\"100\"],\"remark\":[\"12341234\"],\"customerimg\":[\"2020-05-10/37FD19AE00AC47B8AEA52C2C488FF77F.jpg_temp\"],\"mf\":[\"\"],\"operateimg\":[\"img/default.jpg\"]}', '{\"result\":{\"code\":200,\"msg\":\"添加成功\"}}', '/proofread/addProofread', '127.0.0.1', '查询业务表', '2020-05-10 23:19:40');
INSERT INTO `sys_operatelog` VALUES (30, 1, '超级管理员', '{\"name\":[\"123456\"]}', '{\"result\":\"OK\"}', '/bus/createCompanyAJAX.do', '127.0.0.1', '创建新公司', '2020-05-12 14:00:53');
INSERT INTO `sys_operatelog` VALUES (31, 1, '超级管理员', '{\"fway_id\":[\"1\"]}', '{\"result\":{\"msg\":\"文件上传成功\",\"state\":\"OK\"}}', '/bus/uploadFile.do', '127.0.0.1', '上传文件', '2020-05-12 14:33:27');
INSERT INTO `sys_operatelog` VALUES (32, 1, '超级管理员', '{\"id\":[\"\"],\"customername\":[\"123456\"],\"telephone\":[\"1234\"],\"zip\":[\"1234\"],\"address\":[\"1234\"],\"connectionperson\":[\"1324\"],\"phone\":[\"1234\"],\"email\":[\"1234\"],\"bank\":[\"\"],\"account\":[\"\"],\"fax\":[\"\"],\"available\":[\"1\"]}', '{\"result\":{\"code\":200,\"msg\":\"添加成功\"}}', '/customer/addCustomer', '127.0.0.1', '添加客户', '2020-06-21 18:16:34');
INSERT INTO `sys_operatelog` VALUES (33, 1, '超级管理员', '{\"customerid\":[\"1\"],\"id\":[\"\"],\"proofreadname\":[\"123123\"],\"contents[0]\":[\"1\"],\"contents[1]\":[\"2\"],\"contents[2]\":[\"3\"],\"contents[3]\":[\"4\"],\"contents[4]\":[\"5\"],\"paytype\":[\"支付宝\"],\"price\":[\"1234\"],\"remark\":[\"12341234\"],\"customerimg\":[\"img/default.jpg\"],\"mf\":[\"\"],\"operateimg\":[\"img/default.jpg\"]}', '{\"result\":{\"code\":200,\"msg\":\"添加成功\"}}', '/proofread/addProofread', '127.0.0.1', '查询业务表', '2020-06-21 18:16:48');

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限类型[menu/permission]',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `percode` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '权限编码[只有type= permission才有  user:view]',
  `icon` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `href` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `target` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `open` int(11) DEFAULT NULL,
  `ordernum` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT NULL COMMENT '状态【0不可用1可用】',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 124 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 0, 'menu', '尚学堂进销存管理系统', NULL, '&#xe68e;', '', '', 1, 1, 1);
INSERT INTO `sys_permission` VALUES (2, 1, 'menu', '基础管理', NULL, '&#xe857;', '', '', 0, 2, 1);
INSERT INTO `sys_permission` VALUES (3, 1, 'menu', '进货管理', NULL, '&#xe645;', '', NULL, 0, 3, 1);
INSERT INTO `sys_permission` VALUES (4, 1, 'menu', '销售管理', NULL, '&#xe611;', '', '', 0, 4, 1);
INSERT INTO `sys_permission` VALUES (5, 1, 'menu', '系统管理', NULL, '&#xe614;', '', '', 0, 5, 1);
INSERT INTO `sys_permission` VALUES (6, 1, 'menu', '其它管理', NULL, '&#xe628;', '', '', 0, 6, 1);
INSERT INTO `sys_permission` VALUES (7, 2, 'menu', '客户管理', NULL, '&#xe651;', '/bus/toCustomerManager', '', 0, 7, 1);
INSERT INTO `sys_permission` VALUES (8, 2, 'menu', '供应商管理', NULL, '&#xe658;', '/bus/toProviderManager', '', 0, 8, 1);
INSERT INTO `sys_permission` VALUES (9, 2, 'menu', '商品管理', NULL, '&#xe657;', '/bus/toGoodsManager', '', 0, 9, 1);
INSERT INTO `sys_permission` VALUES (10, 3, 'menu', '商品进货', NULL, '&#xe756;', '/bus/toInportManager', '', 0, 10, 1);
INSERT INTO `sys_permission` VALUES (11, 3, 'menu', '商品退货查询', NULL, '&#xe65a;', '/bus/toOutportManager', '', 0, 11, 1);
INSERT INTO `sys_permission` VALUES (12, 4, 'menu', '商品销售', NULL, '&#xe65b;', '123', '', 0, 12, 0);
INSERT INTO `sys_permission` VALUES (13, 4, 'menu', '销售退货查询', NULL, '&#xe770;', '1234', '', 0, 13, 0);
INSERT INTO `sys_permission` VALUES (14, 5, 'menu', '部门管理', NULL, '&#xe770;', '/sys/toDeptManager', '', 0, 14, 1);
INSERT INTO `sys_permission` VALUES (15, 5, 'menu', '菜单管理', NULL, '&#xe857;', '/sys/toMenuManager', '', 0, 15, 1);
INSERT INTO `sys_permission` VALUES (16, 5, 'menu', '权限管理', '', '&#xe857;', '/sys/toPermissionManager', '', 0, 16, 1);
INSERT INTO `sys_permission` VALUES (17, 5, 'menu', '角色管理', '', '&#xe650;', '/sys/toRoleManager', '', 0, 17, 1);
INSERT INTO `sys_permission` VALUES (18, 5, 'menu', '用户管理', '', '&#xe612;', '/sys/toUserManager', '', 0, 18, 1);
INSERT INTO `sys_permission` VALUES (21, 6, 'menu', '登陆日志', NULL, '&#xe675;', '/sys/toLoginfoManager', '', 0, 21, 1);
INSERT INTO `sys_permission` VALUES (22, 6, 'menu', '系统公告', NULL, '&#xe756;', '/sys/toNoticeManager', NULL, 0, 22, 1);
INSERT INTO `sys_permission` VALUES (23, 6, 'menu', '图标管理', NULL, '&#xe670;', '../resources/page/icon.html', NULL, 0, 23, 1);
INSERT INTO `sys_permission` VALUES (30, 14, 'permission', '添加部门', 'dept:create', '', NULL, NULL, 0, 24, 1);
INSERT INTO `sys_permission` VALUES (31, 14, 'permission', '修改部门', 'dept:update', '', NULL, NULL, 0, 26, 1);
INSERT INTO `sys_permission` VALUES (32, 14, 'permission', '删除部门', 'dept:delete', '', NULL, NULL, 0, 27, 1);
INSERT INTO `sys_permission` VALUES (34, 15, 'permission', '添加菜单', 'menu:create', '', '', '', 0, 29, 1);
INSERT INTO `sys_permission` VALUES (35, 15, 'permission', '修改菜单', 'menu:update', '', NULL, NULL, 0, 30, 1);
INSERT INTO `sys_permission` VALUES (36, 15, 'permission', '删除菜单', 'menu:delete', '', NULL, NULL, 0, 31, 1);
INSERT INTO `sys_permission` VALUES (38, 16, 'permission', '添加权限', 'permission:create', '', NULL, NULL, 0, 33, 1);
INSERT INTO `sys_permission` VALUES (39, 16, 'permission', '修改权限', 'permission:update', '', NULL, NULL, 0, 34, 1);
INSERT INTO `sys_permission` VALUES (40, 16, 'permission', '删除权限', 'permission:delete', '', NULL, NULL, 0, 35, 1);
INSERT INTO `sys_permission` VALUES (42, 17, 'permission', '添加角色', 'role:create', '', NULL, NULL, 0, 37, 1);
INSERT INTO `sys_permission` VALUES (43, 17, 'permission', '修改角色', 'role:update', '', NULL, NULL, 0, 38, 1);
INSERT INTO `sys_permission` VALUES (44, 17, 'permission', '角色删除', 'role:delete', '', NULL, NULL, 0, 39, 1);
INSERT INTO `sys_permission` VALUES (46, 17, 'permission', '分配权限', 'role:selectPermission', '', NULL, NULL, 0, 41, 1);
INSERT INTO `sys_permission` VALUES (47, 18, 'permission', '添加用户', 'user:create', '', NULL, NULL, 0, 42, 1);
INSERT INTO `sys_permission` VALUES (48, 18, 'permission', '修改用户', 'user:update', '', NULL, NULL, 0, 43, 1);
INSERT INTO `sys_permission` VALUES (49, 18, 'permission', '删除用户', 'user:delete', '', NULL, NULL, 0, 44, 1);
INSERT INTO `sys_permission` VALUES (51, 18, 'permission', '用户分配角色', 'user:selectRole', '', NULL, NULL, 0, 46, 1);
INSERT INTO `sys_permission` VALUES (52, 18, 'permission', '重置密码', 'user:resetPwd', NULL, NULL, NULL, 0, 47, 1);
INSERT INTO `sys_permission` VALUES (53, 14, 'permission', '部门查询', 'dept:view', NULL, NULL, NULL, 0, 48, 1);
INSERT INTO `sys_permission` VALUES (54, 15, 'permission', '菜单查询', 'menu:view', NULL, NULL, NULL, 0, 49, 1);
INSERT INTO `sys_permission` VALUES (55, 16, 'permission', '权限查询', 'permission:view', NULL, NULL, NULL, 0, 50, 1);
INSERT INTO `sys_permission` VALUES (56, 17, 'permission', '角色查询', 'role:view', NULL, NULL, NULL, 0, 51, 1);
INSERT INTO `sys_permission` VALUES (57, 18, 'permission', '用户查询', 'user:view', NULL, NULL, NULL, 0, 52, 1);
INSERT INTO `sys_permission` VALUES (68, 7, 'permission', '客户查询', 'customer:view', NULL, NULL, NULL, NULL, 60, 1);
INSERT INTO `sys_permission` VALUES (69, 7, 'permission', '客户添加', 'customer:create', NULL, NULL, NULL, NULL, 61, 1);
INSERT INTO `sys_permission` VALUES (70, 7, 'permission', '客户修改', 'customer:update', NULL, NULL, NULL, NULL, 62, 1);
INSERT INTO `sys_permission` VALUES (71, 7, 'permission', '客户删除', 'customer:delete', NULL, NULL, NULL, NULL, 63, 1);
INSERT INTO `sys_permission` VALUES (73, 21, 'permission', '日志查询', 'info:view', NULL, NULL, NULL, NULL, 65, 1);
INSERT INTO `sys_permission` VALUES (74, 21, 'permission', '日志删除', 'info:delete', NULL, NULL, NULL, NULL, 66, 1);
INSERT INTO `sys_permission` VALUES (75, 21, 'permission', '日志批量删除', 'info:batchdelete', NULL, NULL, NULL, NULL, 67, 1);
INSERT INTO `sys_permission` VALUES (76, 22, 'permission', '公告查询', 'notice:view', NULL, NULL, NULL, NULL, 68, 1);
INSERT INTO `sys_permission` VALUES (77, 22, 'permission', '公告添加', 'notice:create', NULL, NULL, NULL, NULL, 69, 1);
INSERT INTO `sys_permission` VALUES (78, 22, 'permission', '公告修改', 'notice:update', NULL, NULL, NULL, NULL, 70, 1);
INSERT INTO `sys_permission` VALUES (79, 22, 'permission', '公告删除', 'notice:delete', NULL, NULL, NULL, NULL, 71, 1);
INSERT INTO `sys_permission` VALUES (81, 8, 'permission', '供应商查询', 'provider:view', NULL, NULL, NULL, NULL, 73, 1);
INSERT INTO `sys_permission` VALUES (82, 8, 'permission', '供应商添加', 'provider:create', NULL, NULL, NULL, NULL, 74, 1);
INSERT INTO `sys_permission` VALUES (83, 8, 'permission', '供应商修改', 'provider:update', NULL, NULL, NULL, NULL, 75, 1);
INSERT INTO `sys_permission` VALUES (84, 8, 'permission', '供应商删除', 'provider:delete', NULL, NULL, NULL, NULL, 76, 1);
INSERT INTO `sys_permission` VALUES (86, 22, 'permission', '公告查看', 'notice:viewnotice', NULL, NULL, NULL, NULL, 78, 1);
INSERT INTO `sys_permission` VALUES (91, 9, 'permission', '商品查询', 'goods:view', NULL, NULL, NULL, 0, 79, 1);
INSERT INTO `sys_permission` VALUES (92, 9, 'permission', '商品添加', 'goods:create', NULL, NULL, NULL, 0, 80, 1);
INSERT INTO `sys_permission` VALUES (93, 6, 'menu', '缓存管理', NULL, '&#xe6b1', '/sys/toCacheManager', '', 0, 81, 1);
INSERT INTO `sys_permission` VALUES (94, 4, 'menu', '业务管理', NULL, '&#xe67b;', '/bus/toProofreadManager', '', 0, 82, 1);
INSERT INTO `sys_permission` VALUES (95, 4, 'menu', '经理审核', NULL, '&#xe67b;', '/bus/toAuditManager', '', 0, 83, 1);
INSERT INTO `sys_permission` VALUES (96, 93, 'permission', '缓存清空', 'cache:removeall', NULL, NULL, NULL, 0, 84, 1);
INSERT INTO `sys_permission` VALUES (97, 93, 'permission', '缓存同步', 'cache:sync', NULL, NULL, NULL, 0, 85, 1);
INSERT INTO `sys_permission` VALUES (98, 93, 'permission', '缓存删除', 'cache:delete', NULL, NULL, NULL, 0, 86, 1);
INSERT INTO `sys_permission` VALUES (99, 93, 'permission', '缓存查看', 'cache:view', NULL, NULL, NULL, 0, 87, 1);
INSERT INTO `sys_permission` VALUES (100, 9, 'permission', '商品修改', 'goods:update', NULL, NULL, NULL, 0, 88, 1);
INSERT INTO `sys_permission` VALUES (101, 9, 'permission', '商品删除', 'goods:delete', NULL, NULL, NULL, 0, 89, 1);
INSERT INTO `sys_permission` VALUES (102, 10, 'permission', '进货查看', 'inport:view', NULL, NULL, NULL, 0, 90, 1);
INSERT INTO `sys_permission` VALUES (103, 10, 'permission', '进货添加', 'inport:create', NULL, NULL, NULL, 0, 91, 1);
INSERT INTO `sys_permission` VALUES (104, 10, 'permission', '进货修改', 'inport:update', NULL, NULL, NULL, 0, 92, 1);
INSERT INTO `sys_permission` VALUES (105, 10, 'permission', '进货删除', 'inport:delete', NULL, NULL, NULL, 0, 93, 1);
INSERT INTO `sys_permission` VALUES (106, 10, 'permission', '进货退货', 'inport:outport', NULL, NULL, NULL, 0, 94, 1);
INSERT INTO `sys_permission` VALUES (107, 94, 'permission', '业务新增', 'proofread:create', NULL, NULL, NULL, 0, 95, 1);
INSERT INTO `sys_permission` VALUES (108, 94, 'permission', '业务修改', 'proofread:update', NULL, NULL, NULL, 0, 96, 1);
INSERT INTO `sys_permission` VALUES (109, 94, 'permission', '业务审核', 'proofread:audit', NULL, NULL, NULL, 0, 97, 1);
INSERT INTO `sys_permission` VALUES (110, 94, 'permission', '业务删除', 'proofread:delete', NULL, NULL, NULL, 0, 98, 1);
INSERT INTO `sys_permission` VALUES (111, 94, 'permission', '业务查看', 'proofread:view', NULL, NULL, NULL, 0, 99, 1);
INSERT INTO `sys_permission` VALUES (112, 94, 'permission', '业务详情查看', 'proofread:viewproofread', NULL, NULL, NULL, 0, 100, 1);
INSERT INTO `sys_permission` VALUES (113, 1, 'menu', '网盘管理', NULL, '&#xe681;', '', '', 0, 101, 1);
INSERT INTO `sys_permission` VALUES (114, 113, 'menu', '分配公司', NULL, '&#xe609;', '/bus/toFileDispatcher', '', 1, 102, 1);
INSERT INTO `sys_permission` VALUES (115, 113, 'menu', '文件管理', NULL, '&#xe655;', '/bus/toFileMain', '', 0, 103, 1);
INSERT INTO `sys_permission` VALUES (116, 113, 'menu', '回收站', NULL, '&#xe666;', '/bus/toFileRecycle', '', 0, 104, 1);
INSERT INTO `sys_permission` VALUES (117, 115, 'permission', '重命名公司', 'company:rename', NULL, NULL, NULL, 0, 105, 1);
INSERT INTO `sys_permission` VALUES (118, 115, 'permission', '创建公司', 'company:create', NULL, NULL, NULL, 0, 106, 1);
INSERT INTO `sys_permission` VALUES (119, 115, 'permission', '下载备份', 'company:backup', NULL, NULL, NULL, 0, 107, 1);
INSERT INTO `sys_permission` VALUES (120, 115, 'permission', '创建文件', 'folder:create', NULL, NULL, NULL, 0, 108, 1);
INSERT INTO `sys_permission` VALUES (121, 115, 'permission', '修改删除文件夹', 'folder:update', NULL, NULL, NULL, 0, 109, 1);
INSERT INTO `sys_permission` VALUES (122, 6, 'menu', '操作日志', NULL, '&#xe6b2;', '/sys/toOperatelog', '', 0, 110, 1);
INSERT INTO `sys_permission` VALUES (123, 4, 'menu', '催账管理', NULL, '&#xe6b2;', '/bus/toDebt', '', 1, 111, 1);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `available` int(11) DEFAULT NULL,
  `createtime` datetime(0) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, '超级管理员', '拥有所有菜单权限', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (4, '基础数据管理员', '基础数据管理员', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (5, '仓库管理员', '仓库管理员', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (6, '销售管理员', '销售管理员', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (7, '系统管理员', '系统管理员', 1, '2019-04-10 14:06:32');
INSERT INTO `sys_role` VALUES (8, '1234', '1234123412341234123412', 0, '2020-04-21 23:54:26');
INSERT INTO `sys_role` VALUES (9, '123', '123123', 1, '2020-04-22 10:14:52');
INSERT INTO `sys_role` VALUES (10, '客户', '客户只允许看自己公司的资料', 1, '2020-05-08 10:08:50');
INSERT INTO `sys_role` VALUES (11, '普通员工', '普通员工', 1, '2020-05-09 08:56:02');
INSERT INTO `sys_role` VALUES (12, '门店经理', '门店经理', 1, '2020-05-09 08:57:42');
INSERT INTO `sys_role` VALUES (13, '财务审核', '审核财务账单', 1, '2020-05-10 20:00:22');

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `rid` int(11) NOT NULL,
  `pid` int(11) NOT NULL,
  PRIMARY KEY (`pid`, `rid`) USING BTREE,
  INDEX `FK_tcsr9ucxypb3ce1q5qngsfk33`(`rid`) USING BTREE,
  CONSTRAINT `sys_role_permission_ibfk_1` FOREIGN KEY (`pid`) REFERENCES `sys_permission` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_permission_ibfk_2` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE CASCADE ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (1, 1);
INSERT INTO `sys_role_permission` VALUES (1, 2);
INSERT INTO `sys_role_permission` VALUES (1, 3);
INSERT INTO `sys_role_permission` VALUES (1, 4);
INSERT INTO `sys_role_permission` VALUES (1, 5);
INSERT INTO `sys_role_permission` VALUES (1, 6);
INSERT INTO `sys_role_permission` VALUES (1, 7);
INSERT INTO `sys_role_permission` VALUES (1, 8);
INSERT INTO `sys_role_permission` VALUES (1, 9);
INSERT INTO `sys_role_permission` VALUES (1, 10);
INSERT INTO `sys_role_permission` VALUES (1, 11);
INSERT INTO `sys_role_permission` VALUES (1, 14);
INSERT INTO `sys_role_permission` VALUES (1, 15);
INSERT INTO `sys_role_permission` VALUES (1, 16);
INSERT INTO `sys_role_permission` VALUES (1, 17);
INSERT INTO `sys_role_permission` VALUES (1, 18);
INSERT INTO `sys_role_permission` VALUES (1, 21);
INSERT INTO `sys_role_permission` VALUES (1, 22);
INSERT INTO `sys_role_permission` VALUES (1, 23);
INSERT INTO `sys_role_permission` VALUES (1, 30);
INSERT INTO `sys_role_permission` VALUES (1, 31);
INSERT INTO `sys_role_permission` VALUES (1, 32);
INSERT INTO `sys_role_permission` VALUES (1, 34);
INSERT INTO `sys_role_permission` VALUES (1, 35);
INSERT INTO `sys_role_permission` VALUES (1, 36);
INSERT INTO `sys_role_permission` VALUES (1, 38);
INSERT INTO `sys_role_permission` VALUES (1, 39);
INSERT INTO `sys_role_permission` VALUES (1, 40);
INSERT INTO `sys_role_permission` VALUES (1, 42);
INSERT INTO `sys_role_permission` VALUES (1, 43);
INSERT INTO `sys_role_permission` VALUES (1, 44);
INSERT INTO `sys_role_permission` VALUES (1, 46);
INSERT INTO `sys_role_permission` VALUES (1, 47);
INSERT INTO `sys_role_permission` VALUES (1, 48);
INSERT INTO `sys_role_permission` VALUES (1, 49);
INSERT INTO `sys_role_permission` VALUES (1, 51);
INSERT INTO `sys_role_permission` VALUES (1, 52);
INSERT INTO `sys_role_permission` VALUES (1, 53);
INSERT INTO `sys_role_permission` VALUES (1, 54);
INSERT INTO `sys_role_permission` VALUES (1, 55);
INSERT INTO `sys_role_permission` VALUES (1, 56);
INSERT INTO `sys_role_permission` VALUES (1, 57);
INSERT INTO `sys_role_permission` VALUES (1, 68);
INSERT INTO `sys_role_permission` VALUES (1, 69);
INSERT INTO `sys_role_permission` VALUES (1, 70);
INSERT INTO `sys_role_permission` VALUES (1, 71);
INSERT INTO `sys_role_permission` VALUES (1, 73);
INSERT INTO `sys_role_permission` VALUES (1, 74);
INSERT INTO `sys_role_permission` VALUES (1, 75);
INSERT INTO `sys_role_permission` VALUES (1, 76);
INSERT INTO `sys_role_permission` VALUES (1, 77);
INSERT INTO `sys_role_permission` VALUES (1, 78);
INSERT INTO `sys_role_permission` VALUES (1, 79);
INSERT INTO `sys_role_permission` VALUES (1, 81);
INSERT INTO `sys_role_permission` VALUES (1, 82);
INSERT INTO `sys_role_permission` VALUES (1, 83);
INSERT INTO `sys_role_permission` VALUES (1, 84);
INSERT INTO `sys_role_permission` VALUES (1, 86);
INSERT INTO `sys_role_permission` VALUES (1, 91);
INSERT INTO `sys_role_permission` VALUES (1, 92);
INSERT INTO `sys_role_permission` VALUES (1, 93);
INSERT INTO `sys_role_permission` VALUES (1, 94);
INSERT INTO `sys_role_permission` VALUES (1, 95);
INSERT INTO `sys_role_permission` VALUES (1, 96);
INSERT INTO `sys_role_permission` VALUES (1, 97);
INSERT INTO `sys_role_permission` VALUES (1, 98);
INSERT INTO `sys_role_permission` VALUES (1, 99);
INSERT INTO `sys_role_permission` VALUES (1, 107);
INSERT INTO `sys_role_permission` VALUES (1, 108);
INSERT INTO `sys_role_permission` VALUES (1, 109);
INSERT INTO `sys_role_permission` VALUES (1, 110);
INSERT INTO `sys_role_permission` VALUES (1, 111);
INSERT INTO `sys_role_permission` VALUES (1, 112);
INSERT INTO `sys_role_permission` VALUES (1, 113);
INSERT INTO `sys_role_permission` VALUES (1, 114);
INSERT INTO `sys_role_permission` VALUES (1, 115);
INSERT INTO `sys_role_permission` VALUES (1, 116);
INSERT INTO `sys_role_permission` VALUES (1, 117);
INSERT INTO `sys_role_permission` VALUES (1, 118);
INSERT INTO `sys_role_permission` VALUES (1, 119);
INSERT INTO `sys_role_permission` VALUES (1, 120);
INSERT INTO `sys_role_permission` VALUES (1, 121);
INSERT INTO `sys_role_permission` VALUES (1, 122);
INSERT INTO `sys_role_permission` VALUES (4, 1);
INSERT INTO `sys_role_permission` VALUES (4, 2);
INSERT INTO `sys_role_permission` VALUES (4, 5);
INSERT INTO `sys_role_permission` VALUES (4, 7);
INSERT INTO `sys_role_permission` VALUES (4, 8);
INSERT INTO `sys_role_permission` VALUES (4, 9);
INSERT INTO `sys_role_permission` VALUES (4, 14);
INSERT INTO `sys_role_permission` VALUES (4, 15);
INSERT INTO `sys_role_permission` VALUES (4, 16);
INSERT INTO `sys_role_permission` VALUES (4, 17);
INSERT INTO `sys_role_permission` VALUES (4, 18);
INSERT INTO `sys_role_permission` VALUES (4, 30);
INSERT INTO `sys_role_permission` VALUES (4, 31);
INSERT INTO `sys_role_permission` VALUES (4, 32);
INSERT INTO `sys_role_permission` VALUES (4, 34);
INSERT INTO `sys_role_permission` VALUES (4, 35);
INSERT INTO `sys_role_permission` VALUES (4, 36);
INSERT INTO `sys_role_permission` VALUES (4, 38);
INSERT INTO `sys_role_permission` VALUES (4, 39);
INSERT INTO `sys_role_permission` VALUES (4, 40);
INSERT INTO `sys_role_permission` VALUES (4, 42);
INSERT INTO `sys_role_permission` VALUES (4, 43);
INSERT INTO `sys_role_permission` VALUES (4, 44);
INSERT INTO `sys_role_permission` VALUES (4, 46);
INSERT INTO `sys_role_permission` VALUES (4, 47);
INSERT INTO `sys_role_permission` VALUES (4, 48);
INSERT INTO `sys_role_permission` VALUES (4, 49);
INSERT INTO `sys_role_permission` VALUES (4, 51);
INSERT INTO `sys_role_permission` VALUES (4, 52);
INSERT INTO `sys_role_permission` VALUES (4, 53);
INSERT INTO `sys_role_permission` VALUES (4, 54);
INSERT INTO `sys_role_permission` VALUES (4, 55);
INSERT INTO `sys_role_permission` VALUES (4, 56);
INSERT INTO `sys_role_permission` VALUES (4, 57);
INSERT INTO `sys_role_permission` VALUES (4, 68);
INSERT INTO `sys_role_permission` VALUES (4, 69);
INSERT INTO `sys_role_permission` VALUES (4, 70);
INSERT INTO `sys_role_permission` VALUES (4, 71);
INSERT INTO `sys_role_permission` VALUES (4, 81);
INSERT INTO `sys_role_permission` VALUES (4, 82);
INSERT INTO `sys_role_permission` VALUES (4, 83);
INSERT INTO `sys_role_permission` VALUES (4, 84);
INSERT INTO `sys_role_permission` VALUES (4, 91);
INSERT INTO `sys_role_permission` VALUES (4, 92);
INSERT INTO `sys_role_permission` VALUES (5, 1);
INSERT INTO `sys_role_permission` VALUES (5, 3);
INSERT INTO `sys_role_permission` VALUES (5, 4);
INSERT INTO `sys_role_permission` VALUES (5, 5);
INSERT INTO `sys_role_permission` VALUES (5, 10);
INSERT INTO `sys_role_permission` VALUES (5, 11);
INSERT INTO `sys_role_permission` VALUES (5, 12);
INSERT INTO `sys_role_permission` VALUES (5, 13);
INSERT INTO `sys_role_permission` VALUES (5, 14);
INSERT INTO `sys_role_permission` VALUES (5, 31);
INSERT INTO `sys_role_permission` VALUES (5, 32);
INSERT INTO `sys_role_permission` VALUES (5, 53);
INSERT INTO `sys_role_permission` VALUES (6, 1);
INSERT INTO `sys_role_permission` VALUES (6, 4);
INSERT INTO `sys_role_permission` VALUES (6, 12);
INSERT INTO `sys_role_permission` VALUES (6, 13);
INSERT INTO `sys_role_permission` VALUES (7, 1);
INSERT INTO `sys_role_permission` VALUES (7, 5);
INSERT INTO `sys_role_permission` VALUES (7, 6);
INSERT INTO `sys_role_permission` VALUES (7, 14);
INSERT INTO `sys_role_permission` VALUES (7, 15);
INSERT INTO `sys_role_permission` VALUES (7, 16);
INSERT INTO `sys_role_permission` VALUES (7, 17);
INSERT INTO `sys_role_permission` VALUES (7, 18);
INSERT INTO `sys_role_permission` VALUES (7, 21);
INSERT INTO `sys_role_permission` VALUES (7, 22);
INSERT INTO `sys_role_permission` VALUES (7, 23);
INSERT INTO `sys_role_permission` VALUES (7, 30);
INSERT INTO `sys_role_permission` VALUES (7, 31);
INSERT INTO `sys_role_permission` VALUES (7, 32);
INSERT INTO `sys_role_permission` VALUES (7, 34);
INSERT INTO `sys_role_permission` VALUES (7, 35);
INSERT INTO `sys_role_permission` VALUES (7, 36);
INSERT INTO `sys_role_permission` VALUES (7, 38);
INSERT INTO `sys_role_permission` VALUES (7, 39);
INSERT INTO `sys_role_permission` VALUES (7, 40);
INSERT INTO `sys_role_permission` VALUES (7, 42);
INSERT INTO `sys_role_permission` VALUES (7, 43);
INSERT INTO `sys_role_permission` VALUES (7, 44);
INSERT INTO `sys_role_permission` VALUES (7, 46);
INSERT INTO `sys_role_permission` VALUES (7, 47);
INSERT INTO `sys_role_permission` VALUES (7, 48);
INSERT INTO `sys_role_permission` VALUES (7, 49);
INSERT INTO `sys_role_permission` VALUES (7, 51);
INSERT INTO `sys_role_permission` VALUES (7, 52);
INSERT INTO `sys_role_permission` VALUES (7, 53);
INSERT INTO `sys_role_permission` VALUES (7, 54);
INSERT INTO `sys_role_permission` VALUES (7, 55);
INSERT INTO `sys_role_permission` VALUES (7, 56);
INSERT INTO `sys_role_permission` VALUES (7, 57);
INSERT INTO `sys_role_permission` VALUES (7, 73);
INSERT INTO `sys_role_permission` VALUES (7, 74);
INSERT INTO `sys_role_permission` VALUES (7, 75);
INSERT INTO `sys_role_permission` VALUES (7, 76);
INSERT INTO `sys_role_permission` VALUES (7, 77);
INSERT INTO `sys_role_permission` VALUES (7, 78);
INSERT INTO `sys_role_permission` VALUES (7, 79);
INSERT INTO `sys_role_permission` VALUES (7, 86);
INSERT INTO `sys_role_permission` VALUES (9, 1);
INSERT INTO `sys_role_permission` VALUES (9, 2);
INSERT INTO `sys_role_permission` VALUES (9, 9);
INSERT INTO `sys_role_permission` VALUES (9, 91);
INSERT INTO `sys_role_permission` VALUES (9, 92);
INSERT INTO `sys_role_permission` VALUES (10, 1);
INSERT INTO `sys_role_permission` VALUES (10, 113);
INSERT INTO `sys_role_permission` VALUES (10, 115);
INSERT INTO `sys_role_permission` VALUES (11, 1);
INSERT INTO `sys_role_permission` VALUES (11, 2);
INSERT INTO `sys_role_permission` VALUES (11, 4);
INSERT INTO `sys_role_permission` VALUES (11, 7);
INSERT INTO `sys_role_permission` VALUES (11, 68);
INSERT INTO `sys_role_permission` VALUES (11, 69);
INSERT INTO `sys_role_permission` VALUES (11, 70);
INSERT INTO `sys_role_permission` VALUES (11, 71);
INSERT INTO `sys_role_permission` VALUES (11, 94);
INSERT INTO `sys_role_permission` VALUES (11, 107);
INSERT INTO `sys_role_permission` VALUES (11, 108);
INSERT INTO `sys_role_permission` VALUES (11, 111);
INSERT INTO `sys_role_permission` VALUES (11, 113);
INSERT INTO `sys_role_permission` VALUES (11, 114);
INSERT INTO `sys_role_permission` VALUES (11, 115);
INSERT INTO `sys_role_permission` VALUES (11, 117);
INSERT INTO `sys_role_permission` VALUES (11, 118);
INSERT INTO `sys_role_permission` VALUES (11, 120);
INSERT INTO `sys_role_permission` VALUES (11, 121);
INSERT INTO `sys_role_permission` VALUES (12, 1);
INSERT INTO `sys_role_permission` VALUES (12, 2);
INSERT INTO `sys_role_permission` VALUES (12, 4);
INSERT INTO `sys_role_permission` VALUES (12, 7);
INSERT INTO `sys_role_permission` VALUES (12, 68);
INSERT INTO `sys_role_permission` VALUES (12, 69);
INSERT INTO `sys_role_permission` VALUES (12, 70);
INSERT INTO `sys_role_permission` VALUES (12, 71);
INSERT INTO `sys_role_permission` VALUES (12, 95);
INSERT INTO `sys_role_permission` VALUES (12, 113);
INSERT INTO `sys_role_permission` VALUES (12, 114);
INSERT INTO `sys_role_permission` VALUES (12, 115);
INSERT INTO `sys_role_permission` VALUES (12, 116);
INSERT INTO `sys_role_permission` VALUES (12, 117);
INSERT INTO `sys_role_permission` VALUES (12, 118);
INSERT INTO `sys_role_permission` VALUES (12, 120);
INSERT INTO `sys_role_permission` VALUES (12, 121);
INSERT INTO `sys_role_permission` VALUES (13, 1);
INSERT INTO `sys_role_permission` VALUES (13, 4);
INSERT INTO `sys_role_permission` VALUES (13, 94);
INSERT INTO `sys_role_permission` VALUES (13, 109);
INSERT INTO `sys_role_permission` VALUES (13, 111);

-- ----------------------------
-- Table structure for sys_role_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_user`;
CREATE TABLE `sys_role_user`  (
  `rid` int(11) NOT NULL,
  `uid` int(11) NOT NULL,
  PRIMARY KEY (`uid`, `rid`) USING BTREE,
  INDEX `FK_203gdpkwgtow7nxlo2oap5jru`(`rid`) USING BTREE,
  CONSTRAINT `sys_role_user_ibfk_1` FOREIGN KEY (`rid`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_user_ibfk_2` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES (1, 2);
INSERT INTO `sys_role_user` VALUES (4, 3);
INSERT INTO `sys_role_user` VALUES (5, 4);
INSERT INTO `sys_role_user` VALUES (5, 6);
INSERT INTO `sys_role_user` VALUES (6, 5);
INSERT INTO `sys_role_user` VALUES (11, 12);
INSERT INTO `sys_role_user` VALUES (12, 10);
INSERT INTO `sys_role_user` VALUES (12, 14);
INSERT INTO `sys_role_user` VALUES (13, 13);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `loginname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `deptid` int(11) DEFAULT NULL,
  `hiredate` datetime(0) DEFAULT NULL,
  `mgr` int(11) DEFAULT NULL,
  `available` int(11) DEFAULT 1,
  `ordernum` int(11) DEFAULT NULL,
  `type` int(255) DEFAULT NULL COMMENT '用户类型[0超级管理员1，管理员，2普通用户]',
  `imgpath` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '头像地址',
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `customerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `FK_3rrcpvho2w1mx1sfiuuyir1h`(`deptid`) USING BTREE,
  CONSTRAINT `sys_user_ibfk_1` FOREIGN KEY (`deptid`) REFERENCES `sys_dept` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, '超级管理员', 'system', '系统深处的男人', 1, '超级管理员', 'b07b848d69e0553b80e601d31571797e', 1, '2018-06-25 11:06:34', NULL, 1, 1, 0, '2020-05-11/EF5B85CF6B3B479582B796C146BA3DFE.jpg', 'FC1EE06AE4354D3FBF7FDD15C8FCDA71', 0);
INSERT INTO `sys_user` VALUES (2, '李四', 'ls', '武汉', 0, 'KING', 'b07b848d69e0553b80e601d31571797e', 1, '2018-06-25 11:06:36', NULL, 1, 2, 1, 'img/head.jpg', 'FC1EE06AE4354D3FBF7FDD15C8FCDA71', 0);
INSERT INTO `sys_user` VALUES (3, '王五', 'ww', '武汉', 1, '管理员', '3c3f971eae61e097f59d52360323f1c8', 3, '2018-06-25 11:06:38', 2, 1, 3, 1, 'img/head.jpg', '3D5F956E053C4E85B7D2681386E235D2', 0);
INSERT INTO `sys_user` VALUES (4, '赵六', 'zl', '武汉', 1, '程序员', '2e969742a7ea0c7376e9551d578e05dd', 4, '2018-06-25 11:06:40', 3, 1, 4, 1, 'img/head.jpg', '6480EE1391E34B0886ACADA501E31145', 0);
INSERT INTO `sys_user` VALUES (5, '孙七', 'sq', '武汉', 1, '程序员', '47b4c1ad6e4b54dd9387a09cb5a03de1', 2, '2018-06-25 11:06:43', 4, 1, 5, 1, 'img/head.jpg', 'FE3476C3E3674E5690C737C269FCBF8E', NULL);
INSERT INTO `sys_user` VALUES (6, '刘八', 'lb', '深圳', 1, '程序员', 'e21875b0cc8e48cb56e999d32197a93f', 4, '2018-08-06 11:21:12', 3, 1, 6, 1, 'img/head.jpg', '741DB50D7EA141828679D2DFB9D12134', NULL);
INSERT INTO `sys_user` VALUES (10, '小彬彬', 'xiaobinbin', '1231234', 1, '12341234', '10de2646bb522d2f1ea9f8752f93fd43', 4, '2020-04-23 14:16:14', 4, 1, 7, 1, 'img/head.jpg', 'C78657B849D749FB8304E68CB76180D6', 3);
INSERT INTO `sys_user` VALUES (12, '销售', 'xiaoshou', '武汉', 1, '测试用的业务员', '7f17064e13a19bdb5ee0dcc30752ca44', 5, '2020-05-10 23:13:52', 14, 1, 8, 1, 'img/head.jpg', '08EE7E6B29E443A884377FC641AA00A1', NULL);
INSERT INTO `sys_user` VALUES (13, '财务', 'caiwu', '123123', 1, '财务测试一号', 'c794481d48bb0202329e28179ccfd8b2', 3, '2020-05-10 23:14:51', 2, 1, 9, 1, 'img/head.jpg', '231E5EF8BEF34F7EAC9E634756E9C2D9', NULL);
INSERT INTO `sys_user` VALUES (14, '经理', 'jingli', '2134', 1, '测试用的经理1号', '69d7ad46a9a12fb8c63793c0831715cb', 5, '2020-05-10 23:15:33', 0, 1, 10, 1, 'img/head.jpg', '6C7C8975911A4F7B9BDDC390EA3D4A64', NULL);
INSERT INTO `sys_user` VALUES (17, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL, 1, NULL, NULL, '', NULL, NULL);

SET FOREIGN_KEY_CHECKS = 1;
