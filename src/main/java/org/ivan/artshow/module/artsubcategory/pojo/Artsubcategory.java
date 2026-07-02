package org.ivan.artshow.module.artsubcategory.pojo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "art_subcategory")
/**
 * Artsubcategory - 实体类
 *
 * <p>Artsubcategory对应数据库表，使用JPA注解映射表结构。</p>
 *
 * @author Ivan Horn
 * @since 1.0.0
 */
public class Artsubcategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "subcategory_id")
    private Integer subCateId;

    @Column(name = "category_id")
    @Getter
    @Setter
    private Integer categoryId;

    @Column(name = "name")
    @Getter
    @Setter
    private String name;

    @Column(name = "cover_image_url")
    @Getter
    @Setter
    private String coverImageUrl;

    @Column(name = "introduction")
    @Getter
    @Setter
    private String introdution;

    @Column(name = "history")
    @Getter
    @Setter
    private String history;

    @Column(name = "features")
    @Getter
    @Setter
    private String features;

    @Column(name = "cultural_meaning")
    @Getter
    @Setter
    private String culturalMeaning;
}
