package com.example.mycinemaapp.daos;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.mycinemaapp.models.UserEntity;

import java.util.List;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;

@Dao
public interface UserDao {
    @Insert(entity = UserEntity.class)
    public void insert(UserEntity userEntity);

    @Query("SELECT * FROM users")
    Flowable<List<UserEntity>> getAllUsers();

    @Query("SELECT password FROM users WHERE email = :email")
    Flowable<String> getPasswordByEmail(String email);

    @Query("SELECT EXISTS (SELECT 1 FROM users WHERE email = :email LIMIT 1)")
    Flowable<Boolean> checkEmailExists(String email);
}
