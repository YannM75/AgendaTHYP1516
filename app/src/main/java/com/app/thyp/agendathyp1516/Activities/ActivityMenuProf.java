package com.app.thyp.agendathyp1516.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;


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
    Intent IntentAbs;

    Button btnAjCours;
    Button btnAbs;
    Button btnAjtExam;
    Button btnExam;
    Button btnJour;
    Button btnAffAbs;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_menu_2);


        intentAjtCours = new Intent(this, GestionEdt.class);
        intentAjtAbs = new Intent(this, ABSENCES.class);
        intentEDT = new Intent(this, EDTDAY.class);
        intentExam = new Intent(this, AfficheExam.class);
        intentAjtExam = new Intent(this, EXAMEN.class);
        IntentAbs = new Intent(this, AfficheAbsences.class);

        btnAjCours = (Button) findViewById(R.id.AjtCours);
        btnJour = (Button) findViewById(R.id.btnJour);
        btnAbs = (Button) findViewById(R.id.AjtAbs);
        btnAjtExam = (Button) findViewById(R.id.AjtExam);
        btnExam = (Button) findViewById(R.id.btnExam);
        btnAffAbs = (Button) findViewById(R.id.btnAbs);

        btnJour.setOnClickListener(new onClickListenerbtnJour());
        btnAjCours.setOnClickListener(new onClickListenerbtnAjC());
        btnAbs.setOnClickListener(new onClickListenerbtnAbS());
        btnAjtExam.setOnClickListener(new onClickListenerbtnAjEXAM());
        btnExam.setOnClickListener(new onClickListenerbtnEXAM());
        btnAffAbs.setOnClickListener(new onClickListenerbtnAffAbs());

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
    public class onClickListenerbtnAffAbs implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(IntentAbs);
        }
    }


    }




