package A08_Completable_Future;

import java.util.concurrent.CompletableFuture;

/*
 * CompletableFuture.anyOf() as the name suggests, returns a new CompletableFuture which is completed 
 * when any of the given CompletableFutures complete, with the same result.
 */
public class A09_AnyOf {
  static CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
    //TODO: Do async operation
    return "Result 1";
  });

  static CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
    //TODO: Do async operation
    return "Result 2";
  });

  static CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
    //TODO: Do async operation
    return "Result 3";
  });

  public static void main(String[] args) {
    CompletableFuture<Object> anyOfFuture = CompletableFuture.anyOf(future1, future2, future3);

    Object result = anyOfFuture.join();  //we can also use .get() here
    //Here we'll the result of any of othe future, which completes first
  }
}
