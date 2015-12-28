package com.app.thyp.agendathyp1516;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import com.app.thyp.agendathyp1516.Activities.ActivityMenu;
import com.app.thyp.agendathyp1516.bdd.UserDataSource;
import com.app.thyp.agendathyp1516.bean.User;

import java.sql.SQLException;

public class ActivityConnection extends AppCompatActivity {

    Button btnValidaton;
    Button btnCancel;
    EditText edtTxtPseudo;
    EditText edtTxtPWD;
    Intent intent;
    UserDataSource db;

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

        db = new UserDataSource(this);

        User user1 = new User("YannM","azerty159",1);
        User user2 = new User("thypbast","azerty357",2);
        User user3 = new User("elyaagoubi","azerty258",1);

        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        db.createUsers(user1);
        db.createUsers(user2);
        db.createUsers(user3);

        db.close();


    }

    public class onClickListenerBtnValidation implements View.OnClickListener {
        @Override
        public void onClick(View v){
            User u = null;
            String pseudo = edtTxtPseudo.getText().toString();
            String pwd = edtTxtPWD.getText().toString();

            Log.i("PSEUDO : ",pseudo);
            Log.i("PWD : ", pwd);

            if(db != null){
                try {
                    db.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //db.getUserWithPseudaAndPWD(pseudo, pwd);
                u = db.getUserByRawQuery(pseudo,pwd);
                if(!u.equals(null)){
                    Log.e("Pseudo database ", u.getPseudo());
                    Log.e("mdp database", u.getPassWord());
                    Log.e("groupe user", String.valueOf(u.getGroupe()));
                    if(pwd.equals(u.getPassWord())){
                        Log.i("Confirmation ","User reconnu");
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"Wrong PassWord", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.e("error user", "user null");
                    Toast.makeText(getApplicationContext(),"Wrong User", Toast.LENGTH_SHORT).show();
                }

            }
            db.close();

        }
    }
}
