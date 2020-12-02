import Vue from 'vue'
import VueRouter from "vue-router";
import LoginVue from "../views/public/LoginVue";
import Content from "../views/public/Content";
import RegistVue from "../views/public/RegistVue";
import Release from "../views/loginUser/Release";
import BlogInfo from "../views/public/BlogInfo";
import MyBlog from "../views/loginUser/MyBlog";
import BlogType from "../views/public/NavBar/BlogType";


Vue.use(VueRouter);
const originalPush = VueRouter.prototype.push
VueRouter.prototype.push = function push(location, onResolve, onReject) {
  if (onResolve || onReject) return originalPush.call(this, location, onResolve, onReject)
  return originalPush.call(this, location).catch(err => err)
}

export const constantRouterMap = [

  {
    path: '/home',
    name: 'home',
    component: Content
  },
  {
    path: '/',
    redirect: '/home'
  },

  {
    path: '/type/:btype',
    component: BlogType
  },

  {
    path: '/myblog',
    name: 'myblog',
    component: MyBlog
  },
  {
    path: '/bloginfo',
    name: 'bloginfo',
    component: BlogInfo
  },
  {
    path: '/release',
    name: 'release',
    component: Release
  }, {
    path: '/login',
    name: 'loginvue',
    component: LoginVue
  }, {
    path: '/regist',
    name: 'regist',
    component:RegistVue
  }
]

const router = new VueRouter({
  mode: 'history',

  routes: constantRouterMap
})

router.beforeEach((to, form, next) => {
  if (to.path === '/myblog' || to.path === '/release') {
    if (sessionStorage.getItem("USER_SESSION") === null) {
      next({
        path: '/login',
        query: {
          msg: "您还没有登录，请先登录哦！"
        }
      })
    } else {
      next()
    }
  } else {
    next()
  }
})

export default router
