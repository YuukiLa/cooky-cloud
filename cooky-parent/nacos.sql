/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 50723
 Source Host           : localhost:3306
 Source Schema         : nacos

 Target Server Type    : MySQL
 Target Server Version : 50723
 File Encoding         : 65001

 Date: 19/12/2019 20:13:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_use` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `effect` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `c_schema` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfo_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 21 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of config_info
-- ----------------------------
INSERT INTO `config_info` VALUES (15, 'cooky-gateway.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      # discovery:\r\n      #   locator:\r\n      #     enabled: true\r\n      #     lower-case-service-id: true\r\n      routes:\r\n        - id: cooky-rbac\r\n          uri: lb://cooky-rbac\r\n          predicates:\r\n            - Path=/rbac/**\r\n          # filters:\r\n          #   - SwaggerHeaderFilter\r\n        - id: cooky-auth\r\n          uri: lb://cooky-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n          filters:\r\n            - AddRequestHeader=X-Request-Foo, Bar\r\n            - name: RequestRateLimiter\r\n              args:\r\n                # 令牌桶每秒填充平均速率\r\n                redis-rate-limiter.replenishRate: 5\r\n                # 令牌桶的上限\r\n                redis-rate-limiter.burstCapacity: 100\r\n                # 使用SpEL表达式从Spring容器中获取Bean对象\r\n                key-resolver: \"#{@pathKeyResolver}\"\r\n        - id: cooky-music\r\n          uri: lb://cooky-music\r\n          predicates:\r\n            - Path=/music/**\r\n      default-filters:\r\n        - StripPrefix=1\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\nribbon:\r\n  eager-load:\r\n    enabled: true\r\n    clients: cooky-auth,cooky-rbac,cooky-music\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', 'b63f52e5d4e2c17bb4fde0cbfc0d40aa', '2019-10-30 00:29:27', '2019-12-18 21:14:48', NULL, '172.17.0.1', '', '', '微服务网关', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (16, 'cooky-music.yaml', 'DEFAULT_GROUP', '\r\nserver:\r\n  port: 8091\r\n\r\n\r\nspring:\r\n  # datasource:\r\n  #   driver-class-name: com.p6spy.engine.spy.P6SpyDriver\r\n  #   url: jdbc:p6spy:mysql://${mysql.url}:3306/cooky-cloud-rbac?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8\r\n  #   username: root\r\n  #   password: 806393858\r\n  #   hikari:\r\n  #     pool-name: cooky-music-pool\r\n  # redis:\r\n  #   database: 0\r\n  #   host: 127.0.0.1\r\n  #   port: 6379\r\n  #   lettuce:\r\n  #     pool:\r\n  #       min-idle: 8\r\n  #       max-idle: 500\r\n  #       max-active: 2000\r\n  #       max-wait: 10000\r\n  #   timeout: 5000\r\n\r\nsecurity:\r\n  oauth2:\r\n    resource:\r\n      id: cooky-music\r\n      user-info-uri: http://localhost:10000/auth/user\r\n      prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n\r\n# mybatis-plus:\r\n#   mapper-locations: classpath:mapper/*.xml\r\n#   type-aliases-package: com.yuuki.cooky.rbac.model.entity\r\n\r\n', 'e9236c5c9377b9f0ae94f3d55478d648', '2019-10-30 00:29:50', '2019-10-30 00:46:15', NULL, '27.38.254.37', '', '', 'null', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (17, 'cooky-auth.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 9999\r\nspring:\r\n  main:\r\n    allow-bean-definition-overriding: true\r\n  datasource:\r\n    url: jdbc:mysql://${mysql.url}:3306/cooky-cloud-rbac?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8\r\n    username: root\r\n    password: root\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      pool-name: cooky-auth-pool\r\n      maximum-pool-size: 20\r\n  #      jdbc-url: jdbc:mysql://127.0.0.1:3306/cooky-rbac\r\n  redis:\r\n    database: 0\r\n    host: ${redis.url}\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n\r\n  application:\r\n    name: cooky-auth\r\nmybatis-plus:\r\n  global-config:\r\n    #字段策略 0:\"忽略判断\",1:\"非 NULL 判断\"),2:\"非空判断\"\r\n    field-strategy: 1\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.yuuki.cooky.auth.model.entity\r\nfeign:\r\n  okhttp:\r\n    enabled: true\r\nribbon:\r\n  eager-load:\r\n    enabled: true\r\n    clients: cooky-rbac\r\nmanagement:\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n\r\ncooky:\r\n  annoUrl: /captcha\r\n  jwt: false\r\n', '626d43079bbb24371f17520682c30aca', '2019-10-31 16:12:00', '2019-12-18 21:04:28', NULL, '172.17.0.1', '', '', 'auth服务', 'null', 'null', 'yaml', 'null');
INSERT INTO `config_info` VALUES (20, 'cooky-rbac.yaml', 'DEFAULT_GROUP', 'server:\r\n  port: 8090\r\n\r\n\r\nspring:\r\n  application:\r\n    name: cooky-rbac\r\n  datasource:\r\n    driver-class-name: com.p6spy.engine.spy.P6SpyDriver\r\n    url: jdbc:p6spy:mysql://127.0.0.1:3306/cooky-cloud-rbac?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8\r\n    username: root\r\n    password: root\r\n    hikari:\r\n      pool-name: cooky-rbac-pool\r\n  redis:\r\n    database: 0\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:8848\r\n  jackson:\r\n    date-format: yyyy-MM-dd\r\n    time-zone: GMT+8\r\nsecurity:\r\n  oauth2:\r\n    resource:\r\n      id: cooky-rbac\r\n      user-info-uri: http://localhost:10000/auth/user\r\n#      prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n\r\nmybatis-plus:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.yuuki.cooky.rbac.model.entity\r\n\r\n', '451d616659fc25b27d38db8b0de61f07', '2019-12-18 21:11:33', '2019-12-18 21:11:33', NULL, '172.17.0.1', '', '', 'rbac服务', NULL, NULL, 'yaml', NULL);

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime(0) NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfoaggr_datagrouptenantdatum`(`data_id`, `group_id`, `tenant_id`, `datum_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '增加租户字段' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfobeta_datagrouptenant`(`data_id`, `group_id`, `tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_beta' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL COMMENT 'source user',
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_configinfotag_datagrouptenanttag`(`data_id`, `group_id`, `tenant_id`, `tag_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_info_tag' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation`  (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`) USING BTREE,
  UNIQUE INDEX `uk_configtagrelation_configidtag`(`id`, `tag_name`, `tag_type`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'config_tag_relation' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_group_id`(`group_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '集群、各Group容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info`  (
  `id` bigint(64) UNSIGNED NOT NULL,
  `nid` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'app_name',
  `content` longtext CHARACTER SET utf8 COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text CHARACTER SET utf8 COLLATE utf8_bin NULL,
  `src_ip` varchar(20) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `op_type` char(10) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL,
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`) USING BTREE,
  INDEX `idx_gmt_create`(`gmt_create`) USING BTREE,
  INDEX `idx_gmt_modified`(`gmt_modified`) USING BTREE,
  INDEX `idx_did`(`data_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 44 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '多租户改造' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
INSERT INTO `his_config_info` VALUES (17, 26, 'cooky-auth.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9999\r\nspring:\r\n  main:\r\n    allow-bean-definition-overriding: true\r\n  datasource:\r\n    url: jdbc:mysql://${mysql.url}:3306/cooky-cloud-rbac?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8\r\n    username: root\r\n    password: root\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      pool-name: cooky-auth-pool\r\n      maximum-pool-size: 20\r\n  #      jdbc-url: jdbc:mysql://127.0.0.1:3306/cooky-rbac\r\n  redis:\r\n    database: 0\r\n    host: ${redis.url}\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n\r\n  application:\r\n    name: cooky-auth\r\nmybatis-plus:\r\n  global-config:\r\n    #字段策略 0:\"忽略判断\",1:\"非 NULL 判断\"),2:\"非空判断\"\r\n    field-strategy: 1\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.yuuki.cooky.auth.model.entity\r\n\r\nmanagement:\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n\r\ncooky:\r\n  annoUrl: /captcha\r\n  jwt: true', '60b12a2efb071567c1571f2abb5c99ec', '2010-05-05 00:00:00', '2019-11-28 22:11:31', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (17, 27, 'cooky-auth.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9999\r\nspring:\r\n  main:\r\n    allow-bean-definition-overriding: true\r\n  datasource:\r\n    url: jdbc:mysql://${mysql.url}:3306/cooky-cloud-rbac?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8\r\n    username: root\r\n    password: root\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      pool-name: cooky-auth-pool\r\n      maximum-pool-size: 20\r\n  #      jdbc-url: jdbc:mysql://127.0.0.1:3306/cooky-rbac\r\n  redis:\r\n    database: 0\r\n    host: ${redis.url}\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n\r\n  application:\r\n    name: cooky-auth\r\nmybatis-plus:\r\n  global-config:\r\n    #字段策略 0:\"忽略判断\",1:\"非 NULL 判断\"),2:\"非空判断\"\r\n    field-strategy: 1\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.yuuki.cooky.auth.model.entity\r\nfeign:\r\n  okhttp:\r\n    enabled: true\r\nmanagement:\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n\r\ncooky:\r\n  annoUrl: /captcha\r\n  jwt: false\r\n', '93e0d779b0960e5c37f0f286ad0b61d9', '2010-05-05 00:00:00', '2019-11-28 22:45:23', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (17, 28, 'cooky-auth.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9999\r\nspring:\r\n  cloud:\r\n    sentinel:\r\n      transport:\r\n        dashboard: localhost:8970\r\n  main:\r\n    allow-bean-definition-overriding: true\r\n  datasource:\r\n    url: jdbc:mysql://${mysql.url}:3306/cooky-cloud-rbac?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8\r\n    username: root\r\n    password: root\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      pool-name: cooky-auth-pool\r\n      maximum-pool-size: 20\r\n  #      jdbc-url: jdbc:mysql://127.0.0.1:3306/cooky-rbac\r\n  redis:\r\n    database: 0\r\n    host: ${redis.url}\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n\r\n  application:\r\n    name: cooky-auth\r\nmybatis-plus:\r\n  global-config:\r\n    #字段策略 0:\"忽略判断\",1:\"非 NULL 判断\"),2:\"非空判断\"\r\n    field-strategy: 1\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.yuuki.cooky.auth.model.entity\r\nfeign:\r\n  okhttp:\r\n    enabled: true\r\nmanagement:\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n\r\ncooky:\r\n  annoUrl: /captcha\r\n  jwt: false\r\n', '36d31bc2dd5693967c48a56f0419c92d', '2010-05-05 00:00:00', '2019-11-28 22:48:58', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (15, 29, 'cooky-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      # discovery:\r\n      #   locator:\r\n      #     enabled: true\r\n      routes:\r\n        - id: cooky-rbac\r\n          uri: lb://cooky-rbac\r\n          predicates:\r\n            - Path=/rbac/**\r\n        - id: cooky-auth\r\n          uri: lb://cooky-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n        - id: cooky-music\r\n          uri: lb://cooky-music\r\n          predicates:\r\n            - Path=/music/**\r\n      default-filters:\r\n        - StripPrefix=1\r\n\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', '8afb781962870540931671ad37ffd0f9', '2010-05-05 00:00:00', '2019-12-03 09:16:21', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (15, 30, 'cooky-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      # discovery:\r\n      #   locator:\r\n      #     enabled: true\r\n      routes:\r\n        - id: cooky-rbac\r\n          uri: lb://cooky-rbac\r\n          predicates:\r\n            - Path=/rbac/**\r\n          filters:\r\n            - SwaggerHeaderFilter\r\n        - id: cooky-auth\r\n          uri: lb://cooky-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n        - id: cooky-music\r\n          uri: lb://cooky-music\r\n          predicates:\r\n            - Path=/music/**\r\n      default-filters:\r\n        - StripPrefix=1\r\n\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', '0495bbe873bde95b71b808120c3471ad', '2010-05-05 00:00:00', '2019-12-03 09:18:55', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (15, 31, 'cooky-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      # discovery:\r\n      #   locator:\r\n      #     enabled: true\r\n      routes:\r\n        - id: cooky-rbac\r\n          uri: lb://cooky-rbac\r\n          predicates:\r\n            - Path=/rbac/**\r\n          #filters:\r\n           # - SwaggerHeaderFilter\r\n        - id: cooky-auth\r\n          uri: lb://cooky-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n        - id: cooky-music\r\n          uri: lb://cooky-music\r\n          predicates:\r\n            - Path=/music/**\r\n      default-filters:\r\n        - StripPrefix=1\r\n\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', 'e354ebde236a2c439bc110a3f2f58375', '2010-05-05 00:00:00', '2019-12-03 09:23:37', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (15, 32, 'cooky-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      # discovery:\r\n      #   locator:\r\n      #     enabled: true\r\n      routes:\r\n        - id: cooky-rbac\r\n          uri: lb://cooky-rbac\r\n          predicates:\r\n            - Path=/rbac/**\r\n          filters:\r\n            - SwaggerHeaderFilter\r\n        - id: cooky-auth\r\n          uri: lb://cooky-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n        - id: cooky-music\r\n          uri: lb://cooky-music\r\n          predicates:\r\n            - Path=/music/**\r\n      default-filters:\r\n        - StripPrefix=1\r\n\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', '0495bbe873bde95b71b808120c3471ad', '2010-05-05 00:00:00', '2019-12-03 18:46:17', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (15, 33, 'cooky-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      # discovery:\r\n      #   locator:\r\n      #     enabled: true\r\n      routes:\r\n        - id: cooky-rbac\r\n          uri: lb://cooky-rbac\r\n          predicates:\r\n            - Path=/rbac/**\r\n          # filters:\r\n          #   - SwaggerHeaderFilter\r\n        - id: cooky-auth\r\n          uri: lb://cooky-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n        - id: cooky-music\r\n          uri: lb://cooky-music\r\n          predicates:\r\n            - Path=/music/**\r\n      default-filters:\r\n        - StripPrefix=1\r\n\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', '173863b6c3d010d1ba36a33382a5f117', '2010-05-05 00:00:00', '2019-12-09 13:38:58', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (15, 34, 'cooky-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          enabled: true\r\n      # routes:\r\n      #   - id: cooky-rbac\r\n      #     uri: lb://cooky-rbac\r\n      #     predicates:\r\n      #       - Path=/rbac/**\r\n      #     # filters:\r\n      #     #   - SwaggerHeaderFilter\r\n      #   - id: cooky-auth\r\n      #     uri: lb://cooky-auth\r\n      #     predicates:\r\n      #       - Path=/auth/**\r\n      #   - id: cooky-music\r\n      #     uri: lb://cooky-music\r\n      #     predicates:\r\n      #       - Path=/music/**\r\n      default-filters:\r\n        - StripPrefix=1\r\n\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', '5ccccdb2917336a9789ef332a6b09528', '2010-05-05 00:00:00', '2019-12-09 13:48:07', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (15, 35, 'cooky-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          enabled: true\r\n          lower-case-service-id: true\r\n      # routes:\r\n      #   - id: cooky-rbac\r\n      #     uri: lb://cooky-rbac\r\n      #     predicates:\r\n      #       - Path=/rbac/**\r\n      #     # filters:\r\n      #     #   - SwaggerHeaderFilter\r\n      #   - id: cooky-auth\r\n      #     uri: lb://cooky-auth\r\n      #     predicates:\r\n      #       - Path=/auth/**\r\n      #   - id: cooky-music\r\n      #     uri: lb://cooky-music\r\n      #     predicates:\r\n      #       - Path=/music/**\r\n      default-filters:\r\n        - StripPrefix=1\r\n\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', '25b4861affd12794dd9d638dfcfe90ca', '2010-05-05 00:00:00', '2019-12-09 13:52:32', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (15, 36, 'cooky-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      discovery:\r\n        locator:\r\n          enabled: true\r\n          lower-case-service-id: true\r\n      # routes:\r\n      #   - id: cooky-rbac\r\n      #     uri: lb://cooky-rbac\r\n      #     predicates:\r\n      #       - Path=/rbac/**\r\n      #     # filters:\r\n      #     #   - SwaggerHeaderFilter\r\n      #   - id: cooky-auth\r\n      #     uri: lb://cooky-auth\r\n      #     predicates:\r\n      #       - Path=/auth/**\r\n      #   - id: cooky-music\r\n      #     uri: lb://cooky-music\r\n      #     predicates:\r\n      #       - Path=/music/**\r\n      # default-filters:\r\n      #   - StripPrefix=1\r\n\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', 'cfdefc303fc522c546f9ceaac81d463a', '2010-05-05 00:00:00', '2019-12-09 13:55:29', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (15, 37, 'cooky-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      # discovery:\r\n      #   locator:\r\n      #     enabled: true\r\n      #     lower-case-service-id: true\r\n      routes:\r\n        - id: cooky-rbac\r\n          uri: lb://cooky-rbac\r\n          predicates:\r\n            - Path=/rbac/**\r\n          # filters:\r\n          #   - SwaggerHeaderFilter\r\n        - id: cooky-auth\r\n          uri: lb://cooky-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n        - id: cooky-music\r\n          uri: lb://cooky-music\r\n          predicates:\r\n            - Path=/music/**\r\n      default-filters:\r\n        - StripPrefix=1\r\n\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', '8e81f99a46f7e62a7e7020ab2f9f0ac6', '2010-05-05 00:00:00', '2019-12-09 20:15:15', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (15, 38, 'cooky-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      # discovery:\r\n      #   locator:\r\n      #     enabled: true\r\n      #     lower-case-service-id: true\r\n      routes:\r\n        - id: cooky-rbac\r\n          uri: lb://cooky-rbac\r\n          predicates:\r\n            - Path=/rbac/**\r\n          # filters:\r\n          #   - SwaggerHeaderFilter\r\n        - id: cooky-auth\r\n          uri: lb://cooky-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n          filters:\r\n            - AddRequestHeader=X-Request-Foo, Bar\r\n            - name: RequestRateLimiter\r\n              args:\r\n                # 令牌桶每秒填充平均速率\r\n                redis-rate-limiter.replenishRate: 1\r\n                # 令牌桶的上限\r\n                redis-rate-limiter.burstCapacity: 2\r\n                # 使用SpEL表达式从Spring容器中获取Bean对象\r\n                key-resolver: \"#{@pathKeyResolver}\"\r\n        - id: cooky-music\r\n          uri: lb://cooky-music\r\n          predicates:\r\n            - Path=/music/**\r\n      default-filters:\r\n        - StripPrefix=1\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', 'a491605bad9f8753ceac45fde56a2e8b', '2010-05-05 00:00:00', '2019-12-13 14:51:17', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (17, 39, 'cooky-auth.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 9999\r\nspring:\r\n  main:\r\n    allow-bean-definition-overriding: true\r\n  datasource:\r\n    url: jdbc:mysql://${mysql.url}:3306/cooky-cloud-rbac?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8\r\n    username: root\r\n    password: root\r\n    driver-class-name: com.mysql.cj.jdbc.Driver\r\n    hikari:\r\n      pool-name: cooky-auth-pool\r\n      maximum-pool-size: 20\r\n  #      jdbc-url: jdbc:mysql://127.0.0.1:3306/cooky-rbac\r\n  redis:\r\n    database: 0\r\n    host: ${redis.url}\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n\r\n  application:\r\n    name: cooky-auth\r\nmybatis-plus:\r\n  global-config:\r\n    #字段策略 0:\"忽略判断\",1:\"非 NULL 判断\"),2:\"非空判断\"\r\n    field-strategy: 1\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.yuuki.cooky.auth.model.entity\r\nfeign:\r\n  okhttp:\r\n    enabled: true\r\nmanagement:\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n\r\ncooky:\r\n  annoUrl: /captcha\r\n  jwt: false\r\n', '93e0d779b0960e5c37f0f286ad0b61d9', '2010-05-05 00:00:00', '2019-12-18 21:04:28', NULL, '172.17.0.1', 'U', '');
INSERT INTO `his_config_info` VALUES (0, 40, 'cooky-rbac', 'DEFAULT_GROUP', '', 'server:\r\n  port: 8090\r\n\r\n\r\nspring:\r\n  application:\r\n    name: cooky-rbac\r\n  datasource:\r\n    driver-class-name: com.p6spy.engine.spy.P6SpyDriver\r\n    url: jdbc:p6spy:mysql://127.0.0.1:3306/cooky-cloud-rbac?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8\r\n    username: root\r\n    password: root\r\n    hikari:\r\n      pool-name: cooky-rbac-pool\r\n  redis:\r\n    database: 0\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:8848\r\n  jackson:\r\n    date-format: yyyy-MM-dd\r\n    time-zone: GMT+8\r\nsecurity:\r\n  oauth2:\r\n    resource:\r\n      id: cooky-rbac\r\n      user-info-uri: http://localhost:10000/auth/user\r\n#      prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n\r\nmybatis-plus:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.yuuki.cooky.rbac.model.entity\r\n\r\n', '451d616659fc25b27d38db8b0de61f07', '2010-05-05 00:00:00', '2019-12-18 21:05:26', NULL, '172.17.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (0, 41, 'cooky-rbac.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 8090\r\n\r\n\r\nspring:\r\n  application:\r\n    name: cooky-rbac\r\n  datasource:\r\n    driver-class-name: com.p6spy.engine.spy.P6SpyDriver\r\n    url: jdbc:p6spy:mysql://127.0.0.1:3306/cooky-cloud-rbac?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8\r\n    username: root\r\n    password: root\r\n    hikari:\r\n      pool-name: cooky-rbac-pool\r\n  redis:\r\n    database: 0\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:8848\r\n  jackson:\r\n    date-format: yyyy-MM-dd\r\n    time-zone: GMT+8\r\nsecurity:\r\n  oauth2:\r\n    resource:\r\n      id: cooky-rbac\r\n      user-info-uri: http://localhost:10000/auth/user\r\n#      prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n\r\nmybatis-plus:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.yuuki.cooky.rbac.model.entity\r\n\r\n', '451d616659fc25b27d38db8b0de61f07', '2010-05-05 00:00:00', '2019-12-18 21:11:33', NULL, '172.17.0.1', 'I', '');
INSERT INTO `his_config_info` VALUES (19, 42, 'cooky-rbac', 'DEFAULT_GROUP', '', 'server:\r\n  port: 8090\r\n\r\n\r\nspring:\r\n  application:\r\n    name: cooky-rbac\r\n  datasource:\r\n    driver-class-name: com.p6spy.engine.spy.P6SpyDriver\r\n    url: jdbc:p6spy:mysql://127.0.0.1:3306/cooky-cloud-rbac?useSSL=false&serverTimezone=UTC&useUnicode=true&characterEncoding=utf8\r\n    username: root\r\n    password: root\r\n    hikari:\r\n      pool-name: cooky-rbac-pool\r\n  redis:\r\n    database: 0\r\n    host: 127.0.0.1\r\n    port: 6379\r\n    lettuce:\r\n      pool:\r\n        min-idle: 8\r\n        max-idle: 500\r\n        max-active: 2000\r\n        max-wait: 10000\r\n    timeout: 5000\r\n  cloud:\r\n    nacos:\r\n      discovery:\r\n        server-addr: 127.0.0.1:8848\r\n  jackson:\r\n    date-format: yyyy-MM-dd\r\n    time-zone: GMT+8\r\nsecurity:\r\n  oauth2:\r\n    resource:\r\n      id: cooky-rbac\r\n      user-info-uri: http://localhost:10000/auth/user\r\n#      prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'\r\n  endpoint:\r\n    health:\r\n      show-details: always\r\n\r\nmybatis-plus:\r\n  mapper-locations: classpath:mapper/*.xml\r\n  type-aliases-package: com.yuuki.cooky.rbac.model.entity\r\n\r\n', '451d616659fc25b27d38db8b0de61f07', '2010-05-05 00:00:00', '2019-12-18 21:11:41', NULL, '172.17.0.1', 'D', '');
INSERT INTO `his_config_info` VALUES (15, 43, 'cooky-gateway.yaml', 'DEFAULT_GROUP', '', 'server:\r\n  port: 10000\r\n\r\nspring:\r\n  cloud:\r\n    gateway:\r\n      # discovery:\r\n      #   locator:\r\n      #     enabled: true\r\n      #     lower-case-service-id: true\r\n      routes:\r\n        - id: cooky-rbac\r\n          uri: lb://cooky-rbac\r\n          predicates:\r\n            - Path=/rbac/**\r\n          # filters:\r\n          #   - SwaggerHeaderFilter\r\n        - id: cooky-auth\r\n          uri: lb://cooky-auth\r\n          predicates:\r\n            - Path=/auth/**\r\n          filters:\r\n            - AddRequestHeader=X-Request-Foo, Bar\r\n            - name: RequestRateLimiter\r\n              args:\r\n                # 令牌桶每秒填充平均速率\r\n                redis-rate-limiter.replenishRate: 5\r\n                # 令牌桶的上限\r\n                redis-rate-limiter.burstCapacity: 100\r\n                # 使用SpEL表达式从Spring容器中获取Bean对象\r\n                key-resolver: \"#{@pathKeyResolver}\"\r\n        - id: cooky-music\r\n          uri: lb://cooky-music\r\n          predicates:\r\n            - Path=/music/**\r\n      default-filters:\r\n        - StripPrefix=1\r\n  redis:\r\n    host: 127.0.0.1\r\n    port: 6379\r\n# security:\r\n#   oauth2:\r\n#     client:\r\n#       access-token-uri: http://localhost:${server.port}/auth/oauth/token\r\n#       user-authorization-uri: http://localhost:${server.port}/auth/oauth/authorize\r\n#       client-id: cooky\r\n#     resource:\r\n#       user-info-uri: http://localhost:${server.port}/auth/user\r\n#       prefer-token-info: false\r\n\r\nmanagement:\r\n  endpoint:\r\n    gateway:\r\n      enabled: true\r\n    health:\r\n      show-details: always\r\n  endpoints:\r\n    web:\r\n      exposure:\r\n        include: \'*\'', 'ace5eca752a1afc40b43e3a20929ac53', '2010-05-05 00:00:00', '2019-12-18 21:14:48', NULL, '172.17.0.1', 'U', '');

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of roles
-- ----------------------------
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');
INSERT INTO `roles` VALUES ('febs', 'ROLE_ADMIN');

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity`  (
  `id` bigint(20) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '配额，0表示使用默认值',
  `usage` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '使用量',
  `max_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) UNSIGNED NOT NULL DEFAULT 0 COMMENT '最大变更历史数量',
  `gmt_create` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime(0) NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = '租户容量信息表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) CHARACTER SET utf8 COLLATE utf8_bin NULL DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `uk_tenant_info_kptenantid`(`kp`, `tenant_id`) USING BTREE,
  INDEX `idx_tenant_id`(`tenant_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_bin COMMENT = 'tenant_info' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users`  (
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `password` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);

SET FOREIGN_KEY_CHECKS = 1;
