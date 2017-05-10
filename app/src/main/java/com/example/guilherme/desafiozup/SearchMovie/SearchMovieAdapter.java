package com.example.guilherme.desafiozup.SearchMovie;

import android.content.Context;
import android.content.ContextWrapper;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guilherme.desafiozup.Models.OMDbModel;
import com.example.guilherme.desafiozup.R;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Guilherme on 07/05/2017.
 *
 * Resumo: Adapter para as linhas do ListView de filmes cadastrados no BD do usuario
 *         Cada elemento contem uma imagem (poste do filme) e tres textview (titulo, ano, genero)
 *
 */

public class SearchMovieAdapter extends ArrayAdapter<OMDbModel> {

    private final Context context;
    private final ArrayList<OMDbModel> data;

    public SearchMovieAdapter(Context context, ArrayList<OMDbModel> data){
        super(context, R.layout.search_movie_list_row, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.search_movie_list_row, parent, false);

        TextView tvFilmeTitulo = (TextView) rowView.findViewById(R.id.tvFilmeTitulo);
        TextView tvFilmeGenero = (TextView) rowView.findViewById(R.id.tvFilmeGenero);
        TextView tvFilmeAno = (TextView) rowView.findViewById(R.id.tvFilmeAno);
        ImageView imgFilmeIcon = (ImageView) rowView.findViewById(R.id.imgFilmeIcone);

        tvFilmeTitulo.setText(data.get(position).getTitle());
        tvFilmeGenero.setText(data.get(position).getGenre());
        tvFilmeAno.setText(data.get(position).getYear());

        Picasso.with(context).load(new File(data.get(position).getPoster())).into(imgFilmeIcon);


        return rowView;

    }
}
