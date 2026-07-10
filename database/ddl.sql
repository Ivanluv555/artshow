-- ============================================================================
-- 数据库完整重建脚本
-- ============================================================================
-- 版本: 2.0.0
-- 日期: 2026-07-08
-- 警告: 此脚本会删除现有数据库，请确保已备份数据！
-- 使用方法: mysql -u root -p < rebuild_database.sql
-- ============================================================================

-- 显示警告信息
SELECT '警告：即将删除并重建artshow数据库！' AS WARNING;
SELECT '请确保已备份重要数据！' AS IMPORTANT;
SELECT '按Ctrl+C取消，或等待5秒后自动继续...' AS ACTION;

-- 删除已存在的数据库
DROP DATABASE IF EXISTS `artshow`;

-- 创建数据库
CREATE DATABASE `artshow` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;

-- 使用数据库
USE `artshow`;

SELECT '数据库artshow已创建，开始创建表结构...' AS STATUS;

-- ============================================================================
-- 基础表（无外键依赖）
-- ============================================================================

-- 用户表
CREATE TABLE `user` (
                        `user_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户ID',
                        `username` VARCHAR(50) NOT NULL COMMENT '用户名',
                        `password_hash` VARCHAR(255) NOT NULL COMMENT '密码哈希',
                        `nickname` VARCHAR(100) DEFAULT NULL COMMENT '昵称',
                        `avatar_url` VARCHAR(500) DEFAULT NULL COMMENT '头像URL',
                        `bio` TEXT COMMENT '个人简介',
                        `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        PRIMARY KEY (`user_id`),
                        UNIQUE KEY `username` (`username`),
                        KEY `idx_username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- 艺术品分类表
CREATE TABLE `art_category` (
                                `category_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '分类ID',
                                `category_name` VARCHAR(100) NOT NULL COMMENT '分类名称',
                                `icon_url` VARCHAR(500) DEFAULT NULL COMMENT '图标URL',
                                `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='艺术品分类表';

-- 徽章表
CREATE TABLE `badge` (
                         `badge_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '徽章ID',
                         `name` VARCHAR(100) NOT NULL COMMENT '徽章名称',
                         `description` TEXT COMMENT '徽章描述',
                         `icon_url` VARCHAR(500) DEFAULT NULL COMMENT '图标URL',
                         `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         PRIMARY KEY (`badge_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='徽章表';

-- 讲师表
CREATE TABLE `course_instructor` (
                                     `instructor_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '讲师ID',
                                     `name` VARCHAR(100) NOT NULL COMMENT '讲师姓名',
                                     `title` VARCHAR(100) DEFAULT NULL COMMENT '职称',
                                     `avatar_url` VARCHAR(500) DEFAULT NULL COMMENT '头像URL',
                                     `bio` TEXT COMMENT '讲师简介',
                                     `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                     PRIMARY KEY (`instructor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='讲师表';

SELECT '基础表创建完成' AS STATUS;

-- ============================================================================
-- 二级表（依赖基础表）
-- ============================================================================

-- 艺术品子分类表
CREATE TABLE `art_subcategory` (
                                   `subcategory_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '子分类ID',
                                   `category_id` BIGINT NOT NULL COMMENT '父分类ID',
                                   `name` VARCHAR(100) NOT NULL COMMENT '子分类名称',
                                   `cover_image_url` VARCHAR(500) DEFAULT NULL COMMENT '封面图片URL',
                                   `introduction` TEXT COMMENT '简介',
                                   `history` TEXT COMMENT '历史',
                                   `features` TEXT COMMENT '特点',
                                   `cultural_meaning` TEXT COMMENT '文化意义',
                                   `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                   PRIMARY KEY (`subcategory_id`),
                                   KEY `idx_category_id` (`category_id`),
                                   CONSTRAINT `art_subcategory_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `art_category` (`category_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='艺术品子分类表';

-- 用户地址表
CREATE TABLE `user_address` (
                                `address_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '地址ID',
                                `user_id` BIGINT NOT NULL COMMENT '用户ID',
                                `recipient_name` VARCHAR(50) NOT NULL COMMENT '收货人姓名',
                                `phone` VARCHAR(20) NOT NULL COMMENT '联系电话',
                                `region` VARCHAR(100) DEFAULT NULL COMMENT '地区',
                                `detail` VARCHAR(500) NOT NULL COMMENT '详细地址',
                                `is_default` TINYINT(1) DEFAULT '0' COMMENT '是否默认地址',
                                `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                PRIMARY KEY (`address_id`),
                                KEY `idx_user_id` (`user_id`),
                                CONSTRAINT `user_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户地址表';

-- 用户徽章表
CREATE TABLE `user_badge` (
                              `user_badge_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '用户徽章ID',
                              `user_id` BIGINT NOT NULL COMMENT '用户ID',
                              `badge_id` BIGINT NOT NULL COMMENT '徽章ID',
                              `earned_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '获得时间',
                              PRIMARY KEY (`user_badge_id`),
                              UNIQUE KEY `uk_user_badge` (`user_id`,`badge_id`),
                              KEY `idx_user_id` (`user_id`),
                              KEY `idx_badge_id` (`badge_id`),
                              CONSTRAINT `user_badge_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
                              CONSTRAINT `user_badge_ibfk_2` FOREIGN KEY (`badge_id`) REFERENCES `badge` (`badge_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户徽章表';

-- 产品表
CREATE TABLE `product` (
                           `product_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '产品ID',
                           `seller_id` BIGINT NOT NULL COMMENT '卖家ID',
                           `name` VARCHAR(200) NOT NULL COMMENT '产品名称',
                           `price` DECIMAL(10,2) NOT NULL COMMENT '价格',
                           `stock` INT DEFAULT '0' COMMENT '库存数量',
                           `cover_image_url` VARCHAR(500) DEFAULT NULL COMMENT '封面图片URL',
                           `description` TEXT COMMENT '产品描述',
                           `is_certified` TINYINT(1) DEFAULT '0' COMMENT '是否认证',
                           `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                           `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                           PRIMARY KEY (`product_id`),
                           KEY `idx_seller_id` (`seller_id`),
                           CONSTRAINT `product_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品表';

-- 课程表
CREATE TABLE `course` (
                          `course_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '课程ID',
                          `instructor_id` BIGINT NOT NULL COMMENT '讲师ID',
                          `title` VARCHAR(200) NOT NULL COMMENT '课程标题',
                          `cover_image_url` VARCHAR(500) DEFAULT NULL COMMENT '封面图片',
                          `price` DECIMAL(10,2) DEFAULT '0.00' COMMENT '课程价格',
                          `type` VARCHAR(50) DEFAULT 'free' COMMENT '课程类型：free-免费，paid-付费',
                          `student_count` INT DEFAULT '0' COMMENT '学生数量',
                          `description` TEXT COMMENT '课程描述',
                          `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                          `updated_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
                          PRIMARY KEY (`course_id`),
                          KEY `idx_instructor_id` (`instructor_id`),
                          KEY `idx_course_type` (`type`),
                          CONSTRAINT `course_ibfk_1` FOREIGN KEY (`instructor_id`) REFERENCES `course_instructor` (`instructor_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';

-- 帖子表
CREATE TABLE `post` (
                        `post_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
                        `user_id` BIGINT NOT NULL COMMENT '用户ID',
                        `subcategory_id` BIGINT DEFAULT NULL COMMENT '子分类ID',
                        `title` VARCHAR(200) DEFAULT NULL COMMENT '标题',
                        `description` TEXT COMMENT '描述',
                        `image_url` VARCHAR(500) DEFAULT NULL COMMENT '图片URL',
                        `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                        PRIMARY KEY (`post_id`),
                        KEY `idx_user_id` (`user_id`),
                        KEY `idx_subcategory_id` (`subcategory_id`),
                        CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
                        CONSTRAINT `post_ibfk_2` FOREIGN KEY (`subcategory_id`) REFERENCES `art_subcategory` (`subcategory_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='帖子表';

-- 订单表
CREATE TABLE `order` (
                         `order_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单ID',
                         `order_number` VARCHAR(50) NOT NULL COMMENT '订单号',
                         `user_id` BIGINT NOT NULL COMMENT '买家ID',
                         `total_price` DECIMAL(10,2) NOT NULL COMMENT '订单总金额',
                         `status` VARCHAR(20) DEFAULT 'pending' COMMENT '订单状态：pending-待支付，paid-已支付，shipped-已发货，completed-已完成，cancelled-已取消',
                         `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                         PRIMARY KEY (`order_id`),
                         UNIQUE KEY `order_number` (`order_number`),
                         KEY `idx_user_id` (`user_id`),
                         KEY `idx_order_number` (`order_number`),
                         KEY `idx_status` (`status`),
                         KEY `idx_order_status_created` (`status`, `created_at`),
                         CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

SELECT '二级表创建完成' AS STATUS;

-- ============================================================================
-- 三级表（依赖二级表）
-- ============================================================================

-- 购物车表
CREATE TABLE `shopping_cart_item` (
                                      `cart_item_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '购物车项ID',
                                      `user_id` BIGINT NOT NULL COMMENT '用户ID',
                                      `product_id` BIGINT NOT NULL COMMENT '产品ID',
                                      `quantity` INT NOT NULL DEFAULT '1' COMMENT '数量',
                                      `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
                                      PRIMARY KEY (`cart_item_id`),
                                      UNIQUE KEY `uk_user_product` (`user_id`,`product_id`),
                                      KEY `idx_user_id` (`user_id`),
                                      KEY `idx_product_id` (`product_id`),
                                      CONSTRAINT `shopping_cart_item_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
                                      CONSTRAINT `shopping_cart_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车表';

-- 课程章节表
CREATE TABLE `course_outline` (
                                  `chapter_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '章节ID',
                                  `course_id` BIGINT NOT NULL COMMENT '课程ID',
                                  `chapter_stand_id` INT DEFAULT NULL COMMENT '章节标准ID',
                                  `title` VARCHAR(200) NOT NULL COMMENT '章节标题',
                                  `content` TEXT COMMENT '章节内容',
                                  `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                  PRIMARY KEY (`chapter_id`),
                                  KEY `idx_course_id` (`course_id`),
                                  CONSTRAINT `course_outline_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程章节表';

-- 用户课程注册表
CREATE TABLE `user_course_enrollment` (
                                          `enrollment_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '注册ID',
                                          `user_id` BIGINT NOT NULL COMMENT '用户ID',
                                          `course_id` BIGINT NOT NULL COMMENT '课程ID',
                                          `certificate_awarded` TINYINT(1) DEFAULT '0' COMMENT '是否颁发证书',
                                          `award_date` TIMESTAMP NULL DEFAULT NULL COMMENT '颁发日期',
                                          `enrolled_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
                                          PRIMARY KEY (`enrollment_id`),
                                          UNIQUE KEY `uk_user_course` (`user_id`,`course_id`),
                                          KEY `idx_user_id` (`user_id`),
                                          KEY `idx_course_id` (`course_id`),
                                          CONSTRAINT `user_course_enrollment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
                                          CONSTRAINT `user_course_enrollment_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户课程注册表';

-- 订单项表（支持商品和课程）
CREATE TABLE `order_item` (
                              `order_item_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '订单项ID',
                              `order_id` BIGINT NOT NULL COMMENT '订单ID',
                              `product_id` BIGINT DEFAULT NULL COMMENT '产品ID（商品订单）',
                              `course_id` BIGINT DEFAULT NULL COMMENT '课程ID（课程订单）',
                              `quantity` INT NOT NULL DEFAULT '1' COMMENT '数量',
                              `price_at_purchase` DECIMAL(10,2) NOT NULL COMMENT '购买时单价',
                              `product_name_snapshot` VARCHAR(200) DEFAULT NULL COMMENT '产品/课程名称快照',
                              `product_image_snapshot` VARCHAR(500) DEFAULT NULL COMMENT '产品/课程图片快照',
                              PRIMARY KEY (`order_item_id`),
                              KEY `idx_order_id` (`order_id`),
                              KEY `idx_product_id` (`product_id`),
                              KEY `idx_course_id` (`course_id`),
                              CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE,
                              CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE SET NULL,
                              CONSTRAINT `order_item_ibfk_3` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单项表（支持商品和课程，product_id和course_id至少一个不为空）';

-- 订单收货地址表
CREATE TABLE `order_shipping_address` (
                                          `shipping_address_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '收货地址ID',
                                          `order_id` BIGINT NOT NULL COMMENT '订单ID',
                                          `recipient_name` VARCHAR(50) NOT NULL COMMENT '收货人',
                                          `phone` VARCHAR(20) NOT NULL COMMENT '联系电话',
                                          `region` VARCHAR(100) DEFAULT NULL COMMENT '地区',
                                          `detail` VARCHAR(500) NOT NULL COMMENT '详细地址',
                                          PRIMARY KEY (`shipping_address_id`),
                                          KEY `idx_order_id` (`order_id`),
                                          CONSTRAINT `order_shipping_address_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单收货地址表';

-- 评论表
CREATE TABLE `post_comment` (
                                `comment_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '评论ID',
                                `post_id` BIGINT NOT NULL COMMENT '帖子ID',
                                `user_id` BIGINT NOT NULL COMMENT '用户ID',
                                `content` TEXT NOT NULL COMMENT '评论内容',
                                `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                                PRIMARY KEY (`comment_id`),
                                KEY `idx_post_id` (`post_id`),
                                KEY `idx_user_id` (`user_id`),
                                CONSTRAINT `post_comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE,
                                CONSTRAINT `post_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';

-- 点赞表
CREATE TABLE `post_like` (
                             `like_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
                             `post_id` BIGINT NOT NULL COMMENT '帖子ID',
                             `user_id` BIGINT NOT NULL COMMENT '用户ID',
                             `created_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
                             PRIMARY KEY (`like_id`),
                             UNIQUE KEY `uk_user_post` (`user_id`,`post_id`),
                             KEY `idx_post_id` (`post_id`),
                             KEY `idx_user_id` (`user_id`),
                             CONSTRAINT `post_like_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE,
                             CONSTRAINT `post_like_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='点赞表';

-- 收藏表
CREATE TABLE `post_collection` (
                                   `collection_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
                                   `post_id` BIGINT NOT NULL COMMENT '帖子ID',
                                   `user_id` BIGINT NOT NULL COMMENT '用户ID',
                                   `create_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
                                   PRIMARY KEY (`collection_id`),
                                   UNIQUE KEY `uk_user_post` (`user_id`,`post_id`),
                                   KEY `idx_post_id` (`post_id`),
                                   KEY `idx_user_id` (`user_id`),
                                   CONSTRAINT `post_collection_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE,
                                   CONSTRAINT `post_collection_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收藏表';

SELECT '三级表创建完成' AS STATUS;

-- ============================================================================
-- 四级表（依赖三级表）
-- ============================================================================

-- 用户章节完成记录表
CREATE TABLE `user_course_chapter_completed` (
                                                 `completion_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '完成记录ID',
                                                 `enrollment_id` BIGINT NOT NULL COMMENT '注册ID',
                                                 `chapter_id` BIGINT NOT NULL COMMENT '章节ID',
                                                 `completed_at` TIMESTAMP NULL DEFAULT CURRENT_TIMESTAMP COMMENT '完成时间',
                                                 PRIMARY KEY (`completion_id`),
                                                 UNIQUE KEY `uk_enrollment_chapter` (`enrollment_id`,`chapter_id`),
                                                 KEY `idx_enrollment_id` (`enrollment_id`),
                                                 KEY `idx_chapter_id` (`chapter_id`),
                                                 CONSTRAINT `user_course_chapter_completed_ibfk_1` FOREIGN KEY (`enrollment_id`) REFERENCES `user_course_enrollment` (`enrollment_id`) ON DELETE CASCADE,
                                                 CONSTRAINT `user_course_chapter_completed_ibfk_2` FOREIGN KEY (`chapter_id`) REFERENCES `course_outline` (`chapter_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户章节完成记录表';

SELECT '四级表创建完成' AS STATUS;

-- ============================================================================
-- 视图创建
-- ============================================================================

-- 订单详情视图
CREATE OR REPLACE VIEW v_order_detail AS
SELECT
    o.order_id,
    o.order_number,
    o.user_id,
    u.username,
    u.nickname,
    o.total_price,
    o.status,
    o.created_at,
    osa.recipient_name,
    osa.phone,
    osa.region,
    osa.detail AS address_detail
FROM `order` o
         LEFT JOIN `user` u ON o.user_id = u.user_id
         LEFT JOIN `order_shipping_address` osa ON o.order_id = osa.order_id;

-- 订单项详情视图
CREATE OR REPLACE VIEW v_order_item_detail AS
SELECT
    oi.order_item_id,
    oi.order_id,
    oi.product_id,
    oi.course_id,
    oi.quantity,
    oi.price_at_purchase,
    oi.product_name_snapshot,
    oi.product_image_snapshot,
    CASE
        WHEN oi.product_id IS NOT NULL THEN 'product'
        WHEN oi.course_id IS NOT NULL THEN 'course'
        ELSE 'unknown'
        END AS item_type
FROM order_item oi;

-- 用户课程进度视图
CREATE OR REPLACE VIEW v_user_course_progress AS
SELECT
    uce.enrollment_id,
    uce.user_id,
    u.username,
    u.nickname,
    uce.course_id,
    c.title AS course_title,
    COUNT(DISTINCT co.chapter_id) AS total_chapters,
    COUNT(DISTINCT uccc.chapter_id) AS completed_chapters,
    ROUND(COUNT(DISTINCT uccc.chapter_id) * 100.0 / NULLIF(COUNT(DISTINCT co.chapter_id), 0), 2) AS progress_percentage,
    uce.certificate_awarded,
    uce.enrolled_at
FROM user_course_enrollment uce
         LEFT JOIN `user` u ON uce.user_id = u.user_id
         LEFT JOIN course c ON uce.course_id = c.course_id
         LEFT JOIN course_outline co ON c.course_id = co.course_id
         LEFT JOIN user_course_chapter_completed uccc ON uce.enrollment_id = uccc.enrollment_id
GROUP BY uce.enrollment_id, uce.user_id, u.username, u.nickname, uce.course_id, c.title, uce.certificate_awarded, uce.enrolled_at;

SELECT '视图创建完成' AS STATUS;

-- ============================================================================
-- 完成提示
-- ============================================================================
SELECT
    '数据库重建完成！' AS message,
    '版本: 2.0.0' AS version,
    '更新日期: 2026-07-08' AS update_date,
    CONCAT('共创建 ',
           (SELECT COUNT(*) FROM information_schema.TABLES WHERE TABLE_SCHEMA = 'artshow' AND TABLE_TYPE = 'BASE TABLE'),
           ' 个表') AS tables_created,
    CONCAT('共创建 ',
           (SELECT COUNT(*) FROM information_schema.VIEWS WHERE TABLE_SCHEMA = 'artshow'),
           ' 个视图') AS views_created;

-- 显示所有表
SHOW TABLES;
