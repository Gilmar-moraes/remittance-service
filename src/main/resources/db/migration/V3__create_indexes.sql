CREATE UNIQUE INDEX uk_usuario_email
ON usuarios(email);

CREATE UNIQUE INDEX uk_usuario_cpf
ON usuarios(cpf);

CREATE UNIQUE INDEX uk_usuario_cnpj
ON usuarios(cnpj);

CREATE INDEX idx_remessa_remetente
ON remessas(remetente_id);

CREATE INDEX idx_remessa_destinatario
ON remessas(destinatario_id);

CREATE INDEX idx_remessa_data
ON remessas(data_hora);