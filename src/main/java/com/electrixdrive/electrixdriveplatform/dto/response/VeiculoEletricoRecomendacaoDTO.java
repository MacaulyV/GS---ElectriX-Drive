package com.electrixdrive.electrixdriveplatform.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta para a recomendação de veículos elétricos.")
public class VeiculoEletricoRecomendacaoDTO {

    @Schema(description = "Marca do veículo elétrico recomendado", example = "Tesla", required = true)
    private String marca;

    @Schema(description = "Modelo do veículo elétrico recomendado", example = "Model S", required = true)
    private String modelo;

    @Schema(description = "Ano do veículo elétrico recomendado", example = "2023")
    private Integer ano;

    @Schema(description = "Consumo médio do veículo elétrico em kWh/100km", example = "15.0", required = true)
    private Double consumoMedio;

    @Schema(description = "Autonomia do veículo elétrico em quilômetros", example = "450.0", required = true)
    private Double autonomia;

    @Schema(description = "Custo de recarga da bateria do veículo elétrico em reais", example = "50.0", required = true)
    private Double custoRecargaBateria;

    @Schema(description = "Emissão de CO2 do veículo elétrico em kg", example = "0.0", required = true)
    private Double emissaoCO2;

    // Construtor
    public VeiculoEletricoRecomendacaoDTO(String marca, String modelo, Double consumoMedio, Double autonomia, Double custoRecargaBateria, Double emissaoCO2) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.consumoMedio = consumoMedio;
        this.autonomia = autonomia;
        this.custoRecargaBateria = custoRecargaBateria;
        this.emissaoCO2 = emissaoCO2;
    }

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

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Double getConsumoMedio() {
        return consumoMedio;
    }

    public void setConsumoMedio(Double consumoMedio) {
        this.consumoMedio = consumoMedio;
    }

    public Double getAutonomia() {
        return autonomia;
    }

    public void setAutonomia(Double autonomia) {
        this.autonomia = autonomia;
    }

    public Double getCustoRecargaBateria() {
        return custoRecargaBateria;
    }

    public void setCustoRecargaBateria(Double custoRecargaBateria) {
        this.custoRecargaBateria = custoRecargaBateria;
    }

    public Double getEmissaoCO2() {
        return emissaoCO2;
    }

    public void setEmissaoCO2(Double emissaoCO2) {
        this.emissaoCO2 = emissaoCO2;
    }
}
