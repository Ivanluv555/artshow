-- ============================================================
-- 用户-讲师关系表
-- ============================================================
-- 用途: 记录用户与讲师之间的学习关系
-- 触发: 当用户报名课程时自动建立关系
-- 规则: 讲师无权主动建立关系，只能通过课程报名建立
-- ============================================================

-- 创建用户-讲师关系表
CREATE TABLE IF NOT EXISTS user_instructor_relationship (
    relationship_id BIGINT PRIMARY KEY COMMENT '关系ID',
    user_id BIGINT NOT NULL COMMENT '用户ID',
    instructor_id BIGINT NOT NULL COMMENT '讲师ID',
    established_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '关系建立时间',

    -- 唯一约束：一个用户与一个讲师只能有一条关系记录
    UNIQUE KEY uk_user_instructor (user_id, instructor_id),

    -- 外键约束
    CONSTRAINT fk_relationship_user
        FOREIGN KEY (user_id) REFERENCES user(user_id) ON DELETE CASCADE,
    CONSTRAINT fk_relationship_instructor
        FOREIGN KEY (instructor_id) REFERENCES course_instructor(instructor_id) ON DELETE CASCADE,

    -- 索引优化
    INDEX idx_user_id (user_id),
    INDEX idx_instructor_id (instructor_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户-讲师关系表';


-- ============================================================
-- 第三范式验证
-- ============================================================
-- 1NF (第一范式): 每个字段都是原子的，不可再分 ✅
--    - relationship_id: 单一主键
--    - user_id: 单一用户标识
--    - instructor_id: 单一讲师标识
--    - established_at: 单一时间戳
--
-- 2NF (第二范式): 非主属性完全依赖于主键 ✅
--    - 主键: relationship_id
--    - 所有非主属性(user_id, instructor_id, established_at)都完全依赖于主键
--    - 不存在部分依赖
--
-- 3NF (第三范式): 非主属性之间不存在传递依赖 ✅
--    - user_id 不依赖于 instructor_id
--    - instructor_id 不依赖于 user_id
--    - established_at 不依赖于其他非主属性
--    - 所有非主属性都直接依赖于主键，不存在传递依赖


-- ============================================================
-- 示例数据
-- ============================================================
-- 假设用户ID=1报名了讲师ID=10的课程，自动创建关系
-- INSERT INTO user_instructor_relationship (relationship_id, user_id, instructor_id)
-- VALUES (1, 1, 10);

-- 假设用户ID=1又报名了讲师ID=10的另一门课程，关系已存在，不重复插入
-- INSERT IGNORE INTO user_instructor_relationship (relationship_id, user_id, instructor_id)
-- VALUES (2, 1, 10);


-- ============================================================
-- 查询示例
-- ============================================================

-- 查询用户的所有讲师
-- SELECT ci.*
-- FROM user_instructor_relationship uir
-- JOIN course_instructor ci ON uir.instructor_id = ci.instructor_id
-- WHERE uir.user_id = ?;

-- 查询讲师的所有学生
-- SELECT u.*
-- FROM user_instructor_relationship uir
-- JOIN user u ON uir.user_id = u.user_id
-- WHERE uir.instructor_id = ?;

-- 统计用户有多少个讲师
-- SELECT COUNT(*)
-- FROM user_instructor_relationship
-- WHERE user_id = ?;

-- 统计讲师有多少个学生
-- SELECT COUNT(*)
-- FROM user_instructor_relationship
-- WHERE instructor_id = ?;

-- 检查用户与讲师是否已有关系
-- SELECT COUNT(*) > 0
-- FROM user_instructor_relationship
-- WHERE user_id = ? AND instructor_id = ?;
