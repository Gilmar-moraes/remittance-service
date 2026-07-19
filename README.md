# Remittance Service

Sistema de remessas desenvolvido como desafio técnico.

O projeto permite o cadastro de usuários Pessoa Física (PF) e Pessoa Jurídica (PJ), gerenciamento de carteiras em Real (BRL) e Dólar (USD) e realização de remessas internacionais utilizando a cotação oficial do Banco Central do Brasil.

---

## Arquitetura

O projeto foi desenvolvido utilizando **Arquitetura Hexagonal (Ports & Adapters)**.

Essa arquitetura foi escolhida por separar claramente as regras de negócio das tecnologias utilizadas, facilitando manutenção, testes e futuras evoluções da aplicação.

As regras de negócio permanecem isoladas da infraestrutura, permitindo substituir banco de dados, APIs externas ou frameworks sem impactar o domínio da aplicação.

---

# Tecnologias utilizadas

| Tecnologia | Motivo da escolha |
|------------|-------------------|
| Java 21 | Versão LTS com recursos modernos da linguagem. |
| Spring Boot 4.1 | Desenvolvimento rápido de APIs REST seguindo boas práticas. |
| Spring Web | Exposição dos endpoints REST. |
| Spring Data JPA | Persistência desacoplada utilizando repositórios. |
| H2 Database | Banco em memória para facilitar testes e execução do desafio. |
| Spring Validation | Validação declarativa das requisições. |
| WebClient | Cliente HTTP moderno, não bloqueante e preparado para futuras integrações. |
| Spring Cache | Evita chamadas desnecessárias à API do Banco Central. |
| Spring Boot Actuator | Monitoramento e métricas da aplicação. |
| Springdoc OpenAPI (Swagger) | Documentação automática da API REST. |
| JUnit 5 | Testes unitários. |
| Mockito | Mock de dependências durante os testes. |
| Maven | Gerenciamento de dependências e build da aplicação. |

---

# Decisões Técnicas

## Arquitetura Hexagonal

Foi escolhida para manter o domínio desacoplado da infraestrutura.

Toda regra de negócio permanece concentrada na camada de aplicação e domínio.

As integrações com banco de dados e API externa são realizadas através de Ports & Adapters.

---

## WebClient

Mesmo sendo uma aplicação síncrona, foi escolhido o WebClient por ser a tecnologia recomendada pelo Spring para novas aplicações e permitir futura evolução para processamento reativo.

---

## Cache

A cotação do dólar é armazenada em cache para evitar múltiplas consultas consecutivas ao Banco Central.

---

## Banco H2

Utilizado apenas para simplificar a execução do desafio.

A arquitetura permite substituir facilmente por PostgreSQL, MySQL ou Oracle.

---

### Flyway

Responsável pelo versionamento do banco de dados, garantindo que a estrutura do banco seja reproduzida de forma consistente em qualquer ambiente.
---
### Springdoc OpenAPI (Swagger)

Documentação automática da API REST.

## Funcionalidades implementadas

- Cadastro de usuários PF e PJ
- Carteira em Real
- Carteira em Dólar
- Depósito de saldo
- Remessas internacionais
- Conversão automática Real → Dólar
- Consulta da cotação do Banco Central
- Cache das cotações
- Limite diário por tipo de usuário
- Validação de saldo
- Validação de CPF/CNPJ
- Tratamento global de exceções
- Documentação Swagger
- Arquitetura Hexagonal

---

# Como executar

Clone o projeto

```bash
git clone
```

Entre na pasta

```bash
cd remittance-service
```

Execute

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em

```
http://localhost:8080
```

---

# Swagger

Após iniciar a aplicação:

```
http://localhost:8080/swagger-ui.html
```

---

# Actuator

```
http://localhost:8080/actuator
```

Exemplos:

```
/actuator/health

/actuator/info

/actuator/metrics
```

---

# Executando os testes

```bash
mvn test
```

---

# Melhorias Futuras

- Docker e Docker Compose para facilitar a execução da aplicação.
- Persistência e histórico de movimentações da carteira (extrato).
- Autenticação e autorização com Spring Security + JWT.
- Criptografia de senhas com BCrypt
- Ampliação da cobertura de testes unitários e de integração.
- Auditoria das operações
- Cache distribuído para a cotação utilizando Redis.
- Retry e Circuit Breaker para integração com o Banco Central

---

# Autor

Gilmar Moraes
