package com.example.newkolbo.fragment;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.newkolbo.Order;
import com.example.newkolbo.Perfume;
import com.example.newkolbo.PerfumeAdapter;
import com.example.newkolbo.R;
import com.example.newkolbo.Activity.OrderActivity;

import java.util.ArrayList;

public class OrderFragment extends Fragment {

    private ListView listView;
    private SearchView searchView;
    private EditText minEditText, maxEditText;
    private PerfumeAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_order, container, false);

        listView = view.findViewById(R.id.lvx);
        searchView = view.findViewById(R.id.searchView);
        minEditText = view.findViewById(R.id.editTextText);
        maxEditText = view.findViewById(R.id.editTextText2);

        ArrayList<Perfume> perfumes = OrderActivity.myOrder2.getPerfumeList();
        adapter = new PerfumeAdapter(getContext(), perfumes);
        listView.setAdapter(adapter);

        setupSearchAndFilter();

        return view;
    }

    private void setupSearchAndFilter() {
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                applyFilter();
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                applyFilter();
                return true;
            }
        });

        TextWatcher priceWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                applyFilter();
            }

            @Override
            public void afterTextChanged(Editable s) { }
        };

        minEditText.addTextChangedListener(priceWatcher);
        maxEditText.addTextChangedListener(priceWatcher);
    }

    private void applyFilter() {
        String query = searchView.getQuery().toString().trim();
        Integer min = null, max = null;

        try {
            min = Integer.parseInt(minEditText.getText().toString());
        } catch (Exception ignored) {}

        try {
            max = Integer.parseInt(maxEditText.getText().toString());
        } catch (Exception ignored) {}

        adapter.filter(query, min, max);
    }
}
