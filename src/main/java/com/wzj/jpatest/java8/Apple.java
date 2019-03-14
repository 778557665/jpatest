package com.wzj.jpatest.java8;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Apple {

    String color;

    Integer weight;

    public String getColor(){
        return color;
    }

    public Integer getWeight(){
        return weight;
    }

    public Apple(String color, Integer weight) {
        this.color = color;
        this.weight = weight;
    }

    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Apple() {
    }

    public static boolean isGreenApple(Apple apple){
        return apple.getColor().equals("green");
    }

    public static boolean isHeavyApple(Apple apple){
        return apple.getWeight() > 150;
    }

    List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> predicate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(predicate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    @Test
    public void test(){
        List<Apple> inventory = new ArrayList<>();
        Apple apple = new Apple();
        apple.color = "1123";
        apple.weight = 111;
        inventory.add(apple);
        filterApples(inventory, (Apple a) ->  a.getWeight() > 100);
    }
}
