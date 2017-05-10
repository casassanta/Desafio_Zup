package com.example.guilherme.desafiozup.ResultMovie;


import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guilherme.desafiozup.Models.OMDbModel;
import com.example.guilherme.desafiozup.R;
import com.example.guilherme.desafiozup.SQLite.BDSQLiteOMDb;
import com.squareup.picasso.Picasso;

import java.io.File;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Resumo: Tela da exibicao do filme pesquisado pelo usuario.
 *
 * Metodos: ler documentacao no arquivo da interface (ResultMovieView)
 *
 */

public class ResultMovieActivity extends AppCompatActivity implements ResultMovieView {

    private Button btnAction;
    private TextView tvFilmeTitulo, tvFilmeAno, tvFilmeGenero, tvFilmeDuracao, tvFilmeSinopse,
            tvFilmeAtores, tvFilmeDiretor, tvFilmeEscritor, tvMetascore, tvFilmeProducao;
    private TextView lbFilmeGenero, lbFilmeDuracao, lbFilmeSinopse, lbFilmeAtores, lbFilmeDiretor, lbFilmeEscritor, lbRatings,
            lbMetascore, lbFilmeProducao;
    private ImageView imgFilmeIcone;
    private ListView listRatings;
    private RatingBar rbMetascore;

    private OMDbModel movie;
    private Boolean flagFromBD;
    private BDSQLiteOMDb bd;
    private ResultMoviePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_movie);

        Intent intent = getIntent();
        movie = (OMDbModel) intent.getSerializableExtra("movie");
        flagFromBD = (Boolean) intent.getSerializableExtra("flagFromBD");
        btnAction = (Button) findViewById(R.id.btnAction);

        tvFilmeTitulo = (TextView) findViewById(R.id.tvFilmeTitulo);
        tvFilmeGenero = (TextView) findViewById(R.id.tvFilmeGenero);
        tvFilmeAno = (TextView) findViewById(R.id.tvFilmeAno);
        tvFilmeDuracao = (TextView) findViewById(R.id.tvFilmeDuracao);
        tvFilmeSinopse = (TextView) findViewById(R.id.tvFilmeSinopse);
        tvFilmeAtores = (TextView) findViewById(R.id.tvFilmeAtores);
        tvFilmeDiretor = (TextView) findViewById(R.id.tvFilmeDiretor);
        tvFilmeEscritor = (TextView) findViewById(R.id.tvFilmeEscritor);
        tvMetascore = (TextView) findViewById(R.id.tvMetascore);
        tvFilmeProducao = (TextView) findViewById(R.id.tvFilmeProducao) ;

        imgFilmeIcone = (ImageView) findViewById(R.id.imgFilmeIcone);

        listRatings = (ListView) findViewById(R.id.listRatings);

        rbMetascore = (RatingBar) findViewById(R.id.rbMetascore);

        lbFilmeGenero = (TextView) findViewById(R.id.lbFilmeGenero);
        lbFilmeDuracao = (TextView) findViewById(R.id.lbFilmeDuracao);
        lbFilmeSinopse = (TextView) findViewById(R.id.lbFilmeSinopse);
        lbFilmeAtores = (TextView) findViewById(R.id.lbFilmeAtores);
        lbFilmeDiretor = (TextView) findViewById(R.id.lbFilmeDiretor);
        lbFilmeEscritor = (TextView) findViewById(R.id.lbFilmeEscritor);
        lbRatings = (TextView) findViewById(R.id.lbRatings);
        lbMetascore = (TextView) findViewById(R.id.lbMetascore);
        lbFilmeProducao = (TextView) findViewById(R.id.lbFilmeProducao);

        bd = new BDSQLiteOMDb(this);
        presenter = new ResultMoviePresenterImpl(getApplicationContext() ,this, bd);

        swapBtnAction(flagFromBD);
        showMovieData(movie);
    }


    public void onClickBtnAction(View view) {
        if (flagFromBD){
            presenter.deleteMovie(movie.getTitle());
            flagFromBD = false;
        }else {
            presenter.saveMovie(movie);
            flagFromBD = true;
        }
        swapBtnAction(flagFromBD);
    }


    @Override
    public void showMessageMovieSaved() {
        Toast.makeText(this, "Movie Saved", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageMovieRemoved() {
        Toast.makeText(this, "Movie Deleted", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMovieData(OMDbModel movie) {

        tvFilmeTitulo.setText(movie.getTitle());
        tvFilmeGenero.setText(movie.getGenre());
        tvFilmeAno.setText("(" + movie.getYear() + ")");
        tvFilmeDuracao.setText(movie.getRuntime());
        tvFilmeSinopse.setText(movie.getPlot());
        tvFilmeAtores.setText(movie.getActors());
        tvFilmeDiretor.setText(movie.getDirector());
        tvFilmeEscritor.setText(movie.getWriter());
        tvMetascore.setText(movie.getMetascore());
        tvFilmeProducao.setText(movie.getProduction());

        // Tratando metaScore -> ratingBar
        if (!movie.getMetascore().equals("N/A")) rbMetascore.setRating( Float.parseFloat(movie.getMetascore())*50/100/10 );

        // Tratando Postar BD ou Url
        if (flagFromBD) Picasso.with(this).load(new File(movie.getPoster())).into(imgFilmeIcone);
        else Picasso.with(this).load(movie.getPoster()).into(imgFilmeIcone);

        ArrayAdapter adapter = new ResultMovieRatingAdapter(this, movie.getRatings());
        listRatings.setAdapter(adapter);

    }

    @Override
    public void swapBtnAction(Boolean isFromBD) {
        if (isFromBD){
            btnAction.setText("Remove from my list");
            btnAction.setBackgroundColor(Color.parseColor("#FF0000"));
        }else{
            btnAction.setText("Add to my list");
            btnAction.setBackgroundColor(Color.parseColor("#008000"));
        }
    }
}
