<template>

</template>

<script>
  export default {
    name: "Index",
    data() {
      return {

      }
    },
    mounted() {
      if (window.localStorage.getItem(this.GLOBAL.language_item) == null) {
        window.localStorage.setItem(this.GLOBAL.language_item, this.GLOBAL.language)
      }
      this.$router.push({
        path: this.GLOBAL.login_path,
      })
    }
  }
</script>

<style scoped>

</style>
