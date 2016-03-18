

-- --------------------------------------------------------

--
-- 表的结构 `ecs_category` 分类
--

DROP TABLE IF EXISTS `cs_category`;
CREATE TABLE `cs_category` (
  `id` bigint(20) unsigned NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `name` varchar(90) NOT NULL,
  `desc` varchar(255) NOT NULL default '',
  `cate_image` varchar(255) NOT NULL default '',
  `parent_id` bigint(20) unsigned NOT NULL default '0',
  `show_in_nav` tinyint(1) NOT NULL default '0',
  `is_show` tinyint(1) unsigned NOT NULL default '1',
  `shop_id` bigint(20) unsigned NOT NULL,
  `level` tinyint(5) unsigned not null default '1',
  PRIMARY KEY  (`id`),
  KEY `idx_cate_shop_id` (`shop_id`, `level`),
  unique key `uni_cate_name` (`name`, `shop_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- 表的结构 `ecs_goods` 商品
-- TODO 1. 统计点击数
--      2. goods_desc nosql
--

DROP TABLE IF EXISTS `cs_goods`;
CREATE TABLE `cs_goods` (
  `id` bigint(20) unsigned NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `cate_id` bigint(20) unsigned NOT NULL default '0',
  `sn` varchar(60) NOT NULL default '',
  `name` varchar(255) NOT NULL,
  `brand` varchar(100) NOT NULL default '',
  `amount` int(11) unsigned NOT NULL default '0',
  `goods_thumb` varchar(255) not null default '',
  `market_price` decimal(10,2) unsigned NOT NULL,
  `shop_price` decimal(10,2) unsigned NOT NULL,
  `brief` varchar(255) NOT NULL default '',
  `is_on_sale` tinyint(1) unsigned NOT NULL default '1',
  `saled_amount` bigint(20) unsigned not null default 0,
  `tag` int(11) unsigned NOT NULL default '0',
  `shop_id` bigint(20) unsigned NOT NULL,
  PRIMARY KEY  (`id`),
  KEY `idx_goods_sn` (`sn`),
  KEY `idx_goods_cate_id` (`cate_id`),
  KEY `idx_goods_name` (`name`),
  KEY `idx_goods_sale` (`is_on_sale`),
  KEY `idx_goods_amount` (`saled_amount`),
  KEY `idx_goods_shop` (`shop_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `ecs_goods_gallery`
--

DROP TABLE IF EXISTS `cs_goods_gallery`;
CREATE TABLE `cs_goods_gallery` (
  `id` bigint(20) unsigned NOT NULL,
  `goods_id` bigint(20) unsigned NOT NULL,
  `img_url` varchar(255) NOT NULL default '',
  `img_desc` varchar(255) NOT NULL default '',
  `img_type` tinyint(5) unsigned not null default 0,
  PRIMARY KEY  (`id`),
  KEY `idx_goods_id` (`goods_id`, `img_type`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `scaffold_goods`.`cs_goods`
ADD COLUMN `unit` VARCHAR(5) NOT NULL;

ALTER TABLE `scaffold_goods`.`cs_goods`
ADD COLUMN `specification` VARCHAR(45) NOT NULL DEFAULT '';