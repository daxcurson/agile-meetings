INSERT INTO `tipos_juego` (`id`, `nombre`, `codigo`) VALUES (NULL, 'Mad, sad, glad', 'MAD');
DROP TABLE `juegos_reuniones`;
ALTER TABLE `juegos` 
	ADD `reunion_id` INT NOT NULL AFTER `id`, 
	ADD `tipo_juego_id` INT NOT NULL AFTER `reunion_id`;
