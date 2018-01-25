package com.daily.learn.guava.cache;

import com.google.common.cache.*;
import com.google.common.collect.ImmutableList;

import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by SunGuiyong
 * on 2018/1/3.
 * Guava Cache
 * <p>
 * ConcurrentMap<K,V> asMap()
 * V get(K key)
 * ImmutableMap<K,V> getAll(Iterable<? extends K> keys)
 * V getUnchecked(K key)
 */
public class CacheTest {

    private RemovalListener<Integer, String> MY_LISTEN = ((removalNotification) ->
            System.out.println("remove key :" + removalNotification.getKey() +
                    "  remove value :" + removalNotification.getValue()));
    private LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(3, TimeUnit.SECONDS)//设置缓存失效时间
            .removalListener(MY_LISTEN)
            .build(//缓存加载器，如果缓存存在，返回值，如果不存在，计算，放入缓存，
                    new CacheLoader<Integer, String>() {
                        @Override
                        public String load(Integer integer) throws Exception {
                            System.out.println("add into cache");
                            return "9";
                        }
                    }
            );
          /*.maximumWeight(100)
            .weigher((Integer, String) -> 13)
            .refreshAfterWrite()
            .recordStats()
            .maximumSize(100)//设置最大缓存值*/

    //没有设置加载器
    private Cache<Integer, String> cache_2 = CacheBuilder.newBuilder()
            .maximumSize(100)
            .expireAfterAccess(3, TimeUnit.SECONDS)
            .build();

    public static void main(String[] args) {
        CacheTest test = new CacheTest();

        System.out.println("*************use CacheLoader**************");
        test.useCacheLoader();

        System.out.println("*************use Callable**************");
        test.useCallable();

    }

    private void useCacheLoader() {
        System.out.println("use unchecked get.don't need catch Exception\n");
        String value = cache.getUnchecked(1);
        try {
            System.out.println("first get value :" + value);
            System.out.println();
            value = cache.get(1);
            System.out.println("second get value from cache :" + value);

            System.out.println("wait 3 seconds");
            Thread.sleep(3000);

            value = cache.get(1);
            System.out.println("get value after sleep 5s:" + value);

            System.out.println();
            value = cache.get(1);
            System.out.println("through cache :" + value);

            cache.invalidate(1);
            value = cache.get(1);
            System.out.println("use invalidate :" + value);

            ConcurrentMap<Integer, String> map = cache.asMap();
            System.out.println("use cache.asMap: " + map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void useCallable() {
        try {
            String value;
            value = cache_2.get(2, () -> String.valueOf(Math.max(123, 13123)));
//            value = cache_2.get(2, () -> null);
            System.out.println("use callable: " + value);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}