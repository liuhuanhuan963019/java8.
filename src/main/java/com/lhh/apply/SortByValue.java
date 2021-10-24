package com.lhh.apply;

import java.util.Map;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

public class SortByValue {
    public static void main(String[] args) {
        Map<String, Integer> map = ImmutableMap.of("0", 3, "1", 8, "0.29", 7, "1.67", 3);
        System.out.println("原始的map：" + map);
        System.out.println("根据map的key降序：" + sortByKey(map, true));
        System.out.println("根据map的key升序：" + sortByKey(map, false));
        System.out.println("根据map的value降序：" + sortByValue(map, true));
        System.out.println("根据map的value升序：" + sortByValue(map, false));
    }
    /**
     * 根据map的key排序
     *
     * @param map 待排序的map
     * @param isDesc 是否降序，true：降序，false：升序
     * @return 排序好的map
     */
    public static <K extends Comparable<? super K>, V> Map<K, V> sortByKey(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }
    /**
     * 根据map的value排序
     *
     * @param map 待排序的map
     * @param isDesc 是否降序，true：降序，false：升序
     * @return 排序好的map
     */
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map, boolean isDesc) {
        Map<K, V> result = Maps.newLinkedHashMap();
        if (isDesc) {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue().reversed())
                    .forEach(e -> result.put(e.getKey(), e.getValue()));
        } else {
            map.entrySet().stream().sorted(Map.Entry.<K, V>comparingByValue())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }
}
