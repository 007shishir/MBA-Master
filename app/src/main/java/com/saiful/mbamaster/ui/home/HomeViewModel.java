package com.saiful.mbamaster.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class HomeViewModel extends ViewModel {

    private MutableLiveData<String> busCom;
    private MutableLiveData<String> fMis;
    private MutableLiveData<String> iBus;
    private MutableLiveData<String> manFi;
    private MutableLiveData<String> prinMan;
    //Business Communication memorise child name
    private MutableLiveData<String> busCom_mem_p01;
    private MutableLiveData<String> busCom_mem_p02;
    private MutableLiveData<String> busCom_mem_p03;
    private MutableLiveData<String> busCom_mem_p04;

    //Business Communication mcq child name
    private MutableLiveData<String> busCom_mcq_p01;
    private MutableLiveData<String> busCom_mcq_p02;
    private MutableLiveData<String> busCom_mcq_p03;
    private MutableLiveData<String> busCom_mcq_p04;


    //Fundamental of MIS memorise child name
    private MutableLiveData<String> fMis_mem_p01;
    private MutableLiveData<String> fMis_mem_p02;
    private MutableLiveData<String> fMis_mem_p03;
    private MutableLiveData<String> fMis_mem_p04;


    //Introduction to Business memorise child name
    private MutableLiveData<String> iBus_mem_p01;
    private MutableLiveData<String> iBus_mem_p02;
    private MutableLiveData<String> iBus_mem_p03;
    private MutableLiveData<String> iBus_mem_p04;


    //Managerial Finance memorise child name
    private MutableLiveData<String> manFi_mem_p01;
    private MutableLiveData<String> manFi_mem_p02;
    private MutableLiveData<String> manFi_mem_p03;
    private MutableLiveData<String> manFi_mem_p04;


    //Principle of Management memorise child name
    private MutableLiveData<String> prinMan_mem_p01;
    private MutableLiveData<String> prinMan_mem_p02;
    private MutableLiveData<String> prinMan_mem_p03;
    private MutableLiveData<String> prinMan_mem_p04;

    public HomeViewModel() {
        busCom = new MutableLiveData<>();
        fMis = new MutableLiveData<>();
        iBus = new MutableLiveData<>();
        manFi = new MutableLiveData<>();
        prinMan = new MutableLiveData<>();

        //SUBJECT name for all the subject
        busCom.setValue("Business Communication");
        fMis.setValue("Fundamentals of MIS");
        iBus.setValue("Introduction to Business");
        manFi.setValue("Managerial Finance");
        prinMan.setValue("Principle of Management");

        //set CHILD name for Business Communication
        busCom_mem_p01 = new MutableLiveData<>();
        busCom_mem_p02 = new MutableLiveData<>();
        busCom_mem_p03 = new MutableLiveData<>();
        busCom_mem_p04 = new MutableLiveData<>();
        busCom_mem_p01.setValue("bus_com_mem_p01");

        //set CHILD name for Fundamental of MIS
        fMis_mem_p01 = new MutableLiveData<>();
        fMis_mem_p02 = new MutableLiveData<>();
        fMis_mem_p03 = new MutableLiveData<>();
        fMis_mem_p04 = new MutableLiveData<>();
        fMis_mem_p01.setValue("fmis_mem_p01");

        //set CHILD name for Introduction to Business
        iBus_mem_p01 = new MutableLiveData<>();
        iBus_mem_p02 = new MutableLiveData<>();
        iBus_mem_p03 = new MutableLiveData<>();
        iBus_mem_p04 = new MutableLiveData<>();
        iBus_mem_p01.setValue("intro_bus_mem_p01");

        //set CHILD name for Managerial Finance
        manFi_mem_p01 = new MutableLiveData<>();
        manFi_mem_p02 = new MutableLiveData<>();
        manFi_mem_p03 = new MutableLiveData<>();
        manFi_mem_p04 = new MutableLiveData<>();
        manFi_mem_p01.setValue("finance_mem_p01");

        //set CHILD name for Managerial Finance
        prinMan_mem_p01 = new MutableLiveData<>();
        prinMan_mem_p02 = new MutableLiveData<>();
        prinMan_mem_p03 = new MutableLiveData<>();
        prinMan_mem_p04 = new MutableLiveData<>();
        prinMan_mem_p01.setValue("p_mngment_mem_p01");

    }

    //These Class will Return the SUBJECT name
    public LiveData<String> getBusComTopic() {
        return busCom;
    }
    public LiveData<String> getFMisTopic() {
        return fMis;
    }
    public LiveData<String> getIBusTopic() {
        return iBus;
    }
    public LiveData<String> getManFiTopic() {
        return manFi;
    }
    public LiveData<String> getPrinManTopic() {
        return prinMan;
    }

    //Providing CHILDNAME for Business Communication - MEMORISE
    public LiveData<String> get_busCom_mem_p01() {
        return busCom_mem_p01;
    }

    //Providing CHILDNAME for Fundamental of MIS - MEMORISE
    public MutableLiveData<String> getfMis_mem_p01() {
        return fMis_mem_p01;
    }

    //Providing CHILDNAME for Introduction to Business - MEMORISE
    public MutableLiveData<String> getiBus_mem_p01() {
        return iBus_mem_p01;
    }

    //Providing CHILDNAME for managerial Finance - MEMORISE
    public MutableLiveData<String> getManFi_mem_p01() {
        return manFi_mem_p01;
    }

    //Providing CHILDNAME for Principle of Management - MEMORISE
    public MutableLiveData<String> getPrinMan_mem_p01() {
        return prinMan_mem_p01;
    }

}