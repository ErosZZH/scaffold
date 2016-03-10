

-- --------------------------------------------------------

--
-- 表的结构 `ecs_cart` 购物车
--
DROP TABLE IF EXISTS `cs_cart_item`;
DROP TABLE IF EXISTS `cs_cart`;

CREATE TABLE `cs_cart` (
  `id` bigint(20) unsigned NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `shop_id` bigint(20) unsigned NOT NULL,
  `product_price` decimal(10,2) unsigned NOT NULL default '0.00',
  PRIMARY KEY  (`id`),
  unique key `idx_cart_user_id` (`user_id`, `shop_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `ecs_cart_goods` 购物车商品
--


CREATE TABLE `cs_cart_item` (
  `id` bigint(20) unsigned NOT NULL,
  `cart_id` bigint(20) unsigned NOT NULL,
  `goods_id` bigint(20) unsigned NOT NULL,
  `goods_name` varchar(255) NOT NULL default '',
  `goods_sn` varchar(60) NOT NULL default '',
  `goods_amount` int(11) unsigned NOT NULL default '0',
  `unit` varchar(5) not null,
  `market_price` decimal(10,2) NOT NULL default '0.00',
  `goods_price` decimal(10,2) NOT NULL default '0.00',
  `shop_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  foreign key (`cart_id`) references cs_cart(`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `ecs_order_info`
--


DROP TABLE IF EXISTS `cs_order_item`;
DROP TABLE IF EXISTS `cs_order`;

CREATE TABLE `cs_order` (
  `id` bigint(20) unsigned NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `order_sn` varchar(20) NOT NULL,
  `user_id` bigint(20) unsigned NOT NULL,
  `order_status` tinyint(5) unsigned NOT NULL default '0',
  `shipping_status` tinyint(5) unsigned NOT NULL default '0',
  `pay_status` tinyint(3) unsigned NOT NULL default '0',
  `consignee` varchar(60) NOT NULL,
  `address_id` bigint(20) unsigned NOT NULL,
  `mobile` varchar(20) NOT NULL,
  `pay_name` varchar(100) NOT NULL,
  `goods_price` decimal(10,2) NOT NULL default '0.00',
  `shipping_price` decimal(10,2) NOT NULL default '0.00',
  `total_price` decimal(10, 2) NOT NULL default '0.00',
  `discount_price` decimal(10,2) NOT NULL default '0.00',
  `confirm_time` datetime,
  `pay_time` datetime,
  `shipping_time` datetime,
  PRIMARY KEY  (`id`),
  UNIQUE KEY `uni_order_sn` (`order_sn`),
  KEY `idx_user_id` (`user_id`),
  KEY `idx_order_status` (`order_status`),
  KEY `idx_shipping_status` (`shipping_status`),
  KEY `idx_pay_status` (`pay_status`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `ecs_order_goods`
--

CREATE TABLE `cs_order_item` (
  `id` bigint(20) unsigned NOT NULL,
  `order_id` bigint(20) unsigned NOT NULL,
  `goods_id` bigint(20) unsigned NOT NULL,
  `goods_name` varchar(255) NOT NULL default '',
  `goods_sn` varchar(60) NOT NULL default '',
  `goods_amount` int(11) unsigned NOT NULL default '0',
  `unit` varchar(5) not null,
  `market_price` decimal(10,2) NOT NULL default '0.00',
  `goods_price` decimal(10,2) NOT NULL default '0.00',
  `shop_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  foreign key (`order_id`) references cs_order(`id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;