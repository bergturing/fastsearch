INSERT INTO `sys_users` (`ID`, `NAME`, `EMAIL`, `PASSWORD`, `PHONE_NUMBER`, `STATUS`, `AVATAR`, `CREATE_TIME`, `LAST_LOGIN_TIME`, `LAST_UPDATE_TIME`)
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