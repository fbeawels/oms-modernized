-- Data SQL pour la base de données H2 OMS
-- Combinaison des données de test pour tous les microservices

-- Adresses de facturation
INSERT INTO BILL_TO_ADDRESS (BILL_TO_ADDRESS_ID, FIRST_NAME, LAST_NAME, ADDRESS_1, ADDRESS_2, CITY, STATE, ZIP_CODE) 
VALUES ('BILL001', 'Jean', 'Dupont', '123 Rue du Commerce', 'Apt 456', 'Paris', 'IDF', '75001');

INSERT INTO BILL_TO_ADDRESS (BILL_TO_ADDRESS_ID, FIRST_NAME, LAST_NAME, ADDRESS_1, ADDRESS_2, CITY, STATE, ZIP_CODE) 
VALUES ('BILL002', 'Marie', 'Martin', '45 Avenue des Affaires', 'Etage 3', 'Lyon', 'RA', '69002');

INSERT INTO BILL_TO_ADDRESS (BILL_TO_ADDRESS_ID, FIRST_NAME, LAST_NAME, ADDRESS_1, ADDRESS_2, CITY, STATE, ZIP_CODE) 
VALUES ('BILL003', 'Pierre', 'Durand', '78 Boulevard Haussmann', NULL, 'Paris', 'IDF', '75008');

-- Adresses de livraison
INSERT INTO SHIP_TO_ADDRESS (SHIP_TO_ADDRESS_ID, FIRST_NAME, LAST_NAME, ADDRESS_1, ADDRESS_2, CITY, STATE, ZIP_CODE) 
VALUES ('SHIP001', 'Jean', 'Dupont', '123 Rue du Commerce', 'Apt 456', 'Paris', 'IDF', '75001');

INSERT INTO SHIP_TO_ADDRESS (SHIP_TO_ADDRESS_ID, FIRST_NAME, LAST_NAME, ADDRESS_1, ADDRESS_2, CITY, STATE, ZIP_CODE) 
VALUES ('SHIP002', 'Marie', 'Martin', '45 Avenue des Affaires', 'Etage 3', 'Lyon', 'RA', '69002');

INSERT INTO SHIP_TO_ADDRESS (SHIP_TO_ADDRESS_ID, FIRST_NAME, LAST_NAME, ADDRESS_1, ADDRESS_2, CITY, STATE, ZIP_CODE) 
VALUES ('SHIP003', 'Pierre', 'Durand', '78 Boulevard Haussmann', NULL, 'Paris', 'IDF', '75008');

-- Informations de paiement
INSERT INTO PAYMENT_INFO (PAYMENT_ID, PAYMENT_STATUS, CARD_TYPE, AUTHORIZED_AMOUNT, COLLECTED_AMOUNT) 
VALUES ('PAY001', 'COMPLETED', 'VISA', 73.17, 73.17);

INSERT INTO PAYMENT_INFO (PAYMENT_ID, PAYMENT_STATUS, CARD_TYPE, AUTHORIZED_AMOUNT, COLLECTED_AMOUNT) 
VALUES ('PAY002', 'COMPLETED', 'MASTERCARD', 120.48, 120.48);

INSERT INTO PAYMENT_INFO (PAYMENT_ID, PAYMENT_STATUS, CARD_TYPE, AUTHORIZED_AMOUNT, COLLECTED_AMOUNT) 
VALUES ('PAY003', 'AUTHORIZED', 'VISA', 89.99, 0.00);

-- Frais
INSERT INTO CHARGES (CHARGES_ID, LINE_SUB_TOTAL, TOTAL_CHARGES, SALES_TAX, GRAND_TOTAL) 
VALUES ('CHARGE001', 59.98, 5.99, 7.20, 73.17);

INSERT INTO CHARGES (CHARGES_ID, LINE_SUB_TOTAL, TOTAL_CHARGES, SALES_TAX, GRAND_TOTAL) 
VALUES ('CHARGE002', 99.99, 7.99, 12.50, 120.48);

INSERT INTO CHARGES (CHARGES_ID, LINE_SUB_TOTAL, TOTAL_CHARGES, SALES_TAX, GRAND_TOTAL) 
VALUES ('CHARGE003', 79.99, 4.99, 5.01, 89.99);

-- Frais de ligne
INSERT INTO LINE_CHARGE (LINE_CHARGE_ID, TOTAL_CHARGES) 
VALUES ('LC001', 65.97);

INSERT INTO LINE_CHARGE (LINE_CHARGE_ID, TOTAL_CHARGES) 
VALUES ('LC002', 107.98);

INSERT INTO LINE_CHARGE (LINE_CHARGE_ID, TOTAL_CHARGES) 
VALUES ('LC003', 84.98);

-- Commandes
INSERT INTO SALES_ORDER (CUSTOMER_ORDER_ID, PRIMARY_PHONE, CUSTOMER_EMAIL_ID, ORDER_STATUS, FIRST_NAME, 
                         ORDER_DATE, PROFILE_ID, LAST_NAME, ENTRY_TYPE, BILL_TO_ADDRESS_ID, SHIP_TO_ADDRESS_ID, 
                         PAYMENT_ID, CHARGES_ID) 
VALUES ('ORD001', '0123456789', 'jean.dupont@ex.com', 'COMPLETED', 'Jean', '2025-05-01', 'PROF001', 
        'Dupont', 'ONLINE', 'BILL001', 'SHIP001', 'PAY001', 'CHARGE001');

INSERT INTO SALES_ORDER (CUSTOMER_ORDER_ID, PRIMARY_PHONE, CUSTOMER_EMAIL_ID, ORDER_STATUS, FIRST_NAME, 
                         ORDER_DATE, PROFILE_ID, LAST_NAME, ENTRY_TYPE, BILL_TO_ADDRESS_ID, SHIP_TO_ADDRESS_ID, 
                         PAYMENT_ID, CHARGES_ID) 
VALUES ('ORD002', '0987654321', 'marie.martin@ex.com', 'PROCESSING', 'Marie', '2025-05-02', 'PROF002', 
        'Martin', 'ONLINE', 'BILL002', 'SHIP002', 'PAY002', 'CHARGE002');

INSERT INTO SALES_ORDER (CUSTOMER_ORDER_ID, PRIMARY_PHONE, CUSTOMER_EMAIL_ID, ORDER_STATUS, FIRST_NAME, 
                         ORDER_DATE, PROFILE_ID, LAST_NAME, ENTRY_TYPE, BILL_TO_ADDRESS_ID, SHIP_TO_ADDRESS_ID, 
                         PAYMENT_ID, CHARGES_ID) 
VALUES ('ORD003', '0654321987', 'pierre.durand@ex.com', 'PENDING', 'Pierre', '2025-05-02', 'PROF003', 
        'Durand', 'ONLINE', 'BILL003', 'SHIP003', 'PAY003', 'CHARGE003');

-- Lignes de commande
INSERT INTO ORDER_LINE (LINE_ITEM_ID, STATUS, CUSTOMER_ORDER_ID, CUSTOMER_SKU, SKU_DESCRIPTION, SHIP_TO_ADDRESS_ID, LINE_CHARGE_ID) 
VALUES ('LI001', 'SHIPPED', 'ORD001', 'SKU001', 'Smartphone haut de gamme', 'SHIP001', 'LC001');

INSERT INTO ORDER_LINE (LINE_ITEM_ID, STATUS, CUSTOMER_ORDER_ID, CUSTOMER_SKU, SKU_DESCRIPTION, SHIP_TO_ADDRESS_ID, LINE_CHARGE_ID) 
VALUES ('LI002', 'PROCESSING', 'ORD001', 'SKU002', 'Coque de protection', 'SHIP001', 'LC001');

INSERT INTO ORDER_LINE (LINE_ITEM_ID, STATUS, CUSTOMER_ORDER_ID, CUSTOMER_SKU, SKU_DESCRIPTION, SHIP_TO_ADDRESS_ID, LINE_CHARGE_ID) 
VALUES ('LI003', 'PROCESSING', 'ORD002', 'SKU003', 'Tablette tactile', 'SHIP002', 'LC002');

INSERT INTO ORDER_LINE (LINE_ITEM_ID, STATUS, CUSTOMER_ORDER_ID, CUSTOMER_SKU, SKU_DESCRIPTION, SHIP_TO_ADDRESS_ID, LINE_CHARGE_ID) 
VALUES ('LI004', 'PROCESSING', 'ORD002', 'SKU004', 'Chargeur rapide', 'SHIP002', 'LC002');

INSERT INTO ORDER_LINE (LINE_ITEM_ID, STATUS, CUSTOMER_ORDER_ID, CUSTOMER_SKU, SKU_DESCRIPTION, SHIP_TO_ADDRESS_ID, LINE_CHARGE_ID) 
VALUES ('LI005', 'PENDING', 'ORD003', 'SKU005', 'Écouteurs sans fil', 'SHIP003', 'LC003');

-- Inventaire
INSERT INTO INVENTORY (SKU_ID, STORE_ID, QUANTITY) 
VALUES ('SKU001', 'STORE001', 10);

INSERT INTO INVENTORY (SKU_ID, STORE_ID, QUANTITY) 
VALUES ('SKU002', 'STORE001', 25);

INSERT INTO INVENTORY (SKU_ID, STORE_ID, QUANTITY) 
VALUES ('SKU003', 'STORE002', 15);

INSERT INTO INVENTORY (SKU_ID, STORE_ID, QUANTITY) 
VALUES ('SKU004', 'STORE002', 30);

INSERT INTO INVENTORY (SKU_ID, STORE_ID, QUANTITY) 
VALUES ('SKU005', 'STORE003', 20);

-- Frais de livraison
INSERT INTO SHIPPING (SKU_ID, STANDARD_SHIPPING, EXPEDITED_SHIPPING, EXPRESS_SHIPPING) 
VALUES ('SKU001', 5.99, 9.99, 14.99);

INSERT INTO SHIPPING (SKU_ID, STANDARD_SHIPPING, EXPEDITED_SHIPPING, EXPRESS_SHIPPING) 
VALUES ('SKU002', 2.99, 5.99, 8.99);

INSERT INTO SHIPPING (SKU_ID, STANDARD_SHIPPING, EXPEDITED_SHIPPING, EXPRESS_SHIPPING) 
VALUES ('SKU003', 7.99, 12.99, 18.99);

INSERT INTO SHIPPING (SKU_ID, STANDARD_SHIPPING, EXPEDITED_SHIPPING, EXPRESS_SHIPPING) 
VALUES ('SKU004', 3.99, 6.99, 9.99);

INSERT INTO SHIPPING (SKU_ID, STANDARD_SHIPPING, EXPEDITED_SHIPPING, EXPRESS_SHIPPING) 
VALUES ('SKU005', 4.99, 8.99, 12.99);

-- Informations produit
INSERT INTO PRODUCT_INFO (PRODUCT_ID, NAME, DESCRIPTION, MANUFACTURER) 
VALUES ('SKU001', 'Smartphone', 'Smartphone haut de gamme', 'TechCorp');

INSERT INTO PRODUCT_INFO (PRODUCT_ID, NAME, DESCRIPTION, MANUFACTURER) 
VALUES ('SKU002', 'Coque', 'Coque de protection', 'AccessCorp');

INSERT INTO PRODUCT_INFO (PRODUCT_ID, NAME, DESCRIPTION, MANUFACTURER) 
VALUES ('SKU003', 'Tablette', 'Tablette tactile', 'TechCorp');

INSERT INTO PRODUCT_INFO (PRODUCT_ID, NAME, DESCRIPTION, MANUFACTURER) 
VALUES ('SKU004', 'Chargeur', 'Chargeur rapide', 'PowerCorp');

INSERT INTO PRODUCT_INFO (PRODUCT_ID, NAME, DESCRIPTION, MANUFACTURER) 
VALUES ('SKU005', 'Écouteurs', 'Écouteurs sans fil', 'AudioCorp');

-- Magasins
INSERT INTO STORE (STORE_ID, STORE_NAME, ADDRESS, CITY, STATE, ZIP_CODE, PHONE) 
VALUES ('STORE001', 'Magasin Paris Centre', '123 Rue de Rivoli', 'Paris', 'IDF', '75001', '0123456789');

INSERT INTO STORE (STORE_ID, STORE_NAME, ADDRESS, CITY, STATE, ZIP_CODE, PHONE) 
VALUES ('STORE002', 'Magasin Paris Est', '45 Avenue des Ternes', 'Paris', 'IDF', '75017', '0123456790');

INSERT INTO STORE (STORE_ID, STORE_NAME, ADDRESS, CITY, STATE, ZIP_CODE, PHONE) 
VALUES ('STORE003', 'Magasin Lyon Centre', '78 Rue de la République', 'Lyon', 'RA', '69002', '0123456791');

INSERT INTO STORE (STORE_ID, STORE_NAME, ADDRESS, CITY, STATE, ZIP_CODE, PHONE) 
VALUES ('STORE004', 'Magasin Marseille', '56 La Canebière', 'Marseille', 'PACA', '13001', '0123456792');

-- Templates d'email
INSERT INTO EMAIL_TEMPLATE (TEMPLATE_ID, TEMPLATE_NAME, TEMPLATE_SUBJECT, TEMPLATE_BODY) 
VALUES ('TEMPLATE001', 'Confirmation de commande', 'Confirmation de votre commande #{orderId}', 'Cher {firstName} {lastName},\n\nNous vous confirmons la réception de votre commande #{orderId}.\n\nMerci pour votre achat!');

INSERT INTO EMAIL_TEMPLATE (TEMPLATE_ID, TEMPLATE_NAME, TEMPLATE_SUBJECT, TEMPLATE_BODY) 
VALUES ('TEMPLATE002', 'Expédition de commande', 'Votre commande #{orderId} a été expédiée', 'Cher {firstName} {lastName},\n\nVotre commande #{orderId} a été expédiée.\n\nVous pouvez suivre votre colis avec le numéro de suivi: {trackingNumber}');

-- Historique des emails
INSERT INTO EMAIL_HISTORY (EMAIL_ID, CUSTOMER_ORDER_ID, EMAIL_TO, EMAIL_SUBJECT, EMAIL_BODY, SENT_DATE, STATUS) 
VALUES ('EMAIL001', 'ORD001', 'jean.dupont@ex.com', 'Confirmation de votre commande #ORD001', 'Cher Jean Dupont,\n\nNous vous confirmons la réception de votre commande #ORD001.\n\nMerci pour votre achat!', TIMESTAMP '2025-05-01 10:30:00', 'SENT');

INSERT INTO EMAIL_HISTORY (EMAIL_ID, CUSTOMER_ORDER_ID, EMAIL_TO, EMAIL_SUBJECT, EMAIL_BODY, SENT_DATE, STATUS) 
VALUES ('EMAIL002', 'ORD001', 'jean.dupont@ex.com', 'Votre commande #ORD001 a été expédiée', 'Cher Jean Dupont,\n\nVotre commande #ORD001 a été expédiée.\n\nVous pouvez suivre votre colis avec le numéro de suivi: TRK123456789', TIMESTAMP '2025-05-02 14:45:00', 'SENT');

-- Fulfillment
INSERT INTO FULFILLMENT (FULFILLMENT_ID, LINE_ITEM_ID, FULFILLMENT_STATUS, TRACKING_NUMBER, CARRIER, SHIP_DATE) 
VALUES ('FUL001', 'LI001', 'SHIPPED', 'TRK123456789', 'DHL', TIMESTAMP '2025-05-02 14:30:00');

INSERT INTO FULFILLMENT (FULFILLMENT_ID, LINE_ITEM_ID, FULFILLMENT_STATUS, TRACKING_NUMBER, CARRIER, SHIP_DATE) 
VALUES ('FUL002', 'LI002', 'PROCESSING', NULL, NULL, NULL);

INSERT INTO FULFILLMENT (FULFILLMENT_ID, LINE_ITEM_ID, FULFILLMENT_STATUS, TRACKING_NUMBER, CARRIER, SHIP_DATE) 
VALUES ('FUL003', 'LI003', 'PROCESSING', NULL, NULL, NULL);

INSERT INTO FULFILLMENT (FULFILLMENT_ID, LINE_ITEM_ID, FULFILLMENT_STATUS, TRACKING_NUMBER, CARRIER, SHIP_DATE) 
VALUES ('FUL004', 'LI004', 'PROCESSING', NULL, NULL, NULL);

INSERT INTO FULFILLMENT (FULFILLMENT_ID, LINE_ITEM_ID, FULFILLMENT_STATUS, TRACKING_NUMBER, CARRIER, SHIP_DATE) 
VALUES ('FUL005', 'LI005', 'PENDING', NULL, NULL, NULL);
