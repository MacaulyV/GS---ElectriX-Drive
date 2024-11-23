package com.electrixdrive.electrixdriveplatform.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Entidade que representa as informações de um veículo a partir de um JSON.")
public class VeiculoJson {

    @Schema(description = "Marca do veículo", example = "Toyota", required = true)
    private String marca;

    @Schema(description = "Modelo do veículo", example = "Corolla", required = true)
    private String modelo;

    @Schema(description = "Lista de anos disponíveis para o modelo", example = "[2020, 2021, 2022]")
    private List<Integer> anosDisponiveis;

    @Schema(description = "Lista de tipos de combustível disponíveis para o veículo")
    private List<TipoCombustivel> tiposCombustivel;

    // Getters e Setters
    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

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

    public List<TipoCombustivel> getTiposCombustivel() {
        return tiposCombustivel;
    }

    public void setTiposCombustivel(List<TipoCombustivel> tiposCombustivel) {
        this.tiposCombustivel = tiposCombustivel;
    }

    @Schema(description = "Classe interna que representa o tipo de combustível do veículo.")
    public static class TipoCombustivel {

        @Schema(description = "Tipo de combustível", example = "Gasolina", required = true)
        private String tipo;

        @Schema(description = "Consumo médio do veículo em km/l", example = "12.5")
        private double consumoMedioKmL;

        @Schema(description = "Autonomia do veículo em quilômetros", example = "600.0")
        private double autonomiaKm;

        @Schema(description = "Emissões de CO2 do veículo em gramas por quilômetro", example = "200.0")
        private double emissoesCO2gKm;

        @Schema(description = "Custo do combustível por tanque em reais", example = "300.0")
        private double custoCombustivelPorTanque;

        // Getters e Setters
        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public double getConsumoMedioKmL() {
            return consumoMedioKmL;
        }

        public void setConsumoMedioKmL(double consumoMedioKmL) {
            this.consumoMedioKmL = consumoMedioKmL;
        }

        public double getAutonomiaKm() {
            return autonomiaKm;
        }

        public void setAutonomiaKm(double autonomiaKm) {
            this.autonomiaKm = autonomiaKm;
        }

        public double getEmissoesCO2gKm() {
            return emissoesCO2gKm;
        }

        public void setEmissoesCO2gKm(double emissoesCO2gKm) {
            this.emissoesCO2gKm = emissoesCO2gKm;
        }

        public double getCustoCombustivelPorTanque() {
            return custoCombustivelPorTanque;
        }

        public void setCustoCombustivelPorTanque(double custoCombustivelPorTanque) {
            this.custoCombustivelPorTanque = custoCombustivelPorTanque;
        }
    }
}
