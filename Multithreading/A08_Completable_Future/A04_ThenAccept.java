package A08_Completable_Future;

import java.util.concurrent.CompletableFuture;

/*
 * If we don't want to return any thing from chaining completableFuture to the main thread, 
 * - Then we can use 'thenAccept'. Usually this will be used at the end of chaining.
 */
public class A04_ThenAccept {
  public static void main(String[] args) {
    CompletableFuture.supplyAsync(() -> {
      return "Hello World !!!";
    })
    .thenApply((result1) -> {
      return result1 + ", Welcome to Multithread programing";
    })
    .thenAccept((finalResult) -> {
      System.out.println("Final result: "+ finalResult);

      //Note: here we dont have to return anything
    });
  }
}
