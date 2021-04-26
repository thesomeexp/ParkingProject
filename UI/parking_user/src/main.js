import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import axios from 'axios'
import VueAxios from 'vue-axios'

import installElementPlus from './plugins/element'

const app = createApp(App)
installElementPlus(app)
app.use(VueAxios, axios)
app.use(router).mount('#app')