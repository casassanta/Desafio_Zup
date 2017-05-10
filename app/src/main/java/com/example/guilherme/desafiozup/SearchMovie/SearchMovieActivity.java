package com.example.guilherme.desafiozup.SearchMovie;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.guilherme.desafiozup.Models.OMDbModel;
import com.example.guilherme.desafiozup.R;
import com.example.guilherme.desafiozup.ResultMovie.ResultMovieActivity;
import com.example.guilherme.desafiozup.SQLite.BDSQLiteOMDb;

import java.util.ArrayList;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Resumo: Tela com a lista dos filmes adicionados pelo usuario ao BD e para pesquisas no OMDb
 *
 * Metodos: ler documentacao no arquivo da interface (SeachMovieView)
 *
 */

public class SearchMovieActivity extends AppCompatActivity implements SearchMovieView {

    private Button btnFilmePresquisar;
    private EditText txtFilmeTitulo;
    private ListView listFilme;
    private TextView lbMinhaLista;

    private ProgressDialog searchProgress;
    private BDSQLiteOMDb bd;
    private SearchMoviePresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        btnFilmePresquisar = (Button) findViewById(R.id.btnFilmePesquisar);
        txtFilmeTitulo = (EditText) findViewById(R.id.txtFilmeTitulo);
        listFilme = (ListView) findViewById(R.id.listFilme);
        lbMinhaLista = (TextView) findViewById(R.id.lbMinhaLista);

        searchProgress = new ProgressDialog(SearchMovieActivity.this);
        bd = new BDSQLiteOMDb(this);
        presenter = new SearchMoviePresenterImpl(this, bd);

        listFilme.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvFilmeTitulo = (TextView) view.findViewById(R.id.tvFilmeTitulo);
                presenter.getMovie(tvFilmeTitulo.getText().toString().toUpperCase());
            }
        });

        presenter.getAllMovieBD();
    }

    @Override
    public void onResume(){
        super.onResume();
        txtFilmeTitulo.setText("");
        presenter.getAllMovieBD();
    }


    public void onClickBtnSearch(View view) {
        presenter.getMovie(txtFilmeTitulo.getText().toString().toUpperCase());
    }


    @Override
    public void showMessageInternetError() {
        Toast.makeText(this, "Internet error", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessageMovieNotFound() {
        Toast.makeText(this, "Movie not found", Toast.LENGTH_LONG).show();
    }

    @Override
    public void showProgressSearching() {
        searchProgress.setTitle("Searching movie...");
        searchProgress.show();
    }

    @Override
    public void hideProgressSearching() {
        searchProgress.hide();
    }



    @Override
    public void updateListViewMovies(ArrayList<OMDbModel> list) {

        ArrayAdapter adapter = new SearchMovieAdapter(this, list);
        listFilme.setAdapter(adapter);
    }

    @Override
    public void goToResultMovieActivity(OMDbModel movie, Boolean flagFromBD) {

        Intent intent = new Intent(SearchMovieActivity.this, ResultMovieActivity.class);
        intent.putExtra("movie", movie);
        intent.putExtra("flagFromBD", flagFromBD);
        startActivity(intent);
    }
}
