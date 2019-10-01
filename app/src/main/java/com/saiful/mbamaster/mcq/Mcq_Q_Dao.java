package com.saiful.mbamaster.mcq;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Mcq_Q_Dao
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMcq_q (Mcq_Q_entity question);

    //@Query("SELECT * FROM mcq_question")
    //List<Mcq_Q_entity> getMcq_q ();

    @Query("SELECT ID, question, option_one, option_two, option_three, option_four, answer, explanation, total_number_of_question, level_of_cards, level_of_question FROM mcq_question WHERE ID = :id LIMIT 1")
    LiveData<List<Mcq_Q_entity>> select_row (String id);

    @Query("SELECT ID, question, option_one, option_two, option_three, option_four, answer, explanation, total_number_of_question, level_of_cards, level_of_question FROM mcq_question WHERE ID = :id")
    List<Mcq_Q_entity> find_quest_option (String id);

    @Query("SELECT COUNT(level_of_question) FROM mcq_question WHERE level_of_question<2 AND ID LIKE :id")
    int countPrimaryQuestion (String id);

    @Query("SELECT COUNT(level_of_question) FROM mcq_question WHERE level_of_question IN(2,3) AND ID LIKE :id")
    int countLearning (String id);

    @Query("SELECT COUNT(level_of_question) FROM mcq_question WHERE level_of_question IN(4,5) AND ID LIKE :id")
    int countMaster (String id);
}
