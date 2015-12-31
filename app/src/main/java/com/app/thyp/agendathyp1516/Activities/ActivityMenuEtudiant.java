package com.app.thyp.agendathyp1516.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.app.thyp.agendathyp1516.R;

public class ActivityMenuEtudiant extends AppCompatActivity {
    Intent intent;
    Button btnJour;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_menu);


        intent = new Intent(this, EDTDAY.class);

        btnJour = (Button) findViewById(R.id.btnJour);

        btnJour.setOnClickListener(new onClickListenerbtnJour());

    }

        public class onClickListenerbtnJour implements View.OnClickListener {
            @Override
            public void onClick(View v){
                startActivity(intent);
            }
        }


}
