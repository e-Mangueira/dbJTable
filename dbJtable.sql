create database dbJtable;
use dbJtable;

CREATE TABLE professor (
  Id int NOT NULL AUTO_INCREMENT,
  Name varchar(60) DEFAULT NULL,
  Department varchar(45) NOT NULL,
  Phone varchar(45) NOT NULL,
  Email varchar(100) NOT NULL,
  PRIMARY KEY (Id)
  );
  

INSERT INTO professor (Name, Department, Phone,Email) VALUES 
  ('Sergio', 'Computer Engeneering', '98 98122-2333', 'sergio@gmail.com'),
  ('Maria', 'Civil Engeneering', '98 98111-3202', 'maria@gmail.com'),
  ('Davi', 'Eletrical Engeneering', '98 98566-6073', 'davi@gmail.com'),
  ('Marcos', 'Math', '99 98294-8891', 'marcos@gmail.com'),
  ('Joana', 'Computer Engeneering', '99 98746-4477', 'joana@gmail.com'),
  ('Nilton', 'Eletrical Engeneering', '98 98355-5203', 'nilton@gmail.com');
