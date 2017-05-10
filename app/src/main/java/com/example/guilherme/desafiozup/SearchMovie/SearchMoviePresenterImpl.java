package com.example.guilherme.desafiozup.SearchMovie;

import com.example.guilherme.desafiozup.Models.OMDbModel;
import com.example.guilherme.desafiozup.Retrofit.OMDbService;
import com.example.guilherme.desafiozup.Retrofit.OMDbServiceGenerator;
import com.example.guilherme.desafiozup.SQLite.BDSQLiteOMDb;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Resumo: presenter da actitivy SearchMoviePresenter
 *
 * Metodos: encontram-se na interface SearchMoviePresenter
 *
 */

public class SearchMoviePresenterImpl implements SearchMoviePresenter {

    private SearchMovieView searchMovie;
    private BDSQLiteOMDb bd;

    public SearchMoviePresenterImpl(SearchMovieView searchMovie, BDSQLiteOMDb bd) {
        this.searchMovie = searchMovie;
        this.bd = bd;
    }

    @Override
    public void getAllMovieBD() {
        ArrayList<OMDbModel> list = bd.getAllMovies();
        searchMovie.updateListViewMovies(list);
    }

    @Override
    public void getMovie(String title) {

        OMDbModel movie = bd.getMovie(title);

        if (movie == null){
            //Procura na API do OMDb
            getMovieFromOMDbAPI(title);
        }else{
            //Vai para a proxima tela passando o filme encontrado.
            searchMovie.goToResultMovieActivity(movie, true);
        }
    }

    @Override
    public void getMovieFromOMDbAPI(String title) {
        searchMovie.showProgressSearching();

        OMDbService service = OMDbServiceGenerator.createService(OMDbService.class);
        Call<OMDbModel> call = service.getOMDbMovie(title);

        call.enqueue(new Callback<OMDbModel>() {
            @Override
            public void onResponse(Call<OMDbModel> call, Response<OMDbModel> response) {

                if (!response.isSuccessful()){ //Nao obteve resposta
                    searchMovie.hideProgressSearching();
                    searchMovie.showMessageMovieNotFound();
                }
                else{  //Sucesso de resposta (vazia ou com os dados)
                    searchMovie.hideProgressSearching();

                    OMDbModel data = response.body();

                    if (data.getResponse().trim().equals("False")) { //Titulo NAO encontrado
                        searchMovie.showMessageMovieNotFound();

                    }else { // Titulo encontrado

                        //downloadMoviePoster(data.Poster, data.Title);
                        searchMovie.goToResultMovieActivity(data, false);
                    }

                }
            }

            @Override
            public void onFailure(Call<OMDbModel> call, Throwable t) {
                searchMovie.hideProgressSearching();
                searchMovie.showMessageInternetError();
            }
        });
    }
}
