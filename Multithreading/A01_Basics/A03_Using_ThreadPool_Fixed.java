package A01_Basics;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class A03_Using_ThreadPool_Fixed {
  public static void main(String[] args) {
    //Creat threadpool
    ExecutorService executorService = Executors.newFixedThreadPool(5);
    MyRunnableThread myRunnable = new MyRunnableThread();
    
    for(int i=1; i<10; i++){
      Thread thread = new Thread(myRunnable);
      executorService.execute(thread);
    }

    executorService.shutdown();
  }
  
}

class MyRunnableThread implements Runnable{

  public void run(){
    System.out.println("Started thread:-- "+Thread.currentThread().getName());


    System.out.println("Ended thread:-- "+Thread.currentThread().getName());
  }
}