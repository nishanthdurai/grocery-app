package com.grocery.app.admin.fragment;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.grocery.app.R;

public class MenuFragment extends Fragment {

    private OnFragmentChangeListener fragmentChangeListener;

    public interface OnFragmentChangeListener {
        void replaceFragment(Fragment fragment);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_admin_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayout addProduct = view.findViewById(R.id.add_product);
        LinearLayout viewOrder = view.findViewById(R.id.view_order);

        addProduct.setOnClickListener(v -> {
            if (fragmentChangeListener != null) {
                fragmentChangeListener.replaceFragment(new AddProductFragment());
            }
        });

        viewOrder.setOnClickListener(v -> {
            if (fragmentChangeListener != null) {
                fragmentChangeListener.replaceFragment(new ViewOrderFragment());
            }
        });
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentChangeListener) {
            fragmentChangeListener = (OnFragmentChangeListener) context;
        } else {
            throw new RuntimeException(context + " must implement OnFragmentChangeListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        fragmentChangeListener = null;
    }
}
