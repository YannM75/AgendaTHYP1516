package com.app.thyp.agendathyp1516.bean;

/**
 * Created by Yann on 28/12/2015.
 */
public class Room {
    private int id;
    private String nameRoom;

    public Room(int id, String name){
        this.id = id;
        this.nameRoom= name;
    }

    public String getNameRoom(){
        return this.nameRoom;
    }
    public int getId(){
        return this.id;
    }
}
