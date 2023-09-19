package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.kieudatquochung.ecommercesellphone.Adapter.PopularListAdapter;
import com.kieudatquochung.ecommercesellphone.Domain.PopularDomain;
import com.kieudatquochung.ecommercesellphone.R;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapterPopular;
    private RecyclerView recyclerViewPopular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        bottom_navigation();
    }

    private void bottom_navigation() {
        LinearLayout homeBtn = findViewById(R.id.homeBtn);
        LinearLayout cartBtn = findViewById(R.id.cartBtn);

        homeBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, MainActivity.class)));

        cartBtn.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, CartActivity.class)));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("MacBook Pro 13 M2 chip", "hello", "pic1", 15, 4, 350000));
        items.add(new PopularDomain("PS5 Digital", "xin chao", "pic2", 10, 4.5, 5000000));
        items.add(new PopularDomain("Iphone 14", "what your name", "pic3", 13, 4.5, 450000));

        recyclerViewPopular = findViewById(R.id.recycleView1);
        recyclerViewPopular.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        adapterPopular = new PopularListAdapter(items);
        recyclerViewPopular.setAdapter(adapterPopular);
    }
}