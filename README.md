# Portal de Vagas em GraphQL e REST
> Trabalho de Conclusão de Curso II onde o objetivo é comparar ambas arquiteturas utilizando o mesmo portal de vagas.
> Possui uma camada Controller em REST e uma camada Controller em GraphQL.
> Ambas Controllers utilizam o mesmo caminho de execução dos endpoints (Service, Repository, Banco de Dados).

## Tecnologias

- Java 17
- Spring Boot
- GrpahQL
- MongoDB com Docker
- JMeter para testes de carga

## MongoDB com Docker:

> Para criar o docker:
>```
>docker run -d \
>--name mongo-graphql \
>-p 27018:27017 \
>-v mongo_data_graphql:/data/db \
>mongo:6.0
>```
>Explicação:
>
>```-d``` → roda em segundo plano;
> 
>```--name``` mongo-graphql → nome do container (pode mudar depois se quiser);
>
>```-p 27018:27017``` → mapeia a porta local 27018 para a interna 27017;
>
>```-v mongo_data_graphql:/data/db``` → cria um volume persistente para manter seus dados;
>
>```mongo:6.0``` → imagem oficial do MongoDB na versão 6.0.

> ```docker ps```para saber se o docker esta rodando

>```docker start mongo-graphql``` para iniciar o docker

>```docker stop mongo-graphql``` para parar o docker

> No ```application.properties``` colocar a linha:
```spring.data.mongodb.uri=mongodb://localhost:27018/portalvagas```

### application.properties

```
spring.application.name=portal-vagas-graphql
spring.graphql.graphiql.enabled=true
spring.graphql.graphiql.path=/graphiql
spring.data.mongodb.uri=mongodb://localhost:27018/portalvagas
```

## Testes com Apache JMeter

O Apache JMeter é uma ferramenta open-source utilizada para testes de carga, desempenho e stress em aplicações web.
Ele permite simular múltiplos usuários acessando endpoints simultaneamente e coletar métricas como tempo de resposta,
throughput e uso de recursos. Neste projeto, o JMeter foi utilizado para executar cenários de teste sobre as APIs REST e GraphQL.

### Instalação do Apache JMeter

1. Acesse o site oficial: https://jmeter.apache.org/download_jmeter.cgi
2. Baixe a versão mais recente (arquivo .zip ou .tgz)
3. Extraia o conteúdo em uma pasta de sua escolha
4. Para rodar a interface gráfica (GUI), basta executar:
   * Windows: bin/jmeter.bat
   * Linux/Mac: `./jmeter` na pasta /bin

### Abrir o plano de testes (.jmx)

Este repositório contém o arquivo: `jmeter/Teste GraphQL.jmx`
Para abrir:
1. Abra o JMeter
2. Clique em File → Open
3. Selecione o arquivo .jmx do repositório

O plano já está configurado com:
* Thread Groups
* CSV Data Set Config
* HTTP Request samplers

### Executar pela Interface Gráfica

Recomenda-se executar somente os scripts de população do banco de dados pela GUI.
Para isso, deixe os demais testes com a opção disable que aparece ao clicar com o botão direito em cada Thread Group.

Depois de executar os scripts, verifique se o banco de dados está com todas as informações.
Ainda, é preciso criar um registro de professor para garantir que os testes rodem quando precisa do ID do professor.
É possível testar a aplicação utilizando o Swagger, pois todos os endpoints funcionam perfeitamente.
`localhost:8080/swagger-ui/index.html#/`

### Executar pela Linha de Comando

Para os testes de carga você pode rodar através do botão Play (verde) na GUI, mas pode haver inconsistência nos dados obtidos.
Dessa forma, recomenda-se executar pelo terminal pois é a forma mais fiel de reproduzir os testes apresentados no TCC.

Para isso, você deve salvar o plano de teste depois de fazer as alterações que deseja e fechar o programa.

No diretório raiz do JMeter, utilize o comando abaixo, atentando-se para as pastas correspondentes:

```
./jmeter.sh -n -t </home/caminho do teste .jmx> -l </home/caminho para criar o .csv de saída> -e -o </home/caminho da pasta criada para exportar os resultados>
```

`-n`: Indica que o JMeter deve ser executado no modo non-GUI (linha de comando).

`-t`: <caminho_para_seu_script.jmx>: Especifica o caminho para o arquivo do plano de teste (.jmx) que você criou na interface gráfica do JMeter.

`-l`: <caminho_para_arquivo_resultados.csv>: Define o local e o nome do arquivo de resultados (.csv) onde os dados brutos do teste serão salvos. Este arquivo é necessário para gerar o relatório HTML.

`-e`: Garante que o relatório HTML (Dashboard) seja gerado após a conclusão do teste de carga.

`-o`: <caminho_para_pasta_de_saida_html>: Define o diretório onde o relatório HTML será salvo. Importante: A pasta especificada deve estar vazia ou não existir antes de executar o comando.

## GraphQL comandos:

Comandos para rodar no localhost do GraphQL: `http://localhost:8080/graphiql?path=/graphql`

### get all:
```graphql
query {
    findAllProfessores {
        id
        nome
        email
        departamento
        coordenador
    }
}
```

### get by id:

```graphql
query {
    buscarProfessorPorId(id: "6747c4a91939a52bdc50757b") {
        id
        nome
        email
        departamento
        coordenador
    }
}
```

```graphql
query {
    buscarEstudantePorId(id: "671c0f53d1b6d6122e52b03e") {
        id
        nome
        email
        candidaturas {
            dataCandidatura
            vaga {
                id
                titulo
                descricao
                empresa
                localizacao
                dataCriacao
            }
        }
    }
}
```
ou
```graphql
query {
  buscarEstudantePorId(id: "671c0f53d1b6d6122e52b03e") {
    nome
    candidaturas {
      vaga {
        titulo
      }
    }
  }
}
```

```graphql
query {
    buscarVagaPorId(id: "68e6915e9d976f25bc2b9581") {
        id
        titulo
        empresa
        idProfessor
    }
}
```

### get by filter:

```graphql
query {
    listarProfessoresPorFiltro(filter: {
        departamento: "Escola Politécnica"
        areaAtuacao: "Banco de Dados"
    }) {
        id
        nome
        email
        departamento
        areaAtuacao
    }
}
```

```graphql
query {
  listarEstudantesPorFiltro(
    filter: { curso: "Ciência da Computação", nome: "Maria" }
  ) {
    id
    nome
    email
    curso
  }
}
```
```graphql
query {
    listarVagasPorFiltro(filter: {
        empresa: "UniXS"
    }) {
        id
        titulo
        empresa
        localizacao
        dataCriacao
    }
}
```
### create:

```graphql
mutation {
    criarProfessor(
        request: {
            nome: "João Carvalho"
            email: "joaoc@unisinos.br"
            formacao: "Ciência da Computação - Doutorado"
            areaAtuacao: "Banco de Dados"
            departamento: "Escola Politécnica"
            coordenador: false
            senha: "123456"
        }
    ) {
        id
        nome
        email
        formacao
        areaAtuacao
        departamento
        coordenador
    }
}
```

```graphql
mutation {
  criarEstudante(
    estudanteRequest: {
      nome: "Maria Flor"
      registroAcademico: "12346532"
      curso: "Ciência da Computação"
      email: "mariaflor@edu.unisinos.br"
      habilidades: ["proatividade", "vontade de aprender"]
      tecnologias: ["Java", "SpringBoot", "MongoDB"]
      textoApresentacao: "Em busca de estágio focado em backend"
      site: "https://linkedin.com/in/mariaflor/"
      senha: "123456"
    }
  ) {
    id
    nome
    email
    curso
    registroAcademico
    habilidades
    tecnologias
  }
}
```

```graphql
mutation {
  criarCandidatura(idEstudante: "68e557f4ff1193078f1a05f1", idVaga: "671c0f78d1b6d6122e52b040") {
    dataCandidatura
    vaga {
      id
      titulo
      descricao
      empresa
      localizacao
      dataCriacao
      idProfessor
    }
  }
}
```

```graphql
mutation {
  criarVaga(
    idProfessor: "671c0f53d1b6d6122e52b03e",
    vaga: {
      titulo: "Desenvolvedor Junior - Java",
      descricao: "Vaga de Desenvolvedor com experiência em Java",
      empresa: "UniXS",
      localizacao: "Porto Alegre"
    }
  ) {
    id
    titulo
    descricao
    empresa
    localizacao
    dataCriacao
    idProfessor
  }
}
```

### update:

```graphql
mutation {
    atualizarProfessor(
        id: "6747c4a91939a52bdc50757b",
        request: {
            nome: "João Carvalho",
            email: "joao.carvalho@unisinos.br",
            formacao: "engenharia",
            areaAtuacao: "pesquisa"
            departamento: "Escola Politécnica",
            coordenador: true,
            senha: "joao"
        }
    ) {
        id
        nome
        email
        departamento
        coordenador
    }
}
```

```graphql
mutation {
  atualizarEstudante(
    id: "6747c4a91939a52bdc50757b"
    request: {
      nome: "Maria Flor Atualizada"
      registroAcademico: "12346532"
      curso: "Engenharia de Software"
      email: "mariaflor@edu.unisinos.br"
      habilidades: ["liderança", "trabalho em equipe"]
      tecnologias: ["Java", "SpringBoot", "MongoDB", "React"]
      textoApresentacao: "Em busca de estágio full stack"
      site: "https://linkedin.com/in/mariaflor/"
      senha: "123456"
    }
  ) {
    id
    nome
    curso
    tecnologias
  }
}
```

```graphql
mutation {
  atualizarVaga(
    id: "68e6915e9d976f25bc2b9581",
    idProfessor: "6747c4a91939a52bdc50757b",
    request: {
      titulo: "Desenvolvedor Backend Java"
      descricao: "Atuação em projetos com Spring Boot e MongoDB"
      empresa: "UniXS Tecnologia"
      localizacao: "São Leopoldo"
    }
  ) {
    id
    titulo
    descricao
    empresa
    localizacao
    dataCriacao
  }
}
```

### delete:

```graphql
mutation {
    deletarProfessor(id: "68e48df854017463a8a050e5")
}
```

```graphql
mutation {
  deletarEstudante(id: "6747c4a91939a52bdc50757b")
}
```

```graphql
mutation {
  deletarCandidatura(id: "6747c4a91939a52bdc50757b", idVaga: "68e6915e9d976f25bc2b9581")
}
```

```graphql
mutation {
  deletarVaga(id: "68e6915e9d976f25bc2b9581", idProfessor: "6747c4a91939a52bdc50757b")
}
```

