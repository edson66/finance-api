create table transacoes(
    id bigint not null auto_increment,
    descricao varchar(200) not null,
    valor decimal not null,
    data datetime not null,
    tipo varchar(100) not null,

    primary key(id)
);