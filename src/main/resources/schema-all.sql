CREATE TABLE IF NOT EXISTS tb_produtos (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255) NOT NULL,
    descricao VARCHAR(255),
    qtd_estoque BIGINT,
    preco DECIMAL(10, 2),
    ativo BOOLEAN,
    dt_importacao DATETIME
    );

UPDATE tb_produtos SET ativo = false;