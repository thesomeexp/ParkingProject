<template>
  <div>
    <div style="width: 380px;margin: 0 auto;">
    <h1 style="text-align: center">添加管理员</h1>
    <el-form :model="admin" status-icon ref="mark" label-width="165px" class="demo-ruleForm" style="width: 380px;margin: 0 auto;">
      <el-form-item label="管理员名字">
        <el-input type="text" v-model="admin.name" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="管理员手机号">
        <el-input type="text" v-model="admin.phone" auto-complete="off"></el-input>
      </el-form-item>
      <el-form-item label="管理员密码">
        <el-input type="text" v-model="admin.password" auto-complete="off"></el-input>
      </el-form-item>
      <div  style="width: 70px;margin: 0 auto;">
        <el-button type="primary" plain @click="add()" style="margin: auto">提交</el-button>
      </div>
    </el-form>
    </div>
  </div>
</template>

<script>
  export default {
    name: "addAdmin",
    data() {
      return {
        admin: {
          name: '',
          phone: '',
          password: '',
        }
      }
    },
    methods: {
        add() {
          this.GLOBAL.checkLoginAndToLogin(this)
          this.$http.post(this.GLOBAL.gateway + "/admin/account",
            {
              "name": this.admin.name,
              "phone": this.admin.phone,
              "password": this.admin.password
            },
            {
              headers: {
                'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item),
                'Authorization': this.GLOBAL.jwt_prefix + window.localStorage.getItem(this.GLOBAL.jwt_item)
              }
            }
          ).then(res => {
            console.log('添加管理员返回', res)
            let res_data = res.data
            if (res_data.code === 0) {
              this.GLOBAL.success(this, "添加成功")
              this.admin.name = ''
              this.admin.phone = ''
              this.admin.password = ''
            } else {
              this.GLOBAL.error(this, res_data.msg)
            }
          })
        }
    }
  }
</script>

<style scoped>

</style>
