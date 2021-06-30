import Vue from 'vue'
import App from './App'
import router from './router'

import 'normalize.css/normalize.css' // A modern alternative to CSS resets

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/zh-CN' // lang i18n

import store from './store'

import '@/icons' // icon
import '@/permission' // permission control
import '@/utils/global' // 自定义全局js

import VueCodeMirror from 'vue-codemirror'
import 'codemirror/lib/codemirror.css'
Vue.use(VueCodeMirror)

/**
 * If you don't want to use mock-server
 * you want to use mockjs for request interception
 * you can execute:
 *
 * import { mockXHR } from '../mock'
 * mockXHR()
 */

import '@/styles/index.scss' // global css
import 'default-passive-events'

// set ElementUI lang to EN
Vue.use(ElementUI, { locale })

Vue.config.productionTip = false

import Moment from 'moment'
Vue.prototype.$moment = Moment

new Vue({
  el: '#app',
  router,
  store,
  render: h => h(App)
})
