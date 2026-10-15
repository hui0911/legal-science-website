import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import './globalstyle.scss'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import Viewer from 'v-viewer' // 引入js
import 'viewerjs/dist/viewer.css' // 引入css
Vue.use(Viewer) // 默认配置写法
// markdown编辑器
import mavonEditor from 'mavon-editor'
import 'mavon-editor/dist/css/index.css'
Vue.use(mavonEditor)

// 全局组件
import Footer from '@/components/Footer/index.vue'
import BasicHeader from '@/components/BasicHeader/index.vue'
import BasicCard from '@/components/BasicCard/index.vue'
import Pagination from '@/components/Pagination/index.vue'
import ImageUpload from '@/components/ImageUpload/index.vue'
import ImgPreview from '@/components/ImgPreview/index.vue'
import BasicDialog from '@/components/BasicDialog/index.vue'
import BasicEmpty from '@/components/BasicEmpty/index.vue'

Vue.component('BasicEmpty', BasicEmpty)
Vue.component('BasicHeader', BasicHeader)
Vue.component('BasicCard', BasicCard)
Vue.component('BasicDialog', BasicDialog)
Vue.component('Pagination', Pagination)
Vue.component('Footer', Footer)
Vue.component('ImageUpload', ImageUpload)
Vue.component('ImgPreview', ImgPreview)


Vue.use(ElementUI)
Vue.config.productionTip = false


// 导出Vue，router，api，可能会用到this，可使用rootVueObj
const rootVueObj = new Vue({
  router: router,
  store,
  render: h => h(App)
}).$mount('#app')

export default rootVueObj
