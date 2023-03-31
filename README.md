# Trabalho de PPOO - Sistema de recomendação e avaliação de filmes e séries


## Versão não funcional, foram omitidos as credenciais da API utilizada e do banco de dados.
-- -
## Definição do Grupo

**Nome do grupo**: critiquei

**Tema escolhido**: Obras audiovisuais

**Integrantes** (preencha abaixo):

- Bárbara Ferreira Rodrigues
- Lucas Gabriel Pereira Moreira
- Michel Alexandrino de Souza
- Yasmim Danzieri Abbondanza Laurentino


## Checklist para a Primeira Entrega

### No banco já estão adicionados usuarios com privilegios de administrador e moderador, para facilitar o teste do sistema.
**Email:** admin /
**Senha:** admin

**Email:** mod /
**Senha:** mod

**Preencha a coluna _Respostas_** na tabela abaixo **antes de fazer a primeira entrega**.

- Obs.: a coluna _Id_ indica a seção das Instruções do trabalho.

| Id    | Descrição                                                          | Respostas                                                             | 
|-------|--------------------------------------------------------------------|-----------------------------------------------------------------------|
| 2.1.1 | O sistema permite cadastrar itens?                                 | (sim)                                                                 |
| 2.1.1 | O sistema usa o código fornecido para obter a descrição dos itens? | (não)                                                                 |
| 2.1.1 | Se sim na resposta anterior, de qual Wiki?                         | (TMDB api)                                                            |
| 2.1.2 | O sistema permite visualizar os detalhes dos itens?                | (sim)                                                                 |
| 2.1.3 | O sistema permite excluir itens cadastrados?                       | (sim)                                                                 |
| 2.1.4 | O sistema permite cadastrar usuários?                              | (sim)                                                                 |
| 2.1.4 | Há tratamento para os diferentes tipos de usuários?                | (sim)                                                                 |
| 3.1.3 | O código implementado tem bom design de classes?                   | (sim)                                                                 |
| 3.1.4 | O código implementado tem divisão de camadas?                      | (sim)                                                                 |
| 3.1.5 | O código faz uso de herança? Tem superclasse e subclasses?         | (sim)                                                                 |
| 3.1.6 | O código inicial fornecido pelo professor foi alterado?            | (sim)                                                                 |
| 3.1.7 | Como os itens são salvos?                                          | (BD)                                                                  |
| 3.1.8 | Diagrama simplificado em PNG se encontra na pasta `doc`?           | (sim)                                                                 |
| 3.1.9 | Código enviado compila sem erros na versão 17 do Java?             | (sim)                                                                 |
| 3.1.9 | Implementação usa pacote `br.ufla.gac106.s2022_2.nomeDoGrupo`?     | (sim)                                                                 |
| 3.1.9 | Foi adicionada alguma biblioteca (arquivo `.jar`)?                 | (sim, jbcrypt, para criptografia; e duas do flatlaf para estilização) |
| 3.1.9 | Código está legível, organizado e bem comentado?                   | (sim)                                                                 |
|       | Todos os integrantes contribuíram, inclusive na implementação?     | (sim)                                                                 |

## Checklist para a Entrega Final

**Preencha a coluna _Respostas_** na tabela abaixo **antes da entrega final**.

- Obs.: a coluna _Id_ indica a seção das Instruções do trabalho.

|  Id   |  Descrição                                                                  | Respostas | 
|-------|-----------------------------------------------------------------------------|-----------|
| 2.1   | Sistema trata todos os itens do módulo de Administração (primeira entrega)? | (sim) |
| 2.2.1 | É possível consultar a lista de itens cadastrados no módulo de Avaliação?   | (não) |
| 2.2.1 | A lista pode ser ordenada por nome e por classificação média?               | (sim) |
| 2.2.2 | É possível filtrar os dados? Quais são os filtros?                          |Por nome   |
| 2.2.3 | O usuário consegue comentar os itens? Mais de uma vez?                      | (sim, sim)|
| 2.2.3 | Os comentários são salvos (persistidos)?                                    | (sim)     |
| 2.2.4 | O usuário consegue classificar os itens? Como?                              | (sim) (Nota 1 a 5) |
| 2.2.4 | As classificações são salvas (persistidas)?                                 | (não) |
| 2.2.5 | No módulo de Avaliação, o usuário consegue ver os detalhes dos itens?       | (sim) |
| 2.2.5 | Os detalhes incluem classificação média e comentários?                      | (sim) |
| 2.2.6 | Foi implementado o módulo de relatórios?                                    | (não) |
| 2.2.6 | Relatório permite consulta da quantidade de itens classificados ou não?     | (não) |
| 2.2.6 | Relatório permite consulta dos 5 itens melhor classificados, de cada tipo?  | (não) |
| 2.2.6 | Relatório permite consulta dos 3 usuários que mais classificaram itens?     | (não) |
| 2.2.6 | Relatório permite consulta dos 3 usuários que mais comentaram itens?        | (não) |
| 2.2.6 | Relatório permite exibição do gráfico usando código fonercido?              | (não) |
| 3.1.3 | O código implementado tem bom design de classes?                   | (sim) |
| 3.1.4 | O código implementado tem divisão de camadas?                      | (sim) |
| 3.1.5 | O código faz uso de herança? Tem superclasse e subclasses?         | (sim) |
| 3.1.6 | O código inicial fornecido pelo professor foi alterado?            | (não) |
| 3.1.7 | Como os itens são salvos?                                          | (BD) |
| 3.1.8 | Diagrama simplificado em PNG se encontra na pasta `doc`?           | (sim) |
| 3.1.9 | Código enviado compila sem erros na versão 17 do Java?             | (sim) |
| 3.1.9 | Implementação usa pacote `br.ufla.gac106.s2022_2.nomeDoGrupo`?     | (sim) |
| 3.1.9 | Foi adicionada alguma biblioteca (arquivo `.jar`)?                 | (sim (qual? jbcrypt, flatlaf)) |
| 3.1.9 | Código está legível, organizado e bem comentado?                   | (sim) |
| 3.1.10| Código faz uso de variável polimórfica?                            | (sim) |
| 3.1.10| Código faz uso de polimorfismo de método?                          | (sim) |
| 3.1.11| Há tratamento de exceção para entradas inválidas do usuário?       | (sim) |
| 3.1.11| Há tratamento de exceção para programa não fechar por erro de execução? | (não) |
| 3.1.11| Há tratamento de exceção para exceções lançadas pela classe Wiki?  | (não) |
| 3.1.12| Foi implementado algum Padrão de Projeto? Qual?                    | (sim (qual?Simple Factory)) |
| 3.1.13| A interface de usuário é de fácil utilização?                      | (sim) |
| 3.1.13| Foi implementada interface gráfica de usuário?                     | (sim) |
|       | Todos os integrantes contribuíram, inclusive na implementação?     | (sim) |
