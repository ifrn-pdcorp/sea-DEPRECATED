import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import Events from '../views/Events.vue'
import NewUser from '../views/NewUser.vue'

Vue.use(VueRouter)

  const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/events',
    name: 'Events',
    component: Events
  },
  {
    path: '/newuser',
    name: 'NewUser',
    component: NewUser
  },
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
