package com.c2c.learnopedia.other;


import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.c2c.learnopedia.model.Courses;

import java.util.ArrayList;

import info.androidhive.navigationdrawer.R;

public class CoursesViewAdapter extends ArrayAdapter<Courses> {

    private Context context;
    private int layoutResourceId;
    private ArrayList<Courses> data = new ArrayList<Courses>();

    public CoursesViewAdapter(Context context, int layoutResourceId, ArrayList<Courses> data) {
        super(context, layoutResourceId, data);
        this.layoutResourceId = layoutResourceId;
        this.context = context;
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.imageTitle = (TextView) row.findViewById(R.id.courses_tv);
            holder.image = (ImageView) row.findViewById(R.id.image_courses);
            row.setTag(holder);
        } else {
            holder = (ViewHolder) row.getTag();
        }

        Courses item = data.get(position);
        holder.imageTitle.setText(item.getCourseDesc());
      //  holder.image.setImageBitmap(item.getImage());

        return row;
    }

    static class ViewHolder {
        TextView imageTitle;
        ImageView image;
    }
}
