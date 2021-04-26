<template>
    <div>
      <h1 style="text-align: center">评论</h1>
      <el-form :model="mark" status-icon ref="mark" label-width="165px" class="demo-ruleForm" style="width: 380px">
        <el-form-item label="停车场ID" prop="pid">
          <el-input type="text" v-model="mark.pid" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="定位精确度评分">
          <el-select v-model="mark.accuracy" size="medium" placeholder="请选择评分" style="max-height: max-content">
            <el-option label="很不准" value="0"></el-option>
            <el-option label="不准确" value="1"></el-option>
            <el-option label="少许误差" value="2"></el-option>
            <el-option label="一般" value="3"></el-option>
            <el-option label="较精准" value="4"></el-option>
            <el-option label="十分精准" value="5"></el-option>
          </el-select>
        </el-form-item>

        <el-form-item label="停车场易寻度" prop="easytofind">
          <el-select v-model="mark.easytofind" size="medium" placeholder="请选择评分" style="max-height: max-content">
            <el-option label="找不到" value="0"></el-option>
            <el-option label="难寻" value="1"></el-option>
            <el-option label="较难寻" value="2"></el-option>
            <el-option label="一般" value="3"></el-option>
            <el-option label="较易寻" value="4"></el-option>
            <el-option label="十分易寻" value="5"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="新增评论" prop="content">
          <el-input type="text" v-model="mark.content" auto-complete="off"></el-input>
        </el-form-item>
        <el-button type="primary" plain @click="add" style="margin: auto">提交</el-button>
      </el-form>
      <router-view/>
    </div>
</template>

<script>
    import Global from "../utils/Global";

    export default {
        name: "AddMark",
        data(){
          return{
            accessToken: '',
            mark: {
              pid: '',
              accuracy: '',
              easytofind:'',
              content: '',
            },
          }
        },



        methods:{
            add:function () {
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
              formData.append("pid",this.mark.pid);
              formData.append("accuracy",this.mark.accuracy);
              formData.append("easytofind", this.mark.easytofind);
              formData.append("content",this.mark.content);
              //获取表单对象
              let req_data = {
                pid: this.pid,
                accuracy: this.accuracy,
                easytofind: this.easytofind,
                content: this.content
              };
              //将表单对象转换成formdata
              let str = this.$qs.stringify(req_data)
              let that = this;
              this.$http.post(Global.gateway+"review-api/review/" + this.mark.pid,
                formData,
                {
                  headers: {
                    'Authorization': 'Bearer ' + that.accessToken,
                  },
                  params: {
                    accuracy : this.accuracy,
                    easytofind : this.easytofind,
                    content : this.content
                  }
                },
              ).then(function (response) {
                console.log(response.data)
                if(response.data.status){
                  alert("添加评论成功!")
                  // location.reload();
                  that.$router.push('/home')
                }
              });
            },
        },

    }
</script>

<style scoped>
  .el-form {
    width: 30%;
    position: relative;
    left: 50%;
    transform: translate(-60%, 10%);
    text-align: center;
  }
</style>
