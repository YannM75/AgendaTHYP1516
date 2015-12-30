package com.app.thyp.agendathyp1516.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.thyp.agendathyp1516.Dictionary;
import com.app.thyp.agendathyp1516.bean.Class;

import java.sql.SQLException;

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

    private Class cursorToUser(Cursor cursor) {

        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();

        Class myclass = null;

        myclass = new Class(cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return myclass;
    }
}
