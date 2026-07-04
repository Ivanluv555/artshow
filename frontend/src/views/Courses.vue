<template>
  <div class="courses">
    <h2>Online Courses</h2>

    <el-row :gutter="20" v-loading="loading">
      <el-col :span="8" v-for="course in courses" :key="course.courseId">
        <el-card shadow="hover" class="course-card">
          <img v-if="course.coverImageUrl" :src="course.coverImageUrl" class="course-cover" />
          <div class="course-content">
            <h3>{{ course.title }}</h3>
            <p class="course-type">{{ course.type }}</p>
            <p class="course-desc">{{ course.description }}</p>
            <div class="course-footer">
              <span class="price">¥{{ course.price }}</span>
              <span class="students">{{ course.studentCount }} students</span>
            </div>
            <el-button type="primary" @click="viewCourse(course)" style="width: 100%">
              View Details
            </el-button>
          </div>
        </el-card>
      </el-col>
    </el-row>

    <!-- Course Detail Dialog -->
    <el-dialog v-model="dialogVisible" :title="selectedCourse?.title" width="60%">
      <div v-if="selectedCourse">
        <img v-if="selectedCourse.coverImageUrl" :src="selectedCourse.coverImageUrl" class="detail-cover" />
        <el-descriptions :column="2" border>
          <el-descriptions-item label="Type">{{ selectedCourse.type }}</el-descriptions-item>
          <el-descriptions-item label="Price">¥{{ selectedCourse.price }}</el-descriptions-item>
          <el-descriptions-item label="Students">{{ selectedCourse.studentCount }}</el-descriptions-item>
          <el-descriptions-item label="Instructor">{{ selectedCourse.instructorId }}</el-descriptions-item>
        </el-descriptions>
        <div class="description-section">
          <h4>Description</h4>
          <p>{{ selectedCourse.description }}</p>
        </div>
        <el-button type="primary" @click="enrollCourse" size="large" style="width: 100%">
          Enroll Now
        </el-button>
      </div>
    </el-dialog>
  </div>
</template>

<script>
import { ref, onMounted } from 'vue'
import { ElMessage } from 'element-plus'
import { courseApi } from '@/api'

export default {
  name: 'Courses',
  setup() {
    const courses = ref([])
    const selectedCourse = ref(null)
    const loading = ref(false)
    const dialogVisible = ref(false)

    const loadCourses = async () => {
      try {
        loading.value = true
        const res = await courseApi.getCourses()
        courses.value = res.data || []
      } catch (error) {
        console.error('Load courses error:', error)
      } finally {
        loading.value = false
      }
    }

    const viewCourse = (course) => {
      selectedCourse.value = course
      dialogVisible.value = true
    }

    const enrollCourse = async () => {
      try {
        await courseApi.enrollCourse({
          courseId: selectedCourse.value.courseId
        })
        ElMessage.success('Enrolled successfully!')
        dialogVisible.value = false
      } catch (error) {
        console.error('Enroll error:', error)
      }
    }

    onMounted(() => {
      loadCourses()
    })

    return {
      courses,
      selectedCourse,
      loading,
      dialogVisible,
      viewCourse,
      enrollCourse
    }
  }
}
</script>

<style scoped>
.courses {
  max-width: 1200px;
  margin: 0 auto;
}

.courses h2 {
  margin-bottom: 30px;
  font-size: 32px;
}

.course-card {
  margin-bottom: 20px;
  overflow: hidden;
}

.course-cover {
  width: 100%;
  height: 200px;
  object-fit: cover;
}

.course-content {
  padding: 20px;
}

.course-card h3 {
  font-size: 20px;
  margin-bottom: 10px;
}

.course-type {
  color: #667eea;
  font-weight: bold;
  margin-bottom: 10px;
}

.course-desc {
  color: #666;
  margin-bottom: 15px;
  min-height: 60px;
}

.course-footer {
  display: flex;
  justify-content: space-between;
  margin-bottom: 15px;
}

.price {
  color: #f56c6c;
  font-size: 24px;
  font-weight: bold;
}

.students {
  color: #999;
}

.detail-cover {
  width: 100%;
  height: 300px;
  object-fit: cover;
  margin-bottom: 20px;
}

.description-section {
  margin: 20px 0;
}

.description-section h4 {
  margin-bottom: 10px;
}
</style>
