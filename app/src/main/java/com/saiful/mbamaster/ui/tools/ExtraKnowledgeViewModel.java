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

    //Get Firebase childname for MIS Business Intelligence
    public LiveData<String> getMBI_mem_01(){
        mem_p01.setValue("Memorise First");
        return mem_p01;
    }
    public LiveData<String> getMBI_mem_02(){
        mem_p02.setValue("Memorise Second");
        return mem_p02;
    }
    public LiveData<String> getMBI_mem_03(){
        mem_p03.setValue("Memorise Third");
        return mem_p03;
    }
    public LiveData<String> getMBI_mem_04(){
        mem_p04.setValue("Memorise Fourth");
        return mem_p04;
    }
    public LiveData<String> getMBI_mcq_01(){
        mcq_p01.setValue("MCQ First");
        return mcq_p01;
    }
    public LiveData<String> getMBI_mcq_02(){
        mcq_p02.setValue("MCQ Second");
        return mcq_p02;
    }
    public LiveData<String> getMBI_mcq_03(){
        mcq_p03.setValue("MCQ Third");
        return mcq_p03;
    }
    public LiveData<String> getMBI_mcq_04(){
        mcq_p04.setValue("MCQ Fourth");
        return mcq_p04;
    }
    //Get Firebase childname for AWS Quicksight
    public LiveData<String> getAQS_mem_01(){
        mem_p01.setValue("AWS Memorise First");
        return mem_p01;
    }
    public LiveData<String> getAQS_mem_02(){
        mem_p02.setValue(" AWS Memorise Second");
        return mem_p02;
    }
    public LiveData<String> getAQS_mem_03(){
        mem_p03.setValue("AWS Memorise Third");
        return mem_p03;
    }
    public LiveData<String> getAQS_mem_04(){
        mem_p04.setValue("AWS Memorise Fourth");
        return mem_p04;
    }
    public LiveData<String> getAQS_mcq_01(){
        mcq_p01.setValue("AWS MCQ First");
        return mcq_p01;
    }
    public LiveData<String> getAQS_mcq_02(){
        mcq_p02.setValue("AWS MCQ Second");
        return mcq_p02;
    }
    public LiveData<String> getAQS_mcq_03(){
        mcq_p03.setValue("AWS MCQ Third");
        return mcq_p03;
    }
    public LiveData<String> getAQS_mcq_04(){
        mcq_p04.setValue("AWS MCQ Fourth");
        return mcq_p04;
    }

    //Get Firebase childname for SEO
    public LiveData<String> getSEO_mem_01(){
        mem_p01.setValue("SEO Memorise First");
        return mem_p01;
    }
    public LiveData<String> getSEO_mem_02(){
        mem_p02.setValue(" SEO Memorise Second");
        return mem_p02;
    }
    public LiveData<String> getSEO_mem_03(){
        mem_p03.setValue("SEO Memorise Third");
        return mem_p03;
    }
    public LiveData<String> getSEO_mem_04(){
        mem_p04.setValue("SEO Memorise Fourth");
        return mem_p04;
    }
    public LiveData<String> getSEO_mcq_01(){
        mcq_p01.setValue("SEO MCQ First");
        return mcq_p01;
    }
    public LiveData<String> getSEO_mcq_02(){
        mcq_p02.setValue("SEO MCQ Second");
        return mcq_p02;
    }
    public LiveData<String> getSEO_mcq_03(){
        mcq_p03.setValue("SEO MCQ Third");
        return mcq_p03;
    }
    public LiveData<String> getSEO_mcq_04(){
        mcq_p04.setValue("SEO MCQ Fourth");
        return mcq_p04;
    }

    //Get Firebase childname for Digital Marketing
    public LiveData<String> getDMK_mem_01(){
        mem_p01.setValue("DMK Memorise First");
        return mem_p01;
    }
    public LiveData<String> getDMK_mem_02(){
        mem_p02.setValue("DMK Memorise Second");
        return mem_p02;
    }
    public LiveData<String> getDMK_mem_03(){
        mem_p03.setValue("DMK Memorise Third");
        return mem_p03;
    }
    public LiveData<String> getDMK_mem_04(){
        mem_p04.setValue("DMK Memorise Fourth");
        return mem_p04;
    }
    public LiveData<String> getDMK_mcq_01(){
        mcq_p01.setValue("DMK MCQ First");
        return mcq_p01;
    }
    public LiveData<String> getDMK_mcq_02(){
        mcq_p02.setValue("DMK MCQ Second");
        return mcq_p02;
    }
    public LiveData<String> getDMK_mcq_03(){
        mcq_p03.setValue("DMK MCQ Third");
        return mcq_p03;
    }
    public LiveData<String> getDMK_mcq_04(){
        mcq_p04.setValue("DMK MCQ Fourth");
        return mcq_p04;
    }

    //Get Firebase childname for C#
    public LiveData<String> getCSH_mem_01(){
        mem_p01.setValue("CSH Memorise First");
        return mem_p01;
    }
    public LiveData<String> getCSH_mem_02(){
        mem_p02.setValue(" CSH Memorise Second");
        return mem_p02;
    }
    public LiveData<String> getCSH_mem_03(){
        mem_p03.setValue("CSH Memorise Third");
        return mem_p03;
    }
    public LiveData<String> getCSH_mem_04(){
        mem_p04.setValue("CSH Memorise Fourth");
        return mem_p04;
    }
    public LiveData<String> getCSH_mcq_01(){
        mcq_p01.setValue("CSH MCQ First");
        return mcq_p01;
    }
    public LiveData<String> getCSH_mcq_02(){
        mcq_p02.setValue("CSH MCQ Second");
        return mcq_p02;
    }
    public LiveData<String> getCSH_mcq_03(){
        mcq_p03.setValue("CSH MCQ Third");
        return mcq_p03;
    }
    public LiveData<String> getCSH_mcq_04(){
        mcq_p04.setValue("CSH MCQ Fourth");
        return mcq_p04;
    }

    //Get Firebase childname for UNITY
    public LiveData<String> getUTY_mem_01(){
        mem_p01.setValue("UTY Memorise First");
        return mem_p01;
    }
    public LiveData<String> getUTY_mem_02(){
        mem_p02.setValue(" UTY Memorise Second");
        return mem_p02;
    }
    public LiveData<String> getUTY_mem_03(){
        mem_p03.setValue("UTY Memorise Third");
        return mem_p03;
    }
    public LiveData<String> getUTY_mem_04(){
        mem_p04.setValue("UTY Memorise Fourth");
        return mem_p04;
    }
    public LiveData<String> getUTY_mcq_01(){
        mcq_p01.setValue("UTY MCQ First");
        return mcq_p01;
    }
    public LiveData<String> getUTY_mcq_02(){
        mcq_p02.setValue("UTY MCQ Second");
        return mcq_p02;
    }
    public LiveData<String> getUTY_mcq_03(){
        mcq_p03.setValue("UTY MCQ Third");
        return mcq_p03;
    }
    public LiveData<String> getUTY_mcq_04(){
        mcq_p04.setValue("UTY MCQ Fourth");
        return mcq_p04;
    }
}