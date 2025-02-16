package A50_Challenges;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class A03_Custom_Blocking_queue {
  
}

class BlockingQueue<E> {
  private int capacity;
  private Queue<E> queue;
  
  private ReentrantLock lock = new ReentrantLock(true);
  private Condition notFull = lock.newCondition();
  private Condition notEmpty = lock.newCondition();

  BlockingQueue(int capacity){
    this.capacity = capacity;
    queue = new LinkedList<E>();
  }

  public void put(E item){
    lock.lock();
    
    try {
      //if queue is full, wait
      if(queue.size() == capacity){
        notFull.wait();
      }

      queue.add(item);
      notEmpty.signalAll();

    } 
    catch (InterruptedException e) {} 
    finally {
      lock.unlock();
    }
  }

  public E take(){
    lock.lock(); 
    try {
      // if queue is empty, wait
      if(queue.isEmpty()){
        notEmpty.await();
      }

      E item = queue.remove();
      notFull.signalAll();
      return item;
    } 
    catch (InterruptedException e) {}
    finally {
      lock.unlock();
    }
    return null; //just to resolve syntax error
  }
}
