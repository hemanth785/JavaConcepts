package A01_Basics;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
 * In Cached thread pool, in worst case we can expect N numbers of threads to be created.
 * N - is number of tasks given to threadpool
 * 
 * - Cached thread pool uses 'Syncronized queue', which can hold only 1 task at a time before handing it to thread
 */
public class A05_Cached_Thread_pool {
  public static void main(String[] args) {
    //Creat threadpool
    ExecutorService executorService = Executors.newCachedThreadPool();
    MyRunnableThread myRunnable = new MyRunnableThread();
    
    for(int i=1; i<20; i++){
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
