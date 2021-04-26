import { createRouter, createWebHistory } from 'vue-router'
import Home from '../views/Home.vue'
import Login from '../views/Login.vue'
import Register from '../views/Register.vue'
import User from '../views/User.vue'
import UserListParking from '../views/UserListParking.vue'
import UserListTemp from '../views/UserListTemp.vue'
import UserListReview from '../views/UserListReview.vue'
import UserListFeedback from '../views/UserListFeedback.vue'
import TestPage from "../views/TestPage";

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/login',
    name: 'Login',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: Login
  },
  {
    path: '/register',
    name: 'Register',
    component: Register
  },
  {
    path: '/user',
    name: 'User',
    component: User
  },
  {
    path: '/userListParking',
    name: 'UserListParking',
    component: UserListParking
  },
  {
    path: '/userListTemp',
    name: 'UserListTemp',
    component: UserListTemp
  },
  {
    path: '/userListReview',
    name: 'UserListReview',
    component: UserListReview
  },
  {
    path: '/userListFeedback',
    name: 'UserListFeedback',
    component: UserListFeedback
  },
  {
    path: '/testPage',
    name: 'TestPage',
    component: TestPage
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
