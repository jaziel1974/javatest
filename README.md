# cepfinder

## Descrição
Teste Java para verificação de habilidades com Java, Spring Boot, Unit Tests.
Trata-se de uma aplicação RestFul com três end-points:
1. /zipcode/query?zipCode={{zipcode}}
   Get request
   Retorna o endereço relacionado a um determinado cep.
   Retorna 204 (no content), caso nenhum endereço seja encontrado
2. /customer/address
   POST request
   Adiciona o endereço à tabela de endereços do cliente e retorna o endereço adicionado.
   Nota: não foi implementada validação de duplicidade
   Exemplo de payload:
   {
    "email": "1@4.com",
    "street": "R Glória",
    "number": "100",
    "neighborhood": "Alto Belo",
    "city": "Xanadu",
    "state": "SP"
   }
3. /customer/address/{{email}}
   GET request
   Retorna a lista de endereços cadastrados para o e-mail informado
   
## Instalação
1. Importar o projeto via Maven para sua IDE favorita
2. Editar as seguintes propriedades do arquivo application.properties conforme a configuração do seu computador:
spring.datasource.url=jdbc:mysql://localhost:3306/cepfinder ==> url de conexão a um banco MySql; o nome do banco de dados pode ser qualquer um
spring.datasource.username=root ==> user para conexão ao banco
spring.datasource.password=root ==> password para conexão ao banco
3. Rodar a aplicação em sua IDE

Ao rodar, a aplicação criará automaticamente a tabela de cadastro de endereços.

## Uso
1. Importar para o postman a coleção CepFinder.postman_collection.json presente no diretório Files, neste projeto;
2. Editar as urls de cada request da coleção: mudar o servidor:porta, se necessário; por padrão está: http://localhost:8080
3. Executar as chamadas das apis
   
## Dependências
Esta aplicação utiliza:
1. Spring Boot
2. MySql
3. JUnit (para testes)
4. WireMock (para testes de acesso a apis externas)

## Relatório do Lint
9 hints encontrados com o lint
- unused imports
- serialversionid unused na classe model
- public methods in test classes doesn't need to be public

## Contato
Jaziel de Oliveira
jaziel1974@gmail.com
