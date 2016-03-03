package com.horopter.crgallery;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by Horopter on 3/3/2016.
 */
class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    protected ImageView photo;
    protected MyClickListener listener;
    public String path;

    public ViewHolder(View view) {
        super(view);
        this.photo = (ImageView) view.findViewById(R.id.imageView_photo);
        this.photo.setOnClickListener(this);
    }

    public void setMyClickListener(MyClickListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onClick(this);
    }
}

/**
 * Called when user taps recyclerview item
 */
interface MyClickListener {
    public void onClick(ViewHolder v);
}
