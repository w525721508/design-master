package design.root.base.ui.sign;


import design.root.base.base.BaseModel;
import design.root.base.base.BasePresenter;
import design.root.base.base.BaseView;
import design.root.base.ui.interfaces.NetCallBack;

/**
 * Created by Administrator on 2018/1/22.
 */

public interface LoginContract {
    interface View extends BaseView {
        void showToast(String toastMsg);

        //注册成功
        void registerSucc(String toastMsg);

        //登录成功
        void signSucc();

        //修改密码成功
        void chagePwdSucc();
    }

    abstract class Model extends BaseModel {
        //注册数据操作
        public abstract void register(String userName, String PwdOne, NetCallBack netCallBack);

        //登录验证操作
        public abstract void sign(String userName, String Pwd, NetCallBack netCallBack);

        //修改密码
        public abstract void changePwd(String username, String PwdOne, String PwdTwo, NetCallBack
                netCallBack);
    }

    abstract class Presenter extends BasePresenter<View, Model> {
        //注册
        public abstract void register(String userName, String PwdOne, String pwdTwo);

        //登录
        public abstract void sign(String userName, String Pwd);

        //修改密码
        public abstract void changePwd(String username, String PwdOne, String PwdTwo);
    }


}
