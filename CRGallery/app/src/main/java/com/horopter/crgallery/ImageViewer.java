package com.horopter.crgallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;


/**
 * Created by Horopter on 3/3/2016.
 */
public class ImageViewer extends AppCompatActivity
{

    ImageView iv1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image);
        iv1 = (ImageView)findViewById(R.id.iv1);
        iv1.setImageResource(R.mipmap.ic_launcher);
        Intent i = getIntent();
        String fp = i.getStringExtra("filepath");
        Bitmap bmp = BitmapFactory.decodeFile(fp);
        iv1.setImageBitmap(bmp);
    }
    public void onStop()
    {
        super.onStop();
    }
}
