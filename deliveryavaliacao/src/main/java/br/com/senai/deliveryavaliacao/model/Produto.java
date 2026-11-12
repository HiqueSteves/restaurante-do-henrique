package br.com.senai.deliveryavaliacao.model;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity 
@Table (name="produto")
public class Produto {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String nome;

    @Column (precision = 10, scale = 2)
    BigDecimal preco;

    @ManyToOne
    @JoinColumn (name = "restaurante_id", nullable = false)
    Restaurante restaurante;

    public Produto() {
    }

    public Produto(Integer id, String nome, BigDecimal preco, Restaurante restaurante) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.restaurante = restaurante;
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

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Restaurante getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(Restaurante restaurante) {
        this.restaurante = restaurante;
    }
}

