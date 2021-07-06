
SELECT name ,img FROM brand;

SELECT distinct brand/*distinct 將全部資料匯聚 同名稱內不同物件 如:同Brand的不同商品*/
	FROM products
    WHERE name like'%%' ;
    
    SELECT brand FROM products;
    
   