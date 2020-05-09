/**
 * 路由实例，提供vuex其他模块访问
 */
export default {
  namespaced: true,
  state: {
    routes: [],
    routerInstance: null
  },
  mutations: {
    setRouter (state, router) {
      state.routerInstance = router
    },
    addRoutes (state, routes) {
      state.routes = routes
      state.routerInstance.addRoutes(routes)
    }
  }
}
