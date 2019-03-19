package design.root.base.ui.interfaces;

/**
 *
 */

public interface NetCallBack<T> {
    void succ(T t);

    void error(String str);

}
