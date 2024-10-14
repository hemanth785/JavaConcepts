package A08_Completable_Future;

import java.util.concurrent.CompletableFuture;

public class A10_Handling_Exceptions {
  /*
   * 1. Handle exceptions using "exceptionally()"" callback
   * 
   * The exceptionally() callback gives you a chance to recover from errors generated from the original Future. 
   * You can log the exception here and return a default value.
   */

  static Integer age = -1;

  CompletableFuture<String> maturityFuture1 = CompletableFuture.supplyAsync(() -> {
    if(age < 0) {
      throw new IllegalArgumentException("Age can not be negative");
    }
    if(age > 18) {
        return "Adult";
    } else {
        return "Child";
    }
  }).exceptionally(ex -> {
    System.out.println("Oops! We have an exception - " + ex.getMessage());
    return "Unknown!";
  });

  /*
   * Handle exceptions using the generic 'handle()' method
   * 
   * The API also provides a more generic method - handle() to recover from exceptions. 
   * It is called whether or not an exception occurs.
   * 
   * Note: If an exception occurs, then the res argument will be null, otherwise, the ex argument will be null.
   */

   CompletableFuture<String> maturityFuture2 = CompletableFuture.supplyAsync(() -> {
    if(age < 0) {
      throw new IllegalArgumentException("Age can not be negative");
    }
    if(age > 18) {
      return "Adult";
    } else {
      return "Child";
    }
   }).handle((res, ex) -> {
      if(ex != null) {
        System.out.println("Oops! We have an exception - " + ex.getMessage());
        return "Unknown!";
      }
      return res;
   });

}
