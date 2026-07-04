<template>
  <div class="categories">
    <h2>Art Categories</h2>

    <el-row :gutter="20" v-loading="loading">
      <el-col :span="8" v-for="category in categories" :key="category.categoryId">
        <el-card shadow="hover" class="category-card" @click="viewSubcategories(category)">
          <div class="category-icon">{{ category.iconUrl || '🎨' }}</div>
          <h3>{{ category.categoryName }}</h3>
          <el-button type="text">View Subcategories →</el-button>
        </el-card>
      </el-col>
    </el-row>

    <!-- Subcategories Dialog -->
    <el-dialog v-model="dialogVisible" :title="selectedCategory?.categoryName" width="80%">
      <el-row :gutter="20">
        <el-col :span="12" v-for="sub in subcategories" :key="sub.subcategoryId">
          <el-card shadow="hover" class="subcategory-card">
            <img v-if="sub.coverImageUrl" :src="sub.coverImageUrl" class="cover-image" />
            <h4>{{ sub.name }}</h4>
            <p class="intro">{{ sub.introduction }}</p>
            <el-collapse>
              <el-collapse-item title="History" name="1">
                <p>{{ sub.history || 'No history available' }}</p>
              </el-collapse-item>
              <el-collapse-item title="Features" name="2">
                <p>{{ sub.features || 'No features available' }}</p>
              </el-collapse-item>
              <el-collapse-item title="Cultural Meaning" name="3">
                <p>{{ sub.culturalMeaning || 'No cultural meaning available' }}</p>
              </el-collapse-item>
            </el-collapse>
          </el-card>
        </el-col>
      </el-row>
    </el-dialog>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { categoryApi } from '@/api'

export default {
  name: 'Categories',
  setup() {
    const categories = ref([])
    const subcategories = ref([])
    const selectedCategory = ref(null)
    const loading = ref(false)
    const dialogVisible = ref(false)

    const loadCategories = async () => {
      try {
        loading.value = true
        const res = await categoryApi.getCategories()
        categories.value = res.data || []
      } catch (error) {
        console.error('Load categories error:', error)
      } finally {
        loading.value = false
      }
    }

    const viewSubcategories = async (category) => {
      selectedCategory.value = category
      try {
        const res = await categoryApi.getSubcategories(category.categoryId)
        subcategories.value = res.data || []
        dialogVisible.value = true
      } catch (error) {
        console.error('Load subcategories error:', error)
      }
    }

    onMounted(() => {
      loadCategories()
    })

    return {
      categories,
      subcategories,
      selectedCategory,
      loading,
      dialogVisible,
      viewSubcategories
    }
  }
}
</script>

<style scoped>
.categories {
  max-width: 1200px;
  margin: 0 auto;
}

.categories h2 {
  margin-bottom: 30px;
  font-size: 32px;
}

.category-card {
  text-align: center;
  padding: 30px;
  cursor: pointer;
  transition: transform 0.3s;
  margin-bottom: 20px;
}

.category-card:hover {
  transform: translateY(-5px);
}

.category-icon {
  font-size: 64px;
  margin-bottom: 20px;
}

.category-card h3 {
  font-size: 24px;
  margin-bottom: 15px;
}

.subcategory-card {
  margin-bottom: 20px;
}

.cover-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
  border-radius: 5px;
  margin-bottom: 15px;
}

.subcategory-card h4 {
  font-size: 20px;
  margin-bottom: 10px;
}

.intro {
  color: #666;
  margin-bottom: 15px;
}
</style>
