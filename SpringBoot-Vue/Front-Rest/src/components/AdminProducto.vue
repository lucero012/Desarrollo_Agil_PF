<template>
  <div class="body">
    <div class="container my-5 px-3 px-md-5">
      <div class="card shadow-lg w-100 mx-auto" style="max-width: 900px;">
        
        <!-- Header con Tabs y Cerrar Sesión -->
        <div class="card-header bg-success text-white d-flex justify-content-between align-items-center">
          <ul class="nav nav-tabs card-header-tabs">
            <li class="nav-item" v-for="tab in tabs" :key="tab.id">
              <button
                class="nav-link"
                :class="{ active: tabActiva === tab.id }"
                @click="tabActiva = tab.id"
              >
                {{ tab.emoji }} {{ tab.label }}
              </button>
            </li>
          </ul>
          <button class="btn btn-outline-light btn-sm ms-3 boton-letra-negra" @click="cerrarSesion">
            🔒 Cerrar Sesión
          </button>
        </div>

        <!-- Cuerpo -->
        <div class="card-body">

          <!-- FORMULARIO -->
          <div v-if="tabActiva === 'formulario'">
            <h4 class="mb-4 text-center">Formulario de Productos</h4>
            <form @submit.prevent="agregarProductos" class="row g-3">
              <div class="col-md-6">
                <label class="form-label">Categoría</label>
                <select class="form-select" v-model="nuevoProducto.categoria" required>
                  <option value="">Selecciona una categoría</option>
                  <option value="comida">Comida</option>
                  <option value="bebida">Bebida</option>
                  <option value="postre">Postre</option>
                  <option value="botanas">Botanas</option>
                </select>
              </div>
              <div class="col-md-6">
                <label class="form-label">Nombre</label>
                <input type="text" class="form-control" v-model="nuevoProducto.nombre" required />
              </div>
              <div class="col-md-6">
                <label class="form-label">Precio</label>
                <input type="number" class="form-control" v-model="nuevoProducto.precio" required />
              </div>
              <div class="col-md-6">
                <label class="form-label">Cantidad</label>
                <input type="number" class="form-control" v-model="nuevoProducto.cantidad" required />
              </div>
              <div class="col-md-12">
                <label class="form-label">Imagen URL</label>
                <input type="text" class="form-control" v-model="nuevoProducto.imagenURL" required />
              </div>
              <div class="col-12 text-end">
                <button type="submit" class="btn btn-success mt-3">
                  {{ editado ? 'Actualizar Producto' : 'Agregar Producto' }}
                </button>
              </div>
            </form>
          </div>

          <!-- LISTA -->
          <div v-if="tabActiva === 'lista'" class="table-responsive mt-4">
            <h4 class="text-center mb-4">Productos Disponibles</h4>
            <table class="table table-hover table-bordered align-middle">
              <thead class="table-success">
                <tr class="text-center">
                  <th>#</th>
                  <th>Categoría</th>
                  <th>Nombre</th>
                  <th>Precio</th>
                  <th>Cantidad</th>
                  <th>Imagen</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(producto, index) in productos" :key="producto.id">
                  <td class="text-center">{{ index + 1 }}</td>
                  <td>{{ producto.categoria }}</td>
                  <td>{{ producto.nombre }}</td>
                  <td>${{ producto.precio }}</td>
                  <td class="text-center">{{ producto.cantidad }}</td>
                  <td class="text-center">
                    <img :src="producto.imagenURL" alt="img" class="img-thumbnail" width="60" />
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

          <!-- ACCIONES -->
          <div v-if="tabActiva === 'acciones'" class="table-responsive mt-4">
            <h4 class="text-center mb-4">Acciones</h4>
            <table class="table table-bordered table-striped">
              <thead class="table-warning">
                <tr class="text-center">
                  <th>#</th>
                  <th>Nombre</th>
                  <th>Categoría</th>
                  <th>Acciones</th>
                </tr>
              </thead>
              <tbody>
                <tr v-for="(producto, index) in productos" :key="producto.id">
                  <td class="text-center">{{ index + 1 }}</td>
                  <td>{{ producto.nombre }}</td>
                  <td>{{ producto.categoria }}</td>
                  <td class="text-center">
                    <div class="d-flex flex-wrap justify-content-center gap-2">
                      <button class="btn btn-warning btn-sm" @click="editarProductos(producto)">
                        <i class="bi bi-pencil-square"></i>
                      </button>
                      <button class="btn btn-danger btn-sm" @click="eliminarProductos(producto.id)">
                        <i class="bi bi-trash3-fill"></i>
                      </button>
                    </div>
                  </td>
                </tr>
              </tbody>
            </table>
          </div>

         <!-- PEDIDOS -->
<div v-if="tabActiva === 'pedidos'" class="table-responsive mt-4">
  <h4 class="text-center mb-4">Pedidos Realizados</h4>
  <table class="table table-bordered table-hover align-middle">
    <thead class="table-info text-center">
      <tr>
        <th>#</th>
        <th>Cliente</th>
        <th>Producto</th>
        <th>Categoría</th>
        <th>Cantidad</th>
        <th>Precio Unitario</th>
        <th>Total</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(pedido, index) in pedidos" :key="pedido.id">
        <td class="text-center">{{ index + 1 }}</td>
        <td>{{ pedido.nombreCliente }}</td>
        <td>{{ pedido.nombre }}</td>
        <td>{{ pedido.categoria }}</td>
        <td class="text-center">{{ pedido.cantidadSeleccionada }}</td>
        <td class="text-end">${{ pedido.precio }}</td>
        <td class="text-end">${{ pedido.precio * pedido.cantidadSeleccionada }}</td>
        
      </tr>
    </tbody>
  </table>

  
<div class="mt-5">
  <h5 class="text-center mb-3">Total por Cliente</h5>
  <table class="table table-sm table-bordered table-striped">
    <thead class="table-secondary text-center">
      <tr>
        <th>Cliente</th>
        <th>Total a Pagar</th>
        <th>Acción</th>
      </tr>
    </thead>
    <tbody>
      <tr v-for="(cliente, index) in totalPorCliente" :key="index">
        <td class="text-center">{{ cliente.cliente }}</td>
        <td class="text-end">${{ cliente.total.toFixed(2) }}</td>
        <td class="text-center">
          <button class="btn btn-success btn-sm" @click="completarPedidosCliente(cliente.cliente)">
            <i class="bi bi-check2-circle"></i> Completar
          </button>
        </td>
      </tr>
    </tbody>
  </table>
</div>

</div>


        </div>
      </div>
    </div>
  </div>
</template>

<script setup>

import { useRouter } from 'vue-router'
import axios from 'axios'
import Swal from 'sweetalert2'
import { ref, onMounted, computed } from 'vue'


const router = useRouter()
const tabActiva = ref('formulario')
const editado = ref(false)

const productos = ref([])
const pedidos = ref([])

const nuevoProducto = ref({
  id: '',
  categoria: '',
  nombre: '',
  precio: '',
  cantidad: '',
  imagenURL: ''
})

const tabs = [
  { id: 'formulario', label: 'Ingresar', emoji: '📝' },
  { id: 'lista', label: 'Productos', emoji: '📦' },
  { id: 'acciones', label: 'Acciones', emoji: '⚙️' },
  { id: 'pedidos', label: 'Pedidos', emoji: '📋' }
]

const cargarProductos = async () => {
  const { data } = await axios.get('http://localhost:8080/productos/traer_productos')
  productos.value = data
}

const cargarPedidos = async () => {
  const { data } = await axios.get('http://localhost:8080/pedidos/traer_pedido')
  pedidos.value = data
}

const agregarProductos = async () => {
  if (editado.value) {
    await axios.put(`http://localhost:8080/productos/editar/${nuevoProducto.value.id}`, nuevoProducto.value)
    editado.value = false
  } else {
    await axios.post('http://localhost:8080/productos/insertar_productos', nuevoProducto.value)
    Swal.fire({ icon: 'success', title: 'Producto agregado correctamente', showConfirmButton: false, timer: 1500 })
  }
  await cargarProductos()
  nuevoProducto.value = { id: '', categoria: '', nombre: '', precio: '', cantidad: '', imagenURL: '' }
}

const editarProductos = (producto) => {
  Object.assign(nuevoProducto.value, producto)
  tabActiva.value = 'formulario'
  editado.value = true
}

const eliminarProductos = async (id) => {
  const result = await Swal.fire({
    title: '¿Estás seguro?',
    text: '¡No podrás deshacer esta acción!',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Sí, eliminar'
  })
  if (result.isConfirmed) {
    await axios.delete(`http://localhost:8080/productos/eliminar-productos/${id}`)
    await cargarProductos()
    Swal.fire('Eliminado!', 'Producto eliminado correctamente.', 'success')
  }
}


const cerrarSesion = async () => {
  const result = await Swal.fire({
    title: '¿Deseas cerrar sesión?',
    text: 'Serás redirigido a la página principal',
    icon: 'question',
    showCancelButton: true,
    confirmButtonText: 'Sí, cerrar sesión'
  })
  if (result.isConfirmed) {
    router.push('/')
  }
}
const totalPorCliente = computed(() => {
  const resumen = {}
  for (const pedido of pedidos.value) {
    if (!resumen[pedido.nombreCliente]) {
      resumen[pedido.nombreCliente] = 0
    }
    resumen[pedido.nombreCliente] += pedido.precio * pedido.cantidadSeleccionada
  }
  return Object.entries(resumen).map(([cliente, total]) => ({ cliente, total }))
})
const completarPedidosCliente = async (nombreCliente) => {
  const result = await Swal.fire({
    title: `¿Completar todos los pedidos de ${nombreCliente}?`,
    text: 'Esta acción eliminará todos sus pedidos.',
    icon: 'warning',
    showCancelButton: true,
    confirmButtonText: 'Sí, completar'
  })

  if (result.isConfirmed) {
    const pedidosDelCliente = pedidos.value.filter(p => p.nombreCliente === nombreCliente)
    for (const pedido of pedidosDelCliente) {
      await axios.delete(`http://localhost:8080/pedidos/eliminar_pedido/${pedido.id}`)
    }
    await cargarPedidos()
    Swal.fire('Completado', `Se eliminaron todos los pedidos de ${nombreCliente}.`, 'success')
  }
}



onMounted(() => {
  cargarProductos()
  cargarPedidos()
})
</script>

<style scoped>
.body {
  min-height: 100vh;
  display: flex;
  justify-content: center;
  align-items: center;
  background: linear-gradient(to right, #3da188, #014034);
  color: white;
  padding: 2rem 0;
}

.card {
  border-radius: 1rem;
}

.nav-tabs .nav-link {
  cursor: pointer;
  font-weight: bold;
  color: #030303;
}

.table th,
.table td {
  vertical-align: middle;
}
</style>
