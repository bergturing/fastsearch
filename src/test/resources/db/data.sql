INSERT INTO `sys_users`
(`ID`, `NAME`, `EMAIL`, `PASSWORD`, `PHONE_NUMBER`, `STATUS`, `AVATAR`, `CREATE_TIME`, `LAST_LOGIN_TIME`, `LAST_UPDATE_TIME`)
VALUES
('1', 'admin1', 'admin1@qq.com', '$2a$10$CO59hGPWBZR7Wu8ECDV7Eu9/4GQAYst7emdJbegL9gUmRAtvLZ7j.', '1536956231', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('2', 'admin2', 'admin2@qq.com', '$2a$10$WhILKBPtFF1WTPxNAQ3ASuRgcx6FGRKZ2Q57My5Zg.5qn03SLgNtq', '1536956232', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('3', 'admin3', 'admin3@qq.com', '$2a$10$CSDdmWTkEzeXBltOILvUQe9ctl35P5waPcP497dVYooCDLyJve12e', '1536956233', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('4', 'admin4', 'admin4@qq.com', '$2a$10$LxqJjh7OuWVjJUEnio2qu.Iepvtg1CCZDyqHee.cjZx9wCiPjS/ei', '1536956234', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('5', 'admin5', 'admin5@qq.com', '$2a$10$HmMgLf3gXLe3GTMSj.UKnud6S.z50KmeVdm5FJS3NBlsdp29rvWFe', '1536956235', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('6', 'admin6', 'admin6@qq.com', '$2a$10$7WzTtYe9vkriFWdpivi1kOWg3Pi4N6glz.VHJ8sEDs7clcXESKmXy', '1536956236', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('7', 'admin7', 'admin7@qq.com', '$2a$10$Upk/PluUhHywtxrP4uR7GOza6887lGQolVujSgT.VGX0ysur9sxIe', '1536956237', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('8', 'admin8', 'admin8@qq.com', '$2a$10$09vinnsycbq1fnJ.j7GZm.d2O/D.jerro/y57HziBG.gNgTktS35q', '1536956238', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('9', 'admin9', 'admin9@qq.com', '$2a$10$S.JpxwTZwuJ1jJvxHrIaQuI5fLALncwGutFbPJ6K7WPl8R6/SI/xK', '1536956239', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02');

INSERT INTO `sys_roles`
(`ID`, `CODE`, `NAME`, `DESCRIPTION`)
VALUES
('1', 'ADMIN', '管理员', '管理员'),
('2', 'USER', '用户', '用户');

INSERT INTO `sys_user_role` (`ID`, `USER_ID`, `ROLE_ID`)
VALUES
('1', '1', '1'),
('2', '2', '2'),
('3', '3', '2'),
('4', '4', '2'),
('5', '5', '2'),
('6', '6', '2'),
('7', '7', '2'),
('8', '8', '2'),
('9', '9', '2');


INSERT INTO `fast_search`.`sys_code_b`
(`ID`, `CODE`, `DESCRIPTION`, `ENABLED_FLAG`, `CREATE_TIME`, `LAST_UPDATE_TIME`)
VALUES
('1', 'TEST_CODE_1', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('2', 'TEST_CODE_2', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('3', 'TEST_CODE_3', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('4', 'TEST_CODE_4', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('5', 'TEST_CODE_5', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('6', 'TEST_CODE_6', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('7', 'TEST_CODE_7', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('8', 'TEST_CODE_8', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('9', 'TEST_CODE_9', '测试代码维护', 'N', '2018-02-25 15:18:20', '2018-03-12 10:29:02');


INSERT INTO `fast_search`.`fs_cars`
(`ID`, `TITLE`, `BRAND_ID`, `SERIES_ID`, `DEPLOYEE_ID`, `PRICE`, `SEATS`, `DISPLACEMENT`, `MILEAGE`, `AGE`, `GEAR_BOX`, `COLOR`, `DRIVE_TYPE`, `EMISSION_STANDARD`, `STYLE`, `FUEL_TYPE`, `WATCH_TIMES`, `CITY_EN_NAME`, `REGION_EN_NAME`, `ADDRESS`, `STATUS`, `COVER`, `DESCRIPTION`, `CREATE_TIME`, `LAST_UPDATE_TIME`)
VALUES
('1', '奥迪SQ5', '1', '1', '1', '126328', '4', '3.6', '25000', '12', 'HAND', 'RED', 'TWO', '2', 'SUV', 'GASOLINE', '1234', 'bj', 'bj', '中国北京市', 'NORMAL', '/images/car/SQ5.jpeg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('2', '奥迪S7', '1', '2', '1', '126328', '4', '5.5', '13000', '3', 'AUTO', 'BLUE', 'FOUR', '2', 'SUV', 'GASOLINE', '1234', 'bj', 'bj', '中国北京市', 'NORMAL', '/images/car/S7.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('3', '奥迪S6', '1', '3', '1', '126328', '4', '8.6', '6000', '7', 'AUTO', 'WHITE', 'TWO', '2', 'SUV', 'GASOLINE', '1234', 'bj', 'bj', '中国北京市', 'NORMAL', '/images/car/S6.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('4', '奥迪S8', '1', '4', '1', '126328', '4', '4.6', '60000', '8', 'AUTO', 'GREE', 'TWO', '2', 'SUV', 'GASOLINE', '1234', 'bj', 'bj', '中国北京市', 'NORMAL', '/images/car/S8.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('5', '奥迪S5 COUPE', '1', '5', '1', '126328', '4', '3.6', '58695', '14', 'AUTO', 'BLACK', 'TWO', '2', 'SUV', 'GASOLINE', '1234', 'bj', 'bj', '中国北京市', 'NORMAL', '/images/car/S5COUPE.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('6', '奥迪S5 四门轿跑车', '1', '6', '1', '126328', '4', '4.9', '15222', '1', 'HAND', 'BLACK', 'TWO', '2', 'SUV', 'GASOLINE', '1234', 'bj', 'bj', '中国北京市', 'NORMAL', '/images/car/S5四门轿跑车.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('7', '奥迪S5 敞篷', '1', '7', '1', '126328', '4', '5.5', '6897', '5', 'HAND', 'RED', 'TWO', '2', 'SUV', 'GASOLINE', '1234', 'bj', 'bj', '中国北京市', 'NORMAL', '/images/car/S5敞篷.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('8', '奥迪S4', '1', '8', '1', '126328', '4', '5.9', '9999', '6', 'AUTO', 'GRAY', 'TWO', '2', 'SUV', 'GASOLINE', '1234', 'bj', 'bj', '中国北京市', 'NORMAL', '/images/car/S4.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02'),
('9', '奥迪S3 四门轿车', '1', '9', '1', '126328', '4', '6.8', '84562', '7', 'AUTO', 'WHITE', 'TWO', '2', 'SUV', 'GASOLINE', '1234', 'bj', 'bj', '中国北京市', 'NORMAL', '/images/car/S3四门轿车.jpg', '描述数据', '2018-02-25 15:18:20', '2018-03-12 10:29:02');

INSERT INTO `fast_search`.`fs_car_brands`
(`ID`, `NAME`)
VALUES
('1', '奥迪'),
('2', '宝马'),
('3', '奔驰'),
('4', '大众');

INSERT INTO `fast_search`.`fs_car_series`
(`ID`, `BRAND_ID`, `NAME`)
VALUES
('1', '1', 'SQ5'),
('2', '1', 'S7'),
('3', '1', 'S6'),
('4', '1', 'S8'),
('5', '1', 'S5 COUPE'),
('6', '1', 'S5 四门轿跑车'),
('7', '1', 'S5 敞篷'),
('8', '1', 'S4'),
('9', '1', 'S3 四门轿车');

INSERT INTO `fast_search`.`fs_car_tags`
(`ID`, `NAME`)
VALUES
('1', '速度快'),
('2', '造型帅');

INSERT INTO `fast_search`.`fs_car_tag_ass`
(`ID`, `CAR_ID`, `CAR_TAG_ID`)
VALUES
('1', '1', '1'),
('2', '1', '2'),
('3', '2', '1'),
('4', '2', '2'),
('5', '3', '1'),
('6', '4', '2'),
('7', '5', '1'),
('8', '6', '2'),
('9', '7', '1'),
('10', '8', '2'),
('11', '9', '1');

INSERT INTO `fast_search`.`fs_car_pictures`
(`ID`, `CAR_ID`, `CDN_PREFIX`, `WIDTH`, `HEIGHT`, `LOCATION`, `PATH`)
VALUES
('1', '1', '/images/car/', '660', '440', '', 'SQ5.jpeg'),
('2', '2', '/images/car/', '220', '165', '', 'S7.jpg'),
('3', '3', '/images/car/', '200', '165', '', 'S6.jpg'),
('4', '4', '/images/car/', '220', '147', '', 'S8.jpg'),
('5', '5', '/images/car/', '220', '155', '', 'S5COUPE.jpg'),
('6', '6', '/images/car/', '220', '147', '', 'S5四门轿跑车.jpg'),
('7', '7', '/images/car/', '220', '142', '', 'S5敞篷.jpg'),
('8', '8', '/images/car/', '220', '141', '', 'S4.jpg'),
('9', '9', '/images/car/', '220', '156', '', 'S3四门轿车.jpg'),
('10', '1', '/images/car/', '300', '200', '', 'SQ5-1.jpeg');








INSERT INTO `fs_support_address`
(`ID`, `BELONG_TO`, `EN_NAME`, `CN_NAME`, `LEVEL`, `BAIDU_MAP_LNG`, `BAIDU_MAP_LAT`)
VALUES
('4', 'bj', 'bj', '北京', 'city', '116.395645', '39.929986'),
('5', 'sh', 'sh', '上海', 'city', '121.487899', '31.249162'),
('6', 'hb', 'sjz', '石家庄', 'city', '114.522082', '38.048958'),
('7', 'hb', 'ts', '唐山', 'city', '118.183451', '39.650531'),
('8', 'hb', 'hd', '邯郸', 'city', '114.482694', '36.609308'),
('9', 'bj', 'dcq', '东城区', 'region', '116.42188470126446', '39.93857401298612'),
('10', 'bj', 'xcq', '西城区', 'region', '116.37319010401802', '39.93428014370851'),
('12', 'bj', 'hdq', '海淀区', 'region', '116.23967780102151', '40.03316204507791'),
('13', 'bj', 'cpq', '昌平区', 'region', '116.21645635689414', '40.2217235498323'),
('14', 'sh', 'ptq', '普陀区', 'region', '121.39844294374956', '31.263742929075534'),
('15', 'sjz', 'caq', '长安区', 'region', '114.59262155387033', '38.07687479578663'),
('16', 'sjz', 'qdq', '桥东区', 'region', '114.51078430496142', '38.06338975380927'),
('17', 'sjz', 'qxq', '桥西区', 'region', '114.43813995531943', '38.033364550068136'),
('18', 'sjz', 'xhq', '新华区', 'region', '114.4535014286928', '38.117218640478164'),
('19', 'bj', 'cyq', '朝阳区', 'region', '116.52169489108084', '39.95895316640668');













