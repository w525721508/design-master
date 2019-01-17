package design.custom.ui.main;

import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.view.MenuItem;

import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;

import java.util.ArrayList;
import java.util.List;

import design.custom.ui.main.adapter.FragmentViewAdapter;
import design.custom.R;
import design.root.base.base.BaseActivity;
import design.root.base.base.BaseFragment;
import design.root.base.entity.TabEntity;
import design.custom.ui.main.fragment.IndexFragment;
import design.custom.ui.main.fragment.MyFragment;
import design.custom.ui.main.fragment.OrderFragment;
import design.custom.databinding.ActivityMainBinding;


public class MainActivity extends BaseActivity<MainPresenter, MainModel, ActivityMainBinding>
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {
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
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
            public void onPageScrolled(int position, float positionOffset, int
                    positionOffsetPixels) {

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


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int i = item.getItemId();
        if (i == R.id.nav_camera) {

        } else if (i == R.id.nav_gallery) {

        } else if (i == R.id.nav_slideshow) {

        } else if (i == R.id.nav_manage) {

        } else if (i == R.id.nav_chage_pwd) {

        } else if (i == R.id.nav_check_update) {

        } else if (i == R.id.nav_log_out) {

        }
        ((DrawerLayout) findViewById(R.id.drawer_layout)).closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void error(String errorMsg) {

    }
}
