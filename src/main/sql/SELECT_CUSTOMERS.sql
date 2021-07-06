USE dl;

/*查詢程式-此TABLE中的全部資料*/
SELECT id, email, password, name, gender, birthday, phone, address,subscribed
FROM customers;

/*用(pkey='xxx')查詢 1筆customer*/
SELECT id, email, password, name, gender, birthday, phone, address,subscribed,discount
FROM customers
WHERE id='A123456789';

SELECT id, email, password, name, gender, birthday, phone, address,subscribed
FROM customers
WHERE id='A123123123' AND password='jasperyu61';