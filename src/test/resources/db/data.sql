INSERT INTO `sys_users` (`ID`, `NAME`, `EMAIL`, `PASSWORD`, `PHONE_NUMBER`, `STATUS`, `AVATAR`, `CREATE_TIME`, `LAST_LOGIN_TIME`, `LAST_UPDATE_TIME`)
VALUES
('1', 'admin1', 'admin1@qq.com', 'dbc98b601e10eb3160a0f96a457ee8f8', '1536956231', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('2', 'admin2', 'admin2@qq.com', 'a2064ed0ed33044179dd169ba3a316aa', '1536956232', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('3', 'admin3', 'admin3@qq.com', 'bdef3f6715633f460fece3f83e44ba11', '1536956233', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('4', 'admin4', 'admin4@qq.com', '9ebbe0c06ed897c408f82013c57edf68', '1536956234', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('5', 'admin5', 'admin5@qq.com', '9d94e7c6cc4c6f7e97cc3c5ed1987642', '1536956235', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('6', 'admin6', 'admin6@qq.com', '497f15ecb0c10a88ff4b450359215e4f', '1536956236', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('7', 'admin7', 'admin7@qq.com', '9c14760ecad97ea1e9242d4f2e91c60b', '1536956237', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('8', 'admin8', 'admin8@qq.com', 'a3be0cf6eb781269b1646d12424b70a7', '1536956238', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02'),
('9', 'admin9', 'admin9@qq.com', '5a224d4f74750e3a7d8b2f28ed9649a4', '1536956239', 'NORMAL', 'http://7xo6gy.com1.z0.glb.clouddn.com/99ff568bd61c744bf31185aeddf13580.png', '2018-02-25 15:18:20', '2018-03-15 12:00:00', '2018-03-12 10:29:02');

INSERT INTO `sys_roles` (`ID`, `NAME`, `DESCRIPTION`)
VALUES
('1', 'ADMIN', '管理员'),
('2', 'USER', '用户');

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