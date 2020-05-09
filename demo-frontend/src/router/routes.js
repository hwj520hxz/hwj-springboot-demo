// 路由配置
export default [{
  path: '/redirect/:path*',
  component: () => import('@/components/redirect' /* webpackChunkName: "redirect" */)
}]
