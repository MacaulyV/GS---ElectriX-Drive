![Capa](https://github.com/user-attachments/assets/666502bb-975d-4879-bfb0-99f37880b207)

# EcoDrive Insight AI

## 👥 Colaboradores do Projeto

- ### **Daniel Bezerra da Silva Melo** - **RM:** [553792](#)
- ### **Gustavo Rocha Caxias** - **RM:** [553310](#)
- ### **Macauly Vivaldo da Silva** - **RM:** [553350](#)

---

## 🚀 Deploy do Projeto

Para acessar o deploy da **EcoDrive Insight AI**, clique no link abaixo:

[🔗 Link do Deploy](URL_DO_DEPLOY_AQUI)

Este deploy permite que você explore todas as funcionalidades do projeto em um ambiente operacional.

---

## 🎥 Vídeo do Projeto

Para assistir ao vídeo de apresentação do **ElectriX Drive Platform**, clique no link abaixo:

[🔗 Assista ao Vídeo do Projeto](https://youtu.be/cawgKS99ugc)

Este vídeo demonstra as funcionalidades & explicando sua usabilidade.

---

---

##  🌟 Link do Deploy do Projeto Real Completo da solução (Opcional)

Para acessar o deploy da **ElectriX Drive Platform**, clique no link abaixo:

[🔗 Assista ao Vídeo do Projeto](https://youtu.be/cawgKS99ugc)

Este vídeo demonstra as funcionalidades & explicando sua usabilidade.

---

## 🌱 Visão Geral do Projeto

O **EcoDrive Insight AI** é uma plataforma baseada em Inteligência Artificial desenvolvida com o objetivo de auxiliar os usuários na transição de um veículo a combustão para um veículo elétrico, promovendo a energia sustentável e a eficiência energética. A plataforma utiliza Machine Learning para fornecer uma comparação detalhada entre um carro a combustão convencional e um veículo elétrico selecionado pelo usuário, oferecendo análises de custo operacional, emissões de CO₂ e economias projetadas ao longo do tempo. 🚗🏠

O projeto combina a simplicidade de uso da interface desenvolvida em Streamlit, recursos poderosos de Machine Learning e a integração de dados de veículos elétricos para tornar as decisões de transição mais claras e informadas. A aplicação objetiva não apenas fornecer informações sobre os benefícios ambientais e financeiros dos veículos elétricos, mas também simplificar a comparação, tornando-a visualmente clara e intuitiva.

---

## 🛠️ Problema e Solução Proposta

**🔍 Problema:** Muitos motoristas possuem dúvidas sobre o real custo-benefício de trocar um veículo a combustão por um veículo elétrico. A falta de informações claras sobre custos operacionais, emissões de carbono e benefícios ao longo do tempo dificulta a decisão de migração para uma solução sustentável.

**💡 Solução:** O EcoDrive Insight AI oferece uma ferramenta educativa e intuitiva que fornece comparações detalhadas entre veículos a combustão e elétricos. Utilizando Machine Learning, a aplicação calcula o custo operacional e as emissões mensais de CO₂, e projeta os benefícios da transição ao longo de 1, 3, 5, 7 e até 10 anos. Isso ajuda os motoristas a tomar uma decisão informada e facilita a adoção de veículos elétricos, promovendo a sustentabilidade.

---

## 🌐 Metodologia Utilizada

1. **📊 Coleta de Dados**: Criamos um dataset de comparação que inclui dados de veículos a combustão e elétricos, como consumo de combustível, custo de recarga, autonomia e emissões de CO₂. Utilizamos um arquivo JSON com dados detalhados de mais de 100 veículos elétricos, enquanto o dataset para o treinamento do modelo foi gerado a partir de um script customizado em Python.

2. **🧠 Treinamento do Modelo de Machine Learning**: Utilizamos um modelo de regressão treinado para prever os custos operacionais e as emissões de CO₂ com base nas variáveis fornecidas pelos usuários e dos dados armazenados. O modelo foi treinado usando scikit-learn, com alto desempenho (R² = 0.99), garantindo precisão na previsão dos resultados.

3. **💻 Desenvolvimento da Interface do Usuário**: A interface foi desenvolvida utilizando **Streamlit**, permitindo que os usuários forneçam informações sobre seus veículos atuais, escolham um veículo elétrico para comparação e visualizem os resultados de maneira simples e clara.

4. **📈 Visualização dos Resultados**: Utilizamos **Plotly** para criar gráficos interativos e visualizações de dados, ajudando o usuário a compreender melhor os benefícios a curto e longo prazo de transição para um veículo elétrico.

---

## 📁 Arquivos do Projeto

Abaixo estão descritos os arquivos principais do projeto, detalhando a função de cada um deles:

### **1. Arquivo Principal: interface/app.py**

- **📄 Descrição**: Este é o arquivo principal que executa a interface com o usuário. Nele, está toda a lógica de interação, incluindo a coleta de dados do veículo atual, seleção do veículo elétrico e visualização dos gráficos comparativos.
- **🔗 Funcionalidade**: Conecta todos os componentes do sistema, desde o carregamento dos modelos até a exibição dos resultados, utilizando as bibliotecas **Streamlit** e **Plotly** para uma experiência visual rica e interativa.

---

### **2. Estilo da Interface: assets/style.css**

- **🎨 Descrição**: Arquivo CSS que define o estilo visual da interface.
- **🎨 Funcionalidade**: Torna a aplicação visualmente atraente, aplicando cores, animações suaves, responsividade e um background em gradiente radial que reforça o tema da energia sustentável.

---

### **3. Modelos de Machine Learning: model/model_cost.pkl, model/model_emissions.pkl**

- **🧠 Descrição**: Modelos treinados para prever o custo operacional mensal e as emissões de CO₂ de veículos a combustão e elétricos.
- **🔗 Funcionalidade**: Fornecem previsões precisas, utilizando os dados fornecidos pelo usuário e do dataset gerado. Esses modelos são carregados pelo arquivo `app.py` para realizar cálculos em tempo real.

---

### **4. Dataset do Veículo Elétrico: data/vehicle_data.json**

- **📋 Descrição**: Contém os dados dos veículos elétricos, incluindo informações como marca, modelo, ano, consumo médio, autonomia da bateria e custo de recarga.
- **🔗 Funcionalidade**: Permite que o usuário selecione veículos elétricos para comparação. Esses dados são utilizados para auto-preencher as informações do veículo elétrico durante o processo de seleção.

---

### **5. Script Gerador de Dataset: data/dataset_generator.py**

- **🛠️ Descrição**: Gera um dataset em CSV contendo informações sobre veículos a combustão e elétricos.
- **🔗 Funcionalidade**: Esse arquivo é usado para criar dados de entrada que alimentam os modelos de Machine Learning. Os dados são usados no treinamento dos modelos e refletem diferentes variáveis, como tipo de combustível e consumo médio.

---

### **6. Treinamento do Modelo: model/train_model.py**

- **📊 Descrição**: Arquivo responsável pelo treinamento dos modelos de Machine Learning.
- **🔗 Funcionalidade**: Utiliza os dados gerados pelo script `dataset_generator.py` para treinar modelos que consigam prever custos operacionais e emissões de CO₂. Após o treinamento, os modelos são exportados como arquivos `.pkl`.

---

### **7. Processamento de Dados: utils/data_processing.py**

- **🧩 Descrição**: Contém funções auxiliares para preparar e processar os dados antes do treinamento e da predição.
- **🔗 Funcionalidade**: Inclui a normalização dos dados e transformações necessárias para preparar os datasets de forma adequada.

---

### **8. Dependências do Projeto: requirements.txt**

- **📋 Descrição**: Contém a lista de todas as bibliotecas e dependências necessárias para rodar a aplicação.
- **🔗 Funcionalidade**: Facilita a instalação de todos os pacotes necessários utilizando o comando `pip install -r requirements.txt`.

---

### **9. Arquivo de Configuração do Git: .gitignore**

- **🚫 Descrição**: Define os arquivos e pastas que devem ser ignorados pelo Git ao fazer o versionamento do projeto.
- **🔗 Funcionalidade**: Garante que arquivos desnecessários, como ambientes virtuais, logs e caches, não sejam incluídos no repositório.

---

## 🌄 Resultados Obtidos

- **🔍 Precisão do Modelo**: Os modelos de previsão atingiram um R² de 0.99, mostrando alta precisão na previsão dos custos operacionais e emissões de CO₂. O erro médio absoluto (MAE) de 34.34 e o erro quadrático médio (MSE) de 3696.56 sugerem uma performance consistente.
- **💻 Interface do Usuário**: A interface do **Streamlit** se destacou pela facilidade de uso e apelo visual. O uso de gráficos **Plotly** melhorou a compreensão das vantagens da transição para veículos elétricos.
- **📈 Análise dos Benefícios a Longo Prazo**: Os usuários podem visualizar estimativas de economias financeiras e redução de emissões para períodos de 1, 3, 5, 7 e 10 anos, tornando a decisão de migrar para um veículo elétrico mais tangível.

---

## 📈 Conclusões

O **EcoDrive Insight AI** proporciona aos motoristas uma ferramenta poderosa e intuitiva para entender os impactos financeiros e ambientais da transição para veículos elétricos. A interface amigável, combinada com modelos de Machine Learning de alta precisão e visualizações ricas, torna a adoção de veículos elétricos uma decisão mais clara e informada. O projeto visa reduzir as emissões de CO₂ e incentivar o uso de alternativas mais sustentáveis, contribuindo para um futuro mais limpo e eficiente. 🌍✨

---

## 🚀 Clone o repositório
```bash
git clone -b EcoDrive-Insight-AI https://github.com/MacaulyV/GS-ElectriX-Drive.git
 ```

## 📦 Instale as dependências
```bash
pip install setuptools
pip install joblib
pip install plotly
pip install xgboost
pip install scikit-learn
 ```

###  📂 Navegue até o diretório onde o arquivo app.py está localizado
```bash
cd interface
```

###  ⚙️ Execute a aplicação usando o Streamlit
```bash
streamlit run app.py
```

##  🌐 Acesse no navegador
###  Abra o navegador e acesse http://localhost:8501

 ---  
