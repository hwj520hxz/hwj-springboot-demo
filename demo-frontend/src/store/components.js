/**
 * 异步组件库
 */
export default {
  namespaced: true,
  state: {
    asyncComponents: {
      'demo': () =>
        import('@/views/demo/demo' /* webpackChunkName: "simple-grid" */)
    }
  }
}
