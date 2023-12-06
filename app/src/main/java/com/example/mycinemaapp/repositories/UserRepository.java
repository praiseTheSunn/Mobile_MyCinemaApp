package com.example.mycinemaapp.repositories;

import android.content.Context;
import android.os.AsyncTask;

import com.example.mycinemaapp.daos.MovieDao;
import com.example.mycinemaapp.daos.UserDao;
import com.example.mycinemaapp.databases.UserDatabase;
import com.example.mycinemaapp.models.MovieEntity;
import com.example.mycinemaapp.models.UserEntity;

import java.util.List;

public class UserRepository {
    private UserDatabase userDatabase;

    public UserRepository(Context context) {
        userDatabase = UserDatabase.getInstance(context);
    }

    public void insertUser(UserEntity userEntity) {
        new UserRepository.InsertUserAsyncTask(userDatabase.UserDao()).execute(userEntity);
    }

    public String getPasswordByEmail(String email) {
        return userDatabase.UserDao().getPasswordByEmail(email).blockingFirst();
    }

    public List<UserEntity> getAllUsers() {
        return userDatabase.UserDao().getAllUsers().blockingFirst();
    }

    public boolean checkEmailExists(String email) {
        return userDatabase.UserDao().checkEmailExists(email).blockingFirst();
    }

    // AsyncTask to insert a movie in the background
    private static class InsertUserAsyncTask extends AsyncTask<UserEntity, Void, Void> {
        private UserDao userDao;

        private InsertUserAsyncTask(UserDao userDao) {
            this.userDao = userDao;
        }

        @Override
        protected Void doInBackground(UserEntity... userEntities) {
            userDao.insert(userEntities[0]);
            return null;
        }
    }
}
