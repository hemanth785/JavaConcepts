package A02_Locks;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.StampedLock;

/*
 * Re-entrant and Syncronized block will help us to apply 'Pessimistic lock' on resources
 * 
 * If we want to apply 'Optimistic lock', then we can use Stamped lock
 */
public class A03_Stamped_Lock {
  public static void main(String[] args) {
    StampedLock lock = new StampedLock();

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

  public void accessSharedResource(StampedLock lock){
    //Here we are not aquireing lock, we are creating version (stamp), 
    // and validating it after the operation is completed. I
    // if stamp is not valid, we need to revert the changes that we did
    long stamp = lock.tryOptimisticRead(); 
    
    resource = 11;

    if(lock.validate(stamp)){
      System.out.println("Successfull updated");
    } else {
      resource = 10;
      System.out.println("Rollback logic");
    }
  } 
}
