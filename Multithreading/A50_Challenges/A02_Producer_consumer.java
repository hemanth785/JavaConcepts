package A50_Challenges;

import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/*
 * Question: 
 * - Implement producer consumer pattern
 * - consumer should be blocked if no item present in queue
 * - Producer should be blocked if queue becomes full
 * 
 * Solution: We can use 'BlockingQueue' datastructure, available in 'java.utils.concurrent' package
 *  Bloking queue waits the consumer if queue becomes empty and waits producer if queue becomes full.
 */

public class A02_Producer_consumer {

  public static void main(String[] args) {
    
    BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(2);
    
    //Producer 
    Runnable producer = new Runnable() { //One way to create thread using lambda expression
      public void run(){
        int item = 1000;
        for(int i=100; i<=1000; i+=100){
          try {
            
            queue.put(item); //thread blocks if queue is full
            //System.out.println("Produced item by thread "+Thread.currentThread().getName()+" -> "+item);
            Thread.sleep(200);
            
          } catch (InterruptedException e) {}
        }
      }
    };

    new Thread(producer).start();
    //new Thread(producer).start();

    //Consumer
    Runnable consumer = () -> { //another way to create thread using lambda expression
      while(true){
        try {

          int consumedItem = queue.take();
          System.out.println("consumed item by thread "+Thread.currentThread().getName()+" <- "+ consumedItem);

          Thread.sleep(2000);

        } catch (InterruptedException e) {}
      }
    };

    new Thread(consumer).start();
    new Thread(consumer).start();

    // Note: Here each message only consumed by one consumer only
  }
}
