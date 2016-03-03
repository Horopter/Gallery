package com.horopter.crgallery;

import android.database.Cursor;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    private static final int COUNT_ITEMS_IN_LINE = 3;

    private RecyclerView mRecyclerView;
    private Cursor mGalleryCursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(getActionBar() != null){
            getActionBar().setDisplayHomeAsUpEnabled(true);
        }
        String[] projection = new String[]{ MediaStore.MediaColumns.DATA };
        mGalleryCursor = getContentResolver().query(
                MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                projection, null, null,
                null);

        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager glm = new GridLayoutManager(this, COUNT_ITEMS_IN_LINE);
        mRecyclerView.setLayoutManager(glm);
        mRecyclerView.setAdapter(new MyRecyclerAdapter(this, mGalleryCursor, glm.getSpanCount()));

    }
}
