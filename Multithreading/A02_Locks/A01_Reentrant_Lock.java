package A02_Locks;

import java.util.concurrent.locks.ReentrantLock;
/*
 * Syncronized lock will apply the 'moniter' lock on entire object, and 
 * it works when 2 threads access method or critical section using the same instance/object, 
 * when called with 2 differet object, it wont work.
 */


 /*
  * Each lock acts as single permit to access the resource
  * 
  * How to decide whether we need single lock or multiple locks?
  * That depends on how much concurrency we are ready to provide on particular resource.
  */
public class A01_Reentrant_Lock {
  public static void main(String[] args) {
    ReentrantLock lock = new ReentrantLock();

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
  int resource;

  public void accessSharedResource(ReentrantLock lock){
    try {
      lock.lock(); //acquiring lock: This lock works even when this method is accessed from different isntance of this class also
      System.out.println("Accessing shared resource: "+ resource);
    
    } catch (Exception e) {
    } finally {
      lock.unlock(); //Release lock
    }
  }
}
