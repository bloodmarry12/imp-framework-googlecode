alter table T_FRAMEWORK_PRI_EXPLORER disable constraint  FK_FRAMEWORK_PRI_EXPLORER;
alter table T_FRAMEWORK_PRI_EXPLORER disable constraint  FK_FRAMEWORK_PRI_EXPLORER_R;

insert into t_framework_constant (ID, VALUE, NAME, SEQ, MODELID)
values (147, '1', '有播放时间,必填', 2, 24);

insert into t_framework_constant (ID, VALUE, NAME, SEQ, MODELID)
values (146, '0', '没有播放时间', 1, 24);

insert into t_framework_constant (ID, VALUE, NAME, SEQ, MODELID)
values (148, '2', '有播放时间,可不填', 3, 24);



insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.plateform.id', 'CMS子平台ID', '0', '平台ID,四位平台编号。');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendRequest.pids', '数据同步:发送请求的平台', '0', '数据同步请求的平台。以","符号分隔多个平台ID。');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendResponse.pids', '数据同步:发送回执的平台', '0', '数据同步回执的平台。以","符号分隔多个平台ID。');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendRequest.size', '数据同步:发送请求数', '500', '平台数据同步，一次事务处理数据量。');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendResponse.size', '数据同步:发送回执数', '500', '平台数据同步，一次书屋处理数据量。');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendRequest.handle', '数据同步:处理请求数', '200', '请求成功后，一次事务处理数据量。');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('sysconfig.datalink.sendResponse.handle', '数据同步:处理回执数', '100', '成功接收回执后，一次事务处理数据量。');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('imp.copyright.import.ftp', '版权附件上传路径', '/CMSDEMO/COPYRIGHT', '版权附件上传的路径');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('imp.tone.import.ftp', '铃音附件上传路径', '/TONE', '铃音附件上传路径');

insert into T_FRAMEWORK_CONFIG (KEY, NAME, VALUE, DESCRIPTION)
values ('imp.tone.batchimport.count', '铃音批量引入模版中的记录数', '10', '铃音批量引入模版中的记录数');

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (1, '0', '流行', 0, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (2, '1', '经典', 1, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (3, '2', '摇滚', 2, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (4, '3', '爵士', 3, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (5, '4', '轻音乐', 4, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (6, '5', '嘻哈', 5, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (7, '6', '影视原声', 6, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (8, '7', '民族', 7, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (9, '8', '古典', 8, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (10, '9', '情歌', 9, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (11, '10', '怀旧', 10, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (12, '11', '欧美经典', 11, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (13, '12', '原创', 12, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (14, '13', '广告歌', 13, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (15, '14', '节日歌', 14, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (16, '15', '劲爆舞曲', 15, 1);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (17, '0', '华语男歌手', 0, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (18, '1', '华语女歌手', 1, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (19, '2', '华语组合', 2, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (20, '3', '欧美男歌手', 3, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (21, '4', '欧美女歌手', 4, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (22, '5', '欧美组合', 5, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (23, '6', '日韩男歌手', 6, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (24, '7', '日韩女歌手', 7, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (25, '8', '日韩组合', 8, 2);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (26, '0', '中文', 0, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (27, '1', '英文', 1, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (28, '2', '日文', 2, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (29, '3', '韩文', 3, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (30, '4', '俄文', 4, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (31, '5', '西班牙文', 5, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (32, '6', '法文', 6, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (33, '7', '纯音乐', 7, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (34, '8', '阿拉伯', 8, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (35, '9', '葡萄牙文', 9, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (36, '10', '德文', 10, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (37, '11', '其他语种', 11, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (38, '12', '冰岛', 12, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (39, '13', '其他语种', 13, 3);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (40, '0', '内地', 0, 4);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (41, '1', '港台', 1, 4);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (42, '2', '欧美', 2, 4);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (43, '3', '日韩', 3, 4);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (44, '4', '其他', 4, 4);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (45, '0', '演唱会', 0, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (46, '1', '歌友会', 1, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (47, '2', '单曲MV', 2, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (48, '3', '明星访谈', 3, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (49, '4', '搞笑动画', 4, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (50, '5', '新闻类视频', 5, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (51, '6', '活动类视频', 6, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (52, '7', '专辑发布会', 7, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (53, '8', '电影片段、插曲、花絮等', 8, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (54, '9', '电视剧片段、插曲、花絮等', 9, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (55, '10', '其他', 10, 5);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (56, '0', '合照', 0, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (57, '1', '大头照', 1, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (58, '2', 'MV截图', 2, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (59, '3', '心情气氛图', 3, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (60, '4', '风景气氛图', 4, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (61, '5', '节庆气氛图', 5, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (62, '6', '生活写真照', 6, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (63, '7', '专辑封面宣传照', 7, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (64, '8', '音乐或影视海报封面照', 8, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (65, '9', '音乐或影视杂志封面照', 9, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (66, '10', '影视舞台演唱会现场剧照', 10, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (67, '11', '录音棚或拍摄现场工作照', 11, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (68, '12', '其他', 12, 6);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (69, '0', '新增', 1, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (70, '1', '修改', 2, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (71, '2', '发布', 3, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (72, '3', '预审', 4, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (73, '4', '审批', 5, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (74, '5', '驳回申请', 6, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (75, '0', '操作失败', 1, 8);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (76, '1', '操作成功', 2, 8);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (77, '0', '非DIY振铃', 1, 9);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (78, '1', 'DIY振铃', 2, 9);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (79, '0', '普通铃音', 1, 10);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (80, '1', '音乐卡', 2, 10);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (81, '2', '高级会员免费铃音', 3, 10);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (82, '3', '免drm全曲', 4, 10);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (83, '0', '未分发', 1, 11);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (84, '1', '已分发', 2, 11);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (85, '0', '普通铃音', 1, 12);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (86, '1', '普通会员铃音', 2, 12);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (87, '2', '高级会员铃音', 3, 12);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (88, '3', 'vip会员铃音', 4, 12);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (89, '4', '渠道专属铃音', 5, 12);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (90, '0', '热播视频', 1, 13);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (91, '1', '多分高视频', 2, 13);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (92, '0', '高', 1, 14);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (93, '1', '中', 2, 14);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (94, '2', '低', 3, 14);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (95, '0', '176*144', 1, 15);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (96, '1', '352*288', 2, 15);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (97, '0', '无字幕', 1, 16);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (98, '1', '简体中文', 2, 16);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (99, '2', '繁体中文', 3, 16);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (100, '3', '英文', 4, 16);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (101, '0', '有音频有视频的媒体文件', 1, 17);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (102, '1', '无音频有视频的媒体文件', 2, 17);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (103, '0', '原因一', 1, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (104, '1', '原因二', 2, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (105, '2', '原因三', 3, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (106, '3', '原因四', 4, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (107, '4', '原因五', 5, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (108, '5', '原因六', 6, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (109, '6', '原因七', 7, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (110, '7', '原因八', 8, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (111, '8', '原因九', 9, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (112, '9', '原因十', 10, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (113, '10', '原因十一', 11, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (114, '11', '原因十二', 12, 18);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (115, '6', '预生效引入', 7, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (116, '7', '分发', 8, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (117, '8', '隐藏', 9, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (118, '9', '恢复', 10, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (119, '0', '铃音拒绝原因一', 1, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (120, '1', '铃音拒绝原因二', 2, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (121, '2', '铃音拒绝原因三', 3, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (122, '3', '铃音拒绝原因四', 4, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (123, '4', '铃音拒绝原因五', 5, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (124, '5', '铃音拒绝原因六', 6, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (125, '6', '铃音拒绝原因七', 7, 19);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (126, '0', '否', 0, 20);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (127, '1', '是', 1, 20);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (128, '10', '新增铃音', 11, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (129, '11', '变更铃音', 12, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (130, '12', '修改铃音', 13, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (134, '0', '产品', 1, 22);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (131, '0', '修改前', 0, 21);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (132, '1', '修改后', 1, 21);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (133, '13', '审批变更铃音', 14, 7);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (135, '1', '试听', 2, 22);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (137, '1', '普通版', 1, 23);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (138, '2', '激情版', 2, 23);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (139, '3', '高潮版', 3, 23);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (140, '4', '盗版', 4, 23);

insert into T_FRAMEWORK_CONSTANT (ID, VALUE, NAME, SEQ, MODELID)
values (141, '5', '牛A与牛C之间', 5, 23);

insert into T_FRAMEWORK_FTP (FTPALIAS, USERNAME, USERPASSWORD, IP, PORT, DEFAULTPATH)
values ('COPYRIGHT_CONTENT', 'imp', 'dev1234', '192.168.104.51', 21, '/CMSDEMO/COPYRIGHT');

insert into T_FRAMEWORK_PRI_ACCOUNT (ID, NAME, PASSWORD, STATUS, OPERATORCONTACT, OPERATOR, ROLEID, LASTLOGINTIME, CREATETIME)
values (32, 'testCP', '4297f44b13955235245b2497399d7a93', 1, '12345678901', '测试用CP账户', 0, to_date('24-09-2009 11:26:08', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into T_FRAMEWORK_PRI_ACCOUNT (ID, NAME, PASSWORD, STATUS, OPERATORCONTACT, OPERATOR, ROLEID, LASTLOGINTIME, CREATETIME)
values (2, 'admin', '4297f44b13955235245b2497399d7a93', 1, '15928085969', 'admin', 3, to_date('24-09-2009 11:28:51', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into T_FRAMEWORK_PRI_ACCOUNT (ID, NAME, PASSWORD, STATUS, OPERATORCONTACT, OPERATOR, ROLEID, LASTLOGINTIME, CREATETIME)
values (33, 'testSP', '4297f44b13955235245b2497399d7a93', 1, '12312312312312', '测试用铃音制作商账户', 2, to_date('24-09-2009 11:28:28', 'dd-mm-yyyy hh24:mi:ss'), null);

insert into T_FRAMEWORK_PRI_ACCOUNT (ID, NAME, PASSWORD, STATUS, OPERATORCONTACT, OPERATOR, ROLEID, LASTLOGINTIME, CREATETIME)
values (34, 'testMCP', '4297f44b13955235245b2497399d7a93', 1, '12312312312', '测试用MCP账户', 4, to_date('24-09-2009 11:25:01', 'dd-mm-yyyy hh24:mi:ss'), null);

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
values (0, '系统导航菜单', '', null, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (6, '账户管理', '', 0, null, 9);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (1, '内容引入平台', '', 0, 6, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (2, '综合管理平台', '', 0, 5, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (3, '局数据平台', '', 0, null, 3);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (4, '分发平台', '', 0, null, 4);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (5, '配置管理平台', '', 0, 1, 5);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (44, '版权引入', '', 1, 7, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (45, '批量版权引入', 'copyright/batch/cpImport.do', 44, 30, 3);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (52, '单个版权引入', '', 44, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (57, 'CP/SP ID管理', '', 5, null, 5);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (141, '图片新增', 'copyright/pictureCreate.do', 52, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (142, '产品引入列表', 'toneOrder/listUnmadeToneOrder.do', 63, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (38, '权限管理', '', 5, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (37, '账户管理', '', 5, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (40, '审批CP账户', 'console/cum/account/listCP4Approval.do', 37, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (42, '新建账号', 'console/cum/account/createCPAccount.do', 37, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (39, '查看所有账户', 'console/cum/account/listAll.do', 37, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (43, '角色管理', 'console/cum/role/listRole.do', 38, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (41, '账号权限管理', 'console/cum/right/listAccount.do', 38, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (140, '预生效内容查询', 'preEffective/listCopyright.do', 115, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (143, '铃音变更审批', 'tone/listAuditing.do', 63, null, 6);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (60, '版权查询', 'copyright/list.do', 44, null, -1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (65, '版权引入管理', '', 2, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (63, '铃音引入', '', 1, 8, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (66, '铃音引入管理', '', 2, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (67, '版权查询', 'copyright/list.do', 65, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (144, '彩铃格式管理', 'toneFileType/list.do?toneType=0', 118, null, 4);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (145, '振铃格式管理', 'toneFileType/list.do?toneType=1', 118, null, 5);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (152, 'MV文件格式管理', 'toneFileType/list.do?toneType=6', 118, null, 10);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (153, '废弃版权查询', 'copyright/listTrashCopyright.do', 65, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (154, 'MV新增', 'copyright/mvCreate.do', 52, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (155, '版权批量分发', 'copyright/butchAddOrder.do', 65, null, 3);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (146, '全曲格式管理', 'toneFileType/list.do?toneType=2', 118, null, 6);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (147, '多媒体格式管理', 'toneFileType/list.do?toneType=3', 118, null, 7);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (80, '铃音盒管理', '', 2, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (148, '音乐随声听格式管理', 'toneFileType/list.do?toneType=4', 118, null, 8);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (149, '创建CPID', 'console/cum/account/createCPID.do', 57, null, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (150, '图片格式管理', 'toneFileType/list.do?toneType=5', 118, null, 9);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (151, '项目查询', 'copyright/listPreEffectiveProject.do', 44, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (87, '产品查询', 'tone/list.do', 66, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (88, '铃音变更审批', 'tone/listAuditing.do', 66, null, 6);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (94, '新建SPID', 'console/cum/account/createSPID.do', 57, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (118, '铃音格式管理', '', 2, null, 3);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (121, '批量版权引入', 'copyright/batch/mcpImport.do', 44, 32, 8);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (115, '预生效内容引入', '', 1, 27, 2);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (105, '查看所有CPID', 'console/cum/account/listCPID.do', 57, null, 3);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (106, '铃音查询', 'tone/list.do', 63, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (108, '查看所有SPID', 'console/cum/account/listSPID.do', 57, null, 4);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (117, '预生效项目浏览', 'copyright/listPreEffectiveProject.do', 115, null, 1);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (124, '铃音格式查询', 'tone/listNoFormatter.do', 63, null, 6);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (136, '音乐新增', 'copyright/musicCreate.do', 52, null, 0);

insert into T_FRAMEWORK_PRI_EXPLORER (ID, NAME, PATH, PARENTID, RIGHTID, EORDER)
values (139, '创建预生效内容', 'preEffective/add.do', 115, null, 0);

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (0, 'p.cms4.root', 'CMS系统权限', null, '系统权限根目录');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (1, 'pt.config', '配置管理平台', 0, '配置管理平台权限');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (2, 'pt.config.accountManagement', '账户管理导航', 1, '账户管理导航菜单');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (5, 'pt.bizzMng', '综合管理平台', 0, '综合管理平台');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (6, 'pt.content.import', '内容引入平台', 0, '');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (7, 'pt.content.import.copyright', '版权引入菜单', 6, '');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (8, 'pt.content.import.tone', '铃音引入菜单', 6, '铃音引入菜单');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (9, 'pt.content.import.release', 'CP发布版权按钮', 7, 'CP发布版权按钮');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (10, 'pt.content.import.requestchange', 'CP请求版权变更', 7, 'CP请求版权变更');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (11, 'pt.content.import.update', 'CP修改版权', 7, 'CP修改版权');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (12, 'pt.content.import.delete', 'CP删除版权', 7, 'CP删除版权');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (13, 'pt.content.import.add', '新增版权', 7, '新增版权(按钮权限)');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (28, 'pt.content.preEffect.create', '创建预生效项目', 27, '创建预生效项目');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (17, 'pt.bizzMng.content.import', '内容引入', 5, '内容引入');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (18, 'pt.Mng.content.change', '管理员版权强制变更', 17, '管理员版权强制变更');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (22, 'pt.Mng.content.prejudicate', '版权预审', 17, '版权预审');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (23, 'pt.Mng.content.checkDuplicate', '管理员剔重', 17, '管理员剔重');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (24, 'pt.Mng.content.addOrder', '派发铃音制作单', 17, '派发铃音制作单');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (25, 'pt.Mng.content.approve', '管理员审批', 17, '管理员审批');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (26, 'pt.Mng.content.reject', '管理员审批拒绝', 17, '管理员审批拒绝');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (27, 'pt.content.preEffect', '预生效内容引入', 6, '预生效内容引入');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (29, 'pt.content.import.batch', '版权批量操作', 6, '版权批量操作');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (30, 'pt.content.import.batch.cpimport', 'CP版权批量引入', 29, 'CP版权批量引入');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (32, 'pt.content.import.batch.mcpimpt', 'MCP批量版权引入', 29, 'MCP批量版权引入');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (39, 'pt.content.import.bussinessRemove', '商用删除版权', 7, '商用删除版权');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (40, 'pt.content.import.cpBussinessRemove', 'CP申请商用删除版权', 39, 'CP申请商用删除版权');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (36, 'pt.content.import.add.cpAdd', 'CP新增版权', 13, 'CP新增版权');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (37, 'pt.content.import.add.mcpAdd', 'MCP新增版权', 13, 'MCP新增版权');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (38, 'pt.content.import.batch.preImport', 'MCP批量预留版权引入', 29, 'MCP批量预留版权引入');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (42, 'pt.bizzMng.content.import.hide', '管理员版权隐藏', 17, '管理员版权隐藏');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (43, 'pt.bizzMng.content.import.business', '管理员版权商用', 17, '管理员版权商用');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (44, 'pt.bizzMng.content.import.delete', '管理员版权删除', 17, '管理员版权删除');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (45, 'pt.bizzMng.content.import.adminBussinessRemove', '管理员商用删除版权', 17, '管理员商用删除版权');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (50, 'pt.content.queryLack.export', '铃音查缺模板导出', 8, '铃音查缺模板导出');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (51, 'pt.bizzMng.tone', '产品管理', 17, '产品管理权限');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (52, 'pt.bizzMng.tone.approve', '产品审批', 51, '产品审批权限');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (53, 'pt.bizzMng.tone.order', '产品分发', 51, '产品分发权限');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (54, 'pt.bizzMng.tone.firstApprove', '产品预审', 51, '产品预审');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (55, 'pt.content.tone.edit', '铃音修改', 8, '铃音修改');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (56, 'pt.content.tone.delete', '铃音删除', 8, '铃音删除');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (57, 'pt.content.tone.release', '铃音发布', 8, '铃音发布');

insert into T_FRAMEWORK_PRI_RIGHT (ID, CODE, RIGHTNAME, PARENTID, DESCRIPTION)
values (58, 'pt.content.tone.update', '铃音变更', 8, '铃音变更');

insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (4, 'MCP角色', '版权代理引入角色');

insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (0, 'CP账户', '版权提供商');

insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (1, '版权认领', '版权认领公司');

insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (2, '铃音制作商', '铃音制作商');

insert into T_FRAMEWORK_PRI_ROLE (ID, NAME, DESCRIPTION)
values (3, 'IMP管理员', '平台业务员');

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
values (9, '批量引入线上音乐版权', '批量引入线上音乐版权', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.24', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (10, '批量引入线上MV版权', '批量引入线上MV版权', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.24', '1|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (11, '批量引入线上图片版权', '批量引入线上图片版权', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.24', '2|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (12, '批量引入线上音乐版权', '批量引入线上音乐版权', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.27', '0|50');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (5, '测试无参函数调用', '测试无参函数调用', 1, '0/2 * * * * ?', 'timeTaskTestService', 'invokeMethod0', 0, '', '');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (6, '测试1s参函数调用', '测试1参函数调用', 1, '0/2 * * * * ?', 'timeTaskTestService', 'invokeMethod1_String', 0, '', '测试参数');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (7, '测试1i参函数调用', '测试1参函数调用', 1, '0/2 * * * * ?', 'timeTaskTestService', 'invokeMethod1_int', 0, '', '1');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (8, '测试多参函数调用', '测试1参函数调用', 1, '0/2 * * * * ?', 'timeTaskTestService', 'invokeMethod2', 0, '192.168.104.27', '测试参数1|3|1,2,3');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (13, '批量引入线上音乐版权', '批量引入线上音乐版权', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.26', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (14, '批量引入线上MV版权', '批量引入线上MV版权', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.26', '1|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (15, '批量引入线上图片版权', '批量引入线上图片版权', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.26', '2|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (16, '铃音查缺批量导入', '铃音查缺批量导入', 1, '0/2 * * * * ?', 'toneService', 'lostLackTmpValidate', 0, '192.168.104.26', '');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (17, '批量引入图片铃音', '批量引入图片铃音', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.24', '5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (18, '批量引入彩铃铃音', '批量引入彩铃铃音', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (19, '批量引入振铃铃音', '批量引入振铃铃音', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '1|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (20, '批量引入全曲铃音', '批量引入全曲铃音', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '2|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (21, '批量引入多媒体彩铃', '批量引入多媒体彩铃', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '3|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (22, '批量引入音乐随身听', '批量引入音乐随身听', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '4|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (23, '批量引入MV视频', '批量引入MV视频', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.23', '6|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (24, '批量引入彩铃铃音', '批量引入彩铃铃音', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.24', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (25, '批量引入MV视频', '批量引入MV视频', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.24', '6|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (26, '批量引入彩铃铃音', '批量引入彩铃铃音', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (27, '批量引入振铃铃音', '批量引入振铃铃音', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '1|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (28, '批量引入全曲铃音', '批量引入全曲铃音', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '2|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (29, '批量引入音乐随身听', '批量引入音乐随身听', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '4|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (30, '批量引入多媒体彩铃', '批量引入多媒体彩铃', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '3|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (31, '批量引入图片铃音', '批量引入图片铃音', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (32, '批量引入MV视频', '批量引入MV视频', 1, '0/2 * * * * ?', 'toneOrderService', 'batchSaveToneTask', 0, '192.168.104.21', '6|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (33, '批量引入线上音乐版权', '批量引入线上音乐版权', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.25', '0|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (34, '批量引入线上图片版权', '批量引入线上图片版权', 1, '0/2 * * * * ?', 'copyrightService', 'batchSaveCopyrightTask', 0, '192.168.104.25', '2|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (35, '批量彩铃自动审批', '批量铃音自动审批', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '0|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (36, '批量振铃自动审批', '批量振铃自动审批', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '1|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (37, '批量全曲自动审批', '批量全曲自动审批', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '2|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (38, '批量多媒体自动审批', '批量多媒体自动审批', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '3|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (39, '批量音乐随身听自动审批', '批量音乐随身听自动审批', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '4|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (40, '批量图片自动审批', '批量图片自动审批', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '5|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (41, '批量MV自动审批', '批量MV自动审批', 1, '0/2 * * * * ?', 'toneService', 'batchApproveTask', 0, '192.168.104.21', '6|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (42, '批量彩铃自动分发', '批量彩自动分发', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '0|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (43, '批量振铃自动分发', '批量振铃自动分发', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '1|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (44, '批量全曲自动分发', '批量全曲自动分发', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '2|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (45, '批量多媒体自动分发', '批量多媒体自动分发', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '3|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (46, '批量音乐随身听自动分发', '批量音乐随身听自动分发', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '4|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (47, '批量图片自动分发', '批量图片自动分发', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '5|5|100');

insert into T_FRAMEWORK_SCHEDULE (ID, TASKNAME, DESCRIPTION, STATUS, CEXP, BEANNAME, METHODNAME, CONCURRENT, HOSTIP, PARAMETER)
values (48, '批量MV自动分发', '批量MV自动分发', 1, '0/2 * * * * ?', 'toneService', 'batchDistributeTask', 0, '192.168.104.21', '6|5|100');

commit;





