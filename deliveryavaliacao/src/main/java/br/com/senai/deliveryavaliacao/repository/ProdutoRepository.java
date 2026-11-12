package br.com.senai.deliveryavaliacao.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.senai.deliveryavaliacao.model.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {
    
}
