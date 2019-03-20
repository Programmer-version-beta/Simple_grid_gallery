package com.example.psw_zad_1;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MyPreviewAdapter extends RecyclerView.Adapter<MyPreviewAdapter.MyViewHolder>{

    private Context context;
    private ArrayList images;
    private int selectedImageIndex;
    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.item_image);
        }
    }

    MyPreviewAdapter(Context context, ArrayList images, int currentIndex){
        this.context = context;
        this.images = images;
        this.selectedImageIndex = currentIndex;
    }

    @NonNull
    @Override
    public MyPreviewAdapter.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, final int i) {
        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int deviceWidth = displaymetrics.widthPixels;
        int deviceHeight = displaymetrics.heightPixels;
        int currentImageIndex = myViewHolder.getAdapterPosition() % images.size() + 1;
        if(currentImageIndex == images.size())
            currentImageIndex = 0;

        myViewHolder.imageView.getLayoutParams().width = deviceWidth;
        myViewHolder.imageView.getLayoutParams().height = deviceHeight;

        Picasso.with(context)
                .load(MainActivity.images.get(currentImageIndex))
                .centerInside()
                .resize(deviceWidth, deviceHeight)
                .into(myViewHolder.imageView);

        if(currentImageIndex == selectedImageIndex) {
            RotateAnimation anim = new RotateAnimation(0f, 350f, 15f, 15f);
            anim.setInterpolator(new LinearInterpolator());
            anim.setRepeatCount(0);
            anim.setDuration(700);

            myViewHolder.imageView.startAnimation(anim);
            selectedImageIndex = -1;
        }
    }

    @Override
    public int getItemCount() {
        return Integer.MAX_VALUE;
    }
}