package com.example.pizzahut;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

public class Non_Veg_Pizza_Image extends BaseAdapter {
    Context context;
    Integer []images={
            R.drawable.one,R.drawable.four,R.drawable.three,R.drawable.five,R.drawable.six
    };

    Non_Veg_Pizza_Image(Context context)
    {
        this.context = context;
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
