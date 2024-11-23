package com.electrixdrive.electrixdriveplatform.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta para os dados de veículos elétricos.")
public class VeiculoEletricoResponseDTO {

    @Schema(description = "Identificador único do veículo elétrico", example = "1", required = true)
    private Long id;

    @Schema(description = "Marca do veículo elétrico", example = "Tesla", required = true)
    private String marca;

    @Schema(description = "Modelo do veículo elétrico", example = "Model X", required = true)
    private String modelo;

    @Schema(description = "Ano do veículo elétrico", example = "2023", required = true)
    private Integer ano;

    @Schema(description = "Consumo médio do veículo elétrico em kWh/100km", example = "18.5", required = true)
    private Double consumoMedio;

    @Schema(description = "Autonomia do veículo elétrico em quilômetros", example = "500.0", required = true)
    private Double autonomia;

    @Schema(description = "Custo de recarga da bateria do veículo elétrico em reais", example = "75.0", required = true)
    private Double custoRecargaBateria;

    @Schema(description = "Emissão de CO2 do veículo elétrico em kg", example = "0.0", required = true)
    private Double emissaoCO2;

    @Schema(description = "Indicação de interesse em energia solar para recarga", example = "Sim", allowableValues = {"Sim", "Não"}, required = true)
    private String interesseSolar; // Representado por "Sim" ou "Não"

    // Construtor
    public VeiculoEletricoResponseDTO(Long id, String marca, String modelo, Integer ano, Double consumoMedio,
                                      Double autonomia, Double custoRecargaBateria, Double emissaoCO2, String interesseSolar) {
        this.id = id;
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.consumoMedio = consumoMedio;
        this.autonomia = autonomia;
        this.custoRecargaBateria = custoRecargaBateria;
        this.emissaoCO2 = emissaoCO2;
        this.interesseSolar = interesseSolar;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getInteresseSolar() {
        return interesseSolar;
    }

    public void setInteresseSolar(String interesseSolar) {
        this.interesseSolar = interesseSolar;
    }
}
