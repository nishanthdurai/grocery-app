package com.grocery.app.admin.fragment;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.grocery.app.R;

import java.io.InputStream;

public class AddProductFragment extends Fragment {

    private static final int PICK_IMAGE_REQUEST = 1;

    private EditText editTextProductName;
    private EditText editTextProductPrice;
    private Button buttonAddProduct;
    private Button buttonSelectImage;
    private ImageView imageViewProductImage;

    private Uri imageUri;

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
        buttonSelectImage = view.findViewById(R.id.buttonSelectImage);
        imageViewProductImage = view.findViewById(R.id.imageViewProductImage);

        buttonSelectImage.setOnClickListener(v -> {
            // Open image picker
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            startActivityForResult(intent, PICK_IMAGE_REQUEST);
        });

        buttonAddProduct.setOnClickListener(v -> {
            String productName = editTextProductName.getText().toString();
            String productPrice = editTextProductPrice.getText().toString();
            // Handle product addition logic
            if (productName.isEmpty() || productPrice.isEmpty() || imageUri == null) {
                Toast.makeText(getContext(), "Please complete all fields and select an image", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(getContext(), "Product added: " + productName + " at " + productPrice, Toast.LENGTH_SHORT).show();
                // Add your logic to handle the product addition here
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            try {
                // Load the selected image into the ImageView
                InputStream inputStream = getContext().getContentResolver().openInputStream(imageUri);
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageViewProductImage.setImageBitmap(bitmap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
