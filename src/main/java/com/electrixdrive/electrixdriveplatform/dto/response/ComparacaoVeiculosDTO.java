package com.electrixdrive.electrixdriveplatform.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "DTO de resposta para a comparação de veículos.")
public class ComparacaoVeiculosDTO {

    @Schema(description = "Consumo mensal de combustível do veículo atual em litros.", example = "120.5")
    private double consumoCombustivelMensal;

    @Schema(description = "Consumo mensal de energia elétrica do veículo elétrico em kWh.", example = "300.0")
    private double consumoEletricoMensal;

    @Schema(description = "Número de reabastecimentos necessários para o veículo atual.", example = "4")
    private double reabastecimentosNecessarios;

    @Schema(description = "Número de recargas necessárias para o veículo elétrico.", example = "5")
    private double recargasNecessarias;

    @Schema(description = "Emissões de CO2 do veículo atual formatadas.", example = "200 kg de CO2")
    private String emissoesCO2FormatadasVeiculoAtual;

    @Schema(description = "Emissões de CO2 do veículo elétrico formatadas.", example = "50 kg de CO2")
    private String emissoesCO2FormatadasVeiculoEletrico;

    @Schema(description = "Custo mensal de combustível do veículo atual.", example = "800.0")
    private double custoCombustivelMensal;

    @Schema(description = "Custo mensal de recarga do veículo elétrico.", example = "150.0")
    private double custoRecargaMensal;

    @Schema(description = "Economia mensal ao trocar para um veículo elétrico.", example = "650.0")
    private double economiaMensal;

    @Schema(description = "Análise detalhada dos resultados da comparação.", example = "O veículo elétrico proporciona uma economia significativa a longo prazo.")
    private String analiseDetalhada;

    @Schema(description = "Conclusão da comparação de veículos.", example = "A transição para o veículo elétrico é financeiramente vantajosa.")
    private String conclusao;

    // Getters e Setters

    public double getConsumoCombustivelMensal() {
        return consumoCombustivelMensal;
    }

    public void setConsumoCombustivelMensal(double consumoCombustivelMensal) {
        this.consumoCombustivelMensal = consumoCombustivelMensal;
    }

    public double getConsumoEletricoMensal() {
        return consumoEletricoMensal;
    }

    public void setConsumoEletricoMensal(double consumoEletricoMensal) {
        this.consumoEletricoMensal = consumoEletricoMensal;
    }

    public double getReabastecimentosNecessarios() {
        return reabastecimentosNecessarios;
    }

    public void setReabastecimentosNecessarios(double reabastecimentosNecessarios) {
        this.reabastecimentosNecessarios = reabastecimentosNecessarios;
    }

    public double getRecargasNecessarias() {
        return recargasNecessarias;
    }

    public void setRecargasNecessarias(double recargasNecessarias) {
        this.recargasNecessarias = recargasNecessarias;
    }

    public String getEmissoesCO2FormatadasVeiculoAtual() {
        return emissoesCO2FormatadasVeiculoAtual;
    }

    public void setEmissoesCO2FormatadasVeiculoAtual(String emissoesCO2FormatadasVeiculoAtual) {
        this.emissoesCO2FormatadasVeiculoAtual = emissoesCO2FormatadasVeiculoAtual;
    }

    public String getEmissoesCO2FormatadasVeiculoEletrico() {
        return emissoesCO2FormatadasVeiculoEletrico;
    }

    public void setEmissoesCO2FormatadasVeiculoEletrico(String emissoesCO2FormatadasVeiculoEletrico) {
        this.emissoesCO2FormatadasVeiculoEletrico = emissoesCO2FormatadasVeiculoEletrico;
    }

    public double getCustoCombustivelMensal() {
        return custoCombustivelMensal;
    }

    public void setCustoCombustivelMensal(double custoCombustivelMensal) {
        this.custoCombustivelMensal = custoCombustivelMensal;
    }

    public double getCustoRecargaMensal() {
        return custoRecargaMensal;
    }

    public void setCustoRecargaMensal(double custoRecargaMensal) {
        this.custoRecargaMensal = custoRecargaMensal;
    }

    public double getEconomiaMensal() {
        return economiaMensal;
    }

    public void setEconomiaMensal(double economiaMensal) {
        this.economiaMensal = economiaMensal;
    }

    public String getAnaliseDetalhada() {
        return analiseDetalhada;
    }

    public void setAnaliseDetalhada(String analiseDetalhada) {
        this.analiseDetalhada = analiseDetalhada;
    }

    public String getConclusao() {
        return conclusao;
    }

    public void setConclusao(String conclusao) {
        this.conclusao = conclusao;
    }
}
