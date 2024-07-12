/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50546
Source Host           : localhost:3306
Source Database       : restaurantsys

Target Server Type    : MYSQL
Target Server Version : 50546
File Encoding         : 65001

Date: 2024-07-12 16:33:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for attendance
-- ----------------------------
DROP TABLE IF EXISTS `attendance`;
CREATE TABLE `attendance` (
  `a_id` varchar(64) NOT NULL,
  `a_type` int(1) DEFAULT NULL,
  `a_date` datetime DEFAULT NULL,
  `a_userid` varchar(64) DEFAULT NULL,
  `a_start` datetime DEFAULT NULL,
  `a_finish` datetime DEFAULT NULL,
  `a_isdel` int(1) DEFAULT NULL,
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of attendance
-- ----------------------------
INSERT INTO `attendance` VALUES ('057214f63bcd4116919d5b360ce30285', '0', '2023-01-03 16:21:58', '7a3390d7e1ab417b9ad156eb5605a06b', '2023-01-03 10:50:28', '2023-01-03 16:21:58', '0');
INSERT INTO `attendance` VALUES ('06a0c2ed00ce4fc98d98108d0645016b', '0', '2023-02-04 16:21:23', null, '2023-02-04 16:21:23', null, '0');
INSERT INTO `attendance` VALUES ('0c4bdd5f8452417093f7d859aa2e962d', '0', '2023-02-04 16:23:45', null, '2023-02-04 16:23:45', null, '0');
INSERT INTO `attendance` VALUES ('1', '0', '2022-11-25 22:15:21', '1', '2022-11-25 22:02:25', '2022-11-25 22:15:21', '1');
INSERT INTO `attendance` VALUES ('27c1db934cf44606833e5d28816cc6af', '1', '2022-12-28 21:03:29', '7a3390d7e1ab417b9ad156eb5605a06b', null, null, '1');
INSERT INTO `attendance` VALUES ('3', '0', '2022-11-25 22:18:11', '2', '2022-11-25 22:08:10', '2022-11-25 22:18:11', '0');
INSERT INTO `attendance` VALUES ('35329ea3672a49afbac7a29b48845e29', '0', '2023-02-04 16:22:20', null, '2023-02-04 16:22:20', null, '0');
INSERT INTO `attendance` VALUES ('3887051f2fdd4a2083526a86ebfb2f45', '1', '2022-12-12 19:22:08', '11', null, null, '0');
INSERT INTO `attendance` VALUES ('3df4f8aad1cc49edacc3cda36d6d5317', '0', '2023-02-04 16:18:33', null, '2023-02-04 16:18:33', null, '0');
INSERT INTO `attendance` VALUES ('5', '1', '2022-11-25 22:18:05', '2', null, null, '0');
INSERT INTO `attendance` VALUES ('5309510de1414a4c9836fb2ae1c39609', '1', '2022-12-29 17:06:34', '7a3390d7e1ab417b9ad156eb5605a06b', null, null, '1');
INSERT INTO `attendance` VALUES ('6', '0', '2022-11-25 20:15:28', '1', '2022-11-25 20:15:28', null, '0');
INSERT INTO `attendance` VALUES ('6bcfeeaf037b4e74bc8648f8963e2f41', '0', '2022-12-28 17:54:27', '7a3390d7e1ab417b9ad156eb5605a06b', '2022-12-28 17:54:27', null, '1');
INSERT INTO `attendance` VALUES ('7', '1', '2022-11-25 20:49:08', '9', null, null, '0');
INSERT INTO `attendance` VALUES ('71720fb1866a4552bac6906aef39e46d', '1', '2022-12-28 21:13:06', '7a3390d7e1ab417b9ad156eb5605a06b', null, null, '1');
INSERT INTO `attendance` VALUES ('7fec09b6be2b47808bcb37571dd0a963', '0', '2022-12-28 17:05:24', '7a3390d7e1ab417b9ad156eb5605a06b', '2022-12-28 17:05:24', null, '1');
INSERT INTO `attendance` VALUES ('8', '0', '2022-11-25 20:49:10', '9', null, '2022-11-25 20:49:10', '0');
INSERT INTO `attendance` VALUES ('9b893f44c3a34d52933e14f85d671331', '0', '2023-01-16 18:33:35', '7a3390d7e1ab417b9ad156eb5605a06b', '2023-01-16 18:33:35', null, '0');
INSERT INTO `attendance` VALUES ('9bf4513a4fc848fabc5c5b131f5da12c', '0', '2023-02-04 16:25:08', '12', '2023-02-04 16:25:08', null, '0');
INSERT INTO `attendance` VALUES ('a1ed1fbb49e447c488a0f5dab3111b03', '0', '2022-12-28 17:04:46', '7a3390d7e1ab417b9ad156eb5605a06b', '2022-12-28 17:04:46', null, '1');
INSERT INTO `attendance` VALUES ('c4b9ea4bf96e48c2a3e62e4834f75f09', '0', '2022-12-29 17:16:06', '7a3390d7e1ab417b9ad156eb5605a06b', '2022-12-29 17:16:06', '2022-12-29 17:16:27', '1');
INSERT INTO `attendance` VALUES ('c9e0cc60644c4f28a303958efc096483', '0', '2023-02-22 19:32:49', '7a3390d7e1ab417b9ad156eb5605a06b', '2023-02-22 19:32:49', null, '0');
INSERT INTO `attendance` VALUES ('e13c2e1622a54ea3ad59aa49efca22f4', '0', '2023-02-04 16:22:14', null, '2023-02-04 16:22:14', null, '0');
INSERT INTO `attendance` VALUES ('ea37042021c94a009b9f0dbdc99ed214', '0', '2022-12-28 20:58:11', '7a3390d7e1ab417b9ad156eb5605a06b', '2022-12-28 20:58:11', null, '1');
INSERT INTO `attendance` VALUES ('f73ece0d2db34e019e31fbcdb9c92598', '1', '2022-12-28 21:18:22', '7a3390d7e1ab417b9ad156eb5605a06b', null, null, '0');

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee` (
  `empid` varchar(64) NOT NULL,
  `loginname` varchar(255) DEFAULT NULL,
  `pwd` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `position` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `isdel` int(11) DEFAULT NULL,
  PRIMARY KEY (`empid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES ('10', '2', 'e10adc3949ba59abbe56e057f20f883e', '2', '0', '2', '1');
INSERT INTO `employee` VALUES ('11', 'waiter2', 'e10adc3949ba59abbe56e057f20f883e', '服务员2', '1', '54321', '1');
INSERT INTO `employee` VALUES ('12', 'chef', 'e10adc3949ba59abbe56e057f20f883e', '后厨', '2', '12345', '0');
INSERT INTO `employee` VALUES ('13', '111', 'e10adc3949ba59abbe56e057f20f883e', '111', '0', '111123', '1');
INSERT INTO `employee` VALUES ('14', 'zc', 'e10adc3949ba59abbe56e057f20f883e', 'zc', '0', '1234', '1');
INSERT INTO `employee` VALUES ('17f5b47a852211ed838100ff81d16d42', '1', 'e10adc3949ba59abbe56e057f20f883e', '22', '0', '10101010107', '1');
INSERT INTO `employee` VALUES ('2', 'zc', 'e10adc3949ba59abbe56e057f20f883e', 'zc', '2', null, '1');
INSERT INTO `employee` VALUES ('4', 'client', 'e10adc3949ba59abbe56e057f20f883e', '中文', '1', '1', '1');
INSERT INTO `employee` VALUES ('5', 'client01', 'e10adc3949ba59abbe56e057f20f883e', 'client01', '1', '13011111111', '1');
INSERT INTO `employee` VALUES ('6', 'client02', 'e10adc3949ba59abbe56e057f20f883e', 'client02', '1', '13000000000', '1');
INSERT INTO `employee` VALUES ('7', '1', 'e10adc3949ba59abbe56e057f20f883e', '姓名', '1', '12345678', '1');
INSERT INTO `employee` VALUES ('7a3390d7e1ab417b9ad156eb5605a06b', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin', '0', null, '0');
INSERT INTO `employee` VALUES ('8', 'adminb', 'e10adc3949ba59abbe56e057f20f883e', 'admin', '0', '123456', '1');
INSERT INTO `employee` VALUES ('9', 'waiter1', 'e10adc3949ba59abbe56e057f20f883e', '服务员1', '1', '123456789', '0');
INSERT INTO `employee` VALUES ('9b50e2638b3f11ed8d8802004c4f4f50', 'chef1', 'e10adc3949ba59abbe56e057f20f883e', 'chef1', '2', '123', '0');
INSERT INTO `employee` VALUES ('c2db015a851e11ed838100ff81d16d42', 'test3', 'e10adc3949ba59abbe56e057f20f883e', 'test3', '1', '987654321', '0');

-- ----------------------------
-- Table structure for md_relation
-- ----------------------------
DROP TABLE IF EXISTS `md_relation`;
CREATE TABLE `md_relation` (
  `mdid` varchar(64) NOT NULL,
  `orderid` varchar(255) DEFAULT NULL,
  `foodid` varchar(255) DEFAULT NULL,
  `md_amount` int(255) DEFAULT NULL,
  `md_price` double(10,2) DEFAULT NULL,
  `md_status` int(1) DEFAULT NULL,
  `isdel` int(1) DEFAULT NULL,
  PRIMARY KEY (`mdid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of md_relation
-- ----------------------------
INSERT INTO `md_relation` VALUES ('21', '11', '2', '1', '10.00', '1', '1');
INSERT INTO `md_relation` VALUES ('22', '11', '1', '1', '28.00', '1', '1');
INSERT INTO `md_relation` VALUES ('23', '12', '2', '1', '10.00', '1', '0');
INSERT INTO `md_relation` VALUES ('24', '12', '1', '1', '28.00', '1', '0');
INSERT INTO `md_relation` VALUES ('25', '13', '2', '2', '20.00', '1', '0');
INSERT INTO `md_relation` VALUES ('26', '13', '1', '1', '28.00', '1', '0');
INSERT INTO `md_relation` VALUES ('27', '14', '42', '2', '56.00', '1', '0');
INSERT INTO `md_relation` VALUES ('28', '14', '40', '1', '38.00', '1', '0');
INSERT INTO `md_relation` VALUES ('29', '14', '38', '1', '108.00', '1', '0');
INSERT INTO `md_relation` VALUES ('30', '15', '41', '2', '576.00', '1', '0');
INSERT INTO `md_relation` VALUES ('31', '16', '37', '2', '176.00', '1', '0');
INSERT INTO `md_relation` VALUES ('32', '16', '36', '1', '58.00', '1', '0');
INSERT INTO `md_relation` VALUES ('33', '16', '42', '2', '56.00', '1', '0');
INSERT INTO `md_relation` VALUES ('34', '16', '40', '1', '38.00', '1', '0');
INSERT INTO `md_relation` VALUES ('35', '17', '37', '1', '88.00', '1', '1');
INSERT INTO `md_relation` VALUES ('36', '17', '36', '1', '58.00', '1', '1');
INSERT INTO `md_relation` VALUES ('37', '18', '36', '1', '58.00', '0', '1');
INSERT INTO `md_relation` VALUES ('38', '18', '37', '1', '88.00', '0', '1');
INSERT INTO `md_relation` VALUES ('39', '19', '37', '1', '88.00', '1', '0');
INSERT INTO `md_relation` VALUES ('40', '19', '36', '1', '58.00', '1', '0');
INSERT INTO `md_relation` VALUES ('41', '19', '38', '1', '108.00', '1', '0');
INSERT INTO `md_relation` VALUES ('42', '20', '37', '1', '88.00', '1', '0');
INSERT INTO `md_relation` VALUES ('43', '20', '36', '1', '58.00', '1', '0');
INSERT INTO `md_relation` VALUES ('44', '21', '37', '1', '88.00', '1', '0');
INSERT INTO `md_relation` VALUES ('45', '22', '37', '1', '88.00', '1', '0');
INSERT INTO `md_relation` VALUES ('46', '22', '38', '1', '108.00', '1', '0');
INSERT INTO `md_relation` VALUES ('47', '22', '36', '1', '58.00', '0', '0');
INSERT INTO `md_relation` VALUES ('48', '16', '38', '1', '108.00', '0', '0');
INSERT INTO `md_relation` VALUES ('49', '23', '38', '2', '216.00', '0', '0');
INSERT INTO `md_relation` VALUES ('50', '23', '37', '1', '88.00', '0', '0');
INSERT INTO `md_relation` VALUES ('51', '0', '37', '1', '88.00', '0', '0');
INSERT INTO `md_relation` VALUES ('52', '0', '36', '1', '58.00', '0', '0');
INSERT INTO `md_relation` VALUES ('53', '25', '37', '1', '88.00', '0', '1');
INSERT INTO `md_relation` VALUES ('54', '25', '36', '1', '58.00', '0', '1');
INSERT INTO `md_relation` VALUES ('55', '26', '37', '1', '88.00', '0', '1');
INSERT INTO `md_relation` VALUES ('56', '26', '36', '1', '58.00', '0', '1');
INSERT INTO `md_relation` VALUES ('57', '27', '37', '1', '88.00', '1', '1');
INSERT INTO `md_relation` VALUES ('58', '27', '36', '1', '58.00', '1', '1');
INSERT INTO `md_relation` VALUES ('59', '28', '37', '1', '88.00', '1', '0');
INSERT INTO `md_relation` VALUES ('60', '28', '36', '1', '58.00', '1', '0');
INSERT INTO `md_relation` VALUES ('61', '28', '38', '1', '108.00', '0', '0');
INSERT INTO `md_relation` VALUES ('63', '29', '36', '1', '58.00', '0', '0');
INSERT INTO `md_relation` VALUES ('64', '30', '38', '1', '108.00', '0', '1');
INSERT INTO `md_relation` VALUES ('65', '30', '37', '1', '88.00', '0', '1');
INSERT INTO `md_relation` VALUES ('66', '30', '36', '1', '48.00', '0', '1');
INSERT INTO `md_relation` VALUES ('67', 'e02aa47b966911ed856902004c4f4f51', '42', '1', '28.00', '1', '0');
INSERT INTO `md_relation` VALUES ('68', 'e02aa47b966911ed856902004c4f4f51', '40', '1', '38.00', '0', '0');
INSERT INTO `md_relation` VALUES ('69', 'e02aa47b966911ed856902004c4f4f51', '39', '1', '25.00', '0', '0');
INSERT INTO `md_relation` VALUES ('f11fcaf7b2a411eda03a00ff71bb3dd6', '097505d3fe1f4141886da3e3e4bf0b77', '38', '1', '1080.00', '0', '0');
INSERT INTO `md_relation` VALUES ('f11fcdddb2a411eda03a00ff71bb3dd6', '097505d3fe1f4141886da3e3e4bf0b77', 'ac483a9d96d711ed856902004c4f4f50', '1', '2.00', '0', '0');
INSERT INTO `md_relation` VALUES ('f11fce35b2a411eda03a00ff71bb3dd6', '097505d3fe1f4141886da3e3e4bf0b77', '37', '1', '88.00', '0', '0');

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `foodid` varchar(255) NOT NULL,
  `m_type` varchar(255) DEFAULT NULL,
  `m_name` varchar(255) DEFAULT NULL,
  `m_price` double(10,2) DEFAULT NULL,
  `m_img` varchar(255) DEFAULT NULL,
  `m_popular` int(1) DEFAULT '0',
  `isdel` int(1) DEFAULT NULL,
  PRIMARY KEY (`foodid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('19f00b9e8b7811ed8d8802004c4f4f50', 'snack', '123', '17.21', 'f45abf6c2a9f41c5a6b7a735b13ebd51.jpg', '0', '1');
INSERT INTO `menu` VALUES ('36', 'staple', '虾卷', '48.00', '20170305192126342.jpg', '1', '0');
INSERT INTO `menu` VALUES ('37', 'staple', '牛肉', '88.00', '20170305192143279.jpg', '1', '0');
INSERT INTO `menu` VALUES ('38', 'staple', '鲜虾', '1080.00', '20170305192151972.jpg', '1', '0');
INSERT INTO `menu` VALUES ('39', 'snack', '草莓蛋糕', '25.00', '20170305192206891.jpg', '0', '0');
INSERT INTO `menu` VALUES ('40', 'snack', '蔬菜萨拉', '38.00', '20170305192220042.jpg', '0', '0');
INSERT INTO `menu` VALUES ('41', 'drink', '拉菲', '288.00', '20170305192231792.jpg', '0', '0');
INSERT INTO `menu` VALUES ('42', 'snack', '提拉米苏', '28.00', '20170305192243383.jpg', '0', '0');
INSERT INTO `menu` VALUES ('ac483a9d96d711ed856902004c4f4f50', 'staple', '2', '2.00', '6043681dce0c41f3be2cf29d4695d03d.jpg', '0', '0');

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `m_id` varchar(255) NOT NULL,
  `m_userid` varchar(255) DEFAULT NULL,
  `m_orderid` varchar(255) DEFAULT NULL,
  `m_type` varchar(255) DEFAULT NULL,
  `m_content` tinytext,
  `m_date` datetime DEFAULT NULL,
  `m_complete` int(1) DEFAULT NULL,
  `m_isdel` int(1) DEFAULT NULL,
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('0f359886967011ed856902004c4f4f50', '1', '28', '1', '28号催单', '2023-01-17 22:05:55', '1', '0');
INSERT INTO `message` VALUES ('10', '2', '25', '1', null, '2022-11-25 16:02:07', '1', '0');
INSERT INTO `message` VALUES ('11', '1', 'e02aa47b966911ed856902004c4f4f51', '2', '31号不要辣的', '2022-11-25 20:35:55', '0', '0');
INSERT INTO `message` VALUES ('12', '1', '29', '1', null, '2022-11-26 11:27:22', '0', '0');
INSERT INTO `message` VALUES ('2c79d90a9fa911ed945302004c4f4f50', '1', '28', '1', '28号再次催单', '2023-01-29 15:47:18', '1', '0');
INSERT INTO `message` VALUES ('7', '1', '22', '1', null, '2022-11-24 21:54:39', '1', '0');
INSERT INTO `message` VALUES ('7b0fe458967011ed856902004c4f4f50', '1', '28', '1', '28号再次催单28号再次催单28号再次催单28号再次催单28号再次催单28号再次催单28号再次催单28号再次催单28号再次催单28号再次催单28号再次催单', '2023-01-17 22:08:56', '1', '0');
INSERT INTO `message` VALUES ('8', '1', '0', '1', null, '2022-11-24 10:40:59', '0', '0');
INSERT INTO `message` VALUES ('9', '1', '23', '1', null, '2022-11-24 10:42:10', '0', '0');
INSERT INTO `message` VALUES ('e02aa47b966911ed856902004c4f4f50', '1', '23', '2', '555哭泣555哭泣555哭泣555哭泣555哭泣555哭泣555哭泣555哭泣555哭泣555哭泣', '2023-01-17 21:21:39', '0', '0');

-- ----------------------------
-- Table structure for m_order
-- ----------------------------
DROP TABLE IF EXISTS `m_order`;
CREATE TABLE `m_order` (
  `orderid` varchar(255) NOT NULL,
  `tableno` varchar(255) DEFAULT NULL,
  `o_status` varchar(255) DEFAULT NULL,
  `price` double(10,2) DEFAULT NULL,
  `orderdate` datetime DEFAULT NULL,
  PRIMARY KEY (`orderid`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='status: 0未下单，1已下单，2已上菜，3已付款';

-- ----------------------------
-- Records of m_order
-- ----------------------------
INSERT INTO `m_order` VALUES ('097505d3fe1f4141886da3e3e4bf0b77', '3', '未上菜', '1170.00', '2023-02-22 19:35:00');
INSERT INTO `m_order` VALUES ('21', '2', '已付款', '88.00', '2022-11-25 20:08:11');
INSERT INTO `m_order` VALUES ('22', '1', '已上菜', '254.00', '2022-11-25 21:52:35');
INSERT INTO `m_order` VALUES ('23', '1', '已付款', '304.00', '2022-11-25 10:42:01');
INSERT INTO `m_order` VALUES ('28', '1', '已付款', '255.00', '2022-11-25 16:23:13');
INSERT INTO `m_order` VALUES ('29', '1', '未上菜', '58.00', '2022-11-26 11:27:11');
INSERT INTO `m_order` VALUES ('e02aa47b966911ed856902004c4f4f51', '2', '上菜中', '91.21', '2022-11-26 16:28:09');

-- ----------------------------
-- Table structure for remark
-- ----------------------------
DROP TABLE IF EXISTS `remark`;
CREATE TABLE `remark` (
  `r_id` varchar(64) NOT NULL,
  `r_userid` varchar(255) DEFAULT NULL,
  `r_content` varchar(255) DEFAULT NULL,
  `r_date` datetime DEFAULT NULL,
  `r_isdel` int(1) DEFAULT NULL,
  PRIMARY KEY (`r_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of remark
-- ----------------------------
INSERT INTO `remark` VALUES ('4', '1', '赶紧上菜', '2022-11-25 22:18:53', '0');
INSERT INTO `remark` VALUES ('5', '1', '发送消息', '2022-11-26 11:27:58', '0');
INSERT INTO `remark` VALUES ('6', '1', '111111', '2022-11-26 11:28:58', '0');

-- ----------------------------
-- Table structure for table_rest
-- ----------------------------
DROP TABLE IF EXISTS `table_rest`;
CREATE TABLE `table_rest` (
  `t_id` varchar(64) NOT NULL,
  `t_no` varchar(255) DEFAULT NULL,
  `t_people` varchar(100) DEFAULT NULL,
  `t_orderid` varchar(64) DEFAULT NULL,
  `t_isuse` int(1) DEFAULT NULL,
  `t_isdel` int(1) DEFAULT NULL,
  PRIMARY KEY (`t_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of table_rest
-- ----------------------------
INSERT INTO `table_rest` VALUES ('0f1dfa968d0111ed8d8802004c4f4f50', '6', '5描述', '12', '0', '1');
INSERT INTO `table_rest` VALUES ('1', '一号桌', '3-4人', '29', '1', '0');
INSERT INTO `table_rest` VALUES ('141c1f818d0611ed8d8802004c4f4f50', '3', '测试大包间描述', '', '0', '1');
INSERT INTO `table_rest` VALUES ('19ae0a6d8d0611ed8d8802004c4f4f50', '5', '测试大包间描述', '', '0', '1');
INSERT INTO `table_rest` VALUES ('2', '二号桌', '1-22人', '31', '1', '0');
INSERT INTO `table_rest` VALUES ('2a7d934c8d0111ed8d8802004c4f4f50', '4', '4444', null, '0', '1');
INSERT INTO `table_rest` VALUES ('3', '三号桌', '1', '097505d3fe1f4141886da3e3e4bf0b77', '1', '0');
INSERT INTO `table_rest` VALUES ('4', '大包房', '容纳15人', '0', '0', '0');
INSERT INTO `table_rest` VALUES ('4ee0f4a28d0011ed8d8802004c4f4f50', '测试包间1', '测试大包间描述', null, '0', '1');
INSERT INTO `table_rest` VALUES ('5', '四号桌', '', '0', '0', '1');
INSERT INTO `table_rest` VALUES ('6be0e7218d0011ed8d8802004c4f4f50', '1', '1', null, '0', '1');
INSERT INTO `table_rest` VALUES ('895719418cfb11ed8d8802004c4f4f50', '测试包间1', '测试大包间描述', null, '0', '1');
INSERT INTO `table_rest` VALUES ('a9a0138a8cf711ed8d8802004c4f4f50', null, '测试大包间描述', null, '0', '1');
INSERT INTO `table_rest` VALUES ('b3407edc8d0811ed8d8802004c4f4f50', '1', '1', null, '0', '1');
INSERT INTO `table_rest` VALUES ('b64959b98d0811ed8d8802004c4f4f50', '2', '2', null, '0', '1');
INSERT INTO `table_rest` VALUES ('bcbe56ee8cf711ed8d8802004c4f4f50', null, '测试大包间描述', null, '0', '1');
INSERT INTO `table_rest` VALUES ('c95fd3348cfa11ed8d8802004c4f4f50', '测试包间', '测试大包间描述', null, '0', '0');
INSERT INTO `table_rest` VALUES ('d52e02908d0811ed8d8802004c4f4f50', '测试豪华包间', '11', null, '0', '0');
