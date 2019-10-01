package com.saiful.mbamaster.ui.gallery;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Level2_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    //child_name variable name
    private MutableLiveData<String> economics_child;
    private MutableLiveData<String> statistics_child;
    private MutableLiveData<String> mngtScience_child;
    private MutableLiveData<String> optManagement_child;
    private MutableLiveData<String> supplyChain_child;

    //subject name variable name
    private MutableLiveData<String> economics_subject;
    private MutableLiveData<String> statistics_subject;
    private MutableLiveData<String> mngtScience_subject;
    private MutableLiveData<String> optManagement_subject;
    private MutableLiveData<String> supplyChain_subject;

    public Level2_ViewModel() {
        mText = new MutableLiveData<>();
        economics_child = new MutableLiveData<>();
        statistics_child = new MutableLiveData<>();
        mngtScience_child = new MutableLiveData<>();
        optManagement_child = new MutableLiveData<>();
        supplyChain_child = new MutableLiveData<>();

        economics_subject = new MutableLiveData<>();
        statistics_subject = new MutableLiveData<>();
        mngtScience_subject = new MutableLiveData<>();
        optManagement_subject = new MutableLiveData<>();
        supplyChain_subject = new MutableLiveData<>();

        mText.setValue("This is gallery fragment");
        economics_child.setValue("economics_mem_p01");
        statistics_child.setValue("statistics_mem_p01");
        mngtScience_child.setValue("mngt_science_mem_p01");
        optManagement_child.setValue("opt_mngt_mem_p01");
        supplyChain_child.setValue("supply_ch_mem_p01");

        economics_subject.setValue("Managerial Economics");
        statistics_subject.setValue("Business Statics");
        mngtScience_subject.setValue("Management Science");
        optManagement_subject.setValue("Operations Management");
        supplyChain_subject.setValue("Supply Chain Management");
    }

    public LiveData<String> getText() {
        return mText;
    }

    //get the CHILD name from here
    public MutableLiveData<String> getEconomics_child() {
        return economics_child;
    }

    public MutableLiveData<String> getStatistics_child() {
        return statistics_child;
    }

    public MutableLiveData<String> getMngtScience_child() {
        return mngtScience_child;
    }

    public MutableLiveData<String> getOptManagement_child() {
        return optManagement_child;
    }

    public MutableLiveData<String> getSupplyChain_child() {
        return supplyChain_child;
    }

    //get the subject name from here

    public MutableLiveData<String> getEconomics_subject() {
        return economics_subject;
    }

    public MutableLiveData<String> getStatistics_subject() {
        return statistics_subject;
    }

    public MutableLiveData<String> getMngtScience_subject() {
        return mngtScience_subject;
    }

    public MutableLiveData<String> getOptManagement_subject() {
        return optManagement_subject;
    }

    public MutableLiveData<String> getSupplyChain_subject() {
        return supplyChain_subject;
    }
}