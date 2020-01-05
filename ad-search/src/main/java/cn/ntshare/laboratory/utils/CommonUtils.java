package cn.ntshare.laboratory.utils;

import java.util.Map;
import java.util.function.Supplier;

/**
 * Created By Q.Hao
 * Description:
 * Created At 2020/1/4
 */
public class CommonUtils {

    public static<K, V> V getOrCreate(K key, Map<K, V> map, Supplier<V> factory) {
        return map.computeIfAbsent(key, k -> factory.get());
    }

    public static String stringConcat(String... args) {
        StringBuilder result = new StringBuilder();
        for (String arg : args) {
            result.append(arg);
            result.append("-");
        }
        result.deleteCharAt(result.length() - 1);
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(stringConcat("a", "b", "c", "d"));
    }
}
