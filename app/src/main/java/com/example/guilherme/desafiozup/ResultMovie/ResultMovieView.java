package com.example.guilherme.desafiozup.ResultMovie;

import com.example.guilherme.desafiozup.Models.OMDbModel;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Resumo: interface implementada pela ResultMovieActivity para exibicao de dados na GUI
 *
 * Metodos:
 *
 * 1- void showMessageMovieSaved()
 *    Retorno: void
 *    Parametros: -
 *    Resumo: Exibe uma mensagem (Toask) de que o filme foi salvo na lista de filmes do usuario.
 *
 * 2- void showMessageMovieRemoved()
 *    Retorno: void
 *    Parametros: -
 *    Resumo: Exibe uma mensagem (Toask) de que o filme foi removido da lista de filmes do usuario.
 *
 * 3- void showMovieData(OMDbModel movie)
 *    Retorno: void
 *    Parametros:
 *                1- OMDbModel movie
 *                   Recebe o objeto "movie" da SearchMovieActivity e preenche todos os campos da ResultMovieActivity
 *                   com os respectivos atributos do model movie.
 *
 * 4- void swapBtnAction(Boolean isFromBD)
 *    Retorno: void
 *    Parametros:
 *                1- Boolean isFromBD
 *                   Recebe um boolean (True/Fase) sinalizando se o filme recebido veio do banco de dados
 *                   ou se veio da API OMDb
 *    Resumo:
 *            Sinaliza qual a acao/texto do botao de Salvar/Deletar. Se a flag for verdade, o filme veio do BD
 *            entao o botao apenas deleta o filme, caso contrario o filme veio da API e ele pode salvar o filme no BD
 *
 */

public interface ResultMovieView {

    void showMessageMovieSaved();
    void showMessageMovieRemoved();

    void showMovieData(OMDbModel movie);

    void swapBtnAction(Boolean isFromBD);

}
