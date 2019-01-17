package design.root.base.api;

import com.blankj.utilcode.util.LogUtils;

import java.util.concurrent.TimeUnit;

import design.root.base.Constant;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/2/1.
 */

public class RetryHelper implements Function<Observable<Throwable>, ObservableSource<?>> {
    private int retryCount;
    private int retryTime;
    private int nowCount = 0;

    RetryHelper(int retryCount) {
        this.retryCount = retryCount;
        this.retryTime = Constant.API_BUILDER.RETRY_DELAY_TIME;
    }


    RetryHelper(int retryCount, int retryTime) {
        this.retryCount = retryCount;
        this.retryTime = retryTime;
    }


    @Override
    public ObservableSource<?> apply(Observable<Throwable> throwableObservable) throws Exception {
        return throwableObservable.flatMap(new Function<Throwable, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Throwable throwable) throws Exception {
                if (throwable instanceof ApiException.IO) {
                    if (++nowCount <= retryCount) {
                        LogUtils.e("重试时间：" + retryTime
                                + "重试：" + nowCount);
                        return Observable.timer(retryTime, TimeUnit.SECONDS);
                    }
                    return Observable.error(throwable);

                } else {
                    return Observable.error(throwable);
                }
            }
        });
    }
}
