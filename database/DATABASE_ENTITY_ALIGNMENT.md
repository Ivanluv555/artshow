# Database-Entity Alignment Report

## Report Date
2026-07-04

## Overview
This report documents the alignment status between database DDL definitions and Java entity classes.

---

## ✅ Complete Alignment Status

All database tables have corresponding entity classes with 100% field alignment.

### Summary
- **Total Tables**: 20
- **Total Entities**: 20
- **Alignment Rate**: 100% ✅
- **Missing Fields**: 0

---

## Entity-Table Mapping

| # | Entity Class | Database Table | Fields | Status |
|---|--------------|----------------|--------|--------|
| 1 | User | user | 7 | ✅ |
| 2 | Product | product | 10 | ✅ |
| 3 | Order | order | 6 | ✅ |
| 4 | Orderitem | order_item | 7 | ✅ |
| 5 | OrderShippingAddress | order_shipping_address | 6 | ✅ |
| 6 | Post | post | 7 | ✅ |
| 7 | Comment | post_comment | 5 | ✅ |
| 8 | Like | post_like | 4 | ✅ |
| 9 | Collection | post_collection | 4 | ✅ |
| 10 | Sci | shopping_cart_item | 5 | ✅ |
| 11 | Address | user_address | 8 | ✅ |
| 12 | Artcategory | art_category | 4 | ✅ |
| 13 | Artsubcategory | art_subcategory | 9 | ✅ |
| 14 | Course | course | 10 | ✅ |
| 15 | Instructor | course_instructor | 6 | ✅ |
| 16 | Chapter | course_outline | 6 | ✅ |
| 17 | Badge | badge | 5 | ✅ |
| 18 | UserBadge | user_badge | 4 | ✅ |
| 19 | UserCourseEnrollment | user_course_enrollment | 6 | ✅ |
| 20 | UserCourseChapterCompleted | user_course_chapter_completed | 4 | ✅ |

---

## Field Mapping Examples

### User (user)
```
user_id → userId (Integer)
username → userName (String)
password_hash → password (String) @JsonIgnore
nickname → nickName (String)
avatar_url → userAvatar (String)
bio → userBio (String)
created_at → createdAt (Date)
```

### Product (product)
```
product_id → id (Integer)
seller_id → sellerId (Integer)
name → name (String)
price → price (Double)
stock → stock (Integer)
cover_image_url → imageUrl (String)
description → description (String)
is_certified → isCertified (Boolean)
created_at → createdAt (Date)
updated_at → updatedAt (Date)
```

### Course (course)
```
course_id → courseId (Integer)
instructor_id → instructorId (Integer)
title → title (String)
cover_image_url → coverImageUrl (String)
price → price (Double)
type → type (String)
student_count → studentCount (Integer)
description → description (String)
created_at → createdAt (Date)
updated_at → updatedAt (Date)
```

---

## Type Mappings

| DDL Type | Java Type | Usage |
|----------|-----------|-------|
| `int` | `Integer` | IDs, counts |
| `varchar(n)` | `String` | Names, titles, URLs |
| `text` | `String` | Descriptions, content |
| `decimal(10,2)` | `Double` | Prices, amounts |
| `tinyint(1)` | `Boolean` | Flags, status |
| `timestamp` | `Date` | Timestamps |

---

## Recent Changes

### 2026-07-02: Field Additions
Added missing timestamp fields to 12 entities:
- Product: `createdAt`, `updatedAt`
- Course: `createdAt`, `updatedAt`
- User: `createdAt`
- Artcategory: `createdAt`
- Artsubcategory: `createdAt`
- Badge: `createdAt`
- Instructor: `createdAt`
- Chapter: `createdAt`
- Address: `createdAt`
- Sci: `createdAt`
- UserCourseEnrollment: `enrolledAt`
- UserCourseChapterCompleted: `completedAt`

### 2026-07-02: Annotation Fixes
Fixed missing `@Column` annotations in Sci entity.

### 2026-07-04: Verification
Confirmed 100% alignment across all entities.

---

## Maintenance

### When Adding New Fields

1. Update DDL:
   ```sql
   ALTER TABLE table_name ADD COLUMN field_name TYPE;
   ```

2. Update Entity:
   ```java
   @Column(name = "field_name")
   @Getter
   @Setter
   private Type fieldName;
   ```

3. Update DTO (if needed)

4. Test thoroughly

---

## Related Files

- `database/ddl.sql` - Complete database schema
- `database/add_missing_fields.sql` - Migration script for timestamp fields

---

**Last Updated**: 2026-07-04  
**Status**: ✅ 100% Aligned  
**Next Review**: When schema changes occur
