# Sistema de Controle Acadêmico de Ensino Superior

Este projeto foi desenvolvido como parte do trabalho final para a integração das disciplinas de **Projeto Sistema Web MVC e SQL**, **Fundamentos de Banco de Dados**, **Programação Orientada a Objetos**, **JavaScript Básico** e **Algoritmos e Programação**. O objetivo foi criar um **Sistema de Controle Acadêmico** para o ensino superior, utilizando diversas tecnologias e conceitos aprendidos ao longo do curso.

## Participantes

- **Artur Machado Soares**: [GitHub](https://github.com/arturmsoares)
- **Augusto Cesar Rezende**: [GitHub](https://github.com/Augusto-debug)
- **Túlio Malta**: [GitHub](https://github.com/tuliomalta)
- **Victor Hugo Silva**: [GitHub](https://github.com/VictorHugoSDev)

## Tecnologias Utilizadas

- **Spring Boot**: Framework Java para construção de sistemas web.
- **Thymeleaf**: Motor de templates Java para renderização de páginas HTML.
- **SQL**: Banco de dados relacional utilizado para armazenar informações acadêmicas.
- **MVC (Model-View-Controller)**: Padrão de arquitetura utilizado para separar a lógica de negócios, a interface do usuário e o controle de fluxo.
- **Java**: Linguagem de programação orientada a objetos utilizada para construir a lógica do sistema.
- **JavaScript**: Utilizado para validação de formulários e interatividade no front-end.
- **Algoritmos e Programação**: Conceitos de programação lógica, como classes, encapsulamento e sobrecarga de métodos, aplicados na construção do sistema.

## Funcionalidades Implementadas

- **Cadastro de Alunos**: Permite o registro de informações dos alunos, incluindo nome, matrícula, curso e disciplinas matriculadas.
- **Cadastro de Professores**: Facilita o registro de dados dos professores, como nome, matrícula e disciplinas lecionadas.
- **Cadastro de Disciplinas**: Gerencia as disciplinas oferecidas, permitindo a inclusão de nome, código e carga horária.
- **Matrícula de Alunos em Disciplinas**: Habilita os alunos a se matricularem nas disciplinas disponíveis, respeitando pré-requisitos e horários.
- **Visualização de Notas**: Os alunos podem consultar suas notas nas disciplinas em que estão matriculados.
- **Gerenciamento de Cursos**: Administra os cursos oferecidos, incluindo a atribuição de professores às disciplinas correspondentes.

## Estrutura do Sistema

### Back-end

- **Spring Boot**: Responsável pela criação da API do sistema e comunicação com o banco de dados SQL.
- **Modelos de Dados (Classes)**: Utilizando **Programação Orientada a Objetos (POO)**, as entidades do sistema (como Aluno, Professor, Disciplina) foram modeladas como classes com métodos para manipulação de dados, aplicação de regras de negócio, encapsulamento e sobrecarga.

### Front-end

- **Thymeleaf**: Utilizado para renderizar as páginas HTML no lado do servidor, integrando com o Java para exibir dados dinâmicos.
- **JavaScript**: Aplicado para validação de campos nos formulários, evitando a entrada de dados inválidos e proporcionando uma melhor experiência do usuário.

### Banco de Dados

- **SQL**: O banco de dados foi projetado para armazenar todas as informações necessárias para o funcionamento do sistema, incluindo tabelas para alunos, professores, cursos, disciplinas, matrículas e notas.

## Implementação de Algoritmos

Durante o desenvolvimento do projeto, diversos **algoritmos de programação** foram aplicados para resolver problemas do sistema, como:

- **Algoritmos de busca e ordenação** para exibir os dados de forma eficiente.
- **Validações** de formulários utilizando **JavaScript**, garantindo que os dados enviados pelo usuário estejam no formato correto.
- **Encapsulamento e Sobrecarregamento** de métodos na POO, criando funções com múltiplos comportamentos dependendo dos parâmetros.

## Instruções para Execução

1. **Clone o repositório**:
   ```bash
   git clone https://github.com/Augusto-debug/trabalhoIntegradorFinal.git
