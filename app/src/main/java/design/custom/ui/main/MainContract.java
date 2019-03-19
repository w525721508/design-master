package design.custom.ui.main;

import design.root.base.base.BaseModel;
import design.root.base.base.BasePresenter;
import design.root.base.base.BaseView;

/**
 */

public interface MainContract {
    interface View extends BaseView {

    }

    abstract class Model extends BaseModel {

    }

    abstract class PreSenter extends BasePresenter<View, Model> {
        abstract void submit();
    }
}
