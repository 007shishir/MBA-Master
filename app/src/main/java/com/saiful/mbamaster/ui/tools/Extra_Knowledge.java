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

    private LinearLayout mLL_mbi, mLL_networkingFCM, mLL_ProjectMngt,
            mLL_humanRIS;

    private Bundle topic;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ekViewModel =
                ViewModelProviders.of(this).get(ExtraKnowledgeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_extra_knowledge, container, false);
//        final TextView textView = root.findViewById(R.id.text_tools);
        mLL_mbi = root.findViewById(R.id.mLL_mbi);
        mLL_networkingFCM = root.findViewById(R.id.mLL_networkingFCM);
        mLL_ProjectMngt = root.findViewById(R.id.mLL_ProjectMngt);
        mLL_humanRIS = root.findViewById(R.id.mLL_humanRIS);


        mLL_mbi.setOnClickListener(this);
        mLL_networkingFCM.setOnClickListener(this);
        mLL_ProjectMngt.setOnClickListener(this);
        mLL_humanRIS.setOnClickListener(this);


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
                topic.putString("topic_name", "Strategic Management &amp; Information Systems");
                topic.putString("topic_code", "smis");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            case R.id.mLL_networkingFCM:
                topic = new Bundle();
                topic.putString("topic_name", "Networking For Corporate Management");
                topic.putString("topic_code", "nfcms");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            case R.id.mLL_ProjectMngt:
                topic = new Bundle();
                topic.putString("topic_name", "Project Management");
                topic.putString("topic_code", "pmngt");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            case R.id.mLL_humanRIS:
                topic = new Bundle();
                topic.putString("topic_name", "Human Resource Information System");
                topic.putString("topic_code", "hris");
                Navigation.findNavController(v).
                        navigate(R.id.action_nav_extra_knowledge_to_ek_mcq_memorise_option, topic);
                break;
            default:
                Toast.makeText(getContext(), "Choose a Option", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}