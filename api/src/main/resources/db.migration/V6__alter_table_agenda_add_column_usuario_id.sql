alter table agenda add column user_id bigint;
alter table agenda add foreign key (user_id) references usuarios(user_id);
alter table agenda add constraint fk_usuario_id foreign key (user_id) references usuarios(user_id);
