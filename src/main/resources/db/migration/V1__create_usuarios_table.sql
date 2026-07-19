CREATE TABLE usuarios (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    nome_completo VARCHAR(255) NOT NULL,

    email VARCHAR(255) NOT NULL,

    senha VARCHAR(255) NOT NULL,

    tipo_usuario VARCHAR(10) NOT NULL,

    cpf VARCHAR(14),

    cnpj VARCHAR(18),

    saldo_real DECIMAL(19,2) NOT NULL,

    saldo_dolar DECIMAL(19,2) NOT NULL
);