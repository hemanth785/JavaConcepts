package A08_Completable_Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/*
 * If we dont want to recieve any result from the CompletableFuture thread, 
 *  - then we can use 'runAsync' function
 */
public class A01_RunAsync {
  public static void main(String[] args) {

    CompletableFuture<Void> future = CompletableFuture.runAsync(new Runnable() { //This piece of code runs on separate thread asynchronously
      
      public void run(){
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }

        System.out.println("inner thread - "+Thread.currentThread().getName());
      }
    });

    System.out.println("Main thread - "+Thread.currentThread().getName());

    try {
      // Block and wait for the future to complete
      future.get();
      System.out.println("future completed");

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }

  //Note: Completable uses ForkJoinPoll executor thread to execute asynchronus code




  /*
   * Completable future with lambda expression
   */

  public void testLambda(){
    CompletableFuture<Void> completableFuture = CompletableFuture.runAsync(() -> {
      try {
        Thread.sleep(2000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      System.out.println("inner thread - "+Thread.currentThread().getName());
    });
  }
}
