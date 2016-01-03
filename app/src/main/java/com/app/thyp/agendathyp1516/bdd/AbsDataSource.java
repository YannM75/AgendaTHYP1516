package com.app.thyp.agendathyp1516.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.thyp.agendathyp1516.Dictionary;
import com.app.thyp.agendathyp1516.bean.Abs;

import java.sql.SQLException;

import static com.app.thyp.agendathyp1516.bdd.MySQLiteAgenda.TABLE_ABSENCES;

/**
 * Created by Abdelbassit on 01/01/2016.
 */
public class AbsDataSource {

    private SQLiteDatabase database;
    private MySQLiteAgenda dbHelper;
    Dictionary dico;

    private String[] allColumns = { MySQLiteAgenda.CL_ID,
            MySQLiteAgenda.CL_NAME_PROF, MySQLiteAgenda.CL_DATE_ABSENCE, MySQLiteAgenda.CL_MOTIF};

    public AbsDataSource(Context context) {

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

    public Long createAbsence(Abs absence) {
        ContentValues values = new ContentValues();
        //values.put(MySQLiteAgenda.CL_ID, 1);
        values.put(MySQLiteAgenda.CL_NAME_PROF, absence.getName_Prof());
        values.put(MySQLiteAgenda.CL_DATE_ABSENCE, absence.getDate_Abs());
        values.put(MySQLiteAgenda.CL_MOTIF, absence.getMotif());

        return database.insert(TABLE_ABSENCES, null, values);
    }

    public Abs getAbsByNameDate(String nom, String Date){
        try{
            Cursor c = database.rawQuery("SELECT * FROM " + MySQLiteAgenda.TABLE_ABSENCES + " WHERE " + MySQLiteAgenda.CL_NAME_PROF + "=?" + " AND " + MySQLiteAgenda.CL_DATE_ABSENCE + "=?", new String[]{nom, Date});
            return cursorToUser(c);
        }catch(Exception e){
            Log.e("Error getUserByRawQuery", e.toString());
            return null;
        }
    }

    public Abs getAbsByDate(String date){
        try{
            Cursor c = database.rawQuery("SELECT * FROM " + MySQLiteAgenda.TABLE_ABSENCES + " WHERE " + MySQLiteAgenda.CL_DATE + " = ?", new String[]{date});
            return cursorToUser(c);
        }catch(Exception e){
            Log.e("Error getUserByRawQuery", e.toString());
            return null;
        }
    }


    private Abs cursorToUser(Cursor cursor) {

        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();

        Abs myclass = null;

        myclass = new Abs(cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return myclass;
    }

}

