package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Stream;

/**
 * SteamMatch
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/3/27
 */
public class SteamMatch {
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(new Integer[]{1, 2, 3, 4, 5, 6, 7, 7, 8, 9, 9, 10});
        boolean b = list.stream().allMatch(i -> i > 2);             // 所有元素都大于2
        System.out.println("All more than two ?  " + " " + b);
        System.out.println(b);          //false

        b = list.stream().anyMatch(i -> i > 6);     // 任意一个大于6？
        System.out.println(b);          // true 

        b = list.stream().noneMatch(i -> i < 3);        // 都小于3
        System.out.println(b);          // false
        
        assert  b;
        
        
        // =============== find ======================

        Optional<Integer> optionalOne = list.stream().filter(i -> i % 2 == 0).findAny();
        Optional<Integer> optionalTwo = list.stream().filter(i -> i % 2 == 0).findAny();
        int resultOne = optionalTwo.orElse(-1);     //如果找不到则返回-1
        int resultTwo = find(list, -1, i -> i % 2 == 0); // 此函数的作用和上一行代码是等效的
        optionalTwo.ifPresent(System.out::println);     // 如果存在则打印出来
        
        System.out.println("resultOne : " + resultOne + " resultTwo : " + resultTwo);

        //================reduce===================== 起到聚合的作用
        Integer reduce = list.stream().reduce(0, (i, j) -> i + j);
        System.out.println("对数组进行求和为： " + reduce);
        list.stream().reduce((i,j) -> i+j).ifPresent(System.out::println);
        
        list.stream().reduce(Integer::max).ifPresent(System.out::println);  // 数组的最大值
        list.stream().reduce(Integer::min).ifPresent(System.out::println);  // 数组的最小值
    }
    
    public static  int find(List<Integer> list , int defaultValue, Predicate<Integer> predicate){
        for (int i : list){
            if (predicate.test(i)) {
                return  i; 
            }

        }
        return defaultValue;
    }
}
