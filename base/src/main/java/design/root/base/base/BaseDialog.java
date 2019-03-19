package design.root.base.base;

import android.content.Context;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.app.AppCompatDialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.blankj.utilcode.util.ActivityUtils;
import com.blankj.utilcode.util.AppUtils;
import com.blankj.utilcode.util.KeyboardUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ScreenUtils;
import com.blankj.utilcode.util.ToastUtils;

import java.util.concurrent.TimeUnit;

import design.root.base.Constant;
import design.root.base.R;

import design.root.base.databinding.DialogBaseBinding;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 *
 */

public abstract class BaseDialog<B extends ViewDataBinding> extends AppCompatDialogFragment {
    private DialogBaseBinding mBaseBinding;
    public B mViewBinding;
    public Context mContext;
    public boolean isShow;
    private Disposable mDisp;
    private long recLen = -1;
    private boolean shutDown = false;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    public abstract int getLayoutId();

    public BaseDialog() {
        this.mContext = getActivity();
    }

    public int getTitle() {
        return R.string.all_dialog;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable
            Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        mContext = ActivityUtils.getTopActivity();
        if (!isEasy()) return createDialog(inflater, container);
        else return getEasyFragmentDialog(inflater, container);
    }

    private View createDialog(LayoutInflater inflater, @Nullable ViewGroup container) {
        mBaseBinding = DataBindingUtil.inflate(inflater, R.layout.dialog_base, container, false);
        mBaseBinding.vsCenter.getViewStub().setLayoutResource(getLayoutId());
        mBaseBinding.vsCenter.getViewStub().setOnInflateListener((viewStub, view) -> mViewBinding
                = DataBindingUtil.bind(view));
        mBaseBinding.vsCenter.getViewStub().inflate();
        if (getTitle() < 0) {
            mBaseBinding.tvTitle.setVisibility(View.GONE);
        } else {
            mBaseBinding.tvTitle.setText(getTitle());
        }
        mBaseBinding.btnCancel.setOnClickListener(this::onCancelClick);
        mBaseBinding.btnOk.setOnClickListener(this::onOkClick);
        initView();
        return mBaseBinding.getRoot();
    }

    /**
     * 获取一个简单的Dialog
     *
     * @param inflater  inflater
     * @param container container
     * @return dialog
     */
    private View getEasyFragmentDialog(@NonNull LayoutInflater inflater, ViewGroup container) {
        mViewBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        this.initView();
        return mViewBinding.getRoot();
    }

    /**
     * 取消键被点击
     */
    public void onCancelClick(View view) {
        KeyboardUtils.hideSoftInput(mViewBinding.getRoot());
        this.dismiss();
    }

    /**
     * 确定键被点击
     */
    public void onOkClick(View view) {
        KeyboardUtils.hideSoftInput(mViewBinding.getRoot());
    }

    /**
     * 是否为简略模式
     *
     * @return 是否为简略模式
     */
    protected boolean isEasy() {
        return false;
    }

    @Override
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
        dismiss();
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        WindowManager.LayoutParams attributes = getDialog().getWindow().getAttributes();
        attributes.width = ScreenUtils.getScreenWidth() * 95 / 100;
        attributes.height = -2;
        getDialog().getWindow().setAttributes(attributes);
    }

    /**
     * 初始化
     */
    public abstract void initView();

    /**
     * 显示
     *
     * @param activity context
     */
    public void show(AppCompatActivity activity) {
        if (AppUtils.isAppForeground()) {
            if (!isShow) {
                this.recLen = -1;
                this.shutDown = false;
                super.show(activity.getSupportFragmentManager(), this.getTag());
                isShow = true;
            }
        } else {
            LogUtils.e("当前app属于后台，不可打开Dialog");
        }
    }

    public void show(BaseActivity activity, int mRecLen) {
        if (AppUtils.isAppForeground()) {
            if (!isShow) {
                this.recLen = mRecLen;
                this.shutDown = true;
                super.show(activity.getSupportFragmentManager(), this.getTag());
                isShow = true;
                io.reactivex.Observable.interval(0, 1, TimeUnit.SECONDS).take(recLen + 1).map(new Function<Long, Long>() {

                    @Override
                    public Long apply(Long aLong) throws Exception {
                        return mRecLen - aLong;
                    }
                }).observeOn(AndroidSchedulers.mainThread()).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Exception {
                        mDisp = disposable;
                    }
                }).subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        mDisp = d;
                    }

                    @Override
                    public void onNext(Long aLong) {
                        recLen = aLong;
                        LogUtils.e("对话框将在" + aLong + "S后关闭");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        dismiss();
                    }
                });
            }
        } else {
            LogUtils.e("当前app属于后台，不可打开Dialog");
        }
    }


    public void dismiss() {
        if (AppUtils.isAppForeground()) {
            if (isShow) {
                isShow = false;
                if (null != mDisp && (!mDisp.isDisposed())) {
                    mDisp.dispose();
                }
                super.dismiss();
            }
        } else {
            SPUtils.getInstance().put(Constant.SYSTEM.DIALOGISDISMISS, true);
            LogUtils.e("当前app属于后台，不可关闭Dialog");
        }
    }

    public void toughDismiss() {
        if (shutDown) {
            if (recLen <= 0) {
                dismiss();
            } else {
                ToastUtils.showLong(recLen + "s 后可关闭");
            }
        }
    }


    /**
     * 设置标题
     *
     * @param title 标题
     */
    protected void setTitle(String title) {
        mBaseBinding.tvTitle.setText(title);
    }

    /**
     * 设置标题
     *
     * @param res 标题
     */
    protected void setTitle(int res) {
        mBaseBinding.tvTitle.setText(res);
    }

    public BaseDialog setBtnName(String okStr, String cancelStr) {
        mBaseBinding.btnCancel.setText(cancelStr);
        mBaseBinding.btnOk.setText(okStr);
        return this;
    }
}
