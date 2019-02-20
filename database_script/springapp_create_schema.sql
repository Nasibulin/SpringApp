-- Generated by Oracle SQL Developer Data Modeler 18.4.0.339.1532
--   at:        2019-02-20 08:02:21 MSK
--   site:      Oracle Database 11g
--   type:      Oracle Database 11g



CREATE user springapp identified by account unlock 
;

CREATE TABLE springapp.categories (
    id          NUMBER NOT NULL,
    parent_id   NUMBER(*, 0) NOT NULL,
    catname     VARCHAR2(255 BYTE)
)
TABLESPACE users LOGGING;

CREATE UNIQUE INDEX springapp.categories__idxv1 ON
    springapp.categories (
        id
    ASC )
        LOGGING;

CREATE INDEX springapp.categories__idx ON
    springapp.categories (
        parent_id
    ASC )
        LOGGING;

ALTER TABLE springapp.categories ADD CONSTRAINT categories_pk PRIMARY KEY ( id );

CREATE TABLE springapp.order_details (
    id            NUMBER NOT NULL,
    orders_id     NUMBER NOT NULL,
    products_id   NUMBER NOT NULL
)
TABLESPACE users LOGGING;

CREATE INDEX springapp.order_details__idx ON
    springapp.order_details (
        orders_id
    ASC )
        LOGGING;

CREATE INDEX springapp.order_details__idxv1 ON
    springapp.order_details (
        products_id
    ASC )
        LOGGING;

ALTER TABLE springapp.order_details ADD CONSTRAINT order_items_pk PRIMARY KEY ( id );

CREATE TABLE springapp.orders (
    id                          NUMBER NOT NULL,
    spring_session_primary_id   CHAR(36 BYTE) NOT NULL,
    status                      INTEGER,
    is_active                   INTEGER NOT NULL,
    created_at                  TIMESTAMP
)
TABLESPACE users LOGGING;

CREATE UNIQUE INDEX springapp.orders__idx ON
    springapp.orders (
        id
    ASC )
        LOGGING;

CREATE UNIQUE INDEX springapp.orders__idxv1 ON
    springapp.orders (
        spring_session_primary_id
    ASC )
        LOGGING;

ALTER TABLE springapp.orders ADD CONSTRAINT orders_pk PRIMARY KEY ( id );

CREATE TABLE springapp.products (
    id              NUMBER NOT NULL,
    categories_id   NUMBER NOT NULL,
    part_number     VARCHAR2(255 BYTE),
    description     VARCHAR2(255 BYTE),
    service         VARCHAR2(10 BYTE),
    price           FLOAT(2)
)
TABLESPACE users LOGGING;

CREATE UNIQUE INDEX springapp.products__idx ON
    springapp.products (
        id
    ASC )
        LOGGING;

ALTER TABLE springapp.products ADD CONSTRAINT products_pk PRIMARY KEY ( id );

CREATE TABLE springapp.spring_session (
    primary_id              CHAR(36 BYTE) NOT NULL,
    session_id              CHAR(36 BYTE) NOT NULL,
    creation_time           NUMBER(19) NOT NULL,
    last_access_time        NUMBER(19) NOT NULL,
    max_inactive_interval   NUMBER(10) NOT NULL,
    expiry_time             NUMBER(19) NOT NULL,
    principal_name          VARCHAR2(100 CHAR)
)
TABLESPACE users LOGGING;

CREATE UNIQUE INDEX springapp.spring_session_ix1 ON
    springapp.spring_session (
        session_id
    ASC )
        LOGGING;

CREATE INDEX springapp.spring_session_ix2 ON
    springapp.spring_session (
        expiry_time
    ASC )
        LOGGING;

CREATE INDEX springapp.spring_session_ix3 ON
    springapp.spring_session (
        principal_name
    ASC )
        LOGGING;

CREATE UNIQUE INDEX springapp.spring_session_pk ON
    springapp.spring_session (
        primary_id
    ASC )
        LOGGING;

ALTER TABLE springapp.spring_session ADD CONSTRAINT spring_session_pk PRIMARY KEY ( primary_id );

CREATE TABLE springapp.spring_session_attributes (
    session_primary_id   CHAR(36 BYTE) NOT NULL,
    attribute_name       VARCHAR2(200 CHAR) NOT NULL,
    attribute_bytes      BLOB NOT NULL
)
TABLESPACE users LOGGING;

CREATE UNIQUE INDEX springapp.spring_session_attributes_pk ON
    springapp.spring_session_attributes (
        session_primary_id
    ASC,
        attribute_name
    ASC )
        LOGGING;

ALTER TABLE springapp.spring_session_attributes ADD CONSTRAINT spring_session_attributes_pk PRIMARY KEY ( session_primary_id,
                                                                                                          attribute_name );

ALTER TABLE springapp.categories
    ADD CONSTRAINT categories_categories_fk FOREIGN KEY ( parent_id )
        REFERENCES springapp.categories ( id )
            ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE springapp.order_details
    ADD CONSTRAINT order_items_orders_fk FOREIGN KEY ( orders_id )
        REFERENCES springapp.orders ( id )
            ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE springapp.order_details
    ADD CONSTRAINT order_items_products_fk FOREIGN KEY ( products_id )
        REFERENCES springapp.products ( id )
            ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE springapp.orders
    ADD CONSTRAINT orders_spring_session_fk FOREIGN KEY ( spring_session_primary_id )
        REFERENCES springapp.spring_session ( primary_id )
            ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE springapp.products
    ADD CONSTRAINT products_categories_fk FOREIGN KEY ( categories_id )
        REFERENCES springapp.categories ( id )
            ON DELETE CASCADE
    NOT DEFERRABLE;

ALTER TABLE springapp.spring_session_attributes
    ADD CONSTRAINT spring_session_attributes_fk FOREIGN KEY ( session_primary_id )
        REFERENCES springapp.spring_session ( primary_id )
            ON DELETE CASCADE
    NOT DEFERRABLE;

CREATE SEQUENCE springapp.categories_id_seq START WITH 1200 CACHE 20 ORDER;

CREATE OR REPLACE TRIGGER springapp.categories_id_trg BEFORE
    INSERT ON springapp.categories
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := springapp.categories_id_seq.nextval;
END;
/

CREATE SEQUENCE springapp.order_details_id_seq START WITH 1 CACHE 20 ORDER;

CREATE OR REPLACE TRIGGER springapp.order_details_id_trg BEFORE
    INSERT ON springapp.order_details
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := springapp.order_details_id_seq.nextval;
END;
/

CREATE SEQUENCE springapp.orders_id_seq START WITH 1 CACHE 20 ORDER;

CREATE OR REPLACE TRIGGER springapp.orders_id_trg BEFORE
    INSERT ON springapp.orders
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := springapp.orders_id_seq.nextval;
END;
/

CREATE SEQUENCE springapp.products_id_seq START WITH 19000 CACHE 20 ORDER;

CREATE OR REPLACE TRIGGER springapp.products_id_trg BEFORE
    INSERT ON springapp.products
    FOR EACH ROW
    WHEN ( new.id IS NULL )
BEGIN
    :new.id := springapp.products_id_seq.nextval;
END;
/



-- Oracle SQL Developer Data Modeler Summary Report: 
-- 
-- CREATE TABLE                             6
-- CREATE INDEX                            12
-- ALTER TABLE                             12
-- CREATE VIEW                              0
-- ALTER VIEW                               0
-- CREATE PACKAGE                           0
-- CREATE PACKAGE BODY                      0
-- CREATE PROCEDURE                         0
-- CREATE FUNCTION                          0
-- CREATE TRIGGER                           4
-- ALTER TRIGGER                            0
-- CREATE COLLECTION TYPE                   0
-- CREATE STRUCTURED TYPE                   0
-- CREATE STRUCTURED TYPE BODY              0
-- CREATE CLUSTER                           0
-- CREATE CONTEXT                           0
-- CREATE DATABASE                          0
-- CREATE DIMENSION                         0
-- CREATE DIRECTORY                         0
-- CREATE DISK GROUP                        0
-- CREATE ROLE                              0
-- CREATE ROLLBACK SEGMENT                  0
-- CREATE SEQUENCE                          4
-- CREATE MATERIALIZED VIEW                 0
-- CREATE MATERIALIZED VIEW LOG             0
-- CREATE SYNONYM                           0
-- CREATE TABLESPACE                        0
-- CREATE USER                              1
-- 
-- DROP TABLESPACE                          0
-- DROP DATABASE                            0
-- 
-- REDACTION POLICY                         0
-- 
-- ORDS DROP SCHEMA                         0
-- ORDS ENABLE SCHEMA                       0
-- ORDS ENABLE OBJECT                       0
-- 
-- ERRORS                                   0
-- WARNINGS                                 0
