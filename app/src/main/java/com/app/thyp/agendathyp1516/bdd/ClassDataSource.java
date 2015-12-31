package com.app.thyp.agendathyp1516.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.thyp.agendathyp1516.Dictionary;
import com.app.thyp.agendathyp1516.bean.Class;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.app.thyp.agendathyp1516.bdd.MySQLiteAgenda.TABLE_CLASS;

/**
 * Created by Abdelbassit on 30/12/2015.
 */
public class ClassDataSource {

    private SQLiteDatabase database;
    private MySQLiteAgenda dbHelper;
    Dictionary dico;

    private String[] allColumns = { MySQLiteAgenda.CL_ID,
            MySQLiteAgenda.CL_NAME_TEACHER, MySQLiteAgenda.CL_NAME_CLASS, MySQLiteAgenda.CL_DATE};

    public ClassDataSource(Context context) {

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
    public Long createClass(Class myclass) {
        ContentValues values = new ContentValues();
        //values.put(MySQLiteAgenda.CL_ID, 1);
        values.put(MySQLiteAgenda.CL_NAME_CLASS, myclass.getName_class());
        values.put(MySQLiteAgenda.CL_NAME_TEACHER, myclass.getName_teacher());
        values.put(MySQLiteAgenda.CL_DATE, myclass.getDate_class());

        return database.insert(TABLE_CLASS, null, values);
    }

    public Class getClassByDate(String date){
        try{
            Cursor c = database.rawQuery("SELECT * FROM " + MySQLiteAgenda.TABLE_CLASS + " WHERE " + MySQLiteAgenda.CL_DATE + " = ?", new String[]{date});
            return cursorToUser(c);
        }catch(Exception e){
            Log.e("Error getUserByRawQuery", e.toString());
            return null;
        }
    }

    public List<Class> getAllClass() {
        List<Class> cours = new ArrayList<Class>();

        Cursor cursor = database.query(MySQLiteAgenda.TABLE_CLASS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Class user = cursorToUser(cursor);
            cours.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return cours;
    }

    private Class cursorToUser(Cursor cursor) {

        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();

        Class myclass = null;

        myclass = new Class(cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return myclass;
    }

    public ArrayList<Class> getAllElements() {

        ArrayList<Class> list = new ArrayList<Class>();

        // Select All Query
        String selectQuery = "SELECT  * FROM " + MySQLiteAgenda.TABLE_CLASS;

        try {

            Cursor cursor = database.rawQuery(selectQuery, null);
            try {

                // looping through all rows and adding to list
                if (cursor.moveToFirst()) {
                    do {
                        Class obj = new Class();
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
