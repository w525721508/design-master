package design.custom.ui.main.adapter;

import android.support.annotation.Nullable;

import java.util.List;

import design.root.base.R;
import design.root.base.base.BaseAdapter;
import design.root.base.databinding.FragmentSignItemBinding;

/**
 * Created by Administrator on 2018/2/7.
 */

public class IndexFragmentAdapter extends BaseAdapter<String, FragmentSignItemBinding> {
    public IndexFragmentAdapter(@Nullable List<String> data) {
        super(R.layout.fragment_sign_item, data);
    }


    @Override
    protected void convert(FragmentSignItemBinding mViewBinding, String item, int position) {
        mViewBinding.tx.setText(item);
    }
}
