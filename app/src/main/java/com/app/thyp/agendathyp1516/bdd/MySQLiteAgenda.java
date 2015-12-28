package com.app.thyp.agendathyp1516.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.logging.Logger;


/**
 * Created by Yann on 23/11/2015.
 */
public class MySQLiteAgenda extends SQLiteOpenHelper {
    //Tables
    public final static String TABLE_USERS = "USERS";
    public final static String TABLE_CLASSROOMS = "CLASSROOMS";
    //Columns
    public final static String CL_PSEUDO= "LOGIN";
    public final static String CL_PWD = "PASSWORD";
    public final static String CL_RIGHTS= "rights";
    public final static String CL_ID = "ID";
    public final static String CL_NUMBER_ROOM = "CLASSROOM";

    public MySQLiteAgenda(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }
    private static final String CREATE_TABLE_USER="create table " + TABLE_USERS + " ("+ CL_ID +" integer primary key autoincrement, " + CL_PSEUDO + " text not null," + CL_PWD + " text not null," + CL_RIGHTS + " int);";

    private static final String CREATE_TABLE_ROOM="create table " + TABLE_CLASSROOMS + "("+ CL_ID +" integer primary key autoincrement, " + CL_NUMBER_ROOM + " text);";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Supression de la table des users
        Log.i("onCreate MySQL : ", "Drop tables");

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);


        //Creation de la table
        Log.i("onCreate MySQL : ","Create tables");
        db.execSQL(CREATE_TABLE_USER);

        Log.i("Request : ",CREATE_TABLE_USER);
        db.execSQL(CREATE_TABLE_ROOM);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
