package com.java8;

/**
 * Dish
 *
 * @author : minchao.du
 * @description :
 * @date : 2018/3/27
 */
public class Dish {
    
    private String name;
    private  final boolean vetegarian;
    private final int calories;
    private final Type type;
    
    public Dish(String name, boolean vetegarian, int calories, Type type) {
        
        this.name = name;
        this.vetegarian = vetegarian;
        this.calories = calories;
        this.type = type;
        
    }

    
    
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isVetegarian() {
        return vetegarian;
    }

    public int getCalories() {
        return calories;
    }

    public Type getType() {
        return type;
    }
    
    public enum Type{
        MEAT,FISH,OTHER
    }
}
