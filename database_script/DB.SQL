--
-- Create Schema Script 
--   Database Version   : 11.2.0.2.0 
--   TOAD Version       : 9.7.2.5 
--   DB Connect String  : XE 
--   Schema             : DB 
--   Script Created by  : DB 
--   Script Created at  : 04.02.2019 21:10:43 
--   Physical Location  :  
--   Notes              :  
--

-- Object Counts: 
--   Indexes: 8         Columns: 8          
--   Tables: 15         Columns: 107        Constraints: 10     


CREATE TABLE VENDOR_APIPULL_CACHE
(
  HASH_   VARCHAR2(32 BYTE)                     NOT NULL,
  DATA_   CLOB                                  NOT NULL,
  TIME_   NUMBER(10)                            NOT NULL,
  SITEID  VARCHAR2(100 BYTE)                    NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE USERS
(
  ID         NUMBER(10)                         NOT NULL,
  EMAIL      VARCHAR2(255 BYTE)                 NOT NULL,
  PASSWORD_  VARCHAR2(255 BYTE)                 NOT NULL,
  VERICODE   VARCHAR2(64 BYTE)                  NOT NULL,
  DISCOUNT   NUMBER(10)                         DEFAULT 0                     NOT NULL,
  USERDATA   CLOB                               NOT NULL,
  IS_SAMPLE  NUMBER(3)                          DEFAULT 0                     NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE STATS_SEARCH
(
  ID          NUMBER(19)                        NOT NULL,
  Q           VARCHAR2(255 BYTE)                NOT NULL,
  TIMESTAMP_  NUMBER(10)                        NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE SITES
(
  TITLE       VARCHAR2(100 BYTE)                NOT NULL,
  SUBTITLE    VARCHAR2(100 BYTE)                NOT NULL,
  ADMINPASS   VARCHAR2(32 BYTE)                 NOT NULL,
  ADMINEMAIL  VARCHAR2(100 BYTE)                NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE SESSIONS
(
  SESSION_ID     VARCHAR2(40 BYTE)              DEFAULT 0                     NOT NULL,
  IP_ADDRESS     VARCHAR2(45 BYTE)              DEFAULT 0                     NOT NULL,
  USER_AGENT     VARCHAR2(120 BYTE)             NOT NULL,
  LAST_ACTIVITY  NUMBER(10)                     DEFAULT 0                     NOT NULL,
  USER_DATA      CLOB                           NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE PRICES
(
  ID                NUMBER(19)                  NOT NULL,
  ART_NUMBER        VARCHAR2(100 BYTE)          NOT NULL,
  ART_NUMBER_CLEAR  VARCHAR2(100 BYTE)          NOT NULL,
  SUP_BRAND         VARCHAR2(255 BYTE)          NOT NULL,
  DESCRIPTION       VARCHAR2(255 BYTE)          NOT NULL,
  QTY               NUMBER(10)                  NOT NULL,
  PRICE             BINARY_DOUBLE               NOT NULL,
  VENDOR_ID         NUMBER(10)                  DEFAULT 1                     NOT NULL,
  IMPORT_GROUP_ID   VARCHAR2(32 BYTE)           DEFAULT 1                     NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE POST_TERMS
(
  ID         NUMBER(10)                         NOT NULL,
  TITLE      VARCHAR2(255 BYTE)                 NOT NULL,
  PERMALINK  VARCHAR2(100 BYTE)                 NOT NULL,
  IMAGE      VARCHAR2(255 BYTE)                 DEFAULT NULL,
  PARENT_ID  NUMBER(10)                         DEFAULT 0                     NOT NULL,
  ORDER_NUM  NUMBER(10)                         DEFAULT 0                     NOT NULL,
  TERM_TYPE  VARCHAR2(255 BYTE)                 DEFAULT 'category'            NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE POSTS
(
  ID            NUMBER(10)                      NOT NULL,
  TITLE         VARCHAR2(255 BYTE)              NOT NULL,
  PERMALINK     VARCHAR2(100 BYTE)              NOT NULL,
  THUMBNAIL     VARCHAR2(255 BYTE)              DEFAULT NULL,
  TEXT          CLOB,
  META          CLOB,
  META2         CLOB,
  LAST_UPDATE   NUMBER(10)                      NOT NULL,
  ALLOW_DELETE  NUMBER(3)                       DEFAULT 1                     NOT NULL,
  IS_IN_MENU    NUMBER(3)                       DEFAULT 1                     NOT NULL,
  MENU_ORDER    NUMBER(10)                      DEFAULT 0                     NOT NULL,
  TERM_ID       NUMBER(10)                      DEFAULT NULL,
  POST_TYPE     VARCHAR2(255 BYTE)              DEFAULT 'page'                NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE ORDER_ITEMS
(
  ID                  NUMBER(10)                NOT NULL,
  CARTID              VARCHAR2(40 BYTE)         DEFAULT NULL,
  ORDERID             NUMBER(10)                DEFAULT NULL,
  LINE_HASH           VARCHAR2(32 BYTE)         DEFAULT NULL,
  PRICES_LINE_ID      NUMBER(10)                DEFAULT NULL,
  ART_NUMBER          VARCHAR2(100 BYTE)        DEFAULT NULL,
  SUP_BRAND           VARCHAR2(255 BYTE)        DEFAULT NULL,
  DESCRIPTION         VARCHAR2(255 BYTE)        DEFAULT NULL,
  PRICE               BINARY_DOUBLE             DEFAULT 0.00                  NOT NULL,
  QTY                 BINARY_DOUBLE             DEFAULT 0.00                  NOT NULL,
  QTY_LIMIT           NUMBER(10)                DEFAULT 0                     NOT NULL,
  QTY_LOT_SIZE        NUMBER(10)                DEFAULT 1                     NOT NULL,
  DISCOUNT            NUMBER(10)                DEFAULT 0                     NOT NULL,
  VENDOR_ID           NUMBER(10)                DEFAULT NULL,
  VENDOR_NAME         VARCHAR2(255 BYTE)        DEFAULT NULL,
  DELIVERY_DAYS       NUMBER(10)                DEFAULT NULL,
  DELIVERY_METHOD     NUMBER(10)                DEFAULT NULL,
  IS_UPDATED_BY_USER  NUMBER(3)                 DEFAULT 0                     NOT NULL,
  STATUS              NUMBER(10)                DEFAULT 1                     NOT NULL,
  STATUS_CHANGE_DATE  NUMBER(10)                DEFAULT NULL,
  TYPE_ITEM           VARCHAR2(10 BYTE)         DEFAULT 'item'                NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE ORDERS
(
  ID                    NUMBER(10)              NOT NULL,
  ORDER_HUMAN_ID        NUMBER(10)              DEFAULT 0                     NOT NULL,
  USER_ID               NUMBER(10)              NOT NULL,
  VERICODE              VARCHAR2(40 BYTE)       NOT NULL,
  ORDER_COMMENT         VARCHAR2(255 BYTE)      DEFAULT NULL,
  AMOUNT_PAID           BINARY_DOUBLE           DEFAULT 0.00                  NOT NULL,
  DATE_                 NUMBER(10)              NOT NULL,
  ORDER_STATUS          NUMBER(10)              DEFAULT NULL,
  ORDER_STATUS_CHANGED  NUMBER(10)              DEFAULT NULL,
  OPENED_BY             NUMBER(10)              DEFAULT NULL,
  CLOSED_BY             NUMBER(10)              DEFAULT NULL,
  IS_ARCHIVED           NUMBER(3)               DEFAULT 0                     NOT NULL,
  IS_SYNC_INPROGRESS    NUMBER(3)               DEFAULT 0                     NOT NULL,
  IS_SYNCHED            NUMBER(3)               DEFAULT 0                     NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE OPTIONS
(
  ID            NUMBER(10)                      NOT NULL,
  OPTION_NAME   VARCHAR2(64 BYTE)               NOT NULL,
  OPTION_VALUE  CLOB                            NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE DELIVERY_METHODS
(
  ID            NUMBER(10)                      NOT NULL,
  TITLE         VARCHAR2(255 BYTE)              NOT NULL,
  DESCR         CLOB,
  PRICE         NUMBER(10)                      NOT NULL,
  IS_AVAILABLE  NUMBER(10)                      DEFAULT 1                     NOT NULL,
  ORDER_NUM     NUMBER(10)                      DEFAULT 0                     NOT NULL,
  IS_DEFAULT    NUMBER(10)                      DEFAULT 0                     NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE CROSSES_SEARCH
(
  ART_NUMBER_CLEAR  VARCHAR2(100 BYTE)          NOT NULL,
  LINE_ID           NUMBER(10)                  NOT NULL,
  VENDOR_ID         NUMBER(10)                  NOT NULL,
  IMPORT_GROUP_ID   VARCHAR2(32 BYTE)           NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE CROSSES
(
  ID               NUMBER(10)                   NOT NULL,
  ART_NUMBERS      CLOB                         NOT NULL,
  VENDOR_ID        NUMBER(10)                   NOT NULL,
  IMPORT_GROUP_ID  VARCHAR2(32 BYTE)            DEFAULT 1                     NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


CREATE TABLE BRANDS_LC
(
  BRAND_ID   VARCHAR2(100 BYTE)                 NOT NULL,
  ORDER_NUM  NUMBER(10)                         DEFAULT 0                     NOT NULL
)
LOGGING 
NOCOMPRESS 
NOCACHE
NOPARALLEL
MONITORING;


ALTER TABLE USERS ADD (
  PRIMARY KEY
 (ID));

ALTER TABLE STATS_SEARCH ADD (
  PRIMARY KEY
 (ID));

ALTER TABLE SESSIONS ADD (
  CHECK (last_activity > 0));

ALTER TABLE POST_TERMS ADD (
  PRIMARY KEY
 (ID));

ALTER TABLE POSTS ADD (
  PRIMARY KEY
 (ID));

ALTER TABLE ORDER_ITEMS ADD (
  CHECK (type_item in ('item','service')));

ALTER TABLE ORDERS ADD (
  PRIMARY KEY
 (ID));

ALTER TABLE OPTIONS ADD (
  PRIMARY KEY
 (ID));

ALTER TABLE DELIVERY_METHODS ADD (
  PRIMARY KEY
 (ID));

ALTER TABLE CROSSES ADD (
  PRIMARY KEY
 (ID));

