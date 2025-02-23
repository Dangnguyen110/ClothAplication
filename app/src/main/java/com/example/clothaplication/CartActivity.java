package com.example.clothaplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    private RecyclerView recyclerViewCart;
    private TextView tvCartTotal;
    private Button btnBackToMain;
    private ProductAdapter cartAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        recyclerViewCart = findViewById(R.id.recyclerViewCart);
        tvCartTotal = findViewById(R.id.tvCartTotal);
        btnBackToMain = findViewById(R.id.btnBackToMain);

        // Gắn Adapter hiển thị danh sách giỏ hàng
        cartAdapter = new ProductAdapter(this, ProductDetailActivity.cart, this::calculateTotal);
        recyclerViewCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewCart.setAdapter(cartAdapter);

        // Tính tổng tiền ban đầu
        calculateTotal();

        // Nút quay lại màn hình chính
        btnBackToMain.setOnClickListener(v -> {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        });
    }


    // Hàm tính tổng tiền
    private void calculateTotal() {
        int total = 0;
        for (Product product : ProductDetailActivity.cart) {
            total += product.getPrice();
        }
        tvCartTotal.setText("Tổng tiền: " + total + " VND");
    }

}
