package com.grocery.app.admin;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.grocery.app.R;
import com.grocery.app.admin.fragment.MenuFragment;
import com.grocery.app.admin.fragment.ViewOrderFragment;

import java.util.Objects;

public class AdminDashboard extends AppCompatActivity implements MenuFragment.OnFragmentChangeListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_admin_dashboard);

        setTitle("Admin dashboard");

        // Load the default fragment
        if (savedInstanceState == null) {
            replaceFragment(new MenuFragment());
        }
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);

        fragmentTransaction.commitNow();
        handleBackArrow();
    }

    @Override
    public void onBackPressed() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.fragment_container) instanceof MenuFragment) {
            super.onBackPressed();
        } else {
            replaceFragment(new MenuFragment());
        }
    }

    private void handleBackArrow() {
        FragmentManager fragmentManager = getSupportFragmentManager();
        boolean show = fragmentManager
                .findFragmentById(R.id.fragment_container) instanceof MenuFragment;
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(!show);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed(); // Handle the up button press
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
