import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import hello from '@/components/hello'
import jobMod from '@/components/jobMod'
import demo2 from '../components/demo2'
import kong from '../components/kong'
import demo3 from '@/view/demo3'
import demo4 from '@/view/demo4'
import home from '@/view/home'
// 方式2  异步加载组件
//const  demo3 = () => import('@/view/demo3')
//const  demo4 = () => import('@/view/demo4')
import zi from '../components/zi'
import notfind from  '../components/notfind'

// 注册一个插件
Vue.use(Router)


const ff =  new Router({
  //使用什么方式切换路由
  mode: 'history',
  // 路由前缀
  base: '/boot',
  //base:'/demo', // 上下文路径
  routes: [
    {
      path: '/',
      name: 'HelloWorld',
      // 别名
      alias: '/jia',
      beforeEnter(to,from,next){
         console.log('局部的沟子函数 -- qian');
         next();
      },
      component: HelloWorld
    },
    {
      path: '/hello/:id?/:type?',
      name: 'hello',
      component: hello
    },
    {
      path: '/demo4',
      name: 'demo4',
      component: demo4
    },

    {
      path: '/home',
      name: 'home',
      component: home,
      children:[
        {
          path: '/',
          name: 'kong',
          component: kong
        },
        {
          path: '/jobMod',
          name: 'jobMod',
          component: jobMod
        }
      ]
    },
    {
       path: '/demo2',
       name: 'demo2',
      // 重定向  值为 路由的name 例如: 访问/demo2 则不进入demo2 他会进入 redircet 定义的路由中
       redirect: 'demo2',
      component: demo2
     },
    {
      // 动态路由  加？ 表示是否是必须的参数 (//d+) 表示参数的正则表达式
      path: '/demo3/:id?/:type?',

      meta:{
        title:'demo3'
      },
      name: 'demo3',
      component: demo3,
      // 子路由
      children:[
        {
          path: 'zi',
          name: 'zi',
          component: zi
        }
      ]
    },
    {
      path: '*',
      name: 'notfind',
      component: notfind
    }
  ]
})

// 全局路由的沟子函数
// 路由跳转前的沟子函数
ff.beforeEach((to,from,next) =>{
   // to 目标目录
  // from 前一个路由
  // next 必须执行next方法 才能跳转
  // 执行next(false)  不跳转，停留在当前页面
  // 执行 next(xxx)  跳转到xxx
  console.log('跳转前......');
  //next(false);
  next();
})
// 跳转后的沟子函数
ff.afterEach((ro,from) =>{
  console.log('跳转后......');

})
export default ff;

