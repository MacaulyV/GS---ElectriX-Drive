package com.electrixdrive.electrixdriveplatform.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Schema(description = "Entidade que representa um veículo atual de um usuário.")
@Entity
@Table(name = "veiculos_atuais")
public class VeiculoAtual {

    @Schema(description = "Identificador único do veículo", example = "1", required = true)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Schema(description = "Marca do veículo", example = "Toyota", required = true)
    @NotBlank(message = "Marca é obrigatória")
    private String marca;

    @Schema(description = "Modelo do veículo", example = "Corolla", required = true)
    @NotBlank(message = "Modelo é obrigatório")
    private String modelo;

    @Schema(description = "Ano de fabricação do veículo", example = "2020", required = true)
    @NotNull(message = "Ano é obrigatório")
    private Integer ano;

    @Schema(description = "Tipo de combustível utilizado pelo veículo", example = "Gasolina", required = true)
    @NotBlank(message = "Tipo de combustível é obrigatório")
    private String tipoCombustivel;

    @Schema(description = "Quilometragem mensal percorrida pelo veículo", example = "1500.0", required = true)
    @NotNull(message = "Quilometragem mensal é obrigatória")
    private Double quilometragemMensal;

    @Schema(description = "Custo de combustível por tanque", example = "300.0")
    private Double custoCombustivelPorTanque;

    @Schema(description = "Consumo médio do veículo em km por litro", example = "12.5")
    private Double consumoMedio;

    @Schema(description = "Autonomia do veículo em km por tanque", example = "600.0")
    private Double autonomia;

    @Schema(description = "Emissão de CO2 do veículo em gramas por km", example = "200.0")
    private Double emissaoCO2;

    @Schema(description = "Usuário associado ao veículo")
    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = true)
    private Usuario usuario;

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

    public Double getEmissaoCO2() {
        return emissaoCO2;
    }

    public void setEmissaoCO2(Double emissaoCO2) {
        this.emissaoCO2 = emissaoCO2;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    /**
     * Calcula a emissão de CO2 por litro de combustível.
     *
     * @return Emissão de CO2 por litro de combustível em gramas.
     */
    @Schema(description = "Calcula a emissão de CO2 por litro de combustível", example = "16.0")
    public double getEmissaoCO2PorLitro() {
        if (consumoMedio != null && emissaoCO2 != null && consumoMedio > 0) {
            return emissaoCO2 / consumoMedio;
        }
        return 0; // Retorna 0 caso não seja possível calcular
    }

    /**
     * Calcula o custo de combustível por litro.
     *
     * @return Custo do combustível por litro em reais.
     */
    @Schema(description = "Calcula o custo do combustível por litro", example = "5.0")
    public double getCustoCombustivelPorLitro() {
        if (custoCombustivelPorTanque != null && autonomia != null && autonomia > 0) {
            return custoCombustivelPorTanque / autonomia;
        }
        return 0; // Retorna 0 caso não seja possível calcular
    }
}
