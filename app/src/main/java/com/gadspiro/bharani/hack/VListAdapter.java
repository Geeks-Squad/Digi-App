package com.gadspiro.bharani.hack;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Bharani on 12/2/17.
 */


public class VListAdapter extends BaseExpandableListAdapter {
    String []         result;

    Context context;
    int [] imageId;
    private static LayoutInflater inflater=null;



    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }

    @Override
    public int getGroupCount() {
        return super.getGroupTypeCount();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 0;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return null;
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return null;
    }

    @Override
    public long getGroupId(int groupPosition) {
        return 0;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return 0;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        return null;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {





        Holder holder=new Holder();
        View rowView;
        rowView = inflater.inflate(R.layout.content_doc_view, null);
        holder.tv=(TextView) rowView.findViewById(R.id.statval);
        holder.img=(ImageView) rowView.findViewById(R.id.doc_img);
        holder.tv.setText(result[childPosition]);
        holder.img.setImageResource(imageId[childPosition]);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(context, "You Clicked "+result[childPosition], Toast.LENGTH_LONG).show();
            }
        });
        return rowView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    public VListAdapter(Context contex, String[] prgmNameList, int[] prgmImages) {
        // TODO Auto-generated constructor stub
        context=contex;
        result=prgmNameList;
        imageId=prgmImages;


        inflater = ( LayoutInflater )context.
                getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public class Holder
    {
        TextView tv;
        ImageView img;
    }


}