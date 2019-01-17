package design.root.base.base;

import android.text.TextUtils;

/**
 * Created by Administrator on 2018/1/22.
 */

public class BaseModel {
    public Boolean isNull(String... str) {
        for (String s : str) {
            if (isNull(s)) {
                return true;
            }
        }
        return false;
    }

    private boolean isNull(String str) {
        if (TextUtils.isEmpty(str)) {
            return true;
        } else {
            return false;
        }
    }
}
