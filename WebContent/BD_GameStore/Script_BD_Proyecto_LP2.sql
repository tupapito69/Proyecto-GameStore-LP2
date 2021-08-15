CREATE DATABASE gamescenter_BD;
use gamescenter_BD;

CREATE TABLE CARGO
(
	cod_cargo VARCHAR(3) PRIMARY KEY,
    nombre VARCHAR(20) NOT NULL
);

insert into CARGO (cod_cargo,nombre) values('A01','Administrador');
insert into CARGO (cod_cargo,nombre) values ('A02','Cajero/Vendedor');

CREATE TABLE EMPLEADO
(
	cod_empleado INT AUTO_INCREMENT PRIMARY KEY,
    nom_empleado VARCHAR(20) NOT NULL,
    ape1_empleado VARCHAR(20) NOT NULL,
    ape2_empleado VARCHAR(20) NOT NULL,
    correo_empleado VARCHAR(60) NULL,
    tlf_empleado INT NULL,
    cod_empleado_sup INT NOT NULL,
    cod_cargo VARCHAR(3),
    FOREIGN KEY(cod_cargo) REFERENCES cargo(cod_cargo),
    FOREIGN KEY (cod_empleado_sup) REFERENCES EMPLEADO(cod_empleado)
);

ALTER TABLE EMPLEADO AUTO_INCREMENT=1000;
insert into EMPLEADO (nom_empleado,ape1_empleado,ape2_empleado,correo_empleado,tlf_empleado,cod_empleado_sup,cod_cargo) 
				values('Carlos','Mogollon','Espinoza','mogolloncarlosalberto23@gmail.com',924589632,1000,'A01');

CREATE TABLE CUPONES
(
	ID VARCHAR(10) PRIMARY KEY,
    cant_usos int NOT NULL,
    importe double NOT NULL,
    cod_empleado INT NOT NULL,
    foreign key (cod_empleado) references EMPLEADO(cod_empleado)
);


INSERT INTO CUPONES (ID,cant_usos,importe,cod_empleado) values ('FRIDAY2020',10,100,1000);

CREATE TABLE LOGIN
(
	usuario VARCHAR(7) PRIMARY KEY,
    contraseña VARCHAR(100) NOT NULL default('c92e3c034d0049f968def81365e764fd'),
    cod_empleado INT NOT NULL,
    FOREIGN KEY (cod_empleado) REFERENCES EMPLEADO(cod_empleado)
);
-- Contraseñaa por defecto 1G@meCent23
insert into LOGIN (usuario,contraseña,cod_empleado) values ('admin','c92e3c034d0049f968def81365e764fd','1000');

CREATE TABLE CATEGORIA
(
	cod_cat VARCHAR(3) PRIMARY KEY,
    nom_categoria VARCHAR(30) NOT NULL
);

INSERT INTO CATEGORIA (cod_cat,nom_categoria) VALUES ('C01','XBOX');
INSERT INTO CATEGORIA (cod_cat,nom_categoria) VALUES ('C02','PLAYSTATION');
INSERT INTO CATEGORIA (cod_cat,nom_categoria) VALUES ('C03','NINTENDO');
INSERT INTO CATEGORIA (cod_cat,nom_categoria) VALUES ('C04','PC');

CREATE TABLE PRODUCTO
(
	cod_producto VARCHAR(5) PRIMARY KEY,
    nom_producto VARCHAR(20) NOT NULL,
    precio_producto DOUBLE NOT NULL,
    stock_producto INT NOT NULL,
    cod_cat VARCHAR(3) NOT NULL,
    FOREIGN KEY (cod_cat) REFERENCES CATEGORIA(cod_cat)
);
INSERT INTO PRODUCTO (cod_producto,nom_producto,precio_producto,stock_producto,cod_cat) VALUES
('P0001','FIFA2020',239.99,20,'C01');
INSERT INTO PRODUCTO (cod_producto,nom_producto,precio_producto,stock_producto,cod_cat) VALUES
('P0002','FIFA2020',239.99,20,'C02');
INSERT INTO PRODUCTO (cod_producto,nom_producto,precio_producto,stock_producto,cod_cat) VALUES
('P0003','FIFA2020',239.99,20,'C03');
INSERT INTO PRODUCTO (cod_producto,nom_producto,precio_producto,stock_producto,cod_cat) VALUES
('P0004','FIFA2020',239.99,20,'C04');
INSERT INTO PRODUCTO (cod_producto,nom_producto,precio_producto,stock_producto,cod_cat) VALUES
('P0005','HALF-LIFE 2',100,20,'C04');
INSERT INTO PRODUCTO (cod_producto,nom_producto,precio_producto,stock_producto,cod_cat) VALUES
('P0006','PES 2020',239.99,20,'C02');
INSERT INTO PRODUCTO (cod_producto,nom_producto,precio_producto,stock_producto,cod_cat) VALUES
('P0007','STARWARS JEDI FO',150,20,'C01');
INSERT INTO PRODUCTO (cod_producto,nom_producto,precio_producto,stock_producto,cod_cat) VALUES
('P0008','STARWARS JEDI FO',150,20,'C02');
INSERT INTO PRODUCTO (cod_producto,nom_producto,precio_producto,stock_producto,cod_cat) VALUES
('P0009','STARWARS JEDI FO',150,20,'C03');
INSERT INTO PRODUCTO (cod_producto,nom_producto,precio_producto,stock_producto,cod_cat) VALUES
('P0010','ZELDA BOTW',239.99,20,'C03');
INSERT INTO PRODUCTO (cod_producto,nom_producto,precio_producto,stock_producto,cod_cat) VALUES
('P0011','RDR2',239.99,20,'C01');


CREATE TABLE CLIENTE
(
	DNI VARCHAR(8) PRIMARY KEY,
    nom_cliente VARCHAR(20) NOT NULL,
    ape1_cliente VARCHAR(20) NOT NULL,
    ape2_cliente VARCHAR(20) NOT NULL,
    tlf_cliente INT NULL
);
SELECT * FROM CLIENTE;
CREATE TABLE ORDEN_VENTA
(
	cod_ordenVenta VARCHAR(10) PRIMARY KEY,
    cod_empleado INT NOT NULL,
    dni_cliente VARCHAR(8) NOT NULL,
    fecha_ordenventa DATETIME NOT NULL,
    importe_total DOUBLE NOT NULL,
    id_cupon VARCHAR(10) NULL,
    FOREIGN KEY (cod_empleado) REFERENCES EMPLEADO(cod_empleado),
    FOREIGN KEY (dni_cliente) REFERENCES CLIENTE(DNI),
    FOREIGN KEY (id_cupon) REFERENCES CUPONES(ID)
);

CREATE TABLE ORDEN_CREDITO
(
	cod_ordenCredito VARCHAR(10) PRIMARY KEY,
    cod_ordenVenta VARCHAR(10) NOT NULL,
    cod_empleado INT NOT NULL,
    dni_cliente VARCHAR(8) NOT NULL,
    fecha_ordencredito DATETIME NOT NULL,
    importe_total DOUBLE NOT NULL,
    id_cupon VARCHAR(10) NULL,
    FOREIGN KEY (cod_ordenVenta) REFERENCES ORDEN_VENTA(cod_ordenVenta),
    FOREIGN KEY (cod_empleado) REFERENCES EMPLEADO(cod_empleado),
    FOREIGN KEY (dni_cliente) REFERENCES CLIENTE(DNI),
    FOREIGN KEY (id_cupon) REFERENCES CUPONES(ID)
);

CREATE TABLE DETALLE_ORDENVENTA
(
	cod_ordenVenta VARCHAR(10) NOT NULL,
    cod_producto VARCHAR(5) NOT NULL,
    cantidad_venta INT NOT NULL,
    precio_detalleventa DOUBLE NOT NULL,
    FOREIGN KEY (cod_ordenVenta) REFERENCES ORDEN_VENTA(cod_ordenVenta),
    FOREIGN KEY (cod_producto) REFERENCES PRODUCTO(cod_producto)
);

CREATE TABLE DETALLE_ORDENCREDITO
(
	cod_ordenCredito VARCHAR(10) NOT NULL,
    cod_producto VARCHAR(5) NOT NULL,
    cantidad_venta INT NOT NULL,
    precio_detalleventa DOUBLE NOT NULL,
    FOREIGN KEY (cod_ordenCredito) REFERENCES ORDEN_CREDITO(cod_ordenCredito),
    FOREIGN KEY (cod_producto) REFERENCES PRODUCTO(cod_producto)
);

ALTER TABLE producto ADD eliminar BOOLEAN NOT NULL DEFAULT TRUE AFTER cod_cat;