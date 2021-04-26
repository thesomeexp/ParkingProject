<template>
  <div>
    <h1 style="text-align: center">反馈列表</h1>
    <div>
      <div style="width: 1000px;margin: 0 auto;">
        <el-select style="margin-left: 150px" v-model="status" placeholder="状态">
          <el-option
            v-for="item in options"
            :key="item.value"
            :label="item.label"
            :value="item.value">
          </el-option>
        </el-select>
        <el-button
          type="primary"
          icon="el-icon-search"
          @click="search">
          查询
        </el-button>
      </div>
      <br>
      <br>
      <el-table :data="feedbacks"
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
        <el-table-column prop="content" label="反馈内容" width="120"></el-table-column>
        <el-table-column prop="contact" label="联系方式" width="120"></el-table-column>
        <el-table-column prop="image" label="操作" width="120">
          <template slot-scope="scope">
            <el-button v-if="scope.row.status === 0" @click="changeStatus(scope.row.id, 1)">已处理</el-button>
            <el-button v-if="scope.row.status === 1" @click="changeStatus(scope.row.id, 0)">取消处理</el-button>
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
    name: "listFeedback",
    data() {
      return {
        link: this.GLOBAL.photo_prefix,
        uid: '',
        pid: '',
        feedbacks: [],
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
        this.req_feedback(this.pageNum, this.pageSize, this.status)
      },

      handleCurrentChange: function (pageNum) {
        this.pageNum = pageNum;
        this.req_feedback(this.pageNum, this.pageSize, this.status)
      },

      findAll() {
        this.req_feedback(this.pageNum, this.pageSize, this.status)
      },

      req_feedback(pageNum, pageSize, status) {
        this.GLOBAL.checkLoginAndToLogin(this)

        let that = this
        this.$http.get(this.GLOBAL.gateway + '/admin/feedback',
          {
            headers: this.request_header,
            params: {
              pageNum: pageNum,
              pageSize: pageSize,
              status: status
            }
          }).then((res) => {
          console.log('返回的反馈信息', res)
          let res_data = res.data
          if (res_data.code === 0) {
            that.feedbacks = res_data.data.list
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
            this.req_feedback(this.pageNum, this.pageSize, this.status)
          } else {
            this.GLOBAL.error(this, res_data.msg)
          }
        })
      },

      search: function () {
        this.pageNum = 1
        this.req_feedback(this.pageNum, this.pageSize, this.status)
      },


    },

    created() {
      this.findAll();
    },
  }
</script>

<style scoped>
</style>
