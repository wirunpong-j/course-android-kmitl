package com.bellkung.moneyflow.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.bellkung.moneyflow.model.ListModel;

/**
 * Created by BellKunG on 8/11/2017 AD.
 */

@Database(entities = {ListModel.class}, version = 1)
public abstract class ListDatabase extends RoomDatabase {
    public abstract ListDAO listRoomDAO();
}
