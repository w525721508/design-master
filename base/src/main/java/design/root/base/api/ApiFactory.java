package design.root.base.api;


import design.root.base.entity.HttpMessage;
import design.root.base.entity.UserEntity;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/2/1.
 */

public class ApiFactory {
    public static class Test {
//        public static Observable<ApiDataEntity> getHotTop() {
//            return obFactory(Api.getInstance().apiService.getHotTopic());
//        }
    }

    public static class UserApi {
        public static Observable<UserEntity> login(UserEntity userEntity) {
            return obFactory(Api.getInstance().apiService.sign(userEntity));
        }

        public static Observable<UserEntity> registered(UserEntity user) {
            return obFactory(Api.getInstance().apiService.registered(user));
        }

        public static Observable<UserEntity> changepwd(String userName, String oldPassword, String newPassword, String appId) {
            return obFactory(Api.getInstance().apiService.changepassword(userName, oldPassword, newPassword, appId));
        }
    }

    public static <T> Observable<T> obFactory(Observable<HttpMessage<T>> observable) {
        return observable.onErrorReturn(new ErrorFilter<>())
                .map(new ResultFilter<T>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .retryWhen(new RetryHelper(3));
    }
}
