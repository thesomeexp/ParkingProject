<template>
  <div>
    <div style="width: 380px;margin: 0 auto;">
      <h1 style="text-align: center">管理员创建链</h1>
      <el-form :model="admin" status-icon ref="mark" label-width="165px" class="demo-ruleForm"
               style="width: 380px;margin: 0 auto;">
        <el-form-item label="管理员id">
          <el-input type="text" v-model="admin.id" auto-complete="off"></el-input>
          <el-button type="primary" plain @click="search()" style="margin: auto">查询</el-button>
          <el-select style="" v-model="admin.isRelate" placeholder="是包含其子账号">
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
          <el-button type="primary" plain @click="deban()" style="margin: auto">解封</el-button>
          <el-button type="danger" plain @click="ban()" style="margin: auto">封禁</el-button>
        </el-form-item>
        <div style="width: 70px;margin: 0 auto;">
        </div>
      </el-form>

    </div>
    <el-table :data="admins"
              border
              style="width: fit-content;position: relative;margin: 0 auto;"
              :header-cell-style="{'text-align':'center'}"
              :cell-style="{'text-align':'center'}"
    >
      <el-table-column prop="id" label="ID" width="75"></el-table-column>
      <el-table-column prop="parent" label="父级ID" width="75">
      </el-table-column>
      <el-table-column prop="name" label="管理员名字" width="120"></el-table-column>
      <el-table-column prop="phone" label="管理员手机号" width="120"></el-table-column>
      <el-table-column prop="status" label="状态" width="120">
        <template slot-scope="scope">
          <span style="color: orange" v-if="scope.row.status === 0">未激活</span>
          <span style="color: green" v-if="scope.row.status === 1">正常</span>
          <span style="color: red" v-if="scope.row.status === 2">封禁</span>
        </template>
      </el-table-column>
    </el-table>
  </div>
</template>

<script>
  export default {
    name: "listAdminParent",
    data() {
      return {
        admin: {
          id: 1,
          isRelate: false
        },
        admins: [],
        options: [
          {
            value: true,
            label: '包含其子账号'
          },
          {
            value: false,
            label: '不包含其子账号'
          }
        ],
      }
    },
    methods: {
      search() {
        this.GLOBAL.checkLoginAndToLogin(this)

        let that = this
        this.$http.get(this.GLOBAL.gateway + '/admin/account/parent/' + that.admin.id,
          {
            headers: {
              'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item),
              'Authorization': this.GLOBAL.jwt_prefix + window.localStorage.getItem(this.GLOBAL.jwt_item)
            }
          }).then((res) => {
          console.log('返回的管理员信息', res)
          let res_data = res.data
          if (res_data.code === 0) {
            that.admins = res_data.data
          } else {
            this.GLOBAL.error(this, res_data.msg)
          }
        })
      },
      ban() {
        this.banbanban(this.admin.id, this.admin.isRelate, 2)
      },
      deban() {
        this.banbanban(this.admin.id, this.admin.isRelate, 1)
      },
      banbanban(id, isRelated, status) {
        this.GLOBAL.checkLoginAndToLogin(this)
        this.$http.post(this.GLOBAL.gateway + "/admin/account/ban",
          {
            "id": id,
            "isRelated": isRelated,
            "status": status
          },
          {
            headers: {
              'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item),
              'Authorization': this.GLOBAL.jwt_prefix + window.localStorage.getItem(this.GLOBAL.jwt_item)
            }
          }
        ).then(res => {
          console.log('修改账号状态返回', res)
          let res_data = res.data
          if (res_data.code === 0) {
            this.GLOBAL.success(this, res_data.msg)
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
