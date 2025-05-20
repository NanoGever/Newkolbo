package com.example.newkolbo.Activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newkolbo.Order;
import com.example.newkolbo.Perfume;
import com.example.newkolbo.R;

import java.text.BreakIterator;
import java.util.List;

public class OrderAdapter extends RecyclerView.Adapter<OrderAdapter.OrderViewHolder> {


    private List<Order> orderList;

    public OrderAdapter(List<Order> orderList) {
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.history_item, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, int position) {
        Order order = orderList.get(position);

        // הצגת הבשמים שנרכשו בלבד (amount > 0)
        StringBuilder perfumesInfo = new StringBuilder();
        for (Perfume perfume : order.getPerfumeList()) {
            if (perfume.getAmount() > 0) {
                perfumesInfo.append(perfume.getName())
                        .append(" x")
                        .append(perfume.getAmount())
                        .append("\n");
            }
        }

        holder.textProducts.setText(perfumesInfo.toString().trim());
        holder.textOrderNumber.setText("מספר הזמנה: " + order.getOrdernum());
        holder.textTotalPrice.setText("מחיר כולל: ₪" + order.getSumprice());
    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView textProducts, textOrderNumber, textTotalPrice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textProducts = itemView.findViewById(R.id.textViewProductName);
            textOrderNumber = itemView.findViewById(R.id.textViewOrderTime);
            textTotalPrice = itemView.findViewById(R.id.textViewTotalPrice);
        }
    }
}