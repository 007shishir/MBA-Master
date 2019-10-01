package com.saiful.mbamaster.ui.tools;

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

public class Extra_Knowledge extends Fragment implements View.OnClickListener {

    private ExtraKnowledgeViewModel ekViewModel;
    private LinearLayout mLL_mbi, mLL_awsQuickSight, mLL_seo, mLL_digitalMarketing, mLL_cSharp,
            mLL_unity, mLL_python, mLL_dJango;
    private Bundle topic;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ekViewModel =
                ViewModelProviders.of(this).get(ExtraKnowledgeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_extra_knowledge, container, false);
//        final TextView textView = root.findViewById(R.id.text_tools);
        mLL_mbi = root.findViewById(R.id.mLL_mbi);
        mLL_awsQuickSight = root.findViewById(R.id.mLL_awsQuickSight);
        mLL_seo = root.findViewById(R.id.mLL_seo);
        mLL_digitalMarketing = root.findViewById(R.id.mLL_digitalMarketing);
        mLL_cSharp = root.findViewById(R.id.mLL_cSharp);
        mLL_unity = root.findViewById(R.id.mLL_unity);
        mLL_python = root.findViewById(R.id.mLL_python);
        mLL_dJango = root.findViewById(R.id.mLL_dJango);

        mLL_mbi.setOnClickListener(this);
        mLL_awsQuickSight.setOnClickListener(this);
        mLL_seo.setOnClickListener(this);
        mLL_digitalMarketing.setOnClickListener(this);
        mLL_cSharp.setOnClickListener(this);
        mLL_unity.setOnClickListener(this);
        mLL_python.setOnClickListener(this);
        mLL_dJango.setOnClickListener(this);

        ekViewModel.getText().observe(this, new Observer<String>() {
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
            case R.id.mLL_mbi:
                topic = new Bundle();
                topic.putString("topic_name", "MIS-Business Intelligence");
                topic.putString("topic_code", "mbi");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            case R.id.mLL_awsQuickSight:
                topic = new Bundle();
                topic.putString("topic_name", "AWS QuickSight");
                topic.putString("topic_code", "aqs");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            case R.id.mLL_seo:
                topic = new Bundle();
                topic.putString("topic_name", "SEO");
                topic.putString("topic_code", "seo");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            case R.id.mLL_digitalMarketing:
                topic = new Bundle();
                topic.putString("topic_name", "Digital Marketing");
                topic.putString("topic_code", "dmk");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            case R.id.mLL_cSharp:
                topic = new Bundle();
                topic.putString("topic_name", "c#");
                topic.putString("topic_code", "csh");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            case R.id.mLL_unity:
                topic = new Bundle();
                topic.putString("topic_name", "UNITY");
                topic.putString("topic_code", "uty");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            case R.id.mLL_python:
                topic = new Bundle();
                topic.putString("topic_name", "Python");
                topic.putString("topic_code", "ptn");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            case R.id.mLL_dJango:
                topic = new Bundle();
                topic.putString("topic_name", "Django");
                topic.putString("topic_code", "djo");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            default:
                Toast.makeText(getContext(), "Choose a Option", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}