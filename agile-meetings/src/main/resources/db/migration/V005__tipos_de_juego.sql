CREATE TABLE `agilemeetings`.`tipos_juego` 
(
	`id` INT NOT NULL AUTO_INCREMENT , 
	`nombre` VARCHAR(200) NOT NULL , 
	`codigo` VARCHAR(30) NOT NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;
ALTER TABLE `tipos_juego` ADD `vista` VARCHAR(100) NOT NULL AFTER `codigo`;
