-- Cloud Shop

-- --------------------------------------------------------

--
-- 表的结构 `ecs_admin_user` 管理员
--

DROP TABLE IF EXISTS `cs_admin`;
CREATE TABLE `cs_admin` (
  `id` bigint(20) unsigned NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `nick_name` varchar(255) default '游客',
  `mobile` varchar(20) not null default '',
  `avatar` varchar(255) not null default '',
  `email` varchar(60) not null default '',
  `role` tinyint(3) unsigned not null default 0,
  `shop_id` bigint(20) unsigned not null default 0,
  PRIMARY KEY  (`id`),
  unique key `idx_admin_username` (`username`),
  key `idx_admin_shop_id` (`shop_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- --------------------------------------------------------

--
-- 表的结构 `ecs_admin_user` 店
--

DROP TABLE IF EXISTS `cs_shop`;
CREATE TABLE `cs_shop` (
  `id` bigint(20) unsigned NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `shop_name` varchar(255) NOT NULL,
  `shop_image` varchar(255) NOT NULL default '',
  `phone` varchar(20) not null default '',
  `address` varchar(255) not null default '',
  PRIMARY KEY  (`id`),
  unique key `idx_admin_shop_name` (`shop_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;



-- --------------------------------------------------------

--
-- 表的结构 `ecs_region`
--

DROP TABLE IF EXISTS `cs_region`;
CREATE TABLE `cs_region` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `parent_id` int(11) unsigned NOT NULL default '0',
  `region_name` varchar(120) NOT NULL,
  `region_type` tinyint(3) NOT NULL default '1',
  PRIMARY KEY  (`id`),
  KEY `idx_parent_id` (`parent_id`),
  KEY `idx_region_type` (`region_type`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- 表的结构 `ecs_user_address`
--

DROP TABLE IF EXISTS `cs_address`;
CREATE TABLE `cs_address` (
  `id` bigint(20) unsigned NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `address_name` varchar(50) NOT NULL default '',
  `user_id` bigint(20) unsigned NOT NULL,
  `consignee` varchar(60) NOT NULL default '',
  `email` varchar(60) NOT NULL default '',
  `province` int(11) NOT NULL default '0',
  `city` int(11) NOT NULL default '0',
  `district` int(11) NOT NULL default '0',
  `full_address` varchar(255) NOT NULL,
  `zipcode` varchar(60) NOT NULL default '',
  `mobile` varchar(60) NOT NULL default '',
  PRIMARY KEY  (`id`),
  KEY `idx_user_id` (`user_id`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- --------------------------------------------------------

--
-- 表的结构 `ecs_users`
--

DROP TABLE IF EXISTS `cs_users`;
CREATE TABLE `cs_users` (
  `id` bigint(20) unsigned NOT NULL,
  `create_date` datetime NOT NULL,
  `modify_date` datetime NOT NULL,
  `email` varchar(60) NOT NULL default '',
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `sex` tinyint(2) unsigned NOT NULL default '0',
  `point` int(11) unsigned NOT NULL default '0',
  `nick_name` varchar(255) default '游客',
  PRIMARY KEY  (`id`),
  UNIQUE key `uni_username` (`username`)
)  ENGINE=InnoDB DEFAULT CHARSET=utf8;