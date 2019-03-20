package design.root.base.ui.sign;


import design.root.base.entity.UserEntity;
import design.root.base.ui.interfaces.NetCallBack;
import design.root.base.ui.sign.LoginContract;
import design.root.base.ui.sign.fragment.InputPasswordFragment;
import design.root.base.ui.sign.fragment.RegisteredFragment;
import design.root.base.ui.sign.fragment.SignFragment;
import design.root.base.util.Global;

/**
 *
 */

public class LoginPresenter extends LoginContract.Presenter {
    public SignFragment signFragment = new SignFragment();
    public RegisteredFragment registeredFragment = new RegisteredFragment();
    public InputPasswordFragment inputPasswordFragment = new InputPasswordFragment();


    @Override
    public void init() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void register(UserEntity userEntity) {
        if (mModel.isNull(userEntity.getUsername(), userEntity.getPassword())) {
            mView.showToast("注册数据不能为空");
        } else {
            mModel.register(userEntity, new NetCallBack<UserEntity>() {
                @Override
                public void succ(UserEntity userEntity) {
                    mView.registerSucc("注册成功");
                }

                @Override
                public void error(String str) {
                    mView.error(str);
                }
            });


        }
    }

    @Override
    public void sign(String userName, String pwd) {
        mModel.sign(userName, pwd, new NetCallBack<UserEntity>() {
            @Override
            public void succ(UserEntity userEntity) {
                Global.userEntity = userEntity;
                mView.signSucc();
            }

            @Override
            public void error(String str) {
                mView.error("账户密码错误");
            }
        });
    }

    @Override
    public void changePwd(String username, String PwdOne, String PwdTwo) {
        if (mModel.isNull(username, PwdOne, PwdTwo)) {
            mView.showToast("修改密码数据不能为空");
        } else {
            mModel.changePwd(username, PwdOne, PwdTwo, new NetCallBack() {
                @Override
                public void succ(Object o) {
                    mView.chagePwdSucc();
                }

                @Override
                public void error(String str) {
                    mView.error(str);
                }
            });
        }
    }

    @Override
    public void showStr(String str) {
        mView.changeTitle(str);
    }
}
