USE dl;

INSERT INTO customers 
   (id, email, password, name, gender, birthday)
   value('A123456789','xxx@uuu.com.tw','justinyu61','JustinYu','M','1990-03-20');

INSERT INTO customers 
   (id, email, password, name, gender, birthday, phone, address, subscribed, discount)
   value('A123123123','yyy@uuu.com.tw','jasperyu61','JasperYu','M','1991-11-28','0989999963','新北市很遠區每天早起路', true,15);

INSERT INTO customers 
   (id, email, password, name, gender, birthday, phone, address, subscribed)
   value(?,?,?,?,?,?,
     ?,?,?);