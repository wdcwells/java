CREATE TABLE PERSON (
	id BIGINT NOT NULL AUTO_INCREMENT,
	first_name varchar(255) NULL,
	last_name varchar(255) NULL,
	PRIMARY KEY (id)
);

insert into PERSON (first_name, last_name) values ('Dave', 'Syer');