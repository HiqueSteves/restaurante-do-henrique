package br.com.senai.deliveryavaliacao.controller;


import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.senai.deliveryavaliacao.dto.ProdutoRequest;
import br.com.senai.deliveryavaliacao.model.Produto;
import br.com.senai.deliveryavaliacao.service.ProdutoService;
import jakarta.validation.Valid;



@RestController 
@RequestMapping ("/produtos")

public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

@PostMapping 
public ResponseEntity<?> cadastrarProduto(@Valid @RequestBody ProdutoRequest request) {
    Optional<Produto> produtoOptional = produtoService.cadastrarProduto(
            request.getRestauranteId(),
            request.getNome(),
            request.getPreco());

    if (produtoOptional.isEmpty()) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(Map.of("message", "Restaurante não encontrado"));
    }

    return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(produtoOptional.get());
}

@GetMapping 
public ResponseEntity<List<Produto>> listarProdutos() {
    List<Produto> produtos = produtoService.listarProdutos();
    return ResponseEntity.ok(produtos);
    }

@GetMapping ("/{id}")
public ResponseEntity<Produto> buscarProdutoPorId(@PathVariable Integer id) { 
    Optional<Produto> produto = produtoService.buscarProdutoPorId(id);   

    if (produto.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(produto.get());
 }


@PutMapping ("/{id}")
public ResponseEntity<Produto> atualizarProduto(@PathVariable Integer id, @Valid @RequestBody ProdutoRequest request) {
    Produto produtoAtualizado = new Produto(null, request.getNome(), request.getPreco(), null);
    Optional<Produto> resultado = produtoService.atualizarProduto(id, produtoAtualizado);

    if (resultado.isEmpty()) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.ok(resultado.get());
}

@DeleteMapping ("/{id}")
public ResponseEntity<Void> deletarProduto(@PathVariable Integer id) {
    boolean deletado = produtoService.deletarProduto(id);

    if (!deletado) {
        return ResponseEntity.notFound().build();
    }

    return ResponseEntity.noContent().build();
}
}