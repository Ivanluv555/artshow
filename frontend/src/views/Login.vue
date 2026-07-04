<template>
  <div class="login-container">
    <el-card class="login-card">
      <h2>{{ isRegister ? 'Register' : 'Login' }}</h2>

      <el-form :model="form" :rules="rules" ref="formRef" label-width="100px">
        <el-form-item label="Username" prop="userName">
          <el-input v-model="form.userName" placeholder="Enter username" />
        </el-form-item>

        <el-form-item label="Password" prop="password">
          <el-input
            v-model="form.password"
            type="password"
            placeholder="Enter password"
            show-password
          />
        </el-form-item>

        <el-form-item v-if="isRegister" label="Nickname" prop="nickName">
          <el-input v-model="form.nickName" placeholder="Enter nickname" />
        </el-form-item>

        <el-form-item>
          <el-button type="primary" @click="handleSubmit" :loading="loading" style="width: 100%">
            {{ isRegister ? 'Register' : 'Login' }}
          </el-button>
        </el-form-item>

        <el-form-item>
          <el-button text @click="toggleMode">
            {{ isRegister ? 'Already have an account? Login' : 'No account? Register' }}
          </el-button>
        </el-form-item>
      </el-form>
    </el-card>
  </div>
</template>

<script>
import { ref, reactive } from 'vue'
import { useRouter } from 'vue-router'
import { ElMessage } from 'element-plus'
import { userApi } from '@/api'

export default {
  name: 'Login',
  setup() {
    const router = useRouter()
    const formRef = ref(null)
    const isRegister = ref(false)
    const loading = ref(false)

    const form = reactive({
      userName: '',
      password: '',
      nickName: ''
    })

    const rules = {
      userName: [
        { required: true, message: 'Please enter username', trigger: 'blur' },
        { min: 3, max: 20, message: 'Length should be 3 to 20', trigger: 'blur' }
      ],
      password: [
        { required: true, message: 'Please enter password', trigger: 'blur' },
        { min: 6, message: 'Password must be at least 6 characters', trigger: 'blur' }
      ],
      nickName: [
        { required: true, message: 'Please enter nickname', trigger: 'blur' }
      ]
    }

    const toggleMode = () => {
      isRegister.value = !isRegister.value
      form.userName = ''
      form.password = ''
      form.nickName = ''
    }

    const handleSubmit = async () => {
      try {
        await formRef.value.validate()
        loading.value = true

        if (isRegister.value) {
          await userApi.register(form)
          ElMessage.success('Registration successful! Please login.')
          toggleMode()
        } else {
          const res = await userApi.login({
            userName: form.userName,
            password: form.password
          })

          localStorage.setItem('token', res.data)
          ElMessage.success('Login successful!')
          router.push('/')
        }
      } catch (error) {
        console.error('Login/Register error:', error)
      } finally {
        loading.value = false
      }
    }

    return {
      form,
      rules,
      formRef,
      isRegister,
      loading,
      toggleMode,
      handleSubmit
    }
  }
}
</script>

<style scoped>
.login-container {
  display: flex;
  justify-content: center;
  align-items: center;
  min-height: calc(100vh - 180px);
  padding: 20px;
}

.login-card {
  width: 100%;
  max-width: 400px;
}

.login-card h2 {
  text-align: center;
  margin-bottom: 30px;
  color: #667eea;
}
</style>
