<template>
  <div>
    <table>
      <tr>
        <td>请选择头像文件</td>
        <td>
          <input id="headImage" accept="image/*" type="file" @change="getFile($event)" />
        </td>
      </tr>
      <tr>
        <td>注意:文件大小不大于2MB</td>
        <td colspan="2">
          <a href="#"  @click="add">提交</a>
        </td>
      </tr>
    </table>
  </div>
</template>

<script>
    export default {
        name: "UploadHeadDemo",
      data() {
        return {
          uri: 'http://localhost:9001/user-api/head',
          accessToken: '',
          file: null
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
          formData.append("image", this.file);
          let req_data = {
            image: this.file
          };
            console.log(this.file)
          let str = this.$qs.stringify(req_data)
          let that = this;
          // console.log(that.accessToken)
          this.$http.post(url,formData,
            {
              headers: {
                'Authorization': 'Bearer ' + that.accessToken,
              }
            }
          ).then(function(response){
            // 将文件置为空
            // that.$refs.headImage.setAttribute('val', '');
            let headImage = document.getElementById('headImage')
            headImage.value = ''
            that.file = null;
            // headImage.outerHTML = headImage.outerHTML
            console.log(response)
          });
        },

      }
    }
</script>

<style scoped>

</style>
