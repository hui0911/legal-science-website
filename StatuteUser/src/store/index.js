import Vue from 'vue'
import Vuex from 'vuex'
/**
 * 此为vuex总输出页面,需要进行对应操作请前往对应子页面
 */
import state from './state'
import mutations from './mutations'
import getters from './getters'
import actions from './actions'
import createPersistedState from 'vuex-persistedstate'

Vue.use(Vuex)

export default new Vuex.Store({
  state, // 状态
  getters, // getter，相当于计算属性
  mutations, // 更新状态，同步
  actions, // 异步
  plugins: [createPersistedState({
    storage: window.sessionStorage,
    reducer (val) {
      // 此时，当count发生改变的时候，就会调用此函数，并且val的值为当前state对象，return的值为当前本地存储的value值（本地存储的key值为vuex）
      return {
        userInfo: val.userInfo,
        isLogin: val.isLogin
      }
    }
  })]
})
