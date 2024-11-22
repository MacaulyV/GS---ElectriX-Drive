import json
import random

# Lista de marcas e modelos de veículos elétricos reais
marcas_modelos = {
    "Tesla": ["Model S", "Model 3", "Model X", "Model Y", "Cybertruck", "Roadster"],
    "Nissan": ["Leaf", "Ariya"],
    "BMW": ["i3", "i4", "iX"],
    "Chevrolet": ["Bolt EV", "Bolt EUV"],
    "Hyundai": ["Kona Electric", "Ioniq 5", "Ioniq 6"],
    "Kia": ["Soul EV", "EV6"],
    "Audi": ["e-tron", "Q4 e-tron"],
    "Volkswagen": ["ID.3", "ID.4", "ID. Buzz"],
    "Ford": ["Mustang Mach-E", "F-150 Lightning"],
    "Lucid": ["Air"],
    "Porsche": ["Taycan"],
    "Jaguar": ["I-Pace"],
    "Mercedes-Benz": ["EQC", "EQA", "EQB", "EQS"],
    "Volvo": ["XC40 Recharge", "C40 Recharge"],
    "Polestar": ["Polestar 2"],
    "Rivian": ["R1T", "R1S"],
    "Renault": ["Zoe"],
    "Peugeot": ["e-208", "e-2008"],
    "Opel": ["Corsa-e", "Mokka-e"],
    "Fiat": ["500 Electric"],
    "Mazda": ["MX-30"],
    "Mini": ["Cooper SE"]
}

# Funções para gerar dados realistas
def gerar_anos(modelo):
    anos_disponiveis = {
        "Tesla": ["2020", "2021", "2022", "2023"],
        "Nissan": ["2020", "2021", "2022", "2023"],
        "BMW": ["2020", "2021", "2022", "2023"],
        "Chevrolet": ["2020", "2021", "2022", "2023"],
        "Hyundai": ["2020", "2021", "2022", "2023"],
        "Kia": ["2020", "2021", "2022", "2023"],
        "Audi": ["2020", "2021", "2022", "2023"],
        "Volkswagen": ["2020", "2021", "2022", "2023"],
        "Ford": ["2020", "2021", "2022", "2023"],
        "Lucid": ["2021", "2022", "2023"],
        "Porsche": ["2020", "2021", "2022", "2023"],
        "Jaguar": ["2020", "2021", "2022", "2023"],
        "Mercedes-Benz": ["2020", "2021", "2022", "2023"],
        "Volvo": ["2020", "2021", "2022", "2023"],
        "Polestar": ["2021", "2022", "2023"],
        "Rivian": ["2021", "2022", "2023"],
        "Renault": ["2020", "2021", "2022", "2023"],
        "Peugeot": ["2020", "2021", "2022", "2023"],
        "Opel": ["2020", "2021", "2022", "2023"],
        "Fiat": ["2020", "2021", "2022", "2023"],
        "Mazda": ["2020", "2021", "2022", "2023"],
        "Mini": ["2020", "2021", "2022", "2023"]
    }
    marca = modelo["marca"]
    return anos_disponiveis.get(marca, ["2020", "2021", "2022", "2023"])

def gerar_consumo_medio(marca, modelo):
    # Valores em kWh/100km para veículos elétricos
    consumo_media_range = {
        "Tesla": (13, 20),
        "Nissan": (12, 18),
        "BMW": (14, 19),
        "Chevrolet": (13, 19),
        "Hyundai": (12, 18),
        "Kia": (12, 18),
        "Audi": (14, 20),
        "Volkswagen": (13, 19),
        "Ford": (14, 20),
        "Lucid": (15, 22),
        "Porsche": (15, 21),
        "Jaguar": (14, 19),
        "Mercedes-Benz": (14, 20),
        "Volvo": (13, 19),
        "Polestar": (14, 20),
        "Rivian": (15, 21),
        "Renault": (12, 18),
        "Peugeot": (13, 19),
        "Opel": (13, 19),
        "Fiat": (12, 18),
        "Mazda": (13, 19),
        "Mini": (13, 19)
    }
    range_consumo = consumo_media_range.get(marca, (13, 20))
    return round(random.uniform(*range_consumo), 2)

def gerar_autonomia_bateria(marca, modelo):
    # Valores em km
    autonomia_range = {
        "Tesla": (350, 600),
        "Nissan": (250, 400),
        "BMW": (300, 500),
        "Chevrolet": (250, 400),
        "Hyundai": (300, 500),
        "Kia": (300, 500),
        "Audi": (300, 500),
        "Volkswagen": (300, 500),
        "Ford": (300, 500),
        "Lucid": (400, 700),
        "Porsche": (300, 500),
        "Jaguar": (300, 500),
        "Mercedes-Benz": (300, 500),
        "Volvo": (300, 500),
        "Polestar": (300, 500),
        "Rivian": (350, 600),
        "Renault": (200, 350),
        "Peugeot": (250, 400),
        "Opel": (250, 400),
        "Fiat": (200, 350),
        "Mazda": (250, 400),
        "Mini": (250, 400)
    }
    range_autonomia = autonomia_range.get(marca, (300, 500))
    return random.randint(*range_autonomia)

def gerar_custo_recarga(marca, modelo):
    # Valores em R$/recarga completa
    custo_recarga_range = {
        "Tesla": (100, 200),
        "Nissan": (80, 160),
        "BMW": (100, 180),
        "Chevrolet": (80, 160),
        "Hyundai": (90, 170),
        "Kia": (90, 170),
        "Audi": (100, 180),
        "Volkswagen": (90, 170),
        "Ford": (100, 180),
        "Lucid": (150, 250),
        "Porsche": (120, 220),
        "Jaguar": (100, 180),
        "Mercedes-Benz": (100, 180),
        "Volvo": (100, 180),
        "Polestar": (100, 180),
        "Rivian": (120, 220),
        "Renault": (70, 140),
        "Peugeot": (80, 160),
        "Opel": (80, 160),
        "Fiat": (70, 140),
        "Mazda": (80, 160),
        "Mini": (80, 160)
    }
    range_custo = custo_recarga_range.get(marca, (100, 200))
    return round(random.uniform(*range_custo), 2)

# Gerar mais de 100 veículos elétricos
veiculos_eletricos = []
for marca, modelos in marcas_modelos.items():
    for modelo in modelos:
        anos = gerar_anos({"marca": marca, "modelo": modelo})
        for ano in anos:
            veiculo = {
                "marca": marca,
                "modelo": modelo,
                "ano": ano,
                "consumo_medio": gerar_consumo_medio(marca, modelo),
                "autonomia_bateria_km": gerar_autonomia_bateria(marca, modelo),
                "custo_recarga": gerar_custo_recarga(marca, modelo),
                "emissoes_CO2": 0
            }
            veiculos_eletricos.append(veiculo)

# Selecionar apenas 100 veículos para evitar duplicatas excessivas
veiculos_eletricos = veiculos_eletricos[:100]

# Estrutura final do JSON
dados_json = {
    "veiculos_eletricos": veiculos_eletricos
}

# Salvar em um arquivo JSON
with open('vehicle_data.json', 'w', encoding='utf-8') as f:
    json.dump(dados_json, f, ensure_ascii=False, indent=4)

print("Arquivo 'vehicle_data.json' gerado com sucesso com 100 veículos elétricos!")
