package com.app.thyp.agendathyp1516.bean;

/**
 * Created by Yann on 23/11/2015.
 */
public class User {
    private String pseudo;
    private int groupe;
    private String passWord;

    public User(String pseudo, String pwd, int groupe){
        this.pseudo = pseudo.trim();
        this.passWord = pwd.trim();
        this.groupe = groupe;
    }

    public String getPseudo() {
        return pseudo;
    }

    public String getPassWord() {

        return passWord;
    }

    public int getGroupe() {
        return groupe;
    }

}
