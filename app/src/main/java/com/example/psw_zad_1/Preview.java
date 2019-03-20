package com.example.psw_zad_1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;


public class Preview extends AppCompatActivity {

    private static final String KEY = "index";
    private RecyclerView recyclerView;
    private int currentIndex;
    private ArrayList images;

    @Override
    protected void onCreate(Bundle savedInstanceState) throws NullPointerException{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        images = MainActivity.images;
        if(getIntent().getExtras() != null) {
            currentIndex = getIntent().getExtras().getInt(KEY);
            getIntent().getExtras().clear();
        } else
            currentIndex = -1;
        initView();
        recyclerView.scrollToPosition(currentIndex + Integer.MAX_VALUE/2);
    }

    public void initView(){
        recyclerView = findViewById(R.id.horizontal_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(new MyPreviewAdapter(this, images, currentIndex));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();
                View firstVisibleItem = linearLayoutManager.getChildAt(0);
                int toScroll;
                if(Math.abs(firstVisibleItem.getLeft()) > (firstVisibleItem.getWidth() / 2))
                    toScroll = firstVisibleItem.getRight();
                else
                    toScroll = firstVisibleItem.getLeft();

                recyclerView.smoothScrollBy(toScroll, 0);
            }
        });
    }
}
