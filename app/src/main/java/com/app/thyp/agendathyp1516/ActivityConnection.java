package com.app.thyp.agendathyp1516;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.thyp.agendathyp1516.Activities.ActivityMenuEtudiant;
import com.app.thyp.agendathyp1516.Activities.ActivityMenuProf;
import com.app.thyp.agendathyp1516.bdd.CoursDataSource;
import com.app.thyp.agendathyp1516.bdd.UserDataSource;
import com.app.thyp.agendathyp1516.bean.User;

import java.sql.SQLException;

public class ActivityConnection extends AppCompatActivity {

    Button btnValidaton;
    Button btnCancel;
    EditText edtTxtPseudo;

    EditText edtTxtPWD;
    Intent intentEtudiant;
    Intent intentProf;

    UserDataSource dbUsers;
    CoursDataSource dbCours;
    String date = "30/12/2015";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connection);
        intentEtudiant = new Intent(this, ActivityMenuEtudiant.class);
        intentProf = new Intent(this, ActivityMenuProf.class);
        Log.d("ActivityCon_onCreate", "onCreate ");

        btnValidaton = (Button)findViewById(R.id.btnValidCo);
        btnCancel = (Button)findViewById(R.id.btnCancelCo);

        btnValidaton.setOnClickListener(new onClickListenerBtnValidation());
        btnCancel.setOnClickListener(new onClickListenerBtnCancel());

        edtTxtPseudo = (EditText)findViewById(R.id.edTxtPseudo);
        edtTxtPWD = (EditText)findViewById(R.id.edTxtPwd);

        dbUsers = new UserDataSource(this);
        dbCours = new CoursDataSource(this);

        User user1 = new User("YannM","azerty159",1);
        User user2 = new User("thypbast","azerty357",2);
        User user3 = new User("elyaagoubi","azerty258",1);


        try {
            dbUsers.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbUsers.createUsers(user1);
        dbUsers.createUsers(user2);
        dbUsers.createUsers(user3);

        dbUsers.close();

        try {
            dbCours.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        dbCours.close();


    }

    @Override
    protected void onResume() {
        super.onResume();
        edtTxtPseudo = (EditText)findViewById(R.id.edTxtPseudo);
        edtTxtPWD = (EditText)findViewById(R.id.edTxtPwd);

        edtTxtPseudo.setText("");
        edtTxtPWD.setText("");
    }

    public class onClickListenerBtnValidation implements View.OnClickListener {
        @Override
        public void onClick(View v){
            User u = null;
            String pseudo = edtTxtPseudo.getText().toString();
            String pwd = edtTxtPWD.getText().toString();

            Log.i("PSEUDO : ", pseudo);
            Log.i("PWD : ", pwd);


            if(dbUsers != null){
                try {
                    dbUsers.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //db.getUserWithPseudaAndPWD(pseudo, pwd);
                u = dbUsers.getUserByRawQuery(pseudo,pwd);
                if(u != null){
                    Log.e("Pseudo database ", u.getPseudo());
                    Log.e("mdp database", u.getPassWord());
                    Log.e("groupe user", String.valueOf(u.getGroupe()));

                    if(pseudo.equals(u.getPseudo())){
                        Log.i("Confirmation ", "User reconnu");
                        if(pwd.equals(u.getPassWord())){
                            if(String.valueOf(u.getGroupe()) == "1"){
                                startActivity(intentEtudiant);
                            }
                            else{
                                startActivity(intentProf);
                            }

                        }
                        else{
                            Log.e("error mdp", "mdp null");
                            Toast.makeText(getApplicationContext(), "Mot de passe incorrect !", Toast.LENGTH_SHORT).show();
                        }
                    }
                }else{
                    Toast.makeText(getApplicationContext(),"Utilisateur non reconnu !", Toast.LENGTH_SHORT).show();
                }

            }
            dbUsers.close();

        }
    }
    public class onClickListenerBtnCancel implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            edtTxtPseudo = (EditText)findViewById(R.id.edTxtPseudo);
            edtTxtPWD = (EditText)findViewById(R.id.edTxtPwd);

            edtTxtPseudo.setText("");
            edtTxtPWD.setText("");
        }
    }

}
