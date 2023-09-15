// import { createStore ,createRouter ,createWebHistory} from 'vuex'
// import {}
// import Layout from '../layout/Layout.vue'
//
// // export default createStore({
// //   state: {
// //   },
// //   getters: {
// //   },
// //   mutations: {
// //   },
// //   actions: {
// //   },
// //   modules: {
// //   }
// // })
// const routers=[
//   {
//     path:'/',
//     name:"Layout",
//     component:Layout,
//     redirect:"/home",
//     children:[
//       {
//         path:'home',
//         name:'Home',
//         component:()=>import("@/views/HomeView"),
//       }
//     ]
//   },
//   {
//     path:'/login',
//     name:'Login',
//     component:()=>import("@/views/Login"),
//   },
// ]
//
// const  router =createRouter(
//   {
//     history:createWebHistory(process.env.BASE_URL),
//     routers
//   }
// )
//   export  default  router
import { createStore } from 'vuex'

export default createStore({
  state: {
  },
  getters: {
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})