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

    private static final String TAG = " ddd";
    private SQLiteDatabase database;
    private MySQLiteAgenda dbHelper;
    Dictionary dico;

    private String[] allColumns = { MySQLiteAgenda.CL_ID,
            MySQLiteAgenda.CL_NAME_TEACHER, MySQLiteAgenda.CL_NAME_CLASS, MySQLiteAgenda.CL_DATE, MySQLiteAgenda.CL_HEURE, MySQLiteAgenda.CL_SALLE};

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
        values.put(MySQLiteAgenda.CL_HEURE, myclass.getHeure());
        values.put(MySQLiteAgenda.CL_SALLE, myclass.getSalle());

        return database.insert(TABLE_CLASS, null, values);
    }

    public Cours getClassByAllData(String Heure, String date){
        try{
            Cursor cursor = database.query(MySQLiteAgenda.TABLE_CLASS,
                    allColumns, "HEURE=? and DATE=?", new String[] { Heure, date }, null, null, null);
            return cursorToCoursVerif(cursor);
        }catch(Exception e){
            Log.e("Error getUserByRawQuery", e.toString());
            return null;
        }
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



    public List<Cours> getCours(String date) {
        List<Cours> COURS = new ArrayList<Cours>();
        String[] where_arg = {date};
        Cursor cursor = database.query(MySQLiteAgenda.TABLE_CLASS,
                allColumns, MySQLiteAgenda.CL_DATE+"=?", where_arg , null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Cours mycours = cursorToUser(cursor);
            COURS.add(mycours);
            cursor.moveToNext();
        }
        cursor.close();
        return COURS;
    }

    private Cours cursorToUser(Cursor cursor) {

        Cours user = null;

        user = new Cours(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return user;
    }
    private Cours cursorToCoursVerif(Cursor cursor) {

        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();

        Cours myclass = null;

        myclass = new Cours(cursor.getString(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getString(5));

        return myclass;
    }
}
