
CREATE SCHEMA IF NOT EXISTS `bedu_m3_pw`;
USE `bedu_m3_pw` ;
--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `bedu_m3_pw`.`students`;

CREATE TABLE `students` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(100) NOT NULL,
  `last_name` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `bedu_m3_pw`.`subjects`;
CREATE TABLE `bedu_m3_pw`.`subjects` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;


--
-- Table structure for table `courses`
--

DROP TABLE IF EXISTS `bedu_m3_pw`.`courses`;
CREATE TABLE `bedu_m3_pw`.`courses` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cycle` varchar(10) NOT NULL,
  `subject_id` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp(),
  PRIMARY KEY (`id`),
  KEY `subjects_FK` (`subject_id`),
  CONSTRAINT `subjects_FK` FOREIGN KEY (`subject_id`) REFERENCES `bedu_m3_pw`.`subjects` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Table structure for table `courses_has_students`
--
DROP TABLE IF EXISTS `bedu_m3_pw`.`courses_has_students`;

CREATE TABLE IF NOT EXISTS `bedu_m3_pw`.`courses_has_students` (
  `course_id` INT NOT NULL,
  `student_id` INT NOT NULL,
  `score` INT NOT NULL,
  CONSTRAINT `courses_has_students_course_id_IDX`
    FOREIGN KEY (`course_id`)
    REFERENCES `bedu_m3_pw`.`courses` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `courses_has_students_student_id_IDX`
    FOREIGN KEY (`student_id`)
    REFERENCES `bedu_m3_pw`.`students` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
