package A02_Locks;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/*
 *  "it can allow multiple limited number of threads to aquire lock"
 */
public class A04_Semaphore_Lock {
  public static void main(String[] args) {
    Semaphore lock = new Semaphore(4);
    //Note: Semaphone with permit 4 is same as, creating 4 different 'Rentrant locks' 
    //and let the tasks use whichever the lock is free - On the other way, each lock acts as single permit to access resource

    SharedResource resouceInstance1 = new SharedResource();
    Thread t1 = new Thread(() -> {
      resouceInstance1.accessSharedResource(lock);
    });
    

    SharedResource resouceInstance2 = new SharedResource();
    Thread t2 = new Thread(() -> {
      resouceInstance2.accessSharedResource(lock);
    });

    t1.start();
    t2.start();
  }
}

class SharedResource {
  int resource = 10;

  public void accessSharedResource(Semaphore lock){
    try {
      lock.acquire();  //we can also mention like 'acquire(2)'
      System.out.println("Accessing shared resource: "+ resource);
    
    } catch (Exception e) {
    } finally {
      lock.release(); //Release lock
    }
  } 
}
