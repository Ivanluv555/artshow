-- 补充实体类中缺失的字段
-- 本脚本用于为已有实体类添加DDL中定义但实体类中缺失的字段

USE `artshow`;

-- 1. Product 表 - 添加时间戳字段
ALTER TABLE `product`
ADD COLUMN IF NOT EXISTS `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
ADD COLUMN IF NOT EXISTS `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间';

-- 2. User 表 - 添加创建时间
ALTER TABLE `user`
ADD COLUMN IF NOT EXISTS `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';

-- 3. art_category 表 - 添加创建时间
ALTER TABLE `art_category`
ADD COLUMN IF NOT EXISTS `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';

-- 4. art_subcategory 表 - 添加创建时间
ALTER TABLE `art_subcategory`
ADD COLUMN IF NOT EXISTS `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';

-- 5. badge 表 - 添加创建时间
ALTER TABLE `badge`
ADD COLUMN IF NOT EXISTS `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';

-- 6. course_instructor 表 - 添加创建时间
ALTER TABLE `course_instructor`
ADD COLUMN IF NOT EXISTS `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';

-- 7. course 表 - 添加时间戳字段
ALTER TABLE `course`
ADD COLUMN IF NOT EXISTS `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
ADD COLUMN IF NOT EXISTS `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间';

-- 8. course_outline 表 - 添加创建时间
ALTER TABLE `course_outline`
ADD COLUMN IF NOT EXISTS `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';

-- 9. shopping_cart_item 表 - 添加创建时间
ALTER TABLE `shopping_cart_item`
ADD COLUMN IF NOT EXISTS `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间';

-- 10. user_address 表 - 添加创建时间
ALTER TABLE `user_address`
ADD COLUMN IF NOT EXISTS `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间';

-- 11. user_course_enrollment 表 - 添加注册时间
ALTER TABLE `user_course_enrollment`
ADD COLUMN IF NOT EXISTS `enrolled_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间';

-- 12. user_course_chapter_completed 表 - 添加完成时间
ALTER TABLE `user_course_chapter_completed`
ADD COLUMN IF NOT EXISTS `completed_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '完成时间';

-- 验证添加的字段
SELECT 'product' AS table_name, COUNT(*) AS column_count
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'product' AND COLUMN_NAME IN ('created_at', 'updated_at')
UNION ALL
SELECT 'user', COUNT(*)
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'user' AND COLUMN_NAME = 'created_at'
UNION ALL
SELECT 'art_category', COUNT(*)
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'art_category' AND COLUMN_NAME = 'created_at'
UNION ALL
SELECT 'art_subcategory', COUNT(*)
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'art_subcategory' AND COLUMN_NAME = 'created_at'
UNION ALL
SELECT 'badge', COUNT(*)
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'badge' AND COLUMN_NAME = 'created_at'
UNION ALL
SELECT 'course_instructor', COUNT(*)
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'course_instructor' AND COLUMN_NAME = 'created_at'
UNION ALL
SELECT 'course', COUNT(*)
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'course' AND COLUMN_NAME IN ('created_at', 'updated_at')
UNION ALL
SELECT 'course_outline', COUNT(*)
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'course_outline' AND COLUMN_NAME = 'created_at'
UNION ALL
SELECT 'shopping_cart_item', COUNT(*)
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'shopping_cart_item' AND COLUMN_NAME = 'created_at'
UNION ALL
SELECT 'user_address', COUNT(*)
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'user_address' AND COLUMN_NAME = 'created_at'
UNION ALL
SELECT 'user_course_enrollment', COUNT(*)
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'user_course_enrollment' AND COLUMN_NAME = 'enrolled_at'
UNION ALL
SELECT 'user_course_chapter_completed', COUNT(*)
FROM information_schema.COLUMNS
WHERE TABLE_SCHEMA = 'artshow' AND TABLE_NAME = 'user_course_chapter_completed' AND COLUMN_NAME = 'completed_at';
