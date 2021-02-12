-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 12-02-2020 a las 08:09:32
-- Versión del servidor: 5.7.14
-- Versión de PHP: 5.6.25

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bdgaleria`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comentarios`
--

CREATE TABLE `comentarios` (
  `idcomentario` int(11) NOT NULL,
  `comentario` varchar(500) NOT NULL,
  `fechacom` datetime NOT NULL,
  `idfoto` int(11) NOT NULL,
  `iduser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `comentarios`
--

INSERT INTO `comentarios` (`idcomentario`, `comentario`, `fechacom`, `idfoto`, `iduser`) VALUES
(9, 'Igual de bonito que siempre el gato', '2020-02-11 13:16:18', 79, 1),
(10, 'Cuidado que se le rompen los dientes', '2020-02-11 13:16:32', 80, 1),
(11, 'Pues vaya suerte, ojala encontrarme eso asi', '2020-02-11 13:16:45', 81, 1),
(12, 'Que cosa mas bonita, ademas la foto con muy buena calidad', '2020-02-11 13:18:20', 79, 24),
(13, 'Pues muy bien me parece', '2020-02-11 13:18:37', 85, 24),
(14, 'Que bonita foto, aunque parece un dibujo en vez de una foto', '2020-02-11 13:18:55', 83, 24),
(15, 'Parece sacado de un canal de la television', '2020-02-11 13:19:10', 82, 24);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fotos`
--

CREATE TABLE `fotos` (
  `idfoto` int(11) NOT NULL,
  `titulo` varchar(100) NOT NULL,
  `narchivo` varchar(50) NOT NULL,
  `descripcion` varchar(250) NOT NULL,
  `iduser` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `fotos`
--

INSERT INTO `fotos` (`idfoto`, `titulo`, `narchivo`, `descripcion`, `iduser`) VALUES
(79, 'Gato solo', '9b77ca97-5d6e-4b41-bad0-091ec3b8c4d0.jpg', 'Mi gato en una foto en la que esta solo', 23),
(80, 'El gato y la calabaza', 'ab5ae90e-56ab-4921-82ef-ee5b1625b8d4.jpg', 'Aqui esta el gato mordiendo una calabaza', 23),
(81, 'Eclipse', '243d3bc8-758d-44f3-918f-4b316ff20101.jpg', 'Me encontre esto de camino a casa', 23),
(82, 'Zorro Primer Plano', '47d1d012-43b1-4fe6-ac6b-8e0bd3204dc1.jpg', 'El zorro en plena naturaleza', 1),
(83, 'Amistad', '50cb9907-9570-4818-a3b0-84b3067e875e.jpg', '', 1),
(84, 'Veraneo', '5c62713d-9682-407f-a014-5e5952d496fb.jpg', 'Aqui estamos, comprando gorros', 1),
(85, 'Dia nevado', '30b24f30-ae8f-4a46-b641-8c119c06a4f1.jpg', 'Despues de un buen dia en la nieve', 1),
(94, 'Red Riding Hood', '32b96312-0a66-4859-8589-897207aee04c.jpg', '', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `iduser` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellidos` varchar(100) NOT NULL,
  `username` varchar(35) NOT NULL,
  `password` varchar(100) NOT NULL,
  `salt` varchar(40) NOT NULL,
  `correo` varchar(100) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`iduser`, `nombre`, `apellidos`, `username`, `password`, `salt`, `correo`) VALUES
(1, 'Pablo', 'Fernandez Alonso', 'RedLayer', '8r56aWfZ7ZvCNAVbBPPiqCrPnvK7HIfzXKldeztI8Eo=', 'OSb70AjxiIjo14X6UvtrD2qbg7GIlL', 'usuarioprueba1@prueba.com'),
(23, 'Aitor', 'Ruiz', 'aitorl', 'Y9suZEQaVthqLesubPjB4SoQqePue1DhgKesI/hgDmM=', 'EFgb61vXMwzOYD5QuoZEDW0VEAQhb4', 'aitorl@aitorl.com'),
(24, 'Laura', 'Gonzalez', 'laurita96', 'l+uucwz1OFBk/Q2OrhXMhVk9W72AgbsIwWdb3rHCZTk=', 'bhbvzrtAtu3kscM6onXZqTnVuFhgJg', 'laurita@laurita.com'),
(25, 'nerea', 'lopez', 'nerea', 'h9keXEi2Ra8dJVkckMvgLPzVjXeATiFR1w/ovBhv4Nw=', 'NkzwJrbSYMnFokOak5HgN20ZFbOWe3', 'nerea@nerea.com');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD PRIMARY KEY (`idcomentario`),
  ADD KEY `idfoto` (`idfoto`),
  ADD KEY `iduser` (`iduser`);

--
-- Indices de la tabla `fotos`
--
ALTER TABLE `fotos`
  ADD PRIMARY KEY (`idfoto`),
  ADD KEY `iduser` (`iduser`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`iduser`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `comentarios`
--
ALTER TABLE `comentarios`
  MODIFY `idcomentario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;
--
-- AUTO_INCREMENT de la tabla `fotos`
--
ALTER TABLE `fotos`
  MODIFY `idfoto` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=101;
--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `iduser` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=26;
--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `comentarios`
--
ALTER TABLE `comentarios`
  ADD CONSTRAINT `comentarios_ibfk_1` FOREIGN KEY (`idfoto`) REFERENCES `fotos` (`idfoto`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `comentarios_ibfk_2` FOREIGN KEY (`iduser`) REFERENCES `usuarios` (`iduser`);

--
-- Filtros para la tabla `fotos`
--
ALTER TABLE `fotos`
  ADD CONSTRAINT `fotos_ibfk_1` FOREIGN KEY (`iduser`) REFERENCES `usuarios` (`iduser`) ON DELETE CASCADE ON UPDATE CASCADE;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
