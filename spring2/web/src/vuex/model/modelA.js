import Vue from 'vue'
import Vuex from 'vuex'
// 模块管理 - 模块化
export default({

  // 作用域
  namespaced:true,
  // 存放一些全局变量 vuex的基本数据，用来存储变量
  state:{
    // count: 1,
    listA: [
      {'id':1,'name':'aaa'},
      {'id':2,'name':'bbb'},
      {'id':3,'name':'ccc'},
    ]
  },
  // 针对state中的数据进行过滤
  getters:{
    // 参数1 表示 state中的数据 参数2 表示这个getters, 可以通过这个获得getters中的其他过滤方法
    filterList (state,getters) {
      var zu = [{'id':4,'name':'ggg'}];
      return  state.listA;
    }
  },
  mutations:{
    // 第一个参数是当前的state， 第二个参数是尽进行操作的值
    // xiugai (state,jin){
    //   state.count += jin;
    // }
  },
  actions:{

  }

})
