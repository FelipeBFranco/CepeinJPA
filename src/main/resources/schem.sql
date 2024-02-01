CREATE TABLE endereco(id_endereco NUMERIC(5),uuid VARCHAR(128),
                      CONSTRAINT ENDERECO_PK PRIMARY KEY(id_endereco));

CREATE TABLE pessoa(id_pessoa NUMERIC(10),uuid VARCHAR(128),nome VARCHAR(70),id_endereco_fk NUMERIC(10), uuid_endereco_fk VARCHAR(128),
                    CONSTRAINT pessoa_PK PRIMARY KEY(id_pessoa),
                    CONSTRAINT pessoa_endereco_id_fk FOREIGN KEY(id_endereco_fk) REFERENCES endereco(id_endereco));

CREATE TABLE produto(id_produto NUMERIC(10),uuid VARCHAR(128),descricao VARCHAR(70),
                     CONSTRAINT produto_pk PRIMARY KEY(id_produto));

CREATE TABLE curso(id_curso NUMERIC(10),uuid VARCHAR(128), id_pessoa_fk NUMERIC(10),uuid_pessoa_fk VARCHAR(128), descricao VARCHAR(200),
                   CONSTRAINT curso_pk PRIMARY KEY(id_curso),
                   CONSTRAINT curso_pessoa_id_fk FOREIGN KEY(id_pessoa_fk) REFERENCES pessoa(id_pessoa));

CREATE TABLE pedido(id_pedido NUMERIC(10),descricao VARCHAR(70), uuid VARCHAR(128),
                    CONSTRAINT pedido_pk PRIMARY KEY(id_pedido));

CREATE TABLE pessoa_produto(id_pessoa_fk NUMERIC(10),id_produto_fk NUMERIC(10),
                            CONSTRAINT pessoa_fk FOREIGN KEY(id_pessoa_fk) REFERENCES pessoa(id_pessoa),
                            CONSTRAINT produto_fk FOREIGN KEY(id_produto_fk) REFERENCES produto(id_produto));

CREATE TABLE pessoa_pedido(uuid_pessoa_fk VARCHAR(128),uuid_pedido_fk VARCHAR(128));