# Remittance Service

Sistema de remessas internacionais desenvolvido como desafio técnico.

A aplicação permite o cadastro de usuários Pessoa Física (PF) e Pessoa Jurídica (PJ), carteiras em Real (BRL) e Dólar (USD), depósitos em carteira e realização de remessas internacionais utilizando a cotação oficial do Banco Central do Brasil.

---

# Arquitetura

O projeto foi desenvolvido utilizando **Arquitetura Hexagonal (Ports & Adapters)**.

Essa arquitetura foi escolhida por separar claramente as regras de negócio das tecnologias utilizadas, facilitando manutenção, testes e futuras evoluções da aplicação.

As regras de negócio permanecem isoladas da infraestrutura, permitindo substituir banco de dados, APIs externas ou frameworks sem impacto no domínio da aplicação.

---

# Tecnologias utilizadas

| Tecnologia | Motivo da escolha |
|------------|-------------------|
| Java 21 | Versão LTS da linguagem. |
| Spring Boot 4 | Desenvolvimento rápido de APIs REST. |
| Spring Web | Exposição dos endpoints REST. |
| Spring Data JPA | Persistência desacoplada através de repositórios. |
| H2 Database | Banco em memória para facilitar execução do desafio. |
| Flyway | Versionamento do banco de dados. |
| Spring Validation | Validação declarativa das requisições. |
| WebClient | Integração com a API do Banco Central. |
| Spring Cache | Cache da cotação do dólar. |
| Spring Boot Actuator | Monitoramento da aplicação. |
| Springdoc OpenAPI (Swagger) | Documentação automática da API. |
| Docker | Execução simplificada da aplicação. |
| JUnit 5 | Testes unitários. |
| Mockito | Mock das dependências. |
| Maven | Build e gerenciamento de dependências. |

---

# Decisões Técnicas

## Arquitetura Hexagonal

A Arquitetura Hexagonal foi escolhida por manter o domínio totalmente desacoplado da infraestrutura.

Toda regra de negócio permanece concentrada nas camadas **Domain** e **Application**, enquanto banco de dados, API do Banco Central e controllers são apenas adaptadores.

Essa abordagem facilita testes, manutenção e futuras substituições de tecnologias.

---

## Java 21

Foi utilizada a versão LTS mais recente da linguagem, oferecendo melhorias de desempenho, legibilidade e suporte de longo prazo.

---

## WebClient

Mesmo sendo uma aplicação síncrona, foi escolhido o WebClient por ser a tecnologia recomendada pelo ecossistema Spring para integrações HTTP modernas e por permitir futura evolução para programação reativa.

---

## Banco H2

O banco H2 foi utilizado apenas para simplificar a execução do desafio.

Como a aplicação utiliza Arquitetura Hexagonal e Spring Data JPA, a troca para PostgreSQL, MySQL ou Oracle pode ser realizada com poucas alterações.

---

## Flyway

Responsável pelo versionamento do banco de dados.

Todas as tabelas são criadas automaticamente através de migrations, garantindo consistência entre ambientes.

---

## Cache

A cotação do dólar é armazenada em cache para reduzir chamadas consecutivas à API do Banco Central.

---

## Swagger

Toda a API está documentada através do Springdoc OpenAPI.

---

# Funcionalidades Implementadas

- Cadastro de usuários PF
- Cadastro de usuários PJ
- Carteira em Real
- Carteira em Dólar
- Depósito em carteira
- Remessas internacionais
- Conversão automática BRL → USD
- Consulta da cotação do Banco Central
- Cache da cotação
- Limite diário por tipo de usuário
- Validação de CPF/CNPJ
- Validação de saldo
- Tratamento global de exceções
- Flyway
- Swagger/OpenAPI
- Docker
- Arquitetura Hexagonal

---

# Como executar

## Opção 1 - Maven

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
mvn clean install
```

Depois

```bash
mvn spring-boot:run
```

A aplicação ficará disponível em

```
http://localhost:8080
```

---

## Opção 2 - Docker

Gerar a imagem

```bash
docker build -t remittance-service .
```

Executar o container

```bash
docker run -p 8080:8080 remittance-service
```

Ou utilizando Docker Compose

```bash
docker-compose up --build
```

---

# Banco de Dados

As migrations do banco são executadas automaticamente pelo Flyway durante a inicialização da aplicação.

Não é necessário criar tabelas manualmente.

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

Endpoints disponíveis

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

# API

## Criar Usuário

### POST

```
/usuarios
```

### Requisição

```json
{
  "nomeCompleto": "Gilmar Moraes",
  "email": "gilmar@email.com",
  "senha": "123456",
  "tipoUsuario": "PF",
  "cpf": "12345678900"
}
```

### Resposta

```json
{
  "id": 1,
  "nomeCompleto": "Gilmar Moraes",
  "email": "gilmar@email.com"
}
```

---

## Depositar Saldo

### POST

```
/usuarios/{id}/depositos
```

### Requisição

```json
{
  "moeda": "BRL",
  "valor": 1000.00
}
```

### Resposta

```json
{
  "mensagem": "Depósito realizado com sucesso."
}
```

---

## Realizar Remessa

### POST

```
/remessas
```

### Requisição

```json
{
  "remetenteId": 1,
  "destinatarioId": 2,
  "valorReal": 100.00
}
```

### Resposta

```json
{
  "id": 1,
  "valorReal": 100.00,
  "valorDolar": 19.54,
  "cotacaoCompra": 5.117,
  "dataCotacao": "2026-07-19",
  "dataHora": "2026-07-19T15:10:24"
}
```

---

# Tratamento de Erros

| Cenário | Status |
|----------|--------|
| Usuário não encontrado | 404 |
| Saldo insuficiente | 400 |
| Limite diário excedido | 400 |
| CPF/CNPJ inválido | 400 |
| Dados inválidos | 400 |

Exemplo

```json
{
  "timestamp": "2026-07-19T15:00:00",
  "status": 400,
  "erro": "Saldo insuficiente."
}
```

---

# Estrutura do Projeto

```
application
│
├── dto
├── service
├── validator
├── port
│   ├── in
│   └── out
│
domain
│
├── model
├── enums
└── exception
│
adapter
│
├── in
│   └── web
│
└── out
    ├── persistence
    └── bcb
```

---

# Melhorias Futuras

- Persistência do histórico de movimentações da carteira (extrato).
- Transferências entre carteiras na mesma moeda.
- Cache distribuído utilizando Redis.
- Retry e Circuit Breaker para integração com o Banco Central.
- Autenticação e autorização utilizando Spring Security + JWT.
- Criptografia de senhas com BCrypt.
- Ampliação da cobertura de testes unitários e testes de integração.
- Observabilidade com logs estruturados e métricas customizadas.
- Containerização com Kubernetes para ambientes de produção.

---

# Autor

**Gilmar Moraes**
