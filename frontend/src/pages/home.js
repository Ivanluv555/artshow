import "../assets/style.css"

const app = document.getElementById('app')
app.querySelector('h2').textContent = 'Welcome to ArtShow (Home)'

// 示例：从后端获取最新文章/商品（通过 /api 前缀代理）
async function loadLatest() {
  try {
    const res = await fetch('/api/post/latest')
    if (!res.ok) throw new Error(res.status)
    const data = await res.json()
    const p = document.createElement('pre')
    p.textContent = JSON.stringify(data, null, 2)
    app.appendChild(p)
  } catch (e) {
    const p = document.createElement('p')
    p.textContent = '无法加载最新内容（请确保后端运行并且 /api 代理可用）'
    app.appendChild(p)
  }
}

loadLatest()
