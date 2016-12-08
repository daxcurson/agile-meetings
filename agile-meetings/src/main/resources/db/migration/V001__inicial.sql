CREATE TABLE IF NOT EXISTS `estados_backlog_item` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estados_backlog_item`
--

INSERT INTO `estados_backlog_item` (`id`, `nombre`) VALUES
(1, 'Nuevo'),
(2, 'Asignado'),
(3, 'Realizado'),
(4, 'Abandonado'),
(5, 'Rechazado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estados_proyecto`
--

CREATE TABLE IF NOT EXISTS `estados_proyecto` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `estados_proyecto`
--

INSERT INTO `estados_proyecto` (`id`, `nombre`) VALUES
(1, 'Abierto'),
(2, 'Cerrado'),
(3, 'Abandonado');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `groups`
--

CREATE TABLE IF NOT EXISTS `groups` (
  `id` int(11) NOT NULL,
  `created` datetime DEFAULT NULL,
  `group_name` varchar(255) DEFAULT NULL,
  `modified` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `group_permission`
--

CREATE TABLE IF NOT EXISTS `group_permission` (
  `id` int(11) NOT NULL,
  `authority` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `items_sprint`
--

CREATE TABLE IF NOT EXISTS `items_sprint` (
  `id` int(11) NOT NULL,
  `item_id` int(11) NOT NULL,
  `sprint_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juegos`
--

CREATE TABLE IF NOT EXISTS `juegos` (
  `id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `juegos_reuniones`
--

CREATE TABLE IF NOT EXISTS `juegos_reuniones` (
  `id` int(11) NOT NULL,
  `juego_id` int(11) DEFAULT NULL,
  `reunion_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas`
--

CREATE TABLE IF NOT EXISTS `personas` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `usuario_sistema` int(11) NOT NULL,
  `user_id` int(11) DEFAULT NULL,
  `habilitada` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `personas_reuniones`
--

CREATE TABLE IF NOT EXISTS `personas_reuniones` (
  `id` int(11) NOT NULL,
  `reunion_id` int(11) DEFAULT NULL,
  `persona_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `product_backlog_items`
--

CREATE TABLE IF NOT EXISTS `product_backlog_items` (
  `id` int(11) NOT NULL,
  `descripcion` text,
  `fecha_creacion` datetime DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL,
  `proyecto_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `proyectos`
--

CREATE TABLE IF NOT EXISTS `proyectos` (
  `id` int(11) NOT NULL,
  `fecha_comienzo` datetime DEFAULT NULL,
  `fecha_creacion` datetime DEFAULT NULL,
  `fecha_fin` datetime DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `estado_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reuniones`
--

CREATE TABLE IF NOT EXISTS `reuniones` (
  `id` int(11) NOT NULL,
  `acciones` varchar(255) DEFAULT NULL,
  `asunto` varchar(255) DEFAULT NULL,
  `fecha_comienzo` tinyblob,
  `fecha_fin` tinyblob,
  `resumen` varchar(255) DEFAULT NULL,
  `proyecto_id` int(11) DEFAULT NULL,
  `tipo_reunion_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id`, `nombre`) VALUES
(1, 'Miembro'),
(2, 'Scrum Master');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles_personas`
--

CREATE TABLE IF NOT EXISTS `roles_personas` (
  `id` int(11) NOT NULL,
  `proyecto_id` int(11) DEFAULT NULL,
  `rol_id` int(11) DEFAULT NULL,
  `persona_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sprints`
--

CREATE TABLE IF NOT EXISTS `sprints` (
  `id` int(11) NOT NULL,
  `fecha_comienzo` date NOT NULL,
  `fecha_fin` date NOT NULL,
  `proyecto_id` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `descripcion` varchar(100) NOT NULL,
  `estado_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tipos_reunion`
--

CREATE TABLE IF NOT EXISTS `tipos_reunion` (
  `id` int(11) NOT NULL,
  `nombre` varchar(255) DEFAULT NULL
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `tipos_reunion`
--

INSERT INTO `tipos_reunion` (`id`, `nombre`) VALUES
(1, 'Sprint Planning meeting'),
(2, 'Sprint Review meeting'),
(3, 'Sprint Retrospective meeting'),
(4, 'Daily Scrum meeting');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL,
  `enabled` int(11) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `group_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `estados_backlog_item`
--
ALTER TABLE `estados_backlog_item`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `estados_proyecto`
--
ALTER TABLE `estados_proyecto`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `groups`
--
ALTER TABLE `groups`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `group_permission`
--
ALTER TABLE `group_permission`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK_14fbne1or7jokuvpatksjhlcv` (`group_id`);

--
-- Indices de la tabla `items_sprint`
--
ALTER TABLE `items_sprint`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `juegos`
--
ALTER TABLE `juegos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `juegos_reuniones`
--
ALTER TABLE `juegos_reuniones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `personas`
--
ALTER TABLE `personas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `personas_reuniones`
--
ALTER TABLE `personas_reuniones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `product_backlog_items`
--
ALTER TABLE `product_backlog_items`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `reuniones`
--
ALTER TABLE `reuniones`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `roles_personas`
--
ALTER TABLE `roles_personas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sprints`
--
ALTER TABLE `sprints`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `tipos_reunion`
--
ALTER TABLE `tipos_reunion`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `username_fc` (`username`),
  ADD UNIQUE KEY `UK_r43af9ap4edm43mmtq01oddj6` (`username`),
  ADD KEY `FK_fm4cfgdt24toh89yw4rbnu1lb` (`group_id`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `estados_backlog_item`
--
ALTER TABLE `estados_backlog_item`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT de la tabla `estados_proyecto`
--
ALTER TABLE `estados_proyecto`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=4;
--
-- AUTO_INCREMENT de la tabla `groups`
--
ALTER TABLE `groups`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `group_permission`
--
ALTER TABLE `group_permission`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `items_sprint`
--
ALTER TABLE `items_sprint`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `juegos`
--
ALTER TABLE `juegos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `juegos_reuniones`
--
ALTER TABLE `juegos_reuniones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `personas`
--
ALTER TABLE `personas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `personas_reuniones`
--
ALTER TABLE `personas_reuniones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `product_backlog_items`
--
ALTER TABLE `product_backlog_items`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `proyectos`
--
ALTER TABLE `proyectos`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `reuniones`
--
ALTER TABLE `reuniones`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT de la tabla `roles_personas`
--
ALTER TABLE `roles_personas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `sprints`
--
ALTER TABLE `sprints`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- AUTO_INCREMENT de la tabla `tipos_reunion`
--
ALTER TABLE `tipos_reunion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT,AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT de la tabla `users`
--
ALTER TABLE `users`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `group_permission`
--
ALTER TABLE `group_permission`
  ADD CONSTRAINT `FK_14fbne1or7jokuvpatksjhlcv` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`);

--
-- Filtros para la tabla `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `FK_fm4cfgdt24toh89yw4rbnu1lb` FOREIGN KEY (`group_id`) REFERENCES `groups` (`id`);
