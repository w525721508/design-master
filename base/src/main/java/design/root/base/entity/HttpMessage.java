package design.root.base.entity;

/**
 * Created by Administrator on 2018/2/1.
 */

public class HttpMessage<T> {
    private String returnCode;

    private String returnInfo;

    private T returnData;

    private boolean success;

    public HttpMessage(String returnCode, String returnInfo) {
        this.returnCode = returnCode;
        this.returnInfo = returnInfo;
    }

    public String getReturnCode() {
        return returnCode;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnInfo() {
        return returnInfo;
    }

    public void setReturnInfo(String returnInfo) {
        this.returnInfo = returnInfo;
    }

    public T getReturnData() {
        return returnData;
    }

    public void setReturnData(T returnData) {
        this.returnData = returnData;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
