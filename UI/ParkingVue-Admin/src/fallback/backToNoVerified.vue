<template>
    <div>
      <router-view/>
    </div>
</template>

<script>
    export default {
        name: "backToNoVerified",
        methods:{
          BackToNoVerified(){
            this.$router.push('/listNoVerifiedPark')
          }
        },

        created() {
          this.BackToNoVerified();
        }
    }
</script>

<style scoped>

</style>
