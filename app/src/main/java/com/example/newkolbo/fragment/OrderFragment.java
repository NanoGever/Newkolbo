package com.example.newkolbo.fragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.newkolbo.Activity.MainOrder;
import com.example.newkolbo.Activity.OrderActivity;
import com.example.newkolbo.Activity.ShopCart;
import com.example.newkolbo.Perfume;
import com.example.newkolbo.PerfumeAdapter;
import com.example.newkolbo.R;

import java.util.ArrayList;

public class OrderFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_order, container, false);
        ListView lv = view.findViewById(R.id.lvx);                               //continue button takes to order page Ordercredit
        /*ArrayList<String> arr = new ArrayList<>();
        arr.add("a"); arr.add("b");
        ArrayAdapter<String> adp = new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1, arr);*/
        PerfumeAdapter adp = new PerfumeAdapter(requireContext(), OrderActivity.myOrder2.getPerfumeList());
        lv.setAdapter(adp);

        return view;
    }
}