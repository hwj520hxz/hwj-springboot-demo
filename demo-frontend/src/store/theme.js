/**
 * 在线换肤
 */
function loadStyle (theme) {
  let body = document.getElementsByTagName('body')[0]
  let themeLinkTag = document.querySelector('link[id=theme]')
  themeLinkTag && body.removeChild(themeLinkTag)
  let link = document.createElement('link')
  link.type = 'text/css'
  link.rel = 'stylesheet'
  link.setAttribute('id', 'theme')
  link.href = `theme/${theme}/style.css`
  body.appendChild(link)
}

export default {
  namespaced: true,
  state: {
    name: 'default' // 默认主题
  },
  getters: {
    name (state, getters, rootState) {
      return rootState.storage.theme || state.name
    }
  },
  mutations: {
    switchTheme (state, theme) {
      state.name = theme
    }
  },
  actions: {
    async switchTheme ({ commit }, theme) {
      alert(theme)
      loadStyle(theme)
      commit('switchTheme', theme)
      commit('storagte/setStorageItem', { name: 'theme', value: theme }, { root: true })
    }
  }
}
