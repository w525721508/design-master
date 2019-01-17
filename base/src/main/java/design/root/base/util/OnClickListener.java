package design.root.base.util;

import android.view.View;

/**
 * Created by Administrator on 2017/12/2.
 */

public abstract class OnClickListener implements View.OnClickListener {
    private long mExitTime = 0;

    @Override
    public void onClick(View view) {
        if ((System.currentTimeMillis() - mExitTime) > 500) {
            mExitTime = System.currentTimeMillis();
            myOnClickListener(view);
        } else {
//            ToastUtils.showLong("您操作太快了");
        }
    }

    protected abstract void myOnClickListener(View v);
}
