<template>
    <div>
      <router-view/>
    </div>
</template>

<script>
    export default {
        name: "backToVerifiedImage",

      methods:{
        BackToVerifiedImage(){
          this.$router.push('/listVerifiedImage')
        }
      },

      created() {
          this.BackToVerifiedImage();
      }
    }
</script>

<style scoped>

</style>
