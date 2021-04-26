<template>
  <div class="login-container">
    <el-form ref="information" :model="information" class="login-form" autocomplete="on" label-position="left">
      <div class="title-container">
        <h3 class="title">停车场后台管理系统</h3>
      </div>

      <el-form-item prop="phone">
        <span class="svg-container">
          <i class="el-icon-user-solid"/>
        </span>
        <el-input
          ref="phone"
          v-model="information.phone"
          placeholder="Phone"
          name="phone"
          type="text"
          tabindex="1"
          autocomplete="on"
        />
      </el-form-item>

      <el-tooltip v-model="capsTooltip" content="大写已打开" placement="right" manual>
        <el-form-item prop="password">
          <span class="svg-container">
            <i class="el-icon-lock"/>
          </span>
          <el-input
            :key="passwordType"
            ref="password"
            v-model="information.password"
            :type="passwordType"
            placeholder="Password"
            name="password"
            tabindex="2"
            autocomplete="on"
            @keyup.native="checkCapslock"
            @blur="capsTooltip = false"
            @keyup.enter.native="login"
          />
        </el-form-item>
      </el-tooltip>

      <el-button :loading="loading" type="primary" style="width:100%;margin-bottom:30px;" @click.native.prevent="Login">登录</el-button>
    </el-form>
  </div>
</template>

<script>
  import * as parkingUtils from "../utils/parkingUtils";

  export default {
    name: 'Login',
    data() {
      return {
        information: {
          phone: '1',
          password: '111'
        },
        passwordType: 'password',
        capsTooltip: false,
        loading: false,
        showDialog: false,
        redirect: undefined,
        otherQuery: {}
      }
    },

    mounted() {
      window.localStorage.removeItem(this.GLOBAL.jwt_item)
      if (this.information.phone === '') {
        this.$refs.phone.focus()
      } else if (this.information.password === '') {
        this.$refs.password.focus()
      }
    },

    methods: {
      Login() {
        // 取得提交的对象
        let req_data = {
          phone: this.information.phone,
          password: this.information.password
        };
        // 转换为formData
        // let str = this.$qs.stringify(req_data)
        let that = this;
        this.$refs.information.validate(valid => {
          if (valid) {
            this.loading = true
            this.$http
              .post(this.GLOBAL.gateway + '/admin/login',
                req_data,
                {
                  headers: {
                    'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item)
                  }
                })
              .then((response) => {
                console.log('登录响应', response)
                let result = response.data;
                // 登录成功
                if (result.code === 0) {
                  // 存储token到localStorage
                  window.localStorage.setItem("accessToken", result.data)
                  parkingUtils.success(this, result.msg)
                  this.$router.push(this.GLOBAL.home_path)
                } else {
                  this.loading = false
                  parkingUtils.error(this, result.msg)
                }
              })
              .catch((err)=>{
                this.loading = false
                console.log('err: ' + err)
              })
          }
        })
      },

      checkCapslock(e) {
        const { key } = e
        this.capsTooltip = key && key.length === 1 && (key >= 'A' && key <= 'Z')
      },

    }
  }
</script>

<style lang="scss">
  /* 修复input 背景不协调 和光标变色 */

  $bg:#283443;
  $light_gray:#fff;
  $cursor: #fff;

  @supports (-webkit-mask: none) and (not (cater-color: $cursor)) {
    .login-container .el-input input {
      color: $cursor;
    }
  }

  /* reset element-ui css */
  .login-container {
    .el-input {
      display: inline-block;
      height: 47px;
      width: 85%;

      input {
        background: transparent;
        border: 0px;
        -webkit-appearance: none;
        border-radius: 0px;
        padding: 12px 5px 12px 15px;
        color: $light_gray;
        height: 47px;
        caret-color: $cursor;

        &:-webkit-autofill {
          box-shadow: 0 0 0 1000px $bg inset !important;
          -webkit-text-fill-color: $cursor !important;
        }
      }
    }

    .el-form-item {
      border: 1px solid rgba(255, 255, 255, 0.1);
      background: rgba(0, 0, 0, 0.11);
      border-radius: 5px;
      color: #ff0000;
    }
  }
</style>

<style lang="scss" scoped>
  $bg: #107b7b;
  $dark_gray:#889aa4;
  $light_gray: #fcf4f4;

  .login-container {
    height: 100%;
    width: 100%;
    position: fixed;
    background-color: $bg;
    overflow: hidden;
    margin: -8px;

    .login-form {
      position: relative;
      width: 400px;
      max-width: 100%;
      padding: 160px 35px 0;
      margin: 0 auto;
      overflow: hidden;
    }

    .tips {
      font-size: 14px;
      color: #fff;
      margin-bottom: 10px;

      span {
        &:first-of-type {
          margin-right: 16px;
        }
      }
    }

    .svg-container {
      padding: 6px 5px 6px 15px;
      color: $dark_gray;
      vertical-align: middle;
      width: 30px;
      display: inline-block;
    }

    .title-container {
      position: relative;

      .title {
        font-size: 26px;
        color: $light_gray;
        margin: 0px auto 40px auto;
        text-align: center;
        font-weight: bold;
      }
    }

    .show-pwd {
      position: absolute;
      right: 10px;
      top: 7px;
      font-size: 16px;
      color: $dark_gray;
      cursor: pointer;
      user-select: none;
    }

    .thirdparty-button {
      position: absolute;
      right: 0;
      bottom: 6px;
    }

    @media only screen and (max-width: 470px) {
      .thirdparty-button {
        display: none;
      }
    }
  }
</style>
