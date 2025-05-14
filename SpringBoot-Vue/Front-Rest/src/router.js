
import { createRouter, createWebHistory } from 'vue-router'
import Bienvenida from './components/Bienvenida.vue'
import Restaurants from './components/Restaurants.vue'
import Menu from './components/Menu.vue'
import Pedido from './components/Pedido.vue'
import Pago from './components/Pago.vue'
import AdminProducto from './components/AdminProducto.vue'
import Login from './components/Login.vue'
import Categoria from './components/Categoria.vue'

const routes = [
  { path: '/', component: Bienvenida },
  { path: '/restaurants', component: Restaurants },
  { path: '/menu', component: Menu },
  { path: '/menu/:restaurantId', component: Menu },        
  { path: '/pedido', component: Pedido },
  { path: '/pago', component: Pago },
  { path: '/admin', component: AdminProducto },
  {path: '/login', component: Login },
  { path: '/categoria/:nombre', name: 'Categoria', component: Categoria },
]

const router = createRouter({
  history: createWebHistory(),
  routes,
})

export default router
