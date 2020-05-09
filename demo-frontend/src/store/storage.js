/**
 * 本地数据存储
 */
export default {
  namespaced: true,
  state: {
    localStorage: {}
  },
  mutations: {
    /**
    * [initStorage 初始化storage键值对集合]
    */
    initStorage (state) {
      let result = {}
      let storage = window.localStorage
      for (let i = 0, len = storage.length; i < len; i++) {
        let key = storage.key(i)
        let value = storage.getItem(key)
        Object.assign(result, { [key]: value })
      }
      state.localStorage = result
    },
    /**
    * [setItem 设置item]
    */
    setLocalStorageItem (state, { name, value } = {}) {
      window.localStorage.setItem(name, value)
      state.localStorage = Object.assign({}, state.localStorage, { [name]: value })
    },
    /**
     * [removeItem 删除指定的item]
     */
    removeLocalStorageItem (state, { name } = {}) {
      window.localStorage.removeItem(name)
      delete state.localStorage[name]
    },
    /**
     * [clearStorage 清除storage]
     */
    clearStorage (state) {
      window.localStorage.clear()
      state.localStorage = {}
    }
  }
}
