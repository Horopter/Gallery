package com.example.horopter.listgallery;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity
{
    GridView lv;
    Context context;
    String file;
    ArrayList<String> paths;
    ArrayList<String> imageNames;
    ArrayList<File> listFile;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
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
            file = new String("/storage/sdcard1/Images");
            listFile = new ArrayList<>();
            Toast.makeText(this,String.valueOf(file),Toast.LENGTH_SHORT).show();
            Log.d("Santosh", String.valueOf(file));
        }
        listf(file,listFile);
        getImages(listFile);
        context = this;
        lv = (GridView) findViewById(R.id.lv1);
        GridAdapter ga = new GridAdapter(this, paths, imageNames);
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
    public void listf(String directoryName, ArrayList<File> files)
    {
        File directory = new File(directoryName);
        File[] fList = directory.listFiles();
        if(fList!=null)
        for (File file : fList)
        {
            if (file.isFile())
            {
                files.add(file);
            }
            else if (file.isDirectory())
            {
                listf(file.getAbsolutePath(), files);
            }
        }
    }
    public void getImages(ArrayList<File> file)
    {
        for (File f : file)
            {
                paths.add(f.getAbsolutePath());
                imageNames.add(f.getName());
            }
    }
}

