#!/bin/bash
# 批量更新业务类注释为简洁格式

# 定义模块说明映射
declare -A MODULE_DESC=(
    ["address"]="地址管理"
    ["artcategory"]="艺术品分类管理"
    ["artsubcategory"]="艺术品子分类管理"
    ["badge"]="徽章管理"
    ["chapter"]="课程章节管理"
    ["collection"]="收藏管理"
    ["comment"]="评论管理"
    ["course"]="课程管理"
    ["enrollment"]="课程注册管理"
    ["instructor"]="讲师管理"
    ["like"]="点赞管理"
    ["order"]="订单管理"
    ["orderitem"]="订单明细管理"
    ["post"]="帖子管理"
    ["product"]="产品管理"
    ["shopcartitem"]="购物车管理"
    ["user"]="用户管理"
)

# 处理Controller
for module in "${!MODULE_DESC[@]}"; do
    desc="${MODULE_DESC[$module]}"

    # 查找Controller文件
    controller_files=$(find "src/main/java/org/ivan/artshow/module/$module" -name "*Controller.java" 2>/dev/null)

    for file in $controller_files; do
        if [ -f "$file" ]; then
            # 使用sed删除旧注释并添加新注释
            sed -i '/^\/\*\*/,/\*\//d' "$file"

            # 在类声明前插入新注释
            awk -v desc="$desc" '
            /^@RestController/ {
                print "/**"
                print " * " desc "控制器"
                print " * 提供" desc "相关的HTTP接口"
                print " *"
                print " * @author Ivan Horn"
                print " * @since 1.0.0"
                print " */"
            }
            { print }
            ' "$file" > "$file.tmp" && mv "$file.tmp" "$file"

            echo "✓ Updated: $file"
        fi
    done
done

echo "批量更新完成！"
