package com.app.thyp.agendathyp1516;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.thyp.agendathyp1516.bean.Exam;

import java.util.ArrayList;

/**
 * Created by M'hamed on 03/01/2016.
 */
public class ItemAdapterExam extends ArrayAdapter<Exam> {

    // declaring our ArrayList of items
    private ArrayList<Exam> objects;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public ItemAdapterExam(Context context, int textViewResourceId, ArrayList<Exam> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }


    /*
     * we are overriding the getView method here - this is what defines how each
     * list item will look.
     */


    public View getView(int position, View convertView, ViewGroup parent) {

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item_exam, null);
        }

        /*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        Exam i = objects.get(position);

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView te = (TextView) v.findViewById(R.id.textExam);
            TextView ted = (TextView) v.findViewById(R.id.textExamData);
            TextView thd = (TextView) v.findViewById(R.id.textHeureData);
            TextView tdd = (TextView) v.findViewById(R.id.textDateData);


            // check to see if each individual textview is null.
            // if not, assign some text!

            if (te != null) {
                te.setText("Examen : ");
            }
            if (ted != null) {
                ted.setText(i.getNom_exam());
            }
            if (thd != null) {
                thd.setText(i.getHeure());
            }
            if (tdd != null) {
                tdd.setText(i.getDate_exam());
            }


        }
        // the view must be returned to our activity
        return v;


    }

}



