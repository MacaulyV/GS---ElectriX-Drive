![Descrição banner](https://github.com/user-attachments/assets/fd23aae5-3758-4cd8-9b38-4abbf9e8b845)

# **ElectriX Drive Plataforma de Comparação <br> de Eficiência com Veículos Elétricos** 🚗💡

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
  <summary align="center"><h3>🌐 Aplicação Web</h3></summary>
  <p>

  ---

Em Andamento
    </p>
</details>

---
