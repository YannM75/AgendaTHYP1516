package com.app.thyp.agendathyp1516.bean;

/**
 * Created by Abdelbassit on 30/12/2015.
 */
public class Cours {
    public String name_class;
    public String name_teacher;
    public String date_class;
    public String Heure;
    public String Salle;

    public Cours() {
        super();
    }

    public Cours(String name_class, String name_teacher, String date_class, String Heure, String Salle){
        super();
        this.name_class = name_class;
        this.name_teacher= name_teacher;
        this.date_class= date_class;
        this.Heure= Heure;
        this.Salle= Salle;
    }

    @Override
    public String toString() {
        return name_class;
    }

    public String getName_class(){
        return this.name_class;
    }

    public void setName_class(String name_class){
        this.name_class = name_class;
    }

    public String getName_teacher(){
        return this.name_teacher;
    }
    public String getDate_class(){
        return this.date_class;
    }
    public String getHeure(){
        return this.Heure;
    }
    public String getSalle(){
        return this.Salle;
    }
}
