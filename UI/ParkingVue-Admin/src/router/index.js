import Vue from 'vue'
import Router from 'vue-router'
import Home from "../components/Home"
import listPark from "../components/listPark"
import listUser from "../components/listUser"
import GetParking from "../components/GetParking"
import GetUser from "../components/GetUser"
import Login from "../components/Login"
import GetDataTest from "../test/GetDataTest"
import testHeader from "../test/postHeader"
import Register from "../components/Register";
import UploadHeadDemo from "../test/UploadHeadDemo";
import ImageCORSTest from "../test/ImageCORSTest";
import AddParking from "../components/AddParking";
import listMyPark from "../components/listMyPark";
import listDisablePark from "../components/listDisablePark";
import listNoVerifiedPark from "../components/listNoVerifiedPark";
import listVerifiedPark from "../components/listVerifiedPark";
import backToDisable from "../fallback/backToDisable";
import backToNoVerified from "../fallback/backToNoVerified";
import backToVerified from "../fallback/backToVerified";
import AddState from "../components/AddState";
import backToAddState from "../fallback/backToAddState";
import AddMark from "../components/AddMark";
import listReview from "../components/listReview";
import listFeedback from "../components/listFeedback";
import addAdmin from "../components/addAdmin";
import listAdminParent from "../components/listAdminParent";
import listAdminAccount from "../components/listAdminAccount";
import AddInfoImage from "../components/AddInfoImage";
import listVerifiedInfoImage from "../components/listVerifiedInfoImage";
import backToVerifiedImage from "../fallback/backToVerifiedImage";
import backToDisableImage from "../fallback/backToDisableImage";
import listDisableInfoImage from "../components/listDisableInfoImage";
import listNoVerifiedImage from "../components/listNoVerifiedImage";
import listInfoImage from "../components/listInfoImage";
import Index from "../components/Index";
import TestChart from "../components/TestChart";
// 引入我的global
import global_ from '../utils/Global'

// 解决ElementUI 导航栏中vue-router在3.0版本上重复点菜单报错问题
const originalPush = Router.prototype.push
Router.prototype.push = function push(location) {
  return originalPush.call(this, location).catch(err => err)
}

Vue.use(Router)

export default new Router({
  mode:'history',
  routes: [
    {name: 'index', path: global_.index_path, component: Index},
    {name: 'login', path: global_.login_path, component: Login},
    {name: 'home', path: global_.home_path, component: Home},
    {name: 'listPark', path: global_.list_park_path, component: listPark},
    {name: 'getParking', path: global_.get_parking_path, component: GetParking},
    {name: 'listReview', path: global_.list_review_path, component: listReview},
    {name: 'listFeedback', path: global_.list_feedback_path, component: listFeedback},
    {name: 'addAdmin', path: global_.add_admin_path, component: addAdmin},
    {name: 'listAdminParent', path: global_.list_admin_parent_path, component: listAdminParent},
    {name: 'listAdminAccount', path: global_.list_admin_account_path, component: listAdminAccount},
    {name: 'listUser', path: global_.list_user_path, component: listUser},
    {name: 'getUser', path: global_.get_user_path, component: GetUser},

    {path: '/testChart', component: TestChart},
    {path: '/gdata', component: GetDataTest},
    {path: '/testHeader', component: testHeader},
    {path: '/register', component: Register},
    {path: '/head', component: UploadHeadDemo},
    {path: '/uploadHeadDemo', component: UploadHeadDemo},
    {path: '/ImageCORSTest', component: ImageCORSTest},
    {path: '/addparking', component: AddParking},
    {path: '/listMyPark', component: listMyPark},
    {path: '/listDisablePark', component: listDisablePark},
    {path: '/listNoVerifiedPark', component: listNoVerifiedPark},
    {path: '/listVerifiedPark', component: listVerifiedPark},
    {path: '/backToDisable', component: backToDisable},
    {path: '/backToNoVerified', component: backToNoVerified},
    {path: '/backToVerified', component: backToVerified},
    {path: '/backToAddState', component: backToAddState},
    {path: '/backToVerifiedImage', component: backToVerifiedImage},
    {path: '/backToDisableImage', component: backToDisableImage},
    {path: '/backToNoVerifiedImage', component: backToDisableImage},
    {path: '/addstate', component: AddState},
    {path: '/addmark', component: AddMark},
    {path: '/addInfoImage', component: AddInfoImage},
    {path: '/listVerifiedImage', component: listVerifiedInfoImage},
    {path: '/listDisableImage', component: listDisableInfoImage},
    {path: '/listNoVerifiedImage', component: listNoVerifiedImage},
    {path: '/listInfoImage', component: listInfoImage},
  ]
})
