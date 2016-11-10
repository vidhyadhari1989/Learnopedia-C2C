package com.c2c.learnopedia.activity;


import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.GridView;

import com.c2c.learnopedia.model.Courses;
import com.c2c.learnopedia.other.CoursesViewAdapter;

import java.util.ArrayList;

import info.androidhive.navigationdrawer.R;

public class ListOfCoursesActivity extends ActionBarActivity {
    private GridView gridView;
    private CoursesViewAdapter gridAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_courses);
        gridView = (GridView) findViewById(R.id.gridView_couses);
        gridAdapter = new CoursesViewAdapter(this, R.layout.list_of_courses_item, getData());
        gridView.setAdapter(gridAdapter);
    }

    private ArrayList<Courses> getData() {
        final ArrayList<Courses> imageItems = new ArrayList<>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_ids);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageItems.add(new Courses(bitmap, "Courses" + i));
        }
        return imageItems;
    }
}