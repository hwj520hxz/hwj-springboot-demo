import '@/plugin'
import '@/filter'
import '@/directive'
import Vue from 'vue'
import App from '@/App.vue'
import store from '@/store'
import router from '@/router'
import { systemConfig } from '@/config'
import { apiHelper } from 'vue-x-axios'
import Element from 'element-ui'
import VueTouchRipple from 'vue-touch-ripple'
import 'vue-touch-ripple/component.css'
import '@/assets/index.scss'
import XElDialog from '@/components/x-el-dialog'
import '@/mock'

Vue.use(VueTouchRipple)
Vue.use(Element, { size: 'small' })
Vue.component('x-el-dialog', XElDialog)

Vue.config.productionTip = false

apiHelper.register({
  systemConfig
}).then(() => {
  // 自动登录
  const autoLogin = () => {
    store.dispatch('menu/generateRoutes')
    store.getters['menu/firEndMenu'] && router.replace({ path: `/${store.getters['menu/firEndMenu'].code}` })
  }
  new Vue({
    store,
    router,
    render: h => h(App),
    created () {
      autoLogin()
    }
  }).$mount('#app')
})
