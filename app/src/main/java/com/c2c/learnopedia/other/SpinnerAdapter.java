package com.c2c.learnopedia.other;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import info.androidhive.navigationdrawer.R;

/**
 * Created by acer on 08-11-2016.
 */

public class SpinnerAdapter extends ArrayAdapter<String> {
    Context context;
    String[] codes;
    int[] images;

    public SpinnerAdapter(Context ctx, String[] codes, int[] images) {
        super(ctx, R.layout.model,codes);
        this.context = ctx;
        this.codes= codes;
        this.images=images;
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.model,null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.countryCode);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.countryFlagImage);

        textView.setText(codes[position]);
        imageView.setImageResource(images[position]);
        return convertView;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView=inflater.inflate(R.layout.model,null);
        }
        TextView textView = (TextView) convertView.findViewById(R.id.countryCode);
        ImageView imageView = (ImageView) convertView.findViewById(R.id.countryFlagImage);

        textView.setText(codes[position]);
        imageView.setImageResource(images[position]);
        return convertView;
    }
}
