<template>
    <div>
        <br/>
        <el-page-header @back="goBack" content="我提交的停车场">
        </el-page-header>
        <br/>

        <el-table
                ref="singleTable"
                :data="infos"
                style="width: 100%;"
                :cell-style="{ padding: '0px' }"
                :show-header=false
                highlight-current-row
        >
            <el-table-column
                    property="id">
                <template v-slot:="scope">
                    <img :src="server_img_url + scope.row.id + '.jpg'" height="50" v-if="scope.row.id"/>
                </template>
            </el-table-column>
            <el-table-column
                    property="name">
            </el-table-column>
            <el-table-column
                    property="location"
            >
                <template v-slot:="scope">
                    <span style="color: orange" :id="'info' + scope.row.id" v-if="scope.row.status === 0">验证中</span>
                    <span style="color: green" :id="'info' + scope.row.id" v-if="scope.row.status === 1">已验证</span>
                    <span style="color: red" :id="'info' + scope.row.id" v-if="scope.row.status === 2">已禁用</span>
                </template>
            </el-table-column>
        </el-table>
        <div>
            <el-button class="pre_page_btn" v-show="pageNum > 1"
                       @click="listParking(pageNum - 1, pageSize)">上一页
            </el-button>
            <!--                    <el-button>第{{ pageNum }}页</el-button>-->
            <el-button class="next_page_btn" v-show="showNextPageBtn"
                       @click="listParking(pageNum + 1, pageSize)">下一页
            </el-button>
        </div>
    </div>
</template>

<script>
    import * as parkingUtils from "../utils/parkingUtils";
    import * as serverConf from "../constants/serverConf";

    export default {
        name: "UserListParking",
        data() {
            return {
                infos: [],
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
            this.listParking(1, 5)
        },
        methods: {
            goBack() {
                parkingUtils.goBack(this)
            },
            listParking(page, pageSize) {
                this.$http.get(serverConf.server_url + '/parking/my?'  + "pageNum=" + page + "&pageSize=" + pageSize, this.requestHeaderConf)
                    .then((res) => {
                        console.log('用户的停车场信息', res)
                        let res_data = res.data;
                        // 获取信息成功
                        if (res_data.code === 0) {
                            let res_data_data = res_data.data
                            this.showNextPageBtn = res_data_data.hasNext
                            this.pageNum = res_data_data.pageNum
                            this.infos = res_data_data.list
                        } else {
                            parkingUtils.error(this, res_data.msg)
                        }
                    })

            }
        }
    }
</script>

<style scoped>

    .pre_page_btn {
        float: left
    }

    .next_page_btn {
        float: right
    }

</style>