<template>
  <div class="menu-container">
    <header class="menu-header">
      <img src="../assets/logo.png" alt="Logo" class="logo" />
      <h2 class="menu-title">MENÚ</h2>
    </header>

    <div class="categorias">
      <div
        class="categoria"
        v-for="categoria in categorias"
        :key="categoria.nombre"
        @click="irACategoria(categoria.ruta)"
      >
        <span>{{ categoria.icono }} {{ categoria.nombre }}</span>
      </div>
    </div>

    <!-- Nuevo botón -->
    <button class="btn-ver-pedido" @click="irAResumenPedido">🛒 Ver Pedido</button>

    <button class="btn-regresar" @click="irInicio">🔙 Regresar</button>
  </div>
</template>


<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const categorias = [
  { nombre: 'BEBIDAS', ruta: '/categoria/bebida', icono: '🥤' },
  { nombre: 'COMIDAS', ruta: '/categoria/comida', icono: '🍽️' },
  { nombre: 'BOTANAS', ruta: '/categoria/botanas', icono: '🍟' },
  { nombre: 'POSTRES', ruta: '/categoria/postre', icono: '🍰' }
]

const irACategoria = (ruta) => {
  router.push(ruta)
}
const irInicio = () => {
  router.push('/login')
}
const irAResumenPedido = () => {
  router.push('/pedido')
}

</script>

<style scoped>
.menu-container {
  background: linear-gradient(to right, #3da188, #014034);
  min-height: 100vh;
  padding: 2rem;
  position: relative;
  display: flex;
  flex-direction: column;
  align-items: center;
}

.menu-header {
  display: flex;
  flex-direction: column;
  align-items: center;
  margin-bottom: 2rem;
}

.logo {
  position: absolute;
  top: 20px;
  left: 20px;
  width: 180px;
  height: auto;
  object-fit: contain;
  border-radius: 10px;
  z-index: 10;
}

.menu-title {
  font-family: 'Abril Fatface', serif;
  font-size: 4.5rem;
  margin-top: 60px;
  color: white;
  text-shadow: 3px 3px 5px rgba(0, 0, 0, 0.3);
  animation: fadeIn 1.2s ease-out;
}

.categorias {
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  gap: 1.5rem;
  margin-bottom: 2rem;
}

.categoria {
  background-color: white;
  color: #014034;
  padding: 1rem 2rem;
  border-radius: 15px;
  font-weight: bold;
  font-size: 1.2rem;
  cursor: pointer;
  box-shadow: 0 4px 8px rgba(0,0,0,0.2);
  transition: transform 0.2s, background-color 0.2s;
}

.categoria:hover {
  background-color: #a3e4d7;
  transform: scale(1.05);
}



.btn-regresar:hover {
  background-color: #b7e9e4;
}

@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translateY(-20px);
  }
  to {
    opacity: 1;
    transform: translateY(0);
  }
}
.btn-ver-pedido, .btn-regresar {
  background: linear-gradient(to right, #fbd786, #79f7da);
  color: #000;
  padding: 10px 30px;
  font-size: 1rem;
  border: none;
  border-radius: 10px;
  cursor: pointer;
  margin-bottom: 1rem;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.3);
  transition: all 0.3s ease;
}

.btn-regresar:hover, .btn-ver-pedido:hover {
  background: linear-gradient(to right, #5dd669, #fbd786);
  transform: scale(1.05);
}

</style>
