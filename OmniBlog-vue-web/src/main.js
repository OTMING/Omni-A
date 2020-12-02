// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'

import  VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'
Vue.use(VueQuillEditor)

import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css';
import 'element-ui/lib/theme-chalk/display.css';


Vue.use(ElementUI);

import App from './App'
import router from './router'

import axios from 'axios';

import VueAxios from 'vue-axios'
axios.defaults.baseURL = '/api'
Vue.prototype.$axios = axios

Vue.use(axios,VueAxios)

Vue.config.productionTip = false


Vue.use(router);


/* eslint-disable no-new */
new Vue({
  el: '#app',
  render: h => h(App),
  router,
  components: { App }

})

