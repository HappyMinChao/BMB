package com.java8;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * StreamFilter
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/3/27
 */
public class StreamFilter {
    
    public static void main(String[] args){
        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 7, 8 , 9, 9 , 10);
        List<Integer> collect = list.stream().filter(i -> i % 2 == 0).collect(toList());

        collect = list.stream().distinct().collect(toList());  // 去重元素
        collect = list.stream().skip(5).collect(toList());      //跳过5个元素
        collect = list.stream().limit(5).collect(toList());     //取前五个元素
        
        collect = list.stream().map(i -> i*2).collect(toList());         //入参为T(Integer)， 返回为R

        List<Dish> menu = init();
        List<String> nameList = menu.stream().map(d -> d.getName()).collect(toList());
    }


    public static List<Dish> init(){
        List<Dish> menu = Arrays.asList(
                new Dish("pork", false, 800, Dish.Type.MEAT), new Dish("beef", false, 700, Dish.Type.MEAT), new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER), new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("season fruit", true, 120, Dish.Type.OTHER), new Dish("pizza", true, 550, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH), new Dish("salmon", false, 450, Dish.Type.FISH) );
        return menu;
    }
    
}
