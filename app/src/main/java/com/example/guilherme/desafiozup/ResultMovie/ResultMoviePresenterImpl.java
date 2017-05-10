package com.example.guilherme.desafiozup.ResultMovie;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.util.Log;

import com.example.guilherme.desafiozup.Models.OMDbModel;
import com.example.guilherme.desafiozup.SQLite.BDSQLiteOMDb;
import com.example.guilherme.desafiozup.SearchMovie.SearchMovieView;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Resumo: presenter da actitivy ResultMovieActivity
 *
 * Metodos: encontram-se na interface ResultMoviePresenter
 */

public class ResultMoviePresenterImpl implements ResultMoviePresenter {

    private Context ctx;
    private ResultMovieView resultMovie;
    private BDSQLiteOMDb bd;


    public ResultMoviePresenterImpl(Context ctx, ResultMovieView resultMovie, BDSQLiteOMDb bd) {
        this.ctx = ctx;
        this.resultMovie = resultMovie;
        this.bd = bd;
    }

    @Override
    public void saveMovie(OMDbModel movie) {

        //Download poster
        Picasso.with(ctx).load(movie.getPoster()).into(picassoImageTarget(ctx, "Posters", movie.getTitle().toUpperCase() + ".png"));

        ContextWrapper cw = new ContextWrapper(ctx);
        File directory = cw.getDir("Posters", Context.MODE_PRIVATE);
        File myImageFile = new File(directory, movie.getTitle().toUpperCase() + ".png");

        movie.setPoster(myImageFile.getAbsolutePath());

        bd.addMovie(movie);
        resultMovie.showMessageMovieSaved();
    }

    @Override
    public void deleteMovie(String title) {

        // Delete Poster
        ContextWrapper cw = new ContextWrapper(ctx);
        File directory = cw.getDir("Posters", Context.MODE_PRIVATE);
        File myImageFile = new File(directory, title.toUpperCase() + ".png");
        myImageFile.delete();

        bd.deleteMovie(title);
        resultMovie.showMessageMovieRemoved();
    }

    private Target picassoImageTarget(Context context, final String imageDir, final String imageName) {
        ContextWrapper cw = new ContextWrapper(context);
        final File directory = cw.getDir(imageDir, Context.MODE_PRIVATE);
        return new Target() {
            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        final File myImageFile = new File(directory, imageName);
                        FileOutputStream fos = null;
                        try {
                            fos = new FileOutputStream(myImageFile);
                            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fos);
                        } catch (IOException e) {
                            e.printStackTrace();
                        } finally {
                            try {
                                fos.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }).start();
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {
                if (placeHolderDrawable != null) {}
            }
        };
    }
}
