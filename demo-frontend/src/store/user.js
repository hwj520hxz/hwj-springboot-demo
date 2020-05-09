/**
 * 当前登录用户
 */
import moment from 'moment'
import Vue from 'vue'

export default {
  namespaced: true,
  state: {
    userInfo: null,
    isLogining: false
  },
  getters: {
    userInfo (state) {
      return state.userInfo
    }
  },
  mutations: {
    setLoginUser (state, userInfo) {
      state.userInfo = userInfo
    },
    setLoginState (state, status) {
      state.isLogining = status
    }
  },
  actions: {
    /**
     * [loginByAccount 账号密码登录]
     * @param  {String} options.account  [账号]
     * @param  {String} options.password  [密码]
     * @param  {Boolean} options.encrypted }  [密码是否已经过md5加密]
     */
    async loginByAccount ({ commit, dispatch }, { account, password, encrypted = false } = {}) {
      let resp = await Vue.$api.login({
        data: {
          userCode: account,
          password,
          encrypted
        }
      })
      if (!resp.data.success) {
        return Promise.reject(new Error(resp.data.message))
      }
      try {
        let user = resp.data.data
        await dispatch('menu/generateRoutes', { userId: user.id }, { root: true })
        commit('setLoginUser', user)
        return Promise.resolve(user)
      } catch (error) {
        return Promise.reject(error)
      }
    },
    /**
     * [loginByStorage 根据storage中的信息登录，自动登录中用到]
     */
    async loginByStorage ({ commit, dispatch, rootState }) {
      commit('storage/initStorage', null, { root: true })
      let expireTime = rootState.storage.localStorage.expireTime
      let { userCode: account, password } = JSON.parse(rootState.storage.localStorage.user || '{}')
      try {
        if (expireTime && moment(expireTime) > moment() && account && password) {
          await dispatch('loginByAccount', { account, password, encrypted: true })
        } else {
          return Promise.reject(new Error())
        }
      } catch (error) {
        return Promise.reject(error)
      }
    },
    /**
     * [loginOut 用户注销]
     */
    async loginOut ({ commit }) {
      commit('setLoginUser', null)
      commit('storage/clearStorage', null, { root: true })
    }
  }
}
