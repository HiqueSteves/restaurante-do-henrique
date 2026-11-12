package br.com.senai.deliveryavaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.deliveryavaliacao.model.Restaurante;

public interface RestauranteRespository extends JpaRepository<Restaurante, Integer> {
    
}
