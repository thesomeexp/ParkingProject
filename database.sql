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
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '创建的用户id',
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '停车场名称',
  `content` varchar(4000) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '停车场描述',
  `longitude` decimal(10,7) NOT NULL COMMENT '经度GCJ-02坐标',
  `latitude` decimal(10,7) NOT NULL COMMENT '纬度GCJ-02坐标',
  `first_url` varchar(512) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '图片链接',
  `status` int(11) NOT NULL COMMENT '状态:0.未验证1.已验证2.已禁用',
  `stateUpdateDate` datetime NOT NULL COMMENT '拥挤度数据更新时间(使用timestamp会有时区问题)',
  `t0` int(11) NOT NULL DEFAULT '5',
  `t1` int(11) NOT NULL DEFAULT '5',
  `t2` int(11) NOT NULL DEFAULT '5',
  `t3` int(11) NOT NULL DEFAULT '5',
  `t4` int(11) NOT NULL DEFAULT '5',
  `t5` int(11) NOT NULL DEFAULT '5',
  `t6` int(11) NOT NULL DEFAULT '5',
  `t7` int(11) NOT NULL DEFAULT '5',
  `t8` int(11) NOT NULL DEFAULT '5',
  `t9` int(11) NOT NULL DEFAULT '5',
  `t10` int(11) NOT NULL DEFAULT '5',
  `t11` int(11) NOT NULL DEFAULT '5',
  `t12` int(11) NOT NULL DEFAULT '5',
  `t13` int(11) NOT NULL DEFAULT '5',
  `t14` int(11) NOT NULL DEFAULT '5',
  `t15` int(11) NOT NULL DEFAULT '5',
  `t16` int(11) NOT NULL DEFAULT '5',
  `t17` int(11) NOT NULL DEFAULT '5',
  `t18` int(11) NOT NULL DEFAULT '5',
  `t19` int(11) NOT NULL DEFAULT '5',
  `t20` int(11) NOT NULL DEFAULT '5',
  `t21` int(11) NOT NULL DEFAULT '5',
  `t22` int(11) NOT NULL DEFAULT '5',
  `t23` int(11) NOT NULL DEFAULT '5',
  PRIMARY KEY (`id`),
  UNIQUE KEY `parking_longitude_latitude_uindex` (`longitude`,`latitude`)
) ENGINE=InnoDB AUTO_INCREMENT=49 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `parking`
--

LOCK TABLES `parking` WRITE;
/*!40000 ALTER TABLE `parking` DISABLE KEYS */;
INSERT INTO `parking` VALUES (32,8,'综合B地下','这个停车场在综B地下, 由侧面可以进去. ',110.3473840,21.2696300,NULL,1,'2020-12-15 18:34:15',1,6,5,5,5,5,5,5,5,7,5,7,8,5,9,8,6,5,5,5,5,9,8,5),(33,8,'爱华广场门前停车场','爱华广场门前停车场爱华广场门前停车场爱华广场门前停车场爱华广场门前停车场爱华广场门前停车场爱华广场门前停车场',110.3571370,21.2733390,NULL,1,'2020-12-15 20:43:28',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,8,8,5),(34,9,'教四停车场','在教四教学楼里. ',110.3432750,21.2661150,NULL,1,'2020-12-16 15:30:44',5,5,5,5,5,5,5,5,5,4,5,8,5,5,4,5,3,5,5,5,5,5,5,5),(35,9,'培英苑停车场','可能培英苑里有吧, 我也没去看过, 瞎猜的. ',110.3501200,21.2646410,NULL,1,'2020-12-16 15:32:56',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5),(36,9,'祥和小区停车场','祥和小区小小的但还是有停车的地方的. ',110.3486720,21.2712140,NULL,1,'2020-12-16 15:33:40',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5),(37,9,'湛江机场停车场','湛江机场停车场湛江机场停车场湛江机场停车场',110.3672220,21.2124600,NULL,0,'2020-12-16 15:34:28',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5),(38,9,'湛江站停车场','湛江站停车场湛江站停车场湛江站停车场湛江站停车场湛江站停车场',110.3883420,21.1888040,NULL,0,'2020-12-16 15:35:24',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5),(39,9,'海滨公园附近停车场','海滨公园附近停车场海滨公园附近停车场海滨公园附近停车场海滨公园附近停车场',110.4160220,21.2006070,NULL,0,'2020-12-16 15:37:31',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5),(40,9,'万达广场地下停车场','万达广场地下停车场万达广场地下停车场万达广场地下停车场',110.4067040,21.2489020,NULL,0,'2020-12-16 15:38:40',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5),(41,9,'万象金沙湾广场停车场','万象金沙湾广场停车场万象金沙湾广场停车场万象金沙湾广场停车场',110.3947150,21.2683850,NULL,0,'2020-12-16 15:39:41',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5),(42,10,'湛江市图书馆停车场','湛江市图书馆停车场湛江市图书馆停车场湛江市图书馆停车场',110.3817590,21.2543530,NULL,0,'2020-12-16 15:44:21',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5),(43,10,'鹰展假日广场停车场','鹰展假日广场停车场鹰展假日广场停车场鹰展假日广场停车场',110.3762990,21.2754790,NULL,0,'2020-12-16 15:44:49',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5),(46,10,'3km','3km',110.3714380,21.2739090,NULL,0,'2020-12-16 17:42:26',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5),(47,10,'3km2','3km2',110.3718460,21.2740290,NULL,2,'2020-12-16 17:43:00',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5),(48,9,'岭师东大门停车场','进入东大门后往前开50m, 右手边就能看到了',110.3518450,21.2739660,NULL,0,'2020-12-22 16:01:42',5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5);
/*!40000 ALTER TABLE `parking` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL COMMENT '停车场id',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `score` int(11) NOT NULL COMMENT '评分',
  `content` varchar(4000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `createdDate` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `review_pid_uid_uindex` (`pid`,`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (1,32,9,5,'这个停车场位置很好找, 主干道上就能看到停车位的牌子. ','2021-01-07 10:18:45'),(3,33,9,5,'不错','2021-01-07 10:32:25'),(4,32,333,4,'停车场里很干净, 位置也宽敞. 就是在地下有点远. ','2021-01-07 10:46:34'),(5,32,222,2,'不知道就是给一下2分, 还行吧其实','2021-01-07 11:25:18'),(6,32,444,1,'不满意的愿意是因为我是测试','2021-01-07 11:25:19'),(7,32,555,5,'我是来凑数的评论, 满意哈哈哈哈哈','2021-01-07 11:25:38'),(8,32,666,3,'不知道说些啥, 加油加油加油奥里给呀嘟嘟嘟嘟','2021-01-07 11:26:03'),(9,32,777,5,'老细, 第几页啦, 我写吾落去了','2021-01-07 11:26:23'),(10,32,10,5,'11111111','2021-01-07 11:49:08'),(11,34,10,5,'ffasf22','2021-01-07 11:51:33');
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temp`
--

DROP TABLE IF EXISTS `temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `pid` int(11) NOT NULL COMMENT '停车场id',
  `uid` int(11) NOT NULL COMMENT '用户id',
  `state` int(11) NOT NULL COMMENT '提交的拥挤度',
  `useful` int(11) NOT NULL COMMENT '有价值数量',
  `unuseful` int(11) NOT NULL COMMENT '无价值数量',
  `stateUpdateDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `temp_stateUpdateDate_index` (`stateUpdateDate`)
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场状态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temp`
--

LOCK TABLES `temp` WRITE;
/*!40000 ALTER TABLE `temp` DISABLE KEYS */;
INSERT INTO `temp` VALUES (1,32,9,1,0,0,'2020-12-31 16:07:03'),(2,32,9,0,0,0,'2020-12-31 16:23:07'),(3,32,9,10,0,0,'2020-12-31 16:23:13'),(4,33,9,10,0,0,'2020-12-31 16:46:06'),(12,32,9,10,0,0,'2020-12-31 21:43:56'),(13,33,9,10,0,0,'2020-12-31 21:44:09'),(14,33,9,10,0,0,'2020-12-31 22:20:52'),(15,32,9,10,0,0,'2020-12-31 22:21:03'),(16,34,9,10,0,1,'2021-01-04 14:31:54'),(17,32,9,10,1,0,'2021-01-05 12:21:33'),(18,32,9,5,1,0,'2021-01-05 14:31:48'),(19,32,9,10,1,1,'2021-01-06 14:12:32'),(20,32,10,10,1,2,'2021-01-06 14:17:38'),(21,34,9,0,1,0,'2021-01-06 14:46:11'),(22,32,9,10,1,0,'2021-01-06 15:49:11'),(23,32,9,7,1,0,'2021-01-06 16:34:20'),(24,34,9,1,0,0,'2021-01-06 16:34:51'),(25,32,111,3,3,1,'2021-01-06 16:41:48'),(26,32,333,1,44,33,'2021-01-06 16:42:07'),(27,32,666,5,54,222,'2021-01-06 16:43:05'),(28,32,454,10,4,5,'2021-01-06 16:43:36'),(29,32,10,8,1,0,'2021-01-07 09:38:08'),(30,34,10,3,0,1,'2021-01-07 09:38:54'),(31,32,10,8,0,0,'2021-01-07 11:28:30'),(32,34,10,10,1,0,'2021-01-07 11:51:25');
/*!40000 ALTER TABLE `temp` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `tempUser`
--

DROP TABLE IF EXISTS `tempUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tempUser` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uid` int(11) NOT NULL COMMENT '用户id',
  `tid` int(11) NOT NULL COMMENT '状态id',
  `useful` int(11) NOT NULL COMMENT '是否有用0未选择-1无帮助1有帮助',
  `createdDate` datetime NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `tempUser_tid_uid_uindex` (`tid`,`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场用户状态详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `tempUser`
--

LOCK TABLES `tempUser` WRITE;
/*!40000 ALTER TABLE `tempUser` DISABLE KEYS */;
INSERT INTO `tempUser` VALUES (7,9,18,1,'2021-01-05 23:34:53'),(8,9,17,1,'2021-01-05 23:36:46'),(9,9,16,-1,'2021-01-05 23:36:54'),(11,9,19,-1,'2021-01-06 14:13:50'),(12,10,19,1,'2021-01-06 14:21:17'),(14,10,20,-1,'2021-01-06 14:25:35'),(15,9,20,1,'2021-01-06 14:25:59'),(16,9,21,1,'2021-01-06 15:09:30'),(17,9,22,1,'2021-01-06 15:49:17'),(18,9,23,1,'2021-01-06 16:34:40'),(19,10,29,1,'2021-01-07 09:38:22'),(20,10,30,-1,'2021-01-07 09:39:07'),(21,10,32,1,'2021-01-07 11:51:28');
/*!40000 ALTER TABLE `tempUser` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `phone` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `role` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '角色 user 或者 admin',
  `status` int(11) NOT NULL DEFAULT '0' COMMENT '状态0未验证1已验证2已锁定',
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_phone_uindex` (`phone`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (8,'1','$2a$10$HOz44nhGldJTiv7F0GNMR.vngmXYsWICOHcoZZ5ZT/Dj.4IrPLPa2','1','admin',1),(9,'2','$2a$10$PUSYU.4iP8UHknMy9Kn.HuAOhQUElvZVx5rwRQp/cWRBMZlphtIc.','2','user',1),(10,'3','$2a$10$/APGryKVXDXj7jNpINV/DO2LYcBHYXpCmBCUrRTm4pQrE8YE/u13y','3','user',1),(11,'4','$2a$10$FWwc0D9c3DEljj06V078rOMBaW4ki17JINm7CVX8YzPzOLxG0TnBG','4','user',1),(12,'55','$2a$10$VVv5hsfALX42tiY07/b0LOYE4ofQqnFxganqrW0fcAEJTKKhTsG26','55','user',1),(13,'66','$2a$10$qyYaPk1REOjK9gkqnp.sse3WZvNyLvaAejeZdH8V5MEbhkDPipItm','66','user',1),(14,'1212','$2a$10$WzoDynNmE/1n6oKffgZ7.eK.OasRJcvVZJu7WGBnjIiDIb01XweYy','1212','user',1),(15,'4444','$2a$10$J1dPjUb.IQ1n3JwVmEJJ4.yr9I3BaMyUfFK5pAMdE2QvIapcH/1FK','4444','user',1);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-02-18 12:27:50
