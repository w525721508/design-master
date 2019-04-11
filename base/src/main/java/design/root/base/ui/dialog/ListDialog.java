package design.root.base.ui.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.view.Gravity;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;

import java.util.List;

import design.root.base.R;
import design.root.base.base.BaseDialog;
import design.root.base.databinding.DialogListBinding;
import design.root.base.entity.ZuoXiEntity;


/**
 * 确认对话框
 */
public class ListDialog extends BaseDialog<DialogListBinding> {
    private View.OnClickListener noOnClickListener;
    private BaseQuickAdapter.OnItemClickListener listener;
    //提示内容
    private String content;
    //入参
    private List<ZuoXiEntity> list;
    DialogAdapter dialogAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.dialog_list;
    }

    @Override
    protected boolean isEasy() {
        return true;
    }

    @Override
    public void initView() {


        mViewBinding.btnNo.setOnClickListener(view -> {
            noOnClickListener.onClick(view);
            dismiss();
        });
//        mViewBinding.tvTitle.setText(content);
        dialogAdapter = new DialogAdapter(list);
        mViewBinding.rvList.setAdapter(dialogAdapter);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mViewBinding.rvList.setLayoutManager(linearLayoutManager);
        mViewBinding.rvList.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL));
        dialogAdapter.openLoadAnimation(BaseQuickAdapter.SCALEIN);
        dialogAdapter.isFirstOnly(false);
        mViewBinding.rvList.setAdapter(dialogAdapter);
        dialogAdapter.setOnItemClickListener(listener);
    }


    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getDialog().getWindow().setGravity(Gravity.CENTER);
        getDialog().getWindow().setBackgroundDrawableResource(R.color.all_transparent);
    }

    public void isClose(Boolean isClose) {
        this.setCancelable(isClose);
    }

    public ListDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public ListDialog setNowContent(String content) {
//        mViewBinding.tvTitle.setText(content);
        return this;
    }

    public ListDialog setOnclick(View.OnClickListener noOnClickListener, BaseQuickAdapter.OnItemClickListener listener) {
        this.noOnClickListener = noOnClickListener;
        this.listener = listener;
        return this;
    }

    public List<ZuoXiEntity> getList() {
        return list;
    }

    public void setList(List<ZuoXiEntity> list) {
        this.list = list;
    }

    public void show(AppCompatActivity activity) {
        super.show(activity);
    }

    public void dismiss() {
        super.dismiss();
    }
    /**
     * 调用方式
     */
    //new PromptDialog().setContent("确认删除该图书？")
    // .setOnclick(yes -> {
    //    确定按钮
    //    }, no -> {
    //    取消按钮
    //    }).show((BaseActivity) getActivity());
}
