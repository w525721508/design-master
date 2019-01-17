package design.custom.ui.main.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

import design.root.base.base.BaseFragment;

/**
 * Created by Administrator on 2018/1/26.
 */

public class FragmentViewAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> fragmentList;

    public FragmentViewAdapter(List<BaseFragment> fragmentList, FragmentManager fm) {
        super(fm);
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if (fragmentList.get(position).getTabTitle() != null) {
            return fragmentList.get(position).getTabTitle();
        }
        return super.getPageTitle(position);
    }
}
