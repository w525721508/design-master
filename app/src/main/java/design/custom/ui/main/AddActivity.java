package design.custom.ui.main;

import design.custom.R;
import design.custom.databinding.ActivityAddBinding;
import design.root.base.base.BaseActivity;

public class AddActivity extends BaseActivity<MainPresenter, MainModel, ActivityAddBinding> {

    @Override
    public int getLayoutId() {
        return R.layout.activity_add;
    }


    @Override
    public void initView() {
        initData();
        initClick();
    }

    private void initClick() {
        mViewBinding.setOnClick(v -> {
            switch (v.getId()) {
                case R.id.btn_ok: {
                }
                break;
                default: {

                }
                break;
            }
        });
    }


    private void initData() {
        mViewBinding.ic.tvTitle.setText("新增");
    }
}
