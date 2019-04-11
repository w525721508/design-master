package design.root.base.ui.dialog;

import android.support.annotation.Nullable;

import java.util.List;

import design.root.base.R;
import design.root.base.base.BaseAdapter;
import design.root.base.databinding.ItemZuoxiBinding;
import design.root.base.entity.ZuoXiEntity;

/**
 * Created by Administrator on 2018/2/7.
 */

public class DialogAdapter extends BaseAdapter<ZuoXiEntity, ItemZuoxiBinding> {
    public DialogAdapter(@Nullable List<ZuoXiEntity> data) {
        super(R.layout.item_zuoxi, data);
    }


    @Override
    protected void convert(ItemZuoxiBinding mViewBinding, ZuoXiEntity item, int position) {
        mViewBinding.tvZuoxi.setText(item.name);
        mViewBinding.tvJiage.setText("￥" + item.price);
        mViewBinding.tvYupiao.setText(item.count + "张");
    }
}
