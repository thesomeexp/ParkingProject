<template>
    <div>
      <div
        v-show="!(path === '/login')">
      <el-menu
         class="el-menu-demo"
         mode="horizontal"
         @select="handleSelect"
         :default-active="curActive"
         active-text-color="#29916E"
      >
        <el-menu-item index="home">
          <span><i class="el-icon-house"/>首页</span>
        </el-menu-item>
        <el-menu-item index="listUser">
          <span><i class="el-icon-user"/>用户管理</span>
        </el-menu-item>
        <el-menu-item index="listPark">
          <span><i class="el-icon-view"/>停车场管理</span>
        </el-menu-item>
        <el-menu-item index="listReview">
          <span><i class="el-icon-document"/>查看评论</span>
        </el-menu-item>
        <el-menu-item index="listFeedback">
          <span><i class="el-icon-question"/>查看反馈</span>
        </el-menu-item>
        <el-submenu index="">
          <template slot="title"><i class="el-icon-monitor"/>系统管理</template>
          <el-menu-item index="addAdmin">添加管理员</el-menu-item>
          <el-menu-item index="listAdminParent">管理员创建链</el-menu-item>
          <el-menu-item index="listAdminAccount">管理员列表</el-menu-item>
        </el-submenu>
        <el-menu-item index="login" @click="logout">
          <span><i class="el-icon-close"/>退出</span>
        </el-menu-item>
      </el-menu>

      </div>
      <router-view/>
    </div>
</template>

<script>
export default {
  name: 'App',
  data(){
    return{
      curActive: 'home',
      path: ''
    }
  },

  methods:{
    handleSelect(path) {
      this.changeRouter(path)
    },
    logout() {
      window.localStorage.removeItem(this.GLOBAL.jwt_item)
      this.GLOBAL.success(this, '退出成功')
    },
    changeRouter(path) {
      if (path != null) {
        this.$router.push({
          path: path
        })
      }
    }
  },

  // 判断路由
  mounted() {
    this.path = this.$route.path;   //this.path = 当前页面路由
    // console.log(this.$route.path)
  },
  //监听路由变化
  watch:{
    $route(to,from){
      this.path = to.path //打开新页面路由
      this.curActive = to.name
      // 修复退出登录后在登陆 focus 在退出的菜单
      if (to.path === this.GLOBAL.home_path || from.path === this.GLOBAL.login_path) {
        this.curActive = 'home'
      }
    }
  }
}
</script>

<style>
   a{text-decoration: none}
</style>
