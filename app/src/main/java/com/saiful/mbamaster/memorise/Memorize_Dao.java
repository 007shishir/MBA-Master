package com.saiful.mbamaster.memorise;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface Memorize_Dao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addMemorizeQ (Memorize_entity question);

    @Query("SELECT ID, Question, e1, e2, image, Level_of_Cards, Total_No_Question, Total_No_Explanation, Level_of_question FROM Memorize WHERE ID= :id LIMIT 1")
    LiveData<List<Memorize_entity>> select_liveData(String id);

    @Query("SELECT ID, Question, e1, e2, image, Level_of_Cards, Level_of_question, Total_No_Question, Total_No_Explanation FROM Memorize WHERE ID=:id LIMIT 1")
    List<Memorize_entity> select_question (String id);

    @Query("SELECT COUNT(Level_of_question) FROM Memorize WHERE Level_of_question<2 AND ID LIKE :id")
    int countPrimaryQuestion (String id);

    @Query("SELECT COUNT(Level_of_question) FROM Memorize WHERE Level_of_question IN(2,3) AND ID LIKE :id")
    int countLearning (String id);

    @Query("SELECT COUNT(Level_of_question) FROM Memorize WHERE Level_of_question IN(4,6) AND ID LIKE :id")
    int countMaster (String id);
}
