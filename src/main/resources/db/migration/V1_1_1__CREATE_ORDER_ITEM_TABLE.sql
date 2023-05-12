CREATE TABLE `tb_02_order_item` (
  `id` binary(16) NOT NULL,
  `product_id` varchar(255) DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  `unit_price` double DEFAULT NULL,
  `order_id` binary(16) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_order_id` (`order_id`),
  CONSTRAINT `fk_order_id` FOREIGN KEY (`order_id`) REFERENCES `tb_01_order` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;