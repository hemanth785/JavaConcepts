package A06_DeadLocks;

import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReentrantLock;

/*
 * Deadlock: Deadlock is a situation in multithreading program, where one thread is holding firstLock,
 *  and waiting for the 2ndLock which is currently held by other thread. and other thread is currently holding 
 *  2nd lock which is required for first thread and waiting for firstLock which is currently held by first Thread
 * 
 * So in this case, none of the thread gets to progress and program halts here.
 */
public class A01_DeadLock_Basics {
  static ReentrantLock lockA = new ReentrantLock();
  static ReentrantLock lockB = new ReentrantLock();

  public static void main(String[] args){
    ExecutorService pool = Executors.newFixedThreadPool(2);

    pool.execute(new ProcessThis());
    pool.execute(new ProcessThat());
  }


  static class ProcessThis implements Runnable{
    public void run(){

      lockA.lock();
      System.out.println("This: aquired lock A");

      //Note: If we remove sleep, then sometimes deadlock may not occur
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      lockB.lock();
      System.out.println("This: aquired lock B");

      lockA.unlock();
      System.out.println("This: released lock A");

      lockB.unlock();
      System.out.println("This: released lock B");
    }
  }

  static class ProcessThat implements Runnable{
    public void run(){
      lockB.lock();
      System.out.println("That: aquired lock B");

      lockA.lock();
      System.out.println("That: aquired lock A");

      lockA.unlock();
      System.out.println("That: released lock A");

      lockB.unlock();
      System.out.println("That: released lock B");
    }
  }

}
