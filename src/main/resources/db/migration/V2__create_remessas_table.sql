CREATE TABLE remessas (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    remetente_id BIGINT NOT NULL,

    destinatario_id BIGINT NOT NULL,

    valor_real DECIMAL(19,2) NOT NULL,

    valor_dolar DECIMAL(19,2) NOT NULL,

    cotacao_compra DECIMAL(19,6) NOT NULL,

    data_cotacao DATE NOT NULL,

    data_hora TIMESTAMP NOT NULL,

    CONSTRAINT fk_remessa_remetente
        FOREIGN KEY (remetente_id)
        REFERENCES usuarios(id),

    CONSTRAINT fk_remessa_destinatario
        FOREIGN KEY (destinatario_id)
        REFERENCES usuarios(id)
);