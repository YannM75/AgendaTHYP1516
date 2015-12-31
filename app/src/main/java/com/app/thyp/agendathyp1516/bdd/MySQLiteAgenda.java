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

    public MySQLiteAgenda(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    private static final String CREATE_TABLE_USER="create table " + TABLE_USERS + " ("+ CL_ID +" integer primary key autoincrement, " + CL_PSEUDO + " text not null," + CL_PWD + " text not null," + CL_RIGHTS + " int);";

    private static final String CREATE_TABLE_ROOM="create table " + TABLE_CLASSROOMS + "("+ CL_ID +" integer primary key autoincrement, " + CL_NUMBER_ROOM + " text);";

    //private static final String CREATE_TABLE_CLASS="create table " + TABLE_CLASS + " ("+ CL_ID +" integer primary key autoincrement, " + CL_NAME_TEACHER + " text," + CL_NAME_CLASS + " text," + CL_DATE + " text);";

    private static final String CREATE_TABLE_CLASS =
            "CREATE TABLE " + TABLE_CLASS + " (" + CL_ID +
                    " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    CL_NAME_TEACHER + " text NOT NULL, " +
                    CL_NAME_CLASS + " text NOT NULL, " +
                    CL_DATE + " text);";
    @Override
    public void onCreate(SQLiteDatabase db) {
        //Supression de la table des users
        Log.i("onCreate MySQL : ", "Drop tables");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);


        //Creation de la table
        Log.i("onCreate MySQL : ", "Create tables");
        db.execSQL(CREATE_TABLE_USER);

        Log.i("Request : ",CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_ROOM);

        Log.i("onCreate MySQL : ","Create table CLASS");
        db.execSQL(CREATE_TABLE_CLASS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);

        Log.i("onCreate MySQL : ", "Create tables");
        db.execSQL(CREATE_TABLE_USER);

        Log.i("Request : ",CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_ROOM);

        Log.i("onCreate MySQL : ", "Create table CLASS");
        db.execSQL(CREATE_TABLE_CLASS);
    }
}
