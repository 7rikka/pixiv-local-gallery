SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for illust
-- ----------------------------
DROP TABLE IF EXISTS `illust`;
CREATE TABLE `illust`  (
                           `id` int UNSIGNED NOT NULL,
                           `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                           `type` int NULL DEFAULT NULL,
                           `description` text CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                           `restrict` int NULL DEFAULT NULL,
                           `x_restrict` int NULL DEFAULT NULL,
                           `user_id` int NULL DEFAULT NULL,
                           `create_date` datetime NULL DEFAULT NULL,
                           `upload_date` datetime NULL DEFAULT NULL,
                           `page_count` int NULL DEFAULT NULL,
                           `width` int NULL DEFAULT NULL,
                           `height` int NULL DEFAULT NULL,
                           `sanity_level` int NULL DEFAULT NULL,
                           `view_count` int NULL DEFAULT NULL,
                           `bookmark_count` int NULL DEFAULT NULL,
                           `visible` int NULL DEFAULT NULL,
                           `comment_count` int NULL DEFAULT NULL,
                           `like_count` int NULL DEFAULT NULL,
                           `ai_type` int NULL DEFAULT NULL,
                           `illust_book_style` int NULL DEFAULT NULL,
                           `series_id` int NULL DEFAULT NULL,
                           `comment_off` int NULL DEFAULT NULL,
                           `create_time` datetime NULL DEFAULT NULL,
                           `update_time` datetime NULL DEFAULT NULL,
                           `app_state` int NULL DEFAULT NULL,
                           `web_state` int NULL DEFAULT NULL,
                           `app_raw` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                           `web_raw` mediumtext CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for image_url_detail
-- ----------------------------
DROP TABLE IF EXISTS `image_url_detail`;
CREATE TABLE `image_url_detail`  (
                                     `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
                                     `illust_id` int NULL DEFAULT NULL,
                                     `index` int NULL DEFAULT NULL,
                                     `type` int NULL DEFAULT NULL,
                                     `url` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                     `size` int NULL DEFAULT NULL,
                                     `height` int NULL DEFAULT NULL,
                                     `width` int NULL DEFAULT NULL,
                                     `sha1` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                                     `state` int NULL DEFAULT NULL,
                                     PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 331357 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for series
-- ----------------------------
DROP TABLE IF EXISTS `series`;
CREATE TABLE `series`  (
                           `id` int UNSIGNED NOT NULL,
                           `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                           PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for tag
-- ----------------------------
DROP TABLE IF EXISTS `tag`;
CREATE TABLE `tag`  (
                        `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
                        `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_as_cs NULL DEFAULT NULL,
                        PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 158129 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tag_relation
-- ----------------------------
DROP TABLE IF EXISTS `tag_relation`;
CREATE TABLE `tag_relation`  (
                                 `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
                                 `illust_id` int NULL DEFAULT NULL,
                                 `tag_id` int NULL DEFAULT NULL,
                                 PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1264061 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for tool
-- ----------------------------
DROP TABLE IF EXISTS `tool`;
CREATE TABLE `tool`  (
                         `id` int UNSIGNED NOT NULL AUTO_INCREMENT,
                         `illust_id` int NULL DEFAULT NULL,
                         `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
                         `id` int UNSIGNED NOT NULL,
                         `name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                         `account` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                         `medium_profile_image_urls` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
                         PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
