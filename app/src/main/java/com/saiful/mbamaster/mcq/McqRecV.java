package com.saiful.mbamaster.mcq;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.saiful.mbamaster.Parameter;
import com.saiful.mbamaster.R;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class McqRecV extends Fragment {

    private RecyclerView mRecycler_Mcq;
    private DatabaseReference mDatabase;
    private View v;
    private ConnectivityManager connectivityManager;
    String childName_fromFragment;

    public McqRecV() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_memorize_rec_v, container, false);
        connectivityManager = (ConnectivityManager) Objects.requireNonNull(getActivity()).getSystemService(CONNECTIVITY_SERVICE);
        assert getArguments() != null;
        childName_fromFragment = getArguments().getString("child_name");
        mDatabase = FirebaseDatabase.getInstance().getReference().child(childName_fromFragment);
        mDatabase.keepSynced(false);
        mRecycler_Mcq = v.findViewById(R.id.mRecycler_Memorize);
        mRecycler_Mcq.setHasFixedSize(true);
        mRecycler_Mcq.setLayoutManager(new LinearLayoutManager(getContext()));

        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        FirebaseRecyclerAdapter<Parameter, ParameterViewHolder> firebaseRecyclerAdapter =
                new FirebaseRecyclerAdapter<Parameter, ParameterViewHolder>
                        (Parameter.class, R.layout.recyclerview_for_memorise_mcq, ParameterViewHolder.class, mDatabase) {
                    @Override
                    protected void populateViewHolder(ParameterViewHolder viewHolder, Parameter model, int position) {
                        final String post_key = getRef(position).getKey();
                        viewHolder.setSource(model.getSource());
                        viewHolder.setTopic(model.getTopic());
                        viewHolder.setSum(model.getSum());
                        viewHolder.setTotal(model.getTotal());
                        viewHolder.mView.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                assert connectivityManager != null;
                                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                                if (networkInfo != null && networkInfo.isConnected()) {
                                    Intent intent = new Intent(getActivity(), McqVersion1.class);
                                    intent.putExtra("key_name", post_key);
                                    intent.putExtra("childName", childName_fromFragment);
                                    Toast.makeText(getContext(), "Please make sure you turn off the rotation of your device", Toast.LENGTH_LONG).show();
                                    startActivity(intent);
                                } else {
                                    Snackbar.make(Objects.requireNonNull(getActivity()).findViewById(R.id.drawer_layout), "No Network Connection...", Snackbar.LENGTH_LONG).show();
                                }
                            }
                        });

                    }
                };
        mRecycler_Mcq.setAdapter(firebaseRecyclerAdapter);
    }

    public static class ParameterViewHolder extends RecyclerView.ViewHolder
    {
        View mView;
        public ParameterViewHolder(View itemView)
        {
            super(itemView);
            mView = itemView;
        }
        public void setSource(String source)
        {
            TextView post_source = mView.findViewById(R.id.mTxt_source);
            post_source.setText(source);
        }
        public void setTopic(String topic)
        {
            TextView post_topic = mView.findViewById(R.id.mTxt_topic);
            post_topic.setText(topic);
        }
        public void setSum(String sum)
        {
            TextView post_sum = mView.findViewById(R.id.mTxt_sum);
            post_sum.setText(sum);
        }
        public void setTotal(String total)
        {
            TextView post_total = mView.findViewById(R.id.mTxt_total);
            post_total.setText(total);
        }
    }
}
