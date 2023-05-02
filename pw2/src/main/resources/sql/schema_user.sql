CREATE SCHEMA IF NOT EXISTS `bedu_m3_pw`;

GRANT ALL ON `bedu_m3_pw`.* TO 'admin'@'localhost' IDENTIFIED BY '654321';
GRANT SELECT ON `bedu_m3_pw`.* TO 'user'@'localhost' IDENTIFIED BY '123456';

FLUSH PRIVILEGES;
