package br.univille.poo2.pokemons.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Classe responsável por lidar com as requisições HTTP
 * A classe deve chamar o service para alguma lógica de negócio.
 * Nunca acesse Repository diretamente.
 */
@Controller
@RequestMapping("/pokemons")
public class PokemonController {

    @GetMapping
    public ModelAndView index() {
        var mv = new ModelAndView("pokemon/lista_fixa");
        return mv;
    }
}
