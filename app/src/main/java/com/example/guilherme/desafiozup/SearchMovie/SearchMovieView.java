package com.example.guilherme.desafiozup.SearchMovie;

import com.example.guilherme.desafiozup.Models.OMDbModel;

import java.util.ArrayList;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Resumo: Interface implementada pela activity SearchMovieActivity
 *
 * Metodos:
 * 1- void showMessageInternetError()
 *    Retorno: void
 *    Parametros: -
 *    Resumo: exibe uma mensagem (Toask) de problema de conexao ao tentar acessar o OMDb. Provavelmente
 *            o dispositivo esta sem acesso a internet.
 *
 * 2- void showMessageMovieNotFound()
 *    Retorno: void
 *    Parametros: -
 *    Resumo: exibe uma mensagme (Toask) caso nao tenha sido possivel encontra o filme no BD e no OMDb.
 *
 * 3- void showProgressSearching()
 *    Retorno: void
 *    Parametros: -
 *    Resumo: exibe uma mensagem (Progress) de que a aplicação esta pesquisando pelo filme
 *
 * 4- void hideProgressSearching()
 *    Retorno: void
 *    Parametros: -
 *    Resumo: remove a mensagem (Progress) de que a aplicacao esta pesquisando por um filme
 *
 * 5- void updateListViewMovies(ArrayList<OMDbModel> list)
 *    Retorno: void
 *    Parametros:
 *                1- ArrayList<OMDbModel> list
 *                   Recebe uma lista de OMDbModel contendo todos os filmes cadastrados na aplicacao pelo usuario
 *                   e insere eles no listview da activity
 *    Resumo:
 *                Recebe a lista de filme cadastrados no BD e atualiza o list view.
 *
 *
 * 6- viod goToResultMovieActivity(OMDbModel filme)
 *    Retorno: void
 *    Parametros:
 *                1- OMDbModel filme
 *                   Recebe um objetivo do filme OMDbModel contendo todos os dados do filme pesquisado ou selecionado no listview
 *    Reusmo:
 *                Transaciona da activity atual para a activity ResultMovieSearch, passando como parametro o filme selecionad/pesquisado
 *
 */

public interface SearchMovieView {

    void showMessageInternetError();
    void showMessageMovieNotFound();

    void showProgressSearching();
    void hideProgressSearching();

    void updateListViewMovies(ArrayList<OMDbModel> list);

    void goToResultMovieActivity(OMDbModel movie, Boolean flagFromBD);
}
