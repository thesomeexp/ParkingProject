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
  `adm_id` int(11) NOT NULL AUTO_INCREMENT,
  `adm_parent` int(11) NOT NULL COMMENT '父级账号id',
  `adm_name` char(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `adm_password` char(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `adm_area_code` int(11) NOT NULL COMMENT '用户手机区号',
  `adm_phone` char(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `adm_status` int(11) NOT NULL COMMENT '状态0未验证1已验证2已锁定',
  PRIMARY KEY (`adm_id`),
  UNIQUE KEY `user_usr_area_code_usr_phone_uindex` (`adm_area_code`,`adm_phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `parking`
--

DROP TABLE IF EXISTS `parking`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `parking` (
  `prk_id` int(11) NOT NULL AUTO_INCREMENT,
  `prk_uid` int(11) NOT NULL COMMENT '创建的用户id',
  `prk_name` varchar(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '停车场名称',
  `prk_content` varchar(4000) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '停车场描述',
  `prk_longitude` decimal(10,7) NOT NULL COMMENT '经度GCJ-02坐标',
  `prk_latitude` decimal(10,7) NOT NULL COMMENT '纬度GCJ-02坐标',
  `prk_geohash` char(6) COLLATE utf8mb4_unicode_ci NOT NULL,
  `prk_status` int(11) NOT NULL COMMENT '状态:0.未验证1.已验证2.已禁用',
  `prk_update_date` datetime NOT NULL COMMENT '拥挤度数据更新时间(使用timestamp会有时区问题)',
  `prk_t0` double NOT NULL DEFAULT '5',
  `prk_t1` double NOT NULL DEFAULT '5',
  `prk_t2` double NOT NULL DEFAULT '5',
  `prk_t3` double NOT NULL DEFAULT '5',
  `prk_t4` double NOT NULL DEFAULT '5',
  `prk_t5` double NOT NULL DEFAULT '5',
  `prk_t6` double NOT NULL DEFAULT '5',
  `prk_t7` double NOT NULL DEFAULT '5',
  `prk_t8` double NOT NULL DEFAULT '5',
  `prk_t9` double NOT NULL DEFAULT '5',
  `prk_t10` double NOT NULL DEFAULT '5',
  `prk_t11` double NOT NULL DEFAULT '5',
  `prk_t12` double NOT NULL DEFAULT '5',
  `prk_t13` double NOT NULL DEFAULT '5',
  `prk_t14` double NOT NULL DEFAULT '5',
  `prk_t15` double NOT NULL DEFAULT '5',
  `prk_t16` double NOT NULL DEFAULT '5',
  `prk_t19` double NOT NULL DEFAULT '5',
  `prk_t18` double NOT NULL DEFAULT '5',
  `prk_t17` double NOT NULL DEFAULT '5',
  `prk_t20` double NOT NULL DEFAULT '5',
  `prk_t21` double NOT NULL DEFAULT '5',
  `prk_t22` double NOT NULL DEFAULT '5',
  `prk_t23` double NOT NULL DEFAULT '5',
  PRIMARY KEY (`prk_id`),
  UNIQUE KEY `parking_prk_longitude_prk_latitude_uindex` (`prk_longitude`,`prk_latitude`),
  KEY `parking_prk_status_prk_geohash_index` (`prk_status`,`prk_geohash`)
) ENGINE=InnoDB AUTO_INCREMENT=120061 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `rew_id` int(11) NOT NULL AUTO_INCREMENT,
  `rew_pid` int(11) NOT NULL COMMENT '停车场id',
  `rew_uid` int(11) NOT NULL COMMENT '用户id',
  `rew_score` int(11) NOT NULL COMMENT '评分',
  `rew_content` char(100) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '评论内容',
  `rew_create_date` datetime NOT NULL,
  PRIMARY KEY (`rew_id`),
  UNIQUE KEY `review_rew_uid_rew_pid_uindex` (`rew_uid`,`rew_pid`),
  KEY `review_rew_pid_index` (`rew_pid`)
) ENGINE=InnoDB AUTO_INCREMENT=100035 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场评论';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `temp`
--

DROP TABLE IF EXISTS `temp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temp` (
  `tmp_id` int(11) NOT NULL AUTO_INCREMENT,
  `tmp_pid` int(11) NOT NULL COMMENT '停车场id',
  `tmp_uid` int(11) NOT NULL COMMENT '用户id',
  `tmp_state` int(11) NOT NULL COMMENT '提交的拥挤度',
  `tmp_useful` int(11) NOT NULL COMMENT '有价值数量',
  `tmp_unuseful` int(11) NOT NULL COMMENT '无价值数量',
  `tmp_update_date` datetime NOT NULL,
  PRIMARY KEY (`tmp_id`),
  UNIQUE KEY `temp_tmp_pid_tmp_update_date_tmp_id_uindex` (`tmp_pid`,`tmp_update_date`,`tmp_id`),
  KEY `temp_tmp_uid_tmp_pid_tmp_update_date_index` (`tmp_uid`,`tmp_pid`,`tmp_update_date`)
) ENGINE=InnoDB AUTO_INCREMENT=66 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场状态';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `tempUser`
--

DROP TABLE IF EXISTS `tempUser`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `tempUser` (
  `tu_id` int(11) NOT NULL AUTO_INCREMENT,
  `tu_uid` int(11) NOT NULL COMMENT '用户id',
  `tu_tid` int(11) NOT NULL COMMENT '状态id',
  `tu_useful` int(11) NOT NULL COMMENT '是否有用0未选择-1无帮助1有帮助',
  `tu_create_date` datetime NOT NULL,
  PRIMARY KEY (`tu_id`),
  KEY `tempUser_tu_uid_tu_tid_index` (`tu_uid`,`tu_tid`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='停车场用户状态详情';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `usr_id` int(11) NOT NULL AUTO_INCREMENT,
  `usr_name` char(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '用户名',
  `usr_password` char(64) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `usr_area_code` int(11) NOT NULL COMMENT '用户手机区号',
  `usr_phone` char(16) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '手机号',
  `usr_status` int(11) NOT NULL COMMENT '状态0未验证1已验证2已锁定',
  PRIMARY KEY (`usr_id`),
  UNIQUE KEY `user_usr_area_code_usr_phone_uindex` (`usr_area_code`,`usr_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=100023 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='用户表';
/*!40101 SET character_set_client = @saved_cs_client */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-03-01 22:57:44
