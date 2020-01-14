<template>
  <div id="app">
<!--    <img src="./assets/logo.png">-->
    <router-view/>
<!--    动态组件-->
<!--    <demo2 :num="1555" :num2="8888" @func="getzhi" />-->
  </div>
</template>

<script>
  import demo2 from './components/demo2'
export default {
  name: 'App',
  components:{
    demo2
  },
  methods: {
    getzhi (data) {
        console.log(data);
    }
  }
}
</script>

<style>
#app {
  font-family: 'Avenir', Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
  margin-top: 60px;
}
</style>
