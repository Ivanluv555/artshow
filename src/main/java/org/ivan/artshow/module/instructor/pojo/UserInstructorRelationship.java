package org.ivan.artshow.module.instructor.pojo;

import jakarta.persistence.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.Date;

/**
 * UserInstructorRelationship - 用户与讲师关系实体类
 *
 * <p>记录用户与讲师之间的学习关系。当用户报名课程时自动建立关系。</p>
 *
 * <p>业务规则：</p>
 * <ul>
 *   <li>关系通过课程报名自动建立</li>
 *   <li>讲师无权主动建立关系</li>
 *   <li>同一用户与同一讲师只能有一条关系记录（唯一约束）</li>
 *   <li>关系记录包含建立时间，便于追溯</li>
 * </ul>
 *
 * <p>第三范式验证：</p>
 * <ul>
 *   <li>1NF: 所有字段都是原子的</li>
 *   <li>2NF: 非主属性完全依赖于主键</li>
 *   <li>3NF: 非主属性之间不存在传递依赖</li>
 * </ul>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
@Entity
@Table(name = "user_instructor_relationship",
       uniqueConstraints = {
           @UniqueConstraint(name = "uk_user_instructor", columnNames = {"user_id", "instructor_id"})
       },
       indexes = {
           @Index(name = "idx_user_id", columnList = "user_id"),
           @Index(name = "idx_instructor_id", columnList = "instructor_id")
       })
public class UserInstructorRelationship {

    @Id
    @GenericGenerator(name = "snowflake", strategy = "org.ivan.artshow.common.config.SnowflakeIdentifierGenerator")
    @GeneratedValue(generator = "snowflake")
    @Column(name = "relationship_id")
    private Long relationshipId;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "instructor_id", nullable = false)
    private Long instructorId;

    @Column(name = "established_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date establishedAt;

    public UserInstructorRelationship() {
    }

    public Long getRelationshipId() {
        return relationshipId;
    }

    public void setRelationshipId(Long relationshipId) {
        this.relationshipId = relationshipId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getInstructorId() {
        return instructorId;
    }

    public void setInstructorId(Long instructorId) {
        this.instructorId = instructorId;
    }

    public Date getEstablishedAt() {
        return establishedAt;
    }

    public void setEstablishedAt(Date establishedAt) {
        this.establishedAt = establishedAt;
    }
}
