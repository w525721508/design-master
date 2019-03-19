package design.custom.ui.main.fragment;


import design.custom.R;
import design.root.base.base.BaseFragment;
import design.custom.ui.main.MainPresenter;
import design.custom.databinding.FragmentOrderBinding;

/**
 */

public class OrderFragment extends BaseFragment<MainPresenter, FragmentOrderBinding> {
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    public void initView() {
        mViewBinding.ic.tvTitle.setText(R.string.menu_order);
    }
}
