package A01_Basics;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class A03_Using_ThreadPool_Fixed {

  
  public static void main(String[] args) {
    // 1. Using ExecutorService Interface - Mininal configuration
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    MyRunnableThread myRunnable = new MyRunnableThread();
    
    for(int i=1; i<10; i++){
      Thread thread = new Thread(myRunnable);
      executorService.execute(thread);
    }

    executorService.shutdown();


    // 1. Using ThreadPoolExecutor Class - Complete cotrol over configuration of Threadpool
    ThreadPoolExecutor executor = new ThreadPoolExecutor(
    2, 
    4, 
    30, 
    TimeUnit.SECONDS, 
    new LinkedBlockingQueue<>(2) //we can use the different blocked Queue of our choice - 
    //LinkedBlockingQueue Expands as more tasks submitted
    );

    for (int i = 0; i < 6; i++) {
        executor.execute(() -> { //this is using lambda expression
            System.out.println(Thread.currentThread().getName() + " is executing a task.");
        });
    }

    executor.shutdown();
  }

  
  
}

class MyRunnableThread implements Runnable{

  public void run(){
    System.out.println("Started thread:-- "+Thread.currentThread().getName());


    System.out.println("Ended thread:-- "+Thread.currentThread().getName());
  }
}