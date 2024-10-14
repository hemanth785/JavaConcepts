package A08_Completable_Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
 * If we want to multiple operations one after another, in child thread asynchronousl
 *  Then we can chain each subsequent operations with 'thenApply' function, on top of 'supplyAsync'
 */
public class A03_ThenApply {
  public static void main(String[] args) {
    CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Step 1 completed - "+Thread.currentThread().getName());

      return "Hello, ";

    }).thenApply((resutl1) -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Step 2 completed - "+Thread.currentThread().getName());

      return resutl1+"Welcome to multithreading. ";

    }).thenApply((resutl2) -> {
      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println("Step 3 completed - "+Thread.currentThread().getName());

      return resutl2 + "See you/";
    });

    try {

      System.out.println(future.get()); // Only This will be a blocking code

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
