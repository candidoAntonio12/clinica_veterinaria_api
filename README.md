# Sistema de Cadastro de Animais - Clínica Veterinária

Documentação inicial do projeto. API REST em **Spring Boot** + frontend em **React**, com persistência em **MySQL/MariaDB**.

## Sumário

- [Visão Geral](#visão-geral)
- [Stack Tecnológica](#stack-tecnológica)
- [Modelo de Dados](#modelo-de-dados)
- [Estrutura de Pastas (Backend)](#estrutura-de-pastas-backend)
- [Configuração do Banco de Dados](#configuração-do-banco-de-dados)
- [Endpoints da API](#endpoints-da-api)
- [Como Rodar o Projeto](#como-rodar-o-projeto)
- [Próximos Passos](#próximos-passos)

---

## Visão Geral

Sistema para uma clínica veterinária cadastrar **tutores (donos)** e os **animais** vinculados a eles. Cada animal pertence a um único tutor; um tutor pode ter vários animais.

## Stack Tecnológica

| Camada       | Tecnologia              |
|--------------|--------------------------|
| Backend      | Spring Boot 3.x (Java)  |
| Frontend     | React                   |
| Banco        | MySQL/MariaDB (via XAMPP) |
| ORM          | Spring Data JPA / Hibernate |
| Build        | Maven                   |
| Admin do BD  | phpMyAdmin              |

## Modelo de Dados

### Entidade: Tutor

| Campo      | Tipo     | Observações                  |
|------------|----------|-------------------------------|
| id         | Long     | PK, auto incremento           |
| nome       | String   | Obrigatório                   |
| telefone   | String   | Obrigatório                   |
| email      | String   | Opcional                      |
| endereco   | String   | Opcional                      |

Relacionamento: **um Tutor tem muitos Animais** (`@OneToMany`).

### Entidade: Animal

| Campo        | Tipo     | Observações                          |
|--------------|----------|----------------------------------------|
| id           | Long     | PK, auto incremento                    |
| nome         | String   | Obrigatório                            |
| especie      | String   | Ex: Cachorro, Gato, Ave                |
| raca         | String   | Opcional                               |
| dataNascimento | Date   | Opcional                               |
| peso         | Double   | Opcional                               |
| tutor_id     | Long     | FK para Tutor (`@ManyToOne`)           |

### Diagrama simplificado

```
Tutor (1) ----------- (N) Animal
  id                      id
  nome                    nome
  telefone                especie
  email                   raca
  endereco                dataNascimento
                           peso
                           tutor_id (FK)
```

## Estrutura de Pastas (Backend)

Sugestão de organização em pacotes (padrão Spring Boot):

```
src/main/java/com/clinica/vet/
├── VetApplication.java
├── model/
│   ├── Tutor.java
│   └── Animal.java
├── repository/
│   ├── TutorRepository.java
│   └── AnimalRepository.java
├── service/
│   ├── TutorService.java
│   └── AnimalService.java
├── controller/
│   ├── TutorController.java
│   └── AnimalController.java
└── dto/
    ├── TutorDTO.java
    └── AnimalDTO.java
```

## Configuração do Banco de Dados

No `application.properties` (ou `.yml`) do Spring Boot:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/clinica_vet?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect
```

> Ajuste `username` e `password` conforme o usuário criado no MySQL/phpMyAdmin.

Criar o banco manualmente (opcional, já que `createDatabaseIfNotExist=true` cria automaticamente):

```sql
CREATE DATABASE clinica_vet;
```

## Endpoints da API

### Tutor

| Método | Rota              | Descrição                |
|--------|-------------------|----------------------------|
| GET    | /api/tutores      | Lista todos os tutores    |
| GET    | /api/tutores/{id} | Busca um tutor por ID     |
| POST   | /api/tutores      | Cadastra um novo tutor    |
| PUT    | /api/tutores/{id} | Atualiza um tutor         |
| DELETE | /api/tutores/{id} | Remove um tutor           |

### Animal

| Método | Rota              | Descrição                       |
|--------|-------------------|-----------------------------------|
| GET    | /api/animais      | Lista todos os animais           |
| GET    | /api/animais/{id} | Busca um animal por ID           |
| POST   | /api/animais      | Cadastra um novo animal          |
| PUT    | /api/animais/{id} | Atualiza um animal               |
| DELETE | /api/animais/{id} | Remove um animal                 |
| GET    | /api/tutores/{id}/animais | Lista animais de um tutor |

### Exemplo de corpo de requisição (POST /api/animais)

```json
{
  "nome": "Rex",
  "especie": "Cachorro",
  "raca": "Labrador",
  "dataNascimento": "2022-03-15",
  "peso": 28.5,
  "tutorId": 1
}
```

## Como Rodar o Projeto

### Backend

```bash
cd backend
./mvnw spring-boot:run
```

API sobe em: `http://localhost:8080`

### Frontend

```bash
cd frontend
npm install
npm start
```

App sobe em: `http://localhost:3000`

### Banco de Dados (XAMPP)

```bash
sudo /opt/lampp/lampp start
```

phpMyAdmin disponível em: `http://localhost/phpmyadmin`

## Próximos Passos

- [ ] Criar entidades `Tutor` e `Animal`
- [ ] Criar repositórios JPA
- [ ] Criar services com regras de negócio
- [ ] Criar controllers REST
- [ ] Testar endpoints (Postman/Insomnia)
- [ ] Criar telas no React (listagem, cadastro, edição)
- [ ] Conectar frontend à API (fetch/axios)
- [ ] Validações de formulário
- [ ] Tratamento de erros (ex: tutor não encontrado)

---

*Documento vivo — atualizar conforme o projeto evolui.*
