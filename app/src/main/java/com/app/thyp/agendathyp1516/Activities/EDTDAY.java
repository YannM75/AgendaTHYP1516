package com.app.thyp.agendathyp1516.Activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.DatePicker;
import android.widget.Toast;

import com.app.thyp.agendathyp1516.ItemAdapter;
import com.app.thyp.agendathyp1516.R;
import com.app.thyp.agendathyp1516.bdd.CoursDataSource;
import com.app.thyp.agendathyp1516.bean.Cours;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

public class EDTDAY extends ListActivity {
    CoursDataSource dbCours;
    // declare class variables
    private ArrayList<Cours> m_parts = new ArrayList<Cours>();
    private Runnable viewParts;
    private ItemAdapter m_adapter;

    int year_x, month_x, day_x;
    static final int DIALOG_ID = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edtday);

        // instantiate our ItemAdapter class
        m_adapter = new ItemAdapter(this, R.layout.list_item, m_parts);

        String date = "30/12/2015";

        dbCours = new CoursDataSource(this);



        try {
            dbCours.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }


        dbCours.close();
        final Calendar cal = Calendar.getInstance();
        year_x = cal.get(Calendar.YEAR);
        month_x = cal.get(Calendar.MONTH);
        day_x = cal.get(Calendar.DAY_OF_MONTH);
        showDialogOn();


    }

    public void showDialogOn() {
        showDialog(DIALOG_ID);
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_ID) {
            return new DatePickerDialog(this, dpickerListner, year_x, month_x, day_x);
        } else return null;
    }
    private Handler handler = new Handler()
    {
        int i =1;
        public void handleMessage(Message msg)
        {


            // create some objects
            // here is where you could also request data from a server
            // and then create objects from that data.
            m_adapter.clear();
            try {
                dbCours.open();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            dbCours.close();
            m_adapter = new ItemAdapter(EDTDAY.this, R.layout.list_item, m_parts);

            // display the list.
            setListAdapter(m_adapter);
            i++;
            Log.d("test", i+" passage");
        }
    };
    private DatePickerDialog.OnDateSetListener dpickerListner
            = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
            year_x = year;
            month_x = monthOfYear + 1;
            day_x = dayOfMonth;



            String resultat =  day_x < 9 ?  "0"+day_x +"/" : day_x +"/";
            resultat +=  month_x < 9 ?  "0"+month_x + "/": month_x + "/";
            resultat +=year_x+"";

            if (dbCours != null) {
                try {
                    dbCours.open();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            m_parts = (ArrayList)dbCours.getCours(resultat);
            viewParts = new Runnable(){
                public void run(){
                    handler.sendEmptyMessage(0);
                }
            };
            Thread thread =  new Thread(null, viewParts, "MagentoBackground");
            thread.start();
            Toast.makeText(EDTDAY.this, year_x + "/" + month_x + "/" + day_x, Toast.LENGTH_LONG).show();

        }
    };


}

