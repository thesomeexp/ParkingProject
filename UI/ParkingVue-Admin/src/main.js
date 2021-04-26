// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
import axios from 'axios'
import qs from 'qs'
/* 完整引入elmentui*/
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';
// 引入Global
import Global from './utils/Global'

Vue.prototype.GLOBAL = Global;
Vue.config.productionTip = false
Vue.prototype.$http = axios
Vue.prototype.$qs = qs;
Vue.use(ElementUI);

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router,
  components: { App },
  template: '<App/>'
})
