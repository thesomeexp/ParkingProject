<template>
  <div>
    <h1 style="text-align: center">停车场管理</h1>
    <div style="width: 1000px;margin: 0 auto;">
      <el-input class="search"
                style="width: 200px;left: 150px"
                clearable
                v-model.trim="name"
                placeholder="输入停车场名称">
      </el-input>
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
      <el-table :data="information"
                border
                style="width: fit-content;position: relative;margin: 0 auto;"
                :header-cell-style="{'text-align':'center'}"
                :cell-style="{'text-align':'center'}"
      >
        <el-table-column prop="id" label="ID" width="75"></el-table-column>
        <el-table-column prop="image" label="停车场图片" width="200">
          <template slot-scope="scope">
            <img style="height: 100px" :src="link + scope.row.id+'.jpg'">
          </template>
        </el-table-column>
        <el-table-column prop="name" label="停车场名称" width="120"></el-table-column>
        <el-table-column prop="content" label="停车场描述" width="200"></el-table-column>
        <el-table-column label="停车场坐标" width="175">
          <template slot-scope="scope">
            {{scope.row.longitude}},{{scope.row.latitude}}
          </template>
        </el-table-column>
        <el-table-column prop="uid" label="提交用户ID" width="120"></el-table-column>
        <el-table-column prop="status" label="状态" width="120" :formatter="statusFormat"></el-table-column>
        <el-table-column label="操作" width="120">
          <template slot-scope="scope">
            <el-button type="text" @click="handleWatch(scope.row.id)" size="big">查看详情</el-button>
          </template>
        </el-table-column>
      </el-table>
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
    name: "listPark",
    data() {
      return {
        link: this.GLOBAL.photo_prefix,
        name: '',
        status: 0,
        information: [],
        pageNum: 1, // 当前页数
        pageSize: 5,    //每页大小
        total: 0,
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
      handleWatch(id) {
        this.$router.push({
          path: this.GLOBAL.get_parking_path,
          query: {id: id}
        })
      },

      handleSizeChange: function (pageSize) {
        this.pageSize = pageSize
        this.req_parking(this.pageNum, this.pageSize, this.name, this.status)
      },

      handleCurrentChange: function (pageNum) {
        this.pageNum = pageNum;
        this.req_parking(this.pageNum, this.pageSize, this.name, this.status)
      },

      findAll() {
        this.req_parking(this.pageNum, this.pageSize, this.name, this.status)
      },

      req_parking(pageNum, pageSize, name, status) {
        this.GLOBAL.checkLoginAndToLogin(this)

        let that = this
        this.$http.get(this.GLOBAL.gateway + '/admin/parking',
          {
            headers: {
              'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item),
              'Authorization': this.GLOBAL.jwt_prefix + window.localStorage.getItem(this.GLOBAL.jwt_item)
            },
            params: {
              pageNum: pageNum,
              pageSize: pageSize,
              name: name,
              status: status
            }
          }).then((res) => {
          console.log('返回的停车场信息', res)
          let res_data = res.data
          if (res_data.code === 0) {
            that.information = res_data.data.list
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
        this.req_parking(this.pageNum, this.pageSize, this.name, this.status)
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
