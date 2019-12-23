/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : cooky-cloud-rbac

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 19/12/2019 20:12:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `oauth_client_details`;
CREATE TABLE `oauth_client_details`  (
  `client_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_ids` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `client_secret` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `scope` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorized_grant_types` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `authorities` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `access_token_validity` int(11) NULL DEFAULT NULL,
  `refresh_token_validity` int(11) NULL DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `autoapprove` tinyint(4) NULL DEFAULT NULL,
  PRIMARY KEY (`client_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of oauth_client_details
-- ----------------------------
INSERT INTO `oauth_client_details` VALUES ('cooky', NULL, '$2a$10$ZGPb6g8ZP8vtXCH9idIoX.y.9XMWy.8JbNWWIvGUcrXw4IqWdDjTW', 'app', 'password,refresh_token', NULL, NULL, 860000, 8600000, NULL, NULL);

-- ----------------------------
-- Table structure for t_dept
-- ----------------------------
DROP TABLE IF EXISTS `t_dept`;
CREATE TABLE `t_dept`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父节点id',
  `dept_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '部门名',
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `ct` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `mt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dept
-- ----------------------------
INSERT INTO `t_dept` VALUES (3, 0, '开发部', NULL, '开发部', '2019-11-13 14:29:23', '2019-11-13 14:29:27');
INSERT INTO `t_dept` VALUES (4, 3, 'java开发部', NULL, 'java开发部', '2019-11-13 14:29:43', '2019-11-13 14:29:46');
INSERT INTO `t_dept` VALUES (5, 0, '测试部', NULL, '测试部1', NULL, NULL);
INSERT INTO `t_dept` VALUES (6, 5, '测试一部', NULL, '测试一部', NULL, NULL);

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `parent_id` int(11) NULL DEFAULT NULL COMMENT '父节点id',
  `menu_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名',
  `menu_component` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '组件名',
  `menu_path` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `menu_title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `method` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL,
  `icon` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图标',
  `perms` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限',
  `type` int(11) NULL DEFAULT NULL COMMENT '类型（0：菜单，1：按钮）',
  `ct` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `mt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_menu
-- ----------------------------
INSERT INTO `t_menu` VALUES (2, 0, 'Sys', 'Layout', '/sys', '系统管理', NULL, NULL, 'icon-cog', '', 0, '2019-09-08 20:01:54', '2019-09-08 20:01:57');
INSERT INTO `t_menu` VALUES (3, 2, 'User', 'components/cooky/user/user', '/user', '用户管理', NULL, NULL, NULL, 'user:view', 0, '2019-10-23 22:50:35', '2019-10-23 22:50:38');
INSERT INTO `t_menu` VALUES (4, 2, 'Dept', 'components/cooky/dept/dept', '/dept', '部门管理', NULL, NULL, NULL, 'dept:view', 0, '2019-10-23 22:50:55', '2019-10-23 22:50:58');
INSERT INTO `t_menu` VALUES (5, 2, 'Role', 'components/cooky/role/role', '/role', '角色管理', NULL, NULL, NULL, 'role:view', 0, '2019-10-23 22:51:17', '2019-10-23 22:51:20');
INSERT INTO `t_menu` VALUES (6, 2, 'Menu', 'components/cooky/menu/menu', '/menu', '菜单管理', NULL, NULL, NULL, 'menu:view', 0, '2019-10-23 22:51:35', '2019-10-23 22:51:37');
INSERT INTO `t_menu` VALUES (7, 3, '', '', '', '新增用户', NULL, NULL, '', 'user:add', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (8, 3, '', '', '', '修改用户', NULL, NULL, '', 'user:edit', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (9, 3, '', '', '', '删除用户', NULL, NULL, '', 'user:del', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (10, 4, '', '', '', '新增部门', NULL, NULL, '', 'dept:add', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (11, 4, '', '', '', '修改部门', NULL, NULL, '', 'dept:edit', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (12, 4, '', '', '', '删除部门', NULL, NULL, '', 'dept:del', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (13, 5, '', '', '', '新增角色', NULL, NULL, '', 'role:add', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (14, 5, '', '', '', '修改角色', NULL, NULL, '', 'role:edit', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (15, 5, '', '', '', '删除角色', NULL, NULL, '', 'role:del', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (16, 6, '', '', '', '新增菜单', NULL, NULL, '', 'menu:add', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (17, 6, '', '', '', '修改菜单', NULL, NULL, '', 'menu:edit', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (18, 6, '', '', '', '删除菜单', NULL, NULL, '', 'menu:del', 1, NULL, NULL);
INSERT INTO `t_menu` VALUES (19, 0, 'Monitor', 'Layout', '/monitor', '系统监控', NULL, NULL, 'icon-pie-chart', '', 0, NULL, NULL);
INSERT INTO `t_menu` VALUES (20, 19, 'Spring-Boot-Admin', 'components/management/spring-boot-admin/index', '/spring-boot-admin', 'SpringBootAdmin', NULL, NULL, '', 'monitor:admin', 0, NULL, NULL);
INSERT INTO `t_menu` VALUES (21, 19, 'Swagger', 'components/management/swagger/index', '/swagger', 'Swagger', NULL, NULL, '', 'monitor:swagger', 0, NULL, NULL);
INSERT INTO `t_menu` VALUES (22, 19, 'skywalking', 'components/management/skywalking/index', '/skywalking', 'skywalking', NULL, NULL, '', 'monitor:skywalking', 0, NULL, NULL);

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `role_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `ct` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `mt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES (1, 'admin', 'admin', '2019-11-24 21:29:55', '2019-12-18 12:25:48');
INSERT INTO `t_role` VALUES (2, '测试', '测试', '2019-12-02 12:22:49', '2019-12-02 12:22:49');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_role_menu`;
CREATE TABLE `t_role_menu`  (
  `role_id` int(11) NULL DEFAULT NULL,
  `menu_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_menu
-- ----------------------------
INSERT INTO `t_role_menu` VALUES (1, 2);
INSERT INTO `t_role_menu` VALUES (1, 3);
INSERT INTO `t_role_menu` VALUES (1, 4);
INSERT INTO `t_role_menu` VALUES (1, 5);
INSERT INTO `t_role_menu` VALUES (1, 6);
INSERT INTO `t_role_menu` VALUES (1, 7);
INSERT INTO `t_role_menu` VALUES (1, 8);
INSERT INTO `t_role_menu` VALUES (1, 9);
INSERT INTO `t_role_menu` VALUES (1, 10);
INSERT INTO `t_role_menu` VALUES (1, 11);
INSERT INTO `t_role_menu` VALUES (1, 12);
INSERT INTO `t_role_menu` VALUES (1, 13);
INSERT INTO `t_role_menu` VALUES (1, 14);
INSERT INTO `t_role_menu` VALUES (1, 15);
INSERT INTO `t_role_menu` VALUES (1, 16);
INSERT INTO `t_role_menu` VALUES (1, 17);
INSERT INTO `t_role_menu` VALUES (1, 18);
INSERT INTO `t_role_menu` VALUES (1, 19);
INSERT INTO `t_role_menu` VALUES (1, 20);
INSERT INTO `t_role_menu` VALUES (1, 21);
INSERT INTO `t_role_menu` VALUES (1, 22);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `sex` varchar(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '性别 0：男 1：女 2：保密',
  `dept_id` int(11) NULL DEFAULT NULL COMMENT '部门id',
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `describe` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `status` int(11) NULL DEFAULT NULL COMMENT '状态 0：禁用 1：启用',
  `ct` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `mt` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'Yuuki', '$2a$10$laxwd4fHEXjaFzmgt0uOtu6SUoNlP3gb3htLj3/8wMJWexTQ5jAwS', 'cae7dee8e340cde65a3b38f968427cac.jpeg', '0', 3, '806393858@qq.com', '15899998888', '超级管理员', 1, '2019-10-23 22:21:55', '2019-10-23 22:21:58');
INSERT INTO `t_user` VALUES (2, 'test', '$2a$10$JA4LuaBPmuKrOFQdELnmqun8JwrzCJ9efLmpgR8DbFW54z.h603Ie', NULL, '1', 5, '', '', '测试', 1, NULL, NULL);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role`  (
  `user_id` int(11) NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES (1, 2);
INSERT INTO `t_user_role` VALUES (1, 1);

SET FOREIGN_KEY_CHECKS = 1;
