package A01_Basics;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class A04_Thread_pool_on_core_count {
  //Create teh number of threads based on the number of CPU cores available

  public static void main(String[] args) {
    //Get CPU core count
    int noOfCores = Runtime.getRuntime().availableProcessors();
    System.out.println("CPU cores: "+noOfCores);
    //Creat threadpool
    ExecutorService executorService = Executors.newFixedThreadPool(noOfCores);
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