package com.example.horopter.listgallery;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {
    GridView lv;
    Context context;
    File file;
    ArrayList<String> paths;
    ArrayList<String> imageNames;
    File [] listFile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        paths = new ArrayList<>();
        imageNames = new ArrayList<>();
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED))
        {
            Toast.makeText(this, "Error! No SDCARD Found!", Toast.LENGTH_LONG)
                    .show();
        }
        else
        {
            file = new File("/storage/sdcard1/Images");
            Toast.makeText(this,String.valueOf(file),Toast.LENGTH_SHORT).show();
            Log.d("Santosh", String.valueOf(file));
        }
        getImages(file);
        context = this;
        lv = (GridView) findViewById(R.id.lv1);
        lv.setAdapter(new GridAdapter(this, paths, imageNames));
    }
    void getImages(File file)
    {
        if (file.isDirectory())
        {
            listFile = file.listFiles();
            for (int i = 0; i < listFile.length; i++)
            {
                    paths.add(listFile[i].getAbsolutePath());
                    imageNames.add(listFile[i].getName());
            }
        }
    }
}
