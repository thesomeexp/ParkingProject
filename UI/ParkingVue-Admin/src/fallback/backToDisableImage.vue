<template>
    <div>
      <router-view/>
    </div>
</template>

<script>
    export default {
        name: "backToDisableImage",

      methods:{
        BackToDisableImage(){
          this.$router.push('/listDisableImage');
        }
      },

      created() {
          this.BackToDisableImage();
      }
    }
</script>

<style scoped>

</style>
