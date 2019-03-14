package com.wzj.jpatest.java8;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import org.junit.Test;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TestOptional {

    @Test
    public void givenEmptyValue_whenCompare_thenOk() {
        Apple apple = new Apple("bule", 120);
        System.out.println("Using orElse");
        Apple result = Optional.ofNullable(apple).orElse(createNewUser());
        System.out.println("Using orElseGet");
        Apple result2 = Optional.ofNullable(apple).orElseGet(() -> createNewUser());
    }

    @Test(expected = NoSuchElementException.class)
    public void whenCreateEmptyOptional_thenNull() {
        Optional<Apple> emptyOpt = Optional.empty();
        emptyOpt.get();
    }

    @Test
    public void whenCheckIfPresent_thenOk() {
        Apple apple = new Apple("bule", 120);
        Optional<Apple> opt = Optional.ofNullable(apple);
        assertTrue(opt.isPresent());

        assertEquals(apple.getColor(), opt.get().getColor());
    }

    private Apple createNewUser() {
        System.out.println("Creating New Apple");
        return new Apple("red", 111);
    }

    public static List<Apple> filterGreenApples(List<Apple> inventory, String color) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getColor().equals(color)){
                result.add(apple);
            }
        }
        return result;
    }

    public static List<Apple> filterApplesByWeight(List<Apple> inventory, Integer weight) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(apple.getWeight() > weight){
                result.add(apple);
            }
        }
        return result;
    }

    public interface ApplePrediccate {
        boolean test (Apple apple);
    }

    public interface AppleFormatter {
        String accept (Apple apple);
    }

    public class AppleHeavyWeightPredicate implements ApplePrediccate {

        @Override
        public boolean test(Apple apple) {
            return apple.getWeight() > 150;
        }
    }

    public class AppleColorPredicate implements ApplePrediccate {

        @Override
        public boolean test(Apple apple) {
            return apple.getColor().equals("red");
        }
    }

    public class AppleFancyFormatter implements AppleFormatter {

        @Override
        public String accept(Apple apple) {
            String result = apple.getWeight() > 150 ? "heavy" : "light";
            return  "A " + result + " " + apple.getColor() + " apple";
        }
    }

    public class AppleSimpleFormatter implements AppleFormatter {

        @Override
        public String accept(Apple apple) {
            return  "An apple of " + apple.getWeight() + "g";
        }
    }

    public static List<Apple> filterApples(List<Apple> inventory, ApplePrediccate applePrediccate){
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if(applePrediccate.test(apple)){
                result.add(apple);
            }
        }
        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
        for (Apple apple : inventory) {
            String color = appleFormatter.accept(apple);
            System.out.println(color);
        }
    }

    @Test
    public void test1(){
        List<Apple> list = new ArrayList<>();
        Apple apple1 = new Apple();
        apple1.color = "red";
        list.add(apple1);
        prettyPrintApple(list, apple -> String.valueOf(apple.getColor().equals("red")));
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T e : list) {
            if(p.test(e)){
                result.add(e);
            }
        }
        return result;
    }

    public static String processFile() throws IOException {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader("data.txt"))){
            return bufferedReader.readLine();
        }
    }

    @Test
    public String test2() throws IOException {
        Supplier<Apple> c1 = Apple::new;
        Supplier<Apple> c2 = () -> new Apple();

        Function<Integer, Apple> c3 = Apple::new;
        Function<Integer, Apple> c4 = (a) -> new Apple(a);

        return null;
    }


}
