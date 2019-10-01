package com.saiful.mbamaster.ui.slideshow;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.saiful.mbamaster.R;

public class Level3 extends Fragment implements View.OnClickListener {

    private Level3_ViewModel microsoftTechViewModel;
    private LinearLayout mLL_eCommerce, mLL_data_mining, mLL_db_management,
            mLL_decision_support, mLL_erp, mLL_entrepreneurship;;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        microsoftTechViewModel =
                ViewModelProviders.of(this).get(Level3_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_level3, container, false);
//        final TextView textView = root.findViewById(R.id.text_slideshow);
        mLL_eCommerce = root.findViewById(R.id.mLL_eCommerce);
        mLL_data_mining = root.findViewById(R.id.mLL_data_mining);
        mLL_db_management = root.findViewById(R.id.mLL_db_management);
        mLL_decision_support = root.findViewById(R.id.mLL_decision_support);
        mLL_erp = root.findViewById(R.id.mLL_erp);
        mLL_entrepreneurship = root.findViewById(R.id.mLL_entrepreneurship);

        mLL_eCommerce.setOnClickListener(this);
        mLL_data_mining.setOnClickListener(this);
        mLL_db_management.setOnClickListener(this);
        mLL_decision_support.setOnClickListener(this);
        mLL_erp.setOnClickListener(this);
        mLL_entrepreneurship.setOnClickListener(this);

        microsoftTechViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
//                textView.setText(s);
            }
        });
        return root;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mLL_eCommerce:
                Navigation.findNavController(v).navigate(R.id.action_nav_level3_to_frag_electric_commerce);
                break;
            case R.id.mLL_data_mining:
                Navigation.findNavController(v).navigate(R.id.action_nav_level3_to_frag_data_mining);
                break;
            case R.id.mLL_db_management:
                Navigation.findNavController(v).navigate(R.id.action_nav_level3_to_frag_dbms);
                break;
            case R.id.mLL_decision_support:
                Navigation.findNavController(v).navigate(R.id.action_nav_level3_to_frag_dss);
                break;
            case R.id.mLL_erp:
                Navigation.findNavController(v).navigate(R.id.action_nav_level3_to_frag_erp);
                break;
            case R.id.mLL_entrepreneurship:
                Navigation.findNavController(v).navigate(R.id.action_nav_level3_to_frag_entrepreneurship);
                break;
            default:
                getToastMessage();
                break;
        }
    }

    public void getToastMessage() {
        Toast.makeText(getContext(), "This section is under development", Toast.LENGTH_SHORT).show();
    }
}