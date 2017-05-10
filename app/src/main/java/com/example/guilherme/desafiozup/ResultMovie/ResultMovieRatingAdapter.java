package com.example.guilherme.desafiozup.ResultMovie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.guilherme.desafiozup.Models.OMDbRatingsModel;
import com.example.guilherme.desafiozup.R;

import java.util.ArrayList;

/**
 * Created by Guilherme on 08/05/2017.
 *
 * Resumo: Adapter para as linhas da ListView de Ratings da activity ResultMovieActivity
 *         Possuindo dois TextView, um para o atributo "Source" e outro para "Value" do model
 *         OMDbRatingModel
 *
 */

public class ResultMovieRatingAdapter extends ArrayAdapter<OMDbRatingsModel> {

    private final Context context;
    private final ArrayList<OMDbRatingsModel> data;

    public ResultMovieRatingAdapter(Context context, ArrayList<OMDbRatingsModel> data){
        super(context, R.layout.result_movie_list_row, data);
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.result_movie_list_row, parent, false);

        TextView tvRatingSource = (TextView) rowView.findViewById(R.id.tvRatingSource);
        TextView tvRatingValue = (TextView) rowView.findViewById(R.id.tvRatingValue);

        tvRatingSource.setText(data.get(position).getSource());
        tvRatingValue.setText(data.get(position).getValue());


        return rowView;

    }
}
