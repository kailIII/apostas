CREATE TABLE aposta(
  id bigint NOT NULL,
  dataatualizacao timestamp,
  datacriacao timestamp NOT NULL,
  datefinalizacao date NOT NULL,
  descricao varchar(255) NOT NULL,
  vencedor_id bigint,
  CONSTRAINT aposta_pkey PRIMARY KEY (id),
  CONSTRAINT aposta_vencedor_fkey FOREIGN KEY (vencedor_id) REFERENCES usuario (id)
);

CREATE TABLE aposta_usuario(
  aposta_id bigint NOT NULL,
  usuarios_id bigint NOT NULL,
  CONSTRAINT apostausuario_usu_fkey FOREIGN KEY (usuarios_id) REFERENCES usuario (id),
  CONSTRAINT apostausuario_aposta_fkey FOREIGN KEY (aposta_id) REFERENCES aposta (id)
);
