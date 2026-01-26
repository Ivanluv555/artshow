import "../assets/style.css"

const el = document.getElementById('product-list')
async function loadProducts() {
  try {
    const res = await fetch('/api/product/list')
    if (!res.ok) throw new Error(res.status)
    const data = await res.json()
    el.textContent = JSON.stringify(data, null, 2)
  } catch (e) {
    el.textContent = '无法加载商品列表'
  }
}

loadProducts()
