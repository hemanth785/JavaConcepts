package Streams.Basics;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class A02_Collect_result_after_operation {
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

    Stream<Integer> salaryGreaterThanThirtyStream = salaries.stream().filter((salary) -> salary > 30000);

    List<Integer> salaryGreaterThanThirtyList = salaryGreaterThanThirtyStream.collect(Collectors.toList());

    System.out.println("salaries greater than 30000: ");
    salaryGreaterThanThirtyList.forEach((Integer salary) -> System.out.println(salary));
  }
}
