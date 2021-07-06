INSERT INTO orders
(id, member_id, created_date, created_time, status, 
payment_type, payment_fee, shipping_type, shippong_fee, 
recipient_name, recipient_email, recipient_phone, shipping_address)
VALUE(0,?,?,0,?,?,?,?,?,?,?,?,?);

INSERT INTO orders_items
(order_id, product_id, brand, color_name, size, quantity, price)
VALUE(?,?,?,?,?,?,?);