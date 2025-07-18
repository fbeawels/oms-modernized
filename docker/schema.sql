-- Schema SQL pour la base de données H2 OMS
-- Combinaison des schémas de tous les microservices

DROP ALL OBJECTS;

CREATE SEQUENCE HIBERNATE_SEQUENCE START WITH 1 INCREMENT BY 1;

-- Tables communes à plusieurs microservices
CREATE TABLE BILL_TO_ADDRESS (
  BILL_TO_ADDRESS_ID VARCHAR2(20) NOT NULL,
  FIRST_NAME VARCHAR2(20),
  LAST_NAME VARCHAR2(20),
  ADDRESS_1 VARCHAR2(100),
  ADDRESS_2 VARCHAR2(100),
  CITY VARCHAR2(20),
  STATE VARCHAR2(20),
  ZIP_CODE VARCHAR2(10),
  PRIMARY KEY(BILL_TO_ADDRESS_ID)
);

CREATE TABLE SHIP_TO_ADDRESS (
  SHIP_TO_ADDRESS_ID VARCHAR2(20) NOT NULL,
  FIRST_NAME VARCHAR2(20),
  LAST_NAME VARCHAR2(20),
  ADDRESS_1 VARCHAR2(100),
  ADDRESS_2 VARCHAR2(100),
  CITY VARCHAR2(20),
  STATE VARCHAR2(20),
  ZIP_CODE VARCHAR2(10),
  PRIMARY KEY(SHIP_TO_ADDRESS_ID)
);

CREATE TABLE PAYMENT_INFO (
  PAYMENT_ID VARCHAR2(20) NOT NULL,
  PAYMENT_STATUS VARCHAR2(20),
  CARD_TYPE VARCHAR2(20),
  AUTHORIZED_AMOUNT NUMBER,
  COLLECTED_AMOUNT NUMBER,
  PRIMARY KEY(PAYMENT_ID)
);

CREATE TABLE CHARGES (
  CHARGES_ID VARCHAR2(20) NOT NULL,
  LINE_SUB_TOTAL NUMBER,
  TOTAL_CHARGES NUMBER,
  SALES_TAX NUMBER,
  GRAND_TOTAL NUMBER,
  PRIMARY KEY(CHARGES_ID)
);

CREATE TABLE ORDER_LINE (
  LINE_ITEM_ID VARCHAR2(20) NOT NULL,
  CUSTOMER_ORDER_ID VARCHAR2(20),
  SHIP_NODE VARCHAR2(20),
  SHIP_NODE_DESC VARCHAR2(20),
  LEVEL_OF_SERVICE VARCHAR2(10),
  PRIME_LINE_NO VARCHAR2(10),
  SUB_LINE_NO VARCHAR2(10),
  CUSTOMER_SKU VARCHAR2(20),
  SKU_DESCRIPTION VARCHAR2(40),
  EST_ARRIVAL_DATE VARCHAR2(20),
  STATUS VARCHAR2(10),
  RESHIPPED_BEFORE VARCHAR2(10),
  SHIP_TO_ADDRESS_ID VARCHAR2(20),
  LINE_CHARGE_ID VARCHAR2(20),
  PRIMARY KEY(LINE_ITEM_ID)
);

CREATE TABLE SALES_ORDER (
  CUSTOMER_ORDER_ID VARCHAR2(20) NOT NULL,
  PRIMARY_PHONE VARCHAR2(20),
  CUSTOMER_EMAIL_ID VARCHAR2(20),
  ORDER_STATUS VARCHAR2(20),
  FIRST_NAME VARCHAR2(20),
  ORDER_DATE VARCHAR2(20),
  PROFILE_ID VARCHAR2(20),
  LAST_NAME VARCHAR2(20),
  ENTRY_TYPE VARCHAR2(20),
  BILL_TO_ADDRESS_ID VARCHAR2(20),
  SHIP_TO_ADDRESS_ID VARCHAR2(20),
  LINE_ITEM_ID VARCHAR2(20),
  PAYMENT_ID VARCHAR2(20),
  CHARGES_ID VARCHAR2(20),
  PRIMARY KEY(CUSTOMER_ORDER_ID)
);

CREATE TABLE INVENTORY (
  SKU_ID VARCHAR2(20) NOT NULL,
  STORE_ID VARCHAR2(20),
  QUANTITY NUMBER,
  PRIMARY KEY(SKU_ID, STORE_ID)
);

CREATE TABLE SHIPPING (
  SKU_ID VARCHAR2(20) NOT NULL,
  STANDARD_SHIPPING NUMBER,
  EXPEDITED_SHIPPING NUMBER,
  EXPRESS_SHIPPING NUMBER,
  PRIMARY KEY(SKU_ID)
);

CREATE TABLE LINE_CHARGE (
  LINE_CHARGE_ID VARCHAR2(20) NOT NULL,
  TOTAL_CHARGES NUMBER,
  PRIMARY KEY(LINE_CHARGE_ID)
);

CREATE TABLE PRODUCT_INFO (
  PRODUCT_ID VARCHAR2(20) NOT NULL,
  NAME VARCHAR2(20),
  DESCRIPTION VARCHAR2(128),
  MANUFACTURER VARCHAR2(20),
  PRIMARY KEY(PRODUCT_ID)
);

-- Table pour le Store Search Controller
CREATE TABLE STORE (
  STORE_ID VARCHAR2(20) NOT NULL,
  STORE_NAME VARCHAR2(50),
  ADDRESS VARCHAR2(100),
  CITY VARCHAR2(20),
  STATE VARCHAR2(20),
  ZIP_CODE VARCHAR2(10),
  PHONE VARCHAR2(20),
  PRIMARY KEY(STORE_ID)
);

-- Table pour le Email Service
CREATE TABLE EMAIL_TEMPLATE (
  TEMPLATE_ID VARCHAR2(20) NOT NULL,
  TEMPLATE_NAME VARCHAR2(50),
  TEMPLATE_SUBJECT VARCHAR2(100),
  TEMPLATE_BODY CLOB,
  PRIMARY KEY(TEMPLATE_ID)
);

CREATE TABLE EMAIL_HISTORY (
  EMAIL_ID VARCHAR2(20) NOT NULL,
  CUSTOMER_ORDER_ID VARCHAR2(20),
  EMAIL_TO VARCHAR2(100),
  EMAIL_SUBJECT VARCHAR2(100),
  EMAIL_BODY CLOB,
  SENT_DATE TIMESTAMP,
  STATUS VARCHAR2(20),
  PRIMARY KEY(EMAIL_ID)
);

-- Table pour le Fulfillment Controller
CREATE TABLE FULFILLMENT (
  FULFILLMENT_ID VARCHAR2(20) NOT NULL,
  LINE_ITEM_ID VARCHAR2(20),
  FULFILLMENT_STATUS VARCHAR2(20),
  TRACKING_NUMBER VARCHAR2(50),
  CARRIER VARCHAR2(20),
  SHIP_DATE TIMESTAMP,
  PRIMARY KEY(FULFILLMENT_ID)
);
