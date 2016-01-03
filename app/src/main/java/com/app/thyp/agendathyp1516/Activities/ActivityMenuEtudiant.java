package com.app.thyp.agendathyp1516.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

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

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Vous ne pouvez pas retourner en arrière !", Toast.LENGTH_SHORT).show();
        Log.i("onBackPressed","Back pressed");
    }


    public class onClickListenerbtnJour implements View.OnClickListener {
        @Override
        public void onClick(View v){
                startActivity(intent);
            }
    }


}
