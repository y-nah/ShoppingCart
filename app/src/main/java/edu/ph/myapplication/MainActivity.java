package edu.ph.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProductAdapter adapter;
    List<Product> productList;
    ArrayList<Product> cartList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        productList = new ArrayList<>();
        productList.add(new Product(1, "Kitty Plushy", 850.0, 1, "Adorable Hello Kitty plush toy, super soft!", R.drawable.kitty));
        productList.add(new Product(2, "Cinnamoroll", 920.0, 1, "Fluffy white puppy with big blue eyes.", R.drawable.cinnamon));
        productList.add(new Product(3, "Kuromi Goth", 950.0, 1, "Edgy Kuromi in her signature black hood.", R.drawable.kuromi));
        productList.add(new Product(4, "My Melody", 850.0, 1, "Sweet My Melody in a pink bunny hood.", R.drawable.melody));
        productList.add(new Product(5, "Pompompurin", 880.0, 1, "Cheerful golden retriever with a beret.", R.drawable.pomporin));
        productList.add(new Product(6, "Pochacco Dog", 820.0, 1, "Sporty Pochacco, loves soccer and running!", R.drawable.pochaco));

        adapter = new ProductAdapter(this, productList,
                product -> {
                    Intent intent = new Intent(MainActivity.this, ProductDetailActivity.class);
                    intent.putExtra("EXTRA_PRODUCT", product);
                    startActivity(intent);
                },
                product -> {
                    boolean found = false;
                    for (Product p : cartList) {
                        if (p.getTitle().equals(product.getTitle())) {
                            p.setQuantity(p.getQuantity() + 1);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        cartList.add(new Product(product.getTitle(), product.getPrice(), 1));
                    }
                    Toast.makeText(this, product.getTitle() + " added to cart!", Toast.LENGTH_SHORT).show();
                });

        recyclerView.setAdapter(adapter);


        findViewById(R.id.btnViewCart).setOnClickListener(v -> {
            if (cartList.isEmpty()) {
                Toast.makeText(this, "Your cart is empty!", Toast.LENGTH_SHORT).show();
            } else {
                Intent intent = new Intent(MainActivity.this, CartActivity.class);
                intent.putExtra("cart_data", cartList);
                startActivity(intent);
            }
        });
    }
}
