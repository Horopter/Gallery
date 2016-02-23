package com.example.horopter.sangallery;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Path;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by Horopter on 2/13/2016.
 */
public class ImageViewer extends AppCompatActivity
{
    TextView text;
    ImageView imageview;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_image);
        Toast.makeText(this,"At least I got started", Toast.LENGTH_LONG).show();
        Intent i = getIntent();
        String filepath = i.getStringExtra("fN");
        int index = filepath.lastIndexOf(File.separator);
        String fileName = filepath.substring(index + 1);
        text = (TextView) findViewById(R.id.tv1);
        text.setText(fileName);
        imageview = (ImageView) findViewById(R.id.iv1);
        Bitmap bmp = BitmapFactory.decodeFile(filepath);
        imageview.setImageBitmap(bmp);
    }
    public void onStop()
    {
        super.onStop();
    }
}
