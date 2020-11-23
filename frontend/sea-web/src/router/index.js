import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/login/Home.vue'
import Events from '../views/events/Events.vue'
import NewUser from '../views/users/NewUser.vue'
import NewEvent from '../views/events/NewEvent.vue'
import AboutEvent from '../views/events/AboutEvent.vue'

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
    path: '/join',
    name: 'NewUser',
    component: NewUser
  },
  {
    path: '/aboutevent',
    name: 'AboutEvent',
    component: AboutEvent
  },
  {
    path: '/newevent',
    name: 'NewEvent',
    component: NewEvent
  },
]


const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

router.beforeEach((to, from, next) => {
  // if(to.name !== 'login' && to.name !== 'join'){
    // fazer validação de usuário logado
  // }
  console.log('chamou meu beforeEach')
  next()
})

export default router
