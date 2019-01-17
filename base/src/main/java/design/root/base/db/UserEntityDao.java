package design.root.base.db;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import design.root.base.entity.UserEntity;

/**
 * Created by Administrator on 2018/1/25.
 */
@Dao
public interface UserEntityDao {
    @Query("SELECT * FROM users WHERE username IN(:username)")
    List<UserEntity> checkUserName(String username);

    @Query("SELECT * FROM users WHERE username IN(:username) And password IN(:password)")
    List<UserEntity> checkUserNamePwd(String username, String password);

    @Insert
    void insert(UserEntity userEntity);

    @Update
    int update(UserEntity userEntity);
}
