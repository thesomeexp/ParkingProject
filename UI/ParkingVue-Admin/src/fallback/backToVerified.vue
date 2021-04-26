<template>
    <div>
      <router-view/>
    </div>
</template>

<script>
    export default {
        name: "backToVerified",
        methods:{
          BackToVerified(){
            this.$router.push('/listVerifiedPark')
          }
        },

        created() {
          this.BackToVerified();
        }
    }
</script>

<style scoped>

</style>
