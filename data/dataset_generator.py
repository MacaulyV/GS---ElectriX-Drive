# data/dataset_generator.py

import pandas as pd
import numpy as np

NUM_SAMPLES = 1000  # Você pode ajustar este número conforme necessário

# Lista para armazenar os dados
data = []

for _ in range(NUM_SAMPLES):
    # Escolher aleatoriamente o tipo de veículo
    vehicle_type = np.random.choice(['Combustão', 'Elétrico'])

    # Gerar quilometragem mensal
    monthly_mileage = np.random.uniform(500, 3000)  # km

    if vehicle_type == 'Combustão':
        # Dados para veículos a combustão
        fuel_consumption = np.random.uniform(5, 15)  # km/l
        fuel_price = np.random.uniform(5.0, 7.0)  # R$/litro
        co2_emission_factor = np.random.uniform(2.3, 2.7)  # kg CO₂ por litro
        # Cálculos
        total_fuel_used = monthly_mileage / fuel_consumption  # litros
        monthly_cost = total_fuel_used * fuel_price  # R$
        total_co2_emitted = total_fuel_used * co2_emission_factor * 1000  # gramas
        # Tipo de combustível
        fuel_type = np.random.choice(['Gasolina', 'Etanol', 'Diesel'])
        consumo_medio = fuel_consumption
    else:
        # Dados para veículos elétricos
        energy_consumption = np.random.uniform(12, 20)  # kWh/100km
        electricity_price = np.random.uniform(0.7, 0.9)  # R$/kWh
        # Cálculos
        total_energy_used = (monthly_mileage * energy_consumption) / 100  # kWh
        monthly_cost = total_energy_used * electricity_price  # R$
        total_co2_emitted = 0  # Emissões diretas são zero
        fuel_type = 'Eletricidade'
        consumo_medio = energy_consumption

    # Adicionar os dados à lista
    data.append({
        'TipoVeiculo': vehicle_type,
        'TipoCombustivel': fuel_type,
        'ConsumoMedio': round(consumo_medio, 2),
        'QuilometragemMensal': round(monthly_mileage, 2),
        'CustoCombustivelEnergia': round(fuel_price if vehicle_type == 'Combustão' else electricity_price, 2),
        'CustoMensalOperacional': round(monthly_cost, 2),
        'EmissoesCO2': round(total_co2_emitted, 2)
    })

# Criar o DataFrame
df = pd.DataFrame(data)

# Salvar em um arquivo CSV
df.to_csv('dataset.csv', index=False)
print("Dataset gerado com sucesso e salvo em 'dataset.csv'.")
