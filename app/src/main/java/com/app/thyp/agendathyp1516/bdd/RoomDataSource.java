package com.app.thyp.agendathyp1516.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.thyp.agendathyp1516.Dictionary;
import com.app.thyp.agendathyp1516.bean.Room;

import java.sql.SQLException;

/**
 * Created by Yann on 28/12/2015.
 */
public class RoomDataSource {

    private SQLiteDatabase database;
    private MySQLiteAgenda dbHelper;
    Dictionary dico;

    private String[] allColumns = { MySQLiteAgenda.CL_ID,
            MySQLiteAgenda.CL_NUMBER_ROOM };

    public RoomDataSource(Context context) {

        dico = new Dictionary();

        dbHelper = new MySQLiteAgenda(context, dico.NOM_BDD,null, dico.VERSION_BDD);
    }
    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }
    public void close(){
        database.close();
    }
    public SQLiteDatabase getBDD(){
        return database;
    }
    public Long createUsers(Room classRoom) {
        ContentValues values = new ContentValues();
        //values.put(MySQLiteAgenda.CL_ID, 1);
        values.put(MySQLiteAgenda.CL_NUMBER_ROOM, classRoom.getNameRoom());
        values.put(MySQLiteAgenda.CL_RIGHTS, classRoom.getId());

        return database.insert(MySQLiteAgenda.TABLE_CLASSROOMS, null, values);
    }
    public Room getRoomByID(int ID){
        return null;
    }
}
