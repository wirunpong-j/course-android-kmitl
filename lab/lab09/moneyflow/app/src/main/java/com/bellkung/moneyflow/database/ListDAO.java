package com.bellkung.moneyflow.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.bellkung.moneyflow.model.ListModel;
import com.bellkung.moneyflow.utils.KeyUtils;

import java.util.List;

/**
 * Created by BellKunG on 8/11/2017 AD.
 */

@Dao
public interface ListDAO {

    @Query("SELECT * FROM ListModel ORDER BY id DESC")
    List<ListModel> getAll();

    @Insert
    void insert(ListModel list);

    @Query("SELECT SUM(amount) FROM ListModel WHERE symbol='" + KeyUtils.ACTION_INCOME + "'")
    double getIncome();

    @Query("SELECT SUM(amount) FROM ListModel WHERE symbol='" + KeyUtils.ACTION_EXPENSE + "'")
    double getExpense();

    @Update
    void update(ListModel list);

    @Delete
    void delete(ListModel list);

}
