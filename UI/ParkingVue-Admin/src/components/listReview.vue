<template>
  <div>
    <h1 style="text-align: center">评论列表</h1>
    <div>
      <div style="width: 1000px;margin: 0 auto;">
      <el-input class="search"
                style="width: 200px;margin-left: 150px;"
                clearable
                v-model.trim="uid"
                placeholder="用户id">
      </el-input>
      <el-input class="search"
                style="width: 200px;"
                clearable
                v-model.trim="pid"
                placeholder="停车场id">
      </el-input>
      <el-button
        type="primary"
        icon="el-icon-search"
        @click="search">
        查询
      </el-button>
      </div>
      <br>
      <br>
      <el-table :data="reviews"
                border
                style="width: fit-content;position: relative;margin: 0 auto;"
                :header-cell-style="{'text-align':'center'}"
                :cell-style="{'text-align':'center'}"
      >
        <el-table-column prop="id" label="ID" width="75"></el-table-column>
        <el-table-column prop="image" label="停车场图片" width="200">
          <template slot-scope="scope">
            <img style="height: 100px" :src="link + scope.row.pid+'.jpg'">
          </template>
        </el-table-column>
        <el-table-column prop="uid" label="用户id" width="120"></el-table-column>
        <el-table-column prop="pid" label="停车场id" width="120"></el-table-column>
        <el-table-column prop="score" label="评分" width="120"></el-table-column>
        <el-table-column prop="content" label="评论" width="120"></el-table-column>
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
    name: "listReview",
    data() {
      return {
        link: this.GLOBAL.photo_prefix,
        uid: '',
        pid: '',
        reviews: [],
        pageNum: 1, // 当前页数
        pageSize: 5,    //每页大小
        total: 0,
      }
    },

    methods: {

      handleSizeChange: function (pageSize) {
        this.pageSize = pageSize
        this.req_review(this.pageNum, this.pageSize, this.uid, this.pid)
      },

      handleCurrentChange: function (pageNum) {
        this.pageNum = pageNum;
        this.req_review(this.pageNum, this.pageSize, this.uid, this.pid)
      },

      findAll() {
        this.req_review(this.pageNum, this.pageSize, this.uid, this.pid)
      },

      req_review(pageNum, pageSize, uid, pid) {
        this.GLOBAL.checkLoginAndToLogin(this)

        let that = this
        this.$http.get(this.GLOBAL.gateway + '/admin/review',
          {
            headers: {
              'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item),
              'Authorization': this.GLOBAL.jwt_prefix + window.localStorage.getItem(this.GLOBAL.jwt_item)
            },
            params: {
              pageNum: pageNum,
              pageSize: pageSize,
              uid: uid,
              pid: pid
            }
          }).then((res) => {
          console.log('返回的评论信息', res)
          let res_data = res.data
          if (res_data.code === 0) {
            that.reviews = res_data.data.list
            that.pageNum = res_data.data.pageNum
            that.pageSize = res_data.data.pageSize
            that.total = res_data.data.total
          } else {
            this.GLOBAL.error(this, res_data.msg)
          }
        })

      },


      search: function () {
        this.pageNum = 1
        this.req_review(this.pageNum, this.pageSize, this.uid, this.pid)
      },

      toLocation() {
        return 'aa'
      },
      statusFormat(row, column) {
        if (row.status === 0) {
          return '未验证'
        }
        if (row.status === 1) {
          return '已验证'
        }
        if (row.status === 2) {
          return '已禁用'
        }
        return '未知状态'
      }
    },

    created() {
      this.findAll();
    },
  }
</script>

<style scoped>
</style>
