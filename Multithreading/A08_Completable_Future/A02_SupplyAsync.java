package A08_Completable_Future;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/*
 * If we want to recieve some result from the CompletableFuture thread, 
 *  - then we have to go ahead with 'supplyAsync' function
 * 
 * While using 'supplyAsync' method, we have to use object of 'Supplier' class and overide 'get()' method of interface, instead of 'run()' method
 * where we need to mention the 'return type' of the child thread
 */
public class A02_SupplyAsync {
  public static void main(String[] args) {

    CompletableFuture<String> future = CompletableFuture.supplyAsync(new Supplier<String>() {

      public String get(){
        try {
          Thread.sleep(2000);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println("inner thread - "+Thread.currentThread().getName());

        return "This is the result"; //we can return any value here
      }
    });

    System.out.println("Main thread - "+Thread.currentThread().getName());

    try {
      // Block and wait for the future to complete
      String res = future.get();
      System.out.println("future completed with result: "+res);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
  }
}
