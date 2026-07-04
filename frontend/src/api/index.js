import api from './request'

// User APIs
export const userApi = {
  login: (data) => api.post('/user/login', data),
  register: (data) => api.post('/user/register', data),
  getUserList: () => api.get('/user/list'),
  getUser: (userId) => api.get('/user', { params: { userId } }),
  updateUser: (data) => api.put('/user', data)
}

// Product APIs
export const productApi = {
  getProducts: () => api.get('/product/list'),
  getProduct: (id) => api.get(`/product/${id}`),
  createProduct: (data) => api.post('/product', data),
  updateProduct: (data) => api.put('/product', data),
  deleteProduct: (id) => api.delete(`/product/${id}`)
}

// Course APIs
export const courseApi = {
  getCourses: () => api.get('/course/list'),
  getCourse: (id) => api.get(`/course/${id}`),
  enrollCourse: (data) => api.post('/course/enroll', data),
  getMyCourses: () => api.get('/course/my')
}

// Post APIs
export const postApi = {
  getPosts: () => api.get('/post/list'),
  getPost: (id) => api.get(`/post/${id}`),
  createPost: (data) => api.post('/post', data),
  updatePost: (data) => api.put('/post', data),
  deletePost: (id) => api.delete(`/post/${id}`),
  likePost: (postId) => api.post('/like', { postId }),
  commentPost: (data) => api.post('/comment', data)
}

// Category APIs
export const categoryApi = {
  getCategories: () => api.get('/artcategory/list'),
  getSubcategories: (categoryId) => api.get('/artsubcategory/list', { params: { categoryId } })
}

// Order APIs
export const orderApi = {
  createOrder: (data) => api.post('/order', data),
  getMyOrders: () => api.get('/order/my'),
  getOrderDetail: (orderId) => api.get('/order', { params: { orderId } })
}

// Cart APIs
export const cartApi = {
  getCart: () => api.get('/cart/list'),
  addToCart: (data) => api.post('/cart', data),
  updateCart: (data) => api.put('/cart', data),
  removeFromCart: (id) => api.delete(`/cart/${id}`)
}
