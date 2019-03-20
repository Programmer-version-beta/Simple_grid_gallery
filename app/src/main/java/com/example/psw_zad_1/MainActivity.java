package com.example.psw_zad_1;

import android.Manifest;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private static final int MEMORY_ACCESS_KEY = 4;
    public static ArrayList <Integer> images = new ArrayList <>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE))
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, MEMORY_ACCESS_KEY);
        if(images.size() == 0)
            createGallery();
        initRecycleView();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
}

    private void initRecycleView(){
        recyclerView = findViewById(R.id.grid_list);
        recyclerView.setAdapter(new MyAdapter(this, images));
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)
            recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        else
            recyclerView.setLayoutManager(new GridLayoutManager(this, 4));
        recyclerView.setHasFixedSize(true);
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == 0) {
                    GridLayoutManager  gridLayoutManager = (GridLayoutManager)recyclerView.getLayoutManager();
                    View firstVisibleImage = recyclerView.getChildAt(0);
                    View lastVisibleImage = recyclerView.getChildAt(gridLayoutManager.findLastVisibleItemPosition() - gridLayoutManager.findFirstVisibleItemPosition());
                    int firstImageTop = firstVisibleImage.getTop();
                    int firstImageBottom = firstVisibleImage.getBottom();
                    int lastImageBottom = lastVisibleImage.getBottom();
                    int toScroll;

                    if (firstImageBottom > firstImageTop * (-1)) {
                        if (lastImageBottom == recyclerView.getHeight() && gridLayoutManager.findLastCompletelyVisibleItemPosition() == images.size() - 1)
                            return;
                        else {
                            toScroll = firstImageTop;
                        }
                    } else {
                        toScroll = firstImageBottom;
                        if (gridLayoutManager.findLastCompletelyVisibleItemPosition() + 1 == images.size()) {
                            if (lastImageBottom - recyclerView.getHeight() < toScroll)
                                toScroll = lastImageBottom - recyclerView.getHeight();
                        }
                    }
                    recyclerView.smoothScrollBy(0, toScroll);
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    void createGallery(){
        images.add(R.mipmap.image1);
        images.add(R.mipmap.image2);
        images.add(R.mipmap.image3);
        images.add(R.mipmap.image4);
        images.add(R.mipmap.image5);
        images.add(R.mipmap.image6);
        images.add(R.mipmap.image7);
        images.add(R.mipmap.image8);
        images.add(R.mipmap.image9);
        images.add(R.mipmap.image10);
        images.add(R.mipmap.image11);
        images.add(R.mipmap.image12);
        images.add(R.mipmap.image13);
        images.add(R.mipmap.image14);
        images.add(R.mipmap.image15);
        images.add(R.mipmap.image16);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch(requestCode){
            case MEMORY_ACCESS_KEY:
                if(!(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                    Toast.makeText(getApplicationContext(), R.string.no_access, Toast.LENGTH_LONG).show();
                }
        }
    }
}
