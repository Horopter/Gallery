package com.example.horopter.sangallery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private RecyclerView.Adapter adp;
    private RecyclerView.LayoutManager lm;
    private List<File> fileList;
    private ArrayList<String> fileNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File root = new File("/storage/sdcard1/Images");
        Toast.makeText(this,root.getAbsolutePath(),Toast.LENGTH_LONG).show();
        rv = (RecyclerView) findViewById(R.id.recyclerView);
        rv.setHasFixedSize(true);
        lm = new GridLayoutManager(this,3);
        rv.setLayoutManager(lm);
        fileList = ImageFinder.dfsImageFind(root);
        adp = new GridAdapter(fileList,this);
        rv.setAdapter(adp);
        fileNames = getNames();
        /*ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                Intent i = new Intent(getApplicationContext(),ImageViewer.class);
                i.putExtra("fN",fileNames.get(position));
                startActivity(i);
            }
        });*/
        /*rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view, int position) {
                        // TODO Handle item click
                        Intent i = new Intent(getApplicationContext(),ImageViewer.class);
                        i.putExtra("fN",fileNames.get(position));
                        startActivity(i);
                    }
                })
        );*/

    }
    public ArrayList<String> getNames()
    {
        ArrayList<String> fileNames = new ArrayList<>();
        for(File f: fileList)
        {
            fileNames.add(f.getAbsolutePath());
        }
        return fileNames;
    }
}
