package com.app.thyp.agendathyp1516.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.thyp.agendathyp1516.Dictionary;
import com.app.thyp.agendathyp1516.bean.Exam;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.app.thyp.agendathyp1516.bdd.MySQLiteAgenda.TABLE_EXAM;

/**
 * Created by M'hamed on 01/01/2016.
 */
public class ExamDataSource {

    private SQLiteDatabase database;
    private MySQLiteAgenda dbHelper;
    Dictionary dico;

    private String[] allColumns = { MySQLiteAgenda.CL_ID,
            MySQLiteAgenda.CL_NAME_EXAM, MySQLiteAgenda.CL_DATE_EXAM, MySQLiteAgenda.CL_HEURE_EXAM};

    public ExamDataSource(Context context) {

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

    public Long createExam(Exam examen) {
        ContentValues values = new ContentValues();
        //values.put(MySQLiteAgenda.CL_ID, 1);
        values.put(MySQLiteAgenda.CL_NAME_EXAM, examen.getNom_exam());
        values.put(MySQLiteAgenda.CL_DATE_EXAM, examen.getDate_exam());
        values.put(MySQLiteAgenda.CL_HEURE_EXAM, examen.getHeure());

        return database.insert(TABLE_EXAM, null, values);
    }

    public Exam getExamByNameDateHeure(String date, String heure){
        try{
            Cursor cursor = database.query(MySQLiteAgenda.TABLE_EXAM,
                    allColumns, "DATE_EXAM=? and HEURE_EXAM=?", new String[]{date, heure}, null, null, null);
            return cursorToExamVerif(cursor);
        }catch(Exception e){
            Log.e("Error getUserByRawQuery", e.toString());
            return null;
        }

    }

    public Exam getExamByName(String nom){
        try{
            Cursor c = database.rawQuery("SELECT * FROM " + MySQLiteAgenda.TABLE_EXAM + " WHERE " + MySQLiteAgenda.CL_NAME_EXAM + " = ?", new String[]{nom});
            return cursorToUser(c);
        }catch(Exception e){
            Log.e("Error getUserByRawQuery", e.toString());
            return null;
        }
    }

    public List<Exam> getExam(String date) {
        List<Exam> EXAM = new ArrayList<Exam>();

        String[] where_arg = {date};
        Cursor cursor = database.query(MySQLiteAgenda.TABLE_EXAM,
                allColumns, MySQLiteAgenda.CL_DATE_EXAM+"=?", where_arg , null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Exam mycours = cursorToUser(cursor);
            EXAM.add(mycours);
            cursor.moveToNext();
        }
        cursor.close();
        return EXAM;
    }

    private Exam cursorToUser(Cursor cursor) {

        Exam myclass = null;

        myclass = new Exam(cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return myclass;
    }

    private Exam cursorToExamVerif(Cursor cursor) {

        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();

        Exam myexam = null;

        myexam = new Exam(cursor.getString(1), cursor.getString(2), cursor.getString(3));

        return myexam;
    }

}
