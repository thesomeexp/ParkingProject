<template>
    <button @click="test()"></button>
</template>

<script>
    import {goBack} from "../utils/parkingUtils";

    export default {
        name: "TestPage",
        methods: {
            test() {
                console.log(window.history.length)
                goBack(this)
            }
        }
    }
</script>

<style scoped>

</style>