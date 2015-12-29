package com.app.thyp.agendathyp1516.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.DatePicker;
import android.widget.Toast;

import com.app.thyp.agendathyp1516.R;

import java.util.Calendar;

public class EDTDAY extends ActionBarActivity {

    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edtday);

        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOn();
    }

    public void showDialogOn(){
        showDialog(DIALOG_ID);
    }

    protected Dialog onCreateDialog(int id){
        if (id == DIALOG_ID){
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        }
        else return null;
    }

    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;
            Toast.makeText(EDTDAY.this, year_x + "/" + month_x + "/"+ day_x, Toast.LENGTH_LONG).show();
        }
    };
}