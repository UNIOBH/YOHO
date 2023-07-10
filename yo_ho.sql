/*
 Navicat Premium Data Transfer

 Source Server         : root
 Source Server Type    : MySQL
 Source Server Version : 80029
 Source Host           : localhost:3306
 Source Schema         : yo_ho

 Target Server Type    : MySQL
 Target Server Version : 80029
 File Encoding         : 65001

 Date: 13/12/2022 16:22:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_apply
-- ----------------------------
DROP TABLE IF EXISTS `t_apply`;
CREATE TABLE `t_apply`  (
  `a_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户申请id',
  `a_p_id` int(0) NULL DEFAULT NULL COMMENT '省份id（外键）',
  `a_c_id` int(0) NULL DEFAULT NULL COMMENT '城市id（外键）',
  `a_s_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '学校名称',
  `a_state` int(0) NULL DEFAULT 2 COMMENT '申请状态（1：同意，0：拒绝，2：未处理）',
  PRIMARY KEY (`a_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_apply
-- ----------------------------
INSERT INTO `t_apply` VALUES (1, 2, 1, '12', 1);
INSERT INTO `t_apply` VALUES (2, 2, 237, '1321', 0);
INSERT INTO `t_apply` VALUES (3, 1, 236, 'fdsfasdfa', 0);

-- ----------------------------
-- Table structure for t_chat
-- ----------------------------
DROP TABLE IF EXISTS `t_chat`;
CREATE TABLE `t_chat`  (
  `h_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '聊天记录id',
  `h_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '消息内容',
  `h_type` int(0) NOT NULL COMMENT '消息类型',
  `h_send_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '发送时间',
  `h_sender_id` int(0) NULL DEFAULT NULL COMMENT '发送者id（外键）',
  `h_recipient_id` int(0) NULL DEFAULT NULL COMMENT '接收者id（外键）',
  PRIMARY KEY (`h_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 30 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_chat
-- ----------------------------
INSERT INTO `t_chat` VALUES (2, '213', 1, '2022-11-09 18:54:07', 2, 4);
INSERT INTO `t_chat` VALUES (3, 'rwerew', 1, '2022-11-09 18:54:16', 4, 2);
INSERT INTO `t_chat` VALUES (4, '123', 1, '2022-11-09 20:24:21', 2, 4);
INSERT INTO `t_chat` VALUES (5, 'ttttttt', 1, '2022-11-09 20:24:49', 2, 4);
INSERT INTO `t_chat` VALUES (6, 'ttttttt', 1, '2022-11-09 20:25:02', 2, 4);
INSERT INTO `t_chat` VALUES (7, 'ttttttt', 1, '2022-11-09 20:25:03', 2, 4);
INSERT INTO `t_chat` VALUES (8, 'ttttttt', 1, '2022-11-09 20:25:03', 2, 4);
INSERT INTO `t_chat` VALUES (9, 'ttttttt', 1, '2022-11-09 20:25:03', 2, 4);
INSERT INTO `t_chat` VALUES (10, 'ggggg', 1, '2022-11-09 20:26:48', 4, 2);
INSERT INTO `t_chat` VALUES (11, '123', 1, '2022-11-09 20:28:48', 4, 2);
INSERT INTO `t_chat` VALUES (12, '123', 1, '2022-11-09 20:32:16', 4, 2);
INSERT INTO `t_chat` VALUES (14, '1', 1, '2022-11-09 21:01:37', 2, 4);
INSERT INTO `t_chat` VALUES (15, 'tttt', 1, '2022-11-09 21:06:18', 4, 2);
INSERT INTO `t_chat` VALUES (16, 'bbbbb', 1, '2022-11-09 21:07:17', 2, 4);
INSERT INTO `t_chat` VALUES (17, 'mmmm', 1, '2022-11-09 21:08:48', 4, 2);
INSERT INTO `t_chat` VALUES (18, '你好', 1, '2022-11-09 21:09:06', 4, 2);
INSERT INTO `t_chat` VALUES (19, '吼吼吼吼吼吼吼吼吼吼吼吼吼吼吼', 1, '2022-11-09 21:19:19', 4, 2);
INSERT INTO `t_chat` VALUES (20, 'dasas', 1, '2022-11-09 21:19:43', 4, 2);
INSERT INTO `t_chat` VALUES (21, 'nihao', 1, '2022-11-10 16:25:47', 2, 4);
INSERT INTO `t_chat` VALUES (22, '1312312312', 1, '2022-11-11 17:02:16', 2, 4);
INSERT INTO `t_chat` VALUES (23, '43244', 1, '2022-11-11 17:02:36', 2, 4);
INSERT INTO `t_chat` VALUES (24, 'qweweqqweweq', 1, '2022-11-16 09:14:28', 2, 4);
INSERT INTO `t_chat` VALUES (25, '你好', 1, '2022-11-18 16:43:22', 2, 4);
INSERT INTO `t_chat` VALUES (26, '哈哈哈', 1, '2022-11-18 16:43:36', 4, 2);
INSERT INTO `t_chat` VALUES (27, '吃饭了吗', 1, '2022-12-07 14:42:35', 2, 4);
INSERT INTO `t_chat` VALUES (28, '嘎嘎嘎', 1, '2022-12-07 14:42:52', 2, 4);
INSERT INTO `t_chat` VALUES (29, 'hdfdfgsdfgsdf ', 1, '2022-12-07 15:29:47', 2, 4);

-- ----------------------------
-- Table structure for t_city
-- ----------------------------
DROP TABLE IF EXISTS `t_city`;
CREATE TABLE `t_city`  (
  `c_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '城市id',
  `p_id` int(0) NULL DEFAULT NULL COMMENT '省份id（外键）',
  `c_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '城市名称',
  PRIMARY KEY (`c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 393 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_city
-- ----------------------------
INSERT INTO `t_city` VALUES (1, 3, '石家庄市');
INSERT INTO `t_city` VALUES (2, 3, '唐山市');
INSERT INTO `t_city` VALUES (3, 3, '秦皇岛市');
INSERT INTO `t_city` VALUES (4, 3, '邯郸市');
INSERT INTO `t_city` VALUES (5, 3, '邢台市');
INSERT INTO `t_city` VALUES (6, 3, '保定市');
INSERT INTO `t_city` VALUES (7, 3, '张家口市');
INSERT INTO `t_city` VALUES (8, 3, '承德市');
INSERT INTO `t_city` VALUES (9, 3, '沧州市');
INSERT INTO `t_city` VALUES (10, 3, '廊坊市');
INSERT INTO `t_city` VALUES (11, 3, '衡水市');
INSERT INTO `t_city` VALUES (12, 4, '太原市');
INSERT INTO `t_city` VALUES (13, 4, '大同市');
INSERT INTO `t_city` VALUES (14, 4, '阳泉市');
INSERT INTO `t_city` VALUES (15, 4, '长治市');
INSERT INTO `t_city` VALUES (16, 4, '晋城市');
INSERT INTO `t_city` VALUES (17, 4, '朔州市');
INSERT INTO `t_city` VALUES (18, 4, '晋中市');
INSERT INTO `t_city` VALUES (19, 4, '运城市');
INSERT INTO `t_city` VALUES (20, 4, '忻州市');
INSERT INTO `t_city` VALUES (21, 4, '临汾市');
INSERT INTO `t_city` VALUES (22, 4, '吕梁市');
INSERT INTO `t_city` VALUES (23, 32, '台北市');
INSERT INTO `t_city` VALUES (24, 32, '高雄市');
INSERT INTO `t_city` VALUES (25, 32, '基隆市');
INSERT INTO `t_city` VALUES (26, 32, '台中市');
INSERT INTO `t_city` VALUES (27, 32, '台南市');
INSERT INTO `t_city` VALUES (28, 32, '新竹市');
INSERT INTO `t_city` VALUES (29, 32, '嘉义市');
INSERT INTO `t_city` VALUES (30, 32, '台北县');
INSERT INTO `t_city` VALUES (31, 32, '宜兰县');
INSERT INTO `t_city` VALUES (32, 32, '桃园县');
INSERT INTO `t_city` VALUES (33, 32, '新竹县');
INSERT INTO `t_city` VALUES (34, 32, '苗栗县');
INSERT INTO `t_city` VALUES (35, 32, '台中县');
INSERT INTO `t_city` VALUES (36, 32, '彰化县');
INSERT INTO `t_city` VALUES (37, 32, '南投县');
INSERT INTO `t_city` VALUES (38, 32, '云林县');
INSERT INTO `t_city` VALUES (39, 32, '嘉义县');
INSERT INTO `t_city` VALUES (40, 32, '台南县');
INSERT INTO `t_city` VALUES (41, 32, '屏东县');
INSERT INTO `t_city` VALUES (42, 32, '澎湖县');
INSERT INTO `t_city` VALUES (43, 32, '台东县');
INSERT INTO `t_city` VALUES (44, 32, '花莲县');
INSERT INTO `t_city` VALUES (45, 6, '沈阳市');
INSERT INTO `t_city` VALUES (46, 6, '大连市');
INSERT INTO `t_city` VALUES (47, 6, '鞍山市');
INSERT INTO `t_city` VALUES (48, 6, '抚顺市');
INSERT INTO `t_city` VALUES (49, 6, '本溪市');
INSERT INTO `t_city` VALUES (50, 6, '丹东市');
INSERT INTO `t_city` VALUES (51, 6, '锦州市');
INSERT INTO `t_city` VALUES (52, 6, '营口市');
INSERT INTO `t_city` VALUES (53, 6, '阜新市');
INSERT INTO `t_city` VALUES (54, 6, '辽阳市');
INSERT INTO `t_city` VALUES (55, 6, '盘锦市');
INSERT INTO `t_city` VALUES (56, 6, '铁岭市');
INSERT INTO `t_city` VALUES (57, 6, '朝阳市');
INSERT INTO `t_city` VALUES (58, 6, '葫芦岛市');
INSERT INTO `t_city` VALUES (59, 7, '长春市');
INSERT INTO `t_city` VALUES (60, 7, '吉林市');
INSERT INTO `t_city` VALUES (61, 7, '四平市');
INSERT INTO `t_city` VALUES (62, 7, '辽源市');
INSERT INTO `t_city` VALUES (63, 7, '通化市');
INSERT INTO `t_city` VALUES (64, 7, '白山市');
INSERT INTO `t_city` VALUES (65, 7, '松原市');
INSERT INTO `t_city` VALUES (66, 7, '白城市');
INSERT INTO `t_city` VALUES (67, 7, '延边朝鲜族自治州');
INSERT INTO `t_city` VALUES (68, 8, '哈尔滨市');
INSERT INTO `t_city` VALUES (69, 8, '齐齐哈尔市');
INSERT INTO `t_city` VALUES (70, 8, '鹤 岗 市');
INSERT INTO `t_city` VALUES (71, 8, '双鸭山市');
INSERT INTO `t_city` VALUES (72, 8, '鸡西市');
INSERT INTO `t_city` VALUES (73, 8, '大庆市');
INSERT INTO `t_city` VALUES (74, 8, '伊春市');
INSERT INTO `t_city` VALUES (75, 8, '牡丹江市');
INSERT INTO `t_city` VALUES (76, 8, '佳木斯市');
INSERT INTO `t_city` VALUES (77, 8, '七台河市');
INSERT INTO `t_city` VALUES (78, 8, '黑河市');
INSERT INTO `t_city` VALUES (79, 8, '绥化市');
INSERT INTO `t_city` VALUES (80, 8, '大兴安岭地区');
INSERT INTO `t_city` VALUES (81, 10, '南京市');
INSERT INTO `t_city` VALUES (82, 10, '无锡市');
INSERT INTO `t_city` VALUES (83, 10, '徐州市');
INSERT INTO `t_city` VALUES (84, 10, '常州市');
INSERT INTO `t_city` VALUES (85, 10, '苏州市');
INSERT INTO `t_city` VALUES (86, 10, '南通市');
INSERT INTO `t_city` VALUES (87, 10, '连云港市');
INSERT INTO `t_city` VALUES (88, 10, '淮安市');
INSERT INTO `t_city` VALUES (89, 10, '盐城市');
INSERT INTO `t_city` VALUES (90, 10, '扬州市');
INSERT INTO `t_city` VALUES (91, 10, '镇江市');
INSERT INTO `t_city` VALUES (92, 10, '泰州市');
INSERT INTO `t_city` VALUES (93, 10, '宿迁市');
INSERT INTO `t_city` VALUES (94, 11, '杭州市');
INSERT INTO `t_city` VALUES (95, 11, '宁波市');
INSERT INTO `t_city` VALUES (96, 11, '温州市');
INSERT INTO `t_city` VALUES (97, 11, '嘉兴市');
INSERT INTO `t_city` VALUES (98, 11, '湖州市');
INSERT INTO `t_city` VALUES (99, 11, '绍兴市');
INSERT INTO `t_city` VALUES (100, 11, '金华市');
INSERT INTO `t_city` VALUES (101, 11, '衢州市');
INSERT INTO `t_city` VALUES (102, 11, '舟山市');
INSERT INTO `t_city` VALUES (103, 11, '台州市');
INSERT INTO `t_city` VALUES (104, 11, '丽水市');
INSERT INTO `t_city` VALUES (105, 13, '合肥市');
INSERT INTO `t_city` VALUES (106, 13, '芜湖市');
INSERT INTO `t_city` VALUES (107, 13, '蚌埠市');
INSERT INTO `t_city` VALUES (108, 13, '淮南市');
INSERT INTO `t_city` VALUES (109, 13, '马鞍山市');
INSERT INTO `t_city` VALUES (110, 13, '淮北市');
INSERT INTO `t_city` VALUES (111, 13, '铜陵市');
INSERT INTO `t_city` VALUES (112, 13, '安庆市');
INSERT INTO `t_city` VALUES (113, 13, '黄山市');
INSERT INTO `t_city` VALUES (114, 13, '滁州市');
INSERT INTO `t_city` VALUES (115, 13, '阜阳市');
INSERT INTO `t_city` VALUES (116, 13, '宿州市');
INSERT INTO `t_city` VALUES (117, 13, '巢湖市');
INSERT INTO `t_city` VALUES (118, 13, '六安市');
INSERT INTO `t_city` VALUES (119, 13, '亳州市');
INSERT INTO `t_city` VALUES (120, 13, '池州市');
INSERT INTO `t_city` VALUES (121, 13, '宣城市');
INSERT INTO `t_city` VALUES (133, 14, '南昌市');
INSERT INTO `t_city` VALUES (134, 14, '景德镇市');
INSERT INTO `t_city` VALUES (135, 14, '萍乡市');
INSERT INTO `t_city` VALUES (136, 14, '九江市');
INSERT INTO `t_city` VALUES (137, 14, '新余市');
INSERT INTO `t_city` VALUES (138, 14, '鹰潭市');
INSERT INTO `t_city` VALUES (139, 14, '赣州市');
INSERT INTO `t_city` VALUES (140, 14, '吉安市');
INSERT INTO `t_city` VALUES (141, 14, '宜春市');
INSERT INTO `t_city` VALUES (142, 14, '抚州市');
INSERT INTO `t_city` VALUES (143, 14, '上饶市');
INSERT INTO `t_city` VALUES (144, 15, '济南市');
INSERT INTO `t_city` VALUES (145, 15, '青岛市');
INSERT INTO `t_city` VALUES (146, 15, '淄博市');
INSERT INTO `t_city` VALUES (147, 15, '枣庄市');
INSERT INTO `t_city` VALUES (148, 15, '东营市');
INSERT INTO `t_city` VALUES (149, 15, '烟台市');
INSERT INTO `t_city` VALUES (150, 15, '潍坊市');
INSERT INTO `t_city` VALUES (151, 15, '济宁市');
INSERT INTO `t_city` VALUES (152, 15, '泰安市');
INSERT INTO `t_city` VALUES (153, 15, '威海市');
INSERT INTO `t_city` VALUES (154, 15, '日照市');
INSERT INTO `t_city` VALUES (155, 15, '莱芜市');
INSERT INTO `t_city` VALUES (156, 15, '临沂市');
INSERT INTO `t_city` VALUES (157, 15, '德州市');
INSERT INTO `t_city` VALUES (158, 15, '聊城市');
INSERT INTO `t_city` VALUES (159, 15, '滨州市');
INSERT INTO `t_city` VALUES (160, 15, '菏泽市');
INSERT INTO `t_city` VALUES (161, 16, '郑州市');
INSERT INTO `t_city` VALUES (162, 16, '开封市');
INSERT INTO `t_city` VALUES (163, 16, '洛阳市');
INSERT INTO `t_city` VALUES (164, 16, '平顶山市');
INSERT INTO `t_city` VALUES (165, 16, '安阳市');
INSERT INTO `t_city` VALUES (166, 16, '鹤壁市');
INSERT INTO `t_city` VALUES (167, 16, '新乡市');
INSERT INTO `t_city` VALUES (168, 16, '焦作市');
INSERT INTO `t_city` VALUES (169, 16, '濮阳市');
INSERT INTO `t_city` VALUES (170, 16, '许昌市');
INSERT INTO `t_city` VALUES (171, 16, '漯河市');
INSERT INTO `t_city` VALUES (172, 16, '三门峡市');
INSERT INTO `t_city` VALUES (173, 16, '南阳市');
INSERT INTO `t_city` VALUES (174, 16, '商丘市');
INSERT INTO `t_city` VALUES (175, 16, '信阳市');
INSERT INTO `t_city` VALUES (176, 16, '周口市');
INSERT INTO `t_city` VALUES (177, 16, '驻马店市');
INSERT INTO `t_city` VALUES (178, 16, '济源市');
INSERT INTO `t_city` VALUES (179, 17, '武汉市');
INSERT INTO `t_city` VALUES (180, 17, '黄石市');
INSERT INTO `t_city` VALUES (181, 17, '十堰市');
INSERT INTO `t_city` VALUES (182, 17, '荆州市');
INSERT INTO `t_city` VALUES (183, 17, '宜昌市');
INSERT INTO `t_city` VALUES (184, 17, '襄樊市');
INSERT INTO `t_city` VALUES (185, 17, '鄂州市');
INSERT INTO `t_city` VALUES (186, 17, '荆门市');
INSERT INTO `t_city` VALUES (187, 17, '孝感市');
INSERT INTO `t_city` VALUES (188, 17, '黄冈市');
INSERT INTO `t_city` VALUES (189, 17, '咸宁市');
INSERT INTO `t_city` VALUES (190, 17, '随州市');
INSERT INTO `t_city` VALUES (191, 17, '仙桃市');
INSERT INTO `t_city` VALUES (192, 17, '天门市');
INSERT INTO `t_city` VALUES (193, 17, '潜江市');
INSERT INTO `t_city` VALUES (194, 17, '神农架林区');
INSERT INTO `t_city` VALUES (195, 17, '恩施土家族苗族自治州');
INSERT INTO `t_city` VALUES (196, 18, '长沙市');
INSERT INTO `t_city` VALUES (197, 18, '株洲市');
INSERT INTO `t_city` VALUES (198, 18, '湘潭市');
INSERT INTO `t_city` VALUES (199, 18, '衡阳市');
INSERT INTO `t_city` VALUES (200, 18, '邵阳市');
INSERT INTO `t_city` VALUES (201, 18, '岳阳市');
INSERT INTO `t_city` VALUES (202, 18, '常德市');
INSERT INTO `t_city` VALUES (203, 18, '张家界市');
INSERT INTO `t_city` VALUES (204, 18, '益阳市');
INSERT INTO `t_city` VALUES (205, 18, '郴州市');
INSERT INTO `t_city` VALUES (206, 18, '永州市');
INSERT INTO `t_city` VALUES (207, 18, '怀化市');
INSERT INTO `t_city` VALUES (208, 18, '娄底市');
INSERT INTO `t_city` VALUES (209, 18, '湘西土家族苗族自治州');
INSERT INTO `t_city` VALUES (210, 19, '广州市');
INSERT INTO `t_city` VALUES (211, 19, '深圳市');
INSERT INTO `t_city` VALUES (212, 19, '珠海市');
INSERT INTO `t_city` VALUES (213, 19, '汕头市');
INSERT INTO `t_city` VALUES (214, 19, '韶关市');
INSERT INTO `t_city` VALUES (215, 19, '佛山市');
INSERT INTO `t_city` VALUES (216, 19, '江门市');
INSERT INTO `t_city` VALUES (217, 19, '湛江市');
INSERT INTO `t_city` VALUES (218, 19, '茂名市');
INSERT INTO `t_city` VALUES (219, 19, '肇庆市');
INSERT INTO `t_city` VALUES (220, 19, '惠州市');
INSERT INTO `t_city` VALUES (221, 19, '梅州市');
INSERT INTO `t_city` VALUES (222, 19, '汕尾市');
INSERT INTO `t_city` VALUES (223, 19, '河源市');
INSERT INTO `t_city` VALUES (224, 19, '阳江市');
INSERT INTO `t_city` VALUES (225, 19, '清远市');
INSERT INTO `t_city` VALUES (226, 19, '东莞市');
INSERT INTO `t_city` VALUES (227, 19, '中山市');
INSERT INTO `t_city` VALUES (228, 19, '潮州市');
INSERT INTO `t_city` VALUES (229, 19, '揭阳市');
INSERT INTO `t_city` VALUES (230, 19, '云浮市');
INSERT INTO `t_city` VALUES (231, 28, '兰州市');
INSERT INTO `t_city` VALUES (232, 33, '香港特别行政区');
INSERT INTO `t_city` VALUES (233, 34, '澳门特别行政区');
INSERT INTO `t_city` VALUES (234, 9, '上海市');
INSERT INTO `t_city` VALUES (235, 22, '重庆市');
INSERT INTO `t_city` VALUES (236, 1, '北京市');
INSERT INTO `t_city` VALUES (237, 2, '天津市');
INSERT INTO `t_city` VALUES (238, 28, '金昌市');
INSERT INTO `t_city` VALUES (239, 28, '白银市');
INSERT INTO `t_city` VALUES (240, 28, '天水市');
INSERT INTO `t_city` VALUES (241, 28, '嘉峪关市');
INSERT INTO `t_city` VALUES (242, 28, '武威市');
INSERT INTO `t_city` VALUES (243, 28, '张掖市');
INSERT INTO `t_city` VALUES (244, 28, '平凉市');
INSERT INTO `t_city` VALUES (245, 28, '酒泉市');
INSERT INTO `t_city` VALUES (246, 28, '庆阳市');
INSERT INTO `t_city` VALUES (247, 28, '定西市');
INSERT INTO `t_city` VALUES (248, 28, '陇南市');
INSERT INTO `t_city` VALUES (249, 28, '临夏回族自治州');
INSERT INTO `t_city` VALUES (250, 28, '甘南藏族自治州');
INSERT INTO `t_city` VALUES (251, 23, '成都市');
INSERT INTO `t_city` VALUES (252, 23, '自贡市');
INSERT INTO `t_city` VALUES (253, 23, '攀枝花市');
INSERT INTO `t_city` VALUES (254, 23, '泸州市');
INSERT INTO `t_city` VALUES (255, 23, '德阳市');
INSERT INTO `t_city` VALUES (256, 23, '绵阳市');
INSERT INTO `t_city` VALUES (257, 23, '广元市');
INSERT INTO `t_city` VALUES (258, 23, '遂宁市');
INSERT INTO `t_city` VALUES (259, 23, '内江市');
INSERT INTO `t_city` VALUES (260, 23, '乐山市');
INSERT INTO `t_city` VALUES (261, 23, '南充市');
INSERT INTO `t_city` VALUES (262, 23, '眉山市');
INSERT INTO `t_city` VALUES (263, 23, '宜宾市');
INSERT INTO `t_city` VALUES (264, 23, '广安市');
INSERT INTO `t_city` VALUES (265, 23, '达州市');
INSERT INTO `t_city` VALUES (266, 23, '雅安市');
INSERT INTO `t_city` VALUES (267, 23, '巴中市');
INSERT INTO `t_city` VALUES (268, 23, '资阳市');
INSERT INTO `t_city` VALUES (269, 23, '阿坝藏族羌族自治州');
INSERT INTO `t_city` VALUES (270, 23, '甘孜藏族自治州');
INSERT INTO `t_city` VALUES (271, 23, '凉山彝族自治州');
INSERT INTO `t_city` VALUES (272, 24, '贵阳市');
INSERT INTO `t_city` VALUES (273, 24, '六盘水市');
INSERT INTO `t_city` VALUES (274, 24, '遵义市');
INSERT INTO `t_city` VALUES (275, 24, '安顺市');
INSERT INTO `t_city` VALUES (276, 24, '铜仁地区');
INSERT INTO `t_city` VALUES (277, 24, '毕节地区');
INSERT INTO `t_city` VALUES (278, 24, '黔西南布依族苗族自治州');
INSERT INTO `t_city` VALUES (279, 24, '黔东南苗族侗族自治州');
INSERT INTO `t_city` VALUES (280, 24, '黔南布依族苗族自治州');
INSERT INTO `t_city` VALUES (281, 21, '海口市');
INSERT INTO `t_city` VALUES (282, 21, '三亚市');
INSERT INTO `t_city` VALUES (283, 21, '五指山市');
INSERT INTO `t_city` VALUES (284, 21, '琼海市');
INSERT INTO `t_city` VALUES (285, 21, '儋州市');
INSERT INTO `t_city` VALUES (286, 21, '文昌市');
INSERT INTO `t_city` VALUES (287, 21, '万宁市');
INSERT INTO `t_city` VALUES (288, 21, '东方市');
INSERT INTO `t_city` VALUES (289, 21, '澄迈县');
INSERT INTO `t_city` VALUES (290, 21, '定安县');
INSERT INTO `t_city` VALUES (291, 21, '屯昌县');
INSERT INTO `t_city` VALUES (292, 21, '临高县');
INSERT INTO `t_city` VALUES (293, 21, '白沙黎族自治县');
INSERT INTO `t_city` VALUES (294, 21, '昌江黎族自治县');
INSERT INTO `t_city` VALUES (295, 21, '乐东黎族自治县');
INSERT INTO `t_city` VALUES (296, 21, '陵水黎族自治县');
INSERT INTO `t_city` VALUES (297, 21, '保亭黎族苗族自治县');
INSERT INTO `t_city` VALUES (298, 21, '琼中黎族苗族自治县');
INSERT INTO `t_city` VALUES (299, 25, '昆明市');
INSERT INTO `t_city` VALUES (300, 25, '曲靖市');
INSERT INTO `t_city` VALUES (301, 25, '玉溪市');
INSERT INTO `t_city` VALUES (302, 25, '保山市');
INSERT INTO `t_city` VALUES (303, 25, '昭通市');
INSERT INTO `t_city` VALUES (304, 25, '丽江市');
INSERT INTO `t_city` VALUES (305, 25, '思茅市');
INSERT INTO `t_city` VALUES (306, 25, '临沧市');
INSERT INTO `t_city` VALUES (307, 25, '文山壮族苗族自治州');
INSERT INTO `t_city` VALUES (308, 25, '红河哈尼族彝族自治州');
INSERT INTO `t_city` VALUES (309, 25, '西双版纳傣族自治州');
INSERT INTO `t_city` VALUES (310, 25, '楚雄彝族自治州');
INSERT INTO `t_city` VALUES (311, 25, '大理白族自治州');
INSERT INTO `t_city` VALUES (312, 25, '德宏傣族景颇族自治州');
INSERT INTO `t_city` VALUES (313, 25, '怒江傈傈族自治州');
INSERT INTO `t_city` VALUES (314, 25, '迪庆藏族自治州');
INSERT INTO `t_city` VALUES (315, 29, '西宁市');
INSERT INTO `t_city` VALUES (316, 29, '海东地区');
INSERT INTO `t_city` VALUES (317, 29, '海北藏族自治州');
INSERT INTO `t_city` VALUES (318, 29, '黄南藏族自治州');
INSERT INTO `t_city` VALUES (319, 29, '海南藏族自治州');
INSERT INTO `t_city` VALUES (320, 29, '果洛藏族自治州');
INSERT INTO `t_city` VALUES (321, 29, '玉树藏族自治州');
INSERT INTO `t_city` VALUES (322, 29, '海西蒙古族藏族自治州');
INSERT INTO `t_city` VALUES (323, 27, '西安市');
INSERT INTO `t_city` VALUES (324, 27, '铜川市');
INSERT INTO `t_city` VALUES (325, 27, '宝鸡市');
INSERT INTO `t_city` VALUES (326, 27, '咸阳市');
INSERT INTO `t_city` VALUES (327, 27, '渭南市');
INSERT INTO `t_city` VALUES (328, 27, '延安市');
INSERT INTO `t_city` VALUES (329, 27, '汉中市');
INSERT INTO `t_city` VALUES (330, 27, '榆林市');
INSERT INTO `t_city` VALUES (331, 27, '安康市');
INSERT INTO `t_city` VALUES (332, 27, '商洛市');
INSERT INTO `t_city` VALUES (333, 20, '南宁市');
INSERT INTO `t_city` VALUES (334, 20, '柳州市');
INSERT INTO `t_city` VALUES (335, 20, '桂林市');
INSERT INTO `t_city` VALUES (336, 20, '梧州市');
INSERT INTO `t_city` VALUES (337, 20, '北海市');
INSERT INTO `t_city` VALUES (338, 20, '防城港市');
INSERT INTO `t_city` VALUES (339, 20, '钦州市');
INSERT INTO `t_city` VALUES (340, 20, '贵港市');
INSERT INTO `t_city` VALUES (341, 20, '玉林市');
INSERT INTO `t_city` VALUES (342, 20, '百色市');
INSERT INTO `t_city` VALUES (343, 20, '贺州市');
INSERT INTO `t_city` VALUES (344, 20, '河池市');
INSERT INTO `t_city` VALUES (345, 20, '来宾市');
INSERT INTO `t_city` VALUES (346, 20, '崇左市');
INSERT INTO `t_city` VALUES (347, 26, '拉萨市');
INSERT INTO `t_city` VALUES (348, 26, '那曲地区');
INSERT INTO `t_city` VALUES (349, 26, '昌都地区');
INSERT INTO `t_city` VALUES (350, 26, '山南地区');
INSERT INTO `t_city` VALUES (351, 26, '日喀则地区');
INSERT INTO `t_city` VALUES (352, 26, '阿里地区');
INSERT INTO `t_city` VALUES (353, 26, '林芝地区');
INSERT INTO `t_city` VALUES (354, 30, '银川市');
INSERT INTO `t_city` VALUES (355, 30, '石嘴山市');
INSERT INTO `t_city` VALUES (356, 30, '吴忠市');
INSERT INTO `t_city` VALUES (357, 30, '固原市');
INSERT INTO `t_city` VALUES (358, 30, '中卫市');
INSERT INTO `t_city` VALUES (359, 31, '乌鲁木齐市');
INSERT INTO `t_city` VALUES (360, 31, '克拉玛依市');
INSERT INTO `t_city` VALUES (361, 31, '石河子市　');
INSERT INTO `t_city` VALUES (362, 31, '阿拉尔市');
INSERT INTO `t_city` VALUES (363, 31, '图木舒克市');
INSERT INTO `t_city` VALUES (364, 31, '五家渠市');
INSERT INTO `t_city` VALUES (365, 31, '吐鲁番市');
INSERT INTO `t_city` VALUES (366, 31, '阿克苏市');
INSERT INTO `t_city` VALUES (367, 31, '喀什市');
INSERT INTO `t_city` VALUES (368, 31, '哈密市');
INSERT INTO `t_city` VALUES (369, 31, '和田市');
INSERT INTO `t_city` VALUES (370, 31, '阿图什市');
INSERT INTO `t_city` VALUES (371, 31, '库尔勒市');
INSERT INTO `t_city` VALUES (372, 31, '昌吉市　');
INSERT INTO `t_city` VALUES (373, 31, '阜康市');
INSERT INTO `t_city` VALUES (374, 31, '米泉市');
INSERT INTO `t_city` VALUES (375, 31, '博乐市');
INSERT INTO `t_city` VALUES (376, 31, '伊宁市');
INSERT INTO `t_city` VALUES (377, 31, '奎屯市');
INSERT INTO `t_city` VALUES (378, 31, '塔城市');
INSERT INTO `t_city` VALUES (379, 31, '乌苏市');
INSERT INTO `t_city` VALUES (380, 31, '阿勒泰市');
INSERT INTO `t_city` VALUES (381, 5, '呼和浩特市');
INSERT INTO `t_city` VALUES (382, 5, '包头市');
INSERT INTO `t_city` VALUES (383, 5, '乌海市');
INSERT INTO `t_city` VALUES (384, 5, '赤峰市');
INSERT INTO `t_city` VALUES (385, 5, '通辽市');
INSERT INTO `t_city` VALUES (386, 5, '鄂尔多斯市');
INSERT INTO `t_city` VALUES (387, 5, '呼伦贝尔市');
INSERT INTO `t_city` VALUES (388, 5, '巴彦淖尔市');
INSERT INTO `t_city` VALUES (389, 5, '乌兰察布市');
INSERT INTO `t_city` VALUES (390, 5, '锡林郭勒盟');
INSERT INTO `t_city` VALUES (391, 5, '兴安盟');
INSERT INTO `t_city` VALUES (392, 5, '阿拉善盟');

-- ----------------------------
-- Table structure for t_comment
-- ----------------------------
DROP TABLE IF EXISTS `t_comment`;
CREATE TABLE `t_comment`  (
  `m_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '评论id',
  `i_id` int(0) NOT NULL COMMENT '帖子id（外键）',
  `m_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '评论内容',
  `m_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '评论时间',
  `m_p_id` int(0) NULL DEFAULT NULL COMMENT '父评论id（用户二级评论，连接本表id）',
  `u_id` int(0) NOT NULL COMMENT '评论用户id（外键）',
  `m_like_count` int(0) NOT NULL DEFAULT 0 COMMENT '点赞量',
  `m_to_m_id` int(0) NULL DEFAULT NULL COMMENT '回复目标评论的id（回复二级评论，连接本表id）',
  PRIMARY KEY (`m_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 53 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment
-- ----------------------------
INSERT INTO `t_comment` VALUES (1, 1, '一级评论', '2022-11-04 17:11:20', 0, 2, 1, 0);
INSERT INTO `t_comment` VALUES (2, 1, '二级评论', '2022-11-04 17:11:38', 1, 2, 0, 0);
INSERT INTO `t_comment` VALUES (3, 1, '二级评论回复', '2022-11-04 17:11:55', 1, 2, 0, 2);
INSERT INTO `t_comment` VALUES (6, 1, '一级评论2', '2022-11-08 10:46:26', 0, 2, 0, 0);
INSERT INTO `t_comment` VALUES (7, 1, 'ttttttt', '2022-11-08 13:10:19', 0, 2, 0, 0);
INSERT INTO `t_comment` VALUES (26, 1, '2222222222222222', '2022-11-08 13:25:19', 0, 2, 0, 0);
INSERT INTO `t_comment` VALUES (27, 1, '3333', '2022-11-08 13:43:31', 0, 2, 0, 0);
INSERT INTO `t_comment` VALUES (28, 1, '3123', '2022-11-08 13:47:37', 0, 2, 0, 0);
INSERT INTO `t_comment` VALUES (29, 1, 'zzzz', '2022-11-08 13:54:00', 1, 2, 0, 0);
INSERT INTO `t_comment` VALUES (30, 1, 'ssssssss', '2022-11-08 14:12:50', 1, 2, 0, 29);
INSERT INTO `t_comment` VALUES (31, 2, '6666666', '2022-11-10 21:50:48', 0, 2, 0, 0);
INSERT INTO `t_comment` VALUES (32, 2, 'hhhhhhh', '2022-11-13 16:22:49', 31, 2, 0, 0);
INSERT INTO `t_comment` VALUES (33, 2, '1111111', '2022-11-17 10:50:29', 0, 2, 0, 0);
INSERT INTO `t_comment` VALUES (34, 2, 'xixixi', '2022-11-17 10:50:43', 31, 2, 0, 32);
INSERT INTO `t_comment` VALUES (35, 2, '6785', '2022-11-17 10:50:55', 33, 2, 0, 0);
INSERT INTO `t_comment` VALUES (36, 4, '说得对', '2022-11-17 14:54:48', 0, 2, 0, 0);
INSERT INTO `t_comment` VALUES (37, 4, '嗯嗯！谢谢', '2022-11-17 14:55:04', 36, 7, 0, 0);
INSERT INTO `t_comment` VALUES (38, 5, '中国人不骗中国人', '2022-11-17 15:00:15', 0, 2, 0, 0);
INSERT INTO `t_comment` VALUES (39, 6, '卷又卷不动，摆又摆不烂', '2022-11-17 15:04:50', 0, 2, 0, 0);
INSERT INTO `t_comment` VALUES (40, 6, '死又不敢死', '2022-11-17 15:05:28', 39, 4, 0, 0);
INSERT INTO `t_comment` VALUES (41, 7, '可以找点搞笑视频放进去', '2022-11-17 15:06:55', 0, 7, 0, 0);
INSERT INTO `t_comment` VALUES (42, 8, '不会，下一个', '2022-11-17 15:13:42', 0, 2, 0, 0);
INSERT INTO `t_comment` VALUES (43, 9, '校门口你不应该卖猪猪 要卖时兴动画片里的人物啥的', '2022-11-17 15:22:21', 0, 4, 0, 0);
INSERT INTO `t_comment` VALUES (44, 9, '是不是还要做一个时兴的头发', '2022-11-17 15:22:33', 43, 2, 0, 0);
INSERT INTO `t_comment` VALUES (45, 9, 'yep', '2022-11-17 15:22:45', 43, 4, 0, 44);
INSERT INTO `t_comment` VALUES (46, 9, '6', '2022-11-17 15:23:23', 0, 7, 0, 0);
INSERT INTO `t_comment` VALUES (47, 10, '本人有幸体验过', '2022-11-17 15:25:31', 0, 4, 0, 0);
INSERT INTO `t_comment` VALUES (48, 5, 'h回复', '2022-11-18 16:37:45', 38, 7, 0, 0);
INSERT INTO `t_comment` VALUES (49, 5, '多发点撒', '2022-11-18 16:37:51', 0, 7, 0, 0);
INSERT INTO `t_comment` VALUES (50, 5, '幅度萨芬', '2022-11-18 16:37:58', 38, 7, 0, 48);
INSERT INTO `t_comment` VALUES (51, 2, 'gagagaga', '2022-12-07 13:33:38', 0, 7, 0, 0);
INSERT INTO `t_comment` VALUES (52, 7, 'yyyyyyyyyyy', '2022-12-07 13:44:07', 0, 7, 0, 0);

-- ----------------------------
-- Table structure for t_comment_like
-- ----------------------------
DROP TABLE IF EXISTS `t_comment_like`;
CREATE TABLE `t_comment_like`  (
  `c_l_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `c_id` int(0) NULL DEFAULT NULL COMMENT '评论id（外键）',
  `u_id` int(0) NULL DEFAULT NULL COMMENT '点赞用户id（外键）',
  PRIMARY KEY (`c_l_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_comment_like
-- ----------------------------
INSERT INTO `t_comment_like` VALUES (1, 1, 2);

-- ----------------------------
-- Table structure for t_friend
-- ----------------------------
DROP TABLE IF EXISTS `t_friend`;
CREATE TABLE `t_friend`  (
  `f_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '好友id',
  `u_id` int(0) NOT NULL COMMENT '用户id（外键）',
  `f_u_id` int(0) NOT NULL COMMENT '用户好友的id（外键）',
  `f_remark` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '好友备注',
  `f_state` int(0) NULL DEFAULT NULL COMMENT '好友状态',
  `f_verification_information` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '好友验证信息',
  `f_del` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`f_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_friend
-- ----------------------------
INSERT INTO `t_friend` VALUES (1, 2, 1, NULL, 1, '123', 1);
INSERT INTO `t_friend` VALUES (2, 2, 1, NULL, 2, '123', 0);
INSERT INTO `t_friend` VALUES (3, 2, 1, NULL, 2, '123', 0);
INSERT INTO `t_friend` VALUES (4, 2, 4, '好铁汁', 1, 'nihao', 0);
INSERT INTO `t_friend` VALUES (5, 4, 2, NULL, 1, NULL, 0);
INSERT INTO `t_friend` VALUES (6, 1, 2, NULL, 1, NULL, 0);
INSERT INTO `t_friend` VALUES (7, 7, 2, NULL, 2, '八嘎', 0);
INSERT INTO `t_friend` VALUES (8, 7, 2, NULL, 0, 'hello', 0);

-- ----------------------------
-- Table structure for t_goods
-- ----------------------------
DROP TABLE IF EXISTS `t_goods`;
CREATE TABLE `t_goods`  (
  `g_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `u_id` int(0) NOT NULL COMMENT '发布用户id（外键）',
  `g_price` decimal(10, 2) NOT NULL COMMENT '商品价格',
  `g_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品名称',
  `g_describe` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品描述',
  `g_c_id` int(0) NOT NULL COMMENT '商品类别（外键）',
  `g_state` int(0) NULL DEFAULT 1 COMMENT '商品状态',
  `g_hits` int(0) NULL DEFAULT 0 COMMENT '商品点击量',
  `g_release_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '商品发布时间',
  `g_last_time` datetime(0) NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '商品最后修改时间',
  `g_is_recommend` int(0) NOT NULL DEFAULT 0 COMMENT '是否推荐',
  `g_final_price` decimal(10, 2) NULL DEFAULT NULL COMMENT '最终成交价格',
  PRIMARY KEY (`g_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 50 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods
-- ----------------------------
INSERT INTO `t_goods` VALUES (1, 4, 23.32, 'test', 'dsfdasfdsafasdfasdfasfsdfdasfasdffasdfasdfasdfasdfasdfasdfasdfasdfasdffdsa的身份噶士大夫撒地方', 1, 1, 409, '2022-10-03 15:23:47', '2022-11-13 16:24:42', 0, NULL);
INSERT INTO `t_goods` VALUES (2, 2, 12.20, 'test', 'testtest', 1, 2, 3, '2022-11-07 15:51:05', '2022-11-08 19:09:27', 1, 123.00);
INSERT INTO `t_goods` VALUES (3, 2, 12.20, 'test', 'testtest', 1, 2, 0, '2022-11-07 15:59:12', '2022-11-07 15:59:12', 0, 120.00);
INSERT INTO `t_goods` VALUES (4, 2, 123.00, 'fdas', '2131', 1, 2, 0, '2022-11-07 16:01:13', '2022-11-11 17:01:06', 1, NULL);
INSERT INTO `t_goods` VALUES (24, 6, 19.99, '夹缝收纳柜', '质量很好，是pp材质的。没有气味，一共五层，可以放很多东西。宽32cm，高70cm，长38cm。要搬走了，不方便带走，低价出，要的联系我！', 12, 1, 2, '2022-11-17 13:59:10', '2022-11-17 14:40:54', 1, NULL);
INSERT INTO `t_goods` VALUES (25, 7, 99.90, 'TTC虎轴', '出一盒虎轴，全新未使用。有意者私！', 9, 1, 0, '2022-11-17 14:02:19', '2022-11-17 14:40:57', 1, NULL);
INSERT INTO `t_goods` VALUES (26, 6, 12.90, '床上桌', '床上书桌折叠小桌子宿舍学生学习桌笔记本电脑桌卧室桌。偏小款，尺寸50*30高23。要的可联系我！', 12, 1, 0, '2022-11-17 14:02:51', '2022-11-18 16:45:38', 1, NULL);
INSERT INTO `t_goods` VALUES (27, 6, 65.59, 'SKII神仙水', '出两瓶神仙水，双十一买多了，全新未使用。价格可商议，要的私信我~', 10, 1, 0, '2022-11-17 14:03:28', '2022-11-17 14:03:28', 0, NULL);
INSERT INTO `t_goods` VALUES (28, 6, 29.90, 'AUX热水壶', '出热水壶，可保温，设定温度~ 功能多多，要的私信我！', 16, 1, 0, '2022-11-17 14:04:28', '2022-11-17 14:04:28', 0, NULL);
INSERT INTO `t_goods` VALUES (29, 6, 1999.00, 'iPhone12', '95成新，未拆过，无损坏的地方。用了一年多，想换新手机了，低价出。诚信要的私我！（价格可商议', 9, 1, 0, '2022-11-17 14:07:04', '2022-11-17 14:07:04', 0, NULL);
INSERT INTO `t_goods` VALUES (30, 7, 30.00, '罗马仕充电宝', '9成新，20000毫安，低价出。诚信要的私我！（价格可商议）', 16, 1, 0, '2022-11-17 14:07:45', '2022-11-17 14:07:45', 0, NULL);
INSERT INTO `t_goods` VALUES (31, 7, 69.90, 'AHC护肤套装', '出一套护肤品，只使用过一两次，共4件商品（一瓶水，一瓶乳，一个洁面，一瓶霜）想要的私我！', 10, 1, 0, '2022-11-17 14:08:26', '2022-11-17 14:08:26', 0, NULL);
INSERT INTO `t_goods` VALUES (32, 2, 9.90, '名创优品风扇', 'USB手拿小风扇，方便携带，送充电线！要的私~', 16, 1, 1, '2022-11-17 14:09:47', '2022-11-17 14:09:47', 0, NULL);
INSERT INTO `t_goods` VALUES (33, 2, 15.00, '荣事达usb小风扇', '荣事达usb小风扇小型学生宿舍便携式迷你可充电台式夹式随身家用电扇夹扇桌面超静音床上手持车载架子电风扇，校内自提。', 16, 1, 0, '2022-11-17 14:10:30', '2022-11-17 14:10:30', 0, NULL);
INSERT INTO `t_goods` VALUES (34, 2, 58.00, 'Ipad蓝牙键盘', 'ipad适用键盘，非机械键盘，有轻微使用痕迹，带白色背光，蓝牙连接，功能完好，普通安卓线充电，贴纸不喜欢的话可以撕掉淘一些自己喜欢的～', 9, 1, 1, '2022-11-17 14:11:30', '2022-11-17 14:11:30', 0, NULL);
INSERT INTO `t_goods` VALUES (35, 2, 66.66, '脉动音响', '脉动5音响华强北顶配款蓝牙炫彩氛围灯音箱重低音户外便携。66mm扬声器、360°全面屏光效、带音随律动声光同步效果、360度环绕灯光、音效全面升级下沉式低音、环绕高低音效。支持内存卡、U盘、手机蓝牙连接播放，type-c充电口。', 9, 1, 0, '2022-11-17 14:13:07', '2022-11-17 14:13:07', 0, NULL);
INSERT INTO `t_goods` VALUES (36, 2, 18.00, '925银吊坠女项链', '925银吊坠女项链纯银镀铂金锁骨链子简约百搭首饰礼物。前男友送的一条项链，现在分手了不想看见，便宜出给有缘人吧，仅试戴，礼盒还在 ，看上的来带走叭', 18, 1, 1, '2022-11-17 14:13:58', '2022-11-17 14:13:58', 0, NULL);
INSERT INTO `t_goods` VALUES (37, 2, 30.00, '香奈儿小圆包', '闺蜜送的包包，399买的，现在39元包邮，我不喜欢，包包太多了，背不过来，没有带包装。一次没背过，全新！喜欢的把他带回去吧， 宝贝还在的，喜欢可私信我！', 18, 1, 4, '2022-11-17 14:14:48', '2022-11-17 14:40:59', 1, NULL);
INSERT INTO `t_goods` VALUES (38, 7, 505.00, 'Airpods Pro蓝牙耳机', '个人闲置未拆封苹果耳机Airpods Pro无线正品耳机国行。本来也有一个的，找了几天找不到了，就新买了这个，后来又找到了，所以我就用之前的。', 9, 1, 0, '2022-11-17 14:15:40', '2022-11-17 14:15:40', 0, NULL);
INSERT INTO `t_goods` VALUES (39, 6, 69.00, 'POP MART/泡泡玛特盲盒', '盲盒福袋！！ 退坑出 一组4-5个。不许愿哦，这个价已经很便宜了。不指定款式，上来直接问这几个什么价的一律不回！！！！！保真，均拆盒拆袋，盒卡都在。喜欢的私！', 15, 1, 1, '2022-11-17 14:16:33', '2022-11-17 14:22:57', 1, NULL);
INSERT INTO `t_goods` VALUES (40, 4, 29.00, '星火英语四级', '星火英语四级真题，包含试题12套，真题卷8套，5套模拟，12套听力等还带视频教程祝您备考12月。本人四六级已过。考证狂魔，推荐这款刷题资料，祝您顺利通过。不讲价！！！', 17, 1, 0, '2022-11-17 14:28:19', '2022-11-17 14:41:04', 1, NULL);
INSERT INTO `t_goods` VALUES (41, 4, 62.00, 'JanSport杰斯伯学生背包', 'JanSport杰斯伯学生背包双肩包经典纯色潮流电脑书包男女。个人闲置，有防伪码，全新，吊牌齐全、有电脑隔层，（尺寸）长33宽21高42cm。', 18, 1, 0, '2022-11-17 14:29:22', '2022-11-17 14:29:22', 0, NULL);
INSERT INTO `t_goods` VALUES (42, 4, 6.90, 'USB手拿小风扇', 'USB手拿小风扇，可放桌上，也可携带，送充电线！要的私~', 16, 1, 0, '2022-11-17 14:30:40', '2022-11-17 14:30:40', 0, NULL);
INSERT INTO `t_goods` VALUES (43, 4, 25.38, '宿舍折叠椅', '宿舍椅子简易凳子靠背椅家用折叠椅子靠背办公椅大学生电脑椅靠背，支持自提。感兴趣的话点“我想要”和我私聊吧', 12, 1, 0, '2022-11-17 14:34:13', '2022-11-17 14:34:13', 0, NULL);
INSERT INTO `t_goods` VALUES (44, 4, 120.00, '兰蔻持妆粉底PO-01号色', '兰蔻持妆粉底PO-01号色，朋友送的，对于我来说实在是太白了，用过几次。2023年10月过期，便宜出了。', 11, 1, 0, '2022-11-17 14:38:06', '2022-11-17 14:38:06', 0, NULL);
INSERT INTO `t_goods` VALUES (45, 4, 56.00, '小米路由器', '小米路由器4A百兆。无线双频，四天线稳定穿墙，防蹭5G双频，95新。正品原装。', 16, 1, 0, '2022-11-17 14:39:19', '2022-11-17 14:39:19', 0, NULL);
INSERT INTO `t_goods` VALUES (46, 4, 112.00, '柴犬幼犬宠物狗', '柴犬幼犬宠物狗狗送狗粮笼子，要回老家带不走 \n品牌型号：纯种柴犬\n转手原因：回老家带不走\n入手渠道：自己养的小狗狗\n全新 两个多月大，疫苗驱虫都按期做了的，什么都吃不挑食 健康好饲养。 \n使用感受：狗狗是人类最忠实的伙伴，请善待！非爱心人士请止步！商家请绕道！', 14, 1, 0, '2022-11-17 14:40:29', '2022-11-17 14:40:52', 1, NULL);
INSERT INTO `t_goods` VALUES (47, 7, 99.00, '风格士大夫', '热伤风', 1, 1, 0, '2022-11-18 16:39:41', '2022-11-18 16:39:41', 0, NULL);

-- ----------------------------
-- Table structure for t_goods_category
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_category`;
CREATE TABLE `t_goods_category`  (
  `g_c_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '商品类别id',
  `g_c_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品类别名称',
  `g_c_del` int(0) NULL DEFAULT 0 COMMENT '逻辑删除',
  PRIMARY KEY (`g_c_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods_category
-- ----------------------------
INSERT INTO `t_goods_category` VALUES (1, '文具', 0);
INSERT INTO `t_goods_category` VALUES (9, '数码', 0);
INSERT INTO `t_goods_category` VALUES (10, '护肤', 0);
INSERT INTO `t_goods_category` VALUES (11, '美容彩妆', 0);
INSERT INTO `t_goods_category` VALUES (12, '生活用品', 0);
INSERT INTO `t_goods_category` VALUES (13, '花卉', 0);
INSERT INTO `t_goods_category` VALUES (14, '宠物', 0);
INSERT INTO `t_goods_category` VALUES (15, '盲盒', 0);
INSERT INTO `t_goods_category` VALUES (16, '电器', 0);
INSERT INTO `t_goods_category` VALUES (17, '书籍', 0);
INSERT INTO `t_goods_category` VALUES (18, '包表首饰', 0);

-- ----------------------------
-- Table structure for t_goods_picture
-- ----------------------------
DROP TABLE IF EXISTS `t_goods_picture`;
CREATE TABLE `t_goods_picture`  (
  `g_p_id` int(0) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '商品图片id',
  `g_id` int(0) NOT NULL COMMENT '商品id（外键）',
  `g_p_img` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '商品图片url',
  `g_p_is_main` int(0) NOT NULL COMMENT '是否为主图',
  `g_p_state` int(0) NULL DEFAULT 0,
  PRIMARY KEY (`g_p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_goods_picture
-- ----------------------------
INSERT INTO `t_goods_picture` VALUES (11, 1, 'GOODS暂无图片.png', 2, 1);
INSERT INTO `t_goods_picture` VALUES (18, 24, '/upload/goods/6/af501d60-2519-4104-98be-3c808b8b2dad-图片1.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (19, 25, '/upload/goods/7/c4c0637c-03e1-4286-a84c-79da75788f95-图片3.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (20, 25, '/upload/goods/7/08951224-cfa1-4cb1-a11d-235f1371ddea-图片2.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (21, 25, '/upload/goods/7/32746bc3-9e88-4e04-9492-40d4c415cfa6-图片4.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (22, 26, '/upload/goods/6/41ad2c27-0bdb-4c2d-83ce-e75fd0868134-图片5.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (23, 27, '/upload/goods/6/4edc7551-be61-40a0-a580-6ae1297e2b43-图片6.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (24, 28, '/upload/goods/6/0a87d541-a161-46dd-8b6a-0f2ea52ed986-图片7.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (25, 29, '/upload/goods/6/1defe197-c2a1-4464-8905-d5902f27a6e5-图片8.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (26, 29, '/upload/goods/6/0ef0325d-7a4d-4006-a08d-b4b85e711954-图片9.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (27, 30, '/upload/goods/7/e0625e7f-e7d1-4006-867d-fbff26475b4e-图片10.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (28, 30, '/upload/goods/7/5bdfef3e-93e3-4ed3-8103-a659aeba2b86-图片11.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (29, 31, '/upload/goods/7/15cef804-4cb8-4f65-8426-2667412b18a3-图片12.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (30, 32, '/upload/goods/2/c654578c-77d2-485b-be8e-0a27eb5670a4-图片13.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (31, 32, 'GOODS暂无图片.png', 2, 1);
INSERT INTO `t_goods_picture` VALUES (32, 33, '/upload/goods/2/444fcec0-7951-4410-9bf8-3f48f04e411d-图片15.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (33, 34, '/upload/goods/2/238b1575-679c-46ca-a76e-f372391f18cc-图片16.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (34, 34, '/upload/goods/2/13ee6f18-538e-4a90-b12d-324f31bc88a6-图片17.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (35, 34, '/upload/goods/2/e588fa02-5203-4a60-aad7-02aa081c9ac8-图片18.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (36, 34, '/upload/goods/2/0bc2b2dd-f55a-4b24-b4b5-5d94975bad45-图片19.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (37, 35, '/upload/goods/2/667f5faf-017d-4fbe-8589-2959f4b314b8-图片21.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (38, 35, '/upload/goods/2/8b08f832-596d-400e-8653-ec9b128fd60e-图片20.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (39, 36, '/upload/goods/2/3f3f5104-a555-4e58-a856-97be7832da81-图片22.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (40, 36, '/upload/goods/2/51a981f1-d7b3-4a37-b4ca-a589f0ccb162-图片23.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (41, 37, '/upload/goods/2/698c30fb-bfa0-486e-bda8-2c8463f235c7-图片24.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (42, 37, '/upload/goods/2/aa1d3f16-b401-4a06-a2ad-c1dd1a244b29-图片25.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (43, 38, '/upload/goods/7/0edfce83-6ee3-4286-834b-33b2e80c2266-图片26.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (44, 38, '/upload/goods/7/997e9751-ce03-4fff-b339-ef6163487c84-图片27.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (45, 39, '/upload/goods/6/340c6c55-117f-4612-a6be-57e336d76ad9-图片28.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (46, 39, '/upload/goods/6/30f87d4d-842d-48fa-ab38-dd7a7a8f622f-图片29.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (47, 39, '/upload/goods/6/c023501c-fb83-4b92-834b-2c933cd4f36f-图片30.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (48, 39, '/upload/goods/6/28715ca8-d7d8-477d-b925-1a6c58d884a1-图片31.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (49, 40, '/upload/goods/4/0b6bdc39-6300-4e77-bdc7-288431da1c2a-图片32.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (50, 40, '/upload/goods/4/4ffa2bac-f42e-45b5-bdf4-5b72c907a6ca-图片33.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (51, 41, '/upload/goods/4/15166a6e-cc11-4089-874e-d074212170ca-图片34.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (52, 41, '/upload/goods/4/5d54c2a2-a03a-4095-8712-a91af7882cea-图片35.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (53, 41, '/upload/goods/4/84ed076c-a13d-4dc5-848c-34fac992ba27-图片36.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (54, 41, '/upload/goods/4/535d269f-91e4-4e4e-9a72-5a7daf0adbba-图片37.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (55, 42, '/upload/goods/4/7ff4cb80-9089-407a-abb7-5d4a90794ae6-图片38.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (56, 43, '/upload/goods/4/d01fdfff-f018-423f-b5c3-47b34490b03b-图片39.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (57, 43, '/upload/goods/4/fd4a10b8-c755-439a-893d-1adf16abcb98-图片40.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (58, 44, '/upload/goods/4/1a4fd341-9f7b-4a7a-8b61-28d04b12598b-图片41.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (59, 45, '/upload/goods/4/37017e39-d252-486b-8117-18e324fc99ca-图片42.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (60, 46, '/upload/goods/4/81a30e6b-488e-438f-b5c2-304a1b7e1cbc-图片43.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (61, 46, '/upload/goods/4/19bfbca5-a91a-4543-98b3-7a4fe3000555-图片44.jpg', 2, 0);
INSERT INTO `t_goods_picture` VALUES (62, 47, '/upload/goods/7/8a1f19f6-dd42-4c3c-850c-776929e112af-QQ图片20221117151558.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (63, 48, '/upload/goods/7/282dda43-c9c5-464c-adf7-9a6fd6d6eb59-QQ图片20221117150336.jpg', 1, 0);
INSERT INTO `t_goods_picture` VALUES (64, 49, '/upload/goods/7/0ac092d9-d05e-4b87-bc6e-eb75d8443545-QQ图片20221117150336.jpg', 1, 0);

-- ----------------------------
-- Table structure for t_invitation
-- ----------------------------
DROP TABLE IF EXISTS `t_invitation`;
CREATE TABLE `t_invitation`  (
  `i_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '帖子id',
  `i_title` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '帖子标题',
  `i_details` longtext CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '帖子详情',
  `i_create_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '帖子创建时间',
  `i_read_count` int(0) NOT NULL DEFAULT 0 COMMENT '帖子阅读量',
  `u_id` int(0) NOT NULL COMMENT '创建帖子用户（外键）',
  `i_like_count` int(0) NOT NULL DEFAULT 0 COMMENT '帖子点赞量',
  `i_last_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '最后修改时间',
  `i_del` int(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `i_is_recommend` int(0) NULL DEFAULT 0 COMMENT '是否推荐',
  PRIMARY KEY (`i_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_invitation
-- ----------------------------
INSERT INTO `t_invitation` VALUES (1, '366666', '<p>ttttttttt</p>', '2022-10-19 10:12:37', 61, 2, 1, '2022-11-16 09:09:15', 1, 1);
INSERT INTO `t_invitation` VALUES (2, '你今天开心吗', '<p>详情</p>', '2022-11-10 18:43:11', 25, 2, 3, '2022-12-08 15:42:46', 1, 1);
INSERT INTO `t_invitation` VALUES (3, 'tttt', '<p>test</p>', '2022-11-10 20:04:24', 3, 2, 0, '2022-11-16 09:14:15', 1, 1);
INSERT INTO `t_invitation` VALUES (4, '应届生参加工作，什么事情越早知道越好？', '<ol><li>刚入职时不要表现得太沉默，要大方介绍自己，不然会给人距离感、陌生感，不利于日后交流，最好能想办法树立一个独特的良好的形象，尽量让同事、领导记住你，但是也不能太突出，惹人眼红。</li><li>稳重踏实、多干活少说话。除非你是性格特别开朗的，做事又特别麻利。</li></ol>', '2022-11-17 14:52:34', 22, 7, 0, '2022-12-07 18:13:31', 1, 1);
INSERT INTO `t_invitation` VALUES (5, '分享贴', '<p class=\"ql-align-center\">分享一些你喜欢的小众文案</p><p class=\"ql-align-center\"><img src=\"http://localhost:8081/upload/invitation/2/40aad23b-39ad-469f-9fd5-5ea235fcd109-QQ图片20221117145756.jpg\"></p>', '2022-11-17 14:59:28', 10, 2, 0, '2022-11-17 15:13:50', 0, 1);
INSERT INTO `t_invitation` VALUES (6, '作为00后你们会焦虑吗？', '<p><br></p><p><img src=\"http://localhost:8081/upload/invitation/7/e496f545-6a0c-4eaf-935a-cbc33d11d811-QQ图片20221117150336.jpg\"></p>', '2022-11-17 15:03:58', 7, 7, 0, '2022-11-17 15:03:58', 0, 0);
INSERT INTO `t_invitation` VALUES (7, 'come on', '<p>家人们家人们，老师让我们做ppt！我打算介绍最右app！快来帮帮我！我想要酷炫的那种！！！<img src=\"http://localhost:8081/upload/invitation/4/5d0f0eb5-c15f-410e-b096-eafb4396211a-QQ图片20221117150627.jpg\"></p>', '2022-11-17 15:06:37', 9, 4, 3, '2022-11-17 15:13:52', 0, 1);
INSERT INTO `t_invitation` VALUES (8, '帮帮孩子吧', '<p>昨晚睡觉了，忘记关电脑，结果第二天早上醒来就变成这样了，请问怎么办呢？求大哥帮帮忙，请你喝水<img src=\"http://localhost:8081/upload/invitation/2/ca826151-c790-4abf-a99f-77d2bf5cae05-QQ图片20221117151253.jpg\"></p>', '2022-11-17 15:13:29', 8, 2, 1, '2022-11-17 15:13:53', 0, 1);
INSERT INTO `t_invitation` VALUES (9, '卖崽', '<p><span style=\"color: rgb(0, 102, 204);\">今天去校门摆摊了，冲了15个青蛙全卖完了，卖得10块。冲2个猪猪没人买。因为充气筒坏了就只有15个。卖15没人要啊。10块就毫不犹豫买了。也赚了一半。这里都是爷爷奶奶接孩子比较舍不得。根据客人来定价。猪头该卖多少呢。明显10块没人要，今晚双十一去广场把剩的10个青蛙卖了。定15&nbsp;猪猪8块，看能不能请掉猪头，下次只拿青蛙。你们觉得猪猪8块好卖吗。进货价4块。青蛙5.2</span><img src=\"http://localhost:8081/upload/invitation/7/9d1007aa-9663-4d0c-b90e-f7bd9d7c08ed-QQ图片20221117151558.jpg\"></p>', '2022-11-17 15:21:18', 4, 7, 0, '2022-11-17 15:21:18', 0, 0);
INSERT INTO `t_invitation` VALUES (10, '我真服了，友情提示不要用透明碗喝水', '<p><br></p><p><img src=\"http://localhost:8081/upload/invitation/2/add6ddcd-a722-4686-9111-3cdb57ecb656-QQ图片20221117152507.jpg\"></p>', '2022-11-17 15:25:17', 0, 2, 0, '2022-11-17 15:25:17', 0, 0);
INSERT INTO `t_invitation` VALUES (11, '发士大夫', '<h1 class=\"ql-align-center\">撒对方</h1><p>法<span style=\"color: rgb(230, 0, 0);\">撒撒旦二个人</span></p><p>更好发挥</p><ol><li><span style=\"background-color: rgb(255, 255, 0);\">反对大师傅</span></li><li><span style=\"background-color: rgb(255, 255, 0);\">分隔符下</span></li></ol><p><img src=\"http://localhost:8081/upload/invitation/7/23b57c26-981f-44fb-809f-43818086c171-QQ图片20221117151605.jpg\"></p>', '2022-11-18 16:40:44', 0, 7, 0, '2022-11-18 16:40:44', 0, 0);

-- ----------------------------
-- Table structure for t_invitation_like
-- ----------------------------
DROP TABLE IF EXISTS `t_invitation_like`;
CREATE TABLE `t_invitation_like`  (
  `i_l_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '帖子点赞id',
  `i_id` int(0) NOT NULL COMMENT '帖子id',
  `u_id` int(0) NOT NULL COMMENT '点赞用户id',
  PRIMARY KEY (`i_l_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_invitation_like
-- ----------------------------
INSERT INTO `t_invitation_like` VALUES (1, 1, 2);
INSERT INTO `t_invitation_like` VALUES (2, 2, 1);
INSERT INTO `t_invitation_like` VALUES (3, 2, 3);
INSERT INTO `t_invitation_like` VALUES (4, 2, 2);
INSERT INTO `t_invitation_like` VALUES (5, 7, 5);
INSERT INTO `t_invitation_like` VALUES (6, 7, 8);
INSERT INTO `t_invitation_like` VALUES (7, 7, 7);
INSERT INTO `t_invitation_like` VALUES (8, 8, 7);

-- ----------------------------
-- Table structure for t_province
-- ----------------------------
DROP TABLE IF EXISTS `t_province`;
CREATE TABLE `t_province`  (
  `p_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '省份id',
  `p_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '省份名称',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_province
-- ----------------------------
INSERT INTO `t_province` VALUES (1, '北京市');
INSERT INTO `t_province` VALUES (2, '天津市');
INSERT INTO `t_province` VALUES (3, '河北省');
INSERT INTO `t_province` VALUES (4, '山西省');
INSERT INTO `t_province` VALUES (5, '内蒙古自治区');
INSERT INTO `t_province` VALUES (6, '辽宁省');
INSERT INTO `t_province` VALUES (7, '吉林省');
INSERT INTO `t_province` VALUES (8, '黑龙江省');
INSERT INTO `t_province` VALUES (9, '上海市');
INSERT INTO `t_province` VALUES (10, '江苏省');
INSERT INTO `t_province` VALUES (11, '浙江省');
INSERT INTO `t_province` VALUES (12, '安徽省');
INSERT INTO `t_province` VALUES (13, '福建省');
INSERT INTO `t_province` VALUES (14, '江西省');
INSERT INTO `t_province` VALUES (15, '山东省');
INSERT INTO `t_province` VALUES (16, '河南省');
INSERT INTO `t_province` VALUES (17, '湖北省');
INSERT INTO `t_province` VALUES (18, '湖南省');
INSERT INTO `t_province` VALUES (19, '广东省');
INSERT INTO `t_province` VALUES (20, '广西壮族自治区');
INSERT INTO `t_province` VALUES (21, '海南省');
INSERT INTO `t_province` VALUES (22, '重庆市');
INSERT INTO `t_province` VALUES (23, '四川省');
INSERT INTO `t_province` VALUES (24, '贵州省');
INSERT INTO `t_province` VALUES (25, '云南省');
INSERT INTO `t_province` VALUES (26, '西藏自治区');
INSERT INTO `t_province` VALUES (27, '陕西省');
INSERT INTO `t_province` VALUES (28, '甘肃省');
INSERT INTO `t_province` VALUES (29, '青海省');
INSERT INTO `t_province` VALUES (30, '宁夏回族自治区');
INSERT INTO `t_province` VALUES (31, '新疆维吾尔自治区');
INSERT INTO `t_province` VALUES (32, '台湾省');
INSERT INTO `t_province` VALUES (33, '香港特别行政区');
INSERT INTO `t_province` VALUES (34, '澳门特别行政区');

-- ----------------------------
-- Table structure for t_school
-- ----------------------------
DROP TABLE IF EXISTS `t_school`;
CREATE TABLE `t_school`  (
  `s_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '学校id',
  `s_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '学校名称',
  `c_id` int(0) NOT NULL COMMENT '城市id（外键）',
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 65 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_school
-- ----------------------------
INSERT INTO `t_school` VALUES (1, '湖南科技职业学院', 196);
INSERT INTO `t_school` VALUES (2, '长沙理工大学', 196);
INSERT INTO `t_school` VALUES (3, '湖南理工学院', 201);
INSERT INTO `t_school` VALUES (4, '北京农学院', 236);
INSERT INTO `t_school` VALUES (5, '中国民航大学', 237);
INSERT INTO `t_school` VALUES (6, '河北建筑工程学院', 7);
INSERT INTO `t_school` VALUES (7, '山西财经大学', 12);
INSERT INTO `t_school` VALUES (8, '四川大学', 251);
INSERT INTO `t_school` VALUES (9, '内蒙古大学', 381);
INSERT INTO `t_school` VALUES (10, '沈阳工业大学', 45);
INSERT INTO `t_school` VALUES (11, '吉林化工学院', 60);
INSERT INTO `t_school` VALUES (12, '长春理工大学', 59);
INSERT INTO `t_school` VALUES (13, '哈尔滨工业大学', 68);
INSERT INTO `t_school` VALUES (14, '上海交通大学', 234);
INSERT INTO `t_school` VALUES (15, '东南大学', 81);
INSERT INTO `t_school` VALUES (16, '浙江师范大学', 100);
INSERT INTO `t_school` VALUES (17, '中国科学技术大学', 105);
INSERT INTO `t_school` VALUES (18, '南昌航空大学', 133);
INSERT INTO `t_school` VALUES (19, '山东大学', 144);
INSERT INTO `t_school` VALUES (20, '郑州大学', 161);
INSERT INTO `t_school` VALUES (21, '武汉大学', 179);
INSERT INTO `t_school` VALUES (22, '华中科技大学', 179);
INSERT INTO `t_school` VALUES (23, '湖南大学', 196);
INSERT INTO `t_school` VALUES (24, '中山大学', 210);
INSERT INTO `t_school` VALUES (25, '广东海洋大学', 217);
INSERT INTO `t_school` VALUES (26, '南宁职业技术学院', 333);
INSERT INTO `t_school` VALUES (27, '华南热带海洋学院', 282);
INSERT INTO `t_school` VALUES (28, '重庆大学', 235);
INSERT INTO `t_school` VALUES (29, '遵义医科大学', 274);
INSERT INTO `t_school` VALUES (30, '昆明学院', 299);
INSERT INTO `t_school` VALUES (31, '西藏民族大学', 326);
INSERT INTO `t_school` VALUES (32, '西安交通大学城市学院', 323);
INSERT INTO `t_school` VALUES (33, '兰州大学', 231);
INSERT INTO `t_school` VALUES (34, '青海警官职业学院', 315);
INSERT INTO `t_school` VALUES (35, '青海民族大学', 315);
INSERT INTO `t_school` VALUES (36, '北方民族大学', 354);
INSERT INTO `t_school` VALUES (37, '新疆农业大学', 359);
INSERT INTO `t_school` VALUES (38, '中国人民大学', 236);
INSERT INTO `t_school` VALUES (39, '上海外国语大学', 234);
INSERT INTO `t_school` VALUES (40, '中南财经政法大学', 179);
INSERT INTO `t_school` VALUES (41, '南华大学', 199);
INSERT INTO `t_school` VALUES (42, '海口经济学院', 281);
INSERT INTO `t_school` VALUES (43, '长沙师范学院', 196);
INSERT INTO `t_school` VALUES (44, '中南大学', 196);
INSERT INTO `t_school` VALUES (45, '湖南中医药大学', 196);
INSERT INTO `t_school` VALUES (46, '中南林业科技大学', 196);
INSERT INTO `t_school` VALUES (47, '长沙医学院', 196);
INSERT INTO `t_school` VALUES (48, '长沙民政职业技术学院', 196);
INSERT INTO `t_school` VALUES (49, '湖南涉外经济学院', 196);
INSERT INTO `t_school` VALUES (50, '长沙航空职业技术学院', 196);
INSERT INTO `t_school` VALUES (51, '湖南第一师范学院', 196);
INSERT INTO `t_school` VALUES (52, '湖南女子学院', 196);
INSERT INTO `t_school` VALUES (53, '湖南警察学院', 196);
INSERT INTO `t_school` VALUES (54, '湖南农业大学东方科技学院', 196);
INSERT INTO `t_school` VALUES (55, '湖南交通职业技术学院', 196);
INSERT INTO `t_school` VALUES (56, '湖南体育职业学院', 196);
INSERT INTO `t_school` VALUES (57, '湖南师范大学树达学院', 196);
INSERT INTO `t_school` VALUES (64, 'test', 236);

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `u_id` int(0) NOT NULL AUTO_INCREMENT COMMENT '用户id',
  `u_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `u_password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户密码',
  `u_nickname` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `u_sex` int(0) NULL DEFAULT NULL COMMENT '用户性别',
  `u_register_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) COMMENT '用户注册时间',
  `u_email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户邮箱',
  `u_avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户头像存储地址',
  `s_id` int(0) NULL DEFAULT NULL COMMENT '用户所在学校（外键）',
  `u_del` int(0) NOT NULL DEFAULT 0 COMMENT '逻辑删除',
  `u_type` int(0) NOT NULL COMMENT '用户类型',
  `u_last_time` datetime(0) NOT NULL DEFAULT CURRENT_TIMESTAMP(0) ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '用户最后更新时间',
  PRIMARY KEY (`u_id`) USING BTREE,
  UNIQUE INDEX `unique_name`(`u_name`) USING BTREE,
  UNIQUE INDEX `unique_email`(`u_email`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'test', 'test', 'mnm', 1, '2022-10-03 15:05:23', '123', '/upload/avatar/common.webp', 1, 0, 1, '2022-11-11 20:02:51');
INSERT INTO `t_user` VALUES (2, 'test2', '123456', 'uuuu', 0, '2022-10-17 21:36:27', '12', '/upload/avatar/2/a98c0986-13a3-497e-ac18-ef18cc5e1a0f-hometopmobile.png', 1, 0, 0, '2022-11-17 09:35:09');
INSERT INTO `t_user` VALUES (4, 'tttt', '1111', 'ttttt', 0, '2022-11-03 15:51:19', '', '/upload/avatar/common.webp', 1, 0, 0, '2022-11-17 14:26:57');
INSERT INTO `t_user` VALUES (5, 'admin', 'admin', NULL, NULL, '2022-11-11 15:20:23', NULL, NULL, NULL, 0, 1, '2022-11-11 15:20:23');
INSERT INTO `t_user` VALUES (6, 'uuuuuu', '123456', 'uuuuu', 0, '2022-11-16 17:17:45', '1', '/upload/avatar/common.webp', 1, 0, 0, '2022-11-17 13:34:13');
INSERT INTO `t_user` VALUES (7, 'uniobh', 'uniobh', 'uniobh', 0, '2022-11-17 13:32:51', '2324934014@qq.com', '/upload/avatar/7/55ee45f2-3bb6-49b0-81dc-b728d5e46ea1-u=2301642508,281697021&fm=253&fmt=auto&app=138&f=JPEG.webp', 1, 0, 0, '2022-11-17 14:47:33');

SET FOREIGN_KEY_CHECKS = 1;
