package com.app.thyp.agendathyp1516.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.thyp.agendathyp1516.ActivityConnection;
import com.app.thyp.agendathyp1516.R;
import com.app.thyp.agendathyp1516.bdd.ExamDataSource;

import com.app.thyp.agendathyp1516.bean.Exam;

import java.sql.SQLException;

/**
 * Created by M'hamed on 01/01/2016.
 */
public class EXAMEN extends AppCompatActivity {

    EditText editTextNomExam;
    EditText editTextDateExam;
    EditText editTextHeureExam;

    ExamDataSource dbExam;
    Button btnAjouterEx;

    Intent intentCo;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_exam);

        intentCo = new Intent(this, ActivityConnection.class);

        editTextNomExam = (EditText) findViewById(R.id.editTextNomExam);
        editTextDateExam = (EditText) findViewById(R.id.editTextDateExam);
        editTextHeureExam = (EditText) findViewById(R.id.editTextHeureExam);

        btnAjouterEx = (Button) findViewById(R.id.btnAjouterEx);
        btnAjouterEx.setOnClickListener(new onClickListenerBtnAjouter());

        dbExam = new ExamDataSource(this);

        try {
            dbExam.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbExam.close();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_settings:
                onClickSettings();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickSettings(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(R.string.StrDeco);

        alertDialogBuilder
                .setMessage("Voulez-vous vous déconnecter ?")
                .setCancelable(false)
                .setPositiveButton(R.string.StrOui,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        startActivity(intentCo);
                        finish();
                    }
                })
                .setNegativeButton(R.string.StrNon, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("onCreateOptionsMenu", "Start");
        MenuInflater mif = new MenuInflater(this);
        mif.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public class onClickListenerBtnAjouter implements View.OnClickListener {

            public void onClick(View v) {
                Exam ex = null;
                String NomExam = editTextNomExam.getText().toString();
                String DateExam = editTextDateExam.getText().toString();
                String HeureExam = editTextHeureExam.getText().toString();

                Log.i("name : ", NomExam);
                Log.i("Date : ", DateExam);
                Log.i("Heure : ", HeureExam);

                if (dbExam != null) {
                    try {
                        dbExam.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    if (dbExam != null) {
                        try {
                            dbExam.open();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }
                        //db.getUserWithPseudaAndPWD(pseudo, pwd);
                        ex = dbExam.getExamByNameDateHeure(DateExam,HeureExam);

                        if (ex != null) {

                            Log.i("PSEUDO : ", "Examen ajouter");
                            Toast.makeText(getApplicationContext(), " Examen existe dèjà", Toast.LENGTH_SHORT).show();

                        } else {
                            Exam Examen = new Exam(NomExam, DateExam, HeureExam);
                            dbExam.createExam(Examen);
                            Toast.makeText(getApplicationContext(), "Examen " + NomExam + " à été ajouter", Toast.LENGTH_SHORT).show();
                            Log.i("PSEUDO : ", "Examen ajouter");
                        }

                    }
                    dbExam.close();
                }




            }




    }

}




