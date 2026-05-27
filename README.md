# 🍷 Registro de Vendas - Adega

Análise, controle e gerenciamento de fluxo de vendas para uma adega de bebidas. Este projeto foi desenvolvido com o objetivo de consolidar conceitos fundamentais de arquitetura backend, lógica de programação orientada a objetos e persistência de dados em ambiente de desenvolvimento.

## 🎯 Objetivo do Projeto

O sistema simula a operação real de gerenciamento de uma adega, permitindo o controle de estoque de produtos (vinhos, destilados, cervejas, etc.), registro detalhado de transações de vendas, cálculo automático de faturamento e relatórios básicos de desempenho comercial.

## 🚀 Principais Funcionalidades

- **Cadastro e Controle de Produtos:** Registro de itens com informações de preço, categoria e quantidade em estoque.
- **Gerenciamento de Estoque Dinâmico:** Baixa automática de produtos no estoque assim que uma venda é concretizada.
- **Registro de Vendas:** Histórico detalhado de transações contendo data, itens vendidos, quantidade e valor total.
- **Validações de Regras de Negócio:** Impedimento de vendas caso o estoque seja insuficiente, validação de valores e entradas de dados.

## 🗄️ Persistência e Banco de Dados

Atualmente, o projeto utiliza o banco de dados **H2** configurado em modo **Embedded (Arquivo Local)**.
- Os dados são salvos localmente em uma pasta dedicada dentro do próprio diretório do projeto, garantindo que as informações não sejam perdidas ao reiniciar a aplicação.
- Essa abordagem foi escolhida para facilitar o desenvolvimento inicial, testes rápidos e portabilidade do projeto sem a necessidade de instalar um SGBD pesado logo de início.

### 🗺️ Roadmap / Próximos Passos
- [ ] **Migração para MySQL:** O próximo marco do projeto é migrar a camada de persistência para o **MySQL**, separando o banco de dados da aplicação e preparando o sistema para um cenário de produção real.

## 🛠️ Tecnologias Utilizadas

- **Linguagem Principal:** Java
- **Banco de Dados:** H2 Database (Persistência em arquivo local)
- **Gerenciador de Dependências:** Maven
- **Práticas de Desenvolvimento:** Orientação a Objetos (POO), encapsulamento, tratamento de exceções e manipulação de coleções/estruturas de dados.

## 🏗️ O que este projeto demonstra sobre minhas habilidades?

Se você está avaliando este repositório para uma vaga de desenvolvimento, este projeto demonstra que sei:
1. **Modelar Domínios de Negócio:** Transformar regras do mundo real (vendas, estoque, produtos) em código limpo e estruturado.
2. **Trabalhar com Persistência de Dados:** Configurar e manipular conexões com bancos de dados relacionais e entender conceitos de ciclo de vida dos dados.
3. **Visão de Arquitetura:** Planejar a evolução do software, evidenciada pela escolha estratégica do H2 para desenvolvimento e o plano mapeado para migração para MySQL.

