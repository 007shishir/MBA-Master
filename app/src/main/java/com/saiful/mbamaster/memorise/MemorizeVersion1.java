package com.saiful.mbamaster.memorise;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.saiful.mbamaster.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
//import com.github.chrisbanes.photoview.PhotoViewAttacher;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class MemorizeVersion1 extends AppCompatActivity {
    Memorize_ViewModel viewModel;
    boolean clicked;
    private ConnectivityManager connectivityManager;
//    PhotoViewAttacher photoViewAttacher;

    private ProgressBar progressBar2, progressPrimary, progressLearning, progressMaster;
    private Handler handler = new Handler();

    private String question, e1, e2, m, id;
    private String getQuestion, getE1, getE2, getM;
    private int totalQ, totalE;
    private int totalPoint;
    private int answeredQn, unAnsweredQN;
    TextView mTxt_quest, mTxt_E1, mTxt_E2, mTxt_level, mTxt_id, mTxt_known, mTxt_unknown,
            mTxt_PointEachQ, mTxt_Submit, mPrimary_Text, mLearning_Text, mMaster_Text,
            mTxt_Header_topic;
    ImageView imageView_memorise;
    LinearLayout mLL_image_memorise;
    LinearLayout mL_explanation;

    String[] questionN = {"q1", "q2", "q3", "q4", "q5", "q6", "q7", "q8", "q9", "q10", "q11", "q12",
            "q13", "q14", "q15", "q16", "q17", "q18", "q19", "q20", "q21", "q22", "q23", "q24", "q25",
            "q26", "q27", "q28", "q29", "q30", "q31", "q32", "q33", "q34", "q35", "q36", "q37", "q38",
            "q39", "q40", "q41"};

    int q1, q2, q3, q4, q5, q6, q7, q8, q9, q10, q11, q12, q13, q14, q15, q16, q17, q18, q19, q20,
            q21, q22, q23, q24, q25, q26, q27, q28, q29, q30, q31, q32, q33, q34, q35, q36, q37,
            q38, q39, q40, q41 = 0;
    int r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19,
            r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35,
            r36, r37, r38, r39, r40 = 0;


    private int mQuestNum = 1;
    private int level_cards, level_question;

    String mPost_key;
    String child_Name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memorize_version1);
        Firebase.setAndroidContext(this);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
//        setSupportActionBar(toolbar);

        // To check internet connectivity
        connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        networkCheck();

//        MobileAds.initialize(this, new OnInitializationCompleteListener() {
//            @Override
//            public void onInitializationComplete(InitializationStatus initializationStatus) {
//            }
//        });
//
//        AdView mAdView_1 = findViewById(R.id.mAdView_1);
//        AdView mAdView_2 = findViewById(R.id.mAdView_2);
//        AdView mAdView_3 = findViewById(R.id.mAdView_3);
//        AdView mAdView_4 = findViewById(R.id.mAdView_4);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView_1.loadAd(adRequest);
//        mAdView_2.loadAd(adRequest);
//        mAdView_3.loadAd(adRequest);
//        mAdView_4.loadAd(adRequest);

        viewModel = ViewModelProviders.of(this).get(Memorize_ViewModel.class);
        mPost_key = Objects.requireNonNull(getIntent().getExtras()).getString("key_name");
        child_Name = Objects.requireNonNull(getIntent().getExtras()).getString("childName");

        //This section is for linking the text box with option
        mTxt_quest = findViewById(R.id.mTxt_quest);
        mTxt_E1 = findViewById(R.id.mTxt_E1);
        mTxt_E2 = findViewById(R.id.mTxt_E2);
        mTxt_level = findViewById(R.id.mTxt_level);
        mTxt_id = findViewById(R.id.mTxt_id);
        mTxt_known = findViewById(R.id.mTxt_known);
        mTxt_unknown = findViewById(R.id.mTxt_unknown);
        mTxt_PointEachQ = findViewById(R.id.mTxt_PointEachQ);
        mTxt_Submit = findViewById(R.id.mTxt_Submit);
        imageView_memorise = findViewById(R.id.mImage_view);
        mLL_image_memorise = findViewById(R.id.mLL_image_memorise);

        mPrimary_Text = findViewById(R.id.mPrimary_Text);
        mLearning_Text = findViewById(R.id.mLearning_Text);
        mMaster_Text = findViewById(R.id.mMaster_Text);
        mL_explanation = findViewById(R.id.mL_explanation);
        mTxt_Header_topic = findViewById(R.id.mTxt_Header_topic);

        progressBar2 = findViewById(R.id.progressBar2);
        progressPrimary = findViewById(R.id.progressPrimary);
        progressLearning = findViewById(R.id.progressLearning);
        progressMaster = findViewById(R.id.progressMaster);


        mTxt_Submit.setVisibility(View.VISIBLE);
        mL_explanation.setVisibility(View.GONE);
        mTxt_known.setVisibility(View.GONE);
        mTxt_unknown.setVisibility(View.GONE);
        mLL_image_memorise.setVisibility(View.GONE);
        header_topic_selection();

        progreesBarBackgroundVISIBLE();
        progressBarBackgroundGONE();

        readFromDatabase();


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTxt_Submit.setVisibility(View.VISIBLE);
                mL_explanation.setVisibility(View.GONE);
                mTxt_known.setVisibility(View.GONE);
                mTxt_unknown.setVisibility(View.GONE);
                mLL_image_memorise.setVisibility(View.GONE);
                updateQuestion();
                Snackbar.make(view, "Refreshing the question...", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void header_topic_selection() {
        switch (child_Name) {
            case "android_mem_p02":
            case "memorise_p01":
            case "android_mcq_p01":
            case "android_mcq_p02":
                mTxt_Header_topic.setText("Android Studio");
                break;
            case "css_mem_p01":
            case "css_mem_p02":
                mTxt_Header_topic.setText("CSS");
                break;
            case "excel2010_mem_p01":
                mTxt_Header_topic.setText("Excel 2010");
                break;
            case "adv_excel_mem_p01":
                mTxt_Header_topic.setText("Advance Excel");
                break;
            case "excel_chart_mem_p01":
                mTxt_Header_topic.setText("Excel Charts");
                break;
            case "excel_func_mem_p01":
                mTxt_Header_topic.setText("Excel Function");
                break;
            case "google_analytic_mem_p01":
                mTxt_Header_topic.setText("Google Analytics");
                break;
            case "html_mem_p01":
            case "html_mem_p02":
                mTxt_Header_topic.setText("HTML");
                break;
            case "java_mem_p01":
            case "java_mem_p02":
                mTxt_Header_topic.setText("Java");
                break;
            case "javascript_mem_p01":
            case "javascript_mem_p02":
                mTxt_Header_topic.setText("JavaScript");
                break;
            case "kotlin_mem_p01":
            case "kotlin_mem_p02":
                mTxt_Header_topic.setText("Kotlin");
                break;
            case "mvvm_mem_p01":
            case "mvvm_mem_p02":
                mTxt_Header_topic.setText("MVVM");
                break;
            case "pivot_tab_mem_p01":
                mTxt_Header_topic.setText("Excel Pivot Table");
                break;
            case "rest_api_mem_p01":
            case "rest_api_mem_p02":
                mTxt_Header_topic.setText("REST API");
                break;
            case "word2010_mem_p01":
                mTxt_Header_topic.setText("Word 2010");
                break;
            case "wordpress_mem_p01":
            case "wordpress_mem_p02":
                mTxt_Header_topic.setText("WordPress");
                break;
            default:
                mTxt_Header_topic.setText("memorize");
                break;

        }
    }

    public void readFromDatabase() {
        mTxt_Submit.setVisibility(View.VISIBLE);
        mL_explanation.setVisibility(View.GONE);
        mLL_image_memorise.setVisibility(View.GONE);
        //update the card status (for the whole question) for the card(40 question)
        updateLevelStatus(level_cards);
        id = child_Name + "_" + mPost_key + "_" + questionN[mQuestNum - 1];
        //count  the number of primary learning and master questioin for the progress bar
        countPriLernMast();
        final List<Memorize_entity> readQfromDatabase =
                Memorize_database.getINSTANCE(getApplicationContext()).memorize_dao().select_question(id);


        if (readQfromDatabase.isEmpty()) {
            updateQuestion();
        }
        if (!readQfromDatabase.isEmpty()) {
            for (Memorize_entity mre : readQfromDatabase) {
                mTxt_Submit.setVisibility(View.VISIBLE);
                idEachQuest(child_Name, mPost_key, questionN[mQuestNum - 1]);

                getQuestion = mre.getQ();
                level_question = mre.getLevel_question();
                eachQuestStatus_DB();
                if (getQuestion == null) {
                    getQuestion_Explanation();
                    if (question == null) {
                        getQuestion_Explanation();
                        getQuestion = question;
                        getE1 = e1;
                        getE2 = e2;
                        getM = m;
                    }
                } else {
                    getQuestion = mre.getQ();
                    getE1 = mre.getE();
                    getE2 = mre.getEe();
                    getM = mre.getM();

                }

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mTxt_quest.setText(Html.fromHtml(getQuestion, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    mTxt_quest.setText(Html.fromHtml(getQuestion));
                }

                totalQ = mre.getTotal_N_Q();
                if (totalQ < 1) {
                    totalQ = 1;
                    Toast.makeText(getApplicationContext(), "find out why totalQ is less than one", Toast.LENGTH_SHORT).show();
                }

                String special_id = child_Name + "_" + mPost_key + "_" + questionN[totalQ - 1];
                final List<Memorize_entity> read_level_fromDatabase = Memorize_database.getINSTANCE(getApplicationContext()).
                        memorize_dao().select_question(special_id);

                if (read_level_fromDatabase.isEmpty()) {
                    level_cards = 0;
                    updateLevelStatus(level_cards);
                }
                if (!read_level_fromDatabase.isEmpty()) {
                    for (Memorize_entity mr_level : read_level_fromDatabase) {
                        level_cards = mr_level.getLevel_cards();
//                        Toast.makeText(getApplicationContext(), "101: "+level_cards, Toast.LENGTH_SHORT).show();
                        updateLevelStatus(level_cards);
                    }
                }

                clicked = true;
                mTxt_known.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clicked = false;
                        next_Q_or_not_KNOWN_offline();
                    }
                });


                mTxt_unknown.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clicked = false;

                        next_Q_or_not_unknown_OFFLINE();
                    }
                });

                mTxt_Submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clicked = true;
                        next_Q_or_not_SUBMIT_offline();
//                        setVisibilityForImage(getM);
                    }
                });

            }
        }
    }

    public void addToDatabase() {
        id = child_Name + "_" + mPost_key + "_" + questionN[mQuestNum - 1];

        Memorize_entity memorize_entity = new Memorize_entity();
        memorize_entity.setId(id);
        memorize_entity.setQ(question);
        memorize_entity.setE(e1);
        memorize_entity.setEe(e2);
        memorize_entity.setTotal_N_Q(totalQ);
        memorize_entity.setLevel_question(level_question);
        memorize_entity.setLevel_cards(level_cards);
        memorize_entity.setM(m);
        viewModel.addMemorizeQ(memorize_entity);
    }

    public void addToDatabaseOFFLINE() {
        id = child_Name + "_" + mPost_key + "_" + questionN[mQuestNum - 1];
//        Toast.makeText(getApplicationContext(), questionN[mQuestNum - 1], Toast.LENGTH_SHORT).show();
        Memorize_entity memorize_entity = new Memorize_entity();
        memorize_entity.setId(id);
        memorize_entity.setQ(getQuestion);
        memorize_entity.setE(getE1);
        memorize_entity.setEe(getE2);
        memorize_entity.setTotal_N_Q(totalQ);
        memorize_entity.setLevel_question(level_question);
        memorize_entity.setLevel_cards(level_cards);
        memorize_entity.setM(getM);
        viewModel.addMemorizeQ(memorize_entity);
    }

    public void getQuestion_Explanation() {
        Firebase mQuestion = new Firebase("https://mba-master-bdf52.firebaseio.com/" + child_Name + "/" + mPost_key + "/" + mQuestNum + "/q");
        mQuestion.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                question = dataSnapshot.getValue(String.class);
                if (question == null || question.equals("")) {
                    Toast.makeText(getApplicationContext(), "No Question Received From Database", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "103: " + question, Toast.LENGTH_LONG).show();
                }
                mTxt_quest.setText(question);
                //For database only
//                q = question;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        Firebase mE1 = new Firebase("https://mba-master-bdf52.firebaseio.com/" + child_Name + "/" + mPost_key + "/" + mQuestNum + "/e");
        mE1.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                e1 = dataSnapshot.getValue(String.class);
//                mTxt_E1.setText(e1);
                //For database only
//                o2 = option2;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Firebase mE2 = new Firebase("https://mba-master-bdf52.firebaseio.com/" + child_Name + "/" + mPost_key + "/" + mQuestNum + "/ee");
        //mChoice3.keepSynced(true);
        mE2.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                e2 = dataSnapshot.getValue(String.class);
//                mTxt_E2.setText(e2);
                //For database only
//                o3 = option3;
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Firebase mM = new Firebase("https://mba-master-bdf52.firebaseio.com/\" + child_Name + \"/\" + mPost_key + \"/\" + mQuestNum + \"/m");
        mM.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                m = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    public void getTotal_Quest_and_Explanation_No() {
        Firebase mTotalQ = new Firebase("https://mba-master-bdf52.firebaseio.com/" + child_Name + "/" + mPost_key + "/info/totalQ");
        mTotalQ.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                totalQ = Integer.parseInt(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Firebase mTotalE = new Firebase("https://mba-master-bdf52.firebaseio.com/" + child_Name + "/" + mPost_key + "/info/totalE");
        mTotalE.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                totalE = Integer.parseInt(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    //update the card status (for the whole question) for the card(40 question)
    public void updateLevelStatus(int a) {
        if (a < 2) {
            mTxt_level.setText("Primary");
            mTxt_level.setBackground(getResources().getDrawable(R.drawable.mcq_card_status_background));
        } else if (a == 2 || a == 3) {
            mTxt_level.setText("Learning");
            mTxt_level.setBackground(getResources().getDrawable(R.drawable.mcq_card_status_yellow));
        } else {
            //Toast.makeText(MemorizeVersion1.this, "Congratulation, you got the highest mark!", Toast.LENGTH_SHORT).show();
            mTxt_level.setText("Master");
            mTxt_level.setBackground(getResources().getDrawable(R.drawable.mcq_card_status_green));
        }
    }

    public void idEachQuest(String a, String b, String c) {
        String id = "ID: " + a + "_" + b + "_" + c;
        mTxt_id.setText(id);
    }

    public int levelINCREASE(int a) {
//        Toast.makeText(getApplicationContext(), "103, totalPoint: "+totalPoint+"totalQ: "+totalQ, Toast.LENGTH_SHORT).show();
        if (totalPoint == totalQ) {
            if (a < 2) {
                a++;
                return a;
            } else if (a == 2 || a == 3) {
                a++;
                return a;
            } else {
                a = 4;
                return a;
            }
        } else {
            a = level_cards;
            return a;
        }
    }

    public int levelDECREASE(int a) {
        if (a < 3) {
            return a;
        } else {
            a--;
            return a;
        }
    }

    //question status for each question
    public void eachQuestStatus() {
        switch (questionN[mQuestNum - 1]) {
            case "q1":
                updateLevelEachQuestionStatus(q1);
                break;
            case "q2":
                updateLevelEachQuestionStatus(q2);
                break;
            case "q3":
                updateLevelEachQuestionStatus(q3);
                break;
            case "q4":
                updateLevelEachQuestionStatus(q4);
                break;
            case "q5":
                updateLevelEachQuestionStatus(q5);
                break;
            case "q6":
                updateLevelEachQuestionStatus(q6);
                break;
            case "q7":
                updateLevelEachQuestionStatus(q7);
                break;
            case "q8":
                updateLevelEachQuestionStatus(q8);
                break;
            case "q9":
                updateLevelEachQuestionStatus(q9);
                break;
            case "q10":
                updateLevelEachQuestionStatus(q10);
                break;
            case "q11":
                updateLevelEachQuestionStatus(q11);
                break;
            case "q12":
                updateLevelEachQuestionStatus(q12);
                break;
            case "q13":
                updateLevelEachQuestionStatus(q13);
                break;
            case "q14":
                updateLevelEachQuestionStatus(q14);
                break;
            case "q15":
                updateLevelEachQuestionStatus(q15);
                break;
            case "q16":
                updateLevelEachQuestionStatus(q16);
                break;
            case "q17":
                updateLevelEachQuestionStatus(q17);
                break;
            case "q18":
                updateLevelEachQuestionStatus(q18);
                break;
            case "q19":
                updateLevelEachQuestionStatus(q19);
                break;
            case "q20":
                updateLevelEachQuestionStatus(q20);
                break;
            case "q21":
                updateLevelEachQuestionStatus(q21);
                break;
            case "q22":
                updateLevelEachQuestionStatus(q22);
                break;
            case "q23":
                updateLevelEachQuestionStatus(q23);
                break;
            case "q24":
                updateLevelEachQuestionStatus(q24);
                break;
            case "q25":
                updateLevelEachQuestionStatus(q25);
                break;
            case "q26":
                updateLevelEachQuestionStatus(q26);
                break;
            case "q27":
                updateLevelEachQuestionStatus(q27);
                break;
            case "q28":
                updateLevelEachQuestionStatus(q28);
                break;
            case "q29":
                updateLevelEachQuestionStatus(q29);
                break;
            case "q30":
                updateLevelEachQuestionStatus(q30);
                break;
            case "q31":
                updateLevelEachQuestionStatus(q31);
                break;
            case "q32":
                updateLevelEachQuestionStatus(q32);
                break;
            case "q33":
                updateLevelEachQuestionStatus(q33);
                break;
            case "q34":
                updateLevelEachQuestionStatus(q34);
                break;
            case "q35":
                updateLevelEachQuestionStatus(q35);
                break;
            case "q36":
                updateLevelEachQuestionStatus(q36);
                break;
            case "q37":
                updateLevelEachQuestionStatus(q37);
                break;
            case "q38":
                updateLevelEachQuestionStatus(q38);
                break;
            case "q39":
                updateLevelEachQuestionStatus(q39);
                break;
            case "q40":
                updateLevelEachQuestionStatus(q40);
                break;
            case "q41":
                updateLevelEachQuestionStatus(q41);
                break;

        }
    }

    //question status per question
    public void updateLevelEachQuestionStatus(int a) {
        if (a < 2) {
            mTxt_PointEachQ.setText("Primary");

        } else if (a == 2 || a == 3) {
            mTxt_PointEachQ.setText("Learning");
        } else {
            mTxt_PointEachQ.setText("Master");
        }
    }

    public void stopRepeatMarkCountSUCCESS() {
        switch (questionN[mQuestNum - 1]) {
            case "q1":
                if (r1 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r1 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q2":
                if (r2 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r2 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q3":
                if (r3 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r3 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q4":
                if (r4 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r4 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q5":
                if (r5 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r5 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q6":
                if (r6 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r6 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q7":
                if (r7 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r7 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q8":
                if (r8 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r8 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q9":
                if (r9 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r9 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q10":
                if (r10 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r10 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q11":
                if (r11 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r11 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q12":
                if (r12 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r12 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q13":
                if (r13 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r13 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q14":
                if (r14 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r14 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q15":
                if (r15 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r15 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q16":
                if (r16 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r16 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q17":
                if (r17 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r17 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q18":
                if (r18 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r18 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q19":
                if (r19 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r19 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q20":
                if (r20 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r20 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q21":
                if (r21 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r21 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q22":
                if (r22 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r22 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q23":
                if (r23 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r23 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q24":
                if (r24 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r24 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q25":
                if (r25 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r25 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q26":
                if (r26 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r26 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q27":
                if (r27 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r27 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q28":
                if (r28 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r28 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q29":
                if (r29 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r29 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q30":
                if (r30 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r30 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q31":
                if (r31 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r31 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q32":
                if (r32 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r32 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q33":
                if (r33 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r33 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q34":
                if (r34 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r34 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q35":
                if (r35 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r35 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q36":
                if (r36 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r36 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q37":
                if (r37 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r37 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q38":
                if (r38 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r38 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q39":
                if (r39 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r39 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q40":
                if (r40 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS();
                    break;
                }
                if (r40 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void stopRepeatMarkCountFAILURE() {
        switch (questionN[mQuestNum - 1]) {
            case "q1":
                if (r1 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r1 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q2":
                if (r2 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r2 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q3":
                if (r3 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r3 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q4":
                if (r4 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r4 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q5":
                if (r5 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r5 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q6":
                if (r6 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r6 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q7":
                if (r7 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r7 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q8":
                if (r8 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r8 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q9":
                if (r9 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r9 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q10":
                if (r10 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r10 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q11":
                if (r11 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r11 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q12":
                if (r12 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r12 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q13":
                if (r13 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r13 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q14":
                if (r14 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r14 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q15":
                if (r15 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r15 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q16":
                if (r16 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r16 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q17":
                if (r17 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r17 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q18":
                if (r18 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r18 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q19":
                if (r19 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r19 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q20":
                if (r20 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r20 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q21":
                if (r21 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r21 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q22":
                if (r22 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r22 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q23":
                if (r23 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r23 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q24":
                if (r24 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r24 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q25":
                if (r25 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r25 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q26":
                if (r26 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r26 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q27":
                if (r27 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r27 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q28":
                if (r28 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r28 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q29":
                if (r29 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r29 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q30":
                if (r30 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r30 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q31":
                if (r31 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r31 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q32":
                if (r32 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r32 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q33":
                if (r33 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r33 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q34":
                if (r34 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r34 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q35":
                if (r35 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r35 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q36":
                if (r36 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r36 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q37":
                if (r37 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r37 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q38":
                if (r38 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r38 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q39":
                if (r39 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r39 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q40":
                if (r40 == 0) {
                    eachQuestStatusFAILURE();
                    break;
                }
                if (r40 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void eachQuestStatusSUCCESS() {
        switch (questionN[answeredQn]) {
            case "q1":
                q1 = eachQuestStatusINCREASE(q1);

                r1 = 1;
                //For room database use only
                level_question = q1;
                updateLevelEachQuestionStatus(q1);
                break;
            case "q2":
                q2 = eachQuestStatusINCREASE(q2);

                r2 = 1;
                //For room database use only
                level_question = q2;
                updateLevelEachQuestionStatus(q2);
                break;
            case "q3":
                q3 = eachQuestStatusINCREASE(q3);

                r3 = 1;
                //For room database use only
                level_question = q3;
                updateLevelEachQuestionStatus(q3);
                break;
            case "q4":
                q4 = eachQuestStatusINCREASE(q4);

                r4 = 1;
                //For room database use only
                level_question = q4;
                updateLevelEachQuestionStatus(q4);
                break;
            case "q5":
                q5 = eachQuestStatusINCREASE(q5);

                r5 = 1;
                //For room database use only
                level_question = q5;
                updateLevelEachQuestionStatus(q5);
                break;
            case "q6":
                q6 = eachQuestStatusINCREASE(q6);

                r6 = 1;
                //For room database use only
                level_question = q6;
                updateLevelEachQuestionStatus(q6);
                break;
            case "q7":
                q7 = eachQuestStatusINCREASE(q7);

                r7 = 1;
                //For room database use only
                level_question = q7;
                updateLevelEachQuestionStatus(q7);
                break;
            case "q8":
                q8 = eachQuestStatusINCREASE(q8);

                r8 = 1;
                //For room database use only
                level_question = q8;
                updateLevelEachQuestionStatus(q8);
                break;
            case "q9":
                q9 = eachQuestStatusINCREASE(q9);

                r9 = 1;
                //For room database use only
                level_question = q9;
                updateLevelEachQuestionStatus(q9);
                break;
            case "q10":
                q10 = eachQuestStatusINCREASE(q10);

                r10 = 1;
                //For room database use only
                level_question = q10;
                updateLevelEachQuestionStatus(q10);
                break;
            case "q11":
                q11 = eachQuestStatusINCREASE(q11);

                r11 = 1;
                //For room database use only
                level_question = q11;
                updateLevelEachQuestionStatus(q11);
                break;
            case "q12":
                q12 = eachQuestStatusINCREASE(q12);

                r12 = 1;
                //For room database use only
                level_question = q12;
                updateLevelEachQuestionStatus(q12);
                break;
            case "q13":
                q13 = eachQuestStatusINCREASE(q13);

                r13 = 1;
                //For room database use only
                level_question = q13;
                updateLevelEachQuestionStatus(q13);
                break;
            case "q14":
                q14 = eachQuestStatusINCREASE(q14);

                r14 = 1;
                //For room database use only
                level_question = q14;
                updateLevelEachQuestionStatus(q14);
                break;
            case "q15":
                q15 = eachQuestStatusINCREASE(q15);

                r15 = 1;
                //For room database use only
                level_question = q15;
                updateLevelEachQuestionStatus(q15);
                break;
            case "q16":
                q16 = eachQuestStatusINCREASE(q16);

                r16 = 1;
                //For room database use only
                level_question = q16;
                updateLevelEachQuestionStatus(q16);
                break;
            case "q17":
                q17 = eachQuestStatusINCREASE(q17);

                r17 = 1;
                //For room database use only
                level_question = q17;
                updateLevelEachQuestionStatus(q17);
                break;
            case "q18":
                q18 = eachQuestStatusINCREASE(q18);

                r18 = 1;
                //For room database use only
                level_question = q18;
                updateLevelEachQuestionStatus(q18);
                break;
            case "q19":
                q19 = eachQuestStatusINCREASE(q19);

                r19 = 1;
                //For room database use only
                level_question = q19;
                updateLevelEachQuestionStatus(q19);
                break;
            case "q20":
                q20 = eachQuestStatusINCREASE(q20);

                r20 = 1;
                //For room database use only
                level_question = q20;
                updateLevelEachQuestionStatus(q20);
                break;
            case "q21":
                q21 = eachQuestStatusINCREASE(q21);

                r21 = 1;
                //For room database use only
                level_question = q21;
                updateLevelEachQuestionStatus(q21);
                break;
            case "q22":
                q22 = eachQuestStatusINCREASE(q22);

                r22 = 1;
                //For room database use only
                level_question = q22;
                updateLevelEachQuestionStatus(q22);
                break;
            case "q23":
                q23 = eachQuestStatusINCREASE(q23);

                r23 = 1;
                //For room database use only
                level_question = q23;
                updateLevelEachQuestionStatus(q23);
                break;
            case "q24":
                q24 = eachQuestStatusINCREASE(q24);

                r24 = 1;
                //For room database use only
                level_question = q24;
                updateLevelEachQuestionStatus(q24);
                break;
            case "q25":
                q25 = eachQuestStatusINCREASE(q25);

                r25 = 1;
                //For room database use only
                level_question = q25;
                updateLevelEachQuestionStatus(q25);
                break;
            case "q26":
                q26 = eachQuestStatusINCREASE(q26);

                r26 = 1;
                //For room database use only
                level_question = q26;
                updateLevelEachQuestionStatus(q26);
                break;
            case "q27":
                q27 = eachQuestStatusINCREASE(q27);

                r27 = 1;
                //For room database use only
                level_question = q27;
                updateLevelEachQuestionStatus(q27);
                break;
            case "q28":
                q28 = eachQuestStatusINCREASE(q28);

                r28 = 1;
                //For room database use only
                level_question = q28;
                updateLevelEachQuestionStatus(q28);
                break;
            case "q29":
                q29 = eachQuestStatusINCREASE(q29);

                r29 = 1;
                //For room database use only
                level_question = q29;
                updateLevelEachQuestionStatus(q29);
                break;
            case "q30":
                q30 = eachQuestStatusINCREASE(q30);

                r30 = 1;
                //For room database use only
                level_question = q30;
                updateLevelEachQuestionStatus(q30);
                break;
            case "q31":
                q31 = eachQuestStatusINCREASE(q31);

                r31 = 1;
                //For room database use only
                level_question = q31;
                updateLevelEachQuestionStatus(q31);
                break;
            case "q32":
                q32 = eachQuestStatusINCREASE(q32);

                r32 = 1;
                //For room database use only
                level_question = q32;
                updateLevelEachQuestionStatus(q32);
                break;
            case "q33":
                q33 = eachQuestStatusINCREASE(q33);

                r33 = 1;
                //For room database use only
                level_question = q33;
                updateLevelEachQuestionStatus(q33);
                break;
            case "q34":
                q34 = eachQuestStatusINCREASE(q34);

                r34 = 1;
                //For room database use only
                level_question = q34;
                updateLevelEachQuestionStatus(q34);
                break;
            case "q35":
                q35 = eachQuestStatusINCREASE(q35);

                r35 = 1;
                //For room database use only
                level_question = q35;
                updateLevelEachQuestionStatus(q35);
                break;
            case "q36":
                q36 = eachQuestStatusINCREASE(q36);

                r36 = 1;
                //For room database use only
                level_question = q36;
                updateLevelEachQuestionStatus(q36);
                break;
            case "q37":
                q37 = eachQuestStatusINCREASE(q37);

                r37 = 1;
                //For room database use only
                level_question = q37;
                updateLevelEachQuestionStatus(q37);
                break;
            case "q38":
                q38 = eachQuestStatusINCREASE(q38);

                r38 = 1;
                //For room database use only
                level_question = q38;
                updateLevelEachQuestionStatus(q38);
                break;
            case "q39":
                q39 = eachQuestStatusINCREASE(q39);

                r39 = 1;
                //For room database use only
                level_question = q39;
                updateLevelEachQuestionStatus(q39);
                break;
            case "q40":
                q40 = eachQuestStatusINCREASE(q40);

                r40 = 1;
                //For room database use only
                level_question = q40;
                updateLevelEachQuestionStatus(q40);
                break;
            case "q41":
                q41 = eachQuestStatusINCREASE(q41);
                updateLevelEachQuestionStatus(q41);
                break;

        }
    }

    public void eachQuestStatusFAILURE() {
        switch (questionN[unAnsweredQN]) {
            case "q1":
                q1 = eachQuestStatusDECREASE(q1);
                r1 = 1;
                //For room database use only
                level_question = q1;
                updateLevelEachQuestionStatus(q1);
                break;
            case "q2":
                q2 = eachQuestStatusDECREASE(q2);
                r2 = 1;
                //For room database use only
                level_question = q2;
                updateLevelEachQuestionStatus(q2);
                break;
            case "q3":
                q3 = eachQuestStatusDECREASE(q3);
                r3 = 1;
                //For room database use only
                level_question = q3;
                updateLevelEachQuestionStatus(q3);
                break;
            case "q4":
                q4 = eachQuestStatusDECREASE(q4);
                r4 = 1;
                //For room database use only
                level_question = q4;
                updateLevelEachQuestionStatus(q4);
                break;
            case "q5":
                q5 = eachQuestStatusDECREASE(q5);
                r5 = 1;
                //For room database use only
                level_question = q5;
                updateLevelEachQuestionStatus(q5);
                break;
            case "q6":
                q6 = eachQuestStatusDECREASE(q6);
                r6 = 1;
                //For room database use only
                level_question = q6;
                updateLevelEachQuestionStatus(q6);
                break;
            case "q7":
                q7 = eachQuestStatusDECREASE(q7);
                r7 = 1;
                //For room database use only
                level_question = q7;
                updateLevelEachQuestionStatus(q7);
                break;
            case "q8":
                q8 = eachQuestStatusDECREASE(q8);
                r8 = 1;
                //For room database use only
                level_question = q8;
                updateLevelEachQuestionStatus(q8);
                break;
            case "q9":
                q9 = eachQuestStatusDECREASE(q9);
                r9 = 1;
                //For room database use only
                level_question = q9;
                updateLevelEachQuestionStatus(q9);
                break;
            case "q10":
                q10 = eachQuestStatusDECREASE(q10);
                r10 = 1;
                //For room database use only
                level_question = q10;
                updateLevelEachQuestionStatus(q10);
                break;
            case "q11":
                q11 = eachQuestStatusDECREASE(q11);
                r11 = 1;
                //For room database use only
                level_question = q11;
                updateLevelEachQuestionStatus(q11);
                break;
            case "q12":
                q12 = eachQuestStatusDECREASE(q12);
                r12 = 1;
                //For room database use only
                level_question = q12;
                updateLevelEachQuestionStatus(q12);
                break;
            case "q13":
                q13 = eachQuestStatusDECREASE(q13);
                r13 = 1;
                //For room database use only
                level_question = q13;
                updateLevelEachQuestionStatus(q13);
                break;
            case "q14":
                q14 = eachQuestStatusDECREASE(q14);
                r14 = 1;
                //For room database use only
                level_question = q14;
                updateLevelEachQuestionStatus(q14);
                break;
            case "q15":
                q15 = eachQuestStatusDECREASE(q15);
                r15 = 1;
                //For room database use only
                level_question = q15;
                updateLevelEachQuestionStatus(q15);
                break;
            case "q16":
                q16 = eachQuestStatusDECREASE(q16);
                r16 = 1;
                //For room database use only
                level_question = q16;
                updateLevelEachQuestionStatus(q16);
                break;
            case "q17":
                q17 = eachQuestStatusDECREASE(q17);
                r17 = 1;
                //For room database use only
                level_question = q17;
                updateLevelEachQuestionStatus(q17);
                break;
            case "q18":
                q18 = eachQuestStatusDECREASE(q18);
                r18 = 1;
                //For room database use only
                level_question = q18;
                updateLevelEachQuestionStatus(q18);
                break;
            case "q19":
                q19 = eachQuestStatusDECREASE(q19);
                r19 = 1;
                //For room database use only
                level_question = q19;
                updateLevelEachQuestionStatus(q19);
                break;
            case "q20":
                q20 = eachQuestStatusDECREASE(q20);
                r20 = 1;
                //For room database use only
                level_question = q20;
                updateLevelEachQuestionStatus(q20);
                break;
            case "q21":
                q21 = eachQuestStatusDECREASE(q21);
                r21 = 1;
                //For room database use only
                level_question = q21;
                updateLevelEachQuestionStatus(q21);
                break;
            case "q22":
                q22 = eachQuestStatusDECREASE(q22);
                r22 = 1;
                //For room database use only
                level_question = q22;
                updateLevelEachQuestionStatus(q22);
                break;
            case "q23":
                q23 = eachQuestStatusDECREASE(q23);
                r23 = 1;
                //For room database use only
                level_question = q23;
                updateLevelEachQuestionStatus(q23);
                break;
            case "q24":
                q24 = eachQuestStatusDECREASE(q24);
                r24 = 1;
                //For room database use only
                level_question = q24;
                updateLevelEachQuestionStatus(q24);
                break;
            case "q25":
                q25 = eachQuestStatusDECREASE(q25);
                r25 = 1;
                //For room database use only
                level_question = q25;
                updateLevelEachQuestionStatus(q25);
                break;
            case "q26":
                q26 = eachQuestStatusDECREASE(q26);
                r26 = 1;
                //For room database use only
                level_question = q26;
                updateLevelEachQuestionStatus(q26);
                break;
            case "q27":
                q27 = eachQuestStatusDECREASE(q27);
                r27 = 1;
                //For room database use only
                level_question = q27;
                updateLevelEachQuestionStatus(q27);
                break;
            case "q28":
                q28 = eachQuestStatusDECREASE(q28);
                r28 = 1;
                //For room database use only
                level_question = q28;
                updateLevelEachQuestionStatus(q28);
                break;
            case "q29":
                q29 = eachQuestStatusDECREASE(q29);
                r29 = 1;
                //For room database use only
                level_question = q29;
                updateLevelEachQuestionStatus(q29);
                break;
            case "q30":
                q30 = eachQuestStatusDECREASE(q30);
                r30 = 1;
                //For room database use only
                level_question = q30;
                updateLevelEachQuestionStatus(q30);
                break;
            case "q31":
                q31 = eachQuestStatusDECREASE(q31);
                r31 = 1;
                //For room database use only
                level_question = q31;
                updateLevelEachQuestionStatus(q31);
                break;
            case "q32":
                q32 = eachQuestStatusDECREASE(q32);
                r32 = 1;
                //For room database use only
                level_question = q32;
                updateLevelEachQuestionStatus(q32);
                break;
            case "q33":
                q33 = eachQuestStatusDECREASE(q33);
                r33 = 1;
                //For room database use only
                level_question = q33;
                updateLevelEachQuestionStatus(q33);
                break;
            case "q34":
                q34 = eachQuestStatusDECREASE(q34);
                r34 = 1;
                //For room database use only
                level_question = q34;
                updateLevelEachQuestionStatus(q34);
                break;
            case "q35":
                q35 = eachQuestStatusDECREASE(q35);
                r35 = 1;
                //For room database use only
                level_question = q35;
                updateLevelEachQuestionStatus(q35);
                break;
            case "q36":
                q36 = eachQuestStatusDECREASE(q36);
                r36 = 1;
                //For room database use only
                level_question = q36;
                updateLevelEachQuestionStatus(q36);
                break;
            case "q37":
                q37 = eachQuestStatusDECREASE(q37);
                r37 = 1;
                //For room database use only
                level_question = q37;
                updateLevelEachQuestionStatus(q37);
                break;
            case "q38":
                q38 = eachQuestStatusDECREASE(q38);
                r38 = 1;
                //For room database use only
                level_question = q38;
                updateLevelEachQuestionStatus(q38);
                break;
            case "q39":
                q39 = eachQuestStatusDECREASE(q39);
                r39 = 1;
                //For room database use only
                level_question = q39;
                updateLevelEachQuestionStatus(q39);
                break;
            case "q40":
                q40 = eachQuestStatusDECREASE(q40);
                r40 = 1;
                //For room database use only
                level_question = q40;
                updateLevelEachQuestionStatus(q40);
                break;
            case "q41":
                q41 = eachQuestStatusDECREASE(q41);
                updateLevelEachQuestionStatus(q41);
                break;

        }
    }

    public int eachQuestStatusINCREASE(int a) {
        if (a < 2) {
            a++;
            return a;
        } else if (a == 2 || a == 3) {
            a++;
            return a;
        } else {
            a = 4;
            return a;
        }
    }

    public int eachQuestStatusDECREASE(int a) {
        if (a < 2) {
            return a;
        } else {
            a--;
            return a;
        }
    }

    public void make_int_r_zero() {
        r1 = 0;
        r2 = 0;
        r3 = 0;
        r4 = 0;
        r5 = 0;
        r6 = 0;
        r7 = 0;
        r8 = 0;
        r9 = 0;
        r10 = 0;
        r11 = 0;
        r12 = 0;
        r13 = 0;
        r14 = 0;
        r15 = 0;
        r16 = 0;
        r17 = 0;
        r18 = 0;
        r19 = 0;
        r20 = 0;
        r21 = 0;
        r22 = 0;
        r23 = 0;
        r24 = 0;
        r25 = 0;
        r26 = 0;
        r27 = 0;
        r28 = 0;
        r29 = 0;
        r30 = 0;
        r31 = 0;
        r32 = 0;
        r33 = 0;
        r34 = 0;
        r35 = 0;
        r36 = 0;
        r37 = 0;
        r38 = 0;
        r39 = 0;
        r40 = 0;
    }

    public void updateQuestion() {
        id = child_Name + "_" + mPost_key + "_" + questionN[mQuestNum - 1];
        countPriLernMast();
        eachQuestStatus();
        idEachQuest(child_Name, mPost_key, questionN[mQuestNum - 1]);

        progreesBarBackgroundVISIBLE();

        Firebase mTotalQ = new Firebase("https://mba-master-bdf52.firebaseio.com/" + child_Name + "/" + mPost_key + "/info/totalQ");
        mTotalQ.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                totalQ = Integer.parseInt(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Firebase mTotalE = new Firebase("https://mba-master-bdf52.firebaseio.com/" + child_Name + "/" + mPost_key + "/info/totalE");
        mTotalE.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                totalE = Integer.parseInt(dataSnapshot.getValue(String.class));

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Firebase mQuestion = new Firebase("https://mba-master-bdf52.firebaseio.com/" + child_Name + "/" + mPost_key + "/" + mQuestNum + "/q");
        mQuestion.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                question = dataSnapshot.getValue(String.class);
                if (question == null || question.equals("")) {
                    Toast.makeText(getApplicationContext(), "No Question Received From Database", Toast.LENGTH_LONG).show();
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mTxt_quest.setText(Html.fromHtml(question, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    mTxt_quest.setText(Html.fromHtml(question));
                }

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


        Firebase mE1 = new Firebase("https://mba-master-bdf52.firebaseio.com/" + child_Name + "/" + mPost_key + "/" + mQuestNum + "/e");
        mE1.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                e1 = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Firebase mE2 = new Firebase("https://mba-master-bdf52.firebaseio.com/" + child_Name + "/" + mPost_key + "/" + mQuestNum + "/ee");
        //mChoice3.keepSynced(true);
        mE2.addValueEventListener(new com.firebase.client.ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                e2 = dataSnapshot.getValue(String.class);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        Firebase mM = new Firebase("https://mba-master-bdf52.firebaseio.com/" + child_Name + "/" + mPost_key + "/" + mQuestNum + "/m");
        mM.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(com.firebase.client.DataSnapshot dataSnapshot) {
                m = dataSnapshot.getValue(String.class);
//                Toast.makeText(getApplicationContext(), "from update question, value of m= "+m, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
        progressBarBackgroundGONE();
//        setVisibilityForImage(m); create problem

        clicked = true;
        mTxt_known.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = false;
                //question status for each question
                eachQuestStatus();
                next_Q_or_not_KNOWN();
                readFromDatabase();
            }
        });


        mTxt_unknown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = false;
                eachQuestStatus();
                next_Q_or_not_unknown();
                readFromDatabase();
            }
        });

        mTxt_Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clicked = true;
                next_Q_or_not_SUBMIT();
//                setVisibilityForImage(m);
                eachQuestStatus();
            }
        });

    }

    public void progreesBarBackgroundVISIBLE() {
        progressBar2.setVisibility(View.VISIBLE);
        mTxt_Submit.setClickable(false);
    }

    public void progressBarBackgroundGONE() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        progressBar2.setVisibility(View.GONE);
                        mTxt_Submit.setClickable(true);
                    }
                });
            }
        }).start();
    }

    public void stopRepeatMarkCountFAILURE_DB() {
        switch (questionN[mQuestNum - 1]) {
            case "q1":
                if (r1 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r1 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q2":
                if (r2 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r2 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q3":
                if (r3 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r3 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q4":
                if (r4 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r4 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q5":
                if (r5 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r5 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q6":
                if (r6 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r6 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q7":
                if (r7 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r7 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q8":
                if (r8 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r8 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q9":
                if (r9 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r9 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q10":
                if (r10 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r10 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q11":
                if (r11 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r11 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q12":
                if (r12 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r12 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q13":
                if (r13 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r13 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q14":
                if (r14 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r14 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q15":
                if (r15 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r15 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q16":
                if (r16 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r16 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q17":
                if (r17 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r17 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q18":
                if (r18 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r18 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q19":
                if (r19 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r19 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q20":
                if (r20 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r20 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q21":
                if (r21 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r21 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q22":
                if (r22 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r22 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q23":
                if (r23 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r23 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q24":
                if (r24 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r24 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q25":
                if (r25 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r25 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q26":
                if (r26 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r26 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q27":
                if (r27 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r27 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q28":
                if (r28 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r28 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q29":
                if (r29 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r29 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q30":
                if (r30 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r30 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q31":
                if (r31 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r31 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q32":
                if (r32 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r32 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q33":
                if (r33 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r33 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q34":
                if (r34 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r34 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q35":
                if (r35 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r35 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q36":
                if (r36 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r36 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q37":
                if (r37 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r37 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q38":
                if (r38 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r38 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q39":
                if (r39 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r39 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q40":
                if (r40 == 0) {
                    eachQuestStatusFAILURE_DB();
                    break;
                }
                if (r40 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void eachQuestStatusFAILURE_DB() {
        level_question = eachQuestStatusDECREASE(level_question);

        switch (questionN[unAnsweredQN]) {
            case "q1":
                q1 = level_question;
                r1 = 1;
//                For room database use only
//                level_question = q1;
                updateLevelEachQuestionStatus(q1);
                break;
            case "q2":
                q2 = level_question;
                r2 = 1;
//                //For room database use only
//                level_question = q2;
                updateLevelEachQuestionStatus(q2);
                break;
            case "q3":
                q3 = level_question;
                r3 = 1;
                //For room database use only
//                level_question = q3;
                updateLevelEachQuestionStatus(q3);
                break;
            case "q4":
                q4 = level_question;
                r4 = 1;
                //For room database use only
                level_question = q4;
                updateLevelEachQuestionStatus(q4);
                break;
            case "q5":
                q5 = level_question;
                r5 = 1;
                //For room database use only
//                level_question = q5;
                updateLevelEachQuestionStatus(q5);
                break;
            case "q6":
                q6 = level_question;
                r6 = 1;
                //For room database use only
//                level_question = q6;
                updateLevelEachQuestionStatus(q6);
                break;
            case "q7":
                q7 = level_question;
                r7 = 1;
                //For room database use only
//                level_question = q7;
                updateLevelEachQuestionStatus(q7);
                break;
            case "q8":
                q8 = level_question;
                r8 = 1;
                //For room database use only
//                level_question = q8;
                updateLevelEachQuestionStatus(q8);
                break;
            case "q9":
                q9 = level_question;
                r9 = 1;
                //For room database use only
//                level_question = q9;
                updateLevelEachQuestionStatus(q9);
                break;
            case "q10":
                q10 = level_question;
                r10 = 1;
                //For room database use only
//                level_question = q10;
                updateLevelEachQuestionStatus(q10);
                break;
            case "q11":
                q11 = level_question;
                r11 = 1;
                //For room database use only
//                level_question = q11;
                updateLevelEachQuestionStatus(q11);
                break;
            case "q12":
                q12 = level_question;
                r12 = 1;
                //For room database use only
//                level_question = q12;
                updateLevelEachQuestionStatus(q12);
                break;
            case "q13":
                q13 = level_question;
                r13 = 1;
                //For room database use only
//                level_question = q13;
                updateLevelEachQuestionStatus(q13);
                break;
            case "q14":
                q14 = level_question;
                r14 = 1;
                //For room database use only
//                level_question = q14;
                updateLevelEachQuestionStatus(q14);
                break;
            case "q15":
                q15 = level_question;
                r15 = 1;
                //For room database use only
//                level_question = q15;
                updateLevelEachQuestionStatus(q15);
                break;
            case "q16":
                q16 = level_question;
                r16 = 1;
                //For room database use only
//                level_question = q16;
                updateLevelEachQuestionStatus(q16);
                break;
            case "q17":
                q17 = level_question;
                r17 = 1;
                //For room database use only
//                level_question = q17;
                updateLevelEachQuestionStatus(q17);
                break;
            case "q18":
                q18 = level_question;
                r18 = 1;
                //For room database use only
//                level_question = q18;
                updateLevelEachQuestionStatus(q18);
                break;
            case "q19":
                q19 = level_question;
                r19 = 1;
                //For room database use only
//                level_question = q19;
                updateLevelEachQuestionStatus(q19);
                break;
            case "q20":
                q20 = level_question;
                r20 = 1;
                //For room database use only
//                level_question = q20;
                updateLevelEachQuestionStatus(q20);
                break;
            case "q21":
                q21 = level_question;
                r21 = 1;
                //For room database use only
//                level_question = q21;
                updateLevelEachQuestionStatus(q21);
                break;
            case "q22":
                q22 = level_question;
                r22 = 1;
                //For room database use only
//                level_question = q22;
                updateLevelEachQuestionStatus(q22);
                break;
            case "q23":
                q23 = level_question;
                r23 = 1;
                //For room database use only
//                level_question = q23;
                updateLevelEachQuestionStatus(q23);
                break;
            case "q24":
                q24 = level_question;
                r24 = 1;
                //For room database use only
//                level_question = q24;
                updateLevelEachQuestionStatus(q24);
                break;
            case "q25":
                q25 = level_question;
                r25 = 1;
                //For room database use only
//                level_question = q25;
                updateLevelEachQuestionStatus(q25);
                break;
            case "q26":
                q26 = level_question;
                r26 = 1;
                //For room database use only
//                level_question = q26;
                updateLevelEachQuestionStatus(q26);
                break;
            case "q27":
                q27 = level_question;
                r27 = 1;
                //For room database use only
//                level_question = q27;
                updateLevelEachQuestionStatus(q27);
                break;
            case "q28":
                q28 = level_question;
                r28 = 1;
                //For room database use only
//                level_question = q28;
                updateLevelEachQuestionStatus(q28);
                break;
            case "q29":
                q29 = level_question;
                r29 = 1;
                //For room database use only
//                level_question = q29;
                updateLevelEachQuestionStatus(q29);
                break;
            case "q30":
                q30 = level_question;
                r30 = 1;
                //For room database use only
//                level_question = q30;
                updateLevelEachQuestionStatus(q30);
                break;
            case "q31":
                q31 = level_question;
                r31 = 1;
                //For room database use only
//                level_question = q31;
                updateLevelEachQuestionStatus(q31);
                break;
            case "q32":
                q32 = level_question;
                r32 = 1;
                //For room database use only
//                level_question = q32;
                updateLevelEachQuestionStatus(q32);
                break;
            case "q33":
                q33 = level_question;
                r33 = 1;
                //For room database use only
//                level_question = q33;
                updateLevelEachQuestionStatus(q33);
                break;
            case "q34":
                q34 = level_question;
                r34 = 1;
                //For room database use only
//                level_question = q34;
                updateLevelEachQuestionStatus(q34);
                break;
            case "q35":
                q35 = level_question;
                r35 = 1;
                //For room database use only
//                level_question = q35;
                updateLevelEachQuestionStatus(q35);
                break;
            case "q36":
                q36 = level_question;
                r36 = 1;
                //For room database use only
//                level_question = q36;
                updateLevelEachQuestionStatus(q36);
                break;
            case "q37":
                q37 = level_question;
                r37 = 1;
                //For room database use only
//                level_question = q37;
                updateLevelEachQuestionStatus(q37);
                break;
            case "q38":
                q38 = level_question;
                r38 = 1;
                //For room database use only
//                level_question = q38;
                updateLevelEachQuestionStatus(q38);
                break;
            case "q39":
                q39 = level_question;
                r39 = 1;
                //For room database use only
//                level_question = q39;
                updateLevelEachQuestionStatus(q39);
                break;
            case "q40":
                q40 = level_question;
                r40 = 1;
                //For room database use only
//                level_question = q40;
                updateLevelEachQuestionStatus(q40);
                break;
            case "q41":
                q41 = eachQuestStatusDECREASE(q41);
                updateLevelEachQuestionStatus(q41);
                break;

        }
    }

    public void stopRepeatMarkCountSUCCESS_DB() {
        switch (questionN[mQuestNum - 1]) {
            case "q1":
                if (r1 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r1 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q2":
                if (r2 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r2 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q3":
                if (r3 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r3 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q4":
                if (r4 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r4 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q5":
                if (r5 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r5 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q6":
                if (r6 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r6 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q7":
                if (r7 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r7 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q8":
                if (r8 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r8 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q9":
                if (r9 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r9 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q10":
                if (r10 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r10 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q11":
                if (r11 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r11 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q12":
                if (r12 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r12 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q13":
                if (r13 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r13 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q14":
                if (r14 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r14 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q15":
                if (r15 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r15 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q16":
                if (r16 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r16 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q17":
                if (r17 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r17 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q18":
                if (r18 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r18 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q19":
                if (r19 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r19 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q20":
                if (r20 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r20 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q21":
                if (r21 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r21 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q22":
                if (r22 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r22 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q23":
                if (r23 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r23 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q24":
                if (r24 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r24 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q25":
                if (r25 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r25 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q26":
                if (r26 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r26 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q27":
                if (r27 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r27 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q28":
                if (r28 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r28 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q29":
                if (r29 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r29 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q30":
                if (r30 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r30 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q31":
                if (r31 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r31 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q32":
                if (r32 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r32 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q33":
                if (r33 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r33 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q34":
                if (r34 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r34 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q35":
                if (r35 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r35 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q36":
                if (r36 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r36 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q37":
                if (r37 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r37 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q38":
                if (r38 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r38 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q39":
                if (r39 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r39 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
            case "q40":
                if (r40 == 0) {
                    totalPoint++;
                    eachQuestStatusSUCCESS_DB();
                    break;
                }
                if (r40 == 1) {
                    Toast.makeText(MemorizeVersion1.this, "You already tried this question", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void eachQuestStatusSUCCESS_DB() {
        level_question = eachQuestStatusINCREASE(level_question);
        switch (questionN[answeredQn]) {
            case "q1":
                q1 = level_question;

                r1 = 1;
                //For room database use only
//                level_question = q1;
                updateLevelEachQuestionStatus(q1);
                break;
            case "q2":
                q2 = level_question;

                r2 = 1;
                //For room database use only
//                level_question = q2;
                updateLevelEachQuestionStatus(q2);
                break;
            case "q3":
                q3 = level_question;

                r3 = 1;
                //For room database use only
//                level_question = q3;
                updateLevelEachQuestionStatus(q3);
                break;
            case "q4":
                q4 = level_question;

                r4 = 1;
                //For room database use only
//                level_question = q4;
                updateLevelEachQuestionStatus(q4);
                break;
            case "q5":
                q5 = level_question;

                r5 = 1;
                //For room database use only
//                level_question = q5;
                updateLevelEachQuestionStatus(q5);
                break;
            case "q6":
                q6 = level_question;

                r6 = 1;
                //For room database use only
//                level_question = q6;
                updateLevelEachQuestionStatus(q6);
                break;
            case "q7":
                q7 = level_question;

                r7 = 1;
                //For room database use only
//                level_question = q7;
                updateLevelEachQuestionStatus(q7);
                break;
            case "q8":
                q8 = level_question;

                r8 = 1;
                //For room database use only
//                level_question = q8;
                updateLevelEachQuestionStatus(q8);
                break;
            case "q9":
                q9 = level_question;

                r9 = 1;
                //For room database use only
//                level_question = q9;
                updateLevelEachQuestionStatus(q9);
                break;
            case "q10":
                q10 = level_question;

                r10 = 1;
                //For room database use only
//                level_question = q10;
                updateLevelEachQuestionStatus(q10);
                break;
            case "q11":
                q11 = level_question;

                r11 = 1;
                //For room database use only
//                level_question = q11;
                updateLevelEachQuestionStatus(q11);
                break;
            case "q12":
                q12 = level_question;

                r12 = 1;
                //For room database use only
//                level_question = q12;
                updateLevelEachQuestionStatus(q12);
                break;
            case "q13":
                q13 = level_question;

                r13 = 1;
                //For room database use only
//                level_question = q13;
                updateLevelEachQuestionStatus(q13);
                break;
            case "q14":
                q14 = level_question;

                r14 = 1;
                //For room database use only
//                level_question = q14;
                updateLevelEachQuestionStatus(q14);
                break;
            case "q15":
                q15 = level_question;

                r15 = 1;
                //For room database use only
//                level_question = q15;
                updateLevelEachQuestionStatus(q15);
                break;
            case "q16":
                q16 = level_question;

                r16 = 1;
                //For room database use only
//                level_question = q16;
                updateLevelEachQuestionStatus(q16);
                break;
            case "q17":
                q17 = level_question;

                r17 = 1;
                //For room database use only
//                level_question = q17;
                updateLevelEachQuestionStatus(q17);
                break;
            case "q18":
                q18 = level_question;

                r18 = 1;
                //For room database use only
//                level_question = q18;
                updateLevelEachQuestionStatus(q18);
                break;
            case "q19":
                q19 = level_question;

                r19 = 1;
                //For room database use only
//                level_question = q19;
                updateLevelEachQuestionStatus(q19);
                break;
            case "q20":
                q20 = level_question;

                r20 = 1;
                //For room database use only
//                level_question = q20;
                updateLevelEachQuestionStatus(q20);
                break;
            case "q21":
                q21 = level_question;

                r21 = 1;
                //For room database use only
//                level_question = q21;
                updateLevelEachQuestionStatus(q21);
                break;
            case "q22":
                q22 = level_question;

                r22 = 1;
                //For room database use only
//                level_question = q22;
                updateLevelEachQuestionStatus(q22);
                break;
            case "q23":
                q23 = level_question;

                r23 = 1;
                //For room database use only
//                level_question = q23;
                updateLevelEachQuestionStatus(q23);
                break;
            case "q24":
                q24 = level_question;

                r24 = 1;
                //For room database use only
//                level_question = q24;
                updateLevelEachQuestionStatus(q24);
                break;
            case "q25":
                q25 = level_question;

                r25 = 1;
                //For room database use only
//                level_question = q25;
                updateLevelEachQuestionStatus(q25);
                break;
            case "q26":
                q26 = level_question;

                r26 = 1;
                //For room database use only
//                level_question = q26;
                updateLevelEachQuestionStatus(q26);
                break;
            case "q27":
                q27 = level_question;

                r27 = 1;
                //For room database use only
//                level_question = q27;
                updateLevelEachQuestionStatus(q27);
                break;
            case "q28":
                q28 = level_question;

                r28 = 1;
                //For room database use only
//                level_question = q28;
                updateLevelEachQuestionStatus(q28);
                break;
            case "q29":
                q29 = level_question;

                r29 = 1;
                //For room database use only
//                level_question = q29;
                updateLevelEachQuestionStatus(q29);
                break;
            case "q30":
                q30 = level_question;

                r30 = 1;
                //For room database use only
//                level_question = q30;
                updateLevelEachQuestionStatus(q30);
                break;
            case "q31":
                q31 = level_question;

                r31 = 1;
                //For room database use only
//                level_question = q31;
                updateLevelEachQuestionStatus(q31);
                break;
            case "q32":
                q32 = level_question;

                r32 = 1;
                //For room database use only
//                level_question = q32;
                updateLevelEachQuestionStatus(q32);
                break;
            case "q33":
                q33 = level_question;

                r33 = 1;
                //For room database use only
//                level_question = q33;
                updateLevelEachQuestionStatus(q33);
                break;
            case "q34":
                q34 = level_question;

                r34 = 1;
                //For room database use only
//                level_question = q34;
                updateLevelEachQuestionStatus(q34);
                break;
            case "q35":
                q35 = level_question;

                r35 = 1;
                //For room database use only
//                level_question = q35;
                updateLevelEachQuestionStatus(q35);
                break;
            case "q36":
                q36 = level_question;

                r36 = 1;
                //For room database use only
//                level_question = q36;
                updateLevelEachQuestionStatus(q36);
                break;
            case "q37":
                q37 = level_question;

                r37 = 1;
                //For room database use only
//                level_question = q37;
                updateLevelEachQuestionStatus(q37);
                break;
            case "q38":
                q38 = level_question;

                r38 = 1;
                //For room database use only
//                level_question = q38;
                updateLevelEachQuestionStatus(q38);
                break;
            case "q39":
                q39 = level_question;

                r39 = 1;
                //For room database use only
//                level_question = q39;
                updateLevelEachQuestionStatus(q39);
                break;
            case "q40":
                q40 = level_question;

                r40 = 1;
                //For room database use only
//                level_question = q40;
                updateLevelEachQuestionStatus(q40);
                break;
            case "q41":
                q41 = eachQuestStatusINCREASE(q41);
                updateLevelEachQuestionStatus(q41);
                break;

        }
    }

    public void eachQuestStatus_DB() {
        updateLevelEachQuestionStatus(level_question);
    }

    //count  the number of primary learning and master questioin for the progress bar
    public void countPriLernMast() {

        final String customID = child_Name + "_" + mPost_key + "_" + "%";

        new Thread(new Runnable() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        int countPrimary = Memorize_database.getINSTANCE(getApplicationContext()).
                                memorize_dao().countPrimaryQuestion(customID);
                        int countLearning = Memorize_database.getINSTANCE(getApplicationContext()).
                                memorize_dao().countLearning(customID);
                        int countMaster = Memorize_database.getINSTANCE(getApplicationContext()).
                                memorize_dao().countMaster(customID);
//                        int countMaster = totalQ - countPrimary - countLearning;
                        progressPrimary.setMax(totalQ);
                        progressLearning.setMax(totalQ);
                        progressMaster.setMax(totalQ);
                        progressPrimary.setProgress(countPrimary);
                        progressLearning.setProgress(countLearning);
                        progressMaster.setProgress(countMaster);

                        String textPr = "Primary: " + String.valueOf(countPrimary) + " (out of " + String.valueOf(totalQ) + ")";
                        String textLr = "Learning: " + String.valueOf(countLearning) + " (out of " + String.valueOf(totalQ) + ")";
                        String textMs = "Master: " + String.valueOf(countMaster) + " (out of " + String.valueOf(totalQ) + ")";

                        mPrimary_Text.setText(textPr);
                        mLearning_Text.setText(textLr);
                        mMaster_Text.setText(textMs);

                    }
                });
            }
        }).start();
    }

    public void networkCheck() {
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            Snackbar.make(findViewById(R.id.mMemorise), "Internet is Available...",
                    Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(findViewById(R.id.mMemorise), "No Network Connection...",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    public void next_Q_or_not_KNOWN() {
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            mTxt_known.setVisibility(View.GONE);
            mTxt_unknown.setVisibility(View.GONE);
            answeredQn = mQuestNum - 1;

            level_cards = levelINCREASE(level_cards);
            updateLevelStatus(level_cards);
            stopRepeatMarkCountSUCCESS();

            addToDatabase();

            countPriLernMast();
            String defaultE = "e";
            String defaultEE = "ee";
            mTxt_E1.setText(defaultE);
            mTxt_E2.setText(defaultEE);

            mQuestNum++;
            if (mQuestNum > totalQ) {
                mQuestNum = 1;
                make_int_r_zero();
            }

            mTxt_Submit.setVisibility(View.VISIBLE);

        } else {
            Snackbar.make(findViewById(R.id.mMemorise), "No Network Connection...",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    public void next_Q_or_not_unknown() {
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            mTxt_known.setVisibility(View.GONE);
            mTxt_unknown.setVisibility(View.GONE);
            unAnsweredQN = mQuestNum - 1;

            stopRepeatMarkCountFAILURE();
            level_cards = levelDECREASE(level_cards);
            updateLevelStatus(level_cards);

            addToDatabase();

            countPriLernMast();
            String defaultE = "e";
            String defaultEE = "ee";
            mTxt_E1.setText(defaultE);
            mTxt_E2.setText(defaultEE);
            mQuestNum++;
//                        Toast.makeText(getApplicationContext(), String.valueOf(mQuestNum), Toast.LENGTH_LONG).show();
            if (mQuestNum > totalQ) {
                mQuestNum = 1;
                make_int_r_zero();
            }

        } else {
            Snackbar.make(findViewById(R.id.mMemorise), "No Network Connection...",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    public void next_Q_or_not_SUBMIT() {
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            setVisibilityForImage(m);
            mTxt_Submit.setVisibility(View.GONE);
            mTxt_known.setVisibility(View.VISIBLE);
            mTxt_unknown.setVisibility(View.VISIBLE);
            mL_explanation.setVisibility(View.VISIBLE);


            if (clicked) {

//                    mTxt_E1.setText(e1);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mTxt_E1.setText(Html.fromHtml(e1, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    mTxt_E1.setText(Html.fromHtml(e1));
                }
//                    mTxt_E2.setText(e2);
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mTxt_E2.setText(Html.fromHtml(e2, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    mTxt_E2.setText(Html.fromHtml(e2));
                }
            }

        } else {
            Snackbar.make(findViewById(R.id.mMemorise), "No Network Connection...",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    public void next_Q_or_not_KNOWN_offline() {
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            mTxt_known.setVisibility(View.GONE);
            mTxt_unknown.setVisibility(View.GONE);
            answeredQn = mQuestNum - 1;

            stopRepeatMarkCountSUCCESS_DB();

            level_cards = levelINCREASE(level_cards);

            updateLevelStatus(level_cards);

            eachQuestStatus_DB();

            addToDatabaseOFFLINE();

            String defaultE = "e";
            String defaultEE = "ee";
            mTxt_E1.setText(defaultE);
            mTxt_E2.setText(defaultEE);

            mQuestNum++;
            if (mQuestNum > totalQ) {
                mQuestNum = 1;
                totalPoint = 0;
                make_int_r_zero();
            }

            mTxt_Submit.setVisibility(View.VISIBLE);
            countPriLernMast();
            readFromDatabase();

        } else {
            Snackbar.make(findViewById(R.id.mMemorise), "No Network Connection...",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    public void next_Q_or_not_unknown_OFFLINE() {
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            mTxt_known.setVisibility(View.GONE);
            mTxt_unknown.setVisibility(View.GONE);
            unAnsweredQN = mQuestNum - 1;

            stopRepeatMarkCountFAILURE_DB();
            eachQuestStatus_DB();
            level_cards = levelDECREASE(level_cards);
            updateLevelStatus(level_cards);

            addToDatabaseOFFLINE();

            String defaultE = "e";
            String defaultEE = "ee";
            mTxt_E1.setText(defaultE);
            mTxt_E2.setText(defaultEE);
            mQuestNum++;
            if (mQuestNum > totalQ) {
                mQuestNum = 1;
                totalPoint = 0;
                make_int_r_zero();
            }
            countPriLernMast();
            readFromDatabase();

        } else {
            Snackbar.make(findViewById(R.id.mMemorise), "No Network Connection...",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    public void next_Q_or_not_SUBMIT_offline() {
        assert connectivityManager != null;
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {

            setVisibilityForImage(getM);
            mTxt_Submit.setVisibility(View.GONE);
            mTxt_known.setVisibility(View.VISIBLE);
            mTxt_unknown.setVisibility(View.VISIBLE);
            mL_explanation.setVisibility(View.VISIBLE);


            if (clicked) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mTxt_E1.setText(Html.fromHtml(getE1, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    mTxt_E1.setText(Html.fromHtml(getE1));
                }
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    mTxt_E2.setText(Html.fromHtml(getE2, Html.FROM_HTML_MODE_COMPACT));
                } else {
                    mTxt_E2.setText(Html.fromHtml(getE2));
                }
            }

            eachQuestStatus_DB();

        } else {
            Snackbar.make(findViewById(R.id.mMemorise), "No Network Connection...",
                    Snackbar.LENGTH_LONG).show();
        }
    }

    // a will recieve the image link and picasso will show the image in imageView
    public void setVisibilityForImage(String a) {

        if (a != null && a.equalsIgnoreCase("n")) {
            mLL_image_memorise.setVisibility(View.GONE);
        } else if (a == null) {
            mLL_image_memorise.setVisibility(View.GONE);
        } else {
            mLL_image_memorise.setVisibility(View.VISIBLE);

            Picasso.get().load(a)
                    .placeholder(R.drawable.ic_menu_camera)
                    .into(imageView_memorise);
        }

    }
}
