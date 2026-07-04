package org.ivan.artshow.module.artcategory.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name = "art_category")
/**
 * Artcategory - 实体类
 *
 * <p>Artcategory对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Artcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "category_id")
    private Integer categoryId;

    @Column(name = "category_name")
    @Getter
    @Setter
    private String categoryName;

    @Column(name = "icon_url")
    @Getter
    @Setter
    private String iconUrl;

    @Column(name = "created_at")
    @Getter
    @Setter
    private Date createdAt;
}
