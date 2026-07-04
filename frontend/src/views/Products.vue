<template>
  <div class="products">
    <h2>Art Products</h2>

    <el-row :gutter="20">
      <el-col :span="24">
        <el-input
          v-model="searchQuery"
          placeholder="Search products..."
          prefix-icon="Search"
          @input="handleSearch"
          style="margin-bottom: 20px"
        />
      </el-col>
    </el-row>

    <el-row :gutter="20" v-loading="loading">
      <el-col :span="6" v-for="product in filteredProducts" :key="product.productId">
        <el-card shadow="hover" class="product-card">
          <img
            v-if="product.coverImageUrl"
            :src="product.coverImageUrl"
            class="product-image"
          />
          <div class="product-content">
            <h3>{{ product.name }}</h3>
            <p class="product-desc">{{ product.description }}</p>
            <div class="product-footer">
              <span class="price">¥{{ product.price }}</span>
              <el-tag v-if="product.isCertified" type="success" size="small">Certified</el-tag>
            </div>
            <p class="stock">Stock: {{ product.stock }}</p>
            <el-button
              type="primary"
              @click="addToCart(product)"
              :disabled="product.stock === 0"
              style="width: 100%"
            >
              {{ product.stock > 0 ? 'Add to Cart' : 'Out of Stock' }}
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ref, computed, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { productApi, cartApi } from '@/api'

export default {
  name: 'Products',
  setup() {
    const products = ref([])
    const searchQuery = ref('')
    const loading = ref(false)

    const filteredProducts = computed(() => {
      if (!searchQuery.value) return products.value
      return products.value.filter(p =>
        p.name.toLowerCase().includes(searchQuery.value.toLowerCase())
      )
    })

    const loadProducts = async () => {
      try {
        loading.value = true
        const res = await productApi.getProducts()
        products.value = res.data || []
      } catch (error) {
        console.error('Load products error:', error)
      } finally {
        loading.value = false
      }
    }

    const handleSearch = () => {
      // Search is handled by computed property
    }

    const addToCart = async (product) => {
      try {
        await cartApi.addToCart({
          productId: product.productId,
          quantity: 1
        })
        ElMessage.success('Added to cart!')
      } catch (error) {
        console.error('Add to cart error:', error)
      }
    }

    onMounted(() => {
      loadProducts()
    })

    return {
      products,
      filteredProducts,
      searchQuery,
      loading,
      handleSearch,
      addToCart
    }
  }
}
</script>

<style scoped>
.products {
  max-width: 1200px;
  margin: 0 auto;
}

.products h2 {
  margin-bottom: 30px;
  font-size: 32px;
}

.product-card {
  margin-bottom: 20px;
}

.product-image {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.product-content {
  padding: 15px;
}

.product-card h3 {
  font-size: 18px;
  margin-bottom: 10px;
  overflow: hidden;
  text-overflow: ellipsis;
  white-space: nowrap;
}

.product-desc {
  color: #666;
  font-size: 14px;
  margin-bottom: 15px;
  height: 40px;
  overflow: hidden;
}

.product-footer {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.price {
  color: #f56c6c;
  font-size: 20px;
  font-weight: bold;
}

.stock {
  color: #999;
  font-size: 14px;
  margin-bottom: 10px;
}
</style>
