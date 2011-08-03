------------------------------------------------
-- Export file for user CMS4DEV               --
-- Created by yuzq on 2009-11-20, ���� 10:08:20 --
------------------------------------------------

spool cms4dev1120.log
prompt
prompt Creating table T_FRAMEWORK_CLUSTERS
prompt ===================================
prompt
create table T_FRAMEWORK_CLUSTERS
(
  URL            VARCHAR2(128) not null,
  LASTACTIVETIME DATE not null,
  LASTSTATUS     CHAR(1) not null,
  LOCALHOST      VARCHAR2(256) not null
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
alter table T_FRAMEWORK_CLUSTERS
  add constraint PK_FRAMEWORK_CLUSTERS primary key (URL, LOCALHOST)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_CONFIG
prompt =================================
prompt
create table T_FRAMEWORK_CONFIG
(
  KEY         VARCHAR2(64) not null,
  NAME        VARCHAR2(64),
  VALUE       VARCHAR2(256),
  DESCRIPTION VARCHAR2(256),
  SCOPE       VARCHAR2(2),
  ADDRESS     VARCHAR2(256) not null
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
comment on column T_FRAMEWORK_CONFIG.KEY
  is '���ùؼ���';
comment on column T_FRAMEWORK_CONFIG.NAME
  is '��������';
comment on column T_FRAMEWORK_CONFIG.VALUE
  is '����ֵ';
comment on column T_FRAMEWORK_CONFIG.DESCRIPTION
  is '����������';
comment on column T_FRAMEWORK_CONFIG.SCOPE
  is '����������Χ 0:ȫ�� 1:������ۺϹ���ƽ̨ 2:�ַ�ƽ̨ 3:��������ƽ̨ -1:������';
comment on column T_FRAMEWORK_CONFIG.ADDRESS
  is '��������ַ Ĭ��:0.0.0.0';
alter table T_FRAMEWORK_CONFIG
  add constraint PK_FRAMEWORK_CONFIG primary key (KEY, ADDRESS)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_CONSTANT
prompt ===================================
prompt
create table T_FRAMEWORK_CONSTANT
(
  ID      NUMBER(20) not null,
  VALUE   VARCHAR2(32),
  NAME    VARCHAR2(64),
  SEQ     NUMBER(2),
  MODELID NUMBER(2)
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
alter table T_FRAMEWORK_CONSTANT
  add constraint PK_FRAMEWORK_CONSTANT primary key (ID)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_CONSTANT_MODEL
prompt =========================================
prompt
create table T_FRAMEWORK_CONSTANT_MODEL
(
  MODELID   NUMBER(2),
  MODELNAME VARCHAR2(50)
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_FTP
prompt ==============================
prompt
create table T_FRAMEWORK_FTP
(
  FTPALIAS     VARCHAR2(64) not null,
  USERNAME     VARCHAR2(64),
  USERPASSWORD VARCHAR2(32),
  IP           VARCHAR2(15),
  PORT         NUMBER(6),
  DEFAULTPATH  VARCHAR2(256)
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
comment on column T_FRAMEWORK_FTP.FTPALIAS
  is 'FTP����ID';
comment on column T_FRAMEWORK_FTP.USERNAME
  is '�û���';
comment on column T_FRAMEWORK_FTP.USERPASSWORD
  is '����';
comment on column T_FRAMEWORK_FTP.IP
  is '��ַ';
comment on column T_FRAMEWORK_FTP.PORT
  is '�˿ں�';
comment on column T_FRAMEWORK_FTP.DEFAULTPATH
  is 'Ĭ��·��';
alter table T_FRAMEWORK_FTP
  add constraint PK_FRAMEWORK_FTPCONFIG primary key (FTPALIAS)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_HTTP_LOG
prompt ===================================
prompt
create table T_FRAMEWORK_HTTP_LOG
(
  ID          VARCHAR2(20) not null,
  OPERATEDATE DATE not null,
  URL         VARCHAR2(256) not null,
  AID         NUMBER(20) not null,
  PARAMETERS  VARCHAR2(2000),
  DESCRIPTION VARCHAR2(2000),
  REMOTEIP    VARCHAR2(15) not null,
  OPERATETIME NUMBER(20) not null,
  LASTURL     VARCHAR2(256),
  SESSIONID   VARCHAR2(128),
  SERVER      VARCHAR2(128)
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
comment on column T_FRAMEWORK_HTTP_LOG.ID
  is '����ID���뵱ǰ��־ID��ȣ����ݸ�ID����׷���ļ���־���ļ���־��������ҵ�������뷵�غ��쳣';
comment on column T_FRAMEWORK_HTTP_LOG.OPERATEDATE
  is '��������';
comment on column T_FRAMEWORK_HTTP_LOG.URL
  is '��ǰ�������ԴURI';
comment on column T_FRAMEWORK_HTTP_LOG.AID
  is '�������˻�ID������û�������˻����ƣ�ͨ������T_FRAMEWORK_PRI_ACCOUNT����';
comment on column T_FRAMEWORK_HTTP_LOG.PARAMETERS
  is '���������Ϣ��������¼getParamterMap�еĲ�����ֵ��';
comment on column T_FRAMEWORK_HTTP_LOG.DESCRIPTION
  is '������Ϣ���û���չ������־��Ϣ�����籸���ļ���ַ��';
comment on column T_FRAMEWORK_HTTP_LOG.REMOTEIP
  is 'Զ�������IP';
comment on column T_FRAMEWORK_HTTP_LOG.OPERATETIME
  is '��ǰcontroller�Ĵ���ʱ�䣻';
comment on column T_FRAMEWORK_HTTP_LOG.LASTURL
  is '��������ĵ�ַ�������ĸ�ҳ��������ĵ�ǰ����';
comment on column T_FRAMEWORK_HTTP_LOG.SESSIONID
  is '���sessionID';
alter table T_FRAMEWORK_HTTP_LOG
  add constraint PKT_FRAMEWORK_HTTP_LOG primary key (ID)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_IBUS_LOG
prompt ===================================
prompt
create table T_FRAMEWORK_IBUS_LOG
(
  PROCID    VARCHAR2(32),
  TRANSID   VARCHAR2(32),
  DATE1     DATE,
  DATE2     DATE,
  EXCEPTION VARCHAR2(512),
  MESSAGE1A VARCHAR2(2000),
  MESSAGE1B VARCHAR2(2000),
  MESSAGE1C VARCHAR2(2000),
  MESSAGE2A VARCHAR2(2000),
  MESSAGE2B VARCHAR2(2000),
  MESSAGE2C VARCHAR2(2000),
  URL       VARCHAR2(512),
  SERVER    VARCHAR2(128)
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_PRI_ACCOUNT
prompt ======================================
prompt
create table T_FRAMEWORK_PRI_ACCOUNT
(
  ID              NUMBER(20) not null,
  NAME            VARCHAR2(64),
  PASSWORD        VARCHAR2(32),
  STATUS          NUMBER(1),
  OPERATORCONTACT VARCHAR2(32),
  OPERATOR        VARCHAR2(128),
  ROLEID          NUMBER(20),
  LASTLOGINTIME   DATE,
  CREATETIME      DATE
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
comment on column T_FRAMEWORK_PRI_ACCOUNT.ID
  is 'ID';
comment on column T_FRAMEWORK_PRI_ACCOUNT.NAME
  is '�û���¼����(SPID/CPID)';
comment on column T_FRAMEWORK_PRI_ACCOUNT.PASSWORD
  is '�û�����';
comment on column T_FRAMEWORK_PRI_ACCOUNT.STATUS
  is '״̬:-1������0�ȴ����1���2ͣ�á�3ɾ��';
comment on column T_FRAMEWORK_PRI_ACCOUNT.OPERATORCONTACT
  is '��������ϵ��ʽ';
comment on column T_FRAMEWORK_PRI_ACCOUNT.OPERATOR
  is '����������';
comment on column T_FRAMEWORK_PRI_ACCOUNT.ROLEID
  is '��ɫ';
comment on column T_FRAMEWORK_PRI_ACCOUNT.LASTLOGINTIME
  is '����¼ϵͳʱ��';
comment on column T_FRAMEWORK_PRI_ACCOUNT.CREATETIME
  is '�˻�����ʱ��';
alter table T_FRAMEWORK_PRI_ACCOUNT
  add constraint PK_FRAMEWORK_PRI_ACCOUNT primary key (ID)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
create unique index IDX_CMS_COMM_ACCOUNT_NAME on T_FRAMEWORK_PRI_ACCOUNT (NAME)
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_PRI_ACCOUNT_RIGHT
prompt ============================================
prompt
create table T_FRAMEWORK_PRI_ACCOUNT_RIGHT
(
  ACCOUNTID NUMBER(20) not null,
  RIGHTID   NUMBER(20) not null
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
alter table T_FRAMEWORK_PRI_ACCOUNT_RIGHT
  add constraint PK_FRAMEWORK_PRI_ACCOUNTRIGHT primary key (ACCOUNTID, RIGHTID)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_PRI_RIGHT
prompt ====================================
prompt
create table T_FRAMEWORK_PRI_RIGHT
(
  ID          NUMBER(20) not null,
  CODE        VARCHAR2(256),
  RIGHTNAME   VARCHAR2(64),
  PARENTID    NUMBER(20),
  DESCRIPTION VARCHAR2(256)
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
comment on column T_FRAMEWORK_PRI_RIGHT.ID
  is '��ˮ��';
comment on column T_FRAMEWORK_PRI_RIGHT.CODE
  is 'ҵ�����,ҵ����ݸñ�����ȷ��Ȩ�޶���';
comment on column T_FRAMEWORK_PRI_RIGHT.RIGHTNAME
  is 'Ȩ������';
comment on column T_FRAMEWORK_PRI_RIGHT.PARENTID
  is '����Ȩ��';
comment on column T_FRAMEWORK_PRI_RIGHT.DESCRIPTION
  is 'Ȩ��������Ϣ';
alter table T_FRAMEWORK_PRI_RIGHT
  add constraint PK_FRAMEWORK_PRI_RIGHT primary key (ID)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
alter table T_FRAMEWORK_PRI_RIGHT
  add constraint PK_FRAMEWORK_PRI_CODE unique (CODE)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_PRI_EXPLORER
prompt =======================================
prompt
create table T_FRAMEWORK_PRI_EXPLORER
(
  ID       NUMBER(20) not null,
  NAME     VARCHAR2(64),
  PATH     VARCHAR2(128),
  PARENTID NUMBER(20),
  RIGHTID  NUMBER(20),
  EORDER   NUMBER(3) default 0
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
comment on column T_FRAMEWORK_PRI_EXPLORER.ID
  is 'ID';
comment on column T_FRAMEWORK_PRI_EXPLORER.NAME
  is '�˵���ʾ����';
comment on column T_FRAMEWORK_PRI_EXPLORER.PATH
  is '�˵�����·��';
comment on column T_FRAMEWORK_PRI_EXPLORER.PARENTID
  is '�˵����ڵ�(Ĭ��Ϊ0)';
comment on column T_FRAMEWORK_PRI_EXPLORER.RIGHTID
  is 'Ȩ��ID';
comment on column T_FRAMEWORK_PRI_EXPLORER.EORDER
  is '��ʾ����';
alter table T_FRAMEWORK_PRI_EXPLORER
  add constraint PK_FRAMEWORK_PRI_EXPLORER primary key (ID)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
alter table T_FRAMEWORK_PRI_EXPLORER
  add constraint FK_FRAMEWORK_PRI_EXPLORER foreign key (PARENTID)
  references T_FRAMEWORK_PRI_EXPLORER (ID);
alter table T_FRAMEWORK_PRI_EXPLORER
  add constraint FK_FRAMEWORK_PRI_EXPLORER_R foreign key (RIGHTID)
  references T_FRAMEWORK_PRI_RIGHT (ID);

prompt
prompt Creating table T_FRAMEWORK_PRI_ROLE
prompt ===================================
prompt
create table T_FRAMEWORK_PRI_ROLE
(
  ID          NUMBER(20) not null,
  NAME        VARCHAR2(128),
  DESCRIPTION VARCHAR2(256)
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
comment on column T_FRAMEWORK_PRI_ROLE.ID
  is 'ID';
comment on column T_FRAMEWORK_PRI_ROLE.NAME
  is '��ɫ����';
comment on column T_FRAMEWORK_PRI_ROLE.DESCRIPTION
  is '��ɫ����';
alter table T_FRAMEWORK_PRI_ROLE
  add constraint PK_FRAMEWORK_PRI_ROLE primary key (ID)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_PRI_ROLE_RIGHT
prompt =========================================
prompt
create table T_FRAMEWORK_PRI_ROLE_RIGHT
(
  ROLEID  NUMBER(20) not null,
  RIGHTID NUMBER(20) not null
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
alter table T_FRAMEWORK_PRI_ROLE_RIGHT
  add constraint PK_FRAMEWORK_PRI_ROLERIGHT primary key (ROLEID, RIGHTID)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating table T_FRAMEWORK_SCHEDULE
prompt ===================================
prompt
create table T_FRAMEWORK_SCHEDULE
(
  ID          NUMBER(3) not null,
  TASKNAME    VARCHAR2(64),
  DESCRIPTION VARCHAR2(256),
  STATUS      NUMBER(1),
  CEXP        VARCHAR2(64),
  BEANNAME    VARCHAR2(128),
  METHODNAME  VARCHAR2(128),
  CONCURRENT  NUMBER(1) default 0,
  HOSTIP      VARCHAR2(128),
  PARAMETER   VARCHAR2(128)
)
tablespace MUSICDATA
  pctfree 10
  pctused 40
  initrans 1
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );
comment on column T_FRAMEWORK_SCHEDULE.ID
  is 'id';
comment on column T_FRAMEWORK_SCHEDULE.TASKNAME
  is '��ʱ��������';
comment on column T_FRAMEWORK_SCHEDULE.DESCRIPTION
  is '��ʱ��������';
comment on column T_FRAMEWORK_SCHEDULE.STATUS
  is '״̬:0-����,1-ֹͣ';
comment on column T_FRAMEWORK_SCHEDULE.CEXP
  is 'ʱ��������ʽ';
comment on column T_FRAMEWORK_SCHEDULE.BEANNAME
  is 'IOC�����е�BeanName';
comment on column T_FRAMEWORK_SCHEDULE.METHODNAME
  is '������';
comment on column T_FRAMEWORK_SCHEDULE.CONCURRENT
  is '�Ƿ񲢷�';
comment on column T_FRAMEWORK_SCHEDULE.HOSTIP
  is '������IP';
alter table T_FRAMEWORK_SCHEDULE
  add constraint PK_FRAMEWORK_SCHEDULE primary key (ID)
  using index 
  tablespace MUSICDATA
  pctfree 10
  initrans 2
  maxtrans 255
  storage
  (
    initial 1M
    next 1M
    minextents 1
    maxextents unlimited
    pctincrease 0
  );

prompt
prompt Creating sequence SEQ_FRAMEWORK_CONSTANT
prompt ========================================
prompt
create sequence SEQ_FRAMEWORK_CONSTANT
minvalue 1
maxvalue 100000000
start with 1
increment by 1
nocache;

prompt
prompt Creating sequence SEQ_FRAMEWORK_PRI_ACCOUNT
prompt ===========================================
prompt
create sequence SEQ_FRAMEWORK_PRI_ACCOUNT
minvalue 1
maxvalue 1000000000000000000000000000
start with 1
increment by 1
nocache;

prompt
prompt Creating sequence SEQ_FRAMEWORK_PRI_EXPLORER
prompt ============================================
prompt
create sequence SEQ_FRAMEWORK_PRI_EXPLORER
minvalue 0
maxvalue 1000000000000000000000000000
start with 1
increment by 1
nocache;

prompt
prompt Creating sequence SEQ_FRAMEWORK_PRI_RIGHT
prompt =========================================
prompt
create sequence SEQ_FRAMEWORK_PRI_RIGHT
minvalue 1
maxvalue 1000000000000000000000000000
start with 1
increment by 1
nocache;

prompt
prompt Creating sequence SEQ_FRAMEWORK_PRI_ROLE
prompt ========================================
prompt
create sequence SEQ_FRAMEWORK_PRI_ROLE
minvalue 1
maxvalue 1000000000000000000000000000
start with 1
increment by 1
nocache;

prompt
prompt Creating sequence SEQ_FRAMWORK_KEY_WORD_SEQ
prompt ===========================================
prompt
create sequence SEQ_FRAMWORK_KEY_WORD_SEQ
minvalue 1
maxvalue 999999999999999999999999999
start with 1
increment by 1
cache 20;

spool off
