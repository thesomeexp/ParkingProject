<template>
  <div>
    <h1 style="text-align: center">基于SpringBoot + Vue—Cli的停车场信息服务平台</h1>
    <div class="progress-center-container">
      <div class="progress-small-container">
        <div class="progress-width">
          <div class="progress-text">审核进度</div>
          <div>
            <el-progress type="circle" :percentage="verified_process"></el-progress>
          </div>
        </div>
        <div class="progress-width">
          <div class="progress-text">通过率</div>
          <div>
            <el-progress type="circle" :percentage="verified_rate"></el-progress>
          </div>
        </div>

        <div class="progress-bar-title">停车场信息</div>
        <div class="progress-bar-div">
          <div class="progress-bar-div-progress">
            <el-progress :percentage="verified_sum_rate" status="success"></el-progress>
          </div>
          <div class="progress-bar-div-text">
            <span style="color: green"> {{ this.verified_num }} </span>已验证
          </div>

        </div>


        <div class="progress-bar-div">
          <div class="progress-bar-div-progress">
            <el-progress :percentage="no_verified_sum_rate" status="warning"></el-progress>
          </div>
          <div class="progress-bar-div-text">
            <span style="color: orange">{{ this.no_verified_num }}</span>未验证
          </div>

        </div>
        <div class="progress-bar-div">
          <div class="progress-bar-div-progress">
            <el-progress :percentage="disable_sum_rate" status="exception"></el-progress>
          </div>
          <div class="progress-bar-div-text">
            <span style="color: red">{{ this.disable_num }}</span>已禁用
          </div>
        </div>
      </div>

      <div class="progress-small-container">
        <div class="progress-width">
          <div class="progress-text">处理进度</div>
          <div>
            <el-progress type="circle" :percentage="feedback_verified_sum_rate"></el-progress>
          </div>
        </div>
        <div class="progress-width">
          <div class="progress-text">未处理进度</div>
          <div>
            <el-progress type="circle" status="warning" :percentage="feedback_no_verified_sum_rate"></el-progress>
          </div>
        </div>

        <div class="progress-bar-title">反馈信息处理</div>
        <div class="progress-bar-div">
          <div class="progress-bar-div-progress">
            <el-progress :percentage="feedback_verified_sum_rate" status="success"></el-progress>
          </div>
          <div class="progress-bar-div-text">
            <span style="color: green"> {{ this.feedback_verified_num }} </span>已处理
          </div>
        </div>
        <div class="progress-bar-div">
          <div class="progress-bar-div-progress">
            <el-progress :percentage="feedback_no_verified_sum_rate" status="warning"></el-progress>
          </div>
          <div class="progress-bar-div-text">
            <span style="color: orange">{{ this.feedback_no_verified_num }}</span>未处理
          </div>
        </div>
      </div>

    </div>
  </div>
</template>

<script>

  export default {
    name: "Home",
    data() {
      return {
        sum: 0, // 总数
        verified_num: 0, // 已验证数量
        no_verified_num: 0, // 未验证数量
        disable_num: 0, // 已禁用数量

        verified_process: 0, // 审核进度
        verified_rate: 0, // 通过率
        verified_sum_rate: 0, // 已验证
        no_verified_sum_rate: 0, // 未验证
        disable_sum_rate: 0, // 已禁用
        feedback_verified_num: 0,
        feedback_verified_sum_rate: 0,
        feedback_no_verified_num: 0,
        feedback_no_verified_sum_rate: 0,
        feedback_sum: 0,
      }
    },

    methods: {
      // 列出那些统计内容
      list() {
        // 判断是否登录
        this.GLOBAL.checkLoginAndToLogin(this)

        let that = this
        this.$http.get(this.GLOBAL.gateway + "/admin/home",
          {
            headers: {
              'Accept-Language': window.localStorage.getItem(this.GLOBAL.language_item),
              'Authorization': this.GLOBAL.jwt_prefix + window.localStorage.getItem(this.GLOBAL.jwt_item)
            }
          }).then((res) => {
          console.log('主页请求', res)
          let res_data = res.data
          if (res_data.code === 0) {
            let result = res.data.data;
            that.verified_num = result.parkingVerified
            that.no_verified_num = result.parkingUnverified
            that.disable_num = result.parkingDisabled
            that.sum = that.verified_num + that.no_verified_num + that.disable_num

            that.verified_process = (1 - (that.no_verified_num / that.sum) ) * 100
            that.verified_process = Number(that.verified_process.toFixed(2))
            if (isNaN(that.verified_process)) {
              that.verified_process = 0
            }
            that.verified_rate = (that.verified_num / that.sum ) * 100
            that.verified_rate = Number(that.verified_rate.toFixed(2))
            if (isNaN(that.verified_rate)) {
              that.verified_rate = 0
            }
            that.verified_sum_rate = (that.verified_num / that.sum ) * 100
            that.no_verified_sum_rate = (that.no_verified_num / that.sum) * 100
            that.disable_sum_rate = (that.disable_num / that.sum) * 100
            that.reviewList = res.data.data.list;

            that.feedback_verified_num = result.feedbackFinished
            that.feedback_no_verified_num = result.feedbackUntreated
            that.feedback_sum = that.feedback_verified_num + that.feedback_no_verified_num
            that.feedback_verified_sum_rate = (that.feedback_verified_num / that.feedback_sum) * 100
            that.feedback_verified_sum_rate = Number(that.feedback_verified_sum_rate.toFixed(2))
            that.feedback_no_verified_sum_rate = (that.feedback_no_verified_num / that.feedback_sum) * 100
            that.feedback_no_verified_sum_rate = Number(that.feedback_no_verified_sum_rate.toFixed(2))
          } else {
            this.GLOBAL.error(this, res_data.msg)
          }
        });
      },

    },

    mounted() {

    },


    created() {
      this.list();
    }
  }

</script>

<style scoped>
  .progress-text {
    width: 126px;
    text-align: center;
  }

  .progress-width {
    float: left;
    width: 126px;
    padding: 10px;
  }

  .progress-bar-div {
    margin-top: 20px;
    margin-left: 20px;
    width: 400px;

    float: left;
  }

  .progress-center-container {
    width: 100%;
    height: 500px;
    /*border: 1px dashed #000;*/

  }

  .progress-small-container {
    width: 800px;
    height: 200px;
    /*border: 1px dashed #000;*/
    margin: 0 auto;
  }

  .progress-bar-div-progress {
    width: 300px;
    height: 13px;
    float: left;
  }

  .progress-bar-div-text {
    width: 100px;
    float: left;
  }

  .progress-bar-title {
    text-align: center;
    width: 400px;
    float: left;
  }
</style>
