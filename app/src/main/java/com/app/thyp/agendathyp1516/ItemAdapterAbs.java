package com.app.thyp.agendathyp1516;

/**
 * Created by Abdelbassit on 03/01/2016.
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.thyp.agendathyp1516.bean.Abs;

import java.util.ArrayList;

/**
 * Created by M'hamed on 03/01/2016.
 */
public class ItemAdapterAbs extends ArrayAdapter<Abs> {

    // declaring our ArrayList of items
    private ArrayList<Abs> objects;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public ItemAdapterAbs(Context context, int textViewResourceId, ArrayList<Abs> objects) {
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
            v = inflater.inflate(R.layout.list_item_abs, null);
        }

        /*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        Abs i = objects.get(position);

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView tp = (TextView) v.findViewById(R.id.textProf);
            TextView tpd = (TextView) v.findViewById(R.id.textProfData);
            TextView tm = (TextView) v.findViewById(R.id.textMotif);
            TextView tmd = (TextView) v.findViewById(R.id.textMotifData);
            TextView tdd = (TextView) v.findViewById(R.id.textDateData);


            // check to see if each individual textview is null.
            // if not, assign some text!

            if (tp != null) {
                tp.setText("Professeur : ");
            }
            if (tpd != null) {
                tpd.setText(i.getName_Prof());
            }
            if (tm != null) {
                tm.setText("Motif : ");
            }
            if (tmd != null) {
                tmd.setText(i.getMotif());
            }
            if (tdd != null) {
                tdd.setText(i.getDate_Abs());
            }


        }
        // the view must be returned to our activity
        return v;


    }

}




