package design.root.base.api;

import java.io.IOException;

/**
 * Created by Administrator on 2018/2/1.
 */

public class ApiException {
    static class API extends RuntimeException {
        private String returnCode;
        private String returnInfo;

        API(String returnCode, String returnInfo) {
            //TODO 应该是returnCode, returnInfo 组成的Json
            super(getErrorDesc(returnCode, returnInfo));
            this.returnInfo = returnInfo;
            this.returnCode = returnCode;
        }

        @Override
        public String getLocalizedMessage() {
            //TODO 应当是getErrorDesc(getMessage())
            return returnCode;

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
    }

    static class IO extends IOException {
        IO(String returnCode, String returnInfo) {
            super(getErrorDesc(returnCode, returnInfo));
        }
    }

    /**
     * 获取问题描述
     *
     * @param returnCode 返回的状态吗
     * @param returnInfo 返回的信息
     * @return 问题描述
     */
    private static String getErrorDesc(String returnCode, String returnInfo) {
        switch (returnCode) {
            //如需根据Code自定义返回信息,则在这里添加

            //默认直接返回原本的returnInfo
            default:
                return returnInfo;
        }
    }
}
