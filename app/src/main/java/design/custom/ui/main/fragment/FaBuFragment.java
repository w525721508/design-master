package design.custom.ui.main.fragment;


import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.blankj.utilcode.util.ActivityUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.ArrayList;

import design.custom.R;
import design.custom.databinding.FragmentFabuBinding;
import design.custom.ui.main.AddActivity;
import design.custom.ui.main.MainPresenter;
import design.custom.ui.main.adapter.AdminItemAdapter;
import design.root.base.base.BaseFragment;
import design.root.base.entity.ItemContent;
import design.root.base.ui.dialog.PromptDialog;
import design.root.base.util.OnClickListener;



/**
 */

public class FaBuFragment extends BaseFragment<MainPresenter, FragmentFabuBinding> {
    AdminItemAdapter indexFragmentAdapter;

    @Override

    protected int getLayoutId() {
        return R.layout.fragment_fabu;
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void initView() {
        mViewBinding.ic.tvTitle.setText(R.string.menu_fabu);

        indexFragmentAdapter = new AdminItemAdapter(new ArrayList<>(), getActivity());
        mViewBinding.rlTest.setAdapter(indexFragmentAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mViewBinding.rlTest.setLayoutManager(linearLayoutManager);
        mViewBinding.rlTest.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        indexFragmentAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        indexFragmentAdapter.isFirstOnly(false);
        mViewBinding.rlTest.setAdapter(indexFragmentAdapter);
        indexFragmentAdapter.setOnItemLongClickListener(new BaseQuickAdapter.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(BaseQuickAdapter adapter, View view, int position) {
                ItemContent wuXianContent = (ItemContent) adapter.getData().get(position);

                PromptDialog promptDialog = new PromptDialog();
                promptDialog.setContent("确定删除？");
                promptDialog.setOnclick(new OnClickListener() {
                    @Override
                    protected void myOnClickListener(View v) {
                        //删除操作
                        promptDialog.dismiss();

                    }
                }, new OnClickListener() {
                    @Override
                    protected void myOnClickListener(View v) {
                        promptDialog.dismiss();
                    }
                });
                promptDialog.show((AppCompatActivity) getActivity());

                return false;
            }
        });

        request();
        mViewBinding.setOnClickListener(new OnClickListener() {
            @Override
            protected void myOnClickListener(View v) {
                switch (v.getId()) {
                    case R.id.ll_seach: {
                    }
                    break;
                    default: {
                        ActivityUtils.startActivity(AddActivity.class);
                    }
                    break;
                }

            }
        });
    }


    private void request() {
    }

    @Override
    public void onResume() {
        super.onResume();
        request();
    }
}
