package com.example.guilherme.desafiozup.ResultMovie;

import com.example.guilherme.desafiozup.Models.OMDbModel;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Resumo: interface implementada pelo presenter da activity ResultMovieActivity
 *
 * Metodos:
 *
 * 1- void saveMovie(OMDbModel movie)
 *    Retorno: void
 *    Paramentros:
 *                1- OMDbModel movie
 *                   Recebe como parametro um "Movie" que esta especificado na OMDbModel, com todos os atributos de
 *                   titulo, year, production, poster, etc.
 *    Resumo:
 *                Recebe um elemento do tipo "Movie" que foi passado pela SearchMovieActivity. Podendo ser
 *                um filme pesquisado direto na API OMDb ou um filme que estava cadastrado no BD do usuario.
 *
 *
 *
 * 2- void deleteMovie(String title)
 *    Retorno: void
 *    Parametros:
 *               1- String title
 *                  Recebe uma strint contento o Titulo do filme buscado ou selecionado na SearchMovieActivity
 *                  pelo usuario e deleta o mesmo do banco de dados.
 *    Resumo:
 *                Recebe o titulo de um filme cadastrado no BD e deleta o mesmo do banco e todos os seus dados.
 *
 */

public interface ResultMoviePresenter {

    void saveMovie(OMDbModel movie);

    void deleteMovie(String title);
}
