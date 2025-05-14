<template>
  <div class="bienvenida">
    <div class="contenido">
      <h1 class="titulo">BIENVENIDOS</h1>
      <img src="../assets/logo.png" alt="Logo" class="logo" /> <br>
      <button @click="irAlMenu">Ingresar</button>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()

const irAlMenu = () => {
  router.push('/login')
}
</script>

<style scoped>
@import url('https://fonts.googleapis.com/css2?family=Abril+Fatface&display=swap');

.bienvenida {
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

.titulo {
  font-family: 'Abril Fatface', serif;
  font-size: 100px;
  margin-bottom: 20px;
  background: linear-gradient(90deg, #ffefba, #47fb8f);
  background-clip: text;
  -webkit-background-clip: text;
  -webkit-text-fill-color: transparent;
  text-shadow: 2px 2px 6px rgba(0, 0, 0, 0.3);
  animation: aparecer 1.8s ease-out, latido 3s infinite;
}

@keyframes aparecer {
  0% {
    opacity: 0;
    transform: translateY(-40px);
  }
  100% {
    opacity: 1;
    transform: translateY(0);
  }
}

@keyframes latido {
  0%, 100% {
    transform: scale(1);
  }
  50% {
    transform: scale(1.5);
  }
}

.logo {
  width: 600px;
  height: 200px;
  object-fit: cover;
  border-radius: 20px;
  margin-bottom: 1rem;
}

button {
  background: linear-gradient(90deg, #ffefba, #47fb8f);
  font-family: 'Abril Fatface', serif;
  padding: 10px 30px;
  border: none;
  font-size: 1.2em;
  border-radius: 10px;
  cursor: pointer;
  transition: background 0.3s ease, transform 0.2s ease;
}

button:hover {
  background-color: #e0e0e0;
  transform: scale(1.05);
}
</style>
