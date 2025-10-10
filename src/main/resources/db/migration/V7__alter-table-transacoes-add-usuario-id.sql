alter table transacoes
add column usuario_id bigint not null;

alter table transacoes
add constraint fk_transacao_usuario
foreign key (usuario_id)
references usuarios (id);