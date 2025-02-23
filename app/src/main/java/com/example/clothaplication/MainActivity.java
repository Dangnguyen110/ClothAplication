package com.example.clothaplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerViewShirts, recyclerViewPants;
    private ProductAdapter shirtsAdapter, pantsAdapter;
    private ArrayList<Product> shirtsList, pantsList;
    private Button btnLogout, btnViewCart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerViewShirts = findViewById(R.id.recyclerViewShirts);
        recyclerViewPants = findViewById(R.id.recyclerViewPants);
        btnLogout = findViewById(R.id.btnLogout);

        recyclerViewShirts.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        recyclerViewPants.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        // Initialize product lists
        shirtsList = new ArrayList<>();
        pantsList = new ArrayList<>();

        // Add sample data
        shirtsList.add(new Product("T-shirt", "https://www.pngarts.com/files/4/Dress-Shirt-PNG-Free-Download.png", 150000));
        shirtsList.add(new Product("Shirt", "https://png.pngtree.com/png-vector/20240205/ourmid/pngtree-black-t-shirt-mockup-realistic-t-shirt-png-image_11645796.png", 250000));
        shirtsList.add(new Product("Jacket", "https://static.vecteezy.com/system/resources/previews/036/277/065/non_2x/ai-generated-red-winter-jacket-isolated-on-transparent-background-free-png.png", 350000));

        pantsList.add(new Product("Jeans", "https://png.pngtree.com/png-vector/20231115/ourmid/pngtree-folded-old-blue-jeans-clothing-png-image_10594549.png", 300000));
        pantsList.add(new Product("Kaki", "https://png.pngtree.com/png-clipart/20240103/original/pngtree-mens-khaki-pants-isolated-on-a-white-background-3d-rendering-png-image_14002878.png", 200000));
        pantsList.add(new Product("Short Pants", "https://example.com/pants3.jpg", 100000));

        // Set up adapters
        shirtsAdapter = new ProductAdapter(this, shirtsList, null);
        pantsAdapter = new ProductAdapter(this, pantsList, null);

        recyclerViewShirts.setAdapter(shirtsAdapter);
        recyclerViewPants.setAdapter(pantsAdapter);

        // Logout button
        btnLogout.setOnClickListener(v -> {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        });

        btnViewCart = findViewById(R.id.btnViewCart);

        btnViewCart.setOnClickListener(v -> {
            startActivity(new Intent(this, CartActivity.class));
        });


    }
}
