package br.com.senai.deliveryavaliacao.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.senai.deliveryavaliacao.model.Restaurante;
import br.com.senai.deliveryavaliacao.repository.RestauranteRespository;

@Service 
public class RestauranteService {
    private final RestauranteRespository restauranteRespository;

    public RestauranteService(RestauranteRespository restauranteRespository) {
        this.restauranteRespository = restauranteRespository;
    }

public Restaurante cadastrarRestaurante(Restaurante restaurante) {
    return restauranteRespository.save(restaurante);
    }

    public List<Restaurante> listarRestaurantes() {
        return restauranteRespository.findAll();
    }

    public Optional<Restaurante> buscarRestaurantePorId(Integer id) {
        return restauranteRespository.findById(id);
    }

    public Optional<Restaurante> atualizarRestaurante(Integer id, Restaurante restauranteAtualizado) {
        Optional<Restaurante> restauranteExistente = restauranteRespository.findById(id);

        if (restauranteExistente.isEmpty()) {
            return Optional.empty(); 
        }

        Restaurante restaurante = restauranteExistente.get();

        restaurante.setNome(restauranteAtualizado.getNome());
        restaurante.setEndereco(restauranteAtualizado.getEndereco());

        return Optional.of(restauranteRespository.save(restaurante));
    }

    public boolean deletarRestaurante(Integer id) {
        if (!restauranteRespository.existsById(id)) {
            return false; 
        }

        restauranteRespository.deleteById(id);
        return true;
    }
}
