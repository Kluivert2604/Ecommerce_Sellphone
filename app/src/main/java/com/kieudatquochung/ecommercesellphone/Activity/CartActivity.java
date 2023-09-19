package com.kieudatquochung.ecommercesellphone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import com.kieudatquochung.ecommercesellphone.Adapter.CartListAdapter;
import com.kieudatquochung.ecommercesellphone.Helper.ChangNumberItemListener;
import com.kieudatquochung.ecommercesellphone.Helper.ManagementCart;
import com.kieudatquochung.ecommercesellphone.R;

import org.w3c.dom.Text;

public class CartActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerView;
    private ManagementCart managementCart;

    private TextView totalFeeTxt, taxTxt, deliveryTxt, totalTxt, emptyTxt;
    private double tax;
    private ScrollView scrollView;
    private ImageView backBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        managementCart = new ManagementCart(this);

        initView();
        setVariable();
        initList();
        calculateCart();
    }

    private void initList() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        adapter = new CartListAdapter(managementCart.getListCart(), this, new ChangNumberItemListener() {
            @Override
            public void change() {
                calculateCart();
            }
        });

        recyclerView.setAdapter(adapter);
        if (managementCart.getListCart().isEmpty()){
            emptyTxt.setVisibility(View.VISIBLE);
            scrollView.setVisibility(View.GONE);
        }else {
            emptyTxt.setVisibility(View.GONE);
            scrollView.setVisibility(View.VISIBLE);
        }
    }

    private void calculateCart() {
        double percentTax = 0.02;
        double delivery = 10;
        tax = Math.round((managementCart.getTotalFee()*percentTax*100.0)) / 100.0;

        double total = Math.round((managementCart.getTotalFee()+tax+delivery) * 100) / 100;
        double itemTotal = Math.round(managementCart.getTotalFee() * 100) / 100;

        totalFeeTxt.setText(itemTotal + "VND");
        taxTxt.setText(tax + "VND");
        deliveryTxt.setText(delivery+ "VND");
        totalTxt.setText(total + "VND");
    }

    private void setVariable() {
        backBtn.setOnClickListener(v -> finish());
    }

    private void initView() {
        totalFeeTxt = findViewById(R.id.txtTotalFee);
        taxTxt = findViewById(R.id.txtTotalTax);
        deliveryTxt = findViewById(R.id.txtDelivery);
        totalTxt = findViewById(R.id.txtTotal);
        recyclerView = findViewById(R.id.recycleCart);
        scrollView = findViewById(R.id.scrollView4);
        backBtn = findViewById(R.id.btnBack);
        emptyTxt = findViewById(R.id.emptyTxt);
    }
}