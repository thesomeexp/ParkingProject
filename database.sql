-- MySQL dump 10.13  Distrib 5.7.33, for Win64 (x86_64)
--
-- Host: 192.168.153.67    Database: parking
-- ------------------------------------------------------
-- Server version	5.7.30

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `admin`
--

DROP TABLE IF EXISTS `admin`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `admin` (
  `admin_id` int(11) NOT NULL AUTO_INCREMENT,
  `admin_parent` int(11) NOT NULL COMMENT '父级账号id',
  `admin_name` char(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `admin_password` char(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `admin_phone` char(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `admin_status` int(11) NOT NULL COMMENT '账号状态: 0未激活1正常2封禁',
  PRIMARY KEY (`admin_id`),
  UNIQUE KEY `user_usr_area_code_usr_phone_uindex` (`admin_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admin`
--

LOCK TABLES `admin` WRITE;
/*!40000 ALTER TABLE `admin` DISABLE KEYS */;
INSERT INTO `admin` VALUES (1,0,'沙雕荣密码:111','$2a$10$HOz44nhGldJTiv7F0GNMR.vngmXYsWICOHcoZZ5ZT/Dj.4IrPLPa2','1',1),(2,1,'西巴蓝222','$2a$10$FEBs5eBasimqr1YQ1VWHgetWRsegTtkbJfq9y0qkRBGhY6fEzE5Je','2',1),(3,1,'西巴黄333','$2a$10$FS6Sd47/ll1qtCgoFKrWQepIOvAU1aHuxz934p/f94HC0FKxQ4go.','3',1),(4,2,'西巴蓝1密码222','$2a$10$9o7PoyxA6tL.QwAq6spoBe80/SQC/sZfB8XGWaxWtLffLeftIDr7y','21',1),(5,2,'西巴蓝2密码222','$2a$10$t86GzuEjVFPA34oDSWL.T.NKm7SZSGS33jUhvhgmsF2nngtDp.Yea','22',2),(6,2,'西巴蓝3密码222','$2a$10$SddJytHe7Fm8BgDmihbFnez3ZonHSnJd1Sz8GKqMGwwx7Xo.MPl4O','23',2),(7,5,'西巴蓝221密码222','$2a$10$ZykzdWr/Tl7V22U31Wy1nOIsYN1yKSaVAq5r3KROP2gb6EEqaZhQS','221',1),(8,1,'鬼哥密码444','$2a$10$FqCcU3eRPUEHh382taAzBOpGColENxVRwrvP9laeJhaxIL2hqvU5e','4',1),(9,1,'西巴钟密码777','$2a$10$mYTi7EXC5GcZB/o4MD7cM.5aspUDd5yz5fioh1C4QBNCd80VwLPLm','7',1),(10,1,'巴哥密码888','$2a$10$5fWBFUhFUMtpIaHiXklHeOByAD1MkJUbEs4jdOc9RQ.jwbEYW4k5u','8',1),(11,1,'最终测试而已密码67676767','$2a$10$Aacd5iUaVVsUxb5hLLjmcuguIU5FInGLq/FWOG0I.m7.r6IlwuVnW','67676767',1);
/*!40000 ALTER TABLE `admin` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `feedback`
--

DROP TABLE IF EXISTS `feedback`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL AUTO_INCREMENT,
  `feedback_pid` int(11) NOT NULL,
  `feedback_uid` int(11) NOT NULL,
  `feedback_create_date` datetime NOT NULL,
  `feedback_content` char(255) NOT NULL,
  `feedback_status` int(11) NOT NULL COMMENT '反馈状态 0未处理1已处理',
  `feedback_contact` char(30) NOT NULL COMMENT '联系方式',
  PRIMARY KEY (`feedback_id`),
  UNIQUE KEY `feedback_fbk_id_uindex` (`feedback_id`),
  KEY `feedback_fbk_uid_index` (`feedback_uid`),
  KEY `feedback_fbk_status_index` (`feedback_status`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COMMENT='反馈表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `feedback`
--

LOCK TABLES `feedback` WRITE;
/*!40000 ALTER TABLE `feedback` DISABLE KEYS */;
INSERT INTO `feedback` VALUES (1,36,8,'2021-03-27 23:16:32','我要反馈',1,'13025562311'),(2,36,8,'2021-03-27 23:51:05','我要反馈',1,'13025562311'),(3,35,100026,'2021-03-28 00:14:51','培英院能不能加张图?',1,'不告诉你'),(4,35,100026,'2021-03-28 00:15:03','培英院能不能加张图?',1,'13025562311'),(5,32,100026,'2021-03-28 00:29:35','综B的入口不好找, 怎么办',1,'1'),(6,34,100026,'2021-03-28 00:30:00','教四都没画好停车线',0,'1'),(7,35,100026,'2021-03-28 00:30:25','这是建议测试',1,'1'),(8,35,100026,'2021-03-28 00:30:32','建议2',0,'2'),(9,120074,8,'2021-04-16 19:19:00','我要反馈',0,'17450856@qq.com'),(10,120074,8,'2021-04-16 19:19:05','我要反馈2',1,'17450856@qq.com'),(11,120075,100029,'2021-04-16 23:19:27','反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈反馈',0,'32ujgd@gmail.com');
/*!40000 ALTER TABLE `feedback` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking` (
  `parking_id` int(11) NOT NULL AUTO_INCREMENT,
  `parking_uid` int(11) NOT NULL COMMENT '创建的用户id',
  `parking_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '停车场名称',
  `parking_content` varchar(4000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '停车场描述',
  `parking_longitude` decimal(10,7) NOT NULL COMMENT '经度GCJ-02坐标',
  `parking_latitude` decimal(10,7) NOT NULL COMMENT '纬度GCJ-02坐标',
  `parking_geohash` char(6) COLLATE utf8mb4_unicode_ci NOT NULL,
  `parking_status` int(11) NOT NULL COMMENT '状态:0.未验证1.已验证2.已禁用',
  `parking_update_date` datetime NOT NULL COMMENT '停车场信息更新时间',
  `parking_t0` double NOT NULL DEFAULT '5',
  `parking_t1` double NOT NULL DEFAULT '5',
  `parking_t2` double NOT NULL DEFAULT '5',
  `parking_t3` double NOT NULL DEFAULT '5',
  `parking_t4` double NOT NULL DEFAULT '5',
  `parking_t5` double NOT NULL DEFAULT '5',
  `parking_t6` double NOT NULL DEFAULT '5',
  `parking_t7` double NOT NULL DEFAULT '5',
  `parking_t8` double NOT NULL DEFAULT '5',
  `parking_t9` double NOT NULL DEFAULT '5',
  `parking_t10` double NOT NULL DEFAULT '5',
  `parking_t11` double NOT NULL DEFAULT '5',
  `parking_t12` double NOT NULL DEFAULT '5',
  `parking_t13` double NOT NULL DEFAULT '5',
  `parking_t14` double NOT NULL DEFAULT '5',
  `parking_t15` double NOT NULL DEFAULT '5',
  `parking_t16` double NOT NULL DEFAULT '5',
  `parking_t19` double NOT NULL DEFAULT '5',
  `parking_t18` double NOT NULL DEFAULT '5',
  `parking_t17` double NOT NULL DEFAULT '5',
  `parking_t20` double NOT NULL DEFAULT '5',
  `parking_t21` double NOT NULL DEFAULT '5',
  `parking_t22` double NOT NULL DEFAULT '5',
  `parking_t23` double NOT NULL DEFAULT '5',
  `parking_capacity` int(11) NOT NULL COMMENT '停车场总容量',
  `parking_free` int(11) NOT NULL DEFAULT '0' COMMENT '停车场空闲车位数量',
  PRIMARY KEY (`parking_id`),
  UNIQUE KEY `parking_prk_longitude_prk_latitude_uindex` (`parking_longitude`,`parking_latitude`),
  KEY `parking_prk_status_prk_geohash_index` (`parking_status`,`parking_geohash`),
  KEY `parking_prk_uid_index` (`parking_uid`),
  KEY `parking_prk_status_prk_name_index` (`parking_status`,`parking_name`),
  KEY `parking_prk_status_index` (`parking_status`)
) ENGINE=InnoDB AUTO_INCREMENT=120078 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking`
--

LOCK TABLES `parking` WRITE;
/*!40000 ALTER TABLE `parking` DISABLE KEYS */;
INSERT INTO `parking` VALUES (32,8,'综合B地下','这个停车场在综B地下, 由侧面可以进去. ',110.3473840,21.2696300,'w7y3p0',1,'2020-12-15 18:34:15',1.5,2,1.8,1,1.7,2,2.4,4,4.5,7,8,8.5,7,7.4,8,8.25,8.5,7.5,6.8,6.4,4.5,2.875,3,2.8,20,0),(33,8,'爱华广场门前停车场','爱华广场门前停车场, 导航至爱华广场就能看到. ',110.3571370,21.2733390,'w7y3p0',1,'2020-12-15 20:43:28',5,5,5,5,5,5,5,5,5,5,5,5,7.5,5,5,6,5,5,5,5,5,8,8,5,20,0),(34,9,'教四停车场','在教四教学楼里. ',110.3432750,21.2661150,'w7y2yz',1,'2020-12-16 15:30:44',5,5,5,5,5,5,5,5,5,4,5,8,5,5,4,7.75,3,5,5,5,5,5,5,5,20,0),(35,9,'培英苑停车场','可能培英苑里有吧, 不过是小区的停车场, 可能要收费的. ',110.3501200,21.2646410,'w7y2zp',1,'2020-12-16 15:32:56',9,9.8,9.9,9.6,9.4,9.5,9.6,9,8,3,3,2.8,5,6,6.6,4.25,7.75,7.75,5.5,6,5,5,3.5,9,20,0),(36,9,'祥和小区停车场','祥和小区小小的但还是有停车的地方的. ',110.3486720,21.2712140,'w7y3p0',1,'2020-12-16 15:33:40',7,5,5,5,5,5,5,5,5,5,5,2.5,5.75,5,5,4.625,5,2.5,5.125,5,5,4,7,5,20,0),(37,9,'湛江机场停车场','湛江机场停车场湛江机场停车场湛江机场停车场',110.3672220,21.2124600,'w7y2xm',1,'2021-03-31 00:58:43',5,5,5,5,5,5,5,5,5,5,5,3.5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(38,9,'湛江站停车场','湛江站停车场湛江站停车场湛江站停车场湛江站停车场湛江站停车场',110.3883420,21.1888040,'w7y2xc',1,'2021-03-31 00:58:57',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(39,9,'海滨公园附近停车场','海滨公园附近停车场海滨公园附近停车场海滨公园附近停车场海滨公园附近停车场',110.4160220,21.2006070,'w7y88e',0,'2021-04-07 21:32:31',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(40,9,'万达广场地下停车场','万达广场地下停车场万达广场地下停车场万达广场地下停车场',110.4067040,21.2489020,'w7y8bk',0,'2020-12-16 15:38:40',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(41,9,'万象金沙湾广场停车场','万象金沙湾广场停车场万象金沙湾广场停车场万象金沙湾广场停车场',110.3947150,21.2683850,'w7y8bp',1,'2021-04-01 10:57:46',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(42,10,'湛江市图书馆停车场','湛江市图书馆停车场湛江市图书馆停车场湛江市图书馆停车场',110.3817590,21.2543530,'w7y2zv',0,'2020-12-16 15:44:21',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(43,10,'鹰展假日广场停车场','鹰展假日广场停车场鹰展假日广场停车场鹰展假日广场停车场',110.3762990,21.2754790,'w7y3p9',0,'2020-12-16 15:44:49',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(46,10,'3km','3km',110.3714380,21.2739090,'w7y3p8',0,'2020-12-16 17:42:26',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(47,10,'3km2','3km2',110.3718460,21.2740290,'w7y3p8',2,'2020-12-16 17:43:00',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(48,9,'岭师东大门停车场','进入东大门后往前开50m, 右手边就能看到了',110.3518450,21.2739660,'w7y3p0',0,'2020-12-22 16:01:42',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120059,100019,'postman的','发电方式',110.3535520,21.2685460,'w7y2zp',0,'2021-02-21 21:39:41',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120060,8,'测试的啦','各方股东身份',110.3539140,21.2654460,'w7y2zp',0,'2021-03-01 18:15:07',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120061,8,'测试2','就是测试',110.3591930,21.2680060,'w7y2zr',0,'2021-03-24 23:59:24',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120062,8,'测试3','测试3',110.3564890,21.2652460,'w7y2zp',0,'2021-03-24 23:59:38',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120063,8,'测试4','测试测试测试',110.3547730,21.2617670,'w7y2zn',0,'2021-03-24 23:59:53',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120064,8,'测试6','测试',110.3513390,21.2612070,'w7y2zn',0,'2021-03-25 00:00:02',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120065,8,'测试7','房贷首付',110.3585920,21.2642470,'w7y2zr',0,'2021-03-25 00:00:10',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120066,100026,'湛江一中测试','分带地方',110.3528240,21.2666410,'w7y2zp',0,'2021-03-27 21:59:35',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120067,100026,'测试','测试测试测试',110.3524370,21.2660010,'w7y2zp',0,'2021-03-27 21:59:52',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120068,8,'测试','测试噢',110.3553520,21.2663060,'w7y2zp',0,'2021-03-28 00:40:52',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120069,8,'23rwer','erwrewr',110.3466190,21.2624470,'w7y2yy',0,'2021-03-30 14:37:35',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120070,8,'综B地下停车场1112','多路分解爱上了附件里是空军副司令大姐夫房间都是垃圾焚烧炉啊的撒开了房间收到两份简历上的',110.3476650,21.2696040,'w7y2zp',0,'2021-04-07 21:27:20',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6,0),(120071,8,'666','666',110.3530560,21.2646070,'w7y2zp',0,'2021-03-31 20:01:28',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,20,0),(120072,8,'测试capacity','发电方式',110.3535520,21.2685470,'w7y2zp',0,'2021-04-07 21:33:04',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6,0),(120074,8,'总体测试22222','总体测试的飞洒范德萨分的发生',110.3549360,21.2719410,'w7y3p0',2,'2021-04-16 20:56:53',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,8.392857142857142,7.261904761904762,5,5,5,5,21,0),(120075,100029,'功能测试','功能测试功能测试功能测试',110.3557600,21.2668060,'w7y2zp',2,'2021-04-16 23:20:12',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,6,8.375,100,0),(120076,8,'恒享花园地下停车场','位于恒享花园里的停车场',110.3516400,21.2654660,'w7y2zp',0,'2021-04-17 23:23:09',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,100,0),(120077,100030,'湛江市中心人民医院','没什么事不要去打扰',110.3543860,21.2501280,'w7y2zh',1,'2021-04-18 16:21:33',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,120,0);
/*!40000 ALTER TABLE `parking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `review_id` int(11) NOT NULL AUTO_INCREMENT,
  `review_pid` int(11) NOT NULL COMMENT '停车场id',
  `review_uid` int(11) NOT NULL COMMENT '用户id',
  `review_score` int(11) NOT NULL COMMENT '评分',
  `review_content` char(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `review_create_date` datetime NOT NULL,
  PRIMARY KEY (`review_id`),
  UNIQUE KEY `review_rew_uid_rew_pid_uindex` (`review_uid`,`review_pid`),
  KEY `review_rew_pid_index` (`review_pid`),
  KEY `review_rew_uid_index` (`review_uid`)
) ENGINE=InnoDB AUTO_INCREMENT=100043 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,32,9,5,'这个停车场位置很好找, 主干道上就能看到停车位的牌子.','2021-01-07 10:18:45'),(4,32,333,4,'停车场里很干净, 位置也宽敞. 就是在地下有点远.','2021-01-07 10:46:34'),(5,32,222,2,'不知道就是给一下2分, 还行吧其实','2021-01-07 11:25:18'),(6,32,444,1,'不满意的愿意是因为我是测试','2021-01-07 11:25:19'),(7,32,555,5,'我是来凑数的评论, 满意哈哈哈哈哈','2021-01-07 11:25:38'),(9,32,777,5,'老细, 第几页啦, 我写吾落去了','2021-01-07 11:26:23'),(10,32,10,5,'11111111','2021-01-07 11:49:08'),(11,34,10,5,'ffasf22','2021-01-07 11:51:33'),(100011,32,100,4,'Test Test Test','2021-02-22 12:05:02'),(100012,32,101,4,'Test Test Test','2021-02-22 12:05:02'),(100013,32,102,4,'Test Test Test','2021-02-22 12:05:02'),(100014,32,103,4,'Test Test Test','2021-02-22 12:05:02'),(100015,32,104,4,'Test Test Test','2021-02-22 12:05:02'),(100016,32,105,4,'Test Test Test','2021-02-22 12:05:02'),(100017,32,106,4,'Test Test Test','2021-02-22 12:05:02'),(100018,32,107,4,'Test Test Test','2021-02-22 12:05:02'),(100019,32,108,4,'Test Test Test','2021-02-22 12:05:02'),(100020,32,109,4,'Test Test Test','2021-02-22 12:05:02'),(100021,32,110,4,'Test Test Test','2021-02-22 12:05:02'),(100022,32,111,4,'Test Test Test','2021-02-22 12:05:03'),(100023,32,112,4,'Test Test Test','2021-02-22 12:05:03'),(100024,32,113,4,'Test Test Test','2021-02-22 12:05:03'),(100025,32,114,4,'Test Test Test','2021-02-22 12:05:03'),(100026,32,115,4,'Test Test Test','2021-02-22 12:05:03'),(100027,32,116,4,'Test Test Test','2021-02-22 12:05:03'),(100028,32,117,4,'Test Test Test','2021-02-22 12:05:03'),(100029,32,118,4,'Test Test Test','2021-02-22 12:05:03'),(100031,35,100019,5,'不错','2021-02-24 12:06:44'),(100032,32,8,5,'附近很好呀','2021-03-01 00:58:57'),(100033,36,8,5,'附近大大的好','2021-03-01 00:59:19'),(100034,35,8,5,'11','2021-03-01 22:02:01'),(100035,32,100024,5,'我是13用户, 这个停车场很好','2021-03-24 21:13:34'),(100036,34,8,4,'教四这个停车场还行','2021-03-27 15:25:44'),(100037,35,100026,5,'不错, 五星好评','2021-03-27 21:58:56'),(100038,32,100026,5,'11','2021-03-28 00:33:08'),(100039,120074,100028,5,'不错','2021-04-16 19:06:21'),(100040,120074,8,5,'不错','2021-04-16 19:13:04'),(100041,120075,100029,5,'功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试','2021-04-16 22:57:02'),(100042,35,100029,5,'功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试评论功能测试','2021-04-16 23:00:31');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temp`
--

DROP TABLE IF EXISTS `temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp` (
  `temp_id` int(11) NOT NULL AUTO_INCREMENT,
  `temp_pid` int(11) NOT NULL COMMENT '停车场id',
  `temp_uid` int(11) NOT NULL COMMENT '用户id',
  `temp_state` int(11) NOT NULL COMMENT '空闲的数量',
  `temp_useful` int(11) NOT NULL COMMENT '有价值数量',
  `temp_unuseful` int(11) NOT NULL COMMENT '无价值数量',
  `temp_update_date` datetime NOT NULL,
  `temp_content` char(100) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '停车位描述',
  `temp_status` int(11) NOT NULL DEFAULT '0' COMMENT '消息回收状态:0未回收1已回收',
  PRIMARY KEY (`temp_id`),
  UNIQUE KEY `temp_tmp_pid_tmp_update_date_tmp_id_uindex` (`temp_pid`,`temp_update_date`,`temp_id`),
  UNIQUE KEY `temp_tmp_uid_tmp_update_date_uindex` (`temp_uid`,`temp_update_date`),
  KEY `temp_tmp_uid_tmp_pid_tmp_update_date_index` (`temp_uid`,`temp_pid`,`temp_update_date`),
  KEY `temp_tmp_status_tmp_update_date_index` (`temp_status`,`temp_update_date`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场状态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temp`
--

LOCK TABLES `temp` WRITE;
/*!40000 ALTER TABLE `temp` DISABLE KEYS */;
INSERT INTO `temp` VALUES (1,32,9,1,0,0,'2020-12-31 16:07:03',NULL,1),(2,32,9,0,0,0,'2020-12-31 16:23:07',NULL,1),(3,32,9,10,0,0,'2020-12-31 16:23:13',NULL,1),(4,33,9,10,0,0,'2020-12-31 16:46:06',NULL,1),(12,32,9,10,0,0,'2020-12-31 21:43:56',NULL,1),(13,33,9,10,0,0,'2020-12-31 21:44:09',NULL,1),(14,33,9,10,0,0,'2020-12-31 22:20:52',NULL,1),(15,32,9,10,0,0,'2020-12-31 22:21:03',NULL,1),(16,34,9,10,0,1,'2021-01-04 14:31:54',NULL,1),(17,32,9,10,1,0,'2021-01-05 12:21:33',NULL,1),(18,32,9,5,1,0,'2021-01-05 14:31:48',NULL,1),(19,32,9,10,1,2,'2021-01-06 14:12:32',NULL,1),(20,32,10,10,1,2,'2021-01-06 14:17:38',NULL,1),(21,34,9,0,1,0,'2021-01-06 14:46:11',NULL,1),(22,32,9,10,1,0,'2021-01-06 15:49:11',NULL,1),(23,32,9,7,1,0,'2021-01-06 16:34:20',NULL,1),(24,34,9,1,0,0,'2021-01-06 16:34:51',NULL,1),(25,32,111,3,3,1,'2021-01-06 16:41:48',NULL,1),(26,32,333,1,44,33,'2021-01-06 16:42:07',NULL,1),(27,32,666,5,54,222,'2021-01-06 16:43:05',NULL,1),(28,32,454,10,4,5,'2021-01-06 16:43:36',NULL,1),(29,32,10,8,1,0,'2021-01-07 09:38:08',NULL,1),(30,34,10,3,0,1,'2021-01-07 09:38:54',NULL,1),(31,32,10,8,0,0,'2021-01-07 11:28:30',NULL,1),(32,34,10,10,1,0,'2021-01-07 11:51:25',NULL,1),(33,32,100019,10,0,0,'2021-01-21 22:19:20',NULL,1),(34,32,100019,0,0,0,'2021-01-21 22:20:32',NULL,1),(35,32,100019,0,0,0,'2021-02-21 22:21:18',NULL,1),(36,32,100019,0,0,0,'2021-02-21 22:52:08',NULL,1),(37,32,100019,2,1,2,'2021-02-21 23:01:13',NULL,1),(38,32,100019,1,1,1,'2021-02-21 23:03:28',NULL,1),(39,32,32,1,1,1,'2021-02-21 23:14:38',NULL,1),(40,32,32,2,2,2,'2021-02-22 01:15:10',NULL,1),(41,32,33,4,33,34,'2021-02-22 01:19:19',NULL,1),(42,32,100019,10,1,1,'2021-02-23 23:25:44',NULL,1),(43,35,8,7,1,0,'2021-02-24 17:41:00',NULL,1),(44,32,8,10,0,0,'2021-02-24 17:42:40',NULL,1),(45,32,100019,10,0,0,'2021-02-28 17:08:18',NULL,1),(46,32,100019,10,0,0,'2021-02-28 17:38:56',NULL,1),(47,32,100019,10,1,0,'2021-02-28 18:09:15',NULL,1),(48,36,100019,10,1,0,'2021-02-28 18:22:54',NULL,1),(49,36,100017,7,0,1,'2021-02-28 18:32:16',NULL,1),(50,36,100018,3,1,0,'2021-02-28 18:33:05',NULL,1),(51,32,100018,3,1,0,'2021-02-28 21:54:41',NULL,1),(52,36,100018,3,0,1,'2021-02-28 21:54:45',NULL,1),(53,32,8,2,1,0,'2021-03-01 00:44:11',NULL,1),(54,36,8,9,0,1,'2021-03-01 00:45:34',NULL,1),(55,32,8,10,1,0,'2021-03-01 12:20:55',NULL,1),(56,33,8,10,0,0,'2021-03-01 12:24:49',NULL,1),(57,32,8,9,0,0,'2021-03-01 12:51:13',NULL,1),(58,32,8,10,1,0,'2021-03-01 15:32:01',NULL,1),(59,36,8,6,1,0,'2021-03-01 15:35:55',NULL,1),(60,35,8,6,0,0,'2021-03-01 18:46:15',NULL,1),(61,32,8,2,0,0,'2021-03-01 19:14:10',NULL,1),(62,35,8,2,2,0,'2021-03-01 19:17:17',NULL,1),(63,35,9,0,1,0,'2021-03-01 19:17:42',NULL,1),(64,32,8,10,1,0,'2021-03-01 22:00:25',NULL,1),(65,36,8,9,0,0,'2021-03-01 22:03:18',NULL,1),(66,36,9,3,0,0,'2021-03-02 15:38:48',NULL,1),(67,35,8,6,0,0,'2021-03-05 22:20:33',NULL,1),(68,32,8,4,1,0,'2021-03-24 20:42:45',NULL,1),(69,32,100024,3,0,0,'2021-03-24 21:14:09',NULL,1),(70,35,8,6,0,0,'2021-03-27 12:42:53',NULL,1),(71,35,8,7,1,0,'2021-03-27 13:23:48',NULL,1),(72,32,8,8,0,0,'2021-03-27 13:29:34',NULL,1),(78,32,100026,8,0,0,'2021-03-27 15:55:19',NULL,1),(79,35,100026,3,0,0,'2021-03-27 15:55:36',NULL,1),(80,34,100026,10,0,0,'2021-03-27 15:55:46',NULL,1),(81,35,100026,3,0,1,'2021-03-27 21:58:15',NULL,1),(82,35,100026,9,1,0,'2021-03-28 00:31:42',NULL,1),(83,32,8,9,0,0,'2021-03-30 15:08:02',NULL,1),(84,32,8,0,0,0,'2021-03-30 21:04:38',NULL,1),(85,37,8,2,1,0,'2021-04-01 11:00:04',NULL,1),(86,36,8,3,0,0,'2021-04-08 08:00:17',NULL,1),(87,36,8,3,0,0,'2021-04-08 10:05:11','在负一楼有',1),(89,36,8,20,0,0,'2021-04-08 11:58:11','free测试',1),(90,36,8,1,0,0,'2021-04-08 12:12:31','free测试',1),(91,36,9,20,0,0,'2021-04-08 12:16:32','22',1),(92,36,8,2,1,0,'2021-04-08 12:40:41','存量测试',1),(93,120074,100028,1,0,0,'2021-04-16 17:39:57','总体测试',1),(94,120074,100028,1,0,0,'2021-04-16 18:39:02','总体测试',1),(95,120074,100027,10,0,0,'2021-04-16 18:40:28','总体测试',1),(96,35,100029,20,1,0,'2021-04-16 22:51:36',NULL,1),(97,120075,100029,30,1,1,'2021-04-16 22:55:21',NULL,1),(98,120075,8,5,1,0,'2021-04-16 23:01:17',NULL,1),(99,120075,100029,5,1,0,'2021-04-16 23:08:52','功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试描述功能测试',1),(100,36,8,20,0,0,'2021-04-17 19:18:14','',1),(101,35,8,3,0,0,'2021-04-17 19:24:52','在负一层有3个',1),(102,32,8,10,0,0,'2021-04-17 21:50:53','',1),(103,35,100030,1,2,0,'2021-04-18 16:23:54','',1),(104,32,100030,2,1,1,'2021-04-18 16:24:03','',1);
/*!40000 ALTER TABLE `temp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` char(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `user_password` char(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `user_area_code` int(11) NOT NULL COMMENT '用户手机区号',
  `user_phone` char(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `user_status` int(11) NOT NULL COMMENT '状态0未验证1已验证2已锁定',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `user_usr_area_code_usr_phone_uindex` (`user_area_code`,`user_phone`),
  KEY `user_user_status_index` (`user_status`)
) ENGINE=InnoDB AUTO_INCREMENT=100031 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'111','$2a$10$HOz44nhGldJTiv7F0GNMR.vngmXYsWICOHcoZZ5ZT/Dj.4IrPLPa2',86,'1',1),(9,'111','$2a$10$HOz44nhGldJTiv7F0GNMR.vngmXYsWICOHcoZZ5ZT/Dj.4IrPLPa2',86,'2',1),(10,'3','$2a$10$/APGryKVXDXj7jNpINV/DO2LYcBHYXpCmBCUrRTm4pQrE8YE/u13y',86,'3',1),(11,'4','$2a$10$FWwc0D9c3DEljj06V078rOMBaW4ki17JINm7CVX8YzPzOLxG0TnBG',86,'4',1),(12,'55','$2a$10$VVv5hsfALX42tiY07/b0LOYE4ofQqnFxganqrW0fcAEJTKKhTsG26',86,'55',1),(13,'66','$2a$10$qyYaPk1REOjK9gkqnp.sse3WZvNyLvaAejeZdH8V5MEbhkDPipItm',86,'66',1),(14,'1212','$2a$10$WzoDynNmE/1n6oKffgZ7.eK.OasRJcvVZJu7WGBnjIiDIb01XweYy',86,'1212',1),(15,'4444','$2a$10$J1dPjUb.IQ1n3JwVmEJJ4.yr9I3BaMyUfFK5pAMdE2QvIapcH/1FK',86,'4444',1),(19,'2uu','$2a$10$q8YG2LebKd0hx6dgUpsbsO/GZ4zTgcWISN7kXtWWJVmBA.gxs1xOO',66,'2',2),(100017,'1','$2a$10$P498BI7k2kcW7/e5fL679.Fw05JFGowDIQsgoUJfFXIt.WCs0jMxe',666,'1',2),(100018,'1','$2a$10$NckN1BoHkRIB95LOlgvNW.G9N4dt8G9pbgYLK6Yxfu8O1Q4yPBfam',666,'2',2),(100019,'1','$2a$10$eM0BcQ3xxaI0wrM1dkHBRuOmdOhCMtTbp10djMhpqNQnaSE15ndWK',666,'3',2),(100021,'1','$2a$10$acD/ch82gu5MIqgpxgIGSua3GKA673JB0VJ5ZmT7KzPwvD4ZgKKOa',666,'1113',2),(100022,'1','$2a$10$33lwcQEnd0UF8fLouSGzYupK4wbJS5Ytu/Mdr.EqPOgr6lFbH6udy',86,'123',1),(100023,'12','$2a$10$z2fMYqHMsIE8gsznlG01vO7mZsz8527.14bip4PuxL3RAkO.b6ysS',86,'12',1),(100024,'13','$2a$10$gOBTktAVucCPCUURi2tX8O6rkV4cvssRzXfWRS6fKyp8EGsGxT0BW',86,'13',1),(100025,'12','$2a$10$I0yM4DbAWXzRFHHa3nfkruMfqCw6xoijCKyJuTMdbEbonOJDn3qRe',1,'12',0),(100026,'10086','$2a$10$hAMRFkVZBCrMKjmkT07kp.CphzA1j77BeEPPcYlZhmxPKPjjz8lxa',86,'10086',1),(100027,'222','$2a$10$FEBs5eBasimqr1YQ1VWHgetWRsegTtkbJfq9y0qkRBGhY6fEzE5Je',86,'222',1),(100028,'2223','$2a$10$kWnRXasBHEFSd5IWZdJAROm0o/ydWJueMZ2ofDLEowsXd4zX6FSM2',86,'2223',1),(100029,'8848','$2a$10$.fBslYOtPIiZfPPbUjzL5OYFzuqulSgeBltJRjkFbGcYymQAzHTmK',86,'8848',1),(100030,'1666我是新用户','$2a$10$C.8P2.FUhNgqUxz.i4MqveeUoXq2QfPNloTTo3HWi5IylI2ftXVGC',86,'1666',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `voting`
--

DROP TABLE IF EXISTS `voting`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `voting` (
  `voting_id` int(11) NOT NULL AUTO_INCREMENT,
  `voting_uid` int(11) NOT NULL COMMENT '用户id',
  `voting_tid` int(11) NOT NULL COMMENT '状态id',
  `voting_useful` int(11) NOT NULL COMMENT '是否有用0未选择-1无帮助1有帮助',
  `voting_create_date` datetime NOT NULL,
  PRIMARY KEY (`voting_id`),
  KEY `tempUser_tu_uid_tu_tid_index` (`voting_uid`,`voting_tid`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='拥挤度投票表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `voting`
--

LOCK TABLES `voting` WRITE;
/*!40000 ALTER TABLE `voting` DISABLE KEYS */;
INSERT INTO `voting` VALUES (7,9,18,1,'2021-01-05 23:34:53'),(8,9,17,1,'2021-01-05 23:36:46'),(9,9,16,-1,'2021-01-05 23:36:54'),(11,9,19,-1,'2021-01-06 14:13:50'),(12,10,19,1,'2021-01-06 14:21:17'),(14,10,20,-1,'2021-01-06 14:25:35'),(15,9,20,1,'2021-01-06 14:25:59'),(16,9,21,1,'2021-01-06 15:09:30'),(17,9,22,1,'2021-01-06 15:49:17'),(18,9,23,1,'2021-01-06 16:34:40'),(19,10,29,1,'2021-01-07 09:38:22'),(20,10,30,-1,'2021-01-07 09:39:07'),(21,10,32,1,'2021-01-07 11:51:28'),(22,100019,41,-1,'2021-02-22 01:11:40'),(23,100019,19,-1,'2021-02-22 01:13:52'),(24,100019,42,-1,'2021-02-23 23:26:53'),(25,8,47,1,'2021-02-28 18:18:43'),(26,8,48,1,'2021-02-28 18:22:58'),(27,8,49,-1,'2021-02-28 18:32:22'),(28,8,50,1,'2021-02-28 18:33:20'),(29,8,51,1,'2021-02-28 21:55:14'),(30,8,52,-1,'2021-02-28 21:55:18'),(31,8,53,1,'2021-03-01 00:44:22'),(32,8,54,-1,'2021-03-01 00:45:43'),(33,8,55,1,'2021-03-01 12:50:38'),(34,8,58,1,'2021-03-01 15:35:45'),(35,8,59,1,'2021-03-01 15:36:05'),(36,8,62,1,'2021-03-01 19:17:23'),(37,9,62,1,'2021-03-01 19:17:33'),(38,8,63,1,'2021-03-01 19:17:56'),(39,8,64,1,'2021-03-01 22:00:33'),(40,8,68,1,'2021-03-24 20:42:59'),(41,8,71,1,'2021-03-27 13:23:55'),(42,100026,73,1,'2021-03-27 15:50:24'),(43,100026,81,-1,'2021-03-27 21:58:36'),(44,100026,82,1,'2021-03-28 00:31:53'),(45,8,85,1,'2021-04-01 11:00:09'),(46,8,92,1,'2021-04-08 12:41:10'),(47,100028,42,1,'2021-04-16 18:32:45'),(48,100028,43,1,'2021-04-16 18:35:21'),(49,100029,96,1,'2021-04-16 22:52:56'),(50,100029,97,-1,'2021-04-16 22:55:36'),(51,8,97,1,'2021-04-16 23:01:14'),(52,8,98,1,'2021-04-16 23:08:36'),(53,100029,99,1,'2021-04-16 23:18:49'),(54,100030,103,1,'2021-04-18 16:24:36'),(55,100030,104,1,'2021-04-18 16:24:41'),(56,8,103,1,'2021-04-18 16:24:54'),(57,8,104,-1,'2021-04-18 16:24:58');
/*!40000 ALTER TABLE `voting` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-04-18 16:44:20
