insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION, SCOPE, ADDRESS)
values ('sysconfig.plateform.id', '子平台ID', '0', '平台ID,四位平台编号。', '-1', '0.0.0.0');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION, SCOPE, ADDRESS)
values ('imp.framework.clusters.type', '集群配置:服务器类型', '0', '集群配置：0-SLAVE;1-MASTER', '-1', '0.0.0.0');



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
VALUES ('8', 'testscript', '测试脚本', '0', '0 0/1 * * * ?', 'ScriptExecutManagerFacade', 'executeScript', '0', null, 'test|js');

INSERT INTO "t_framework_script" VALUES ('test', 'test', 'println(''fuck you in jsscript engin.'');', '2011-8-2');

commit;