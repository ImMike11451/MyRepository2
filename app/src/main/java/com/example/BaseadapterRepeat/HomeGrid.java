package com.example.BaseadapterRepeat;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.Bean.Row;
import com.example.Bean.ServeBean;
import com.example.smartcities.Home;
import com.example.smartcities.R;

import java.util.ArrayList;
import java.util.List;

public class HomeGrid extends BaseAdapter {

    private Context mcontext;
    private List<Row> mlist;

     public HomeGrid(Context context, List<Row> list) {
        this.mcontext = context;
        this.mlist = list;
    }

    public HomeGrid(Home context) {
    }

    @Override
    public int getCount() {
        return Math.min(mlist.size(),8);
    }

    @Override
    public Object getItem(int position) {
        return mlist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = LayoutInflater.from(mcontext).inflate(R.layout.homegridview,null,false);

        TextView title =convertView.findViewById(R.id.iconname);
        ImageView img = convertView.findViewById(R.id.AllServeImg);
        title.setText(mlist.get(position).getServiceName());
        Glide.with(mcontext).load("http://10.35.249.105:10011" + mlist.get(position).getImgUrl()).into(img);
        return convertView;
    }
}
