![Descrição banner](https://github.com/user-attachments/assets/fd23aae5-3758-4cd8-9b38-4abbf9e8b845)
  
---

# 🚗 **ElectriXDrive Platform**

---

## 🌍 **Visão Geral do Projeto**

**ElectriXDrive Platform** é uma solução completa para ajudar os usuários a comparar o uso de veículos à combustão e elétricos, permitindo que tomem decisões informadas sobre a transição para veículos elétricos e energia sustentável. Esta plataforma visa fornecer informações precisas e fáceis de entender sobre consumo de energia, emissões de CO₂, custos de recarga e muito mais.

## 🚀 Deploy do Projeto

Clique no link para acessar o deploy da ElectriX Drive Platform, feito no Render.

[🌐 Link do Deploy da Interface](https://electrix-drive-platform.onrender.com/login)
[🧪 Testar Endpoints da API no Swagger](https://electrix-drive-platform.onrender.com/swagger-ui/index.html#/)

⚠️ **Atenção**:  O carregamento no render é um pouco demorado clique e aguarde um momento para o carregamento completo do projeto.

---

## 🎥 Vídeo do Projeto junto ao Pitch

Para assistir ao vídeo de apresentação do **ElectriX Drive Platform**, clique no link abaixo:

[🎬 Assista ao Vídeo do Projeto](https://youtu.be/yfK_DmMpmDk)

Este vídeo demonstra as funcionalidades & explicando sua usabilidade.

⚠️ **Atenção**:  O vídeo tem 11 minutos. Utilizamos os 3 minutos disponíveis para o pitch, mais os 10 minutos disponíveis para apresentar o projeto, tudo em um único vídeo de 11 minutos. Recomendamos fortemente assistir o vídeo na velocidade 1.5x para uma melhor experiência.

---

## 🛠️ **Estrutura do Projeto**

O projeto está organizado em diferentes módulos que trabalham juntos para entregar a solução ponta a ponta. Abaixo, detalho cada parte:

### 🖥️ **Backend**

O backend foi desenvolvido usando **Spring Boot** com uma arquitetura bem definida em camadas para facilitar a manutenção e escalabilidade.

- **Camada de Controladores**: ✨ Os controladores são responsáveis por lidar com as requisições dos clientes e redirecioná-las para o serviço apropriado. Exemplos incluem `UsuarioController`, `VeiculoAtualController` e `VeiculoEletricoController`.
- **Serviços**: ⚙️ A lógica de negócio está localizada nos serviços, como `UsuarioService`, `VeiculoAtualService` e `VeiculoEletricoService`, que executam as ações necessárias e interagem com os repositórios.
- **Repositórios**: 🗃️ A camada de persistência de dados foi criada com **JPA (Java Persistence API)**. Exemplos incluem `UsuarioRepository`, `VeiculoAtualRepository` e `VeiculoEletricoRepository`. Eles são responsáveis pela comunicação direta com o banco de dados **Oracle**.
- **Banco de Dados**: 💃 O projeto usa um banco de dados **Oracle**, configurado para armazenar dados de usuários, veículos e comparações. O acesso ao banco é configurado via `application.properties`, e foi utilizado o driver JDBC da Oracle para gerenciar a conexão.

### 💻 **Frontend**

O frontend foi desenvolvido com **Thymeleaf**, que é integrado ao **Spring Boot** para renderizar as páginas HTML dinamicamente.

- **Templates HTML**: 📄 Incluem telas como `login.html`, `escolha.html`, `comparacao.html`, entre outras. Cada página possui um propósito específico, desde permitir o login do usuário até a visualização dos resultados das comparações de veículos.
- **JavaScript e CSS**: 🎭 Foram usados para adicionar interatividade à interface do usuário e estilizar as páginas, tornando a experiência do usuário mais atraente e intuitiva.

### 📜 **Documentação da API**

Para documentar os endpoints da API, foi utilizado o **Swagger** (Springfox para Swagger 2).

- **SwaggerConfig**: 📜 A configuração do Swagger permite que o usuário acesse a documentação da API em `/swagger-ui/`, facilitando o entendimento dos endpoints disponíveis, métodos HTTP e os dados que precisam ser enviados em cada requisição.
- **Redirecionamento Inicial**: 🔄 Foi criado um controlador chamado `SwaggerHomeRedirect` para redirecionar a página inicial para o Swagger, garantindo que o usuário possa acessar a documentação com facilidade.

### 🗂️ **Modelos e DTOs**

- **Modelos de Dados**: 🧩 Representam entidades persistidas no banco de dados, como `Usuario`, `VeiculoAtual` e `VeiculoEletrico`. Eles incluem campos como `email`, `senha`, `marca`, `modelo`, `emissaoCO2`, entre outros, e também relacionamentos como `@ManyToOne` e `@OneToMany`.
- **DTOs (Data Transfer Objects)**: 📦 Foram criados DTOs para facilitar a transferência de dados entre camadas, como `VeiculoAtualInputDTO`, `VeiculoEletricoRequestDTO` e `ComparacaoVeiculosDTO`. Eles garantem que apenas os dados necessários sejam enviados e recebidos.

---
## 🔄 **Comparador de Veículos**

Uma das principais funcionalidades do projeto é o **comparador de veículos**, que permite ao usuário comparar o desempenho de um veículo à combustão com um veículo elétrico.

- **Serviço de Comparação**: 📊 A lógica de comparação está no `ComparacaoVeiculoService`, que faz cálculos para consumo de combustível, custo de recarga, emissões de CO₂, entre outros, baseando-se nos dados fornecidos pelo usuário.
- **Controle de Exceções**: 🚨 O `GlobalExceptionHandler` foi implementado para lidar com exceções comuns, como `ResourceNotFoundException` e `CredenciaisInvalidasException`, garantindo que mensagens de erro sejam amigáveis e informativas.

---
## 👥 **Funcionalidades do Usuário**

### 🔑 **Autenticação e Registro**

- **Registro e Login**: Os usuários podem se registrar ou autenticar por meio de e-mail e senha. A lógica está implementada no `UsuarioService` e `UsuarioController`, que valida as credenciais ou cria um novo usuário caso não exista.

### 🚗 **Cadastro de Veículos**

- **Veículos Atuais**: Os usuários também podem cadastrar seus veículos atuais, fornecendo informações como tipo de combustível, marca, modelo, ano e quilometragem mensal. Esta funcionalidade permite que a plataforma entenda as características do veículo atual do usuário para comparação posterior.

### ⚡ **Escolha e Recomendação de Veículos Elétricos**

- **Escolha de Veículo Elétrico**: Os usuários podem escolher um veículo elétrico da lista fornecida, visualizando informações como consumo médio, autonomia, custo de recarga e emissões de CO₂.
- **Recomendação de Veículos Elétricos**: A plataforma oferece recomendações de veículos elétricos com base na marca do veículo atual do usuário, ajudando na escolha de um modelo adequado.

### 📊 **Comparação de Veículos**

- **Comparação Direta**: Os usuários podem comparar diretamente um veículo à combustão com um veículo elétrico, obtendo uma análise detalhada de consumo, custo, emissões e economia potencial. Esta comparação é exibida de forma clara, permitindo ao usuário visualizar a economia financeira e ambiental.
- **Exploração e Visualização**: A plataforma também permite que os usuários explorem informações sobre veículos disponíveis, visualizando marcas e modelos de veículos à combustão e elétricos.

### 🌍 **Exploração de Veículos Disponíveis**

- **Explorar Modelos**: Os usuários podem explorar diferentes modelos e marcas de veículos disponíveis no sistema. Esta funcionalidade facilita o conhecimento das opções de veículos à combustão e elétricos, permitindo decisões mais bem informadas.

---
## ⚙️ **Configurações do Projeto**

### 📃️ **Configuração do Banco de Dados**

As configurações para conexão com o banco **Oracle** estão no arquivo `application.properties`, incluindo URL, nome de usuário e senha do banco. Isso garante uma conexão segura e estável para a persistência de dados dos usuários e veículos.

### 🌐 **Configuração do Servidor**

- **Porta do Servidor**: A aplicação está configurada para rodar na porta `8080`. Isso pode ser alterado no arquivo de propriedades do projeto, conforme necessário.

---
## 🚀 **Como Rodar o Projeto**

### 📜 **Requisitos**

- **JDK 17** ou superior
- **Maven** para gerenciamento de dependências
- **Oracle Database** para persistência dos dados

### 🛠️ **Passos**

```sh
1. **Clone o repositório**: `git clone -b https://github.com/MacaulyV/GS-ElectriX-Drive.git`
2. **Navegue até o diretório do projeto**: `cd ElectriXDrivePlatform`
3. **Compile o projeto com Maven**: `mvn clean install`
4. **Execute o projeto**: `mvn spring-boot:run`
5. **Acesse a aplicação no navegador**: `http://localhost:8080/login`
```

Para visualizar a documentação da API, visite: `http://localhost:8080/swagger-ui/index.html#/`

---

## 📋 **Exemplos de Requisições POST para Testes**

### **Autenticação de Usuário**
```json
{
  "email": "vivi@gmail.com",
  "senha": "5454123"
}
```

### **Cadastro de Veículo Atual**
```json
{
  "marca": "Toyota",
  "modelo": "Corolla",
  "ano": 2020,
  "tipoCombustivel": "Gasolina",
  "quilometragemMensal": 1500
}
```

### **Cadastro de Veículo Elétrico**
```json
{
  "marca": "Tesla",
  "modelo": "Model 3",
  "ano": 2023,
  "interesseSolar": "Sim"
}
```
---

## 📈 **Considerações Finais**

O projeto **ElectriXDrive Platform** foi desenvolvido para fornecer aos usuários uma visão clara e objetiva dos benefícios da transição para veículos elétricos, tanto do ponto de vista ambiental quanto econômico. Utilizando **Spring Boot** e **Oracle**, o sistema garante um backend robusto enquanto oferece uma interface de usuário amigável com **Thymeleaf**.

---
