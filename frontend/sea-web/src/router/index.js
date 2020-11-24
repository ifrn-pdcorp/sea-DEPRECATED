import Vue from 'vue'
import VueRouter from 'vue-router'
import UserService from '../services/users'
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

router.beforeEach(async (to, from, next) => {
  var user = null
  await UserService.loadSession().then(response => {
    user = response.data.user
  }).catch(() => {
    user = null
  })
  if (user) {
    if (to.name === 'Home' || to.name === 'NewUser') {
      next({ name: 'Events' })
    }
  } else {
    if (to.name !== 'Home' && to.name !== 'NewUser') {
      next({ name: 'Home' })
    }
  }
  next()
})

export default router
