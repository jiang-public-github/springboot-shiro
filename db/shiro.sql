/*
 Navicat Premium Data Transfer

 Source Server         : 172.0.0.1 - mysql - 8.0
 Source Server Type    : MySQL
 Source Server Version : 80022
 Source Host           : 127.0.0.1:3307
 Source Schema         : shiro

 Target Server Type    : MySQL
 Target Server Version : 80022
 File Encoding         : 65001

 Date: 26/01/2021 15:11:05
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `parent_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '父级点编号',
  `parent_ids` varchar(3500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '当前菜单的所有父节点编号',
  `menu_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `menu_url` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单链接地址',
  `permission` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '权限标识',
  `icon` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单图标',
  `sort` int(0) NOT NULL DEFAULT 0 COMMENT '排序',
  `is_show` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否显示（0-显示，1-隐藏）',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '菜单备注',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '0', 'root', '/', NULL, '/', 0, '0', '跟', NULL, '2021-01-21 13:14:41', NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES ('a9f88d5e01f94c1b88c9c991968489d5', '1', '0,1', '首页', '/', '', '/', 100, '0', '', NULL, '2021-01-21 13:14:41', NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES ('a9f88d5e01f94c1b88c9c991968489d6', '1', '0,1', '购物车', '/', 'role:sys:show,role:sys:edit', '/', 200, '0', '', NULL, '2021-01-21 13:14:41', NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES ('a9f88d5e01f94c1b88c9c991968489d7', '1', '0,1', '分类', '/', NULL, '/', 300, '0', '', NULL, '2021-01-21 13:14:41', NULL, NULL, '0');
INSERT INTO `sys_menu` VALUES ('a9f88d5e01f94c1b88c9c991968489d8', '1', '0,1', '个人中心', '/', NULL, '/', 400, '0', '', NULL, '2021-01-21 13:14:41', NULL, NULL, '0');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名称',
  `english_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色英文名',
  `is_forbidden` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否禁用（0-未禁用，1-已禁用）',
  `system` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '应用系统',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色备注',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('a9f88d5e01f94c1b88c9c991968489d3', '超级管理员', 'admin', '0', NULL, '系统超级管理员', NULL, '2021-01-21 13:10:01', NULL, NULL, '0');
INSERT INTO `sys_role` VALUES ('a9f88d5e01f94c1b88c9c991968489d4', '普通用户', 'user', '0', NULL, '普通用户', NULL, '2021-01-21 13:10:01', NULL, NULL, '0');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统角色编号（对应sys_role - id）',
  `menu_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统菜单编号（对应sys_menu - id）',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  INDEX `menu_id`(`menu_id`) USING BTREE,
  CONSTRAINT `sys_role_menu_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_role_menu_ibfk_2` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统-角色-菜单表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('c3ed4f490ca242a3bebd71904d257510', 'a9f88d5e01f94c1b88c9c991968489d4', 'a9f88d5e01f94c1b88c9c991968489d8', NULL, '2021-01-21 13:20:31', NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES ('c3ed4f490ca242a3bebd71904d257584', 'a9f88d5e01f94c1b88c9c991968489d3', 'a9f88d5e01f94c1b88c9c991968489d5', NULL, '2021-01-21 13:20:31', NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES ('c3ed4f490ca242a3bebd71904d257585', 'a9f88d5e01f94c1b88c9c991968489d3', 'a9f88d5e01f94c1b88c9c991968489d6', NULL, '2021-01-21 13:20:31', NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES ('c3ed4f490ca242a3bebd71904d257586', 'a9f88d5e01f94c1b88c9c991968489d3', 'a9f88d5e01f94c1b88c9c991968489d7', NULL, '2021-01-21 13:20:31', NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES ('c3ed4f490ca242a3bebd71904d257587', 'a9f88d5e01f94c1b88c9c991968489d3', 'a9f88d5e01f94c1b88c9c991968489d8', NULL, '2021-01-21 13:20:31', NULL, NULL, '0');
INSERT INTO `sys_role_menu` VALUES ('c3ed4f490ca242a3bebd71904d257589', 'a9f88d5e01f94c1b88c9c991968489d4', 'a9f88d5e01f94c1b88c9c991968489d5', NULL, '2021-01-21 13:20:31', NULL, NULL, '0');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `login_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名（真实姓名）',
  `phone` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '手机号码',
  `id_number` varchar(18) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '身份证号码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `sort` int(0) NULL DEFAULT 0 COMMENT '排序',
  `head_portrait` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `remarks` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注信息',
  `is_forbidden` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '是否禁用（0-否，1-是）',
  `login_ip` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '最后登录的IP',
  `login_data` datetime(0) NULL DEFAULT NULL COMMENT '最后登录的时间',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记 （0-正常，1-删除）',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('5ed01be45e434bb0a3b866cd88da37f7', 'admin', '928bfd2577490322a6e19b793691467e', '超级管理员', '15285326224', '1', '888888888@qq.com', 1000000000, NULL, NULL, '0', NULL, NULL, NULL, '2021-01-21 13:01:44', NULL, NULL, '0');
INSERT INTO `sys_user` VALUES ('5ed01be45e434bb0a3b866cd88da37f8', 'user', 'b8c2d5b0a37cc51f91d5e8970347a3a3', '普通用户', '15285326110', '1', '999999999@qq.com', 1000000000, NULL, NULL, '0', NULL, NULL, NULL, '2021-01-21 13:01:44', NULL, NULL, '0');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '主键',
  `user_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统用户编号（对应sys_user - id）',
  `role_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '系统角色编号（对应sys_role - id）',
  `create_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '创建者',
  `create_date` datetime(0) NOT NULL COMMENT '创建时间',
  `update_by` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '更新者',
  `update_date` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `del_flag` char(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL DEFAULT '0' COMMENT '删除标记（0-正常，1-删除）',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `user_id`(`user_id`) USING BTREE,
  INDEX `role_id`(`role_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT,
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统-用户-角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('b1b763740e0c4b3990f8da3ccfe98331', '5ed01be45e434bb0a3b866cd88da37f7', 'a9f88d5e01f94c1b88c9c991968489d3', NULL, '2021-01-21 13:18:55', NULL, NULL, '0');
INSERT INTO `sys_user_role` VALUES ('b1b763740e0c4b3990f8da3ccfe98332', '5ed01be45e434bb0a3b866cd88da37f8', 'a9f88d5e01f94c1b88c9c991968489d4', NULL, '2021-01-21 13:19:33', NULL, NULL, '0');
INSERT INTO `sys_user_role` VALUES ('b1b763740e0c4b3990f8da3ccfe98333', '5ed01be45e434bb0a3b866cd88da37f7', 'a9f88d5e01f94c1b88c9c991968489d4', NULL, '2021-01-21 13:19:33', NULL, NULL, '0');

SET FOREIGN_KEY_CHECKS = 1;
