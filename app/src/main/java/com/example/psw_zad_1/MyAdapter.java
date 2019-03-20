package com.example.psw_zad_1;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder>{

    private Context context;
    private ArrayList images;

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        MyViewHolder(View v) {
            super(v);
            imageView = v.findViewById(R.id.item_image);
        }
    }

    MyAdapter(Context context, ArrayList images){
        this.context = context;
        this.images = images;
    }

    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull final ViewGroup viewGroup, int i) {
        View view =  LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyAdapter.MyViewHolder myViewHolder, int i) {
        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(context, Preview.class).putExtra("index", myViewHolder.getAdapterPosition()));
            }
        });

        DisplayMetrics displaymetrics = new DisplayMetrics();
        ((Activity) context).getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int deviceWidth;
        int deviceHeight;

        if(context.getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            deviceWidth = (displaymetrics.widthPixels - 16)/ 2;
            deviceHeight = (displaymetrics.heightPixels - 20) / 4;
        }
        else{
            deviceWidth = (displaymetrics.widthPixels - 20) / 4;
            deviceHeight = (displaymetrics.heightPixels - 16) / 2;
        }

        myViewHolder.imageView.getLayoutParams().width = deviceWidth;
        myViewHolder.imageView.getLayoutParams().height = deviceHeight;
        myViewHolder.imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Picasso.with(context)
                .load(MainActivity.images.get(i))
                .centerCrop()
                .resize(deviceWidth, deviceHeight)
                .into(myViewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return images.size();
    }
}