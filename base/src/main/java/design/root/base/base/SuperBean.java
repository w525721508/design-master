package design.root.base.base;


import android.arch.persistence.room.Ignore;

import java.lang.reflect.Field;
import java.util.HashMap;


/**
 * @author fish.leong@msn.com
 */
public class SuperBean {
    private static final int ACTION_ADD = 0;
    private static final int ACTION_UPDATE = 1;
    private static final int ACTION_DELETE = 2;
    private static final int ACTION_VERIFY = 3;
    @Ignore
    private String tableName;
    @Ignore
    private int action = ACTION_ADD;
    @Ignore
    private HashMap<String, Object> pks;
    @Ignore
    private HashMap<String, Object> cols;

    public boolean toAddData() {
        try {
            action = ACTION_ADD;
            Class<?> table = Class.forName((getClass().getName() + "$table"));// 表
            Class<?> primaryKeys = Class.forName((getClass().getName() + "$primarykeys"));// 主键
            Class<?> columns = Class.forName((getClass().getName() + "$columns"));// 列
            tableName = table.getEnumConstants()[0].toString();
            pks = new HashMap<>();
            cols = new HashMap<>();
            Field tmp;
            for (Object pk : primaryKeys.getEnumConstants()) {
                try {
                    tmp = getClass().getDeclaredField(pk.toString());
                } catch (Exception e) {
                    tmp = getClass().getSuperclass().getDeclaredField(pk.toString());
                }
                tmp.setAccessible(true);
                pks.put(pk.toString(), tmp.get(this));
            }
            for (Object col : columns.getEnumConstants()) {
                try {
                    tmp = getClass().getDeclaredField(col.toString());
                } catch (Exception e) {
                    tmp = getClass().getSuperclass().getDeclaredField(col.toString());
                }
                tmp.setAccessible(true);
                cols.put(col.toString(), tmp.get(this));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean toDeleteData() {
        try {
            action = ACTION_DELETE;
            Class<?> table = Class.forName((getClass().getName() + "$table"));// 表
            Class<?> primaryKeys = Class.forName((getClass().getName() + "$primarykeys"));// 主键
            tableName = table.getEnumConstants()[0].toString();
            pks = new HashMap<>();
            this.cols = new HashMap<>();
            Field tmp;
            for (Object pk : primaryKeys.getEnumConstants()) {
                try {
                    tmp = getClass().getDeclaredField(pk.toString());
                } catch (Exception e) {
                    tmp = getClass().getSuperclass().getDeclaredField(pk.toString());
                }
                tmp.setAccessible(true);
                pks.put(pk.toString(), tmp.get(this));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean toUpdateData() {
        try {
            action = ACTION_UPDATE;
            Class<?> table = Class.forName((getClass().getName() + "$table"));// 表
            Class<?> primaryKeys = Class.forName((getClass().getName() + "$primarykeys"));// 主键
            Class<?> columns = Class.forName((getClass().getName() + "$columns"));// 列
            tableName = table.getEnumConstants()[0].toString();
            pks = new HashMap<>();
            cols = new HashMap<>();
            Field tmp;
            for (Object pk : primaryKeys.getEnumConstants()) {
                try {
                    tmp = getClass().getDeclaredField(pk.toString());
                } catch (Exception e) {
                    tmp = getClass().getSuperclass().getDeclaredField(pk.toString());
                }
                tmp.setAccessible(true);
                pks.put(pk.toString(), tmp.get(this));
            }
            for (Object col : columns.getEnumConstants()) {
                try {
                    tmp = getClass().getDeclaredField(col.toString());
                } catch (Exception e) {
                    tmp = getClass().getSuperclass().getDeclaredField(col.toString());
                }
                tmp.setAccessible(true);
                cols.put(col.toString(), tmp.get(this));
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean toVerifyData(Enum<?>... cols) {
        try {
            action = ACTION_VERIFY;
            Class<?> table = ClassLoader.getSystemClassLoader().loadClass((getClass().getName() + "$table"));// 表
            tableName = table.getEnumConstants()[0].toString();
            pks = new HashMap<>();
            this.cols = new HashMap<>();
            Field tmp;
            for (Enum<?> col : cols) {
                try {
                    tmp = getClass().getDeclaredField(col.toString());
                } catch (Exception e) {
                    tmp = getClass().getSuperclass().getDeclaredField(col.toString());
                }
                tmp.setAccessible(true);
                pks.put(col.toString(), tmp.get(this));
            }
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public static int getActionAdd() {
        return ACTION_ADD;
    }

    public static int getActionUpdate() {
        return ACTION_UPDATE;
    }

    public static int getActionDelete() {
        return ACTION_DELETE;
    }

    public String getTableName() {
        return tableName;
    }

    public int getAction() {
        return action;
    }

    public HashMap<String, Object> getPks() {
        return pks;
    }

    public HashMap<String, Object> getCols() {
        return cols;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void setPks(HashMap<String, Object> pks) {
        this.pks = pks;
    }

    public void setCols(HashMap<String, Object> cols) {
        this.cols = cols;
    }
}
