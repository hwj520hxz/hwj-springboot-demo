import Vue from 'vue'
import store from '@/store'
import routes from './routes'
import VueRouter from 'vue-router'

Vue.use(VueRouter)

let router = new VueRouter({
  routes
})

// router.beforeEach((to, from, next) => {
//   // 当前用户未登录时，访问需要登录的路由时
//   // if (to.meta.needLogin !== false && to.name !== 'login' && !store.state.user.userInfo) {
//   //   next({ name: 'login', params: { module: to.path.substr(1) }, query: to.query })
//   //   return
//   // }
//   // // 访问登录模块时
//   // if (to.name === 'login') {
//   //   store.commit('user/setLoginUser', null) // 清空登录信息
//   //   next()
//   //   return
//   // }
//   // 没有命中任何路由，默认跳转到第一个模块路由
//   if (to.matched.length === 0) {
//     next({ path: '/' + store.state.menu.firEndMenu ?. code })
//     return
//   }
//   next()
// })

// 将路由实例对象托管到vuex
store.commit('router/setRouter', router)

export default router
