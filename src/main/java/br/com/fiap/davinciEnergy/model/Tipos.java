package br.com.fiap.davinciEnergy.model;

public enum Tipos {

    arCondicionado("Ar Condicionado"),
    fogao("Fogão"),
    microondas("Micro-ondas"),
    fornoEletrico("Forno Elétrico"),
    Lampada("Lampada"),
    lavadorRoupa(" lavador de roupa"),
    refrigerador("Refrigerador"),
    televisor("Televisor"),
    ventilador("Ventilador");

    Tipos(String label){this.label=label;}

    private  final String label;

    public String getLabel(){return  label;}
}

