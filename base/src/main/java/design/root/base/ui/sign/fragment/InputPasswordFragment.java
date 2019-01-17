package design.root.base.ui.sign.fragment;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.text.InputFilter;
import android.text.InputType;
import android.view.View;
import android.widget.EditText;

import design.root.base.R;
import design.root.base.databinding.FragmentInputPasswordBinding;
import design.root.base.base.BaseFragment;
import design.root.base.ui.sign.LoginPresenter;

/**
 */
public class InputPasswordFragment extends BaseFragment<LoginPresenter,
        FragmentInputPasswordBinding> {
    private String onePwd;
    private String twoPwd;
    //找回密码发验证码为1，注册发验证码为2
    int startway = -1;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_input_password;
    }


    @Override
    public void initView() {
        InputFilter[] inputFilter = new InputFilter[]{new InputFilter.LengthFilter(6)};
        mViewBinding.etOnePassword.setInputType(InputType.TYPE_CLASS_NUMBER | InputType
                .TYPE_NUMBER_VARIATION_PASSWORD);
        mViewBinding.etOnePassword.setFilters(inputFilter);
        mViewBinding.etTwoPassword.setInputType(InputType.TYPE_CLASS_NUMBER | InputType
                .TYPE_NUMBER_VARIATION_PASSWORD);
        mViewBinding.etTwoPassword.setFilters(inputFilter);
        mViewBinding.setOnClickListener(view -> {
            int i = view.getId();
            if (i == R.id.btn_next) {
                mPresenter.changePwd(mViewBinding.etPhone.getText().toString(), mViewBinding
                        .etOnePassword.getText().toString(), mViewBinding.etTwoPassword
                        .getText().toString());

            } else if (i == R.id.tv_existing_account) {
                pop();

            }
        });
        mViewBinding.etPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                switchIcon(mViewBinding.etPhone, b, R.mipmap.login_username2, R.mipmap
                        .login_username1);
            }
        });
        mViewBinding.etOnePassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                switchIcon(mViewBinding.etOnePassword, b, R.mipmap.login_pwd02, R.mipmap
                        .login_pwd01);
            }
        });
        mViewBinding.etTwoPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean b) {
                switchIcon(mViewBinding.etTwoPassword, b, R.mipmap.pwd_two02, R.mipmap
                        .pwd_two01);
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
