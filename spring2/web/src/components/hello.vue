<template xmlns:router-link="http://www.w3.org/1999/html">

  <div>
    <p> this is hello view</p>
    <router-link :to="{path: '/'}">okok</router-link>
    <child-component>
      <template slot="girl">
        漂亮、美丽、购物、逛街
      </template>
      <template slot="boy">
        帅气、才实
      </template>
      <div>
        我是一类人，
        我是默认的插槽{{san}}
      </div>
    </child-component>
    <p>这个是hello 界面</p>
  </div>

</template>

<script>
  import Vue from 'vue'
  Vue.component('child-component',{
    template:`
            <div>
            <h4>这个世界不仅有男人和女人{{nihao}}{{san}}</h4>

            <slot name="girl"></slot>

            <div style="height:1px;background-color:red;"></div>

            <slot name="boy"></slot>

            <div style="height:1px;background-color:red;"></div>

            <slot></slot>
            </div>
        `,
      data() {
        return{
          nihao:'123456'
        }

      }
  })

    export default {
        name: "hello",
      data() {
        return{
          san:'333333'
        }

      }

    }
</script>

<style scoped>

</style>
