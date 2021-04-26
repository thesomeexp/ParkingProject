<template>
    <div>
      <h1 style="text-align: center">添加停车场状态</h1>
      <el-form :model="information" status-icon ref="information" label-width="100px" class="demo-ruleForm"
               style="width: 24%;margin-left: 36%">
        <el-form-item label="停车场ID" prop="pid">
          <el-input type="text" v-model="information.pid" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="停车场坐标" prop="location">
          <el-input type="text" v-model="information.location" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="拥挤度" prop="state">
          <el-input v-model.number="information.state"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="submit()">提交</el-button>
          <el-button @click="resetForm('information')">重置</el-button>
        </el-form-item>
      </el-form>
      <router-view/>
    </div>
</template>

<script>
    export default {
        name: "AddState",
        data(){
          return{
            information:{pid:'',location:'',state:''}
          }
        },

        methods:{
          submit(){
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
            //取得表单对象
            let req_data = {
              pid: this.information.pid,
              location: this.information.location,
              state: this.information.state,
            }
            //讲表单对象转换成formdata
            let str = this.$qs.stringify(req_data);
            console.log("转换的formData   " +  str)
            this.$http
              .post('http://localhost:9001/temp-api/'+this.information.pid,
                str,{
                  headers: {
                    'Authorization': 'Bearer ' + this.accessToken
                  }
                })
              .then((response)=>{
                console.log(response)
                // this.information = response.data
                console.log(response.data.message)
                if(response.data.message === '你只能在30分钟内提交一次状态哦'){
                  alert('你只能在30分钟内提交一次状态哦')
                }
                else if(response.data.message === '停车场状态值有误') {
                  alert('停车场状态值有误')
                }
                else if(response.data.message === '经纬度定位不合法'){
                  alert('经纬度定位不合法')
                }
                else if(response.data.message === '坐标为空'){
                  alert('坐标为空')
                }
                else if(response.data.message === '评分为空'){
                  alert('拥挤度为空')
                }
                else {
                  alert('提交成功')
                  this.$router.push('/backToAddState');
                }
              })
              .catch((err)=>{
                console.log('err: ' + err)
              })
            // this.$router.push('/');
          },

          resetForm(formName) {
            this.$refs[formName].resetFields();
          },
        }
    }
</script>

<style scoped>

</style>
