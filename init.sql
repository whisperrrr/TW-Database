CREATE SCHEMA `parking_lot_sys` DEFAULT CHARACTER SET utf8 ;

CREATE TABLE `parking_lot_sys`.`parking_lot_test` (
  `mark` CHAR(2) NOT NULL,
  `id` INT UNSIGNED NOT NULL,
  `is_empty` TINYINT NOT NULL,
  `car_number` VARCHAR(10) NULL,
  `park_in_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`mark`, `id`),
  UNIQUE INDEX `car_number_UNIQUE` (`car_number` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;