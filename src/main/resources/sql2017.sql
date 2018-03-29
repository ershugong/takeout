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


alter table tb_menu add ext varchar(255) COMMENT '描述';
alter table tb_menu add create_time datetime DEFAULT NULL COMMENT '创建时间';

---添加地点经纬度，评分，起送，配送价
alter table tb_shop
add latitude Double default NULL COMMENT "纬度，浮点数，范围为-90~90，负数表示南纬",
add longitude Double default NULL COMMENT "纬度，浮点数，范围为-90~90，负数表示南纬",
add low_send TINYINT DEFAULT 0 COMMENT "多少元起送",
add send_price TINYINT DEFAULT 0 COMMENT "配送价";

---菜单表添加赞的数目以及月售量
alter table tb_menu
add laud INT default 0 COMMENT "赞的数目",
add rate INT default 0 COMMENT "月售数目";

---菜单表修改类型字段为字符串类型
ALTER TABLE tb_menu MODIFY COLUMN `type` varchar(10) DEFAULT NULL COMMENT '类型';

---菜单表添加当前被加入到购物车的数目
alter table tb_menu
add numb INT default 0 COMMENT "当前被加入到购物车的数目"

---订单表添加创建时间，店铺名，订单结算价格，订单编号，菜名等字段
alter table tb_order
ADD `create_time` VARCHAR(60) DEFAULT NULL COMMENT '订单创建时间',
ADD `shop_name` VARCHAR(60) DEFAULT NULL COMMENT '店铺名',
ADD `price` INT DEFAULT 0 COMMENT '订单结算价格',
ADD `order_id` VARCHAR(32) DEFAULT NULL COMMENT '订单编号',
ADD `menu_name` VARCHAR(200) DEFAULT NULL COMMENT '菜名'

---修改订单状态的字段类型为varchar
ALTER TABLE tb_order MODIFY COLUMN `STATUS` varchar(20) DEFAULT NULL COMMENT '类型';

---订单表添加数量字段
alter table tb_order
ADD `numb` INT DEFAULT 0 COMMENT '订单数量（对应的菜色）'

---评价表添加订单的menuId
alter table tb_comment
ADD `menu_id` VARCHAR(32) DEFAULT NULL COMMENT '菜色id'

---评价表添加评价的用户名
alter table tb_comment
ADD `user_name` VARCHAR(60) DEFAULT NULL COMMENT '评价的用户名'

---评价表添加评价的创建时间
alter table tb_comment
ADD `create_time` datetime DEFAULT NULL COMMENT '评价的创建时间'

---评价表添加评价类型（5好，3中，1差）
alter table tb_comment
ADD `comment_type` VARCHAR(10) DEFAULT NULL COMMENT '评价类型：BAD--差评，NOTBAD--中评，GOOD--好评'

---地址表添加用户id，创建时间
alter table tb_address
ADD `create_time` datetime DEFAULT NULL COMMENT '地址的创建时间',
ADD `user_id` VARCHAR(32) DEFAULT NULL COMMENT '用户id',
ADD `user_name` VARCHAR(32) DEFAULT NULL COMMENT '用户名',
ADD `phone` VARCHAR(32) DEFAULT NULL COMMENT '用户电话号码'

---订单表添加备注字段
alter table tb_order add ext varchar(255) COMMENT '描述';

---地址表中添加是否为默认地址的字段
alter table tb_address add is_default TINYINT COMMENT '是否为默认的地址（0:否,1:是）';

--订单表中添加是否已经提醒商家的字段
alter table tb_order add remind TINYINT COMMENT '是否已经提醒商家';

--订单表中添加对应的地址id字段
alter table tb_order add address_id VARCHAR(32) COMMENT '地址id';

