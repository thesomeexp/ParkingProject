<template>
    <div>
      GetDataTest
      <button @click="GetDataTest" >测试获取敏感数据</button>
    </div>
</template>

<script>
    export default {
        name: "GetDataTest",
      data(){
        return {
          phone: '333',
          password: '333',
          accessToken: ''
        }
      },
      methods: {
        GetDataTest() {
          // 获取本地token, 在请求时都需要加在请求头上
          let localStorageToken = localStorage.getItem("accessToken")
          // 如果本地token不为空则设置到请求头去
          if (localStorageToken != null) {
            this.accessToken = localStorageToken
          }
          console.log('本地token: ' + this.accessToken)

          this.$http
            .get('http://localhost:9001/user-api/get',
              {
                headers: {
                  'Authorization': 'Bearer ' + this.accessToken
                }
              })
            .then((response)=>{
              console.log(response)
              // console.log(response.data)
              let obj = response.data
              if (obj.status === "fail")
                console.log("失败了")
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
