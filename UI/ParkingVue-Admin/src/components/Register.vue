<template>
    <div>
      <h1 style="text-align: center">Welcome to register page!</h1>
      <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="90px" style="min-width: 300px" class="demo-ruleForm">
        <el-form-item label="姓名" prop="name">
          <el-input type="text" v-model="ruleForm2.name" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="密码" prop="password">
          <el-input type="password" v-model="ruleForm2.password" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="确认密码" prop="checkPass">
          <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off"></el-input>
        </el-form-item>
        <el-form-item label="电话" prop="phone">
          <el-input v-model.number="ruleForm2.phone"></el-input>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="Register">提交</el-button>
          <el-button @click="resetForm('ruleForm2')">重置</el-button>
        </el-form-item>
      </el-form>
      </div>
</template>

<script>
    export default {
        name: "RegisterDemo",
        data(){
          let validatePass = (rule, value, callback) => {
            if (value === '') {
              callback(new Error('请输入密码'));
            } else {
              if (this.ruleForm2.checkPass !== '') {
                this.$refs.ruleForm2.validateField('checkPass');
              }
              callback();
            }
          };
          let validatePass2 = (rule, value, callback) => {
            if (value === '') {
              callback(new Error('请再次输入密码'));
            } else if (value !== this.ruleForm2.password) {
              callback(new Error('两次输入密码不一致!'));
            } else {
              callback();
            }
          };
            return{
              ruleForm2: {
                name:'',
                password: '',
                checkPass: '',
                phone: ''
              },
              rules2: {
                pass: [
                  { validator: validatePass, trigger: 'blur' }
                ],
                checkPass: [
                  { validator: validatePass2, trigger: 'blur' }
                ],
              }
            };
        },

        methods:{
          Register(){
            //取得表单对象
            let req_data = {
              name: this.ruleForm2.name,
              password: this.ruleForm2.password,
              checkPass: this.ruleForm2.checkPass,
              phone: this.ruleForm2.phone
            }
            //讲表单对象转换成formdata
            let str = this.$qs.stringify(req_data);
            console.log("表单姓名密码" + this.ruleForm2.name + this.ruleForm2.password)
            console.log("转换的formData" + str)
            this.$http
              .post(this.GLOBAL.gateway+'http://localhost:9001/user-api/register',
                str)
              .then((response)=>{
                console.log(response)
                // console.log(response.data)
              })
              .catch((err)=>{
                console.log('err: ' + err)
              })
            this.$router.push('/');
          },
          resetForm(formName) {
            this.$refs[formName].resetFields();
          }
          }
    }
</script>

<style scoped>
  .el-form{
    width: 30%;
    position: absolute;
    left: 50%;
    transform: translate(-60%,10%);
    text-align: center;
  }

</style>
