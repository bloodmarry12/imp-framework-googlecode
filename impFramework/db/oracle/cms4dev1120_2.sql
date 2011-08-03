insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION, SCOPE, ADDRESS)
values ('sysconfig.plateform.id', '子平台ID', '0', '平台ID,四位平台编号。', '-1', '0.0.0.0');

-- 配置clusters系统基础
-- 需要将address参数修改为需要发布的系统路径，否则系统启动将无法加载该配置项
insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION, SCOPE, ADDRESS)
values ('imp.framework.clusters.type', '集群配置:服务器类型', '0', '集群配置：0-SLAVE;1-MASTER', '-1', '0.0.0.0');
insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION, SCOPE, ADDRESS)
values ('imp.framework.clusters.type', '集群配置:MASTER服务地址', '0', '集群配置：MASTER服务地址', '-1', '0.0.0.0');

-- 添加默认帐户
insert into T_FRAMEWORK_PRI_ACCOUNT (ID, NAME, PASSWORD, STATUS, OPERATORCONTACT, OPERATOR, ROLEID, LASTLOGINTIME, CREATETIME)
values (0, 'ahli', '4297f44b13955235245b2497399d7a93', 1, '13689076736', 'li.aohai', 3, to_date('19-11-2009 17:25:14', 'dd-mm-yyyy hh24:mi:ss'), null);
-- 添加系统基础菜单根节点
insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (0, '系统导航菜单', '', null, null, 0);
-- 添加系统基础权限根节点
insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (0, 'p.cms4.root', 'CMS系统权限', null, '系统权限根目录');
-- 添加系统基础角色
insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (3, 'IMP管理员', '平台业务员');

-- 插入定时任务表框架基础数据
insert into t_framework_schedule (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (2, '服务器集群:SLAVE服务器心跳', '通知MASTER服务器，当前服务器状态', 1, '0/10 * * * * ?', 'clustersService', 'registRemote', 0, 'http://192.168.104.27:8080/imp4Framework', '');
insert into t_framework_schedule (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (1, 'HTTP日志入库', 'HTTP日志从应用服务器嵌入数据库入基础数据库', 1, '0/10 * * * * ?', 'httpLoggerService', 'persistenceHttpLoggerEntity', 0, 'http://192.168.104.27:8080/imp4Framework', '1000');
insert into t_framework_schedule (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (3, 'IBUS交互日志入库', 'IBUS交互日志从嵌入式数据库，入库到oracle数据库；', 1, '0/10 * * * * ?', 'ibusLogService', 'dataToDatabase', 0, 'http://192.168.104.27:8080/imp4Framework', '100');

commit;