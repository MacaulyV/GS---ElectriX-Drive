![Descrição banner](https://github.com/user-attachments/assets/fd23aae5-3758-4cd8-9b38-4abbf9e8b845)

# **ElectriX Drive Platform** 🚗💡

## 👥 Colaboradores do Projeto

- ### **Daniel Bezerra da Silva Melo** - **RM:** [553792](#)
- ### **Gustavo Rocha Caxias** - **RM:** [553310](#)
- ### **Macauly Vivaldo da Silva** - **RM:** [553350](#)

---

## 📄 **Introdução**

A plataforma ElectriX Drive é uma solução desenvolvida para fornecer aos usuários informações claras e detalhadas sobre os benefícios financeiros e ambientais da adoção de carros elétricos e painéis solares 🌞. Utilizando uma combinação de tecnologias modernas e acessíveis 💻, buscamos promover escolhas mais sustentáveis de forma prática e intuitiva. Seja por meio de uma interface web completa 🌐 ou de um aplicativo móvel 📱, a solução foi pensada para tornar a experiência do usuário enriquecedora, educativa e útil para decisões mais conscientes sobre mobilidade e sustentabilidade 🌱.

---

## ✨ **Descrição do Projeto**

A solução é dividida em duas partes principais: a **API** e a **Aplicação Web**.

### API 📊
- Gerencia os dados dos usuários e dos veículos 🚗.
- Realiza comparações entre veículos de combustão ⛽ e elétricos ⚡.

### Aplicação Web 🌐
- Permite que os usuários acessem todos os serviços oferecidos pela API de forma intuitiva e prática 📱.

Assim, a solução completa visa oferecer uma experiência integrada para a gestão de veículos e análise de eficiência, ajudando os usuários a entender a viabilidade da transição para veículos elétricos. 🚗💡

---

## 🎥 Vídeo do Projeto

Para assistir ao vídeo de apresentação do **Projeto**, clique no link abaixo:

[🔗 Assista ao Vídeo do Projeto](URL_DO_VIDEO_AQUI)

---

# Instruções Importantes

1. **Erro ao Adicionar Veículos**: 
   - Ao adicionar os veículos, embora exista um modelo e uma marca no JSON, pode ocorrer um erro indicando que não foi possível identificar alguns modelos ou que são inválidos. Isso acontece por um motivo que ainda não descobrimos. Portanto, caso um veículo não funcione no teste, tente outro modelo e marca até que funcione corretamente.

2. **Endpoint de Edição (PUT)**: 
   - Ao testar o endpoint de edição (PUT) na interface web, você precisará fornecer no último campo o mesmo tipo de combustível que está salvo no JSON. Você pode visualizar esse valor na tabela de combustível já salva do veículo correspondente.

---

 <details>
  <summary align="center"><h3>📋 GestaoVeiculosAPI</h3></summary>
  <p>

---

### 📋 GestaoVeiculosAPI

A API ajuda a gerenciar usuários e seus veículos, permitindo comparar a eficiência entre diferentes tipos de veículos. Isso oferece uma visão clara das vantagens de cada tipo de veículo. A solução foi criada para fornecer insights práticos que facilitam a transição para veículos elétricos e tornam a gestão dos dados mais eficiente.

### 🚀 Funcionamento da API

A API Gestão de Veículos foi construída utilizando a plataforma ASP.NET Core e Entity Framework Core para manipulação de dados. Ela fornece funcionalidades como:

- Relacionar usuários com seus veículos e preferências de comparação. 🚘👥
- Cadastrar e atualizar veículos de combustão e veículos elétricos. 🔄
- Realizar comparações de eficiência entre veículos, retornando insights claros sobre qual deles é mais vantajoso em termos de consumo, ajudando os usuários na tomada de decisão sobre a transição para veículos elétricos. ⚡🔋

---

## 📑 Estrutura Geral das Camadas

A API foi dividida de forma modular, com responsabilidades claramente separadas para garantir escalabilidade e fácil manutenção. Abaixo está uma breve descrição de cada parte do projeto:

### 🗂️ Estrutura das Camadas

#### 📂 Controllers (Camada de Controle):

- 🔑 **AuthController.cs**: Controla a autenticação dos usuários e a geração de tokens JWT para segurança da API.
- 📊 **ComparacaoEficienciaApiController.cs**: Responsável pela comparação entre veículos elétricos e de combustão. Calcula a eficiência e fornece análises detalhadas.
- 👤 **UsuarioApiController.cs**: Gerencia ações relacionadas aos usuários, como cadastro, edição e exclusão.
- ⛽ **VeiculoCombustaoApiController.cs**: Gerencia o CRUD (Create, Read, Update, Delete) dos veículos de combustão.
- ⚡ **VeiculoEletricoApiController.cs**: Gerencia o CRUD dos veículos elétricos.
- 🚙 **VeiculoDisponiveisApiController.cs**: Fornece informações sobre os veículos disponíveis, tanto de combustão quanto elétricos, a partir de dados pré-definidos para comparação.

#### 🗃️ Data (Dados):

- 📄 **ApplicationDbContext.cs**: Define o contexto do banco de dados utilizado pelo Entity Framework Core, incluindo as tabelas e seus relacionamentos.
- 📁 **veiculos_eletricos_validacao.json**, **veiculos_combustao_validacao.json**, etc.: Arquivos JSON usados para validação de veículos disponíveis e suas especificações.

#### 📦 DTOs (Data Transfer Objects):

- 🔄 **ComparacaoEficiencia**: Conjunto de DTOs utilizados para organizar e transferir dados das comparações, como:
  - 📊 **ComparacaoDTO.cs**: Definição dos dados a serem comparados.
  - 📝 **ExplicacaoDTO.cs**: Fornece uma descrição detalhada do resultado da comparação para tornar mais claro ao usuário final.
  - 📈 **ResultadoComparacaoDTO.cs**: Exibe o resultado final da comparação de eficiência.
- ✉️ **Request**: DTOs utilizados para receber os dados enviados à API:
  - 🔐 **LoginRequestDTO.cs**: Dados de login do usuário, como e-mail e senha.
  - 👤 **UsuarioRequestDTO.cs**, ⛽ **VeiculoCombustaoRequestDTO.cs**, ⚡ **VeiculoEletricoRequestDTO.cs**: Dados necessários para cadastrar ou atualizar entidades.
- 📤 **Response**: DTOs utilizados para devolver os dados de resposta ao cliente:
  - 👤 **UsuarioResponseDTO.cs**, ⛽ **VeiculoCombustaoResponseDTO.cs**, ⚡ **VeiculoEletricoResponseDTO.cs**: Estruturas que controlam quais informações serão devolvidas ao cliente em cada resposta.

#### 🛡️ Filters (Filtros):

- 🔑 **ApiKeyAuthAttribute.cs**: Define o filtro de autenticação por chave de API.

#### ⚙️ Middleware:

- 🚨 **ExceptionMiddleware.cs**: Middleware para tratamento de exceções não tratadas, garantindo respostas padronizadas e seguras.

#### 🗄️ Repositories (Repositórios):

Define as operações de acesso ao banco de dados para as entidades 👤 **Usuario**, ⛽ **VeiculoCombustao**, e ⚡ **VeiculoEletrico**, incluindo a interface que define o contrato (Interfaces) e a implementação real.

#### 🛠️ Services:

- ⚡ **VeiculoService.cs**: Implementa lógicas mais complexas envolvendo veículos, como validação de modelo e cálculo de eficiência.

---

## 📈 Estrutura de Dados

A API utiliza o banco de dados Oracle para armazenar informações sobre 👤 **usuários** e 🚗 **veículos**. Abaixo estão descritas as tabelas principais e seus atributos:

### 👤 Usuarios:

- 🆔 **ID_Usuario**: Identificador único (chave primária).
- 📛 **Nome**: Nome completo do usuário.
- 📧 **Email**: Endereço de e-mail do usuário.
- 🔒 **Senha**: Senha para autenticação.

### ⛽ VeiculosCombustao:

- 🆔 **ID_Veiculo_Combustao**: Identificador único.
- 👤 **ID_Usuario**: Referência ao proprietário (usuário).
- 🚘 **Modelo**: Modelo do veículo.
- 🏢 **Marca**: Marca do veículo.
- 📅 **Ano**: Ano de fabricação.
- 🛣️ **Quilometragem_Mensal**: Quilometragem mensal percorrida.
- ⛽ **Consumo_Medio**: Consumo médio em km/l.
- ⛽ **Autonomia_Tanque**: Autonomia do tanque em km.

### ⚡ VeiculosEletricos:

- 🆔 **ID_Veiculo_Eletrico**: Identificador único.
- 👤 **ID_Usuario**: Referência ao proprietário (usuário).
- 🚘 **Modelo**: Modelo do veículo.
- 🏢 **Marca**: Marca do veículo.
- 📅 **Ano**: Ano de fabricação.
- 🔋 **Consumo_Medio**: Consumo médio em kWh/100km.
- 🔋 **Autonomia**: Autonomia em km por carga.

---

## 📏 Conclusão

A API de Gestão de Veículos foi desenvolvida com um design modular, extensível e é totalmente documentada com Swagger. Isso garante fácil manutenção e uma integração intuitiva. A separação clara entre as camadas torna o sistema fácil de entender e evoluir, seja com novas funcionalidades ou integrações.

Além disso, a documentação bem estruturada facilita o uso, a integração e o desenvolvimento de interfaces que utilizam essa API, proporcionando um processo mais simples e eficiente para todos os usuários.

---

  </p>
</details>

 <details>
  <summary align="center"><h3>🌐 ElectriXDriveUI</h3></summary>
  <p>

  ---

# 🚗 Documentação da Camada Web - ElectriXDriveUI

## 📄 Descrição Inicial

A camada web do projeto **ElectriXDriveUI** é responsável pela interface visual do sistema, permitindo que os usuários interajam com as funcionalidades da API de gestão de veículos. Esta camada utiliza o padrão MVC (Model-View-Controller) para organizar o código, facilitando a manutenção e a expansão do sistema. A camada web comunica-se diretamente com a API para realizar operações CRUD (Create, Read, Update, Delete) sobre 🚘 veículos de combustão e ⚡ veículos elétricos, além de realizar comparações de eficiência entre esses tipos de veículos.

## 📂 Estrutura dos Arquivos da Camada Web

Abaixo está a descrição dos principais arquivos e pastas do projeto, organizada para facilitar o entendimento da função de cada um dentro do contexto da camada web.

### 🧭 Controllers

- **🔄 ComparacaoController.cs**: Responsável por lidar com as requisições relacionadas à comparação de eficiência entre veículos de combustão e elétricos. Coordena a chamada à API de comparação e direciona os resultados para a view correta.
- **🏠 HomeController.cs**: Gerencia as requisições relacionadas à página inicial da aplicação, como o acesso à página principal e às rotas de privacidade.
- **🔑 LoginController.cs**: Realiza a autenticação do usuário, permitindo o login e redirecionando o usuário para a tela de introdução após o sucesso na autenticação.
- **🛠️ VeiculoCombustaoController.cs**: Gerencia as operações de CRUD relacionadas aos veículos de combustão. Realiza a integração com a API para adicionar, editar, listar e remover veículos de combustão.
- **⚡ VeiculoEletricoController.cs**: Responsável pelas operações CRUD para os veículos elétricos. Realiza chamadas à API para criar, listar, editar e deletar veículos elétricos.

### 📊 Data

#### 🗂️ Repositories/Implementations

- **👤 UsuarioRepository.cs**: Implementação do repositório para realizar as operações necessárias com os dados do usuário, como acesso e manipulação de informações.
- **🛠️ VeiculoCombustaoRepository.cs**: Gerencia o acesso aos dados dos veículos de combustão, incluindo métodos para obtenção, criação e exclusão.
- **⚡ VeiculoEletricoRepository.cs**: Implementação dos métodos para interação com os dados dos veículos elétricos.

#### 🔌 Repositories/Interfaces

Contém as interfaces de cada repositório (e.g., **IUsuarioRepository**, **IVeiculoCombustaoRepository**, **IVeiculoEletricoRepository**), que definem os contratos para implementação das operações de acesso aos dados.

### 📦 DTOs (Data Transfer Objects)

- **👤 UsuarioResponseDTO.cs**: Contém a definição do DTO usado para representar as respostas dos dados de usuário da API, garantindo a transferência de informações de forma organizada.
- **🛠️ VeiculoCombustaoResponseDTO.cs**: DTO que define a estrutura dos dados retornados pela API sobre veículos de combustão.
- **⚡ VeiculoEletricoResponseDTO.cs**: Define a estrutura dos dados retornados pela API sobre veículos elétricos.

### 📐 Models

- **❗ ErrorViewModel.cs**: Model utilizado para gerenciar os erros que possam ocorrer durante a execução das requisições do sistema, armazenando o identificador do erro e a mensagem correspondente.

### 📊 ViewModels

- **🔑 LoginViewModel.cs**: Contém a estrutura necessária para a autenticação do usuário, como campos de e-mail e senha.
- **🛠️ VeiculoCombustaoViewModel.cs**: Define os dados necessários para realizar operações com veículos de combustão na interface do usuário.
- **⚡ VeiculoEletricoViewModel.cs**: Define os dados utilizados para manipulação de veículos elétricos na camada de interface.

### 👁️ Views

#### 🔄 Comparacao

- **📄 Index.cshtml**: View responsável por exibir o formulário de comparação entre um veículo de combustão e um veículo elétrico. Apresenta os campos necessários para realização da comparação e mostra o resultado.

#### 🏠 Home

- **📄 Index.cshtml**: Exibe a página inicial do sistema, apresentando uma introdução sobre o ElectriX Drive e direcionando o usuário às funcionalidades.
- **🔒 Privacy.cshtml**: Página de política de privacidade.

#### 🔑 Login

- **📄 Index.cshtml**: View de login que coleta informações como e-mail e senha do usuário, permitindo o acesso ao sistema.

#### 🛠️ VeiculoCombustao

- **📝 Create.cshtml**: Formulário para criação de um novo veículo de combustão.
- **✏️ Edit.cshtml**: Formulário para edição de um veículo de combustão existente.
- **📋 Index.cshtml**: Lista todos os veículos de combustão do usuário, exibindo os dados cadastrados e permitindo as ações de edição e exclusão.

#### ⚡ VeiculoEletrico

- **📝 Create.cshtml**: Formulário para criação de um novo veículo elétrico.
- **✏️ Edit.cshtml**: Formulário para edição de um veículo elétrico existente.
- **📋 Index.cshtml**: Exibe a lista de veículos elétricos cadastrados e permite a realização de operações CRUD.

### 🚀 Program.cs

Arquivo principal para configurar o pipeline de execução do **ASP.NET Core**. Ele define os serviços usados pela aplicação, como Swagger para documentação, 📜 CORS para permissões de acesso e injeção de dependências para os repositórios.

## 🏁 Conclusão

A camada web do **ElectriXDriveUI** é responsável pela interface visual do sistema, proporcionando uma experiência de usuário intuitiva e integrando diretamente com a API. Com uma organização clara em **🧭 Controllers**, **👁️ Views**, **🗂️ Repositories**, e **📊 ViewModels**, ela facilita a realização de operações CRUD e permite uma comparação detalhada entre veículos de combustão e elétricos. Esta camada está devidamente estruturada para suportar o desenvolvimento futuro e possibilitar um alto grau de manutenção e expansão.
    </p>
</details>

---
