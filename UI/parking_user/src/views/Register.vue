<template>
    <div id="bg">
        <el-card class="box-card register-card">

            <table class="loginTable">
                <tr>
                    <td width="75"><el-input type="text" v-model="areaCode" auto-complete="off" placeholder="区号"></el-input></td>
                    <td><el-input type="text" v-model="phone" auto-complete="off" placeholder="请输入手机号"></el-input></td>
                </tr>
                <tr>
                    <td>用户名:</td>
                    <td><el-input type="text" v-model="name" auto-complete="off" placeholder="请输入用户名"></el-input></td>
                </tr>
                <tr><td>密码:</td>
                    <td><el-input type="password" v-model="password" auto-complete="off" placeholder="请输入密码"></el-input></td>
                </tr>
                <tr>
                    <td>确认密码:</td>
                    <td><el-input type="password" v-model="checkPass" auto-complete="off" placeholder="请输入密码"></el-input></td>
                </tr>
                <tr>
                    <td align="center" colspan="2">
                        <el-button type="primary" @click="Register()">注册</el-button>
                        <el-button @click="toLogin()">去登录</el-button>
                    </td>
                </tr>
            </table>
        </el-card>
    </div>
</template>

<script>
    import * as serverConf from "../constants/serverConf";
    import * as parkingUtils from "../utils/parkingUtils";

    export default {
        name: "Register",
        data() {
            return {
                name: '',
                password: '',
                checkPass: '',
                areaCode: 86,
                phone: ''
            };
        },

        methods: {
            Register() {
                if (this.password !== this.checkPass) {
                    parkingUtils.warning(this, '两次输入的密码不相等')
                    return
                }
                let that = this
                this.$http
                    .post(serverConf.server_url + '/user/register',
                        {
                            name: this.name,
                            password: this.password,
                            areaCode: this.areaCode,
                            phone: this.phone
                        },
                        {
                            headers: {
                                'Accept-Language': parkingUtils.getAcceptLanguage(),
                            }
                        })
                    .then(res => {
                        console.log(res)
                        let res_data = res.data
                        if (res_data.code === 0) {
                            parkingUtils.success(this, res_data.msg + ', 请前往登录')
                            this.$router.replace('/login')
                        } else {
                            parkingUtils.error(this, res_data.msg)
                        }
                    })
                    .catch((err) => {
                        console.log('err: ' + err)
                    })
            },
            toLogin() {
                console.log('Register.vue toLogin()')
                this.$router.replace({
                    path: '/login'
                })
            }
        }
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
        background-size: cover;
        -webkit-background-size: cover;
        -o-background-size: cover;
    }

    .register-card {
        text-align: center;
        margin-top: 150px;
    }

</style>
