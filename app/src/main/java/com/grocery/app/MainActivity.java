package com.grocery.app;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class MainActivity extends AppCompatActivity {

    CardView btnAdmin;
    CardView btnCustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdmin = findViewById(R.id.btn_admin);
        btnCustomer = findViewById(R.id.btn_customer);

//        btn_tutor.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,
//                TutorSignIn.class)));
//
//        btn_parent.setOnClickListener(v -> startActivity(new Intent(MainActivity.this,
//                ParentSignIn.class)));

    }
}