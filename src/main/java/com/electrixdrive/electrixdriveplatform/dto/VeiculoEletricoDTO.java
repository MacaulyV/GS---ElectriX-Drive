package com.electrixdrive.electrixdriveplatform.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "DTO para os dados de veículos elétricos.")
public class VeiculoEletricoDTO {

    @Schema(description = "Marca do veículo elétrico", example = "Tesla", required = true)
    private String marca;

    @Schema(description = "Lista de modelos de veículos elétricos disponíveis")
    private List<Modelo> modelos;

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public List<Modelo> getModelos() {
        return modelos;
    }

    public void setModelos(List<Modelo> modelos) {
        this.modelos = modelos;
    }

    @Schema(description = "Modelo de veículo elétrico.")
    public static class Modelo {

        @Schema(description = "Modelo do veículo elétrico", example = "Model S", required = true)
        private String modelo;

        @Schema(description = "Lista de anos disponíveis para o modelo", example = "[2020, 2021, 2022]")
        private List<Integer> anosDisponiveis;

        @Schema(description = "Consumo médio do veículo em kWh por 100 km", example = "15.0")
        private Double consumoMedioKwh100km;

        @Schema(description = "Autonomia da bateria do veículo em quilômetros", example = "450.0")
        private Double autonomiaBateriaKm;

        @Schema(description = "Custo de recarga da bateria em reais", example = "75.0")
        private Double custoRecargaBateria;

        @Schema(description = "Emissões de CO2 do veículo em gramas por quilômetro", example = "0.0")
        private Double emissoesCO2gKm;

        public String getModelo() {
            return modelo;
        }

        public void setModelo(String modelo) {
            this.modelo = modelo;
        }

        public List<Integer> getAnosDisponiveis() {
            return anosDisponiveis;
        }

        public void setAnosDisponiveis(List<Integer> anosDisponiveis) {
            this.anosDisponiveis = anosDisponiveis;
        }

        public Double getConsumoMedioKwh100km() {
            return consumoMedioKwh100km;
        }

        public void setConsumoMedioKwh100km(Double consumoMedioKwh100km) {
            this.consumoMedioKwh100km = consumoMedioKwh100km;
        }

        public Double getAutonomiaBateriaKm() {
            return autonomiaBateriaKm;
        }

        public void setAutonomiaBateriaKm(Double autonomiaBateriaKm) {
            this.autonomiaBateriaKm = autonomiaBateriaKm;
        }

        public Double getCustoRecargaBateria() {
            return custoRecargaBateria;
        }

        public void setCustoRecargaBateria(Double custoRecargaBateria) {
            this.custoRecargaBateria = custoRecargaBateria;
        }

        public Double getEmissoesCO2gKm() {
            return emissoesCO2gKm;
        }

        public void setEmissoesCO2gKm(Double emissoesCO2gKm) {
            this.emissoesCO2gKm = emissoesCO2gKm;
        }
    }
}
