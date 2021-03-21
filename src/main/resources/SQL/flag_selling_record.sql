create table flag_selling_record(
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
	`record_id` varchar(32) NOT NULL DEFAULT '' COMMENT '售卖记录ID',
	`red_flag_num` bigint(20) NOT NULL DEFAULT 0 COMMENT '红色导标棋数量',
    `green_flag_num` bigint(20) NOT NULL DEFAULT 0 COMMENT '绿色导标棋数量',
    `yellow_flag_num` bigint(20) NOT NULL DEFAULT 0 COMMENT '黄色导标棋数量',
    `white_flag_num` bigint(20) NOT NULL DEFAULT 0 COMMENT '白色导标棋数量',
    `blue_flag_num` bigint(20) NOT NULL DEFAULT 0 COMMENT '蓝色导标棋数量',
    `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
	`update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
	PRIMARY KEY(`id`),
	INDEX `index_create_time`(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='导标棋售卖记录表';