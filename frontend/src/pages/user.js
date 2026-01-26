import "../assets/style.css"

const el = document.getElementById('profile')
async function loadProfile() {
  try {
    const res = await fetch('/api/user/profile')
    if (!res.ok) throw new Error(res.status)
    const data = await res.json()
    el.textContent = JSON.stringify(data, null, 2)
  } catch (e) {
    el.textContent = '无法加载用户信息'
  }
}

loadProfile()
