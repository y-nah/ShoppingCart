package edu.ph.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class ProductDetailActivity extends AppCompatActivity {

    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);

        ImageView imgDetail = findViewById(R.id.imgDetail);
        TextView tvTitle = findViewById(R.id.tvDetailTitle);
        TextView tvPrice = findViewById(R.id.tvDetailPrice);
        TextView tvDescription = findViewById(R.id.tvDetailDescription);
        TextView tvQuantity = findViewById(R.id.tvQuantity);
        TextView tvSubtotal = findViewById(R.id.tvSubtotal);
        Button btnMinus = findViewById(R.id.btnMinus);
        Button btnPlus = findViewById(R.id.btnPlus);
        Button btnAddToCart = findViewById(R.id.btnAddToCart);

        Product product = (Product) getIntent().getSerializableExtra("EXTRA_PRODUCT");

        if (product != null) {
            imgDetail.setImageResource(product.getImageResource());
            tvTitle.setText(product.getTitle());
            tvPrice.setText(String.format("P%.2f", product.getPrice()));
            tvDescription.setText(product.getDescription());
            tvSubtotal.setText(String.format("Subtotal: P%.2f", product.getPrice()));

            btnMinus.setOnClickListener(v -> {
                if (quantity > 0) {
                    quantity--;
                    tvQuantity.setText(String.valueOf(quantity));
                    tvSubtotal.setText(String.format("Subtotal: P%.2f", product.getPrice() * quantity));
                }
            });

            btnPlus.setOnClickListener(v -> {
                quantity++;
                tvQuantity.setText(String.valueOf(quantity));
                tvSubtotal.setText(String.format("Subtotal: P%.2f", product.getPrice() * quantity));
            });

            btnAddToCart.setOnClickListener(v -> {
                ArrayList<Product> cartList = new ArrayList<>();
                cartList.add(new Product(product.getTitle(), product.getPrice(), quantity));

                Intent intent = new Intent(ProductDetailActivity.this, CartActivity.class);
                intent.putExtra("cart_data", cartList);
                startActivity(intent);
            });
        }
    }
}