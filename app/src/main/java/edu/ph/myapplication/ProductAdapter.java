package edu.ph.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {

    Context context;
    List<Product> productList;
    OnItemClickListener listener;
    OnAddToCartListener cartListener;

    public interface OnItemClickListener {
        void onItemClick(Product product);
    }

    public interface OnAddToCartListener {
        void onAddToCart(Product product);
    }

    public ProductAdapter(Context context, List<Product> productList,
                          OnItemClickListener listener, OnAddToCartListener cartListener) {
        this.context = context;
        this.productList = productList;
        this.listener = listener;
        this.cartListener = cartListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = productList.get(position);
        holder.tvTitle.setText(product.getTitle());
        holder.tvPrice.setText(String.format("P%.2f", product.getPrice()));
        holder.tvDescription.setText(product.getDescription());
        holder.imgProduct.setImageResource(product.getImageResource());

        holder.itemView.setOnClickListener(v -> listener.onItemClick(product));
        holder.btnAddToCart.setOnClickListener(v -> cartListener.onAddToCart(product));
    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imgProduct;
        TextView tvTitle, tvPrice, tvDescription;
        Button btnAddToCart;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgProduct = itemView.findViewById(R.id.imgProduct);
            tvTitle = itemView.findViewById(R.id.tvProductTitle);
            tvPrice = itemView.findViewById(R.id.tvProductPrice);
            tvDescription = itemView.findViewById(R.id.tvProductDescription);
            btnAddToCart = itemView.findViewById(R.id.btnAddToCart);
        }
    }
}
