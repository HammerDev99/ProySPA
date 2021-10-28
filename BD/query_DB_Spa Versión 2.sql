create database DB_Spa
use DB_Spa

create table Cliente(
	cedula varchar(50) not null,
	nombre varchar(50) not null,
	telefono varchar(50) not null,
	celular varchar(50) not null,
	email varchar(50) not null,
	primary key(cedula) 
)

create table Procedimiento(
	cod_pro int identity,
	tipo_pro varchar(50) not null,
	descripcion_pro varchar(200) not null,
	duracion_pro varchar(50) not null,
	valor_pro float not null,
	primary key(cod_pro)
)

create table Agenda(
	cod_A int identity,
	fecha_A varchar(50) not null,
	hora_A varchar(50) not null,
	cedula varchar(50) not null,
	cod_pro int not null,
	primary key (fecha_A,hora_A),
	foreign key (cedula) references Cliente(cedula) on delete cascade on update cascade,
	foreign key (cod_pro) references Procedimiento(cod_pro) on delete cascade on update cascade
)

/*create table Factura(
	cod_fac int unique identity,
	fecha_A varchar(50) not null,
	hora_A varchar(50) not null,
	cedula varchar(50) not null,
	cod_pro int not null,
	primary key (cod_fac),
	foreign key (fecha_A,hora_A) references Agenda(fecha_A,hora_A),
)*/

insert into Cliente values ('1017223009','Daniel Arbelaez','4537324','3003963572','arbe.94@hotmail.com')
insert into Cliente values ('1017658409','Sandra Patricia','4537324','3032453572','sandralval.75@hotmail.com')
insert into Cliente values ('1145723009','Andres Arbelaez','4537324','3003963572','arbe.94@hotmail.com')
insert into Cliente values ('2154384563','Eliana Cristina Alvarez','4831705','31056635762','elianacrisal@hotmail.com')
insert into Cliente values ('43820635','Sebastian Mejia Serna','4605398','3205687946','smejias@hotmail.com')
insert into Cliente values ('43107582','Alba Acevedo Quintero','4531924','3113045823','alba@hotmail.com')


insert into Procedimiento values ('Masaje','masaje corporal','30 minutos',40000)
insert into Procedimiento values ('otra cosa','masaje facial','40 minutos',20000)
insert into Procedimiento values ('Cabitaci�n','cualquier cosa','30 minutos',40000)


insert into Agenda values ('10/10/2013','7:30 pm','1017223009',1)
insert into Agenda values ('27/10/2013','7:00 pm','1017223009',1)
insert into Agenda values ('27/10/2013','8:00 pm','1017223009',3)
insert into Agenda values ('28/10/2013','9:00 am','2154384563',2)

insert into Factura values ('10/10/2013','7:30 pm','1017223009',1)

select fecha_A,hora_A,cedula,cod_pro FROM Agenda where fecha_A='27/10/2013'
select fecha_A,hora_A,cedula,cod_pro FROM Agenda order by fecha_A
select *from Agenda inner join Procedimiento on Agenda.cod_pro=Procedimiento.cod_pro inner join Cliente on Agenda.cedula=Cliente.cedula where Agenda.fecha_A='27/10/2013'
select *from Procedimiento where cod_pro=1
select tipo_pro,descripcion_pro,duracion_pro,valor_pro from Procedimiento where cod_pro=1
select *from Procedimiento where cod_pro=1
select cod_pro,tipo_pro,descripcion_pro,duracion_pro,valor_pro from Procedimiento where cod_pro=1
select *from Cliente 
select *from Agenda 

UPDATE Agenda SET cedula=1017223009,cod_pro=2 WHERE fecha_A='27/10/2013' and hora_A='7:00 pm'
UPDATE Agenda SET cedula=2154384563,cod_pro=1,hora_A='9:00 pm' WHERE fecha_A='28/10/2013' and hora_A='9:00 am'

select *from Cliente where nombre='daniel arbelaez'

SELECT *FROM Agenda WHERE fecha_A='10/10/2013' and hora_A='7:30 pm'
select *from Agenda

select Agenda.*, nombre,telefono,celular,email,tipo_pro,descripcion_pro,duracion_pro,valor_pro from Agenda inner join Cliente on Cliente.cedula=Agenda.cedula inner join Procedimiento on Agenda.cod_pro=Procedimiento.cod_pro order by cod_A