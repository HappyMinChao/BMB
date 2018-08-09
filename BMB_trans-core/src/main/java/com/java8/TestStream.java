package com.java8;

import com.sun.org.apache.bcel.internal.generic.RETURN;

import java.util.*;

import static java.util.stream.Collectors.toList;

/**
 * TestStream
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/3/27
 */
public class TestStream {


    public static void main(String[] args){
        List<Dish> menu = init();
        List list = getDishNameByCollections(menu);
        System.out.println(list);
        list = getDishNameByStream(menu);
        System.out.println(list);

    }

    public static List<String> getDishNameByStream(List<Dish> menu) {
        return menu.stream()           // 把list转换成stream, 使用的Bilder设计模式
            .filter(d -> d.getCalories() < 400) // 参数为Predict， 还会返回自己
            .sorted(Comparator.comparing(Dish::getCalories))
            .map(Dish::getName)  // 通过名字构建集合
            .collect(toList())
        ;
    }
    
    private static List<String> getDishNameByCollections(List<Dish> menu){
        List<Dish> lowCalories = new ArrayList<>();
        
        //filter and get calories less 400 
        for (Dish d : menu) {
            if (d.getCalories() < 400) {
                lowCalories.add(d);
            }
        }
        
        //sort
        Collections.sort(lowCalories, (d1, d2) -> Integer.compare(d1.getCalories() , d2.getCalories()));

        List<String> dishNameList = new ArrayList<>();
        for (Dish d : lowCalories) {
            dishNameList.add(d.getName());
        }
        
        return  dishNameList;
        
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
