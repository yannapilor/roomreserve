DROP DATABASE  IF EXISTS `gcp_room_reservation`;

CREATE DATABASE  IF NOT EXISTS `gcp_room_reservation`;
USE `gcp_room_reservation`;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` char(68) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: http://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `users` 
VALUES 
('john','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
('mary','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
('susan','{bcrypt}$2a$04$eFytJDGtjbThXa80FyOOBuFdK2IwjyWefYkMpiBEFlpBwDH.5PM0K',1),
('tan','{bcrypt}$2a$10$HS4NrDJeUVH3bioDyW2NVOrS.yKaZGYdZcNW28wFgqHxxY5v4odGy',1);

--
-- Table structure for table `authorities`
--

DROP TABLE IF EXISTS `authorities`;
CREATE TABLE `authorities` (
  `username` varchar(50) NOT NULL,
  `authority` varchar(50) NOT NULL,
  UNIQUE KEY `authorities_idx_1` (`username`,`authority`),
  CONSTRAINT `authorities_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `authorities`
--

INSERT INTO `authorities` 
VALUES 
('john','ROLE_CLIENT'),
('mary','ROLE_STAFF'),
('tan','ROLE_STAFF'),
('susan','ROLE_CLIENT');

DROP TABLE IF EXISTS `room`;
CREATE TABLE `room` (
  `room_id` int(10) NOT NULL AUTO_INCREMENT,
  `type`  varchar(100) DEFAULT NULL,
  `price` int(10) DEFAULT NULL,
  `availability` int(10) NOT NULL,
  PRIMARY KEY (`room_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;
  
DROP TABLE IF EXISTS `available_room`;
  
INSERT INTO `room` 
VALUES 
(1,'Standard',1500,7),
(2,'Premium',2500,4),
(3,'Deluxe', 3000,6),
(4,'Family_room', 4000,8);

CREATE TABLE `reservation` (
  `room_number` int(10) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `room_id` int(10) NOT NULL,
  PRIMARY KEY (`room_number`),
  KEY `username_idx_1` (`username`),
  CONSTRAINT `username_ibfk_1` FOREIGN KEY (`username`) REFERENCES `users` (`username`),
  KEY `room_idx_1` (`username`),
  CONSTRAINT `room_ibfk_1` FOREIGN KEY (`room_id`) REFERENCES `room` (`room_id`)
  ) ENGINE=InnoDB DEFAULT CHARSET=latin1;