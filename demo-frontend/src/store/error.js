/**
 * 异常捕获
 */
import moment from 'moment'

export default {
  namespaced: true,
  state: {
    hasNew: false,
    errorLog: []
  },
  mutations: {
    /**
      * [setHasNew 是否有新的记录]
     * @param  {[Boolean]} hasNew     [true：有，false：没有]
     */
    setHasNew (state, hasNew) {
      state.hasNew = hasNew
    },
    /**
     * [pushError 记录错误信息]
     * @param  {[Object]} error     [错误信息对象]
     */
    pushError (state, error) {
      state.hasNew = true
      state.errorLog.unshift(Object.assign({}, error, { time: moment().format('HH:mm:ss') }))
    }
  }
}
