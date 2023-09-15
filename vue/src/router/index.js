import { createRouter, createWebHistory } from 'vue-router'
import User from '../views/User.vue'
import Layout from "../layout/Layout.vue";
const routes = [
  {
    path: '/',
    name: 'Layout',
    component: Layout,
    redirect:"/user",
    //component: HomeView
    children :[{
      path:'/user',
      name:'User',
      // 路由拦截第一步
      meta: {
        requireAuth: true

      },
      component:()=>import("@/views/User"),
    },
      {
        path:'/book',
        name:'Book',
        component:()=>import("@/views/Book"),
      },
      {
        path:'/news',
        name:'News',
        component:()=>import("@/views/News"),
      },
    ]
  },
    {
    path:'/login',
    name:'Login',
    component:()=>import("@/views/Login"),
  },

  {
    path:'/register',
    name:'register',
    component:()=>import("@/views/Register"),
  },
  {
    path:'/person',
    name:'person',
    component:()=>import("@/views/Person"),
  },

]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

//  activeRouter()
//
// function activeRouter() {
//   const userStr = sessionStorage.getItem("user")
//   if (userStr) {
//     const user = JSON.parse(userStr)
//     let root = {
//       path: '/',
//       name: 'Layout',
//       component: Layout,
//       redirect: "/home",
//       children: []
//     }
//     user.permissions.forEach(p => {
//       let obj = {
//         path: p.path,
//         name: p.name,
//         component: () => import("@/views/" + p.name)
//       };
//       root.children.push(obj)
//     })
//     if (router) {
//       router.addRoute(root)
//     }
//   }
// }

//路由拦截，首先在index中添加meta
router.beforeEach((to, from, next) => {
  if (to.matched.some(res => res.meta.requireAuth)) { // 验证是否需要登陆
    var id=window.sessionStorage.getItem('user');
    console.log(id)
    if (id) { // 查询本地存储信息是否已经登陆
      next();
    } else {
      next({
        path: '/login', // 未登录则跳转至login页面
        redirect:  '/user' // 登陆成功后回到当前页面，这里传值给login页面，to.fullPath为当前点击的页面
      });
    }
  } else {
    next();
  }
})


export default router
