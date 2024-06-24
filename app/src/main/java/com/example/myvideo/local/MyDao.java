package com.example.myvideo.local;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.myvideo.models.ModelAuthCache;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao

public interface MyDao {


    @Insert
    Completable insert (List<ModelAuthCache> list);

    @Query("select * from ModelAuthCache")
    Single <List<ModelAuthCache>> getall();


    @Delete
    Completable delete (ModelAuthCache m);

}
