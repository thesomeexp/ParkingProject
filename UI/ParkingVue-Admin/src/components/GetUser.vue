<template>
  <div>

    <table class="el-table" style="width: 80%;height: 55%;margin: 3% 12.5%">
      <tr>
        <td style="text-align: center">用户ID</td>
        <td style="text-align: center">用户名</td>
        <td style="text-align: center">手机号区号</td>
        <td style="text-align: center">手机号</td>
        <td style="text-align: center">状态</td>
        <td style="text-align: center">操作</td>
      </tr>
      <tr style="text-align: center">
        <td style="text-align: center">{{user.id}}</td>
        <td style="text-align: center"><el-input v-model="user.name" style="width: 200px" :disabled="isDisable"></el-input></td>
        <td style="text-align: center">{{user.areaCode}}</td>
<!--        <td style="text-align: center"><el-input v-model="user.areaCode" style="width: 200px" :disabled="isDisable"></el-input></td>-->
        <td style="text-align: center">{{user.phone}}</td>
        <td style="text-align: center">
          <el-select style="width: 100px" v-model="user.status" :disabled="isDisable" >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </td>
        <td style="text-align: center">
          <div style="width: 100%" v-show="isDisable">
            <el-button type="primary" icon="el-icon-edit" @click="changeDisable()" ></el-button>
            <el-button icon="el-icon-refresh" @click="refresh_user()" ></el-button>
          </div>
          <div style="width: 100%" v-show="!isDisable">
            <el-button type="primary" icon="el-icon-check" @click="submitChange()" ></el-button>
            <el-button icon="el-icon-close"  @click="changeDisable()" ></el-button>
          </div>
        </td>
      </tr>
    </table>

    <router-view/>
  </div>

</template>

<script>

    export default {
        name: "GetUser.vue",
      data() {
        return {
          req_id: 0,
          user: {
            name: '',
            areaCode: 0,
            phone: '',
            status: 0
          },
          isDisable: true,
          options: [
            {
              value: 0,
              label: '未验证'
            },
            {
              value: 1,
              label: '已验证'
            },
            {
              value: 2,
              label: '已禁用'
            }
          ]
        }
      },

      methods: {
        submitChange() {
          this.update_user()
          this.changeDisable()
        },
        changeDisable() {
          this.isDisable = !this.isDisable
        },

        initData() {
          this.req_id = this.$route.query.id
          this.refresh_user()
        },

        refresh_user() {
          this.GLOBAL.checkLoginAndToLogin(this)
          let that = this
          this.$http.get(this.GLOBAL.gateway + "/admin/user/" + that.req_id, {
            headers: {
              'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item),
              'Authorization': this.GLOBAL.jwt_prefix + window.localStorage.getItem(this.GLOBAL.jwt_item)
            }
          }).then(res => {
            console.log('单条用户数据', res)
            let res_data = res.data
            if (res_data.code === 0) {
              that.user = res_data.data
            } else {
              this.GLOBAL.error(this, res_data.msg)
            }
          })
        },

        update_user() {
          this.GLOBAL.checkLoginAndToLogin(this)
          let that = this
          this.$http
            .put(this.GLOBAL.gateway + '/admin/user/' + that.req_id, {
              'name': that.user.name,
              'status': that.user.status,
            }, {
              headers: {
                'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item),
                'Authorization': this.GLOBAL.jwt_prefix + window.localStorage.getItem(this.GLOBAL.jwt_item)
              }
            }).then(res => {
            console.log('更新返回', res)
            let res_data = res.data
            if (res_data.code === 0) {
              this.GLOBAL.success(this, '修改成功')
              this.refresh_user()
            } else {
              this.GLOBAL.error(this, res_data.msg)
            }
          })
        }

      },

      created() {
        this.initData();
      },

      mounted() {

      },
    }

</script>

<style scoped>

</style>
