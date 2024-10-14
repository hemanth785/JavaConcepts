package A01_Basics;

public class A01_Extends_thread_class {

  public static void main(String[] args) {
    MyThread childThread = new MyThread();
    childThread.start();

    childThread.start(); //This is not possible, when we create thread by extensing Thread class
    //Note: if we want to spawn multiple thread for same class, then we need to make use of Runnable interface

    for(int i=1; i<=10; i++){
      System.out.println("Parent thread: "+i);
    }
  }
  
}

class MyThread extends Thread{
  public void run(){
    for(int i=1; i<=10; i++){
      System.out.println("Child thread: "+i);
    }
  }
}
