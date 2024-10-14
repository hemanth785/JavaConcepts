package A01_Basics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class A06_Scheduled_thread_pool {
   public static void main(String[] args) {
    //for scheduling of tasks
    ScheduledExecutorService executorService = Executors.newScheduledThreadPool(5);
    MyRunnableThread myRunnable = new MyRunnableThread();

    System.out.println("I am here---");
    
    //1. Task to run once after 5 seconds delay
    //executorService.schedule(myRunnable, 3, TimeUnit.SECONDS);

    //2. Task to run repeatedly after every 'x' seconds
    executorService.scheduleAtFixedRate(myRunnable, 5, 5, TimeUnit.SECONDS);
    //Note: This runs indefinitely, until and unless we shutdown executor service

    //3. Task to run repeately after 'x' seconds after previous task completes
    executorService.scheduleWithFixedDelay(myRunnable, 5, 5, TimeUnit.SECONDS);

    //executorService.shutdown();
  }
}

class MyRunnableThread implements Runnable{

  public void run(){
    System.out.println("Started thread:-- "+Thread.currentThread().getName());
    System.out.println("Ended thread:-- "+Thread.currentThread().getName());
  }
}
