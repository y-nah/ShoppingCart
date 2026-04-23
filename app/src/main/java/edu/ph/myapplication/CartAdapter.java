package edu.ph.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {

    Context context;
    List<Product> cartList;
    OnCartChangeListener listener;

    public interface OnCartChangeListener {
        void onCartChanged();
    }

    public CartAdapter(Context context, List<Product> cartList, OnCartChangeListener listener) {
        this.context = context;
        this.cartList = cartList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context)
                .inflate(R.layout.item_cart, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Product product = cartList.get(position);

        holder.tvTitle.setText(product.getTitle());
        holder.tvPrice.setText(String.format("P%.2f each", product.getPrice()));
        holder.tvQty.setText(String.valueOf(product.getQuantity()));
        holder.tvSubtotal.setText(String.format("Subtotal: P%.2f", product.getSubtotal()));

        holder.btnPlus.setOnClickListener(v -> {
            product.setQuantity(product.getQuantity() + 1);
            notifyItemChanged(position);
            listener.onCartChanged();
        });

        holder.btnMinus.setOnClickListener(v -> {
            if (product.getQuantity() > 1) {
                product.setQuantity(product.getQuantity() - 1);
                notifyItemChanged(position);
                listener.onCartChanged();
            }
        });

        holder.btnRemove.setOnClickListener(v -> {
            cartList.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, cartList.size());
            listener.onCartChanged();
        });
    }

    @Override
    public int getItemCount() {
        return cartList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvPrice, tvQty, tvSubtotal;
        Button btnPlus, btnMinus, btnRemove;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvCartItemTitle);
            tvPrice = itemView.findViewById(R.id.tvCartItemPrice);
            tvQty = itemView.findViewById(R.id.tvCartQty);
            tvSubtotal = itemView.findViewById(R.id.tvCartItemSubtotal);
            btnPlus = itemView.findViewById(R.id.btnCartPlus);
            btnMinus = itemView.findViewById(R.id.btnCartMinus);
            btnRemove = itemView.findViewById(R.id.btnRemove);
        }
    }
}
