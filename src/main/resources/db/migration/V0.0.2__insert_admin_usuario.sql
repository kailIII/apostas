INSERT INTO usuario(
            id, datacriacao, dataatualizacao, nome, login, senha)
    VALUES (1, '2015-07-28', null, 'Administrador :)', 'admin', '21232f297a57a5a743894a0e4a801fc3');

CREATE TABLE hibernate_sequences(
	sequence_name varchar(255) NOT NULL,
	sequence_next_hi_value bigint NOT NULL
);

INSERT INTO hibernate_sequences( sequence_name, sequence_next_hi_value)
VALUES ('Usuario',2);
