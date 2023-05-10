package com.anpln.vkv11;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomBaseAdapter extends BaseAdapter {

    Context context;
    String[] nameList;
    Long[] sizeList;
    Long[] dateList;
    int[] images;
    LayoutInflater layoutInflater;

    public CustomBaseAdapter(Context ctx, ArrayList<String> nameList, ArrayList<Long> sizeList, ArrayList<Long> dateList,  int[] images){
        this. context = ctx;
        this.nameList = nameList.toArray(new String[0]);
        this.sizeList = sizeList.toArray(new Long[0]);
        this.dateList = dateList.toArray(new Long[0]);
        this.images = images;
        layoutInflater = LayoutInflater.from(ctx);
    }


    @Override
    public int getCount() {
        return nameList.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = layoutInflater.inflate(R.layout.activity_custom_file, null);
        TextView txtViewName =  (TextView) view.findViewById(R.id.textViewName);
        TextView txtViewSize =  (TextView) view.findViewById(R.id.textViewSize);
        TextView txtViewDate =  (TextView) view.findViewById(R.id.textViewDate);
        ImageView typeImg = (ImageView) view.findViewById(R.id.imageType);
        txtViewName.setText(nameList[i]);
        txtViewSize.setText(Math.toIntExact(sizeList[i]));
        txtViewDate.setText(Math.toIntExact(dateList[i]));
        typeImg.setImageResource(images[i]);
        return view;
    }
}
