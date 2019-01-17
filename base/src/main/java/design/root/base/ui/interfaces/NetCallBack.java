package design.root.base.ui.interfaces;

/**
 * Created by Administrator on 2018/2/2.
 */

public interface NetCallBack<T> {
    void succ(T t);

    void error(String str);

}
