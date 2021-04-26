<template>
    <div>
      <h1 style="text-align: center">已禁用停车场列表</h1>
      <el-table :data="information.slice((currentPage-1)*pageSize,currentPage*pageSize)"
                border
                style="width: fit-content;position: relative;margin-left: 10%"
                :header-cell-style="{'text-align':'center'}"
                :cell-style="{'text-align':'center'}">
        <el-table-column prop="id" label="停车场编号" width="120"></el-table-column>
        <el-table-column prop="image" label="停车场图片" width="120">
          <template slot-scope="scope">
            <img style="height: 100px;width: 100px" :src="link+'image/'+scope.row.id+'.jpg'">
          </template>
        </el-table-column>
        <el-table-column prop="name" label="停车场名称" width="120"></el-table-column>
        <el-table-column prop="content" label="停车场描述" width="120"></el-table-column>
        <el-table-column prop="longitude" label="经度" width="120"></el-table-column>
        <el-table-column prop="latitude" label="纬度" width="120"></el-table-column>
        <el-table-column prop="state" label="状态" width="120"></el-table-column>
        <el-table-column prop="infosubmitdate" label="提交时间" width="160">
          <template slot-scope="scope">{{ scope.row.infosubmitdate | formatDate}}</template>
        </el-table-column>
        <el-table-column label="操作" width="120">
          <template slot-scope="scope">
            <el-button type="text" size="big" @click="verifiedInfo(scope.row.id)">启用</el-button>
          </template>
        </el-table-column>
      </el-table>
      <br>
      <el-pagination
        style="margin-left: 40%"
        background
        @size-change="handleSizeChange"
        @current-change="handleCurrentChange"
        :current-page="currentPage"
        :page-size="pageSize"
        layout="total, prev, pager, next"
        :total="information.length">
      </el-pagination>
      <router-view/>
    </div>
</template>

<script>
    import {formatDate} from '../utils/filters/formatDate.js'
    import Global from "../utils/Global";
    export default {
        name: "listDisablePark",
        data(){
          return{
            link: Global.pre_url,
            information:[],
            currentPage:1,  //初始页
            pageSize:5,    //每页的数据
          }
        },

        filters: {
            formatDate(time) {
              let date = new Date(time);
              return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
            }
          },

        methods:{
          //初始页currentPage,初始每页数据pageSize
          handleSizeChange: function (size) {
            this.pagesize = size;
            console.log(this.pagesize); //每页下拉显示数据
          },

          handleCurrentChange: function (currentPage) {
            this.currentPage = currentPage;
            console.log(this.currentPage); //点击第几页
            console.log(this.information.length)
          },

          verifiedInfo:function (id) {
            // 获取本地token, 判断是否登录
            let localStorageToken = localStorage.getItem("accessToken")
            // 如果本地token不为空则设置到请求头去
            if (localStorageToken != null) {
              this.accessToken = localStorageToken
            } else {
              alert("未登录")
              return
            }
            // let timestamp = Date.parse(new Date());
            let formData = new FormData();
            formData.append('pid',id);
            this.$http.put(Global.gateway+"infos-api/admin/infos/"+id,formData,{
              params: {state: 'verified'},
              headers: {'Authorization': 'Bearer ' + this.accessToken},
            }).then(res=>{
              console.log(res.data.status)
              if(res.data.status === 'success'){
                alert('验证成功')
                this.$router.push('/backToDisable')
              }
            })
          },

          list () {
            console.log("list的方法")
            // 获取本地token, 判断是否登录
            let localStorageToken = localStorage.getItem("accessToken")
            // 如果本地token不为空则设置到请求头去
            if (localStorageToken != null) {
              this.accessToken = localStorageToken
            } else {
              alert("未登录")
              return
            }
            let that = this
            this.$http.get(Global.gateway+"infos-api/admin/infos/disable?page=1&pagesize=100",
              {
                headers: {'Authorization': 'Bearer ' + this.accessToken}
              }).then((res) => {
              console.log(res.data)
              that.information = res.data.data.list;
            });
          }
        },

        created() {
          this.list();
        }
    }
</script>

<style scoped>

</style>
