SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for wf_cc_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_cc_order`;
CREATE TABLE `wf_cc_order` (
  `order_Id` varchar(32) DEFAULT NULL COMMENT '流程实例ID',
  `actor_Id` varchar(50) DEFAULT NULL COMMENT '参与者ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '发起人',
  `create_Time` varchar(50) DEFAULT NULL COMMENT '抄送时间',
  `finish_Time` varchar(50) DEFAULT NULL COMMENT '完成时间',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  KEY `IDX_CCORDER_ORDER` (`order_Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='抄送实例表';

-- ----------------------------
-- Table structure for wf_hist_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_order`;
CREATE TABLE `wf_hist_order` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `process_Id` varchar(32) NOT NULL COMMENT '流程定义ID',
  `order_State` tinyint(1) NOT NULL COMMENT '状态',
  `creator` varchar(50) DEFAULT NULL COMMENT '发起人',
  `create_Time` varchar(50) NOT NULL COMMENT '发起时间',
  `end_Time` varchar(50) DEFAULT NULL COMMENT '完成时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '期望完成时间',
  `priority` tinyint(1) DEFAULT NULL COMMENT '优先级',
  `parent_Id` varchar(32) DEFAULT NULL COMMENT '父流程ID',
  `order_No` varchar(50) DEFAULT NULL COMMENT '流程实例编号',
  `variable` varchar(2000) DEFAULT NULL COMMENT '附属变量json存储',
  PRIMARY KEY (`id`),
  KEY `IDX_HIST_ORDER_PROCESSID` (`process_Id`),
  KEY `IDX_HIST_ORDER_NO` (`order_No`),
  KEY `FK_HIST_ORDER_PARENTID` (`parent_Id`),
  CONSTRAINT `FK_HIST_ORDER_PROCESSID` FOREIGN KEY (`process_Id`) REFERENCES `wf_process` (`id`),
  CONSTRAINT `FK_HIST_ORDER_PARENTID` FOREIGN KEY (`parent_Id`) REFERENCES `wf_hist_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史流程实例表';

-- ----------------------------
-- Table structure for wf_hist_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task`;
CREATE TABLE `wf_hist_task` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `order_Id` varchar(32) NOT NULL COMMENT '流程实例ID',
  `task_Name` varchar(100) NOT NULL COMMENT '任务名称',
  `display_Name` varchar(200) NOT NULL COMMENT '任务显示名称',
  `task_Type` tinyint(1) NOT NULL COMMENT '任务类型',
  `perform_Type` tinyint(1) DEFAULT NULL COMMENT '参与类型',
  `task_State` tinyint(1) NOT NULL COMMENT '任务状态',
  `operator` varchar(50) DEFAULT NULL COMMENT '任务处理人',
  `create_Time` varchar(50) NOT NULL COMMENT '任务创建时间',
  `finish_Time` varchar(50) DEFAULT NULL COMMENT '任务完成时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '任务期望完成时间',
  `action_Url` varchar(200) DEFAULT NULL COMMENT '任务处理url',
  `parent_Task_Id` varchar(32) DEFAULT NULL COMMENT '父任务ID',
  `variable` varchar(2000) DEFAULT NULL COMMENT '附属变量json存储',
  PRIMARY KEY (`id`),
  KEY `IDX_HIST_TASK_ORDER` (`order_Id`),
  KEY `IDX_HIST_TASK_TASKNAME` (`task_Name`),
  KEY `IDX_HIST_TASK_PARENTTASK` (`parent_Task_Id`),
  CONSTRAINT `FK_HIST_TASK_ORDERID` FOREIGN KEY (`order_Id`) REFERENCES `wf_hist_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史任务表';

-- ----------------------------
-- Table structure for wf_hist_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_hist_task_actor`;
CREATE TABLE `wf_hist_task_actor` (
  `task_Id` varchar(32) NOT NULL COMMENT '任务ID',
  `actor_Id` varchar(50) NOT NULL COMMENT '参与者ID',
  KEY `IDX_HIST_TASKACTOR_TASK` (`task_Id`),
  CONSTRAINT `FK_HIST_TASKACTOR` FOREIGN KEY (`task_Id`) REFERENCES `wf_hist_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='历史任务参与者表';

-- ----------------------------
-- Table structure for wf_order
-- ----------------------------
DROP TABLE IF EXISTS `wf_order`;
CREATE TABLE `wf_order` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `parent_Id` varchar(32) DEFAULT NULL COMMENT '父流程ID',
  `process_Id` varchar(32) NOT NULL COMMENT '流程定义ID',
  `creator` varchar(50) DEFAULT NULL COMMENT '发起人',
  `create_Time` varchar(50) NOT NULL COMMENT '发起时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '期望完成时间',
  `last_Update_Time` varchar(50) DEFAULT NULL COMMENT '上次更新时间',
  `last_Updator` varchar(50) DEFAULT NULL COMMENT '上次更新人',
  `priority` tinyint(1) DEFAULT NULL COMMENT '优先级',
  `parent_Node_Name` varchar(100) DEFAULT NULL COMMENT '父流程依赖的节点名称',
  `order_No` varchar(50) DEFAULT NULL COMMENT '流程实例编号',
  `variable` varchar(2000) DEFAULT NULL COMMENT '附属变量json存储',
  `version` int(3) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `IDX_ORDER_PROCESSID` (`process_Id`),
  KEY `IDX_ORDER_NO` (`order_No`),
  KEY `FK_ORDER_PARENTID` (`parent_Id`),
  CONSTRAINT `FK_ORDER_PROCESSID` FOREIGN KEY (`process_Id`) REFERENCES `wf_process` (`id`),
  CONSTRAINT `FK_ORDER_PARENTID` FOREIGN KEY (`parent_Id`) REFERENCES `wf_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程实例表';

-- ----------------------------
-- Table structure for wf_process
-- ----------------------------
DROP TABLE IF EXISTS `wf_process`;
CREATE TABLE `wf_process` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `name` varchar(100) DEFAULT NULL COMMENT '流程名称',
  `display_Name` varchar(200) DEFAULT NULL COMMENT '流程显示名称',
  `type` varchar(100) DEFAULT NULL COMMENT '流程类型',
  `instance_Url` varchar(200) DEFAULT NULL COMMENT '实例url',
  `state` tinyint(1) DEFAULT NULL COMMENT '流程是否可用',
  `content` longblob COMMENT '流程模型定义',
  `version` int(2) DEFAULT NULL COMMENT '版本',
  `create_Time` varchar(50) DEFAULT NULL COMMENT '创建时间',
  `creator` varchar(50) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`),
  KEY `IDX_PROCESS_NAME` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='流程定义表';

-- ----------------------------
-- Table structure for wf_surrogate
-- ----------------------------
DROP TABLE IF EXISTS `wf_surrogate`;
CREATE TABLE `wf_surrogate` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `process_Name` varchar(100) DEFAULT NULL COMMENT '流程名称',
  `operator` varchar(50) DEFAULT NULL COMMENT '授权人',
  `surrogate` varchar(50) DEFAULT NULL COMMENT '代理人',
  `odate` varchar(64) DEFAULT NULL COMMENT '操作时间',
  `sdate` varchar(64) DEFAULT NULL COMMENT '开始时间',
  `edate` varchar(64) DEFAULT NULL COMMENT '结束时间',
  `state` tinyint(1) DEFAULT NULL COMMENT '状态',
  PRIMARY KEY (`id`),
  KEY `IDX_SURROGATE_OPERATOR` (`operator`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='委托代理表';

-- ----------------------------
-- Table structure for wf_task
-- ----------------------------
DROP TABLE IF EXISTS `wf_task`;
CREATE TABLE `wf_task` (
  `id` varchar(32) NOT NULL COMMENT '主键ID',
  `order_Id` varchar(32) NOT NULL COMMENT '流程实例ID',
  `task_Name` varchar(100) NOT NULL COMMENT '任务名称',
  `display_Name` varchar(200) NOT NULL COMMENT '任务显示名称',
  `task_Type` tinyint(1) NOT NULL COMMENT '任务类型',
  `perform_Type` tinyint(1) DEFAULT NULL COMMENT '参与类型',
  `operator` varchar(50) DEFAULT NULL COMMENT '任务处理人',
  `create_Time` varchar(50) DEFAULT NULL COMMENT '任务创建时间',
  `finish_Time` varchar(50) DEFAULT NULL COMMENT '任务完成时间',
  `expire_Time` varchar(50) DEFAULT NULL COMMENT '任务期望完成时间',
  `action_Url` varchar(200) DEFAULT NULL COMMENT '任务处理的url',
  `parent_Task_Id` varchar(32) DEFAULT NULL COMMENT '父任务ID',
  `variable` varchar(2000) DEFAULT NULL COMMENT '附属变量json存储',
  `version` tinyint(1) DEFAULT NULL COMMENT '版本',
  PRIMARY KEY (`id`),
  KEY `IDX_TASK_ORDER` (`order_Id`),
  KEY `IDX_TASK_TASKNAME` (`task_Name`),
  KEY `IDX_TASK_PARENTTASK` (`parent_Task_Id`),
  CONSTRAINT `FK_TASK_ORDERID` FOREIGN KEY (`order_Id`) REFERENCES `wf_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务表';

-- ----------------------------
-- Table structure for wf_task_actor
-- ----------------------------
DROP TABLE IF EXISTS `wf_task_actor`;
CREATE TABLE `wf_task_actor` (
  `task_Id` varchar(32) NOT NULL COMMENT '任务ID',
  `actor_Id` varchar(50) NOT NULL COMMENT '参与者ID',
  KEY `IDX_TASKACTOR_TASK` (`task_Id`),
  CONSTRAINT `FK_TASK_ACTOR_TASKID` FOREIGN KEY (`task_Id`) REFERENCES `wf_task` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='任务参与者表';
SET FOREIGN_KEY_CHECKS=1;
