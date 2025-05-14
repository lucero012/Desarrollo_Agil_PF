<script setup>
import { ref, onMounted, watch } from 'vue'
import { useRoute, useRouter } from 'vue-router'
import axios from 'axios'

const route = useRoute()
const router = useRouter()
const categoria = ref(route.params.nombre)
const productos = ref([])

const cargarProductosPorCategoria = async () => {
  try {
    const response = await axios.get(`http://localhost:8080/productos/categoria/${categoria.value}`)
    productos.value = response.data
  } catch (error) {
    console.error('Error al obtener productos por categoría:', error)
  }
}


const pedido = ref([])


onMounted(() => {
  const pedidoGuardado = localStorage.getItem('pedido')
  if (pedidoGuardado) {
    pedido.value = JSON.parse(pedidoGuardado)
  }
  cargarProductosPorCategoria()
})


watch(() => route.params.nombre, (nuevoNombre) => {
  categoria.value = nuevoNombre
  cargarProductosPorCategoria()
})


const agregarAlPedido = (producto) => {
  const existente = pedido.value.find(p => p.id === producto.id)
  if (existente) {
    existente.cantidadSeleccionada += 1
  } else {
    pedido.value.push({ ...producto, cantidadSeleccionada: 1 })
  }
  localStorage.setItem('pedido', JSON.stringify(pedido.value))
}

const irAResumenPedido = () => {
  router.push('/pedido')
}

const irMenu = () => {
  router.push('/menu')
}
</script>

<template>
  <div class="categoria-container">
    <h2 class="titulo">{{ categoria.toUpperCase() }}</h2>

    <div class="productos-grid">
      <div class="producto" v-for="producto in productos" :key="producto.id">
        <img :src="producto.imagenURL" alt="imagen" />
        <h4>{{ producto.nombre }}</h4>
        <p>Precio: ${{ producto.precio }}</p>
        <button class="btn-regresar" @click="agregarAlPedido(producto)">Agregar al pedido</button>
      </div>
    </div>

    <button class="btn-ver-pedido" @click="irAResumenPedido">🛒 Ver Pedido</button>
    <button class="btn-regresar" @click="irMenu">🔙 Regresar</button>
  </div>
</template>

<style scoped>
.categoria-container {
  padding: 2rem;
  background: linear-gradient(to right, #3da188, #014034);
  min-height: 100vh;
  text-align: center;
}

.titulo {
  font-family: 'Abril Fatface', serif;
  font-size: 3rem;
  margin-top: 20px;
  margin-bottom: 2rem;
  color: white;
  text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.3);
}

.productos-grid {
  display: grid;
  grid-template-columns: repeat(4, 1fr);
  gap: 1.5rem;
  padding: 0 1rem;
}

.producto {
  background: white;
  border-radius: 10px;
  padding: 1rem;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
  aspect-ratio: 1 / 1;
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  align-items: center;
  overflow: hidden;
}

.producto img {
  width: 100%;
  height: auto;
  max-height: 50%;
  object-fit: cover;
  border-radius: 8px;
}

.producto h4,
.producto p {
  margin: 0.5rem 0;
  color: #014034;
}

.btn-ver-pedido, .btn-regresar {
  font-size: 1.2rem;
  padding: 10px 20px;
  border: none;
  border-radius: 8px;
  cursor: pointer;
  margin-top: 2rem;
  margin-right: 10px;
  transition: background-color 0.3s ease, transform 0.3s ease;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.2);
}


.btn-regresar, .btn-ver-pedido {
  background-color: #014034;
  color: white;
}

.btn-ver-pedido:hover,
.btn-regresar:hover {
  background-color: #3da188;
  transform: scale(1.05);
}

.btn-ver-pedido:focus,
.btn-regresar:focus {
  outline: none;
}

@media (max-width: 1024px) {
  .productos-grid {
    grid-template-columns: repeat(2, 1fr);
  }
}

@media (max-width: 600px) {
  .productos-grid {
    grid-template-columns: repeat(1, 1fr);
  }
}
</style>
