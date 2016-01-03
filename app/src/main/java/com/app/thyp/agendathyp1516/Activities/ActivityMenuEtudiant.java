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

import com.app.thyp.agendathyp1516.ActivityConnection;
import com.app.thyp.agendathyp1516.R;

public class ActivityMenuEtudiant extends AppCompatActivity {
    Intent intent;
    Intent IntentAbs;
    Intent intentExam;

    Button btnJour;
    Button btnAbs;
    Button btnExam;
    Intent intentCo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_menu);

        intentCo = new Intent(this, ActivityConnection.class);
        intent = new Intent(this, EDTDAY.class);
        IntentAbs = new Intent(this, AfficheAbsences.class);
        intentExam  = new Intent(this, AfficheExam.class);

        btnJour = (Button) findViewById(R.id.btnJour);
        btnAbs = (Button) findViewById(R.id.btnAbs);
        btnExam = (Button) findViewById(R.id.btnExam);

        btnJour.setOnClickListener(new onClickListenerbtnJour());
        btnAbs.setOnClickListener(new onClickListenerbtnAffAbs());
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

        AlertDialog alertDialog = alertDialogBuilder.create();

        alertDialog.show();
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


    public class onClickListenerbtnJour implements View.OnClickListener {
        @Override
        public void onClick(View v){
                startActivity(intent);
            }
    }
    public class onClickListenerbtnAffAbs implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(IntentAbs);
        }
    }
    public class onClickListenerbtnEXAM implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            startActivity(intentExam);
        }
    }


}
