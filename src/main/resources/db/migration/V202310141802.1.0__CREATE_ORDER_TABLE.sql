CREATE TABLE IF NOT EXISTS `tb_01_order` (
  `id` binary(16) NOT NULL,
  `create_date` date DEFAULT NULL,
  `customer_cpf` varchar(255) DEFAULT NULL,
  `delivery_date` date DEFAULT NULL,
  `payment_type` smallint DEFAULT NULL,
  `total_price` decimal(38,2) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;