package design.root.base.db;

import android.arch.persistence.room.Room;


import java.util.List;

import design.root.base.App;
import design.root.base.entity.UserEntity;

/**
 * Created by Administrator on 2018/1/26.
 */

public class DbHelper {
    public static DbHelper dbHelper;
    public static AppDatabase db;

    public static AppDatabase getAppDatabaseInstance() {
        if (db == null) {
            db = Room.databaseBuilder(App.instance, AppDatabase.class, "db")
                    .allowMainThreadQueries().build();
        }
        return db;
    }

    public static DbHelper getInstance() {
        if (dbHelper == null) {
            dbHelper = new DbHelper();
        }
        return dbHelper;
    }


    public void insertUserEntity(UserEntity userEntity) {
        db = getAppDatabaseInstance();
        db.beginTransaction();
        db.userEntityDao().insert(userEntity);
        db.setTransactionSuccessful();
        db.endTransaction();
    }

    public List<UserEntity> queryUserNameToList(UserEntity userEntity) {
        db = getAppDatabaseInstance();
        return db.userEntityDao().checkUserName(userEntity.getUsername());
    }

    public List<UserEntity> queryUserNamePwdToList(UserEntity userEntity) {
        db = getAppDatabaseInstance();
        return db.userEntityDao().checkUserNamePwd(userEntity.getUsername(), userEntity.getPassword
                ());
    }

    public boolean updateUserEntity(UserEntity userEntity) {
        db = getAppDatabaseInstance();
        return db.userEntityDao().update(userEntity) > 0;
    }
}
