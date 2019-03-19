package design.custom.ui.main.fragment;


import android.content.Intent;
import android.view.View;

import design.custom.R;
import design.custom.databinding.FragmentMyBinding;
import design.custom.ui.main.MainPresenter;
import design.root.base.base.BaseFragment;
import design.root.base.util.Global;
import design.root.base.util.OnClickListener;

/**
 */

public class MyFragment extends BaseFragment<MainPresenter, FragmentMyBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my;
    }

    @Override
    public void initView() {
        mViewBinding.ic.tvTitle.setText(R.string.menu_myself);
        mViewBinding.tvMyMobile.setText(Global.userEntity.getMobile());
        mViewBinding.tvMySex.setText(Global.userEntity.getSex());
        mViewBinding.tvMyUserName.setText(Global.userEntity.getUsername());
        mViewBinding.setOnClickListener(new OnClickListener() {
            @Override
            protected void myOnClickListener(View v) {
                switch (v.getId()) {
                    case R.id.btn_close: {
                        //修改密码
                        getActivity().finish();
                    }
                    break;
                    case R.id.btnMyLogout: {
                        //退出
                        getActivity().finish();
                        getActivity().startActivity(new Intent(getContext(), design.root.base.ui.sign.LoginActivity.class));
                    }
                    break;
                }
            }
        });
    }
}
