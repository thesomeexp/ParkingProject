<template>
    <div>
      <router-view/>
    </div>
</template>

<script>
    export default {
        name: "backToNoVerifiedImage",

      methods:{
        BackToNoVerifiedImage(){
          this.$router.push('/listNoVerifiedImage')
        }
      }
    }
</script>

<style scoped>

</style>
