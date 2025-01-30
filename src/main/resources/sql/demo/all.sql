USE noteboardDB;

-- users
INSERT INTO `users` VALUES
                        (1, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', '$2a$11$wzgtAnpsWFBPjKOoer.MYum92OpQjle4hv56JOwfRJgRhRK33cnOS', 'user1@example.com', null),
                        (2, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', '$2a$11$wzgtAnpsWFBPjKOoer.MYum92OpQjle4hv56JOwfRJgRhRK33cnOS', 'user2@example.com', null),
                        (3, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', '$2a$11$wzgtAnpsWFBPjKOoer.MYum92OpQjle4hv56JOwfRJgRhRK33cnOS', 'user3@example.com', null),
                        (4, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', '$2a$11$wzgtAnpsWFBPjKOoer.MYum92OpQjle4hv56JOwfRJgRhRK33cnOS', 'user4@example.com', null),
                        (5, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', '$2a$11$wzgtAnpsWFBPjKOoer.MYum92OpQjle4hv56JOwfRJgRhRK33cnOS', 'user5@example.com', null);

ALTER TABLE `users` AUTO_INCREMENT = 6;

-- groups
INSERT INTO `groups` VALUES
                         (1, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', 'Group 1', null, 1),
                         (2, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', 'Group 2', null, 2),
                         (3, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', 'Group 3', null, 3),
                         (4, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', 'Group 4', null, 4),
                         (5, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', 'Group 5', null, 1),
                         (6, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', 'Group 6', null, 2),
                         (7, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', 'Group 7', null, 3),
                         (8, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', 'Group 8', null, 1),
                         (9, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', 'Group 9', null, 4);

ALTER TABLE `groups` AUTO_INCREMENT = 10;

-- members
INSERT INTO `group_user` VALUES
                             (1, 2),
                             (1, 3),
                             (1, 4),
                             (2, 1),
                             (2, 4),
                             (3, 1),
                             (3, 4),
                             (4, 1),
                             (4, 2),
                             (4, 3),
                             (5, 2),
                             (5, 3),
                             (6, 1),
                             (7, 1);

ALTER TABLE `group_user` AUTO_INCREMENT = 15;

-- messages
INSERT INTO `messages` VALUES
                           (1, '2025-01-29 21:10:47.222300', '2025-01-29 21:10:47.222300', 'Sed vulputate faucibus rhoncus. Cras et.', null, 1, 1),
                           (2, '2025-01-29 21:11:47.222300', '2025-01-29 21:11:47.222300', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec augue velit quam.', null, 1, 1),
                           (3, '2025-01-29 21:12:47.222300', '2025-01-29 21:12:47.222300', 'Sed lacinia laoreet.', null, 2, 1),
                           (4, '2025-01-29 21:13:47.222300', '2025-01-29 21:13:47.222300', 'Morbi sed risus odio. Aenean ullamcorper neque neque, in at.', null, 3, 1),
                           (5, '2025-01-29 21:14:47.222300', '2025-01-29 21:14:47.222300', 'Sed vulputate faucibus rhoncus. Cras et.', null, 4, 1),
                           (6, '2025-01-29 21:15:47.222300', '2025-01-29 21:15:47.222300', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec augue velit quam.', null, 1, 1),
                           (7, '2025-01-29 21:16:47.222300', '2025-01-29 21:16:47.222300', 'Sed lacinia laoreet.', null, 2, 1),
                           (8, '2025-01-29 21:17:47.222300', '2025-01-29 21:17:47.222300', 'Morbi sed risus odio. Aenean ullamcorper neque neque, in at.', null, 3, 1),
                           (9, '2025-01-29 21:18:47.222300', '2025-01-29 21:18:47.222300', 'Sed vulputate faucibus rhoncus. Cras et.', null, 4, 1),
                           (10, '2025-01-29 21:19:47.222300', '2025-01-29 21:19:47.222300', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec augue velit quam.', null, 1, 1),
                           (11, '2025-01-29 21:20:47.222300', '2025-01-29 21:20:47.222300', 'Sed lacinia laoreet.', null, 2, 1),
                           (12, '2025-01-29 21:21:47.222300', '2025-01-29 21:21:47.222300', 'Morbi sed risus odio. Aenean ullamcorper neque neque, in at.', null, 3, 1),
                           (13, '2025-01-29 21:22:47.222300', '2025-01-29 21:22:47.222300', 'Sed vulputate faucibus rhoncus. Cras et.', null, 4, 1),
                           (14, '2025-01-29 21:23:47.222300', '2025-01-29 21:23:47.222300', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec augue velit quam.', null, 3, 1),
                           (15, '2025-01-29 21:24:47.222300', '2025-01-29 21:24:47.222300', 'Sed lacinia laoreet.', null, 2, 1),
                           (16, '2025-01-29 21:25:47.222300', '2025-01-29 21:25:47.222300', 'Morbi sed risus odio. Aenean ullamcorper neque neque, in at.', null, 1, 1),
                           (17, '2025-01-29 21:26:47.222300', '2025-01-29 21:26:47.222300', 'Sed vulputate faucibus rhoncus. Cras et.', null, 1, 2),
                           (18, '2025-01-29 21:27:47.222300', '2025-01-29 21:27:47.222300', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec augue velit quam.', null, 2, 2),
                           (19, '2025-01-29 21:28:47.222300', '2025-01-29 21:28:47.222300', 'Sed lacinia laoreet.', null, 4, 2),
                           (20, '2025-01-29 21:29:47.222300', '2025-01-29 21:29:47.222300', 'Morbi sed risus odio. Aenean ullamcorper neque neque, in at.', null, 1, 2),
                           (21, '2025-01-29 21:30:47.222300', '2025-01-29 21:30:47.222300', 'Sed vulputate faucibus rhoncus. Cras et.', null, 2, 2),
                           (22, '2025-01-29 21:31:47.222300', '2025-01-29 21:31:47.222300', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec augue velit quam.', null, 1, 3),
                           (23, '2025-01-29 21:32:47.222300', '2025-01-29 21:32:47.222300', 'Sed lacinia laoreet.', null, 3, 3),
                           (24, '2025-01-29 21:33:47.222300', '2025-01-29 21:33:47.222300', 'Morbi sed risus odio. Aenean ullamcorper neque neque, in at.', null, 4, 4),
                           (25, '2025-01-29 21:34:47.222300', '2025-01-29 21:34:47.222300', 'Sed vulputate faucibus rhoncus. Cras et.', null, 1, 5),
                           (26, '2025-01-29 21:35:47.222300', '2025-01-29 21:35:47.222300', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec augue velit quam.', null, 2, 5),
                           (27, '2025-01-29 21:36:47.222300', '2025-01-29 21:36:47.222300', 'Sed lacinia laoreet.', null, 2, 6),
                           (28, '2025-01-29 21:37:47.222300', '2025-01-29 21:37:47.222300', 'Morbi sed risus odio. Aenean ullamcorper neque neque, in at.', null, 1, 7),
                           (29, '2025-01-29 21:38:47.222300', '2025-01-29 21:38:47.222300', 'Sed vulputate faucibus rhoncus. Cras et.', null, 3, 7),
                           (30, '2025-01-29 21:39:47.222300', '2025-01-29 21:39:47.222300', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec augue velit quam.', null, 1, 8),
                           (31, '2025-01-29 21:40:47.222300', '2025-01-29 21:40:47.222300', 'Sed lacinia laoreet.', null, 4, 9),
                           (32, '2025-01-29 21:41:47.222300', '2025-01-29 21:41:47.222300', 'Morbi sed risus odio. Aenean ullamcorper neque neque, in at.', null, 4, 9);

ALTER TABLE `messages` AUTO_INCREMENT = 33;
