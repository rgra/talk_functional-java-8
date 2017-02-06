CREATE DATABASE `test`;

USE `test`;

CREATE TABLE `author` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `age` int(11) NOT NULL DEFAULT '20',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

INSERT INTO test.author (id, first_name, last_name, age) VALUES(2, 'Rabea', 'Gransberger', 33);
INSERT INTO test.author (id, first_name, last_name, age) VALUES(3, 'Werder', 'Bremen', 118);
