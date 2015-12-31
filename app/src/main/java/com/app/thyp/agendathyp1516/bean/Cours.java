package com.app.thyp.agendathyp1516.bean;

/**
 * Created by Abdelbassit on 30/12/2015.
 */
public class Cours {
    private String name_class;
    private String name_teacher;
    private String date_class;

    public Cours() {
        super();
    }

    public Cours(String name_class, String name_teacher, String date_class){
        super();
        this.name_class = name_class;
        this.name_teacher= name_teacher;
        this.date_class= date_class;
    }

    @Override
    public String toString() {
        return this.name_class + ". " + this.name_teacher + " [$" + this.date_class + "]";
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
}
