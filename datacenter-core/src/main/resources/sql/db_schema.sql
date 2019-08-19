/**
 * Table: dc_sys_user 系统用户表
 */
CREATE TABLE `dc_sys_user` (
   `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT 'id',
   `user_name` varchar(32) DEFAULT NULL COMMENT '用户名',
   `password` varchar(128) DEFAULT NULL COMMENT '密码',
   `real_name` varchar(30) DEFAULT NULL COMMENT '真实姓名',
   `mobile` varchar(32) DEFAULT NULL COMMENT '手机号码',
   `email` varchar(64) DEFAULT NULL COMMENT '邮箱地址',
   `department_id` bigint(20) DEFAULT NULL COMMENT '所属部门id',
   `department_name` varchar(64) DEFAULT NULL COMMENT '所属部门名称',
   `status` tinyint(4) DEFAULT '0' COMMENT '用户状态（0-正常,1-删除,2-未激活,3-禁用,4-冻结）',
   `remark` varchar(500) DEFAULT NULL COMMENT '备注',
   `last_login_time` bigint(20) DEFAULT NULL COMMENT '最后登录时间',
   `last_login_ip` varchar(30) DEFAULT NULL COMMENT '最后登录IP',
   `created_by` varchar(32) DEFAULT NULL COMMENT '创建人',
   `created_time` bigint(20) DEFAULT NULL COMMENT '创建时间',
   `last_modified_by` varchar(32) DEFAULT NULL COMMENT '最后修改人',
   `last_modified_time` bigint(20) DEFAULT NULL COMMENT '最后修改时间',
   PRIMARY KEY (`id`)
 ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 CHECKSUM=1 DELAY_KEY_WRITE=1 ROW_FORMAT=DYNAMIC COMMENT='系统用户';