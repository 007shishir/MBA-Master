package com.saiful.mbamaster.ui.tools;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ExtraKnowledgeViewModel extends ViewModel {

    private MutableLiveData<String> mText;
    private MutableLiveData<String> mem_p01;
    private MutableLiveData<String> mem_p02;
    private MutableLiveData<String> mem_p03;
    private MutableLiveData<String> mem_p04;
    private MutableLiveData<String> mcq_p01;
    private MutableLiveData<String> mcq_p02;
    private MutableLiveData<String> mcq_p03;
    private MutableLiveData<String> mcq_p04;




    public ExtraKnowledgeViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
        //Child name for Business Intelligence
        mem_p01 = new MutableLiveData<>();
        mem_p02 = new MutableLiveData<>();
        mem_p03 = new MutableLiveData<>();
        mem_p04 = new MutableLiveData<>();
        mcq_p01 = new MutableLiveData<>();
        mcq_p02 = new MutableLiveData<>();
        mcq_p03 = new MutableLiveData<>();
        mcq_p04 = new MutableLiveData<>();
        mText.setValue("This is tools fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }

    //Get Firebase childname for Strategic Management Information System
    public LiveData<String> getSMIS_mem_01(){
        mem_p01.setValue("smis_mem_01");
        return mem_p01;
    }
    public LiveData<String> getSMIS_mem_02(){
        mem_p02.setValue("SMIS_mem_02");
        return mem_p02;
    }
    public LiveData<String> getSMIS_mem_03(){
        mem_p03.setValue("SMIS_mem_03");
        return mem_p03;
    }
    public LiveData<String> getSMIS_mem_04(){
        mem_p04.setValue("SMIS_mem_04");
        return mem_p04;
    }
    public LiveData<String> getSMIS_mcq_01(){
        mcq_p01.setValue("smis_mcq_01");
        return mcq_p01;
    }
    public LiveData<String> getSMIS_mcq_02(){
        mcq_p02.setValue("SMIS_mcq_02");
        return mcq_p02;
    }
    public LiveData<String> getSMIS_mcq_03(){
        mcq_p03.setValue("SMIS_mcq_03");
        return mcq_p03;
    }
    public LiveData<String> getSMIS_mcq_04(){
        mcq_p04.setValue("SMIS_mcq_04");
        return mcq_p04;
    }


    //Get Firebase childname for Networking For Corporate Management
    public LiveData<String> getNFCM_mem_01(){
        mem_p01.setValue("nfcm_mem_01");
        return mem_p01;
    }
    public LiveData<String> getNFCM_mem_02(){
        mem_p02.setValue(" NFCM Memorise Second");
        return mem_p02;
    }
    public LiveData<String> getNFCM_mem_03(){
        mem_p03.setValue("NFCM Memorise Third");
        return mem_p03;
    }
    public LiveData<String> getNFCM_mem_04(){
        mem_p04.setValue("NFCM Memorise Fourth");
        return mem_p04;
    }
    public LiveData<String> getNFCM_mcq_01(){
        mcq_p01.setValue("nfcm_mcq_01");
        return mcq_p01;
    }
    public LiveData<String> getNFCM_mcq_02(){
        mcq_p02.setValue("NFCM MCQ Second");
        return mcq_p02;
    }
    public LiveData<String> getNFCM_mcq_03(){
        mcq_p03.setValue("NFCM MCQ Third");
        return mcq_p03;
    }
    public LiveData<String> getNFCM_mcq_04(){
        mcq_p04.setValue("NFCM MCQ Fourth");
        return mcq_p04;
    }

    //Get Firebase childname for Project Management
    public LiveData<String> getPM_mem_01(){
        mem_p01.setValue("pm_mem_01");
        return mem_p01;
    }
    public LiveData<String> getPM_mem_02(){
        mem_p02.setValue(" PM Memorise Second");
        return mem_p02;
    }
    public LiveData<String> getPM_mem_03(){
        mem_p03.setValue("PM Memorise Third");
        return mem_p03;
    }
    public LiveData<String> getPM_mem_04(){
        mem_p04.setValue("PM Memorise Fourth");
        return mem_p04;
    }
    public LiveData<String> getPM_mcq_01(){
        mcq_p01.setValue("PM MCQ First");
        return mcq_p01;
    }
    public LiveData<String> getPM_mcq_02(){
        mcq_p02.setValue("PM MCQ Second");
        return mcq_p02;
    }
    public LiveData<String> getPM_mcq_03(){
        mcq_p03.setValue("PM MCQ Third");
        return mcq_p03;
    }
    public LiveData<String> getPM_mcq_04(){
        mcq_p04.setValue("PM MCQ Fourth");
        return mcq_p04;
    }

    //Get Firebase childname for Human Resource Informaiton System
    public LiveData<String> getHRIS_mem_01(){
        mem_p01.setValue("hris_mem_01");
        return mem_p01;
    }
    public LiveData<String> getHRIS_mem_02(){
        mem_p02.setValue("HRIS Memorise Second");
        return mem_p02;
    }
    public LiveData<String> getHRIS_mem_03(){
        mem_p03.setValue("HRIS Memorise Third");
        return mem_p03;
    }
    public LiveData<String> getHRIS_mem_04(){
        mem_p04.setValue("HRIS Memorise Fourth");
        return mem_p04;
    }
    public LiveData<String> getHRIS_mcq_01(){
        mcq_p01.setValue("HRIS MCQ First");
        return mcq_p01;
    }
    public LiveData<String> getHRIS_mcq_02(){
        mcq_p02.setValue("HRIS MCQ Second");
        return mcq_p02;
    }
    public LiveData<String> getHRIS_mcq_03(){
        mcq_p03.setValue("HRIS MCQ Third");
        return mcq_p03;
    }
    public LiveData<String> getHRIS_mcq_04(){
        mcq_p04.setValue("HRIS MCQ Fourth");
        return mcq_p04;
    }
}