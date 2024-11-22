import streamlit as st
import pandas as pd
import numpy as np
import joblib
import json
import requests  # Necessário para futuras integrações com APIs
import plotly.express as px  # Importação para gráficos interativos


# Configurando o estilo da página com CSS personalizado
st.markdown(
    """
    <style>
    /* Fundo com gradiente radial roxo e azul escuro */
    .stApp {
        background-image: radial-gradient(circle 382px at 50% 50.2%, rgba(73,76,212,1) 0.1%, rgba(3,1,50,1) 100.2%);
        color: #FFFFFF; /* Cor do texto padrão */
        font-family: Arial, Helvetica, sans-serif; /* Fonte padrão mais natural */
    }

 /* interface/assets/style.css */

/* Reset básico */
body, html, div, span, applet, object, iframe,
h1, h2, h3, h4, h5, h6, p, blockquote, pre,
a, abbr, acronym, address, big, cite, code,
del, dfn, em, img, ins, kbd, q, s, samp,
small, strike, strong, sub, sup, tt, var,
b, u, i, center,
dl, dt, dd, ol, ul, li,
fieldset, form, label, legend,
table, caption, tbody, tfoot, thead, tr, th, td,
article, aside, canvas, details, embed,
figure, figcaption, footer, header, hgroup,
menu, nav, output, ruby, section, summary,
time, mark, audio, video {
    margin: 0;
    padding: 0;
    border: 0;
    font-size: 100%;
    font: inherit;
    vertical-align: baseline;
}

/* Box-sizing */
*, *:before, *:after {
    box-sizing: border-box;
}

html, body {
    min-height: 100vh;
    background-size: cover;
    background-attachment: fixed;
    font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
    line-height: 1.6;
}

/* Títulos */
.title {
    font-weight: 400;
    font-size: 3em;
    text-align: center;
    margin-top: 20px;
    margin-bottom: 32px;
    -webkit-background-clip: text;
    color: transparent;
    background-image: linear-gradient(109.6deg, rgba(62,161,219,1) 11.2%, rgba(93,52,236,1) 100.2%);
    animation: fadeIn 1s ease-in-out;
}

.description {
    margin-top: 2%;
    font-size: 1.2em;
    text-align: center;
    margin-bottom: 30px;
    color: #ecf0f1;
    text-shadow: 1px 1px #000000;
    animation: fadeIn 1.5s ease-in-out;
}

/* Seções */
.section-header {
    font-weight: 400;
    font-size: 3em;
    margin-top: 40px;
    margin-bottom: 55px;
    color: #8e44ad;
    text-align: center;
    border-bottom: 2px solid #9b59b6;
    padding-bottom: 5px;
    animation: slideInLeft 0.5s ease-in-out;
}

/* Cards de Comparação */
.comparison-card {
    background-color: rgba(0, 0, 0, 0.7);
    padding: 20px;
    border-radius: 25px;
    margin-bottom: 20px;
    transition: transform 0.3s, box-shadow 0.3s;
    animation: fadeInUp 0.5s ease-in-out;
}

.comparison-card:hover {
    transform: scale(1.05);
    box-shadow: 0 4px 20px rgba(155, 89, 182, 0.5);
}

.card-header {
    font-size: 1.5em;
    margin-bottom: 10px;
    color: #9b59b6;
}

.card-content {
    font-size: 1.1em;
    margin-bottom: 5px;
}

/* Benefícios */
.benefits {
    background-color: rgba(0, 0, 0, 0.6);
    padding: 15px;
    border-radius: 5px;
    margin-bottom: 20px;
    animation: fadeInUp 0.5s ease-in-out;
}

.benefits strong {
    color: #8e44ad;
}

/* Informações do Veículo */
.vehicle-info {
    font-size: 1.1em;
    margin-bottom: 10px;
    color: #ecf0f1;
}

.vehicle-info strong {
    color: #9b59b6;
}

/* Mensagens de Erro */
.error {
    font-size: 1.1em;
    background-image: linear-gradient( 111.4deg,  rgba(7,7,9,1) 6.5%, rgba(27,24,113,1) 93.2% );
    color: #ffffff;
    padding: 15px;
    border-radius: 10px;
    margin-bottom: 10px;
    animation: shake 0.5s;
}

/* Mensagens de Sucesso */
.success-message {
    font-weight: bold;
    padding: 15px;
    font-size: 1.1em;
    background-image: linear-gradient( 109.6deg,  rgba(61,245,167,1) 11.2%, rgba(9,111,224,1) 91.1% );
    color: #ffffff;
    border-radius: 10px;
    margin-bottom: 20px;
    animation: fadeIn 0.5s ease-in-out;
}

/* Botões */
.stButton>button {
    background-image: linear-gradient( 91.2deg,  rgba(136,80,226,1) 4%, rgba(16,13,91,1) 96.5% );
    color: #FFFFFF;
    border-radius: 15px;
    padding: 10px 20px;
    font-size: 1.2em;
    transition: background-color 0.3s ease, transform 0.3s ease;
}

.stButton>button:hover {
    background-color: #9b59b6;
    transform: scale(1.05);
    box-shadow: 0 0 200px rgba(149, 0, 255, 0.5);
}

/* Inputs e Selects */
.stSelectbox, .stTextInput, .stNumberInput, .stRadio {
    background-color: rgba(50, 50, 70, 0.9); /* Alterei o fundo dos campos para um tom escuro */
    color: #ffffff; /* Cor do texto dentro dos campos */
    border: 1px solid #8e44ad; /* Adicionei uma borda roxa para realçar */
    border-radius: 15px;
    padding: 10px;
    margin-bottom: 15px;
    transition: box-shadow 0.3s ease, background-color 0.3s ease;
}

/* Hover para inputs e selects */
.stSelectbox:hover, .stTextInput:hover, .stNumberInput:hover, .stRadio:hover {
   box-shadow: 0 0 50px rgba(149, 0, 255, 0.5);
    background-color: rgba(70, 70, 100, 0.9); /* Cor de fundo levemente alterada ao passar o mouse */
}

/* Checkbox Group */
.checkbox-group {
    font-size: 1.1em;
    color: #ecf0f1;
    margin-bottom: 10px;
}

/* Markdown Customizada */
.stMarkdown {
    background-color: rgba(0, 0, 0, 0.6);
    padding: 15px;
    border-radius: 10px;
    margin-bottom: 20px;
    box-shadow: 0 4px 6px rgba(155, 89, 182, 0.2);
}

/* Footer */
footer {
    font-weight: bold;
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    background-color: rgba(0, 0, 0, 0.9);
    color: #bdc3c7;
    text-align: center;
    padding: 10px 0;
    animation: fadeInUp 1s ease-in-out;
}

/* Gráficos */
.stPlotlyChart {
    transition: all 1.0s ease-in-out;
    background-color: rgba(0, 0, 0, 0.6);
    border-radius: 20px;
    padding: 5%;
    margin-bottom: 20px;
}

/* Gráficos */
.stPlotlyChart:hover {
 transform: scale(1.05);
}

/* Animações */
@keyframes fadeIn {
    from { opacity: 0; }
    to { opacity: 1; }
}

@keyframes fadeInUp {
    from { opacity: 0; transform: translateY(20px); }
    to { opacity: 1; transform: translateY(0); }
}
@keyframes slideInLeft {
    from { opacity: 0; transform: translateX(-20px); }
    to { opacity: 1; transform: translateX(0); }
}

@keyframes shake {
    0% { transform: translateX(0); }
    25% { transform: translateX(-5px); }
    50% { transform: translateX(5px); }
    75% { transform: translateX(-5px); }
    100% { transform: translateX(0); }
}

/* Responsividade */

/* Dispositivos móveis */
@media (max-width: 768px) {
    .title {
        font-size: 2.5em;
    }

    .description {
        font-size: 1em;
    }

    .section-header {
        font-size: 1.8em;
    }

    .comparison-card {
        padding: 15px;
    }

    .card-header {
        font-size: 1.3em;
    }

    .card-content {
        font-size: 1em;
    }

    .benefits {
        font-size: 1em;
        padding: 10px;
    }

    .vehicle-info {
        font-size: 1em;
    }

    .stButton>button {
        width: 100%;
        padding: 12px 0;
        font-size: 1em;
    }

    .stSelectbox, .stTextInput, .stNumberInput, .stRadio {
        font-size: 0.9em;
    }

    .stMarkdown {
        padding: 10px;
    }

    footer {
        font-size: 12px;
    }
}

/* Tablets */
@media (min-width: 769px) and (max-width: 1024px) {
    .title {
        font-size: 2.8em;
    }

    .description {
        font-size: 1.1em;
    }

    .section-header {
        font-size: 1.9em;
    }

    .comparison-card {
        padding: 18px;
    }

    .card-header {
        font-size: 1.4em;
    }

    .card-content {
        font-size: 1.05em;
    }

    .benefits {
        font-size: 1.1em;
        padding: 12px;
    }

    .vehicle-info {
        font-size: 1.05em;
    }

    .stButton>button {
        width: 100%;
        padding: 12px 0;
        font-size: 1em;
    }

    .stSelectbox, .stTextInput, .stNumberInput, .stRadio {
        font-size: 1em;
    }

    .stMarkdown {
        padding: 12px;
    }

    footer {
        font-size: 13px;
    }
}

/* Desktops grandes */
@media (min-width: 1025px) {
    .title {
        font-size: 3em;
    }

    .description {
        font-size: 1.2em;
    }

    .section-header {
        font-size: 2em;
    }

    .comparison-card {
        padding: 20px;
    }

    .card-header {
        font-size: 1.5em;
    }

    .card-content {
        font-size: 1.1em;
    }

    .benefits {
        font-size: 1.2em;
        padding: 15px;
    }

    .vehicle-info {
        font-size: 1.1em;
    }

    .stButton>button {
        padding: 10px 20px;
        font-size: 1em;
    }

    .stSelectbox, .stTextInput, .stNumberInput, .stRadio {
        font-size: 1em;
    }

    .stMarkdown {
        padding: 15px;
    }

    footer {
        font-size: 14px;
    }
}

/* Animações e Transições */
.stButton>button {
    transition: background-color 0.3s ease, transform 0.3s ease, box-shadow 0.3s ease;
}

.stButton>button:active {
    transform: scale(0.98);
}

.comparison-card:hover {
    transform: scale(1.05);
    box-shadow: 0 4px 20px rgba(155, 89, 182, 0.5);
}

.stSelectbox:hover, .stTextInput:hover, .stNumberInput:hover, .stRadio:hover {
    box-shadow: 0 0 10px rgba(155, 89, 182, 0.5);
}

.stPlotlyChart {
    animation: fadeInUp 0.5s ease-in-out;
}

/* Estilo para o spinner de carregamento */
.stSpinner > div > div {
    border-top-color: #8e44ad;
}
    
    </style>
    """,
    unsafe_allow_html=True
)

# Carregar modelos e escalonador pré-treinados
model_cost = joblib.load('../model/model_cost.pkl')
model_emissions = joblib.load('../model/model_emissions.pkl')
scaler = joblib.load('../model/scaler.pkl')

# Carregar dados dos veículos elétricos a partir de um arquivo JSON
with open('../data/vehicle_data.json', 'r', encoding='utf-8') as f:
    vehicle_data = json.load(f)

# Função para obter dados de um veículo elétrico selecionado pelo usuário
def get_electric_vehicle_data(marca, modelo, ano):
    for veiculo in vehicle_data['veiculos_eletricos']:
        if veiculo['marca'] == marca and veiculo['modelo'] == modelo and veiculo['ano'] == ano:
            return veiculo
    return None  # Retorna None se o veículo não for encontrado

# Função para validar os campos de entrada fornecidos pelo usuário
def validar_entradas(consumo_medio, quilometragem_mensal, custo_combustivel, emissao_co2):
    erros = []
    if consumo_medio < 3 or consumo_medio > 20:
        erros.append("O consumo médio deve estar entre 3 km/l e 20 km/l.")
    if quilometragem_mensal < 100 or quilometragem_mensal > 10000:
        erros.append("A quilometragem mensal deve estar entre 100 km e 10.000 km.")
    if custo_combustivel < 3 or custo_combustivel > 10:
        erros.append("O custo do combustível deve estar entre R$ 3,00 e R$ 10,00 por litro.")
    if emissao_co2 < 2 or emissao_co2 > 3:
        erros.append("A emissão de CO₂ deve estar entre 2 kg/litro e 3 kg/litro.")
    return erros  # Retorna a lista de erros encontrados

# Função para obter o custo médio da eletricidade
def obter_custo_eletricidade():
    # Simulação de requisição a uma API para obter o custo da eletricidade
    # Aqui, vamos usar um valor fixo como exemplo
    custo_eletricidade_padrao = 0.65  # R$/kWh
    return custo_eletricidade_padrao

# Função para calcular economias e reduções de emissões ao longo dos anos
def calcular_economias(custo_atual, custo_ev, anos, emissoes_atual, emissoes_ev):
    economias = []
    reducoes = []
    for ano in anos:
        economia = (custo_atual - custo_ev) * 12 * ano  # Economia anual
        reducao = (emissoes_atual - emissoes_ev) * 12 * ano  # Redução anual de emissões
        economias.append(economia)
        reducoes.append(reducao)
    return economias, reducoes

# Inicializar session_state para armazenar resultados da comparação
if 'comparacao_mensal' not in st.session_state:
    st.session_state.comparacao_mensal = None

# Título e descrição do aplicativo
st.markdown("<div class='title'>🌿 EcoDrive Insight AI</div>", unsafe_allow_html=True)
st.markdown("<div class='description'>Compare seu veículo atual com veículos elétricos e descubra os benefícios da transição para energia sustentável.</div>", unsafe_allow_html=True)

# Seção para entrada dos dados do veículo atual
st.markdown("<div class='section-header'>🚗 Dados do seu veículo atual</div>", unsafe_allow_html=True)

col1, col2 = st.columns(2)

with col1:
    consumo_medio = st.number_input("Consumo médio do seu veículo (km/l)", min_value=1.0, max_value=20.0, value=12.0, format="%.2f")
    quilometragem_mensal = st.number_input("Quilometragem mensal percorrida (km)", min_value=100.0, max_value=10000.0, value=1500.0, format="%.2f")
    tipo_combustivel = st.selectbox("Tipo de combustível", ["Gasolina", "Etanol", "Diesel", "GNV"])
    custo_combustivel = st.number_input("Custo do combustível (R$/litro)", min_value=3.0, max_value=10.0, value=6.0, format="%.2f")

with col2:
    nome_veiculo = st.text_input("Marca e modelo do seu veículo", "Ex: Ford Ka")
    emissao_co2 = st.number_input("Emissão de CO₂ do seu veículo (kg/litro)", min_value=2.0, max_value=3.0, value=2.31, format="%.2f")

# Validação dos campos de entrada
erros_entrada = validar_entradas(consumo_medio, quilometragem_mensal, custo_combustivel, emissao_co2)

if erros_entrada:
    for erro in erros_entrada:
        st.markdown(f"<div class='error'>{erro}</div>", unsafe_allow_html=True)

# Seção para seleção do veículo elétrico
st.markdown("<div class='section-header'>🔌 Escolha um veículo elétrico para comparar</div>", unsafe_allow_html=True)

marcas = sorted(set([veiculo['marca'] for veiculo in vehicle_data['veiculos_eletricos']]))
marca_selecionada = st.selectbox("Marca", marcas)

modelos = sorted(set([veiculo['modelo'] for veiculo in vehicle_data['veiculos_eletricos'] if veiculo['marca'] == marca_selecionada]))
modelo_selecionado = st.selectbox("Modelo", modelos)

anos = sorted(set([veiculo['ano'] for veiculo in vehicle_data['veiculos_eletricos'] if veiculo['marca'] == marca_selecionada and veiculo['modelo'] == modelo_selecionado]))
ano_selecionado = st.selectbox("Ano", anos)

veiculo_eletrico = get_electric_vehicle_data(marca_selecionada, modelo_selecionado, ano_selecionado)

if veiculo_eletrico:
    st.markdown(f"<div class='vehicle-info'><strong>Consumo médio (kWh/100km):</strong> {veiculo_eletrico['consumo_medio']}</div>", unsafe_allow_html=True)
    st.markdown(f"<div class='vehicle-info'><strong>Autonomia da bateria (km):</strong> {veiculo_eletrico['autonomia_bateria_km']}</div>", unsafe_allow_html=True)
    st.markdown(f"<div class='vehicle-info'><strong>Custo de recarga (R$):</strong> {veiculo_eletrico['custo_recarga']}</div>", unsafe_allow_html=True)
else:
    st.markdown("<div class='error'>Veículo elétrico não encontrado.</div>", unsafe_allow_html=True)

# Opção para customizar o custo da eletricidade
st.markdown("<div class='section-header'>⚡ Custo da Eletricidade</div>", unsafe_allow_html=True)

opcao_custo_eletricidade = st.radio(
    "Como deseja informar o custo da eletricidade?",
    ('Usar valor padrão', 'Inserir manualmente')
)

if opcao_custo_eletricidade == 'Usar valor padrão':
    custo_eletricidade = obter_custo_eletricidade()
    st.markdown(f"<div class='vehicle-info'>O custo médio da eletricidade considerado será de <strong>R$ {custo_eletricidade:.2f} por kWh</strong>.</div>", unsafe_allow_html=True)
else:
    custo_eletricidade = st.number_input("Informe o custo da eletricidade (R$/kWh)", min_value=0.5, max_value=2.0, value=0.8, format="%.2f")

# Botão para calcular a comparação
if st.button("Comparar veículos"):
    if erros_entrada:
        st.markdown("<div class='error'>Por favor, corrija os erros antes de prosseguir.</div>", unsafe_allow_html=True)
    else:
        with st.spinner('Calculando a comparação...'):
            # Preparar os dados para o modelo
            # Para o veículo atual
            tipo_veiculo = 0  # Combustão
            if tipo_combustivel == "Gasolina":
                tipo_combustivel_cod = 0
            elif tipo_combustivel == "Etanol":
                tipo_combustivel_cod = 1
            elif tipo_combustivel == "Diesel":
                tipo_combustivel_cod = 2
            else:
                tipo_combustivel_cod = 3  # GNV

            # Construir o DataFrame de entrada
            X_usuario = pd.DataFrame({
                'TipoVeiculo': [tipo_veiculo],
                'TipoCombustivel': [tipo_combustivel_cod],
                'ConsumoMedio': [consumo_medio],
                'QuilometragemMensal': [quilometragem_mensal],
                'CustoCombustivelEnergia': [custo_combustivel]
            })

            # Escalonar as features
            X_usuario_scaled = scaler.transform(X_usuario)

            # Previsões para o veículo atual
            custo_operacional_atual = model_cost.predict(X_usuario_scaled)[0]
            emissoes_co2_atual = model_emissions.predict(X_usuario_scaled)[0]

            # Para o veículo elétrico
            tipo_veiculo_ev = 1  # Elétrico
            tipo_combustivel_cod_ev = 4  # Eletricidade

            consumo_medio_ev = veiculo_eletrico['consumo_medio']

            X_ev = pd.DataFrame({
                'TipoVeiculo': [tipo_veiculo_ev],
                'TipoCombustivel': [tipo_combustivel_cod_ev],
                'ConsumoMedio': [consumo_medio_ev],
                'QuilometragemMensal': [quilometragem_mensal],
                'CustoCombustivelEnergia': [custo_eletricidade]
            })

            # Escalonar as features
            X_ev_scaled = scaler.transform(X_ev)

            # Previsões para o veículo elétrico
            custo_operacional_ev = model_cost.predict(X_ev_scaled)[0]
            emissoes_co2_ev = model_emissions.predict(X_ev_scaled)[0]

            # Armazenar os resultados da comparação na session_state
            st.session_state.comparacao_mensal = {
                'custo_atual': custo_operacional_atual,
                'custo_ev': custo_operacional_ev,
                'emissoes_atual': emissoes_co2_atual,
                'emissoes_ev': emissoes_co2_ev
            }

            # Exibir os resultados
            st.markdown("<div class='success-message'>Comparação realizada com sucesso!</div>", unsafe_allow_html=True)

            st.markdown("<div class='section-header'>📊 Comparação dos Veículos</div>", unsafe_allow_html=True)

            col1, col2 = st.columns(2)

            with col1:
                st.markdown("<div class='comparison-card'>", unsafe_allow_html=True)
                st.markdown("<div class='card-header'>Seu Veículo Atual</div>", unsafe_allow_html=True)
                st.markdown(f"<div class='card-content'><strong>Custo Operacional Mensal (R$):</strong> {custo_operacional_atual:.2f}</div>", unsafe_allow_html=True)
                st.markdown(f"<div class='card-content'><strong>Emissões de CO₂ Mensais (g):</strong> {emissoes_co2_atual:.2f}</div>", unsafe_allow_html=True)
                st.markdown("</div>", unsafe_allow_html=True)

            with col2:
                st.markdown("<div class='comparison-card'>", unsafe_allow_html=True)
                st.markdown("<div class='card-header'>Veículo Elétrico</div>", unsafe_allow_html=True)
                st.markdown(f"<div class='card-content'><strong>Custo Operacional Mensal (R$):</strong> {custo_operacional_ev:.2f}</div>", unsafe_allow_html=True)
                st.markdown(f"<div class='card-content'><strong>Emissões de CO₂ Mensais (g):</strong> {emissoes_co2_ev:.2f}</div>", unsafe_allow_html=True)
                st.markdown("</div>", unsafe_allow_html=True)

            # Cálculo da economia
            economia_custo = custo_operacional_atual - custo_operacional_ev
            reducao_emissoes = emissoes_co2_atual - emissoes_co2_ev

            st.markdown("---")
            st.markdown("<div class='section-header'>💡 Benefícios da Transição</div>", unsafe_allow_html=True)

            st.markdown(f"<div class='benefits'><strong>🔸 Economia Mensal Estimada:</strong> R$ {economia_custo:.2f}</div>", unsafe_allow_html=True)
            st.markdown(f"<div class='benefits'><strong>🔸 Redução nas Emissões de CO₂:</strong> {reducao_emissoes:.2f} g</div>", unsafe_allow_html=True)

            # Gráficos comparativos usando Plotly
            st.markdown("<div class='section-header'>Visualização Comparativa</div>", unsafe_allow_html=True)

            # Gráfico de barras para custos usando Plotly com tema personalizado
            df_custos = pd.DataFrame({
                'Veículo': ['Atual', 'Elétrico'],
                'Custo Operacional Mensal (R$)': [custo_operacional_atual, custo_operacional_ev]
            })

            fig_custos = px.bar(
                df_custos,
                x='Veículo',
                y='Custo Operacional Mensal (R$)',
                title='Comparação de Custos Operacionais Mensais',
                color='Veículo',
                color_discrete_map={'Atual': '#4A90E2', 'Elétrico': '#50E3C2'},
                text_auto=True
            )
            fig_custos.update_layout(
                plot_bgcolor='rgba(0,0,0,0)',
                paper_bgcolor='rgba(0,0,0,0)',
                title_font=dict(color='#FFFFFF', size=20),
                xaxis_title_font=dict(color='#FFFFFF', size=16),
                yaxis_title_font=dict(color='#FFFFFF', size=16),
                xaxis=dict(showgrid=False, tickfont=dict(color='#FFFFFF')),
                yaxis=dict(showgrid=True, gridcolor='rgba(255,255,255,0.2)', tickfont=dict(color='#FFFFFF')),
                legend=dict(font=dict(color='#FFFFFF'))
            )
            fig_custos.update_traces(marker_line_color='rgba(0,0,0,0)', textfont_color='#FFFFFF')

            st.plotly_chart(fig_custos, use_container_width=True)

            # Gráfico de barras para emissões usando Plotly com tema personalizado
            df_emissoes = pd.DataFrame({
                'Veículo': ['Atual', 'Elétrico'],
                'Emissões de CO₂ Mensais (g)': [emissoes_co2_atual, emissoes_co2_ev]
            })

            fig_emissoes = px.bar(
                df_emissoes,
                x='Veículo',
                y='Emissões de CO₂ Mensais (g)',
                title='Comparação de Emissões de CO₂ Mensais',
                color='Veículo',
                color_discrete_map={'Atual': '#4A90E2', 'Elétrico': '#50E3C2'},
                text_auto=True
            )
            fig_emissoes.update_layout(
                plot_bgcolor='rgba(0,0,0,0)',
                paper_bgcolor='rgba(0,0,0,0)',
                title_font=dict(color='#FFFFFF', size=20),
                xaxis_title_font=dict(color='#FFFFFF', size=16),
                yaxis_title_font=dict(color='#FFFFFF', size=16),
                xaxis=dict(showgrid=False, tickfont=dict(color='#FFFFFF')),
                yaxis=dict(showgrid=True, gridcolor='rgba(255,255,255,0.2)', tickfont=dict(color='#FFFFFF')),
                legend=dict(font=dict(color='#FFFFFF'))
            )
            fig_emissoes.update_traces(marker_line_color='rgba(0,0,0,0)', textfont_color='#FFFFFF')

            st.plotly_chart(fig_emissoes, use_container_width=True)

# 📈 **Seção: Estimativa de Economia ao Longo do Tempo**
st.markdown("<div class='section-header'>💸 Estimativa de Economia ao Longo do Tempo</div>", unsafe_allow_html=True)

# Verificar se a comparação mensal foi realizada
if st.session_state.comparacao_mensal:
    with st.form(key='estimativa_form'):
        st.markdown("<div class='checkbox-group'>Selecione os anos para a estimativa:</div>", unsafe_allow_html=True)
        anos_options = [1, 2, 3, 5, 7, 10]
        anos_selecionados = []
        for ano in anos_options:
            if st.checkbox(f"{ano} anos", key=f"ano_{ano}"):
                anos_selecionados.append(ano)
        submit_button = st.form_submit_button(label='Visualizar Benefícios em Anos')

    if submit_button:
        if not anos_selecionados:
            st.markdown("<div class='error'>Por favor, selecione pelo menos um ano para visualizar os benefícios.</div>", unsafe_allow_html=True)
        else:
            # Extrair os resultados da comparação mensal
            custo_operacional_atual = st.session_state.comparacao_mensal['custo_atual']
            custo_operacional_ev = st.session_state.comparacao_mensal['custo_ev']
            emissoes_co2_atual = st.session_state.comparacao_mensal['emissoes_atual']
            emissoes_co2_ev = st.session_state.comparacao_mensal['emissoes_ev']

            # Calcular economias e reduções para os anos selecionados
            economias, reducoes = calcular_economias(custo_operacional_atual, custo_operacional_ev, anos_selecionados, emissoes_co2_atual, emissoes_co2_ev)

            # Criar DataFrame para Plotly
            df_economias = pd.DataFrame({
                'Anos': anos_selecionados,
                'Economia Total (R$)': economias,
                'Redução Total de Emissões de CO₂ (g)': reducoes
            })

            # Gráfico de Linha para Economia ao Longo do Tempo com tema personalizado
            fig_economia = px.line(
                df_economias,
                x='Anos',
                y='Economia Total (R$)',
                title='Economia Total ao Longo do Tempo',
                markers=True,
                labels={'Economia Total (R$)': 'Economia Total (R$)', 'Anos': 'Anos'},
                color_discrete_sequence=['#50E3C2']
            )
            fig_economia.update_layout(
                plot_bgcolor='rgba(0,0,0,0)',
                paper_bgcolor='rgba(0,0,0,0)',
                title_font=dict(color='#FFFFFF', size=20),
                xaxis_title_font=dict(color='#FFFFFF', size=16),
                yaxis_title_font=dict(color='#FFFFFF', size=16),
                xaxis=dict(showgrid=False, tickfont=dict(color='#FFFFFF')),
                yaxis=dict(showgrid=True, gridcolor='rgba(255,255,255,0.2)', tickfont=dict(color='#FFFFFF')),
                legend=dict(font=dict(color='#FFFFFF'))
            )
            fig_economia.update_traces(line=dict(width=4), marker=dict(size=8))

            st.plotly_chart(fig_economia, use_container_width=True)

            # Gráfico de Linha para Redução de Emissões ao Longo do Tempo com tema personalizado
            fig_reducao = px.line(
                df_economias,
                x='Anos',
                y='Redução Total de Emissões de CO₂ (g)',
                title='Redução Total de Emissões de CO₂ ao Longo do Tempo',
                markers=True,
                labels={'Redução Total de Emissões de CO₂ (g)': 'Redução Total de Emissões de CO₂ (g)', 'Anos': 'Anos'},
                color_discrete_sequence=['#E74C3C']
            )
            fig_reducao.update_layout(
                plot_bgcolor='rgba(0,0,0,0)',
                paper_bgcolor='rgba(0,0,0,0)',
                title_font=dict(color='#FFFFFF', size=20),
                xaxis_title_font=dict(color='#FFFFFF', size=16),
                yaxis_title_font=dict(color='#FFFFFF', size=16),
                xaxis=dict(showgrid=False, tickfont=dict(color='#FFFFFF')),
                yaxis=dict(showgrid=True, gridcolor='rgba(255,255,255,0.2)', tickfont=dict(color='#FFFFFF')),
                legend=dict(font=dict(color='#FFFFFF'))
            )
            fig_reducao.update_traces(line=dict(width=4), marker=dict(size=8))

            st.plotly_chart(fig_reducao, use_container_width=True)

            # Exibir os resultados em cards
            st.markdown("<div class='section-header'>📄 Detalhes das Estimativas</div>", unsafe_allow_html=True)
            for idx, ano in enumerate(anos_selecionados):
                economia = economias[idx]
                reducao = reducoes[idx]
                st.markdown(f"""
                <div class='comparison-card'>
                    <div class='card-header'>Estatísticas para {ano} Anos</div>
                    <div class='card-content'><strong>Economia Total (R$):</strong> {economia:.2f}</div>
                    <div class='card-content'><strong>Redução Total de Emissões de CO₂ (g):</strong> {reducao:.2f}</div>
                </div>
                """, unsafe_allow_html=True)
else:
    st.markdown("<div class='error'>Realize a comparação mensal antes de visualizar as estimativas a longo prazo.</div>", unsafe_allow_html=True)

# Footer personalizado
st.markdown("""
<footer>
    Desenvolvido por Macauly Souza • EcoDrive Insight AI<br>
    Daniel Bezerra da Silva Melo, Gustavo Rocha Caxias, Macauly Vivaldo da Silva
</footer>
""", unsafe_allow_html=True)
