package com.app.thyp.agendathyp1516.bdd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.app.thyp.agendathyp1516.bean.User;

import org.w3c.dom.Comment;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yann on 23/11/2015.
 */
public class UserDataSource {

    // Database fields
    private SQLiteDatabase database;
    private DataBase dbHelper;

    private static final int VERSION_BDD = 1;
    private static final String NOM_BDD = "edtAppBDD.db";
    private String[] allColumns = { DataBase.CL_ID,
            DataBase.CL_PSEUDO, DataBase.CL_PWD, DataBase.CL_RIGHTS };

    public UserDataSource(Context context) {

        dbHelper = new DataBase(context, NOM_BDD,null, VERSION_BDD);
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
        values.put(DataBase.CL_ID, "null");
        values.put(DataBase.CL_PSEUDO, user.getPseudo());
        values.put(DataBase.CL_RIGHTS, user.getGroupe());
        values.put(DataBase.CL_PWD, user.getPassWord());

        long insertPseudo = database.insert(DataBase.TABLE_USERS, null,
                values);

        Cursor cursor = database.query(DataBase.TABLE_USERS,
                allColumns, DataBase.CL_PSEUDO + " = " + insertPseudo, null,
                null, null, null);

        cursor.moveToFirst();
        User newUser = cursorToUser(cursor);
        
        cursor.close();
        return database.insert(DataBase.TABLE_USERS, null, values);
    }

    public User getUserWithPseudaAndPWD(String pseudo, String pwd){

        Cursor c = database.query(DataBase.TABLE_USERS, new String[]{DataBase.CL_ID, DataBase.CL_PSEUDO, DataBase.CL_PWD, DataBase.CL_RIGHTS}, DataBase.CL_PSEUDO + " LIKE \"" + pseudo + "\" AND " + DataBase.CL_PWD + "\" LIKE \"" + pwd + "\"", null, null, null, null);
        return cursorToUser(c);
    }

    public List<User> getAllComments() {
        List<User> users = new ArrayList<User>();

        Cursor cursor = database.query(DataBase.TABLE_USERS,
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
        User user = null;

        return user;
    }

}
