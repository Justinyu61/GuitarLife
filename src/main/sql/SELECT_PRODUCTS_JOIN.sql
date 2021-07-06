SELECT id, name, unit_price, stock, description, 
	photo_url, discount, shelf_date, type 
    FROM products WHERE id = '33';

SELECT brand_id,photo_url,stock
	FROM brand
    WHERE brand = '1';

/*CROSS JOIN*/
SELECT id,brand_id,brand.name,name, brand, unit_price,brand.stock,stock, photo_url,shelf_date
FROM products JOIN brand;/*有JOIN沒有ON...的JOIN竟是CROSS JOIN*/
/*WHERE id = 33;*/
/*INNER JOIN*/
SELECT id,brand_id,name,brand.name, unit_price,brand.stock,stock, photo_url
FROM brand INNER JOIN products on id=product_id;
/*LEFT/RIGHT/FULL(MySQL不支援FULL OUTER JOIN)OUTER JOIN*/
/*LEFT JOIN*/
SELECT id,brand_id,name,brand.name, unit_price,brand.stock,stock, photo_url
FROM products LEFT OUTER JOIN brand on id=product_id
where id='33';

SELECT brand, COUNT(brand) FROM products GROUP BY brand;

/*RIGHT JOIN*/
SELECT id,brand_id,name,brand.name, unit_price,brand.stock,stock, photo_url
FROM brand right outer JOIN products on id=product_id
where id='33';