package design.root.base.api;

import design.root.base.entity.HttpMessage;
import design.root.base.entity.UserEntity;
import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2018/1/31.
 */

public interface ApiService {

    @POST("sign")
    Observable<HttpMessage<UserEntity>> sign(@Body UserEntity userEntity);

    @POST("registered")
    Observable<HttpMessage<UserEntity>> registered(@Body UserEntity userEntity);

    @POST("changepassword")
    Observable<HttpMessage<UserEntity>> changepassword(@Query("userName") String userName, @Query("oldPassword") String oldPassword,
                                                   @Query("newPassword") String newPassword, @Query("appId") String appId);
}
