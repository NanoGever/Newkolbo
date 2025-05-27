package com.example.newkolbo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class PerfumeAdapter extends ArrayAdapter<Perfume> {
    private Context context;
    private ArrayList<Perfume> list;
    private ArrayList<Perfume> fullList; // לשמירה על המקור לצורך סינון

    public PerfumeAdapter(@NonNull Context context, ArrayList<Perfume> list) {
        super(context, R.layout.item_perfume, list);
        this.context = context;
        this.list = new ArrayList<>(list);      // תצוגה נוכחית
        this.fullList = new ArrayList<>(list);  // עותק קבוע למקור
    }

    public void filter(String query, Integer minPrice, Integer maxPrice) {
        list.clear();

        for (Perfume p : fullList) {
            boolean matchesQuery = query == null || query.isEmpty() ||
                    p.getName().toLowerCase().contains(query.toLowerCase());

            boolean matchesMin = minPrice == null || p.getPrice() >= minPrice;
            boolean matchesMax = maxPrice == null || p.getPrice() <= maxPrice;

            if (matchesQuery && matchesMin && matchesMax) {
                list.add(p);
            }
        }

        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Perfume getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.item_perfume, parent, false);

        Perfume p = list.get(position);

        TextView tvName = rowView.findViewById(R.id.itemName);
        TextView tvPrice = rowView.findViewById(R.id.itemPrice);
        TextView tvAmount = rowView.findViewById(R.id.itemAmount);
        TextView btPlus = rowView.findViewById(R.id.btPlus);
        TextView btMinus = rowView.findViewById(R.id.btMinus);

        tvName.setText(p.getName());
        tvPrice.setText("₪" + p.getPrice());
        tvAmount.setText("" + p.getAmount());

        btPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                p.setAmount(p.getAmount() + 1);
                tvAmount.setText("" + p.getAmount());
            }
        });

        btMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (p.getAmount() > 0) {
                    p.setAmount(p.getAmount() - 1);
                    tvAmount.setText("" + p.getAmount());
                } else {
                    Toast.makeText(context, "אי אפשר פחות מאפס", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return rowView;
    }
}
