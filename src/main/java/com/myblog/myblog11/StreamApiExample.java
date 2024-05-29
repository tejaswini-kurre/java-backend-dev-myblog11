package com.myblog.myblog11;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class StreamApiExample {
    public static void main(String[] args) {

        //Predicate Functional Interface
//        Predicate<Integer> val = x -> x%2 == 0;
//        boolean test = val.test(10);
//        System.out.println(test);

//        Predicate<String> val2 =  str -> str.equals("mile");
//        boolean stalin = val2.test("stalin");
//        System.out.println(stalin);

//        List<Integer> numbers = Arrays.asList(12,78,45,37,80);
//        List<Integer> evenNum = numbers.stream().filter(x -> x % 2 == 0).collect(Collectors.toList());
//        System.out.println(evenNum);

//        List<Integer> numbers2 = Arrays.asList(12,78,45,37,80);
//        List<Integer> oddNum = numbers2.stream().filter(x -> x % 2 != 0).collect(Collectors.toList());
//        System.out.println(oddNum);

//        List<String> names = Arrays.asList("mike", "adam", "mike", "stalin");
//        List<String> data1 = names.stream().filter(x -> x.startsWith("m")).collect(Collectors.toList());
//        System.out.println(data1);
//        List<String> data2 = names.stream().filter(x -> x.equals("mike")).collect(Collectors.toList());
//        System.out.println(data2);

        //Function Functional Interface
//        Function<String, Integer> result = str -> str.length();
//        Integer val = result.apply("mike");
//        System.out.println(val);

//        Function<Integer, Integer> result = str -> str+10;
//        Integer val = result.apply(40);
//        System.out.println(val);

//        List<Integer> numbers2 = Arrays.asList(12,78,45,37,80);
//        List<Integer> data1 = numbers2.stream().map(x -> x + 100).collect(Collectors.toList());
//        System.out.println(data1);

//        List<String> numbers2 = Arrays.asList("mike", "adam", "stalin");
//        List<String> data1 = numbers2.stream().map(x -> x.toUpperCase()).collect(Collectors.toList());
//        System.out.println(data1);

//        List<String> numbers2 = Arrays.asList("mike", "adam", "stalin");
//        List<String> data1 = numbers2.stream().sorted().collect(Collectors.toList());
//        System.out.println(data1);

//        List<Integer> numbers2 = Arrays.asList(12,78,45,37,80);
//        List<Integer> data1 = numbers2.stream().sorted().collect(Collectors.toList());
//        System.out.println(data1);

//        List<Integer> numbers2 = Arrays.asList(12,78,45,37,80, 12, 37, 20, 12);
//        List<Integer> data1 = numbers2.stream().distinct().collect(Collectors.toList());
//        System.out.println(data1);

        //Consumer Functional Interface
//        Consumer<Integer> con = num -> System.out.println(num);
//        con.accept(100);

//       List<String> names = Arrays.asList("mike", "adam", "sam");
//       Consumer<String> val = name -> System.out.println(name);
//       names.forEach(val);

//        List<String> names = Arrays.asList("mike", "adam", "sam");
//        names.forEach(name -> System.out.println(name));

        //Supplier Functional Interface
//        Supplier<Integer> x = () -> new Random().nextInt(500);
//        System.out.println(x.get());


        //Group by in stream api
        List<Employees> employees = Arrays.asList(
                new Employees("mike", 20, "chennai"),
                new Employees("adam", 30, "mumbai"),
                new Employees("sam", 40, "chennai"),
                new Employees("stalin", 50, "benguluru")
        );

        Map<String, List<Employees>> groupedRes = employees.stream().collect(Collectors.groupingBy(e -> e.getCity()));
        for(String key: groupedRes.keySet()) {
            List<Employees> emp = groupedRes.get(key);
            System.out.print(key + "- ");
            emp.forEach(e -> System.out.println(e.getName() + "," + e.getAge()));
        }

    }
}
