package design.root.base.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;


/**
 * Created by Administrator on 2018/1/25.
 */
@Database(entities = {design.root.base.entity.UserEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract UserEntityDao userEntityDao();

//    public abstract UserDao userDao();
}
