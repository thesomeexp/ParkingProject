<template>
  <div>
      <h1 style="text-align: center">添加停车场</h1>
      <el-form :model="parking" status-icon ref="parking" label-width="130px" class="demo-ruleForm">
        <el-form-item label="停车场名称" prop="parking.name">
          <el-input type="text" v-model="parking.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="停车场描述" prop="parking.content">
          <el-input type="text" v-model="parking.content" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="停车场坐标" prop="parking.location">
          <el-input type="text" v-model="parking.location" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="停车场图片">
          <input id="headImage" accept="image/*" type="file" @change="getFile($event)"/>
        </el-form-item>
        <el-button type="primary" plain @click="add">提交</el-button>
      </el-form>
      <router-view/>
  </div>
</template>

<script>
  export default {
    name: "AddParking",
    data() {
      return {
        uri: 'http://localhost:9001/infos-api/infos',
        accessToken: '',
        file: null,
        parking: {
          name: '',
          content: '',
          location: '',
        },
      }
    },
    methods: {
      getFile: function (event) { //选中本地文件就会保存到Vue.file
        this.file = event.target.files[0];
      },
      add: function () {
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
        formData.append("name",this.parking.name);
        formData.append("location",this.parking.location);
        formData.append("image", this.file);
        formData.append("content",this.parking.content);
        //获取表单对象
        let req_data = {
          name: this.name,
          location: this.location,
          image: this.file,
          content: this.content
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
          console.log(response.data)
          if(response.data.status){
            alert("添加停车位成功!")
            that.$router.push('/home')
          }
        });
      },
    }
  }
</script>

<style scoped>
  .el-form {
    width: 30%;
    position: absolute;
    left: 50%;
    transform: translate(-60%, 10%);
    text-align: center;
  }
</style>
