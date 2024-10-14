package A02_Callable;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class A08_Callable_with_multiple_threads {
  public static void main(String[] args) {
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    MyRunnableThread runnableThread = new MyRunnableThread();

    //When multiple threads are created in a short duration of time, we need to store all the future objects in list
    List<Future> futureList = new ArrayList<>();
    for(int i=1; i<=10; i++){
      Future<Integer> future = executorService.submit(runnableThread); 
      futureList.add(future);
    }
    

    /* Note: Since future.get() is blocking code, 
     whatever the code which is not dependant on thread task result, we should right it before here */

    try {
      // We'll get the actual return value here, once thread return some value after completing execution
      for(int i=1; i<=10; i++){
        Future<Integer> future = (Future)futureList.get(i);
        // Note: here even though thread 5 completes its task, its result will be printed only after first 4 thread completed task, and this is the drawback
        int taskResult = future.get();
        System.out.println("---Recieved result for thread/task -> "+i+", sleepTime: "+taskResult+" ---");
      }
      
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

    int waitTime = new Random().nextInt(2, 5);
    try {
      Thread.sleep(waitTime * 1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("Ended thread:----- "+Thread.currentThread().getName());
    // System.out.println("----------");

    return waitTime;
  }
}
