package br.univille.poo2.pokemons.repository;

import br.univille.poo2.pokemons.entity.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * Classe responsável por manipular os dados do banco de dados
 * Consultas (com filtros), inserções/updates e deleção
 */
public interface PokemonRepository extends JpaRepository<Pokemon,Long> {


    @Query(value = "select distinct tipo from pokemon order by TRANSLATE(tipo, 'ÁÉÍÓÚÂÊÎÔÛÃÕÀÈÌÒÙÇáéíóúâêîôûãõàèìòùç', 'AEIOUAEIOUAEOUCaeiouaeiouaoaeiouc')", nativeQuery = true)
    List<String> findAllTipos();

    List<Pokemon> findAllByTipoOrderByNome(String tipo);

}
