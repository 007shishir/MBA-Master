package com.saiful.mbamaster.ui.slideshow;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class Level3_ViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> dataMining_mem_p01;
    private MutableLiveData<String> dbms_mem_p01;
    private MutableLiveData<String> dss_mem_p01;
    private MutableLiveData<String> eCommerce_mem_p01;
    private MutableLiveData<String> entrepreneurship_mem_p01;
    private MutableLiveData<String> erp_mem_p01;

    public Level3_ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is slideshow fragment");

        //Only for Memorise section
        dataMining_mem_p01 = new MutableLiveData<>();
        dbms_mem_p01 = new MutableLiveData<>();
        dss_mem_p01 = new MutableLiveData<>();
        eCommerce_mem_p01 = new MutableLiveData<>();
        entrepreneurship_mem_p01 = new MutableLiveData<>();
        erp_mem_p01 = new MutableLiveData<>();

        //Set the CHILD name for all subject - MEMORISE
        dataMining_mem_p01.setValue("dataMining_mem_p01");
        dbms_mem_p01.setValue("dbms_mem_p01");
        dss_mem_p01.setValue("dss_mem_p01");
        eCommerce_mem_p01.setValue("e_commerce_mem_p01");
        entrepreneurship_mem_p01.setValue("entrepreneurship_mem_p01");
        erp_mem_p01.setValue("erp_mem_p01");
    }

    public LiveData<String> getText() {
        return mText;
    }

    public MutableLiveData<String> getDataMining_mem_p01() {
        return dataMining_mem_p01;
    }

    public MutableLiveData<String> getDbms_mem_p01() {
        return dbms_mem_p01;
    }

    public MutableLiveData<String> getDss_mem_p01() {
        return dss_mem_p01;
    }

    public MutableLiveData<String> geteCommerce_mem_p01() {
        return eCommerce_mem_p01;
    }

    public MutableLiveData<String> getEntrepreneurship_mem_p01() {
        return entrepreneurship_mem_p01;
    }

    public MutableLiveData<String> getErp_mem_p01() {
        return erp_mem_p01;
    }
}