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
    Button btnAjCours;

    Intent intentEDT;
    Button btnJour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_menu_2);


        intentAjtCours = new Intent(this, GestionEdt.class);
        intentEDT = new Intent(this, EDTDAY.class);

        btnAjCours = (Button) findViewById(R.id.AjtCours);
        btnJour = (Button) findViewById(R.id.btnJour);

        btnJour.setOnClickListener(new onClickListenerbtnJour());
        btnAjCours.setOnClickListener(new onClickListenerbtnAjC());

    }

    public class onClickListenerbtnJour implements View.OnClickListener {
        @Override
        public void onClick(View v){
            startActivity(intentEDT);
        }
    }
    public class onClickListenerbtnAjC implements View.OnClickListener {
        @Override
        public void onClick(View v){
            startActivity(intentAjtCours);
        }
    }


}
