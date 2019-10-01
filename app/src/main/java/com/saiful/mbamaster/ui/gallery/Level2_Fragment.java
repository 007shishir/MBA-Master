package com.saiful.mbamaster.ui.gallery;

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

public class Level2_Fragment extends Fragment implements View.OnClickListener {

    private Level2_ViewModel level2_viewModel;
    private LinearLayout mLL_economics, mLL_statistics, mLL_mngtScience,
            mLL_optManagement, mLL_supplyChain;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        level2_viewModel =
                ViewModelProviders.of(this).get(Level2_ViewModel.class);
        View root = inflater.inflate(R.layout.fragment_level_2, container, false);
//        final TextView textView = root.findViewById(R.id.text_gallery);
        mLL_economics = root.findViewById(R.id.mLL_economics);
        mLL_statistics = root.findViewById(R.id.mLL_statistics);
        mLL_mngtScience = root.findViewById(R.id.mLL_mngtScience);
        mLL_optManagement = root.findViewById(R.id.mLL_optManagement);
        mLL_supplyChain = root.findViewById(R.id.mLL_supplyChain);

        mLL_economics.setOnClickListener(this);
        mLL_statistics.setOnClickListener(this);
        mLL_mngtScience.setOnClickListener(this);
        mLL_optManagement.setOnClickListener(this);
        mLL_supplyChain.setOnClickListener(this);

        level2_viewModel.getText().observe(this, new Observer<String>() {
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
            case R.id.mLL_economics:
                Navigation.findNavController(v).navigate(R.id.action_nav_level2_to_mng_economics);
                break;
            case R.id.mLL_statistics:
                Navigation.findNavController(v).navigate(R.id.action_nav_level2_to_business_statics);
                break;
            case R.id.mLL_mngtScience:
                Navigation.findNavController(v).navigate(R.id.action_nav_level2_to_mngt_science);
                break;
            case R.id.mLL_optManagement:
                Navigation.findNavController(v).navigate(R.id.action_nav_level2_to_operation_management);
                break;
            case R.id.mLL_supplyChain:
                Navigation.findNavController(v).navigate(R.id.action_nav_level2_to_supply_chain);
                break;
            default:
                showingToastMessage();
                break;
        }
    }

    public void showingToastMessage() {
        Toast.makeText(getContext(), "This section is under development", Toast.LENGTH_SHORT).show();
    }
}