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
    //All subject memorise child name
    private MutableLiveData<String> busCom_mem_p01;
    private MutableLiveData<String> fMis_mem_p01;
    private MutableLiveData<String> iBus_mem_p01;
    private MutableLiveData<String> manFi_mem_p01;
    private MutableLiveData<String> prinMan_mem_p01;


    //all subject mcq child name
    private MutableLiveData<String> busCom_mcq_p01;
    private MutableLiveData<String> fMis_mcq_p01;
    private MutableLiveData<String> iBus_mcq_p01;
    private MutableLiveData<String> manFi_mcq_p01;
    private MutableLiveData<String> prinMan_mcq_p01;

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
        busCom_mem_p01.setValue("bus_com_mem_p01");

        //set CHILD name for Fundamental of MIS
        fMis_mem_p01 = new MutableLiveData<>();
        fMis_mem_p01.setValue("fmis_mem_p01");

        //set CHILD name for Introduction to Business
        iBus_mem_p01 = new MutableLiveData<>();
        iBus_mem_p01.setValue("intro_bus_mem_p01");

        //set CHILD name for Managerial Finance
        manFi_mem_p01 = new MutableLiveData<>();
        manFi_mem_p01.setValue("finance_mem_p01");

        //set CHILD name for Managerial Finance
        prinMan_mem_p01 = new MutableLiveData<>();
        prinMan_mem_p01.setValue("p_mngment_mem_p01");

        //All the MCQ variable initiation
        busCom_mcq_p01 = new MutableLiveData<>();
        fMis_mcq_p01 = new MutableLiveData<>();
        iBus_mcq_p01 = new MutableLiveData<>();
        manFi_mcq_p01 = new MutableLiveData<>();
        prinMan_mcq_p01 = new MutableLiveData<>();

        //Firebase child name - MCQ
        busCom_mcq_p01.setValue("busCom_mcq_p01");
        fMis_mcq_p01.setValue("fMis_mcq_p01");
        iBus_mcq_p01.setValue("iBus_mcq_p01");
        manFi_mcq_p01.setValue("manFi_mcq_p01");
        prinMan_mcq_p01.setValue("prinMan_mcq_p01");


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

    public MutableLiveData<String> getBusCom_mcq_p01() {
        return busCom_mcq_p01;
    }

    public MutableLiveData<String> getfMis_mcq_p01() {
        return fMis_mcq_p01;
    }

    public MutableLiveData<String> getiBus_mcq_p01() {
        return iBus_mcq_p01;
    }

    public MutableLiveData<String> getManFi_mcq_p01() {
        return manFi_mcq_p01;
    }

    public MutableLiveData<String> getPrinMan_mcq_p01() {
        return prinMan_mcq_p01;
    }
}