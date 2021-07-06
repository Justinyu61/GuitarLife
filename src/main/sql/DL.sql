CREATE DATABASE `dl` 
/*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ 
/*!80016 DEFAULT ENCRYPTION='N' */;


/*失效日期: 2021-01-15 04:02
CREATE TABLE `customers` (
  `id` char(10) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `gender` char(1) NOT NULL,
  `birthday` date NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
*/

/*失效日期:2021-1-15 04:04
CREATE TABLE `customers` (
  `id` char(10) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `gender` char(1) NOT NULL,
  `birthday` date NOT NULL,
  `phone` varchar(20) NOT NULL DEFAULT '',
  `address` varchar(100) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
*/

CREATE TABLE `customers` (/*於2021-01-15建立*/
  `id` char(10) NOT NULL,
  `email` varchar(60) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `gender` char(1) NOT NULL,
  `birthday` date NOT NULL,
  `phone` varchar(20) NOT NULL DEFAULT '',
  `address` varchar(100) NOT NULL DEFAULT '',
  `subscribed` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `email_UNIQUE` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `orders` (
  `id` int NOT NULL,
  `member_id` char(10) NOT NULL,
  `created_date` date NOT NULL,
  `created_time` time NOT NULL,
  `status` int NOT NULL DEFAULT '0',
  `payment_type` varchar(10) NOT NULL,
  `payment_fee` double NOT NULL DEFAULT '0',
  `payment_note` varchar(100) NOT NULL DEFAULT '',
  `shipping_type` varchar(45) NOT NULL,
  `shippong_fee` double NOT NULL DEFAULT '0',
  `shipping_note` varchar(100) NOT NULL DEFAULT '',
  `recipient_name` varchar(20) NOT NULL,
  `recipient_email` varchar(60) NOT NULL,
  `recipient_phone` varchar(20) NOT NULL,
  `shipping_address` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_orders_TO_customers_idx` (`member_id`),
  CONSTRAINT `fk_orders_TO_customers` FOREIGN KEY (`member_id`) REFERENCES `customers` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `order_items` (
  `order_id` int NOT NULL,
  `product_id` int NOT NULL,
  `brand` varchar(45) NOT NULL,
  `color_name` varchar(45) NOT NULL DEFAULT '',
  `size` varchar(10) NOT NULL DEFAULT '',
  `quantity` double NOT NULL,
  `price` int NOT NULL DEFAULT '0',
  PRIMARY KEY (`order_id`,`product_id`,`brand`,`color_name`,`size`),
  CONSTRAINT `fk_order_items_TO_orders` FOREIGN KEY (`order_id`) REFERENCES `orders` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

