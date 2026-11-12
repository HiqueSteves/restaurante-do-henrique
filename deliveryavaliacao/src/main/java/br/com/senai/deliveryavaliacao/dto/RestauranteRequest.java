package br.com.senai.deliveryavaliacao.dto;



import jakarta.validation.constraints.NotBlank;

public class RestauranteRequest {
    @NotBlank (message = "Informe o nome do restaurante")
    private String nome;

    @NotBlank (message = "Informe o endereço do restaurante")
    private String endereco;

    public RestauranteRequest() {
    }

    public RestauranteRequest(String nome, String endereco) {
        this.nome = nome;
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }




}
