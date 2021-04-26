<template>
    <!--    欢迎围观我用VUE堆得屎山-->
    <div>

        <el-card class="topBar box-card" :body-style="{ padding: '10px' }" v-show="this.showTopBar">
            <el-row>
                <el-button icon="el-icon-user-solid" circle @click="toUser()"></el-button>
                <el-button icon="el-icon-place" circle @click="listParkings()"></el-button>
                <!--                <el-button icon="el-icon-place" circle @click="test()"></el-button>-->
                <el-button icon="el-icon-delete" circle @click="logout()"></el-button>
            </el-row>
        </el-card>

        <div id="container"></div>

        <!--        彩色点-->
        <el-card class="bottomBar box-card" v-show="this.colorful_marker.lng !== 0" :body-style="{ padding: '10px' }">
            坐标: [{{ this.colorful_marker.lng }}, {{ this.colorful_marker.lat }}] <br/>
            地址: {{ this.colorful_marker.address }} <br/>
            <el-button type="success" plain @click="dialogAddParking()">添加停车场</el-button>
            <el-button plain @click="cleanColourfulMarker()">取消</el-button>
        </el-card>

        <el-dialog
                title="添加停车场"
                v-model="dialogAddParkingVisible"
                width="90%" top="0">
            <table>
                <tr>
                    <td>名称:
                        <label>
                            <el-input type="text" v-model="colorful_marker.name"
                                      placeholder="停车场名称(20字内)"
                                      maxlength="100"></el-input>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>容量:
                        <label>
                            <el-input type="number" v-model="colorful_marker.capacity"
                                      placeholder="停车场容量"
                                      ></el-input>
                        </label>
                    </td>
                </tr>
                <tr>
                    <td>描述:
                        <label>
                            <el-input type="textarea" v-model="colorful_marker.content"
                                      placeholder="100字描述"
                                      maxlength="100"></el-input>
                        </label>
                    </td>
                </tr>
                <br/>
                <tr>
                    <td>上传图片:<input id="headImage" accept="image/*" type="file" @change="getFile($event)"/></td>
                </tr>
            </table>
            <template #footer>
    <span class="dialog-footer">
      <el-button type="primary" @click="addParking()">确 定</el-button>
      <el-button @click="dialogAddParkingVisible = false">取 消</el-button>
    </span>
            </template>
        </el-dialog>


        <!--        列出所有停车场列表panel-->
        <el-card class="parkingsCard box-card" :body-style="{ padding: '10px' }" v-show="this.showparkingsCard">
            <el-table
                    ref="singleTable"
                    :data="infos"
                    style="width: 100%;"
                    max-height="200"
                    :cell-style="{ padding: '0px' }"
                    :show-header=false
                    @row-click="onSelectRow"
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
                    <template v-slot:="scope">
                        <p v-if="scope.row.name">{{ scope.row.name }}<br/>(<span style="color: green">{{ scope.row.free }}</span>/{{ scope.row.capacity }})</p>
                    </template>
                </el-table-column>
                <el-table-column
                        property="distance">
                    <template v-slot:="scope">
                        <p v-if="scope.row.distance">{{ scope.row.distance }}m</p>
                        <p style="font-size: small;" v-if="scope.row.t">拥挤度:{{ scope.row.t }}</p>
                    </template>
                </el-table-column>
                <el-table-column
                        property="location"
                >
                    <template v-slot:="scope">
                        <span :id="'info' + scope.row.id" v-if="scope.row.location"><el-button
                                icon="el-icon-s-promotion" type="primary" @click="toAMapApp(scope.row.id, scope.row.location)"></el-button></span>
                        <span v-if="scope.row"><el-button icon="el-icon-more"
                                                          @click="showDetail(scope.row)"></el-button></span>
                    </template>
                </el-table-column>
            </el-table>
        </el-card>

        <el-drawer
                v-model="drawer"
                direction="btt"
                size="85%"
                :with-header="false"
        >

            <div class="my-chart">
                <canvas id="myChart"></canvas>
            </div>


            <!--    最近更新的状态-->
            <el-divider content-position="center">近10分钟拥挤度信息</el-divider>
            <div v-if="tempList.length === 0">
                暂无信息
            </div>
            <div v-if="tempList.length !== 0">
                <table class="el-table">
                    <tr v-for="item in tempList">
                        <td class="useful-info">
                            <el-tag class="status-class" @click="showTempContent(item.content)"> 空闲数: {{item.state}}</el-tag>
                            {{ item.stateUpdateDate }}
                        </td>
<!--                        <td class="useful-info" v-if="item.state < 7 && item.state > 3">-->
<!--                            <el-tag class="status-class"> 拥挤度: {{item.state}}</el-tag>-->
<!--                            {{ item.stateUpdateDate }}-->
<!--                        </td>-->
<!--                        <td class="useful-info" v-if="item.state <= 3">-->
<!--                            <el-tag class="status-class" type="success"> 拥挤度: {{item.state}}</el-tag>-->
<!--                            {{ item.stateUpdateDate }}-->
<!--                        </td>-->

                        <td v-show="item.myOpt === 0">
                            <el-button size="mini" class="useful-btn" type="success" plain
                                       @click="usefulToMe(item.pid, item.id, 1)">有用({{
                                item.useful }})
                            </el-button>
                            <el-button size="mini" class="useful-btn" type="danger" plain
                                       @click="usefulToMe(item.pid, item.id, -1)">无用({{
                                item.unuseful }})
                            </el-button>
                        </td>

                        <td v-show="item.myOpt === 1">
                            <el-button size="mini" class="useful-btn" type="success"
                                       @click="winmsg('你已经投过票了噢')">有用({{ item.useful
                                }})
                            </el-button>
                            <el-button size="mini" class="useful-btn" type="danger" plain
                                       @click="winmsg('你已经投过票了噢')">无用({{
                                item.unuseful }})
                            </el-button>
                        </td>

                        <td v-show="item.myOpt === -1">
                            <el-button size="mini" class="useful-btn" type="success" plain
                                       @click="winmsg('你已经投过票了噢')">有用({{
                                item.useful }})
                            </el-button>
                            <el-button size="mini" class="useful-btn" type="danger"
                                       @click="winmsg('你已经投过票了噢')">无用({{ item.unuseful
                                }})
                            </el-button>
                        </td>
                    </tr>
                </table>
            </div>

            <!--            操作-->
            <el-divider content-position="center">操作</el-divider>
            <div class="parkingOperation">
                <el-button type="success" @click="dialogAddTemp()">添加空闲车位</el-button>
                <el-button type="success" @click="dialogAddReview()">添加评论</el-button>
                <el-button @click="dialogAddFeedback()">反馈/建议</el-button>
            </div>
            <el-dialog
                    title="添加空闲车位"
                    v-model=dialogAddTempVisible
                    width="90%">
                <table style="margin: 0 auto;">
                    <tr>
                        <td>空闲停车位数:</td>
                        <td width="75"><el-input type="number" v-model="myState"
                                                 placeholder="空闲停车位数"
                        ></el-input></td>
                        <td>/ {{info.capacity}}</td>
                    </tr>
                </table>
                <el-input
                        type="textarea"
                        :rows="6"
                        placeholder="100字描述"
                        maxlength="100"
                        v-model="myTempContent">
                </el-input>
                <template #footer>
    <span class="dialog-footer">
      <el-button type="primary" @click="addTemp(info.id)">确 定</el-button>
      <el-button @click="dialogAddTempVisible = false">取 消</el-button>
    </span>
                </template>
            </el-dialog>
            <el-dialog
                    title="添加评论"
                    v-model="dialogAddReviewVisible"
                    width="90%">
                <el-rate v-model="myStar"></el-rate>
                <el-input
                        type="textarea"
                        :rows="6"
                        placeholder="100字评论"
                        maxlength="100"
                        v-model="myReview">
                </el-input>
                <template #footer>
    <span class="dialog-footer">
      <el-button type="primary" @click="addReview(info.id)">确 定</el-button>
      <el-button @click="dialogAddReviewVisible = false">取 消</el-button>
    </span>
                </template>
            </el-dialog>
            <el-dialog
                    title="添加反馈/建议"
                    v-model="dialogAddFeedbackVisible"
                    width="90%">
                <el-input
                        type="textarea"
                        :rows="6"
                        placeholder="255字反馈"
                        maxlength="100"
                        v-model="feedbackContent">
                </el-input>
                <el-input v-model="feedbackContact" placeholder="30字联系方式"></el-input>
<!--                <el-input-->
<!--                        type="textarea"-->
<!--                        :rows="1"-->
<!--                        placeholder="30字联系方式"-->
<!--                        maxlength="100"-->
<!--                        v-model="feedbackContact">-->
<!--                </el-input>-->
                <template #footer>
    <span class="dialog-footer">
      <el-button type="primary" @click="addFeedback(info.id)">确 定</el-button>
      <el-button @click="dialogAddFeedbackVisible = false">取 消</el-button>
    </span>
                </template>
            </el-dialog>

            <div>
                <table>
                    <tr>
                        <td>名称:</td>
                        <td>{{ info.name }}</td>
                    </tr>
                    <tr>
                        <td>停车位总数:</td>
                        <td>{{ info.capacity }}</td>
                    </tr>
                    <tr>
                        <td>详情:</td>
                        <td>{{ info.content }}</td>
                    </tr>
                </table>
            </div>

            <!--    评论-->
            <div>
                <el-divider content-position="center">评论</el-divider>
                <div v-if="reviewList.length === 0">
                    暂无评论
                </div>
                <div v-for="item in reviewList">
                    <el-divider content-position="left">
                        评分: {{ item.score }}星
                    </el-divider>
                    <div>{{ item.content }}</div>
                    <el-divider content-position="right">{{ item.createdDate }} -- {{ item.uid }}</el-divider>
                </div>
                <div>
                    <el-button class="pre_page_btn" v-show="pageNum > 1"
                               @click="listReviews(this.reviewList[0].pid, pageNum - 1, 5)">上一页
                    </el-button>
                    <!--                    <el-button>第{{ pageNum }}页</el-button>-->
                    <el-button class="next_page_btn" v-show="showNextPageBtn"
                               @click="listReviews(this.reviewList[0].pid, pageNum + 1, 5)">下一页
                    </el-button>
                </div>
            </div>

        </el-drawer>

    </div>
</template>

<script>
    import {formatDate} from '../utils/filters/formatDate.js'
    import remoteLoad from '../utils/remoteLoad.js'
    import * as parkingUtils from "../utils/parkingUtils";
    import * as serverConf from "../constants/serverConf";
    import Chart from 'chart.js'

    export default {
        name: 'Home',
        data() {
            return {
                aMap: {}, // AMap实例
                aMapUI: {},
                map: null, // 地图实例
                geolocation: null, // 定位插件
                geocoder: null, // 地理编码插件
                mapConfig: {
                    resizeEnable: true,
                    zoom: 15,
                    center: [110.348588, 21.271104]
                },
                geolocationConfig: {
                    // noIpLocate: 3, // 所有终端禁止使用IP定位
                    showButton: false,        //显示定位按钮，默认：true
                    showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
                    showCircle: false,        //定位成功后用圆圈表示定位精度范围，默认：true
                },
                requestHeaderConf: {
                    headers: {
                        'Accept-Language': parkingUtils.getAcceptLanguage(),
                        'Authorization': parkingUtils.getAuthorization()
                    }
                },
                red_icon: require('../assets/mark_red.png'),
                blue_icon: require('../assets/mark_blue.png'),
                green_icon: require('../assets/mark_green.png'),
                my_icon: require('../assets/mark_my.png'),
                colorful_marker: { //
                    name: '',
                    content: '',
                    file: null,
                    lng: 0,
                    lat: 0,
                    address: '',
                    marker: null,
                    capacity: 1
                },
                myLngLat: null, // 自身position
                infos: [], // 获取到的所有停车场信息
                markers: [], // 点信息
                server_img_url: serverConf.server_img_url,
                // panel 展示相关
                showTopBar: true, // 显示最上面的面板
                showparkingsCard: false, // 显示列出的停车场面板
                drawer: false, // 弹出停车场详情的抽屉
                data_internal: [
                    null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null,
                ], // 10分钟内拥挤度的点
                tempList: [], // 停车场的所有状态以及投票
                reviewList: [], // 停车场的评论
                pageNum: 1, // 页号
                showNextPageBtn: true,
                info: null, // 当前操作的停车场
                dialogAddTempVisible: false, // 添加拥挤度的弹窗
                myState: 5, // 空闲停车位数
                myTempContent: '', // 状态内容
                myStateMin: 0,
                myStateMax: 10,
                dialogAddReviewVisible: false, // 添加评论的弹窗
                myStar: 5,
                myReview: '',
                dialogAddParkingVisible: false, // 添加停车场的弹窗
                dialogAddFeedbackVisible: false, // 添加反馈的弹窗
                feedbackContent: '',
                feedbackContact: '',

            }
        },

        methods: {
            // 实例化地图
            initMap() {
                console.log('Home.vue initMap()')
                // 加载PositionPicker，loadUI的路径参数为模块名中 'ui/' 之后的部分
                this.aMapUI = window.AMapUI
                this.aMap = window.AMap
                console.log('aMap', this.aMap)
                this.map = new this.aMap.Map('container', this.mapConfig)
                this.map.on('click', (ev) => {
                    this.onClick(ev)
                })
                this.map.plugin('AMap.ToolBar', () => { // 异步加载工具栏
                    let toolbar = new this.aMap.ToolBar()
                    this.map.addControl(toolbar)
                })
                this.map.plugin('AMap.Geolocation', () => { // 异步加载定位工具
                    let geolocation = new this.aMap.Geolocation({
                        // noIpLocate: 3, // 所有终端禁止使用IP定位
                        showButton: false,        //显示定位按钮，默认：true
                        showMarker: true,        //定位成功后在定位到的位置显示点标记，默认：true
                        showCircle: false,        //定位成功后用圆圈表示定位精度范围，默认：true
                    })
                    this.geolocation = geolocation
                    this.map.addControl(this.geolocation)
                    this.geolocation.watchPosition() // 开启定位监控，每隔一段时间会自动调用定位成功或失败的回调函数
                    this.aMap.event.addListener(this.geolocation, 'complete', this.onLocateComplete);//返回定位信息
                    this.aMap.event.addListener(this.geolocation, 'error', this.onLocateError);      //返回定位出错信息
                })
                this.map.plugin('AMap.Scale', () => { // 异步加载比例尺
                    let scale = new this.aMap.Scale({
                        offset: new this.aMap.Pixel(10, 20) // 比例尺偏移量
                    })
                    this.map.addControl(scale)
                })
                this.map.plugin('AMap.Geocoder', () => {
                    let geocoder = new this.aMap.Geocoder({
                        radius: 1000,
                        extensions: 'all',
                        key: '8ea0ea09e10e77939c5114f53a52eac4'
                    })
                    this.geocoder = geocoder
                })
            },
            onClick(ev) {
                console.log('地图被点击', ev)
                if (this.showparkingsCard) {
                    this.showparkingsCard = !this.showparkingsCard
                    return
                }
                this.cleanColourfulMarker()
                this.colorful_marker.lng = ev.lnglat.lng
                this.colorful_marker.lat = ev.lnglat.lat
                this.geocoder.getAddress([ev.lnglat.lng, ev.lnglat.lat], (status, result) => {
                    if (status === 'complete' && result.info === 'OK') {
                        if (result.regeocode) {
                            this.colorful_marker.address = result.regeocode.formattedAddress;
                        }
                    }
                })
                let colorful_marker = new this.aMap.Marker({
                    name: '彩色点',
                    position: [ev.lnglat.lng, ev.lnglat.lat],
                    visible: true,
                    draggable: false,
                    template: '<span>1</span>',
                    icon: new this.aMap.Icon({
                        image: this.my_icon,
                        size: new this.aMap.Size(19, 31),  //图标大小
                        imageSize: new this.aMap.Size(19, 31)
                    }),
                })
                colorful_marker.on('click', (e) => {
                    console.log('点击了彩色点', e)
                    this.map.remove(this.colorful_marker.marker)
                    this.cleanColourfulMarker()
                })
                this.colorful_marker.marker = colorful_marker
                this.map.add(colorful_marker)
            },
            onLocateComplete(result) {
                console.log('定位成功', result)
                if (this.myLngLat === null) {
                    this.myLngLat = result.position
                    this.listParkings()
                } else {
                    this.myLngLat = result.position
                }
            },
            onLocateError(result) {
                console.log('定位失败', result)
                parkingUtils.error(this, '定位失败' + result.message)
            },
            onSelectRow(row, column, event) { // 面板中选择了一行就要将对应点居中显示
                // console.log('选择了一行', row)
                let index = this.infos.map(item => item.id).indexOf(row.id)
                let selectMarker = this.markers[index]
                this.map.setCenter(selectMarker.getPosition())
            },
            selectCurrRow(position) { // 点击了点, 就在面板中选中当前行
                // console.log('position', position)
                this.cleanColourfulMarker()
                let location = position.lng + ',' + position.lat
                let index = this.infos.map(item => item.location).indexOf(location)
                // console.log('选中的info', this.infos[index])
                // console.log('index', index)
                this.$refs.singleTable.setCurrentRow(this.infos[index])
                let target = '#info' + this.infos[index].id
                // let anchor = this.$el.querySelector(target)
                // let anchor = document.getElementById(target)
                // document.body.scrollTop = anchor.offsetTop
                window.location.href = target
            },

            // 按钮类
            listParkings() {
                console.log('Home.vue listParkings()')
                if (this.myLngLat === null) {
                    parkingUtils.error(this, '定位失败, 请刷新页面重试')
                    this.map.destroy()
                    this.showTopBar = false
                    return;
                } else if (!parkingUtils.checkLogin()) {
                    parkingUtils.error(this, '未登录')
                    this.map.destroy()
                    this.$router.push('Login')
                    return
                }
                this.map.remove(this.markers)
                this.map.setCenter(this.myLngLat)
                parkingUtils.warning(this, '刷新附近停车场数据中...')
                this.infos = []
                this.markers = []
                this.$http.get(serverConf.server_url + '/parking?location=' + this.myLngLat.getLng() + ',' + this.myLngLat.getLat(), this.requestHeaderConf)
                    .then((res) => {
                        console.log('获取附近停车场数据', res)
                        let res_data = res.data
                        if (res_data.code === 0) {
                            this.infos = res_data.data
                            let t = new Date().getHours()
                            for (let index in this.infos) {  // 第一次for计算距离, 初始化坐标
                                this.infos[index].distance = Math.round(this.myLngLat.distance([this.infos[index].longitude, this.infos[index].latitude]))
                                this.infos[index].location = this.infos[index].longitude + ',' + this.infos[index].latitude
                                this.infos[index].t = Math.round(this.infos[index]['t' + t] * 10) / 10
                            }
                            this.infos = this.infos.sort(this.compare)   // 对infos重排序
                            for (let index in this.infos) {
                                this.infos[index].distance = Math.round(this.myLngLat.distance([this.infos[index].longitude, this.infos[index].latitude]))
                                let this_icon = this.blue_icon // 默认icon
                                if (this.infos[index]['t' + t] >= 7)
                                    this_icon = this.red_icon
                                else if (this.infos[index]['t' + t] <= 3)
                                    this_icon = this.green_icon
                                let marker = new this.aMap.Marker({
                                    name: this.infos[index].name,
                                    position: [this.infos[index].longitude, this.infos[index].latitude],
                                    img: serverConf.server_img_url + this.infos[index].id + '.jpg',
                                    visible: true,
                                    draggable: false,
                                    template: '<span>1</span>',
                                    icon: new this.aMap.Icon({
                                        image: this_icon,
                                        size: new this.aMap.Size(19, 31),  //图标大小
                                        imageSize: new this.aMap.Size(19, 31)
                                    }),
                                })
                                marker.on('click', (e) => {
                                    console.log('点击了点', e)
                                    this.map.setCenter(marker.getPosition())
                                    this.showparkingsCard = true
                                    this.selectCurrRow(marker.getPosition()) // 面板同步选中行
                                })
                                this.markers.push(marker)
                            }
                            this.map.add(this.markers)
                        } else {
                            parkingUtils.error(this, res_data.msg)
                        }
                    })
                    .catch(this.catchErr)
            },
            compare(obj1, obj2) {
                var val1 = obj1.distance;
                var val2 = obj2.distance;
                if (val1 < val2) {
                    return -1;
                } else if (val1 > val2) {
                    return 1;
                } else {
                    return 0;
                }
            },
            showDetail(row) { // 显示详情
                this.drawer = !this.drawer
                let info = row
                this.info = info
                this.$nextTick(() => { // 先让drawer加载出来
                    let ctx = document.getElementById("myChart"); //绘制图表
                    let myChart = new Chart(ctx, {
                        type: "line",
                        legend: {},
                        data: {
                            labels: ["0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11",
                                "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23"],
                            datasets: [
                                {
                                    label: '近10分钟拥挤度',
                                    borderWidth: 1,
                                    pointRadius: 2.5,
                                    backgroundColor: "rgba(0, 255, 0, 0.75)",
                                    borderColor: "rgba(0, 255, 0, 1)",
                                    data: this.data_internal
                                },
                                {
                                    label: "停车场24小时拥挤度",
                                    backgroundColor: "rgba(225,10,10,0.3)",
                                    borderColor: "rgba(225,103,110,1)",
                                    borderWidth: 1,
                                    pointRadius: 2.5,
                                    pointStrokeColor: "#fff",
                                    data: [info['t0'], info['t1'], info['t2'], info['t3'], info['t4'], info['t5'],
                                        info['t6'], info['t7'], info['t8'], info['t9'], info['t10'], info['t11'],
                                        info['t12'], info['t13'], info['t14'], info['t15'], info['t16'], info['t17'],
                                        info['t18'], info['t19'], info['t20'], info['t21'], info['t22'], info['t23']
                                    ], //y轴
                                    fill: "false",
                                },
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
                    this.getInternal(myChart, info.id)// 10分钟内的状态
                    this.listTemp(info.id)
                    this.listReviews(info.id, 1, 5)
                })
            },
            getInternal(chart, id) {
                if (!parkingUtils.checkLogin()) {
                    parkingUtils.error(this, '未登录')
                    this.map.destroy()
                    this.$router.push('Login')
                    return
                }
                let that = this
                this.$http.get(serverConf.server_url + '/temp/interval?pid=' + id,
                    this.requestHeaderConf
                ).then(res => {
                    console.log('获取10分钟内均值', res)
                    let res_data = res.data
                    if (res_data.code === 0) {
                        for (let i = 0; i < this.data_internal.length; i++) {
                            this.data_internal[i] = null
                        }
                        if (res_data.data !== -1) {
                            let hour = new Date().getHours()
                            this.data_internal[hour] = res_data.data
                        }
                        chart.update()

                    } else {
                        that.GLOBAL.error(that, res_data.msg)
                    }
                }).catch(this.catchErr)
            },
            listTemp(id) {
                if (!parkingUtils.checkLogin()) {
                    parkingUtils.error(this, '未登录')
                    this.map.destroy()
                    this.$router.push('Login')
                    return
                }
                let that = this
                this.$http.get(serverConf.server_url + '/temp?pid=' + id,
                    this.requestHeaderConf
                ).then(res => {
                    console.log('列出10分钟内temp', res)
                    let res_data = res.data
                    if (res_data.code === 0) {
                        that.tempList = res_data.data
                        for (let i = 0; i < that.tempList.length; i++) {
                            that.tempList[i].stateUpdateDate = that.formatDate2(that.tempList[i].stateUpdateDate)
                        }
                    } else {
                        parkingUtils.error(that, res_data.msg)
                    }
                }).catch(this.catchErr)
            },
            listReviews(id, page, pageSize) {
                if (!parkingUtils.checkLogin()) {
                    parkingUtils.error(this, '未登录')
                    this.map.destroy()
                    this.$router.push('Login')
                    return
                }
                console.log('listReviews', id, page, pageSize)
                let that = this
                this.$http.get(serverConf.server_url + "/review?pid=" + id + "&pageNum=" + page + "&pageSize=" + pageSize,
                    this.requestHeaderConf
                ).then(res => {
                    console.log('评论信息', res)
                    let res_data = res.data
                    if (res_data.code === 0) {
                        that.showNextPageBtn = res_data.data.hasNext
                        that.pageNum = res_data.data.pageNum
                        that.reviewList = res_data.data.list;
                        for (let i = 0; i < that.reviewList.length; i++) {
                            that.reviewList[i].createdDate = that.formatDate(that.reviewList[i].createdDate)
                        }
                    } else {
                        parkingUtils.error(that, res_data.msg)
                    }
                }).catch(this.catchErr)
            },

            dialogAddTemp() {
                this.dialogAddTempVisible = !this.dialogAddTempVisible
            },
            addTemp(id) {
                let that = this;
                this.$http.post(serverConf.server_url + '/temp',
                    {
                        'pid': id,
                        'state': this.myState,
                        'content': this.myTempContent                 },
                    this.requestHeaderConf,
                ).then(res => {
                    console.log('提交状态', res)
                    let res_data = res.data;
                    if (res_data.code === 0) {
                        parkingUtils.success(that, '提交成功')
                    } else {
                        parkingUtils.error(that, res_data.msg)
                    }
                }).catch(this.catchErr)
                this.dialogAddTempVisible = !this.dialogAddTempVisible
            },

            dialogAddReview() {
                this.dialogAddReviewVisible = !this.dialogAddReviewVisible
            },
            addReview(id) {
                console.log('rate, content', this.myStar, this.myReview)
                let that = this
                this.$http.post(serverConf.server_url + '/review',
                    {
                        'pid': id,
                        'score': this.myStar,
                        'content': this.myReview
                    },
                    this.requestHeaderConf,
                ).then(res => {
                    console.log('添加评论', res)
                    let res_data = res.data;
                    if (res_data.code === 0) {
                        parkingUtils.success(that, '提交成功')
                        this.myStar = 5
                        this.myReview = ''
                    } else {
                        parkingUtils.error(that, res_data.msg)
                    }
                }).catch(this.catchErr)
                this.dialogAddReviewVisible = !this.dialogAddReviewVisible
            },

            dialogAddFeedback() {
                this.dialogAddFeedbackVisible = !this.dialogAddFeedbackVisible
            },
            addFeedback(id) {
              console.log('feedback', id)
                this.$http.post(serverConf.server_url + '/feedback',
                    {
                        'pid': id,
                        'content': this.feedbackContent,
                        'contact': this.feedbackContact
                    },
                    this.requestHeaderConf,
                ).then(res => {
                    console.log('添加反馈', res)
                    let res_data = res.data;
                    if (res_data.code === 0) {
                        parkingUtils.success(this, res_data.msg)
                        this.feedbackContent = ''
                        this.feedbackContact = ''
                    } else {
                        parkingUtils.error(this, res_data.msg)
                    }
                }).catch(this.catchErr)
                this.dialogAddFeedbackVisible = !this.dialogAddFeedbackVisible
            },

            getFile: function (event) { //选中本地文件就会保存到Vue.file
                this.colorful_marker.file = event.target.files[0];
            },
            dialogAddParking() {
                this.dialogAddParkingVisible = !this.dialogAddParkingVisible
            },
            addParking() {
                console.log('addParking')
                // 是否选择了图片
                if (this.colorful_marker.file === null) {
                    parkingUtils.error(this, '请选择一张图片')
                    return
                }
                let formData = new FormData();
                formData.append("name", this.colorful_marker.name);
                formData.append("location", this.colorful_marker.lng + ',' + this.colorful_marker.lat);
                formData.append("image", this.colorful_marker.file);
                formData.append("content", this.colorful_marker.content);
                formData.append("capacity", this.colorful_marker.capacity);
                let that = this;
                this.$http.post(serverConf.server_url + '/parking', formData,
                    this.requestHeaderConf,
                ).then((res) => {
                    console.log('添加停车场', res)
                    let res_data = res.data;
                    if (res_data.code === 0) {
                        // 重置文件
                        let headImage = document.getElementById('headImage')
                        headImage.value = ''
                        that.file = null;
                        this.colorful_marker.name = ''
                        this.colorful_marker.content = ''
                        this.colorful_marker.capacity=1
                        parkingUtils.success(this, '申请成功')
                        this.dialogAddParkingVisible = !this.dialogAddParkingVisible
                    } else {
                        parkingUtils.error(this, res_data.msg)
                    }
                }).catch(this.catchErr)
            },

            winmsg(msg) {
                parkingUtils.warning(this, msg)
            },

            showTempContent(content) {
                alert(content)
            },

            usefulToMe(pid, tid, opt) {
                console.log('usefulToMe(' + tid + "," + opt + ")")
                let that = this
                this.$http.post(serverConf.server_url + '/voting',
                    {
                        "tid": tid,
                        "useful": opt
                    },
                    this.requestHeaderConf,
                ).then(res => {
                        console.log(res)
                        let res_data = res.data;
                        if (res_data.code === 0) {
                            parkingUtils.success(this, '投票成功')
                            that.listTemp(pid)
                        } else {
                            parkingUtils.error(that, res_data.msg)
                        }
                    }
                )
            },

            cleanColourfulMarker() {
                this.colorful_marker.lng = 0
                this.colorful_marker.lat = 0
                this.colorful_marker.address = ''
                if (this.colorful_marker.marker !== null) {
                    this.map.remove(this.colorful_marker.marker)
                }
            },

            logout() {
                parkingUtils.setAuthorization(null)
                this.map.destroy()
                parkingUtils.success(this, '退出成功')
                this.$router.replace('Login')
            },

            // 跳转类
            toAMapApp(id, dest) {
                parkingUtils.warning(this, '跳转到高德导航')
                console.log(this.myLngLat)
                console.log(id, dest)
                let that = this
                this.$http.post(serverConf.server_url + '/parking/reduceFree/' + id,
                    {},
                    this.requestHeaderConf,
                ).then(res => {
                    console.log('消费停车位', res)
                    let res_data = res.data;
                    if (res_data.code === 0) {
                        console.log('减少成功')
                    } else {
                        parkingUtils.error(that, res_data.msg)
                    }
                }).catch(this.catchErr)
                window.open('http://uri.amap.com/navigation?from=' + this.myLngLat.lng + ',' + this.myLngLat.lat
                    + '&to=' + dest + '&mode=car&src=nyx_super')
            },
            toUser() { // 用户详情页
                this.$router.push('/user')
            },

            // 公用回调函数
            catchErr(err) {
                parkingUtils.error(this, '连接服务器异常')
                console.log('err: ' + err)
            },

            // 工具类
            formatDate2(time) {
                let date = new Date(time);
                return formatDate(date, 'MM-dd hh:mm:ss');
            },
            formatDate(time) {
                let date = new Date(time);
                return formatDate(date, 'yyyy-MM-dd hh:mm:ss');
            },

            // 测试类
            test() {
                // this.map.setCenter([this.myLng, this.myLat])
                // this.map.destroy()
                // this.$router.push('TestPage')
                this.drawer = !this.drawer
                // 距离第一个maker的距离
                // let distance = this.markers[0].getPosition().distance([this.myLng, this.myLat])
                // let distance = this.myLngLat.distance([this.markers[0].getPosition().getLng(), this.markers[0].getPosition().getLat()])
                // console.log(distance)

                // this.showparkingsCard = true
            },


        },

        async created() {
            console.log('Home.vue created()')
            // // 已载入高德地图API，则直接初始化地图 FIXME 从其他组件使用路由回来时地图页面空白
            // if (window.AMap && window.AMapUI) {
            //     console.log('window存在')
            //     this.initMap()
            //     // 未载入高德地图API，则先载入API再初始化
            // } else {
            //     console.log('window不存在')
            await remoteLoad(`http://webapi.amap.com/maps?v=1.3&key=8ea0ea09e10e77939c5114f53a52eac4&plugin=AMap.Map,AMap.ToolBar,AMap.Geolocation,AMap.Geocoder,AMap.Scale`)
            await remoteLoad('http://webapi.amap.com/ui/1.0/main.js')
            this.initMap()
        }
        // }
    }
</script>

<style>

    #container {
        height: 100%;
        width: 100%;
        position: absolute;
        top: 0px;
        bottom: 0px;
        left: 0px;
        right: 0px;
    }

    .topBar {
        z-index: 1;
        position: absolute;
        top: 0px;
        width: 98%;
        left: 1%;
        right: 1%;
    }

    .parkingsCard {
        z-index: 999;
        position: absolute;
        bottom: 0px;
        width: 98%;
        left: 1%;
        right: 1%;
    }

    .useful-info {
        width: 50%;
    }

    .useful-btn {
        width: 40%;
        margin-left: 0px;
        padding: 0px;
    }

    .status-class {
        font-size: 15px;
    }

    .el-drawer__body {
        flex: 1;
        overflow: auto;
    }

    .my-chart {
        width: 100%;
    }

    .pre_page_btn {
        float: left
    }

    .next_page_btn {
        float: right
    }

    .parkingOperation {
        float: left;
        width: 100%;
    }

    .bottomBar {
        z-index: 10000;
        position: absolute;
        bottom: 0px;
        width: 98%;
        left: 1%;
        right: 1%;
        text-align: left;
    }

</style>