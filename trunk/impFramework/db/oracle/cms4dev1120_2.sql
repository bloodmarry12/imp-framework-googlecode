insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION, SCOPE, ADDRESS)
values ('sysconfig.plateform.id', '��ƽ̨ID', '0', 'ƽ̨ID,��λƽ̨��š�', '-1', '0.0.0.0');

-- ����clustersϵͳ����
-- ��Ҫ��address�����޸�Ϊ��Ҫ������ϵͳ·��������ϵͳ�������޷����ظ�������
insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION, SCOPE, ADDRESS)
values ('imp.framework.clusters.type', '��Ⱥ����:����������', '0', '��Ⱥ���ã�0-SLAVE;1-MASTER', '-1', '0.0.0.0');
insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION, SCOPE, ADDRESS)
values ('imp.framework.clusters.type', '��Ⱥ����:MASTER�����ַ', '0', '��Ⱥ���ã�MASTER�����ַ', '-1', '0.0.0.0');

-- ���Ĭ���ʻ�
insert into T_FRAMEWORK_PRI_ACCOUNT (ID, NAME, PASSWORD, STATUS, OPERATORCONTACT, OPERATOR, ROLEID, LASTLOGINTIME, CREATETIME)
values (0, 'ahli', '4297f44b13955235245b2497399d7a93', 1, '13689076736', 'li.aohai', 3, to_date('19-11-2009 17:25:14', 'dd-mm-yyyy hh24:mi:ss'), null);
-- ���ϵͳ�����˵����ڵ�
insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (0, 'ϵͳ�����˵�', '', null, null, 0);
-- ���ϵͳ����Ȩ�޸��ڵ�
insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (0, 'p.cms4.root', 'CMSϵͳȨ��', null, 'ϵͳȨ�޸�Ŀ¼');
-- ���ϵͳ������ɫ
insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (3, 'IMP����Ա', 'ƽ̨ҵ��Ա');

-- ���붨ʱ������ܻ�������
insert into t_framework_schedule (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (2, '��������Ⱥ:SLAVE����������', '֪ͨMASTER����������ǰ������״̬', 1, '0/10 * * * * ?', 'clustersService', 'registRemote', 0, 'http://192.168.104.27:8080/imp4Framework', '');
insert into t_framework_schedule (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (1, 'HTTP��־���', 'HTTP��־��Ӧ�÷�����Ƕ�����ݿ���������ݿ�', 1, '0/10 * * * * ?', 'httpLoggerService', 'persistenceHttpLoggerEntity', 0, 'http://192.168.104.27:8080/imp4Framework', '1000');
insert into t_framework_schedule (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (3, 'IBUS������־���', 'IBUS������־��Ƕ��ʽ���ݿ⣬��⵽oracle���ݿ⣻', 1, '0/10 * * * * ?', 'ibusLogService', 'dataToDatabase', 0, 'http://192.168.104.27:8080/imp4Framework', '100');

commit;