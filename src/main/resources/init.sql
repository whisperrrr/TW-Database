/*建表*/
CREATE SCHEMA `student_test_sys` DEFAULT CHARACTER SET utf8;

/*建立account账号表*/
CREATE TABLE `student_test_sys`.`account` (
  `account` VARCHAR(20) NOT NULL,
  `password` VARCHAR(20) NOT NULL,
  `identity` VARCHAR(20) NOT NULL,
  PRIMARY KEY (`account`),
  UNIQUE INDEX `account_UNIQUE` (`account` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

/*建立administrator管理者表*/
CREATE TABLE `student_test_sys`.`administrator` (
  `administrator_id` VARCHAR(10) NOT NULL,
  `aname` VARCHAR(45) NULL DEFAULT 'root',
  `aacount` VARCHAR(45) NOT NULL,
  `apassword` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`administrator_id`),
  UNIQUE INDEX `aacount_UNIQUE` (`aacount` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_icelandic_ci;

/*建立student学生表*/
CREATE TABLE `student_test_sys`.`student` (
  `student_id` VARCHAR(20) NOT NULL,
  `sname` VARCHAR(45) NOT NULL,
  `sage` TINYINT UNSIGNED NOT NULL,
  `ssex` ENUM('男', '女') NOT NULL,
  `sclass` VARCHAR(10) NULL,
  `saccount` VARCHAR(45) NULL,
  `spassword` VARCHAR(45) NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE INDEX `saccount_UNIQUE` (`saccount` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

/*建立teacher老师表*/
CREATE TABLE `student_test_sys`.`teacher` (
  `teacher_id` VARCHAR(20) NOT NULL,
  `tname` VARCHAR(45) NOT NULL,
  `tsex` ENUM('男', '女') NOT NULL,
  `subject_id` VARCHAR(20) NOT NULL,
  `taccount` VARCHAR(45) NOT NULL,
  `tpassword` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`teacher_id`),
  UNIQUE INDEX `taccount_UNIQUE` (`taccount` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

/*建立subject科目表*/
CREATE TABLE `student_test_sys`.`subject` (
  `subject_id` VARCHAR(20) NOT NULL,
  `subject_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`subject_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

/*建立testpaper试题库*/
CREATE TABLE `student_test_sys`.`testpaper` (
  `testpaper_id` VARCHAR(20) NOT NULL,
  `subject_id` VARCHAR(20) NOT NULL,
  `teacher_id` VARCHAR(20) NOT NULL,
  `content` LONGTEXT NOT NULL,
  `answer` LONGTEXT NOT NULL,
  `max_score` CHAR(5) NOT NULL,
  `max_time` TIME NOT NULL,
  PRIMARY KEY (`testpaper_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

/*建立examination试卷表*/
CREATE TABLE `student_test_sys`.`examination` (
  `examination_id` INT NOT NULL AUTO_INCREMENT,
  `testpaper_id` VARCHAR(20) NOT NULL,
  `student_id` VARCHAR(20) NOT NULL,
  `eanswer` LONGTEXT NULL,
  `teacher_id` VARCHAR(20) NOT NULL,
  `escore` CHAR(5) NULL,
  PRIMARY KEY (`examination_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

/*添加学生数据*/
INSERT INTO `student_test_sys`.`student` (`student_id`, `sname`, `ssex`, `sage`)
    VALUES ('1001', '小明', '男', '18');
INSERT INTO `student_test_sys`.`student` (`student_id`, `sname`, `ssex`, `sage`)
    VALUES ('1002', '李四', '女', '20');

/*添加老师数据*/
INSERT INTO `student_test_sys`.`teacher` (`teacher_id`, `tname`, `tsex`, `subject_id`, `taccount`, `tpassword`)
    VALUES ('1', 'hebe', '女', '001', 'hebe', '123');
INSERT INTO `student_test_sys`.`teacher` (`teacher_id`, `tname`, `tsex`, `subject_id`, `taccount`, `tpassword`)
    VALUES ('2', 'selina', '女', '002', 'selina', '123');

/*添加账号数据*/
INSERT INTO `student_test_sys`.`account` (`account`, `password`, `identity`)
    VALUES ('张三', '123', 'administrator');
INSERT INTO `student_test_sys`.`account` (`account`, `password`, `identity`)
    VALUES ('hebe', '123', 'teacher');
INSERT INTO `student_test_sys`.`account` (`account`, `password`, `identity`)
    VALUES ('selina', '123', 'teacher');

/*添加科目信息*/
INSERT INTO `student_test_sys`.`subject` (`subject_id`, `subject_name`)
    VALUES ('001', '语文');
INSERT INTO `student_test_sys`.`subject` (`subject_id`, `subject_name`)
    VALUES ('002', '数学');
INSERT INTO `student_test_sys`.`subject` (`subject_id`, `subject_name`)
    VALUES ('003', '英语');
INSERT INTO `student_test_sys`.`subject` (`subject_id`, `subject_name`)
    VALUES ('004', '体育');

/*添加试题库数据*/
INSERT INTO `student_test_sys`.`testpaper` (`testpaper_id`, `subject_id`, `teacher_id`, `content`, `answer`, `max_score`, `max_time`)
    VALUES ('0011', '001', '1', '海山生明月', '天涯共此时', '100', '100');
INSERT INTO `student_test_sys`.`testpaper` (`testpaper_id`, `subject_id`, `teacher_id`, `content`, `answer`, `max_score`, `max_time`)
    VALUES ('0021', '002', '2', '1+1=', '2', '100', '100');

/*添加试卷数据*/
INSERT INTO `student_test_sys`.`examination` (`examination_id`, `testpaper_id`, `student_id`, `teacher_id`, `escore`)
    VALUES ('1', '0011', '1001', '1', '88');
INSERT INTO `student_test_sys`.`examination` (`examination_id`, `testpaper_id`, `student_id`, `teacher_id`, `escore`)
    VALUES ('2', '0011', '1002', '1', '98');
INSERT INTO `student_test_sys`.`examination` (`examination_id`, `testpaper_id`, `student_id`, `teacher_id`)
    VALUES ('3', '0021', '1001', '2');
INSERT INTO `student_test_sys`.`examination` (`examination_id`, `testpaper_id`, `student_id`, `teacher_id`)
    VALUES ('4', '0021', '1002', '2');

