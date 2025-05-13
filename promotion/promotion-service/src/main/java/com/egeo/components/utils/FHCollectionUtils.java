package com.egeo.components.utils;

import com.egeo.utils.StringUtils;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * @Description todo
 * @Author lsl
 * @Date 2023/12/29 11:31
 * @Version V1.0
 **/
public class FHCollectionUtils {

    /**使用HashSet实现List去重(无序)
     *
     * @param list
     * */
    public static List<String> removeDuplicationByHashSet(List<String> list) {
        HashSet set = new HashSet(list);
        //把List集合所有元素清空
        list.clear();
        //把HashSet对象添加至List集合
        list.addAll(set);
        return list;
    }

    /**
     * @See listToMap(list,UserVo::getUserId, userVo -> userVo);
     * @See listToMap(list,UserVo::getUserId,UserVo::getUserName);
     * @Description List<T>转Map<K,V>
     * @Param srcList 源集合
     * @Param keyMapper key函数
     * @Param valMapper value函数
     * @return java.util.Map<K,V>
     **/
    public static <T, K, V> Map<K, V> listToMap(Collection<T> srcList, Function<? super T, ? extends K> keyMapper, Function<? super T, ? extends V> valMapper) {
        if(CollectionUtils.isEmpty(srcList)){ return null;}
        Map<K, V> map =srcList.stream().collect(Collectors.toMap(keyMapper, valMapper, (k1, k2)->k2));
        return map;
    }

    /**
     * @See listToStrList(list,UserVo::getUserId);
     * @Description List<T>转List<K>
     * @Param srcList 源集合
     * @Param keyMapper key函数
     * @return java.util.List<K>
     **/
    public static <T, K> List<K> listToStrList(Collection<T> srcList, Function<? super T, ? extends K> idMapper) {
        if(CollectionUtils.isEmpty(srcList)){ return null;}
        List<K> list =srcList.stream().map(idMapper).distinct().collect(Collectors.toList());
        return list;
    }

    /**
     * @See listToStrList(list,UserVo::getUserId,e->e.getTaskId()!=null);
     * @Description List<T>转List<K>
     * @Param srcList 源集合
     * @Param keyMapper key函数
     * @Param filter 过滤函数
     * @return java.util.List<K>
     **/
    public static <T, K> List<K> listToStrList(Collection<T> srcList, Function<? super T, ? extends K> idMapper, Predicate<? super T> filter) {
        if(CollectionUtils.isEmpty(srcList)){ return null;}
        if(filter == null){
            return listToStrList(srcList,idMapper);
        }
        List<K> list =srcList.stream().filter(filter).map(idMapper).distinct().collect(Collectors.toList());
        return list;
    }

    /**
     * 校验一个map的key是否包含
     * @param map
     * @param target
     * @return
     */
    public static <T> boolean isContainsKey(Map<String, T> map, String target){
        if(CollectionUtils.isEmpty(map) || StringUtils.isEmpty(target)){
            return false;
        }
        return map.containsKey(target);
    }

    public static <T, U extends Comparable<? super U>> Optional<T> findMinT(List<T> conditions, Function<? super T, ? extends U> keyExtractor) {
        if(CollectionUtils.isEmpty(conditions)){
            return null;
        }
        // 确保U类型实现了Comparable接口，以便能够进行比较
        return conditions.stream().min(Comparator.comparing(keyExtractor));
    }

    public static <T> List<List<T>> splitList(List<T> list, int size) {
        if (list == null) {return Collections.EMPTY_LIST;}
        int page = (list.size() - 1)/ size + 1;
        List<List<T>> listGroup = new ArrayList<>(page);
        if (list.size() <= size) {
            listGroup.add(list);
            return listGroup;
        }
        int listSize = list.size();
        for (int i = 0; i < list.size(); i += size) {
            List<T> newList;
            if (i + size > listSize) {
                newList = list.subList(i, listSize);
            } else {
                newList = list.subList(i, i + size);
            }
            listGroup.add(newList);
        }
        return listGroup;
    }
}
