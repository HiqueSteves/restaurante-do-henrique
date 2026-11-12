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

import br.com.senai.deliveryavaliacao.dto.RestauranteRequest;
import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.service.RestauranteService;
import jakarta.validation.Valid;


@RestController 
@RequestMapping ("/restaurantes")
public class RestauranteController {

    private final RestauranteService restauranteService;

    public RestauranteController(RestauranteService restauranteService) {
        this.restauranteService = restauranteService;
    }

    @PostMapping 
    public ResponseEntity<Restaurante> cadastrarRestaurante(@Valid @RequestBody RestauranteRequest request) {
        Restaurante restaurante = new Restaurante(null, request.getNome(), request.getEndereco());
        Restaurante novoRestaurante = restauranteService.cadastrarRestaurante(restaurante);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(novoRestaurante);

    }

    @GetMapping 
    public ResponseEntity<List<Restaurante>> listarRestaurantes() {
        List<Restaurante> restaurantes = restauranteService.listarRestaurantes();
        return ResponseEntity.ok(restaurantes);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<?> buscarRestaurantePorId(@PathVariable Integer id) {
        
        Optional<Restaurante> restaurante = restauranteService.buscarRestaurantePorId(id);

        if (restaurante.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Restaurante não encontrado"));
        }

        return ResponseEntity.ok(restaurante.get());
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> atualizarRestaurante(@PathVariable Integer id, @Valid @RequestBody RestauranteRequest request) {
        Restaurante restauranteAtualizado = new Restaurante(null, request.getNome(), request.getEndereco());
        Optional<Restaurante> resultado = restauranteService.atualizarRestaurante(id, restauranteAtualizado);

        if (resultado.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Restaurante não encontrado"));
        }

        return ResponseEntity.ok(resultado.get());
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<?> deletarRestaurante(@PathVariable Integer id) {
        boolean deletado = restauranteService.deletarRestaurante(id);

        if (!deletado) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(Map.of("message", "Restaurante não encontrado"));
        }

        return ResponseEntity.noContent().build();
    }
}