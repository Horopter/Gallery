package com.example.horopter.listgallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Horopter on 2/7/2016.
 */
public class GridAdapter extends BaseAdapter {
    ArrayList<String> result;
    Context context;
    ArrayList<String> imageId;
    private static LayoutInflater inflater = null;
    public static class Holder
    {
        public static ImageView img;
        public static TextView tv;
    }
    public GridAdapter(MainActivity mainActivity, ArrayList<String> imagesList, ArrayList<String> images) {
        result = imagesList;
        context = mainActivity;
        imageId = images;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        if(result==null)
            return 0;
        return result.size();
    }

    @Override
    public Object getItem(int position)
    {
        return result.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View v = inflater.inflate(R.layout.image_list,null);
        Holder.tv = (TextView) v.findViewById(R.id.tv1);
        Holder.img = (ImageView) v.findViewById(R.id.iv1);
        Holder.tv.setText(imageId.get(position));
        Bitmap bmp = BitmapFactory.decodeFile(result.get(position));
        Holder.img.setImageBitmap(bmp);
        return v;
    }
}
