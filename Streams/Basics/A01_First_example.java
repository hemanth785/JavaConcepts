package Streams.Basics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Get and count employes with salary greater than 3000
 */
public class A01_First_example {
  public static void main(String[] args) {
    List<Integer> salaries = new ArrayList<>();

    salaries.add(40000);
    salaries.add(35000);
    salaries.add(23000);
    salaries.add(15000);
    salaries.add(53000);
    salaries.add(26000);
    salaries.add(26000);
    salaries.add(38000);
    salaries.add(44000);
    salaries.add(44000);

    List<Integer> streamResult = salaries.stream().filter((salary) -> {
      return salary > 20000;
    })
    .map(salary -> {
      return salary+1000;
    })
    .distinct()
    .sorted(Comparator.reverseOrder()) //default reverse sorting
    .sorted((a, b) -> {
      return a-b; //custom sorting
    })
    .limit(5)
    .collect(Collectors.toList()); //terminal operation

    System.out.println("Stream result 1: "+ streamResult);


    //Custom sorting using lambda expression
    List<Integer> sortedList = salaries.stream()
      .sorted( (i1, i2) -> i2.compareTo(i1) )
      .collect(Collectors.toList());


    //Reduce method - It is also a terminal operation
    Optional<Integer> totalSum = salaries.stream()
    .reduce((sumSoFar, val) -> { //val1 stores sum so far
      return sumSoFar+val;
    });

    System.out.println("Total sum: "+ totalSum.get());

    //toArrayMethod
    Integer[] salaryArray = salaries.stream()
    .sorted()
    .toArray(size -> new Integer[size]);

    System.out.println("salary Array: "+ Arrays.toString(salaryArray));
  }
  
}