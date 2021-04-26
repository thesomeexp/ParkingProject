<template>

  <div style="width: 55%;height: 55%;margin: 3% 23%">
    <canvas id="myChart"></canvas>
  </div>
</template>

<script>
  import Chart from "chart.js";

  export default {
    name: "TestChart",
    methods: {
      initData() {
        console.log('hello')

        let ctx = document.getElementById("myChart"); //绘制图表
        let myChart = new Chart(ctx, {
          type: 'line',

          data: {
            labels: ['红', '蓝', '黄', '绿', '紫', '橙'],
            datasets: [
              {
                label: '示例1',
                data: [12, 19, 3, 5, 0, 3],
                borderColor: 'blue',
                backgroundColor: 'skyBlue',
                borderWidth: 1,
                fill: false,

              },
              {
                type: 'bar',
                label: '示例2',
                data: [null, null, 133, null, null, null],
                borderColor: 'red',
                backgroundColor: 'pink',
                borderWidth: 1,
                fill: false,
              },
            ]
          }


        })
      }
    },


    mounted() {
      this.initData()
    }
  }
</script>

<style scoped>

</style>
