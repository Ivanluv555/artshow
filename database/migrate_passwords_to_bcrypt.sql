-- ============================================================
-- 密码BCrypt加密迁移脚本
-- ============================================================
-- 用途: 将现有用户的明文密码迁移为BCrypt加密
-- 警告: 由于BCrypt是单向加密，无法从明文直接转换为BCrypt哈希
--       因此本脚本提供两种方案供选择
-- ============================================================

-- 方案A: 强制用户重置密码（推荐）
-- ============================================================
-- 优点: 最安全，不存储任何明文密码
-- 缺点: 用户需要重置密码

-- 1. 添加临时标记字段
ALTER TABLE user ADD COLUMN password_needs_reset TINYINT(1) DEFAULT 0 COMMENT '是否需要重置密码';

-- 2. 标记所有现有用户需要重置密码
UPDATE user SET password_needs_reset = 1 WHERE password_hash NOT LIKE '$2a$%' AND password_hash NOT LIKE '$2b$%' AND password_hash NOT LIKE '$2y$%';

-- 3. 将现有密码设置为随机无效值（防止使用明文密码登录）
-- 注意: 这会使所有明文密码失效
-- UPDATE user SET password_hash = CONCAT('INVALID_', MD5(RAND())) WHERE password_needs_reset = 1;

-- 4. 前端需要检测 password_needs_reset 标志并引导用户重置密码


-- 方案B: 使用默认密码（适用于测试环境）
-- ============================================================
-- 优点: 用户可以立即使用默认密码登录
-- 缺点: 所有用户使用相同的默认密码，不安全

-- 默认密码: "Password123!"
-- 对应的BCrypt哈希（strength=10）: $2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy

-- 将所有非BCrypt密码替换为默认密码的哈希
-- 注意: 仅用于测试环境！生产环境请使用方案A
-- UPDATE user
-- SET password_hash = '$2a$10$N9qo8uLOickgx2ZMRZoMyeIjZAgcfl7p92ldGxad68LJZdL17lhWy'
-- WHERE password_hash NOT LIKE '$2a$%'
--   AND password_hash NOT LIKE '$2b$%'
--   AND password_hash NOT LIKE '$2y$%';


-- 方案C: 保留原密码用于参考（最不推荐）
-- ============================================================
-- 将原密码备份到另一个字段，然后手动通知用户

-- 1. 添加备份字段
-- ALTER TABLE user ADD COLUMN old_password_backup VARCHAR(255) COMMENT '旧密码备份（仅临时使用）';

-- 2. 备份现有密码
-- UPDATE user SET old_password_backup = password_hash WHERE password_hash NOT LIKE '$2a$%';

-- 3. 设置为无效密码
-- UPDATE user SET password_hash = 'NEEDS_RESET' WHERE old_password_backup IS NOT NULL;

-- 4. 手动通知用户或导出密码列表


-- ============================================================
-- 验证脚本
-- ============================================================

-- 检查是否还有非BCrypt密码
SELECT
    COUNT(*) as total_users,
    SUM(CASE WHEN password_hash LIKE '$2a$%' OR password_hash LIKE '$2b$%' OR password_hash LIKE '$2y$%' THEN 1 ELSE 0 END) as bcrypt_users,
    SUM(CASE WHEN password_hash NOT LIKE '$2a$%' AND password_hash NOT LIKE '$2b$%' AND password_hash NOT LIKE '$2y$%' THEN 1 ELSE 0 END) as plaintext_users
FROM user;

-- 查看需要重置密码的用户
SELECT user_id, username, password_needs_reset
FROM user
WHERE password_needs_reset = 1
LIMIT 10;


-- ============================================================
-- 清理脚本（迁移完成后执行）
-- ============================================================

-- 删除临时标记字段（当所有用户都完成密码重置后）
-- ALTER TABLE user DROP COLUMN password_needs_reset;

-- 删除备份字段（如果使用了方案C）
-- ALTER TABLE user DROP COLUMN old_password_backup;


-- ============================================================
-- 使用说明
-- ============================================================
-- 1. 开发/测试环境: 可以使用方案B，设置默认密码
-- 2. 生产环境: 必须使用方案A，强制用户重置密码
-- 3. 执行前请备份数据库
-- 4. 建议在低峰期执行
-- 5. 执行后立即部署新的支持BCrypt的应用版本
