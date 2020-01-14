<template>
  <div>

<!--     动态切换组件-->
<!--   keep-alive 保持状态-->
    <keep-alive>
      <component is="demo2"/>
    </keep-alive>
    <button @click="dian">点击一下</button>
    <p>{{count}}</p>
    <p>{{quanju}}</p>
    <hr/>
    <button @click="add(1)">add</button>
    <hr/>
    <ul v-for="items in filterlista" :key="items.id">
      <li>{{items.name}}</li>

    </ul>

    <div>

      <router-view/>
    </div>



  </div>
    
</template>

<script>
  import  demo2 from '@/components/demo2'
  import  demo1 from '@/components/hello'
    export default {
        name: "demo3",
      mounted:{
         f1: function () {
           console.log(this.$router);
         },


      },
      methods:{
        add: function(var1){
            this.$store.commit('modelB/xiugai',var1);  //通过mutations 和 actions 才能修改state中的值  通过this.$store.commit(‘作用域/方法名’，参数) 来执行
        },
        dian: function () {
          // 方式1 通过js的路由切换页面
          // this.$router.push('/hello');
          // 方式2 通过name来切换
          this.$router.push({
            name:'hello',
            // 只有在index.js 的路由中有参数的才生效 如果写的是path 则不能加params 但可以加到 path后面 : /hello/333/444
            params:{
               id: 12,
              type:'dfd'
            },
            // url 参数传参
            query:{
              key:'bjj'
            }
          });

         // this.$router.replace({})   replacce 和 push 用法一样 ， 但是replace 不会产生历史记录
          // this.$router.go(2) 正数为前进2页  负数为后退2页
        }
      },
      // 计算属性 方法里必须有一个返回值 return, computed中的函数调用是不需要（）的
      computed:{
        count() {
             return this.$store.state.modelB.count;  // 如果有作用域 则在参数.的前面加上作用域
          },
        filterlista: function(){
          return  this.$store.getters['modelA/filterList'];  // [作用域/方法名]
        },
        quanju:function () {
          return this.$store.state.ones;
        }

      },

      components:{
        demo2,demo1
      }
    }
</script>

<style scoped>

</style>
