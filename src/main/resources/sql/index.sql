USE noteboardDB;

CREATE INDEX idx_users_username ON `users` (username);
CREATE INDEX idx_groups_name ON `groups` (name);
