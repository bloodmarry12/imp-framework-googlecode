prompt
prompt Creating table T_FRAMEWORK_CONFIG
prompt =================================
prompt
create table T_FRAMEWORK_CONFIG
(
  KEY         VARCHAR2(64) not null,
  NAME        VARCHAR2(64),
  VALUE       VARCHAR2(64) not null,
  DESCRIPTION VARCHAR2(256)
)
tablespace MUSICDATA;

alter table T_FRAMEWORK_CONFIG
  add constraint PK_FRAMEWORK_CONFIG primary key (KEY)
  using index 
  tablespace MUSICIDX;

prompt
prompt Creating table T_FRAMEWORK_CONSTANT
prompt ===================================
prompt
create table T_FRAMEWORK_CONSTANT
(
  ID      NUMBER(20) not null,
  VALUE   VARCHAR2(32) not null,
  NAME    VARCHAR2(64) not null,
  SEQ     NUMBER(2) not null,
  MODELID NUMBER(2) not null
)
tablespace MUSICDATA;

alter table T_FRAMEWORK_CONSTANT
  add constraint PK_FRAMEWORK_CONSTANT primary key (ID)
  using index 
  tablespace MUSICIDX;

prompt
prompt Creating table T_FRAMEWORK_FTP
prompt ==============================
prompt
create table T_FRAMEWORK_FTP
(
  FTPALIAS     VARCHAR2(64) not null,
  USERNAME     VARCHAR2(64) not null,
  USERPASSWORD VARCHAR2(32) not null,
  IP           VARCHAR2(15) not null,
  PORT         NUMBER(6) not null,
  DEFAULTPATH  VARCHAR2(256)
)
tablespace MUSICDATA;

alter table T_FRAMEWORK_FTP
  add constraint PK_FRAMEWORK_FTPCONFIG primary key (FTPALIAS)
  using index 
  tablespace MUSICIDX;

prompt
prompt Creating table T_FRAMEWORK_PRI_ACCOUNT
prompt ======================================
prompt
create table T_FRAMEWORK_PRI_ACCOUNT
(
  ID              NUMBER(20) not null,
  NAME            VARCHAR2(64) not null,
  PASSWORD        VARCHAR2(32) not null,
  STATUS          NUMBER(1) not null,
  OPERATORCONTACT VARCHAR2(32),
  OPERATOR        VARCHAR2(128),
  ROLEID          NUMBER(20) not null,
  LASTLOGINTIME   DATE,
  CREATETIME      DATE
)
tablespace MUSICDATA;

alter table T_FRAMEWORK_PRI_ACCOUNT
  add constraint PK_FRAMEWORK_PRI_ACCOUNT primary key (ID)
  using index 
  tablespace MUSICIDX;

prompt
prompt Creating table T_FRAMEWORK_PRI_ACCOUNT_RIGHT
prompt ============================================
prompt
create table T_FRAMEWORK_PRI_ACCOUNT_RIGHT
(
  ACCOUNTID NUMBER(20) not null,
  RIGHTID   NUMBER(20) not null
)
tablespace MUSICDATA;

alter table T_FRAMEWORK_PRI_ACCOUNT_RIGHT
  add constraint PK_FRAMEWORK_PRI_ACCOUNTRIGHT primary key (ACCOUNTID, RIGHTID)
  using index 
  tablespace MUSICIDX;

prompt
prompt Creating table T_FRAMEWORK_PRI_RIGHT
prompt ====================================
prompt
create table T_FRAMEWORK_PRI_RIGHT
(
  ID          NUMBER(20) not null,
  CODE        VARCHAR2(256) not null,
  RIGHTNAME   VARCHAR2(64) not null,
  PARENTID    NUMBER(20),
  DESCRIPTION VARCHAR2(256)
)
tablespace MUSICDATA;

alter table T_FRAMEWORK_PRI_RIGHT
  add constraint PK_FRAMEWORK_PRI_RIGHT primary key (ID)
  using index 
  tablespace MUSICIDX;
  
alter table T_FRAMEWORK_PRI_RIGHT
  add constraint PK_FRAMEWORK_PRI_CODE unique (CODE)
  using index 
  tablespace MUSICIDX;

prompt
prompt Creating table T_FRAMEWORK_PRI_EXPLORER
prompt =======================================
prompt
create table T_FRAMEWORK_PRI_EXPLORER
(
  ID       NUMBER(20) not null,
  NAME     VARCHAR2(64) not null,
  PATH     VARCHAR2(128),
  PARENTID NUMBER(20),
  RIGHTID  NUMBER(20),
  EORDER   NUMBER(3) default 0
)
tablespace MUSICDATA;

alter table T_FRAMEWORK_PRI_EXPLORER
  add constraint PK_FRAMEWORK_PRI_EXPLORER primary key (ID)
  using index 
  tablespace MUSICIDX;
  
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
  NAME        VARCHAR2(128) not null,
  DESCRIPTION VARCHAR2(256)
)
tablespace MUSICDATA;

alter table T_FRAMEWORK_PRI_ROLE
  add constraint PK_FRAMEWORK_PRI_ROLE primary key (ID)
  using index 
  tablespace MUSICIDX;


prompt
prompt Creating table T_FRAMEWORK_PRI_ROLE_RIGHT
prompt =========================================
prompt
create table T_FRAMEWORK_PRI_ROLE_RIGHT
(
  ROLEID  NUMBER(20) not null,
  RIGHTID NUMBER(20) not null
)
tablespace MUSICDATA;

alter table T_FRAMEWORK_PRI_ROLE_RIGHT
  add constraint PK_FRAMEWORK_PRI_ROLERIGHT primary key (ROLEID, RIGHTID)
  using index 
  tablespace MUSICIDX;


prompt
prompt Creating table T_FRAMEWORK_SCHEDULE
prompt ===================================
prompt
create table T_FRAMEWORK_SCHEDULE
(
  ID          NUMBER(3) not null,
  TASKNAME    VARCHAR2(64) not null,
  DESCRIPTION VARCHAR2(256),
  STATUS      NUMBER(1) not null,
  CEXP        VARCHAR2(64) not null,
  BEANNAME    VARCHAR2(128) not null,
  METHODNAME  VARCHAR2(128) not null,
  CONCURRENT  NUMBER(1) default 0 not null,
  HOSTIP      VARCHAR2(15),
  PARAMETER   VARCHAR2(128)
)
tablespace MUSICDATA;

alter table T_FRAMEWORK_SCHEDULE
  add constraint PK_FRAMEWORK_SCHEDULE primary key (ID)
  using index 
  tablespace MUSICIDX;



prompt
prompt Creating sequence SEQ_FRAMEWORK_CONSTANT
prompt ========================================
prompt
create sequence SEQ_FRAMEWORK_CONSTANT
minvalue 1
maxvalue 100000000
start with 145
increment by 1
nocache;

prompt
prompt Creating sequence SEQ_FRAMEWORK_PRI_ACCOUNT
prompt ===========================================
prompt
create sequence SEQ_FRAMEWORK_PRI_ACCOUNT
minvalue 1
maxvalue 1000000000000000000000000000
start with 35
increment by 1
nocache;

prompt
prompt Creating sequence SEQ_FRAMEWORK_PRI_EXPLORER
prompt ============================================
prompt
create sequence SEQ_FRAMEWORK_PRI_EXPLORER
minvalue 0
maxvalue 1000000000000000000000000000
start with 156
increment by 1
nocache;

prompt
prompt Creating sequence SEQ_FRAMEWORK_PRI_RIGHT
prompt =========================================
prompt
create sequence SEQ_FRAMEWORK_PRI_RIGHT
minvalue 1
maxvalue 1000000000000000000000000000
start with 59
increment by 1
nocache;

prompt
prompt Creating sequence SEQ_FRAMEWORK_PRI_ROLE
prompt ========================================
prompt
create sequence SEQ_FRAMEWORK_PRI_ROLE
minvalue 1
maxvalue 1000000000000000000000000000
start with 5
increment by 1
nocache;