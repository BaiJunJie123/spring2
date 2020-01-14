<template>
  <div>
    <input @click="sendMsg" type="button" value="给父组件传递值">
    <p>
      this is demo 2 || {{data1}}
    </p>
    <p>{{num}}</p>
    <p>{{num2}}</p>
    <slot name="aa"></slot>



  </div>

</template>

<script>
    export default {
        name: "demo2",
        // props:['num','num2'],

      props: {
        num: {
          typeof: Number, //参数类型
          required: true, // 是否必传
          default: 0, //默认值
          validator(val) {
            return val > 100;
          } // 校验规则
        },
        num2:{
          typeof: Number
        }
      },
         data() {
              return {
                 data1: 'ok'

           }
         },
      methods: {
        sendMsg () {
          //给父类传值
            this.$emit('func','22');
        }
      }

    }
</script>

<style scoped>

</style>
