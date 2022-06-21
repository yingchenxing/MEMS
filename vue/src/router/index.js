import Vue from 'vue'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

const routes = [
    {
        path: '/',
        name: 'manage',
        redirect: '/home',
        component: () => import('../views/Manage.vue'),
        children: [
            {path: 'user', name: 'User', component: () => import('../views/User.vue')},
            {path: 'home', name: 'Home', component: () => import('../views/Home.vue')},
            {path: 'file', name: 'File', component: () => import('../views/File.vue')},
            // {path: 'profile', name: 'Profile', component: () => import('../views/Profile.vue')},
            {path: 'station', name: 'Station', component: () => import('../views/Station.vue')},
            // {path: 'log', name: 'LOG', component: () => import('../views/LOG.vue')},
            // {path: 'ticket', name: 'Ticket', component: () => import('../views/Ticket.vue')},
        ]
    }
]
const router = new VueRouter({
    mode: 'history',
    base: process.env.BASE_URL,
    routes
})

router.beforeEach((to, from, next) => {
    // localStorage.setItem("currentPathName", to.name)//设置当前的路由名称，为了在Header组件中去使用
    // store.commit("setPath")
    next()//放行路由
})

export default router
