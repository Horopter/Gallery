package com.example.horopter.gallery;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.ThumbnailUtils;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;

/**
 * Created by Horopter on 2/15/2016.
 */
public class SplashscreenActivity extends AppCompatActivity {
    public static String f = "/storage/sdcard1/thumbs";
    String file;
    ArrayList<String> paths;
    ArrayList<String> imageNames;
    ArrayList<File> listFile;
    Context context;
    private ProgressDialog progressDialog;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        context = this;
        //Initialize a LoadViewTask object and call the execute() method
        new LoadViewTask().execute();

    }

    //To use the AsyncTask, it must be subclassed
    private class LoadViewTask extends AsyncTask<Void, Integer, Void>
    {
        //Before running code in separate thread
        @Override
        protected void onPreExecute()
        {
            progressDialog = ProgressDialog.show(SplashscreenActivity.this,"Loading...",
                    "Loading Gallery, please wait...", false, false);
        }

        //The code to be executed in a background thread.
        @Override
        protected Void doInBackground(Void... params)
        {
            try
            {
                synchronized (this)
                {
                    paths = new ArrayList<>();
                    imageNames = new ArrayList<>();
                    if (!Environment.getExternalStorageState().equals(
                            Environment.MEDIA_MOUNTED))
                    {
                        Toast.makeText(context, "Error! No SDCARD Found!", Toast.LENGTH_LONG)
                                .show();
                    }
                    else
                    {
                        file = "/storage/sdcard1/Images";
                        listFile = new ArrayList<>();
                        Toast.makeText(context,String.valueOf(file),Toast.LENGTH_LONG).show();
                        Log.d("Santosh", String.valueOf(file));
                    }
                    publishProgress(25);
                    listf(file, listFile);
                    publishProgress(50);
                    getImages(listFile);
                    publishProgress(75);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
            return null;
        }

        //Update the progress
        @Override
        protected void onProgressUpdate(Integer... values)
        {
            //set the current progress of the progress dialog
            progressDialog.setProgress(values[0]);
        }

        //after executing the code in the thread
        @Override
        protected void onPostExecute(Void result)
        {
            //close the progress dialog
            progressDialog.dismiss();
            //initialize the View
            setContentView(R.layout.main);
        }
    }
    public void sms(String imageId,String result)
    {
        Bitmap bmp;
        if(!new File(f,"Thumb-"+imageId).exists()) {
            bmp = ThumbnailUtils.extractThumbnail(BitmapFactory.decodeFile(result), 128, 128);
            saveImageToExternalStorage(bmp,"Thumb-"+imageId);
        }
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
                    if(file.getName().endsWith(".jpg")||file.getName().endsWith(".jpeg")||file.getName().endsWith(".png")||file.getName().endsWith(".gif"))
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
            sms(f.getName(),f.getAbsolutePath());
        }

    }
    public boolean saveImageToExternalStorage(Bitmap image,String name) {
        String fullPath = f;
        try {
            File dir = new File(fullPath);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            OutputStream fOut = null;
            File file = new File(fullPath,name);
            file.createNewFile();
            fOut = new FileOutputStream(file);
            image.compress(Bitmap.CompressFormat.PNG, 100, fOut);
            fOut.flush();
            fOut.close();
            MediaStore.Images.Media.insertImage(context.getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
            return true;
        } catch (Exception e) {
            Log.e("saveToExternalStorage()", e.getMessage());
            return false;
        }
    }
}
