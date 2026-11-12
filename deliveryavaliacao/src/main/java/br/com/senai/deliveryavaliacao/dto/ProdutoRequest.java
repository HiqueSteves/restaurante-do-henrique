package br.com.senai.deliveryavaliacao.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProdutoRequest {
    @NotNull 
    private Integer restauranteId;

    @NotBlank (message = "Informe o nome do produto")
    private String nome;
    @Min (value = 0, message = "O preço do produto deve ser maior ou igual a zero")
    @NotNull (message = "Informe o preço do produto")
    private BigDecimal preco;

    public ProdutoRequest() {
    }

    public ProdutoRequest(Integer restauranteId, String nome, BigDecimal preco) {
        this.restauranteId = restauranteId;
        this.nome = nome;
        this.preco = preco;
    }

    public Integer getRestauranteId() {
        return restauranteId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }
}
