package com.electrixdrive.electrixdriveplatform.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de requisição para dados do veículo elétrico.")
public class VeiculoEletricoRequestDTO {

    @Schema(description = "Marca do veículo elétrico", example = "Tesla", required = true)
    private String marca;

    @Schema(description = "Modelo do veículo elétrico", example = "Model 3", required = true)
    private String modelo;

    @Schema(description = "Ano do veículo elétrico", example = "2023", required = true)
    private Integer ano;

    @Schema(description = "Interesse em energia solar para carregamento", example = "Sim", allowableValues = {"Sim", "Não"}, required = true)
    private String interesseSolar; // Representado por "Sim" ou "Não"

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

    public String getInteresseSolar() {
        return interesseSolar;
    }

    public void setInteresseSolar(String interesseSolar) {
        this.interesseSolar = interesseSolar;
    }
}
