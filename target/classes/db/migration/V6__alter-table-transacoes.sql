alter table transacoes
add column categoria_id bigint not null;

alter table transacoes
add constraint fk_transacao_categoria
foreign key (categoria_id)
references categorias (id);