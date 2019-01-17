package design.root.base;

/**
 * Created by Administrator on 2018/1/31.
 */

public class Constant {
    //API 网址
//    public static final String API_HOST = "https://api.readhub.me";
    public static final String API_HOST = "http://123.206.47.186/";

    public final class API_BUILDER {
        public static final int WRITE_TIMEOUT = 2;
        public static final int READ_TIMEOUT = 2;
        public static final int CONNECT_TIMEOUT = 2;
        public static final int RETRY_DELAY_TIME = 3000;

    }

    public final class RETURN_CODE {
        public static final String SERVER_OK = "0";//成功
        public static final String NO_LOGIN = "A1";//没有登录
        public static final String SERVER_ERR = "500";//服务器异常
    }

    public final class RETURN_INFO {
        public static final String NO_LOGIN = "未登录";//没有登录
        public static final String SERVER_ERR = "服务器异常";//服务器异常
    }

    public final class SYSTEM {
        public static final String DIALOGISDISMISS = "DIALOGISDISMISS";
        //本地服务器切换
        public static final boolean NEEDSERVER = true;
    }
}
