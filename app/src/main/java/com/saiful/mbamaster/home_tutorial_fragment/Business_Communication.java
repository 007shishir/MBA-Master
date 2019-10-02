package com.saiful.mbamaster.home_tutorial_fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;

import com.saiful.mbamaster.R;
import com.saiful.mbamaster.ui.home.HomeViewModel;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class Business_Communication extends Fragment implements View.OnClickListener {

    private TextView mTxt_and_mem_p01, mTxt_and_mem_p02, mTxt_and_mem_p03, mTxt_and_mem_p04,
            mTxt_and_mcq_p01, mTxt_and_mcq_p02, mTxt_and_mcq_p03, mTxt_and_mcq_p04;
    View rootView;
    HomeViewModel homeViewModel;
    TextView mTxt_topic_name;
    String child_name, sub_name;

    public Business_Communication() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_android__tutorial, container, false);

        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        sub_name = homeViewModel.getBusComTopic().getValue();

        mTxt_topic_name = rootView.findViewById(R.id.mTxt_topic_name);
        mTxt_topic_name.setText(sub_name);

        mTxt_and_mem_p01 = rootView.findViewById(R.id.mTxt_and_mem_p01);
        mTxt_and_mem_p02 = rootView.findViewById(R.id.mTxt_and_mem_p02);
        mTxt_and_mem_p03 = rootView.findViewById(R.id.mTxt_and_mem_p03);
        mTxt_and_mem_p04 = rootView.findViewById(R.id.mTxt_and_mem_p04);

        mTxt_and_mem_p02.setText("FMIS");
        mTxt_and_mem_p03.setText("IntroB");
        mTxt_and_mem_p04.setText("PrinMngt");


        mTxt_and_mcq_p01 = rootView.findViewById(R.id.mTxt_and_mcq_p01);
        mTxt_and_mcq_p02 = rootView.findViewById(R.id.mTxt_and_mcq_p02);
        mTxt_and_mcq_p03 = rootView.findViewById(R.id.mTxt_and_mcq_p03);
        mTxt_and_mcq_p04 = rootView.findViewById(R.id.mTxt_and_mcq_p04);

        mTxt_and_mem_p01.setOnClickListener(this);
        mTxt_and_mem_p02.setOnClickListener(this);
        mTxt_and_mem_p03.setOnClickListener(this);
        mTxt_and_mem_p04.setOnClickListener(this);

        mTxt_and_mcq_p01.setOnClickListener(this);
        mTxt_and_mcq_p02.setOnClickListener(this);
        mTxt_and_mcq_p03.setOnClickListener(this);
        mTxt_and_mcq_p04.setOnClickListener(this);
        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mTxt_and_mem_p01:
                child_name = homeViewModel.get_busCom_mem_p01().getValue();
                Bundle memorise_p01 = new Bundle();
                memorise_p01.putString("child_name", child_name);
                Navigation.findNavController(v).
                        navigate(R.id.action_frag_business_communication_to_memorise_recV, memorise_p01);
                break;
            case R.id.mTxt_and_mem_p02:
                child_name = homeViewModel.getfMis_mem_p01().getValue();
                Bundle memorise_p02 = new Bundle();
                memorise_p02.putString("child_name", child_name);
                Navigation.findNavController(v).
                        navigate(R.id.action_frag_business_communication_to_memorise_recV, memorise_p02);
                break;
            case R.id.mTxt_and_mem_p03:
                child_name = homeViewModel.getiBus_mem_p01().getValue();
                Bundle memorise_p03 = new Bundle();
                memorise_p03.putString("child_name", child_name);
                Navigation.findNavController(v).
                        navigate(R.id.action_frag_business_communication_to_memorise_recV, memorise_p03);
                break;
            case R.id.mTxt_and_mem_p04:
                child_name = homeViewModel.getPrinMan_mem_p01().getValue();
                Bundle memorise_p04 = new Bundle();
                memorise_p04.putString("child_name", child_name);
                Navigation.findNavController(v).
                        navigate(R.id.action_frag_business_communication_to_memorise_recV, memorise_p04);
                break;
            case R.id.mTxt_and_mcq_p01:
                child_name = homeViewModel.getBusCom_mcq_p01().getValue();
                navigateNEXT_fragment_mcq();
                break;
            case R.id.mTxt_and_mcq_p02:
                printingToastMessage();
                break;
            case R.id.mTxt_and_mcq_p03:
                printingToastMessage();
                break;
            case R.id.mTxt_and_mcq_p04:
                printingToastMessage();
                break;
            default:
                printingToastMessage();
                break;
        }
    }

    public void navigateNEXT_fragment_mcq(){
        Bundle mcq = new Bundle();
        mcq.putString("child_name", child_name);
        Navigation.findNavController(Objects.requireNonNull(getView())).
                navigate(R.id.action_frag_business_communication_to_mcq_recV_p1, mcq);
    }

    public void printingToastMessage() {
        Toast.makeText(getContext(), "This section is under development", Toast.LENGTH_SHORT).show();
    }
}
