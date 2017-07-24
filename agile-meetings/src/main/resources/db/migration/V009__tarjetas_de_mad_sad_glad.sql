CREATE TABLE `agilemeetings`.`tarjetas` 
(
	`id` INT NOT NULL AUTO_INCREMENT , 
	`juego_id` INT NOT NULL , 
	`texto` TEXT NOT NULL , 
	`estado` VARCHAR(30) NOT NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;
