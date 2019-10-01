package com.saiful.mbamaster.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.saiful.mbamaster.R;

public class HomeFragment extends Fragment implements View.OnClickListener {

    private HomeViewModel homeViewModel;
    private LinearLayout mLL_bus_com, mLL_fundamental_mis, mLL_introduction_business,
            mLL_managerial_finance, mLL_principle_management;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        mLL_bus_com = root.findViewById(R.id.mLL_bus_com);
        mLL_fundamental_mis = root.findViewById(R.id.mLL_fundamental_mis);
        mLL_introduction_business = root.findViewById(R.id.mLL_introduction_business);
        mLL_managerial_finance = root.findViewById(R.id.mLL_managerial_finance);
        mLL_principle_management = root.findViewById(R.id.mLL_principle_management);

        mLL_bus_com.setOnClickListener(this);
        mLL_fundamental_mis.setOnClickListener(this);
        mLL_introduction_business.setOnClickListener(this);
        mLL_managerial_finance.setOnClickListener(this);
        mLL_principle_management.setOnClickListener(this);

//        homeViewModel.getText().observe(this, new Observer<String>() {
//            @Override
//            public void onChanged(@Nullable String s) {
//                textView.setText(s);
//            }
//        });
        return root;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.mLL_bus_com:
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_frag_business_communication);
                break;
            case R.id.mLL_fundamental_mis:
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_frag_fundamental_mis);
                break;
            case R.id.mLL_introduction_business:
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_frag_introduction_business);
                break;
            case R.id.mLL_managerial_finance:
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_frag_managerial_finance);
                break;
            case R.id.mLL_principle_management:
                Navigation.findNavController(v).navigate(R.id.action_nav_home_to_frag_principle_of_management);
                break;
            default:
                Toast.makeText(getContext(), "this is default", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}