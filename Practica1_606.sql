-- phpMyAdmin SQL Dump
-- version 4.2.11
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 24-02-2015 a las 22:01:03
-- Versión del servidor: 5.6.21
-- Versión de PHP: 5.6.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `Practica1_606`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Activos`
--

CREATE TABLE IF NOT EXISTS `Activos` (
  `crucigrama` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `usuario` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Amigos`
--

CREATE TABLE IF NOT EXISTS `Amigos` (
  `usuario_source` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `usuario_target` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Contiene`
--

CREATE TABLE IF NOT EXISTS `Contiene` (
  `crucicgrama` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `palabra` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `orientacion` int(11) NOT NULL,
  `x` int(11) NOT NULL,
  `y` int(11) NOT NULL,
  `resuelta` int(11) NOT NULL,
  `puntuacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Crucigramas`
--

CREATE TABLE IF NOT EXISTS `Crucigramas` (
  `titulo` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fecha` date NOT NULL,
  `source_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `target_user` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Etiquetas`
--

CREATE TABLE IF NOT EXISTS `Etiquetas` (
  `etiqueta` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `palabra` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Historial`
--

CREATE TABLE IF NOT EXISTS `Historial` (
  `usuario` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `palabra` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fecha` date NOT NULL,
  `hora` time NOT NULL,
  `correcta` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Palabras`
--

CREATE TABLE IF NOT EXISTS `Palabras` (
  `palabra` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `enunciado` varchar(255) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `imagen` blob
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `Usuarios`
--

CREATE TABLE IF NOT EXISTS `Usuarios` (
  `nombre` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8 COLLATE utf8_spanish_ci NOT NULL,
  `fecha_n` date DEFAULT NULL,
  `imagen` blob,
  `puntuacion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `Activos`
--
ALTER TABLE `Activos`
 ADD PRIMARY KEY (`crucigrama`,`usuario`), ADD KEY `usuario` (`usuario`);

--
-- Indices de la tabla `Amigos`
--
ALTER TABLE `Amigos`
 ADD PRIMARY KEY (`usuario_source`,`usuario_target`), ADD KEY `usuario_target` (`usuario_target`);

--
-- Indices de la tabla `Contiene`
--
ALTER TABLE `Contiene`
 ADD PRIMARY KEY (`crucicgrama`,`palabra`), ADD KEY `palabra` (`palabra`);

--
-- Indices de la tabla `Crucigramas`
--
ALTER TABLE `Crucigramas`
 ADD PRIMARY KEY (`titulo`), ADD KEY `target_user` (`target_user`), ADD KEY `source_user` (`source_user`);

--
-- Indices de la tabla `Etiquetas`
--
ALTER TABLE `Etiquetas`
 ADD PRIMARY KEY (`etiqueta`,`palabra`), ADD KEY `palabra` (`palabra`);

--
-- Indices de la tabla `Historial`
--
ALTER TABLE `Historial`
 ADD PRIMARY KEY (`usuario`,`palabra`), ADD KEY `palabra` (`palabra`);

--
-- Indices de la tabla `Palabras`
--
ALTER TABLE `Palabras`
 ADD PRIMARY KEY (`palabra`);

--
-- Indices de la tabla `Usuarios`
--
ALTER TABLE `Usuarios`
 ADD PRIMARY KEY (`nombre`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `Activos`
--
ALTER TABLE `Activos`
ADD CONSTRAINT `Activos_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `Usuarios` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `Activos_ibfk_2` FOREIGN KEY (`crucigrama`) REFERENCES `Crucigramas` (`titulo`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Amigos`
--
ALTER TABLE `Amigos`
ADD CONSTRAINT `Amigos_ibfk_1` FOREIGN KEY (`usuario_source`) REFERENCES `Usuarios` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `Amigos_ibfk_2` FOREIGN KEY (`usuario_target`) REFERENCES `Usuarios` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Contiene`
--
ALTER TABLE `Contiene`
ADD CONSTRAINT `Contiene_ibfk_1` FOREIGN KEY (`crucicgrama`) REFERENCES `Crucigramas` (`titulo`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `Contiene_ibfk_2` FOREIGN KEY (`palabra`) REFERENCES `Palabras` (`palabra`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Crucigramas`
--
ALTER TABLE `Crucigramas`
ADD CONSTRAINT `Crucigramas_ibfk_1` FOREIGN KEY (`source_user`) REFERENCES `Usuarios` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `Crucigramas_ibfk_2` FOREIGN KEY (`target_user`) REFERENCES `Usuarios` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Etiquetas`
--
ALTER TABLE `Etiquetas`
ADD CONSTRAINT `Etiquetas_ibfk_1` FOREIGN KEY (`palabra`) REFERENCES `Palabras` (`palabra`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `Historial`
--
ALTER TABLE `Historial`
ADD CONSTRAINT `Historial_ibfk_1` FOREIGN KEY (`usuario`) REFERENCES `Usuarios` (`nombre`) ON DELETE CASCADE ON UPDATE CASCADE,
ADD CONSTRAINT `Historial_ibfk_2` FOREIGN KEY (`palabra`) REFERENCES `Palabras` (`palabra`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
