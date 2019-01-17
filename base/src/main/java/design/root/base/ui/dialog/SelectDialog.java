package design.root.base.ui.dialog;

import android.view.View;

import design.root.base.R;
import design.root.base.base.BaseDialog;
import design.root.base.base.BasePresenter;
import design.root.base.databinding.DialogSelectBinding;


/**
 * Created by Administrator on 2018/1/24.
 */

public class SelectDialog extends BaseDialog<DialogSelectBinding> {
    String strContent;
    private View.OnClickListener noOnClickListener;
    private View.OnClickListener yesOnClickListener;

    @Override
    public int getLayoutId() {
        return R.layout.dialog_select;
    }

    @Override
    public int getTitle() {
        return super.getTitle();
    }

    @Override
    public void onCancelClick(View view) {
        super.onCancelClick(view);
    }

    @Override
    public void onOkClick(View view) {
        this.dismiss();
        super.onOkClick(view);
    }

    public void setContent(String strContent) {
        this.strContent = strContent;
    }

    @Override
    public void initView() {
        mViewBinding.tvContent.setText(strContent);
    }

}
