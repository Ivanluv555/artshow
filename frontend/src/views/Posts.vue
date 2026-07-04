<template>
  <div class="posts">
    <h2>Community Posts</h2>

    <el-row :gutter="20">
      <el-col :span="24">
        <el-button type="primary" @click="showCreateDialog" style="margin-bottom: 20px">
          Create New Post
        </el-button>
      </el-col>
    </el-row>

    <el-row :gutter="20" v-loading="loading">
      <el-col :span="24" v-for="post in posts" :key="post.postId">
        <el-card shadow="hover" class="post-card">
          <div class="post-header">
            <div class="user-info">
              <strong>User #{{ post.userId }}</strong>
              <span class="post-date">{{ formatDate(post.createdAt) }}</span>
            </div>
            <el-tag v-if="post.subcategoryId" size="small">Category</el-tag>
          </div>

          <h3>{{ post.title }}</h3>
          <p class="post-content">{{ post.description }}</p>

          <img v-if="post.imageUrl" :src="post.imageUrl" class="post-image" />

          <div class="post-actions">
            <el-button text @click="likePost(post)">
              👍 Like
            </el-button>
            <el-button text @click="showComments(post)">
              💬 Comments
            </el-button>
            <el-button text @click="collectPost(post)">
              ⭐ Collect
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- Create Post Dialog -->
    <el-dialog v-model="createDialogVisible" title="Create New Post" width="60%">
      <el-form :model="newPost" label-width="100px">
        <el-form-item label="Title">
          <el-input v-model="newPost.title" placeholder="Enter post title" />
        </el-form-item>
        <el-form-item label="Content">
          <el-input
            v-model="newPost.description"
            type="textarea"
            :rows="6"
            placeholder="Enter post content"
          />
        </el-form-item>
        <el-form-item label="Image URL">
          <el-input v-model="newPost.imageUrl" placeholder="Enter image URL (optional)" />
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="createPost">Submit</el-button>
          <el-button @click="createDialogVisible = false">Cancel</el-button>
        </el-form-item>
      </el-form>
    </el-dialog>
  </div>
</template>

<script>
import { ref, reactive, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { postApi } from '@/api'

export default {
  name: 'Posts',
  setup() {
    const posts = ref([])
    const loading = ref(false)
    const createDialogVisible = ref(false)

    const newPost = reactive({
      title: '',
      description: '',
      imageUrl: ''
    })

    const loadPosts = async () => {
      try {
        loading.value = true
        const res = await postApi.getPosts()
        posts.value = res.data || []
      } catch (error) {
        console.error('Load posts error:', error)
      } finally {
        loading.value = false
      }
    }

    const showCreateDialog = () => {
      createDialogVisible.value = true
      newPost.title = ''
      newPost.description = ''
      newPost.imageUrl = ''
    }

    const createPost = async () => {
      try {
        await postApi.createPost(newPost)
        ElMessage.success('Post created successfully!')
        createDialogVisible.value = false
        loadPosts()
      } catch (error) {
        console.error('Create post error:', error)
      }
    }

    const likePost = async (post) => {
      try {
        await postApi.likePost(post.postId)
        ElMessage.success('Liked!')
      } catch (error) {
        console.error('Like post error:', error)
      }
    }

    const collectPost = async (post) => {
      try {
        ElMessage.success('Collected!')
      } catch (error) {
        console.error('Collect post error:', error)
      }
    }

    const showComments = (post) => {
      ElMessage.info('Comment feature coming soon!')
    }

    const formatDate = (date) => {
      if (!date) return ''
      return new Date(date).toLocaleDateString()
    }

    onMounted(() => {
      loadPosts()
    })

    return {
      posts,
      loading,
      createDialogVisible,
      newPost,
      showCreateDialog,
      createPost,
      likePost,
      collectPost,
      showComments,
      formatDate
    }
  }
}
</script>

<style scoped>
.posts {
  max-width: 800px;
  margin: 0 auto;
}

.posts h2 {
  margin-bottom: 30px;
  font-size: 32px;
}

.post-card {
  margin-bottom: 20px;
}

.post-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 15px;
}

.user-info {
  display: flex;
  flex-direction: column;
}

.post-date {
  color: #999;
  font-size: 12px;
  margin-top: 5px;
}

.post-card h3 {
  font-size: 20px;
  margin-bottom: 10px;
}

.post-content {
  color: #666;
  margin-bottom: 15px;
  line-height: 1.6;
}

.post-image {
  width: 100%;
  max-height: 400px;
  object-fit: cover;
  border-radius: 5px;
  margin-bottom: 15px;
}

.post-actions {
  display: flex;
  gap: 15px;
  padding-top: 15px;
  border-top: 1px solid #eee;
}
</style>
