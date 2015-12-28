package com.app.thyp.agendathyp1516.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.app.thyp.agendathyp1516.Dictionary;
import com.app.thyp.agendathyp1516.bean.Room;
import com.app.thyp.agendathyp1516.bean.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yann on 23/11/2015.
 */
public class UserDataSource {

    // Database fields
    private SQLiteDatabase database;
    private MySQLiteAgenda dbHelper;
    private Dictionary dico;

    private String[] allColumns = { MySQLiteAgenda.CL_ID,
            MySQLiteAgenda.CL_PSEUDO, MySQLiteAgenda.CL_PWD, MySQLiteAgenda.CL_RIGHTS };

    public UserDataSource(Context context) {
        dico = new Dictionary();
        dbHelper = new MySQLiteAgenda(context, dico.NOM_BDD,null, dico.VERSION_BDD);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {

        dbHelper.close();
    }

    public SQLiteDatabase getBDD(){
        return database;
    }

    public Long createUsers(User user) {
        ContentValues values = new ContentValues();
        //values.put(MySQLiteAgenda.CL_ID, 1);
        values.put(MySQLiteAgenda.CL_PSEUDO, user.getPseudo());
        values.put(MySQLiteAgenda.CL_RIGHTS, user.getGroupe());
        values.put(MySQLiteAgenda.CL_PWD, user.getPassWord());

        return database.insert(MySQLiteAgenda.TABLE_USERS, null, values);
    }

    public User getUserWithPseudaAndPWD(String pseudo, String pwd){

        Cursor c = database.query(MySQLiteAgenda.TABLE_USERS, new String[]{MySQLiteAgenda.CL_PSEUDO, MySQLiteAgenda.CL_PWD, MySQLiteAgenda.CL_RIGHTS}, MySQLiteAgenda.CL_PSEUDO + " LIKE \"%" + pseudo + "%\" AND " + MySQLiteAgenda.CL_PWD + "\" LIKE \"%" + pwd + "%\"", null, null, null, null);
        return cursorToUser(c);
    }
    public User getUserByRawQuery(String pseudo, String pwd){
        try{
            Cursor c = database.rawQuery("SELECT * FROM " + MySQLiteAgenda.TABLE_USERS + " WHERE " + MySQLiteAgenda.CL_PSEUDO + " = ?", new String[]{pseudo});
            return cursorToUser(c);
        }catch(Exception e){
            Log.e("Error getUserByRawQuery", e.toString());
            return null;
        }
    }

    public List<User> getAllComments() {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(MySQLiteAgenda.TABLE_USERS,
                allColumns, null, null, null, null, null);

        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            User user = cursorToUser(cursor);
            users.add(user);
            cursor.moveToNext();
        }
        cursor.close();
        return users;
    }

    private User cursorToUser(Cursor cursor) {

        if (cursor.getCount() == 0) return null;

        cursor.moveToFirst();

        User user = null;

        user = new User(cursor.getString(1), cursor.getString(2), cursor.getInt(3));

        return user;
    }

}
