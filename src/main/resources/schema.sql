CREATE TABLE endereco(id_endereco INTEGER AUTO_INCREMENT ,uuid VARCHAR(128) UNIQUE, rua VARCHAR(50), cep VARCHAR(10), numero VARCHAR(10), cidade VARCHAR(50), estado VARCHAR(2),
                      CONSTRAINT endereco_pk PRIMARY KEY(id_endereco));

CREATE TABLE pessoa(id_pessoa INTEGER AUTO_INCREMENT,uuid VARCHAR(128) UNIQUE,nome VARCHAR(70),id_endereco_fk INTEGER, uuid_endereco_fk VARCHAR(128),
                    CONSTRAINT pessoa_PK PRIMARY KEY(id_pessoa),
                    CONSTRAINT pessoa_endereco_id_fk FOREIGN KEY(id_endereco_fk) REFERENCES endereco(id_endereco));

CREATE TABLE produto(id_produto INTEGER AUTO_INCREMENT,uuid VARCHAR(128) UNIQUE,descricao VARCHAR(70),
                     CONSTRAINT produto_pk PRIMARY KEY(id_produto));

CREATE TABLE curso(id_curso INTEGER AUTO_INCREMENT ,uuid VARCHAR(128) UNIQUE, id_pessoa_fk INTEGER,uuid_pessoa_fk VARCHAR(128), descricao VARCHAR(200),
                   CONSTRAINT curso_pk PRIMARY KEY(id_curso),
                   CONSTRAINT curso_pessoa_id_fk FOREIGN KEY(id_pessoa_fk) REFERENCES pessoa(id_pessoa));

CREATE TABLE pedido(id_pedido INTEGER AUTO_INCREMENT ,descricao VARCHAR(70), uuid VARCHAR(128) UNIQUE,
                    CONSTRAINT pedido_pk PRIMARY KEY(id_pedido));

CREATE TABLE pessoa_produto(id_pessoa_fk INTEGER,id_produto_fk INTEGER,
                            CONSTRAINT pessoa_fk FOREIGN KEY(id_pessoa_fk) REFERENCES pessoa(id_pessoa),
                            CONSTRAINT produto_fk FOREIGN KEY(id_produto_fk) REFERENCES produto(id_produto));

CREATE TABLE pessoa_pedido(uuid_pessoa_fk VARCHAR(128),uuid_pedido_fk VARCHAR(128));