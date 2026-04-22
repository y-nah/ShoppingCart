package edu.ph.myapplication;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class CartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart2);

        TextView tvDetails = findViewById(R.id.tvCartDetails);
        TextView tvTotal = findViewById(R.id.tvGrandTotal);

        ArrayList<Product> list = (ArrayList<Product>) getIntent().getSerializableExtra("cart_data");

        if (list != null && !list.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            double grandTotal = 0;

            for (Product p : list) {
                sb.append(p.getTitle()).append("\n")
                        .append("Qty: ").append(p.getQuantity())
                        .append(" | Price: P").append(p.getPrice())
                        .append("\nSubtotal: P").append(p.getSubtotal())
                        .append("\n--------------------------\n");

                grandTotal += p.getSubtotal();
            }

            tvDetails.setText(sb.toString());
            tvTotal.setText("P" + String.format("%.2f", grandTotal));
        }

        findViewById(R.id.btnBack).setOnClickListener(v -> finish());
    }
}