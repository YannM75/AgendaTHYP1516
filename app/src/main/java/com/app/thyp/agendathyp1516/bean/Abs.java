package com.app.thyp.agendathyp1516.bean;

/**
 * Created by Abdelbassit on 01/01/2016.
 */
public class Abs {private String nom_prof;
    private String date_abs;
    private String motif;

    public Abs() {
        super();
    }

    public Abs(String nom_prof, String date_abs, String motif){
        super();
        this.nom_prof = nom_prof;
        this.date_abs= date_abs;
        this.motif= motif;
    }

    @Override
    public String toString() {
        return this.nom_prof + ". " + this.date_abs + " [$" + this.motif + "]";
    }

    public String getName_Prof(){
        return this.nom_prof;
    }

    public void setName_Prof(String nom_prof){
        this.nom_prof = nom_prof;
    }

    public String getDate_Abs(){
        return this.date_abs;
    }
    public String getMotif(){
        return this.motif;
    }
}
