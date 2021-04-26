<template>
    <div>
      <img height="100px"  :src="'http://192.168.80.10/head/'+ id + '.jpg'">
      <button @click="getImage" >请求头像</button>
    </div>
</template>

<script>
    export default {
        name: "ImageCORSTest",
      data() {
        return {
          id: '3'
        }
      },
      methods: {
        getImage: function () {
          this.id = '4'
        }

      }

    }
</script>

<style scoped>

</style>
