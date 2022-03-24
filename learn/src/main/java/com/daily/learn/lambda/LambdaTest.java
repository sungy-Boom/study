package com.daily.learn.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
 * @auth amos
 * @date 2018/11/30
 **/
public class LambdaTest {

    public static void main(String[] args) {
        List<String> list = Arrays.asList("a", "c", "b", "d", "b");
        List<String> stream = list.stream().filter(element -> {
            System.out.println("filter() was invoked");
            return element.contains("b");
        }).map(ele -> {
            System.out.println("map() was invoked");
            return ele.toUpperCase();
        }).collect(Collectors.toList());

        System.out.println(stream);
        Stream<Integer> iteratedStream = Stream.iterate(10, n -> n + 1).limit(10);
        iteratedStream.forEach(System.out::println);
    }

}
