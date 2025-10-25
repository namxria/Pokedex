package br.univille.poo2.pokemons.controller;

import br.univille.poo2.pokemons.dto.CriarPokemonRequest;
import br.univille.poo2.pokemons.dto.EditarPokemonRequest;
import br.univille.poo2.pokemons.entity.Pokemon;
import br.univille.poo2.pokemons.service.PokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * Classe responsável por lidar com as requisições HTTP
 * A classe deve chamar o service para alguma lógica de negócio.
 * Nunca acesse Repository diretamente.
 */
@Controller
@RequestMapping("/pokemons2")
public class PokemonDinamicoController {


    //@Autowired -> Evite utilizar injeção via atributo
    // use via construtor
    private final PokemonService pokemonService;

    /**
     * Injeção de dependencia via construtor
     * @param pokemonService
     */
    public PokemonDinamicoController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("/agrupado-por-tipos")
    public ModelAndView agrupadoPorTipo() {
        var mv = new ModelAndView("pokemon/agrupado_por_tipos");
        var lista = pokemonService.obterTodosAgrupadosPorTipo();
        // Adiciona a lista de pokemons na view com o nome 'pokemons'
        mv.addObject("tipos", lista);
        return mv;
    }


    @GetMapping
    public ModelAndView index() {
        var mv = new ModelAndView("pokemon/lista_dinamica");
        var lista = pokemonService.findAll();
        // Adiciona a lista de pokemons na view com o nome 'pokemons'
        mv.addObject("pokemons", lista);
        return mv;
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable("id") Long id) {
        pokemonService.deletarPeloId(id);
        return "redirect:/pokemons2";
    }

    @GetMapping("/{id}")
    public ModelAndView vizualizar(@PathVariable("id") Long id) {
        var optional = pokemonService.obterPeloId(id);
        if(optional.isEmpty()){
            return new ModelAndView("pokemon/nao_ha_pokemons");
        }
        var mv = new ModelAndView("pokemon/visualizar_pokemon");
        mv.addObject("pokemon", optional.get());
        return mv;
    }

    @GetMapping("/criar")
    public ModelAndView criar() {
        var mv = new ModelAndView("pokemon/novo_pokemon");
        mv.addObject("criarPokemonRequest", new CriarPokemonRequest(null, null, null, null));
        return mv;
    }

    @PostMapping("/criar")
    public ModelAndView criar(@ModelAttribute  CriarPokemonRequest request) {
        ModelAndView mv;
        try {
            var pokemon = pokemonService.criar(request);
            return new ModelAndView("redirect:/pokemons2/"+pokemon.getId());
        } catch (Exception e) {
            mv = new ModelAndView("pokemon/novo_pokemon");
            mv.addObject("criarPokemonRequest", request);
            mv.addObject("erro", e.getMessage());
            return mv;
        }
    }

    @GetMapping("/editar/{id}")
    public ModelAndView editar(@PathVariable("id") Long id) {
        var optional = pokemonService.obterPeloId(id);
        if(optional.isEmpty()){
            return new ModelAndView("pokemon/nao_ha_pokemons");
        }
        var mv = new ModelAndView("pokemon/editar_pokemon");
        var pokemon = optional.get();
        var request = new EditarPokemonRequest(pokemon.getId(), pokemon.getNome(), pokemon.getTipo(), pokemon.getEstagio(), pokemon.getEstagio());
        mv.addObject("editarPokemonRequest", optional.get());
        return mv;
    }
    @PostMapping("/editar")
    public ModelAndView editar(@ModelAttribute EditarPokemonRequest request) {
        ModelAndView mv;
        try {
            var pokemon = pokemonService.editar(request);
            return new ModelAndView("redirect:/pokemons2/"+pokemon.getId());
        } catch (Exception e) {
            mv = new ModelAndView("pokemon/editar_pokemon");
            mv.addObject("editarPokemonRequest", request);
            mv.addObject("erro", e.getMessage());
            return mv;
        }
    }

    @GetMapping
    @RequestMapping("/aleatorio")
    public ModelAndView aleatorio() {
        var optional = pokemonService.obterUmpokemonAleatoriamente();
        // Caso não exista pokemons cadastrados, retorna uma mensagem de erro
        if(optional.isEmpty()){
            return new ModelAndView("pokemon/nao_ha_pokemons");
        }
        // Adiciona o objeto pokemon na view com o nome 'pokemon'
        var mv = new ModelAndView("pokemon/pokemon_aleatorio");
        mv.addObject("pokemon", optional.get());
        return mv;
    }



}
