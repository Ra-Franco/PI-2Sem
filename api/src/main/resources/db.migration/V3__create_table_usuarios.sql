create table usuarios(
	user_id bigint auto_increment,
	user_email varchar(200) not null,
	user_password varchar(200) not null,

	primary key(user_id)
);