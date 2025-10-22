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
        pokemon.setNome("Bubassauro");
        pokemon.setTipo("Planta");
        pokemon.setEstagio("Primeiro");
        pokemon.setFotoUrl("https://upload.wikimedia.org/wikipedia/commons/1/1a/Flag_of_Argentina.svg");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Ivyssauro");
        pokemon.setTipo("Planta-Venenoso");
        pokemon.setEstagio("Segundo");
        pokemon.setFotoUrl("https://upload.wikimedia.org/wikipedia/commons/1/1a/Flag_of_Argentina.svg");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Venussauro");
        pokemon.setTipo("Planta-Venenoso");
        pokemon.setEstagio("Terceiro");
        pokemon.setFotoUrl("https://upload.wikimedia.org/wikipedia/commons/1/1a/Flag_of_Argentina.svg");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Squirtle");
        pokemon.setTipo("Água");
        pokemon.setEstagio("Primeiro");
        pokemon.setFotoUrl("https://upload.wikimedia.org/wikipedia/commons/1/1a/Flag_of_Argentina.svg");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Wartortle");
        pokemon.setTipo("Água");
        pokemon.setEstagio("Segundo");
        pokemon.setFotoUrl("https://upload.wikimedia.org/wikipedia/commons/1/1a/Flag_of_Argentina.svg");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Blastoise");
        pokemon.setTipo("Água");
        pokemon.setEstagio("Terceiro");
        pokemon.setFotoUrl("https://upload.wikimedia.org/wikipedia/commons/1/1a/Flag_of_Argentina.svg");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Carmander");
        pokemon.setTipo("Fogo");
        pokemon.setEstagio("Primeiro");
        pokemon.setFotoUrl("https://upload.wikimedia.org/wikipedia/commons/1/1a/Flag_of_Argentina.svg");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Charmeleon");
        pokemon.setTipo("Fogo");
        pokemon.setEstagio("Segundo");
        pokemon.setFotoUrl("https://upload.wikimedia.org/wikipedia/commons/1/1a/Flag_of_Argentina.svg");
        pokemonRepository.save(pokemon);

        pokemon = new Pokemon();
        pokemon.setNome("Charizard");
        pokemon.setTipo("Fogo-Voador");
        pokemon.setEstagio("Terceiro");
        pokemon.setFotoUrl("https://upload.wikimedia.org/wikipedia/commons/1/1a/Flag_of_Argentina.svg");
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


    public List<TipoDTO> obterTodosAgrupadosPorContinente(){
        // List<String>
        var lista = new ArrayList<TipoDTO>();
        // 1 consulta
        var continentes = pokemonRepository.findAllContinentes();
        // 7 consultas
        for(var c : continentes){
            var pokemon = pokemonRepository.findAllByContinenteOrderByNome(c);
            lista.add(new TipoDTO(c,pokemon));
        }
        return lista;
    }

    public Pokemon criar(CriarPokemonRequest request) {
        StringBuilder builder = new StringBuilder();
        if(Strings.isBlank(request.nome())){
            builder.append("Favor informar o nome.").append("\n");
        }
        if(Strings.isBlank(request.capital())){
            builder.append("Favor informar a capital.").append("\n");
        }
        if(Strings.isBlank(request.continente())){
            builder.append("Favor informar o continente.").append("\n");
        }
        if(Strings.isBlank(request.idioma())){
            builder.append("Favor informar o idioma.").append("\n");
        }
        if(Strings.isBlank(request.bandeiraUrl())){
            builder.append("Favor informar a url da bandeira.").append("\n");
        }
        if (!builder.isEmpty()){
            throw new RuntimeException(builder.toString());
        }
        var pokemon = new pokemon();
        pokemon.setNome(request.nome());
        pokemon.setCapital(request.capital());
        pokemon.setContinente(request.continente());
        pokemon.setBandeiraUrl(request.bandeiraUrl());
        pokemon.setIdioma(request.idioma());
        return pokemonRepository.save(pokemon);
    }

    public Optional<pokemon> obterPeloId(Long id) {
        return pokemonRepository.findById(id);
    }

    public pokemon editar(EditarpokemonRequest request) {
        StringBuilder builder = new StringBuilder();
        if(Strings.isBlank(request.nome())){
            builder.append("Favor informar o nome.").append("\n");
        }
        if(Strings.isBlank(request.capital())){
            builder.append("Favor informar a capital.").append("\n");
        }
        if(Strings.isBlank(request.continente())){
            builder.append("Favor informar o continente.").append("\n");
        }
        if(Strings.isBlank(request.idioma())){
            builder.append("Favor informar o idioma.").append("\n");
        }
        if(Strings.isBlank(request.bandeiraUrl())){
            builder.append("Favor informar a url da bandeira.").append("\n");
        }
        if (!builder.isEmpty()){
            throw new RuntimeException(builder.toString());
        }
        var old = pokemonRepository.findById(request.id()).orElseThrow();
        old.setNome(request.nome());
        old.setCapital(request.capital());
        old.setContinente(request.continente());
        old.setBandeiraUrl(request.bandeiraUrl());
        old.setIdioma(request.idioma());
        return pokemonRepository.save(old);
    }

    public void deletarPeloId(Long id) {
        pokemonRepository.deleteById(id);
    }
}
