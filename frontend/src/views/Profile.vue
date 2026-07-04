<template>
  <div class="profile">
    <h2>User Profile</h2>

    <el-row :gutter="20">
      <el-col :span="8">
        <el-card shadow="always">
          <div class="user-info">
            <el-avatar :size="100" :src="user.avatarUrl">
              {{ user.nickName?.charAt(0) || 'U' }}
            </el-avatar>
            <h3>{{ user.nickName || user.userName }}</h3>
            <p>@{{ user.userName }}</p>
            <p class="bio">{{ user.userBio || 'No bio yet' }}</p>
          </div>
        </el-card>
      </el-col>

      <el-col :span="16">
        <el-card shadow="always">
          <el-tabs v-model="activeTab">
            <el-tab-pane label="My Courses" name="courses">
              <div v-if="myCourses.length === 0" class="empty-state">
                <p>No courses enrolled yet</p>
                <el-button type="primary" @click="$router.push('/courses')">
                  Browse Courses
                </el-button>
              </div>
              <div v-else>
                <el-card
                  v-for="course in myCourses"
                  :key="course.courseId"
                  class="item-card"
                >
                  <h4>{{ course.title }}</h4>
                  <p>{{ course.description }}</p>
                  <el-progress :percentage="50" />
                </el-card>
              </div>
            </el-tab-pane>

            <el-tab-pane label="My Orders" name="orders">
              <div v-if="myOrders.length === 0" class="empty-state">
                <p>No orders yet</p>
                <el-button type="primary" @click="$router.push('/products')">
                  Shop Now
                </el-button>
              </div>
              <div v-else>
                <el-card
                  v-for="order in myOrders"
                  :key="order.orderId"
                  class="item-card"
                >
                  <div class="order-info">
                    <span>Order #{{ order.orderNumber }}</span>
                    <el-tag :type="getOrderStatusType(order.status)">
                      {{ order.status }}
                    </el-tag>
                  </div>
                  <p>Total: ¥{{ order.totalPrice }}</p>
                  <p class="order-date">{{ formatDate(order.createdAt) }}</p>
                </el-card>
              </div>
            </el-tab-pane>

            <el-tab-pane label="Settings" name="settings">
              <el-form :model="user" label-width="120px">
                <el-form-item label="Username">
                  <el-input v-model="user.userName" disabled />
                </el-form-item>
                <el-form-item label="Nickname">
                  <el-input v-model="user.nickName" />
                </el-form-item>
                <el-form-item label="Avatar URL">
                  <el-input v-model="user.avatarUrl" />
                </el-form-item>
                <el-form-item label="Bio">
                  <el-input
                    v-model="user.userBio"
                    type="textarea"
                    :rows="4"
                  />
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="updateProfile">
                    Save Changes
                  </el-button>
                  <el-button @click="logout">Logout</el-button>
                </el-form-item>
              </el-form>
            </el-tab-pane>
          </el-tabs>
        </el-card>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import { ref, reactive, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { userApi, courseApi, orderApi } from '@/api'

export default {
  name: 'Profile',
  setup() {
    const router = useRouter()
    const activeTab = ref('courses')

    const user = reactive({
      userName: '',
      nickName: '',
      avatarUrl: '',
      userBio: ''
    })

    const myCourses = ref([])
    const myOrders = ref([])

    const loadUserInfo = async () => {
      try {
        // In real app, get user ID from token
        const userId = 1
        const res = await userApi.getUser(userId)
        Object.assign(user, res.data)
      } catch (error) {
        console.error('Load user info error:', error)
      }
    }

    const loadMyCourses = async () => {
      try {
        const res = await courseApi.getMyCourses()
        myCourses.value = res.data || []
      } catch (error) {
        console.error('Load courses error:', error)
      }
    }

    const loadMyOrders = async () => {
      try {
        const res = await orderApi.getMyOrders()
        myOrders.value = res.data || []
      } catch (error) {
        console.error('Load orders error:', error)
      }
    }

    const updateProfile = async () => {
      try {
        await userApi.updateUser(user)
        ElMessage.success('Profile updated successfully!')
      } catch (error) {
        console.error('Update profile error:', error)
      }
    }

    const logout = () => {
      localStorage.removeItem('token')
      ElMessage.success('Logged out successfully!')
      router.push('/login')
    }

    const getOrderStatusType = (status) => {
      const types = {
        pending: 'warning',
        paid: 'success',
        shipped: 'info',
        completed: 'success',
        cancelled: 'danger'
      }
      return types[status] || 'info'
    }

    const formatDate = (date) => {
      if (!date) return ''
      return new Date(date).toLocaleString()
    }

    onMounted(() => {
      loadUserInfo()
      loadMyCourses()
      loadMyOrders()
    })

    return {
      activeTab,
      user,
      myCourses,
      myOrders,
      updateProfile,
      logout,
      getOrderStatusType,
      formatDate
    }
  }
}
</script>

<style scoped>
.profile {
  max-width: 1200px;
  margin: 0 auto;
}

.profile h2 {
  margin-bottom: 30px;
  font-size: 32px;
}

.user-info {
  text-align: center;
  padding: 20px;
}

.user-info h3 {
  margin: 20px 0 10px;
  font-size: 24px;
}

.user-info p {
  color: #666;
  margin: 5px 0;
}

.bio {
  margin-top: 15px;
  font-style: italic;
}

.empty-state {
  text-align: center;
  padding: 60px 20px;
  color: #999;
}

.empty-state p {
  margin-bottom: 20px;
  font-size: 16px;
}

.item-card {
  margin-bottom: 15px;
}

.item-card h4 {
  margin-bottom: 10px;
}

.item-card p {
  color: #666;
  margin-bottom: 10px;
}

.order-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 10px;
}

.order-date {
  color: #999;
  font-size: 12px;
}
</style>
