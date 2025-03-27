package A06_DeadLocks;

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

  public static void main(String[] args) {
    Thread task1 = new Thread(() -> {
      lockA.lock();
      System.out.println("Task 1 acquired lock A");

      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) { e.printStackTrace(); }

      lockB.lock();
      System.out.println("Task 1 acquired lock B");

      lockB.notify();
      System.out.println("Task 1 released lock B");

      lockA.notify();
      System.out.println("Task 1 released lock A");
  
    });

    Thread task2 = new Thread(() -> {
      lockB.lock();
      System.out.println("Task 2 acquired lock B");

      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) { e.printStackTrace(); }

      lockA.lock();
      System.out.println("Task 2 acquired lock A");

      lockA.notify();
      System.out.println("Task 2 released lock A");

      lockB.notify();
      System.out.println("Task 2 released lock B");
  
    });

    task1.start();
    task2.start();
  }

}
