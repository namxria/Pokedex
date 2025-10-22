package br.univille.poo2.pokemons.dto;

import br.univille.poo2.pokemons.entity.Pokemon;

import java.util.List;
/*
DTO -> Data Transfer Object
Um objeto para transferir dados
Transferência entre camadas do sistema
 */
// record -> criar objetos imutáveis
public record TipoDTO (String nome
                               , List<Pokemon> pokemons) {
}
