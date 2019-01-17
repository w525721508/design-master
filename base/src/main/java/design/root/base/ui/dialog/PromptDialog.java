package design.root.base.ui.dialog;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;

import design.root.base.R;
import design.root.base.base.BaseDialog;
import design.root.base.databinding.DialogPromptBinding;


/**
 * 确认对话框
 */
public class PromptDialog extends BaseDialog<DialogPromptBinding> {
    private View.OnClickListener noOnClickListener;
    private View.OnClickListener yesOnClickListener;
    //提示内容
    private String content;


    @Override
    public int getLayoutId() {
        return R.layout.dialog_prompt;
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
        mViewBinding.btnYes.setOnClickListener(view -> {
            yesOnClickListener.onClick(view);
            dismiss();
        });
        mViewBinding.tvTitle.setText(content);
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

    public PromptDialog setContent(String content) {
        this.content = content;
        return this;
    }

    public PromptDialog setNowContent(String content) {
        mViewBinding.tvTitle.setText(content);
        return this;
    }

    public PromptDialog setOnclick(View.OnClickListener yesOnClickListener, View.OnClickListener noOnClickListener) {
        this.yesOnClickListener = yesOnClickListener;
        this.noOnClickListener = noOnClickListener;
        return this;
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
