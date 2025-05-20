package com.example.newkolbo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.Date;

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

        // הצגת מוצרים שנרכשו בלבד (amount > 0)
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

        // הצגת תאריך הזמנה
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault());
        String formattedDate = sdf.format(new Date(order.getDate()));
        holder.textOrderDate.setText("תאריך הזמנה: " + formattedDate);
    }


    @Override
    public int getItemCount() {
        return orderList.size();
    }

    static class OrderViewHolder extends RecyclerView.ViewHolder {
        TextView textOrderDate;

        TextView textProducts, textOrderNumber, textTotalPrice;

        public OrderViewHolder(@NonNull View itemView) {
            super(itemView);
            textProducts = itemView.findViewById(R.id.textViewProductName);
            textOrderNumber = itemView.findViewById(R.id.textViewOrderTime);
            textTotalPrice = itemView.findViewById(R.id.textViewTotalPrice);
            textOrderDate = itemView.findViewById(R.id.textOrderDate);
        }
    }
}