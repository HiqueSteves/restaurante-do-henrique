package br.com.senai.deliveryavaliacao.model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

@Entity 
@Table (name="restaurante")
public class Restaurante {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nome;
    String endereco;

    public Restaurante() {
    }

    public Restaurante(Integer id, String nome, String endereco) {
        this.id = id;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Integer getId() {
        return id;
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
