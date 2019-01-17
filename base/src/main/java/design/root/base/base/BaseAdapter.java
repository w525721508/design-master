package design.root.base.base;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import design.root.base.R;

import java.util.List;

/**
 * Created by Administrator on 2018/2/7.
 */

public abstract class BaseAdapter<T, B extends ViewDataBinding> extends BaseQuickAdapter<T,
        BaseViewHolder> {

    public BaseAdapter(@LayoutRes int layoutResId, @Nullable List<T> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, T item) {
        convert((B) helper.mViewBinding, item, helper.getLayoutPosition());
    }

    protected abstract void convert(B mViewBinding, T item, int position);

    @Override
    protected View getItemView(int layoutResId, ViewGroup parent) {
        ViewDataBinding binding = DataBindingUtil.inflate(mLayoutInflater, layoutResId, parent,
                false);
        if (binding == null) return super.getItemView(layoutResId, parent);
        View view = binding.getRoot();
        view.setTag(R.id.BaseQuickAdapter_databinding_support, binding);
        return view;
    }
}
