package com.app.thyp.agendathyp1516.bdd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


/**
 * Created by Yann on 23/11/2015.
 */
public class MySQLiteAgenda extends SQLiteOpenHelper {
    //Tables
    public final static String TABLE_USERS = "USERS";
    public final static String TABLE_CLASSROOMS = "CLASSROOMS";
    public final static String TABLE_CLASS = "COURS";
    public final static String TABLE_ABSENCES = "ABSENCES";
    public final static String TABLE_EXAM = "EXAM";
    //Columns table_users
    public final static String CL_PSEUDO= "LOGIN";
    public final static String CL_PWD = "PASSWORD";
    public final static String CL_RIGHTS= "rights";
    //colums table classroom
    public final static String CL_ID = "ID";
    public final static String CL_NUMBER_ROOM = "CLASSROOM";
    //colums table_class
    public final static String CL_NAME_CLASS = "CLASS_NAME";
    public final static String CL_NAME_TEACHER = "NAME_TEACHER";
    public final static String CL_DATE = "DATE";
    public final static String CL_HEURE = "HEURE";
    public final static String CL_SALLE = "SALLE";

    //colums table_class
    public final static String CL_NAME_PROF = "NAME_PROF";
    public final static String CL_DATE_ABSENCE = "DATE_ABSENCE";
    public final static String CL_MOTIF = "MOTIF";
    //colums table_class
    public final static String CL_NAME_EXAM = "NAME_EXAM";
    public final static String CL_DATE_EXAM = "DATE_EXAM";
    public final static String CL_HEURE_EXAM = "HEURE_EXAM";




    public MySQLiteAgenda(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    private static final String CREATE_TABLE_USER="create table " + TABLE_USERS + " ("+ CL_ID +" integer primary key autoincrement, " + CL_PSEUDO + " text not null," + CL_PWD + " text not null," + CL_RIGHTS + " int);";

    private static final String CREATE_TABLE_ROOM="create table " + TABLE_CLASSROOMS + "("+ CL_ID +" integer primary key autoincrement, " + CL_NUMBER_ROOM + " text);";

    private static final String CREATE_TABLE_ABSENCES="create table " + TABLE_ABSENCES + " ("+ CL_ID +" integer primary key autoincrement, " + CL_NAME_PROF + " text," + CL_DATE_ABSENCE + " text," + CL_MOTIF + " text);";

    private static final String CREATE_TABLE_EXAM="create table " + TABLE_EXAM + " ("+ CL_ID +" integer primary key autoincrement, " + CL_NAME_EXAM + " text," + CL_DATE_EXAM + " text," + CL_HEURE_EXAM + " text);";

    private static final String CREATE_TABLE_CLASS =
            "CREATE TABLE " + TABLE_CLASS + " (" + CL_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CL_NAME_CLASS + " text NOT NULL, " +
                    CL_NAME_TEACHER + " text NOT NULL, " + CL_DATE + " text NOT NULL, " + CL_HEURE + " text NOT NULL, " + CL_SALLE + " text);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Supression de la table des users
        Log.i("onCreate MySQL : ", "Drop tables");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSROOMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ABSENCES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);

        //Creation de la table
        Log.i("onCreate MySQL : ", "Create tables");
        db.execSQL(CREATE_TABLE_USER);

        Log.i("Request : ",CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_ROOM);

        Log.i("onCreate MySQL : ","Create table CLASS");
        db.execSQL(CREATE_TABLE_CLASS);

        Log.i("onCreate MySQL : ", "Create tables");
        db.execSQL(CREATE_TABLE_ABSENCES);

        Log.i("onCreate MySQL : ", "Create tables");
        db.execSQL(CREATE_TABLE_EXAM);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASSROOMS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ABSENCES);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);

        Log.i("onCreate MySQL : ", "Create tables");
        db.execSQL(CREATE_TABLE_USER);

        Log.i("Request : ",CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_ROOM);

        Log.i("onCreate MySQL : ", "Create table CLASS");
        db.execSQL(CREATE_TABLE_CLASS);

        Log.i("onCreate MySQL : ", "Create table CLASS");
        db.execSQL(CREATE_TABLE_ABSENCES);

        Log.i("onCreate MySQL : ", "Create table CLASS");
        db.execSQL(CREATE_TABLE_EXAM);
    }
}
