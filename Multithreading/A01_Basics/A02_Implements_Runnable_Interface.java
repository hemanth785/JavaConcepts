package A01_Basics;

import A02_Callable.MyRunnableThread;

public class A02_Implements_Runnable_Interface {
  public static void main(String[] args) {
    MyRunnableThread runnableThread = new MyRunnableThread();
    Thread childThread1 = new Thread(runnableThread);
    Thread childThread2 = new Thread(runnableThread);
  
    childThread1.start();
    childThread2.start(); //We can start multiple threads using runnable interface
  
    for(int i=1; i<=10; i++){
      System.out.println("Parent thread: "+i);
    }
  }
 
}

class MyRunnableThread implements Runnable{
  public void run(){
    for(int i=1; i<=10; i++){
      System.out.println("Child thread: "+i);
    }
  }
}
