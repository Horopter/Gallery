package com.example.horopter.gallery;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    GridView lv;
    TextView tv;
    Context context;
    ArrayList<String> paths;
    ArrayList<String> imageNames;
    ArrayList<File> listFile;
    public static String f = "/storage/sdcard1/thumbs";
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paths = new ArrayList<>();
        imageNames = new ArrayList<>();
        context = this;
        Intent i = getIntent();
        paths = i.getStringArrayListExtra("filepath");
        imageNames = i.getStringArrayListExtra("filename");
        lv = (GridView) findViewById(R.id.lv1);
        tv = (TextView) findViewById(R.id.tv1);
        tv.setText(tv.getText()+" "+listFile.size());
        ScrollAdapter ga = new ScrollAdapter(this, paths, imageNames);
        lv.setAdapter(ga);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id) {
                Intent i = new Intent(MainActivity.this, ImageViewer.class);
                i.putExtra("filepath",paths);
                i.putExtra("filename", imageNames);
                i.putExtra("position", position);
                startActivity(i);
            }

        });
    }
}

