<template>
  <div class="pago-container">
    <header>
      <img src="../assets/logo.png" alt="Logo" class="logo" />
      <h2>PAGAR</h2>
    </header>

    <div v-if="pedido.length > 0" class="resumen">
      <div class="item" v-for="(producto, index) in pedido" :key="index">
        <span>{{ producto.nombre }}</span>
        <span>${{ producto.precio }}</span>
      </div>
      <hr />
      <div class="total">
        <strong>Total:</strong>
        <strong>${{ total }}</strong>
      </div>
      <div class="acciones">
        <button @click="realizarPago">PAGAR</button>
        <button @click="$router.go(-1)">Regresar</button>
      </div>
    </div>

    <div v-else class="vacio">
      <p>No hay productos para pagar.</p>
      <button @click="$router.push('/menu')">Ir al Menú</button>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Swal from 'sweetalert2'
import { useRouter } from 'vue-router'

const router = useRouter()
const pedido = ref([])
const total = ref(0)

onMounted(() => {
  const pedidoGuardado = JSON.parse(localStorage.getItem('pedido')) || []
  pedido.value = pedidoGuardado
  total.value = pedidoGuardado.reduce((sum, item) => sum + Number(item.precio), 0)
})

const realizarPago = () => {
  Swal.fire({
    icon: 'success',
    title: 'Pago realizado correctamente',
    text: 'Gracias por tu compra',
    confirmButtonText: 'Aceptar'
  }).then(() => {
    localStorage.removeItem('pedido')
    router.push('/')
  })
}
</script>

<style scoped>
.pago-container {
  padding: 2rem;
  background-color: #e0f7f4;
  min-height: 100vh;
  text-align: center;
}

.logo {
  width: 600px;
  height: 200px;
  object-fit: cover;
  border-radius: 20px;
  margin-bottom: 1rem;
}

.resumen {
  margin-top: 2rem;
  max-width: 500px;
  margin-inline: auto;
}

.item {
  display: flex;
  justify-content: space-between;
  margin-bottom: 10px;
  font-size: 1.1rem;
}

.total {
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
  font-size: 1.2rem;
}

.acciones {
  margin-top: 2rem;
  display: flex;
  justify-content: center;
  gap: 1rem;
}

button {
  background-color: #00796b;
  color: white;
  border: none;
  padding: 0.6rem 1.2rem;
  border-radius: 5px;
  cursor: pointer;
}

.vacio {
  margin-top: 4rem;
}
</style>
