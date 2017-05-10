package com.example.guilherme.desafiozup.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.guilherme.desafiozup.Models.OMDbModel;
import com.example.guilherme.desafiozup.Models.OMDbRatingsModel;

import java.util.ArrayList;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Resumo: criacao do banco de dados e tabelas para os filmes da aplicacao
 *
 * Metodos:
 * 1- addMovie(OMDbModel data)
 *    Recebe um filme e todos os seus dados, e entao armazena no banco de dados
 *
 * 2- getMovie(String title)
 *    Recebe o titulo de algum filme e entao busca-se o mesmo na tabela de dados, retornando um OMDbModel
 *
 * 3- cursorToMovie(Cursor cursor)
 *    Recebe um cursor com os dados da tabela de filme e transforma as linhas em objetos OMDbModel
 *
 * 4- getAllMovies()
 *    Recupera todos os filmes cadastrados na tabela de movies e retorna um ArrayList de OMDbModel
 *
 * 5- deleteMovie(String title)
 *    Recebe o titulo de um filme a ser deletado da base de dados
 *
 */

public class BDSQLiteOMDb extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "DesafioZup";
    private static final int DATABASE_VERSION = 1;

    public BDSQLiteOMDb(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_TABLE = "CREATE TABLE movies (" +
                "title TEXT PRIMARY KEY," +
                "year TEXT," +
                "rated TEXT," +
                "release TEXT," +
                "runtime TEXT," +
                "genre TEXT," +
                "director TEXT," +
                "writer TEXT," +
                "actors TEXT," +
                "plot TEXT,"+
                "language TEXT," +
                "country TEXT," +
                "awards TEXT," +
                "poster TEXT," +
                "metascore TEXT," +
                "production TEXT)";

        db.execSQL(CREATE_TABLE);

        CREATE_TABLE = "CREATE TABLE moviesRating (" +
                "title TEXT," +
                "source TEXT," +
                "value TEXT, " +
                "PRIMARY KEY(title, source))";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS movies");
        this.onCreate(db);
    }


    public long addMovie(OMDbModel data){
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("title", data.getTitle().toUpperCase());
        values.put("year", data.getYear());
        values.put("rated", data.getRated());
        values.put("release", data.getRelease());
        values.put("runtime", data.getRuntime());
        values.put("genre", data.getGenre());
        values.put("director", data.getDirector());
        values.put("writer", data.getWriter());
        values.put("actors", data.getActors());
        values.put("plot", data.getPlot());
        values.put("language", data.getLanguage());
        values.put("country", data.getCountry());
        values.put("awards", data.getAwards());
        values.put("poster", data.getPoster());
        values.put("metascore", data.getMetascore());
        values.put("production", data.getProduction());

        long i = db.insert("movies", null, values);

        if (data.getRatings().size() > 0 ){
            for (OMDbRatingsModel rating : data.getRatings()){
                values.clear();

                values.put("title", data.getTitle().toUpperCase());
                values.put("source", rating.getSource());
                values.put("value", rating.getValue());

                db.insert("moviesRating", null, values);
            }
        }


        db.close();
        return i;
    }

    public OMDbModel getMovie(String title){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursorData = db.query("movies",
                new String[] {"title", "year", "rated", "release", "runtime", "genre", "director", "writer", "actors", "plot", "language", "country", "awards", "poster", "metascore", "production"},
                "title = ?",
                new String[] {title}, null, null, null, null);

        Cursor cursorRating = db.query("moviesRating",
                new String[] {"source", "value"},
                "title = ?",
                new String[] {title}, null, null, null, null);

        if (cursorData.isAfterLast()){
            return null;
        }else {
            cursorData.moveToFirst();
            cursorRating.moveToFirst();
            OMDbModel data = cursorToMovie(cursorData, cursorRating);
            return data;
        }
    }


    private OMDbModel cursorToMovie(Cursor cursorData, Cursor cursorRating){

        OMDbModel data = new OMDbModel();

        data.setTitle(cursorData.getString(0));
        data.setYear(cursorData.getString(1));
        data.setRated(cursorData.getString(2));
        data.setRelease(cursorData.getString(3));
        data.setRuntime(cursorData.getString(4));
        data.setGenre(cursorData.getString(5));
        data.setDirector(cursorData.getString(6));
        data.setWriter(cursorData.getString(7));
        data.setActors(cursorData.getString(8));
        data.setPlot(cursorData.getString(9));
        data.setLanguage(cursorData.getString(11));
        data.setCountry(cursorData.getString(11));
        data.setAwards(cursorData.getString(12));
        data.setPoster(cursorData.getString(13));
        data.setMetascore(cursorData.getString(14));
        data.setProduction(cursorData.getString(15));

        ArrayList<OMDbRatingsModel> list = new ArrayList<OMDbRatingsModel>();

        cursorRating.moveToFirst();
        if (!cursorRating.isAfterLast()) {
            do {
                list.add(new OMDbRatingsModel(cursorRating.getString(0), cursorRating.getString(1)));
            } while (cursorRating.moveToNext());
        }

        data.setRatings(list);

        return data;
    }



    public ArrayList<OMDbModel> getAllMovies(){
        ArrayList<OMDbModel> listMovies = new ArrayList<OMDbModel>();

        String query = "SELECT * FROM movies ORDER BY title";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursorData = db.rawQuery(query, null);

        if (cursorData.moveToFirst()){
            do {
                        Cursor cursorRatings = cursorRatings = db.query("moviesRating",
                        new String[] {"source", "value"},
                        "title = ?",
                        new String[] {cursorData.getString(0)}, null, null, null, null);

                OMDbModel data = cursorToMovie(cursorData, cursorRatings);
                listMovies.add(data);
            } while (cursorData.moveToNext());
        }
        return listMovies;
    }



    public int deleteMovie(String title){
        SQLiteDatabase db = this.getWritableDatabase();

        int i = db.delete("movies", "title = ?", new String[] {title.toUpperCase()});
        db.delete("moviesRating", "title = ?", new String[] {title.toUpperCase()});
        db.close();

        return i;
    }
}
