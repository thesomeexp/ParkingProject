<template>
  <div>

    <template style="width: 55%;height: 55%;margin: 3% 23%">
      <div class="block">
        <el-carousel >
          <el-carousel-item class="carousel-item" v-for="item in img_list" :key="item" >
            <img class="carousel-img" :src="item" />
          </el-carousel-item>
        </el-carousel>
      </div>
    </template>
    <table class="el-table" style="width: 80%;height: 55%;margin: 3% 12.5%">
      <tr>
        <td style="text-align: center">停车场编号</td>
        <td style="text-align: center">停车场名称</td>
        <td style="text-align: center">坐标</td>
        <td style="text-align: center">提交者编号</td>
        <td style="text-align: center">状态</td>
        <td style="text-align: center">状态更新时间</td>
        <td style="text-align: center">容量</td>
        <td style="text-align: center">操作</td>
      </tr>
      <tr style="text-align: center">
        <td style="text-align: center">{{information.id}}</td>
        <td style="text-align: center"><el-input v-model="information.name" style="width: 200px" :disabled="isDisable"></el-input></td>
        <td style="text-align: center"><el-input v-model="req_location" style="width: 200px" :disabled="isDisable"></el-input></td>
        <td style="text-align: center">{{information.uid}}</td>
        <td style="text-align: center">
          <el-select style="width: 100px" v-model="information.status" :disabled="isDisable" >
            <el-option
              v-for="item in options"
              :key="item.value"
              :label="item.label"
              :value="item.value">
            </el-option>
          </el-select>
        </td>
        <td style="text-align: center">{{information.stateUpdateDate | formatDate}}</td>
        <td style="text-align: center"><el-input type="number" v-model="information.capacity" style="width: 80px" :disabled="isDisable"></el-input></td>
        <td style="text-align: center">
          <div style="width: 100%" v-show="isDisable">
            <el-button type="primary" icon="el-icon-edit" @click="changeDisable()" ></el-button>
            <el-button icon="el-icon-refresh" @click="refresh_parking()" ></el-button>
          </div>
          <div style="width: 100%" v-show="!isDisable">
            <el-button type="primary" icon="el-icon-check" @click="submitChange()" ></el-button>
            <el-button icon="el-icon-close"  @click="changeDisable()" ></el-button>
          </div>
        </td>
      </tr>
      <tr>
        <td colspan="7">
          <el-input type="textarea"  v-model="information.content" :disabled="isDisable"></el-input></td>
      </tr>
    </table>

    <div style="width: 55%;height: 55%;margin: 3% 23%">
      <canvas id="myChart"></canvas>
    </div>

    <router-view/>
  </div>

</template>

<script>
  import {formatDate} from '@/utils/filters/formatDate.js'
  import Chart from 'chart.js'

  export default {
    name: "GetParking",
    data() {
      return {
        req_id: 0,
        img_link: '',
        img_list: [],
        information: {
          name: '', infoSubmitDate: '', status: '', username: '',
          longitude: '', latitude: '', location: '', stateUpdateDate: '', t0: '', t1: '', t2: '', t3: '', t4: '', t5: '',
          t6: '', t7: '', t8: '', t9: '', t10: '', t11: '', t12: '', t13: '', t14: '', t15: '',
          t16: '', t17: '', t18: '', t19: '', t20: '', t21: '', t22: '', t23: '', capacity: 1
        },
        req_location: '',
        isDisable: true,
        options: [
          {
            value: 0,
            label: '未验证'
          },
          {
            value: 1,
            label: '已验证'
          },
          {
            value: 2,
            label: '已禁用'
          }
        ]
      }
    },

    filters: {
      formatDate(time) {
        let date = new Date(time);
        return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
      }
    },

    methods: {
      submitChange() {
        this.update_parking()
        this.changeDisable()
      },
      changeDisable() {
        this.isDisable = !this.isDisable
      },

      initData() {
        this.req_id = this.$route.query.id
        this.img_link = this.GLOBAL.photo_prefix + this.req_id + '.jpg'
        this.img_list.push(this.img_link)
        this.refresh_parking()
      },

      refresh_parking() {
        this.GLOBAL.checkLoginAndToLogin(this)
        let that = this
        this.$http.get(this.GLOBAL.gateway + "/admin/parking/" + that.req_id, {
          headers: {
            'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item),
            'Authorization': this.GLOBAL.jwt_prefix + window.localStorage.getItem(this.GLOBAL.jwt_item)
          }
        }).then(res => {
          console.log('单条停车场数据', res)
          let res_data = res.data
          if (res_data.code === 0) {
            that.information = res_data.data
            that.req_location = that.information.longitude + ',' + that.information.latitude

            let ctx = document.getElementById("myChart"); //绘制图表
            let info = this.information
            let myChart = new Chart(ctx, {
              type: "line",
              legend: {},
              data: {
                labels: ["0时", "1", "2", "3", "4", "5", "6时", "7", "8", "9", "10", "11",
                  "12时", "13", "14", "15", "16", "17", "18时", "19", "20", "21", "22", "23时"],
                datasets: [
                  {
                    label: "停车场24小时拥挤度",
                    backgroundColor: "rgba(225,10,10,0.3)",
                    borderColor: "rgba(225,103,110,1)",
                    borderWidth: 0.5,
                    pointRadius: 5,
                    pointStrokeColor: "#fff",
                    data: [info['t0'], info['t1'], info['t2'], info['t3'], info['t4'], info['t5'],
                      info['t6'], info['t7'], info['t8'], info['t9'], info['t10'], info['t11'],
                      info['t12'], info['t13'], info['t14'], info['t15'], info['t16'], info['t17'],
                      info['t18'], info['t19'], info['t20'], info['t21'], info['t22'], info['t23']
                    ], //y轴
                    fill: "false",
                  }
                ]
              },
              options: {
                scales: {
                  yAxes: [
                    {
                      display: true,
                      ticks: {
                        suggestedMin: 0,
                        suggestedMax: 10,
                        beginAtZero: true
                      }
                    }
                  ]
                }
              }
            })
          } else {
            this.GLOBAL.error(this, res_data.msg)
          }
        })
      },

      update_parking() {
        this.GLOBAL.checkLoginAndToLogin(this)
        let that = this
        this.$http
        .put(this.GLOBAL.gateway + '/admin/parking/' + that.req_id, {
          'name': that.information.name,
          'content': that.information.content,
          'location': that.req_location,
          'status': that.information.status,
          'capacity': that.information.capacity,
        }, {
          headers: {
            'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item),
            'Authorization': this.GLOBAL.jwt_prefix + window.localStorage.getItem(this.GLOBAL.jwt_item)
          }
        }).then(res => {
          console.log('更新返回', res)
          let res_data = res.data
          if (res_data.code === 0) {
            this.GLOBAL.success(this, '修改成功')
            this.refresh_parking()
          } else {
            this.GLOBAL.error(this, res_data.msg)
          }
        })
      }


    },

    created() {
      this.initData();
    },

    mounted() {

    },
    options: {
      responsive: true,
      maintainAspectRatio: false,
      yAxis: {
        min: 0,
        max: 10
      }
    },
  }

</script>
<style scoped>
  .carousel-item {
    width: 100%;
    height: 100%;
    background: white;
    display: flex;
    justify-content: center;
  }

  .carousel-img {
    max-width: 100%;
    max-height: 100%;
  }
</style>
