# SEA - Sistema de Eventos Acadêmicos
Esse repositório contém os arquivos fontes do Sistema de Eventos Acadêmicos (SEA). 
Este software é desenvolvido pelo Grupo de Pesquisa e Desenvolvimento em sistemas corporativos  (P&amp;DCorp) do IFRN - Campus Avançado Lajes.
O objetivo do sistema proposto é que este dê suporte ao gerenciamento dos eventos acadêmicos que são realizados pelos campi do IFRN.

## Arquitetura do SEA
O SEA é desenvolvido seguindo uma arquitetura REST. 
Nossa API REST é desenvolvida na linguagem Java utilizando o framework [Spring](https://spring.io/). Os arquivos do projeto da API se encontram na seguinte pasta do repositório:
>`/backend/api-sea/`

 O front-end web do SEA é implementado com [Vue.js](https://vuejs.org/) e os arquivos do projeto web podem ser encontrados na seguinte pasta do repositório:
>`/frontend/sea-web/`

## Softwares utilizados no desenvolvimento
Os seguintes softwares são utilizados no desenvolvimento do projeto. Alguns deles possuem utilização opcional e são devidade identificados. Nesta sessão são listados links para download e instalação no Windows e Ubuntu.

### Chocolatey (Opcional)
O [Chocolatey](https://chocolatey.org/) é um software de gerenciamento de pacotes para Windows nos moldes do que é o *apt-get* no Ubuntu. 
Ele facilita a instalação e configuração de programas no Windows e, caso você esteja desenvolvendo nesta plataforma, recomendamos a sua utilização.
Para os demais softwares listados aqui, também apresentaremos formas de instalá-los utilizando o Chocolatey.

Para instalar o Chocolatey basta abrir o powershell como administrador e executar a seguinte linha de comando:
>`Set-ExecutionPolicy Bypass -Scope Process -Force; [System.Net.ServicePointManager]::SecurityProtocol = [System.Net.ServicePointManager]::SecurityProtocol -bor 3072; iex ((New-Object System.Net.WebClient).DownloadString('https://chocolatey.org/install.ps1'))`

Mais informações sobre a sua instalação podem ser encontradas [aqui](https://chocolatey.org/install).

### Cmder (Opcional)
Para quem utiliza Windows e gostaria de uma interface de terminal semelhante as utilizadas nos sistemas Unix, o Cmder é uma exelente opção. Você pode instalar o Cmder baixando a aplicação diretamente do [site deles](https://cmder.net/) (sugerimos a versão completa, que já vem com o git) ou, caso utilize o Chocolatey, com o seguinte comando no terminal (o terminal deve ser aberto como administrador):
>`choco install cmder`

### Java Development Kit (JDK) 11
Utilizamos o JDK 11 para o desenvolvimento do SEA. Você pode utilizar a versão da Oracle ou a versão Open JDK. 

#### No Windows:
Caso decida pela versão da Oracle, você pode baixá-la [aqui](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html), executar o arquivo de instalação e seguir seus passos. 
Atente-se para a [configuração das variáveis de ambiente](https://medium.com/@mauriciogeneroso/configurando-java-4-como-configurar-as-vari%C3%A1veis-java-home-path-e-classpath-no-Windows-46040950638f) necessárias.

Para instalação da Open JDK utilizando o Chocolatey, executar o seguinte comando no terminal (o terminal deve ser executado como administrador):
>`choco install ojdkbuild11`

O Chocolatey já configura as variáveis de ambiente necessárias.

#### No Ubuntu:
>A fazer
<!-- >`sudo apt-get install openjdk` -->

### Eclipse IDE
O [Eclipse](https://www.eclipse.org/) é uma IDE para o desenvolvimento de aplicações Java amplamente utilizada. 
Utilizamos ela no desenvolvimento do nosso projeto, porém também é possível a utilização da IDE [Spring Tool Suite](https://spring.io/tools) (que é baseada no Eclipse). 
Para executar qualquer uma dessas ferramentas, a JDK deve estar previamente instalada.

#### No Windows:
Você pode baixar a versão **Eclipse IDE for Enterprise Java Developers** [aqui](https://www.eclipse.org/downloads/).

Para instalação do Eclipse utilizando o Chocolatey, executar o seguinte comando no terminal (o terminal deve ser executado como administrador):
>`choco install eclipse`

#### No Ubuntu:
>A fazer

### Postman
O [Postman](https://www.postman.com/) é uma aplicação para execução de requisições HTTP. Utilizamos o Postman para executar e testar requisições à nossa API REST.
#### No Windows:
Você pode baixar o instalador do Postman [aqui](https://www.postman.com/downloads/).

Para instalação do Postman utilizando o Chocolatey, executar o seguinte comando no terminal (o terminal deve ser executado como administrador):
>`choco install postman`

#### No Ubuntu:
>A fazer


### Node.js
O [Node.js](https://nodejs.org/) é necessário para o desenvolvimento da nossa aplicação front-end.

#### No Windows:
Você pode baixar o Node.js [aqui](https://nodejs.org/).

Para instalação do Node.js utilizando o Chocolatey, executar o seguinte comando no terminal (o terminal deve ser executado como administrador):
>`choco install nodejs`

#### No Ubuntu:
>A fazer


### Visual Studio Code
O [Visual Studio Code](https://code.visualstudio.com/) é uma IDE bastante utilizada para o desenvolvimento de aplicações web. Nós a utilizamos para o desenvolvimento da nossa aplicação front-end.

#### No Windows:
Você pode baixar o Visual Studio Code [aqui](https://code.visualstudio.com/Download).

Para instalação do Visual Studio Code utilizando o Chocolatey, executar o seguinte comando no terminal (o terminal deve ser executado como administrador):
>`choco install vscode`

#### No Ubuntu:
>A fazer


### Vue CLI
O [Vue CLI](https://cli.vuejs.org/) é uma interface de linha de comando para a criação e configuração de projetos que utilizam o Vue.js. 
Sua instalação é feita através do npm, sistema de gerenciamento de pacotes do Node.js. 
Detalhes de sua instalação são descritos [aqui](https://cli.vuejs.org/guide/installation.html). Você precisa de privilégios de administrador para instalação do Vue CLI.
>`npm install -g @vue/cli`

### Docker
O [Docker](https://www.docker.com/) é uma aplicação de gerenciamento de containers. Com ele conseguimos gerenciar e executar pequenos pedaços da nossa aplicação de maneira independente. Saiba mais [aqui](https://www.redhat.com/pt-br/topics/containers/what-is-docker).

#### No Windows:
Para instalação do Docker no Windows Home, é necessário habilitar o recurso WSL2 além de instalar um pacote de atualização de kernel Linux. Mais detalhes sobre esse procedimento é discutido [aqui](https://docs.docker.com/docker-for-windows/install-windows-home/). No Windows Pro, apesar de possível, não é necessária a habilitação do recurso.

Você pode baixar o Docker Desktop [aqui](https://hub.docker.com/editions/community/docker-ce-desktop-windows/).

Para instalação do Docker utilizando o Chocolatey, executar o seguinte comando no terminal (o terminal deve ser executado como administrador):
>`choco install docker-desktop`

#### No Ubuntu:
>A fazer

## Configurando o ambiente de desenvolvimento
Esta sessão descreve os procedimentos necessários para a configuração do ambiente de desenvolvimento do projeto. 
Para isso você necessita ter instalado todos os softwares obrigatórios descritos na sessão anterior.

### Banco de dados
Neste projeto utilizamos o banco de dados [PostgreSQL](https://www.postgresql.org/) e o executamos através de um container Docker. 
A configuração desse container se encontra no arquivo `/backend/api-sea/docker-compose.yml`.
Para executar o banco de dados entre na pasta `/backend/api-sea/` e execute o seguite comando no terminal:
>`docker-compose up`

Se o Docker tiver sido instalado corretamente, irá baixar as imagens necessárias e executará o container contendo o banco de dados.
Nesta configuração também há um container do **Adminer**, utilizado para acessar o banco de dados através de uma interface web.
Para acessar essa interface web, após a execução do container, acesse o endereço `http://localhost:9000/`.

### API Rest
Abra o Eclipse utilizando a pasta `/backend/` como workspace.
Selecione a opção do menu `File -> Import` e na janela que irá abrir busque a opção `Existing Gradle Project`. 
Selecione a pasta `/backend/api-sea/` e siga as demais instruções do wizard.
Aguarde o Gradle baixar e configurar as bibliotecas necessárias ao projeto antes de começar a explorá-lo.

Para executar o projeto basta executar a classe `ApiSeaApplication.java` no pacote `br.edu.ifrn.laj.pdcorp.apisea`.
A API poderá ser acessada no endereço `http://localhost:8080/`.
A API depende do banco de dados para ser executada.

### SEA Web
Abra o Visual Studio Code e selecione a seguinte opção do menu `File -> Open Folder`.
Selecione a pasta `/frontend/sea-web/` e você terá acesso a todos os arquivos da aplicação web.
Você também pode navegar até a pasta `/frontend/sea-web/` no terminal e digitar o seguinte comando:
>`code .`

Antes de executar o projeto você deve instalar as dependências de biblioteca.
Dentro da pasta `/frontend/sea-web/` execute o seguinte comando no terminal:
>`npm install`

Este comando irá baixar e configurar todas as dependências necessárias para a execução do SEA Web.
Depois de seguir esses passos, na pasta `/frontend/sea-web/` execute o seguinte comando no terminal:
>`npm run serve`

A API Rest deve estar em execução para que a aplicação web consiga acessá-la.

## Equipe
- [Dannylo Johnathan](https://github.com/dannylo) (Professor)
- [Fernando Soares](https://github.com/fernandosoares88) (Professor)
- [Débora Lavínia](https://github.com/dlavinia) (Aluna)
- [Wesley Barbosa](https://github.com/WesleyDev01) (Aluno)