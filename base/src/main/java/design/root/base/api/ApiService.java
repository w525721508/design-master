package design.root.base.api;

import design.root.base.entity.HttpMessage;
import design.root.base.entity.UserEntity;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Administrator on 2018/1/31.
 */

public interface ApiService {

    @POST("index.php?c=Index&a=login")
    Observable<HttpMessage<UserEntity>> login(@Body UserEntity userEntity);

    @POST("index.php?c=Super&a=super")
    Observable<HttpMessage<String>> superUser(@Body UserEntity userEntity);
}
