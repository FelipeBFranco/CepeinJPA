CREATE TABLE endereco (
    id_endereco NUMERIC(5)PRIMARY KEY,
    uuid_endereco VARCHAR(128),
    cidade_endereco VARCHAR(60),
    uf_endereco VARCHAR(2)
);

CREATE TABLE pessoa (
    id_pessoa NUMERIC(10),
    uuid_pessoa VARCHAR(128),
    nome_pessoa VARCHAR(70),
    id_endereco_fk NUMERIC(5), 
    uuid_endereco_fk VARCHAR(128),
    CONSTRAINT pessoa_pk PRIMARY KEY (id_pessoa),
    CONSTRAINT pessoa_endereco_id_fk FOREIGN KEY (id_endereco_fk) REFERENCES endereco(id_endereco)
);

