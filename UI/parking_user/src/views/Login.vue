<template>
    <div id="bg">
        <div class="loginDiv">
            <el-card class="box-card">
                <table class="loginTable">
                    <tr>
                        <td width="75">
                            <el-input type="text" v-model="areaCode" auto-complete="off" placeholder="区号"></el-input>
                        </td>
                        <td>
                            <el-input type="text" v-model="phone" auto-complete="off" placeholder="请输入手机号"></el-input>
                        </td>
                    </tr>
                    <p/>
                    <tr>
                        <td>
                            密码:
                        </td>
                        <td>
                            <el-input type="password" v-model="password" auto-complete="off" placeholder="请输入密码"></el-input>
                        </td>
                    </tr>
                    <tr>
                        <td align="center" colspan="2">
                            <el-button type="primary" @click="Login()">登录</el-button>
                            <el-button @click="toRegister()">去注册</el-button>
                        </td>
                    </tr>
                </table>
            </el-card>
        </div>
        <p/>
    </div>
</template>

<script>
    import * as serverConf from "../constants/serverConf";
    import * as parkingUtils from "../utils/parkingUtils";

    export default {
        name: "Login",
        data() {
            return {
                areaCode: 86,
                phone: '',
                password: ''
            }
        },
        methods: {
            Login() {
                console.log('Login.vue Login()')
                this.$http
                    .post(serverConf.server_url + '/user/login',
                        {
                            areaCode: this.areaCode,
                            phone: this.phone,
                            password: this.password
                        },
                        {
                            headers: {
                                'Accept-Language': parkingUtils.getAcceptLanguage(),
                            }
                        })
                    .then((res) => {
                        console.log(res)
                        let res_data = res.data;
                        // 登录成功
                        if (res_data.code === 0) {
                            // 存储token到localStorage
                            parkingUtils.setAuthorization(res_data.data)
                            parkingUtils.success(this, res_data.msg)
                            parkingUtils.goBack(this)
                        } else {
                            parkingUtils.error(this, res_data.msg)
                        }
                    })
                    .catch((err) => {
                        console.log('err: ' + err)
                    })
            },
            toRegister() {
                console.log('Login.vue toRegister()')
                this.$router.replace({
                    path: '/register'
                })
            }
        },
    }
</script>

<style scoped>

    .loginTable {
        margin: 0 auto;
        width: 100%;
    }

    #bg {
        background: url(../assets/bg.jpg) no-repeat;
        position: absolute;
        top: 0px;
        bottom: 0px;
        left: 0px;
        right: 0px;
        /*background-size:100% 100%;*/
        background-size: cover;
        -webkit-background-size: cover;
        -o-background-size: cover;
    }

    #titleLogo {
        text-align: center;
        font-family: Georgia;
        font-size: 40px;

    }

    .loginDiv {
        text-align: center;
        margin-top: 200px;
    }
</style>