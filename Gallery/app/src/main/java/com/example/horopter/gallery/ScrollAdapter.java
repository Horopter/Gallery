package com.example.horopter.gallery;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


import java.io.ByteArrayOutputStream;
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
    public static class Holder
    {
        public static ImageView img;
        public static TextView tv;
    }
    public ScrollAdapter(MainActivity mainActivity, ArrayList<String> imagesList, ArrayList<String> images) {
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
        /*Picasso.with(context)//Uri.fromFile(new File(result.get(position)))
                .load(getImageUri(context, ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(result.get(position)), 128, 128))) // didn't work
                .into(Holder.img);*/
        Bitmap bmp = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(result.get(position)),128,128);
        Holder.img.setImageBitmap(bmp);
        return v;
    }
    public Uri getImageUri(Context inContext, Bitmap inImage) {
        ByteArrayOutputStream bytes = new ByteArrayOutputStream();
        inImage.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
        String path = MediaStore.Images.Media.insertImage(inContext.getContentResolver(), inImage, "Title", null);
        return Uri.parse(path);
    }
}
