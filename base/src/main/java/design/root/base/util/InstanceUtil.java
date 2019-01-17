package design.root.base.util;


public class InstanceUtil {
    /**
     * 通过实例工厂去实例化相应类
     *
     * @param <T> 返回实例的泛型类型
     * @return 实例
     */
    public static <T> T getInstance(Class clazz) {
        try {
            return (T) clazz.newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
