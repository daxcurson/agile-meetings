ALTER TABLE `juegos` ADD `codigo` VARCHAR(30) NOT NULL AFTER `tipo_juego_id`;
ALTER TABLE `juegos` ADD `estado_juego_id` INT NOT NULL AFTER `id`;
CREATE TABLE `agilemeetings`.`estados_juego` 
(
	`id` INT NOT NULL AUTO_INCREMENT , 
	`nombre` VARCHAR(100) NOT NULL , 
	`codigo` INT(30) NOT NULL , 
	PRIMARY KEY (`id`)
) ENGINE = InnoDB;
ALTER TABLE `estados_juego` CHANGE `codigo` `codigo` VARCHAR(30) NOT NULL;
INSERT INTO `estados_juego` (`id`, `nombre`, `codigo`) VALUES 
	(NULL, 'Abierto', 'ABIERTO'), 
	(NULL, 'Cerrado', 'CERRADO');
