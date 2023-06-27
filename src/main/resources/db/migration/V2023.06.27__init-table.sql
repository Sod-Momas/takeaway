
-- 创建 users 表
CREATE TABLE users (
   username VARCHAR(50) NOT NULL PRIMARY KEY,
   password VARCHAR(100) NOT NULL,
   enabled BOOLEAN NOT NULL
);
COMMENT ON COLUMN "users"."username" IS '用户名';
COMMENT ON COLUMN "users"."password" IS '登录口令';
COMMENT ON COLUMN "users"."enabled" IS '用户启用';
COMMENT ON TABLE "users" IS '用户的信息';

-- 创建 authorities 表
CREATE TABLE authorities (
   username VARCHAR(50) NOT NULL,
   authority VARCHAR(50) NOT NULL,
   CONSTRAINT fk_authorities_users FOREIGN KEY (username)
      REFERENCES users (username) ON DELETE CASCADE
);
COMMENT ON COLUMN "authorities"."username" IS '用户名';
COMMENT ON COLUMN "authorities"."authority" IS '权限';
COMMENT ON TABLE "authorities" IS '用户的权限信息';

-- 创建 groups 表
CREATE TABLE groups (
   id SERIAL PRIMARY KEY,
   group_name VARCHAR(50) NOT NULL
);
COMMENT ON COLUMN "groups"."id" IS 'id';
COMMENT ON COLUMN "groups"."group_name" IS '组名';
COMMENT ON TABLE "groups" IS '用户组的信息';

-- 创建 group_members 表
CREATE TABLE group_members (
   id SERIAL PRIMARY KEY,
   username VARCHAR(50) NOT NULL,
   group_id INT NOT NULL,
   CONSTRAINT fk_group_members_users FOREIGN KEY (username)
      REFERENCES users (username) ON DELETE CASCADE,
   CONSTRAINT fk_group_members_groups FOREIGN KEY (group_id)
      REFERENCES groups (id) ON DELETE CASCADE
);
COMMENT ON COLUMN "group_members"."id" IS 'id';
COMMENT ON COLUMN "group_members"."username" IS '用户名';
COMMENT ON COLUMN "group_members"."group_id" IS '用户组的id';
COMMENT ON TABLE "group_members" IS '用户与用户组之间的关联信息';

-- 创建 persistent_logins 表
CREATE TABLE persistent_logins (
   username VARCHAR(64) NOT NULL,
   series VARCHAR(64) NOT NULL PRIMARY KEY,
   token VARCHAR(64) NOT NULL,
   last_used TIMESTAMP NOT NULL
);
COMMENT ON COLUMN "persistent_logins"."username" IS '用户名';
COMMENT ON COLUMN "persistent_logins"."series" IS '一个唯一标识符，用于区分不同的令牌';
COMMENT ON COLUMN "persistent_logins"."token" IS '存储加密的持久化令牌';
COMMENT ON COLUMN "persistent_logins"."last_used" IS '记录最后使用该令牌的日期和时间';
COMMENT ON TABLE "persistent_logins" IS '用于实现"记住我"功能';
