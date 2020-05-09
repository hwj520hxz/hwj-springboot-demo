/**
 * 系统菜单
 */
import Vue from 'vue'
import { menus } from '@/mock/menu'
/**
 * [initMenuData 处理菜单数据]
 * @param  {[Array]} aMenuData [原始扁平化菜单数据]
 * @param  {[Array]} context [store上下文]
 */
function initMenuData (aMenuData, context) {
  let rootMenu = aMenuData.filter(item1 => {
    return !aMenuData.find(item2 => {
      return item2.id === item1.parentId
    })
  })
  rootMenu.forEach(item => {
    item.level = 1
    item.siteMap = [item.name]
    getChildMenu(item, aMenuData, context)
  })
}

/**
 * [getChildMenu 递归设置子菜单]
 * @param  {[Object]} pMenu     [父菜单]
 * @param  {[Array]} aMenuData [原始扁平化菜单数据]
 */
function getChildMenu (pMenu, aMenuData, { commit, state, rootState } = {}) {
  pMenu.childMenu = []
  aMenuData.forEach(item => {
    if (item.parentId === pMenu.id) {
      item.level = pMenu.level + 1
      pMenu.childMenu.push(item)
      item.siteMap = pMenu.siteMap.concat([item.name])
      getChildMenu(item, aMenuData, { commit, state, rootState })
    }
  })
  if (!state.firEndMenu && pMenu.childMenu.length === 0 && rootState.components.asyncComponents[pMenu.component]) {
    commit('setFirEndMenu', pMenu)
  }
}

/**
 * [registerRoute 根据菜单动态注册路由]
 * @param  {[type]} aMenuData [原始扁平化菜单数据]
 * @return {[type]}           [description]
 */
function registerRouteByMenu (aMenuData, { commit, rootState } = {}) {
  let aTempRoute = []
  aMenuData.forEach(menu => {
    if (menu.childMenu.length > 0) {
      return false
    }
    if (menu.childMenu.length === 0 && !menu.component && !menu.config) {
      console.warn(`菜单:${menu.siteMap}，未配置Vue组件名或Url地址参数！`)
      return false
    }
    let route = {
      path: '/' + menu.code,
      name: menu.code,
      meta: {
        menu
      }
    }
    let { target } = menu
    if (target === 'blank') {
      route.beforeEnter = (to, from, next) => {
        window.open(menu.config)
        next(false)
      }
    } else if (target === 'component') {
      route.component = rootState.components.asyncComponents[menu.component]
      if (!route.component) {
        console.warn(`菜单(${menu.siteMap})配置的组件(${menu.component})，未在异步组件库(src/store/components.js)文件中找到，请前往该文件中注册！`)
        return false
      }
      route.props = route => {
        let props = {
          menu: menu
        }
        props = Object.assign({}, props, menu.config)
        return props
      }
    }
    aTempRoute.push(route)
  })
  commit('router/addRoutes', aTempRoute, { root: true })
}

export default {
  namespaced: true,
  state: {
    rawMenu: [], // 原始菜单数据
    firEndMenu: null // 第一个末级菜单
  },
  getters: {
    treeMenu (state) {
      return state.rawMenu.filter(item => item.level === 1)
    },
    firEndMenu (state) {
      return state.rawMenu.find(item => item.routeConfig.affix) || state.firEndMenu
    }
  },
  mutations: {
    /**
     * [setFirEndMenu 设置第一个末级菜单]
     * @param {Object} firEndMenu [第一个末级菜单]
     */
    setFirEndMenu (state, firEndMenu) {
      state.firEndMenu = firEndMenu
    }
  },
  actions: {
    /**
    * [generateRoutes 路由生成]
    * @param  {int} options.uId   }  [用户id]
    */
    generateRoutes ({ commit, state, rootState }) {
      let resp = menus
      try {
        let menuData = resp.map(item => {
          item.routeConfig = item.routeConfig ? JSON.parse(item.routeConfig) : {}
          if (item.target === 'blank') return item
          try {
            item.config = item.config ? JSON.parse(item.config) : {}
            return item
          } catch (error) {
            console.error(`菜单：${item.name}，模块配置或路由配置有误，请检查！`)
            return item
          }
        })
        if (menuData.length === 0) {
          alert('当前用户没有可访问的菜单，请联系管理员！')
          return
        }
        initMenuData(menuData, { commit, state, rootState })
        registerRouteByMenu(menuData, { commit, rootState })
        state.rawMenu = menuData
      } catch (error) {
        return Promise.reject(error)
      }
    }
  }
}
