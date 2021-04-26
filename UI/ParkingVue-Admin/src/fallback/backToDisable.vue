<template>
  <div>
    <router-view/>
  </div>
</template>

<script>
    export default {
        name: "backToDisable",
        methods:{
          BackToListDisable(){
            this.$router.push('/listDisablePark')
          }
        },

        created() {
          this.BackToListDisable();
        }
    }
</script>

<style scoped>

</style>
