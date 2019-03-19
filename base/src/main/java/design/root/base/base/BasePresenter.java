package design.root.base.base;

/**
 */

public abstract class BasePresenter<V, M> {
    protected V mView;
    protected M mModel;

    BasePresenter setView(V v, M m) {
        this.mView = v;
        this.mModel = m;
        return this;
    }

    public abstract void init();

    public abstract void onDestroy();

}
