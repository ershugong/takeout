CREATE TABLE `tb_activity` (
  `id` varchar(32) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '活动名称',
  `low_line` int(11) DEFAULT NULL COMMENT '最低获取优惠的价格',
  `discount` int(11) DEFAULT NULL COMMENT '优惠金额',
  `shop_id` varchar(32) DEFAULT NULL COMMENT '商店id',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=utf8;


 CREATE TABLE `tb_address` (
  `id` varchar(32) NOT NULL,
  `province` varchar(100) DEFAULT NULL COMMENT '省',
  `city` varchar(100) DEFAULT NULL COMMENT '市',
  `area` varchar(100) DEFAULT NULL COMMENT '区/县',
  `detail_place` varchar(500) DEFAULT NULL COMMENT '补充细致地区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_comment` (
  `id` varchar(32) NOT NULL,
  `content` varchar(500) DEFAULT NULL COMMENT '内容',
  `user_id` varchar(32) DEFAULT NULL COMMENT '发起人',
  `shop_id` varchar(32) DEFAULT NULL COMMENT '被评价的商店',
  `photo` varchar(500) DEFAULT NULL COMMENT '图像',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_menu` (
  `id` varchar(32) NOT NULL,
  `name` varchar(200) DEFAULT NULL COMMENT '菜名',
  `head_pic` varchar(500) DEFAULT NULL COMMENT '菜色图像',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型',
  `price` int(11) DEFAULT NULL COMMENT '价格',
  `status` tinyint(4) DEFAULT NULL COMMENT '是否上线（0:否，1:是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_order` (
  `id` varchar(32) NOT NULL,
  `user_id` varchar(32) DEFAULT NULL COMMENT '用户',
  `shop_id` varchar(32) DEFAULT NULL COMMENT '商店',
  `menu_id` varchar(32) DEFAULT NULL COMMENT '菜色',
  `status` tinyint(4) DEFAULT NULL COMMENT '是否结账（0:否，1:是）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


CREATE TABLE `tb_shop` (
  `id` varchar(32) NOT NULL COMMENT '商店id',
  `shop_name` varchar(200) DEFAULT NULL COMMENT '商店名称',
  `shoper_id` varchar(32) DEFAULT NULL COMMENT '店主id',
  `logo` varchar(500) DEFAULT NULL COMMENT '商店logo',
  `address` varchar(500) DEFAULT NULL COMMENT '商店地址',
  `is_food` tinyint(4) DEFAULT NULL COMMENT '是否为美食（0 : 否，1：是）',
  `is_market` tinyint(4) DEFAULT NULL COMMENT '是否为超市（0 : 否，1：是）',
  `is_fruit` tinyint(4) DEFAULT NULL COMMENT '是否为果蔬（0 : 否，1：是）',
  `is_dessert` tinyint(4) DEFAULT NULL COMMENT '是否为甜品（0 : 否，1：是）',
  `is_major_send` tinyint(4) DEFAULT NULL COMMENT '是否为平台专送（0 : 否，1：是）',
  `is_supper` tinyint(4) DEFAULT NULL COMMENT '是否为正餐（0 : 否，1：是）',
  `is_snack` tinyint(4) DEFAULT NULL COMMENT '是否为小吃（0 : 否，1：是）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `tb_user` (
  `id` varchar(32) NOT NULL COMMENT '用户登录的账号id',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `user_name` varchar(200) DEFAULT NULL COMMENT '用户名',
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(15) DEFAULT NULL COMMENT '联系电话',
  `wx_user_id` varchar(200) DEFAULT NULL COMMENT '微信账号',
  `type` tinyint(4) DEFAULT NULL COMMENT '类型（0：用户，1：商家）',
  `head_pic` varchar(500) DEFAULT NULL COMMENT '头像',
  `sex` tinyint(4) DEFAULT NULL COMMENT '性别',
  `shop_id` tinyint(4) DEFAULT NULL COMMENT '商店id(用户没有)',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

