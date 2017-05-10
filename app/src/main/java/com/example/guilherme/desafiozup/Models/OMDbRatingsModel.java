package com.example.guilherme.desafiozup.Models;

import java.io.Serializable;

/**
 * Created by Guilherme on 08/05/2017.
 *
 * Resumo: Model para o atributo "Ratings" da classe model de "Filmes".
 *         Um dos atributos retornado pro OMDb, é uma lista de string Ratings que possui
 *         outros dois atributos "Source" e "Value"
 *
 */

public class OMDbRatingsModel implements Serializable{

    private String Source;
    private String Value;

    public OMDbRatingsModel(String Source, String Value) {
        this.Source = Source;
        this.Value = Value;
    }

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getValue() {
        return Value;
    }

    public void setValue(String value) {
        Value = value;
    }
}
