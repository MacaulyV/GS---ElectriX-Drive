package com.electrixdrive.electrixdriveplatform.dto.input;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de entrada para os dados do veículo atual.")
public class VeiculoAtualInputDTO {

    @Schema(description = "Marca do veículo", example = "Toyota", required = true)
    @NotBlank(message = "Marca é obrigatória")
    private String marca;

    @Schema(description = "Modelo do veículo", example = "Corolla", required = true)
    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;

    @Schema(description = "Ano do veículo", example = "2020", required = true)
    @NotNull(message = "Ano é obrigatório")
    private Integer ano;

    @Schema(description = "Tipo de combustível do veículo", example = "Gasolina", required = true)
    @NotBlank(message = "Tipo de combustível é obrigatório")
    private String tipoCombustivel;

    @Schema(description = "Quilometragem mensal percorrida pelo veículo", example = "1500.0", required = true)
    @NotNull(message = "Quilometragem mensal é obrigatória")
    private Double quilometragemMensal;

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
}
