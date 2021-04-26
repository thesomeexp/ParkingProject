<template>
  <div>
    <h1 style="text-align: center">已验证停车场列表</h1>
    <el-table :data="information.slice((currentPage-1)*pageSize,currentPage*pageSize)"
              border
              style="width: fit-content;position: relative;margin-left: 23%"
              :header-cell-style="{'text-align':'center'}"
              :cell-style="{'text-align':'center'}">
      <el-table-column prop="id" label="ID" width="120"></el-table-column>
      <el-table-column prop="image" label="停车场图片" width="120">
        <template slot-scope="scope">
          <img style="height: 100px;width: 100px" :src="link+'image/info_detail/'+scope.row.pid+'/'+scope.row.id+'.jpg'">
        </template>
      </el-table-column>
      <el-table-column prop="pid" label="停车场ID" width="120"></el-table-column>
      <el-table-column prop="state" label="状态" width="120"></el-table-column>
      <el-table-column prop="submitdate" label="提交时间" width="160">
        <template slot-scope="scope">{{ scope.row.submitdate | formatDate}}</template>
      </el-table-column>
      <el-table-column label="操作" width="120">
        <template slot-scope="scope">
          <el-button type="text" size="big" @click="disableInfo(scope.row.id)">禁用</el-button>
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
        name: "listVerifiedInfoImage",

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


        //列出所有已验证的详情
        listVerified () {
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
          this.$http.get(Global.gateway+"infoImage-api/admin/infoimage/verified??page=1&pagesize=100",
            {
              headers: {'Authorization': 'Bearer ' + this.accessToken}
            }).then((res) => {
            console.log(res.data)
            that.information = res.data.data.list;
          });
        },


        //禁用已验证的详情
        disableInfo:function (id) {
          // 获取本地token, 判断是否登录
          let localStorageToken = localStorage.getItem("accessToken")
          // 如果本地token不为空则设置到请求头去
          if (localStorageToken != null) {
            this.accessToken = localStorageToken
          } else {
            alert("未登录")
            return
          }
          let formData = new FormData();
          formData.append('id',id);
          this.$http.put(Global.gateway+"infoImage-api/admin/infoimage/"+id,formData,{
            params: {state: 'disable'},
            headers: {'Authorization': 'Bearer ' + this.accessToken}
          }).then(res=>{
            console.log(res.data.status)
            if(res.data.status === 'success'){
              alert('禁用成功')
              this.$router.push('/backToVerifiedImage')
            }
          })
        },

      },//method END


      created() {
          this.listVerified();
      }

    }
</script>

<style scoped>

</style>
