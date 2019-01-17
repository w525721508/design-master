package design.root.base.ui.sign.fragment;

import android.view.View;

import design.root.base.R;
import design.root.base.databinding.FragmentRegisteredBinding;

import design.root.base.base.BaseFragment;
import design.root.base.ui.sign.LoginPresenter;


/**
 * Created by Administrator on 2018/1/25.
 */

public class RegisteredFragment extends BaseFragment<LoginPresenter,
        FragmentRegisteredBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_registered;
    }

    @Override
    public void initView() {
        mViewBinding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int i = v.getId();
                if (i == R.id.btn_register) {
                    mPresenter.register(mViewBinding.etPhone.getText().toString(),
                            mViewBinding.etPwdOne.getText().toString(), mViewBinding.etPwdTwo
                                    .getText().toString());

                } else {

                }
            }
        });

    }
}
