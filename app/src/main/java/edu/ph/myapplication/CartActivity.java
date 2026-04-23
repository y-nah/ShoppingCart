package edu.ph.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    RecyclerView recyclerCart;
    CartAdapter cartAdapter;
    ArrayList<Product> cartList;

    TextView tvSubtotal, tvShipping, tvTax, tvGrandTotal;

    double shippingFee = 50.0;
    double taxRate = 0.12;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);

        recyclerCart = findViewById(R.id.recyclerCart);
        tvSubtotal = findViewById(R.id.tvSubtotal);
        tvShipping = findViewById(R.id.tvShipping);
        tvTax = findViewById(R.id.tvTax);
        tvGrandTotal = findViewById(R.id.tvGrandTotal);

        cartList = (ArrayList<Product>) getIntent().getSerializableExtra("cart_data");

        if (cartList == null) cartList = new ArrayList<>();

        cartAdapter = new CartAdapter(this, cartList, this::updateTotals);
        recyclerCart.setLayoutManager(new LinearLayoutManager(this));
        recyclerCart.setAdapter(cartAdapter);

        updateTotals();

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());

        findViewById(R.id.btnCheckout).setOnClickListener(v ->
                Toast.makeText(this, "Order placed! Thank you! 🎉", Toast.LENGTH_SHORT).show()
        );
    }

    private void updateTotals() {
        double subtotal = 0;
        for (Product p : cartList) {
            subtotal += p.getSubtotal();
        }
        double tax = subtotal * taxRate;
        double total = subtotal + shippingFee + tax;

        tvSubtotal.setText(String.format("P%.2f", subtotal));
        tvShipping.setText(String.format("P%.2f", shippingFee));
        tvTax.setText(String.format("P%.2f", tax));
        tvGrandTotal.setText(String.format("P%.2f", total));
    }
}
