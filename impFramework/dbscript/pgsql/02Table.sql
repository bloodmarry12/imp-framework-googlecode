
create table t_framework_config
(
  KEY         VARCHAR(64) not null,
  NAME        VARCHAR(64),
  VALUE       VARCHAR(64) not null,
  DESCRIPTION VARCHAR(256),
  SCOPE       VARCHAR(2),
  ADDRESS     VARCHAR(256) not null
);
alter table t_framework_config
  add constraint pk_framework_config primary key (KEY);
  COMMENT ON COLUMN t_framework_config.scope IS '''配置所属范围 0:全局 1:引入和综合管理平台 2:分发平台 3:合作管理平台 -1:服务器'';';
  COMMENT ON COLUMN t_framework_config.address IS '如果为0.0.0.0为每台服务器所有，如果是特定的IP则为特定IP所有';



create table t_framework_constant
(
  ID      int8    DEFAULT nextval('seq_framework_constant'::regclass) NOT NULL,
  VALUE   VARCHAR(32) not null,
  NAME    VARCHAR(64) not null,
  SEQ     numeric(2) not null,
  MODELID numeric(2) not null
);
alter table t_framework_constant
  add constraint pk_framework_constant primary key (ID);
  
create table T_FRAMEWORK_CONSTANT_MODEL
(
  MODELID   numeric(2),
  MODELNAME VARCHAR(50)
);


create table t_framework_ftp
(
  FTPALIAS     VARCHAR(64) not null,
  USERNAME     VARCHAR(64) not null,
  USERPASSWORD VARCHAR(32) not null,
  IP           VARCHAR(15) not null,
  PORT         numeric(6) not null,
  DEFAULTPATH  VARCHAR(256)
);
alter table t_framework_ftp
  add constraint pk_framework_ftpconfig primary key (FTPALIAS);


create table T_FRAMEWORK_HTTP_LOG
(
  ID          VARCHAR(20) not null,
  OPERATEDATE timestamp not null,
  URL         VARCHAR(256) not null,
  AID         numeric(20) not null,
  PARAMETERS  VARCHAR(2000),
  DESCRIPTION VARCHAR(2000),
  REMOTEIP    VARCHAR(15) not null,
  OPERATETIME numeric(20) not null,
  LASTURL     VARCHAR(256),
  SESSIONID   VARCHAR(128),
  SERVER      VARCHAR(128)
);
alter table T_FRAMEWORK_HTTP_LOG
  add constraint PKT_FRAMEWORK_HTTP_LOG primary key (ID);


create table t_framework_pri_account
(
  ID      int8    DEFAULT nextval('seq_framework_pri_account'::regclass) NOT NULL,
  NAME            VARCHAR(64) not null,
  PASSWORD        VARCHAR(32) not null,
  STATUS          numeric(1) not null,
  OPERATORCONTACT VARCHAR(32),
  OPERATOR        VARCHAR(128),
  ROLEID          numeric(20) not null,
  LASTLOGINTIME   timestamp,
  lastpwdmodtime timestamp,
  CREATETIME      timestamp
);
alter table t_framework_pri_account
  add constraint pk_framework_pri_account primary key (ID);
create unique index idx_cms_comm_account_name on t_framework_pri_account (NAME);

create table T_FRAMEWORK_PRI_ACCOUNT_RIGHT
(
  ACCOUNTID numeric(20) not null,
  RIGHTID   numeric(20) not null
);
alter table T_FRAMEWORK_PRI_ACCOUNT_RIGHT
  add constraint PK_FRAMEWORK_PRI_ACCOUNTRIGHT primary key (ACCOUNTID, RIGHTID);


create table t_framework_pri_right
(
  ID      int8    DEFAULT nextval('seq_framework_pri_right'::regclass) NOT NULL,
  CODE        VARCHAR(256) not null,
  RIGHTNAME   VARCHAR(64) not null,
  PARENTID    numeric(20),
  DESCRIPTION VARCHAR(256)
);
alter table t_framework_pri_right
  add constraint pk_framework_pri_right primary key (ID); 
alter table t_framework_pri_right
  add constraint pk_framework_pri_code unique (CODE);
  


create table t_framework_pri_explorer
(
  ID      int8    DEFAULT nextval('seq_framework_pri_explorer'::regclass) NOT NULL,
  NAME     VARCHAR(64) not null,
  PATH     VARCHAR(128),
  PARENTID int8,
  RIGHTID  int8,
  EORDER   numeric(3) default 0
);
alter table t_framework_pri_explorer
  add constraint pk_framework_pri_explorer primary key (ID); 
alter table t_framework_pri_explorer
  add constraint fk_framework_pri_explorer foreign key (PARENTID)
  references t_framework_pri_explorer (ID); 
alter table t_framework_pri_explorer
  add constraint fk_framework_pri_explorer_r foreign key (RIGHTID)
  references t_framework_pri_right (ID);


create table t_framework_pri_role
(
  ID      int8    DEFAULT nextval('seq_framework_pri_role'::regclass) NOT NULL,
  NAME        VARCHAR(128) not null,
  DESCRIPTION VARCHAR(256)
);
alter table t_framework_pri_role
  add constraint pk_framework_pri_role primary key (ID);


create table t_framework_pri_role_right
(
  ROLEID  numeric(20) not null,
  RIGHTID numeric(20) not null
);
alter table t_framework_pri_role_right
  add constraint pk_framework_pri_roleright primary key (ROLEID, RIGHTID);

create table t_framework_schedule
(
   id bigint NOT NULL,
  TASKNAME    VARCHAR(64) not null,
  DESCRIPTION VARCHAR(256),
  STATUS      int  not null,
  CEXP        VARCHAR(64) not null,
  BEANNAME    VARCHAR(128) not null,
  METHODNAME  VARCHAR(128) not null,
  CONCURRENT  numeric(1) default 0 not null,
  HOSTIP      VARCHAR(15),
  PARAMETER   VARCHAR(128)
);
alter table t_framework_schedule
  add constraint pk_framework_schedule primary key (ID);
COMMENT ON COLUMN t_framework_schedule.status IS '''状态:0-运行,1-停止'';';
COMMENT ON COLUMN t_framework_schedule.cexp IS '''时间正则表达式'';';
COMMENT ON COLUMN t_framework_schedule.beanname IS '''IOC容器中的BeanName'';';
COMMENT ON COLUMN t_framework_schedule.methodname IS '''方法名'';';
COMMENT ON COLUMN t_framework_schedule.concurrent IS '''服务器IP''，如果为0.0.0.0则为全局，对每台都生效，如果为特定IP则只对特定IP生效';  

create table t_framework_clusters
(
  URL            VARCHAR(128) not null,
  LASTACTIVETIME timestamp not null,
  LASTSTATUS     VARCHAR(1) not null,
  LOCALHOST      VARCHAR(256) not null
);
alter table t_framework_clusters
  add constraint pk_framework_clusters primary key (URL, LOCALHOST);
  
  
  
CREATE TABLE t_framework_script (
  ID varchar(128) NOT NULL,
  NAME varchar(256) NOT NULL,
  CONTENT text NOT NULL,
  LOADDATE date NOT NULL
);
alter table t_framework_script
  add constraint pk_t_framework_script primary key (ID);
  
  
CREATE TABLE t_framework_script_log (
  OPERATEID varchar(20) NOT NULL,
  TYPE varchar(100) DEFAULT NULL,
  TOKEN varchar(200) DEFAULT NULL,
  SCRIPT1 text,
  SCRIPT2 text,
  SCRIPT3 text,
  CREATETIME timestamp DEFAULT NULL,
  LASTEXECUTETIME timestamp DEFAULT NULL,
  COUNT int8 DEFAULT NULL,
  RESULT varchar(1000) DEFAULT NULL,
  USERID varchar(20) DEFAULT NULL,
  DATASOURCETYPE varchar(20) DEFAULT NULL
);
alter table t_framework_script_log
  add constraint t_framework_script_log primary key (OPERATEID);
  
  
 CREATE TABLE t_framework_sv_limit (
  ID varchar(32) NOT NULL,
  URI varchar(2048) NOT NULL,
  LIMITTYPE varchar(2) NOT NULL,
  LIMITTIME numeric(10,0) NOT NULL,
  LIMITNUM numeric(4,0) DEFAULT NULL
);



