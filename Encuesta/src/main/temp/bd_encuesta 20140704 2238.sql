-- MySQL Administrator dump 1.4
--
-- ------------------------------------------------------
-- Server version	5.5.29


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


--
-- Create schema cb_bd_encuesta
--

CREATE DATABASE IF NOT EXISTS cb_bd_encuesta;
USE cb_bd_encuesta;

--
-- Definition of table `acceso`
--

DROP TABLE IF EXISTS `acceso`;
CREATE TABLE `acceso` (
  `idAcceso` int(11) NOT NULL AUTO_INCREMENT,
  `idMenu` int(11) NOT NULL,
  `idCargo` int(11) NOT NULL,
  `habilitado` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idAcceso`),
  UNIQUE KEY `fk_accesomenu_codcargo_codmenu` (`idMenu`,`idCargo`),
  KEY `fk_codcargo_cargo_idx` (`idCargo`),
  KEY `fk_codmenu_menu_idx` (`idMenu`),
  CONSTRAINT `fk_codcargo_cargo` FOREIGN KEY (`idCargo`) REFERENCES `cargo` (`idCargo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_codmenu_menu` FOREIGN KEY (`idMenu`) REFERENCES `menu` (`idMenu`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `acceso`
--

/*!40000 ALTER TABLE `acceso` DISABLE KEYS */;
INSERT INTO `acceso` (`idAcceso`,`idMenu`,`idCargo`,`habilitado`) VALUES 
 (1,1,1,'HABILITADO'),
 (2,2,1,'HABILITADO'),
 (3,3,1,'HABILITADO'),
 (4,4,1,'HABILITADO'),
 (5,1,2,'HABILITADO'),
 (6,2,2,'HABILITADO'),
 (7,3,2,'HABILITADO'),
 (8,1,3,'HABILITADO'),
 (9,2,3,'HABILITADO'),
 (10,4,3,'HABILITADO'),
 (11,5,1,'HABILITADO');
/*!40000 ALTER TABLE `acceso` ENABLE KEYS */;


--
-- Definition of table `alternativas`
--

DROP TABLE IF EXISTS `alternativas`;
CREATE TABLE `alternativas` (
  `idAlternativas` int(11) NOT NULL,
  `tipo_alternativa_d` int(11) DEFAULT NULL,
  `tipo_respuesta_d` int(11) DEFAULT NULL,
  `porcentaje` double DEFAULT NULL,
  `puntaje` int(11) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `alternativa` varchar(250) DEFAULT NULL,
  `idPregunta` int(11) NOT NULL,
  PRIMARY KEY (`idAlternativas`),
  KEY `fk_Alternativas_Pregunta1_idx` (`idPregunta`),
  CONSTRAINT `fk_Alternativas_Pregunta1` FOREIGN KEY (`idPregunta`) REFERENCES `pregunta` (`idPregunta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `alternativas`
--

/*!40000 ALTER TABLE `alternativas` DISABLE KEYS */;
/*!40000 ALTER TABLE `alternativas` ENABLE KEYS */;


--
-- Definition of table `anio`
--

DROP TABLE IF EXISTS `anio`;
CREATE TABLE `anio` (
  `idAnio` int(11) NOT NULL,
  `idCiclo` int(11) NOT NULL,
  `idCarrera` int(11) NOT NULL,
  `ciclo_academico_d` int(11) DEFAULT NULL,
  `idCurso` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `maximo_alumnos` int(11) DEFAULT NULL,
  `minimo_alumnos` int(11) DEFAULT NULL,
  `nombre_seccion` varchar(250) DEFAULT NULL,
  `session_d` int(11) DEFAULT NULL,
  PRIMARY KEY (`idAnio`),
  KEY `fk_Anio_Ciclo1_idx` (`idCiclo`),
  KEY `fk_Anio_Carrera1_idx` (`idCarrera`),
  KEY `fk_Anio_Curso1_idx` (`idCurso`),
  CONSTRAINT `fk_Anio_Carrera1` FOREIGN KEY (`idCarrera`) REFERENCES `carrera` (`idCarrera`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Anio_Ciclo1` FOREIGN KEY (`idCiclo`) REFERENCES `ciclo` (`idCiclo`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Anio_Curso1` FOREIGN KEY (`idCurso`) REFERENCES `curso` (`idCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `anio`
--

/*!40000 ALTER TABLE `anio` DISABLE KEYS */;
/*!40000 ALTER TABLE `anio` ENABLE KEYS */;


--
-- Definition of table `asignacionprofesor`
--

DROP TABLE IF EXISTS `asignacionprofesor`;
CREATE TABLE `asignacionprofesor` (
  `idAsignacionProfesor` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `idAnio` int(11) NOT NULL,
  `idProfesor` int(11) NOT NULL,
  PRIMARY KEY (`idAsignacionProfesor`),
  KEY `fk_AsignacionProfesor_Anio1_idx` (`idAnio`),
  KEY `fk_AsignacionProfesor_Usuario1_idx` (`idProfesor`),
  CONSTRAINT `fk_AsignacionProfesor_Anio1` FOREIGN KEY (`idAnio`) REFERENCES `anio` (`idAnio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AsignacionProfesor_Usuario1` FOREIGN KEY (`idProfesor`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `asignacionprofesor`
--

/*!40000 ALTER TABLE `asignacionprofesor` DISABLE KEYS */;
/*!40000 ALTER TABLE `asignacionprofesor` ENABLE KEYS */;


--
-- Definition of table `aula`
--

DROP TABLE IF EXISTS `aula`;
CREATE TABLE `aula` (
  `idAula` int(11) NOT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `tipo_aula_d` int(11) DEFAULT NULL,
  `capacidad` varchar(250) DEFAULT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `observacion` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idAula`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `aula`
--

/*!40000 ALTER TABLE `aula` DISABLE KEYS */;
/*!40000 ALTER TABLE `aula` ENABLE KEYS */;


--
-- Definition of table `cargo`
--

DROP TABLE IF EXISTS `cargo`;
CREATE TABLE `cargo` (
  `idCargo` int(11) NOT NULL AUTO_INCREMENT,
  `cargo` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`idCargo`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `cargo`
--

/*!40000 ALTER TABLE `cargo` DISABLE KEYS */;
INSERT INTO `cargo` (`idCargo`,`cargo`) VALUES 
 (1,'ADMINISTRADOR'),
 (2,'DOCENTE'),
 (3,'ALUMNO');
/*!40000 ALTER TABLE `cargo` ENABLE KEYS */;


--
-- Definition of table `carrera`
--

DROP TABLE IF EXISTS `carrera`;
CREATE TABLE `carrera` (
  `idCarrera` int(11) NOT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `descripcion` varchar(250) DEFAULT NULL,
  `obervacion` varchar(250) DEFAULT NULL,
  `tipo_carrera_d` int(11) DEFAULT NULL,
  PRIMARY KEY (`idCarrera`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `carrera`
--

/*!40000 ALTER TABLE `carrera` DISABLE KEYS */;
/*!40000 ALTER TABLE `carrera` ENABLE KEYS */;


--
-- Definition of table `ciclo`
--

DROP TABLE IF EXISTS `ciclo`;
CREATE TABLE `ciclo` (
  `idCiclo` int(11) NOT NULL,
  `modalidad_d` int(11) DEFAULT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `annio_d` int(11) DEFAULT NULL,
  `numero_d` int(11) DEFAULT NULL,
  `fecha` datetime DEFAULT NULL,
  `estado_ciclo_d` int(11) DEFAULT NULL,
  `habilitado` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idCiclo`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `ciclo`
--

/*!40000 ALTER TABLE `ciclo` DISABLE KEYS */;
/*!40000 ALTER TABLE `ciclo` ENABLE KEYS */;


--
-- Definition of table `curso`
--

DROP TABLE IF EXISTS `curso`;
CREATE TABLE `curso` (
  `idCurso` int(11) NOT NULL,
  `curso` varchar(250) DEFAULT NULL,
  `tipo_curso_d` int(11) DEFAULT NULL,
  `curso_corto` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idCurso`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `curso`
--

/*!40000 ALTER TABLE `curso` DISABLE KEYS */;
/*!40000 ALTER TABLE `curso` ENABLE KEYS */;


--
-- Definition of table `dominio`
--

DROP TABLE IF EXISTS `dominio`;
CREATE TABLE `dominio` (
  `idDominio` int(11) NOT NULL,
  `campo` varchar(250) DEFAULT NULL,
  `valor` varchar(250) DEFAULT NULL,
  `valor_corto` varchar(250) DEFAULT NULL,
  `mas_datos` varchar(250) DEFAULT NULL,
  `idDominio_sub` int(11) DEFAULT NULL,
  `idDominio_hijo` int(11) DEFAULT NULL,
  `obligatorio` varchar(250) DEFAULT NULL,
  `habilitado` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idDominio`),
  KEY `fk_Dominio_Dominio_idx` (`idDominio_hijo`),
  KEY `fk_Dominio_Dominio1_idx` (`idDominio_sub`),
  CONSTRAINT `fk_Dominio_Dominio` FOREIGN KEY (`idDominio_hijo`) REFERENCES `dominio` (`idDominio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Dominio_Dominio1` FOREIGN KEY (`idDominio_sub`) REFERENCES `dominio` (`idDominio`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `dominio`
--

/*!40000 ALTER TABLE `dominio` DISABLE KEYS */;
INSERT INTO `dominio` (`idDominio`,`campo`,`valor`,`valor_corto`,`mas_datos`,`idDominio_sub`,`idDominio_hijo`,`obligatorio`,`habilitado`) VALUES 
 (1,'SEXO',NULL,NULL,NULL,NULL,NULL,NULL,'HABILITADO'),
 (2,'SEXO','MASCULINO','M',NULL,NULL,1,NULL,'HABILITADO'),
 (3,'SEXO','FEMENINO','F',NULL,NULL,1,NULL,'HABILITADO');
/*!40000 ALTER TABLE `dominio` ENABLE KEYS */;


--
-- Definition of table `encuesta`
--

DROP TABLE IF EXISTS `encuesta`;
CREATE TABLE `encuesta` (
  `idEncuesta` int(11) NOT NULL,
  `idTipoEncuesta` int(11) NOT NULL,
  `idAnio` int(11) NOT NULL,
  `idNumeroEncuesta` int(11) NOT NULL,
  PRIMARY KEY (`idEncuesta`),
  KEY `fk_AsignacionEncuesta_Encuesta1_idx` (`idTipoEncuesta`),
  KEY `fk_AsignacionEncuesta_Anio1_idx` (`idAnio`),
  KEY `fk_Encuesta_NumeroEncuesta1_idx` (`idNumeroEncuesta`),
  CONSTRAINT `fk_AsignacionEncuesta_Anio1` FOREIGN KEY (`idAnio`) REFERENCES `anio` (`idAnio`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_AsignacionEncuesta_Encuesta1` FOREIGN KEY (`idTipoEncuesta`) REFERENCES `tipoencuesta` (`idEncuesta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Encuesta_NumeroEncuesta1` FOREIGN KEY (`idNumeroEncuesta`) REFERENCES `numeroencuesta` (`idNumeroEncuesta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `encuesta`
--

/*!40000 ALTER TABLE `encuesta` DISABLE KEYS */;
/*!40000 ALTER TABLE `encuesta` ENABLE KEYS */;


--
-- Definition of table `encuestaalumno`
--

DROP TABLE IF EXISTS `encuestaalumno`;
CREATE TABLE `encuestaalumno` (
  `idEncuestaAlumno` int(11) NOT NULL,
  `inicio_encuesta` datetime DEFAULT NULL,
  `fin_encuesta` datetime DEFAULT NULL,
  `resolvio_d` int(11) DEFAULT NULL,
  `idEncuestaProfesor` int(11) NOT NULL,
  `idAlumno` int(11) NOT NULL,
  PRIMARY KEY (`idEncuestaAlumno`),
  KEY `fk_EncuestaAlumno_EncuestaProfesor1_idx` (`idEncuestaProfesor`),
  KEY `fk_EncuestaAlumno_Usuario1_idx` (`idAlumno`),
  CONSTRAINT `fk_EncuestaAlumno_EncuestaProfesor1` FOREIGN KEY (`idEncuestaProfesor`) REFERENCES `encuestaprofesor` (`idEncuestaProfesor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EncuestaAlumno_Usuario1` FOREIGN KEY (`idAlumno`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `encuestaalumno`
--

/*!40000 ALTER TABLE `encuestaalumno` DISABLE KEYS */;
/*!40000 ALTER TABLE `encuestaalumno` ENABLE KEYS */;


--
-- Definition of table `encuestaprofesor`
--

DROP TABLE IF EXISTS `encuestaprofesor`;
CREATE TABLE `encuestaprofesor` (
  `idEncuestaProfesor` int(11) NOT NULL,
  `idEncuesta` int(11) NOT NULL,
  `idProfesor` int(11) NOT NULL,
  PRIMARY KEY (`idEncuestaProfesor`),
  KEY `fk_EncuestaProfesor_Encuesta1_idx` (`idEncuesta`),
  KEY `fk_EncuestaProfesor_Usuario1_idx` (`idProfesor`),
  CONSTRAINT `fk_EncuestaProfesor_Encuesta1` FOREIGN KEY (`idEncuesta`) REFERENCES `encuesta` (`idEncuesta`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_EncuestaProfesor_Usuario1` FOREIGN KEY (`idProfesor`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `encuestaprofesor`
--

/*!40000 ALTER TABLE `encuestaprofesor` DISABLE KEYS */;
/*!40000 ALTER TABLE `encuestaprofesor` ENABLE KEYS */;


--
-- Definition of table `marcadas`
--

DROP TABLE IF EXISTS `marcadas`;
CREATE TABLE `marcadas` (
  `idMarcadas` int(11) NOT NULL,
  `idEncuestaAlumno` int(11) NOT NULL,
  `idAlternativas` int(11) NOT NULL,
  `tipo_respuesta_d` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMarcadas`),
  KEY `fk_Respondidas_EncuestaAlumno1_idx` (`idEncuestaAlumno`),
  KEY `fk_Marcadas_Alternativas1_idx` (`idAlternativas`),
  CONSTRAINT `fk_Marcadas_Alternativas1` FOREIGN KEY (`idAlternativas`) REFERENCES `alternativas` (`idAlternativas`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Respondidas_EncuestaAlumno1` FOREIGN KEY (`idEncuestaAlumno`) REFERENCES `encuestaalumno` (`idEncuestaAlumno`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `marcadas`
--

/*!40000 ALTER TABLE `marcadas` DISABLE KEYS */;
/*!40000 ALTER TABLE `marcadas` ENABLE KEYS */;


--
-- Definition of table `matricula`
--

DROP TABLE IF EXISTS `matricula`;
CREATE TABLE `matricula` (
  `idMatricula` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  `idAsignacion` int(11) NOT NULL,
  `idAlumno` int(11) NOT NULL,
  PRIMARY KEY (`idMatricula`),
  KEY `fk_Matricula_AsignacionProfesor1_idx` (`idAsignacion`),
  KEY `fk_Matricula_Usuario1_idx` (`idAlumno`),
  CONSTRAINT `fk_Matricula_AsignacionProfesor1` FOREIGN KEY (`idAsignacion`) REFERENCES `asignacionprofesor` (`idAsignacionProfesor`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_Matricula_Usuario1` FOREIGN KEY (`idAlumno`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `matricula`
--

/*!40000 ALTER TABLE `matricula` DISABLE KEYS */;
/*!40000 ALTER TABLE `matricula` ENABLE KEYS */;


--
-- Definition of table `menu`
--

DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `idMenu` int(11) NOT NULL AUTO_INCREMENT,
  `nom_menu` varchar(200) DEFAULT NULL,
  `url_menu` varchar(200) DEFAULT NULL,
  `icono_menu` varchar(200) DEFAULT NULL,
  `tipo_menu` int(11) DEFAULT NULL,
  `id_submenu` int(11) DEFAULT NULL,
  `orden_menu` int(11) DEFAULT NULL,
  `master_menu` int(11) DEFAULT NULL,
  PRIMARY KEY (`idMenu`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `menu`
--

/*!40000 ALTER TABLE `menu` DISABLE KEYS */;
INSERT INTO `menu` (`idMenu`,`nom_menu`,`url_menu`,`icono_menu`,`tipo_menu`,`id_submenu`,`orden_menu`,`master_menu`) VALUES 
 (1,'Inicio','#','fa fa-home',1,1,1,0),
 (2,'Mantenimiento','#','fa fa-table',1,2,2,1),
 (3,'Docente','#','#',2,1,3,1),
 (4,'Alumno','#','#',2,1,4,1),
 (5,'Dominio','inicio_dominio_buscar.html','#',2,1,5,1);
/*!40000 ALTER TABLE `menu` ENABLE KEYS */;


--
-- Definition of table `numeroencuesta`
--

DROP TABLE IF EXISTS `numeroencuesta`;
CREATE TABLE `numeroencuesta` (
  `idNumeroEncuesta` int(11) NOT NULL,
  `fecha` datetime DEFAULT NULL,
  PRIMARY KEY (`idNumeroEncuesta`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `numeroencuesta`
--

/*!40000 ALTER TABLE `numeroencuesta` DISABLE KEYS */;
/*!40000 ALTER TABLE `numeroencuesta` ENABLE KEYS */;


--
-- Definition of table `pregunta`
--

DROP TABLE IF EXISTS `pregunta`;
CREATE TABLE `pregunta` (
  `idPregunta` int(11) NOT NULL,
  `tipo_pregunta_d` int(11) DEFAULT NULL,
  `puntaje` int(11) DEFAULT NULL,
  `orden` int(11) DEFAULT NULL,
  `pregunta` varchar(250) DEFAULT NULL,
  `pregunta_obligatoria_d` int(11) DEFAULT NULL,
  `idNumeroEncuesta` int(11) NOT NULL,
  PRIMARY KEY (`idPregunta`),
  KEY `fk_Pregunta_NumeroEncuesta1_idx` (`idNumeroEncuesta`),
  CONSTRAINT `fk_Pregunta_NumeroEncuesta1` FOREIGN KEY (`idNumeroEncuesta`) REFERENCES `numeroencuesta` (`idNumeroEncuesta`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pregunta`
--

/*!40000 ALTER TABLE `pregunta` DISABLE KEYS */;
/*!40000 ALTER TABLE `pregunta` ENABLE KEYS */;


--
-- Definition of table `profesocurso`
--

DROP TABLE IF EXISTS `profesocurso`;
CREATE TABLE `profesocurso` (
  `idProfesoCurso` int(11) NOT NULL,
  `idProfesor` int(11) NOT NULL,
  `idCurso` int(11) NOT NULL,
  PRIMARY KEY (`idProfesoCurso`),
  KEY `fk_Asignacion_Profesor_Usuario1_idx` (`idProfesor`),
  KEY `fk_ProfesoCurso_Curso1_idx` (`idCurso`),
  CONSTRAINT `fk_Asignacion_Profesor_Usuario1` FOREIGN KEY (`idProfesor`) REFERENCES `usuario` (`idUsuario`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_ProfesoCurso_Curso1` FOREIGN KEY (`idCurso`) REFERENCES `curso` (`idCurso`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profesocurso`
--

/*!40000 ALTER TABLE `profesocurso` DISABLE KEYS */;
/*!40000 ALTER TABLE `profesocurso` ENABLE KEYS */;


--
-- Definition of table `tipoencuesta`
--

DROP TABLE IF EXISTS `tipoencuesta`;
CREATE TABLE `tipoencuesta` (
  `idEncuesta` int(11) NOT NULL,
  `encuesta` varchar(250) DEFAULT NULL,
  `idCiclo` int(11) NOT NULL,
  `habilitado` varchar(250) DEFAULT NULL,
  PRIMARY KEY (`idEncuesta`),
  KEY `fk_Encuesta_Ciclo1_idx` (`idCiclo`),
  CONSTRAINT `fk_Encuesta_Ciclo1` FOREIGN KEY (`idCiclo`) REFERENCES `ciclo` (`idCiclo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tipoencuesta`
--

/*!40000 ALTER TABLE `tipoencuesta` DISABLE KEYS */;
/*!40000 ALTER TABLE `tipoencuesta` ENABLE KEYS */;


--
-- Definition of table `usuario`
--

DROP TABLE IF EXISTS `usuario`;
CREATE TABLE `usuario` (
  `idUsuario` int(11) NOT NULL,
  `apellido_paterno` varchar(250) DEFAULT NULL,
  `apellido_materno` varchar(250) DEFAULT NULL,
  `nombre` varchar(250) DEFAULT NULL,
  `user_name` varchar(250) DEFAULT NULL,
  `pass_name` varchar(250) DEFAULT NULL,
  `email` varchar(250) DEFAULT NULL,
  `fecha_nac` datetime DEFAULT NULL,
  `telefono` varchar(250) DEFAULT NULL,
  `codigo` varchar(250) DEFAULT NULL,
  `habilitado` varchar(250) DEFAULT NULL,
  `tipo_usuario_d` int(11) DEFAULT NULL,
  `idCargo` int(11) DEFAULT NULL,
  PRIMARY KEY (`idUsuario`),
  KEY `fk_cargo_usuario_idx` (`idCargo`),
  CONSTRAINT `fk_cargo_usuario` FOREIGN KEY (`idCargo`) REFERENCES `cargo` (`idCargo`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `usuario`
--

/*!40000 ALTER TABLE `usuario` DISABLE KEYS */;
INSERT INTO `usuario` (`idUsuario`,`apellido_paterno`,`apellido_materno`,`nombre`,`user_name`,`pass_name`,`email`,`fecha_nac`,`telefono`,`codigo`,`habilitado`,`tipo_usuario_d`,`idCargo`) VALUES 
 (1,'Correa',NULL,NULL,'admin','admin',NULL,NULL,NULL,NULL,NULL,1,1);
/*!40000 ALTER TABLE `usuario` ENABLE KEYS */;




/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
