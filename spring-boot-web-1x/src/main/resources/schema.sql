SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_j_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_j_user`;
CREATE TABLE `sys_j_user` (
                              `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
                              `user_code` varchar(255) DEFAULT NULL,
                              `user_name` varchar(255) DEFAULT NULL,
                              PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
                         `user_id` bigint(20) NOT NULL AUTO_INCREMENT,
                         `user_code` varchar(255) DEFAULT NULL,
                         `user_name` varchar(255) DEFAULT NULL,
                         PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;
SET FOREIGN_KEY_CHECKS=1;
