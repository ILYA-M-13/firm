drop table if exists employee;
drop table if exists department;


create table department (
 id integer AUTO_INCREMENT PRIMARY KEY,
 name varchar(100) not null);

create table employee (
 id integer AUTO_INCREMENT PRIMARY KEY,
 name varchar(100) not null,
 birthday date not null,
 salary integer not null,
 department_id integer not null);


INSERT into department (name)
VALUES
('Бухгалтерия'),
('Отдел Безопасности'),
('Секретариат'),
('ПТО');

INSERT into employee (name, birthday, salary, department_id)
VALUES
('Анастасия', '1990-08-08', '100000','1'),
('Елена', '1991-09-09', '50000','1'),
('Екатерина', '1989-07-07', '45000','1'),
('Ольга', '1988-06-05', '90000','1'),
('Игорь', '1988-07-02', '200000','2'),
('Елизавета', '1988-06-05', '150000','2'),
('Семен', '1970-09-05', '90000','2'),
('Борис', '1978-06-04', '123000','2'),
('Гальнара', '1976-03-05', '30000','3'),
('Марина', '1968-06-05', '55000','3'),
('Мирослава', '2000-01-02', '62000','3'),
('Александра', '2001-06-05', '85000','3'),
('Александр', '1999-06-05', '90000','4'),
('Артем', '1998-06-05', '103000','4'),
('Павел', '1997-06-05', '64000','4'),
('Илья', '1996-06-05', '48000','4');


