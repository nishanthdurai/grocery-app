package com.grocery.app.admin.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.grocery.app.R;

public class AddProductFragment extends Fragment {

    private EditText editTextProductName;
    private EditText editTextProductPrice;
    private Button buttonAddProduct;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_add_product, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        editTextProductName = view.findViewById(R.id.editTextProductName);
        editTextProductPrice = view.findViewById(R.id.editTextProductPrice);
        buttonAddProduct = view.findViewById(R.id.buttonAddProduct);

        buttonAddProduct.setOnClickListener(v -> {
            String productName = editTextProductName.getText().toString();
            String productPrice = editTextProductPrice.getText().toString();
            // Handle product addition logic
            Toast.makeText(getContext(), "Product added: " + productName + " at " + productPrice, Toast.LENGTH_SHORT).show();
        });
    }
}
