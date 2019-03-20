package design.root.base.ui.sign;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.sms.SmsController;

import design.root.base.App;
import design.root.base.R;
import design.root.base.base.BaseActivity;
import design.root.base.databinding.ActivityLoginBinding;
import design.root.base.util.Global;


public class LoginActivity extends BaseActivity<LoginPresenter, LoginModel, ActivityLoginBinding>
        implements LoginContract.View {

    private Class startClass;

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    public void initView() {
        loadRootFragment(R.id.fl, mPresenter.signFragment);
//        SmsController.getInstance(LoginActivity.this, App.instance.APPID).startAll();
    }


    @Override
    public void showToast(String toastMsg) {
//        SelectDialog selectDialog = new SelectDialog();
//        selectDialog.setContent("确定登录");
//        selectDialog.show(LoginActivity.this);
        ToastUtils.showLong(toastMsg);
    }

    @Override
    public void registerSucc(String toastMsg) {
        closeLoading();
        ToastUtils.showLong(toastMsg);
        mPresenter.showStr("登录");
        pop();
    }

    @Override
    public void signSucc() {
        closeLoading();
        Class startActivity = null;
        try {
            if (Global.userEntity.getType() == 2) {
                startActivity = Class.forName("design.custom.ui.main.AdminActivity");
            } else {
                startActivity = Class.forName("design.custom.ui.main.MainActivity");
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        if (startActivity != null) {
            ActivityUtils.startActivity(startActivity);
        }
        ActivityUtils.finishActivity(LoginActivity.class);
    }

    @Override
    public void chagePwdSucc() {
        closeLoading();
        ToastUtils.showLong("修改成功");
        mPresenter.showStr("登录");
        pop();
    }

    @Override
    public void changeTitle(String str) {
        mViewBinding.tvTitle.setText(str);
    }


    @Override
    public void error(String errorMsg) {
        closeLoading();
        ToastUtils.showLong(errorMsg);
    }
}

