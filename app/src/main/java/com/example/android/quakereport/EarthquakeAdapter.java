package com.example.android.quakereport;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by lamkeong on 6/14/2017.
 */

public class EarthquakeAdapter extends ArrayAdapter<Earthquake> {


    public EarthquakeAdapter(@NonNull Context context, @NonNull List<Earthquake> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listViewItem = convertView;
        if (listViewItem==null){
            listViewItem = LayoutInflater.from(getContext()).inflate(
                    R.layout.earthquake_list_item,parent,false);
        }

        final Earthquake currentEarthquake = getItem(position);
        double currentMag = currentEarthquake.getMag();

        TextView magTextView = (TextView) listViewItem.findViewById(R.id.mag_TextView);
        GradientDrawable magnitudeCircle = (GradientDrawable) magTextView.getBackground();

        magTextView.setText(String.format("%.1f",(currentEarthquake.getMag())));

        if(currentMag<=2){
            magnitudeCircle.setColor(ContextCompat.getColor(getContext(),R.color.magnitude1));
        } else if(currentMag<=3) {
            magnitudeCircle.setColor(ContextCompat.getColor(getContext(),R.color.magnitude2));
        } else if(currentMag<=4) {
            magnitudeCircle.setColor(ContextCompat.getColor(getContext(),R.color.magnitude3));
        } else if(currentMag<=5) {
            magnitudeCircle.setColor(ContextCompat.getColor(getContext(),R.color.magnitude4));
        }else if(currentMag<=6) {
            magnitudeCircle.setColor(ContextCompat.getColor(getContext(),R.color.magnitude5));
        }else if(currentMag<=7) {
            magnitudeCircle.setColor(ContextCompat.getColor(getContext(),R.color.magnitude6));
        }else if(currentMag<=8) {
            magnitudeCircle.setColor(ContextCompat.getColor(getContext(),R.color.magnitude7));
        }else if(currentMag<=9) {
            magnitudeCircle.setColor(ContextCompat.getColor(getContext(),R.color.magnitude8));
        }else if(currentMag<=10) {
            magnitudeCircle.setColor(ContextCompat.getColor(getContext(),R.color.magnitude9));
        }else  {
            magnitudeCircle.setColor(ContextCompat.getColor(getContext(),R.color.magnitude10plus));
        }


        String place = currentEarthquake.getPlace();
        String firstPart;
        String secondPart;

        if(place.contains(" of ")){
            String[]parts= place.split(" of ");
            firstPart = parts[0] + " of ";
            secondPart = parts[1];
            TextView firstPartTextView = (TextView) listViewItem.findViewById(R.id.firstPart_TextView);
            firstPartTextView.setText(firstPart);

            TextView secondPartTextView = (TextView) listViewItem.findViewById(R.id.secondPart_TextView);
            secondPartTextView.setText(secondPart);
        } else {
            TextView firstPartTextView = (TextView) listViewItem.findViewById(R.id.firstPart_TextView);
            firstPartTextView.setText("Near the ");
            TextView secondPartTextView = (TextView) listViewItem.findViewById(R.id.secondPart_TextView);
            secondPartTextView.setText(place);
        }

        TextView dateTextView = (TextView) listViewItem.findViewById(R.id.date_TextView);
        Date date = new Date(currentEarthquake.getDate());
        SimpleDateFormat df = new SimpleDateFormat("MMM dd, yyyy");
        String formattedDate = df.format(date);
        dateTextView.setText(formattedDate);

        TextView timeTextView=(TextView)listViewItem.findViewById(R.id.time_TextView);
        SimpleDateFormat tf=new SimpleDateFormat("hh:mm a");
        String formattedTime = tf.format(date);
        timeTextView.setText(formattedTime);


        return listViewItem;
    }
}
