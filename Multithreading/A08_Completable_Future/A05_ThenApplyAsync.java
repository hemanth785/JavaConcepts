package A08_Completable_Future;

import java.util.concurrent.CompletableFuture;

public class A05_ThenApplyAsync {
  public static void main(String[] args) {
    //Using 'thenApply' - Uses the same thread to execute all the chained tasks sequentially,  
    //and if there are no wating period subtasks usually run on main thread itself
    System.out.println("DEMO ---- 'thenApply'");

    CompletableFuture.supplyAsync(() -> {
      //try { Thread.sleep(1000);  } catch (InterruptedException e) {}

      System.out.println("Step 1: "+Thread.currentThread().getName());
      return "Hello World !!!";
    })
    .thenApply((result1) -> {
      //try { Thread.sleep(1000);  } catch (InterruptedException e) {}

      System.out.println("Step 2: "+Thread.currentThread().getName());
      return result1 + ", Welcome to Multithread programing";
    })
    .thenApply((finalResult) -> {
      //try { Thread.sleep(1000);  } catch (InterruptedException e) {}
      
      System.out.println("Step 3: "+Thread.currentThread().getName());
      System.out.println("Final result: "+ finalResult);

      return finalResult;
    });

    //Adding sleep here, so that we can print the logs from child thread
    try { Thread.sleep(4000);  } catch (InterruptedException e) {}


    System.out.println();
    System.out.println();
    System.out.println();


    //Using 'thenApplyAsync' - Always uses thread from worker pool to execute all tasks
    System.out.println("DEMO ---- 'thenApplyAsync'");

    CompletableFuture.supplyAsync(() -> {
      //try { Thread.sleep(2000);  } catch (InterruptedException e) {}

      System.out.println("Step 1: "+Thread.currentThread().getName());
      return "Hello World !!!";
    })
    .thenApplyAsync((result1) -> {
      //try { Thread.sleep(2000);  } catch (InterruptedException e) {}

      System.out.println("Step 2: "+Thread.currentThread().getName());
      return result1 + ", Welcome to Multithread programing";
    })
    .thenApplyAsync((finalResult) -> {
      //try { Thread.sleep(2000);  } catch (InterruptedException e) {}
      
      System.out.println("Step 3: "+Thread.currentThread().getName());
      System.out.println("Final result: "+ finalResult);

      return finalResult;
    });

    //Adding sleep here, so that we can print the logs from child thread
    try { Thread.sleep(4000);  } catch (InterruptedException e) {}

  }
}
