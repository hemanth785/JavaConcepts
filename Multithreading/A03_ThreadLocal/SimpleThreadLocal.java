package A03_ThreadLocal;

public class SimpleThreadLocal {
  public static void main(String[] args) {
    ThreadLocal<String> userContextLocal = new ThreadLocal<>();

    //Thread implemented using lambda expression
    // Thread 1
    Thread thread1 = new Thread(() -> {
      System.out.println("Thread 1");
      userContextLocal.set("Hemanth");

      try {
        Thread.sleep(3000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }

      String username = userContextLocal.get();
      System.out.println("Thread 1 username: "+username);
    });

    // Thread 2
    Thread thread2 = new Thread(() -> {
      System.out.println("Thread 2");
      userContextLocal.set("Harsha");


      String username = userContextLocal.get();
      System.out.println("Thread 2 username: "+username);
    });

    //Start thread
    thread1.start();
    thread2.start();
  }
}
