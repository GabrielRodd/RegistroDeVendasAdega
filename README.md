# 🍷 Registro de Vendas - Adega

API REST para análise, controle e gerenciamento de fluxo de vendas de uma adega de bebidas. Este projeto foi desenvolvido utilizando o ecossistema **Spring Boot** para consolidar conceitos fundamentais de arquitetura backend profissional, padrões de projeto (Design Patterns) e persistência de dados.

## 🎯 Objetivo do Projeto

O sistema simula a operação real de gerenciamento de uma adega, permitindo o controle de estoque de produtos (vinhos, destilados, cervejas, etc.), registro detalhado de transações de vendas, cálculo automático de faturamento e relatórios de desempenho comercial através de endpoints organizados.

## 🚀 Principais Funcionalidades

- **Cadastro e Controle de Produtos:** Endpoints para registrar, atualizar e listar itens com informações de preço, categoria e quantidade em estoque.
- **Gerenciamento de Estoque Dinâmico:** Baixa automática e atômica de produtos no estoque assim que uma venda é concretizada através da API.
- **Registro de Vendas:** Histórico detalhado de transações contendo data, itens vendidos, quantidade e valor total da operação.
- **Validações de Regras de Negócio:** Tratamento de exceções customizado para impedir vendas caso o estoque seja insuficiente ou entradas sejam inválidas.

## 🗄️ Persistência e Banco de Dados

O gerenciamento da camada de dados é feito via **Spring Data JPA / Hibernate**:
- **Ambiente Atual:** Utiliza o banco de dados **H2 configurado em modo Embedded (Arquivo Local)**. Os dados são salvos em uma pasta dedicada dentro do próprio diretório do projeto, garantindo a persistência entre reinicializações da API.
- **Estratégia:** Essa abordagem facilita o desenvolvimento ágil e testes rápidos sem a necessidade de configurar um SGBD externo inicialmente.

### 🗺️ Roadmap / Próximos Passos
- [ ] **Migração para MySQL:** O próximo marco planejado é alterar as propriedades do Spring (`application.properties`/`yml`) para migrar a persistência para o **MySQL**, separando o banco da aplicação para um cenário de produção.

## 🛠️ Tecnologias Utilizadas

- **Ambiente Core:** Java & Spring Boot
- **Persistência & ORM:** Spring Data JPA / Hibernate
- **Banco de Dados:** H2 Database (Persistência local em arquivo)
- **Gerenciador de Dependências:** Maven
- **Arquitetura:** Divisão em camadas (Controller, Service, Repository, Entity)

## 🏗️ O que este projeto demonstra sobre minhas habilidades?

Se você está avaliando este repositório para uma vaga de desenvolvimento, este projeto demonstra que sei:
1. **Trabalhar com o Ecossistema Spring:** Utilização prática de injeção de dependências, inversão de controle e gerenciamento de ciclo de vida de Beans.
2. **Desenvolver APIs robustas:** Arquitetura limpa dividida em camadas claras de responsabilidade, facilitando a manutenção e testes.
3. **Mapeamento Objeto-Relacional (ORM):** Domínio de annotations do JPA para modelar entidades e relacionamentos do mundo real no banco de dados.
