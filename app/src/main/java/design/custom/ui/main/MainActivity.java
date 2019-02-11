package design.custom.ui.main;

import android.support.v4.view.ViewPager;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import design.custom.R;
import design.custom.databinding.ActivityMainBinding;
import design.custom.ui.main.adapter.FragmentViewAdapter;
import design.custom.ui.main.fragment.IndexFragment;
import design.custom.ui.main.fragment.MyFragment;
import design.custom.ui.main.fragment.OrderFragment;
import design.root.base.base.BaseActivity;
import design.root.base.base.BaseFragment;
import design.root.base.entity.TabEntity;


public class MainActivity extends BaseActivity<MainPresenter, MainModel, ActivityMainBinding>
        implements MainContract.View {
    private FragmentViewAdapter fragmentViewAdapter;
    private int[] titleInts = {R.string.menu_index, R.string.menu_order, R.string.menu_myself};
    private int[] unSelectIconList = {R.mipmap.icon_menu_cashier_0, R.mipmap.icon_menu_service_0, R
            .mipmap.icon_menu_myself_0};

    private int[] selectIconList = {R.mipmap.icon_menu_cashier_1, R.mipmap.icon_menu_service_1, R
            .mipmap.icon_menu_myself_1};
    //选中菜单字体颜色
    private int selectColer = R.color.red_guanyu;
    //未选中菜单字体颜色
    private int unSelectColor = R.color.black_likui;

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    public void initView() {
        List<BaseFragment> list = new ArrayList<>();
        list.add(new IndexFragment());
        list.add(new OrderFragment());
        list.add(new MyFragment());

        fragmentViewAdapter = new FragmentViewAdapter(list, getSupportFragmentManager());
        mViewBinding.fragmentContainer.setAdapter(fragmentViewAdapter);
        initMenu();
        mViewBinding.tabLayout.setTabData(mTabEntities);
        mPresenter.init();
    }

    private void initMenu() {
        for (int i = 0; i < titleInts.length; i++) {
            mTabEntities.add(new TabEntity(titleInts[i], selectIconList[i], unSelectIconList[i]));
        }
        mViewBinding.tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewBinding.fragmentContainer.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });
//        mViewBinding.tabLayout.showDot(2);
        mViewBinding.fragmentContainer.addOnPageChangeListener(new ViewPager.OnPageChangeListener
                () {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mViewBinding.tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    @Override
    public void error(String errorMsg) {

    }
}
