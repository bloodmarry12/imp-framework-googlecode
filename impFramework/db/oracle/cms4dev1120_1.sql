------------------------------------------------
-- Export file for user CMS4DEV               --
-- Created by yuzq on 2009-11-20, 上午 10:08:20 --
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
  is '配置关键字';
comment on column T_FRAMEWORK_CONFIG.NAME
  is '配置名称';
comment on column T_FRAMEWORK_CONFIG.VALUE
  is '配置值';
comment on column T_FRAMEWORK_CONFIG.DESCRIPTION
  is '配置描述项';
comment on column T_FRAMEWORK_CONFIG.SCOPE
  is '配置所属范围 0:全局 1:引入和综合管理平台 2:分发平台 3:合作管理平台 -1:服务器';
comment on column T_FRAMEWORK_CONFIG.ADDRESS
  is '服务器地址 默认:0.0.0.0';
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
  is 'FTP别名ID';
comment on column T_FRAMEWORK_FTP.USERNAME
  is '用户名';
comment on column T_FRAMEWORK_FTP.USERPASSWORD
  is '密码';
comment on column T_FRAMEWORK_FTP.IP
  is '地址';
comment on column T_FRAMEWORK_FTP.PORT
  is '端口号';
comment on column T_FRAMEWORK_FTP.DEFAULTPATH
  is '默认路径';
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
  is '主键ID，与当前日志ID相等；根据该ID可以追查文件日志，文件日志中拦截了业务层的输入返回和异常';
comment on column T_FRAMEWORK_HTTP_LOG.OPERATEDATE
  is '操作日期';
comment on column T_FRAMEWORK_HTTP_LOG.URL
  is '当前请求的资源URI';
comment on column T_FRAMEWORK_HTTP_LOG.AID
  is '操作的账户ID，这里没有冗余账户名称，通过关联T_FRAMEWORK_PRI_ACCOUNT连查';
comment on column T_FRAMEWORK_HTTP_LOG.PARAMETERS
  is '请求参数信息，迭代记录getParamterMap中的参数键值对';
comment on column T_FRAMEWORK_HTTP_LOG.DESCRIPTION
  is '描述信息，用户扩展附加日志信息，例如备份文件地址；';
comment on column T_FRAMEWORK_HTTP_LOG.REMOTEIP
  is '远程请求的IP';
comment on column T_FRAMEWORK_HTTP_LOG.OPERATETIME
  is '当前controller的处理时间；';
comment on column T_FRAMEWORK_HTTP_LOG.LASTURL
  is '发起请求的地址，即从哪个页面请求发起的当前请求；';
comment on column T_FRAMEWORK_HTTP_LOG.SESSIONID
  is '请的sessionID';
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
  is '用户登录名称(SPID/CPID)';
comment on column T_FRAMEWORK_PRI_ACCOUNT.PASSWORD
  is '用户密码';
comment on column T_FRAMEWORK_PRI_ACCOUNT.STATUS
  is '状态:-1新增、0等待激活、1激活、2停用、3删除';
comment on column T_FRAMEWORK_PRI_ACCOUNT.OPERATORCONTACT
  is '操作人联系方式';
comment on column T_FRAMEWORK_PRI_ACCOUNT.OPERATOR
  is '操作人名称';
comment on column T_FRAMEWORK_PRI_ACCOUNT.ROLEID
  is '角色';
comment on column T_FRAMEWORK_PRI_ACCOUNT.LASTLOGINTIME
  is '最后登录系统时间';
comment on column T_FRAMEWORK_PRI_ACCOUNT.CREATETIME
  is '账户创建时间';
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
  is '流水号';
comment on column T_FRAMEWORK_PRI_RIGHT.CODE
  is '业务编码,业务根据该编码来确定权限对象';
comment on column T_FRAMEWORK_PRI_RIGHT.RIGHTNAME
  is '权限名称';
comment on column T_FRAMEWORK_PRI_RIGHT.PARENTID
  is '父类权限';
comment on column T_FRAMEWORK_PRI_RIGHT.DESCRIPTION
  is '权限描述信息';
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
  is '菜单显示内容';
comment on column T_FRAMEWORK_PRI_EXPLORER.PATH
  is '菜单链接路径';
comment on column T_FRAMEWORK_PRI_EXPLORER.PARENTID
  is '菜单父节点(默认为0)';
comment on column T_FRAMEWORK_PRI_EXPLORER.RIGHTID
  is '权限ID';
comment on column T_FRAMEWORK_PRI_EXPLORER.EORDER
  is '显示排序';
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
  is '角色名称';
comment on column T_FRAMEWORK_PRI_ROLE.DESCRIPTION
  is '角色描述';
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
  is '定时任务名称';
comment on column T_FRAMEWORK_SCHEDULE.DESCRIPTION
  is '定时任务描述';
comment on column T_FRAMEWORK_SCHEDULE.STATUS
  is '状态:0-运行,1-停止';
comment on column T_FRAMEWORK_SCHEDULE.CEXP
  is '时间正则表达式';
comment on column T_FRAMEWORK_SCHEDULE.BEANNAME
  is 'IOC容器中的BeanName';
comment on column T_FRAMEWORK_SCHEDULE.METHODNAME
  is '方法名';
comment on column T_FRAMEWORK_SCHEDULE.CONCURRENT
  is '是否并发';
comment on column T_FRAMEWORK_SCHEDULE.HOSTIP
  is '服务器IP';
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
