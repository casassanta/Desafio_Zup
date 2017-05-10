package com.example.guilherme.desafiozup.SearchMovie;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Resumo: Interface implementada pelo presenter da SearchMovieActivity
 *
 * Metodos:
 *
 * 1- void getAllMovieBD()
 *    Retorno: void
 *    Parametros: -
 *    Resumo: Gera uma lista com todos os OMDbModel de filmes cadastrados no BD e envia para a GUI
 *
 * 2- void getMovie(String title)
 *    Retorno: void
 *    Parametros:
 *                1- String title
 *                   Recebe o titulo de um filme e procura no banco de dados.
 *                   Caso nao encontra, procura na API e envia para a proxima tela (ResultMovieActivity)
 *                   o objeto OMDbModel contendo o filme pesquisado
 *
 * 3- void getMovieFromOMDbAPI(String title)
 *    Retorno: void
 *    Parametros:
 *               1- String title
 *                 Recebe o titulo de um filme e procura na API do OMDb
 *                 Essa funcao eh chamada pela funcao getMovie da interface, quando o filme nao eh encontrado
 *                 no banco de dados
 */

public interface SearchMoviePresenter {

    void getAllMovieBD();

    void getMovie(String title);

    void getMovieFromOMDbAPI(String title);

}
