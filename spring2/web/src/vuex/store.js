import Vue from 'vue'
import Vuex from 'vuex'
import  modelA from './model/modelA'
import  modelB from './model/modelb'


Vue.use(Vuex)
// vuex 做状态管理
export default new Vuex.Store({

  modules:{
    modelA,modelB
  },
  // 全局命名空间 可以通过 this.$store.state.count;  来获取
  state:{
    ones: 1,

  }

})
