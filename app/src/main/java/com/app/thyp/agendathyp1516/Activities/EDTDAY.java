package com.app.thyp.agendathyp1516.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.app.thyp.agendathyp1516.R;
import com.app.thyp.agendathyp1516.bdd.ClassDataSource;
import com.app.thyp.agendathyp1516.bean.Class;

import java.sql.SQLException;
import java.util.Calendar;

public class EDTDAY extends ActionBarActivity {
    ClassDataSource dbClass;

    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;
    TextView cours;
    TextView nomProf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edtday);
        cours = (TextView) findViewById(R.id.nom_cours);
        nomProf = (TextView) findViewById(R.id.nom_prof);

        String date = "2015/12/30"; // Retrived date in your specified format from sqlite
        /*
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date res = null;
        Date d = null;
        try {
            d = (Date)sdf.parse(date);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        Calendar c = Calendar.getInstance();
        c.setTime(d);

        int day = c.get(Calendar.DAY_OF_MONTH);
        int month = c.get(Calendar.MONTH);
        int year = c.get(Calendar.YEAR);
        */
        dbClass = new ClassDataSource(this);

        Class class1 = new Class("Android", "Guillaume", date);
        Class class2 = new Class("Gestion de projet", "Khaldoun", date);
        Class class3 = new Class("Technique web", "Nasserdine", date);

        try {
            dbClass.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbClass.createClass(class1);
        dbClass.createClass(class2);
        dbClass.createClass(class3);

        dbClass.close();


        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOn();
    }

    public void showDialogOn() {
        showDialog(DIALOG_ID);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        } else return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;

            Toast.makeText(EDTDAY.this, year_x + "/" + month_x + "/" + day_x, Toast.LENGTH_LONG).show();

            String resultat = "";

            if (dbClass != null) {
                try {
                    dbClass.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            resultat += year_x;
            resultat += "/" + month_x;
            resultat += "/" + day_x;

            Class u = null;
            u = dbClass.getClassByDate(resultat);
            if (u != null) {
                Log.e("Pseudo database ", u.getName_class());
                Log.e("mdp database", u.getName_teacher());
                Log.e("groupe user", String.valueOf(u.getDate_class()));

                cours.setText(u.getName_class());
                nomProf.setText(u.getName_teacher());

            } else {
                Toast.makeText(getApplicationContext(), resultat, Toast.LENGTH_SHORT).show();
            }
            dbClass.close();
        }
    };
}