package com.example.guilherme.desafiozup.Models;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Reumo: Model para o objeto do tipo "Filme". Possui os atributos retornados pelo JSON ao conultar
 *        a API do OMDb.
 */

public class OMDbModel implements Serializable {

    private String Response;
    private String Title;
    private String Year;
    private String Rated;
    private String Release;
    private String Runtime;
    private String Genre;
    private String Director;
    private String Writer;
    private String Actors;
    private String Plot;
    private String Language;
    private String Country;
    private String Awards;
    private String Poster;
    private ArrayList<OMDbRatingsModel> Ratings;
    private String Metascore;
    private String Production;


    public String getResponse() {
        return Response;
    }

    public void setResponse(String result) {
        Response = result;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title.toUpperCase();
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getRated() {
        return Rated;
    }

    public void setRated(String rated) {
        Rated = rated;
    }

    public String getRelease() {
        return Release;
    }

    public void setRelease(String release) {
        Release = release;
    }

    public String getRuntime() {
        return Runtime;
    }

    public void setRuntime(String runtime) {
        Runtime = runtime;
    }

    public String getGenre() {
        return Genre;
    }

    public void setGenre(String genre) {
        Genre = genre;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public String getAwards() {
        return Awards;
    }

    public void setAwards(String awards) {
        Awards = awards;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public ArrayList<OMDbRatingsModel> getRatings() {

        return Ratings;
    }

    public void setRatings(ArrayList<OMDbRatingsModel> Ratings){
       this.Ratings = Ratings;
    }

    public String getMetascore() {
        return Metascore;
    }

    public void setMetascore(String metascore) {
        Metascore = metascore;
    }

    public String getProduction() {
        return Production;
    }

    public void setProduction(String production) {
        Production = production;
    }
}
