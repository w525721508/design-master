package design.root.base.ui.sign;

import com.blankj.utilcode.util.LogUtils;

import java.util.List;

import design.root.base.Constant;
import design.root.base.api.ApiFactory;
import design.root.base.db.DbHelper;
import design.root.base.entity.UserEntity;
import design.root.base.ui.interfaces.NetCallBack;
import design.root.base.ui.sign.LoginContract;
import io.reactivex.functions.Consumer;

/**
 * Created by Administrator on 2018/1/22.
 */

public class LoginModel extends LoginContract.Model {


    @Override
    public void register(String userName, String PwdOne, NetCallBack netCallBack) {
        UserEntity user = new UserEntity();
        user.setUsername(userName);
        user.setPassword(PwdOne);
        user.setAge("15");
        user.setMobile("10086");
        user.setSex("保密");
        user.toAddData();
        if (Constant.SYSTEM.NEEDSERVER) {
            ApiFactory.UserApi.superUser(user).subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    netCallBack.succ(s);
                }
            }, throwable -> {
                netCallBack.error(throwable.getMessage());
            });
        } else {
            if (DbHelper.getInstance().queryUserNameToList(user).size() > 0) {
                netCallBack.error("注册失败，该账户已存在");
            } else {
                DbHelper.getInstance().insertUserEntity(user);
                netCallBack.succ("");
            }

        }


    }

    @Override
    public void sign(String userName, String pwd, NetCallBack netCallBack) {
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userName);
        userEntity.setPassword(pwd);
        if (Constant.SYSTEM.NEEDSERVER) {
            ApiFactory.UserApi.login(userEntity).subscribe(new Consumer<UserEntity>() {
                @Override
                public void accept(UserEntity userEntity) throws Exception {
                    netCallBack.succ(userEntity);
                    LogUtils.e(userEntity.toString());
                }
            }, throwable -> {
                netCallBack.error(throwable.getMessage());
            });
        } else {
            List<UserEntity> userEntities = DbHelper.getInstance().queryUserNamePwdToList
                    (userEntity);
            if (userEntities.size() > 0) {
                netCallBack.succ(userEntities.get(0));
                LogUtils.e(userEntity.toString());
            } else {
                netCallBack.error("账户密码错误");
            }
        }

    }

    //    @Override
    public void changePwd(String username, String PwdOne, String PwdTwo, NetCallBack netCallBack) {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setId(DbHelper.getInstance().queryUserNameToList(user).get(0).getId());
        user.setPassword(PwdTwo);
        user.setAge("15");
        user.setMobile("10086");
        user.setSex("保密");
        user.toUpdateData();

        if (Constant.SYSTEM.NEEDSERVER) {
            ApiFactory.UserApi.superUser(user).subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Exception {
                    netCallBack.succ(s);
                }
            }, throwable -> {
                netCallBack.error(throwable.getMessage());
            });
        } else {
            if (DbHelper.getInstance().updateUserEntity(user)) {
                netCallBack.succ("");
            } else {
                netCallBack.error("修改失败");
            }
        }

    }
}
