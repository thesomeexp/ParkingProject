<template>
    <div>
        <br/>
        <el-page-header @back="goBack" content="我的反馈/建议">
        </el-page-header>
        <br/>

        <el-table
                ref="singleTable"
                :data="feedbacks"
                style="width: 100%;"
                :cell-style="{ padding: '0px' }"
                :show-header=false
                highlight-current-row
        >
            <el-table-column
                    property="id">
                <template v-slot:="scope">
                    <img :src="server_img_url + scope.row.pid + '.jpg'" height="50" v-if="scope.row.pid"/>
                </template>
            </el-table-column>
            <el-table-column>
                <template v-slot:="scope">
                    <p>{{ scope.row.contact }}</p>
                    <p>{{ scope.row.content }}</p>
                    <p>{{ formatDate2(scope.row.createdDate) }}</p>
                </template>
            </el-table-column>
            <el-table-column>
                <template v-slot:="scope">
                    <span style="color: orange" v-if="scope.row.status === 0">处理中</span>
                    <span style="color: green" v-if="scope.row.status === 1">已处理</span>
                </template>
            </el-table-column>
        </el-table>
        <div>
            <el-button class="pre_page_btn" v-show="pageNum > 1"
                       @click="listFeedback(pageNum - 1, pageSize)">上一页
            </el-button>
            <!--                    <el-button>第{{ pageNum }}页</el-button>-->
            <el-button class="next_page_btn" v-show="showNextPageBtn"
                       @click="listFeedback(pageNum + 1, pageSize)">下一页
            </el-button>
        </div>
    </div>
</template>

<script>
    import * as parkingUtils from "../utils/parkingUtils";
    import * as serverConf from "../constants/serverConf";
    import {formatDate} from "../utils/filters/formatDate";

    export default {
        name: "UserListFeedback",
        data() {
            return {
                feedbacks: [],
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
            console.log('UserListParking.vue mounted()')
            if (!parkingUtils.checkLogin()) {
                parkingUtils.error(this, '未登录')
                this.$router.push('Login')
                return
            }
            this.listFeedback(1, 5)
        },
        methods: {
            goBack() {
                parkingUtils.goBack(this)
            },
            listFeedback(page, pageSize) {
                this.$http.get(serverConf.server_url + '/feedback/my?'  + "pageNum=" + page + "&pageSize=" + pageSize, this.requestHeaderConf)
                    .then((res) => {
                        console.log('用户的反馈', res)
                        let res_data = res.data;
                        // 获取信息成功
                        if (res_data.code === 0) {
                            let res_data_data = res_data.data
                            this.showNextPageBtn = res_data_data.hasNext
                            this.pageNum = res_data_data.pageNum
                            this.feedbacks = res_data_data.list
                        } else {
                            parkingUtils.error(this, res_data.msg)
                        }
                    })

            },
            formatDate2(time) {
                let date = new Date(time);
                return formatDate(date, 'yyyy-MM-dd');
            },
        }
    }
</script>

<style scoped>

</style>