package br.com.senai.deliveryavaliacao.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.ProdutoRepository;
import br.com.senai.deliveryavaliacao.repository.RestauranteRespository;

@Service 
public class ProdutoService {
    private final ProdutoRepository produtoRepository;
    private final RestauranteRespository restauranteRespository;

    public ProdutoService(ProdutoRepository produtoRepository, RestauranteRespository restauranteRespository) {
        this.produtoRepository = produtoRepository;
        this.restauranteRespository = restauranteRespository;
    }

    public Optional<Produto> cadastrarProduto(Produto produto) {
        return Optional.of(produtoRepository.save(produto));
    }

    public Optional<Produto> cadastrarProduto(Integer restauranteId, String nome, BigDecimal preco) {
        Optional<Restaurante> restaurante = restauranteRespository.findById(restauranteId);
        if (restaurante.isEmpty()) {
            return Optional.empty();
        }

        Produto produto = new Produto(null, nome, preco, restaurante.get());
        return Optional.of(produtoRepository.save(produto));
    }

    public List<Produto> listarProdutos() {
        return produtoRepository.findAll();
    }

    public Optional<Produto> buscarProdutoPorId(Integer id) {
        return produtoRepository.findById(id);
    }

    public Optional<Produto> atualizarProduto(Integer id, Produto produtoAtualizado) {
        Optional<Produto> produtoExistente = produtoRepository.findById(id);

        if (produtoExistente.isEmpty()) {
            return Optional.empty(); 
        }

        Produto produto = produtoExistente.get();

        produto.setNome(produtoAtualizado.getNome());
        produto.setPreco(produtoAtualizado.getPreco());
        produto.setRestaurante(produtoAtualizado.getRestaurante());

        return Optional.of(produtoRepository.save(produto));
    }

    public boolean deletarProduto(Integer id) {
        if (!produtoRepository.existsById(id)) {
            return false; 
        }

        produtoRepository.deleteById(id);
        return true;
    }
}
