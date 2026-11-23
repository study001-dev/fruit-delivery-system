-- 创建骑手表
CREATE TABLE IF NOT EXISTS `rider` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '骑手ID',
  `username` varchar(255) NOT NULL COMMENT '用户名',
  `password` varchar(255) NOT NULL COMMENT '密码',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `id_card` varchar(20) NOT NULL COMMENT '身份证号',
  `avatar` varchar(255) DEFAULT NULL COMMENT '头像',
  `status` int(11) NOT NULL DEFAULT 0 COMMENT '状态（0 禁用 / 1 启用）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='骑手';

-- 为订单表添加骑手相关字段
ALTER TABLE `orders` ADD COLUMN IF NOT EXISTS `rider_id` bigint(20) DEFAULT NULL COMMENT '骑手ID';
ALTER TABLE `orders` ADD COLUMN IF NOT EXISTS `rider_name` varchar(255) DEFAULT NULL COMMENT '骑手姓名';
