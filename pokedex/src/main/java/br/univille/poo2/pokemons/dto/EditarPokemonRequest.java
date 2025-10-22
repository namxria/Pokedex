package br.univille.poo2.pokemons.dto;

public record EditarPokemonRequest(Long id, String nome, String tipo, String estagio, String fotoUrl) {
}
