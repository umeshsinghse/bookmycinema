CREATE TABLE `movies` (
  `id` int NOT NULL AUTO_INCREMENT,
  `duration` double NOT NULL,
  `genre` enum('ACTION','ANIMATED','ANIME','COMEDY','CRIMINAL','DOCUMENTARY','HORROR','ROMANTIC','SCIFICTION','THRILLER') DEFAULT NULL,
  `language` enum('BHOJPURI','ENGLISH','HINDI','KANNADA','MALLAYALAM','MARATHI','PUNJABI','TAMIL','TELUGU') DEFAULT NULL,
  `movie_name` varchar(255) NOT NULL,
  `rating` double DEFAULT NULL,
  `release_date` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `show_seats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `is_available` bit(1) NOT NULL,
  `is_food_attached` bit(1) NOT NULL,
  `price` int NOT NULL,
  `seat_no` varchar(255) DEFAULT NULL,
  `seat_type` enum('CLASSIC','PREMIUM') DEFAULT NULL,
  `show_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKldtrq74q8syptlbgqag9cw9w1` (`show_id`),
  CONSTRAINT `FKldtrq74q8syptlbgqag9cw9w1` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



CREATE TABLE `shows` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `time` time(6) DEFAULT NULL,
  `movie_id` int DEFAULT NULL,
  `theater_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKqdpwhiv5r3lx844pct0eudapk` (`movie_id`),
  KEY `FKq1hxb5a1cutihabqq6lo5ccud` (`theater_id`),
  CONSTRAINT `FKq1hxb5a1cutihabqq6lo5ccud` FOREIGN KEY (`theater_id`) REFERENCES `theater` (`id`),
  CONSTRAINT `FKqdpwhiv5r3lx844pct0eudapk` FOREIGN KEY (`movie_id`) REFERENCES `movies` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


CREATE TABLE `theater` (
  `id` int NOT NULL AUTO_INCREMENT,
  `location` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_71lt5dyvgngmkh75o0rx6myo3` (`location`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `theater_seats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `seat_no` varchar(255) DEFAULT NULL,
  `seat_type` enum('CLASSIC','PREMIUM') DEFAULT NULL,
  `theater_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKe461xicbrhr1l20149loqt7qd` (`theater_id`),
  CONSTRAINT `FKe461xicbrhr1l20149loqt7qd` FOREIGN KEY (`theater_id`) REFERENCES `theater` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `tickets` (
  `id` int NOT NULL AUTO_INCREMENT,
  `booked_at` datetime(6) DEFAULT NULL,
  `booked_seats` varchar(255) DEFAULT NULL,
  `total_tickets_price` int NOT NULL,
  `show_id` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKosj8dc2tn2tcidsfimopidq13` (`show_id`),
  KEY `FK4eqsebpimnjen0q46ja6fl2hl` (`user_id`),
  CONSTRAINT `FK4eqsebpimnjen0q46ja6fl2hl` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKosj8dc2tn2tcidsfimopidq13` FOREIGN KEY (`show_id`) REFERENCES `shows` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

CREATE TABLE `user_credential` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;






