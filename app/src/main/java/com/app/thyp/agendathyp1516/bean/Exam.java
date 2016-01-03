package com.app.thyp.agendathyp1516.bean;

/**
 * Created by M'hamed on 02/01/2016.
 */
public class Exam {

    private String nom_exam;
    private String date_exam;
    private String heure;

    public Exam() {
        super();
    }

    public Exam(String nom_exam, String date_exam, String heure){
        super();
        this.nom_exam = nom_exam;
        this.date_exam= date_exam;
        this.heure= heure;
    }

    @Override
    public String toString() {
        return this.nom_exam + ". " + this.date_exam + " [$" + this.heure + "]";
    }

    public String getNom_exam(){
        return this.nom_exam;
    }

    public void setNom_exam(String nom_exam){
        this.nom_exam = nom_exam;
    }

    public String getDate_exam(){
        return this.date_exam;
    }
    public String getHeure(){
        return this.heure;
    }
}



