package com.example.clothaplication;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductViewHolder> {

    private final Context context;
    private final ArrayList<Product> productList;
    private final Runnable updateTotalCallback; // Callback for updating total price if used in the cart

    public ProductAdapter(Context context, ArrayList<Product> productList, Runnable updateTotalCallback) {
        this.context = context;
        this.productList = productList;
        this.updateTotalCallback = updateTotalCallback;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false);
        return new ProductViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        Product product = productList.get(position);

        // Display product details
        holder.productName.setText(product.getName());
        holder.productPrice.setText(product.getPrice() + " VND");
        Picasso.get().load(product.getImageUrl()).into(holder.productImage);

        // Open product details on item click
        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductDetailActivity.class);
            intent.putExtra("name", product.getName());
            intent.putExtra("price", product.getPrice());
            intent.putExtra("imageUrl", product.getImageUrl());
            context.startActivity(intent);
        });

        // Show/remove product from cart
        if (updateTotalCallback != null) {
            holder.btnRemove.setVisibility(View.VISIBLE); // Show remove button
            holder.btnRemove.setOnClickListener(v -> {
                productList.remove(position); // Remove product from list
                notifyItemRemoved(position); // Update RecyclerView
                notifyItemRangeChanged(position, productList.size()); // Recalculate item indices
                updateTotalCallback.run(); // Trigger total price update
            });
        } else {
            holder.btnRemove.setVisibility(View.GONE); // Hide remove button when not in cart
        }
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView productName, productPrice;
        ImageView productImage;
        Button btnRemove;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            productName = itemView.findViewById(R.id.tvProductName);
            productPrice = itemView.findViewById(R.id.tvProductPrice);
            productImage = itemView.findViewById(R.id.ivProductImage);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
