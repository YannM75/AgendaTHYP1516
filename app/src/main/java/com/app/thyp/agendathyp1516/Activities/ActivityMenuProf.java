package com.app.thyp.agendathyp1516.Activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import com.app.thyp.agendathyp1516.R;

/**
 * Created by Abdelbassit on 31/12/2015.
 */
public class ActivityMenuProf extends AppCompatActivity {

    Intent intentAjtCours;
    Intent intentAjtAbs;
    Intent intentAjtExam;
    Intent intentEDT;
    Intent intentExam;

    Button btnAjCours;
    Button btnAbs;
    Button btnAjtExam;
    Button btnExam;
    Button btnJour;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_menu_2);


        intentAjtCours = new Intent(this, GestionEdt.class);
        intentAjtAbs = new Intent(this, ABSENCES.class);
        intentEDT = new Intent(this, EDTDAY.class);
        intentExam = new Intent(this, AfficheExam.class);
        intentAjtExam = new Intent(this, EXAMEN.class);


        btnAjCours = (Button) findViewById(R.id.AjtCours);
        btnJour = (Button) findViewById(R.id.btnJour);
        btnAbs = (Button) findViewById(R.id.AjtAbs);
        btnAjtExam = (Button) findViewById(R.id.AjtExam);
        btnExam = (Button) findViewById(R.id.btnExam);


        btnJour.setOnClickListener(new onClickListenerbtnJour());
        btnAjCours.setOnClickListener(new onClickListenerbtnAjC());
        btnAbs.setOnClickListener(new onClickListenerbtnAbS());
        btnAjtExam.setOnClickListener(new onClickListenerbtnAjEXAM());
        btnExam.setOnClickListener(new onClickListenerbtnEXAM());

    }

    @Override
    public void onBackPressed() {
        Log.i("onBackPressed", "Back pressed");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);

        alertDialogBuilder.setTitle(R.string.StrDeco);

        alertDialogBuilder
                .setMessage("Voulez-vous vous déconnecter ?")
                .setCancelable(false)
                .setPositiveButton(R.string.StrOui,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int id) {
                        finish();
                    }
                })
                .setNegativeButton(R.string.StrNon, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }


    public class onClickListenerbtnJour implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(intentEDT);
        }
    }

    public class onClickListenerbtnAjC implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(intentAjtCours);
        }
    }

   public class onClickListenerbtnAbS implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(intentAjtAbs);
        }
    }
    public class onClickListenerbtnAjEXAM implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(intentAjtExam);
        }
    }
    public class onClickListenerbtnEXAM implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(intentExam);
        }
    }



    }




