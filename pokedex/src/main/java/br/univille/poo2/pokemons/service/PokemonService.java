package br.univille.poo2.pokemons.service;

import br.univille.poo2.pokemons.dto.TipoDTO;
import br.univille.poo2.pokemons.dto.CriarPokemonRequest;
import br.univille.poo2.pokemons.dto.EditarPokemonRequest;
import br.univille.poo2.pokemons.entity.Pokemon;
import br.univille.poo2.pokemons.repository.PokemonRepository;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Classe responsável por toda lógica de negócios
 * Esta classe que deve acessar o banco de dados via Repository
 */
@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> findAll() {
        return pokemonRepository.findAll();
    }

    public void adicionarPokemons(){
        var pokemon = new Pokemon();
        pokemon.setNome("Bulbassauro");
        pokemon.setTipo("Planta");
        pokemon.setEstagio("Primeiro");
        pokemon.setFotoUrl("https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/001.png");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Ivyssauro");
        pokemon.setTipo("Planta-Venenoso");
        pokemon.setEstagio("Segundo");
        pokemon.setFotoUrl("https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/002.png");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Venussauro");
        pokemon.setTipo("Planta-Venenoso");
        pokemon.setEstagio("Terceiro");
        pokemon.setFotoUrl("https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/003.png");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Squirtle");
        pokemon.setTipo("Água");
        pokemon.setEstagio("Primeiro");
        pokemon.setFotoUrl("https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/007.png");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Wartortle");
        pokemon.setTipo("Água");
        pokemon.setEstagio("Segundo");
        pokemon.setFotoUrl("https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/008.png");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Blastoise");
        pokemon.setTipo("Água");
        pokemon.setEstagio("Terceiro");
        pokemon.setFotoUrl("https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/009.png");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Carmander");
        pokemon.setTipo("Fogo");
        pokemon.setEstagio("Primeiro");
        pokemon.setFotoUrl("https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/004.png");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Charmeleon");
        pokemon.setTipo("Fogo");
        pokemon.setEstagio("Segundo");
        pokemon.setFotoUrl("https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/005.png");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Charizard");
        pokemon.setTipo("Fogo-Voador");
        pokemon.setEstagio("Terceiro");
        pokemon.setFotoUrl("https://www.pokemon.com/static-assets/content-assets/cms2/img/pokedex/full/006.png");
        pokemonRepository.save(pokemon);

        obterTodosAgrupadosPorTipo();
    }

    public void deletarTudo(){
        pokemonRepository.deleteAll();
    }

    /**
     * Consulta todos os países e escolhe um aleatoriamente
     * Caso não possuir países cadastrados, ele retorna um Optional.empty()
     * @return
     */
    public Optional<Pokemon> obterUmpokemonAleatoriamente() {
        var lista = findAll();
        if(lista.isEmpty()){ return Optional.empty();}
        return Optional.ofNullable(lista.get((int) (Math.random() * lista.size())));
    }


    public List<TipoDTO> obterTodosAgrupadosPorTipo(){
        // List<String>
        var lista = new ArrayList<TipoDTO>();
        // 1 consulta
        var tipos = pokemonRepository.findAllTipos();
        // 7 consultas
        for(var c : tipos){
            var pokemon = pokemonRepository.findAllByTipoOrderByNome(c);
            lista.add(new TipoDTO(c,pokemon));
        }
        return lista;
    }

    public Pokemon criar(CriarPokemonRequest request) {
        StringBuilder builder = new StringBuilder();
        if(Strings.isBlank(request.nome())){
            builder.append("Favor informar o nome.").append("\n");
        }
        if(Strings.isBlank(request.tipo())){
            builder.append("Favor informar o tipo.").append("\n");
        }
        if(Strings.isBlank(request.estagio())){
            builder.append("Favor informar o estagio.").append("\n");
        }
        if(Strings.isBlank(request.fotoUrl())){
            builder.append("Favor informar a url da foto.").append("\n");
        }
        if (!builder.isEmpty()){
            throw new RuntimeException(builder.toString());
        }
        var pokemon = new Pokemon();
        pokemon.setNome(request.nome());
        pokemon.setTipo(request.tipo());
        pokemon.setEstagio(request.estagio());
        pokemon.setFotoUrl(request.fotoUrl());
        return pokemonRepository.save(pokemon);
    }

    public Optional<Pokemon> obterPeloId(Long id) {
        return pokemonRepository.findById(id);
    }

    public Pokemon editar(EditarPokemonRequest request) {
        StringBuilder builder = new StringBuilder();
        if(Strings.isBlank(request.nome())){
            builder.append("Favor informar o nome.").append("\n");
        }
        if(Strings.isBlank(request.tipo())){
            builder.append("Favor informar o tipo.").append("\n");
        }
        if(Strings.isBlank(request.estagio())){
            builder.append("Favor informar o estagio.").append("\n");
        }
        if(Strings.isBlank(request.fotoUrl())){
            builder.append("Favor informar a url da foto.").append("\n");
        }
        if (!builder.isEmpty()){
            throw new RuntimeException(builder.toString());
        }
        var old = pokemonRepository.findById(request.id()).orElseThrow();
        old.setNome(request.nome());
        old.setTipo(request.tipo());
        old.setEstagio(request.estagio());
        old.setFotoUrl(request.fotoUrl());
        return pokemonRepository.save(old);
    }

    public void deletarPeloId(Long id) {
        pokemonRepository.deleteById(id);
    }

}
