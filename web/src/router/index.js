import { createRouter, createWebHistory } from 'vue-router'
import UserView from '@/views/User/UserView'
import ClassView from '@/views/Class/ClassView'
import TextBoxView from '@/views/TextBox/TextBoxView'
import NotFound from '@/views/error/NotFound'
import LogIn from '@/views/Account/LoginView.vue'
import Register from '@/views/Account/RegisterView.vue'

const routes = [
  {
    path: "/login/",
    name: "login",
    component: LogIn,
  },
  {
    path: "/register/",
    name: "register",
    component: Register,
  },
  {
    path: "/",
    name: "home",
    redirect: "/user/"
  },
  {
    path: "/user/",
    name: "user_index",
    component: UserView,
  },
  {
    path: "/class/",
    name: "class_index",
    component: ClassView,
  },
  {
    path: "/text/",
    name: "text_index",
    component: TextBoxView,
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound,
  },
  {
    path: "/:catchAll(.*)",
    redirect: "/404/"
  },

]

const router = createRouter({
  history: createWebHistory(),
  routes
})

export default router
