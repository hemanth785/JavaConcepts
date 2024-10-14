package Streams.Basics;

import java.util.ArrayList;
import java.util.List;

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
    salaries.add(38000);
    salaries.add(44000);

    long count = salaries.stream().filter((salary) -> salary > 30000).count();

    System.out.println("Employes having salary greater than 30000: "+ count);
  }
  
}