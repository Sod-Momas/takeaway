

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS "sys_dept";
CREATE TABLE "sys_dept"  (
                             "id" bigint NOT NULL  ,
                             "name" varchar(64)   NOT NULL DEFAULT '' ,
                             "parent_id" bigint NOT NULL DEFAULT 0 ,
                             "tree_path" varchar(255)   NULL DEFAULT '' ,
                             "sort" int NULL DEFAULT 0 ,
                             "status" int2 NOT NULL DEFAULT 1 ,
                             "deleted" int2 NULL DEFAULT 0 ,
                             "create_time" DATE NULL DEFAULT NULL ,
                             "update_time" DATE NULL DEFAULT NULL ,
                             "create_by" bigint NULL DEFAULT NULL ,
                             "update_by" bigint NULL DEFAULT NULL ,
                             PRIMARY KEY ("id")
) ;

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO "sys_dept" VALUES (1, '有来技术', 0, '0', 1, 1, 0, NULL, NULL, 1, 1);
INSERT INTO "sys_dept" VALUES (2, '研发部门', 1, '0,1', 1, 1, 0, NULL, '2022-04-19 12:46:37', 2, 2);
INSERT INTO "sys_dept" VALUES (3, '测试部门', 1, '0,1', 1, 1, 0, NULL, '2022-04-19 12:46:37', 2, 2);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS "sys_dict";
CREATE TABLE "sys_dict"  (
                             "id" bigint NOT NULL  ,
                             "type_code" varchar(64)   NULL DEFAULT NULL ,
                             "name" varchar(50)   NULL DEFAULT '' ,
                             "value" varchar(50)   NULL DEFAULT '' ,
                             "sort" int NULL DEFAULT 0 ,
                             "status" int2 NULL DEFAULT 0 ,
                             "defaulted" int2 NULL DEFAULT 0 ,
                             "remark" varchar(255)   NULL DEFAULT '' ,
                             "create_time" DATE NULL DEFAULT NULL ,
                             "update_time" DATE NULL DEFAULT NULL ,
                             PRIMARY KEY ("id")
) ;

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO "sys_dict" VALUES (1, 'gender', '男', '1', 1, 1, 0, NULL, '2019-05-05 13:07:52', '2022-06-12 23:20:39');
INSERT INTO "sys_dict" VALUES (2, 'gender', '女', '2', 2, 1, 0, NULL, '2019-04-19 11:33:00', '2019-07-02 14:23:05');
INSERT INTO "sys_dict" VALUES (3, 'gender', '未知', '0', 1, 1, 0, NULL, '2020-10-17 08:09:31', '2020-10-17 08:09:31');

-- ----------------------------
-- Table structure for sys_dict_type
-- ----------------------------
DROP TABLE IF EXISTS "sys_dict_type";
CREATE TABLE "sys_dict_type"  (
                                  "id" bigint NOT NULL  ,
                                  "name" varchar(50)   NULL DEFAULT '' ,
                                  "code" varchar(50)   NULL DEFAULT '' ,
                                  "status" INT2 NULL DEFAULT 0 ,
                                  "remark" varchar(255)   NULL DEFAULT NULL ,
                                  "create_time" DATE NULL DEFAULT NULL ,
                                  "update_time" DATE NULL DEFAULT NULL ,
                                  PRIMARY KEY ("id")
) ;

-- ----------------------------
-- Records of sys_dict_type
-- ----------------------------
INSERT INTO "sys_dict_type" VALUES (1, '性别', 'gender', 1, NULL, '2019-12-06 19:03:32', '2022-06-12 16:21:28');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS "sys_menu";
CREATE TABLE "sys_menu"  (
                             "id" bigint NOT NULL ,
                             "parent_id" bigint NOT NULL ,
                             "tree_path" varchar(255)   NULL DEFAULT NULL ,
                             "name" varchar(64)   NOT NULL DEFAULT '' ,
                             "type" int2 NOT NULL ,
                             "path" varchar(128)   NULL DEFAULT '' ,
                             "component" varchar(128)   NULL DEFAULT NULL ,
                             "perm" varchar(128)   NULL DEFAULT NULL ,
                             "visible" INT2 NOT NULL DEFAULT 1 ,
                             "sort" int NULL DEFAULT 0 ,
                             "icon" varchar(64)   NULL DEFAULT '' ,
                             "redirect" varchar(128)   NULL DEFAULT NULL ,
                             "create_time" DATE NULL DEFAULT NULL ,
                             "update_time" DATE NULL DEFAULT NULL ,
                             PRIMARY KEY ("id")
) ;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO "sys_menu" VALUES (1, 0, '0', '系统管理', 2, '/system', 'Layout', NULL, 1, 1, 'system', '/system/user', '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO "sys_menu" VALUES (2, 1, '0,1', '用户管理', 1, 'user', 'system/user/index', NULL, 1, 1, 'user', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO "sys_menu" VALUES (3, 1, '0,1', '角色管理', 1, 'role', 'system/role/index', NULL, 1, 2, 'role', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO "sys_menu" VALUES (4, 1, '0,1', '菜单管理', 1, 'menu', 'system/menu/index', NULL, 1, 3, 'menu', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO "sys_menu" VALUES (5, 1, '0,1', '部门管理', 1, 'dept', 'system/dept/index', NULL, 1, 4, 'tree', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO "sys_menu" VALUES (6, 1, '0,1', '字典管理', 1, 'dict', 'system/dict/index', NULL, 1, 5, 'dict', NULL, '2021-08-28 09:12:21', '2021-08-28 09:12:21');
INSERT INTO "sys_menu" VALUES (20, 0, '0', '多级菜单', 2, '/multi-level', 'Layout', NULL, 1, 9, 'multi_level', '/multi-level/multi-level1', '2022-02-16 23:11:00', '2022-02-16 23:11:00');
INSERT INTO "sys_menu" VALUES (21, 20, '0,20', '菜单一级', 2, 'multi-level1', 'demo/multi-level/level1', NULL, 1, 1, '', '/multi-level/multi-level2', '2022-02-16 23:13:38', '2022-02-16 23:13:38');
INSERT INTO "sys_menu" VALUES (22, 21, '0,20,21', '菜单二级', 2, 'multi-level2', 'demo/multi-level/children/level2', NULL, 1, 1, '', '/multi-level/multi-level2/multi-level3-1', '2022-02-16 23:14:23', '2022-02-16 23:14:23');
INSERT INTO "sys_menu" VALUES (23, 22, '0,20,21,22', '菜单三级-1', 1, 'multi-level3-1', 'demo/multi-level/children/children/level3-1', NULL, 1, 1, '', '', '2022-02-16 23:14:51', '2022-02-16 23:14:51');
INSERT INTO "sys_menu" VALUES (24, 22, '0,20,21,22', '菜单三级-2', 1, 'multi-level3-2', 'demo/multi-level/children/children/level3-2', NULL, 1, 2, '', '', '2022-02-16 23:15:08', '2022-02-16 23:15:08');
INSERT INTO "sys_menu" VALUES (26, 0, '0', '外部链接', 2, '/external-link', 'Layout', NULL, 1, 8, 'link', 'noredirect', '2022-02-17 22:51:20', '2022-02-17 22:51:20');
INSERT INTO "sys_menu" VALUES (30, 26, '0,26', 'document', 3, 'https://juejin.cn/post/7228990409909108793', '', NULL, 1, 1, 'document', '', '2022-02-18 00:01:40', '2022-02-18 00:01:40');
INSERT INTO "sys_menu" VALUES (31, 2, '0,1,2', '用户新增', 4, '', NULL, 'sys:user:add', 1, 1, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11');
INSERT INTO "sys_menu" VALUES (32, 2, '0,1,2', '用户编辑', 4, '', NULL, 'sys:user:edit', 1, 2, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11');
INSERT INTO "sys_menu" VALUES (33, 2, '0,1,2', '用户删除', 4, '', NULL, 'sys:user:delete', 1, 3, '', '', '2022-10-23 11:04:08', '2022-10-23 11:04:11');
INSERT INTO "sys_menu" VALUES (36, 0, '0', '组件封装', 2, '/component', 'Layout', NULL, 1, 10, 'menu', '', '2022-10-31 09:18:44', '2022-10-31 09:18:47');
INSERT INTO "sys_menu" VALUES (37, 36, '0,36', '富文本编辑器', 1, 'wang-editor', 'demo/wang-editor', NULL, 1, 1, '', '', NULL, NULL);
INSERT INTO "sys_menu" VALUES (38, 36, '0,36', '图片上传', 1, 'upload', 'demo/upload', NULL, 1, 2, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32');
INSERT INTO "sys_menu" VALUES (39, 36, '0,36', '图标选择器', 1, 'icon-selector', 'demo/icon-selector', NULL, 1, 3, '', '', '2022-11-20 23:16:30', '2022-11-20 23:16:32');
INSERT INTO "sys_menu" VALUES (40, 0, '0', '接口', 2, '/api', 'Layout', NULL, 1, 7, 'api', '', '2022-02-17 22:51:20', '2022-02-17 22:51:20');
INSERT INTO "sys_menu" VALUES (41, 40, '0,40', '接口文档', 1, 'apidoc', 'demo/api-doc', NULL, 1, 1, 'api', '', '2022-02-17 22:51:20', '2022-02-17 22:51:20');
INSERT INTO "sys_menu" VALUES (70, 3, '0,1,3', '角色新增', 4, '', NULL, 'sys:role:add', 1, 1, '', NULL, '2023-05-20 23:39:09', '2023-05-20 23:39:09');
INSERT INTO "sys_menu" VALUES (71, 3, '0,1,3', '角色编辑', 4, '', NULL, 'sys:role:edit', 1, 2, '', NULL, '2023-05-20 23:40:31', '2023-05-20 23:40:31');
INSERT INTO "sys_menu" VALUES (72, 3, '0,1,3', '角色删除', 4, '', NULL, 'sys:role:delete', 1, 3, '', NULL, '2023-05-20 23:41:08', '2023-05-20 23:41:08');
INSERT INTO "sys_menu" VALUES (73, 4, '0,1,4', '菜单新增', 4, '', NULL, 'sys:menu:add', 1, 1, '', NULL, '2023-05-20 23:41:35', '2023-05-20 23:41:35');
INSERT INTO "sys_menu" VALUES (74, 4, '0,1,4', '菜单编辑', 4, '', NULL, 'sys:menu:edit', 1, 3, '', NULL, '2023-05-20 23:41:58', '2023-05-20 23:41:58');
INSERT INTO "sys_menu" VALUES (75, 4, '0,1,4', '菜单删除', 4, '', NULL, 'sys:menu:delete', 1, 3, '', NULL, '2023-05-20 23:44:18', '2023-05-20 23:44:18');
INSERT INTO "sys_menu" VALUES (76, 5, '0,1,5', '部门新增', 4, '', NULL, 'sys:dept:add', 1, 1, '', NULL, '2023-05-20 23:45:00', '2023-05-20 23:45:00');
INSERT INTO "sys_menu" VALUES (77, 5, '0,1,5', '部门编辑', 4, '', NULL, 'sys:dept:edit', 1, 2, '', NULL, '2023-05-20 23:46:16', '2023-05-20 23:46:16');
INSERT INTO "sys_menu" VALUES (78, 5, '0,1,5', '部门删除', 4, '', NULL, 'sys:dept:delete', 1, 3, '', NULL, '2023-05-20 23:46:36', '2023-05-20 23:46:36');
INSERT INTO "sys_menu" VALUES (79, 6, '0,1,6', '字典类型新增', 4, '', NULL, 'sys:dict_type:add', 1, 1, '', NULL, '2023-05-21 00:16:06', '2023-05-21 00:16:06');
INSERT INTO "sys_menu" VALUES (81, 6, '0,1,6', '字典类型编辑', 4, '', NULL, 'sys:dict_type:edit', 1, 2, '', NULL, '2023-05-21 00:27:37', '2023-05-21 00:27:37');
INSERT INTO "sys_menu" VALUES (84, 6, '0,1,6', '字典类型删除', 4, '', NULL, 'sys:dict_type:delete', 1, 3, '', NULL, '2023-05-21 00:29:39', '2023-05-21 00:29:39');
INSERT INTO "sys_menu" VALUES (85, 6, '0,1,6', '字典数据新增', 4, '', NULL, 'sys:dict:add', 1, 4, '', NULL, '2023-05-21 00:46:56', '2023-05-21 00:47:06');
INSERT INTO "sys_menu" VALUES (86, 6, '0,1,6', '字典数据编辑', 4, '', NULL, 'sys:dict:edit', 1, 5, '', NULL, '2023-05-21 00:47:36', '2023-05-21 00:47:36');
INSERT INTO "sys_menu" VALUES (87, 6, '0,1,6', '字典数据删除', 4, '', NULL, 'sys:dict:delete', 1, 6, '', NULL, '2023-05-21 00:48:10', '2023-05-21 00:48:20');
INSERT INTO "sys_menu" VALUES (88, 2, '0,1,2', '重置密码', 4, '', NULL, 'sys:user:reset_pwd', 1, 4, '', NULL, '2023-05-21 00:49:18', '2023-05-21 00:49:18');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS "sys_role";
CREATE TABLE "sys_role"  (
                             "id" bigint NOT NULL ,
                             "name" varchar(64)   NOT NULL DEFAULT '' ,
                             "code" varchar(32)   NULL DEFAULT NULL ,
                             "sort" int NULL DEFAULT NULL ,
                             "status" INT2 NULL DEFAULT 1 ,
                             "data_scope" int2 NULL DEFAULT NULL ,
                             "deleted" INT2 NOT NULL DEFAULT 0 ,
                             "create_time" DATE NULL DEFAULT NULL ,
                             "update_time" DATE NULL DEFAULT NULL ,
                             PRIMARY KEY ("id")
) ;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO "sys_role" VALUES (1, '超级管理员', 'ROOT', 1, 1, 0, 0, '2021-05-21 14:56:51', '2018-12-23 16:00:00');
INSERT INTO "sys_role" VALUES (2, '系统管理员', 'ADMIN', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO "sys_role" VALUES (3, '访问游客', 'GUEST', 3, 1, 2, 0, '2021-05-26 15:49:05', '2019-05-05 16:00:00');
INSERT INTO "sys_role" VALUES (4, '系统管理员1', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO "sys_role" VALUES (5, '系统管理员2', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO "sys_role" VALUES (6, '系统管理员3', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO "sys_role" VALUES (7, '系统管理员4', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO "sys_role" VALUES (8, '系统管理员5', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO "sys_role" VALUES (9, '系统管理员6', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO "sys_role" VALUES (10, '系统管理员7', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO "sys_role" VALUES (11, '系统管理员8', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);
INSERT INTO "sys_role" VALUES (12, '系统管理员9', 'ADMIN1', 2, 1, 1, 0, '2021-03-25 12:39:54', NULL);

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS "sys_role_menu";
CREATE TABLE "sys_role_menu"  (
                                  "role_id" bigint NOT NULL ,
                                  "menu_id" bigint NOT NULL
) ;

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO "sys_role_menu" VALUES (2, 1);
INSERT INTO "sys_role_menu" VALUES (2, 2);
INSERT INTO "sys_role_menu" VALUES (2, 3);
INSERT INTO "sys_role_menu" VALUES (2, 4);
INSERT INTO "sys_role_menu" VALUES (2, 5);
INSERT INTO "sys_role_menu" VALUES (2, 6);
INSERT INTO "sys_role_menu" VALUES (2, 11);
INSERT INTO "sys_role_menu" VALUES (2, 12);
INSERT INTO "sys_role_menu" VALUES (2, 19);
INSERT INTO "sys_role_menu" VALUES (2, 18);
INSERT INTO "sys_role_menu" VALUES (2, 17);
INSERT INTO "sys_role_menu" VALUES (2, 13);
INSERT INTO "sys_role_menu" VALUES (2, 14);
INSERT INTO "sys_role_menu" VALUES (2, 15);
INSERT INTO "sys_role_menu" VALUES (2, 16);
INSERT INTO "sys_role_menu" VALUES (2, 9);
INSERT INTO "sys_role_menu" VALUES (2, 10);
INSERT INTO "sys_role_menu" VALUES (2, 37);
INSERT INTO "sys_role_menu" VALUES (2, 20);
INSERT INTO "sys_role_menu" VALUES (2, 21);
INSERT INTO "sys_role_menu" VALUES (2, 22);
INSERT INTO "sys_role_menu" VALUES (2, 23);
INSERT INTO "sys_role_menu" VALUES (2, 24);
INSERT INTO "sys_role_menu" VALUES (2, 32);
INSERT INTO "sys_role_menu" VALUES (2, 33);
INSERT INTO "sys_role_menu" VALUES (2, 39);
INSERT INTO "sys_role_menu" VALUES (2, 34);
INSERT INTO "sys_role_menu" VALUES (2, 26);
INSERT INTO "sys_role_menu" VALUES (2, 30);
INSERT INTO "sys_role_menu" VALUES (2, 31);
INSERT INTO "sys_role_menu" VALUES (2, 36);
INSERT INTO "sys_role_menu" VALUES (2, 38);
INSERT INTO "sys_role_menu" VALUES (2, 39);
INSERT INTO "sys_role_menu" VALUES (2, 40);
INSERT INTO "sys_role_menu" VALUES (2, 41);
INSERT INTO "sys_role_menu" VALUES (2, 1);
INSERT INTO "sys_role_menu" VALUES (2, 2);
INSERT INTO "sys_role_menu" VALUES (2, 3);
INSERT INTO "sys_role_menu" VALUES (2, 4);
INSERT INTO "sys_role_menu" VALUES (2, 5);
INSERT INTO "sys_role_menu" VALUES (2, 6);
INSERT INTO "sys_role_menu" VALUES (2, 20);
INSERT INTO "sys_role_menu" VALUES (2, 21);
INSERT INTO "sys_role_menu" VALUES (2, 22);
INSERT INTO "sys_role_menu" VALUES (2, 23);
INSERT INTO "sys_role_menu" VALUES (2, 24);
INSERT INTO "sys_role_menu" VALUES (2, 26);
INSERT INTO "sys_role_menu" VALUES (2, 30);
INSERT INTO "sys_role_menu" VALUES (2, 31);
INSERT INTO "sys_role_menu" VALUES (2, 32);
INSERT INTO "sys_role_menu" VALUES (2, 33);
INSERT INTO "sys_role_menu" VALUES (2, 36);
INSERT INTO "sys_role_menu" VALUES (2, 37);
INSERT INTO "sys_role_menu" VALUES (2, 38);
INSERT INTO "sys_role_menu" VALUES (2, 39);
INSERT INTO "sys_role_menu" VALUES (2, 40);
INSERT INTO "sys_role_menu" VALUES (2, 41);
INSERT INTO "sys_role_menu" VALUES (2, 70);
INSERT INTO "sys_role_menu" VALUES (2, 71);
INSERT INTO "sys_role_menu" VALUES (2, 72);
INSERT INTO "sys_role_menu" VALUES (2, 73);
INSERT INTO "sys_role_menu" VALUES (2, 74);
INSERT INTO "sys_role_menu" VALUES (2, 75);
INSERT INTO "sys_role_menu" VALUES (2, 76);
INSERT INTO "sys_role_menu" VALUES (2, 77);
INSERT INTO "sys_role_menu" VALUES (2, 78);
INSERT INTO "sys_role_menu" VALUES (2, 79);
INSERT INTO "sys_role_menu" VALUES (2, 81);
INSERT INTO "sys_role_menu" VALUES (2, 84);
INSERT INTO "sys_role_menu" VALUES (2, 85);
INSERT INTO "sys_role_menu" VALUES (2, 86);
INSERT INTO "sys_role_menu" VALUES (2, 87);
INSERT INTO "sys_role_menu" VALUES (2, 88);

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS "sys_user";
CREATE TABLE "sys_user"  (
                             "id" int NOT NULL ,
                             "username" varchar(64)   NULL DEFAULT NULL ,
                             "nickname" varchar(64)   NULL DEFAULT NULL ,
                             "gender" INT2 NULL DEFAULT 1 ,
                             "password" varchar(100)   NULL DEFAULT NULL ,
                             "dept_id" int NULL DEFAULT NULL ,
                             "avatar" varchar(255)   NULL DEFAULT '' ,
                             "mobile" varchar(20)   NULL DEFAULT NULL ,
                             "status" INT2 NULL DEFAULT 1 ,
                             "email" varchar(128)   NULL DEFAULT NULL ,
                             "deleted" INT2 NULL DEFAULT 0 ,
                             "create_time" DATE NULL DEFAULT NULL ,
                             "update_time" DATE NULL DEFAULT NULL ,
                             PRIMARY KEY ("id")
) ;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO "sys_user" VALUES (1, 'root', '有来技术', 0, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', NULL, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621590365', 1, 'youlaitech@163.com', 0, NULL, NULL);
INSERT INTO "sys_user" VALUES (2, 'admin', '系统管理员', 1, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 1, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621210366', 1, '', 0, '2019-10-10 13:41:22', '2022-07-31 12:39:30');
INSERT INTO "sys_user" VALUES (3, 'test', '测试小用户', 1, '$2a$10$xVWsNOhHrCxh5UbpCE7/HuJ.PAOKcYAqRxD2CO2nVnJS.IAXkr5aq', 3, 'https://oss.youlai.tech/youlai-boot/2023/05/16/811270ef31f548af9cffc026dfc3777b.gif', '17621210366', 1, 'youlaitech@163.com', 0, '2021-06-05 01:31:29', '2021-06-05 01:31:29');
INSERT INTO "sys_user" VALUES (287, '123', '123', 1, '$2a$10$mVoBVqm1837huf7kcN0wS.GVYKEFv0arb7GvzfFXoTyqDlcRzT.6i', 1, '', NULL, 1, NULL, 1, '2023-05-21 14:11:19', '2023-05-21 14:11:25');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS "sys_user_role";
CREATE TABLE "sys_user_role"  (
                                  "user_id" bigint NOT NULL ,
                                  "role_id" bigint NOT NULL ,
                                  PRIMARY KEY ("user_id", "role_id")
) ;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO "sys_user_role" VALUES (1, 1);
INSERT INTO "sys_user_role" VALUES (2, 2);
INSERT INTO "sys_user_role" VALUES (3, 3);
INSERT INTO "sys_user_role" VALUES (287, 2);


COMMENT ON TABLE sys_dept IS '部门表';
COMMENT ON COLUMN sys_dept.id IS 'id';
COMMENT ON COLUMN sys_dept.name IS '部门名称';
COMMENT ON COLUMN sys_dept.parent_id IS '父节点id';
COMMENT ON COLUMN sys_dept.tree_path IS '父节点id路径';
COMMENT ON COLUMN sys_dept.sort IS '显示顺序';
COMMENT ON COLUMN sys_dept.status IS '状态(1:正常;0:禁用)';
COMMENT ON COLUMN sys_dept.deleted IS '逻辑删除标识(1:已删除;0:未删除)';
COMMENT ON COLUMN sys_dept.create_time IS '创建时间';
COMMENT ON COLUMN sys_dept.update_time IS '更新时间';
COMMENT ON COLUMN sys_dept.create_by IS '创建人ID';
COMMENT ON COLUMN sys_dept.update_by IS '修改人ID';

COMMENT ON TABLE sys_dict IS '字典数据表';
COMMENT ON COLUMN sys_dict.id IS '主键';
COMMENT ON COLUMN sys_dict.type_code IS '字典类型编码';
COMMENT ON COLUMN sys_dict.name IS '字典项名称';
COMMENT ON COLUMN sys_dict.value IS '字典项值';
COMMENT ON COLUMN sys_dict.sort IS '排序';
COMMENT ON COLUMN sys_dict.status IS '状态(1:正常;0:IS 禁用)';
COMMENT ON COLUMN sys_dict.defaulted IS '是否默认(1:是;0:IS 否)';
COMMENT ON COLUMN sys_dict.remark IS '备注';
COMMENT ON COLUMN sys_dict.create_time IS '创建时间';
COMMENT ON COLUMN sys_dict.update_time IS '更新时间';


COMMENT ON TABLE sys_dict_type IS '字典类型表';
COMMENT ON COLUMN sys_dict_type.id IS '主键';
COMMENT ON COLUMN sys_dict_type.name IS '类型名称';
COMMENT ON COLUMN sys_dict_type.code IS '类型编码';
COMMENT ON COLUMN sys_dict_type.status IS '状态(0:正常;1:禁用)';
COMMENT ON COLUMN sys_dict_type.remark IS '备注';
COMMENT ON COLUMN sys_dict_type.create_time IS '创建时间';
COMMENT ON COLUMN sys_dict_type.update_time IS '更新时间';

COMMENT ON TABLE sys_menu IS '菜单管理';
COMMENT ON COLUMN sys_menu.id IS '主键';
COMMENT ON COLUMN sys_menu.parent_id IS '父菜单ID';
COMMENT ON COLUMN sys_menu.tree_path IS '父节点ID路径';
COMMENT ON COLUMN sys_menu.name IS '菜单名称';
COMMENT ON COLUMN sys_menu.type IS '菜单类型(1:菜单；2:目录；3:外链；4:按钮)';
COMMENT ON COLUMN sys_menu.path IS '路由路径(浏览器地址栏路径)';
COMMENT ON COLUMN sys_menu.component IS '组件路径(vue页面完整路径，省略.vue后缀)';
COMMENT ON COLUMN sys_menu.perm IS '权限标识';
COMMENT ON COLUMN sys_menu.visible IS '显示状态(1-显示;0-隐藏)';
COMMENT ON COLUMN sys_menu.sort IS '排序';
COMMENT ON COLUMN sys_menu.icon IS '菜单图标';
COMMENT ON COLUMN sys_menu.redirect IS '跳转路径';
COMMENT ON COLUMN sys_menu.create_time IS '创建时间';
COMMENT ON COLUMN sys_menu.update_time IS '更新时间';


COMMENT ON TABLE sys_role IS '角色表';
COMMENT ON COLUMN sys_role.id IS '主键';
COMMENT ON COLUMN sys_role.name IS  '角色名称';
COMMENT ON COLUMN sys_role.code IS  '角色编码';
COMMENT ON COLUMN sys_role.sort IS  '显示顺序';
COMMENT ON COLUMN sys_role.status IS  '角色状态(1-正常；0-停用)';
COMMENT ON COLUMN sys_role.data_scope IS  '数据权限(0-所有数据；1-部门及子部门数据；2-本部门数据；3-本人数据)';
COMMENT ON COLUMN sys_role.deleted IS  '逻辑删除标识(0-未删除；1-已删除)';
COMMENT ON COLUMN sys_role.create_time IS  '更新时间';
COMMENT ON COLUMN sys_role.update_time IS  '创建时间';

COMMENT ON TABLE sys_role_menu IS '角色表';
COMMENT ON COLUMN sys_role_menu.role_id IS '角色ID';
COMMENT ON COLUMN sys_role_menu.menu_id IS '菜单ID';

COMMENT ON TABLE sys_user IS '角色表';
COMMENT ON COLUMN sys_user.id IS '主键';
COMMENT ON COLUMN sys_user.username IS '用户名';
COMMENT ON COLUMN sys_user.nickname IS '昵称';
COMMENT ON COLUMN sys_user.gender IS '性别((1:男;2:女))';
COMMENT ON COLUMN sys_user.password IS '密码';
COMMENT ON COLUMN sys_user.dept_id IS '部门ID';
COMMENT ON COLUMN sys_user.avatar IS '用户头像';
COMMENT ON COLUMN sys_user.mobile IS '联系方式';
COMMENT ON COLUMN sys_user.status IS '用户状态((1:正常;0:禁用))';
COMMENT ON COLUMN sys_user.email IS '用户邮箱';
COMMENT ON COLUMN sys_user.deleted IS '逻辑删除标识(0:未删除;1:已删除)';
COMMENT ON COLUMN sys_user.create_time IS '创建时间';
COMMENT ON COLUMN sys_user.update_time IS '更新时间';

COMMENT ON TABLE sys_user_role IS '用户和角色关联表';
COMMENT ON COLUMN sys_user_role.user_id IS '用户ID';
COMMENT ON COLUMN sys_user_role.role_id IS '角色ID';
