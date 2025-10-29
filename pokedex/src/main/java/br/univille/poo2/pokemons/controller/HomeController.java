package br.univille.poo2.pokemons.controller;

import br.univille.poo2.pokemons.service.PokemonService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    private final PokemonService pokemonService;

    public HomeController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    // Mapeia a url raiz para o arquivo html home.html
    // GET
    @GetMapping
    public String home() {
        // Retorna o nome do arquivo html
        // resources/templates/home.html
        return "index";
    }

    @GetMapping
    @RequestMapping("/criar-pokemons")
    public ModelAndView criarPokemons() {
        pokemonService.adicionarPokemons();
        // Redireciona para a url raiz
        return new ModelAndView("redirect:/pokemons2");
    }

    @GetMapping
    @RequestMapping("/deletar-pokemons")
    public ModelAndView deletarPokemons() {
        pokemonService.deletarTudo();
        // Redireciona para a url raiz
        return new ModelAndView("redirect:/pokemons2");
    }

}
