create table eventos(
	ev_id bigint not null auto_increment,
	ev_data_ini date not null,
	ev_data_fim date not null,
	ev_descricao varchar(255) not null,
	agenda_id bigint not null,

	primary key(ev_id),
	foreign key(agenda_id) REFERENCES agenda(id)
);