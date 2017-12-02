package com.gadspiro.bharani.hack; /**
 * Created by Bharani on 12/1/17.
 */


import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.gadspiro.bharani.hack.R;

public class ImageAdapter extends BaseAdapter {
    private Context mContext;

    public String[] tabs={

            "Add Documents",
            "My Documents",
            "Send",
            "Status"

    };




    public String[] tabsdesc={

            "Click here to add new documents",
            "Click here to see existing documents",
            "Click here to send documents",
            "Click here to check document status"

    };


    // Keep all Images in array
    public Integer[] mThumbIds = {
            R.drawable.ic_note_add_white_48dp,
            R.drawable.ic_description_white_48dp,
            R.drawable.ic_input_white_48dp,
            R.drawable.ic_history_white_48dp
    };

    // Constructor
    public ImageAdapter(Context c){
        mContext = c;
    }

    @Override
    public int getCount() {
        return mThumbIds.length;
    }

    @Override
    public Object getItem(int position) {
        return mThumbIds[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public int[][] color={
            {203,74,75},
            {89,140,84},
            {67,81,174},
            {230,160,74}
    };



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final float scale = mContext.getResources().getDisplayMetrics().density;
        int pixels = (int) (150 * scale + 0.5f);

        LayoutInflater layoutInflater=LayoutInflater.from(mContext);
        View v=layoutInflater.inflate(R.layout.grid_item, parent,false);
        RelativeLayout relativeLayout=(RelativeLayout) v.findViewById(R.id.i);
        relativeLayout.setBackgroundColor(Color.rgb(color[position][0],color[position][1],color[position][2]));


        RelativeLayout.LayoutParams rel_btn = new RelativeLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, pixels);
        rel_btn.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
        relativeLayout.setLayoutParams(rel_btn);


        ImageView imageView=(ImageView) v.findViewById(R.id.img);
        imageView.setImageResource(mThumbIds[position]);
        TextView textView=(TextView) v.findViewById(R.id.t1);
        textView.setText(tabs[position]);
        TextView textView2=(TextView) v.findViewById(R.id.t2);
        textView2.setText(tabsdesc[position]);


        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

      //  imageView.setLayoutParams(new GridView.LayoutParams(500, 500));
        return v;
    }

}