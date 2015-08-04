CREATE TABLE usuario (
    id bigint NOT NULL,
    datacriacao timestamp without time zone,
    dataatualizacao timestamp without time zone,
    nome varchar(255) NOT NULL,
    login varchar(20) NOT NULL UNIQUE,
    senha varchar(50) NOT NULL
);

ALTER TABLE usuario ADD CONSTRAINT usuario_pkey PRIMARY KEY (id);
