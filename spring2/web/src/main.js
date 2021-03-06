// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import store from  './vuex/store'
import  element from './elementUIForMod'
// ajax
import axios from 'axios'
Vue.prototype.$axios = axios;

import allUrl  from './public/publicUrl'

Vue.prototype.$allUrl = allUrl


Vue.config.productionTip = false


new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
