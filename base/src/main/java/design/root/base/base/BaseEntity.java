package design.root.base.base;

import design.root.base.App;

/**
 * Created by Administrator on 2018/1/31.
 */

public class BaseEntity extends SuperBean {
    private String appid = App.instance.APPID;

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }
}

