package net.uncrash.core.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * ThreadLocal 工具类,通过在ThreadLocal存储map信息,来实现在ThreadLocal中维护多个信息
 *
 * @Author: zhouhao
 */
@SuppressWarnings("unchecked")
public class ThreadLocalUtils {

    private ThreadLocalUtils() {
    }

    private static final ThreadLocal<Map<String, Object>> LOCAL = ThreadLocal.withInitial(HashMap::new);

    public static Map<String, Object> getAll() {
        return LOCAL.get();
    }

    public static <T> T put(String key, T value) {
        LOCAL.get().put(key, value);
        return value;
    }

    public static void remove(String key) {
        LOCAL.get().remove(key);
    }

    public static void clear() {
        LOCAL.remove();
    }

    /**
     * 从ThreadLocal中获取值
     *
     * @param key 键
     * @param <T> 值泛型
     * @return 值, 不存在则返回null, 如果类型与泛型不一致, 可能抛出{@link ClassCastException}
     * @see Map#get(Object)
     * @see ClassCastException
     */
    public static <T> T get(String key) {
        return ((T) LOCAL.get().get(key));
    }

    /**
     * 从ThreadLocal中获取值,并指定一个当值不存在的提供者
     *
     * @see Supplier
     */
    public static <T> T get(String key, Supplier<T> supplier) {
        return ((T) LOCAL.get().computeIfAbsent(key, k -> supplier.get()));
    }

    /**
     * 获取然后删除
     *
     * @param key
     * @param <T>
     * @return
     */
    public static <T> T getAndRemove(String key) {
        try {
            return get(key);
        } finally {
            remove(key);
        }
    }

}
