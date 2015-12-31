package com.app.thyp.agendathyp1516.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.thyp.agendathyp1516.Dictionary;
import com.app.thyp.agendathyp1516.bean.Cours;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.app.thyp.agendathyp1516.bdd.MySQLiteAgenda.TABLE_CLASS;

/**
 * Created by Abdelbassit on 30/12/2015.
 */
public class CoursDataSource {

    private SQLiteDatabase database;
    private MySQLiteAgenda dbHelper;
    Dictionary dico;

    private String[] allColumns = { MySQLiteAgenda.CL_ID,
            MySQLiteAgenda.CL_NAME_TEACHER, MySQLiteAgenda.CL_NAME_CLASS, MySQLiteAgenda.CL_DATE};

    public CoursDataSource(Context context) {

        dico = new Dictionary();
        dbHelper = new MySQLiteAgenda(context, Dictionary.NOM_BDD,null, Dictionary.VERSION_BDD);
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

    public Long createClass(Cours myclass) {
        ContentValues values = new ContentValues();
        //values.put(MySQLiteAgenda.CL_ID, 1);
        values.put(MySQLiteAgenda.CL_NAME_CLASS, myclass.getName_class());
        values.put(MySQLiteAgenda.CL_NAME_TEACHER, myclass.getName_teacher());
        values.put(MySQLiteAgenda.CL_DATE, myclass.getDate_class());

        return database.insert(TABLE_CLASS, null, values);
    }

    public Cours getClassByDate(String date){
        try{
            Cursor c = database.rawQuery("SELECT * FROM " + MySQLiteAgenda.TABLE_CLASS + " WHERE " + MySQLiteAgenda.CL_DATE + " = ?", new String[]{date});
            return cursorToUser(c);
        }catch(Exception e){
            Log.e("Error getUserByRawQuery", e.toString());
            return null;
        }
    }

    public List<Cours> getAllClass() {
        List<Cours> cours = new ArrayList<Cours>();

        Cursor cursor = database.query(MySQLiteAgenda.TABLE_CLASS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Cours user = cursorToUser(cursor);
            cours.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return cours;
    }

    private Cours cursorToUser(Cursor cursor) {

        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();

        Cours myclass = null;

        myclass = new Cours(cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return myclass;
    }

    public ArrayList<Cours> getAllElements() {

        ArrayList<Cours> list = new ArrayList<Cours>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + MySQLiteAgenda.TABLE_CLASS;

        try {

            Cursor cursor = database.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Cours obj = new Cours();
                        //only one column
                        obj.setName_class(cursor.getString(0));

                        //you could add additional columns here..

                        list.add(obj);
                    } while (cursor.moveToNext());
                }

            } finally {
                try { cursor.close(); } catch (Exception ignore) {}
            }

        } finally {
            try { database.close(); } catch (Exception ignore) {}
        }

        return list;
    }

}
