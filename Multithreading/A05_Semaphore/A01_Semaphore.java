package A05_Semaphore;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class A01_Semaphore {
  private static Semaphore semaphore;
  public static void main(String[] args) {
    //Creat threadpool
    ExecutorService executorService = Executors.newFixedThreadPool(10);
    MyRunnableThread myRunnable = new MyRunnableThread();

    //In our code, the below resource can be accessed simultaniously by 3 threads at a time.
    semaphore = new Semaphore(3);
    
    for(int i=1; i<20; i++){
      Thread thread = new Thread(myRunnable);
      executorService.execute(thread);
    }

    executorService.shutdown();
  }

  static class MyRunnableThread implements Runnable{

    public void run(){
      System.out.println("Started thread:-- "+Thread.currentThread().getName());
  
      
  
      int sleepTime = new Random().nextInt();
      try {

        semaphore.acquire();

        // This is the resource, where we want to restrict the access to 
        // certain max number of threads at a given time 
        Thread.sleep(sleepTime);

        semaphore.release();
  
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
  
      System.out.println("Ended thread:-- "+Thread.currentThread().getName());
    }
  }
}


