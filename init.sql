CREATE SCHEMA `tw_springboot_1.3` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `tw_springboot_1.3`.`student_new` (
  `name` VARCHAR(20) NOT NULL,
  `gender` VARCHAR(10) NOT NULL,
  `class_num` VARCHAR(10) NOT NULL,
  PRIMARY KEY (`name`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;