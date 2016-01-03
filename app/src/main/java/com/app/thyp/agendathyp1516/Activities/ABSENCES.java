package com.app.thyp.agendathyp1516.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.app.thyp.agendathyp1516.bdd.AbsDataSource;
import com.app.thyp.agendathyp1516.bean.Abs;


import java.sql.SQLException;

/**
 * Created by M'hamed on 31/12/2015.
 */

public class ABSENCES extends AppCompatActivity {

    EditText editTextNom;
    EditText editTextDate;
    EditText editTextMotif;
    Intent intentCo;

    AbsDataSource dbAbs;
    Button btnAjouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_absence);

        editTextNom = (EditText) findViewById(R.id.editTextNomExam);
        editTextDate = (EditText) findViewById(R.id.editTextDate);
        editTextMotif = (EditText) findViewById(R.id.editTextMotif);

        btnAjouter = (Button) findViewById(R.id.btnAjouterEx);
        btnAjouter.setOnClickListener(new onClickListenerBtnAjouter());

        dbAbs = new AbsDataSource(this);

        intentCo = new Intent(this, ActivityConnection.class);

        try {
            dbAbs.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbAbs.close();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("onCreateOptionsMenu", "Start");
        MenuInflater mif = new MenuInflater(this);
        mif.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
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

    public class onClickListenerBtnAjouter implements View.OnClickListener {

        public void onClick(View v) {
            Abs a = null;
            String NomProf = editTextNom.getText().toString();
            String Date = editTextDate.getText().toString();
            String Motif = editTextMotif.getText().toString();

            Log.i("PSEUDO : ", NomProf);
            Log.i("PSEUDO : ", Date);
            Log.i("PSEUDO : ", Motif);

            if (dbAbs != null) {
                try {
                    dbAbs.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }


                if (dbAbs != null) {
                    try {
                        dbAbs.open();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                    //db.getUserWithPseudaAndPWD(pseudo, pwd);
                    a = dbAbs.getAbsByNameDate(NomProf, Date);
                    if (a != null) {

                        Log.i("PSEUDO : ", "cours ajouter");
                        Toast.makeText(getApplicationContext(), "Cette absence existe dèjà", Toast.LENGTH_SHORT).show();

                    } else {
                        Abs Absence = new Abs(NomProf, Date, Motif);
                        dbAbs.createAbsence(Absence);
                        Toast.makeText(getApplicationContext(), "Absence de " + NomProf + " à été ajouter", Toast.LENGTH_SHORT).show();
                        Log.i("PSEUDO : ", "Absence ajouter");
                    }

                }
                dbAbs.close();
            }


        }

    }
}