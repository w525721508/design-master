package design.root.base.api;

import android.os.Build;
import android.support.annotation.RequiresApi;

import com.blankj.utilcode.util.LogUtils;

import design.root.base.Constant;
import design.root.base.entity.HttpMessage;

import io.reactivex.functions.Function;

/**
 * Created by Administrator on 2018/2/1.
 */

@RequiresApi(api = Build.VERSION_CODES.N)
class ErrorFilter<T> implements Function<Throwable, HttpMessage<T>> {
    @Override
    public HttpMessage<T> apply(Throwable throwable) throws Exception {
        if (throwable != null) LogUtils.e(throwable.getMessage());
        //可根据Throwable具体区分,现在先统一变成服务器异常
        return new HttpMessage<>(Constant.RETURN_CODE.SERVER_ERR, Constant.RETURN_INFO.SERVER_ERR);
    }
}
