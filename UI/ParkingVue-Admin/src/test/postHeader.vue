<template>
    <div>
      phone<input v-model="phone"><br>
      password<input v-model="password">
      <button @click="LoginTest" >登录测试Axios</button>
      <button @click="axiosGetTest">get测试</button>
    </div>
</template>

<script>

    export default {
        name: "testHeader",
      data() {
        return {
          token: 'eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJhdXRoMCIsImV4cCI6MTU5MTYxNzQ3OSwiaWF0IjoxNTkxNjEzODc5fQ.SWG10S6agFn6dTDCgtcu5SWg9Csi38CvAJLpdQUJ_20',
          phone: '333',
          password: '333'
        }
      },
      methods: {
        LoginTest() {
          const req_data = {
            phone: this.phone,
            password: this.password
          };
          let str = this.$qs.stringify(req_data)

          console.log(this.phone, this.password)
          console.log(str)

          this.$http
            .post('http://localhost:9001/user-api/login',
              str)
            .then((response)=>{
              console.log(response)
              // console.log(response.data)
            })
            .catch((err)=>{
              console.log('err: ' + err)
            })
        },
        axiosGetTest(){

          this.$http
            .get('http://localhost:9001/infos-api/infos?location=110.348549,21.27037',
              {
                headers: {
                  'Authorization': 'Bearer ' + this.token
                }
              })
            .then((response)=>{
              console.log(response)
              // console.log(response.data)
            })
            .catch((err)=>{
              console.log('err: ' + err)
            })

        },
        AxiosPOSTTest() {
          const req_data = {
            phone: this.phone, password: this.password
          };
          let str = this.$qs.stringify(req_data)

          console.log(this.phone, this.password)
          console.log(str)

          this.$http
            .post('http://localhost:9001/login',
              str,
              {
                headers: {
                  'Authorization': 'Bearer ' + this.token
                }
              })
            .then((response)=>{
              console.log(response)
              // console.log(response.data)
            })
            .catch((err)=>{
              console.log('err: ' + err)
            })

        }
      }
    }
</script>

<style scoped>

</style>
