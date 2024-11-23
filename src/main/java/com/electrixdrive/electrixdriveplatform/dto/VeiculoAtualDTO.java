package com.electrixdrive.electrixdriveplatform.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO para os dados do veículo atual.")
public class VeiculoAtualDTO {

    @Schema(description = "Marca do veículo atual", example = "Toyota", required = true)
    private String marca;

    @Schema(description = "Modelo do veículo atual", example = "Corolla", required = true)
    private String modelo;

    @Schema(description = "Ano de fabricação do veículo atual", example = "2020", required = true)
    private Integer ano;

    @Schema(description = "Tipo de combustível utilizado pelo veículo", example = "Gasolina", required = true)
    private String tipoCombustivel;

    @Schema(description = "Quilometragem mensal percorrida pelo veículo", example = "1500.0", required = true)
    private Double quilometragemMensal;

    @Schema(description = "Custo de combustível por tanque do veículo", example = "350.0")
    private Double custoCombustivelPorTanque;

    @Schema(description = "Consumo médio do veículo em quilômetros por litro", example = "12.5")
    private Double consumoMedioKmL;

    @Schema(description = "Autonomia do veículo em quilômetros", example = "600.0")
    private Double autonomiaKm;

    @Schema(description = "Emissão de CO2 do veículo em gramas por quilômetro", example = "200.0")
    private Double emissaoCO2gKm;

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

    public String getTipoCombustivel() {
        return tipoCombustivel;
    }

    public void setTipoCombustivel(String tipoCombustivel) {
        this.tipoCombustivel = tipoCombustivel;
    }

    public Double getQuilometragemMensal() {
        return quilometragemMensal;
    }

    public void setQuilometragemMensal(Double quilometragemMensal) {
        this.quilometragemMensal = quilometragemMensal;
    }

    public Double getCustoCombustivelPorTanque() {
        return custoCombustivelPorTanque;
    }

    public void setCustoCombustivelPorTanque(Double custoCombustivelPorTanque) {
        this.custoCombustivelPorTanque = custoCombustivelPorTanque;
    }

    public Double getConsumoMedioKmL() {
        return consumoMedioKmL;
    }

    public void setConsumoMedioKmL(Double consumoMedioKmL) {
        this.consumoMedioKmL = consumoMedioKmL;
    }

    public Double getAutonomiaKm() {
        return autonomiaKm;
    }

    public void setAutonomiaKm(Double autonomiaKm) {
        this.autonomiaKm = autonomiaKm;
    }

    public Double getEmissaoCO2gKm() {
        return emissaoCO2gKm;
    }

    public void setEmissaoCO2gKm(Double emissaoCO2gKm) {
        this.emissaoCO2gKm = emissaoCO2gKm;
    }
}
