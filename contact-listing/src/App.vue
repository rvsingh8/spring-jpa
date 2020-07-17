<template>
  <div id="app">
  </div>
</template>

<script>
import Axios from 'axios';
export default {
  name: 'App',
  created() {
    console.log("initiates");
    Axios.get('http://localhost:8080/contacts')
    .then(response => {
    console.log("resp", response.data)
    })
    }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
