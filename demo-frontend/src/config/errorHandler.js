import Vue from 'vue'
import store from '@/store'

Vue.config.errorHandler = (error, vm, info) => {
  console.error(error)
  store.commit('error/pushError', { message: error.message, info: `${vm.$vnode.tag} error in ${info}`, stack: error.stack, url: window.location.href, vueInstance: vm })
}
