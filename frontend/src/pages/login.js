import "../assets/style.css"

const form = document.getElementById('login-form')
form.addEventListener('submit', async (e) => {
  e.preventDefault()
  const formData = new FormData(form)
  const payload = {
    username: formData.get('username'),
    password: formData.get('password')
  }
  try {
    const res = await fetch('/api/user/login', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(payload)
    })
    if (!res.ok) throw new Error(res.status)
    const data = await res.json()
    alert('登录成功')
    location.href = '/dashboard.html'
  } catch (err) {
    alert('登录失败：' + err)
  }
})
