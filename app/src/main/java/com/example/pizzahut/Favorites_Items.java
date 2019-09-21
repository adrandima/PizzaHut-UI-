package com.example.pizzahut;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.pizzahut.Service.MyDBHandler;

import java.util.ArrayList;

public class Favorites_Items extends BaseAdapter {
    Context context;
    Integer []images={
                    R.drawable.four
            };

    MyDBHandler db;
    Favorites_Items(Context context)
    {
        this.context = context;
        db = new MyDBHandler(context);
        ArrayList<String> loc = db.getAllFavorite();
        for (String loc1: loc){
            if(loc.size()>0) {
                images= new Integer[]{
                        R.drawable.one,
                        R.drawable.four
                };
            }
            else {
                images= new Integer[]{
                        R.drawable.four
                };
            }
        }
    }
    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Object getItem(int position) {
        return images[position];
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ImageView imageView = new ImageView(context);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        imageView.setLayoutParams(new GridView.LayoutParams(1040,500));
        return imageView;
    }
}