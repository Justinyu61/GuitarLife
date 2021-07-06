USE dl;

SELECT id, member_id, created_date, created_time, status, payment_type, shipping_type	
	FROM orders 
	WHERE member_id="A123123123";

SELECT id, member_id, created_date, created_time, status, payment_type, shipping_type
	,price, quantity, (price*quantity) as _total_amount
	FROM orders JOIN order_items ON orders.id = order_items.order_id
	WHERE member_id="A123123123";     
    
SELECT id, member_id, created_date, created_time, status, payment_type, shipping_type
	, sum(price*quantity) as _total_amount
	FROM orders JOIN order_items ON orders.id = order_items.order_id
	WHERE member_id="A123123123";   


/*查詢指定客戶的歷史訂單，並將購買明細的金額加總(sum)*/    
SELECT orders.id, member_id, created_date,created_time , status, payment_type, shipping_type
	, sum(price*quantity) as total_amount, (sum(price*quantity) + payment_fee + shipping_fee) as total_amount_with_fee
	FROM orders JOIN order_items ON orders.id = order_items.order_id    
	WHERE member_id='A123123123' 
    GROUP BY orders.id;   

/*檢視產品明細*/    
SELECT 	orders.id, member_id, created_date, created_time, status, 
		payment_type, payment_fee, payment_note, 
		shipping_type, shipping_fee, shipping_note, 
        recipient_name, recipient_email, recipient_phone, shipping_address,
        order_items.product_id, products.name as product_name, 
        order_items.color_name,size,price,quantity,(price*quantity) as amount
	FROM orders LEFT JOIN order_items ON orders.id = order_items.order_id  
		 LEFT JOIN products ON order_items.product_id = products.id
        
	WHERE orders.id='3';    