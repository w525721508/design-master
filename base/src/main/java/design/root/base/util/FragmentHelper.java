package design.root.base.util;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

/**
 * Created by tzduan on 18/2/7.
 */

public class FragmentHelper {

    public static void removeFragment(FragmentManager fragmentManager, String tag) {
        if(fragmentManager != null) {
            try {
                if(fragmentManager != null && fragmentManager.findFragmentByTag(tag) != null) {
                    fragmentManager.popBackStack(tag, 1);
                }
            } catch (Exception var4) {
                ;
            }

            Fragment targetFragment = fragmentManager.findFragmentByTag(tag);
            if(targetFragment != null) {
                FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();
                localFragmentTransaction.remove(targetFragment);
                localFragmentTransaction.commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
            }
        }

    }

    public static void removeFragment(FragmentManager fragmentManager, Fragment targetFragment) {
        if(fragmentManager != null) {
            String tag = targetFragment.getTag();

            try {
                try {
                    if(fragmentManager != null && fragmentManager.findFragmentByTag(tag) != null) {
                        fragmentManager.popBackStack(tag, 1);
                    }
                } catch (Exception var5) {
                    ;
                }

                FragmentTransaction localFragmentTransaction = fragmentManager.beginTransaction();
                localFragmentTransaction.remove(targetFragment);
                localFragmentTransaction.commitAllowingStateLoss();
                fragmentManager.executePendingTransactions();
                Fragment fragment = fragmentManager.findFragmentByTag(tag);
                if(fragment != null) {
                    localFragmentTransaction.remove(fragment);
                    localFragmentTransaction.commitAllowingStateLoss();
                    fragmentManager.executePendingTransactions();
                }
            } catch (Exception var6) {
                ;
            }
        }

    }

    public static void addFragment(FragmentManager supportFragmentManager, Fragment baseDialogFragment, String tag) {
        addFragment(supportFragmentManager, baseDialogFragment, android.R.id.content, tag);
    }

    public static void addFragment(FragmentManager supportFragmentManager, Fragment baseDialogFragment, int content, String tag) {
        try {
            FragmentTransaction transaction = supportFragmentManager.beginTransaction();
            Fragment fragment = supportFragmentManager.findFragmentById(content);
            if(fragment != null) {
                if(fragment instanceof FragmentManager.OnBackStackChangedListener) {
                    supportFragmentManager.addOnBackStackChangedListener((FragmentManager.OnBackStackChangedListener)fragment);
                }

                transaction.hide(fragment);
            }

            transaction.add(content, baseDialogFragment, tag);
            transaction.addToBackStack(tag);
            transaction.commitAllowingStateLoss();
        } catch (IllegalStateException var6) {
            var6.printStackTrace();
        }

    }

    public static void addDialogFragment(FragmentManager supportFragmentManager, Fragment baseDialogFragment, String tag){
        FragmentTransaction ft = supportFragmentManager.beginTransaction();
        ft.add(baseDialogFragment, tag);
        ft.commitAllowingStateLoss();
    }
}
