package br.com.fiap.davinciEnergy.model;

public enum Tempo {


    horas("Horas"),
    minutos("Minutos");

    Tempo(String label){this.label=label;}

    private  final String label;

    public String getLabel(){return  label;}
}
