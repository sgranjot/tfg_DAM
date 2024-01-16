CREATE DATABASE IF NOT EXISTS tfg;
USE tfg;

DROP TABLE IF EXISTS usuario;
CREATE TABLE usuario (
    id_usuario INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    usuario VARCHAR(40) NOT NULL UNIQUE,
    contrasenia VARCHAR(20) NOT NULL,
    activado BOOLEAN NOT NULL
);

DROP TABLE IF EXISTS direccion;
CREATE TABLE direccion (
    id_direccion INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    calle VARCHAR(50) NOT NULL,
    numero VARCHAR(3) NOT NULL,
    piso VARCHAR(10),
    puerta VARCHAR(10),
    codigo_postal VARCHAR(5) NOT NULL,
    poblacion VARCHAR(40) NOT NULL,
    provincia VARCHAR(40) NOT NULL,
    pais VARCHAR(20) NOT NULL,
    numero_plaza VARCHAR(3),
    CONSTRAINT uc_direccion UNIQUE (calle,numero,piso,puerta,codigo_postal,poblacion,provincia,pais,numero_plaza)
);

DROP TABLE IF EXISTS arrendatario;
CREATE TABLE arrendatario (
    id_arrendatario INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    nombre VARCHAR(40) NOT NULL,
    apellidos VARCHAR(50) NOT NULL,
    dni VARCHAR(9) NOT NULL UNIQUE,
    telefono VARCHAR(9) NOT NULL,
    email VARCHAR(30),
    CONSTRAINT FKarrendatario_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario)
        ON DELETE CASCADE ON UPDATE CASCADE
);

DROP TABLE IF EXISTS tipo_propiedad;
CREATE TABLE tipo_propiedad (
    id_tipo_propiedad INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    tipo_propiedad VARCHAR(15) NOT NULL UNIQUE
);

DROP TABLE IF EXISTS propiedad;
CREATE TABLE propiedad (
    id_propiedad INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_direccion INT NOT NULL,
    id_tipo_propiedad INT NOT NULL,
    libre BOOLEAN NOT NULL,
    CONSTRAINT FKpropiedad_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FKpropiedad_direccion FOREIGN KEY (id_direccion)
        REFERENCES direccion (id_direccion)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FKpropiedad_tipo_propiedad FOREIGN KEY (id_tipo_propiedad)
        REFERENCES tipo_propiedad (id_tipo_propiedad)
        ON DELETE CASCADE ON UPDATE CASCADE,
	CONSTRAINT uc_propiedad_usuario_direccion_tipo UNIQUE (id_usuario, id_direccion, id_tipo_propiedad)
);

drop table if exists contrato;
CREATE TABLE contrato (
    id_contrato INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_propiedad INT NOT NULL,
    id_arrendatario INT NOT NULL,
    fecha_inicio DATE NOT NULL,
    fecha_fin DATE NOT NULL,
    fianza INT NOT NULL,
    mensualidad INT NOT NULL,
    CONSTRAINT FKcontrato_propiedad FOREIGN KEY (id_propiedad)
        REFERENCES propiedad (id_propiedad)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FKcontrato_arrendatario FOREIGN KEY (id_arrendatario)
        REFERENCES arrendatario (id_arrendatario)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FKcontrato_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario)
        ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists mensualidad;
CREATE TABLE mensualidad (
    id_mensualidad INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_contrato INT NOT NULL,
    cantidad INT NOT NULL,
    fecha DATE NOT NULL,
    estado BOOLEAN NOT NULL,
    CONSTRAINT UQ_mensualidad_contrato_fecha UNIQUE (id_contrato, fecha),
    CONSTRAINT FKmensualidad_contrato FOREIGN KEY (id_contrato)
        REFERENCES contrato (id_contrato)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FKmensualidad_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario)
        ON DELETE CASCADE ON UPDATE CASCADE
);

drop table if exists tipo_gasto;
CREATE TABLE tipo_gasto (
    id_tipo_gasto INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    tipo_gasto VARCHAR(15) NOT NULL UNIQUE
);

drop table if exists gasto;
CREATE TABLE gasto (
    id_gasto INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    id_usuario INT NOT NULL,
    id_tipo_gasto INT NOT NULL,
    id_propiedad INT NOT NULL,
    importe DOUBLE NOT NULL,
    fecha DATE NOT NULL,
    descripcion VARCHAR(300),
    CONSTRAINT FKgasto_tipo_gasto FOREIGN KEY (id_tipo_gasto)
        REFERENCES tipo_gasto (id_tipo_gasto)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FKgasto_propiedad FOREIGN KEY (id_propiedad)
        REFERENCES propiedad (id_propiedad)
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FKgasto_usuario FOREIGN KEY (id_usuario)
        REFERENCES usuario (id_usuario)
        ON DELETE CASCADE ON UPDATE CASCADE
);

insert into tipo_propiedad values
(null,'vivienda'),
(null,'local'),
(null,'oficina'),
(null,'nave'),
(null,'garaje');

insert into tipo_gasto values
(null, 'impuesto'),
(null, 'comunidad'),
(null, 'suministro'),
(null, 'incidencia'),
(null,'hipoteca');

insert into usuario values
(0,'pepe','pepe',1);

insert into direccion values
(null, 'Leganes','18','2º','B','28982','Parla','Madrid','España','No'),
(null, 'Zamora','4','3º','A','28982','Parla','Madrid','España','No'),
(null, 'Jerusalén','1','4º','C','28982','Parla','Madrid','España','No'),
(null, 'Real','33','2º','D','28982','Parla','Madrid','España','No'),
(null, 'Servilla',1,'','','28980','Madrid','Madrid','España','52'),
(null, 'Gran Via',133,'','','28980','Madrid','Madrid','España','24'),
(null, 'Goya',13,'','','28980','Madrid','Madrid','España','No'),
(null, 'Serrano',123,'','','28980','Madrid','Madrid','España','No'),
(null, 'Avd. de Europa',41,'1','A','28414','Pinto','Madrid','España','No'),
(null, 'Turano',14,'','','28782','Fuenlabrada','Madrid','España','No');

insert into propiedad values
(null,1,1,1,1),
(null,1,2,1,1),
(null,1,3,1,0),
(null,1,4,1,0),
(null,1,5,5,1),
(null,1,6,5,0),
(null,1,7,2,1),
(null,1,8,2,0),
(null,1,9,3,1),
(null,1,10,4,1);

insert into arrendatario values
(null,1,'Alberto','Hervás Portillo','52125478L','658741526','alberto.hervas@gmail.com'),
(null,1,'Daniel','Herranz Segundo','52147887F','676421586','daniel.herranz@gmail.com'),
(null,1,'Alfredo','Retuerto Rivera','51458520N','655412236','alfredo.retuerto@gmail.com'),
(null,1,'Roberto','Fernández Martín','53221774S','669878522','roberto.fernandez@gmail.com');

insert into contrato values
(null,1,3,1,'2021-05-10','2025-05-10',1200,600),
(null,1,4,2,'2022-02-11','2026-02-11',1400,700),
(null,1,6,3,'2022-10-10','2023-10-10',200,150),
(null,1,8,4,'2020-08-01','2024-08-01',8000,4000);

insert into gasto values
(null,1,1,1,253.50,'2023-05-05','Impuesto de bienes inmuebles'),
(null,1,2,2,42.40,'2023-05-05',''),
(null,1,3,3,41.86,'2023-05-05','Agua'),
(null,1,4,4,129.90,'2023-05-05','Reparación de caldera'),
(null,1,5,5,327.66,'2023-05-05',''),
(null,1,1,6,224.70,'2023-05-05','Impuesto de bienes inmuebles'),
(null,1,2,7,35.50,'2023-05-05',''),
(null,1,3,8,103.50,'2023-05-05','Gas'),
(null,1,4,9,789.99,'2023-05-05','Sustitución de frigorífico'),
(null,1,5,10,1214.4,'2023-05-05','');