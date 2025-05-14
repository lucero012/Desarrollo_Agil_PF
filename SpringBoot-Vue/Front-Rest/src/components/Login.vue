<template>
  <div class="body">
    <!-- Pantalla inicial -->
    <div v-if="pantalla === 'inicio'" class="contenido">
      <h2 class="bien">Bienvenido <br> a <br> WebFood</h2>
      <p class="mensaje">¿Cómo deseas ingresar?</p>
      <div class="button-container">
        <button @click="entrarComoUsuario"> 👤 Usuario</button>
        <button @click="pantalla = 'admin'"> 🔐 Administrador</button>
      </div>
    </div>

    <!-- Pantalla de login para administrador -->
    <div v-if="pantalla === 'admin'" class="login">
      <h2>Iniciar sesión</h2>
      <input type="text" v-model="adminUser" placeholder="Usuario" />
      <input type="password" v-model="adminPassword" placeholder="Contraseña" />
      <div class="button-container">
        <button @click="validarAdmin">Ingresar</button>
        <button @click="pantalla = 'inicio'">Regresar</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue'
import { useRouter } from 'vue-router'

const router = useRouter()

const pantalla = ref('inicio')
const adminUser = ref('')
const adminPassword = ref('')

const entrarComoUsuario = () => {
  router.push('/menu')
}

const validarAdmin = () => {
  if (adminUser.value === 'admin' && adminPassword.value === 'admin123') {
    router.push('/admin')
  } else {
    alert('Usuario o contraseña incorrectos.')
  }
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
  color: white;
}

.contenido {
  text-align: center;
}

.login {
  text-align: center;
  width: 100%;
  max-width: 400px;
  padding: 40px 30px;
  border-radius: 20px;
  backdrop-filter: blur(15px);
  background: rgba(255, 255, 255, 0.15);
  border: 1px solid rgba(255, 255, 255, 0.3);
  box-shadow: 0 25px 35px rgba(0, 0, 0, 0.5);
  z-index: 10;
  overflow: hidden;
  color: #fdfdfd;
  margin: auto;
}

.login h2 {
  font-size: 2em;
  margin-bottom: 25px;
  font-family: 'Abril Fatface', serif;
  text-shadow: 1px 1px 3px rgba(0, 0, 0, 0.4);
}

input[type="text"],
input[type="password"] {
  padding: 12px;
  margin: 12px 0;
  width: 100%;
  max-width: 100%;
  border-radius: 10px;
  border: none;
  background: rgb(255, 255, 255);
  color: #333;
  font-size: 1em;
  box-shadow: 0 3px 6px rgba(0, 0, 0, 0.2);
  transition: box-shadow 0.3s ease;
}

input[type="text"]:focus,
input[type="password"]:focus {
  outline: none;
  box-shadow: 0 0 8px #49e0c5;
}

.button-container {
  display: flex;
  flex-direction: column;
  gap: 15px;
  margin-top: 25px;
}

.button-container button {
  background: linear-gradient(to right, #eedaa3, #49e0c5);
  font-family: 'Abril Fatface', serif;
  padding: 10px 30px;
  border: none;
  font-size: 1.2em;
  border-radius: 10px;
  cursor: pointer;
  transition: all 0.3s ease;
  color: #000;
}

.button-container button:hover {
  background: linear-gradient(to right, #49e0c5, #eedaa3);
  transform: scale(1.05);
}

.bien {
  font-family: 'Abril Fatface', serif;
  font-size: 8em;
  margin-bottom: 20px;
  background: linear-gradient(90deg, #10100f, #202833);
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  text-shadow: 2px 2px 6px rgba(0, 0, 0, 0.3);
  animation: aparecer 1.8s ease-out, latido 3s infinite;
}

@keyframes aparecer {
  0% { opacity: 0; transform: translateY(-40px); }
  100% { opacity: 1; transform: translateY(0); }
}

@keyframes latido {
  0%, 100% { transform: scale(1); }
  50% { transform: scale(1.05); }
}

.mensaje {
  font-size: 1.3em;
  margin: 40px 0;
  color: #e0ffe5;
  font-weight: 500;
}
</style>
