package com.example.horopter.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import static android.support.v4.app.ActivityCompat.startActivity;

/**
 * Created by Horopter on 2/7/2016.
 */
public class ScrollAdapter extends BaseAdapter {
    ArrayList<String> result;
    Context context;
    ArrayList<String> imageId;
    private static LayoutInflater inflater = null;
    private String f;
    public static class Holder
    {
        public static ImageView img;
        public static TextView tv;
    }
    public ScrollAdapter(MainActivity mainActivity, ArrayList<String> imagesList, ArrayList<String> images) {
        result = imagesList;
        context = mainActivity;
        imageId = images;
        inflater = LayoutInflater.from(context);
        f = "/storage/sdcard1/thumbs";
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
        Bitmap bmp= BitmapFactory.decodeFile(f + "/" + "Thumb-"+imageId.get(position));
        Holder.img.setImageBitmap(bmp);
        return v;
    }
}
