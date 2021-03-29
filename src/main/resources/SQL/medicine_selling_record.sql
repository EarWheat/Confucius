create table medicine_selling_record(
	`id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
	`record_id` varchar(32) NOT NULL DEFAULT '' COMMENT '售卖记录ID',
	`num` bigint(20) NOT NULL DEFAULT 0 COMMENT '三药贩卖数量',
    `create_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '创建时间',
	`update_time` datetime NOT NULL DEFAULT '1971-01-01 00:00:00' COMMENT '更新时间',
	PRIMARY KEY(`id`),
	INDEX `index_create_time`(create_time)
)ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='三药售卖记录表';