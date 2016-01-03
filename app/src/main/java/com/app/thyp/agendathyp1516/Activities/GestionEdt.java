package com.app.thyp.agendathyp1516.Activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.app.thyp.agendathyp1516.R;
import com.app.thyp.agendathyp1516.bdd.CoursDataSource;
import com.app.thyp.agendathyp1516.bean.Cours;

import java.sql.SQLException;

public class GestionEdt extends AppCompatActivity {
    EditText edtTxtNomCours;
    EditText edtTxtDateCours;
    EditText edtTxtNomProf;
    EditText editTxtHeureCours;
    EditText editTxtSalleCours;
    CoursDataSource dbCours;
    Button btnAjouter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gestion_edt);

        edtTxtNomCours = (EditText)findViewById(R.id.edtTxtNomCours);
        edtTxtDateCours = (EditText)findViewById(R.id.edtTxtDateCours);
        edtTxtNomProf = (EditText)findViewById(R.id.edtTxtNomProf);
        editTxtHeureCours = (EditText)findViewById(R.id.editTxtHeureCours);
        editTxtSalleCours = (EditText)findViewById(R.id.editTxtSalleCours);

        btnAjouter = (Button)findViewById(R.id.btnAjouterEx);
        btnAjouter.setOnClickListener(new onClickListenerBtnAjouter());

        dbCours = new CoursDataSource(this);
        try {
            dbCours.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        dbCours.close();
    }

    public class onClickListenerBtnAjouter implements View.OnClickListener {
        @Override
        public void onClick(View v){
            Cours c = null;
            String NomCours = edtTxtNomCours.getText().toString();
            String DateCours = edtTxtDateCours.getText().toString();
            String NomProf = edtTxtNomProf.getText().toString();
            String HeureCours = editTxtHeureCours.getText().toString();
            String SalleCours = editTxtSalleCours.getText().toString();

            Log.i("PSEUDO : ", NomCours);
            Log.i("PSEUDO : ", DateCours);
            Log.i("PSEUDO : ", NomProf);
            Log.i("PSEUDO : ", HeureCours);
            Log.i("PSEUDO : ", SalleCours);
            if(dbCours != null){
                try {
                    dbCours.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                //db.getUserWithPseudaAndPWD(pseudo, pwd);
                c = dbCours.getClassByAllData(HeureCours, DateCours);
                if(c != null){

                            Log.i("PSEUDO : ", "cours ajouter");
                            Toast.makeText(getApplicationContext(), "Un cours existe déja à cette heure !", Toast.LENGTH_SHORT).show();

                        }else{
                             Cours newCours = new Cours(NomCours, NomProf, DateCours, HeureCours, SalleCours);
                             dbCours.createClass(newCours);
                             Toast.makeText(getApplicationContext(), "le cours "+NomCours+" à été ajouter", Toast.LENGTH_SHORT).show();
                    Log.i("PSEUDO : ", "cours ajouter");}

                }
            dbCours.close();
            }


        }

    }


