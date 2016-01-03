package com.app.thyp.agendathyp1516;

/**
 * Created by Abdelbassit on 02/01/2016.
 */
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.thyp.agendathyp1516.bean.Cours;

import java.util.ArrayList;

public class ItemAdapter extends ArrayAdapter<Cours> {

    // declaring our ArrayList of items
    private ArrayList<Cours> objects;

    /* here we must override the constructor for ArrayAdapter
    * the only variable we care about now is ArrayList<Item> objects,
    * because it is the list of objects we want to display.
    */
    public ItemAdapter(Context context, int textViewResourceId, ArrayList<Cours> objects) {
        super(context, textViewResourceId, objects);
        this.objects = objects;
    }

    /*
     * we are overriding the getView method here - this is what defines how each
     * list item will look.
     */
    public View getView(int position, View convertView, ViewGroup parent){

        // assign the view we are converting to a local variable
        View v = convertView;

        // first check to see if the view is null. if so, we have to inflate it.
        // to inflate it basically means to render, or show, the view.
        if (v == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_item, null);
        }

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 *
		 * Therefore, i refers to the current Item object.
		 */
        Cours i = objects.get(position);

        if (i != null) {

            // This is how you obtain a reference to the TextViews.
            // These TextViews are created in the XML files we defined.

            TextView tc = (TextView) v.findViewById(R.id.textCours);
            TextView tcd = (TextView) v.findViewById(R.id.textCoursData);
            TextView tp = (TextView) v.findViewById(R.id.textProf);
            TextView tpd = (TextView) v.findViewById(R.id.textProfData);
            TextView tdd = (TextView) v.findViewById(R.id.textDateData);
            TextView thd = (TextView) v.findViewById(R.id.textHeureData);
            TextView ts = (TextView) v.findViewById(R.id.textSalle);
            TextView tsd = (TextView) v.findViewById(R.id.textSalleData);
            // check to see if each individual textview is null.
            // if not, assign some text!
            if (tc != null){
                tc.setText("Cours : ");
            }
            if (tcd != null){
                tcd.setText(i.getName_teacher());
            }
            if (tp != null){
                tp.setText("Professeur : ");
            }
            if (tpd != null){
                tpd.setText(i.getName_class());
            }

            if (tdd != null){
                tdd.setText(i.getDate_class());
            }
            if (thd != null){
                thd.setText(i.getHeure());
            }
            if (ts != null){
                ts.setText("Salle : ");
            }
            if (tsd != null){
                tsd.setText(i.getSalle());
            }

        }

        // the view must be returned to our activity
        return v;

    }

}