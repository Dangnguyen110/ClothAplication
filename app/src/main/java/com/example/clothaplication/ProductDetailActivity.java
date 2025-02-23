package com.example.clothaplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    private ImageView ivProductDetailImage;
    private TextView tvProductDetailName, tvProductDetailPrice;
    private Button btnAddToCart, btnBackToMainFromDetail;

    // Danh sách giỏ hàng (tạm thời giữ trong bộ nhớ)
    public static ArrayList<Product> cart = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ivProductDetailImage = findViewById(R.id.product_image);
        tvProductDetailName = findViewById(R.id.product_name);
        tvProductDetailPrice = findViewById(R.id.product_price);
        btnAddToCart = findViewById(R.id.add_to_cart_button);
        btnBackToMainFromDetail = findViewById(R.id.btnBackToMainFromDetail);


        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String productName = intent.getStringExtra("name");
        int productPrice = intent.getIntExtra("price", 0);
        String productImage = intent.getStringExtra("imageUrl");

        // Hiển thị dữ liệu
        tvProductDetailName.setText(productName);
        tvProductDetailPrice.setText("Price: " + productPrice + " VND");

        // Sử dụng Picasso để tải ảnh
        Picasso.get().load(productImage).into(ivProductDetailImage);

        // Xử lý thêm sản phẩm vào giỏ hàng
        btnAddToCart.setOnClickListener(v -> {
            Product product = new Product(productName, productImage, productPrice);
            cart.add(product);
            Toast.makeText(this, "Added to category", Toast.LENGTH_SHORT).show();
        });
        // Xử lý quay lại màn hình chính
        btnBackToMainFromDetail.setOnClickListener(v -> {
            Intent backToMainIntent = new Intent(ProductDetailActivity.this, MainActivity.class);
            backToMainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(backToMainIntent);
            finish();
        });
    }
}
