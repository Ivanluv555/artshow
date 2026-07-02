CREATE DATABASE IF NOT EXISTS `artshow`;

-- artshow.art_category definition

CREATE TABLE `art_category` (
  `category_id` int NOT NULL AUTO_INCREMENT COMMENT '分类ID',
  `category_name` varchar(100) NOT NULL COMMENT '分类名称',
  `icon_url` varchar(500) DEFAULT NULL COMMENT '图标URL',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='艺术品分类表';

-- artshow.art_subcategory definition

CREATE TABLE `art_subcategory` (
  `subcategory_id` int NOT NULL AUTO_INCREMENT COMMENT '子分类ID',
  `category_id` int NOT NULL COMMENT '父分类ID',
  `name` varchar(100) NOT NULL COMMENT '子分类名称',
  `cover_image_url` varchar(500) DEFAULT NULL COMMENT '封面图片URL',
  `introduction` text COMMENT '简介',
  `history` text COMMENT '历史',
  `features` text COMMENT '特点',
  `cultural_meaning` text COMMENT '文化意义',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`subcategory_id`),
  KEY `idx_category_id` (`category_id`),
  CONSTRAINT `art_subcategory_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `art_category` (`category_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='艺术品子分类表';

-- artshow.badge definition

CREATE TABLE `badge` (
  `badge_id` int NOT NULL AUTO_INCREMENT COMMENT '徽章ID',
  `name` varchar(100) NOT NULL COMMENT '徽章名称',
  `description` text COMMENT '徽章描述',
  `icon_url` varchar(500) DEFAULT NULL COMMENT '图标URL',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`badge_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='徽章表';

-- artshow.course definition

CREATE TABLE `course` (
  `course_id` int NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `instructor_id` int NOT NULL COMMENT '讲师ID',
  `title` varchar(200) NOT NULL COMMENT '课程标题',
  `cover_image_url` varchar(500) DEFAULT NULL COMMENT '封面图片',
  `price` decimal(10,2) DEFAULT '0.00' COMMENT '课程价格',
  `type` varchar(50) DEFAULT NULL COMMENT '课程类型',
  `student_count` int DEFAULT '0' COMMENT '学生数量',
  `description` text COMMENT '课程描述',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`course_id`),
  KEY `idx_instructor_id` (`instructor_id`),
  CONSTRAINT `course_ibfk_1` FOREIGN KEY (`instructor_id`) REFERENCES `course_instructor` (`instructor_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程表';

-- artshow.course_instructor definition

CREATE TABLE `course_instructor` (
  `instructor_id` int NOT NULL AUTO_INCREMENT COMMENT '讲师ID',
  `name` varchar(100) NOT NULL COMMENT '讲师姓名',
  `title` varchar(100) DEFAULT NULL COMMENT '职称',
  `avatar_url` varchar(500) DEFAULT NULL COMMENT '头像URL',
  `bio` text COMMENT '讲师简介',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`instructor_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='讲师表';

-- artshow.course_outline definition

CREATE TABLE `course_outline` (
  `chapter_id` int NOT NULL AUTO_INCREMENT COMMENT '章节ID',
  `course_id` int NOT NULL COMMENT '课程ID',
  `chapter_stand_id` int DEFAULT NULL COMMENT '章节标准ID',
  `title` varchar(200) NOT NULL COMMENT '章节标题',
  `content` text COMMENT '章节内容',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`chapter_id`),
  KEY `idx_course_id` (`course_id`),
  CONSTRAINT `course_outline_ibfk_1` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='课程章节表';

-- artshow.`order` definition

CREATE TABLE `order` (
  `order_id` int NOT NULL AUTO_INCREMENT COMMENT '订单ID',
  `order_number` varchar(50) NOT NULL COMMENT '订单号',
  `user_id` int NOT NULL COMMENT '买家ID',
  `total_price` decimal(10,2) NOT NULL COMMENT '订单总金额',
  `status` varchar(20) DEFAULT 'pending' COMMENT '订单状态',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`order_id`),
  UNIQUE KEY `order_number` (`order_number`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_order_number` (`order_number`),
  CONSTRAINT `order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

-- artshow.order_item definition

CREATE TABLE `order_item` (
  `order_item_id` int NOT NULL AUTO_INCREMENT COMMENT '订单项ID',
  `order_id` int NOT NULL COMMENT '订单ID',
  `product_id` int NOT NULL COMMENT '产品ID',
  `quantity` int NOT NULL DEFAULT '1' COMMENT '数量',
  `price_at_purchase` decimal(10,2) NOT NULL COMMENT '购买时单价',
  `product_name_snapshot` varchar(200) DEFAULT NULL COMMENT '产品名称快照',
  `product_image_snapshot` varchar(500) DEFAULT NULL COMMENT '产品图片快照',
  PRIMARY KEY (`order_item_id`),
  KEY `idx_order_id` (`order_id`),
  KEY `idx_product_id` (`product_id`),
  CONSTRAINT `order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE,
  CONSTRAINT `order_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单项表';

-- artshow.order_shipping_address definition

CREATE TABLE `order_shipping_address` (
  `shipping_address_id` int NOT NULL AUTO_INCREMENT COMMENT '收货地址ID',
  `order_id` int NOT NULL COMMENT '订单ID',
  `recipient_name` varchar(50) NOT NULL COMMENT '收货人',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `region` varchar(100) DEFAULT NULL COMMENT '地区',
  `detail` varchar(500) NOT NULL COMMENT '详细地址',
  PRIMARY KEY (`shipping_address_id`),
  KEY `idx_order_id` (`order_id`),
  CONSTRAINT `order_shipping_address_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `order` (`order_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单收货地址表';

-- artshow.post definition

CREATE TABLE `post` (
  `post_id` int NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `subcategory_id` int DEFAULT NULL COMMENT '子分类ID',
  `title` varchar(200) DEFAULT NULL COMMENT '标题',
  `description` text COMMENT '描述',
  `image_url` varchar(500) DEFAULT NULL COMMENT '图片URL',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`post_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_subcategory_id` (`subcategory_id`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`subcategory_id`) REFERENCES `art_subcategory` (`subcategory_id`) ON DELETE SET NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='帖子表';

-- artshow.post_collection definition

CREATE TABLE `post_collection` (
  `collection_id` int NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
  `post_id` int NOT NULL COMMENT '帖子ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `create_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '收藏时间',
  PRIMARY KEY (`collection_id`),
  UNIQUE KEY `uk_user_post` (`user_id`,`post_id`),
  KEY `idx_post_id` (`post_id`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `post_collection_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `post_collection_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='收藏表';

-- artshow.post_comment definition

CREATE TABLE `post_comment` (
  `comment_id` int NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `post_id` int NOT NULL COMMENT '帖子ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `content` text NOT NULL COMMENT '评论内容',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`comment_id`),
  KEY `idx_post_id` (`post_id`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `post_comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `post_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='评论表';

-- artshow.post_like definition

CREATE TABLE `post_like` (
  `like_id` int NOT NULL AUTO_INCREMENT COMMENT '点赞ID',
  `post_id` int NOT NULL COMMENT '帖子ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`like_id`),
  UNIQUE KEY `uk_user_post` (`user_id`,`post_id`),
  KEY `idx_post_id` (`post_id`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `post_like_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `post` (`post_id`) ON DELETE CASCADE,
  CONSTRAINT `post_like_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='点赞表';

-- artshow.product definition

CREATE TABLE `product` (
  `product_id` int NOT NULL AUTO_INCREMENT COMMENT '产品ID',
  `seller_id` int NOT NULL COMMENT '卖家ID',
  `name` varchar(200) NOT NULL COMMENT '产品名称',
  `price` decimal(10,2) NOT NULL COMMENT '价格',
  `stock` int DEFAULT '0' COMMENT '库存数量',
  `cover_image_url` varchar(500) DEFAULT NULL COMMENT '封面图片URL',
  `description` text COMMENT '产品描述',
  `is_certified` tinyint(1) DEFAULT '0' COMMENT '是否认证',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updated_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`product_id`),
  KEY `idx_seller_id` (`seller_id`),
  CONSTRAINT `product_ibfk_1` FOREIGN KEY (`seller_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='产品表';

-- artshow.shopping_cart_item definition

CREATE TABLE `shopping_cart_item` (
  `cart_item_id` int NOT NULL AUTO_INCREMENT COMMENT '购物车项ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `product_id` int NOT NULL COMMENT '产品ID',
  `quantity` int NOT NULL DEFAULT '1' COMMENT '数量',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '添加时间',
  PRIMARY KEY (`cart_item_id`),
  UNIQUE KEY `uk_user_product` (`user_id`,`product_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_product_id` (`product_id`),
  CONSTRAINT `shopping_cart_item_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `shopping_cart_item_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车表';

-- artshow.`user` definition

CREATE TABLE `user` (
  `user_id` int NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `password_hash` varchar(255) NOT NULL COMMENT '密码哈希',
  `nickname` varchar(100) DEFAULT NULL COMMENT '昵称',
  `avatar_url` varchar(500) DEFAULT NULL COMMENT '头像URL',
  `bio` text COMMENT '个人简介',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`),
  KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- artshow.user_address definition

CREATE TABLE `user_address` (
  `address_id` int NOT NULL AUTO_INCREMENT COMMENT '地址ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `recipient_name` varchar(50) NOT NULL COMMENT '收货人姓名',
  `phone` varchar(20) NOT NULL COMMENT '联系电话',
  `region` varchar(100) DEFAULT NULL COMMENT '地区',
  `detail` varchar(500) NOT NULL COMMENT '详细地址',
  `is_default` tinyint(1) DEFAULT '0' COMMENT '是否默认地址',
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`address_id`),
  KEY `idx_user_id` (`user_id`),
  CONSTRAINT `user_address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户地址表';

-- artshow.user_badge definition

CREATE TABLE `user_badge` (
  `user_badge_id` int NOT NULL AUTO_INCREMENT COMMENT '用户徽章ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `badge_id` int NOT NULL COMMENT '徽章ID',
  `earned_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '获得时间',
  PRIMARY KEY (`user_badge_id`),
  UNIQUE KEY `uk_user_badge` (`user_id`,`badge_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_badge_id` (`badge_id`),
  CONSTRAINT `user_badge_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `user_badge_ibfk_2` FOREIGN KEY (`badge_id`) REFERENCES `badge` (`badge_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户徽章表';

-- artshow.user_course_chapter_completed definition

CREATE TABLE `user_course_chapter_completed` (
  `completion_id` int NOT NULL AUTO_INCREMENT COMMENT '完成记录ID',
  `enrollment_id` int NOT NULL COMMENT '注册ID',
  `chapter_id` int NOT NULL COMMENT '章节ID',
  `completed_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '完成时间',
  PRIMARY KEY (`completion_id`),
  UNIQUE KEY `uk_enrollment_chapter` (`enrollment_id`,`chapter_id`),
  KEY `idx_enrollment_id` (`enrollment_id`),
  KEY `idx_chapter_id` (`chapter_id`),
  CONSTRAINT `user_course_chapter_completed_ibfk_1` FOREIGN KEY (`enrollment_id`) REFERENCES `user_course_enrollment` (`enrollment_id`) ON DELETE CASCADE,
  CONSTRAINT `user_course_chapter_completed_ibfk_2` FOREIGN KEY (`chapter_id`) REFERENCES `course_outline` (`chapter_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户章节完成记录表';

-- artshow.user_course_enrollment definition

CREATE TABLE `user_course_enrollment` (
  `enrollment_id` int NOT NULL AUTO_INCREMENT COMMENT '注册ID',
  `user_id` int NOT NULL COMMENT '用户ID',
  `course_id` int NOT NULL COMMENT '课程ID',
  `certificate_awarded` tinyint(1) DEFAULT '0' COMMENT '是否颁发证书',
  `award_date` timestamp NULL DEFAULT NULL COMMENT '颁发日期',
  `enrolled_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`enrollment_id`),
  UNIQUE KEY `uk_user_course` (`user_id`,`course_id`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_course_id` (`course_id`),
  CONSTRAINT `user_course_enrollment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`) ON DELETE CASCADE,
  CONSTRAINT `user_course_enrollment_ibfk_2` FOREIGN KEY (`course_id`) REFERENCES `course` (`course_id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户课程注册表';