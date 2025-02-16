package A02_Locks;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class A02_ReadWriteLock {
   public static void main(String[] args) {
    ReadWriteLock lock = new ReentrantReadWriteLock(); //here 'ReadWriteLock' is Intefaice, 'ReentrantReadWriteLock' is a implementation
    
    SharedResource resouceInstance1 = new SharedResource();
    Thread t1 = new Thread(() -> {
      resouceInstance1.fetchSharedResource(lock);
    });
    

    SharedResource resouceInstance2 = new SharedResource();
    Thread t2 = new Thread(() -> {
      resouceInstance2.updateSharedResource(lock);
    });

    t1.start();
    t2.start();
  }
}

class SharedResource {
  int resource;
  
  public void fetchSharedResource(ReadWriteLock lock){
    try {
      lock.readLock().lock();  //Read lock is shared lock, multiple thread can simultaniously get readLock and also write lock
        // Note: Do only read operations here
      
    } catch (Exception e) {
    } finally {
      lock.readLock().unlock();;
    }
  }

  public void updateSharedResource(ReadWriteLock lock){
    try {
      lock.writeLock().lock(); //If some thread acquire writeLock, none of the threads can aquire read and write lock both
        // Note: Do only read operations here
      
    } catch (Exception e) {
    } finally {
      lock.writeLock().unlock();;
    }
  }
}