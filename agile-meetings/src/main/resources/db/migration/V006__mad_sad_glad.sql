INSERT INTO `tipos_juego` (`id`, `nombre`, `codigo`,`vista`) VALUES (NULL, 'Mad, sad, glad', 'MAD','juego_mad_sad_glad');
DROP TABLE `juegos_reuniones`;
ALTER TABLE `juegos` 
	ADD `reunion_id` INT NOT NULL AFTER `id`, 
	ADD `tipo_juego_id` INT NOT NULL AFTER `reunion_id`;
