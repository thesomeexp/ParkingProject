<template>

</template>

<script>
  export default {
    name: "Global",
    // 图片的链接
    pre_url: "http://localhost:8080",
    photo_prefix: "http://localhost:8080/parking/photo/",
    // 请求的链接
    gateway: "http://localhost:8080",
    language_item: 'parkingLanguage',
    language: 'ch',
    jwt_item: 'accessToken',
    authorization: 'Authorization',
    jwt_prefix: 'Bearer ',

    index_path: '/',
    login_path: '/login',
    home_path: '/home',
    list_park_path: '/listPark',
    logout_path: '/logout',
    get_parking_path: '/getParking',
    list_review_path: '/listReview',
    list_feedback_path: '/listFeedback',
    add_admin_path: '/addAdmin',
    list_admin_parent_path: '/listAdminParent',
    list_admin_account_path: '/listAdminAccount',
    list_user_path: '/listUser',
    get_user_path: '/getUser',


    checkLogin(jwt) {
      if (jwt != null) {
        // 判断是否过期
        let jwt1 = decodeURIComponent(escape(window.atob(jwt.split('.')[1])))
        let exp = JSON.parse(jwt1).exp
        // 时间戳位数
        let nowT = new Date().getTime() / 1000
        // console.log('当前时间戳: ' + nowT)
        // console.log('jwt时间戳: ' + exp)
        if (nowT > exp) {
          console.log('jwt过期')
          return false
        }
        return true
      } else {
        console.log('jwt为null')
        return false
      }
    },

    checkLoginAndToLogin(from) {
      let local_jwt = window.localStorage.getItem(this.jwt_item)
      if (local_jwt === undefined || local_jwt === null || !this.checkLogin(local_jwt)) {
        from.$router.push({
          path: this.login_path,
        })
      }
    },

    error(from, msg) {
      from.$message({
        message: msg,
        type: 'error'
      });
    },

    success(from, msg) {
      from.$message({
        message: msg,
        type: 'success'
      });
    }
  }
</script>

<style scoped>

</style>
