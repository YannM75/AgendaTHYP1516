package com.app.thyp.agendathyp1516;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


import com.app.thyp.agendathyp1516.Activities.ActivityMenu;
import com.app.thyp.agendathyp1516.bdd.DataBase;

public class ActivityConnection extends AppCompatActivity {

    Button btnValidaton;
    Button btnCancel;
    EditText edtTxtPseudo;
    EditText edtTxtPWD;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        intent = new Intent(this, ActivityMenu.class);

        Log.d("ActivityCon_onCreate", "onCreate ");

        btnValidaton = (Button)findViewById(R.id.btnValidCo);
        btnCancel = (Button)findViewById(R.id.btnCancelCo);

        btnValidaton.setOnClickListener(new onClickListenerBtnValidation());

        edtTxtPseudo = (EditText)findViewById(R.id.edTxtPseudo);
        edtTxtPWD = (EditText)findViewById(R.id.edTxtPwd);

        DataBase db = new DataBase(this);
    }

    public class onClickListenerBtnValidation implements View.OnClickListener {
        @Override
        public void onClick(View v){
            String pseudo = edtTxtPseudo.getText().toString();
            String pwd = edtTxtPWD.getText().toString();

            startActivity(intent);
        }
    }
}
