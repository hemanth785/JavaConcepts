package A50_Challenges;

/*
 * Question: Print 1 to 100 sequentially using 2 threads, 
 * - where one thread will print only even numbers
 * - and another thread will print only odd numbers
 */
public class A01_PrintEvenOddWith2Threads {
  public static void main(String[] args) {
    Thread oddThread = new Thread(new PrintEvenOdd(1), "Odd thread");
    Thread evenThread = new Thread(new PrintEvenOdd(0), "Even thread");
  
    oddThread.start();
    evenThread.start();
  }
 
}

class PrintEvenOdd implements Runnable{
  int remainder; //this is specific to object

  // it is important for this object to be static, 
  // because there should be single instance of this object for applying synchronization
  static Object obj = new Object(); 
  static int counter = 1;

  PrintEvenOdd(int remainder){
    this.remainder = remainder;
  }

  public void run(){
    while(counter < 10){

      synchronized(obj){

        while(counter%2 != remainder){
          try {
            
            obj.wait();

          } catch (InterruptedException e) {}
        }
       
        
        System.out.println( Thread.currentThread().getName()+" - "+counter);
        counter++;

        obj.notify();
      }
    }
  }
}
