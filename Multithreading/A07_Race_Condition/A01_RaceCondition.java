package A07_Race_Condition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class A01_RaceCondition {
  private static int booksLeft = 1;
  //Map which stores who has loaned which book details
  private static Map<String, List<String>> loanedBooks = new HashMap<>();

  public static void main(String[] args) {
    loanedBooks.put("book1", new ArrayList<>());
    
    //Thread-1
    Runnable task1 = () -> {
      if(booksLeft > 0){
        loanedBooks.get("book1").add("User-1");
        booksLeft--;
      }
    };

    //Thead-2
    Runnable task2 = () -> {
      if(booksLeft > 0){
        loanedBooks.get("book1").add("User-2");
        booksLeft--;
      }
    };

    new Thread(task1).start();
    new Thread(task2).start();

    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      System.out.println("book1 is loaned by: "+loanedBooks.get("book1"));
      System.out.println("count: "+booksLeft);
    }

  }
}
