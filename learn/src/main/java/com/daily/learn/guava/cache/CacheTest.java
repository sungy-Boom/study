package com.daily.learn.guava.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.ImmutableList;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

/**
 * Created by SunGuiyong
 * on 2018/1/3.
 */
public class CacheTest {

    private LoadingCache<Integer, String> cache = CacheBuilder.newBuilder()
            .maximumSize(100)//设置最大缓存值
            .expireAfterAccess(5, TimeUnit.SECONDS)//设置缓存失效时间
            .recordStats()
            /*.refreshAfterWrite()*/
            .build(//缓存加载器，如果缓存存在，返回值，如果不存在，计算，放入缓存，
                    new CacheLoader<Integer, String>() {
                        @Override
                        public String load(Integer integer) throws Exception {
                            System.out.println("add into cache");
                            return "9";
                        }
                    }
            );

    public static void main(String[] args) {
        CacheTest test = new CacheTest();

        System.out.println("*************use CacheLoader**************");
        test.useCacheLoader();

        System.out.println("*************use Callable**************");
        test.useCallable();

    }

    private void useCacheLoader() {

        try {
            String value = cache.get(1);
            System.out.println(value);
            System.out.println();
            System.out.println("get value from cache");
            value = cache.get(1);
            System.out.println(value);

            System.out.println("wait 5 seconds");
            Thread.sleep(5000);
            value = cache.get(1);
            System.out.println(value);
            System.out.println();
            System.out.println("get value from cache");
            value = cache.get(1);
            System.out.println(value);

            ConcurrentMap<Integer, String> map = cache.asMap();
            System.out.println(map);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void useCallable() {
        System.out.println("use unchecked get.don't need catch Exception\n");
        String value = cache.getUnchecked(1);
        System.out.println(value);

        try {
            value = cache.get(2, () -> String.valueOf(Math.max(123, 13123)));
            System.out.println("use callable: " + value);

            cache.invalidate(1);
            cache.invalidateAll(ImmutableList.of(1, 2));
            System.out.println();
            value = cache.get(1);
            System.out.println("get after invalidate : " + value);

            System.out.println();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}