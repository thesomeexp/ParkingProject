<template>
    <div>
        <br/>
        <el-page-header @back="goBack" content="个人信息页">
        </el-page-header>
        <br/>

        <table class="profileTable">
            <tr>
                <td width="75">id:</td>
                <td>{{id}}</td>
            </tr>
            <br/>
            <tr>
                <td>用户名:</td>
                <td>{{name}}</td>
            </tr>
            <br/>
            <tr>
                <td>区号:</td>
                <td>{{areaCode}}</td>
            </tr>
            <br/>
            <tr>
                <td>手机号:</td>
                <td>{{phone}}</td>
            </tr>
            <br/>
            <tr>
                <td>状态:</td>
                <td>{{status}}</td>
            </tr>
        </table>

        <br/>
        <el-button style="width: 100%" @click="toUserListParking()">提交的停车场</el-button>
        <br/>
        <el-button style="width: 100%" @click="toUserListTemp()">提交的拥挤度状态</el-button>
        <br/>
        <el-button style="width: 100%" @click="toUserListReview()">提交的评论</el-button>
        <br/>
        <el-button style="width: 100%" @click="toUserListFeedback()">提交的反馈</el-button>
        <br/>
        <el-button style="width: 100%" type="danger" @click="logout()">退出</el-button>

    </div>
</template>

<script>
    import * as serverConf from "../constants/serverConf";
    import * as parkingUtils from "../utils/parkingUtils";

    export default {
        name: "User",
        data() {
          return {
              id: 0,
              name: '',
              areaCode: '',
              phone: '',
              status: 0,
              requestHeaderConf: {
                  headers: {
                      'Accept-Language': parkingUtils.getAcceptLanguage(),
                      'Authorization': parkingUtils.getAuthorization()
                  }
              },
          }
        },
        methods: {
            goBack() {
                parkingUtils.goBack(this)
            },
            toUserListParking() {
                this.$router.push('/userListParking')
            },
            toUserListTemp() {
                this.$router.push('/userListTemp')
            },
            toUserListReview() {
                this.$router.push('/userListReview')
            },
            toUserListFeedback() {
                this.$router.push('/userListFeedback')
            },

            logout() {
                parkingUtils.setAuthorization(null)
                parkingUtils.success(this, '退出成功')
                this.$router.replace('Login')
            },
        },
        mounted() {
            console.log('User.vue mounted()')
            if (!parkingUtils.checkLogin()) {
                parkingUtils.error(this, '未登录')
                this.$router.push('Login')
                return
            }
            this.$http.get(serverConf.server_url + '/user/profile', this.requestHeaderConf)
                .then((res) => {
                    console.log('用户信息', res)
                    let res_data = res.data;
                    // 获取信息成功
                    if (res_data.code === 0) {
                        let res_data_data = res_data.data
                        this.id = res_data_data.id
                        this.name = res_data_data.name
                        this.areaCode = res_data_data.areaCode
                        this.phone = res_data_data.phone
                        this.status = res_data_data.status
                    } else {
                        parkingUtils.error(this, res_data.msg)
                    }
                })
        }
    }
</script>

<style scoped>

    .profileTable {
        margin: 0 auto;
        width: 100%;
    }

</style>