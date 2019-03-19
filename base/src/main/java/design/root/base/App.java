package design.root.base;

import android.app.Application;

import com.blankj.utilcode.util.Utils;

/**
 */

public class App extends Application {
    public String APPID = "test2";
    public static App instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        //初始化工具类
        Utils.init(this);



        //测试Bugly，请勿打开此注释
//        CrashReport.testJavaCrash();
    }
}
