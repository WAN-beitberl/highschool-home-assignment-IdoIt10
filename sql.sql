CREATE DATABASE high_school;
USE high_school;
CREATE TABLE `students` (
    `id` INT(3) NOT NULL AUTO_INCREMENT,
    `first_name` VARCHAR(20) NOT NULL,
    `last_name` VARCHAR(20) NOT NULL,
    `email` VARCHAR(45) NOT NULL unique,
    `gender` VARCHAR(10) NOT NULL,
    `ip_address` VARCHAR(45) NOT NULL unique,
    `cm_height` INT(3) NOT NULL,
    `age` INT(2) NOT NULL,
    `has_car` varchar(5) NOT NULL,
    `car_color` varchar(10),
    `garde` INT(1) NOT NULL,
    `gardes_avg` double NOT NULL,
    `identification_card` INT(9) NOT NULL unique,
    PRIMARY KEY (`id`)
);

CREATE TABLE `friendships` (
    `id` INT(3) NOT NULL AUTO_INCREMENT,
    CONSTRAINT `friend_id` FOREIGN KEY (`friend_id`) REFERENCES `students`(`id`),
    CONSTRAINT `other_friend_id` FOREIGN KEY (`other_friend_id`) REFERENCES `students`(`id`),
    PRIMARY KEY (`id`)
);

CREATE VIEW `view` AS
select students.identification_card, students.grades_avg
from students