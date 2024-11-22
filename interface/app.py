import streamlit as st
import plotly.express as px

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

# Dados dos veículos elétricos embutidos no código
vehicle_data = {
    "veiculos_eletricos": [
        {
            "marca": "Tesla",
            "modelo": "Model 3",
            "ano": 2023,
            "consumo_medio": 15.0,  # kWh/100km
            "autonomia_bateria_km": 350,
            "custo_recarga": 25.0  # R$ por recarga
        },
        {
            "marca": "Nissan",
            "modelo": "Leaf",
            "ano": 2022,
            "consumo_medio": 14.5,
            "autonomia_bateria_km": 300,
            "custo_recarga": 20.0
        },
        {
            "marca": "Chevrolet",
            "modelo": "Bolt",
            "ano": 2023,
            "consumo_medio": 16.0,
            "autonomia_bateria_km": 360,
            "custo_recarga": 22.0
        },
        # Adicione mais veículos conforme necessário
    ]
}

# Função para obter dados de um veículo elétrico selecionado pelo usuário
def get_electric_vehicle_data(marca, modelo, ano):
    for veiculo in vehicle_data['veiculos_eletricos']:
        if veiculo['marca'] == marca and veiculo['modelo'] == modelo and veiculo['ano'] == ano:
            return veiculo
    return None  # Retorna None se o veículo não for encontrado

# Inicializar session_state para armazenar resultados da comparação (se necessário)
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

# Exibir informações básicas (sem cálculos)
st.markdown("<div class='vehicle-info'>Marca e Modelo: <strong>{}</strong></div>".format(nome_veiculo), unsafe_allow_html=True)
st.markdown("<div class='vehicle-info'>Consumo Médio: <strong>{} km/l</strong></div>".format(consumo_medio), unsafe_allow_html=True)
st.markdown("<div class='vehicle-info'>Quilometragem Mensal: <strong>{} km</strong></div>".format(quilometragem_mensal), unsafe_allow_html=True)
st.markdown("<div class='vehicle-info'>Tipo de Combustível: <strong>{}</strong></div>".format(tipo_combustivel), unsafe_allow_html=True)
st.markdown("<div class='vehicle-info'>Custo do Combustível: <strong>R$ {:.2f}/litro</strong></div>".format(custo_combustivel), unsafe_allow_html=True)
st.markdown("<div class='vehicle-info'>Emissão de CO₂: <strong>{} kg/litro</strong></div>".format(emissao_co2), unsafe_allow_html=True)

# Seção para seleção do veículo elétrico
st.markdown("<div class='section-header'>🔌 Escolha um veículo elétrico para comparar</div>", unsafe_allow_html=True)

marcas = sorted(set([veiculo['marca'] for veiculo in vehicle_data['veiculos_eletricos']]))
if marcas:
    marca_selecionada = st.selectbox("Marca", marcas)
else:
    marca_selecionada = st.selectbox("Marca", ["Nenhuma disponível"])

modelos = sorted(set([veiculo['modelo'] for veiculo in vehicle_data['veiculos_eletricos'] if veiculo['marca'] == marca_selecionada]))
if modelos:
    modelo_selecionado = st.selectbox("Modelo", modelos)
else:
    modelo_selecionado = st.selectbox("Modelo", ["Nenhum disponível"])

anos = sorted(set([veiculo['ano'] for veiculo in vehicle_data['veiculos_eletricos'] if veiculo['marca'] == marca_selecionada and veiculo['modelo'] == modelo_selecionado]))
if anos:
    ano_selecionado = st.selectbox("Ano", anos)
else:
    ano_selecionado = st.selectbox("Ano", ["Nenhum disponível"])

veiculo_eletrico = get_electric_vehicle_data(marca_selecionada, modelo_selecionado, ano_selecionado)

if veiculo_eletrico:
    st.markdown(f"<div class='vehicle-info'><strong>Consumo médio (kWh/100km):</strong> {veiculo_eletrico['consumo_medio']} kWh/100km</div>", unsafe_allow_html=True)
    st.markdown(f"<div class='vehicle-info'><strong>Autonomia da bateria (km):</strong> {veiculo_eletrico['autonomia_bateria_km']} km</div>", unsafe_allow_html=True)
    st.markdown(f"<div class='vehicle-info'><strong>Custo de recarga (R$):</strong> R$ {veiculo_eletrico['custo_recarga']:.2f}</div>", unsafe_allow_html=True)
else:
    st.markdown("<div class='error'>Veículo elétrico não encontrado.</div>", unsafe_allow_html=True)

# Opção para customizar o custo da eletricidade
st.markdown("<div class='section-header'>⚡ Custo da Eletricidade</div>", unsafe_allow_html=True)

opcao_custo_eletricidade = st.radio(
    "Como deseja informar o custo da eletricidade?",
    ('Usar valor padrão', 'Inserir manualmente')
)

if opcao_custo_eletricidade == 'Usar valor padrão':
    custo_eletricidade = 0.65  # Valor fixo
    st.markdown(f"<div class='vehicle-info'>O custo médio da eletricidade considerado será de <strong>R$ {custo_eletricidade:.2f} por kWh</strong>.</div>", unsafe_allow_html=True)
else:
    custo_eletricidade = st.number_input("Informe o custo da eletricidade (R$/kWh)", min_value=0.5, max_value=2.0, value=0.8, format="%.2f")
    st.markdown(f"<div class='vehicle-info'>Custo da Eletricidade: <strong>R$ {custo_eletricidade:.2f}/kWh</strong></div>", unsafe_allow_html=True)

# Footer personalizado
st.markdown("""
<footer>
    Desenvolvido por Macauly Souza • EcoDrive Insight AI<br>
    Daniel Bezerra da Silva Melo, Gustavo Rocha Caxias, Macauly Vivaldo da Silva
</footer>
""", unsafe_allow_html=True)
