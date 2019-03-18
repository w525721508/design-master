package design.custom.ui.main.adapter;

import android.content.Context;
import android.support.annotation.Nullable;

import java.util.List;

import design.root.base.R;
import design.root.base.base.BaseAdapter;
import design.root.base.databinding.FragmentSignItemBinding;
import design.root.base.entity.ItemContent;

/**
 * Created by Administrator on 2018/2/7.
 */

public class AdminItemAdapter extends BaseAdapter<ItemContent, FragmentSignItemBinding> {
    Context context;

    public AdminItemAdapter(@Nullable List<ItemContent> data) {
        super(R.layout.item_ziyuans, data);
    }

    public AdminItemAdapter(@Nullable List<ItemContent> data, Context context) {
        super(R.layout.item_ziyuans, data);
        this.context = context;
    }


    @Override
    protected void convert(FragmentSignItemBinding mViewBinding, ItemContent item, int position) {
        mViewBinding.tx.setText(item.getContent());
    }
}
