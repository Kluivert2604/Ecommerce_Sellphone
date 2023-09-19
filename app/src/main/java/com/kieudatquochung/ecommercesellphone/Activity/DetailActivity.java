package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kieudatquochung.ecommercesellphone.Domain.PopularDomain;
import com.kieudatquochung.ecommercesellphone.Helper.ManagementCart;
import com.kieudatquochung.ecommercesellphone.R;

public class DetailActivity extends AppCompatActivity {
    private Button addToCartBtn;
    private TextView titleTxt, feeTxt, descriptionTxt, reviewTxt, scoreTxt;
    private ImageView picItem, backBtn;
    private PopularDomain object;
    private int numberOrder = 1;
    private ManagementCart managementCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        managementCart = new ManagementCart(this);
        initView();
        getBundle();
    }

    private void getBundle() {
        object = (PopularDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId= this.getResources().getIdentifier(object.getPicUrl(), "drawable", this.getPackageName());

        Glide.with(this)
                .load(drawableResourceId)
                .into(picItem);

        titleTxt.setText(object.getTitle());
        feeTxt.setText(object.getPrice() + "VND");
        descriptionTxt.setText(object.getDescription());
        reviewTxt.setText(object.getReview()+"");
        scoreTxt.setText(object.getScore()+"");

        addToCartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                object.setNumberInCart(numberOrder);
                managementCart.insertProduct(object);
            }
        });

        backBtn.setOnClickListener(v -> finish());

    }

    private void initView() {
        addToCartBtn = findViewById(R.id.btnAddToCart);
        feeTxt = findViewById(R.id.txtPrice1);
        titleTxt = findViewById(R.id.txtTitle1);
        descriptionTxt = findViewById(R.id.txtDescription);
        picItem = findViewById(R.id.itemPic);
        reviewTxt = findViewById(R.id.txtReview1);
        scoreTxt = findViewById(R.id.scoreTxt1);
        backBtn = findViewById(R.id.backBtn);

    }
}