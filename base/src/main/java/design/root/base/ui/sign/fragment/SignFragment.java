package design.root.base.ui.sign.fragment;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.EditText;


import design.root.base.R;
import design.root.base.base.BaseFragment;
import design.root.base.databinding.FragmentSignBinding;
import design.root.base.ui.sign.LoginPresenter;


/**
 * Created by Administrator on 2018/1/24.
 */

public class SignFragment extends BaseFragment<LoginPresenter, FragmentSignBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_sign;
    }

    @Override
    public void initView() {
        mViewBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.btn_login) {
                    mPresenter.sign(mViewBinding.etPhone.getText().toString(), mViewBinding
                            .etPwd.getText().toString());

                } else if (i == R.id.tv_signUp) {
                    start(mPresenter.registeredFragment);

                } else if (i == R.id.tv_forgotPassword) {
                    start(mPresenter.inputPasswordFragment);
                }
            }
        });
        mViewBinding.etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                switchIcon(mViewBinding.etPhone, b, R.mipmap.login_username2, R.mipmap
                        .login_username1);
            }
        });
        mViewBinding.etPwd.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                switchIcon(mViewBinding.etPwd, b, R.mipmap.login_pwd02, R.mipmap.login_pwd01);
            }
        });


    }


    private void switchIcon(EditText view, Boolean b, int iconIdOne, int iconTwo) {
        Resources res = getResources();
        Drawable img_off;
        if (b) {
            img_off = res.getDrawable(iconIdOne);
        } else {
            img_off = res.getDrawable(iconTwo);
        }
        img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
        view.setCompoundDrawables(img_off, null, null, null);
    }

}
