alter table T_FRAMEWORK_PRI_EXPLORER disable constraint  FK_FRAMEWORK_PRI_EXPLORER;
alter table T_FRAMEWORK_PRI_EXPLORER disable constraint  FK_FRAMEWORK_PRI_EXPLORER_R;

insert into t_framework_constant (ID, VALUE, NAME, SEQ, MODELID)
values (147, '1', '�в���ʱ��,����', 2, 24);

insert into t_framework_constant (ID, VALUE, NAME, SEQ, MODELID)
values (146, '0', 'û�в���ʱ��', 1, 24);

insert into t_framework_constant (ID, VALUE, NAME, SEQ, MODELID)
values (148, '2', '�в���ʱ��,�ɲ���', 3, 24);



insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.plateform.id', 'CMS��ƽ̨ID', '0', 'ƽ̨ID,��λƽ̨��š�');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendRequest.pids', '����ͬ��:���������ƽ̨', '0', '����ͬ�������ƽ̨����","���ŷָ����ƽ̨ID��');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendResponse.pids', '����ͬ��:���ͻ�ִ��ƽ̨', '0', '����ͬ����ִ��ƽ̨����","���ŷָ����ƽ̨ID��');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendRequest.size', '����ͬ��:����������', '500', 'ƽ̨����ͬ����һ����������������');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendResponse.size', '����ͬ��:���ͻ�ִ��', '500', 'ƽ̨����ͬ����һ�����ݴ�����������');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendRequest.handle', '����ͬ��:����������', '200', '����ɹ���һ����������������');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendResponse.handle', '����ͬ��:�����ִ��', '100', '�ɹ����ջ�ִ��һ����������������');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('imp.copyright.import.ftp', '��Ȩ�����ϴ�·��', '/CMSDEMO/COPYRIGHT', '��Ȩ�����ϴ���·��');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('imp.tone.import.ftp', '���������ϴ�·��', '/TONE', '���������ϴ�·��');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('imp.tone.batchimport.count', '������������ģ���еļ�¼��', '10', '������������ģ���еļ�¼��');

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (1, '0', '����', 0, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (2, '1', '����', 1, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (3, '2', 'ҡ��', 2, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (4, '3', '��ʿ', 3, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (5, '4', '������', 4, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (6, '5', '����', 5, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (7, '6', 'Ӱ��ԭ��', 6, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (8, '7', '����', 7, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (9, '8', '�ŵ�', 8, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (10, '9', '���', 9, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (11, '10', '����', 10, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (12, '11', 'ŷ������', 11, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (13, '12', 'ԭ��', 12, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (14, '13', '����', 13, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (15, '14', '���ո�', 14, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (16, '15', '��������', 15, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (17, '0', '�����и���', 0, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (18, '1', '����Ů����', 1, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (19, '2', '�������', 2, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (20, '3', 'ŷ���и���', 3, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (21, '4', 'ŷ��Ů����', 4, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (22, '5', 'ŷ�����', 5, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (23, '6', '�պ��и���', 6, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (24, '7', '�պ�Ů����', 7, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (25, '8', '�պ����', 8, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (26, '0', '����', 0, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (27, '1', 'Ӣ��', 1, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (28, '2', '����', 2, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (29, '3', '����', 3, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (30, '4', '����', 4, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (31, '5', '��������', 5, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (32, '6', '����', 6, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (33, '7', '������', 7, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (34, '8', '������', 8, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (35, '9', '��������', 9, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (36, '10', '����', 10, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (37, '11', '��������', 11, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (38, '12', '����', 12, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (39, '13', '��������', 13, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (40, '0', '�ڵ�', 0, 4);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (41, '1', '��̨', 1, 4);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (42, '2', 'ŷ��', 2, 4);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (43, '3', '�պ�', 3, 4);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (44, '4', '����', 4, 4);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (45, '0', '�ݳ���', 0, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (46, '1', '���ѻ�', 1, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (47, '2', '����MV', 2, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (48, '3', '���Ƿ�̸', 3, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (49, '4', '��Ц����', 4, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (50, '5', '��������Ƶ', 5, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (51, '6', '�����Ƶ', 6, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (52, '7', 'ר��������', 7, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (53, '8', '��ӰƬ�Ρ�������������', 8, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (54, '9', '���Ӿ�Ƭ�Ρ�������������', 9, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (55, '10', '����', 10, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (56, '0', '����', 0, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (57, '1', '��ͷ��', 1, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (58, '2', 'MV��ͼ', 2, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (59, '3', '��������ͼ', 3, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (60, '4', '�羰����ͼ', 4, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (61, '5', '��������ͼ', 5, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (62, '6', '����д����', 6, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (63, '7', 'ר������������', 7, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (64, '8', '���ֻ�Ӱ�Ӻ���������', 8, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (65, '9', '���ֻ�Ӱ����־������', 9, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (66, '10', 'Ӱ����̨�ݳ����ֳ�����', 10, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (67, '11', '¼����������ֳ�������', 11, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (68, '12', '����', 12, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (69, '0', '����', 1, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (70, '1', '�޸�', 2, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (71, '2', '����', 3, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (72, '3', 'Ԥ��', 4, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (73, '4', '����', 5, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (74, '5', '��������', 6, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (75, '0', '����ʧ��', 1, 8);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (76, '1', '�����ɹ�', 2, 8);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (77, '0', '��DIY����', 1, 9);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (78, '1', 'DIY����', 2, 9);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (79, '0', '��ͨ����', 1, 10);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (80, '1', '���ֿ�', 2, 10);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (81, '2', '�߼���Ա�������', 3, 10);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (82, '3', '��drmȫ��', 4, 10);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (83, '0', 'δ�ַ�', 1, 11);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (84, '1', '�ѷַ�', 2, 11);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (85, '0', '��ͨ����', 1, 12);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (86, '1', '��ͨ��Ա����', 2, 12);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (87, '2', '�߼���Ա����', 3, 12);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (88, '3', 'vip��Ա����', 4, 12);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (89, '4', '����ר������', 5, 12);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (90, '0', '�Ȳ���Ƶ', 1, 13);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (91, '1', '��ָ���Ƶ', 2, 13);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (92, '0', '��', 1, 14);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (93, '1', '��', 2, 14);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (94, '2', '��', 3, 14);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (95, '0', '176*144', 1, 15);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (96, '1', '352*288', 2, 15);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (97, '0', '����Ļ', 1, 16);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (98, '1', '��������', 2, 16);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (99, '2', '��������', 3, 16);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (100, '3', 'Ӣ��', 4, 16);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (101, '0', '����Ƶ����Ƶ��ý���ļ�', 1, 17);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (102, '1', '����Ƶ����Ƶ��ý���ļ�', 2, 17);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (103, '0', 'ԭ��һ', 1, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (104, '1', 'ԭ���', 2, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (105, '2', 'ԭ����', 3, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (106, '3', 'ԭ����', 4, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (107, '4', 'ԭ����', 5, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (108, '5', 'ԭ����', 6, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (109, '6', 'ԭ����', 7, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (110, '7', 'ԭ���', 8, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (111, '8', 'ԭ���', 9, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (112, '9', 'ԭ��ʮ', 10, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (113, '10', 'ԭ��ʮһ', 11, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (114, '11', 'ԭ��ʮ��', 12, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (115, '6', 'Ԥ��Ч����', 7, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (116, '7', '�ַ�', 8, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (117, '8', '����', 9, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (118, '9', '�ָ�', 10, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (119, '0', '�����ܾ�ԭ��һ', 1, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (120, '1', '�����ܾ�ԭ���', 2, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (121, '2', '�����ܾ�ԭ����', 3, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (122, '3', '�����ܾ�ԭ����', 4, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (123, '4', '�����ܾ�ԭ����', 5, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (124, '5', '�����ܾ�ԭ����', 6, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (125, '6', '�����ܾ�ԭ����', 7, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (126, '0', '��', 0, 20);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (127, '1', '��', 1, 20);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (128, '10', '��������', 11, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (129, '11', '�������', 12, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (130, '12', '�޸�����', 13, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (134, '0', '��Ʒ', 1, 22);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (131, '0', '�޸�ǰ', 0, 21);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (132, '1', '�޸ĺ�', 1, 21);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (133, '13', '�����������', 14, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (135, '1', '����', 2, 22);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (137, '1', '��ͨ��', 1, 23);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (138, '2', '�����', 2, 23);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (139, '3', '�߳���', 3, 23);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (140, '4', '����', 4, 23);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (141, '5', 'ţA��ţC֮��', 5, 23);

insert into T_FRAMEWORK_FTP (FTPALIAS, USERNAME, USERPASSWORD, IP, PORT, DEFAULTPATH)
values ('COPYRIGHT_CONTENT', 'imp', 'dev1234', '192.168.104.51', 21, '/CMSDEMO/COPYRIGHT');

insert into T_FRAMEWORK_PRI_ACCOUNT (ID, NAME, PASSWORD, STATUS, OPERATORCONTACT, OPERATOR, ROLEID, LASTLOGINTIME, CREATETIME)
values (32, 'testCP', '4297f44b13955235245b2497399d7a93', 1, '12345678901', '������CP�˻�', 0, to_date('24-09-2009 11:26:08', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into T_FRAMEWORK_PRI_ACCOUNT (ID, NAME, PASSWORD, STATUS, OPERATORCONTACT, OPERATOR, ROLEID, LASTLOGINTIME, CREATETIME)
values (2, 'admin', '4297f44b13955235245b2497399d7a93', 1, '15928085969', 'admin', 3, to_date('24-09-2009 11:28:51', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into T_FRAMEWORK_PRI_ACCOUNT (ID, NAME, PASSWORD, STATUS, OPERATORCONTACT, OPERATOR, ROLEID, LASTLOGINTIME, CREATETIME)
values (33, 'testSP', '4297f44b13955235245b2497399d7a93', 1, '12312312312312', '�����������������˻�', 2, to_date('24-09-2009 11:28:28', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into T_FRAMEWORK_PRI_ACCOUNT (ID, NAME, PASSWORD, STATUS, OPERATORCONTACT, OPERATOR, ROLEID, LASTLOGINTIME, CREATETIME)
values (34, 'testMCP', '4297f44b13955235245b2497399d7a93', 1, '12312312312', '������MCP�˻�', 4, to_date('24-09-2009 11:25:01', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (32, 10);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (32, 7);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (32, 13);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (32, 12);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (32, 0);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (32, 36);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (32, 9);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (32, 6);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (32, 29);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (32, 30);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (32, 11);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (33, 0);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (33, 58);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (33, 57);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (33, 6);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (33, 56);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (33, 50);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (33, 8);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (33, 55);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 2);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 45);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 53);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 17);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 25);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 51);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 1);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 26);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 18);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 24);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 22);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 43);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 52);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 5);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 44);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 42);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 0);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (2, 23);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 13);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 10);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 28);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 32);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 9);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 5);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 25);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 29);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 11);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 27);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 12);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 6);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 38);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 37);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 0);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 7);

insert into T_FRAMEWORK_PRI_ACCOUNT_RIGHT (ACCOUNTID, RIGHTID)
values (34, 17);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (0, 'ϵͳ�����˵�', '', null, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (6, '�˻�����', '', 0, null, 9);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (1, '��������ƽ̨', '', 0, 6, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (2, '�ۺϹ���ƽ̨', '', 0, 5, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (3, '������ƽ̨', '', 0, null, 3);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (4, '�ַ�ƽ̨', '', 0, null, 4);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (5, '���ù���ƽ̨', '', 0, 1, 5);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (44, '��Ȩ����', '', 1, 7, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (45, '������Ȩ����', 'copyright/batch/cpImport.do', 44, 30, 3);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (52, '������Ȩ����', '', 44, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (57, 'CP/SP ID����', '', 5, null, 5);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (141, 'ͼƬ����', 'copyright/pictureCreate.do', 52, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (142, '��Ʒ�����б�', 'toneOrder/listUnmadeToneOrder.do', 63, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (38, 'Ȩ�޹���', '', 5, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (37, '�˻�����', '', 5, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (40, '����CP�˻�', 'console/cum/account/listCP4Approval.do', 37, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (42, '�½��˺�', 'console/cum/account/createCPAccount.do', 37, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (39, '�鿴�����˻�', 'console/cum/account/listAll.do', 37, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (43, '��ɫ����', 'console/cum/role/listRole.do', 38, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (41, '�˺�Ȩ�޹���', 'console/cum/right/listAccount.do', 38, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (140, 'Ԥ��Ч���ݲ�ѯ', 'preEffective/listCopyright.do', 115, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (143, '�����������', 'tone/listAuditing.do', 63, null, 6);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (60, '��Ȩ��ѯ', 'copyright/list.do', 44, null, -1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (65, '��Ȩ�������', '', 2, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (63, '��������', '', 1, 8, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (66, '�����������', '', 2, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (67, '��Ȩ��ѯ', 'copyright/list.do', 65, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (144, '�����ʽ����', 'toneFileType/list.do?toneType=0', 118, null, 4);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (145, '�����ʽ����', 'toneFileType/list.do?toneType=1', 118, null, 5);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (152, 'MV�ļ���ʽ����', 'toneFileType/list.do?toneType=6', 118, null, 10);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (153, '������Ȩ��ѯ', 'copyright/listTrashCopyright.do', 65, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (154, 'MV����', 'copyright/mvCreate.do', 52, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (155, '��Ȩ�����ַ�', 'copyright/butchAddOrder.do', 65, null, 3);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (146, 'ȫ����ʽ����', 'toneFileType/list.do?toneType=2', 118, null, 6);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (147, '��ý���ʽ����', 'toneFileType/list.do?toneType=3', 118, null, 7);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (80, '�����й���', '', 2, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (148, '������������ʽ����', 'toneFileType/list.do?toneType=4', 118, null, 8);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (149, '����CPID', 'console/cum/account/createCPID.do', 57, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (150, 'ͼƬ��ʽ����', 'toneFileType/list.do?toneType=5', 118, null, 9);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (151, '��Ŀ��ѯ', 'copyright/listPreEffectiveProject.do', 44, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (87, '��Ʒ��ѯ', 'tone/list.do', 66, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (88, '�����������', 'tone/listAuditing.do', 66, null, 6);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (94, '�½�SPID', 'console/cum/account/createSPID.do', 57, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (118, '������ʽ����', '', 2, null, 3);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (121, '������Ȩ����', 'copyright/batch/mcpImport.do', 44, 32, 8);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (115, 'Ԥ��Ч��������', '', 1, 27, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (105, '�鿴����CPID', 'console/cum/account/listCPID.do', 57, null, 3);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (106, '������ѯ', 'tone/list.do', 63, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (108, '�鿴����SPID', 'console/cum/account/listSPID.do', 57, null, 4);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (117, 'Ԥ��Ч��Ŀ���', 'copyright/listPreEffectiveProject.do', 115, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (124, '������ʽ��ѯ', 'tone/listNoFormatter.do', 63, null, 6);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (136, '��������', 'copyright/musicCreate.do', 52, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (139, '����Ԥ��Ч����', 'preEffective/add.do', 115, null, 0);

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (0, 'p.cms4.root', 'CMSϵͳȨ��', null, 'ϵͳȨ�޸�Ŀ¼');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (1, 'pt.config', '���ù���ƽ̨', 0, '���ù���ƽ̨Ȩ��');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (2, 'pt.config.accountManagement', '�˻�������', 1, '�˻��������˵�');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (5, 'pt.bizzMng', '�ۺϹ���ƽ̨', 0, '�ۺϹ���ƽ̨');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (6, 'pt.content.import', '��������ƽ̨', 0, '');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (7, 'pt.content.import.copyright', '��Ȩ����˵�', 6, '');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (8, 'pt.content.import.tone', '��������˵�', 6, '��������˵�');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (9, 'pt.content.import.release', 'CP������Ȩ��ť', 7, 'CP������Ȩ��ť');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (10, 'pt.content.import.requestchange', 'CP�����Ȩ���', 7, 'CP�����Ȩ���');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (11, 'pt.content.import.update', 'CP�޸İ�Ȩ', 7, 'CP�޸İ�Ȩ');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (12, 'pt.content.import.delete', 'CPɾ����Ȩ', 7, 'CPɾ����Ȩ');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (13, 'pt.content.import.add', '������Ȩ', 7, '������Ȩ(��ťȨ��)');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (28, 'pt.content.preEffect.create', '����Ԥ��Ч��Ŀ', 27, '����Ԥ��Ч��Ŀ');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (17, 'pt.bizzMng.content.import', '��������', 5, '��������');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (18, 'pt.Mng.content.change', '����Ա��Ȩǿ�Ʊ��', 17, '����Ա��Ȩǿ�Ʊ��');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (22, 'pt.Mng.content.prejudicate', '��ȨԤ��', 17, '��ȨԤ��');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (23, 'pt.Mng.content.checkDuplicate', '����Ա����', 17, '����Ա����');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (24, 'pt.Mng.content.addOrder', '�ɷ�����������', 17, '�ɷ�����������');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (25, 'pt.Mng.content.approve', '����Ա����', 17, '����Ա����');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (26, 'pt.Mng.content.reject', '����Ա�����ܾ�', 17, '����Ա�����ܾ�');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (27, 'pt.content.preEffect', 'Ԥ��Ч��������', 6, 'Ԥ��Ч��������');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (29, 'pt.content.import.batch', '��Ȩ��������', 6, '��Ȩ��������');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (30, 'pt.content.import.batch.cpimport', 'CP��Ȩ��������', 29, 'CP��Ȩ��������');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (32, 'pt.content.import.batch.mcpimpt', 'MCP������Ȩ����', 29, 'MCP������Ȩ����');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (39, 'pt.content.import.bussinessRemove', '����ɾ����Ȩ', 7, '����ɾ����Ȩ');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (40, 'pt.content.import.cpBussinessRemove', 'CP��������ɾ����Ȩ', 39, 'CP��������ɾ����Ȩ');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (36, 'pt.content.import.add.cpAdd', 'CP������Ȩ', 13, 'CP������Ȩ');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (37, 'pt.content.import.add.mcpAdd', 'MCP������Ȩ', 13, 'MCP������Ȩ');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (38, 'pt.content.import.batch.preImport', 'MCP����Ԥ����Ȩ����', 29, 'MCP����Ԥ����Ȩ����');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (42, 'pt.bizzMng.content.import.hide', '����Ա��Ȩ����', 17, '����Ա��Ȩ����');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (43, 'pt.bizzMng.content.import.business', '����Ա��Ȩ����', 17, '����Ա��Ȩ����');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (44, 'pt.bizzMng.content.import.delete', '����Ա��Ȩɾ��', 17, '����Ա��Ȩɾ��');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (45, 'pt.bizzMng.content.import.adminBussinessRemove', '����Ա����ɾ����Ȩ', 17, '����Ա����ɾ����Ȩ');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (50, 'pt.content.queryLack.export', '������ȱģ�嵼��', 8, '������ȱģ�嵼��');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (51, 'pt.bizzMng.tone', '��Ʒ����', 17, '��Ʒ����Ȩ��');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (52, 'pt.bizzMng.tone.approve', '��Ʒ����', 51, '��Ʒ����Ȩ��');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (53, 'pt.bizzMng.tone.order', '��Ʒ�ַ�', 51, '��Ʒ�ַ�Ȩ��');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (54, 'pt.bizzMng.tone.firstApprove', '��ƷԤ��', 51, '��ƷԤ��');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (55, 'pt.content.tone.edit', '�����޸�', 8, '�����޸�');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (56, 'pt.content.tone.delete', '����ɾ��', 8, '����ɾ��');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (57, 'pt.content.tone.release', '��������', 8, '��������');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (58, 'pt.content.tone.update', '�������', 8, '�������');

insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (4, 'MCP��ɫ', '��Ȩ���������ɫ');

insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (0, 'CP�˻�', '��Ȩ�ṩ��');

insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (1, '��Ȩ����', '��Ȩ���칫˾');

insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (2, '����������', '����������');

insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (3, 'IMP����Ա', 'ƽ̨ҵ��Ա');

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 0);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 6);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 7);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 11);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 12);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 9);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 10);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 13);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 37);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 29);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 32);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 38);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 27);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 28);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 5);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 17);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (4, 25);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (2, 0);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (2, 6);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (2, 8);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (2, 55);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (2, 56);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (2, 57);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (2, 58);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (2, 50);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 0);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 5);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 17);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 51);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 53);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 52);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 24);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 22);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 23);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 45);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 25);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 26);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 44);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 43);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 18);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 42);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 1);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (0, 0);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (0, 6);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (0, 7);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (0, 11);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (0, 12);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (0, 9);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (0, 10);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (0, 13);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (0, 36);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (0, 29);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (0, 30);

insert into T_FRAMEWORK_PRI_ROLE_RIGHT (ROLEID, RIGHTID)
values (3, 2);

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (9, '���������������ְ�Ȩ', '���������������ְ�Ȩ', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.24', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (10, '������������MV��Ȩ', '������������MV��Ȩ', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.24', '1|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (11, '������������ͼƬ��Ȩ', '������������ͼƬ��Ȩ', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.24', '2|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (12, '���������������ְ�Ȩ', '���������������ְ�Ȩ', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.27', '0|50');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (5, '�����޲κ�������', '�����޲κ�������', 1, '0/2 * * * * ?', 'timeTaskTestService', 'invokeMethod0', 0, '', '');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (6, '����1s�κ�������', '����1�κ�������', 1, '0/2 * * * * ?', 'timeTaskTestService', 'invokeMethod1_String', 0, '', '���Բ���');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (7, '����1i�κ�������', '����1�κ�������', 1, '0/2 * * * * ?', 'timeTaskTestService', 'invokeMethod1_int', 0, '', '1');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (8, '���Զ�κ�������', '����1�κ�������', 1, '0/2 * * * * ?', 'timeTaskTestService', 'invokeMethod2', 0, '192.168.104.27', '���Բ���1|3|1,2,3');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (13, '���������������ְ�Ȩ', '���������������ְ�Ȩ', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.26', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (14, '������������MV��Ȩ', '������������MV��Ȩ', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.26', '1|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (15, '������������ͼƬ��Ȩ', '������������ͼƬ��Ȩ', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.26', '2|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (16, '������ȱ��������', '������ȱ��������', 1, '0/2 * * * * ?', 'toneService', 'lostLackTmpValidate', 0, '192.168.104.26', '');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (17, '��������ͼƬ����', '��������ͼƬ����', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.24', '5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (18, '���������������', '���������������', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (19, '����������������', '����������������', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '1|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (20, '��������ȫ������', '��������ȫ������', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '2|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (21, '���������ý�����', '���������ý�����', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '3|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (22, '������������������', '������������������', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '4|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (23, '��������MV��Ƶ', '��������MV��Ƶ', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '6|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (24, '���������������', '���������������', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.24', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (25, '��������MV��Ƶ', '��������MV��Ƶ', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.24', '6|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (26, '���������������', '���������������', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (27, '����������������', '����������������', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '1|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (28, '��������ȫ������', '��������ȫ������', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '2|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (29, '������������������', '������������������', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '4|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (30, '���������ý�����', '���������ý�����', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '3|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (31, '��������ͼƬ����', '��������ͼƬ����', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (32, '��������MV��Ƶ', '��������MV��Ƶ', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '6|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (33, '���������������ְ�Ȩ', '���������������ְ�Ȩ', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.25', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (34, '������������ͼƬ��Ȩ', '������������ͼƬ��Ȩ', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.25', '2|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (35, '���������Զ�����', '���������Զ�����', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '0|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (36, '���������Զ�����', '���������Զ�����', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '1|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (37, '����ȫ���Զ�����', '����ȫ���Զ�����', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '2|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (38, '������ý���Զ�����', '������ý���Զ�����', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '3|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (39, '���������������Զ�����', '���������������Զ�����', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '4|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (40, '����ͼƬ�Զ�����', '����ͼƬ�Զ�����', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '5|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (41, '����MV�Զ�����', '����MV�Զ�����', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '6|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (42, '���������Զ��ַ�', '�������Զ��ַ�', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '0|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (43, '���������Զ��ַ�', '���������Զ��ַ�', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '1|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (44, '����ȫ���Զ��ַ�', '����ȫ���Զ��ַ�', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '2|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (45, '������ý���Զ��ַ�', '������ý���Զ��ַ�', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '3|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (46, '���������������Զ��ַ�', '���������������Զ��ַ�', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '4|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (47, '����ͼƬ�Զ��ַ�', '����ͼƬ�Զ��ַ�', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '5|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (48, '����MV�Զ��ַ�', '����MV�Զ��ַ�', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '6|5|100');

commit;





