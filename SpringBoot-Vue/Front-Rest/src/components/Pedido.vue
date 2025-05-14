<template>
  <div class="body">
    <div class="resumen-container">
      <h2 class="titulo">Resumen del Pedido</h2>

      <div class="form-group">
        <label for="nombre" class="form-label">Tu nombre</label>
        <input
          id="nombre"
          v-model="nombreCliente"
          type="text"
          class="form-control"
          placeholder="Ingresa tu nombre"
        />
      </div>

      <div v-for="prod in pedido" :key="prod.id" class="resumen-item">
        <p>
          {{ prod.nombre }} x{{ prod.cantidadSeleccionada }} —
          ${{ prod.precio * prod.cantidadSeleccionada }}
        </p>
      </div>

      <h3 class="total">Total: ${{ total }}</h3>

      <div class="button-container">
        <button @click="confirmarPedido">✅ Confirmar</button>
        <button @click="cancelarPedido">❌ Cancelar</button>
        <button @click="router.push('/menu')">🔙 Seguir comprando</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import { useRouter } from 'vue-router'
import axios from 'axios'
import Swal from 'sweetalert2'

const router = useRouter()
const pedido = ref([])
const total = ref(0)
const nombreCliente = ref('')  

onMounted(() => {
  const pedidoGuardado = localStorage.getItem('pedido')
  if (pedidoGuardado) {
    pedido.value = JSON.parse(pedidoGuardado)
    total.value = pedido.value.reduce(
      (acc, prod) => acc + prod.precio * prod.cantidadSeleccionada,
      0
    )
  }
})

const confirmarPedido = async () => {
  if (!nombreCliente.value.trim()) {
    Swal.fire('Nombre requerido', 'Por favor ingresa tu nombre.', 'warning')
    return
  }

  const pedidoConDatos = {
    id: Date.now(),
    nombre: nombreCliente.value,
    productos: pedido.value
  }

  try {
    await axios.post('http://localhost:8080/pedidos/guardar', pedidoConDatos)
    Swal.fire('¡Pedido confirmado!', 'Tu pedido fue enviado con éxito.', 'success')
    localStorage.removeItem('pedido')
    pedido.value = []
    total.value = 0
    nombreCliente.value = '' 
    router.push('/menu')
  } catch (error) {
    console.error('Error al guardar el pedido:', error)
    Swal.fire('Error', 'No se pudo confirmar el pedido.', 'error')
  }
}

const cancelarPedido = () => {
  localStorage.removeItem('pedido')
  pedido.value = []
  total.value = 0
  nombreCliente.value = ''
  router.push('/menu')
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Abril+Fatface&display=swap');

.body {
  display: flex;
  justify-content: center;
  align-items: center;
  height: 100vh;
  background: linear-gradient(to right, #3da188, #014034);
  padding: 1rem;
}

.resumen-container {
  background: rgba(255, 255, 255, 0.15);
  backdrop-filter: blur(12px);
  border: 1px solid rgba(255, 255, 255, 0.3);
  padding: 2rem;
  border-radius: 20px;
  width: 100%;
  max-width: 500px;
  color: white;
  text-align: center;
  box-shadow: 0 20px 35px rgba(0, 0, 0, 0.5);
}

.titulo {
  font-family: 'Abril Fatface', serif;
  font-size: 2.5rem;
  margin-bottom: 1.5rem;
}

.form-group {
  margin-bottom: 1.5rem;
}

input.form-control {
  padding: 12px;
  width: 100%;
  border-radius: 10px;
  border: none;
  font-size: 1rem;
  background: #fff;
  color: #333;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.2);
}

input.form-control:focus {
  outline: none;
  box-shadow: 0 0 8px #49e0c5;
}

.resumen-item {
  background: rgba(255, 255, 255, 0.3);
  padding: 1rem;
  margin-bottom: 1rem;
  border-radius: 10px;
  font-weight: bold;
}

.total {
  font-size: 1.5rem;
  margin: 1.5rem 0;
  color: #ffe;
}

.button-container {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.button-container button {
  background: linear-gradient(to right, #eedaa3, #49e0c5);
  font-family: 'Abril Fatface', serif;
  padding: 12px;
  border: none;
  font-size: 1.2em;
  border-radius: 10px;
  cursor: pointer;
  color: #000;
  transition: all 0.3s ease;
}

.button-container button:hover {
  background: linear-gradient(to right, #49e0c5, #eedaa3);
  transform: scale(1.05);
}
</style>
