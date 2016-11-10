package com.c2c.learnopedia.other;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.c2c.learnopedia.model.Subject;

import java.util.ArrayList;
import java.util.List;

import info.androidhive.navigationdrawer.R;

public class GridViewAdapter extends BaseAdapter {
    Context mContext;
    List<Subject> mAllSubjectList;
    OnListItemClickListner onListItemClickListner;

 ArrayList<Subject> mSubjectData = new ArrayList<>();

    public GridViewAdapter(Context mContext, ArrayList<Subject> mSubjectData) {
       this.mContext=mContext;
        this.mSubjectData=mSubjectData;
    }

//    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<Subject> data) {
//        super(context,layoutResourceId,data);
//        this.layoutResourceId = layoutResourceId;
//        this.context = context;
//        this.mSubjectData = data;
//
//    }

//    public GridViewAdapter(Context context, List<Subject> mSubjectData) {
//        mContext=context;
//        this.mSubjectData=mSubjectData;
//
//    }


//    public GridViewAdapter(Context context, int layoutResourceId, ArrayList<Subject> data) {
//        super(context, layoutResourceId, data);
//        this.layoutResourceId = layoutResourceId;
//        this.context = context;
//        this.data = data;
//    }


    @Override
    public int getCount() {
        Log.v("TAG","size== in adapter "+mSubjectData.size());
        return mSubjectData.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        ViewHolder holder;

        if (row == null) {
            row= ((Activity)mContext).getLayoutInflater().inflate(R.layout.grid_item_layout, null);
//            LayoutInflater inflater = ((Activity) mContext).getLayoutInflater();
//            row = inflater.inflate(layoutResourceId, parent, false);
            holder = new ViewHolder();
            holder.subject_name = (TextView)row.findViewById(R.id.subject_name);
            holder.image = (ImageView)row.findViewById(R.id.subject_image);
            row.setTag(holder);
        } else {
            holder = (ViewHolder)row.getTag();
        }
        Log.v("TAG", "photo==" + mSubjectData.get(position).getImage());

        Subject item = mSubjectData.get(position);
      holder.subject_name.setText(item.getSubjectName());
        holder.subject_name.setText(mSubjectData.get(position).getSubjectName());
       // holder.image.setImageBitmap(item.getImage());
        Log.v("minuSubjectImage",mSubjectData.get(position).getSubjectName());
        Glide
                .with(mContext)
                .load(mSubjectData.get(position).getImage())
                .placeholder(R.drawable.ic_noavatar) // can also be a drawable
                .into(holder.image);

        return row;
    }

    static class ViewHolder {
        TextView subject_name;
        ImageView image;
    }

    private class OnListItemClickListner {
    }
}