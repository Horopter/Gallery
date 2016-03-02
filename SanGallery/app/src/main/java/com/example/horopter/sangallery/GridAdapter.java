package com.example.horopter.sangallery;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.List;

/**
 * Created by Horopter on 2/11/2016.
 */
public class GridAdapter  extends RecyclerView.Adapter<GridAdapter.ViewHolder> {
    private List<File> fileList;
    private Context c;
    private LayoutInflater inf;
    private int pos;
    public GridAdapter(List<File> fileList, Context con){
        this.fileList = fileList;
        inf = LayoutInflater.from(con);
        c = con;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inf.inflate(R.layout.grid_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        final File imgf = fileList.get(position);
        pos = position;
        Glide.with(c).load(imgf).thumbnail(0.5f).centerCrop().into(holder.getImageView());
        holder.getTextView().setText(imgf.getName());
        /*holder.getImageView().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ImageViewer.class);
                intent.putExtra("fN", imgf.getAbsolutePath());
                v.getContext().startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount() {
        return fileList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView imgv;
        private TextView tv;

        public ViewHolder(View view) {
            super(view);
            imgv = (ImageView)view.findViewById(R.id.imageView);
            tv = (TextView)view.findViewById(R.id.textView);
            view.setOnClickListener(this);
        }
        public ImageView getImageView()
        {
            return imgv;
        }
        public TextView getTextView()
        {
            return tv;
        }
        public void onClick(View view)
        {
            Intent i = new Intent(c,ImageViewer.class);
            i.putExtra("fN",fileList.get(pos).getAbsolutePath());
            c.startActivity(i);
        }
    }
}