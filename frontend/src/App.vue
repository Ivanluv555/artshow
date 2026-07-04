<template>
  <div id="app">
    <el-container>
      <el-header>
        <div class="header-content">
          <h1>🎨 Artshow</h1>
          <el-menu
            mode="horizontal"
            :default-active="activeMenu"
            router
            class="nav-menu"
          >
            <el-menu-item index="/">Home</el-menu-item>
            <el-menu-item index="/categories">Categories</el-menu-item>
            <el-menu-item index="/courses">Courses</el-menu-item>
            <el-menu-item index="/products">Products</el-menu-item>
            <el-menu-item index="/posts">Community</el-menu-item>
            <el-menu-item v-if="!isLoggedIn" index="/login">Login</el-menu-item>
            <el-menu-item v-else index="/profile">Profile</el-menu-item>
          </el-menu>
        </div>
      </el-header>

      <el-main>
        <router-view />
      </el-main>

      <el-footer>
        <p>&copy; 2026 Artshow - Art Exhibition & Course Management System</p>
      </el-footer>
    </el-container>
  </div>
</template>

<script>
import { ref, computed } from 'vue'
import { useRouter, useRoute } from 'vue-router'

export default {
  name: 'App',
  setup() {
    const router = useRouter()
    const route = useRoute()

    const token = ref(localStorage.getItem('token'))
    const isLoggedIn = computed(() => !!token.value)
    const activeMenu = computed(() => route.path)

    return {
      isLoggedIn,
      activeMenu
    }
  }
}
</script>

<style>
* {
  margin: 0;
  padding: 0;
  box-sizing: border-box;
}

#app {
  font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
  min-height: 100vh;
}

.el-header {
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
  color: white;
  display: flex;
  align-items: center;
  padding: 0 20px;
}

.header-content {
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
}

.header-content h1 {
  margin: 0;
  font-size: 28px;
}

.nav-menu {
  background: transparent;
  border: none;
  flex: 1;
  justify-content: flex-end;
}

.nav-menu .el-menu-item {
  color: white;
  border-bottom: 2px solid transparent;
}

.nav-menu .el-menu-item:hover {
  background: rgba(255, 255, 255, 0.1);
  border-bottom-color: white;
}

.nav-menu .el-menu-item.is-active {
  border-bottom-color: white;
  background: rgba(255, 255, 255, 0.1);
}

.el-main {
  min-height: calc(100vh - 120px);
  padding: 20px;
  background: #f5f7fa;
}

.el-footer {
  background: #333;
  color: white;
  text-align: center;
  line-height: 60px;
}
</style>
