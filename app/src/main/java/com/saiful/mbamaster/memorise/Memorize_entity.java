package com.saiful.mbamaster.memorise;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Memorize")
public class Memorize_entity {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "ID")
    private String id;

    @ColumnInfo (name = "Question")
    private String q;

    @ColumnInfo (name = "e1")
    private String e;

    @ColumnInfo (name = "e2")
    private String ee;

    @ColumnInfo (name = "image")
    private String m;

    @ColumnInfo (name = "Total_No_Question")
    private int total_N_Q;

    @ColumnInfo (name = "Total_No_Explanation")
    private int total_E;

    @ColumnInfo (name = "Level_of_Cards")
    private int level_cards;

    @ColumnInfo (name = "Level_of_question")
    private int level_question;



    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getQ() {
        return q;
    }

    public void setQ(String q) {
        this.q = q;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getEe() {
        return ee;
    }

    public void setEe(String ee) {
        this.ee = ee;
    }

    public void setM(String m) {
        this.m = m;
    }

    public String getM() {
        return m;
    }

    public int getTotal_N_Q() {
        return total_N_Q;
    }

    public void setTotal_N_Q(int total_N_Q) {
        this.total_N_Q = total_N_Q;
    }

    public int getTotal_E() {
        return total_E;
    }

    public void setTotal_E(int total_E) {
        this.total_E = total_E;
    }

    public int getLevel_cards() {
        return level_cards;
    }

    public void setLevel_cards(int level_cards) {
        this.level_cards = level_cards;
    }

    public int getLevel_question() {
        return level_question;
    }

    public void setLevel_question(int level_question) {
        this.level_question = level_question;
    }
}
