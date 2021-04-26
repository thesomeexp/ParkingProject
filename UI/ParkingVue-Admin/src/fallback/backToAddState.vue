<template>
  <div>
    <router-view/>
  </div>
</template>

<script>
    export default {
        name: "backToAddState",
        methods:{
          BackToAddState(){
            this.$router.push('/addstate')
          }
        },

        created() {
          this.BackToAddState();
        }
    }
</script>

<style scoped>

</style>
