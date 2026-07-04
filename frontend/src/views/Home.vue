<template>
  <div class="home">
    <el-row :gutter="20">
      <el-col :span="24">
        <div class="hero-section">
          <h1>Welcome to Artshow</h1>
          <p>Discover Art, Learn Skills, Connect with Artists</p>
        </div>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="features">
      <el-col :span="8">
        <el-card shadow="hover" class="feature-card">
          <h3>🎨 Art Categories</h3>
          <p>Explore diverse art forms and styles</p>
          <el-button type="primary" @click="$router.push('/categories')">
            Browse Categories
          </el-button>
        </el-card>
      </el-col>

      <el-col :span="8">
        <el-card shadow="hover" class="feature-card">
          <h3>📚 Online Courses</h3>
          <p>Learn from professional instructors</p>
          <el-button type="primary" @click="$router.push('/courses')">
            View Courses
          </el-button>
        </el-card>
      </el-col>

      <el-col :span="8">
        <el-card shadow="hover" class="feature-card">
          <h3>🛍️ Art Products</h3>
          <p>Buy authentic artworks and supplies</p>
          <el-button type="primary" @click="$router.push('/products')">
            Shop Now
          </el-button>
        </el-card>
      </el-col>
    </el-row>

    <el-row :gutter="20" class="stats-section">
      <el-col :span="24">
        <el-card>
          <h2>Platform Statistics</h2>
          <el-row :gutter="20">
            <el-col :span="6">
              <div class="stat-item">
                <h3>{{ stats.users }}</h3>
                <p>Active Users</p>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="stat-item">
                <h3>{{ stats.courses }}</h3>
                <p>Courses Available</p>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="stat-item">
                <h3>{{ stats.products }}</h3>
                <p>Products Listed</p>
              </div>
            </el-col>
            <el-col :span="6">
              <div class="stat-item">
                <h3>{{ stats.posts }}</h3>
                <p>Community Posts</p>
              </div>
            </el-col>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'

export default {
  name: 'Home',
  setup() {
    const stats = ref({
      users: 0,
      courses: 0,
      products: 0,
      posts: 0
    })

    onMounted(() => {
      // Simulate loading stats
      stats.value = {
        users: 1234,
        courses: 56,
        products: 789,
        posts: 345
      }
    })

    return {
      stats
    }
  }
}
</script>

<style scoped>
.home {
  max-width: 1200px;
  margin: 0 auto;
}

.hero-section {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  padding: 80px 20px;
  text-align: center;
  border-radius: 10px;
  margin-bottom: 40px;
}

.hero-section h1 {
  font-size: 48px;
  margin-bottom: 20px;
}

.hero-section p {
  font-size: 24px;
  opacity: 0.9;
}

.features {
  margin-bottom: 40px;
}

.feature-card {
  text-align: center;
  padding: 20px;
}

.feature-card h3 {
  font-size: 24px;
  margin-bottom: 15px;
}

.feature-card p {
  color: #666;
  margin-bottom: 20px;
}

.stats-section {
  margin-bottom: 40px;
}

.stats-section h2 {
  text-align: center;
  margin-bottom: 30px;
}

.stat-item {
  text-align: center;
  padding: 20px;
}

.stat-item h3 {
  font-size: 36px;
  color: #667eea;
  margin-bottom: 10px;
}

.stat-item p {
  color: #666;
  font-size: 16px;
}
</style>
