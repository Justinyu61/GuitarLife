/*查詢全部的產品*/
SELECT id , name, brand,unit_price, stock, description, photo_url, discount, shelf_date FROM products;

/*符合部分名稱查詢的產品*/
SELECT id , name, brand,unit_price, stock, description, 
photo_url, discount, shelf_date, type FROM products
WHERE brand LIKE '%%' AND type LIKE  '%%'; /* %XXX% 這個式查詢的寫入方式*/

/*符合部分名稱查詢的產品由新到舊*/
SELECT id , name, brand,unit_price, stock, description, 
photo_url, discount, shelf_date FROM products
WHERE name LIKE '%Faith%' /* %XXX% 這個式查詢的寫入方式*/
ORDER BY shelf_date DESC;


SELECT distinct brand /*將全部資料匯聚 同名稱內不同物件 如:同Brand的不同商品*/
	FROM products
    WHERE name LIKE '%Faith%' ;
    
SELECT branp From products;    

SELECT brand FROM products group by brand;

SELECT id, name, brand, photo_url FROM products where brand LIKE '%Martin%' ORDER BY brand;

select type from products;

select distinct type from products where type like '%吉他%';

SELECT DISTINCT type FROM products;