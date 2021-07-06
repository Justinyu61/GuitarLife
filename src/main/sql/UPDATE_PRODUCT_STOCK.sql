SELECT*FROM dl.products;

UPDATE products SET stock=stock-1
   WHERE id=33 AND stock>=0 ;
   
 UPDATE products SET stock=stock-1
WHERE id= 1 AND stock>=2;