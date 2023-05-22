CREATE TABLE `Transaction` (
  `id` varchar(50) DEFAULT NULL,
  `details` varchar(200) DEFAULT NULL,
  `date` date DEFAULT NULL,
  `amount` int DEFAULT NULL,
  `customer` varchar(50) DEFAULT NULL,
  `account` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL
);