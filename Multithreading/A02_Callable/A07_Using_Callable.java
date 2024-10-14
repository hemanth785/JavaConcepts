package A02_Callable;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/*
 * Note: While creating thread using 'Runnable' interface, it won't return any value from thread
 * - So if we want to return any value for spawned child thread to parent thread, 
 *      - then we need to create child thread using 'Callable' interface
 *      - In callable interface, we need to provide implementation for 'call()' method
 */

public class A07_Using_Callable {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    MyRunnableThread runnableThread = new MyRunnableThread();

    //This future is just placeholder to recieve value from child thread thread
    Future<Integer> future = executorService.submit(runnableThread); //while using callable interface, we have to use " .submit() ", instead of .execute()

    /* Note: Since future.get() is blocking code, 
     whatever the code which is not dependant on thread task result, we should right it before here */

    try {
      /* We'll get the actual return value here, once thread return some value after completing execution
      * This futute.get() is a blocking the controller, until thread execution is complete
      * One walkaround we can use is, make use of timeout interval for thread
      */
      int taskResult = future.get();
      System.out.println("Thread task result: "+taskResult);

    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }

    executorService.shutdown();
  }
}

class MyRunnableThread implements Callable<Integer>{
  public Integer call(){
    System.out.println("Started thread:-- "+Thread.currentThread().getName());
    System.out.println("Ended thread:-- "+Thread.currentThread().getName());
    System.out.println("----------");

    return new Random().nextInt(1, 10);
  }
}
