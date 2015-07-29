CREATE TABLE aposta(
  id bigint NOT NULL,
  dataatualizacao timestamp,
  datacriacao timestamp NOT NULL,
  datefinalizacao date NOT NULL,
  descricao varchar(255) NOT NULL,
  CONSTRAINT aposta_pkey PRIMARY KEY (id)
);

CREATE TABLE palpite(
  id bigint NOT NULL,
  dataatualizacao timestamp,
  datacriacao timestamp NOT NULL,
  descricao varchar(255) NOT NULL,
  venceu boolean NOT NULL,
  usuario_id bigint NOT NULL,
  aposta_id bigint NOT NULL,
  CONSTRAINT palpite_pkey PRIMARY KEY (id),
  CONSTRAINT palpite_usuario_fkey FOREIGN KEY (usuario_id) REFERENCES usuario(id),
  CONSTRAINT palpite_aposta_fkey FOREIGN KEY (aposta_id) REFERENCES aposta(id)
);
