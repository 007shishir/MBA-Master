package com.saiful.mbamaster.ui.tools;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.saiful.mbamaster.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EK_mcq_memorise_option extends Fragment implements View.OnClickListener {

    private TextView mTxt_and_mem_p01, mTxt_and_mem_p02, mTxt_and_mem_p03, mTxt_and_mem_p04,
            mTxt_and_mcq_p01, mTxt_and_mcq_p02, mTxt_and_mcq_p03, mTxt_and_mcq_p04, mTxt_topic_name;
    View rootView;
    private String topic_name, topic_code;
    private Toolbar toolbar;
    String mem_p01, mem_p02, mem_p03, mem_p04, mcq_p01, mcq_p02, mcq_p03, mcq_p04;
    ExtraKnowledgeViewModel ekViewModel;

    public EK_mcq_memorise_option() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_android__tutorial, container, false);

        ekViewModel =
                ViewModelProviders.of(this).get(ExtraKnowledgeViewModel.class);
        mTxt_topic_name = rootView.findViewById(R.id.mTxt_topic_name);
        assert getArguments() != null;
        topic_name = getArguments().getString("topic_name");
        topic_code = getArguments().getString("topic_code");
        mTxt_topic_name.setText(topic_name);
        childNameRetrieval();

        mTxt_and_mem_p01 = rootView.findViewById(R.id.mTxt_and_mem_p01);
        mTxt_and_mem_p02 = rootView.findViewById(R.id.mTxt_and_mem_p02);
        mTxt_and_mem_p03 = rootView.findViewById(R.id.mTxt_and_mem_p03);
        mTxt_and_mem_p04 = rootView.findViewById(R.id.mTxt_and_mem_p04);

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
                Toast.makeText(getContext(), mem_p01, Toast.LENGTH_SHORT).show();
//                Bundle memorise_p01 = new Bundle();
//                memorise_p01.putString("child_name", "adv_excel_mem_p01");
//                Navigation.findNavController(v).navigate(R.id.action_advanced_excel_tutorial_to_memorise_recV, memorise_p01);
                break;
            case R.id.mTxt_and_mem_p02:
                Toast.makeText(getContext(), mem_p02, Toast.LENGTH_SHORT).show();
                break;
            case R.id.mTxt_and_mem_p03:
                Toast.makeText(getContext(), mem_p03, Toast.LENGTH_SHORT).show();
                break;
            case R.id.mTxt_and_mem_p04:
                Toast.makeText(getContext(), mem_p04, Toast.LENGTH_SHORT).show();
                break;
            case R.id.mTxt_and_mcq_p01:
                Toast.makeText(getContext(), mcq_p01, Toast.LENGTH_SHORT).show();
                break;
            case R.id.mTxt_and_mcq_p02:
                Toast.makeText(getContext(), mcq_p02, Toast.LENGTH_SHORT).show();
                break;
            case R.id.mTxt_and_mcq_p03:
                Toast.makeText(getContext(), mcq_p03, Toast.LENGTH_SHORT).show();
                break;
            case R.id.mTxt_and_mcq_p04:
                Toast.makeText(getContext(), mcq_p04, Toast.LENGTH_SHORT).show();
                break;
            default:
                printingToastMessage();
                break;
        }
    }

    public void printingToastMessage() {
        Toast.makeText(getContext(), "This section is under development", Toast.LENGTH_SHORT).show();
    }

    public void childNameRetrieval() {
        assert topic_code != null;
        if (topic_code.equalsIgnoreCase("mbi")) {
            mem_p01 = ekViewModel.getMBI_mem_01().getValue();
            mem_p02 = ekViewModel.getMBI_mem_02().getValue();
            mem_p03 = ekViewModel.getMBI_mem_03().getValue();
            mem_p04 = ekViewModel.getMBI_mem_04().getValue();
            mcq_p01 = ekViewModel.getMBI_mcq_01().getValue();
            mcq_p02 = ekViewModel.getMBI_mcq_02().getValue();
            mcq_p03 = ekViewModel.getMBI_mcq_03().getValue();
            mcq_p04 = ekViewModel.getMBI_mcq_04().getValue();
        }else if (topic_code.equalsIgnoreCase("aqs")){
            mem_p01 = ekViewModel.getAQS_mem_01().getValue();
            mem_p02 = ekViewModel.getAQS_mem_02().getValue();
            mem_p03 = ekViewModel.getAQS_mem_03().getValue();
            mem_p04 = ekViewModel.getAQS_mem_04().getValue();
            mcq_p01 = ekViewModel.getAQS_mcq_01().getValue();
            mcq_p02 = ekViewModel.getAQS_mcq_02().getValue();
            mcq_p03 = ekViewModel.getAQS_mcq_03().getValue();
            mcq_p04 = ekViewModel.getAQS_mcq_04().getValue();
        }else if (topic_code.equalsIgnoreCase("seo")){
            mem_p01 = ekViewModel.getSEO_mem_01().getValue();
            mem_p02 = ekViewModel.getSEO_mem_02().getValue();
            mem_p03 = ekViewModel.getSEO_mem_03().getValue();
            mem_p04 = ekViewModel.getSEO_mem_04().getValue();
            mcq_p01 = ekViewModel.getSEO_mcq_01().getValue();
            mcq_p02 = ekViewModel.getSEO_mcq_02().getValue();
            mcq_p03 = ekViewModel.getSEO_mcq_03().getValue();
            mcq_p04 = ekViewModel.getSEO_mcq_04().getValue();
        }else if (topic_code.equalsIgnoreCase("dmk")){
            mem_p01 = ekViewModel.getDMK_mem_01().getValue();
            mem_p02 = ekViewModel.getDMK_mem_02().getValue();
            mem_p03 = ekViewModel.getDMK_mem_03().getValue();
            mem_p04 = ekViewModel.getDMK_mem_04().getValue();
            mcq_p01 = ekViewModel.getDMK_mcq_01().getValue();
            mcq_p02 = ekViewModel.getDMK_mcq_02().getValue();
            mcq_p03 = ekViewModel.getDMK_mcq_03().getValue();
            mcq_p04 = ekViewModel.getDMK_mcq_04().getValue();
        }else if (topic_code.equalsIgnoreCase("csh")){
            mem_p01 = ekViewModel.getCSH_mem_01().getValue();
            mem_p02 = ekViewModel.getCSH_mem_02().getValue();
            mem_p03 = ekViewModel.getCSH_mem_03().getValue();
            mem_p04 = ekViewModel.getCSH_mem_04().getValue();
            mcq_p01 = ekViewModel.getCSH_mcq_01().getValue();
            mcq_p02 = ekViewModel.getCSH_mcq_02().getValue();
            mcq_p03 = ekViewModel.getCSH_mcq_03().getValue();
            mcq_p04 = ekViewModel.getCSH_mcq_04().getValue();
        }else if (topic_code.equalsIgnoreCase("uty")){
            mem_p01 = ekViewModel.getUTY_mem_01().getValue();
            mem_p02 = ekViewModel.getUTY_mem_02().getValue();
            mem_p03 = ekViewModel.getUTY_mem_03().getValue();
            mem_p04 = ekViewModel.getUTY_mem_04().getValue();
            mcq_p01 = ekViewModel.getUTY_mcq_01().getValue();
            mcq_p02 = ekViewModel.getUTY_mcq_02().getValue();
            mcq_p03 = ekViewModel.getUTY_mcq_03().getValue();
            mcq_p04 = ekViewModel.getUTY_mcq_04().getValue();
        }else if (topic_code.equalsIgnoreCase("ptn")){
            mem_p01 = ekViewModel.getSEO_mem_01().getValue();
            mem_p02 = ekViewModel.getSEO_mem_02().getValue();
            mem_p03 = ekViewModel.getSEO_mem_03().getValue();
            mem_p04 = ekViewModel.getSEO_mem_04().getValue();
            mcq_p01 = ekViewModel.getSEO_mcq_01().getValue();
            mcq_p02 = ekViewModel.getSEO_mcq_02().getValue();
            mcq_p03 = ekViewModel.getSEO_mcq_03().getValue();
            mcq_p04 = ekViewModel.getSEO_mcq_04().getValue();
        }

        else
            Toast.makeText(getContext(), topic_code, Toast.LENGTH_LONG).show();
    }
}
