<template>
    <div>
        <br/>
        <el-page-header @back="goBack" content="我提交的停车位状态">
        </el-page-header>
        <br/>

        <el-table
                ref="singleTable"
                :data="temps"
                style="width: 100%;"
                :cell-style="{ padding: '0px' }"
                :show-header=false
                highlight-current-row
        >
            <el-table-column>
                <template v-slot:="scope">
                    <img :src="server_img_url + scope.row.pid + '.jpg'" height="50" v-if="scope.row.pid"/>
                </template>
            </el-table-column>
            <el-table-column>
                <template v-slot:="scope">
                    <p style="font-size: small;"> {{ scope.row.parkingName }}</p>
                    <p style="font-size: small;"> {{ formatDate2(scope.row.stateUpdateDate) }}</p>
                </template>
            </el-table-column>
            <el-table-column>
                <template v-slot:="scope">
                    <p style="font-size: small;">空闲数:
                        <el-tag> {{ scope.row.state }}</el-tag>
                    </p>
                    <p>
                        <el-tag type="success"> {{ scope.row.useful }}</el-tag>
                        /
                        <el-tag type="danger">{{ scope.row.unuseful }}</el-tag>
                    </p>
                </template>
            </el-table-column>
        </el-table>
        <div>
            <el-button class="pre_page_btn" v-show="pageNum > 1"
                       @click="listTemp(pageNum - 1, pageSize)">上一页
            </el-button>
            <!--                    <el-button>第{{ pageNum }}页</el-button>-->
            <el-button class="next_page_btn" v-show="showNextPageBtn"
                       @click="listTemp(pageNum + 1, pageSize)">下一页
            </el-button>
        </div>
    </div>
</template>

<script>
    import {formatDate} from '../utils/filters/formatDate.js'
    import * as parkingUtils from "../utils/parkingUtils";
    import * as serverConf from "../constants/serverConf";

    export default {
        name: "UserListTemp",
        data() {
            return {
                temps: [],
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
            this.listTemp(1, 5)
        },
        methods: {
            goBack() {
                parkingUtils.goBack(this)
            },
            listTemp(page, pageSize) {
                this.$http.get(serverConf.server_url + '/temp/my?' + "pageNum=" + page + "&pageSize=" + pageSize, this.requestHeaderConf)
                    .then((res) => {
                        console.log('用户提交的拥挤度信息', res)
                        let res_data = res.data;
                        // 获取信息成功
                        if (res_data.code === 0) {
                            let res_data_data = res_data.data
                            this.showNextPageBtn = res_data_data.hasNext
                            this.pageNum = res_data_data.pageNum
                            this.temps = res_data_data.list
                        } else {
                            parkingUtils.error(this, res_data.msg)
                        }
                    })

            },

            formatDate2(time) {
                let date = new Date(time);
                return formatDate(date, 'MM-dd hh:mm:ss');
            },
        }
    }
</script>

<style scoped>

</style>