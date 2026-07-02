#!/usr/bin/env python3
# -*- coding: utf-8 -*-
"""批量简化JavaDoc注释"""
import os
import re
from pathlib import Path

# 模块中文说明
MODULE_NAMES = {
    'address': '地址',
    'artcategory': '艺术品分类',
    'artsubcategory': '艺术品子分类',
    'badge': '徽章',
    'chapter': '课程章节',
    'collection': '收藏',
    'comment': '评论',
    'course': '课程',
    'enrollment': '课程注册',
    'instructor': '讲师',
    'like': '点赞',
    'order': '订单',
    'orderitem': '订单明细',
    'post': '帖子',
    'product': '产品',
    'shopcartitem': '购物车',
    'user': '用户'
}

def simplify_javadoc(file_path):
    """简化JavaDoc注释"""
    try:
        with open(file_path, 'r', encoding='utf-8') as f:
            content = f.read()

        # 提取模块名
        module_match = re.search(r'module/(\w+)/', file_path)
        module = module_match.group(1) if module_match else ''
        module_cn = MODULE_NAMES.get(module, module)

        # 提取类名
        class_name = Path(file_path).stem

        # 确定类型和描述
        if 'Controller.java' in file_path:
            type_cn = '控制器'
            desc = f'提供{module_cn}相关的HTTP接口'
        elif file_path.endswith('Service.java') and not file_path.endswith('IService.java'):
            type_cn = '服务实现类'
            desc = f'实现{module_cn}的业务逻辑'
        elif 'IService.java' in file_path or '/I' in file_path:
            type_cn = '服务接口'
            desc = f'定义{module_cn}的业务方法'
        else:
            return False

        # 新的简洁注释
        new_javadoc = f'''/**
 * {module_cn}{type_cn}
 * {desc}
 *
 * @author Ivan Horn
 * @since 1.0.0
 */'''

        # 删除旧的JavaDoc注释（包含<p>标签的冗长格式）
        content = re.sub(r'/\*\*[\s\S]*?\*/', '', content)

        # 在类声明或@Service/@RestController注解前插入新注释
        if '@Service' in content:
            content = re.sub(r'(@Service)', new_javadoc + '\n\\1', content, count=1)
        elif '@RestController' in content:
            # 在@RestController之前插入
            content = re.sub(r'(@RestController)', new_javadoc + '\n\\1', content, count=1)
        elif 'public interface' in content or 'public class' in content:
            content = re.sub(r'(public\s+(interface|class))', new_javadoc + '\n\\1', content, count=1)
        else:
            return False

        # 写回文件
        with open(file_path, 'w', encoding='utf-8') as f:
            f.write(content)

        print(f'✓ {file_path}')
        return True

    except Exception as e:
        print(f'✗ {file_path}: {e}')
        return False

def main():
    base_dir = Path('src/main/java/org/ivan/artshow/module')

    if not base_dir.exists():
        print(f'错误：目录不存在 {base_dir}')
        return

    # 查找所有Controller和Service文件
    files = list(base_dir.rglob('*Controller.java')) + \
            list(base_dir.rglob('*Service.java'))

    print(f'找到 {len(files)} 个文件\n')

    success = 0
    for file in files:
        if simplify_javadoc(str(file)):
            success += 1

    print(f'\n完成！成功更新 {success}/{len(files)} 个文件')

if __name__ == '__main__':
    main()
