package com.example.guilherme.desafiozup.Retrofit;

import com.example.guilherme.desafiozup.Models.OMDbModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Resumo: sintaxe para requisicao no endpoint usando retrofit
 *
 * Metodos:
 * 1- Call<OMDbModel> getOMDbMovie
 *    Pesquisa um filme no endpoint atraves da chave "t", ou seja, pelo titulo
 */

public interface OMDbService {

    public final static String BASE_URL = "http://www.omdbapi.com/";

    @GET("?")
    Call<OMDbModel> getOMDbMovie(@Query("t") String title);
}