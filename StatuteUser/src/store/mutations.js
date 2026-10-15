/**
 * 该文件用于修改vuex的state数据,且只可调用本页面方法进行修改
 */
export default {
  setIsLogin (state, isLogin) {
    state.isLogin = isLogin
  },
  setUserInfo (state, userInfo) {
    state.userInfo = userInfo
  }
}
