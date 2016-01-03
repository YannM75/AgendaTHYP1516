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
        Log.i("onBackPressed", "Back pressed");
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getApplicationContext());

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
        public void onClick(View v){
                startActivity(intent);
            }
    }


}
