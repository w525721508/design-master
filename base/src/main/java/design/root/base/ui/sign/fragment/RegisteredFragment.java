package design.root.base.ui.sign.fragment;

import android.view.View;

import design.root.base.R;
import design.root.base.base.BaseFragment;
import design.root.base.databinding.FragmentRegisteredBinding;
import design.root.base.entity.UserEntity;
import design.root.base.ui.sign.LoginPresenter;


/**
 *
 */

public class RegisteredFragment extends BaseFragment<LoginPresenter, FragmentRegisteredBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_registered;
    }

    @Override
    public void initView() {
        mPresenter.showStr("注册");
        mViewBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.btn_register) {
                    UserEntity userEntity = new UserEntity();
                    userEntity.setUsername(mViewBinding.etPhone.getText().toString());
                    userEntity.setPassword(mViewBinding.etPwdOne.getText().toString());
                    mPresenter.register(userEntity);

                } else {
                    mPresenter.showStr("登录");
                    pop();
                }
            }
        });

    }
}
