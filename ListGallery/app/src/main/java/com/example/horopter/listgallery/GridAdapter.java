package com.example.horopter.listgallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
        TextView tv = (TextView) v.findViewById(R.id.tv1);
        ImageView img = (ImageView) v.findViewById(R.id.iv1);
        tv.setText(imageId.get(position));
        Bitmap bmp = BitmapFactory.decodeFile(result.get(position));
        img.setImageBitmap(bmp);
        return v;
    }
}
