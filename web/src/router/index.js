import { createRouter, createWebHistory } from 'vue-router'
import UserView from '@/views/User/UserView'
import ClassView from '@/views/Class/ClassView'
import TextBoxSendView from '@/views/TextBox/TextBoxSendView'
import TextBoxReceiveView from '@/views/TextBox/TextBoxReceiveView'
import NotFound from '@/views/error/NotFound'
import LogIn from '@/views/Account/LoginView.vue'
import Register from '@/views/Account/RegisterView.vue'
import Settings from '@/views/User/SettingsView.vue'
import store from '@/store/index'
import ClassList from '@/views/Class/ClassListView.vue'
import MyClass from '@/views/Class/MyClassView.vue'
import SettingPwd from '@/views/User/SettingPwdView.vue'

const routes = [
  {
    path: "/login/",
    name: "login",
    component: LogIn,
    meta: {
      requestAuth: false,
      hideComponent: true,
    }
  },
  {
    path: "/register/",
    name: "register",
    component: Register,
    meta: {
      requestAuth: false,
      hideComponent: true,
    }
  },
  {
    path: "/",
    name: "home",
    redirect: "/user/",
    meta: {
      requestAuth: true,
      hideComponent: false,
    }
  },
  {
    path: "/user/",
    name: "user_index",
    component: UserView,
    meta: {
      requestAuth: true,
      hideComponent: false,
    }
  },
  {
    path: "/class/",
    name: "class_index",
    component: ClassView,
    meta: {
      requestAuth: true,
      hideComponent: false,
    }
  },

  {
    path: "/class/list/",
    name: "class_list",
    component: ClassList,
    meta: {
      requestAuth: true,
      hideComponent: false,
    }
  },
  {
    path: "/class/mycalss/",
    name: "my_class",
    component: MyClass,
    meta: {
      requestAuth: true,
      hideComponent: false,
    }
  },

  {
    path: "/text/send/",
    name: "text_send",
    component: TextBoxSendView,
    meta: {
      requestAuth: true,
      hideComponent: false,
    }
  },
  {
    path: "/text/receive/",
    name: "text_receive",
    component: TextBoxReceiveView,
    meta: {
      requestAuth: true,
      hideComponent: false,
    }
  },
  {
    path: "/settings/",
    name: "settings",
    component: Settings,
    meta: {
      requestAuth: true,
      hideComponent: false,
    }
  },
  {
    path: "/settings/pwd/",
    name: "setting_pwd",
    component: SettingPwd,
    meta: {
      requestAuth: true,
      hideComponent: false,
    }
  },
  {
    path: "/404/",
    name: "404",
    component: NotFound,
    meta: {
      requestAuth: false,
      hideComponent: true,
    }
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

router.beforeEach((to, from, next) => {
  if (to.meta.requestAuth && !store.state.user.is_login) {
    next({ name: "login" });
  } else {
    next();
  }
})

export default router
