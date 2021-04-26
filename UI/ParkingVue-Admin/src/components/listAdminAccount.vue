<template>
  <div>
    <h1 style="text-align: center">管理员列表</h1>
    <div>
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
    <br>
    <el-pagination
      style="margin-left: 30%"
      background
      @size-change="handleSizeChange"
      @current-change="handleCurrentChange"
      :current-page="pageNum"
      :page-sizes="[1, 2, 3, 5, 10, 20, 50, 100]"
      :page-size="pageSize"
      layout="sizes, total, prev, pager, next"
      :total="total">
    </el-pagination>
    <router-view/>
  </div>
</template>

<script>
    export default {
        name: "listAdminAccount",
      data() {
        return {
          link: this.GLOBAL.photo_prefix,
          uid: '',
          pid: '',
          admins: [],
          pageNum: 1, // 当前页数
          pageSize: 5,    //每页大小
          total: 0,
          status: 0,
          options: [
            {
              value: 0,
              label: '未处理'
            },
            {
              value: 1,
              label: '已处理'
            }
          ],
          request_header: {
            'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item),
            'Authorization': this.GLOBAL.jwt_prefix + window.localStorage.getItem(this.GLOBAL.jwt_item)
          }
        }
      },

      methods: {

        handleSizeChange: function (pageSize) {
          this.pageSize = pageSize
          this.req_admin(this.pageNum, this.pageSize)
        },

        handleCurrentChange: function (pageNum) {
          this.pageNum = pageNum;
          this.req_admin(this.pageNum, this.pageSize)
        },

        findAll() {
          this.req_admin(this.pageNum, this.pageSize)
        },

        req_admin(pageNum, pageSize) {
          this.GLOBAL.checkLoginAndToLogin(this)

          let that = this
          this.$http.get(this.GLOBAL.gateway + '/admin/account',
            {
              headers: this.request_header,
              params: {
                pageNum: pageNum,
                pageSize: pageSize,
              }
            }).then((res) => {
            console.log('返回的管理员信息', res)
            let res_data = res.data
            if (res_data.code === 0) {
              that.admins = res_data.data.list
              that.pageNum = res_data.data.pageNum
              that.pageSize = res_data.data.pageSize
              that.total = res_data.data.total
            } else {
              this.GLOBAL.error(this, res_data.msg)
            }
          })
        },

        changeStatus(id, status) {
          console.log(id, status)
          this.GLOBAL.checkLoginAndToLogin(this)
          let that = this
          this.$http
            .patch(this.GLOBAL.gateway + '/admin/feedback/' + id, {
              'status': status
            }, {
              headers: this.request_header
            }).then(res => {
            console.log('更新反馈状态', res)
            let res_data = res.data
            if (res_data.code === 0) {
              this.req_admin(this.pageNum, this.pageSize)
            } else {
              this.GLOBAL.error(this, res_data.msg)
            }
          })
        },

        search: function () {
          this.pageNum = 1
          this.req_admin(this.pageNum, this.pageSize)
        },


      },

      created() {
        this.findAll();
      },
    }
</script>

<style scoped>

</style>
