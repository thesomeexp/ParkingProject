<template>
    <div>
        <br/>
        <el-page-header @back="goBack" content="我提交的评论">
        </el-page-header>
        <br/>

        <el-table
                ref="singleTable"
                :data="reviews"
                style="width: 100%;"
                :cell-style="{ padding: '0px' }"
                :show-header=false
                highlight-current-row
        >
            <el-table-column>
                <template v-slot:="scope">
                    <p style="font-size: small;"> {{ scope.row.parkingName }}</p>
                    <img :src="server_img_url + scope.row.pid + '.jpg'" height="50" v-if="scope.row.pid"/>
                </template>
            </el-table-column>
            <el-table-column>
                <template v-slot:="scope">
                    <p>评分: {{ scope.row.score }}星</p>
                    <p> {{ scope.row.content }}</p>
                    <p style="font-size: small;"> {{ formatDate2(scope.row.createdDate) }}</p>
                </template>
            </el-table-column>
        </el-table>
        <div>
            <el-button class="pre_page_btn" v-show="pageNum > 1"
                       @click="listReview(pageNum - 1, pageSize)">上一页
            </el-button>
            <!--                    <el-button>第{{ pageNum }}页</el-button>-->
            <el-button class="next_page_btn" v-show="showNextPageBtn"
                       @click="listReview(pageNum + 1, pageSize)">下一页
            </el-button>
        </div>
    </div>
</template>

<script>
    import {formatDate} from '../utils/filters/formatDate.js'
    import * as parkingUtils from "../utils/parkingUtils";
    import * as serverConf from "../constants/serverConf";

    export default {
        name: "UserListReview",
        data() {
            return {
                reviews: [],
                server_img_url: serverConf.server_img_url,
                page: 1,
                pageSize: 5,
                pageNum: 1,
                showNextPageBtn: false,
                requestHeaderConf: {
                    headers: {
                        'Accept-Language': parkingUtils.getAcceptLanguage(),
                        'Authorization': parkingUtils.getAuthorization()
                    }
                },
            }
        },
        mounted() {
            console.log('UserListTemp.vue mounted()')
            if (!parkingUtils.checkLogin()) {
                parkingUtils.error(this, '未登录')
                this.$router.push('Login')
                return
            }
            this.listReview(1, 5)
        },
        methods: {
            goBack() {
                parkingUtils.goBack(this)
            },
            listReview(page, pageSize) {
                this.$http.get(serverConf.server_url + '/review/my?' + "pageNum=" + page + "&pageSize=" + pageSize, this.requestHeaderConf)
                    .then((res) => {
                        console.log('用户的评论', res)
                        let res_data = res.data;
                        // 获取信息成功
                        if (res_data.code === 0) {
                            let res_data_data = res_data.data
                            this.showNextPageBtn = res_data_data.hasNext
                            this.pageNum = res_data_data.pageNum
                            this.reviews = res_data_data.list
                        } else {
                            parkingUtils.error(this, res_data.msg)
                        }
                    })

            },

            formatDate2(time) {
                let date = new Date(time);
                return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
            },
        }
    }
</script>

<style scoped>

</style>