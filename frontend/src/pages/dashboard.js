import "../assets/style.css"

const el = document.getElementById('stats')
async function loadStats() {
  try {
    const res = await fetch('/api/dashboard/stats')
    if (!res.ok) throw new Error(res.status)
    const data = await res.json()
    el.textContent = JSON.stringify(data, null, 2)
  } catch (e) {
    el.textContent = '无法加载仪表盘统计（后端未运行或接口变更）'
  }
}

loadStats()
