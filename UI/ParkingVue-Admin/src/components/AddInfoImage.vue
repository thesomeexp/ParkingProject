<template>
    <div>
      <h1 style="text-align: center">添加停车场详情</h1>
      <el-form :model="parking" status-icon ref="parking" label-width="142px" class="demo-ruleForm" style="width: 300px;margin: 3% 35% ">
        <el-form-item label="停车场ID" prop="pid">
          <el-input type="text" v-model="parking.pid" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="停车场图片">
          <input id="headImage" accept="info_detail/*" type="file" @change="getFile($event)"/>
        </el-form-item>
        <el-button type="primary" plain @click="submit" style="margin-left: 55%">提交</el-button>
      </el-form>
      <router-view/>
    </div>
</template>

<script>
    import Global from "../utils/Global";

    export default {
        name: "AddInfoImage",

      data(){
          return{
            uri: Global.gateway+'infoImage-api/infoimage',
            accessToken: '',
            file: null,
            parking:{id:''}
          }
      },


      methods:{
        getFile: function (event) { //选中本地文件就会保存到Vue.file
          this.file = event.target.files[0];
        },


        submit: function () {
          // 获取本地token, 判断是否登录
          let localStorageToken = localStorage.getItem("accessToken")
          // 如果本地token不为空则设置到请求头去
          if (localStorageToken != null) {
            this.accessToken = localStorageToken
          } else {
            alert("未登录")
            return
          }
          let url = this.uri;
          let formData = new FormData();
          formData.append("pid",this.parking.pid);
          formData.append("image", this.file);
          //获取表单对象
          let req_data = {
            id:this.id,
            image: this.file
          };
          console.log(this.file)
          //将表单对象转换成formdata
          let str = this.$qs.stringify(req_data)
          let that = this;
          this.$http.post(url, formData,
            {
              headers: {
                'Authorization': 'Bearer ' + that.accessToken,
              }
            },
          ).then(function (response) {
            let headImage = document.getElementById('headImage')
            headImage.value = ''
            that.file = null;
            console.log(response)
            if(response.data.status === 'success'){
              alert("添加停车位成功!")
              that.$router.push('/home')
            }
            else
              alert("添加停车位失败")
          });
        },
      },


    }
</script>

<style scoped>

</style>
